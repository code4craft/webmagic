package us.codecraft.webmagic.selector;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author hooy
 */
public final class JaxpSelectorUtils {

    private JaxpSelectorUtils() {
        throw new RuntimeException("The util class cannot be instanced");
    }

    public static List<Node> NodeListToArrayList(NodeList nodes) {
        List<Node> list = new ArrayList<>(nodes.getLength());
        for (int i = 0; i < nodes.getLength(); i++) {
            list.add(nodes.item(i));
        }
        return list;
    }

    public static String nodeToString(Node node) throws TransformerException {
        List<Node> before = Collections.singletonList(node);
        List<String> after = nodesToStrings(before);
        if (after.size() > 0) {
            return after.get(0);
        } else {
            return null;
        }
    }

    public static List<String> nodesToStrings(List<Node> nodes) throws TransformerException {
        List<String> results = new ArrayList<>(nodes.size());
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        StreamResult xmlOutput = new StreamResult();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        for (Node node : nodes) {
            if (node.getNodeType() == Node.ATTRIBUTE_NODE || node.getNodeType() == Node.TEXT_NODE) {
                results.add(node.getTextContent());
            } else {
                xmlOutput.setWriter(new StringWriter());
                transformer.transform(new DOMSource(node), xmlOutput);
                results.add(xmlOutput.getWriter().toString());
            }
        }
        return results;
    }

}
