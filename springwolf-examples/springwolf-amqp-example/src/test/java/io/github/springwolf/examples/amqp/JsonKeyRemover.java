// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp;

import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonNode;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JsonKeyRemover {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static JsonNode removeKeys(String json, List<String> path) throws IOException {
        JsonNode root = mapper.readTree(json);

        JsonNode node = traverseTo(path, root);
        if (node.isObject()) {
            ((ObjectNode) node).removeAll();
        }

        return root;
    }

    private static JsonNode traverseTo(List<String> path, JsonNode root) {
        JsonNode node = root;

        List<String> pathCopy = new LinkedList<>(path);
        while (!pathCopy.isEmpty()) {
            String key = pathCopy.remove(0);
            node = node.path(key);
        }
        return node;
    }
}
