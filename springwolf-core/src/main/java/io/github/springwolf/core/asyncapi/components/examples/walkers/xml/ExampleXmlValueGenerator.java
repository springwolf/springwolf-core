// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples.walkers.xml;

import io.github.springwolf.core.asyncapi.components.examples.walkers.ExampleValueGenerator;
import io.github.springwolf.core.asyncapi.components.examples.walkers.PropertyExample;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
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
    private final Schema<String> OVERRIDE_SCHEMA = new StringSchema();

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
    public String lookupSchemaName(Schema schema) {
        if (schema.getXml() != null) {
            return schema.getXml().getName();
        }
        return schema.getName();
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
    public Node createDateExample() {
        return document.createTextNode(DEFAULT_DATE_EXAMPLE);
    }

    @Override
    public Node createDateTimeExample() {
        return document.createTextNode(DEFAULT_DATE_TIME_EXAMPLE);
    }

    @Override
    public Node createEmailExample() {
        return document.createTextNode(DEFAULT_EMAIL_EXAMPLE);
    }

    @Override
    public Node createPasswordExample() {
        return document.createTextNode(DEFAULT_PASSWORD_EXAMPLE);
    }

    @Override
    public Node createByteExample() {
        return document.createTextNode(DEFAULT_BYTE_EXAMPLE);
    }

    @Override
    public Node createBinaryExample() {
        return document.createTextNode(DEFAULT_BINARY_EXAMPLE);
    }

    @Override
    public Node createUuidExample() {
        return document.createTextNode(DEFAULT_UUID_EXAMPLE);
    }

    @Override
    public Node createStringExample() {
        return createStringExample(DEFAULT_STRING_EXAMPLE);
    }

    @Override
    public Node createStringExample(String value) {
        return document.createTextNode(value);
    }

    @Override
    public Node createEnumExample(String anEnumValue) {
        return createStringExample(anEnumValue);
    }

    @Override
    public Node createUnknownSchemaStringTypeExample(String schemaType) {
        return document.createTextNode("unknown schema type: " + schemaType);
    }

    @Override
    public Node createUnknownSchemaStringFormatExample(String schemaFormat) {
        return document.createTextNode("unknown string schema format: " + schemaFormat);
    }

    @Override
    public Node createArrayExample(Node arrayItem) {
        return arrayItem;
    }

    @Override
    public String prepareForSerialization(Schema schema, Node exampleObject) {
        final String name = lookupSchemaName(schema);

        final Node objectToWrite;
        if (exampleObject instanceof Element) {
            objectToWrite = exampleObject;
        } else {
            objectToWrite = wrapNode(name, exampleObject);
        }
        try {
            document.appendChild(objectToWrite);

            String xml = exampleXmlValueSerializer.writeDocumentAsXmlString(document);
            exampleCache.putIfAbsent(name, exampleObject);

            // spec workaround to embedded xml examples as string https://github.com/asyncapi/spec/issues/1038
            schema.setType(OVERRIDE_SCHEMA.getType());
            schema.setTypes(OVERRIDE_SCHEMA.getTypes());

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
    public Node getExampleOrNull(String name, Object example) {
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
