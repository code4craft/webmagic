package us.codecraft.forger;

import us.codecraft.forger.compiler.ForgerCompiler;
import us.codecraft.forger.property.PropertyLoader;

/**
 * @author code4crafter@gmail.com
 */
public class ForgerFactory {

    private final PropertyLoader propertyLoader;

    private final ForgerCompiler forgerCompiler;

    public ForgerFactory(PropertyLoader propertyLoader, ForgerCompiler forgerCompiler) {
        this.propertyLoader = propertyLoader;
        this.forgerCompiler = forgerCompiler;
    }

    public <T> Forger<T> compile(String sourceCode) {
        Class clazz = forgerCompiler.compile(sourceCode);
        return new Forger(clazz, propertyLoader);
    }

    public <T> Forger<T> create(Class clazz) {
        return new Forger(clazz, propertyLoader);
    }
}
