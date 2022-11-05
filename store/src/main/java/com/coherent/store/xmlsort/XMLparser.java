package com.coherent.store.xmlsort;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMLparser {
    private static final String CONFIG_FILE = "store/src/main/resources/config.xml";

    public Map<String, String> getMap() {
        Map<String, String> map = new LinkedHashMap<>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(CONFIG_FILE));
            document.getDocumentElement().normalize();

            if (document.getFirstChild().hasChildNodes()) {
                NodeList nodes = document.getFirstChild().getChildNodes();
                for (int i = 0; i < nodes.getLength(); i++) {
                    Node node = nodes.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        map.put(node.getNodeName(), node.getTextContent());
                    }
                }
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}