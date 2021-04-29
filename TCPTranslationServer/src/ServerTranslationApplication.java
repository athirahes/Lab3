import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTranslationApplication {

	public static void main(String[] args) throws IOException {

		String ss = "[ServerSocket] ";
		ServerSocket serverSocket = null;
		Translator translation = new Translator();

		try {
			
			// Bind ServerSocket to a port
			int portNo = 4228;
			serverSocket = new ServerSocket(portNo); 
			System.out.println(ss + "Waiting for connection request...");

			while (true) {
				Socket clientSocket = serverSocket.accept(); // Accept client request for connection
				System.out.println(ss + "clientSocket connection request accepted!");
				
				//Receive the data
				DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream()); //Create input stream							
				int textIndex = inputStream.read(); // Read from the network
				String text = inputStream.readUTF();
				String textLanguage = inputStream.readUTF();
				System.out.println(ss + "Requested text at index " + textIndex + ": " + text);
				System.out.println(ss + "Requested textLanguage: " + textLanguage);

				
				//Process data received from server client
				String translatedText = translation.getResult(textIndex, textLanguage);
				System.out.println(ss + "TranslatedText Text: " + translatedText);
				
				
				// Sends/return data to the other server (client)
				DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream()); // Create stream to write data on the network
				outputStream.writeUTF(translatedText); // Send current data back to the client
				
				clientSocket.close(); // Close the client socket
				System.out.println(ss + "clientSocket connection closed\n");
			}

		} catch (IOException ioe) {
			if (serverSocket != null)
				serverSocket.close();

			ioe.printStackTrace();
		}
	}
}
