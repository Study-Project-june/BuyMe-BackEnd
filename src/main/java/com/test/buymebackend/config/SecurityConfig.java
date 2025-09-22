package com.test.buymebackend.config;


import com.test.buymebackend.config.security.jwt.JwtAuthenticationFilter;
import com.test.buymebackend.config.security.jwt.JwtAuthorizationFilter;
import com.test.buymebackend.config.security.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.test.buymebackend.config.SecurityConstant.PUBLIC_URLS;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationConfiguration authenticationConfiguration;
    //TODO IOC 와 DI 내용 공부하기 아래와 같이 생성자를 만들면 알아서 값을 넣어줌
    public SecurityConfig(JwtTokenProvider jwtTokenProvider, AuthenticationConfiguration authenticationConfiguration) {
        this.jwtTokenProvider = jwtTokenProvider;
        //스프링 시큐리티가 자동으로 구성해준 AuthenticationManager 를 사용하기 위함
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable) //jwt 에선 비활성화 함
                .csrf(AbstractHttpConfigurer::disable)//jwt 환경에선 비활성화 하는게 일반적
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(PUBLIC_URLS).permitAll() //public_url 은 통과
                                .anyRequest().authenticated() // 그 외 다른 url 들은 인증필요하다.
                )
                //체인의 맨 뒤에 추가
                .addFilter(new JwtAuthenticationFilter(authenticationManager(authenticationConfiguration),jwtTokenProvider))
                //(x,y) y 의 앞쪽에 필터를 추가
                .addFilterBefore(new JwtAuthorizationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
