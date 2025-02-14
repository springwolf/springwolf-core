// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.common_model_converters.standalone;

import io.github.springwolf.addons.common_model_converters.configuration.CommonModelConvertersAutoConfiguration;
import io.github.springwolf.addons.common_model_converters.converters.enums.NameInReffedSchemaModelConverter;
import io.github.springwolf.addons.common_model_converters.converters.monetaryamount.MonetaryAmountConverter;
import io.github.springwolf.core.standalone.plugin.StandalonePlugin;
import io.swagger.v3.core.converter.ModelConverter;

import java.util.List;

public class StandaloneCommonModelConverterPlugin implements StandalonePlugin {
    @Override
    public List<ModelConverter> getModelConverters() {
        CommonModelConvertersAutoConfiguration commonModelConvertersAutoConfiguration =
                new CommonModelConvertersAutoConfiguration();
        MonetaryAmountConverter monetaryAmountConverter =
                commonModelConvertersAutoConfiguration.monetaryAmountConverter();
        NameInReffedSchemaModelConverter nameInReffedSchemaModelConverter =
                commonModelConvertersAutoConfiguration.nameInReffedSchemaModelConverter();

        return List.of(monetaryAmountConverter, nameInReffedSchemaModelConverter);
    }
}
