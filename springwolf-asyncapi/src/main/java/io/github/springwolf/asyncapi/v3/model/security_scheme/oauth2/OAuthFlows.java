// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.security_scheme.oauth2;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.springwolf.asyncapi.v3.model.ExtendableObject;
import io.github.springwolf.asyncapi.v3.model.security_scheme.oauth2.flows.AuthorizationCodeOAuthFlow;
import io.github.springwolf.asyncapi.v3.model.security_scheme.oauth2.flows.ClientCredentialsOAuthFlow;
import io.github.springwolf.asyncapi.v3.model.security_scheme.oauth2.flows.ImplicitOAuthFlow;
import io.github.springwolf.asyncapi.v3.model.security_scheme.oauth2.flows.PasswordOAuthFlow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Allows configuration of the supported OAuth Flows.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OAuthFlows extends ExtendableObject {
    /**
     * Configuration for the OAuth Implicit flow.
     */
    @JsonProperty("implicit")
    private ImplicitOAuthFlow implicit;

    /**
     * Configuration for the OAuth Resource Owner Protected Credentials flow.
     */
    @JsonProperty("password")
    private PasswordOAuthFlow password;

    /**
     * Configuration for the OAuth Client Credentials flow.
     */
    @JsonProperty("clientCredentials")
    private ClientCredentialsOAuthFlow clientCredentials;

    /**
     * Configuration for the OAuth Authorization Code flow.
     */
    @JsonProperty("authorizationCode")
    private AuthorizationCodeOAuthFlow authorizationCode;
}
