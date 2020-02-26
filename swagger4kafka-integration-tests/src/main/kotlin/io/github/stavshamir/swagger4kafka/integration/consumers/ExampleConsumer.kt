package io.github.stavshamir.swagger4kafka.integration.consumers

import io.github.stavshamir.swagger4kafka.integration.dtos.ExamplePayloadDto
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class ExampleConsumer {

    companion object {
        private val logger = LoggerFactory.getLogger(ExampleConsumer::class.java)
    }

    @KafkaListener(topics = ["example-topic"], containerFactory = "exampleKafkaListenerContainerFactory")
    fun receiveExamplePayload(payload: ExamplePayloadDto) {
        logger.info("Received new message in example-topic: {}", payload.toString())
    }

}