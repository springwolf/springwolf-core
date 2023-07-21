package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.configuration.properties.SpringWolfConfigProperties.LoadingMode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

/**
 * Spring ApplicationListener listening on {@link ApplicationReadyEvent}. If loadingMode equals {@link LoadingMode#BACKGROUND}
 * this listener triggers background AsyncAPI creation.
 */
@Slf4j
@RequiredArgsConstructor
@Service
@ConditionalOnProperty(name= "springwolf.loading-mode", havingValue = "BACKGROUND")
public class AsyncApiApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    private final TaskExecutor taskExecutor;
    private final AsyncApiService asyncApiService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.debug("triggering background asyncapi creation..");
        taskExecutor.execute(this.asyncApiService::getAsyncAPI);
    }
}
