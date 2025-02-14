// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone.plugin;

import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.beans.BeanMethodsScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.spring.ComponentClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodReturnService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.TypeExtractor;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.OperationCustomizer;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.core.env.Environment;
import org.springframework.util.StringValueResolver;

import java.util.List;

@Getter
@Builder
public class StandalonePluginContext {
    private Environment environment;
    private SpringwolfClassScanner classScanner;
    private StringValueResolver stringValueResolver;
    private PayloadMethodParameterService payloadMethodParameterService;
    private PayloadMethodReturnService payloadMethodReturnService;
    private HeaderClassExtractor headerClassExtractor;
    private ComponentsService componentsService;
    private List<OperationCustomizer> operationCustomizers;
    private AsyncApiDocketService asyncApiDocketService;
    private TypeExtractor typeExtractor;
    private PayloadService payloadService;
    private BeanMethodsScanner beanMethodsScanner;
    private ComponentClassScanner componentClassScanner;
}
