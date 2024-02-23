// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.security_scheme.oauth2.flows;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.springwolf.asyncapi.v3.model.ExtendableObject;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Configuration details for a supported OAuth Flow
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OAuthFlow extends ExtendableObject {
    /**
     * The URL to be used for obtaining refresh tokens. This MUST be in the form of an absolute URL.
     */
    @JsonProperty("refreshUrl")
    private String refreshUrl;

    /**
     * REQUIRED. The available scopes for the OAuth2 security scheme. A map between the scope name and a short description for it.
     */
    @NotNull
    @JsonProperty("availableScopes")
    private Map<String, String> availableScopes;
}
