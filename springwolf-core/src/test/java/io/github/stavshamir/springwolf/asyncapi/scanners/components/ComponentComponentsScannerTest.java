package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import org.junit.Test;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ComponentComponentsScannerTest {

    @Test
    public void scanForComponents() {
        Set<Class<?>> components = new ComponentComponentsScanner(this.getClass().getPackage().getName()).scanForComponents();

        // Gives back both the @Configuration and inner @TestConfiguration classes.
        assertThat(components)
                .hasSize(2)
                .contains(TestBeanConfiguration.class)
                .contains(CustomConfiguration.class)
                .doesNotContain(ComponentsScanner.class);
    }

    @Test
    public void scanForComponents_should_require_a_not_empty_package() {
        assertThatThrownBy(() -> new ComponentComponentsScanner(""))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void scan_components_default_environment() {
        Set<Class<?>> components = new ComponentComponentsScanner(this.getClass().getPackage().getName()).scanForComponents();

        // Gives back both the @Configuration and inner @TestConfiguration classes.
        assertThat(components)
                .hasSize(2)
                .contains(TestBeanConfiguration.class)
                .contains(CustomConfiguration.class)
                .doesNotContain(ComponentsScanner.class);
    }

    @Test
    public void scan_components_test_environment() {
        //ComponentComponentsScanner should provide a way to initialize profile/env
        Set<Class<?>> components = new ComponentComponentsScanner(this.getClass().getPackage().getName()).scanForComponents();

        // Gives back both the @Configuration and inner @TestConfiguration classes.
        assertThat(components)
                .hasSize(3)
                .contains(TestBeanConfiguration.class)
                .contains(CustomConfiguration.class)
                .contains(TestBeanConditional.class)
                .doesNotContain(ComponentsScanner.class);
    }


}
