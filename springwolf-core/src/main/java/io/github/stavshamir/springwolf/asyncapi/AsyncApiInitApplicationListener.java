package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.configuration.properties.SpringWolfConfigProperties;
import io.github.stavshamir.springwolf.configuration.properties.SpringWolfConfigProperties.InitMode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

/**
 * Spring ApplicationListener listening on {@link ApplicationReadyEvent}. Triggers the AsyncAPI creation.
 * If loadingMode equals {@link InitMode#BACKGROUND} AsyncAPI creation is triggered asynchronously using the
 * provided Spring TaskExecutor.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AsyncApiInitApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    private final TaskExecutor taskExecutor;
    private final AsyncApiService asyncApiService;
    private final SpringWolfConfigProperties configProperties;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (configProperties.getInitMode() == InitMode.FAIL_FAST) {
            log.debug("triggering asyncapi creation..");
            this.asyncApiService.getAsyncAPI();
        } else {
            log.debug("triggering background asyncapi creation..");
            taskExecutor.execute(this.asyncApiService::getAsyncAPI);
        }
    }
}
