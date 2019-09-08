package org.clirpg.content;

import org.clirpg.PlaceContent;
import org.clirpg.Resources;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Reader {
    private String fullPath;

    public Reader(final String filename) {
        fullPath = Resources.getInstance().getFronFilename();
    }

    public PlaceContent readContent() {
        Document document = openDocument();

        String name = document.getName();
        String description = document.getDescription();
        List<Action> actions = document.getActions();

        PlaceContent content = new PlaceContent();
        content.setName(name);
        content.setDescription(description);
        content.setActions(actions);

        return content;
    }

    private Document openDocument() {
        File file = new File(fullPath);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();

        DocumentBuilder documentBuilder = null;

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("An error occured. Unable to build " +
                    "the xml document");
            System.exit(1);
        }

        org.w3c.dom.Document document = null;
        try {
            document = documentBuilder.parse(file);
        } catch (SAXException | IOException e) {
            System.err.println("An error occured. Unable to parse " +
                    "the xml document");
            System.exit(1);
        }

        return new Document(document);
    }
}
