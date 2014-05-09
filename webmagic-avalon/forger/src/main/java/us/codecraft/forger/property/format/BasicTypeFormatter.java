package us.codecraft.forger.property.format;

import java.util.Arrays;
import java.util.List;

/**
 * @author code4crafter@gmail.com
 * @since 0.3.2
 */
public abstract class BasicTypeFormatter<T> implements TypeFormatter<T> {

    @Override
    public T format(String text) {
        if (text == null) {
            return null;
        }
        text = text.trim();
        return formatTrimmed(text);
    }

    @Override
    public T format(String text, String[] params) {
        return format(text);
    }

    protected abstract T formatTrimmed(String raw);

    public static final List<Class<? extends TypeFormatter>> basicTypeFormatters = Arrays.<Class<? extends TypeFormatter>>asList(IntegerFormatter.class,
            LongFormatter.class, DoubleFormatter.class, FloatFormatter.class, ShortFormatter.class,
            CharactorFormatter.class, ByteFormatter.class, BooleanFormatter.class, DateFormatter.class, StringFormatter.class);

    public static Class<?> detectBasicClass(Class<?> type) {
        if (type.equals(Integer.TYPE) || type.equals(Integer.class)) {
            return Integer.class;
        } else if (type.equals(Long.TYPE) || type.equals(Long.class)) {
            return Long.class;
        } else if (type.equals(Double.TYPE) || type.equals(Double.class)) {
            return Double.class;
        } else if (type.equals(Float.TYPE) || type.equals(Float.class)) {
            return Float.class;
        } else if (type.equals(Short.TYPE) || type.equals(Short.class)) {
            return Short.class;
        } else if (type.equals(Character.TYPE) || type.equals(Character.class)) {
            return Character.class;
        } else if (type.equals(Byte.TYPE) || type.equals(Byte.class)) {
            return Byte.class;
        } else if (type.equals(Boolean.TYPE) || type.equals(Boolean.class)) {
            return Boolean.class;
        }
        return type;
    }

    public static class IntegerFormatter extends BasicTypeFormatter<Integer> {
        @Override
        public Integer formatTrimmed(String raw) {
            return Integer.parseInt(raw);
        }

        @Override
        public Class<Integer> clazz() {
            return Integer.class;
        }
    }

    public static class LongFormatter extends BasicTypeFormatter<Long> {
        @Override
        public Long formatTrimmed(String raw) {
            return Long.parseLong(raw);
        }

        @Override
        public Class<Long> clazz() {
            return Long.class;
        }
    }

    public static class DoubleFormatter extends BasicTypeFormatter<Double> {
        @Override
        public Double formatTrimmed(String raw) {
            return Double.parseDouble(raw);
        }

        @Override
        public Class<Double> clazz() {
            return Double.class;
        }
    }

    public static class FloatFormatter extends BasicTypeFormatter<Float> {
        @Override
        public Float formatTrimmed(String raw) {
            return Float.parseFloat(raw);
        }

        @Override
        public Class<Float> clazz() {
            return Float.class;
        }
    }

    public static class ShortFormatter extends BasicTypeFormatter<Short> {
        @Override
        public Short formatTrimmed(String raw) {
            return Short.parseShort(raw);
        }

        @Override
        public Class<Short> clazz() {
            return Short.class;
        }
    }

    public static class CharactorFormatter extends BasicTypeFormatter<Character> {
        @Override
        public Character formatTrimmed(String raw) {
            return raw.charAt(0);
        }

        @Override
        public Class<Character> clazz() {
            return Character.class;
        }
    }

    public static class ByteFormatter extends BasicTypeFormatter<Byte> {
        @Override
        public Byte formatTrimmed(String raw) {
            return Byte.parseByte(raw, 10);
        }

        @Override
        public Class<Byte> clazz() {
            return Byte.class;
        }
    }

    public static class BooleanFormatter extends BasicTypeFormatter<Boolean> {
        @Override
        public Boolean formatTrimmed(String raw) {
            return Boolean.parseBoolean(raw);
        }

        @Override
        public Class<Boolean> clazz() {
            return Boolean.class;
        }
    }

    public static class StringFormatter implements TypeFormatter<String> {

        @Override
        public String format(String text) {
            return text;
        }

        @Override
        public String format(String text, String[] params) {
            return format(text);
        }

        @Override
        public Class<String> clazz() {
            return String.class;
        }
    }


}
