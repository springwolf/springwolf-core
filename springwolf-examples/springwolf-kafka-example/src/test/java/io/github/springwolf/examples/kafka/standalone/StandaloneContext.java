// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.standalone;

import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.OperationCustomizer;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.StringValueResolver;

import java.util.List;

@Getter
@Builder
public class StandaloneContext {
    private SpringwolfClassScanner classScanner;
    private StringValueResolver stringValueResolver;
    private PayloadMethodParameterService payloadMethodParameterService;
    private HeaderClassExtractor headerClassExtractor;
    private ComponentsService componentsService;
    private List<OperationCustomizer> operationCustomizers;
}
