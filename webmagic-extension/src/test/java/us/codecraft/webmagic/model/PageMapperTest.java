package us.codecraft.webmagic.model;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.selector.PlainText;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author code4crafter@gmail.com
 *         Date: 2017/6/3
 *         Time: 下午3:23
 */
public class PageMapperTest {

    public static class GithubRepo {

        @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$.name",source = ExtractBy.Source.RawText)
        private String name;

        public String getName() {
            return name;
        }
    }

    @Test
    public void test_get() throws Exception {
        PageMapper<GithubRepo> pageMapper = new PageMapper<GithubRepo>(GithubRepo.class);
        GithubRepo githubRepo = pageMapper.get(getMockJsonPage());
        assertThat(githubRepo.getName()).isEqualTo("webmagic");
    }

    private Page getMockJsonPage() throws IOException {
        Page page = new Page();
        page.setRawText(IOUtils.toString(getClass().getClassLoader().getResourceAsStream("json/mock-githubrepo.json")));
        page.setRequest(new Request("https://api.github.com/repos/code4craft/webmagic"));
        page.setUrl(new PlainText("https://api.github.com/repos/code4craft/webmagic"));
        return page;
    }
    
}
