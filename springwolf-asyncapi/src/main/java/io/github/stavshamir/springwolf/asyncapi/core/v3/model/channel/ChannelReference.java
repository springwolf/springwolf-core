// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.model.channel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.Reference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelReference implements Reference {
    @JsonIgnore
    private String ref;

    @Override
    public String getRef() {
        return ref;
    }
}
