package us.codecraft.webmagic.pipeline;

import us.codecraft.webmagic.MultiPageModel;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.utils.Experimental;
import us.codecraft.webmagic.utils.DoubleKeyMap;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A pipeline combines the result in more than one page together.<br>
 * Used for news and articles containing more than one web page. <br>
 * MultiPagePipeline will store parts of object and output them when all parts are extracted.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
@Experimental
public class MultiPagePipeline implements Pipeline {

    private DoubleKeyMap<String, String, Boolean> pageMap = new DoubleKeyMap<String, String, Boolean>(ConcurrentHashMap.class);

    private DoubleKeyMap<String, String, MultiPageModel> objectMap = new DoubleKeyMap<String, String, MultiPageModel>(ConcurrentHashMap.class);

    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, Object> resultItemsAll = resultItems.getAll();
        Iterator<Map.Entry<String, Object>> iterator = resultItemsAll.entrySet().iterator();
        while (iterator.hasNext()) {
            handleObject(iterator);
        }
    }

    private void handleObject(Iterator<Map.Entry<String, Object>> iterator) {
        Map.Entry<String, Object> objectEntry = iterator.next();
        Object o = objectEntry.getValue();
        //需要拼凑
        if (o instanceof MultiPageModel) {
            MultiPageModel multiPageModel = (MultiPageModel) o;
            //这次处理的部分，设置为完成
            pageMap.put(multiPageModel.getPageKey(), multiPageModel.getPage(), Boolean.FALSE);
            //每个key单独加锁
            synchronized (pageMap.get(multiPageModel.getPageKey())) {
                pageMap.put(multiPageModel.getPageKey(), multiPageModel.getPage(), Boolean.TRUE);
                //其他需要拼凑的部分
                if (multiPageModel.getOtherPages() != null) {
                    for (String otherPage : multiPageModel.getOtherPages()) {
                        Boolean aBoolean = pageMap.get(multiPageModel.getPageKey(), otherPage);
                        if (aBoolean == null) {
                            pageMap.put(multiPageModel.getPageKey(), otherPage, Boolean.FALSE);
                        }
                    }
                }
                //check if all pages are processed
                Map<String, Boolean> booleanMap = pageMap.get(multiPageModel.getPageKey());
                objectMap.put(multiPageModel.getPageKey(), multiPageModel.getPage(), multiPageModel);
                if (booleanMap == null) {
                    return;
                }
                // /过滤，这次完成的page item中，还未拼凑完整的item，不进入下一个pipeline
                for (Map.Entry<String, Boolean> stringBooleanEntry : booleanMap.entrySet()) {
                    if (!stringBooleanEntry.getValue()) {
                        iterator.remove();
                        return;
                    }
                }
                List<Map.Entry<String, MultiPageModel>> entryList = new ArrayList<Map.Entry<String, MultiPageModel>>();
                entryList.addAll(objectMap.get(multiPageModel.getPageKey()).entrySet());
                if (entryList.size() != 0) {
                    Collections.sort(entryList, new Comparator<Map.Entry<String, MultiPageModel>>() {
                        @Override
                        public int compare(Map.Entry<String, MultiPageModel> o1, Map.Entry<String, MultiPageModel> o2) {
                            try {
                                int i1 = Integer.parseInt(o1.getKey());
                                int i2 = Integer.parseInt(o2.getKey());
                                return i1 - i2;
                            } catch (NumberFormatException e) {
                                return o1.getKey().compareTo(o2.getKey());
                            }
                        }
                    });
                    // 合并
                    MultiPageModel value = entryList.get(0).getValue();
                    for (int i = 1; i < entryList.size(); i++) {
                        value = value.combine(entryList.get(i).getValue());
                    }
                    objectEntry.setValue(value);
                }
            }
        }

    }

}
