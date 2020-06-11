package io.github.stavshamir.swagger4kafka.asyncapi.types.channel.operation.message;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
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
