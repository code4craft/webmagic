package us.codecraft.webmagic.selector;

import java.util.ArrayList;
import java.util.List;

/**
 * All extractors will do extracting separately, <br>
 * and the results of extractors will combined as the final result.
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
public class OrSelector implements Selector {

    private List<Selector> selectors = new ArrayList<Selector>();

    public OrSelector(Selector... selectors) {
        for (Selector selector : selectors) {
            this.selectors.add(selector);
        }
    }

    public OrSelector(List<Selector> selectors) {
        this.selectors = selectors;
    }

    @Override
    public String select(String text) {
        for (Selector selector : selectors) {
            String result = selector.select(text);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    @Override
    public List<String> selectList(String text) {
        List<String> results = new ArrayList<String>();
        for (Selector selector : selectors) {
            List<String> strings = selector.selectList(text);
            results.addAll(strings);
        }
        return results;
    }
}
