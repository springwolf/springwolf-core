// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi;

import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.core.grouping.AsyncApiGroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Pure unit tests of {@link DefaultAsyncApiService}. Faking spring context support
 */
class DefaultAsyncApiServiceTest {

    private DefaultAsyncApiService defaultAsyncApiService;
    private AsyncApiCreator asyncApiCreator;
    private AsyncApiGroupService groupService;

    @BeforeEach
    void setup() {
        asyncApiCreator = mock(AsyncApiCreator.class);
        groupService = mock(AsyncApiGroupService.class);

        defaultAsyncApiService = new DefaultAsyncApiService(asyncApiCreator, groupService);
    }

    @Test
    void shouldThrowExceptionOnSubsequentGetAsyncApi() {
        // Given an AsyncApiDocketService which throws an exception
        when(asyncApiCreator.createAsyncApi()).thenThrow(new RuntimeException("test exception"));

        // When getAsyncAPI is invoked the first time
        Throwable cause = null;
        try {
            defaultAsyncApiService.getAsyncAPI();
            fail("RuntimeException expected");
        } catch (RuntimeException exc) {
            // Then a RuntimeException is thrown.
            assertThat(exc.getMessage()).isEqualTo("Error occurred during creation of AsyncAPI");
            cause = exc.getCause();
            assertThat(cause.getMessage()).isEqualTo("test exception");
        }

        // When further invocations of getAsyncAPI
        try {
            defaultAsyncApiService.getAsyncAPI();
            fail("RuntimeException expected");
        } catch (RuntimeException exc) {
            // Then the same RuntimeException as on first invocatin should be thrown.

            assertThat(exc.getMessage()).isEqualTo("Error occurred during creation of AsyncAPI");
            assertThat(exc.getCause()).isSameAs(cause);
        }
    }

    @Test
    void shouldLazyInitializeOnLoadingModeLazy() {
        AsyncAPI expectedAsyncApi = mock(AsyncAPI.class);
        when(asyncApiCreator.createAsyncApi()).thenReturn(expectedAsyncApi);

        // When getAsyncAPI() isn't called yet
        // Then isNotInitilized() returns 'true'.
        assertThat(defaultAsyncApiService.isNotInitialized()).isTrue();

        // When getAsyncAPI() is invoked
        AsyncAPI asyncAPI = defaultAsyncApiService.getAsyncAPI();

        // Then the result is a non-null AsyncAPI instance and
        // isNotInitialized() return 'false'.
        assertThat(asyncAPI).isEqualTo(expectedAsyncApi);
        assertThat(defaultAsyncApiService.isNotInitialized()).isFalse();
    }

    @Test
    void shouldGetGroupedAsyncApi() {
        // given
        AsyncAPI expectedAsyncApi = mock(AsyncAPI.class);
        when(asyncApiCreator.createAsyncApi()).thenReturn(expectedAsyncApi);
        AsyncAPI groupedAsyncAPI = mock(AsyncAPI.class);
        when(groupService.group(any())).thenReturn(Map.of("group", groupedAsyncAPI));

        // when
        AsyncAPI result = defaultAsyncApiService.getForGroupName("group");

        // then
        assertThat(result).isEqualTo(groupedAsyncAPI);
    }

    @Test
    void shouldNotThrowWhenAsyncApiGroupDoesNotExist() {
        // given
        AsyncAPI expectedAsyncApi = mock(AsyncAPI.class);
        when(asyncApiCreator.createAsyncApi()).thenReturn(expectedAsyncApi);

        // when
        try {
            defaultAsyncApiService.getForGroupName("group");
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException exc) {
            // then
            assertThat(exc.getMessage()).isEqualTo("AsyncAPI group group was not found");
        }
    }
}
