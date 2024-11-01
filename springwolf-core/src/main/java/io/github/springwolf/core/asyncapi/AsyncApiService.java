// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi;

import io.github.springwolf.asyncapi.v3.model.AsyncAPI;

import java.util.Optional;

public interface AsyncApiService {

    AsyncAPI getAsyncAPI();

    /**
     * Default implementation was added to avoid breaking (compiler) change.
     *
     * Maintainer note: remove default implementation
     */
    default Optional<AsyncAPI> getForGroupName(String groupName) {
        return Optional.ofNullable(getAsyncAPI());
    }
}
