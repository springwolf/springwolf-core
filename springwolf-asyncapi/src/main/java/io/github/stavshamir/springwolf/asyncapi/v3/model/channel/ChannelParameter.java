// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.channel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.v3.model.ExtendableObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.Reference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Describes a parameter included in a channel address.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ChannelParameter extends ExtendableObject implements Reference {
    /**
     * An enumeration of string values to be used if the substitution options are from a limited set.
     */
    @JsonProperty("enum")
    private List<String> enumValues;

    /**
     * The default value to use for substitution, and to send, if an alternate value is not supplied.
     */
    @JsonProperty("default")
    private String defaultValue;

    /**
     * An optional description for the parameter. <a href="https://spec.commonmark.org/">CommonMark syntax</a> MAY be
     * used for rich text representation.
     */
    @JsonProperty(value = "description")
    private String description;

    /**
     * An array of examples of the parameter value.
     */
    @JsonProperty(value = "examples")
    private List<String> examples;

    /**
     * A runtime expression that specifies the location of the parameter value.
     */
    @JsonProperty(value = "location")
    private String location;

    @JsonIgnore
    private String ref;

    @Override
    public String getRef() {
        return ref;
    }
}
