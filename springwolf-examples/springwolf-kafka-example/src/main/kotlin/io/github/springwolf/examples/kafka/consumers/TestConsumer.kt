package io.github.springwolf.examples.kafka.consumers

import io.github.springwolf.core.asyncapi.annotations.AsyncListener
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher
import org.springframework.stereotype.Component
import kotlinx.coroutines.*

@Component
class AsyncConsumerProducer {
    @AsyncListener(
        operation =
        AsyncOperation(
            channelName = "INCOMING_REQUEST",
            description = "Inbound message.",
            payloadType = Input::class,
        ),
    )
    @AsyncPublisher(
        operation =
        AsyncOperation(
            channelName = "OUTBOUND_RESPONSE",
            description = "Outbound message.",
            payloadType = Output::class,
        ),
    )
    suspend fun consumeAndProduce(input: Input) = coroutineScope {
//  suspend fun consumeAndProduce(@Payload input: Input) = coroutineScope {

        delay(100) // ensure suspend function is being used

        Output(input.value)
    }
}

data class Input(val value: String)
data class Output(val value: String)
