package us.codecraft.forger;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import us.codecraft.forger.compiler.GroovyForgerCompiler;
import us.codecraft.forger.property.AnnotationPropertyLoader;
import us.codecraft.forger.property.SimpleFieldPropertyLoader;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * @author code4crafter@gmail.com
 */
public class ForgerFactoryTest {

    @Test
    public void testForgerCreateByClassProperty() throws Exception {
        ForgerFactory forgerFactory = new ForgerFactory(new SimpleFieldPropertyLoader(), null);
        Forger<Foo> forger = forgerFactory.<Foo>create(Foo.class);
        Foo foo = forger.forge(ImmutableMap.<String, Object>of("foo", "test"));
        assertThat(foo.getFoo()).isEqualTo("test");
    }

    @Test
      public void testForgerCreateByClassAnnotation() throws Exception {
        ForgerFactory forgerFactory = new ForgerFactory(new AnnotationPropertyLoader(), null);
        Forger<Foo> forger = forgerFactory.<Foo>create(Foo.class);
        Foo foo = forger.forge(ImmutableMap.<String, Object>of("fooa", "test"));
        assertThat(foo.getFoo()).isEqualTo("test");
    }

    @Test
    public void testForgerCreateByClassAnnotationCompile() throws Exception {
        ForgerFactory forgerFactory = new ForgerFactory(new AnnotationPropertyLoader(), new GroovyForgerCompiler());
        Forger<Fooable> forger = forgerFactory.<Fooable>compile(Foo.SOURCE_CODE);
        Fooable foo = forger.forge(ImmutableMap.<String, Object>of("fooa", "test"));
        Field field = forger.getClazz().getDeclaredField("foo");
        field.setAccessible(true);
        assertThat(field.get(foo)).isEqualTo("test");
        assertThat(foo.foo()).isEqualTo("test");
    }

    @Test
    public void testForgerCreateByClassAnnotationWithCollections() throws Exception {
        ForgerFactory forgerFactory = new ForgerFactory(new AnnotationPropertyLoader(), null);
        Forger<Bar> forger = forgerFactory.<Bar>create(Bar.class);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("bar", "bar");
        Map<String, String> submap = new HashMap<String, String>();
        submap.put("1", "1");
        submap.put("2", "2");
        map.put("idMap", submap);
        List<String> sublist = new ArrayList<String>();
        sublist.add("test");
        map.put("values", sublist);
        Bar forge = forger.forge(map);
        assertThat(forge.getValues().size() > 0);
        assertThat(forge.getIdMap().get("1")).isEqualTo(1);
    }
}
