// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration;

/**
 * Something gone wrong due to the configuration, which prevents Springwolf from starting up correctly.
 * Its cause can most likely be fixed by changing the configuration
 */
public class SpringwolfConfigurationException extends IllegalArgumentException {
    public SpringwolfConfigurationException(String message) {
        super(message);
    }
}
