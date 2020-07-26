package io.github.stavshamir.springwolf.asyncapi.types.channel.operation.bindings.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode
public class GroupId {

    public GroupId(List<String> _enum) {
        this._enum = _enum;
    }

    @Getter
    private final String type = "string";

    @Getter
    @JsonProperty("enum")
    private List<String> _enum;

    public static GroupId of(String groupId) {
        return new GroupId(Collections.singletonList(groupId));
    }

}
