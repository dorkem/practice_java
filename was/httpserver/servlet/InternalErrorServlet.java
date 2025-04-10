package java_practice.was.httpserver.servlet;

import java_practice.was.httpserver.HttpRequest;
import java_practice.was.httpserver.HttpResponse;
import java_practice.was.httpserver.HttpServlet;

public class InternalErrorServlet implements HttpServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) {
        response.setStatus(500);
        response.writeBody("<h1>Internal Error</h1>");
    }
}
