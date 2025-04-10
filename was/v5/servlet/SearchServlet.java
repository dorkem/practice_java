package java_practice.was.v5.servlet;

import java_practice.was.httpserver.HttpRequest;
import java_practice.was.httpserver.HttpResponse;
import java_practice.was.httpserver.HttpServlet;

public class SearchServlet implements HttpServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) {
        String query = request.getParameter("q");
        response.writeBody("<h1>Search</h1>");
        response.writeBody("<ul>");
        response.writeBody("<li>query: " + query + "</li>");
        response.writeBody("</ul>");
    }
}
