package io.github.stavshamir.springwolf.example

import org.apache.commons.io.IOUtils
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.springframework.web.client.RestTemplate
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.io.File
import java.io.InputStream
import java.nio.charset.StandardCharsets

/**
 * While the assertion of this test is identical to ApiIntegrationTests,
 * the setup uses a full docker-compose context with a real amqp instance.
 */
@Testcontainers
// @Ignore("Uncomment this line if you have issues running this test on your local machine.")
class ApiIntegrationWithDockerTests {

    companion object {
        private val restTemplate = RestTemplate()

        private const val APP_NAME = "app_1"
        private const val APP_PORT = 8080

        @JvmField
        @Container
        val environment: DockerComposeContainer<Nothing> = DockerComposeContainer<Nothing>(File("docker-compose.yml"))
            .withExposedService(APP_NAME, APP_PORT)
    }

    private fun baseUrl(): String {
        val host = environment.getServiceHost(APP_NAME, APP_PORT)
        val port = environment.getServicePort(APP_NAME, APP_PORT)
        return "http://$host:$port"
    }

    @Test
    fun `asyncapi-docs shold return the correct json response`() {
        val url = "${baseUrl()}/springwolf/docs"
        val actual = restTemplate.getForObject(url, String::class.java)
        println("Got: $actual")

        val s: InputStream = this.javaClass.getResourceAsStream("/asyncapi.json")
        val expected: String = IOUtils.toString(s, StandardCharsets.UTF_8)

        print(actual)

        JSONAssert.assertEquals(expected, actual, JSONCompareMode.STRICT_ORDER)
    }

}