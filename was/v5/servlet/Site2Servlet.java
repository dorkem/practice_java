package java_practice.was.v5.servlet;

import java_practice.was.httpserver.HttpRequest;
import java_practice.was.httpserver.HttpResponse;
import java_practice.was.httpserver.HttpServlet;

public class Site2Servlet implements HttpServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) {
        response.writeBody("<h1>site2</h1>");
    }
}
