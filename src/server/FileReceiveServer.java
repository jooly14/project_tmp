package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileReceiveServer extends Thread{
	boolean start = false;
	ServerSocket serverSocket;
	Socket clientSocket;
	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(4445);
			clientSocket = serverSocket.accept();
			start = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}


	public Socket getClientSocket() {
		return clientSocket;
	}


	public boolean getStart() {
		return start;
	}
}
