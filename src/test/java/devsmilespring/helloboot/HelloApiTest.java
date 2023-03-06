package devsmilespring.helloboot;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.DEFINED_PORT)
class HelloApiTest {

    @Test
    void hello() {
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> res = rest.getForEntity(
                "http://localhost:9090/app/hello?name={name}", String.class, "Spring");

        // status code 200, header text/plain, body Hello Spring
        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE))
                .startsWith(MediaType.TEXT_PLAIN_VALUE);
        Assertions.assertThat(res.getBody()).isEqualTo("Decorator :Hello Spring");

    }
}