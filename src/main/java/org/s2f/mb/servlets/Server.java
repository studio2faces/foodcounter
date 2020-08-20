package org.s2f.mb.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    private static final Logger log = LoggerFactory.getLogger(AddAndShowServlet.class);
    static List<String> lines = new ArrayList<>();


    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8081)) {
            System.out.println("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                lines = new ArrayList<>();


                try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                     PrintWriter output = new PrintWriter(socket.getOutputStream())) {

                    // ждем первой строки запроса
                    while (!input.ready()) ;

                    // считываем и печатаем все что было отправлено клиентом
                    System.out.println();
                    while (input.ready()) {
                        String line = input.readLine();
                        lines.add(line);
                        System.out.println(line);
                    }

                    HttpServletRequest request = createHttpServletRequest(lines);
                    sendRequestToServlet((MyRequest) request, output);

                    output.flush();

                    System.out.println("Client disconnected!");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static HttpServletRequest createHttpServletRequest(List<String> lines) {
        String body = lines.get(0);
        String[] bodyParams = body.split(" ");

        String method = bodyParams[0];
        log.info("Method is {}.", method);

        String url = bodyParams[1];

        String servletName = url.substring(0, url.indexOf("?"));
        log.info("Servlet is {}", servletName);

        url = url.substring(url.indexOf("?") + 1);
        Map<String, String[]> params = getRequestParams(url);
        log.info("Params are {}", url);

        return new MyRequest(method, servletName, params);
    }

    public static Map<String, String[]> getRequestParams(String requestLine) {
        Map<String, String[]> params = new HashMap<>();
        String[] paramsFromRequestLine = requestLine.split("&");
        for (String x : paramsFromRequestLine) {
            String[] pair = x.split("=");
            String key = pair[0];
            String[] values = {pair[1]};
            params.put(key, values);
        }
        return params;
    }

    public static void sendRequestToServlet(MyRequest request, PrintWriter output) throws IOException {
        if (request.getMethod().equals("POST") && request.getServletName().equals("/AddAndShowServlet")) {
            try {
                new AuthorizationFilter().doFilterWithoutChain(request);
                new AddAndShowServlet().doPost(request, output);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        } else if (request.getMethod().equals("GET") && request.getServletName().equals("/AddAndShowServlet")) {
            try {
                new AuthorizationFilter().doFilterWithoutChain(request);
                new AddAndShowServlet().doGet(request, output);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        } else if (request.getMethod().equals("POST") && request.getServletName().equals("/AuthorizationServlet")) {
            new AuthorizationServlet().doPost(request, output);
        }
    }
}