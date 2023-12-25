// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.security_scheme;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OpenIdConnectSecurityScheme extends SecurityScheme {
    /**
     * REQUIRED. OpenId Connect URL to discover OAuth2 configuration values. This MUST be in the form of an absolute URL.
     */
    @NotNull
    @JsonProperty("openIdConnectUrl")
    private String openIdConnectUrl;

    /**
     * List of the needed scope names. An empty array means no scopes are needed.
     */
    @JsonProperty("scopes")
    private List<Object> scopes;
}
