// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.controller;

import io.github.stavshamir.springwolf.asyncapi.controller.dtos.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Used in plugins with publishing enabled.
 * Located in springwolf-core to allow sharing of code
 */
@RequiredArgsConstructor
@Slf4j
public abstract class PublishingBaseController implements InitializingBean {

    private final PublishingPayloadCreator publishingPayloadCreator;

    protected abstract boolean isEnabled();

    protected abstract void publishMessage(String topic, MessageDto message, Object payload);

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam String topic, @RequestBody MessageDto message) {
        if (!isEnabled()) {
            String errorMessage = "Publishing using %s is not enabled - message will not be published"
                    .formatted(this.getClass().getSimpleName());
            log.warn(errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }

        PublishingPayloadCreator.Result result = publishingPayloadCreator.createPayloadObject(message);
        if (result.payload() != null) {
            publishMessage(topic, message, result.payload());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body(result.errorMessage());
    }

    @Override
    public void afterPropertiesSet() {
        log.debug(
                "Message publishing via %s is active.".formatted(this.getClass().getSimpleName()));
    }
}
