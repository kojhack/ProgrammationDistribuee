import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try {
			// Connexion au serveur
			Socket socket = new Socket("localhost", 5000);
			System.out.println("Connected to server on port 5000");

			// Flux d'entrée et sortie pour communiquer avec le serveur
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			InputStream is = socket.getInputStream();
			BufferedReader input = new BufferedReader(new InputStreamReader(is));

			Scanner sc = new Scanner(System.in);
			String operation;
			while (true) {
				// Demander à l'utilisateur d'entrer une opération
				System.out.print("Enter an operation (e.g. 3 + 4) or type 'BYE' to quit: ");
				operation = sc.nextLine();

				// Envoyer l'opération au serveur
				pw.println(operation);

				// Si le client tape "BYE", quitter
				if (operation.equalsIgnoreCase("BYE")) {
					String response = input.readLine();
					System.out.println("Server response: " + response);
					break;
				}

				// Recevoir la réponse du serveur et l'afficher
				String response = input.readLine();
				System.out.println("Server response: " + response);
			}

			// Fermer la connexion
			socket.close();
			System.out.println("Disconnected from server");

		} catch (IOException e) {
			e.printStackTrace();
 }
}
}
