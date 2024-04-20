// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.kotlinx_serialization_model_converter.converter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class SampleEvent(
    @SerialName("string_field")
    val stringField: String,
    @SerialName("optional_field")
    val optionalField: String? = null,
    @SerialName("boolean_field")
    val booleanField: Boolean,
    @SerialName("int_field")
    val intField: Int,
    @SerialName("long_field")
    val longField: Long,
    @SerialName("float_field")
    val floatField: Float,
    @SerialName("double_field")
    val doubleField: Double,
    @SerialName("short_field")
    val shortField: Short,
    @SerialName("byte_field")
    val byteField: Byte,
    @SerialName("list_field")
    val listField: List<String>,
    @SerialName("set_field")
    val setField: Set<String>,
    @SerialName("map_field")
    val mapField: Map<Int, String>,
    @SerialName("date_field")
    @Serializable(with = LocalDateSerializer::class)
    val dateField: LocalDate,
    @SerialName("enum_field")
    val enumField: Color,
    @SerialName("nested_class")
    val nestedClass: NestedClass?,
    @SerialName("listed_references")
    val listedReferences: List<NestedClass>,
) {
    @Serializable
    data class NestedClass(
        val id: Int,
        val name: String,
        val color: Color,
    )
}

enum class Color {
    RED,
    GREEN,
    BLUE,
}

@Serializable
data class ClassWithListProperty(
    @SerialName("list_field")
    val listField: List<String>,
)

@Serializable
data class ClassWithSetProperty(
    @SerialName("set_field")
    val setField: Set<String>,
)

@Serializable
data class ClassWithCollectionProperty(
    @SerialName("collection_field")
    val setField: Collection<String>,
)

@Serializable
data class ClassWithEnumProperty(
    @SerialName("enum_field")
    val enumField: Color,
)

@Serializable
data class ClassWithNestedProperty(
    @SerialName("nested_class")
    val nestedClass: NestedClass,
) {
    @Serializable
    data class NestedClass(
        val id: Int,
        val name: String,
        val color: Color,
    )
}

@Serializable
data class ClassWithPolymorphism(
    @SerialName("pet")
    val pet: Pet,
)

@Serializable
sealed interface Pet

@Serializable
@SerialName("dog")
data class Dog(val bark: Boolean, val breed: String) : Pet

@Serializable
@SerialName("cat")
data class Cat(val hunt: Boolean, val age: Int) : Pet
