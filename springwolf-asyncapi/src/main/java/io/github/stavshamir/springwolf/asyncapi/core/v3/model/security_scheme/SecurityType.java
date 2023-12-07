// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.model.security_scheme;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SecurityType {
    @JsonProperty("userPassword")
    USER_PASSWORD,
    @JsonProperty("apiKey")
    API_KEY,
    @JsonProperty("X509")
    X509,
    @JsonProperty("symmetricEncryption")
    SYMMETRIC_ENCRYPTION,
    @JsonProperty("asymmetricEncryption")
    ASYMMETRIC_ENCRYPTION,
    @JsonProperty("httpApiKey")
    HTTP_API_KEY,
    @JsonProperty("http")
    HTTP,
    @JsonProperty("oauth2")
    OAUTH2,
    @JsonProperty("openIdConnect")
    OPEN_ID_CONNECT,
    @JsonProperty("plain")
    PLAIN,
    @JsonProperty("scramSha256")
    SCRAM_SHA256,
    @JsonProperty("scramSha512")
    SCRAM_SHA512,
    @JsonProperty("gssapi")
    GSSAPI
}
