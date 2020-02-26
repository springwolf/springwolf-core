package io.github.stavshamir.swagger4kafka.integration.configuration

import io.github.stavshamir.swagger4kafka.configuration.Docket
import io.github.stavshamir.swagger4kafka.configuration.EnableSwagger4Kafka
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
@EnableSwagger4Kafka
class Swagger4KafkaConfiguration(@param:Value("\${kafka.bootstrap.servers}") private val BOOTSTRAP_SERVERS: String) {

    @Bean
    fun docket(): Docket = Docket.builder()
            .serviceName("swagger4kafka Example Project")
            .basePackage("io.github.stavshamir.swagger4kafka.integration.consumers")
            .producerConfiguration(producerConfiguration())
            .build()

    private fun producerConfiguration() = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to BOOTSTRAP_SERVERS,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java,
            JsonSerializer.ADD_TYPE_INFO_HEADERS to false
    )

}