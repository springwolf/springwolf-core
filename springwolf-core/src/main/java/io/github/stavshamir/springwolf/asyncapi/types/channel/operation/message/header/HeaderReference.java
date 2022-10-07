package io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
public class HeaderReference {

    @Getter
    private String $ref;

    private HeaderReference(String $ref) {
        this.$ref = $ref;
    }

    public static HeaderReference fromModelName(String modelName) {
        return new HeaderReference("#/components/schemas/" + modelName);
    }

}
