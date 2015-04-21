package us.codecraft.webmagic.formatter;

import org.junit.Test;
import us.codecraft.webmagic.model.formatter.DateFormatter;

import java.util.Date;

/**
 * @author code4crafter@gmail.com
 */
public class DateFormatterTest {

    @Test
    public void testDateFormatter() throws Exception {
        DateFormatter dateFormatter = new DateFormatter();
        dateFormatter.initParam(new String[]{"yyyy-MM-dd HH:mm"});
        Date format = dateFormatter.format("2013-09-10 22:11");
        System.out.println(format);
    }
}
