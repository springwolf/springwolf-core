// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.StringUtils;
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

    private static final Boolean DEFAULT_BOOLEAN_EXAMPLE = true;

    private static final String DEFAULT_STRING_EXAMPLE = "string";

    private static final Integer DEFAULT_INTEGER_EXAMPLE = 0;

    private static final Double DEFAULT_NUMBER_EXAMPLE = 1.1;

    private static final String DEFAULT_DATE_EXAMPLE = "2015-07-20";
    private static final String DEFAULT_DATE_TIME_EXAMPLE = "2015-07-20T15:49:04-07:00";
    private static final String DEFAULT_PASSWORD_EXAMPLE = "string-password";
    private static final String DEFAULT_BYTE_EXAMPLE = "YmFzZTY0LWV4YW1wbGU=";
    private static final String DEFAULT_BINARY_EXAMPLE =
            "0111010001100101011100110111010000101101011000100110100101101110011000010110010001111001";

    private static final String DEFAULT_EMAIL_EXAMPLE = "example@example.com";
    private static final String DEFAULT_UUID_EXAMPLE = "3fa85f64-5717-4562-b3fc-2c963f66afa6";

    private static String DEFAULT_UNKNOWN_SCHEMA_EXAMPLE(String type) {
        return "unknown schema type: " + type;
    }

    private static String DEFAULT_UNKNOWN_SCHEMA_STRING_EXAMPLE(String format) {
        return "unknown string schema format: " + format;
    }

    @Override
    public boolean canHandle(String contentType) {
        return (StringUtils.equals(contentType, "text/xml") || StringUtils.equals(contentType, "application/xml"));
    }

    @Override
    public void initialize() {
        try {
            if (document == null) {
                document = createDocument();
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Node createIntegerExample(Integer value) {
        return document.createTextNode(value.toString());
    }

    @Override
    public Node createDoubleExample(Double value) {
        return document.createTextNode(value.toString());
    }

    @Override
    public Node createBooleanExample() {
        return createBooleanExample(DEFAULT_BOOLEAN_EXAMPLE);
    }

    @Override
    public Node createBooleanExample(Boolean value) {
        return document.createTextNode(value.toString());
    }

    @Override
    public Node createIntegerExample() {
        return createIntegerExample(DEFAULT_INTEGER_EXAMPLE);
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

    private Element wrapNode(String name, Node toWrap) {
        Element rootElement = document.createElement(name);
        rootElement.appendChild(toWrap);
        return rootElement;
    }

    private Element handlePropertyExample(Map.Entry<String, Node> propertyExample) throws ParserConfigurationException {
        final Node exampleValue = propertyExample.getValue();
        if (exampleValue instanceof Element) {
            return (Element) exampleValue;
        } else if (exampleValue instanceof Text) {
            return wrapNode(propertyExample.getKey(), exampleValue);
        } else {
            throw new IllegalArgumentException(
                    "Unsupported type " + exampleValue.getClass().getSimpleName());
        }
    }

    @Override
    public Node createDoubleExample() {
        return createDoubleExample(DEFAULT_NUMBER_EXAMPLE);
    }

    @Override
    public Node generateDateExample() {
        return document.createTextNode(DEFAULT_DATE_EXAMPLE);
    }

    @Override
    public Node generateDateTimeExample() {
        return document.createTextNode(DEFAULT_DATE_TIME_EXAMPLE);
    }

    @Override
    public Node generateEmailExample() {
        return document.createTextNode(DEFAULT_EMAIL_EXAMPLE);
    }

    @Override
    public Node generatePasswordExample() {
        return document.createTextNode(DEFAULT_PASSWORD_EXAMPLE);
    }

    @Override
    public Node generateByteExample() {
        return document.createTextNode(DEFAULT_BYTE_EXAMPLE);
    }

    @Override
    public Node generateBinaryExample() {
        return document.createTextNode(DEFAULT_BINARY_EXAMPLE);
    }

    @Override
    public Node generateUuidExample() {
        return document.createTextNode(DEFAULT_UUID_EXAMPLE);
    }

    @Override
    public Node generateStringExample() {
        return generateStringExample(DEFAULT_STRING_EXAMPLE);
    }

    @Override
    public Node generateStringExample(String value) {
        return document.createTextNode(value);
    }

    @Override
    public Node generateEnumExample(String anEnumValue) {
        return generateStringExample(anEnumValue);
    }

    @Override
    public Node generateUnknownSchemaStringTypeExample(String schemaType) {
        return document.createTextNode(DEFAULT_UNKNOWN_SCHEMA_EXAMPLE(schemaType));
    }

    @Override
    public Node generateUnknownSchemaFormatExample(String schemaFormat) {
        return document.createTextNode(DEFAULT_UNKNOWN_SCHEMA_STRING_EXAMPLE(schemaFormat));
    }

    @Override
    public Node generateArrayExample(Node arrayItem) {
        return arrayItem;
    }

    @Override
    public String toString(String name, Node exampleObject) throws JsonProcessingException {
        final Node objectToWrite;
        if (exampleObject instanceof Element) {
            objectToWrite = exampleObject;
        } else {
            objectToWrite = wrapNode(name, exampleObject);
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
        // TODO unused or in test to fix
        return null;
    }

    @Override
    public Node exampleOrNull(Object example) {
        if (example instanceof Node) {
            return (Node) example;
        }

        return null;
    }

    @Override
    public Node createEmptyObjectExample() {
        return document.createTextNode("");
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
