package java_practice.was.httpserver.servlet;

import java_practice.was.httpserver.HttpRequest;
import java_practice.was.httpserver.HttpResponse;
import java_practice.was.httpserver.HttpServlet;

public class DiscardServlet implements HttpServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) {
        //empty
    }
}
