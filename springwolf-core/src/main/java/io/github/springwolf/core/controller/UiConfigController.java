// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.controller;

import io.github.springwolf.core.asyncapi.grouping.AsyncApiGroupService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UiConfigController {

    private final AsyncApiGroupService asyncApiGroupService;
    private final SpringwolfConfigProperties configProperties;

    @GetMapping(
            path = {"${springwolf.path.base:/springwolf}/ui-config"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UiConfig getUiConfig() {

        final UiConfig.InitialConfig initial = createInitialConfig();
        final List<UiConfig.UiConfigGroup> groups = createGroups();

        return new UiConfig(initial, groups);
    }

    private List<UiConfig.UiConfigGroup> createGroups() {
        return asyncApiGroupService
                .getAsyncApiGroups()
                .map(el -> new UiConfig.UiConfigGroup(el.getGroupName()))
                .toList();
    }

    private UiConfig.InitialConfig createInitialConfig() {
        SpringwolfConfigProperties.UI.Defaults defaults =
                configProperties.getUi().getDefaults();
        return new UiConfig.InitialConfig(defaults.isShowBindings(), defaults.isShowHeaders());
    }

    private record UiConfig(InitialConfig initialConfig, List<UiConfigGroup> groups) {
        private record InitialConfig(boolean showBindings, boolean showHeaders) {}

        private record UiConfigGroup(String name) {}
    }
}
