package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class CompositeComponentsScannerTest {

  @Test
  public void scanForComponents_should_give_multiple_results_in_order() {

    ComponentsScanner scanner = new CompositeComponentsScanner(
        new ManualComponentsScanner(
            TestBean.class
        ),
        new ManualComponentsScanner(
            TestBeanConfiguration.class
        )
    );

    assertThat(scanner.scanForComponents())
        .hasSize(2)
        .containsExactly(TestBean.class, TestBeanConfiguration.class);
  }
}
