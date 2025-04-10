package java_practice.was.v2;

import java.io.*;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java_practice.network1.util.MyLogger.log;

public class HttpRequestHandlerV2 implements Runnable {

    private final Socket socket;

    public HttpRequestHandlerV2(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            process();
        } catch (Exception e) {
            log(e);
        }
    }

    private void process() {
        try (socket;
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), UTF_8))) {

            String requestString = requestToString(reader);

            if (requestString.contains("/favicon.ico")) {
                log("favicon 요청");
                return;
            }
            log("HTTP 요청 정보 출력");
            System.out.println(requestString);
            log("HTTP 응답 생성중...");
            sleep(5000);
            responseToClient(writer);
            log("HTTP 응답 전달 완료");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String requestToString(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            if(line.isEmpty()) {
                break;
            }
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    private void responseToClient(PrintWriter writer) throws IOException {
        String body = "<h1>Hello World</h1>";
        int length = body.length();

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Content-Type: text/html\r\n");
        sb.append("Content-Length: " + length + "\r\n");
        sb.append("\r\n");
        sb.append(body);

        log("HTTP 응답 정보 출력");
        System.out.println(sb);

        writer.println(sb);
        writer.flush();
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
