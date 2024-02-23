// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtendableObject {

    private static final Pattern extensionPropertyNamePattern = Pattern.compile("^x-[\\w\\d\\-\\_]+$");

    /**
     * Extension fields in the form x-extension-field-name for the exposed API.
     */
    @JsonAnyGetter
    @JsonIgnore
    protected Map<String, Object> extensionFields;

    @JsonAnySetter
    protected final void readExtensionProperty(String name, Object value) {
        if (extensionPropertyNamePattern.matcher(name).matches()) {
            if (extensionFields == null) {
                extensionFields = new HashMap<>();
            }

            extensionFields.put(name, value);
        } else {
            throw new IllegalArgumentException(String.format("\"%s\" is not valid extension property", name));
        }
    }
}
