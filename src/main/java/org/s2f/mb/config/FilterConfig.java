package org.s2f.mb.config;

import org.s2f.mb.servlets.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.Ordered;

import javax.servlet.DispatcherType;
import java.util.Collections;

/*@Configuration
public class FilterConfig {


    private final AuthorizationFilter authorizationFilter;

    @Autowired
    public FilterConfig(AuthorizationFilter dateLoggingFilter) {
        this.authorizationFilter = dateLoggingFilter;
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> dateLoggingFilterRegistration() {
        FilterRegistrationBean<AuthorizationFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(authorizationFilter);
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/AddAndShowServlet*"));
       filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST);
       // filterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE - 1);
        return filterRegistrationBean;
    }

}*/
