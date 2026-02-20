// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.channel.inferrer;

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

    @Nested
    class ExplicitChannelName {

        @Test
        void returnsExplicitChannelName() throws Exception {
            // given
            AsyncOperation op = asyncOp(WithExplicitChannel.class);

            // when
            ChannelNameResolver resolver = new ChannelNameResolver(List.of(), stringValueResolver);
            String result = resolver.resolve(op, anyMethod());

            // then
            assertThat(result).isEqualTo("my-explicit-channel");
        }

        @Test
        void resolvesPropertyPlaceholderInExplicitName() throws Exception {
            // given
            doAnswer(invocation -> {
                        String arg = (String) invocation.getArguments()[0];
                        if ("${my.channel}".equals(arg)) return "resolved-channel";
                        return arg;
                    })
                    .when(stringValueResolver)
                    .resolveStringValue(any());
            AsyncOperation op = asyncOp(WithPlaceholderChannel.class);

            // when
            ChannelNameResolver resolver = new ChannelNameResolver(List.of(), stringValueResolver);
            String result = resolver.resolve(op, anyMethod());

            // then
            assertThat(result).isEqualTo("resolved-channel");
        }

        @Test
        void explicitChannelNameSkipsInferrers() throws Exception {
            // given
            ChannelNameInferrer inferrer = mock(ChannelNameInferrer.class);
            AsyncOperation op = asyncOp(WithExplicitChannel.class);

            // when
            ChannelNameResolver resolver = new ChannelNameResolver(List.of(inferrer), stringValueResolver);
            String result = resolver.resolve(op, anyMethod());

            // then
            assertThat(result).isEqualTo("my-explicit-channel");
            verify(inferrer, never()).inferChannelName(any());
        }
    }

    @Nested
    class InferredChannelName {

        @Test
        void infersFromFirstInferrerWhenExplicitIsBlank() throws Exception {
            // given
            ChannelNameInferrer inferrer = method -> Optional.of("inferred-queue");
            AsyncOperation op = asyncOp(WithBlankChannel.class);

            // when
            ChannelNameResolver resolver = new ChannelNameResolver(List.of(inferrer), stringValueResolver);
            String result = resolver.resolve(op, anyMethod());

            // then
            assertThat(result).isEqualTo("inferred-queue");
        }

        @Test
        void skipsEmptyInferrerAndUsesNext() throws Exception {
            // given
            ChannelNameInferrer emptyInferrer = method -> Optional.empty();
            ChannelNameInferrer goodInferrer = method -> Optional.of("second-inferrer-channel");
            AsyncOperation op = asyncOp(WithBlankChannel.class);

            // when
            ChannelNameResolver resolver =
                    new ChannelNameResolver(List.of(emptyInferrer, goodInferrer), stringValueResolver);
            String result = resolver.resolve(op, anyMethod());

            // then
            assertThat(result).isEqualTo("second-inferrer-channel");
        }

        @Test
        void firstNonEmptyInferrerWins() throws Exception {
            // given
            ChannelNameInferrer first = method -> Optional.of("first-channel");
            ChannelNameInferrer second = method -> Optional.of("second-channel");
            AsyncOperation op = asyncOp(WithBlankChannel.class);

            // when
            ChannelNameResolver resolver = new ChannelNameResolver(List.of(first, second), stringValueResolver);
            String result = resolver.resolve(op, anyMethod());

            // then
            assertThat(result).isEqualTo("first-channel");
        }

        @Test
        void infersWhenPreferredPropertyResolvesToBlank() throws Exception {
            // given
            doAnswer(invocation -> "").when(stringValueResolver).resolveStringValue(any());

            ChannelNameInferrer inferrer = method -> Optional.of("fallback-channel");
            ChannelNameResolver resolver = new ChannelNameResolver(List.of(inferrer), stringValueResolver);
            AsyncOperation op = asyncOp(WithPlaceholderThatResolvesToBlank.class);

            // when
            String result = resolver.resolve(op, anyMethod());

            // then
            assertThat(result).isEqualTo("fallback-channel");
        }
    }

    @Nested
    class ErrorPaths {

        @Test
        void throwsWhenExplicitBlankAndNoInferrers() throws Exception {
            // given
            ChannelNameResolver resolver = new ChannelNameResolver(List.of(), stringValueResolver);
            AsyncOperation op = asyncOp(WithBlankChannel.class);
            Method method = anyMethod();

            // when // then
            assertThatThrownBy(() -> resolver.resolve(op, method))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("No channelName could be resolved");
        }

        @Test
        void throwsWhenExplicitBlankAndAllInferrersEmpty() throws Exception {
            // given
            ChannelNameInferrer empty1 = m -> Optional.empty();
            ChannelNameInferrer empty2 = m -> Optional.empty();
            ChannelNameResolver resolver = new ChannelNameResolver(List.of(empty1, empty2), stringValueResolver);
            AsyncOperation op = asyncOp(WithBlankChannel.class);
            Method method = anyMethod();

            // when // then
            assertThatThrownBy(() -> resolver.resolve(op, method))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("No channelName could be resolved");
        }

        @Test
        void treatsWhitespaceAsBlank() {
            // given
            ChannelNameInferrer inferrer = method -> Optional.of("   ");
            ChannelNameResolver resolver = new ChannelNameResolver(List.of(inferrer), stringValueResolver);
            AsyncOperation op = asyncOp(WithWhitespaceOnlyChannel.class);

            // when // then
            assertThatThrownBy(() -> resolver.resolve(op, anyMethod()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("No channelName could be resolved");
        }
    }

    private AsyncOperation asyncOp(Class<?> clazz) {
        return clazz.getAnnotation(TestListener.class).operation();
    }

    private Method anyMethod() throws NoSuchMethodException {
        return HelperClass.class.getDeclaredMethod("method", String.class);
    }

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
