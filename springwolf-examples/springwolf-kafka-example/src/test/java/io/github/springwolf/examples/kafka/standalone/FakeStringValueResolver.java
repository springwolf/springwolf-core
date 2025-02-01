// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.standalone;

import jakarta.annotation.Nullable;
import org.springframework.core.env.Environment;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.StringValueResolver;

class FakeStringValueResolver implements StringValueResolver {
    private final PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");
    private final Environment environment;

    public FakeStringValueResolver(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String resolveStringValue(@Nullable String strVal) {
        if (strVal == null) return null;

        final String strValReplaced = helper.replacePlaceholders(strVal, environment::getProperty);

        final String resolved;
        if (strVal.contains("#{")) {
            String spel = strValReplaced.replace("#{", "").replace("}", "");

            StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
            evaluationContext.setRootObject(environment);

            ExpressionParser parser = new SpelExpressionParser();
            resolved = parser.parseExpression(spel).getValue(evaluationContext, String.class);
        } else {
            resolved = strValReplaced;
        }
        return resolved;
    }
}
