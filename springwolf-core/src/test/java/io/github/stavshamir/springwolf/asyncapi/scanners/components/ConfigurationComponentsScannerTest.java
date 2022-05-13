package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import org.junit.Test;

public class ConfigurationComponentsScannerTest {

  @Test
  public void scanForComponents_should_find_inside_configuration() {
    String packageName = this.getClass().getPackage().getName();
    ComponentsScanner scanner = new ConfigurationComponentsScanner(packageName);
    Set<Class<?>> components = scanner.scanForComponents();

    assertThat(components)
        .hasSize(1)
        .contains(TestBean.class);
  }

  @Test
  public void scanForComponents_should_require_a_not_empty_package() {
    assertThatThrownBy(() -> new ConfigurationComponentsScanner(""))
        .isExactlyInstanceOf(IllegalArgumentException.class);
  }
}
