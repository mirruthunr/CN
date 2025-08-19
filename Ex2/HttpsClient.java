import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class HttpsClient {
    public static void main(String[] args) {
        String host = "example.org";
        int port = 443;

        try {
            // Create an SSL socket connection to the server
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(host, port);

            // Send HTTP GET request
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.println("GET / HTTP/1.1");
            writer.println("Host: " + host);
            writer.println("Connection: close");
            writer.println(); // End of headers
            writer.flush();

            // Read the server's response
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Close all streams and socket
            writer.close();
            reader.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
