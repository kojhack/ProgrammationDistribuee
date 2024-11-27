import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class BasicServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started on port 5000");
            //Attends la connexion du  client
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader input = new BufferedReader(isr);
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);

            String message;
            while (true) {

                message = input.readLine();

                // Si le message est "BYE", on termine la communication
                if (message.equalsIgnoreCase("BYE")) {
                    pw.println("Goodbye!");
                    break;
                }

                // Capitaliser le message et renvoyer la réponse au client
                pw.println("Message capitalized: " + message.toUpperCase());
            }


            socket.close();
            System.out.println("Client disconnected");
        } catch (IOException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();

        }
    }
}