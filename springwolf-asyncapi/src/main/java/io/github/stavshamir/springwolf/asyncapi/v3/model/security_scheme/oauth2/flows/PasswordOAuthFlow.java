// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.security_scheme.oauth2.flows;

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
public class PasswordOAuthFlow extends OAuthFlow {
    /**
     * REQUIRED. The token URL to be used for this flow. This MUST be in the form of an absolute URL.
     */
    @NotNull
    @JsonProperty("tokenUrl")
    private String tokenUrl;

    @Builder(builderMethodName = "passwordBuilder")
    public PasswordOAuthFlow(
            String refreshUrl, @NotNull Map<String, String> availableScopes, @NotNull String tokenUrl) {
        super(refreshUrl, availableScopes);
        this.tokenUrl = tokenUrl;
    }
}
