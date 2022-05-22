package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ManualComponentsScannerTest {

    @Test
    public void scanForComponents_should_give_back_given_classes() {

        ComponentsScanner scanner = new ManualComponentsScanner(
            TestBean.class
        );

        assertThat(scanner.scanForComponents())
            .hasSize(1)
            .contains(TestBean.class);
    }
}
