// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.kotlinx_serialization_model_converter.converter;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.util.ObjectMapperFactory;
import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

class KotlinxSerializationTypeConverterTest {

    ObjectMapper jsonMapper = ObjectMapperFactory.createJson31();
    private final PrettyPrinter printer = new DefaultPrettyPrinter();

    @Nested
    class TestListProperty {

        ModelConverters setUp() {
            final KotlinxSerializationModelConverter modelConverter = new KotlinxSerializationModelConverter();
            final ModelConverters converters = new ModelConverters();
            converters.addConverter(modelConverter);
            return converters;
        }

        @Test
        void testClassWithListProperty() {
            ModelConverters modelConverters = setUp();

            var result = modelConverters.readAll(new AnnotatedType(ClassWithListProperty.class));
            Schema schema = result.get(ClassWithListProperty.class.getSimpleName());

            final Schema<?> listField = (Schema<?>) schema.getProperties().get("list_field");
            assertThat(listField).isNotNull();
            assertThat(listField.getType()).isEqualTo("array");
            assertThat(listField.getNullable()).isFalse();
            assertThat(listField.getItems()).isNotNull();
            assertThat(listField.getItems().getType()).isEqualTo("string");

        }
    }

    @Test
    void serializeKotlin() {
        final KotlinxSerializationModelConverter modelConverter = new KotlinxSerializationModelConverter();
        final ModelConverters converters = new ModelConverters();
        converters.addConverter(modelConverter);

        var media = converters.readAll(new AnnotatedType(SampleEvent.class));
        // FIXME: The NestedClass is duplicated
        assertThat(media).hasSize(3);
        final Schema<?> model = media.get(SampleEvent.class.getSimpleName());
        assertThat(model).isNotNull();
        assertThat(model.getType()).isEqualTo("object");
        assertThat(model.getProperties()).hasSize(16);
        // FIXME: Shouldn't be 15 ?
        assertThat(model.getRequired()).hasSize(14);

        assertBasicFieldNames(model);

        assertLocalDateField(model.getProperties().get("date_field"));

        assertEnumField(model.getProperties().get("enum_field"));

        assertColorEnum(model.getProperties().get("enum_field"));

        assertNestedClass(
                model.getProperties().get("nested_class"),
                "#/components/schemas/SampleEvent$NestedClass",
                media.get("NestedClass"));
    }

    @Test
    void validateGeneratedJson() throws IOException {
        final KotlinxSerializationModelConverter modelConverter = new KotlinxSerializationModelConverter();
        final ModelConverters converters = new ModelConverters();
        converters.addConverter(modelConverter);

        var media = converters.readAll(new AnnotatedType(SampleEvent.class));

        String example = ClasspathUtil.readAsString("/simple.json");
        assertThatJson(jsonMapper.writer(printer).writeValueAsString(media)).isEqualTo(example);
    }

    @Test
    void validateGeneratedFqnJson() throws IOException {
        final KotlinxSerializationModelConverter modelConverter = new KotlinxSerializationModelConverter(true);
        final ModelConverters converters = new ModelConverters();
        converters.addConverter(modelConverter);

        var media = converters.readAll(new AnnotatedType(SampleEvent.class));

        String example = ClasspathUtil.readAsString("/fqn.json");
        assertThatJson(jsonMapper.writer(printer).writeValueAsString(media)).isEqualTo(example);
    }

    private void assertLocalDateField(Schema<?> dateField) {
        assertThat(dateField).isNotNull();
        assertThat(dateField.getType()).isEqualTo("string");
        assertThat(dateField.getFormat()).isEqualTo("date");
    }

    private void assertEnumField(Schema<?> enumField) {
        assertThat(enumField).isNotNull();
        assertThat(enumField.getType()).isEqualTo("string");
        assertThat(enumField.getNullable()).isFalse();
        assertThat(enumField.getEnum()).isNotNull();
        assertThat(enumField.getEnum())
                .isEqualTo(Color.getEntries().stream().map(Enum::name).toList());
    }

    private void assertNestedClass(Schema<?> nestedClass, String expectedRef, Schema<?> nestedModel) {
        assertThat(nestedClass).isNotNull();
        assertThat(nestedClass.getType()).isNull();
        assertThat(nestedClass.getNullable()).isTrue();
        assertThat(nestedClass.get$ref()).isEqualTo(expectedRef);

        assertThat(nestedModel).isNotNull();
        assertThat(nestedModel.getType()).isEqualTo("object");
        assertThat(nestedModel.getProperties()).hasSize(3);
        assertThat(nestedModel.getRequired()).hasSize(3);
    }

    private void assertColorEnum(Schema<?> enumModel) {
        assertThat(enumModel).isNotNull();
        assertThat(enumModel.getType()).isEqualTo("string");
        assertThat(enumModel.getEnum()).isEqualTo(List.of("RED", "GREEN", "BLUE"));
    }

    private void assertBasicFieldNames(Schema<?> model) {
        final Schema<?> stringField = (Schema<?>) model.getProperties().get("string_field");
        assertThat(stringField).isNotNull();
        assertThat(stringField.getType()).isEqualTo("string");
        assertThat(stringField.getNullable()).isFalse();

        final Schema<?> optionalField = (Schema<?>) model.getProperties().get("optional_field");
        assertThat(optionalField).isNotNull();
        assertThat(optionalField.getType()).isEqualTo("string");
        // FIXME
        // assertThat(optionalField.getNullable()).isTrue();

        final Schema<?> booleanField = (Schema<?>) model.getProperties().get("boolean_field");
        assertThat(booleanField).isNotNull();
        assertThat(booleanField.getType()).isEqualTo("boolean");
        assertThat(booleanField.getNullable()).isFalse();

        final Schema<?> intField = (Schema<?>) model.getProperties().get("int_field");
        assertThat(intField).isNotNull();
        assertThat(intField.getType()).isEqualTo("integer");
        assertThat(intField.getNullable()).isFalse();

        final Schema<?> longField = (Schema<?>) model.getProperties().get("long_field");
        assertThat(longField).isNotNull();
        assertThat(longField.getType()).isEqualTo("integer");
        assertThat(longField.getNullable()).isFalse();

        final Schema<?> floatField = (Schema<?>) model.getProperties().get("float_field");
        assertThat(floatField).isNotNull();
        assertThat(floatField.getType()).isEqualTo("number");
        assertThat(floatField.getNullable()).isFalse();

        final Schema<?> doubleField = (Schema<?>) model.getProperties().get("double_field");
        assertThat(doubleField).isNotNull();
        assertThat(doubleField.getType()).isEqualTo("number");
        assertThat(doubleField.getNullable()).isFalse();

        final Schema<?> shortField = (Schema<?>) model.getProperties().get("short_field");
        assertThat(shortField).isNotNull();
        assertThat(shortField.getType()).isEqualTo("integer");
        assertThat(shortField.getNullable()).isFalse();

        final Schema<?> byteField = (Schema<?>) model.getProperties().get("byte_field");
        assertThat(byteField).isNotNull();
        assertThat(byteField.getType()).isEqualTo("integer");
        assertThat(byteField.getNullable()).isFalse();

        final Schema<?> listField = (Schema<?>) model.getProperties().get("list_field");
        assertThat(listField).isNotNull();
        assertThat(listField.getType()).isEqualTo("array");
        assertThat(listField.getNullable()).isFalse();
        assertThat(listField.getItems()).isNotNull();
        assertThat(listField.getItems().getType()).isEqualTo("string");

        final Schema<?> listReferences = (Schema<?>) model.getProperties().get("listed_references");
        assertThat(listReferences).isNotNull();
        assertThat(listReferences.getType()).isEqualTo("array");
        assertThat(listReferences.getNullable()).isFalse();
        assertThat(listReferences.getItems()).isNotNull();
        assertThat(listReferences.getItems().getType()).isNull();
        assertThat(listReferences.getItems().get$ref()).isEqualTo("#/components/schemas/SampleEvent$NestedClass");

        final Schema<?> setField = (Schema<?>) model.getProperties().get("set_field");
        assertThat(setField).isNotNull();
        assertThat(setField.getType()).isEqualTo("array");
        assertThat(setField.getNullable()).isFalse();
        assertThat(setField.getUniqueItems()).isTrue();
        assertThat(listField.getItems()).isNotNull();
        assertThat(listField.getItems().getType()).isEqualTo("string");

        final Schema<?> mapField = (Schema<?>) model.getProperties().get("map_field");
        assertThat(mapField).isNotNull();
        assertThat(mapField.getType()).isEqualTo("object");
        assertThat(mapField.getNullable()).isFalse();

        final Schema<?> missingProperty = (Schema<?>) model.getProperties().get("missing");
        assertThat(missingProperty).isNull();
    }
}
