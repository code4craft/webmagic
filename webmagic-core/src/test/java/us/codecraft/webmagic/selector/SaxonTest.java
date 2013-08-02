package us.codecraft.webmagic.selector;

import net.sf.saxon.xpath.XPathFactoryImpl;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactoryConfigurationException;

/**
 * @author yihua.huang@dianping.com <br>
 * @date: 13-8-2 <br>
 * Time: 下午5:48 <br>
 */
public class SaxonTest {

    @Test
    public void test() throws XPathFactoryConfigurationException {
//        System.setProperty("javax.xml.xpath.XPathFactory:" + NamespaceConstant.OBJECT_MODEL_SAXON, "net.sf.saxon.xpath.XPathFactoryImpl");
//        XPathFactory xpf = XPathFactory.newInstance(NamespaceConstant.OBJECT_MODEL_SAXON);
        String xml = "<root><a>#BBB#</a><a>#CCC#</a><b><a>#DDD#</a></b></root>";
        try {
            HtmlCleaner htmlCleaner = new HtmlCleaner();
            TagNode tagNode = htmlCleaner.clean("");
            Document document = new DomSerializer(new CleanerProperties()).createDOM(tagNode);

            javax.xml.xpath.XPathFactory factory = XPathFactoryImpl.newInstance();
            XPath xpath = factory.newXPath();
            XPathExpression expr = xpath.compile("//a[matches(.,'#...#')]");

            Object result = expr.evaluate(document, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            System.out.println(nodes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
