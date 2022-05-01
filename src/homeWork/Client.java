package homeWork;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        new Client().start("localhost", 8970);
    }

    public void start(String host, int port) throws IOException {
        Socket socket = new Socket(host, port);
        System.out.println("Клиент запущен");
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

        runOutputLoop(outputStream);

        runInputLoop(inputStream);
    }

    private void runInputLoop(DataInputStream inputStream) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        String message = inputStream.readUTF();
                        System.out.println("From server: " + message);
                        if (message.startsWith("/end")) {
                            System.exit(0);
                        }
                    } catch (IOException e) {
                        System.out.println("Подключение прервано");
                        break;
                    }
                }
            }
        }).start();
    }

    private void runOutputLoop(DataOutputStream outputStream) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String message = scanner.nextLine();
            if (message.startsWith("/end")) {
                break;
            }
            outputStream.writeUTF(message);
        }
    }
}
