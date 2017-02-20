package org.asion.boot.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Base Application Test
 *
 * @author Asion.
 * @since 16/7/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        value = {"server.port=0"},
        webEnvironment = RANDOM_PORT,
        classes = SpringBootMvcMybatisTestApplication.class
)
public class BaseApplicationTests {

    @LocalServerPort
    protected int port = 0;

    @Test
    public void test() {
        System.out.println("test");
    }
}