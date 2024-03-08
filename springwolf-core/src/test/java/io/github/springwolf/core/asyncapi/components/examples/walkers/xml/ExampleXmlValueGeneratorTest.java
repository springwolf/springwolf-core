// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples.walkers.xml;

import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.media.XML;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;

import static org.assertj.core.api.Assertions.assertThat;

class ExampleXmlValueGeneratorTest {

    @Test
    void cacheShouldResolveBySchemaName() {
        // given
        ExampleXmlValueGenerator generator = new ExampleXmlValueGenerator(new DefaultExampleXmlValueSerializer());

        StringSchema schema1 = new StringSchema();
        schema1.setName("full.a.schema1");
        schema1.setXml(new XML().name("schema1"));

        StringSchema schema2 = new StringSchema();
        schema2.setName("full.b.schema1");
        schema2.setXml(new XML().name("schema1"));

        generator.initialize();
        Node example1 = generator.createStringExample();
        generator.prepareForSerialization(schema1, example1);

        Node cachedExample1 = generator.getExampleOrNull(schema1, "does-not-matter-for-test-1");

        // when
        generator.initialize();
        Node exampleFromCache = generator.getExampleOrNull(schema2, "does-not-matter-for-test-2");

        // then
        assertThat(exampleFromCache).isNotEqualTo(cachedExample1);
        assertThat(exampleFromCache).isNull();
    }

    @Test
    void cacheShouldStoreExampleBySchemaName() {

        ExampleXmlValueGenerator generator = new ExampleXmlValueGenerator(new DefaultExampleXmlValueSerializer());

        StringSchema schema1 = new StringSchema();
        schema1.setName("full.a.schema1");
        schema1.setXml(new XML().name("schema1"));

        StringSchema schema2 = new StringSchema();
        schema2.setName("schema1");

        generator.initialize();
        Node example1 = generator.createStringExample("example1");
        generator.prepareForSerialization(schema1, example1);

        generator.initialize();
        Node exampleFromCache = generator.getExampleOrNull(schema2, "example-string");

        assertThat(exampleFromCache).isNull();
    }
}
