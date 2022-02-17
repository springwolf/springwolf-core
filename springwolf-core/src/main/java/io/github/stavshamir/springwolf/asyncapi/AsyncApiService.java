package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.asyncapi.types.AsyncAPI;
import org.springframework.stereotype.Service;

@Service
public interface AsyncApiService {

    AsyncAPI getAsyncAPI();

}
