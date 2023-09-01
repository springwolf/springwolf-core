package io.github.stavshamir.springwolf.asyncapi.scanners.classes;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(TestConditionalComponent.CONDITIONAL_PROPERTY)
public class TestConditionalComponent {
    public static final String CONDITIONAL_PROPERTY = "include-conditional-component";
}
