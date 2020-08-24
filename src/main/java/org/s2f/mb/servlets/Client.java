package org.s2f.mb.servlets;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.s2f.mb.servlets.Server.sendRequestToServlet;

public class Client implements Runnable {
    private static Socket socket;
    private static List<String> lines;

    public Client(Socket client) {
        Client.socket = client;
    }

    @Override
    public void run() {
        try {
            try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                 PrintWriter output = new PrintWriter(socket.getOutputStream())) {

                // ждем первой строки запроса
                while (!input.ready()) ;

                // считываем и печатаем все что было отправлено клиентом
                lines = new ArrayList<>();
                System.out.println();
                while (input.ready()) {
                    String line = input.readLine();
                    lines.add(line);
                    System.out.println(line);
                }

                HttpServletRequest request = Server.createHttpServletRequest(lines);
                sendRequestToServlet((MyRequest) request, output);

                output.flush();

                System.out.println("Client disconnected!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}