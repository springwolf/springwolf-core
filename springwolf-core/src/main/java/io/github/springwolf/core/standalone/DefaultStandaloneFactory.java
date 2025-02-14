// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone;

import io.github.springwolf.core.asyncapi.AsyncApiCustomizer;
import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import io.github.springwolf.core.asyncapi.channels.ChannelsService;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.components.examples.SchemaWalkerProvider;
import io.github.springwolf.core.asyncapi.components.examples.walkers.SchemaWalker;
import io.github.springwolf.core.asyncapi.components.examples.walkers.xml.ExampleXmlValueSerializer;
import io.github.springwolf.core.asyncapi.components.examples.walkers.yaml.ExampleYamlValueSerializer;
import io.github.springwolf.core.asyncapi.components.postprocessors.AvroSchemaPostProcessor;
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
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.spring.ComponentClassScanner;
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
import io.github.springwolf.core.asyncapi.schemas.converters.SchemaTitleModelConverter;
import io.github.springwolf.core.configuration.SpringwolfAutoConfiguration;
import io.github.springwolf.core.configuration.SpringwolfScannerConfiguration;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.github.springwolf.core.standalone.bean.StandaloneStringValueResolver;
import io.github.springwolf.core.standalone.common.SpringwolfConfigPropertiesLoader;
import io.github.springwolf.core.standalone.plugin.StandalonePlugin;
import io.github.springwolf.core.standalone.plugin.StandalonePluginContext;
import io.github.springwolf.core.standalone.plugin.StandalonePluginResult;
import io.swagger.v3.core.converter.ModelConverter;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DefaultStandaloneFactory implements StandaloneFactory {
    private final AsyncApiService asyncApiService;

    public DefaultStandaloneFactory(String basePackage, List<StandalonePlugin> plugins, Environment environment) {
        StringValueResolverProxy stringValueResolver = new StringValueResolverProxy();

        stringValueResolver.setEmbeddedValueResolver(new StandaloneStringValueResolver(environment));

        SpringwolfConfigProperties properties = new SpringwolfConfigPropertiesLoader()
                .loadConfigProperties(
                        environment,
                        SpringwolfConfigConstants.SPRINGWOLF_CONFIG_PREFIX,
                        SpringwolfConfigProperties.class);
        // TODO: is it a good way to set it?
        properties.getDocket().setBasePackage(basePackage);

        // TODO: can this autoConfiguration be analysed and the beans instanciated via custom dependency injection?
        SpringwolfAutoConfiguration autoConfiguration = new SpringwolfAutoConfiguration();
        SpringwolfScannerConfiguration scannerAutoConfiguration = new SpringwolfScannerConfiguration();

        AsyncApiDocketService asyncApiDocketService = autoConfiguration.asyncApiDocketService(properties);

        ComponentClassScanner componentClassScanner =
                scannerAutoConfiguration.componentClassScanner(asyncApiDocketService, environment);
        BeanMethodsScanner beanMethodsScanner = new DefaultBeanMethodsScanner(componentClassScanner);
        SpringwolfClassScanner springwolfClassScanner =
                new SpringwolfClassScanner(componentClassScanner, beanMethodsScanner);

        List<SchemasPostProcessor> schemasPostProcessors = createSchemaPostProcessors(autoConfiguration, properties);
        SchemaTitleModelConverter schemaTitleModelConverter = autoConfiguration.schemaTitleModelConverter();
        List<ModelConverter> modelConverters = new ArrayList<>();
        plugins.forEach(plugin -> modelConverters.addAll(plugin.getModelConverters()));
        modelConverters.add(schemaTitleModelConverter);
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
        List<OperationBindingProcessor> operationBindingProcessors = new ArrayList<>();
        List<MessageBindingProcessor> messageBindingProcessors = new ArrayList<>();
        List<OperationCustomizer> operationCustomizers = new ArrayList<>();
        AsyncAnnotationMessageService asyncAnnotationMessageService = autoConfiguration.asyncAnnotationMessageService(
                componentsService, payloadAsyncOperationService, messageBindingProcessors, stringValueResolver);

        AsyncAnnotationProvider<AsyncListener> asyncListenerAnnotationProvider =
                scannerAutoConfiguration.asyncListenerAnnotationProvider();
        AsyncAnnotationProvider<AsyncPublisher> asyncPublisherAnnotationProvider =
                scannerAutoConfiguration.asyncPublisherAnnotationProvider();
        List<ChannelsScanner> coreChannelScanners = createChannelScanners(
                scannerAutoConfiguration,
                asyncListenerAnnotationProvider,
                springwolfClassScanner,
                asyncApiDocketService,
                asyncAnnotationMessageService,
                operationBindingProcessors,
                stringValueResolver,
                asyncPublisherAnnotationProvider);
        List<OperationsScanner> coreOperationScanners = createOperationScanners(
                scannerAutoConfiguration,
                asyncListenerAnnotationProvider,
                springwolfClassScanner,
                asyncAnnotationMessageService,
                operationBindingProcessors,
                operationCustomizers,
                stringValueResolver,
                asyncPublisherAnnotationProvider);

        StandalonePluginContext context = StandalonePluginContext.builder()
                .environment(environment)
                .classScanner(springwolfClassScanner)
                .stringValueResolver(stringValueResolver)
                .payloadMethodParameterService(payloadMethodParameterService)
                .payloadMethodReturnService(payloadMethodReturnService)
                .headerClassExtractor(headerClassExtractor)
                .componentsService(componentsService)
                .operationCustomizers(operationCustomizers)
                .build();

        List<ChannelsScanner> channelsScanners = new ArrayList<>(coreChannelScanners);

        List<OperationsScanner> operationsScanners = new ArrayList<>(coreOperationScanners);

        List<AsyncApiCustomizer> customizers = new ArrayList<>();

        plugins.forEach(plugin -> {
            try {
                customizers.addAll(plugin.getAsyncApiCustomizers());

                StandalonePluginResult pluginResult = plugin.load(context);
                operationBindingProcessors.addAll(pluginResult.getOperationBindingProcessors());
                messageBindingProcessors.addAll(pluginResult.getMessageBindingProcessors());
                channelsScanners.addAll(pluginResult.getChannelsScanners());
                operationsScanners.addAll(pluginResult.getOperationsScanners());
                operationCustomizers.addAll(pluginResult.getOperationCustomizers());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        ChannelsService channelsService = autoConfiguration.channelsService(channelsScanners);
        OperationsService operationsService = autoConfiguration.operationsService(operationsScanners);

        GroupingService groupingService = autoConfiguration.groupingService();
        AsyncApiGroupService asyncApiGroupService = autoConfiguration.asyncApiGroupService(properties, groupingService);

        this.asyncApiService = autoConfiguration.asyncApiService(
                asyncApiDocketService,
                channelsService,
                operationsService,
                componentsService,
                customizers,
                asyncApiGroupService);
    }

    private static List<OperationsScanner> createOperationScanners(
            SpringwolfScannerConfiguration scannerAutoConfiguration,
            AsyncAnnotationProvider<AsyncListener> asyncListenerAnnotationProvider,
            SpringwolfClassScanner springwolfClassScanner,
            AsyncAnnotationMessageService asyncAnnotationMessageService,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<OperationCustomizer> operationCustomizers,
            StringValueResolverProxy stringValueResolver,
            AsyncAnnotationProvider<AsyncPublisher> asyncPublisherAnnotationProvider) {
        OperationsScanner asyncListenerMethodLevelAnnotationOperationScanner =
                scannerAutoConfiguration.asyncListenerMethodLevelAnnotationOperationScanner(
                        asyncListenerAnnotationProvider,
                        springwolfClassScanner,
                        asyncAnnotationMessageService,
                        operationBindingProcessors,
                        operationCustomizers,
                        stringValueResolver);
        OperationsScanner asyncListenerClassLevelListenerAnnotationOperationsScanner =
                scannerAutoConfiguration.asyncListenerClassLevelListenerAnnotationOperationsScanner(
                        asyncListenerAnnotationProvider,
                        springwolfClassScanner,
                        asyncAnnotationMessageService,
                        operationBindingProcessors,
                        operationCustomizers,
                        stringValueResolver);

        OperationsScanner asyncPublisherClassLevelOperationAnnotationScanner =
                scannerAutoConfiguration.asyncPublisherClassLevelOperationAnnotationScanner(
                        asyncPublisherAnnotationProvider,
                        springwolfClassScanner,
                        asyncAnnotationMessageService,
                        operationBindingProcessors,
                        operationCustomizers,
                        stringValueResolver);
        OperationsScanner asyncPublisherClassLevelListenerAnnotationOperationsScanner =
                scannerAutoConfiguration.asyncPublisherClassLevelListenerAnnotationOperationsScanner(
                        asyncPublisherAnnotationProvider,
                        springwolfClassScanner,
                        operationBindingProcessors,
                        asyncAnnotationMessageService,
                        operationCustomizers,
                        stringValueResolver);
        return List.of(
                asyncListenerMethodLevelAnnotationOperationScanner,
                asyncListenerClassLevelListenerAnnotationOperationsScanner,
                asyncPublisherClassLevelOperationAnnotationScanner,
                asyncPublisherClassLevelListenerAnnotationOperationsScanner);
    }

    private static List<ChannelsScanner> createChannelScanners(
            SpringwolfScannerConfiguration scannerAutoConfiguration,
            AsyncAnnotationProvider<AsyncListener> asyncListenerAnnotationProvider,
            SpringwolfClassScanner springwolfClassScanner,
            AsyncApiDocketService asyncApiDocketService,
            AsyncAnnotationMessageService asyncAnnotationMessageService,
            List<OperationBindingProcessor> operationBindingProcessors,
            StringValueResolverProxy stringValueResolver,
            AsyncAnnotationProvider<AsyncPublisher> asyncPublisherAnnotationProvider) {
        ChannelsScanner asyncListenerMethodLevelAnnotationChannelScanner =
                scannerAutoConfiguration.asyncListenerMethodLevelAnnotationChannelScanner(
                        asyncListenerAnnotationProvider,
                        springwolfClassScanner,
                        asyncApiDocketService,
                        asyncAnnotationMessageService,
                        operationBindingProcessors,
                        stringValueResolver);
        ChannelsScanner asyncListenerClassLevelAnnotationChannelScanner =
                scannerAutoConfiguration.asyncListenerClassLevelAnnotationChannelScanner(
                        asyncListenerAnnotationProvider,
                        springwolfClassScanner,
                        asyncApiDocketService,
                        asyncAnnotationMessageService,
                        operationBindingProcessors,
                        stringValueResolver);

        ChannelsScanner asyncPublisherClassLevelChannelAnnotationScanner =
                scannerAutoConfiguration.asyncPublisherClassLevelChannelAnnotationScanner(
                        asyncPublisherAnnotationProvider,
                        springwolfClassScanner,
                        asyncApiDocketService,
                        asyncAnnotationMessageService,
                        operationBindingProcessors,
                        stringValueResolver);
        ChannelsScanner asyncPublisherClassLevelAnnotationChannelScanner =
                scannerAutoConfiguration.asyncPublisherClassLevelAnnotationChannelScanner(
                        asyncPublisherAnnotationProvider,
                        springwolfClassScanner,
                        asyncApiDocketService,
                        asyncAnnotationMessageService,
                        operationBindingProcessors,
                        stringValueResolver);
        return List.of(
                asyncListenerMethodLevelAnnotationChannelScanner,
                asyncListenerClassLevelAnnotationChannelScanner,
                asyncPublisherClassLevelChannelAnnotationScanner,
                asyncPublisherClassLevelAnnotationChannelScanner);
    }

    private static List<SchemasPostProcessor> createSchemaPostProcessors(
            SpringwolfAutoConfiguration autoConfiguration, SpringwolfConfigProperties properties) {
        SchemaWalker jsonSchemaWalker = autoConfiguration.jsonSchemaWalker();
        ExampleXmlValueSerializer exampleXmlValueSerializer = autoConfiguration.exampleXmlValueSerializer();
        SchemaWalker xmlSchemaWalker = autoConfiguration.xmlSchemaWalker(exampleXmlValueSerializer);
        ExampleYamlValueSerializer exampleYamlValueSerializer = autoConfiguration.exampleYamlValueSerializer();
        SchemaWalker yamlSchemaWalker = autoConfiguration.yamlSchemaWalker(exampleYamlValueSerializer, properties);
        List<SchemaWalker> schemaWalkers = List.of(jsonSchemaWalker, xmlSchemaWalker, yamlSchemaWalker);
        SchemaWalkerProvider schemaWalkerProvider = autoConfiguration.schemaWalkerProvider(schemaWalkers);
        ExampleGeneratorPostProcessor exampleGeneratorPostProcessor =
                autoConfiguration.exampleGeneratorPostProcessor(schemaWalkerProvider);
        AvroSchemaPostProcessor avroSchemaPostProcessor = autoConfiguration.avroSchemaPostProcessor();
        return List.of(avroSchemaPostProcessor, exampleGeneratorPostProcessor);
    }

    @Override
    public AsyncApiService getAsyncApiService() {
        return this.asyncApiService;
    }
}
