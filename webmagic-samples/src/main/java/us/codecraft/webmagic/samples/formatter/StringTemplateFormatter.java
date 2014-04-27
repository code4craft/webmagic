package us.codecraft.webmagic.samples.formatter;

import us.codecraft.webmagic.model.formatter.ObjectFormatter;

/**
 * @author yihua.huang@dianping.com
 */
public class StringTemplateFormatter implements ObjectFormatter<String> {

    private String template;

    @Override
    public String format(String raw) throws Exception {
        return String.format(template, raw);
    }

    @Override
    public Class<String> clazz() {
        return String.class;
    }

    @Override
    public void initParam(String[] extra) {
        template = extra[0];
    }
}
