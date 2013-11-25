import java.net.*;
import java.util.Scanner;
import java.io.*;

public class SimpleServer {
	private int port = 3456;
	private ServerSocket s = null;
	
	public void activate() throws IOException {
		try {	// crea un server che accetta connessioni
			s = new ServerSocket(port);
		} catch(IOException e) {
			System.err.println("Could not listen on port " + port);
			System.exit(1);
		}
		
		// Rimane in attesa di connessioni
		while(true) {
			System.out.println("Server in listening sulla porta " + port);
			Socket s1 = s.accept();
			System.out.println("Socket accepted");
			ServerThread st = new ServerThread(s1);
			st.start();
		}
	}
	
	public static void main(String[] args) {
		SimpleServer s = new SimpleServer();
		try {
			s.activate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class ServerThread extends Thread {
	private Socket s = null;
	public ServerThread(Socket socket) {
		super("ServerThread");
		s = socket;
	}
	
	public void run() {
		try {
			OutputStream o = s.getOutputStream();
			PrintWriter output = new PrintWriter(o,true);
			InputStream i = s.getInputStream();
			Scanner input = new Scanner(i);
			output.println("Ciao caro client"); // spedisce un dato
			String dato = input.next(); // legge un dato
			System.out.println(dato); // elabora il dato
		} catch(IOException e) {
			System.err.println("I/O exception"); System.exit(1);
		} finally {
			try {
				s.close();
			} catch(IOException e) {
				System.err.println("Something went wrong");
				System.exit(0);
			}
		}
	}
}
