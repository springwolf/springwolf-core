// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;

/**
 * Encapsulates the resolved name for the contained schema.
 * @param name The fully qualified name or the simple name of the schema.
 * @param schema The SchemaObject.
 */
public record NamedSchemaObject(String name, SchemaObject schema) {}
