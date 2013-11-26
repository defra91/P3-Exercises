import java.net.*;
import java.io.*;	
import java.util.*;

public class SocketTCP {
	public static void main(String[] args) {
		int port = 1234;
		ServerSocket servSock = new ServerSocket(port);
		Socket link = servSock.accept();
		Scanner input = new Scanner(link.getInputStream());
		PrintWriter output = new PrintWriter(link.getOutputStream(),true);
		output.println("In attesa di dati...");
		String in = input.nextLine();
		link.close();
	}
}
