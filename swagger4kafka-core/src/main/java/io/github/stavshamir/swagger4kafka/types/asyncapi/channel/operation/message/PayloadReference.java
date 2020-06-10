package io.github.stavshamir.swagger4kafka.types.asyncapi.channel.operation.message;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PayloadReference {

    @Getter
    private String $ref;

    private PayloadReference(String $ref) {
        this.$ref = $ref;
    }

    public static PayloadReference fromModelName(String modelName) {
        return new PayloadReference("#/components/schemas/" + modelName);
    }

}
