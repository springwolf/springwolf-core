import org.apache.commons.io.IOUtils
import org.junit.ClassRule
import org.junit.Test
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.springframework.web.client.RestTemplate
import org.testcontainers.containers.DockerComposeContainer
import java.io.File
import java.io.InputStream
import java.nio.charset.StandardCharsets


class ApiIntegrationTests {

    companion object {
        private val restTemplate = RestTemplate()

        private const val APP_NAME = "app_1"
        private const val APP_PORT = 8080

        @JvmField
        @ClassRule
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

        val s: InputStream = this.javaClass.getResourceAsStream("asyncapi.json")
        val expected: String = IOUtils.toString(s, StandardCharsets.UTF_8)

        JSONAssert.assertEquals(expected, actual, JSONCompareMode.NON_EXTENSIBLE)
    }

}