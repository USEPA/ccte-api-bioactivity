package gov.epa.ccte.api.bioactivity.web.rest;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class OpenApiTests {

    @LocalServerPort
    private int port;

    @Value("${application.version}")
    private String expectedVersion;

    @Test
    void ensureOpenApiVersionMatchesMavenVersion() throws Exception {
        String url = "http://localhost:" + port + "/v3/api-docs";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
        String jsonResponse = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        String apiVersion = JsonPath.read(jsonResponse, "$.info.version");
        assertThat(apiVersion).isNotNull().isEqualTo(expectedVersion);
    }
}