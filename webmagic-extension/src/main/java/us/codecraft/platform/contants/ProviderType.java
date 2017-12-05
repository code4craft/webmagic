package us.codecraft.platform.contants;

/**
 * @Author :王龙
 * @Description 通信类型
 * @Data 2017/12/2 14:11
 * @Modified By：
 */
public enum ProviderType {
    ADSL("adls拨号", "us.codecraft.platform.download.proxy.provider.AdslHttpProvider", 1),
    PROXY("代理IP", "us.codecraft.platform.download.proxy.provider.ProxyHttpProvider", 2);

    private String name;
    private String className;
    private int index;

    // 构造方法
    private ProviderType(String name, String className, int index) {
        this.name = name;
        this.className = className;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public int getIndex() {
        return index;
    }

    public static String getClassName(int index) {
        for (ProviderType type : ProviderType.values()) {
            if (type.getIndex() == index) {
                return type.getClassName();
            }
        }
        return "";
    }

    @Override
    public String toString() {
        return this.getName() + ":" + this.getClassName();
    }
}
