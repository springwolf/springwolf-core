// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.springwolf.asyncapi.v3.model.ExtendableObject;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * License information for the exposed API.
 *
 * @see <a href="https://www.asyncapi.com/docs/reference/specification/v3.1.0#licenseObject">License</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class License extends ExtendableObject {

    /**
     * Required. The license name used for the API.
     */
    @NotNull
    @JsonProperty(value = "name")
    private String name;

    /**
     * A URL to the license used for the API. MUST be in the format of a URL.
     */
    @JsonProperty(value = "url")
    private String url;
}
