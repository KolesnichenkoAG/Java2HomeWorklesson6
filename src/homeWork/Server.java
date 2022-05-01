package homeWork;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    private static final int PORT = 8970;

    public static void main(String[] args) throws IOException {
        new Server().start(PORT);
    }

    public void start(int port) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен");
        }
    }
}
