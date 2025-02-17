// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone.bean;

import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.StringValueResolver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: probably not needed anymore
 */
@Slf4j
public class StandaloneStringValueResolver implements StringValueResolver {
    private static final PropertyPlaceholderHelper propertyHelper = new PropertyPlaceholderHelper("${", "}");
    private static final Pattern spelPattern = Pattern.compile("#\\{(.+)}");
    private static final ExpressionParser parser = new SpelExpressionParser();
    private final Environment environment;
    private final StandardEvaluationContext evaluationContext;

    public StandaloneStringValueResolver(Environment environment) {
        this.environment = environment;

        this.evaluationContext = new StandardEvaluationContext();
        this.evaluationContext.setRootObject(environment);
    }

    @Override
    public String resolveStringValue(@Nullable String strVal) {
        if (strVal == null) return null;

        String resolvedPlaceholders = propertyHelper.replacePlaceholders(strVal, environment::getProperty);

        Matcher spelMatcher = spelPattern.matcher(resolvedPlaceholders);
        if (spelMatcher.find()) {
            String expression = spelMatcher.group(1);
            String resolvedExpression = parser.parseExpression(expression).getValue(evaluationContext, String.class);
            if (resolvedExpression != null) {
                return spelMatcher.replaceFirst(resolvedExpression);
            } else {
                log.info("Failed to resolve expression ({}) in: {}", expression, strVal);
            }
        }

        return resolvedPlaceholders;
    }
}
