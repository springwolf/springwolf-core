package io.github.stavshamir.springwolf.asyncapi;

import com.asyncapi.v2._6_0.model.info.Info;
import com.asyncapi.v2._6_0.model.server.Server;
import io.github.stavshamir.springwolf.asyncapi.types.AsyncAPI;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.configuration.properties.SpringWolfConfigProperties;
import io.github.stavshamir.springwolf.configuration.properties.SpringWolfConfigProperties.LoadingMode;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Pure unit tests of {@link DefaultAsyncApiService} without spring test support
 */
public class DefaultAsyncApiServiceUnitTest {

    private DefaultAsyncApiService defaultAsyncApiService;
    private SpringWolfConfigProperties configProperties = new SpringWolfConfigProperties();
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

        defaultAsyncApiService = new DefaultAsyncApiService(
                asyncApiDocketService, channelsService, schemasService, customizers, configProperties);
    }

    @Test
    void shouldEagerInitializeOnLoadingModeFailFast() {
        prepareValidDocket();
        configProperties.setLoadingMode(LoadingMode.FAIL_FAST);

        defaultAsyncApiService.afterPropertiesSet();
        assertThat(defaultAsyncApiService.isInitialized()).isTrue();
    }

    @Test
    void testExceptionOnFailFastInitialize() {
        prepareExceptionDocket();
        configProperties.setLoadingMode(LoadingMode.FAIL_FAST);

        assertThatThrownBy(() -> {
                    defaultAsyncApiService.afterPropertiesSet();
                })
                .isInstanceOfAny(RuntimeException.class)
                .hasMessage("Error occured during creation of AsyncAPI");
    }

    @Test
    void testExceptionOnLazyInitialize() {
        prepareExceptionDocket();
        configProperties.setLoadingMode(LoadingMode.LAZY);

        // should not initialize asyncapi, no exception expected.
        defaultAsyncApiService.afterPropertiesSet();

        Throwable cause = null;

        try {
            defaultAsyncApiService.getAsyncAPI();
            fail("RuntimeException expected");
        } catch (RuntimeException exc) {
            assertThat(exc.getMessage()).isEqualTo("Error occured during creation of AsyncAPI");
            cause = exc.getCause();
            assertThat(cause.getMessage()).isEqualTo("test exception");
        }

        // further invocations of getAsyncAPI should again throw RuntimeException.
        // cause should be same instance.
        try {
            defaultAsyncApiService.getAsyncAPI();
            fail("RuntimeException expected");
        } catch (RuntimeException exc) {
            assertThat(exc.getMessage()).isEqualTo("Error occured during creation of AsyncAPI");
            assertThat(exc.getCause()).isSameAs(cause);
        }
    }

    @Test
    void shouldLazyInitializeOnLoadingModeLazy() {
        prepareValidDocket();
        configProperties.setLoadingMode(LoadingMode.LAZY);
        defaultAsyncApiService.afterPropertiesSet();
        assertThat(defaultAsyncApiService.isInitialized()).isFalse();

        AsyncAPI asyncAPI = defaultAsyncApiService.getAsyncAPI();
        assertThat(asyncAPI).isNotNull();
        assertThat(defaultAsyncApiService.isInitialized()).isTrue();
    }

    /**
     * prepares prepares {@link AsyncApiDocketService} mock to throw Exception on getAsyncApiDocket().
     */
    private void prepareExceptionDocket() {
        when(asyncApiDocketService.getAsyncApiDocket()).thenThrow(new RuntimeException("test exception"));
    }

    /**
     * prepares {@link AsyncApiDocketService} mock to return a valid AsyncApiDocket.
     */
    private void prepareValidDocket() {
        Info info = Info.builder()
                .description("Info")
                .version("1.0")
                .title("Test AsyncApi")
                .build();

        Server server1 = Server.builder()
                .description("Server 1")
                .url("mq:/localhost:1234")
                .protocol("kafka")
                .build();

        AsyncApiDocket docket = AsyncApiDocket.builder()
                .id("id")
                .defaultContentType("application/json")
                .servers(Map.of("server", server1))
                .info(info)
                .build();

        when(asyncApiDocketService.getAsyncApiDocket()).thenReturn(docket);
    }
}
