// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas;

import io.swagger.v3.core.jackson.TypeNameResolver;

/**
 * Subklass of {@link TypeNameResolver} with a public constructor to
 * create new instances of TypeNameResolvers.
 */
public class SpringWolfTypeNameResolver extends TypeNameResolver {

    public SpringWolfTypeNameResolver() {
        super();
    }
}
