package org.asion.boot.mvc.web;

import org.asion.boot.mvc.BaseApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Spring Boot Test
 *
 * @author Asion.
 * @since 16/7/12.
 */
public class HelloControllerTest extends BaseApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void indexLoads() {
        ResponseEntity<String> entity = testRestTemplate.getForEntity("http://127.0.0.1:" + port + "/", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody())
                .isNotNull()
                .contains("<p>Hello \n" +
                        "        <span></span>\n" +
                        "        </p>");
        System.out.println(entity.getBody());
    }

    @Test
    public void helloLoads() {
        ResponseEntity<String> entity = testRestTemplate.getForEntity("http://127.0.0.1:" + port + "/hello/Asion", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody())
                .isNotNull()
                .contains("<p>Hello \n" +
                        "        <span>Asion</span>\n" +
                        "        </p>");
        System.out.println(entity.getBody());
    }


}
