package java_practice.network1.network.tcp.v5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static java_practice.network1.util.MyLogger.log;

public class ServerV5 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        // 12345번 포트로 열어둠
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        while (true){
            // 어짜피 OS backlog Queue에 없으면 블로킹상태임
            Socket socket = serverSocket.accept();
            log("소켓 연결: " + socket);

            SessionV5 session = new SessionV5(socket);
            Thread thread = new Thread(session);
            thread.start();
        }
    }
}
