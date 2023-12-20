// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.model.security_scheme.oauth2.flows;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ImplicitOAuthFlow extends OAuthFlow {
    /**
     * REQUIRED. The authorization URL to be used for this flow. This MUST be in the form of an absolute URL.
     */
    @NotNull
    @JsonProperty("authorizationUrl")
    private String authorizationUrl;

    @Builder(builderMethodName = "implicitBuilder")
    public ImplicitOAuthFlow(
            String refreshUrl, @NotNull Map<String, String> availableScopes, @NotNull String authorizationUrl) {
        super(refreshUrl, availableScopes);
        this.authorizationUrl = authorizationUrl;
    }
}
