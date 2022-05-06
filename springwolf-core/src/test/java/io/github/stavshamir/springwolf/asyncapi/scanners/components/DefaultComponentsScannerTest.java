package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DefaultComponentsScannerTest {

    private final DefaultComponentsScanner componentsScanner = new DefaultComponentsScanner();

    @Test
    public void getComponents() {
        Set<Class<?>> components = componentsScanner.scanForComponents(this.getClass().getPackage().getName());

        assertThat(components)
                .hasSize(1)
                .contains(DefaultComponentsScanner.class)
                .doesNotContain(ComponentsScanner.class);
    }

    @Test
    public void getComponents_should_find_inside_configuration() {
        String packageName = this.getClass().getPackage().getName();
        Set<Class<?>> components = componentsScanner.scanForComponents(packageName, packageName);

        assertThat(components)
                .hasSize(2)
                .contains(DefaultComponentsScanner.class)
                .contains(TestBean.class)
                .doesNotContain(ComponentsScanner.class);
    }

    @Test
    public void getComponents_should_find_inside_configuration_without_base_package() {
        String packageName = this.getClass().getPackage().getName();
        Set<Class<?>> components = componentsScanner.scanForComponents(null, packageName);

        assertThat(components)
                .hasSize(1)
                .contains(TestBean.class);
    }

    @Test
    public void getComponents_should_filter_configuration_beans_on_base_package() {
        String packageName = this.getClass().getPackage().getName();
        Set<Class<?>> components = componentsScanner.scanForComponents("other.package", packageName);

        assertThat(components)
                .isEmpty();
    }

    @Test
    public void getComponents_should_require_at_least_one_base_package() {
        assertThatThrownBy(() -> componentsScanner.scanForComponents("", ""))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
