// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.model.security_scheme;

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
public class ApiKeySecurityScheme extends SecurityScheme {

    /**
     * REQUIRED. The location of the API key. Valid values are "user" and "password" for apiKey
     */
    @NotNull
    @JsonProperty("in")
    private ApiKeyLocation in;

    @Builder(builderMethodName = "apiKeyBuilder")
    public ApiKeySecurityScheme(String description, @NotNull ApiKeyLocation in, String ref) {
        super(SecurityType.API_KEY, description, ref);
        this.in = in;
    }

    public enum ApiKeyLocation {
        @JsonProperty("user")
        USER,
        @JsonProperty("password")
        PASSWORD
    }
}
