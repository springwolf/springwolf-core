// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.classes.spring;

import io.github.springwolf.core.asyncapi.scanners.common.utils.AnnotationScannerUtil;
import io.github.springwolf.core.asyncapi.scanners.classes.ClassScanner;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ConfigurationClassScanner implements ClassScanner {

    private final ComponentClassScanner scanner;

    @Override
    public Set<Class<?>> scan() {
        return scanner.scan().stream()
                // All Configurations are also Components
                .filter((cls) -> AnnotationScannerUtil.findAnnotation(Configuration.class, cls) != null)
                .collect(Collectors.toSet());
    }
}
