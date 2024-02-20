// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.example;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class ExampleXmlValueGenerator implements ExampleValueGenerator<Node, String> {

    private final Set<String> SUPPORTED_CONTENT_TYPES = Set.of("text/xml", "application/xml");

    private final ExampleXmlValueSerializer exampleXmlValueSerializer;

    private Document document;

    private final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    private final Map<String, Node> exampleCache = new HashMap<>();

    public ExampleXmlValueGenerator(ExampleXmlValueSerializer exampleXmlValueSerializer) {
        this.exampleXmlValueSerializer = exampleXmlValueSerializer;
    }

    @Override
    public boolean canHandle(String contentType) {
        return SUPPORTED_CONTENT_TYPES.contains(contentType);
    }

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
    public Node createObjectExample(String name, List<PropertyExample<Node>> properties) {
        if (name == null) {
            throw new IllegalArgumentException("Object name must not be empty");
        }
        try {
            Element rootElement = document.createElement(name);

            for (PropertyExample<Node> propertyExample : properties) {
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

    private Element handlePropertyExample(PropertyExample<Node> propertyExample) throws ParserConfigurationException {
        final Node exampleValue = propertyExample.example();
        if (exampleValue instanceof Element) {
            return (Element) exampleValue;
        } else if (exampleValue instanceof Text) {
            return wrapNode(propertyExample.name(), exampleValue);
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
        return document.createTextNode("unknown schema type: " + schemaType);
    }

    @Override
    public Node generateUnknownSchemaFormatExample(String schemaFormat) {
        return document.createTextNode("unknown string schema format: " + schemaFormat);
    }

    @Override
    public Node generateArrayExample(Node arrayItem) {
        return arrayItem;
    }

    @Override
    public String prepareForSerialization(String name, Node exampleObject) {
        final Node objectToWrite;
        if (exampleObject instanceof Element) {
            objectToWrite = exampleObject;
        } else {
            objectToWrite = wrapNode(name, exampleObject);
        }
        try {
            document.appendChild(objectToWrite);
            String xml = exampleXmlValueSerializer.writeDocumentAsXmlString(document);
            log.debug("name {} -> xml: {}", name, xml);

            exampleCache.putIfAbsent(name, exampleObject);
            return xml;
        } catch (TransformerException | DOMException e) {
            log.error("Serialize {}", name, e);
            return null;
        }
    }

    @Override
    public Node createRaw(Object exampleValue) {
        return readXmlString(exampleValue.toString());
    }

    @Override
    public Node exampleOrNull(String name, Object example) {
        if (example instanceof Node) {
            return (Node) example;
        }

        if (exampleCache.containsKey(name)) {
            return this.document.importNode(exampleCache.get(name), true);
        }

        return null;
    }

    @Override
    public Node createEmptyObjectExample() {
        return document.createTextNode("");
    }

    private Document createDocument() throws ParserConfigurationException {
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.newDocument();
    }

    private Node readXmlString(String xmlString) {
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            return documentBuilder.parse(xmlString);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            log.info("Unable to convert example to XMl Node: {}", xmlString, e);
        }
        return null;
    }
}
