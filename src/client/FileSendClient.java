package client;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileSendClient extends Thread{
	ChatClient chatClient;
	String[] split;
	public FileSendClient(ChatClient chatClient,String[] split) {
		this.chatClient = chatClient;
		this.split =split;
	}
	@Override
	public void run() {
		BufferedInputStream bis =null;
		FileInputStream fis = null;
		BufferedOutputStream bos = null;
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 4444);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bos = new BufferedOutputStream(socket.getOutputStream());
			fis = new FileInputStream(split[4]);
			bis = new BufferedInputStream(fis);
			
			int data;
			while ((data = bis.read())!=-1) {
				bos.write(data);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(bos!=null){
					bos.close();
				}
				if(bis!=null){
					bis.close();
				}
				if(fis!= null){
					fis.close();
				}
				if(socket!=null){
					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		chatClient.completeSend(split.clone());

	}
}