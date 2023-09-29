package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties.InitMode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * Spring ApplicationListener listening on {@link ApplicationReadyEvent}. Triggers the AsyncAPI creation.
 * If loadingMode equals {@link InitMode#BACKGROUND} AsyncAPI creation is triggered asynchronously using the
 * provided Spring TaskExecutor.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SpringwolfInitApplicationListener implements ApplicationListener<ApplicationReadyEvent>, InitializingBean {

    private final AsyncApiService asyncApiService;
    private final SpringwolfConfigProperties configProperties;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (configProperties.getInitMode() == InitMode.BACKGROUND) {
            log.debug("triggering background asyncapi creation..");
            new Thread(asyncApiService::getAsyncAPI).start();
        }
    }

    @Override
    public void afterPropertiesSet() {
        if (configProperties.getInitMode() == InitMode.FAIL_FAST) {
            log.debug("triggering asyncapi creation..");
            this.asyncApiService.getAsyncAPI();
        }
    }
}
