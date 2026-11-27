// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.web.configuration;

import io.github.springwolf.web.advice.SpringwolfWebResponseAdvice;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class SpringwolfWebResponseAdviceAutoConfiguration {

    @Bean
    public SpringwolfWebResponseAdvice springwolfWebResponseAdvice() {
        return new SpringwolfWebResponseAdvice();
    }
}
