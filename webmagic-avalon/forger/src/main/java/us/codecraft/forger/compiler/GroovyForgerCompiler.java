package us.codecraft.forger.compiler;

import groovy.lang.GroovyClassLoader;

/**
 * @author code4crafter@gmail.com
 */
public class GroovyForgerCompiler implements ForgerCompiler{

    private GroovyClassLoader groovyClassLoader = new GroovyClassLoader();

    @Override
    public Class compile(String sourceCode) {
        return groovyClassLoader.parseClass(sourceCode);
    }
}
