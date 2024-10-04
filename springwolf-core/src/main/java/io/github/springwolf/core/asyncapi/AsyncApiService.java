// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi;

import io.github.springwolf.asyncapi.v3.model.AsyncAPI;

import java.util.Optional;

public interface AsyncApiService {

    AsyncAPI getAsyncAPI();

     // TODO where do we want do add the breaking change?
     default Optional<AsyncAPI> getForGroupName(String groupName) {
         return Optional.ofNullable(getAsyncAPI());
     }
}
