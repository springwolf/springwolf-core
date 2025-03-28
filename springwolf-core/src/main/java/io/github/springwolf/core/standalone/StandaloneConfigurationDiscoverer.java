// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone;

import io.github.springwolf.core.asyncapi.scanners.classes.spring.annotations.ClassScannerUtil;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.util.List;

/**
 * Discovers classes annotated with {@link StandaloneConfiguration}.
 */
public class StandaloneConfigurationDiscoverer {

    /**
     * @param scanPackages comma separated list of packages
     */
    public static List<Class<?>> scan(String scanPackages, ConfigurableEnvironment environment) {
        final TypeFilter filter = new AnnotationTypeFilter(StandaloneConfiguration.class);
        return ClassScannerUtil.getClasses(scanPackages, filter, environment);
    }
}
