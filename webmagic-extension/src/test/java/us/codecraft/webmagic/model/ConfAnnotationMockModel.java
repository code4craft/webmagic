package us.codecraft.webmagic.model;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.annotation.Configurable;

@Configurable()
public class ConfAnnotationMockModel {
	
	private String f1;
	private String f2;
	private String f3;
	private String f4;
	private List<String> f5;
	
	@Test
	public void testModel(){
		String agent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36";
		String url = "http://www.liqucn.com/yx/401967.shtml";
		Site site = Site.me().setUserAgent(agent);
		ConfAnnotationMockModel model = OOSpider.create(site, ConfAnnotationMockModel.class).setSpawnUrl(false).<ConfAnnotationMockModel>get(url);
		
		assertNotNull(model);
		assertNotNull(model.f1);
		System.out.println(model.f1);
		assertNotNull(model.f2);
		System.out.println(model.f2);
		assertNotNull(model.f3);
		System.out.println(model.f3);
		assertNotNull(model.f4);
		System.out.println(model.f4);
		assertNotNull(model.f5);
		System.out.println(model.f5);
	}
}
