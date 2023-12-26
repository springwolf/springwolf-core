// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.security_scheme.oauth2.flows;

import io.github.stavshamir.springwolf.asyncapi.v3.ClasspathUtil;
import io.github.stavshamir.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class OAuthFlowTest {
    private static final DefaultAsyncApiSerializer serializer = new DefaultAsyncApiSerializer();

    @Test
    void shouldSerializeAuthorizationCodeOAuthFlow() throws IOException {
        var oauthFlow = AuthorizationCodeOAuthFlow.authorizationCodeBuilder()
                .authorizationUrl("https://example.com/api/oauth/dialog")
                .tokenUrl("https://example.com/api/oauth/token")
                .availableScopes(Map.of(
                        "write:pets", "modify pets in your account",
                        "read:pets", "read your pets"))
                .build();
        String example = ClasspathUtil.readAsString("/v3/model/security_scheme/flows/oauth-flow.json");
        assertThatJson(serializer.toJsonString(oauthFlow)).isEqualTo(example);
    }
}
