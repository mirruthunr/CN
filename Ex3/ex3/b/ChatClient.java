import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // or use actual IP
        int port = 5000;

        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Connected to server.");

            // Thread to receive messages from server
            Thread receiveThread = new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Server: " + message);
                        if (message.equalsIgnoreCase("exit")) {
                            System.out.println("Server ended the chat.");
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Connection lost.");
                }
            });

            // Thread to send messages to server
            Thread sendThread = new Thread(() -> {
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                    String message;
                    while ((message = userInput.readLine()) != null) {
                        out.println(message);
                        if (message.equalsIgnoreCase("exit")) break;
                    }
                } catch (IOException e) {
                    System.out.println("Error sending message.");
                }
            });

            receiveThread.start();
            sendThread.start();

            receiveThread.join();
            sendThread.join();
            socket.close();
            System.out.println("Chat closed.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
