import java.io.*;
import java.net.*;

public class EchoServer {

    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {

        int port = 5000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server started on port " + port);
            System.out.println("Waiting for clients...");

            while (true) {

                Socket socket = serverSocket.accept();

                System.out.println("Client connected: "
                        + socket.getInetAddress());

                Thread clientThread =
                        new Thread(() -> handleClient(socket));

                clientThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static void handleClient(Socket socket) {

        try (
            BufferedReader input =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

            PrintWriter output =
                    new PrintWriter(
                            socket.getOutputStream(), true)
        ) {

            String message;

            while ((message = input.readLine()) != null) {

                System.out.println(
                        "Received from client: " + message);

                output.println("Echo: " + message);
            }

        } catch (IOException e) {

            System.out.println("Client disconnected.");

        } finally {

            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
