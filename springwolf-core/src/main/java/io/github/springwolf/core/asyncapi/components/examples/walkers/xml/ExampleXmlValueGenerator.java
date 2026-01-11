// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples.walkers.xml;

import io.github.springwolf.core.asyncapi.components.examples.walkers.ExampleValueGenerator;
import io.github.springwolf.core.asyncapi.components.examples.walkers.PropertyExample;
import io.github.springwolf.core.asyncapi.components.examples.walkers.SchemaWalker;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;

@Slf4j
public class ExampleXmlValueGenerator implements ExampleValueGenerator<Node, String> {

    private final Set<String> SUPPORTED_CONTENT_TYPES =
            Set.of(MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_XML_VALUE);
    private final Schema<String> OVERRIDE_SCHEMA = new StringSchema();

    private final ExampleXmlValueSerializer exampleXmlValueSerializer;

    private Document document;

    private final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    private final Map<String, Node> exampleCache = new HashMap<>();

    private final Stack<Element> nodeStack = new Stack<>();

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
            nodeStack.clear();
            document = createDocument();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<String> lookupSchemaName(Schema schema) {
        if (schema.getXml() != null && schema.getXml().getName() != null) {
            return Optional.of(schema.getXml().getName());
        }
        return Optional.ofNullable(schema.getName());
    }

    @Override
    public Optional<Node> createIntegerExample(Integer value, Schema schema) {
        return createNodeOrAddAttribute(value.toString(), schema);
    }

    @Override
    public Optional<Node> createDoubleExample(Double value, Schema schema) {
        return createNodeOrAddAttribute(value.toString(), schema);
    }

    @Override
    public Optional<Node> createBooleanExample(Boolean value, Schema schema) {
        return createNodeOrAddAttribute(value.toString(), schema);
    }

    @Override
    public Element startObject(Optional<String> name) {
        return nodeStack.push(document.createElement(name.orElseThrow(
                () -> new SchemaWalker.ExampleGeneratingException("There is no name set for Schema"))));
    }

    @Override
    public void endObject() {
        nodeStack.pop();
    }

    @Override
    public void addPropertyExamples(Node object, List<PropertyExample<Node>> properties) {
        if (object == null) {
            throw new IllegalArgumentException("Element to add properties must not be empty");
        }
        try {

            for (PropertyExample<Node> propertyExample : properties) {
                object.appendChild(handlePropertyExample(propertyExample));
            }

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
        if (exampleValue instanceof Element element) {
            return element;
        } else if (exampleValue instanceof Text) {
            return wrapNode(propertyExample.name(), exampleValue);
        } else {
            throw new IllegalArgumentException(
                    "Unsupported type " + exampleValue.getClass().getSimpleName());
        }
    }

    @Override
    public Optional<Node> createStringExample(String value, Schema schema) {
        return createNodeOrAddAttribute(value, schema);
    }

    @Override
    public Optional<Node> createEnumExample(String anEnumValue, Schema schema) {
        return createStringExample(anEnumValue, schema);
    }

    @Override
    public Optional<Node> createUnknownSchemaStringTypeExample(String schemaType) {
        return Optional.of(document.createTextNode("unknown schema type: " + schemaType));
    }

    @Override
    public Optional<Node> createUnknownSchemaStringFormatExample(String schemaFormat) {
        return Optional.of(document.createTextNode("unknown string schema format: " + schemaFormat));
    }

    @Override
    public Node createArrayExample(Optional<String> arrayNameOptional, Node arrayItem) {
        return arrayNameOptional
                .map(arrayName -> wrapNode(arrayName, arrayItem))
                .orElseThrow(() -> new SchemaWalker.ExampleGeneratingException(
                        "Unable to add array item to array, because the array schema does not have a name"));
    }

    @Override
    public String prepareForSerialization(Schema schema, Node exampleObject) {
        final Optional<Node> objectToWrite;
        if (exampleObject instanceof Element) {
            objectToWrite = Optional.of(exampleObject);
        } else {
            objectToWrite = lookupSchemaName(schema).map(name -> wrapNode(name, exampleObject));
        }

        if (objectToWrite.isEmpty()) {
            return null;
        }

        try {
            document.appendChild(objectToWrite.get());

            String xml = exampleXmlValueSerializer.writeDocumentAsXmlString(document);
            exampleCache.putIfAbsent(getCacheKey(schema), exampleObject);

            // spec workaround to embedded xml examples as string https://github.com/asyncapi/spec/issues/1038
            schema.setType(OVERRIDE_SCHEMA.getType());
            schema.setTypes(OVERRIDE_SCHEMA.getTypes());

            return xml;
        } catch (TransformerException | DOMException e) {
            log.warn("Unable to serialize example for schema {}", schema.getName(), e);
            return null;
        }
    }

    @Override
    public Node createRaw(Object exampleValue) {
        return readXmlString(exampleValue.toString());
    }

    @Override
    public Node getExampleOrNull(Optional<String> fieldName, Schema schema, Object example) {
        String name = getCacheKey(schema);

        if (example instanceof Node node) {
            return node;
        }

        if (exampleCache.containsKey(name)) {
            Node oldElement = exampleCache.get(name);
            Node newElement = modifyElementFromCacheIfNeeded(
                    oldElement,
                    fieldName.orElseThrow(
                            () -> new SchemaWalker.ExampleGeneratingException("There is no name set for Schema")));
            return this.document.importNode(newElement, true);
        }

        return null;
    }

    private Node modifyElementFromCacheIfNeeded(Node oldElement, String fieldName) {
        if (oldElement instanceof Element) {
            // check if the wrapping xml-tag needs to be different than the example from the cache
            Document doc = oldElement.getOwnerDocument();
            return doc.renameNode(oldElement, null, fieldName);
        }
        return oldElement;
    }

    @Override
    public Optional<Node> createEmptyObjectExample() {
        return Optional.of(document.createTextNode(""));
    }

    private String getCacheKey(Schema schema) {
        return schema.getName();
    }

    private Document createDocument() throws ParserConfigurationException {
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.newDocument();
    }

    private Node readXmlString(String xmlString) {
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xmlString));
            Element element = documentBuilder.parse(is).getDocumentElement();
            return document.importNode(element, true);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            log.info("Unable to convert example to XMl Node: {}", xmlString, e);
        }
        return null;
    }

    private Optional<Node> createNodeOrAddAttribute(String value, Schema schema) {
        if (!nodeStack.isEmpty() && isAttribute(schema)) {
            Element currentParent = nodeStack.peek();
            lookupSchemaName(schema).ifPresent(name -> currentParent.setAttribute(name, value));
            return Optional.empty();
        } else {
            return Optional.of(document.createTextNode(value));
        }
    }

    private boolean isAttribute(Schema schema) {
        return schema.getXml() != null
                && schema.getXml().getAttribute() != null
                && schema.getXml().getAttribute();
    }
}
