package java_practice.network1.network.tcp.v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static java_practice.network1.util.MyLogger.log;

public class ServerV1 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        // 12345번 포트로 열어둠
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트: "+ PORT);

        // 클라이언트가 12345번 포트로 접속하면 소켓을 만들어준다.
        // 이 소켓으로 클라이언트와 서버가 통신할 수 있음.
        Socket socket = serverSocket.accept();

        // 실제 클라이언트와 서버 간의 어떤 통신을 이 소켓이라는 것을 통해 이뤄짐
        log("소켓 연결" + socket);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        // 클라이언트에게 문자 받기
        String received = input.readUTF();
        log("client -> server: " + received);

        // 클라이언트에게 문자 보내기
        String toSend = received + " World!";
        output.writeUTF(toSend);
        log("client <<- server: " + toSend);

        // 자원 종료
        log("연결 종료 " + socket);
        input.close();
        output.close();
        socket.close();
        serverSocket.close();
    }
}
