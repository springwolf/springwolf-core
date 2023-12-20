// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.model.operation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.ExtendableObject;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.Reference;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * An object that specifies where an operation has to send the reply.
 * </p>
 * For specifying and computing the location of a reply address, a runtime expression is used.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OperationReplyAddress extends ExtendableObject implements Reference {

    /**
     * An optional description of the address. <a href="https://spec.commonmark.org/"> can be used for rich text
     * representation.
     */
    @JsonProperty("description")
    private String description;

    /**
     * REQUIRED. A runtime expression that specifies the location of the reply address.
     */
    @NotNull
    @JsonProperty("location")
    private String location;

    @JsonIgnore
    private String ref;

    @Override
    public String getRef() {
        return ref;
    }
}
