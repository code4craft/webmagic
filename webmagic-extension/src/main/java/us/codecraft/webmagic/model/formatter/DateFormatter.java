package us.codecraft.webmagic.model.formatter;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * @author code4crafter@gmail.com
 * @since 0.3.2
 */
public class DateFormatter implements ObjectFormatter<Date> {

    private String[] datePatterns = new String[]{"yyyy-MM-dd HH:mm"};

    @Override
    public Date format(String raw) throws Exception {
        return DateUtils.parseDate(raw, datePatterns);
    }

    @Override
    public Class<Date> clazz() {
        return Date.class;
    }

    @Override
    public void initParam(String[] extra) {
        datePatterns = extra;
    }
}
