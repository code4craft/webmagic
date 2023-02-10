package us.codecraft.webmagic.selector;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import net.sf.saxon.lib.NamespaceConstant;
import net.sf.saxon.xpath.XPathEvaluator;
import us.codecraft.webmagic.utils.BaseSelectorUtils;

import static us.codecraft.webmagic.selector.JaxpSelectorUtils.*;

/**
 * 支持xpath2.0的选择器。包装了HtmlCleaner和Saxon HE。<br>
 *
 * @author code4crafter@gmail.com, hooy <br>
 * Date: 13-4-21
 * Time: 上午9:39
 */
public class Xpath2Selector implements Selector, NodeSelector {

    private final String xpathStr;

    private XPathExpression xPathExpression;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Xpath2Selector(String xpathStr) {
        this.xpathStr = xpathStr;
        try {
            init();
        } catch (XPathExpressionException e) {
            throw new IllegalArgumentException("XPath error!", e);
        }
    }

    public static Xpath2Selector newInstance(String xpathStr) {
        return new Xpath2Selector(xpathStr);
    }

    enum XPath2NamespaceContext implements NamespaceContext {

        INSTANCE;

        private final Map<String, String> prefix2NamespaceMap = new ConcurrentHashMap<>();

        private final Map<String, List<String>> namespace2PrefixMap = new ConcurrentHashMap<>();

        private void put(String prefix, String namespaceURI) {
            prefix2NamespaceMap.put(prefix, namespaceURI);
            List<String> prefixes = namespace2PrefixMap.computeIfAbsent(namespaceURI, k -> new ArrayList<>());
            prefixes.add(prefix);
        }

        XPath2NamespaceContext() {
            put("fn", NamespaceConstant.FN);
            put("xslt", NamespaceConstant.XSLT);
            put("xhtml", NamespaceConstant.XHTML);
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
            Document doc = parse(text);
            return select(doc);
        } catch (Exception e) {
            logger.error("select text error! " + xpathStr, e);
        }
        return null;
    }

    @Override
    public String select(Node node) {
        try {
            return (String) xPathExpression.evaluate(node, XPathConstants.STRING);
        } catch (Exception e) {
            logger.error("select text error! " + xpathStr, e);
        }
        return null;
    }

    @Override
    public List<String> selectList(String text) {
        try {
            Document doc = parse(text);
            return selectList(doc);
        } catch (Exception e) {
            logger.error("select text error! " + xpathStr, e);
        }
        return null;
    }

    @Override
    public List<String> selectList(Node node) {
        try {
            NodeList result = (NodeList) xPathExpression.evaluate(node, XPathConstants.NODESET);
            List<Node> nodes = NodeListToArrayList(result);
            return nodesToStrings(nodes);
        } catch (Exception e) {
            logger.error("select text error! " + xpathStr, e);
        }
        return null;
    }

    public Node selectNode(String text) {
        try {
            Document doc = parse(text);
            return selectNode(doc);
        } catch (Exception e) {
            logger.error("select text error! " + xpathStr, e);
        }
        return null;
    }

    public Node selectNode(Node node) {
        try {
            return (Node) xPathExpression.evaluate(node, XPathConstants.NODE);
        } catch (Exception e) {
            logger.error("select text error! " + xpathStr, e);
        }
        return null;
    }

    public List<Node> selectNodes(String text) {
        try {
            Document doc = parse(text);
            return selectNodes(doc);
        } catch (Exception e) {
            logger.error("select text error! " + xpathStr, e);
        }
        return null;
    }

    public List<Node> selectNodes(Node node) {
        try {
            NodeList result = (NodeList) xPathExpression.evaluate(node, XPathConstants.NODESET);
            return NodeListToArrayList(result);
        } catch (Exception e) {
            logger.error("select text error! " + xpathStr, e);
        }
        return null;
    }

    protected static Document parse(String text) throws ParserConfigurationException {
        // HtmlCleaner could not parse <tr></tr> or <td></td> tag directly
        text = BaseSelectorUtils.preParse(text);
        HtmlCleaner htmlCleaner = new HtmlCleaner();
        TagNode tagNode = htmlCleaner.clean(text);
        return new DomSerializer(new CleanerProperties()).createDOM(tagNode);
    }

}
