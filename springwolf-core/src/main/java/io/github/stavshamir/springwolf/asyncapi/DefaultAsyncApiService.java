package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.asyncapi.types.AsyncAPI;
import io.github.stavshamir.springwolf.asyncapi.types.Components;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultAsyncApiService implements AsyncApiService {

    private final AsyncApiDocket docket;
    private final ChannelsService channelsService;
    private final SchemasService schemasService;

    private AsyncAPI asyncAPI;

    @PostConstruct
    void buildAsyncApi() {
        log.debug("Building AsyncAPI document");

        Components components = Components.builder()
                .schemas(schemasService.getDefinitions())
                .build();

        asyncAPI = AsyncAPI.builder()
                .info(docket.getInfo())
                .servers(docket.getServers())
                .channels(channelsService.getChannels())
                .components(components)
                .build();
    }

    @Override
    public AsyncAPI getAsyncAPI() {
        return asyncAPI;
    }

}
