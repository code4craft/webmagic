package us.codecraft.webmagic.pipeline;

import us.codecraft.webmagic.PagedModel;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.utils.DoubleKeyMap;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用于实现分页的Pipeline。<br>
 * 在使用redis做分布式爬虫时，请不要使用此功能。<br>
 *
 * @author code4crafter@gmail.com <br>
 * @date: 13-8-4 <br>
 * Time: 下午5:15 <br>
 */
public class PagedPipeline implements Pipeline {

    private DoubleKeyMap<String, String, Boolean> pageMap = new DoubleKeyMap<String, String, Boolean>(ConcurrentHashMap.class);

    private DoubleKeyMap<String, String, PagedModel> objectMap = new DoubleKeyMap<String, String, PagedModel>(ConcurrentHashMap.class);

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
        if (o instanceof PagedModel) {
            PagedModel pagedModel = (PagedModel) o;
            pageMap.put(pagedModel.getPageKey(), pagedModel.getPage(), Boolean.TRUE);
            if (pagedModel.getOtherPages() != null) {
                for (String otherPage : pagedModel.getOtherPages()) {
                    Boolean aBoolean = pageMap.get(pagedModel.getPageKey(), otherPage);
                    if (aBoolean == null) {
                        pageMap.put(pagedModel.getPageKey(), otherPage, Boolean.FALSE);
                    }
                }
            }
            //check if all pages are processed
            Map<String, Boolean> booleanMap = pageMap.get(pagedModel.getPageKey());
            objectMap.put(pagedModel.getPageKey(), pagedModel.getPage(), pagedModel);
            if (booleanMap == null) {
                return;
            }
            for (Map.Entry<String, Boolean> stringBooleanEntry : booleanMap.entrySet()) {
                if (!stringBooleanEntry.getValue()) {
                    iterator.remove();
                    return;
                }
            }
            List<Map.Entry<String, PagedModel>> entryList = new ArrayList<Map.Entry<String, PagedModel>>();
            entryList.addAll(objectMap.get(pagedModel.getPageKey()).entrySet());
            if (entryList.size() != 0) {
                Collections.sort(entryList, new Comparator<Map.Entry<String, PagedModel>>() {
                    @Override
                    public int compare(Map.Entry<String, PagedModel> o1, Map.Entry<String, PagedModel> o2) {
                        try {
                            int i1 = Integer.parseInt(o1.getKey());
                            int i2 = Integer.parseInt(o2.getKey());
                            return i1 - i2;
                        } catch (NumberFormatException e) {
                            return o1.getKey().compareTo(o2.getKey());
                        }
                    }
                });
                PagedModel value = entryList.get(0).getValue();
                for (int i = 1; i < entryList.size(); i++) {
                    value = value.combine(entryList.get(i).getValue());
                }
                objectEntry.setValue(value);
            }
        }
    }

}
