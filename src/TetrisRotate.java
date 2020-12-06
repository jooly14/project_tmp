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
			//왼쪽벽이 부딪히는 게 있는지 확인
			if(btnNew[1].getX()==0){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
			//왼쪽벽이 부딪히는 게 있는지 확인
			}else if(btnNew[1].getX()==20){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
			//오른쪽 벽에 부딪힐 때
			}else if(btnNew[1].getX()==20*14){
				btnNew[1].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
				//왼쪽벽이 부딪히는 게 있는지 확인
			}else if(stackedBlock[btnNew[1].getX()/20-1][btnNew[1].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
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
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
			//왼쪽벽이 부딪히는 게 있는지 확인
			}else if(stackedBlock[btnNew[1].getX()/20-2][btnNew[1].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
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
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
			//오른쪽 벽에 부딪힐 때
			}else if(stackedBlock[btnNew[1].getX()/20+1][btnNew[1].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
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
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
			//아무것도 부딪히는 게 없을 경우
			}else{
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+40,btnNew[1].getY());
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
			//바닥이 부딪히는 지 확인
			if(btnNew[1].getY()==580){
				btnNew[1].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
			//바닥에 한칸 부족할때
			}else if(btnNew[1].getY()==560){
				btnNew[1].setLocation(btnNew[1].getX(),btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
				//아무것도 부딪히는 게 없을 경우
				btnNew[1].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
			//오른쪽 벽이 부딪히는 게 있는지 확인
			if(btnNew[1].getX()==20*14){
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
			//오른쪽 한칸 벽이 부딪히는 게 있는지 확인
			}else if(btnNew[1].getX()==20*13){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
			//왼쪽 벽에 부딪히는 지 확인
			}else if(btnNew[1].getX()==0){
				btnNew[1].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
				//오른쪽 벽이 부딪히는 게 있는지 확인
			}else if(stackedBlock[btnNew[1].getX()/20+1][btnNew[1].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(btnNew[3].getX()<0){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
					main.rotateNum=1;
				}else if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
			//오른쪽 한칸 벽이 부딪히는 게 있는지 확인
			}else if(stackedBlock[btnNew[1].getX()/20+2][btnNew[1].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
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
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
			//왼쪽 벽에 부딪히는 지 확인
			}else if(stackedBlock[btnNew[1].getX()/20-1][btnNew[1].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX()+40, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
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
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
			//아무것도 부딪히는 게 없을 경우
			}else{
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-40, btnNew[1].getY());
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
			//바닥에 부딪히는 경우
			if(btnNew[1].getY()==580){
				btnNew[1].setLocation(btnNew[1].getX(),btnNew[1].getY()-40);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+40);
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
			//1.옆벽이 부딪히는 게 있는지 확인
			if(btnNew[1].getX()==0){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
			//2.옆에 부딪히는 블록이 있는지 확인
			}else if(stackedBlock[btnNew[1].getX()/20-1][btnNew[1].getY()/20]!=null){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(stackedBlock[btnNew[1].getX()/20][btnNew[1].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
			//3.아무것도 부딪히는 게 없을 경우
			}else{
				btnNew[0].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+20, btnNew[1].getY()-20);
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
			//1.바닥이 부딪히는 지 확인
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
					//원상복귀
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
			//2.안 부딪히는 경우 확인
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
			//1.옆벽이 부딪히는 게 있는지 확인
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
					//원상복구
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
					//원상복구
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
					//원상복구
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
			//2.옆에 부딪히는 블록이 있을 때
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
					//원상복구
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
					//원상복구
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
					//원상복구
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
			//3.아무것도 부딪히는 게 없을 경우
			}else{
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
					//원상복구
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
					//원상복구
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
					//원상복구
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
				//원상복구
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
				//원상복구
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
				//원상복구
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
			//1.옆벽이 부딪히는 게 있는지 확인
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
					//원상복귀
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
					//원상복귀
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
			//2.옆 블록에 부딪히는 지 확인
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
					//원상복귀
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
					//원상복귀
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
			//3.아무것도 부딪히는 게 없을 경우
			}else{
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
				//원상복귀
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
				//원상복귀
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
				//원상복귀
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
			//옆벽이 부딪히는 게 있는지 확인
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
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
				//아무것도 부딪히는 게 없을 경우
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
			//바닥이 부딪히는 지 확인
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
					//원상복귀
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
					//원상복귀
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
					main.rotateNum=2;
				}
			}else{
				//아무것도 부딪히는 게 없을 경우
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
			//옆벽이 부딪히는 게 있는지 확인
			if(btnNew[1].getX()==0){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					//원상복귀
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
					//원상복귀
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
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					//원상복귀
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
					//원상복귀
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
				//아무것도 부딪히는 게 없을 경우
				btnNew[0].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
					btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					//원상복귀
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
			//바닥이 부딪히는 지 확인
			if(btnNew[1].getY()==580){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(stackedBlock[btnNew[2].getX()/20][btnNew[2].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					//원상복귀
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
					//원상복귀
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
				//아무것도 부딪히는 게 없을 경우
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				//다른 블록이 그 위치에 있는 경우에는 그만큼 위치를 옮기고 이동했는 데도 안 되면 rotate취소
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
					btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
					btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					//원상복귀
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
			//옆벽이 부딪히는 게 있는지 확인
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
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
					//원상복귀
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
				//아무것도 부딪히는 게 없을 경우
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				if(stackedBlock[btnNew[0].getX()/20][btnNew[0].getY()/20]!=null){
					btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()+20);
					btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
					btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
					btnNew[3].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
					//원상복귀
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
				//원상복귀
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
			//옆벽이 부딪히는 게 있는지 확인
			if(btnNew[1].getX()==0){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
			}else{
				//아무것도 부딪히는 게 없을 경우
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()-20);
			}
			break;
		case 1:
			//바닥이 부딪히는 지 확인
			if(btnNew[1].getY()==580){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
			}else{
				//아무것도 부딪히는 게 없을 경우
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
			}
			
			break;
		case 2:
			//옆벽이 부딪히는 게 있는지 확인
			if(btnNew[1].getX()==20*14){
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
			}else{
				//아무것도 부딪히는 게 없을 경우
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
			//옆벽이 부딪히는 게 있는지 확인
			if(btnNew[1].getX()==20*14){
				btnNew[1].setLocation(btnNew[1].getX()-20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()+20);
				btnNew[3].setLocation(btnNew[1].getX()+20,btnNew[1].getY()+20);
			}else{
				//아무것도 부딪히는 게 없을 경우
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
			//옆벽이 부딪히는 게 있는지 확인
			if(btnNew[1].getX()==0){
				btnNew[1].setLocation(btnNew[1].getX()+20, btnNew[1].getY());
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
			}else{
				//아무것도 부딪히는 게 없을 경우
				btnNew[0].setLocation(btnNew[1].getX()+20,btnNew[1].getY());
				btnNew[2].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()-20);
			}
			break;
		case 3:
			//바닥이 부딪히는 지 확인
			if(btnNew[1].getY()==580){
				btnNew[1].setLocation(btnNew[1].getX(), btnNew[1].getY()-20);
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
			}else{
				//아무것도 부딪히는 게 없을 경우
				btnNew[0].setLocation(btnNew[1].getX(),btnNew[1].getY()-20);
				btnNew[2].setLocation(btnNew[1].getX()-20,btnNew[1].getY());
				btnNew[3].setLocation(btnNew[1].getX()-20,btnNew[1].getY()+20);
			}
			
			break;
		}
	}
}
