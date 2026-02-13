// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.channel;

import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

/**
 * Resolves the AsyncAPI channel name for a listener method.
 * <p>
 * Resolution order:
 * <ol>
 *   <li>If {@link AsyncOperation#channelName()} is non-blank (after property-placeholder resolution), use it.</li>
 *   <li>Otherwise, consult each registered {@link ChannelNameInferrer} in order and use the first non-blank result.</li>
 *   <li>If no name can be determined, an {@link IllegalArgumentException} is thrown.</li>
 * </ol>
 */
@Slf4j
@RequiredArgsConstructor
public class ChannelNameResolver {

    private final List<ChannelNameInferrer> channelNameInferrers;
    private final StringValueResolver stringValueResolver;

    public String resolve(AsyncOperation operationAnnotation, Method method) {
        String explicit = stringValueResolver.resolveStringValue(operationAnnotation.channelName());
        if (StringUtils.isNotBlank(explicit)) {
            log.debug("Using explicit channelName '{}' for method '{}'", explicit, method.getName());
            return explicit;
        }

        for (ChannelNameInferrer inferrer : channelNameInferrers) {
            Optional<String> inferred = inferrer.inferChannelName(method);
            if (inferred.isPresent() && StringUtils.isNotBlank(inferred.get())) {
                log.debug(
                        "Inferred channelName '{}' for method '{}' via {}",
                        inferred.get(),
                        method.getName(),
                        inferrer.getClass().getSimpleName());
                return inferred.get();
            }
        }

        log.warn(
                "No channelName could be resolved for method '{}': @AsyncOperation.channelName() is blank and no ChannelNameInferrer provided a value",
                method.getName());
        throw new IllegalArgumentException(
                "No channelName was set in @AsyncOperation and none could be inferred from the listener annotation on method: "
                        + method.getName());
    }
}
