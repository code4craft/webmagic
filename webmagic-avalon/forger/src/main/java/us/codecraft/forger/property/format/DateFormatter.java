package us.codecraft.forger.property.format;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * @author code4crafter@gmail.com
 * @since 0.3.2
 */
public class DateFormatter implements TypeFormatter<Date> {

    public static final String[] DEFAULT_PATTERN = new String[]{"yyyy-MM-dd HH:mm"};

    @Override
    public Date format(String text) {
        return format(text,DEFAULT_PATTERN);
    }

    @Override
    public Date format(String text, String[] params) {
        try {
            return DateUtils.parseDate(text, params);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Class<Date> clazz() {
        return Date.class;
    }

}
