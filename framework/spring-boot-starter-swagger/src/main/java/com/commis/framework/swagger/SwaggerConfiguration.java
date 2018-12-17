package com.commis.framework.swagger;

import static com.google.common.base.Predicates.not;
import static springfox.documentation.builders.PathSelectors.regex;

import com.fasterxml.classmate.TypeResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@EnableSwagger2
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfiguration {

    private final SwaggerProperties properties;

    public SwaggerConfiguration(final SwaggerProperties properties) {
        this.properties = properties;
        log.info("using springfox.swagger2 with properties='{}'", properties);
    }

    @Bean
    public Docket docket(final TypeResolver resolver) {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(not(regex("/error.*")))
            .build()
            .pathMapping("/")
            .apiInfo(new ApiInfoBuilder()
                .title(properties.getTitle())
                .version(properties.getVersion())
                .build()
            );
    }

    @Bean
    public WebMvcConfigurer forwardToIndex() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                if (properties.isRedirect()) {
                    registry.addViewController("/").setViewName("redirect:/swagger-ui.html");
                }
            }
        };
    }

    /**
     * 支持跨域访问的配置
     */
    @Bean
    public WebMvcConfigurer supportCORSConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedHeaders("*")
                    .allowedMethods("*")
                    .allowedOrigins("*");
            }
        };
    }
}
