package us.codecraft.webmagic.exception;

/**
 * @author code4crafter@gmail.com
 */
public class DynamicClassCompileException extends Exception{

    public DynamicClassCompileException(String message) {
        super(message);
    }

    public DynamicClassCompileException(String message, Throwable cause) {
        super(message, cause);
    }
}
