package com.CNUSWAcademy.MyBlog.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("jwt") // Java 클래스에 Propertie 값을 가져와서 사용하는 애너테이션
public class JwtProperties {

    private String issuer;
    private String secretKey;

}
