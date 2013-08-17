package us.codecraft.webmagic.pipeline;

import us.codecraft.webmagic.MultiPageModel;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.model.annotation.Experimental;
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
        if (o instanceof MultiPageModel) {
            MultiPageModel multiPageModel = (MultiPageModel) o;
            pageMap.put(multiPageModel.getPageKey(), multiPageModel.getPage(), Boolean.TRUE);
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
                MultiPageModel value = entryList.get(0).getValue();
                for (int i = 1; i < entryList.size(); i++) {
                    value = value.combine(entryList.get(i).getValue());
                }
                objectEntry.setValue(value);
            }
        }
    }

}
