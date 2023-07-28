package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.asyncapi.types.AsyncAPI;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.fixtures.AsyncApiDocketFixture;
import io.github.stavshamir.springwolf.schemas.SchemasService;
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
public class DefaultAsyncApiServiceTest {

    private DefaultAsyncApiService defaultAsyncApiService;
    private AsyncApiDocketService asyncApiDocketService;
    private ChannelsService channelsService;
    private SchemasService schemasService;
    private List<AsyncApiCustomizer> customizers = new ArrayList<>();

    @BeforeEach
    public void setup() {
        asyncApiDocketService = mock(AsyncApiDocketService.class);
        channelsService = mock(ChannelsService.class);
        schemasService = mock(SchemasService.class);

        when(channelsService.findChannels()).thenReturn(Map.of());
        when(schemasService.getDefinitions()).thenReturn(Map.of());

        defaultAsyncApiService =
                new DefaultAsyncApiService(asyncApiDocketService, channelsService, schemasService, customizers);
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
