// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.addons.genericbinding.annotation.processor;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class PropertiesUtil {

    public static Map<String, Object> toMap(String[] propertyStrings) {
        return convertPropertiesToNestedMap(buildPropertiesFrom((propertyStrings)));
    }

    private static Properties buildPropertiesFrom(String[] propertyStrings) {
        Properties properties = new Properties();
        for (String bindingProperty : propertyStrings) {
            try {
                properties.load(new StringReader(bindingProperty));
            } catch (IOException e) {
                log.warn("Unable to parse property %s".formatted(bindingProperty), e);
            }
        }
        return properties;
    }

    private static Map<String, Object> convertPropertiesToNestedMap(Properties properties) {
        Map<String, Object> bindingData = new HashMap<>();
        for (String propertyName : properties.stringPropertyNames()) {
            LinkedList<String> path = new LinkedList<>(Arrays.asList(propertyName.split("\\.")));

            Map<String, Object> mapNode = bindingData;
            while (path.size() > 1) {
                String pathElement = path.get(0);
                if (!mapNode.containsKey(pathElement)) {
                    mapNode.put(pathElement, new HashMap<>());
                }

                mapNode = (Map<String, Object>) mapNode.get(pathElement);

                path.pop();
            }

            mapNode.put(path.get(0), properties.get(propertyName));
        }
        return bindingData;
    }
}
