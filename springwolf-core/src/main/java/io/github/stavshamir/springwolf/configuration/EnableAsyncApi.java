package io.github.stavshamir.springwolf.configuration;

import io.github.stavshamir.springwolf.SpringWolfConfigConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Enable the documentation of asynchronous consumer endpoints (methods annotated by @KafkaListener in a class annotated with @{@link Component}).
 * <br>
 * This annotation should be applied to a Spring java config and should have an accompanying '@{@link Configuration}' annotation.
 * @deprecated not necessary any more as springwolf components are loaded via autoconfiguration.
 * @author Stav Shamir
 */
@Retention(value=RUNTIME)
@Target(value=TYPE)
@Deprecated
public @interface EnableAsyncApi { }
