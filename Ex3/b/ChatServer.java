import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        int port = 5000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for client...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            // Thread to receive messages from client
            Thread receiveThread = new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Client: " + message);
                        if (message.equalsIgnoreCase("exit")) {
                            System.out.println("Client exited the chat.");
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Connection lost.");
                }
            });

            // Thread to send messages to client
            Thread sendThread = new Thread(() -> {
                try {
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
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
            clientSocket.close();
            System.out.println("Chat ended.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
