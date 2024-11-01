// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi;

import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.components.Components;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.core.asyncapi.channels.ChannelsService;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.grouping.AsyncApiGroupService;
import io.github.springwolf.core.asyncapi.operations.OperationsService;
import io.github.springwolf.core.configuration.docket.AsyncApiDocket;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class DefaultAsyncApiService implements AsyncApiService {

    /**
     * Record holding the result of AsyncAPI creation.
     *
     * @param asyncAPI
     * @param exception
     */
    private record AsyncAPIResult(AsyncAPI asyncAPI, Throwable exception) {}
    // -> master (internal)
    // -> per group

    private final AsyncApiDocketService asyncApiDocketService;
    private final ChannelsService channelsService;
    private final OperationsService operationsService;
    private final ComponentsService componentsService;
    private final List<AsyncApiCustomizer> customizers;
    private final AsyncApiGroupService groupService;

    private volatile AsyncAPIResult asyncAPIResult = null;
    private volatile Map<String, AsyncAPI> asyncApiGroupMap = new HashMap<>();

    @Override
    public AsyncAPI getAsyncAPI() {
        if (isNotInitialized()) {
            initAsyncAPI();
        }

        if (asyncAPIResult.asyncAPI != null) {
            return asyncAPIResult.asyncAPI;
        } else {
            throw new RuntimeException("Error occurred during creation of AsyncAPI", asyncAPIResult.exception);
        }
    }

    @Override
    public Optional<AsyncAPI> getForGroupName(String groupName) {
        if (isNotInitialized()) {
            initAsyncAPI();
        }

        // TODO: should be grouping be part of the AsyncAPIResult class?
        return Optional.ofNullable(this.asyncApiGroupMap.get(groupName));
    }

    /**
     * Does the 'heavy work' of building the AsyncAPI documents once. Stores the resulting
     * AsyncAPI document or alternatively a caught exception/error in the instance variable asyncAPIResult.
     */
    protected synchronized void initAsyncAPI() {
        if (this.asyncAPIResult != null) {
            return; // Double Check Idiom
        }

        try {
            log.debug("Building AsyncAPI document");

            AsyncApiDocket docket = asyncApiDocketService.getAsyncApiDocket();

            // ChannelsService must be invoked before accessing SchemasService,
            // because during channel scanning, all detected schemas are registered with
            // SchemasService.
            Map<String, ChannelObject> channels = channelsService.findChannels();

            Map<String, Operation> operations = operationsService.findOperations();

            Components components = Components.builder()
                    .schemas(componentsService.getSchemas())
                    .messages(componentsService.getMessages())
                    .build();

            AsyncAPI asyncAPI = AsyncAPI.builder()
                    .info(docket.getInfo())
                    .id(docket.getId())
                    .defaultContentType(docket.getDefaultContentType())
                    .servers(docket.getServers())
                    .channels(channels)
                    .operations(operations)
                    .components(components)
                    .build();

            //            master = asyncAPI;

            for (AsyncApiCustomizer customizer : customizers) {
                log.debug("Starting customizer {}", customizer.getClass().getName());
                customizer.customize(asyncAPI);
            }
            this.asyncAPIResult = new AsyncAPIResult(asyncAPI, null);
            this.asyncApiGroupMap = groupService.group(asyncAPI);

            log.debug("AsyncAPI document was built");
        } catch (Throwable t) {
            log.debug("Failed to build AsyncAPI document", t);
            this.asyncAPIResult = new AsyncAPIResult(null, t);
        }
    }

    /**
     * checks whether asyncApi has internally already been initialized or not.
     *
     * @return true if asyncApi has not allready been created and initialized.
     */
    public boolean isNotInitialized() {
        return this.asyncAPIResult == null;
    }
}
