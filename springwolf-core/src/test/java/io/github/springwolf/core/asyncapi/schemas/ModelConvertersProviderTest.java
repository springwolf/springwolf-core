// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas;

import io.github.springwolf.core.configuration.properties.PayloadSchemaFormat;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.core.jackson.TypeNameResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class ModelConvertersProviderTest {

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void createsSpringwolfTypeNameResolverWithUseFqnSetting(boolean useFqn) {
        // given
        ModelConvertersProvider provider =
                new ModelConvertersProvider(configProperties(useFqn, PayloadSchemaFormat.ASYNCAPI_V3), List.of());

        // when
        TypeNameResolver typeNameResolver = provider.getTypeNameResolver();

        // then
        assertThat(typeNameResolver).isInstanceOf(SpringwolfTypeNameResolver.class);
        assertThat(typeNameResolver.getUseFqn()).isEqualTo(useFqn);
    }

    @ParameterizedTest
    @CsvSource({"ASYNCAPI_V3,false", "OPENAPI_V3,false", "OPENAPI_V3_1,true"})
    void createsModelConvertersWithExpectedOpenapi31Flag(
            PayloadSchemaFormat payloadSchemaFormat, boolean expectedOpenapi31) {
        // given
        ModelConvertersProvider provider =
                new ModelConvertersProvider(configProperties(true, payloadSchemaFormat), List.of());

        // when
        ModelConverters modelConverters = provider.getModelConverters();
        ModelResolver resolver = (ModelResolver) modelConverters.getConverters().get(0);

        // then
        assertThat(modelConverters.getConverters()).hasSize(1);
        assertThat(resolver.isOpenapi31()).isEqualTo(expectedOpenapi31);
    }

    @Test
    void addsExternalModelConvertersToTheCreatedModelConverters() {
        // given
        ModelConverter externalModelConverter = mock(ModelConverter.class);
        ModelConvertersProvider provider = new ModelConvertersProvider(
                configProperties(false, PayloadSchemaFormat.OPENAPI_V3_1), List.of(externalModelConverter));

        // when
        List<ModelConverter> converters = provider.getModelConverters().getConverters();

        // then
        assertThat(converters).hasSize(2);
        assertThat(converters.get(0)).isSameAs(externalModelConverter);
        assertThat(converters.get(1)).isInstanceOf(ModelResolver.class);
    }

    private static SpringwolfConfigProperties configProperties(boolean useFqn, PayloadSchemaFormat format) {
        SpringwolfConfigProperties properties = new SpringwolfConfigProperties();
        properties.setUseFqn(useFqn);
        properties.getDocket().setPayloadSchemaFormat(format);
        return properties;
    }
}
