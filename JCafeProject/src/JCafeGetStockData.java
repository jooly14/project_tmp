import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
//JCafeDeadLineList에서 사용할 데이터를 가져오는 기능을 가진 클래스
public class JCafeGetStockData extends JFrame {
	//useTotal inventoryArray 두 배열 배열인덱스를 같이 써도 됨. 길이랑 배열순서가 같음
	int[] useTotal; //재료명 당 사용량을 가져옴	//오늘의 음료 누적 판매량을 바탕으로 계산 
	String[][] inventoryArray = new String[3][];	//인벤토리 파일에 있는 재료의 이름 갯수 단위
	public JCafeGetStockData() {
		stockManage();
		
		//혹시 오류나면 확인용 코드입니다.
	/*	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel pnl = new JPanel();
		pnl.setLayout(new GridLayout(0, 5));
		for (int i = 0; i < useTotal.length; i++) {
			pnl.add(new JLabel(inventoryArray[0][i]));
			pnl.add(new JLabel());
			pnl.add(new JLabel(inventoryArray[1][i]+inventoryArray[2][i]));
			pnl.add(new JLabel());
			pnl.add(new JLabel(useTotal[i] + ""));
		}
		add(pnl);
		pack();
		setVisible(true);*/
	}
	
	void stockManage() {
		FileReader fr = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		FileWriter fw = null;

		String stockNameStr = "";	//인벤토리에 저장된 재료명
		String stockYesterDayStr = "";	//인벤토리에 저장된 전날 재고
		String stockNameUnit = "";	//인벤토리에 저장된 재료의 단위
		// 재고 품목 가져오기 // 어떤 재고를 가지고 있는지 찾아온다
		try {
			fr = new FileReader("JCafeData/inventory");
			br = new BufferedReader(fr);
			String readData = null;

			while ((readData = br.readLine()) != null) {
				String[] inventoryData = readData.split("/");
				//인벤토리에 총 몇 줄이 저장되어있는지 알수 없어서 한 줄에 저장한 다음 split을 사용할 예정
				//split을 사용할 때는 배열의 길이를 설정하지 않아도 되는 점을 이용
				stockNameStr = stockNameStr + inventoryData[0] + "/"; 
				stockYesterDayStr = stockYesterDayStr + inventoryData[1] + "/"; 
				stockNameUnit = stockNameUnit + inventoryData[2] + "/"; 
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {try {
				if (br != null) {br.close();}
				if (fr != null) {fr.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//한 줄로 받아온 데이터를 나눠서 배열에 담았음
		inventoryArray[0] = stockNameStr.split("/");
		inventoryArray[1] = stockYesterDayStr.split("/");
		inventoryArray[2] = stockNameUnit.split("/");
		
		
		// 재고 품목을 배열에 넣었다. 그에 상응하는 사용량 배열을 생성
		// 인덱스를 같이 사용할 예정
		useTotal = new int[inventoryArray[0].length];	// 총 사용된 재료양을 담을 예정
		
		Date date = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		String dt = transFormat.format(date);
		
		//판매된 음료 총 량 대비 사용된 재료양 계산하는 메서드
		getUsingMaterialTotal("./JCafeData/SaleData/SellMenuData/"+dt+"/coffee","./JCafeData/InitData/InitMaterialData/coffee_material");
		getUsingMaterialTotal("./JCafeData/SaleData/SellMenuData/"+dt+"/latte","./JCafeData/InitData/InitMaterialData/latte_material");
		getUsingMaterialTotal("./JCafeData/SaleData/SellMenuData/"+dt+"/tea","./JCafeData/InitData/InitMaterialData/tea_material");
					//매개변수 : 오늘의 판매자료(초기화자료에 판매량 반영된 자료),음료당 사용 재료 저장파일

		
	}
	//판매된 음료 총 량 대비 사용된 재료양 계산하는 메서드
	void getUsingMaterialTotal(String fileName1, String fileName2){
		String drinkNameStr = ""; // 팔린 음료 이름 확인
		String drinkQuantity = "";	//판매된 음료 개수
		
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(fileName1);
			br = new BufferedReader(fr);
			String readData = null;
			while ((readData = br.readLine()) != null) {
				String[] splitdata = readData.split("/");
				// 결제내역에 포함된 메뉴의 인덱스만 가져오기
				//파일에  몇 줄로 저장되어 있는 지 알 수 없으니 한 줄로 받아오고 split을 이용해서 배열에 담을 예정
				if (!(splitdata[2].equals("0"))) { // 1개이상 판매된 메뉴만 가져옴
					drinkNameStr = drinkNameStr + splitdata[0] + "/"; // 몇번째 열에 있는 메뉴인지 가져옴
					drinkQuantity = drinkQuantity + splitdata[2] + "/"; // 팔린 개수를 가져옴
				}
				
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {br.close();}
				if (fr != null) {fr.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//한줄에 받아온 데이터를 split으로 배열에 넣음
		String[] drinkQuantityArray = drinkQuantity.split("/");
		String[] drinkNameArray = drinkNameStr.split("/");	
		//음료 판매량을 담고 있는 String배열을 int배열로 변환
		int[] drinkQuantityArrayInt = new int[drinkQuantityArray.length];
		if(!(drinkQuantityArray[0].equals(""))){
			for (int i = 0; i < drinkQuantityArray.length; i++) {
				drinkQuantityArrayInt[i] = Integer.parseInt(drinkQuantityArray[i]);
			}
		}
		
		//음료명을 이용해서  재료 사용량을 가져온다.
		if(!(drinkNameArray[0].equals(""))){
			try {
				fr = new FileReader(fileName2);
				br = new BufferedReader(fr);
				String readData = null;
				for (int i = 0; i < drinkNameArray.length; i++) {
					while ((readData = br.readLine()) != null) {	
						//System.out.println(drinkNameArray[i]);
						String[] useMaterial = readData.split("/"); 
						if (useMaterial[0].equals(drinkNameArray[i])) {	//음료 재료 소비량을 줄 단위로 읽어오며 판매된 음료만 재료 사용량을 저장한다.
							// useMaterial은 메뉴 당 사용되는 재료들의 목록 //음료명/재료명/사용량/재료명/사용량/...
							//메뉴 당 사용되는 재료들은 원두만 사용하는 거도 있고 원두랑 우유를 소비하는 거도 있고 해서 저장된 길이가 일정하지 않음
							//그래서 useMaterial 길이 만큼 반복문을 돌리면서 inventory에 저장된 재료명 순서대로
							//사용된 양을 useTotal에 누적시킴
							for (int k = 0; k < inventoryArray[0].length; k++) {
								for (int j = 1; j < useMaterial.length; j += 2) {
									//System.out.print(drinkNameArray[i]);
									// 재고를 각각 몇개 사용했는지 누적
									//인벤토리 순서대로 재료 소비량을 useTotal에 누적
									if (useMaterial[j].equals(inventoryArray[0][k])) {	
										useTotal[k] = useTotal[k]
												+ Integer.parseInt(useMaterial[j + 1]) * drinkQuantityArrayInt[i]; 
									}
								}
							}
							break;
						}
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null) {br.close();}
					if (fr != null) {fr.close();}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		
	}

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JCafeGetStockData();
	}*/

}
