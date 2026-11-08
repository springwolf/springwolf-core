// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi;

import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.core.configuration.SpringwolfConfigurationException;
import io.github.springwolf.core.grouping.AsyncApiGroupService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;

import java.util.Map;

@RequiredArgsConstructor
public class DefaultAsyncApiService implements AsyncApiService {
    private final AsyncApiCreator asyncApiCreator;
    private final AsyncApiGroupService groupService;

    private volatile AsyncAPIResult asyncAPIResult = null;

    @Override
    public AsyncAPI getAsyncAPI() {
        return getAsyncAPIResult().asyncAPI;
    }

    @Override
    public AsyncAPI getForGroupName(String groupName) {
        Map<String, AsyncAPI> groups = getAsyncAPIResult().groupedApi;

        if (groups != null && groups.containsKey(groupName)) {
            return groups.get(groupName);
        }
        throw new SpringwolfConfigurationException("AsyncAPI group %s was not found".formatted(groupName));
    }

    private AsyncAPIResult getAsyncAPIResult() {
        if (isNotInitialized()) {
            initAsyncAPI();
        }

        if (asyncAPIResult == null || asyncAPIResult.exception != null) {
            throw new AsyncApiCreationException(asyncAPIResult.exception);
        } else {
            return asyncAPIResult;
        }
    }

    /**
     * Does the 'heavy work' of building the AsyncAPI documents once. Stores the resulting
     * AsyncAPI document or alternatively a caught exception/error in the instance variable asyncAPIResult.
     */
    protected synchronized void initAsyncAPI() {
        if (this.asyncAPIResult != null) {
            return; // Double Check Idiom
        }

        try {
            AsyncAPI asyncAPI = asyncApiCreator.createAsyncApi();

            Map<String, AsyncAPI> groupedApi = groupService.group(asyncAPI);
            this.asyncAPIResult = new AsyncAPIResult(asyncAPI, groupedApi, null);
        } catch (Throwable t) {
            this.asyncAPIResult = new AsyncAPIResult(null, null, t);
        }
    }

    /**
     * checks whether asyncApi has internally already been initialized or not.
     *
     * @return true if asyncApi has not already been created and initialized.
     */
    public boolean isNotInitialized() {
        return this.asyncAPIResult == null;
    }

    /**
     * Record holding the result of AsyncAPI creation.
     */
    private record AsyncAPIResult(
            @Nullable AsyncAPI asyncAPI, @Nullable Map<String, AsyncAPI> groupedApi, Throwable exception) {}
}
