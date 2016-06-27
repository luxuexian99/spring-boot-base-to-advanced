package org.asion.intro;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Spring Boot Test
 * AssertJ
 *
 * @author Asion.
 * @since 16/6/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SpringBootIntroApplication.class
)
public class SpringBootIntroApplicationTest {

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port = 0;

    private String uri = "http://localhost";

    @Before
    public void before() {
        uri = uri + ":" + port;
    }

    @Test
    public void testHome() throws Exception {
        ResponseEntity<String> entity = restTemplate.getForEntity(uri, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).contains("<title>Static");
    }

    @Test
    public void testCss() throws Exception {
        ResponseEntity<String> entity =
                restTemplate.getForEntity(uri + "/webjars/bootstrap/3.3.6/css/bootstrap.min.css", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).contains("body");
        assertThat(entity.getHeaders().getContentType()).isEqualTo(MediaType.valueOf("text/css"));
    }
}
