import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JCafeIpGo {
	static boolean chk;	//정적 변수라서 메인이 실행된 이후 초기화되지 않는다.
	public static void ipgo(){
		//전날 발주한 물량이 입고되지 않은 경우 입고가 진행된다.
		if(!chk){
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -1);
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
			String dt = transFormat.format(cal.getTime());
			File dir = new File("JCafeData/OrderRegistration/"+dt);
			if (dir.exists()) {
				ipgoing(dir);	//전날 발주파일이 존재하는 경우에만 입고를 진행한다.
			}
			chk = true;	//입고가 진행되고 나서는 체크 변수가 true가 된다.	//입고가 반복진행되지 않도록 한다.
		}
	}
	static void ipgoing(File file){
		FileReader fr=null;
		BufferedReader br = null;
		String readDataSplit = "";
		//전날 발주 파일을 읽어온다.
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String readData = null;
			while((readData = br.readLine())!=null){
				readDataSplit = readDataSplit + readData + "!";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (br != null) {br.close();}
				if (fr != null) {fr.close();}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String[] orderDataSplit = readDataSplit.split("!");
		String[][] orderData = new String[3][orderDataSplit.length];
		//재료명/발주량/단위 순서로 저장되어있음
		for(int i=0;i<orderDataSplit.length;i++){
			String[] str = orderDataSplit[i].split("/");
			orderData[0][i] = str[0];
			orderData[1][i] = str[1];
			orderData[2][i] = str[2];
		}
		//입고
		changeInventory(orderData);
	}
	static void changeInventory(String[][] orderData) {
		JCafeGetStockData gsd = new JCafeGetStockData();	//인벤토리 파일 읽어오는 클래스 활용
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter("JCafeData/inventory");		//인벤토리에 반영시키기 위해서 FileWriter생성
			pw = new PrintWriter(fw);
			for(int j=0;j<orderData[0].length;j++){	//발주 파일의 행의 길이 만큼 반복
				int newChk = 0;
				for(int i=0;i<gsd.inventoryArray[0].length;i++){	//인벤토리에 있는 재료의 종류 수 만큼 반복
					if(gsd.inventoryArray[0][i].equals(orderData[0][j])){	//발주한 재료와 인벤토리에 있는 재료의 이름이 같다면
						int bigunit = Integer.parseInt(orderData[1][j]);	//큰단위로 저장된 발주량을
						if (orderData[2][j].equals("L")) {					//작은 단위로 변환
							orderData[2][j] = "ml";
							bigunit = bigunit *1000;
						} else if (orderData[2][j].equals("Kg")) {
							orderData[2][j] = "g";
							bigunit = bigunit *1000;
						} else if (orderData[2][j].equals("Box")) {
							orderData[2][j] = "ea";
							bigunit = bigunit *24;
						} 
						orderData[1][j] =  (Integer.parseInt(gsd.inventoryArray[1][i])+bigunit)+"";
						newChk = 1;
						break;	
					}
				}
				if(newChk==0){
					int bigunit = Integer.parseInt(orderData[1][j]);
					if (orderData[2][j].equals("L")) {					//작은 단위로 변환
						orderData[2][j] = "ml";
						bigunit = bigunit *1000;
					} else if (orderData[2][j].equals("Kg")) {
						orderData[2][j] = "g";
						bigunit = bigunit *1000;
					} else if (orderData[2][j].equals("Box")) {
						orderData[2][j] = "ea";
						bigunit = bigunit *24;
					} 
					orderData[1][j] =  bigunit+"";
				}
				pw.println(orderData[0][j]+"/"+orderData[1][j]+"/"+orderData[2][j]);
			}
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (pw != null) {pw.close();}
				if (fw != null) {fw.close();}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*public static void main(String[] args) {
		JCafeIpGo.ipgo();
	}*/
}
