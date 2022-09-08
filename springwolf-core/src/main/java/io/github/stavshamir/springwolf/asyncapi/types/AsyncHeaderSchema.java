package io.github.stavshamir.springwolf.asyncapi.types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.models.media.StringSchema;

public class AsyncHeaderSchema extends StringSchema {
    @JsonIgnore
    private final String headerName;
    
    public AsyncHeaderSchema(String headerName){
        this.headerName = headerName;
    }

    public String getHeaderName() {
        return headerName;
    }
}
