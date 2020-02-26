package io.github.stavshamir.swagger4kafka.integration.configuration

import io.github.stavshamir.swagger4kafka.integration.dtos.ExamplePayloadDto
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
@EnableKafka
class KafkaConsumerConfiguration(@param:Value("\${kafka.bootstrap.servers}") private val BOOTSTRAP_SERVERS: String) {
    @Bean
    fun consumerConfiguration() = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to BOOTSTRAP_SERVERS,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to JsonDeserializer::class.java,
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest",
            ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to true,
            ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG to "100",
            ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG to "15000",
            ConsumerConfig.GROUP_ID_CONFIG to "example-group-id"
    )

    @Bean
    fun exampleKafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, ExamplePayloadDto> {
        val containerFactory = ConcurrentKafkaListenerContainerFactory<String, ExamplePayloadDto>()
        val consumerFactory = DefaultKafkaConsumerFactory<String, ExamplePayloadDto>(consumerConfiguration(), StringDeserializer(), JsonDeserializer<ExamplePayloadDto>(ExamplePayloadDto::class.java))
        containerFactory.consumerFactory = consumerFactory
        return containerFactory
    }

}