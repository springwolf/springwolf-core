// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas;

import io.swagger.v3.core.jackson.TypeNameResolver;

/**
 * Subclass of {@link TypeNameResolver} with a public constructor to
 * create new instances of TypeNameResolvers.
 *
 * Avoids interference with other libraries usages of the TypeNameResolver
 */
public class SpringwolfTypeNameResolver extends TypeNameResolver {
    public SpringwolfTypeNameResolver(boolean isUseFqn) {
        super();

        setUseFqn(isUseFqn);
    }
}
