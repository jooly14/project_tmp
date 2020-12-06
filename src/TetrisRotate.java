import javax.swing.JButton;

public class TetrisRotate {
	GamePlayPanel main;
	int currentBlock;
	int rotateNum;
	JButton[] btnNew;
	public TetrisRotate(GamePlayPanel main) {
		this.main = main;
	}
	
	void rotate0(int rotateNum,JButton[] btnNew,JButton[][] stackedBlock){
		switch (rotateNum) {
		case 0:
			//���ʺ��� �ε����� �� �ִ��� Ȯ��
			if(btnNew[1].getX()==0){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
						main.rotateNum=3;
					}
				}
			//���ʺ��� �ε����� �� �ִ��� Ȯ��
			}else if(btnNew[1].getX()==20){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
						main.rotateNum=3;
					}
				}
			//������ ���� �ε��� ��
			}else if(btnNew[1].getX()==20*14){
				btnNew[1].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-40, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-40, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-40, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
						main.rotateNum=3;
					}
				}
				//���ʺ��� �ε����� �� �ִ��� Ȯ��
			}else if(stackedBlock[btnNew[1].getX()/20-1][btnNew[1].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(btnNew[3].getX()>14*20){
					btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
				}else if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getY()>580||btnNew[3].getX()>14*20||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getY()>580||btnNew[3].getX()>14*20||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getY()>580||btnNew[3].getX()>14*20||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
						main.rotateNum=3;
					}
				}
			//���ʺ��� �ε����� �� �ִ��� Ȯ��
			}else if(stackedBlock[btnNew[1].getX()/20-2][btnNew[1].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(btnNew[3].getX()>14*20){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
				}else if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//���󺹱�
					if(btnNew[3].getX()>14*20||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getY()>580||btnNew[3].getX()>14*20||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getY()>580||btnNew[3].getX()>14*20||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
						main.rotateNum=3;
					}
				}
			//������ ���� �ε��� ��
			}else if(stackedBlock[btnNew[1].getX()/20+1][btnNew[1].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(btnNew[0].getX()<0){
					btnNew[1].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
				}else if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//���󺹱�
					if(btnNew[0].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-40, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//���󺹱�
					if(btnNew[0].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-40, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//���󺹱�
					if(btnNew[0].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-40, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
						main.rotateNum=3;
					}
				}
			//�ƹ��͵� �ε����� �� ���� ���
			}else{
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
						main.rotateNum=3;
					}
				}
			}
			break;
		case 1:
			//�ٴ��� �ε����� �� Ȯ��
			if(btnNew[1].getY()==580){
				btnNew[1].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					//���󺹱�
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					main.rotateNum=0;
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
					//���󺹱�
					if(btnNew[1].getX()>14*20||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
						main.rotateNum=0;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
					//���󺹱�
					if(btnNew[1].getX()>14*20||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
						main.rotateNum=0;
					}
				}
			//�ٴڿ� ��ĭ �����Ҷ�
			}else if(btnNew[1].getY()==560){
				btnNew[1].setLocation(btnNew[1].getX(),btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
					//���󺹱�
					if(btnNew[1].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
						btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
						main.rotateNum=0;
					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
					//���󺹱�
					if(btnNew[1].getX()>14*20||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
						btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
						main.rotateNum=0;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
					//���󺹱�
					if(btnNew[1].getX()>14*20||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
						btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
						main.rotateNum=0;
					}
				}
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[1].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
					//���󺹱�
					if(btnNew[1].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
						main.rotateNum=0;
					}
				}else if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
					//���󺹱�
					if(btnNew[1].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
						main.rotateNum=0;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
					//���󺹱�
					if(btnNew[1].getX()>14*20||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
						main.rotateNum=0;
					}
				}
			}
			
			break;
		case 2:
			//������ ���� �ε����� �� �ִ��� Ȯ��
			if(btnNew[1].getX()==20*14){
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					//���󺹱�
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
						main.rotateNum=1;
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
						main.rotateNum=1;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
						main.rotateNum=1;
					}
				}
			//������ ��ĭ ���� �ε����� �� �ִ��� Ȯ��
			}else if(btnNew[1].getX()==20*13){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
						main.rotateNum=1;
					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
						main.rotateNum=1;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
						main.rotateNum=1;
					}
				}
			//���� ���� �ε����� �� Ȯ��
			}else if(btnNew[1].getX()==0){
				btnNew[1].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-40, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
						main.rotateNum=1;
					}
				}else if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-40, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
						main.rotateNum=1;
					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-40, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
						main.rotateNum=1;
					}
				}
				//������ ���� �ε����� �� �ִ��� Ȯ��
			}else if(stackedBlock[btnNew[1].getX()/20+1][btnNew[1].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(btnNew[3].getX()<0){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
					main.rotateNum=1;
				}else if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					//���󺹱�
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
						main.rotateNum=1;
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//���󺹱�
					if(btnNew[3].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
						main.rotateNum=1;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//���󺹱�
					if(btnNew[3].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
						main.rotateNum=1;
					}
				}
			//������ ��ĭ ���� �ε����� �� �ִ��� Ȯ��
			}else if(stackedBlock[btnNew[1].getX()/20+2][btnNew[1].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(btnNew[3].getX()<0){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
					main.rotateNum=1;
				}else if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getY()>580||btnNew[3].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
						main.rotateNum=1;
					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//���󺹱�
					if(btnNew[3].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
						main.rotateNum=1;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//���󺹱�
					if(btnNew[3].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
						main.rotateNum=1;
					}
				}
			//���� ���� �ε����� �� Ȯ��
			}else if(stackedBlock[btnNew[1].getX()/20-1][btnNew[1].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(btnNew[0].getX()>20*14){
					btnNew[1].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
					main.rotateNum=1;
				}else if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getY()>580||btnNew[0].getX()>20*14||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-40, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
						main.rotateNum=1;
					}
				}else if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getY()>580||btnNew[0].getX()>20*14||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-40, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
						main.rotateNum=1;
					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getY()>580||btnNew[0].getX()>20*14||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-40, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
						main.rotateNum=1;
					}
				}
			//�ƹ��͵� �ε����� �� ���� ���
			}else{
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
						main.rotateNum=1;
					}
				}else if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
						main.rotateNum=1;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
						main.rotateNum=1;
					}
				}
			}
			break;
		case 3:
			//�ٴڿ� �ε����� ���
			if(btnNew[1].getY()==580){
				btnNew[1].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
					//���󺹱�
					if(btnNew[1].getX()>20*14||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()+40);
						btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
						main.rotateNum=2;
					}
				}else if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
					//���󺹱�
					if(btnNew[1].getX()>20*14||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()+40);
						btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
						main.rotateNum=2;
					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					//���󺹱�
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+40);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					main.rotateNum=2;
				}
			}else{
				btnNew[1].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
					//���󺹱�
					if(btnNew[1].getX()>20*14||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
						main.rotateNum=2;
					}
				}else if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
					//���󺹱�
					if(btnNew[1].getX()>20*14||btnNew[3].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
						btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
						main.rotateNum=2;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
					//���󺹱�
					if(btnNew[1].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
						main.rotateNum=2;
					}
				}
			}
			
			break;
		}
	}
	void rotate1(int rotateNum,JButton[] btnNew,JButton[][] stackedBlock){
		switch (rotateNum) {
		case 0:
			//1.������ �ε����� �� �ִ��� Ȯ��
			if(btnNew[1].getX()==0){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+40);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-40);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
						main.rotateNum=3;
					}
				}
			//2.���� �ε����� ����� �ִ��� Ȯ��
			}else if(stackedBlock[btnNew[1].getX()/20-1][btnNew[1].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+40);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-40);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
						main.rotateNum=3;
					}
				}
			//3.�ƹ��͵� �ε����� �� ���� ���
			}else{
				btnNew[0].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+20, btnNew[1].getY()-20);
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+40);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-40);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
						main.rotateNum=3;
					}
				}
			}
			break;
		case 1:
			//1.�ٴ��� �ε����� �� Ȯ��
			if(btnNew[1].getY()==580){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
				if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
					//���󺹱�
					if(btnNew[1].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
						main.rotateNum = 0;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
					if(btnNew[1].getX()>14*20||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
						main.rotateNum = 0;

					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
					if(btnNew[1].getX()>14*20||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-40, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
						main.rotateNum = 0;

					}
				}
			//2.�� �ε����� ��� Ȯ��
			}else{
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
				
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
					if(btnNew[1].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
						btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
						main.rotateNum = 0;

					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
					if(btnNew[1].getX()>14*20||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
						btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
						main.rotateNum = 0;

					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
					if(btnNew[1].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
						btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
						main.rotateNum = 0;

					}
				}
			}
			
			break;
		case 2:
			//1.������ �ε����� �� �ִ��� Ȯ��
			if(btnNew[1].getX()==20*14){
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
				if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
						main.rotateNum = 1;

					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
						main.rotateNum = 1;

					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-40);
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+40);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
						main.rotateNum = 1;

					}
				}
			//2.���� �ε����� ����� ���� ��
			}else if(stackedBlock[btnNew[1].getX()/20+1][btnNew[1].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
				if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
					//���󺹱�
					if(btnNew[2].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
						main.rotateNum = 1;

					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
					//���󺹱�
					if(btnNew[2].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
						main.rotateNum = 1;

					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-40);
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
					//���󺹱�
					if(btnNew[2].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+40);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
						main.rotateNum = 1;

					}
				}
			//3.�ƹ��͵� �ε����� �� ���� ���
			}else{
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
					//���󺹱�
					if(btnNew[2].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
						main.rotateNum = 1;

					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
					//���󺹱�
					if(btnNew[2].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
						main.rotateNum = 1;

					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-40);
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
					//���󺹱�
					if(btnNew[2].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+40);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
						main.rotateNum = 1;

					}
				}
			}
			break;
		case 3:
			btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
			btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
			btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
			if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
				//���󺹱�
				if(btnNew[2].getX()>14*20||
				stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
				stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
				stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
				stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
					main.rotateNum = 2;

				}
			}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
				//���󺹱�
				if(btnNew[2].getX()<0||
				stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
				stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
				stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
				stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
					main.rotateNum = 2;
				}
			}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
				//���󺹱�
				if(btnNew[2].getX()<0||
				stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
				stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
				stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
				stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
					main.rotateNum = 2;
				}
			}
			break;
		}
	}
	void rotate2(int rotateNum,JButton[] btnNew,JButton[][] stackedBlock){
		switch (rotateNum) {
		case 0:
			//1.������ �ε����� �� �ִ��� Ȯ��
			if(btnNew[1].getX()==20*14){
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
						main.rotateNum=3;
					}
				}
			//2.�� ��Ͽ� �ε����� �� Ȯ��
			}else if(stackedBlock[btnNew[1].getX()/20+1][btnNew[1].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
					//���󺹱�
					if(btnNew[0].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
					//���󺹱�
					if(btnNew[0].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
						main.rotateNum=3;
					}
				}
			//3.�ƹ��͵� �ε����� �� ���� ���
			}else{
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
					//���󺹱�
					if(btnNew[3].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+40);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
					//���󺹱�
					if(btnNew[3].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-40);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
						main.rotateNum=3;
					}
				}
			}
			break;
		case 1:
			btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
			btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
			btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
			if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
				//���󺹱�
				if(btnNew[1].getX()<0||
				stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
				stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
				stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
				stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
					main.rotateNum=0;
				}
			}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
				//���󺹱�
				if(btnNew[3].getX()>20*14||
				stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
				stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
				stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
				stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
					main.rotateNum=0;
				}
			}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
				//���󺹱�
				if(btnNew[0].getY()>580||
				stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
				stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
				stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
				stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
					main.rotateNum=0;
				}
			}
			break;
		case 2:
			//������ �ε����� �� �ִ��� Ȯ��
			if(btnNew[1].getX()==0){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
						main.rotateNum=1;
					}
				}else if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
						main.rotateNum=1;
					}
				}
			}else if(stackedBlock[btnNew[1].getX()/20-1][btnNew[1].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
					//���󺹱�
					if(btnNew[1].getY()>580||btnNew[0].getX()>20*14||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
						main.rotateNum=1;
					}
				}else if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
					//���󺹱�
					if(btnNew[1].getY()>580||btnNew[0].getX()>20*14||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
						main.rotateNum=1;
					}
				}
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
						main.rotateNum=1;
					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
						main.rotateNum=1;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
					//���󺹱�
					if(btnNew[0].getX()>20*14||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
						main.rotateNum=1;
					}
				}
			}
			break;
			/////////////////////////////////////////
		case 3:
			//�ٴ��� �ε����� �� Ȯ��
			if(btnNew[1].getY()==580){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
						main.rotateNum=2;
					}
				}else if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					//���󺹱�
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
					main.rotateNum=2;
				}
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
						btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
						main.rotateNum=2;
					}
				}else if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
					//���󺹱�
					if(btnNew[3].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
						btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
						main.rotateNum=2;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
						main.rotateNum=2;
					}
				}
			}
			
			break;
		}
	}
	void rotate3(int rotateNum,JButton[] btnNew,JButton[][] stackedBlock){
		switch (rotateNum) {
		case 0:
			//������ �ε����� �� �ִ��� Ȯ��
			if(btnNew[1].getX()==0){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					//���󺹱�
					if(btnNew[1].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						main.rotateNum=3;
					}
				}
			}else if(stackedBlock[btnNew[1].getX()/20-1][btnNew[1].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					//���󺹱�
					if(btnNew[1].getY()>580||btnNew[2].getX()>20*14||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						main.rotateNum=3;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					//���󺹱�
					if(btnNew[1].getY()>580||btnNew[2].getX()>20*14||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						main.rotateNum=3;
					}
				}
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[0].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						main.rotateNum=3;
					}
				}
			}
			break;
		case 1:
			//�ٴ��� �ε����� �� Ȯ��
			if(btnNew[1].getY()==580){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getX()>20*14||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						main.rotateNum=0;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					//���󺹱�
					if(btnNew[1].getX()>20*14||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						main.rotateNum=0;
					}
				}
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				//�ٸ� ����� �� ��ġ�� �ִ� ��쿡�� �׸�ŭ ��ġ�� �ű�� �̵��ߴ� ���� �� �Ǹ� rotate���
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					//���󺹱�
					if(btnNew[3].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
						btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
						btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						main.rotateNum=0;
					}
				}
			}
			
			break;
		case 2:
			//������ �ε����� �� �ִ��� Ȯ��
			if(btnNew[1].getX()==20*14){
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						main.rotateNum=1;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					//���󺹱�
					if(
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						main.rotateNum=1;
					}
				}
			}else if(stackedBlock[btnNew[1].getX()/20+1][btnNew[1].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					//���󺹱�
					if(btnNew[2].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						main.rotateNum=1;
					}
				}else if(stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					//���󺹱�
					if(btnNew[2].getX()<0||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY()+20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						main.rotateNum=1;
					}
				}
			}else{
				//�ƹ��͵� �ε����� �� ���� ���
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					//���󺹱�
					if(btnNew[3].getY()>580||
					stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
					stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
					stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
					stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
						btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
						btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
						btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
						btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
						main.rotateNum=1;
					}
				}
			}
			break;
		case 3:
			btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
			btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
			btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
			if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				//���󺹱�
				if(btnNew[3].getX()>20*14||
				stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null||
				stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null||
				stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null||
				stackedBlock[btnNew[3].getX()/20][btnNew[3].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					main.rotateNum=2;
				}
			}
			break;
		}
		
	}
	void rotate4(int rotateNum,JButton[] btnNew,JButton[][] stackedBlock){
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
	void rotate5(int rotateNum,JButton[] btnNew,JButton[][] stackedBlock){
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
