import java.io.*;
import java.net.*;

public class EchoClient {
    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {

        String host = "localhost";
        int port = 5000;

        try (
            Socket socket = new Socket(host, port);

            BufferedReader input =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

            PrintWriter output =
                    new PrintWriter(
                            socket.getOutputStream(), true);

            BufferedReader userInput =
                    new BufferedReader(
                            new InputStreamReader(System.in))
        ) {

            System.out.println("Connected to server.");
            System.out.println("Type messages (type 'exit' to quit):");

            while (true) {

                System.out.print("You: ");
                String message = userInput.readLine();

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
                output.println(message);

                String response = input.readLine();

                System.out.println("Server: " + response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
