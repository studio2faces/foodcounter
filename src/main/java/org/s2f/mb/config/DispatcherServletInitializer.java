package org.s2f.mb.config;

import org.s2f.mb.servlets.AuthorizationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

   /* @Override
    protected Filter[] getServletFilters() {
        DelegatingFilterProxy filterProxy = new DelegatingFilterProxy();
        filterProxy.setTargetBeanName("authorizationFilter");

        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);

        return new Filter[]{encodingFilter, filterProxy};
    }
*/
   /* @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy("authorizationFilter");
        servletContext.addFilter("authorizationFilter", delegatingFilterProxy)
                .addMappingForUrlPatterns(null, false, "/AddAndShowServlet");

        *//*DelegatingFilterProxy encodingFilter = new DelegatingFilterProxy("encodingFilter");
        servletContext.addFilter("encodingFilter", encodingFilter)
                .addMappingForUrlPatterns(null, false, "/");*//*

    }*/


}
