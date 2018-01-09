package us.codecraft.webmagic.utils;

import us.codecraft.webmagic.Request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author code4crafter@gmail.com
 *         Date: 2017/6/5
 *         Time: 下午4:58
 */
public abstract class RequestUtils {

    private static Pattern p4Range = Pattern.compile("\\[(\\d+)\\-(\\d+)\\]");

    public static List<Request> from(String exp){
        Matcher matcher = p4Range.matcher(exp);
        if (!matcher.find()) {
            return Collections.singletonList(new Request(exp));
        }
        int rangeFrom = Integer.parseInt(matcher.group(1));
        int rangeTo = Integer.parseInt(matcher.group(2));
        if (rangeFrom > rangeTo) {
            return Collections.emptyList();
        }
        List<Request> requests = new ArrayList<Request>(rangeTo - rangeFrom + 1);
        for (int i = rangeFrom; i <= rangeTo; i++) {
            requests.add(new Request(matcher.replaceAll(String.valueOf(i))));
        }
        return requests;
    }

}
