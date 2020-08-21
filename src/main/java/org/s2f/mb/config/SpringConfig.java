package org.s2f.mb.config;

import org.s2f.mb.service.interceptors.AuthorizationInterceptor;

import org.s2f.mb.servlets.AuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@ComponentScan("org.s2f.mb")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {
}
