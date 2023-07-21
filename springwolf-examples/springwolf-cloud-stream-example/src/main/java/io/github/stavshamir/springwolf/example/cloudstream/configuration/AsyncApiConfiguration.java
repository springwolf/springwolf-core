package io.github.stavshamir.springwolf.example.cloudstream.configuration;

import com.asyncapi.v2._6_0.model.info.Contact;
import com.asyncapi.v2._6_0.model.info.Info;
import com.asyncapi.v2._6_0.model.info.License;
import com.asyncapi.v2._6_0.model.server.Server;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncListener;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncPublisher;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AsyncApiConfiguration {

    private final String BOOTSTRAP_SERVERS;

    public AsyncApiConfiguration(@Value("${spring.kafka.bootstrap-servers}") String bootstrapServers) {
        this.BOOTSTRAP_SERVERS = bootstrapServers;
    }

    /**
     * This bean is only required if full control on the {@link AsyncApiDocket} is needed
     * <p>
     * By default, Springwolf uses the {@see Info} provided in the application.properties
     * Consumers are detected when the @KafkaListener or {@link AsyncListener} annotation is used
     * Producers are detected when the springwolf {@link AsyncPublisher} annotation is used
     */
    @Bean
    @ConditionalOnProperty(value = "customAsyncApiDocketBean", havingValue = "true", matchIfMissing = true)
    public AsyncApiDocket asyncApiDocket() {
        Info info = Info.builder()
                .version("1.0.0")
                .title("Springwolf example project - CloudStream")
                .contact(Contact.builder()
                        .name("springwolf")
                        .url("https://github.com/springwolf/springwolf-core")
                        .email("example@example.com")
                        .build())
                .description("Springwolf example project")
                .license(License.builder().name("Apache License 2.0").build())
                .build();

        return AsyncApiDocket.builder()
                .basePackage("io.github.stavshamir.springwolf.example")
                .info(info)
                .server(
                        "kafka",
                        Server.builder()
                                .protocol("kafka")
                                .url(BOOTSTRAP_SERVERS)
                                .build())
                .build();
    }
}
