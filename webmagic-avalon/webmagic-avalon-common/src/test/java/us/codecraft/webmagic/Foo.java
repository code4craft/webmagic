package us.codecraft.webmagic;

import us.codecraft.forger.property.Inject;
import us.codecraft.forger.property.format.Formatter;

/**
 * @author code4crafter@gmail.com
 */
public class Foo {

    @Formatter("")
    @Inject("fooa")
    private String foo;

    public static final String SOURCE_CODE="package us.codecraft.webmagic;\n" +
            "\n" +
            "import us.codecraft.forger.property.Inject;\n" +
            "import us.codecraft.forger.property.format.Formatter;\n" +
            "\n" +
            "/**\n" +
            " * @author code4crafter@gmail.com\n" +
            " */\n" +
            "public class Foo {\n" +
            "\n" +
            "    @Formatter(\"\")\n" +
            "    @Inject(\"fooa\")\n" +
            "    private String foo;\n" +
            "\n" +
            "    public String getFoo() {\n" +
            "        return foo;\n" +
            "    }\n" +
            "\n" +
            "    public String foo() {\n" +
            "        return foo;\n" +
            "    }\n" +
            "}";

    public String getFoo() {
        return foo;
    }

    public String foo() {
        return foo;
    }
}
