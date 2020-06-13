package org.s2f.mb.servlets;

import org.s2f.mb.service.LocalUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("login", LocalUser.getLocalUser().get().getLogin());
        request.setAttribute("uuid", LocalUser.getLocalUser().get().getUuid().toString());
        this.getServletContext().getRequestDispatcher("/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
