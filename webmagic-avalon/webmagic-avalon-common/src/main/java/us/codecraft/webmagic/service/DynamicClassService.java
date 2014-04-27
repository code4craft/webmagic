package us.codecraft.webmagic.service;

import us.codecraft.webmagic.exception.DynamicClassCompileException;

/**
 * @author code4crafter@gmail.com
 */
public interface DynamicClassService {

    public Class compileAndSave(String sourceCode) throws DynamicClassCompileException;

}
