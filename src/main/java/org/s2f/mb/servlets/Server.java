package org.s2f.mb.servlets;

import javax.servlet.FilterChain;
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

                    // отправляем ответ
                    output.println("HTTP/1.1 200 OK");
                    output.println("Content-Type: text/html; charset=utf-8");
                    output.println();
                    output.println("<p>Привет всем!</p>");
                    output.flush();

                    // по окончанию выполнения блока try-with-resources потоки,
                    // а вместе с ними и соединение будут закрыты
                    System.out.println("Client disconnected!");

                    HttpServletRequest request = createHttpServletRequest(lines);
                    sendRequestToServlet((MyRequest) request);

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
        System.out.println("Method: " + method);
        String url = bodyParams[1];
        String servletName = url.substring(0, url.indexOf("?"));
        System.out.println("Servlet: " + servletName);

        url = url.substring(url.indexOf("?") + 1);
        Map<String, String[]> params = getRequestParams(url);
        System.out.println("URL: " + url);


        HttpServletRequest httpServletRequest = new MyRequest(method, servletName, params);
        return httpServletRequest;
    }

   /* public static HttpServletResponse createHttpServletResponse{
        HttpServletResponse httpServletResponse = new MyResponse();
    }*/

    public static Map<String, String[]> getRequestParams(String requestLine) {
        Map<String, String[]> params = new HashMap<>();
        String[] paramsFromRequestLine = requestLine.split("&");
        for (String x : paramsFromRequestLine) {
            System.out.println("Pair: " + x);
            String[] pair = x.split("=");
            String key = pair[0];
            String[] values = {pair[1]};
            params.put(key, values);
        }
        return params;
    }

    public static void sendRequestToServlet(MyRequest request) throws IOException {
        if (request.getMethod().equals("POST") && request.getServletName().equals("/AddAndShowServlet")) {
           // new AuthorizationFilter(request, null,);
            new AddAndShowServlet().doPost(request, null);
        } else if (request.getMethod().equals("GET") && request.getServletName().equals("/AddAndShowServlet")) {
            new AddAndShowServlet().doGet(request, null);
        } else if (request.getMethod().equals("POST") && request.getServletName().equals("/AuthorizationServlet")) {
            new AuthorizationServlet().doPost(request, null);
        }
    }
}
