package us.codecraft.webmagic.selector;

import net.sf.saxon.lib.NamespaceConstant;
import net.sf.saxon.xpath.XPathEvaluator;
import org.apache.log4j.Logger;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.namespace.NamespaceContext;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 支持xpath2.0的选择器。包装了HtmlCleaner和Saxon HE。<br>
 *
 * @author code4crafter@gmail.com <br>
 *         Date: 13-4-21
 *         Time: 上午9:39
 */
public class Xpath2Selector implements Selector {

    private String xpathStr;

    private XPathExpression xPathExpression;

    private Logger logger = Logger.getLogger(getClass());

    public Xpath2Selector(String xpathStr) {
        this.xpathStr = xpathStr;
        try {
            init();
        } catch (XPathExpressionException e) {
            throw new IllegalArgumentException("XPath error!", e);
        }
    }

    enum XPath2NamespaceContext implements NamespaceContext {

        INSTANCE;

        private final Map<String, String> prefix2NamespaceMap = new ConcurrentHashMap<String, String>();

        private final Map<String, List<String>> namespace2PrefixMap = new ConcurrentHashMap<String, List<String>>();

        private void put(String prefix, String namespaceURI) {
            prefix2NamespaceMap.put(prefix, namespaceURI);
            List<String> prefixes = namespace2PrefixMap.get(namespaceURI);
            if (prefixes == null) {
                prefixes = new ArrayList<String>();
                namespace2PrefixMap.put(namespaceURI, prefixes);
            }
            prefixes.add(prefix);
        }

        private XPath2NamespaceContext() {
            put("fn", NamespaceConstant.FN);
            put("xslt", NamespaceConstant.XSLT);
        }

        @Override
        public String getNamespaceURI(String prefix) {
            return prefix2NamespaceMap.get(prefix);
        }

        @Override
        public String getPrefix(String namespaceURI) {
            List<String> prefixes = namespace2PrefixMap.get(namespaceURI);
            if (prefixes == null || prefixes.size() < 1) {
                return null;
            }
            return prefixes.get(0);
        }

        @Override
        public Iterator getPrefixes(String namespaceURI) {
            List<String> prefixes = namespace2PrefixMap.get(namespaceURI);
            if (prefixes == null || prefixes.size() < 1) {
                return null;
            }
            return prefixes.iterator();
        }
    }

    private void init() throws XPathExpressionException {
        XPathEvaluator xPathEvaluator = new XPathEvaluator();
        xPathEvaluator.setNamespaceContext(XPath2NamespaceContext.INSTANCE);
        xPathExpression = xPathEvaluator.compile(xpathStr);
    }

    @Override
    public String select(String text) {
        try {
            HtmlCleaner htmlCleaner = new HtmlCleaner();
            TagNode tagNode = htmlCleaner.clean(text);
            Document document = new DomSerializer(new CleanerProperties()).createDOM(tagNode);
            Object result;
            try {
                result = xPathExpression.evaluate(document, XPathConstants.NODESET);
            } catch (XPathExpressionException e) {
                result = xPathExpression.evaluate(document, XPathConstants.STRING);
            }
            if (result instanceof NodeList) {
                NodeList nodeList = (NodeList) result;
                if (nodeList.getLength() == 0) {
                    return null;
                }
                Node item = nodeList.item(0);
                if (item.getNodeType() == Node.ATTRIBUTE_NODE || item.getNodeType() == Node.TEXT_NODE) {
                    return item.getTextContent();
                } else {
                    StreamResult xmlOutput = new StreamResult(new StringWriter());
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                    transformer.transform(new DOMSource(item), xmlOutput);
                    return xmlOutput.getWriter().toString();
                }
            }
            return result.toString();
        } catch (Exception e) {
            logger.error("select text error! " + xpathStr, e);
        }
        return null;
    }

    @Override
    public List<String> selectList(String text) {
        List<String> results = new ArrayList<String>();
        try {
            HtmlCleaner htmlCleaner = new HtmlCleaner();
            TagNode tagNode = htmlCleaner.clean(text);
            Document document = new DomSerializer(new CleanerProperties()).createDOM(tagNode);
            Object result;
            try {
                result = xPathExpression.evaluate(document, XPathConstants.NODESET);
            } catch (XPathExpressionException e) {
                result = xPathExpression.evaluate(document, XPathConstants.STRING);
            }
            if (result instanceof NodeList) {
                NodeList nodeList = (NodeList) result;
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                StreamResult xmlOutput = new StreamResult();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node item = nodeList.item(i);
                    if (item.getNodeType() == Node.ATTRIBUTE_NODE || item.getNodeType() == Node.TEXT_NODE) {
                        results.add(item.getTextContent());
                    } else {
                        xmlOutput.setWriter(new StringWriter());
                        transformer.transform(new DOMSource(item), xmlOutput);
                        results.add(xmlOutput.getWriter().toString());
                    }
                }
            } else {
                results.add(result.toString());
            }
        } catch (Exception e) {
            logger.error("select text error! " + xpathStr, e);
        }
        return results;
    }
}
