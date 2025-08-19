import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // or use "127.0.0.1"
        int port = 12345;

        try (
            Socket socket = new Socket(serverAddress, port);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            System.out.println("Connected to Echo Server.");
            System.out.println("Type something (type 'exit' to quit):");

            String input;
            while ((input = userInput.readLine()) != null) {
                out.println(input); // send to server
                String response = in.readLine(); // receive from server
                System.out.println("Server: " + response);

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
