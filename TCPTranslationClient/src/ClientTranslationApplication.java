import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class ClientTranslationApplication {

	static ClientTranslationFrame clientTranslationFrame = new ClientTranslationFrame();

	public void SendText(int textIndex, String sentence, String language) throws InterruptedException {

		try {

			// Receive data from the server socket
			Socket tsocket = new Socket(InetAddress.getLocalHost(), 4228); // Connect to the server @ localhost, port 4228

			// Sends data to the server
			DataOutputStream outputStream = new DataOutputStream(tsocket.getOutputStream());	// Create stream to write data on the network

			// Receive data from client frame & Send to the server
			outputStream.write(textIndex);
			outputStream.writeUTF(sentence);
			outputStream.writeUTF(language);
			// outputStream.flush();

			System.out.println("[Client] textIndex: " + textIndex);
			System.out.println("[Client] language: " + language);

			// Receive data from the server
//			DataInputStream inputStream = new DataInputStream(tsocket.getInputStream()); // create input stream
//			String textT = inputStream.readUTF(); // Read from the network
			
			InputStreamReader inputStreamReader = new InputStreamReader(tsocket.getInputStream(), StandardCharsets.UTF_8);
			BufferedReader bufferedReader = new BufferedReader (inputStreamReader);
			String textTbr = bufferedReader.readLine(); // Read from the network
			clientTranslationFrame.updateTranslatedText(String.valueOf(textTbr));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws UnknownHostException, IOException {

		// Launch client-side frame
		clientTranslationFrame.setVisible(true);
	}

}
