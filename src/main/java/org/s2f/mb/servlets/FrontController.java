package org.s2f.mb.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(FrontController.class);
    public static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    AuthorizationFilter authorizationFilter = context.getBean("authorizationFilter", AuthorizationFilter.class);
    EncodingFilter encodingFilter = context.getBean("encodingFilter", EncodingFilter.class);
    AddAndShowServlet addAndShowServlet = context.getBean("addAndShowServlet", AddAndShowServlet.class);
    AuthorizationServlet authorizationServlet = context.getBean("authorizationServlet", AuthorizationServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("FrontController: method {}, url {}.", req.getMethod(), req.getRequestURL().toString());
        String servletName = req.getRequestURL().toString().substring(22);
        String method = req.getMethod();

        encodingFilter.doFilterWithoutChain(req, resp);

        if (servletName.equals("AuthorizationServlet")) {
            if (method.equals("POST")) {
                authorizationServlet.doPost(req, resp);
            }
        } else if (servletName.equals("AddAndShowServlet")) {
            authorizationFilter.doFilterWithoutChain(req);
            if (method.equals("GET")) {
                addAndShowServlet.doGet(req, resp);
            } else if (method.equals("POST")) {
                addAndShowServlet.doPost(req, resp);
            }
        }
    }
}
