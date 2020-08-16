package io.github.stavshamir.springwolf.schemas;

import io.swagger.v3.oas.models.media.Schema;

import java.util.Map;

public interface SchemasService {

    Map<String, Schema> getDefinitions();

    String register(Class<?> type);

}
