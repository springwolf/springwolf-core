package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import org.junit.Test;

public class ComponentComponentsScannerTest {

    @Test
    public void scanForComponents() {
        Set<Class<?>> components = new ComponentComponentsScanner(this.getClass().getPackage().getName()).scanForComponents();

        // Gives back both the @Configuration and inner @TestConfiguration classes.
        assertThat(components)
            .hasSize(2)
            .contains(TestBeanConfiguration.class)
            .contains(ApplicationContextComponentsScannerTest.CustomConfiguration.class)
            .doesNotContain(ComponentsScanner.class);
    }

    @Test
    public void scanForComponents_should_require_a_not_empty_package() {
        assertThatThrownBy(() -> new ComponentComponentsScanner(""))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
