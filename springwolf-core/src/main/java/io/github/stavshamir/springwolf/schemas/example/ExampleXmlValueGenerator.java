package io.github.stavshamir.springwolf.schemas.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

public class ExampleXmlValueGenerator implements ExampleValueGenerator<XmlNode<?>> {

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
    public XmlNode createIntegerExample(Integer value) {
        return null;
    }

    @Override
    public XmlNode createDoubleExample(Double value) {
        return null;
    }

    @Override
    public XmlNode<?> createBooleanExample() {
        return new XmlValueNode(DEFAULT_BOOLEAN_EXAMPLE);
    }

    @Override
    public XmlNode createBooleanExample(Boolean value) {
        return null;
    }

    @Override
    public XmlNode createIntegerExample() {
        return null;
    }

    @Override
    public XmlNode createObjectExample(String name, List<Map.Entry<String, XmlNode<?>>> properties) {
        try {
            Element rootElement = document.createElement(name);

            for (Map.Entry<String, XmlNode<?>> propertyExample : properties) {
                rootElement.appendChild(handlePropertyExample(propertyExample));
            }

            return new XmlElementNode(rootElement);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private Element handlePropertyExample(Map.Entry<String, XmlNode<?>> propertyExample) throws ParserConfigurationException {
        final XmlNode<?> exampleValue = propertyExample.getValue();
        if (exampleValue instanceof XmlElementNode) {
            return ((XmlElementNode) exampleValue).getValue();
        } else if (exampleValue instanceof XmlValueNode) {
            Element element = document.createElement(propertyExample.getKey());
            Text exampleValueNode = document.createTextNode(((XmlValueNode) exampleValue).getValue());
            element.appendChild(exampleValueNode);
            return element;
        } else {
            throw new IllegalArgumentException("Unsupported type " + exampleValue.getClass().getSimpleName());
        }
    }

    @Override
    public XmlNode createDoubleExample() {
        return null;
    }

    @Override
    public XmlNode generateDateExample() {
        return null;
    }

    @Override
    public XmlNode generateDateTimeExample() {
        return null;
    }

    @Override
    public XmlNode generateEmailExample() {
        return null;
    }

    @Override
    public XmlNode generatePasswordExample() {
        return null;
    }

    @Override
    public XmlNode generateByteExample() {
        return null;
    }

    @Override
    public XmlNode generateBinaryExample() {
        return null;
    }

    @Override
    public XmlNode generateUuidExample() {
        return null;
    }

    @Override
    public XmlNode generateStringExample() {
        return new XmlValueNode(DEFAULT_STRING_EXAMPLE);
    }

    @Override
    public XmlNode generateStringExample(String value) {
        return null;
    }

    @Override
    public XmlNode generateEnumExample(String anEnumValue) {
        return null;
    }

    @Override
    public XmlNode generateUnknownSchemaStringTypeExample(String schemaType) {
        return null;
    }

    @Override
    public XmlNode generateUnknownSchemaFormatExample(String schemaFormat) {
        return null;
    }

    @Override
    public XmlNode wrapAsArray(List<XmlNode<?>> list) {
        return null;
    }

    @Override
    public String toString(XmlNode exampleObject) throws JsonProcessingException {
        if (exampleObject instanceof XmlElementNode) {
            try {
                document.appendChild(((XmlElementNode) exampleObject).getValue());
                return writeDocumentAsXmlString(document);
            } catch (TransformerException e) {
                return null;
            }
        } else {
            throw new IllegalArgumentException("XML must have a root node");
        }
    }

    @Override
    public XmlNode createRaw(Object exampleValueString) {
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
