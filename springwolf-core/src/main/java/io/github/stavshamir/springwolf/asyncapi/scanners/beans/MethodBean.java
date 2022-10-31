package io.github.stavshamir.springwolf.asyncapi.scanners.beans;

import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Method;

@Data
@Builder
public class MethodBean {

    private final Method method;
    private final String beanName;
//    private final Class<?>

}
