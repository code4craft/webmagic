package us.codecraft.forger.compiler;

import org.junit.Test;
import us.codecraft.forger.Foo;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author code4crafter@gmail.com
 */
public class GroovyForgerCompilerTest {

    @Test
    public void testGroovyClassLoader() throws Exception {
        GroovyForgerCompiler groovyForgerCompiler = new GroovyForgerCompiler();
        Class compiledClass = groovyForgerCompiler.compile(Foo.SOURCE_CODE);
        assertThat(compiledClass.getName()).isEqualTo("Foo");
    }
}
