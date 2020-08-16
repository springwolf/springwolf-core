package io.github.stavshamir.springwolf.asyncapi.types.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contact information for the exposed API.
 *
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#contactObject">Contact specification</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    /**
     * The identifying name of the contact person/organization.
     */
    private String name;

    /**
     * The URL pointing to the contact information. MUST be in the format of a URL.
     */
    private String url;

    /**
     * The email address of the contact person/organization. MUST be in the format of an email address.
     */
    private String email;

}
