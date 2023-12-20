// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.model.security_scheme;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.security_scheme.oauth2.OAuthFlows;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OAuth2SecurityScheme extends SecurityScheme {

    /**
     * REQUIRED. An object containing configuration information for the flow types supported.
     */
    @NotNull
    @JsonProperty("flows")
    private OAuthFlows flows;

    /**
     * List of the needed scope names. An empty array means no scopes are needed.
     */
    @JsonProperty("scopes")
    private List<String> scopes;

    @Builder(builderMethodName = "oAuth2Builder")
    public OAuth2SecurityScheme(@NotNull OAuthFlows flows, List<String> scopes, String description, String ref) {
        super(SecurityType.OAUTH2, description, ref);
        this.flows = flows;
        this.scopes = scopes;
    }
}
