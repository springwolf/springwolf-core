package io.github.stavshamir.swagger4kafka.integration

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IntegrationTestsApplication

fun main(args: Array<String>) {
    runApplication<IntegrationTestsApplication>(*args)
}
