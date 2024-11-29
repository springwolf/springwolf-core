// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration.docket;

import io.github.springwolf.asyncapi.v3.model.info.Contact;
import io.github.springwolf.asyncapi.v3.model.info.Info;
import io.github.springwolf.asyncapi.v3.model.info.License;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;

import java.util.HashMap;
import java.util.Map;

public class AsyncApiInfoMapper {
    public static Info mapInfo(SpringwolfConfigProperties.ConfigDocket.Info configDocketInfo) {
        Info asyncapiInfo = Info.builder()
                .version(configDocketInfo.getVersion())
                .title(configDocketInfo.getTitle())
                .description(configDocketInfo.getDescription())
                .termsOfService(configDocketInfo.getTermsOfService())
                .contact(configDocketInfo.getContact())
                .license(configDocketInfo.getLicense())
                .build();

        // copy extension fields
        if (configDocketInfo.getExtensionFields() != null) {
            Map<String, Object> extFieldsMap = Map.copyOf(configDocketInfo.getExtensionFields());
            asyncapiInfo.setExtensionFields(extFieldsMap);
        }
        return asyncapiInfo;
    }

    public static Info mergeInfo(Info original, Info updates) {
        Info info = Info.builder()
                .title(updates.getTitle() != null ? updates.getTitle() : original.getTitle())
                .version(updates.getVersion() != null ? updates.getVersion() : original.getVersion())
                .description(updates.getDescription() != null ? updates.getDescription() : original.getDescription())
                .termsOfService(
                        updates.getTermsOfService() != null
                                ? updates.getTermsOfService()
                                : original.getTermsOfService())
                .contact(mergeContact(original.getContact(), updates.getContact()))
                .license(mergeLicense(original.getLicense(), updates.getLicense()))
                .tags(updates.getTags() != null ? updates.getTags() : original.getTags())
                .build();

        // copy extension fields
        Map<String, Object> extFieldsMap = new HashMap<>();
        if (original.getExtensionFields() != null) {
            extFieldsMap.putAll(original.getExtensionFields());
        }
        if (updates.getExtensionFields() != null) {
            extFieldsMap.putAll(updates.getExtensionFields());
        }
        if (!extFieldsMap.isEmpty()) {
            info.setExtensionFields(extFieldsMap);
        }

        return info;
    }

    private static Contact mergeContact(Contact original, Contact updates) {
        if (updates == null) {
            return original;
        }

        return Contact.builder()
                .name(updates.getName() != null ? updates.getName() : original.getName())
                .url(updates.getUrl() != null ? updates.getUrl() : original.getUrl())
                .email(updates.getEmail() != null ? updates.getEmail() : original.getEmail())
                .build();
    }

    private static License mergeLicense(License original, License updates) {
        if (updates == null) {
            return original;
        }

        return License.builder()
                .name(updates.getName() != null ? updates.getName() : original.getName())
                .url(updates.getUrl() != null ? updates.getUrl() : original.getUrl())
                .build();
    }
}
