// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.stavshamir.springwolf.asyncapi.v3.model.ExtendableObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Contact information for the exposed API.
 *
 * @see <a href="https://www.asyncapi.com/docs/reference/specification/v3.0.0#contactObject">Contact</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Contact extends ExtendableObject {

    /**
     * The identifying name of the contact person/organization.
     */
    @JsonProperty(value = "name")
    private String name;

    /**
     * The URL pointing to the contact information. MUST be in the format of a URL.
     */
    @JsonProperty(value = "url")
    private String url;

    /**
     * The email address of the contact person/organization. MUST be in the format of an email address.
     */
    @JsonProperty(value = "email")
    private String email;
}
