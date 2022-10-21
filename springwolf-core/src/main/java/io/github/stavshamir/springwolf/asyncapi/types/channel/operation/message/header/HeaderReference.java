package io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class HeaderReference {

    @Getter
    @EqualsAndHashCode.Include
    @ToString.Include
    private String $ref;

    private HeaderReference(String $ref) {
        this.$ref = $ref;
    }

    public static HeaderReference fromModelName(String modelName) {
        return new HeaderReference("#/components/schemas/" + modelName);
    }

}
