package org.example.restapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 1. Swagger 설정 어노테이션 방식
//    - 선언적이고 간결하지만 정적인 값만 사용 가능
//@OpenAPIDefinition(
//        info = @Info(title = "REST API 사용설명서",
//                description = "REST API 수업 자료 입니다.",
//                version = "v1",
//                contact = @Contact(
//                        name = "bear",
//                        email = "bear.ohgiraffers@gmail.com"
//                ))
//)
//@Configuration
//public class SwaggerConfig {
//}

// Swagger 설정방식 두번째
// 자바 코드로 OPENAPI 설정
//    - OpenAPI 객체를 직접 만들어 빈으로 등록: 조건별 설정, 외부 설정값 연동 등 동적 구성 가능
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
                .title("Rest API 설명서")
                .description("REST API 수업 자료 입니다.")
                .version("1.0.0");
    }
}
