import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
//JCafeDeadLineList���� ����� �����͸� �������� ����� ���� Ŭ����
public class JCafeGetStockData extends JFrame {
	//useTotal inventoryArray �� �迭 �迭�ε����� ���� �ᵵ ��. ���̶� �迭������ ����
	int[] useTotal; //���� �� ��뷮�� ������	//������ ���� ���� �Ǹŷ��� �������� ��� 
	String[][] inventoryArray = new String[3][];	//�κ��丮 ���Ͽ� �ִ� ����� �̸� ���� ����
	public JCafeGetStockData() {
		stockManage();
		
		//Ȥ�� �������� Ȯ�ο� �ڵ��Դϴ�.
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

		String stockNameStr = "";	//�κ��丮�� ����� ����
		String stockYesterDayStr = "";	//�κ��丮�� ����� ���� ���
		String stockNameUnit = "";	//�κ��丮�� ����� ����� ����
		// ��� ǰ�� �������� // � ��� ������ �ִ��� ã�ƿ´�
		try {
			fr = new FileReader("JCafeData/inventory");
			br = new BufferedReader(fr);
			String readData = null;

			while ((readData = br.readLine()) != null) {
				String[] inventoryData = readData.split("/");
				//�κ��丮�� �� �� ���� ����Ǿ��ִ��� �˼� ��� �� �ٿ� ������ ���� split�� ����� ����
				//split�� ����� ���� �迭�� ���̸� �������� �ʾƵ� �Ǵ� ���� �̿�
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
		//�� �ٷ� �޾ƿ� �����͸� ������ �迭�� �����
		inventoryArray[0] = stockNameStr.split("/");
		inventoryArray[1] = stockYesterDayStr.split("/");
		inventoryArray[2] = stockNameUnit.split("/");
		
		
		// ��� ǰ���� �迭�� �־���. �׿� �����ϴ� ��뷮 �迭�� ����
		// �ε����� ���� ����� ����
		useTotal = new int[inventoryArray[0].length];	// �� ���� ������ ���� ����
		
		Date date = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		String dt = transFormat.format(date);
		
		//�Ǹŵ� ���� �� �� ��� ���� ���� ����ϴ� �޼���
		getUsingMaterialTotal("./JCafeData/SaleData/SellMenuData/"+dt+"/coffee","./JCafeData/InitData/InitMaterialData/coffee_material");
		getUsingMaterialTotal("./JCafeData/SaleData/SellMenuData/"+dt+"/latte","./JCafeData/InitData/InitMaterialData/latte_material");
		getUsingMaterialTotal("./JCafeData/SaleData/SellMenuData/"+dt+"/tea","./JCafeData/InitData/InitMaterialData/tea_material");
					//�Ű����� : ������ �Ǹ��ڷ�(�ʱ�ȭ�ڷῡ �Ǹŷ� �ݿ��� �ڷ�),����� ��� ��� ��������

		
	}
	//�Ǹŵ� ���� �� �� ��� ���� ���� ����ϴ� �޼���
	void getUsingMaterialTotal(String fileName1, String fileName2){
		String drinkIdx = ""; // ���° ���� �ִ� Ŀ�ǰ� �ȷȴ��� Ȯ��
		String drinkQuantity = "";	//�Ǹŵ� ���� ����
		
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(fileName1);
			br = new BufferedReader(fr);
			String readData = null;
			int idx = 0;
			while ((readData = br.readLine()) != null) {
				String[] splitdata = readData.split("/");
				// ���������� ���Ե� �޴��� �ε����� ��������
				//���Ͽ�  �� �ٷ� ����Ǿ� �ִ� �� �� �� ������ �� �ٷ� �޾ƿ��� split�� �̿��ؼ� �迭�� ���� ����
				if (!(splitdata[2].equals("0"))) { // 1���̻� �Ǹŵ� �޴��� ������
					drinkIdx = drinkIdx + idx + "/"; // ���° ���� �ִ� �޴����� ������
					drinkQuantity = drinkQuantity + splitdata[2] + "/"; // �ȸ� ������ ������
				}
				idx++;
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
		//���ٿ� �޾ƿ� �����͸� split���� �迭�� ����
		String[] drinkQuantityArray = drinkQuantity.split("/");
		String[] drinkIdxArray = drinkIdx.split("/");	
		//���� �Ǹŷ��� ��� �ִ� String�迭�� int�迭�� ��ȯ
		int[] drinkQuantityArrayInt = new int[drinkQuantityArray.length];
		if(!(drinkQuantityArray[0].equals(""))){
			for (int i = 0; i < drinkQuantityArray.length; i++) {
				drinkQuantityArrayInt[i] = Integer.parseInt(drinkQuantityArray[i]);
			}
		}
		
		//drinkIdxArray�� �̿��ؼ�  ��� ��뷮�� �����´�.
		if(!(drinkIdxArray[0].equals(""))){
			try {
				fr = new FileReader(fileName2);
				br = new BufferedReader(fr);
				String readData = null;
				int idx = 0;
				for (int i = 0; i < drinkIdxArray.length; i++) {
					while ((readData = br.readLine()) != null) {	
						if (idx == Integer.parseInt(drinkIdxArray[i])) {	//���� ��� �Һ��� �� ������ �о���� �Ǹŵ� ���Ḹ ��� ��뷮�� �����Ѵ�.
							// useMaterial�� �޴� �� ���Ǵ� ������ ��� //�����/����/��뷮/����/��뷮/...
							//�޴� �� ���Ǵ� ������ ���θ� ����ϴ� �ŵ� �ְ� ���ζ� ������ �Һ��ϴ� �ŵ� �ְ� �ؼ� ����� ���̰� �������� ����
							//�׷��� useMaterial ���� ��ŭ �ݺ����� �����鼭 inventory�� ����� ���� �������
							//���� ���� useTotal�� ������Ŵ
							String[] useMaterial = readData.split("/"); 
							for (int k = 0; k < inventoryArray[0].length; k++) {
								for (int j = 1; j < useMaterial.length; j += 2) {
									// ��� ���� � ����ߴ��� ����
									//�κ��丮 ������� ��� �Һ��� useTotal�� ����
									if (useMaterial[j].equals(inventoryArray[0][k])) {	
										useTotal[k] = useTotal[k]
												+ Integer.parseInt(useMaterial[j + 1]) * drinkQuantityArrayInt[i]; 
									}
								}
							}
							idx++;
							break;
						}
						idx++;
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
