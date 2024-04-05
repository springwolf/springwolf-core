// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.generic_binding.annotation.processor;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class PropertiesUtil {

    private static final Pattern ARRAY_PATTERN = Pattern.compile("^\\[([^]]+)]$");

    private PropertiesUtil() {}

    public static Map<String, Object> toMap(String[] propertyStrings) {
        return convertPropertiesToNestedMap(buildPropertiesFrom((propertyStrings)));
    }

    private static Properties buildPropertiesFrom(String[] propertyStrings) {
        Properties properties = new Properties();
        for (String bindingProperty : propertyStrings) {
            try {
                properties.load(new StringReader(bindingProperty));
            } catch (IOException e) {
                log.warn("Unable to parse property {}", bindingProperty, e);
            }
        }
        return properties;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> convertPropertiesToNestedMap(Properties properties) {
        Map<String, Object> bindingData = new HashMap<>();
        for (String propertyName : properties.stringPropertyNames()) {
            LinkedList<String> path = new LinkedList<>(Arrays.asList(propertyName.split("\\.")));

            Map<String, Object> mapNode = bindingData;
            while (path.size() > 1) {
                String pathElement = path.get(0);
                mapNode.computeIfAbsent(pathElement, k -> new HashMap<>());
                mapNode = (Map<String, Object>) mapNode.get(pathElement);

                path.pop();
            }

            var value = parseValue(properties.get(propertyName));
            mapNode.put(path.get(0), value);
        }
        return bindingData;
    }

    private static Object parseValue(Object input) {
        if (input instanceof String inputString) {
            Matcher matcher = ARRAY_PATTERN.matcher(inputString);

            // Check if the pattern matches the input string
            if (matcher.find()) {
                // Extract the key and values
                String[] values = matcher.group(1).split(",");
                return Arrays.stream(values).map(String::trim).toList();
            }
        }

        return input;
    }
}
