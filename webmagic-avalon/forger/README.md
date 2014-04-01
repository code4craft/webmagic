forger
======

Dynamic Java object generator with template class and configuration.

## Compiler

Use groovy compiler. Compile source code to Java class.

## PropertyLoader

Load properties of object from user input.

## API

```java
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
```