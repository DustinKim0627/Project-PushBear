package game.item;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.interFace.Movable;
import game.main.Music;
import game.ui.FightCanvas;
import game.ui.GameFrame;
import game.ui.IntroCanvas;
import game.ui.OnlineCanvas;
import game.ui.SingleCanvas;

public class Character implements Movable {
	private Image bearImg_left, bearImg_right; //곰 캐릭터 이미지
	private static Character character;
	private IntroCanvas introCanvas;

	/* +++++++++++++좌측 곰 변수++++++++++++++ */
	private int bearImg_leftX, bearImg_leftY; // 좌측 곰 x,y좌표
	private static int widthL, heightL; // 좌측 곰 너비, 높이
	private int imgIndexL; // 좌측 곰 이미지 인덱스
	private static int bearImg_leftDx1, bearImg_leftDy1, bearImg_leftDx2, bearImg_leftDy2; // 좌측 곰 이미지 중심 좌표

	/* +++++++++++++우측 곰 변수++++++++++++++ */
	private int bearImg_rightX, bearImg_rightY; // 우측 곰 x,y좌표
	private static int widthR, heightR; // 우측 곰 너비, 높이
	private int imgIndexR; // 우측 곰 이미지 인덱스
	private static int bearImg_rightDx1, bearImg_rightDy1, bearImg_rightDx2, bearImg_rightDy2; // 우측 곰 이미지 중심 좌표

	/* +++++++++++++빙산 변수++++++++++++++ */
	private IceBerg iceBerg;
	private int vx;
	
	/* +++++++++++++눈 변수++++++++++++++ */
	private Snow snow;

	private int rVx;// 우측 곰 이동할 단위
	private int lVx; //좌측 곰 이동할 단위 

	private int timeoutForMove;
	

	private static int cnt = 0;
	private static int cntt = 1;
	public static boolean change = false;
	Music EffectMusic;

	private boolean LfreezingFlag=false; //좌측 곰 프리징 유무
	private boolean RfreezingFlag=false; //우측 곰 프리징 유무
	
	private static boolean bearDrop;
	private boolean visible=true;
	
	public Character() {
		character=this;
		
		bearDrop=false;
		
		iceBerg = IceBerg.getIceBerg(); //생성된 객체 불러오기
		timeoutForMove = 7;

		try {
			bearImg_left = ImageIO.read(new File("res/images/BBear-freezing.png")); // 왼쪽 곰 이미지
			bearImg_right = ImageIO.read(new File("res/images/IBear-freezing.png")); // 오른쪽 곰 이미지


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* +++++++++++++좌측 곰 초기화 설정++++++++++++++ */
		bearImg_leftX = 400; // 좌측 곰 시작점 x축
		bearImg_leftY = 480; // 좌측 곰 시작점 y축
		widthL = 166; // 좌측 곰 너비
		heightL = 256; // 좌측 곰 높이
		lVx=20; //좌측 곰 이동단위
		imgIndexL = 0; // 좌측 곰 이미지인덱스
		
		/* +++++++++++++우측 곰 초기화 설정++++++++++++++ */
		bearImg_rightX = 1050; // 우측 곰 시작점 x축
		bearImg_rightY = 480; // 우측 곰 시작점 y축
		widthR = 160; // 우측 곰 너비
		heightR = 256; // 우측 곰 높이
		imgIndexR = 0; // 우측 곰 이미지인덱스
		rVx = 20;// 우측 곰 이동단위
	
		vx=20;//빙산 이동단위

	}

	/* +++++++++++캐릭터 이동 메소드+++++++++++++ */
	// wall 183 , 261
	// bear 169,256
	public void bearL_moveLeft() { // 좌측곰 왼쪽 이동
		
			this.bearImg_leftX -= lVx;
		

		// 빙하가 빙하 왼쪽 끝에 있거나 ,왼쪽곰이 왼쪽 낭떠러지로 떨어지거나
		if (iceBerg.getIceBergX() - 120 < 180 || bearImg_leftX < 160)
			lVx = 0;

	}

	public void bearL_moveRight() { // 좌측곰 오른쪽 이동
		
			this.bearImg_leftX += lVx;
		

		// 왼쪽 곰이 (왼쪽 빙하 경계선)빙하를 밀 때
		if (bearImg_leftX > iceBerg.getIceBergX() - 140) {
			iceBerg.plusIceBergX(lVx);
			if (iceBerg.getIceBergX() + 130 >= bearImg_rightX) // 빙하 우 경계선이 우측 곰과 맞닿을때
				bearR_moveRight();// 우측곰이 뒤로 밀림
		}

		if (iceBerg.getIceBergX() + 60 > 1280) // 빙하가 오른쪽 끝으로 가면 멈춤
			lVx = 0;

	}

	public void bearR_moveLeft() {// 우측곰 왼쪽 이동
		
			this.bearImg_rightX -= rVx;

		if (bearImg_rightX < iceBerg.getIceBergX() + 140) {// 우측곰 손과 빙하 우 경계 비교해서 빙하를 민다.
			iceBerg.minusIceBergX(rVx);
			if (bearImg_leftX >= iceBerg.getIceBergX() - 130)// 좌측곰이랑 빙하랑 닿으면
				bearL_moveLeft();// 좌측곰이 왼쪽으로 밀림
		}

		if (iceBerg.getIceBergX() < 160)// 혹은 빙하가 좌측 낭떠러지 끝으로 갈 때, 멈춤
			rVx = 0;

	}

	public void bearR_moveRight() { // 우측곰 오른쪽 이동
			this.bearImg_rightX += rVx;
		
		if (bearImg_rightX > 1320 || iceBerg.getIceBergX() + 60 > 1280) {
			rVx = 0;
		}
	}

	/* +++++++++++캐릭터 이미지인덱스 변화 메소드+++++++++++++ */
	public void bearL_back() {// 좌측 곰이 뒤로 갈 때 이미지인덱스 변화
		if (imgIndexL == 0)
			imgIndexL = 7;
		imgIndexL--;
	}

	public void bearL_front() {// 좌측 곰이 앞으로 갈 때 이미지인덱스 변화
		imgIndexL++;
		imgIndexL %= 7;
	}

	public void bearR_back() { // 우측 곰이 뒤로갈 때 이미지인덱스 변화
		if (imgIndexR == 0)
			imgIndexR = 7;
		imgIndexR--;
	}

	public void bearR_front() {// 우측 곰이 앞으로 갈 때 이미지인덱스 변화
		imgIndexR++;
		imgIndexR %= 7;
	}
	
	public void bearL_freezing() { // 좌측 곰 프리징
		imgIndexL++;
		imgIndexL = (imgIndexL % 4) + 7;
		this.bearImg_leftX-=40;
	}
	
	public void bearR_freezing() { //우측 곰 프리징
		imgIndexR++;
		imgIndexR = (imgIndexR % 4) + 7;
		this.bearImg_rightX+=40;
	}
	

	public void aivx() {// 우측 곰이 앞으로 갈 때 이미지인덱스 변화
		this.bearImg_rightX -= rVx;
		bearR_front();
		if (bearImg_rightX < iceBerg.getIceBergX() + 140) {// 우측곰 손과 빙하 우 경계 비교해서 빙하를 민다.
			iceBerg.minusIceBergX(rVx);
			if (bearImg_leftX >= iceBerg.getIceBergX() - 130)// 좌측곰이랑 빙하랑 닿으면
				bearL_moveLeft();// 좌측곰이 왼쪽으로 밀림
		}
	}

	public void draw(Graphics g) {

		/* +++++++++++ 좌측 곰 이미지 그리기 +++++++++++++ */

	
		
		if(GameFrame.canvasId==1) {
			int offsetX = widthL / 2; // 왼쪽 이미지의 x축 중심점 이동위해서 선언
			int offsetY = heightL / 2; // 왼쪽 이미지의 y축 중심점 이동위해서 선언
			bearImg_leftDx1 = (int) (bearImg_leftX - offsetX);
			bearImg_leftDy1 = (int) (bearImg_leftY - offsetY);
			bearImg_leftDx2 = (int) (bearImg_leftX + widthL - offsetX);
			bearImg_leftDy2 = (int) (bearImg_leftY + heightL - offsetY);
			
			g.drawImage(bearImg_left, bearImg_leftDx1, bearImg_leftDy1, bearImg_leftDx2, bearImg_leftDy2,
					imgIndexL * widthL, 0, imgIndexL * widthL + widthL, heightL, SingleCanvas.getInstacne());
			
			offsetX = widthR / 2; // 오른쪽 이미지의 x축 중심점 이동위해서 선언
			offsetY = heightR / 2; // 오른쪽 이미지의 y축 중심점 이동위해서 선언

			bearImg_rightDx1 = (int) (bearImg_rightX - offsetX);
			bearImg_rightDy1 = (int) (bearImg_rightY - offsetY);
			bearImg_rightDx2 = (int) (bearImg_rightX + widthR - offsetX);
			bearImg_rightDy2 = (int) (bearImg_rightY + heightR - offsetY);

			g.drawImage(bearImg_right, bearImg_rightDx1, bearImg_rightDy1, bearImg_rightDx2, bearImg_rightDy2,
					imgIndexR * widthR, 0, imgIndexR * widthR + widthR, heightR, SingleCanvas.getInstacne());
			
		}else if(GameFrame.canvasId==2) {
			int offsetX = widthL / 2; // 왼쪽 이미지의 x축 중심점 이동위해서 선언
			int offsetY = heightL / 2; // 왼쪽 이미지의 y축 중심점 이동위해서 선언
			bearImg_leftDx1 = (int) (bearImg_leftX - offsetX);
			bearImg_leftDy1 = (int) (bearImg_leftY - offsetY);
			bearImg_leftDx2 = (int) (bearImg_leftX + widthL - offsetX);
			bearImg_leftDy2 = (int) (bearImg_leftY + heightL - offsetY);
			
			g.drawImage(bearImg_left, bearImg_leftDx1, bearImg_leftDy1, bearImg_leftDx2, bearImg_leftDy2,
					imgIndexL * widthL, 0, imgIndexL * widthL + widthL, heightL, FightCanvas.getInstacne());
			
			offsetX = widthR / 2; // 오른쪽 이미지의 x축 중심점 이동위해서 선언
			offsetY = heightR / 2; // 오른쪽 이미지의 y축 중심점 이동위해서 선언

			bearImg_rightDx1 = (int) (bearImg_rightX - offsetX);
			bearImg_rightDy1 = (int) (bearImg_rightY - offsetY);
			bearImg_rightDx2 = (int) (bearImg_rightX + widthR - offsetX);
			bearImg_rightDy2 = (int) (bearImg_rightY + heightR - offsetY);

			g.drawImage(bearImg_right, bearImg_rightDx1, bearImg_rightDy1, bearImg_rightDx2, bearImg_rightDy2,
					imgIndexR * widthR, 0, imgIndexR * widthR + widthR, heightR, FightCanvas.getInstacne());
		}else if(GameFrame.canvasId==3) {
			if(OnlineCanvas.c_socket!=null) {
				int offsetX = widthR / 2; // 오른쪽 이미지의 x축 중심점 이동위해서 선언
				int offsetY = heightR / 2; // 오른쪽 이미지의 y축 중심점 이동위해서 선언

				bearImg_rightDx1 = (int) (bearImg_rightX - offsetX);
				bearImg_rightDy1 = (int) (bearImg_rightY - offsetY);
				bearImg_rightDx2 = (int) (bearImg_rightX + widthR - offsetX);
				bearImg_rightDy2 = (int) (bearImg_rightY + heightR - offsetY);

				g.drawImage(bearImg_right, bearImg_rightDx1, bearImg_rightDy1, bearImg_rightDx2, bearImg_rightDy2,
						imgIndexR * widthR, 0, imgIndexR * widthR + widthR, heightR, OnlineCanvas.getInstacne());
			}
			int offsetX = widthL / 2; // 왼쪽 이미지의 x축 중심점 이동위해서 선언
			int offsetY = heightL / 2; // 왼쪽 이미지의 y축 중심점 이동위해서 선언
			bearImg_leftDx1 = (int) (bearImg_leftX - offsetX);
			bearImg_leftDy1 = (int) (bearImg_leftY - offsetY);
			bearImg_leftDx2 = (int) (bearImg_leftX + widthL - offsetX);
			bearImg_leftDy2 = (int) (bearImg_leftY + heightL - offsetY);
			
			g.drawImage(bearImg_left, bearImg_leftDx1, bearImg_leftDy1, bearImg_leftDx2, bearImg_leftDy2,
					imgIndexL * widthL, 0, imgIndexL * widthL + widthL, heightL, OnlineCanvas.getInstacne());
			
			
		}

		

		/* +++++++++++ 우측 곰 이미지 그리기 +++++++++++++ */

		

		
		// 왼곰이 빙하밖으로 떨어지면
		if (bearImg_leftX < 180 || bearImg_leftX > 1250) {
			bearDrop=true;
			
			bearImg_leftY++; // 화면 밑으로 사라짐
			vx = 0; //빙하 멈춤
			lVx=0; //좌측 곰 멈춤
			rVx=0; //우측 곰 멈춤
			
			if (GameFrame.canvasId == 1) { //SingleCanvas
				cnt++;

				if (cnt == 1) { // 싱글-왼곰이 빙하 밖 떨어지면 음악재생
					Thread th1 = new Thread(new Runnable() {
						@Override
						public void run() {

							GameFrame.music.singleStop();
							GameFrame.music.wooStart();
						}
					});
					cnt++;
					th1.start();
				}
			}

			if (GameFrame.canvasId == 2) { //FightCanvas
				cntt++;
				if (cntt == 2) { // 멀티-곰이 빙하 밖 떨어지면 음악재생
					Thread th2 = new Thread(new Runnable() {
						@Override
						public void run() {
							GameFrame.music.fighterStop();
							GameFrame.music.wooStart();
						}
					});
					cntt++;
					th2.start();
				}
			}
			
			if (GameFrame.canvasId == 3) { //OnlineCanvas
				cntt++;
				if (cntt == 3) { // 온라인-곰이 빙하 밖 떨어지면 음악재생
					Thread th3 = new Thread(new Runnable() {
						@Override
						public void run() {
							GameFrame.music.onlineStop();
							GameFrame.music.wooStart();
						}
					});
					cntt++;
					th3.start();
				}
			}

		}

		// 오른곰이 빙하밖으로 떨어지면
		if (bearImg_rightX < 160 || bearImg_rightX > 1320) {
			bearDrop=true;
			
			bearImg_rightY++; // 화면 밑으로 사라짐
			vx = 0; //빙하 멈춤
			lVx=0; //좌측 곰 멈춤
			rVx=0; //우측 곰 멈춤
			
			if (GameFrame.canvasId == 1) {
				cnt++;

				if (cnt == 1) { // 싱글-오른곰이 빙하 밖 떨어지면 음악재생
					Thread th1 = new Thread(new Runnable() {
						@Override
						public void run() {

							GameFrame.music.singleStop();
							GameFrame.music.wooStart();
						}
					});
					cnt++;
					th1.start();
				}
			}

			if (GameFrame.canvasId == 2) {
				cntt++;
				if (cntt == 2) { // 멀티-오른곰이 빙하 밖 떨어지면 음악재생
					Thread th2 = new Thread(new Runnable() {
						@Override
						public void run() {
							GameFrame.music.fighterStop();
							GameFrame.music.wooStart();
						}
					});
					cntt++;
					th2.start();
				}
			}//end if 
			
			if (GameFrame.canvasId == 3) {
				cntt++;
				if (cntt == 3) { // 온라인-오른곰이 빙하 밖 떨어지면 음악재생
					Thread th3 = new Thread(new Runnable() {
						@Override
						public void run() {
							GameFrame.music.fighterStop();
							GameFrame.music.wooStart();
						}
					});
					cntt++;
					th3.start();
				}
			}//end if 

		}
		
	}//end draw
	
	
	public void setbearImg_rightX(int x) {
		this.bearImg_rightX=bearImg_rightX+10;
	}
	
	public void setlVx(int lVx) {
		this.lVx=lVx;
	}
	

	public static int getBearImg_leftDx2() { // 좌측곰 dx2축 반환
		return bearImg_leftDx2;
	}

	public static int getBearImg_leftDy1() { // 좌측곰 dy1축 반환
		return bearImg_leftDy1;
	}

	public static int getBearImg_leftHeight() { // 좌측곰 높이 반환
		return heightL;
	}

	public static int getBearImg_rightDx2() { // 우측곰 dx2축 반환
		return bearImg_rightDx2;
	}

	public static int getBearImg_rightDy1() { // 우측곰 dy1축 반환
		return bearImg_rightDy1;
	}

	public static int getBearImg_rightDx1() {
		return bearImg_rightDx1;
	}

	public static int getBearImg_rightDy2() {
		return bearImg_rightDy2;
	}

	public static int getBearImg_leftDx1() {
		return bearImg_leftDx1;
	}
	
	public static Character getCharacter() {
		return character;
	}

	public void isRfreezing() {
		this.RfreezingFlag=true;
	}

	public boolean getRfreezing() {
		return RfreezingFlag;
	}

	public void isLfreezing() {
		this.LfreezingFlag=true;
	}

	public boolean getLfreezing() {
		return LfreezingFlag;
	}

	public void bearsStop() { //곰들 멈추기
		rVx=0;
		lVx=0;
	}
	
	public static boolean isBearDrop() {
		return bearDrop;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}  

	

//	public void pause() {// ESC클릭시 캐릭터 이동멈춤
//	pause = !pause;
//	if (pause == true)
//		vx = 10;
//	else
//		vx = 0;
//}

}