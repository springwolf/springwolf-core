// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.common_model_converters.converters.enums;

import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class NameInReffedSchemaModelConverterTest {

    private final NameInReffedSchemaModelConverter modelsConverter = new NameInReffedSchemaModelConverter();

    private final ModelConverters converters = new ModelConverters();

    @Test
    void nameIsAddedToReffedSchema() {
        // given
        converters.addConverter(modelsConverter);

        // when
        final Map<String, Schema> models = converters.readAll(MyRootObject.class);

        // then
        assertThat(models).isNotNull();
        assertThat(models.size()).isEqualTo(2);
        Schema myRootObject = models.get("MyRootObject");
        assertThat(myRootObject).isNotNull();
        assertThat(myRootObject.getName()).isEqualTo("MyRootObject");
        Schema myEnumObjectField = (Schema) myRootObject.getProperties().get("myEnumObjectField");
        assertThat(myEnumObjectField.getName()).isEqualTo("myEnumObjectField");
        assertThat(myEnumObjectField.get$ref()).isEqualTo("#/components/schemas/MyEnumObject");

        Schema myEnumObject = models.get("MyEnumObject");
        assertThat(myEnumObject).isNotNull();
        assertThat(myEnumObject.getName()).isEqualTo("MyEnumObject");
    }

    @io.swagger.v3.oas.annotations.media.Schema(enumAsRef = true)
    public enum MyEnumObject {
        VALUE1,
        VALUE2
    }

    public record MyRootObject(MyEnumObject myEnumObjectField) {}
}
