package com.test.buymebackend.config;

import java.lang.reflect.Method;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration //Spring 설정 파일이라고 알려주는 어노테이션
public class WebConfig implements WebMvcConfigurer {

    @Bean//spring 컨테이너가 관리하는 객체(Bean)으로 등록해달라는 의미
    public WebMvcRegistrations webMvcRegistrations() {  //Springboot 에서 자동으로 설정해주는 MVC 의 구성요소들을
                                                        // 개발자가 직접 설정한 코드로 교체할 수 있도록 해주는 interface 임
        return new WebMvcRegistrations() { //해당 interface 를 익명 클래스를 사용하여 getRequestMappingHandlerMapping 함수를 수정해줌
            @Override
            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
                return new CustomRequestMappingHandlerMapping(); //spring 의 기본 클래스인 RequestMappingHandlerMapping 을 상속 받고 있음
            }
        };
    }

    // CORS 설정 추가
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(6000);
    }

    private static class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
        //상속 클래스는 스프링 애플리케이션이 시작될때 @Controller 와 RequestMapping 을 미리 스캔해서
        //어떤 URL 이 어떤 메소드와 연결되는지에 대한 Map 을 만드는 역할을 하는 클래스
        //해당 클래스 기능 대신 Custom 하여 추가적인 기능을 부여한 클래스를 생성함
        @Override
        protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
            RequestMappingInfo info = super.getMappingForMethod(method, handlerType);
            if(info != null) {
                if(AnnotatedElementUtils.hasAnnotation(handlerType, RestController.class)
                        && !handlerType.getPackageName().contains("springdoc")) { //handlerType 클래스에 RestController 어노테이션이
                    //붙어있는지 확인 한 후 붙어 있다면 해당 요청에 /api 라는 경로를 추가적으로 붙여주는것
                    info = RequestMappingInfo.paths("/api")
                            .build()
                            .combine(info);
                }
            }
            return info;
        }
    }
}
