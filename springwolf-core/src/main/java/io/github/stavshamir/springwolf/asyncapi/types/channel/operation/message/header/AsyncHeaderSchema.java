package io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.models.media.StringSchema;
import lombok.Builder;

import java.util.List;

public class AsyncHeaderSchema extends StringSchema {
    @JsonIgnore
    private final String headerName;
    
    public AsyncHeaderSchema(String headerName){
        this.headerName = headerName;
    }

    public String getHeaderName() {
        return headerName;
    }

    @Builder(builderMethodName = "headerBuilder")
    private static AsyncHeaderSchema createHeader(String headerName, String description, String example, List<String> enumValue) {
        AsyncHeaderSchema header = new AsyncHeaderSchema(headerName);
        header.setDescription(description);
        header.setExample(example);
        header.setEnum(enumValue);
        return header;
    }
}
