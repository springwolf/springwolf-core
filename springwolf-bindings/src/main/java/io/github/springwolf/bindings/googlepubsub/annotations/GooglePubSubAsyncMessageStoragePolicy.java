// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.googlepubsub.annotations;

import java.lang.annotation.Inherited;

@Inherited
public @interface GooglePubSubAsyncMessageStoragePolicy {
    String[] allowedPersistenceRegions() default {};
}
