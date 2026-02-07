// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.security_scheme;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.springwolf.asyncapi.v3.model.ExtendableObject;
import io.github.springwolf.asyncapi.v3.model.Reference;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Defines a security scheme that can be used by the operations. Supported schemes are:
 *<ul>
 *     <li>User/Password.</li>
 *     <li>API key (either as user or as password).</li>
 *     <li>X.509 certificate.</li>
 *     <li>End-to-end encryption (either symmetric or asymmetric).</li>
 *     <li>HTTP authentication.</li>
 *     <li>HTTP API key.</li>
 *     <li>OAuth2's common flows (Implicit, Resource Owner Protected Credentials, Client Credentials and Authorization Code) as defined in RFC6749.</li>
 *     <li>OpenID Connect Discovery.</li>
 *     <li>SASL (Simple Authentication and Security Layer) as defined in RFC4422.</li>
 * </ul>
 *
 * @see <a href="https://www.asyncapi.com/docs/reference/specification/v3.1.0#securitySchemeObject">Schema Scheme Object</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SecurityScheme extends ExtendableObject implements Reference {
    /**
     * REQUIRED. The type of the security scheme. Valid values are
     * <ul>
     *     <li>"userPassword"</li>
     *     <li>"apiKey"</li>
     *     <li>"X509"</li>
     *     <li>"symmetricEncryption"</li>
     *     <li>"asymmetricEncryption"</li>
     *     <li>"httpApiKey"</li>
     *     <li>"http"</li>
     *     <li>"oauth2"</li>
     *     <li>"openIdConnect"</li>
     *     <li>"plain"</li>
     *     <li>"scramSha256"</li>
     *     <li>"scramSha512"</li>
     *     <li>"gssapi"</li>
     * </ul>
     */
    @NotNull
    @JsonProperty("type")
    private SecurityType type;

    /**
     * A short description for security scheme. <a href="https://spec.commonmark.org/">CommonMark syntax</a> MAY be
     * used for rich text representation.
     */
    @JsonProperty(value = "description")
    private String description;

    @JsonIgnore
    private String ref;

    @Override
    public String getRef() {
        return ref;
    }
}
