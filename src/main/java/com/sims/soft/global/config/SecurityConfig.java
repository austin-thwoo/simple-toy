package com.sims.soft.global.config;

import com.google.gson.Gson;

import com.sims.soft.global.dto.ErrorResponse;
import com.sims.soft.global.error.model.ErrorCode;
import com.sims.soft.global.filter.TokenFilter;
import com.sims.soft.global.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@RequiredArgsConstructor
@Configuration
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final TokenProvider tokenProvider;
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2-console/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic()
                .disable()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()//이거없으면 큰일남
                .antMatchers("/auth/**", "/resource/*").permitAll()
                .antMatchers("/user/**","/board/**").hasAnyRole("USER")
                .antMatchers("/manage/examination/**","/manage").hasRole("ADMIN")
//                .antMatchers("/standard/**{Get}").hasRole("ADMIN")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint())
                .and().
                addFilterBefore(new TokenFilter(tokenProvider), UsernamePasswordAuthenticationFilter.
                        class);
    }

    @EnableGlobalMethodSecurity( securedEnabled = true)
    public static class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

        @Override
        protected MethodSecurityExpressionHandler createExpressionHandler() {
            return new OAuth2MethodSecurityExpressionHandler();
        }
    }

    private AccessDeniedHandler accessDeniedHandler() {


        log.info("accessDeniedHandler");

        return (httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
            accessDeniedException(httpServletRequest, httpServletResponse);
        };
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {
        log.info("authenticationEntryPoint");

        return (httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            invalidTokenException(httpServletRequest, httpServletResponse);
        };
    }

    private void accessDeniedException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {


        log.info("accessDeniedException");


        PrintWriter out = httpServletResponse.getWriter();
        httpServletResponse.setContentType("application/json");
        httpServletRequest.setCharacterEncoding("UTF-8");

        final ErrorResponse exception = ErrorResponse.of(ErrorCode.HANDLE_ACCESS_DENIED);

        out.print(new Gson().toJson(exception));
        out.flush();
    }

    private void invalidTokenException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        log.info("invalidTokenException");


        PrintWriter out = httpServletResponse.getWriter();
        httpServletResponse.setContentType("application/json");
        httpServletRequest.setCharacterEncoding("UTF-8");

        final ErrorResponse exception = ErrorResponse.of(ErrorCode.HANDLE_INVALID_TOKEN);

        out.print(new Gson().toJson(exception));
         out.flush();
    }
}
