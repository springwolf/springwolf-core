package io.github.stavshamir.springwolf.configuration;

import com.asyncapi.v2.model.info.Info;
import com.asyncapi.v2.model.server.Server;
import io.github.stavshamir.springwolf.asyncapi.scanners.components.*;
import io.github.stavshamir.springwolf.asyncapi.types.ConsumerData;
import io.github.stavshamir.springwolf.asyncapi.types.ProducerData;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class AsyncApiDocket {

    /**
     * The {@link ComponentsScanner} used for finding the components that contain the asynchronous consumer endpoints.
     *
     * @see ApplicationContextComponentsScanner
     * @see DefaultClassPathComponentsScanner
     * @see ComponentComponentsScanner
     * @see ConfigurationComponentsScanner
     * @see CompositeComponentsScanner
     */
    private ComponentsScanner componentsScanner;

    /**
     * <b>Required.</b>
     * Provide metadata about the API. The metadata can be used by the clients if needed.
     *
     * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#infoObject">Info specification</a>
     */
    @NonNull
    private final Info info;

    /**
     * Provides connection details of servers.
     */
    @Singular
    private final Map<String, Server> servers;

    /**
     * Provides information about the producers.
     */
    @Singular
    private final List<ProducerData> producers;

    @Singular
    private final List<ConsumerData> consumers;

    @SuppressWarnings("unused")
    public static class AsyncApiDocketBuilder {
        @SuppressWarnings("FieldCanBeLocal")
        private ComponentsScanner componentsScanner;

        /**
         * The base package to scan for listeners which are declared inside a class annotated with @Component or @Service.
         */
        public AsyncApiDocketBuilder basePackage(String value) {
            this.componentsScanner = new DefaultClassPathComponentsScanner(value, value);
            return this;
        }
    }
}
