package org.asion.boot.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>Spring Boot MVC</p>
 * <p>Spring Boot MVC</p>
 * <p>使用了一个简单的 Spring MVC 例子, 来做;</p>
 *
 * <p>只需要几句简单的代码, 就可以轻松的构建一个Spring MVC程序, 对比以前的一大堆xml配置是不是简单了不知多少倍!</p>
 *
 * @author Asion.
 * @since 16/6/26.
 */
@SpringBootApplication
@EnableWebMvc
@Configuration
public class SpringBootMvcApplication extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/static/**")) {
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMvcApplication.class, args);
    }

}
