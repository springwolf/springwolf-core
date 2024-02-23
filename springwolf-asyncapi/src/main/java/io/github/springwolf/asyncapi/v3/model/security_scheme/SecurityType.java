// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.security_scheme;

public enum SecurityType {
    USER_PASSWORD("userPassword"),
    API_KEY("apiKey"),
    X509("X509"),
    SYMMETRIC_ENCRYPTION("symmetricEncryption"),
    ASYMMETRIC_ENCRYPTION("asymmetricEncryption"),
    HTTP_API_KEY("httpApiKey"),
    HTTP("http"),
    OAUTH2("oauth2"),
    OPEN_ID_CONNECT("openIdConnect"),
    PLAIN("plain"),
    SCRAM_SHA256("scramSha256"),
    SCRAM_SHA512("scramSha512"),
    GSSAPI("gssapi");

    public final String type;

    SecurityType(String type) {
        this.type = type;
    }

    public static SecurityType fromString(String type) {
        return valueOf(type.toUpperCase());
    }

    @Override
    public String toString() {
        return this.type;
    }
}
