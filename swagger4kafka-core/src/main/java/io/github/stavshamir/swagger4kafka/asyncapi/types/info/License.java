package io.github.stavshamir.swagger4kafka.asyncapi.types.info;

import lombok.*;

/**
 * License information for the exposed API.
 *
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#licenseObject">License specification</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class License {
    /**
     * <b>Required.</b>
     * The license name used for the API.
     */
    @NonNull
    private String name;

    /**
     * A URL to the license used for the API. MUST be in the format of a URL.
     */
    private String url;
}
