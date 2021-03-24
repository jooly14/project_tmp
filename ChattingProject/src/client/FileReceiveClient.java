package client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
//파일을 받기 위해서 서버랑 연결
public class FileReceiveClient extends Thread{
	ChatClient chatClientPrac;
	String[] split;
	public FileReceiveClient(ChatClient chatClientPrac,String[] split) {
		this.chatClientPrac = chatClientPrac;
		this.split =split;

	}
	@Override
	public void run() {
		BufferedInputStream bis =null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 4445);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
//			if(new File(split[2]).exists()){
//				split[2] = split[2].split("[.]")[0]+ new SimpleDateFormat("yyyyMMddhhmmss").format(System.currentTimeMillis())+"."+split[2].split("[.]")[1];
//			}
			bis = new BufferedInputStream(socket.getInputStream());	//서버에서 받아와서
			fos = new FileOutputStream(split[2],false);				//내 컴퓨터에 씀
			bos = new BufferedOutputStream(fos);
			
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
				if(fos!= null){
					fos.close();
				}
				if(socket!=null){
					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		chatClientPrac.completeReceive(split.clone());	//파일 전송이 완료되었음을 알림

	}
}
