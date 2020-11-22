import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.SocketTimeoutException;

public class JCafeGetMenuToMakeButton {
	
	static String[][] getInfo(String fileName) {
		String[][] strMenu2 = null;
		String saveData0 = "";
		String saveData1 = "";
		
		FileReader fr = null;
		BufferedReader br = null;
		int idx = 0;
		try {
			fr = new FileReader("JCafeData/InitData/InitMenuData/"+fileName);
			br = new BufferedReader(fr);
			String readData = null;
			while ((readData = br.readLine()) != null) {
				String[] splitdata = readData.split("/");
					saveData0 = saveData0 + splitdata[0] + "/";
					saveData1 = saveData1 + splitdata[1] + "/";
					
				idx++;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (fr != null) {
					fr.close();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		strMenu2 = new String[idx][3];
		String[][] strMenu3 = new String[3][idx];
		strMenu3[0] = saveData0.split("/");
		strMenu3[2] = saveData1.split("/");
		for(int i=0;i<idx;i++){
			strMenu3[1][i] = "1";
		}
		for(int i=0;i<idx;i++){
			for(int j=0;j<3;j++){
				strMenu2[i][j] = strMenu3[j][i];
			}
		}
		return strMenu2;
	}
	static void setData(String[][] arr1,String[] arr2,String[] arr3,String[] arr4){
		for(int i=0;i<arr1.length;i++){
			arr2[i] = arr1[i][0];
			arr3[i] = arr1[i][1];
			arr4[i] = arr1[i][2];
		}
	}
}
