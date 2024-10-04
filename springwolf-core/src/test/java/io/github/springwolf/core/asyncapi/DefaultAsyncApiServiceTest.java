// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi;

import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.core.asyncapi.channels.ChannelsService;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.operations.OperationsService;
import io.github.springwolf.core.configuration.docket.AsyncApiDocket;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import io.github.springwolf.core.configuration.docket.AsyncApiGroup;
import io.github.springwolf.core.fixtures.AsyncApiDocketFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Pure unit tests of {@link DefaultAsyncApiService}. Faking spring context support
 */
class DefaultAsyncApiServiceTest {

    private DefaultAsyncApiService defaultAsyncApiService;
    private AsyncApiDocketService asyncApiDocketService;
    private ChannelsService channelsService;
    private OperationsService operationsService;
    private ComponentsService componentsService;
    private List<AsyncApiCustomizer> customizers = new ArrayList<>();
    private List<AsyncApiGroup> asyncApiGroups = new ArrayList<>();

    @BeforeEach
    public void setup() {
        asyncApiDocketService = mock(AsyncApiDocketService.class);
        channelsService = mock(ChannelsService.class);
        operationsService = mock(OperationsService.class);
        componentsService = mock(ComponentsService.class);

        when(channelsService.findChannels()).thenReturn(Map.of());
        when(componentsService.getSchemas()).thenReturn(Map.of());

        defaultAsyncApiService = new DefaultAsyncApiService(
                asyncApiDocketService, channelsService, operationsService, componentsService, customizers, asyncApiGroups);
    }

    @Test
    void shouldThrowExceptionOnSubsequentGetAsyncApi() {
        // Given an AsyncApiDocketService which throws an exception on 'getAsyncApiDocket()'
        when(asyncApiDocketService.getAsyncApiDocket()).thenThrow(new RuntimeException("test exception"));

        // When getAsyncAPI is invoked the first time
        Throwable cause = null;
        try {
            defaultAsyncApiService.getAsyncAPI();
            fail("RuntimeException expected");
        } catch (RuntimeException exc) {
            // Then a RuntimeException is thrown.
            assertThat(exc.getMessage()).isEqualTo("Error occured during creation of AsyncAPI");
            cause = exc.getCause();
            assertThat(cause.getMessage()).isEqualTo("test exception");
        }

        // When further invocations of getAsyncAPI
        try {
            defaultAsyncApiService.getAsyncAPI();
            fail("RuntimeException expected");
        } catch (RuntimeException exc) {
            // Then the same RuntimeException as on first invocatin should be thrown.

            assertThat(exc.getMessage()).isEqualTo("Error occured during creation of AsyncAPI");
            assertThat(exc.getCause()).isSameAs(cause);
        }
    }

    @Test
    void shouldLazyInitializeOnLoadingModeLazy() {
        // Given a valid Docket-Specification
        AsyncApiDocket docket = AsyncApiDocketFixture.createMinimal();
        when(asyncApiDocketService.getAsyncApiDocket()).thenReturn(docket);

        // When getAsyncAPI() isn't called yet
        // Then isNotInitilized() returns 'true'.
        assertThat(defaultAsyncApiService.isNotInitialized()).isTrue();

        // When getAsyncAPI() is invoked
        AsyncAPI asyncAPI = defaultAsyncApiService.getAsyncAPI();

        // Then the result is a non-null AsyncAPI instance and
        // isNotInitialized() return 'false'.
        assertThat(asyncAPI).isNotNull();
        assertThat(defaultAsyncApiService.isNotInitialized()).isFalse();
    }
}
