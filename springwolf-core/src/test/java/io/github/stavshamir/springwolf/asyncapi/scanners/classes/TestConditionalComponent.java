package io.github.stavshamir.springwolf.asyncapi.scanners.classes;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty("include-conditional-component")
public class TestConditionalComponent {}
