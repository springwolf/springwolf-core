// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples.walkers.xml;

import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.media.XML;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ExampleXmlValueGeneratorTest {
    private final DefaultExampleXmlValueSerializer serializer = new DefaultExampleXmlValueSerializer();

    @Test
    void cacheShouldResolveBySchemaName() {
        // given
        ExampleXmlValueGenerator generator = new ExampleXmlValueGenerator(serializer);

        StringSchema schema1 = new StringSchema();
        schema1.setName("full.a.schema1");
        schema1.setXml(new XML().name("schema1"));

        StringSchema schema2 = new StringSchema();
        schema2.setName("full.b.schema1");
        schema2.setXml(new XML().name("schema1"));

        generator.initialize();

        Node example1 = generator
                .createStringExample("does-not-matter-for-test-1", schema1)
                .get();
        generator.prepareForSerialization(schema1, example1);

        Node cachedExample1 =
                generator.getExampleOrNull(Optional.of("fieldName1"), schema1, "does-not-matter-for-test-1");

        // when
        generator.initialize();
        Node exampleFromCache =
                generator.getExampleOrNull(Optional.of("fieldName2"), schema2, "does-not-matter-for-test-2");

        // then
        assertThat(exampleFromCache).isNotEqualTo(cachedExample1);
        assertThat(exampleFromCache).isNull();
    }

    @Test
    void cacheShouldResolveBySchemaNameAndRenameToWrappingField() {
        // given
        ExampleXmlValueGenerator generator = new ExampleXmlValueGenerator(serializer);

        StringSchema schema1 = new StringSchema();
        schema1.setName("full.a.schema1");
        schema1.setXml(new XML().name("schema1"));

        StringSchema schema2 = new StringSchema();
        schema2.setName("full.b.schema1");
        schema2.setXml(new XML().name("schema1"));

        generator.initialize();

        Node example1 = generator.createRaw("<xml><value>aValue</value></xml>");
        generator.prepareForSerialization(schema1, example1);

        Node cachedExample1 =
                generator.getExampleOrNull(Optional.of("fieldName1"), schema1, "does-not-matter-for-test-1");

        // when
        generator.initialize();
        Node exampleFromCache =
                generator.getExampleOrNull(Optional.of("fieldName2"), schema2, "does-not-matter-for-test-2");

        // then
        assertThat(((Element) cachedExample1).getTagName()).isEqualTo("fieldName1");
        assertThat(exampleFromCache).isNull();
    }

    @Test
    void cacheShouldStoreExampleBySchemaName() {
        // given
        ExampleXmlValueGenerator generator = new ExampleXmlValueGenerator(serializer);
        StringSchema schema1 = new StringSchema();
        schema1.setName("full.a.schema1");
        schema1.setXml(new XML().name("schema1"));

        StringSchema schema2 = new StringSchema();
        schema2.setName("schema1");

        generator.initialize();

        Node example1 = generator.createStringExample("example1", schema1).get();
        generator.prepareForSerialization(schema1, example1);

        generator.initialize();
        Node exampleFromCache = generator.getExampleOrNull(Optional.of("fieldName"), schema2, "example-string");

        assertThat(exampleFromCache).isNull();
    }

    @Test
    void shouldCreateRawFromXmlString() {
        // given
        ExampleXmlValueGenerator generator = new ExampleXmlValueGenerator(serializer);
        generator.initialize();

        // when
        Node result = generator.createRaw("<xml>example</xml>");

        // then
        assertNotNull(result);
    }
}
