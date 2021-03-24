package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
//파일 전송받을 서버
//포트번호를 같게 하면 전송하는 사람과 전송받는 사람 구분이 어려워서 따로 생성
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
