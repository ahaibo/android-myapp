package hai.com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Administrator on 2017/6/3.
 */

public class DomParseTest {

    private Document document;

    @Before
    public void before() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
        document = documentBuilder.parse("test.xml");
    }

    @Test
    public void list() {
        if (null == document) return;
        Element element = document.getDocumentElement();
        System.out.println(element.getNodeName());
        NodeList nodeList = element.getChildNodes();
        iteratorNodeList(nodeList);
    }

    private void iteratorNodeList(NodeList nodeList) {
        if (nodeList.getLength() == 0) return;
        System.out.print("\t");
        for (int i = 0, j = nodeList.getLength(); i < j; i++) {
            Node node = nodeList.item(i);
            System.out.print(node.getNodeName());
            System.out.println();
            iteratorNodeList(node.getChildNodes());
        }
    }


}
