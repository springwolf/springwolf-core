package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CompositeComponentsScanner implements ComponentsScanner {

  private final ComponentsScanner[] componentsScanners;

  public CompositeComponentsScanner(ComponentsScanner... componentsScanners) {

    List<ComponentsScanner> scannerList = new ArrayList<>();
    for (ComponentsScanner scanner : componentsScanners) {
      if (scanner != null) {
        scannerList.add(scanner);
      }
    }

    if (scannerList.isEmpty()) {
      throw new IllegalArgumentException("There must be at least one valid/non-null beans scanner given to the composite scanner");
    }

    this.componentsScanners = scannerList.toArray(new ComponentsScanner[0]);
  }

  @Override
  public Set<Class<?>> scanForComponents() {

    final Set<Class<?>> components = new LinkedHashSet<>();
    for (ComponentsScanner componentsScanner : this.componentsScanners) {
      components.addAll(componentsScanner.scanForComponents());
    }

    return components;
  }
}
