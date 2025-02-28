// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.classes.spring;

import io.github.springwolf.core.asyncapi.scanners.classes.ClassScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.spring.annotations.ClassScannerUtil;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
public class ComponentClassScanner implements ClassScanner {

    private final AsyncApiDocketService asyncApiDocketService;

    private final Environment environment;

    @Override
    public Set<Class<?>> scan() {
        String basePackages = asyncApiDocketService.getAsyncApiDocket().getBasePackage();
        if (!StringUtils.hasText(basePackages)) {
            throw new IllegalArgumentException("Base package must not be blank");
        }

        AnnotationTypeFilter filter = new AnnotationTypeFilter(Component.class);
        return new HashSet<>(ClassScannerUtil.getClasses(basePackages, filter, environment));
    }
}
