// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.schema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.v3.model.ExtendableObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.ExternalDocumentation;
import io.github.stavshamir.springwolf.asyncapi.v3.model.Reference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * The Schema Object allows the definition of input and output data types. These types can be objects, but also
 * primitives and arrays. This object is a superset of the JSON Schema Specification Draft 07. The empty schema
 * (which allows any instance to validate) MAY be represented by the boolean value true and a schema which allows no
 * instance to validate MAY be represented by the boolean value false.
 * </p>
 * Further information about the properties can be found in JSON Schema Core and JSON Schema Validation. Unless stated
 * otherwise, the property definitions follow the JSON Schema specification as referenced here.
 *
 * @see <a href="https://www.asyncapi.com/docs/reference/specification/v3.0.0#schemaObject">Schema Object</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Schema extends ExtendableObject implements Reference {
    /**
     * Adds support for polymorphism. The discriminator is the schema property name that is used to differentiate
     * between other schema that inherit this schema. The property name used MUST be defined at this schema and it
     * MUST be in the required property list. When used, the value MUST be the name of this schema or any schema that
     * inherits it. See Composition and Inheritance for more details.
     */
    @JsonProperty(value = "discriminator")
    private String discriminator;

    /**
     * Additional external documentation for this schema.
     */
    @JsonProperty(value = "externalDocs")
    private ExternalDocumentation externalDocs;

    /**
     * Specifies that a schema is deprecated and SHOULD be transitioned out of usage. Default value is false.
     */
    @JsonProperty(value = "deprecated")
    private Boolean deprecated;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "properties")
    private Map<String, Schema> properties;

    /**
     * <a href="https://spec.commonmark.org/">CommonMark syntax</a> can be used for rich text representation.
     */
    @JsonProperty(value = "description")
    private String description;

    /**
     * See Data Type Formats for further details. While relying on JSON Schema's defined formats, the AsyncAPI
     * Specification offers a few additional predefined formats.
     */
    @JsonProperty(value = "format")
    private String format;

    @JsonProperty(value = "maximum")
    private Integer maximum;

    @JsonProperty(value = "minimum")
    private Integer minimum;

    @JsonProperty("enum")
    private List<String> enumValues;

    @JsonIgnore
    private String ref;

    @Override
    public String getRef() {
        return ref;
    }
}
