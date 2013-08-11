package us.codecraft.webmagic.utils;

import java.io.File;

/**
 * 文件持久化的基础类。<br>
 *
 * @author code4crafter@gmail.com <br>
 *         Date: 13-8-11 <br>
 *         Time: 下午4:21 <br>
 */
public class FilePersistentBase {

    protected String path;

    public static String PATH_SEPERATOR = "/";

    static {
        String property = System.getProperties().getProperty("file.separator");
        if (property != null) {
            PATH_SEPERATOR = property;
        }
    }

    public void setPath(String path) {
        this.path = path;
        if (!path.endsWith(PATH_SEPERATOR)) {
            path += PATH_SEPERATOR;
        }
    }

    public File getFile(String fullName) {
        checkAndMakeParentDirecotry(fullName);
        return new File(fullName);
    }

    public void checkAndMakeParentDirecotry(String fullName) {
        int index = fullName.lastIndexOf(PATH_SEPERATOR);
        if (index > 0) {
            String path = fullName.substring(0, index);
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }

    public String getPath() {
        return path;
    }
}
