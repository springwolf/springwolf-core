// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.security_scheme;

import io.github.stavshamir.springwolf.asyncapi.v3.ClasspathUtil;
import io.github.stavshamir.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializer;
import io.github.stavshamir.springwolf.asyncapi.v3.model.security_scheme.oauth2.OAuthFlows;
import io.github.stavshamir.springwolf.asyncapi.v3.model.security_scheme.oauth2.flows.ImplicitOAuthFlow;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class SecuritySchemeTest {
    private static final DefaultAsyncApiSerializer serializer = new DefaultAsyncApiSerializer();

    @Test
    void shouldSerializeUserPassword() throws IOException {
        var securityScheme =
                SecurityScheme.builder().type(SecurityType.USER_PASSWORD).build();

        String example = ClasspathUtil.readAsString("/v3/model/security_scheme/userPassword.json");
        assertThatJson(serializer.toJsonString(securityScheme)).isEqualTo(example);
    }

    @Test
    void shouldSerializeApiKey() throws IOException {
        var securityScheme = ApiKeySecurityScheme.apiKeyBuilder()
                .in(ApiKeySecurityScheme.ApiKeyLocation.USER)
                .build();

        String example = ClasspathUtil.readAsString("/v3/model/security_scheme/apiKey.json");
        assertThatJson(serializer.toJsonString(securityScheme)).isEqualTo(example);
    }

    @Test
    void shouldSerializeX509() throws IOException {
        var securityScheme = SecurityScheme.builder().type(SecurityType.X509).build();

        String example = ClasspathUtil.readAsString("/v3/model/security_scheme/x509.json");
        assertThatJson(serializer.toJsonString(securityScheme)).isEqualTo(example);
    }

    @Test
    void shouldSerializeSymmetricEncryption() throws IOException {
        var securityScheme =
                SecurityScheme.builder().type(SecurityType.SYMMETRIC_ENCRYPTION).build();

        String example = ClasspathUtil.readAsString("/v3/model/security_scheme/symmetricEncryption.json");
        assertThatJson(serializer.toJsonString(securityScheme)).isEqualTo(example);
    }

    @Test
    void shouldSerializeHttpBasic() throws IOException {
        var securityScheme = HttpSecurityScheme.httpBuilder().scheme("basic").build();

        String example = ClasspathUtil.readAsString("/v3/model/security_scheme/http-basic.json");
        assertThatJson(serializer.toJsonString(securityScheme)).isEqualTo(example);
    }

    @Test
    void shouldSerializeHttpBearer() throws IOException {
        var securityScheme = HttpSecurityScheme.httpBuilder()
                .scheme("bearer")
                .bearerFormat("JWT")
                .build();

        String example = ClasspathUtil.readAsString("/v3/model/security_scheme/http-bearer.json");
        assertThatJson(serializer.toJsonString(securityScheme)).isEqualTo(example);
    }

    @Test
    void shouldSerializeHttpApiKey() throws IOException {
        var securityScheme = HttpApiKeySecurityScheme.httpApiKeyBuilder()
                .name("api_key")
                .in(HttpApiKeySecurityScheme.HttpApiKeyLocation.HEADER)
                .build();

        String example = ClasspathUtil.readAsString("/v3/model/security_scheme/httpApiKey.json");
        assertThatJson(serializer.toJsonString(securityScheme)).isEqualTo(example);
    }

    @Test
    void shouldSerializeOAuth2() throws IOException {
        var securityScheme = OAuth2SecurityScheme.oAuth2Builder()
                .flows(OAuthFlows.builder()
                        .implicit(ImplicitOAuthFlow.implicitBuilder()
                                .authorizationUrl("https://example.com/api/oauth/dialog")
                                .availableScopes(Map.of(
                                        "write:pets", "modify pets in your account",
                                        "read:pets", "read your pets"))
                                .build())
                        .build())
                .scopes(List.of("write:pets"))
                .build();

        String example = ClasspathUtil.readAsString("/v3/model/security_scheme/oauth2.json");
        assertThatJson(serializer.toJsonString(securityScheme)).isEqualTo(example);
    }

    @Test
    void shouldSerializeScramSha512() throws IOException {
        var securityScheme =
                SecurityScheme.builder().type(SecurityType.SCRAM_SHA512).build();

        String example = ClasspathUtil.readAsString("/v3/model/security_scheme/scramSha512.json");
        assertThatJson(serializer.toJsonString(securityScheme)).isEqualTo(example);
    }
}
