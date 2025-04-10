package java_practice.was.v2;

import java.io.IOException;

public class ServerMain {
    final static int PORT = 12345;

    public static void main(String[] args) throws IOException {
        HttpServerV2 server = new HttpServerV2(PORT);
        server.start();
    }
}
