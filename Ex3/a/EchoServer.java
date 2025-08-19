import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Echo Server started. Waiting for client...");

            // Accept one client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected from " + clientSocket.getInetAddress());

            // Input and output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;

            // Echo loop
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Client says: " + inputLine);
                out.println("Echo: " + inputLine);

                if (inputLine.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            clientSocket.close();
            System.out.println("Connection closed.");

        } catch (IOException e) {
            System.err.println("Error in EchoServer: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
