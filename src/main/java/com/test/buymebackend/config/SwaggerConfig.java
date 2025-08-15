package com.test.buymebackend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    //객체의 타입이
    public OpenAPI openAPI() {

        String jwtSchemeName = "bearer-jwt";

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList(jwtSchemeName);

        Components components = new Components()
                // 1. 기존의 bearer-jwt (HTTP Bearer)
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer"));

        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                //api 옆에 자물쇠 버튼 생성 해당 api 에 토큰이 필요하다는 의미
                .addSecurityItem(securityRequirement)
                //토큰 입력창 생성
                .components(components)
                //해당 프로젝트 정보를 담는 것
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("BuyMe API")
                .description("BuyMe 프로젝트 API 문서")
                .version("v1.0.0");
    }
}
