import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class BasicClient {
    public static void main(String[] args) throws IOException {


        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to server on port 5000");

            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os,true);
            InputStream is = socket.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(is));
            Scanner sc = new Scanner(System.in);

            String message;
            while (true) {
                System.out.print("Entrez votre requête (ou 'BYE' pour quitter) : ");
                message = sc.nextLine();

                // Envoyer le message au serveur
                pw.println(message);

                // Si le client tape "BYE", on arrête l'application
                if (message.equalsIgnoreCase("BYE")) {
                    String response = input.readLine();
                    System.out.println("Server response: " + response);
                    break;
                }
                String response = input.readLine();
                System.out.println("Server response: " + response);
            }

            socket.close();
            System.out.println("Disconnected from server");

        } catch ( IOException e) {
            e.printStackTrace();
        }


    }
}