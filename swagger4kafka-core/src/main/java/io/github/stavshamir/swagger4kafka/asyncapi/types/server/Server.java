package io.github.stavshamir.swagger4kafka.asyncapi.types.server;

import lombok.*;

/**
 * An object representing a message broker, a server or any other kind of computer program capable of sending and/or receiving data.
 * This object is used to capture details such as URIs, protocols and security configuration.
 *
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#serverObject">Server specification</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Server {

    /**
     * <b>REQUIRED.</b>
     * A URL to the target host. This URL MAY be relative, to indicate that the host location is relative to the
     * location where the AsyncAPI document is being served.
     */
    @NonNull
    private String url;

    /**
     * <b>REQUIRED.</b>
     * The protocol this URL supports for connection. Supported protocol include, but are not limited to:
     * amqp, amqps, http, https, jms, kafka, kafka-secure, mqtt, secure-mqtt, stomp, stomps, ws, wss.
     */
    @NonNull
    private String protocol;

    /**
     * The version of the protocol used for connection. For instance: AMQP 0.9.1, HTTP 2.0, Kafka 1.0.0, etc.
     */
    private String protocolVersion;

    /**
     * An optional string describing the host designated by the URL. CommonMark syntax MAY be used for rich text
     * representation.
     */
    private String description;

    /**
     * @return A ServerBuilder with protocol set to "kafka".
     */
    public static ServerBuilder kafka() {
        return Server.builder().protocol("kafka");
    }

}
