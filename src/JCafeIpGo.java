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
	static boolean chk;	//���� ������ ������ ����� ���� �ʱ�ȭ���� �ʴ´�.
	public static void ipgo(){
		//���� ������ ������ �԰���� ���� ��� �԰� ����ȴ�.
		if(!chk){
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -1);
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
			String dt = transFormat.format(cal.getTime());
			File dir = new File("JCafeData/OrderRegistration/"+dt);
			if (dir.exists()) {
				ipgoing(dir);	//���� ���������� �����ϴ� ��쿡�� �԰� �����Ѵ�.
			}
			chk = true;	//�԰� ����ǰ� ������ üũ ������ true�� �ȴ�.	//�԰� �ݺ�������� �ʵ��� �Ѵ�.
		}
	}
	static void ipgoing(File file){
		FileReader fr=null;
		BufferedReader br = null;
		String readDataSplit = "";
		//���� ���� ������ �о�´�.
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
		//����/���ַ�/���� ������ ����Ǿ�����
		for(int i=0;i<orderDataSplit.length;i++){
			String[] str = orderDataSplit[i].split("/");
			orderData[0][i] = str[0];
			orderData[1][i] = str[1];
			orderData[2][i] = str[2];
		}
		//�԰�
		changeInventory(orderData);
	}
	static void changeInventory(String[][] orderData) {
		JCafeGetStockData gsd = new JCafeGetStockData();	//�κ��丮 ���� �о���� Ŭ���� Ȱ��
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter("JCafeData/inventory");		//�κ��丮�� �ݿ���Ű�� ���ؼ� FileWriter����
			pw = new PrintWriter(fw);
			for(int j=0;j<orderData[0].length;j++){	//���� ������ ���� ���� ��ŭ �ݺ�
				int newChk = 0;
				for(int i=0;i<gsd.inventoryArray[0].length;i++){	//�κ��丮�� �ִ� ����� ���� �� ��ŭ �ݺ�
					if(gsd.inventoryArray[0][i].equals(orderData[0][j])){	//������ ���� �κ��丮�� �ִ� ����� �̸��� ���ٸ�
						int bigunit = Integer.parseInt(orderData[1][j]);	//ū������ ����� ���ַ���
						if (orderData[2][j].equals("L")) {					//���� ������ ��ȯ
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
					int unit = Integer.parseInt(orderData[1][j]);
					System.out.println(unit);
					if (orderData[2][j].equals("L")) {					//���� ������ ��ȯ
						orderData[2][j] = "ml";
						unit = unit *1000;
					} else if (orderData[2][j].equals("Kg")) {
						orderData[2][j] = "g";
						unit = unit *1000;
					} else if (orderData[2][j].equals("Box")) {
						orderData[2][j] = "ea";
						unit = unit *24;
					} 
					orderData[1][j] =  unit+"";
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
