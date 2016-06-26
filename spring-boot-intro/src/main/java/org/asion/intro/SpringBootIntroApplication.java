package org.asion.intro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * <p>Spring Boot Intro</p>
 * <p>Spring Boot 引子</p>
 * <p>使用了一个简单的 Servlet 例子, 来做;</p>
 *
 * <p>只需要几句简单的代码, 就可以轻松的构建一个Servlet程序, 对比以前的一大堆xml配置是不是简单了不知多少倍!</p>
 *
 * @author Asion.
 * @since 16/6/26.
 */
@SpringBootApplication
public class SpringBootIntroApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootIntroApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootIntroApplication.class, args);
    }
}
