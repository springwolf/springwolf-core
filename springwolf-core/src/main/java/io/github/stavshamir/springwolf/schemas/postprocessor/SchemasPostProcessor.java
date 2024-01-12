// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.postprocessor;

import io.swagger.v3.oas.models.media.Schema;

import java.util.Map;

/**
 * Internal interface to allow post-processing of a new schema (and their definition) after detection.
 * <br/>
 * It is closely coupled with the data structure of the SchemaService.
 */
public interface SchemasPostProcessor {
    void process(Schema schema, Map<String, Schema> definitions);
}
