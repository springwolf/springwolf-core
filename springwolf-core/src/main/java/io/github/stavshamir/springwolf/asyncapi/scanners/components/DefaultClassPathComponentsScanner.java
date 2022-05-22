package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class DefaultClassPathComponentsScanner extends CompositeComponentsScanner {

    public DefaultClassPathComponentsScanner(String basePackage, String configurationBasePackage) {
        super(
            StringUtils.isNotBlank(basePackage) ? new ComponentComponentsScanner(basePackage) : null,
            StringUtils.isNotBlank(configurationBasePackage) ? new ConfigurationComponentsScanner(configurationBasePackage) : null
        );
    }
}
