// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.springwolf.asyncapi.v3.model.ExtendableObject;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The Multi Format Schema Object represents a schema definition. It differs from the Schema Object in that it
 * supports multiple schema formats or languages (e.g., JSON Schema, Avro, etc.).
 *
 * @see <a href="https://www.asyncapi.com/docs/reference/specification/v3.0.0#multiFormatSchemaObject">Multi Format Schema Object</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MultiFormatSchema extends ExtendableObject {
    /**
     * Required. A string containing the name of the schema format that is used to define the information. If
     * schemaFormat is missing, it MUST default to application/vnd.aai.asyncapi+json;version={{asyncapi}} where
     * {{asyncapi}} matches the AsyncAPI Version String. In such a case, this would make the Multi Format Schema Object
     * equivalent to the Schema Object. When using Reference Object within the schema, the schemaFormat of the resource
     * being referenced MUST match the schemaFormat of the schema that contains the initial reference. For example, if
     * you reference Avro schema, then schemaFormat of referencing resource and the resource being reference MUST match.
     * </p>
     * Check out the supported schema formats table for more information. Custom values are allowed but their
     * implementation is OPTIONAL. A custom value MUST NOT refer to one of the schema formats listed in the table.
     * </p>
     * When using Reference Objects within the schema, the schemaFormat of the referenced resource MUST match the
     * schemaFormat of the schema containing the reference.
     */
    @Builder.Default
    @NotNull
    @JsonProperty(value = "schemaFormat")
    private String schemaFormat = SchemaFormat.DEFAULT.toString();

    /**
     * Required. Definition of the message payload. It can be of any type but defaults to Schema Object. It MUST match
     * the schema format defined in schemaFormat, including the encoding type. E.g., Avro should be inlined as either a
     * YAML or JSON object instead of as a string to be parsed as YAML or JSON. Non-JSON-based schemas
     * (e.g., Protobuf or XSD) MUST be inlined as a string.
     */
    @JsonProperty(value = "schema")
    private Object schema;
}
