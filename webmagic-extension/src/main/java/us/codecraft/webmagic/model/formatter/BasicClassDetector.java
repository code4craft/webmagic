package us.codecraft.webmagic.model.formatter;

public interface BasicClassDetector {
    Class<?> detectBasicClass(Class<?> type);
}

class IntegerClassDetector implements BasicClassDetector {
    @Override
    public Class<?> detectBasicClass(Class<?> type) {
        if (type.equals(Integer.TYPE) || type.equals(Integer.class)) {
            return Integer.class;
        }
        return null;
    }
}

class LongClassDetector implements BasicClassDetector {
    @Override
    public Class<?> detectBasicClass(Class<?> type) {
        if (type.equals(Long.TYPE) || type.equals(Long.class)) {
            return Long.class;
        }
        return null;
    }
}

class DoubleClassDetector implements BasicClassDetector {
    @Override
    public Class<?> detectBasicClass(Class<?> type) {
        if (type.equals(Double.TYPE) || type.equals(Double.class)) {
            return Double.class;
        }
        return null;
    }
}

class FloatClassDetector implements BasicClassDetector {
    @Override
    public Class<?> detectBasicClass(Class<?> type) {
        if (type.equals(Float.TYPE) || type.equals(Float.class)) {
            return Float.class;
        }
        return null;
    }
}

class ShortClassDetector implements BasicClassDetector {
    @Override
    public Class<?> detectBasicClass(Class<?> type) {
        if (type.equals(Short.TYPE) || type.equals(Short.class)) {
            return Short.class;
        }
        return null;
    }
}

class CharacterClassDetector implements BasicClassDetector {
    @Override
    public Class<?> detectBasicClass(Class<?> type) {
        if (type.equals(Character.TYPE) || type.equals(Character.class)) {
            return Character.class;
        }
        return null;
    }
}

class ByteClassDetector implements BasicClassDetector {
    @Override
    public Class<?> detectBasicClass(Class<?> type) {
        if (type.equals(Byte.TYPE) || type.equals(Byte.class)) {
            return Byte.class;
        }
        return null;
    }
}

class BooleanClassDetector implements BasicClassDetector {
    @Override
    public Class<?> detectBasicClass(Class<?> type) {
        if (type.equals(Boolean.TYPE) || type.equals(Boolean.class)) {
            return Boolean.class;
        }
        return null;
    }
}
