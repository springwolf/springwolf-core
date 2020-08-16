package io.github.stavshamir.springwolf.asyncapi.types.info;

import lombok.*;

/**
 * The object provides metadata about the API. The metadata can be used by the clients if needed.
 *
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#infoObject">Info specification</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Info {

    /**
     * <b>Required.</b>
     * The title of the application.
     */
    @NonNull
    private String title;

    /**
     * <b>Required.</b>
     * Provides the version of the application API (not to be confused with the specification version).
     */
    @NonNull
    private String version;

    /**
     * A short description of the application. CommonMark syntax can be used for rich text representation.
     */
    private String description;

    /**
     * A URL to the Terms of Service for the API. MUST be in the format of a URL.
     */
    private String termsOfService;

    /**
     * The contact information for the exposed API.
     */
    private Contact contact;

    /**
     * The license information for the exposed API.
     */
    private License license;

}
