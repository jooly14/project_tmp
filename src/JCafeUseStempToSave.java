import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class JCafeUseStempToSave {
	public JCafeUseStempToSave(String phoneNum,int stempCnt) {
		
		FileReader fr=null;
		BufferedReader br=null;
		FileWriter fw=null;
		PrintWriter pw=null;
		try {
			fr=new FileReader(new File("JCafeData\\StempData"));
			br=new BufferedReader(fr);
			
			String[]data=null;
			String l=null;
			String save="";
			int cnt=0;
			while((l=br.readLine())!=null){
				save=save+l+"!";
				cnt++;
			}
			data=save.split("!");//여기서 a 배열이 파일 내용을 가지고있음
			
			fw=new FileWriter(new File("JCafeData\\StempData"));//여기서 파일 데이터가 초기화됨
			pw=new PrintWriter(fw);
			
			boolean haveOrNot=true; // 신규사용자인지 원래사용자인지 판별
			
			for(int i=0;i<data.length;i++){
				String[] str=data[i].split("/"); // 배열에 한줄의 번호,스템프갯수 받아옴
				if(str[0].equals(phoneNum)){ // 파일의 데이터의 번호가 입력한 전화번호와 같다면
					pw.println(phoneNum+"/"+stempCnt); // 입력한번호/사용 후 스템프  입력
					haveOrNot=false;//신규사용자인지를 false로 바꾼다.
				}else//입력한 번호가 아니라면
					pw.println(data[i]);// 원래의 데이터를 다시 입력
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}try{
			if(fw!=null) fw.close();
			if(fr!=null) fr.close();
			if(br!=null) br.close();
			if(pw!=null) pw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
// 스템프 사용화면에서 사용 후의 스템프정보를 저장하는 클래스