import javax.swing.JButton;

public class TetrisRotate {
	TetrisMain main;
	int currentBlock;
	int rotateNum;
	JButton[] btnNew;
	public TetrisRotate(TetrisMain main) {
		this.main = main;
	}
	
	void rotate0(int rotateNum,JButton[] btnNew){
		switch (rotateNum) {
		case 0:
			//������ �ε����� �� �ִ��� Ȯ��
			if(btnNew[1].getX()==0){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
			}else if(btnNew[1].getX()==20){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
			}else if(btnNew[1].getX()==20*14){
				btnNew[1].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
			}
			break;
		case 1:
			//�ٴ��� �ε����� �� Ȯ��
			if(btnNew[1].getY()==580){
				btnNew[1].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
			}else if(btnNew[1].getY()==560){
				btnNew[1].setLocation(btnNew[1].getX(),btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[1].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
			}
			
			break;
		case 2:
			//������ �ε����� �� �ִ��� Ȯ��
			if(btnNew[1].getX()==20*14){
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
				
			}else if(btnNew[1].getX()==20*13){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
			}else if(btnNew[1].getX()==0){
				btnNew[1].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
			}
			break;
		case 3:
			if(btnNew[1].getY()==580){
				btnNew[1].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
			}else{
				btnNew[1].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
			}
			
			break;
		}
	}
	void rotate1(int rotateNum,JButton[] btnNew){
		switch (rotateNum) {
		case 0:
			//������ �ε����� �� �ִ��� Ȯ��
			if(btnNew[1].getX()==0){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[0].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+20, btnNew[1].getY()-20);
			}
			break;
		case 1:
			//�ٴ��� �ε����� �� Ȯ��
			if(btnNew[1].getY()==580){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
			}
			
			break;
		case 2:
			//������ �ε����� �� �ִ��� Ȯ��
			if(btnNew[1].getX()==20*14){
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
			}
			break;
		case 3:
			btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
			btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
			btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
			break;
		}
	}
	
	void rotate2(int rotateNum,JButton[] btnNew){
		switch (rotateNum) {
		case 0:
			//������ �ε����� �� �ִ��� Ȯ��
			if(btnNew[1].getX()==20*14){
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
			}
			break;
		case 1:
			btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
			btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
			btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
			break;
		case 2:
			//������ �ε����� �� �ִ��� Ȯ��
			if(btnNew[1].getX()==0){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
			}
			break;
		case 3:
			//�ٴ��� �ε����� �� Ȯ��
			if(btnNew[1].getY()==580){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
			}
			
			break;
		}
	}
	void rotate3(int rotateNum,JButton[] btnNew){
		switch (rotateNum) {
		case 0:
			//������ �ε����� �� �ִ��� Ȯ��
			if(btnNew[1].getX()==0){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[0].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
			}
			break;
		case 1:
			//�ٴ��� �ε����� �� Ȯ��
			if(btnNew[1].getY()==580){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
			}
			
			break;
		case 2:
			//������ �ε����� �� �ִ��� Ȯ��
			if(btnNew[1].getX()==20*14){
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
			}
			break;
		case 3:
			btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
			btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
			btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
			break;
		}
		
	}
	void rotate4(int rotateNum,JButton[] btnNew){
		switch (rotateNum) {
		case 0:
			//������ �ε����� �� �ִ��� Ȯ��
			if(btnNew[1].getX()==0){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
			}
			break;
		case 1:
			//�ٴ��� �ε����� �� Ȯ��
			if(btnNew[1].getY()==580){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
			}
			
			break;
		case 2:
			//������ �ε����� �� �ִ��� Ȯ��
			if(btnNew[1].getX()==20*14){
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
			}
			break;
		case 3:
			btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
			btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
			btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
			break;
		}
		
	}
	void rotate5(int rotateNum,JButton[] btnNew){
		switch (rotateNum) {
		case 0:
			//������ �ε����� �� �ִ��� Ȯ��
			if(btnNew[1].getX()==20*14){
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
			}
			break;
		case 1:
			btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
			btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
			btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
			break;
		case 2:
			//������ �ε����� �� �ִ��� Ȯ��
			if(btnNew[1].getX()==0){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
			}
			break;
		case 3:
			//�ٴ��� �ε����� �� Ȯ��
			if(btnNew[1].getY()==580){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
			}
			
			break;
		}
	}
}
