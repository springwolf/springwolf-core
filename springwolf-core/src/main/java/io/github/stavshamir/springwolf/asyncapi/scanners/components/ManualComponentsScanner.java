package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import com.google.common.collect.Sets;
import java.util.Set;

public class ManualComponentsScanner implements ComponentsScanner {

    private final Set<Class<?>> components;

    public ManualComponentsScanner(Set<Class<?>> components) {
        this.components = components;
    }

    public ManualComponentsScanner(Class<?>... components) {
        this(Sets.newHashSet(components));
    }

    @Override
    public Set<Class<?>> scanForComponents() {
        return this.components;
    }
}
