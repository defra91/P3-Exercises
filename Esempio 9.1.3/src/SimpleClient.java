import java.net.*;
import java.util.Scanner;
import java.io.*;

public class SimpleClient {
	private Socket s = null;
	private InetAddress ip;
	private int port = 3456;
	
	public void go() throws IOException {
		ip = InetAddress.getByName("127.0.0.1"); // localhost
		s = new Socket(ip,port); // tenta la connessione al server
		try {
			Scanner input = new Scanner(s.getInputStream());
			OutputStream o = s.getOutputStream();
			PrintWriter output = new PrintWriter(o,true);
			String dato = input.next();	// legge un dato
			// elabora il dato
			System.out.println(dato);
			System.out.println("Premi enter per mandare la ricevuta");
			new Scanner(System.in).next();
			output.println("ricevuto!"); // manda un dato
		} finally {	// chiude la connessione ed esce
			s.close();
		}
	}
	
	public static void main(String[] args) {
		SimpleClient c = new SimpleClient();
		try {
			c.go();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
