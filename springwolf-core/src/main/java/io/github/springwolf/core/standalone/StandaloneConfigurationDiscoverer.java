// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone;

import io.github.springwolf.core.asyncapi.scanners.classes.spring.annotations.ClassScannerUtil;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.util.List;

/**
 * Discovers classes annotated with {@link StandaloneConfiguration}.
 */
public class StandaloneConfigurationDiscoverer {
    public static List<Class<?>> scan(ConfigurableEnvironment environment) {
        return scan(SpringwolfConfigConstants.SPRINGWOLF_PACKAGE, environment);
    }

    public static List<Class<?>> scan(String scanPackage, ConfigurableEnvironment environment) {
        final TypeFilter filter = new AnnotationTypeFilter(StandaloneConfiguration.class);
        return ClassScannerUtil.getClasses(scanPackage, filter, environment);
    }
}
