import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

    public static void main(String[] args) {

        String host = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(host, port)) {

            System.out.println("Connected to Name Server.");

            BufferedReader input = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));

            PrintWriter output = new PrintWriter(
                    socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter domain name: ");
            String name = scanner.nextLine();

            // Send domain name to server
            output.println(name);

            // Receive IP address from server
            String response = input.readLine();

            System.out.println("Response from Name Server: " + response);

        } catch (IOException e) {

            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
