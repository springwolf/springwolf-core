// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.common_model_converters.converters.enums;

import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NameInReffedSchemaModelConverterTest {

    private final NameInReffedSchemaModelConverter modelsConverter = new NameInReffedSchemaModelConverter();

    private final ModelConverters converters = new ModelConverters();

    @Test
    void testNameIsAddedToReffedSchema() {
        // given
        converters.addConverter(modelsConverter);

        // when
        final Map<String, Schema> models = converters.readAll(MyRootObject.class);

        // then
        assertNotNull(models);
        assertEquals(2, models.size());
        Schema myRootObject = models.get("MyRootObject");
        assertNotNull(myRootObject);
        assertEquals("MyRootObject", myRootObject.getName());
        Schema myEnumObjectField = (Schema) myRootObject.getProperties().get("myEnumObjectField");
        assertEquals("myEnumObjectField", myEnumObjectField.getName());
        assertEquals("#/components/schemas/MyEnumObject", myEnumObjectField.get$ref());

        Schema myEnumObject = models.get("MyEnumObject");
        assertNotNull(myEnumObject);
        assertEquals("MyEnumObject", myEnumObject.getName());
    }

    @io.swagger.v3.oas.annotations.media.Schema(enumAsRef = true)
    public enum MyEnumObject {
        VALUE1,
        VALUE2
    }

    public record MyRootObject(MyEnumObject myEnumObjectField) {}
}
