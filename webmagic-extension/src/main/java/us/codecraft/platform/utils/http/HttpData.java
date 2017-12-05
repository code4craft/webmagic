package us.codecraft.platform.utils.http;

import org.apache.http.HttpEntity;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/***
 * 
 * Title: HttpData.java<br>
 * Description: Http请求数据类<br>
 * Company: www.e7code.com<br>
 * 
 * @author ssr
 * @author 2016年8月20日
 */
public final class HttpData {
    // 编码
    private String charset = "UTF-8";
    // 上传文件
    private Map<String, Object> files = null;
    // 请求头参数
    private Map<String, String> headers = null;
    // 表单参数
    private Map<String, String> params = null;
    // 请求体
    private HttpEntity postEntity = null;

    /***
     * 添加请求头参数
     * 
     * @param name
     *            参数名称
     * @param value
     *            参数值
     * @return HttpData对象本身
     */
    public HttpData addHeader(String name, Object value) {
        if (name != null && value != null) {
            if (headers == null) {
                headers = new HashMap<> ();
            }
            headers.put(name, String.valueOf(value));
        }
        return this;
    }

    /***
     * 添加表单参数
     * 
     * @param name
     *            参数名称
     * @param value
     *            参数值
     * @return HttpData对象本身
     */
    public HttpData addParam(String name, Object value) {
        if (name != null && value != null) {
            if (params == null) {
                params = new HashMap<> ();
            }
            params.put(name, String.valueOf(value));
        }
        return this;
    }

    /***
     * 添加文件
     * 
     * @param name
     *            参数名称
     * @param value
     *            参数值
     * @return HttpData对象本身
     */
    public HttpData addFile(String name, File value) {
        if (name != null && value != null) {
            if (files == null) {
                files = new HashMap<> ();
            }
            files.put(name, value);
        }
        return this;
    }

    /***
     * 添加文件流
     * 
     * @param paramName
     *            参数名称
     * @param value
     *            文件流
     * @param fileName
     *            文件名名称
     * @return HttpData对象本身
     */
    public HttpData addFile(String paramName, InputStream value, String fileName) {
        if (paramName != null && value != null && fileName != null) {
            if (files == null){
                files = new HashMap<>();
            }
            Object[] item = new Object[2];
            item[0] = value;
            item[1] = fileName;
            files.put(paramName, item);
        }
        return this;
    }

    /***
     * 添加文件字节数组
     * 
     * @param paramName
     *            参数名称
     * @param value
     *            文件字节数组
     * @param value
     *            文件名
     * @return HttpData对象本身
     */
    public HttpData addFile(String paramName, byte[] value, String fileName) {
        if (paramName != null && value != null && fileName != null) {
            if (files == null) {
                files = new HashMap<> ();
            }
            Object[] item = new Object[2];
            item[0] = value;
            item[1] = fileName;
            files.put(paramName, item);
        }
        return this;
    }

    // --------------------------------------------------------
    /** 获取编码：默认UTF-8 */
    public String getCharset() {
        return charset;
    }

    /** 设置编码：默认UTF-8 */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    /** 获取请求体：默认为null */
    public HttpEntity getPostEntity() {
        return postEntity;
    }

    /**
     * 设置post请求体：设置后addParam和addFile添加的参数将失效，常用请求体如下：
     * 1、StringEntity：字符串体，常用于webservice，提交xml格式内容； 2、InputStreamEntity：流
     * 3、ByteArrayEntity：字符数组 4、FileEntity：文件
     */
    public void setPostEntity(HttpEntity postEntity) {
        this.postEntity = postEntity;
    }

    /** 获取上传文件列表：无上传文件返回null */
    public Map<String, Object> getFiles() {
        return files;
    }

    /** 获取请求头参数列表：无参数返回null */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /** 获取参数列表：无参数返回null */
    public Map<String, String> getParams() {
        return params;
    }

}