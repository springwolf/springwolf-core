package io.github.stavshamir.swagger4kafka.integration.dtos

data class ExamplePayloadDto(val aString: String, val aLong: Long, val anEnum: ExampleEnum) {
    enum class ExampleEnum {
        FOO1, FOO2, FOO3
    }
}
