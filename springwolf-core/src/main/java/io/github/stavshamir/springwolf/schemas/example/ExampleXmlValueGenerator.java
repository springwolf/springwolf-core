package io.github.stavshamir.springwolf.schemas.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.tuple.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

public class ExampleXmlValueGenerator implements ExampleValueGenerator<Node> {

    private Document document;

    private static final String DEFAULT_BOOLEAN_EXAMPLE = "true";

    private static final String DEFAULT_STRING_EXAMPLE = "string";

    @Override
    public void initialize() {
        try {
            document = createDocument();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Node createIntegerExample(Integer value) {
        return null;
    }

    @Override
    public Node createDoubleExample(Double value) {
        return null;
    }

    @Override
    public Node createBooleanExample() {
        return document.createTextNode(DEFAULT_BOOLEAN_EXAMPLE);
    }

    @Override
    public Node createBooleanExample(Boolean value) {
        return null;
    }

    @Override
    public Node createIntegerExample() {
        return null;
    }

    @Override
    public Node createObjectExample(String name, List<Map.Entry<String, Node>> properties) {
        if (name == null) {
            throw new IllegalArgumentException("Object Name must not be empty");
        }
        try {
            Element rootElement = document.createElement(name);

            for (Map.Entry<String, Node> propertyExample : properties) {
                rootElement.appendChild(handlePropertyExample(propertyExample));
            }

            return rootElement;
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private Element handlePropertyExample(Map.Entry<String, Node> propertyExample) throws ParserConfigurationException {
        final Node exampleValue = propertyExample.getValue();
        if (exampleValue instanceof Element) {
            return (Element) exampleValue;
        } else if (exampleValue instanceof Text) {
            Element element = document.createElement(propertyExample.getKey());
            element.appendChild(exampleValue);
            return element;
        } else {
            throw new IllegalArgumentException("Unsupported type " + exampleValue.getClass().getSimpleName());
        }
    }

    @Override
    public Node createDoubleExample() {
        return null;
    }

    @Override
    public Node generateDateExample() {
        return null;
    }

    @Override
    public Node generateDateTimeExample() {
        return null;
    }

    @Override
    public Node generateEmailExample() {
        return null;
    }

    @Override
    public Node generatePasswordExample() {
        return null;
    }

    @Override
    public Node generateByteExample() {
        return null;
    }

    @Override
    public Node generateBinaryExample() {
        return null;
    }

    @Override
    public Node generateUuidExample() {
        return null;
    }

    @Override
    public Node generateStringExample() {
        return document.createTextNode(DEFAULT_STRING_EXAMPLE);
    }

    @Override
    public Node generateStringExample(String value) {
        return null;
    }

    @Override
    public Node generateEnumExample(String anEnumValue) {
        return null;
    }

    @Override
    public Node generateUnknownSchemaStringTypeExample(String schemaType) {
        return null;
    }

    @Override
    public Node generateUnknownSchemaFormatExample(String schemaFormat) {
        return null;
    }

    @Override
    public Node wrapAsArray(List<Node> list) {
        return null;
    }

    @Override
    public String toString(String name, Node exampleObject) throws JsonProcessingException {
        final Node objectToWrite;
        if (exampleObject instanceof Element) {
            objectToWrite = exampleObject;
        } else {
            objectToWrite = createObjectExample(name, List.of(Pair.of(name, exampleObject)));
        }
        try {
            document.appendChild(objectToWrite);
            return writeDocumentAsXmlString(document);
        } catch (TransformerException e) {
            return null;
        }
    }

    @Override
    public Node createRaw(Object exampleValueString) {
        return null;
    }

    private static Document createDocument() throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.newDocument();

    }

    private String writeDocumentAsXmlString(Document document) throws TransformerException {
        DOMSource domSource = new DOMSource(document);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter sw = new StringWriter();
        StreamResult sr = new StreamResult(sw);
        transformer.transform(domSource, sr);
        return sw.toString();

    }
}
