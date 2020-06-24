package io.github.stavshamir.swagger4kafka.configuration.protocol;

import com.google.common.collect.ImmutableSet;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
public class Protocols {

    private final Set<? extends AsyncApiProtocolConfiguration> all;
    private final KafkaProtocolConfiguration kafka;

    @Builder
    Protocols(KafkaProtocolConfiguration kafka) {
        this.kafka = kafka;
        this.all = ImmutableSet.of(kafka);
    }

}
