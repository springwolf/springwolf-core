// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.kotlinx_serialization_model_converter.converter

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Kotlinx LocalDate Serializer from string to LocalDate from ISO format (`yyyy-MM-dd`).
 */
object LocalDateSerializer : KSerializer<LocalDate> {
    private const val DATE_PATTERN: String = "yyyy-MM-dd"
    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN)

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): LocalDate {
        return LocalDate.parse(decoder.decodeString(), dateTimeFormatter)
    }

    override fun serialize(
        encoder: Encoder,
        value: LocalDate,
    ) {
        encoder.encodeString(value.format(dateTimeFormatter))
    }
}
