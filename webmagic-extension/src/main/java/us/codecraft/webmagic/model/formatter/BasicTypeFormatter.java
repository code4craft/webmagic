package us.codecraft.webmagic.model.formatter;

import java.util.Arrays;
import java.util.List;

/**
 * @author code4crafter@gmail.com
 * @since 0.3.2
 */
public abstract class BasicTypeFormatter<T> implements ObjectFormatter<T> {

    @Override
    public void initParam(String[] extra) {

    }

    @Override
    public T format(String raw) throws Exception {
        if (raw == null) {
            return null;
        }
        raw = raw.trim();
        return formatTrimmed(raw);
    }

    protected abstract T formatTrimmed(String raw) throws Exception;
    public static final List<Class<? extends ObjectFormatter>> basicTypeFormatters = Arrays.<Class<? extends ObjectFormatter>>asList(IntegerFormatter.class,
            LongFormatter.class, DoubleFormatter.class, FloatFormatter.class, ShortFormatter.class,
            CharactorFormatter.class, ByteFormatter.class, BooleanFormatter.class);
    public static final List<BasicClassDetector> basicClassDetector= Arrays.asList(new IntegerClassDetector(),
            new LongClassDetector(),
            new FloatClassDetector(),
            new DoubleClassDetector(),
            new ShortClassDetector(),
            new ByteClassDetector(),
            new BooleanClassDetector(),
            new CharacterClassDetector());

    public static Class<?> detectBasicClass(Class<?> type) {
        for (BasicClassDetector detector : basicClassDetector) {
            Class<?> detectedClass = detector.detectBasicClass(type);
            if (detectedClass != null) {
                return detectedClass;
            }
        }
        return type;
    }

    public static class IntegerFormatter extends BasicTypeFormatter<Integer> {
        @Override
        public Integer formatTrimmed(String raw) throws Exception {
            return Integer.parseInt(raw);
        }

        @Override
        public Class<Integer> clazz() {
            return Integer.class;
        }
    }

    public static class LongFormatter extends BasicTypeFormatter<Long> {
        @Override
        public Long formatTrimmed(String raw) throws Exception {
            return Long.parseLong(raw);
        }

        @Override
        public Class<Long> clazz() {
            return Long.class;
        }
    }

    public static class DoubleFormatter extends BasicTypeFormatter<Double> {
        @Override
        public Double formatTrimmed(String raw) throws Exception {
            return Double.parseDouble(raw);
        }

        @Override
        public Class<Double> clazz() {
            return Double.class;
        }
    }

    public static class FloatFormatter extends BasicTypeFormatter<Float> {
        @Override
        public Float formatTrimmed(String raw) throws Exception {
            return Float.parseFloat(raw);
        }

        @Override
        public Class<Float> clazz() {
            return Float.class;
        }
    }

    public static class ShortFormatter extends BasicTypeFormatter<Short> {
        @Override
        public Short formatTrimmed(String raw) throws Exception {
            return Short.parseShort(raw);
        }

        @Override
        public Class<Short> clazz() {
            return Short.class;
        }
    }

    public static class CharactorFormatter extends BasicTypeFormatter<Character> {
        @Override
        public Character formatTrimmed(String raw) throws Exception {
            return raw.charAt(0);
        }

        @Override
        public Class<Character> clazz() {
            return Character.class;
        }
    }

    public static class ByteFormatter extends BasicTypeFormatter<Byte> {
        @Override
        public Byte formatTrimmed(String raw) throws Exception {
            return Byte.parseByte(raw, 10);
        }

        @Override
        public Class<Byte> clazz() {
            return Byte.class;
        }
    }

    public static class BooleanFormatter extends BasicTypeFormatter<Boolean> {
        @Override
        public Boolean formatTrimmed(String raw) throws Exception {
            return Boolean.parseBoolean(raw);
        }

        @Override
        public Class<Boolean> clazz() {
            return Boolean.class;
        }
    }

}
