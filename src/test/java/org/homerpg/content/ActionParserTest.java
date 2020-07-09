package org.homerpg.content;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActionParserTest {
    private String xmlDocument = """
            <?xml version="1.0" encoding="utf-8" ?>
            <action name="hello">
                <alias name="truc" />
                <default>Default action description</default>
                <item name="hello">Item description</item>
            </action>""";
    private Document document = null;

    @BeforeEach
    void setUp() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        document = documentBuilder.parse(new InputSource(new StringReader(xmlDocument)));
    }

    @Test
    void parse() {
        NodeList nodes = document.getElementsByTagName("action");
        ActionParser parser = new ActionParser(nodes.item(0));
        parser.parse();
        final Action action = parser.getAction();

        final String rootName = action.getNames().get(0);
        assertEquals(rootName, "hello");

        final String aliasName = action.getNames().get(1);
        assertEquals(aliasName, "truc");

        final String defaultDesc = action.getDefaultAction();
        assertEquals(defaultDesc, "Default action description");

        final Item item = action.getItems().get(0);
        final String itemDescription = item.getDescription();
        assertEquals(itemDescription, "Item description");

        final String itemName = item.getName();
        assertEquals(itemName, "hello");
    }
}