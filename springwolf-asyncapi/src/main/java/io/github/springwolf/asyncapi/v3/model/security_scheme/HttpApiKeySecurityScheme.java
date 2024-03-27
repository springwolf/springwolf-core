// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.security_scheme;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HttpApiKeySecurityScheme extends SecurityScheme {
    /**
     * REQUIRED. The name of the header, query or cookie parameter to be used.
     */
    @NotNull
    @JsonProperty("name")
    private String name;

    /**
     * REQUIRED. The location of the API key. Valid values are "query", "header" or "cookie" for httpApiKey.
     */
    @NotNull
    @JsonProperty("in")
    private HttpApiKeyLocation in;

    @Builder(builderMethodName = "httpApiKeyBuilder")
    public HttpApiKeySecurityScheme(String name, @NotNull HttpApiKeyLocation in, String description, String ref) {
        super(SecurityType.HTTP_API_KEY, description, ref);
        this.name = name;
        this.in = in;
    }

    public enum HttpApiKeyLocation {
        QUERY("query"),
        HEADER("header"),
        COOKIE("cookie");

        public final String type;

        HttpApiKeyLocation(String type) {
            this.type = type;
        }

        public static HttpApiKeyLocation fromString(String type) {
            return valueOf(type.toUpperCase());
        }

        @Override
        public String toString() {
            return this.type;
        }
    }
}
