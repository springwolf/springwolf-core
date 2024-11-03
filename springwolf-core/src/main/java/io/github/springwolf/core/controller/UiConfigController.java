// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.controller;

import io.github.springwolf.core.asyncapi.grouping.AsyncApiGroupService;
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

    @GetMapping(
            path = {"${springwolf.path.base:/springwolf}/ui-config"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UiConfig getUiConfig() {
        return new UiConfig(asyncApiGroupService
                .getAsyncApiGroups()
                .map(el -> new UiConfig.UiConfigGroup(el.getGroupName()))
                .toList());
    }

    private record UiConfig(List<UiConfigGroup> groups) {
        private record UiConfigGroup(String name) {}
    }
}
