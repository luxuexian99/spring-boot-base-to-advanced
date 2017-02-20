package org.asion.boot.mvc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>Spring Boot MVC Mybatis</p>
 * <p>Spring Boot MVC Mybatis</p>
 * <p>一个 Spring Boot Mybatis</p>
 *
 * @author Asion.
 * @since 16/7/12.
 */
@SpringBootApplication
@EnableWebMvc
@ComponentScan(
        value = "org.asion.boot.mvc",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = SpringBootMvcMybatisApplication.class)
        }
)
public class SpringBootMvcMybatisTestApplication extends WebMvcConfigurerAdapter {

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

}
