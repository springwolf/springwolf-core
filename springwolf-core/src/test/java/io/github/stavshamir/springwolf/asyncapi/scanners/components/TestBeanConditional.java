package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import lombok.Value;
import org.springframework.context.annotation.Profile;

@Value
@Profile("test-profile")
public class TestBeanConditional {

    String value;
}
