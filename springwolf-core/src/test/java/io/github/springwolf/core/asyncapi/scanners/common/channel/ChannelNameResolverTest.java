// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.channel;

import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringValueResolver;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class ChannelNameResolverTest {

    private final StringValueResolver stringValueResolver = mock(StringValueResolver.class);

    @BeforeEach
    void setUp() {
        // Default: pass-through resolver
        doAnswer(invocation -> invocation.getArgument(0))
                .when(stringValueResolver)
                .resolveStringValue(any());
    }

    // ---- helpers to grab AsyncOperation from test annotations ----

    private AsyncOperation asyncOp(Class<?> clazz) {
        return clazz.getAnnotation(TestListener.class).operation();
    }

    private Method anyMethod() throws NoSuchMethodException {
        return HelperClass.class.getDeclaredMethod("method", String.class);
    }

    // ================================================================
    // Explicit channelName tests
    // ================================================================

    @Nested
    class ExplicitChannelName {

        @Test
        void returnsExplicitChannelName() throws Exception {
            ChannelNameResolver resolver = new ChannelNameResolver(List.of(), stringValueResolver);
            AsyncOperation op = asyncOp(WithExplicitChannel.class);

            String result = resolver.resolve(op, anyMethod());

            assertThat(result).isEqualTo("my-explicit-channel");
        }

        @Test
        void resolvesPropertyPlaceholderInExplicitName() throws Exception {
            doAnswer(invocation -> {
                        String arg = (String) invocation.getArguments()[0];
                        if ("${my.channel}".equals(arg)) return "resolved-channel";
                        return arg;
                    })
                    .when(stringValueResolver)
                    .resolveStringValue(any());

            ChannelNameResolver resolver = new ChannelNameResolver(List.of(), stringValueResolver);
            AsyncOperation op = asyncOp(WithPlaceholderChannel.class);

            String result = resolver.resolve(op, anyMethod());

            assertThat(result).isEqualTo("resolved-channel");
        }

        @Test
        void explicitChannelNameSkipsInferrers() throws Exception {
            ChannelNameInferrer inferrer = mock(ChannelNameInferrer.class);
            ChannelNameResolver resolver = new ChannelNameResolver(List.of(inferrer), stringValueResolver);
            AsyncOperation op = asyncOp(WithExplicitChannel.class);

            String result = resolver.resolve(op, anyMethod());

            assertThat(result).isEqualTo("my-explicit-channel");
            verify(inferrer, never()).inferChannelName(any());
        }
    }

    // ================================================================
    // Inference tests
    // ================================================================

    @Nested
    class InferredChannelName {

        @Test
        void infersFromFirstInferrerWhenExplicitIsBlank() throws Exception {
            ChannelNameInferrer inferrer = method -> Optional.of("inferred-queue");
            ChannelNameResolver resolver = new ChannelNameResolver(List.of(inferrer), stringValueResolver);
            AsyncOperation op = asyncOp(WithBlankChannel.class);

            String result = resolver.resolve(op, anyMethod());

            assertThat(result).isEqualTo("inferred-queue");
        }

        @Test
        void skipsEmptyInferrerAndUsesNext() throws Exception {
            ChannelNameInferrer emptyInferrer = method -> Optional.empty();
            ChannelNameInferrer goodInferrer = method -> Optional.of("second-inferrer-channel");
            ChannelNameResolver resolver =
                    new ChannelNameResolver(List.of(emptyInferrer, goodInferrer), stringValueResolver);
            AsyncOperation op = asyncOp(WithBlankChannel.class);

            String result = resolver.resolve(op, anyMethod());

            assertThat(result).isEqualTo("second-inferrer-channel");
        }

        @Test
        void firstNonEmptyInferrerWins() throws Exception {
            ChannelNameInferrer first = method -> Optional.of("first-channel");
            ChannelNameInferrer second = method -> Optional.of("second-channel");
            ChannelNameResolver resolver = new ChannelNameResolver(List.of(first, second), stringValueResolver);
            AsyncOperation op = asyncOp(WithBlankChannel.class);

            String result = resolver.resolve(op, anyMethod());

            assertThat(result).isEqualTo("first-channel");
        }

        @Test
        void infersWhenPropertyResolvesToBlank() throws Exception {
            doAnswer(invocation -> {
                        String arg = (String) invocation.getArguments()[0];
                        if ("${empty.channel}".equals(arg)) return "";
                        return arg;
                    })
                    .when(stringValueResolver)
                    .resolveStringValue(any());

            ChannelNameInferrer inferrer = method -> Optional.of("fallback-channel");
            ChannelNameResolver resolver = new ChannelNameResolver(List.of(inferrer), stringValueResolver);
            AsyncOperation op = asyncOp(WithPlaceholderThatResolvesToBlank.class);

            String result = resolver.resolve(op, anyMethod());

            assertThat(result).isEqualTo("fallback-channel");
        }
    }

    // ================================================================
    // Edge cases / error paths
    // ================================================================

    @Nested
    class ErrorPaths {

        @Test
        void throwsWhenExplicitBlankAndNoInferrers() throws Exception {
            ChannelNameResolver resolver = new ChannelNameResolver(List.of(), stringValueResolver);
            AsyncOperation op = asyncOp(WithBlankChannel.class);
            Method method = anyMethod();

            assertThatThrownBy(() -> resolver.resolve(op, method))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("No channelName was set in @AsyncOperation");
        }

        @Test
        void throwsWhenExplicitBlankAndAllInferrersEmpty() throws Exception {
            ChannelNameInferrer empty1 = m -> Optional.empty();
            ChannelNameInferrer empty2 = m -> Optional.empty();
            ChannelNameResolver resolver = new ChannelNameResolver(List.of(empty1, empty2), stringValueResolver);
            AsyncOperation op = asyncOp(WithBlankChannel.class);
            Method method = anyMethod();

            assertThatThrownBy(() -> resolver.resolve(op, method))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("No channelName was set in @AsyncOperation");
        }

        @Test
        void treatsWhitespaceOnlyAsBlank() throws Exception {
            ChannelNameInferrer inferrer = method -> Optional.of("inferred-from-whitespace");
            ChannelNameResolver resolver = new ChannelNameResolver(List.of(inferrer), stringValueResolver);
            AsyncOperation op = asyncOp(WithWhitespaceOnlyChannel.class);

            String result = resolver.resolve(op, anyMethod());

            assertThat(result).isEqualTo("inferred-from-whitespace");
        }
    }

    // ================================================================
    // Test helper annotations and classes
    // ================================================================

    @Retention(RetentionPolicy.RUNTIME)
    @interface TestListener {
        AsyncOperation operation();
    }

    @TestListener(operation = @AsyncOperation(channelName = "my-explicit-channel"))
    private static class WithExplicitChannel {}

    @TestListener(operation = @AsyncOperation(channelName = "${my.channel}"))
    private static class WithPlaceholderChannel {}

    @TestListener(operation = @AsyncOperation(channelName = "${empty.channel}"))
    private static class WithPlaceholderThatResolvesToBlank {}

    @TestListener(operation = @AsyncOperation())
    private static class WithBlankChannel {}

    @TestListener(operation = @AsyncOperation(channelName = "   "))
    private static class WithWhitespaceOnlyChannel {}

    private static class HelperClass {
        private void method(String payload) {}
    }
}
