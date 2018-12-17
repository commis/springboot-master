package com.commis.framework.swagger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("springbootcamp.swagger")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwaggerProperties {

    String title = "APPLICATION.NAME";
    String version = "APPLICATION.VERSION";
    boolean redirect = true;
}
