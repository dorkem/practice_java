package java_practice.was.V4;

import java.io.IOException;

public class ServerMain {
    final static int PORT = 12345;

    public static void main(String[] args) throws IOException {
        HttpServerV4 server = new HttpServerV4(PORT);
        server.start();
    }
}
