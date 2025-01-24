// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import io.github.springwolf.core.asyncapi.AsyncApiCustomizer;
import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import io.github.springwolf.core.asyncapi.channels.ChannelsService;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.components.examples.SchemaWalkerProvider;
import io.github.springwolf.core.asyncapi.components.examples.walkers.SchemaWalker;
import io.github.springwolf.core.asyncapi.components.postprocessors.ExampleGeneratorPostProcessor;
import io.github.springwolf.core.asyncapi.components.postprocessors.SchemasPostProcessor;
import io.github.springwolf.core.asyncapi.grouping.AsyncApiGroupService;
import io.github.springwolf.core.asyncapi.grouping.GroupingService;
import io.github.springwolf.core.asyncapi.operations.OperationsService;
import io.github.springwolf.core.asyncapi.scanners.ChannelsScanner;
import io.github.springwolf.core.asyncapi.scanners.OperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.beans.BeanMethodsScanner;
import io.github.springwolf.core.asyncapi.scanners.beans.DefaultBeanMethodsScanner;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.OperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.classes.ClassScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationProvider;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.message.AsyncAnnotationMessageService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadAsyncOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodReturnService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.PayloadService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.TypeExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.utils.StringValueResolverProxy;
import io.github.springwolf.core.asyncapi.scanners.operations.annotations.OperationCustomizer;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaService;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaUtil;
import io.github.springwolf.core.configuration.SpringwolfAutoConfiguration;
import io.github.springwolf.core.configuration.SpringwolfScannerConfiguration;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.swagger.v3.core.converter.ModelConverter;
import jakarta.annotation.Nullable;
import org.springframework.core.env.Environment;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StandaloneContext {
    public AsyncApiService create(@Nullable String basePackage) throws IOException {
        SpringwolfAutoConfiguration autoConfiguration = new SpringwolfAutoConfiguration();
        SpringwolfScannerConfiguration scannerAutoConfiguration = new SpringwolfScannerConfiguration();

        Environment environment = new SpringwolfConfigPropertiesLoader().loadEnvironment();
        StringValueResolverProxy stringValueResolver = new StringValueResolverProxy();
        stringValueResolver.setEmbeddedValueResolver(createStringValueResolver(environment));

        SpringwolfConfigProperties properties =
                new SpringwolfConfigPropertiesLoader().loadSpringwolfConfigProperties(environment);
        AsyncApiDocketService asyncApiDocketService = autoConfiguration.asyncApiDocketService(properties);

        ClassScanner componentClassScanner = createComponentClassScanner(basePackage, properties);

        BeanMethodsScanner beanMethodsScanner = new DefaultBeanMethodsScanner(componentClassScanner);
        SpringwolfClassScanner springwolfClassScanner =
                new SpringwolfClassScanner(componentClassScanner, beanMethodsScanner);

        SchemaWalker jsonSchemaWalker = autoConfiguration.jsonSchemaWalker();
        List<SchemaWalker> schemaWalkers = new ArrayList<>();
        schemaWalkers.add(jsonSchemaWalker);
        SchemaWalkerProvider schemaWalkerProvider = autoConfiguration.schemaWalkerProvider(schemaWalkers);
        ExampleGeneratorPostProcessor exampleGeneratorPostProcessor =
                autoConfiguration.exampleGeneratorPostProcessor(schemaWalkerProvider);

        List<ModelConverter> modelConverters = new ArrayList<>(); // TODO:
        List<SchemasPostProcessor> schemasPostProcessors = List.of(exampleGeneratorPostProcessor); // TODO:
        SwaggerSchemaUtil swaggerSchemaUtil = autoConfiguration.swaggerSchemaUtil();
        SwaggerSchemaService swaggerSchemaService =
                autoConfiguration.schemasService(modelConverters, schemasPostProcessors, swaggerSchemaUtil, properties);
        ComponentsService componentsService = autoConfiguration.componentsService(swaggerSchemaService);

        TypeExtractor typeExtractor = autoConfiguration.typeExtractor(properties);
        PayloadExtractor payloadExtractor = autoConfiguration.payloadExtractor(typeExtractor);
        HeaderClassExtractor headerClassExtractor = autoConfiguration.headerClassExtractor(swaggerSchemaService);
        PayloadService payloadService = autoConfiguration.payloadService(componentsService, properties);
        PayloadAsyncOperationService payloadAsyncOperationService =
                autoConfiguration.payloadAsyncOperationService(payloadExtractor, payloadService);
        PayloadMethodParameterService payloadMethodParameterService =
                autoConfiguration.payloadMethodParameterService(payloadExtractor, payloadService);
        PayloadMethodReturnService payloadMethodReturnService =
                autoConfiguration.payloadMethodReturnService(payloadService);
        List<OperationBindingProcessor> operationBindingProcessors = new ArrayList<>(); // TODO:
        List<MessageBindingProcessor> messageBindingProcessors = new ArrayList<>(); // TODO:
        List<OperationCustomizer> operationCustomizers = new ArrayList<>(); // TODO:
        AsyncAnnotationMessageService asyncAnnotationMessageService = autoConfiguration.asyncAnnotationMessageService(
                componentsService, payloadAsyncOperationService, messageBindingProcessors, stringValueResolver);

        AsyncAnnotationProvider<AsyncListener> asyncListenerAnnotationProvider =
                scannerAutoConfiguration.asyncListenerAnnotationProvider();
        AsyncAnnotationProvider<AsyncPublisher> asyncPublisherAnnotationProvider =
                scannerAutoConfiguration.asyncPublisherAnnotationProvider();
        ChannelsScanner asyncListenerMethodLevelAnnotationChannelScanner =
                scannerAutoConfiguration.asyncListenerMethodLevelAnnotationChannelScanner(
                        asyncListenerAnnotationProvider,
                        springwolfClassScanner,
                        asyncApiDocketService,
                        asyncAnnotationMessageService,
                        operationBindingProcessors,
                        stringValueResolver);
        // TODO: class scanner
        OperationsScanner asyncListenerMethodLevelAnnotationOperationScanner =
                scannerAutoConfiguration.asyncListenerMethodLevelAnnotationOperationScanner(
                        asyncListenerAnnotationProvider,
                        springwolfClassScanner,
                        asyncAnnotationMessageService,
                        operationBindingProcessors,
                        operationCustomizers,
                        stringValueResolver);

        GroupingService groupingService = autoConfiguration.groupingService();
        AsyncApiGroupService asyncApiGroupService = autoConfiguration.asyncApiGroupService(properties, groupingService);
        ChannelsService channelsService =
                autoConfiguration.channelsService(List.of(asyncListenerMethodLevelAnnotationChannelScanner)); // TODO:
        OperationsService operationsService = autoConfiguration.operationsService(
                List.of(asyncListenerMethodLevelAnnotationOperationScanner)); // TODO:

        List<AsyncApiCustomizer> customizers = new ArrayList<>();

        return autoConfiguration.asyncApiService(
                asyncApiDocketService,
                channelsService,
                operationsService,
                componentsService,
                customizers,
                asyncApiGroupService);
    }

    private ClassScanner createComponentClassScanner(String basePackage, SpringwolfConfigProperties properties) {
        String actualBasePackage =
                basePackage != null ? basePackage : properties.getDocket().getBasePackage();
        ScanResult scanResult = new ClassGraph()
                .enableAllInfo() // TODO: is everything required?
                .acceptPackages(actualBasePackage)
                .scan();

        return new ClassScanner() {
            private final HashSet<Class<?>> classes = new HashSet<>(
                    scanResult.getClassesWithAnnotation(Component.class).loadClasses());

            @Override
            public Set<Class<?>> scan() {
                return classes;
            }
        };
    }

    private StringValueResolver createStringValueResolver(Environment environment) {
        return new StringValueResolver() {
            @Override
            public String resolveStringValue(@Nullable String strVal) {
                if (strVal == null) return null;
                if (!strVal.contains("${")) return strVal;

                System.out.println("Trying to resolve: " + strVal);

                StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
                evaluationContext.setRootObject(environment);

                ExpressionParser parser = new SpelExpressionParser();
                return parser.parseExpression(strVal).getValue(evaluationContext, String.class);
            }
        };
    }
}
