package com.example.demo.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity //시큐리티 설정을 사용할 수 있게함
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private MyUserDetailService myUserDetailService;

    //스프링시큐리티 암호화객체
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //기본 시큐리티를 설정하는 메서드
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //csrf토큰 사용안함 x
        http.csrf( csrf -> csrf.disable());

        //권한설정!!!
        //모든 요청에 대해서 사용자 인증이 필요함
        //http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());

        //user페이지에 대해서만 인증이 필요함
        //http.authorizeHttpRequests( authorize -> authorize.requestMatchers("/user/**").authenticated().anyRequest().permitAll() );

        //user페이지에 대해서는 USER권한이 필요함
//        http.authorizeHttpRequests( authorize -> authorize
//                        .requestMatchers("/user/**").hasRole("USER")
//                        .anyRequest().permitAll() );

        //user페이지는 USER권한, admin페이지는 ADMIN권한이 필요함
//        http.authorizeHttpRequests( authorize -> authorize
//                .requestMatchers("/user/**").hasRole("USER")
//                .requestMatchers("/admin/**").hasRole("ADMIN")
//                .anyRequest().permitAll()
//                );

        //all페이지는 인증만 되면 들어감. user경로는 롤이 필요함, admin경로도 롤이 필요함. 나머지는 모두 허용
        http.authorizeHttpRequests( authorize -> authorize
                .requestMatchers("/all").authenticated()
                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN", "TESTER")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
        );

        //시큐리티가 제공하는 기본 폼로그인 기능을 쓸수 있습니다. (권한이나, 인증이 없으면 시큐리티가 낚아채서 기본로그인패이지를 보여줌)
        //http.formLogin( Customizer.withDefaults() );

        //사용자의 커스텀 로그인 페이지를 보여줌
        http.formLogin( form ->
                form.loginPage("/login")
                        .loginProcessingUrl("/loginForm") //클라에서 보내는 로그인요청 지정
                        //.failureUrl("/login?error=true") //로그인 실패시 URL
                        .failureHandler( customAuthenticationFail() )
        );

        //권한없음 페이지에 대한 처리
        http.exceptionHandling( ex -> ex
                //.accessDeniedPage("/accessDenied") //권한 없을때 보여줄 페이지
                .accessDeniedHandler( accessDeniedHandler() )
        );

        //로그아웃 처리
        http.logout( out -> out
                .logoutUrl("/logout") //로그아웃 시킬 url
                .logoutSuccessUrl("/login") // 로그아웃 이후에 보여질 페이지
        );

        //나를 기억해
        http.rememberMe( remember -> remember
                .key("coding404") //쿠키를 생성할 때 사용하게되는 비밀키
                .rememberMeParameter("remember-me") //화면에서 넘어오는 name값
                .tokenValiditySeconds( 3600 ) //쿠키의 수명(자동로그인 수명)
                .userDetailsService(myUserDetailService) //리멤버미 성공하면 실행시킬 클래스
        );

        return http.build();
    }

    @Bean
    public CustomAuthenticationFailure customAuthenticationFail() {
        return new CustomAuthenticationFailure("/login"); //로그인 실패시 처리
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDenyHandler("/hello"); //권한 없을때 처리
    }


}
