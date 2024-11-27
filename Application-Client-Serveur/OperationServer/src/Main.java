import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
	public static void main(String[] args) {

		try {
			ServerSocket serverSocket = new ServerSocket(5000);
			System.out.println("Server started on port 5000");

			// Attendre la connexion d'un client
			Socket socket = serverSocket.accept();
			System.out.println("Client connected");

			// Flux d'entrée et sortie pour communiquer avec le client
			InputStream is = socket.getInputStream();
			BufferedReader input = new BufferedReader(new InputStreamReader(is));
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);

			String operation;
			while (true) {
				// Lire l'opération envoyée par le client
				operation = input.readLine();

				// Si l'opération est "BYE", fermer la connexion
				if (operation.equalsIgnoreCase("BYE")) {
					pw.println("Goodbye!");
					break;
				}

				// Calculer le résultat de l'opération
				try {
					double result = calculate(operation);
					pw.println("Result: " + result);
				} catch (Exception e) {
					pw.println("Error in calculation");
				}
			}

			// Fermer la connexion
			socket.close();
			System.out.println("Client disconnected");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static double calculate(String operation) throws Exception {
		String[] parts = operation.split(" ");
		double num1 = Double.parseDouble(parts[0]);
		String operator = parts[1];
		double num2 = Double.parseDouble(parts[2]);

		switch (operator) {
			case "+":
				return num1 + num2;
			case "-":
				return num1 - num2;
			case "*":
				return num1 * num2;
			case "/":
				if (num2 == 0) throw new ArithmeticException("Cannot divide by zero");
				return num1 / num2;
			default:
				throw new Exception("Unknown operator");
		}
	}

}

