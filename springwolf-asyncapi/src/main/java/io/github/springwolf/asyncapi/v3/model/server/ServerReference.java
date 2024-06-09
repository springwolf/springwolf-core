// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.server;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.springwolf.asyncapi.v3.model.Reference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServerReference implements Reference {
    @JsonIgnore
    private String ref;

    @Override
    public String getRef() {
        return ref;
    }

    public static ServerReference fromServer(String server) {
        return new ServerReference("#/servers/" + server);
    }
}
