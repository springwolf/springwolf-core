// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;

public class SchemaObjectMerger {
    public static SchemaObject merge(SchemaObject initial, SchemaObject... schemas) {
        for (SchemaObject schema : schemas) {
            if (schema == null) {
                continue;
            }

            schema.getProperties().forEach((key, value) -> initial.getProperties().putIfAbsent(key, value));
        }
        return initial;
    }
}
