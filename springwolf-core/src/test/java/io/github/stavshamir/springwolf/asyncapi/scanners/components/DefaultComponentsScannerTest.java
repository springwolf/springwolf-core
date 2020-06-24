package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultComponentsScannerTest {

    private final DefaultComponentsScanner componentsScanner = new DefaultComponentsScanner();

    @Test
    public void getComponents() {
        Set<Class<?>> components = componentsScanner.scanForComponents(this.getClass().getPackage());

        assertThat(components)
                .contains(DefaultComponentsScanner.class)
                .doesNotContain(ComponentsScanner.class);
    }

}