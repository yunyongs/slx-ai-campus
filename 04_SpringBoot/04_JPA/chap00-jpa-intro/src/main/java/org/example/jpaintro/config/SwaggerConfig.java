package org.example.jpaintro.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableWebMvc   // Spring Boot에서는 붙이지 않는다. MVC 자동설정이 꺼져 Swagger UI가 깨진다.
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    // annotations.info.Info(1번 방식)와 이름이 같아서 models 쪽은 전체 경로로 적는다
    private io.swagger.v3.oas.models.info.Info apiInfo() {
        return new io.swagger.v3.oas.models.info.Info()
                .title("JPA Intro API 설명서")
                .description("chap00-jpa-intro 메뉴 API 수업 자료 입니다.")
                // 강사 코드 :
//                .title("나의 첫 JPA를 사용한 RESTAPI 서버")
//                .description("REST API 수업 API 명세서")
                .version("1.0.0");
    }
}
