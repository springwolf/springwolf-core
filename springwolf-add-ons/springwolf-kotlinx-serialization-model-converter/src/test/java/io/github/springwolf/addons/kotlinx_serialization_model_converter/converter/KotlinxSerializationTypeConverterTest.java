// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.kotlinx_serialization_model_converter.converter;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.util.ObjectMapperFactory;
import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

class KotlinxSerializationTypeConverterTest {

    ObjectMapper jsonMapper = ObjectMapperFactory.createJson31();
    private final PrettyPrinter printer = new DefaultPrettyPrinter();

    private ModelConverters modelConverters;

    @BeforeEach
    void setUp() {
        modelConverters = new ModelConverters();
        modelConverters.addConverter(new KotlinxSerializationModelConverter());
    }

    @Test
    void validateGeneratedJson() throws Exception {
        final KotlinxSerializationModelConverter modelConverter = new KotlinxSerializationModelConverter();
        final ModelConverters converters = new ModelConverters();
        converters.addConverter(modelConverter);

        var media = converters.readAll(new AnnotatedType(SampleEvent.class));

        String example = ClasspathUtil.readAsString("/simple.json");
        assertThatJson(jsonMapper.writer(printer).writeValueAsString(media)).isEqualTo(example);
    }

    @Test
    void validateGeneratedFqnJson() throws Exception {
        final KotlinxSerializationModelConverter modelConverter = new KotlinxSerializationModelConverter(true);
        final ModelConverters converters = new ModelConverters();
        converters.addConverter(modelConverter);

        var media = converters.readAll(new AnnotatedType(SampleEvent.class));

        String example = ClasspathUtil.readAsString("/fqn.json");
        assertThatJson(jsonMapper.writer(printer).writeValueAsString(media)).isEqualTo(example);
    }

    @Nested
    class TestListProperty {
        @Test
        void classWithListProperty() {
            var result = modelConverters.readAll(new AnnotatedType(ClassWithListProperty.class));
            Schema<?> schema = result.get(ClassWithListProperty.class.getSimpleName());

            final Schema<?> listField = (Schema<?>) schema.getProperties().get("list_field");
            assertThat(listField).isNotNull();
            assertThat(listField.getType()).isEqualTo("array");
            assertThat(listField.getNullable()).isFalse();
            assertThat(listField.getItems()).isNotNull();
            assertThat(listField.getItems().getType()).isEqualTo("string");
        }

        @Test
        void classWithCollectionProperty() {
            var result = modelConverters.readAll(new AnnotatedType(ClassWithCollectionProperty.class));
            Schema<?> schema = result.get(ClassWithCollectionProperty.class.getSimpleName());

            final Schema<?> listField = (Schema<?>) schema.getProperties().get("collection_field");
            assertThat(listField).isNotNull();
            assertThat(listField.getType()).isEqualTo("array");
            assertThat(listField.getNullable()).isFalse();
            assertThat(listField.getItems()).isNotNull();
            assertThat(listField.getItems().getType()).isEqualTo("string");
        }
    }

    @Nested
    class TestSetProperty {
        @Test
        void classWithSetProperty() {
            var result = modelConverters.readAll(new AnnotatedType(ClassWithSetProperty.class));
            Schema<?> schema = result.get(ClassWithSetProperty.class.getSimpleName());

            final Schema<?> setField = (Schema<?>) schema.getProperties().get("set_field");
            assertThat(setField).isNotNull();
            assertThat(setField.getType()).isEqualTo("array");
            assertThat(setField.getNullable()).isFalse();
            assertThat(setField.getUniqueItems()).isTrue();
            assertThat(setField.getItems()).isNotNull();
            assertThat(setField.getItems().getType()).isEqualTo("string");
        }
    }

    @Nested
    class TestEnumProperty {
        @Test
        void classWithEnumProperty() {
            var result = modelConverters.readAll(new AnnotatedType(ClassWithEnumProperty.class));
            Schema<?> schema = result.get(ClassWithEnumProperty.class.getSimpleName());

            final Schema<?> enumField = (Schema<?>) schema.getProperties().get("enum_field");
            assertThat(enumField).isNotNull();
            assertThat(enumField.getType()).isEqualTo("string");
            assertThat(enumField.getNullable()).isFalse();
            assertThat(enumField.getEnum()).isNotNull();
            assertThat(enumField.getEnum())
                    .isEqualTo(Color.getEntries().stream().map(Enum::name).toList());
        }
    }

    @Nested
    class TestNestedClass {
        @Test
        void classWithNestedProperty() {
            var result = modelConverters.readAll(new AnnotatedType(ClassWithNestedProperty.class));
            Schema<?> schema = result.get(ClassWithNestedProperty.class.getSimpleName());

            final Schema<?> nestedClass = (Schema<?>) schema.getProperties().get("nested_class");
            assertThat(nestedClass).isNotNull();
            assertThat(nestedClass.getType()).isNull();
            assertThat(nestedClass.get$ref()).isEqualTo("#/components/schemas/ClassWithNestedProperty$NestedClass");

            final Schema<?> nestedModel = result.get("ClassWithNestedProperty$NestedClass");
            assertThat(nestedModel).isNotNull();
            assertThat(nestedModel.getType()).isEqualTo("object");
            assertThat(nestedModel.getProperties()).hasSize(3);
            assertThat(nestedModel.getRequired()).hasSize(3);
        }
    }

    @Nested
    class TestSealedClass {
        @Test
        void sealedClassSerialization() {
            var result = modelConverters.readAll(new AnnotatedType(ClassWithPolymorphism.class));
            assertThat(result).hasSize(3);
            assertThat(result.get(Dog.class.getSimpleName())).isNotNull();
            assertThat(result.get(Cat.class.getSimpleName())).isNotNull();
            assertThat(result.get(Pet.class.getSimpleName())).isNull();

            Schema<?> schema = result.get(ClassWithPolymorphism.class.getSimpleName());

            final Schema<?> sealedClass = (Schema<?>) schema.getProperties().get("pet");
            assertThat(sealedClass).isNotNull();
            assertThat(sealedClass.getType()).isNull();
            assertThat(sealedClass.get$ref()).isNull();
            assertThat(sealedClass.getOneOf()).hasSize(2);
            assertThat(sealedClass.getOneOf().get(0).get$ref()).isEqualTo("#/components/schemas/Cat");
            assertThat(sealedClass.getOneOf().get(1).get$ref()).isEqualTo("#/components/schemas/Dog");
        }
    }

    @Test
    void serializeKotlin() {
        final KotlinxSerializationModelConverter modelConverter = new KotlinxSerializationModelConverter();
        final ModelConverters converters = new ModelConverters();
        converters.addConverter(modelConverter);

        var media = converters.readAll(new AnnotatedType(SampleEvent.class));
        assertThat(media).hasSize(2);
        final Schema<?> model = media.get(SampleEvent.class.getSimpleName());
        assertThat(model).isNotNull();
        assertThat(model.getType()).isEqualTo("object");
        assertThat(model.getProperties()).hasSize(16);
        // With 2 nullable fields, we should have only 14 required fields
        assertThat(model.getRequired()).hasSize(14);

        assertBasicFieldNames(model);

        assertLocalDateField(model.getProperties().get("date_field"));
    }

    private void assertLocalDateField(Schema<?> dateField) {
        assertThat(dateField).isNotNull();
        assertThat(dateField.getType()).isEqualTo("string");
        assertThat(dateField.getFormat()).isEqualTo("date");
    }

    private void assertBasicFieldNames(Schema<?> model) {
        final Schema<?> stringField = (Schema<?>) model.getProperties().get("string_field");
        assertThat(stringField).isNotNull();
        assertThat(stringField.getType()).isEqualTo("string");
        assertThat(stringField.getNullable()).isFalse();

        final Schema<?> optionalField = (Schema<?>) model.getProperties().get("optional_field");
        assertThat(optionalField).isNotNull();
        assertThat(optionalField.getType()).isEqualTo("string");

        final Schema<?> booleanField = (Schema<?>) model.getProperties().get("boolean_field");
        assertThat(booleanField).isNotNull();
        assertThat(booleanField.getType()).isEqualTo("boolean");
        assertThat(booleanField.getNullable()).isFalse();

        final Schema<?> intField = (Schema<?>) model.getProperties().get("int_field");
        assertThat(intField).isNotNull();
        assertThat(intField.getType()).isEqualTo("integer");
        assertThat(intField.getFormat()).isEqualTo("int32");
        assertThat(intField.getNullable()).isFalse();

        final Schema<?> longField = (Schema<?>) model.getProperties().get("long_field");
        assertThat(longField).isNotNull();
        assertThat(longField.getType()).isEqualTo("integer");
        assertThat(longField.getFormat()).isEqualTo("int64");
        assertThat(longField.getNullable()).isFalse();

        final Schema<?> floatField = (Schema<?>) model.getProperties().get("float_field");
        assertThat(floatField).isNotNull();
        assertThat(floatField.getType()).isEqualTo("number");
        assertThat(floatField.getFormat()).isEqualTo("float");
        assertThat(floatField.getNullable()).isFalse();

        final Schema<?> doubleField = (Schema<?>) model.getProperties().get("double_field");
        assertThat(doubleField).isNotNull();
        assertThat(doubleField.getType()).isEqualTo("number");
        assertThat(doubleField.getFormat()).isEqualTo("double");
        assertThat(doubleField.getNullable()).isFalse();

        final Schema<?> shortField = (Schema<?>) model.getProperties().get("short_field");
        assertThat(shortField).isNotNull();
        assertThat(shortField.getType()).isEqualTo("integer");
        assertThat(shortField.getNullable()).isFalse();

        final Schema<?> byteField = (Schema<?>) model.getProperties().get("byte_field");
        assertThat(byteField).isNotNull();
        assertThat(byteField.getType()).isEqualTo("string");
        assertThat(byteField.getFormat()).isEqualTo("byte");
        assertThat(byteField.getNullable()).isFalse();

        final Schema<?> listReferences = (Schema<?>) model.getProperties().get("listed_references");
        assertThat(listReferences).isNotNull();
        assertThat(listReferences.getType()).isEqualTo("array");
        assertThat(listReferences.getNullable()).isFalse();
        assertThat(listReferences.getItems()).isNotNull();
        assertThat(listReferences.getItems().getType()).isNull();
        assertThat(listReferences.getItems().get$ref()).isEqualTo("#/components/schemas/SampleEvent$NestedClass");

        final Schema<?> mapField = (Schema<?>) model.getProperties().get("map_field");
        assertThat(mapField).isNotNull();
        assertThat(mapField.getType()).isEqualTo("object");
        assertThat(mapField.getNullable()).isFalse();

        final Schema<?> missingProperty = (Schema<?>) model.getProperties().get("missing");
        assertThat(missingProperty).isNull();
    }
}
