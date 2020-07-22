package game.ui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import game.button.BackButton;
import game.interFace.Movable;
import game.item.Background;
import game.item.Character;
import game.item.IceBerg;
import game.item.Olaf;
import game.item.Santa;
import game.item.Snow;
import game.item.Sun;

public class FightCanvas extends Canvas {// 같은 PC에서 2P
	private Random random;

	private Image img;
	private Image gameOverImg;
	private Image[] numImg;

	private static FightCanvas fightCanvas;
	private static IntroCanvas introCanvas;

	private Movable[] items;

	private Background background; // 배경화면
	private Character character; // 캐릭터(곰)
	private Santa santa; // 산타클로스
	private Olaf olaf; // 올라프
	private BackButton backButton; // 뒤로가기 버튼
	private IceBerg iceBerg; // 빙하
	private Sun sun;
	private Snow snow;

	private int frontTime; // 시간 카운트 앞숫자
	private int backTime; // 시간 카운트 뒷숫자
	private int fx, fy; // 카운트 앞자리 수
	private int bx, by; // 카운트 뒷자리 수

	private final int P1PUSH = 83;
	private final int P2PUSH = 40;

	private int max;
	private int santaCnt;// 산타 랜덤 출현 카운트
	private int olafCnt; // 올라프 랜덤 출현 카운트
	private int presentCnt;// 선물 랜덤 출현 카운트
	private int snowCnt; // 눈 랜덤 출현 카운트
	private int count;

	private int unitIndex = 0;
	private static boolean isStop;

	public FightCanvas() {
		random = new Random();

		isStop = true;

		fightCanvas = this;

		numImg = new Image[10]; // 카운트다운 숫자 이미지 배열 10개 선언

		items = new Movable[100];

		max = 100;

		fx = 640;
		fy = 650;
		bx = 700;
		by = 650;
		backTime = 1; // 시간카운트 뒷숫자 초기화
		frontTime = 3; // 시간 카운트 앞숫자 초기화

		try { // 카운트 다운 및 게임오버 화면
			numImg[0] = ImageIO.read(new File("res/images/0.png"));
			numImg[1] = ImageIO.read(new File("res/images/1.png"));
			numImg[2] = ImageIO.read(new File("res/images/2.png"));
			numImg[3] = ImageIO.read(new File("res/images/3.png"));
			numImg[4] = ImageIO.read(new File("res/images/4.png"));
			numImg[5] = ImageIO.read(new File("res/images/5.png"));
			numImg[6] = ImageIO.read(new File("res/images/6.png"));
			numImg[7] = ImageIO.read(new File("res/images/7.png"));
			numImg[8] = ImageIO.read(new File("res/images/8.png"));
			numImg[9] = ImageIO.read(new File("res/images/9.png"));

			gameOverImg = ImageIO.read(new File("res/images/GameOver2.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		background = new Background();
		sun = new Sun();
		iceBerg = new IceBerg(); // 객체 생성 순서 중요함!!
		character = new Character();
		// Character가 먼저 객체 생성한 후 빙산을 객체 생성하면 캐릭터들이 그냥 지나치게 됨
		backButton = new BackButton();

		santaCnt = 200; // 산타 출현 카운트다운
		olafCnt = 200; // 올라프 출현 카운트다운
		presentCnt = random.nextInt(200) + 250;// 선물 출현 카운트다운
		snowCnt = random.nextInt(100) + 250; // 눈 출현 카운트다운

		items[unitIndex++] = background; // 배경
		items[unitIndex++] = character; // 곰 캐릭터
		items[unitIndex++] = iceBerg; // 빙산
		items[unitIndex++] = sun; // 해
		items[unitIndex++] = backButton;

		new Thread(new Runnable() { // 시간 초 쓰레드

			@Override
			public void run() {
				try {
					while (isStop) {
						backTime--;

						if (backTime == -1) { // 시간 카운트 뒷숫자
							backTime = 9;
							frontTime--;
							if (frontTime == -1)
								frontTime = 0;
						}
						if (backTime == 0 && frontTime == 0) { // 시간 초가 0초되면 캐릭터 멈춤
							isStop = false;
							character.bearsStop();
						}
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int result = e.getKeyCode();

				switch (result) {
				case KeyEvent.VK_LEFT: {// 방향키 좌
					character.bearR_moveLeft();
					character.bearR_back();
					break;
				}
				case KeyEvent.VK_RIGHT: {// 방향키 우
					character.bearR_moveRight();
					character.bearR_front();
					break;
				}
				case 65: {// A키 p1 왼쪽이동
					character.bearL_moveLeft();
					character.bearL_back();
					break;
				}
				case 68: {// D키 p1 오른쪽 이동
					character.bearL_moveRight();
					character.bearL_front();
					break;
				}
				case P1PUSH: {// 1p 밀기 보조키
					character.bearL_moveRight();
					break;
				}
				case P2PUSH: {
					character.bearR_moveLeft();
					break;
				}
				} // end switch
			} // end keyPressed
		});

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

				int x = e.getX();
				int y = e.getY();
				
				if ((backButton.contains(x, y))&&(x > 1350 && x < 1416) && (y > 650 && y < 712)) {
					GameFrame.getInstance().changeCanvas(5);
					GameFrame.music.wooStop();
				}
			}
		});
		
		this.addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				if ((x > 1244 && x < 1416) && (y > 14 && y < 216)) {
					sun.contain = true;
					GameFrame.music.ahStart();
				} else {
					sun.contain = false;
					
				}

			}

		});

	} // end public FightCanvas()

	public void start() {

		Thread th = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					moveUpdate(); // 단위벡터 단위로 움직임
					fightCanvas.repaint();
					try {
						Thread.sleep(17);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		th.start();
	}

	public void moveUpdate() {// 단위 이동후업데이트
		for (int i = 0; i < unitIndex; i++) {
			items[i].update(); // 화면의 이동시 변경 후 업데이트 (쓰레드에서 호출됨)
		}

		if (unitIndex >= max) {
			Movable[] temp = new Movable[max + 50];

			for (int i = 0; i < unitIndex; i++)
				temp[i] = items[i];

			items = temp;
			max += 50;
		}

		if (--santaCnt == 0) { // 산타 카운트가 0이면 산타 생성

			santa = new Santa(); // 반복적으로 산타 생성

			items[unitIndex++] = santa;

			santaCnt = 500;

		} // ~end if

		if (--presentCnt == 0) { // 선물카운트가 0이면 선물 투척

			items[unitIndex++] = santa.throwPresent(); // 산타가 선물 투척

//			presentCnt = random.nextInt(400) + 200;
			presentCnt = 400;
			
		}

		if (--olafCnt == 0) { // 올라프카운트가 0이면 올라프 생성
			olaf = new Olaf(); // 반복적으로 올라프 생성

			items[unitIndex++] = olaf;

			olafCnt = 300;
		}

		if (--snowCnt == 0) { // 선물카운트가 0이면 선물 투척

			items[unitIndex++] = olaf.throwSnow(); // 산타가 선물 투척

			if (frontTime == 2) {
				snowCnt = random.nextInt(100) + 50;
			} else if (frontTime == 1) {
				snowCnt = random.nextInt(50) + 20;
			} else {
				snowCnt = random.nextInt(50) + 1;
			}
			

		}

	}

	@Override
	public void update(Graphics g) { // 화면 지우기
		paint(g);
	}

	@Override
	public void paint(Graphics g) {
		Image buf = createImage(this.getWidth(), this.getHeight()); // 더블 버퍼링 구현
		Graphics gg = buf.getGraphics();

		for (int i = 0; i < unitIndex; i++) {
			items[i].draw(gg);
		}

		System.out.println("backTime: " + backTime);
		System.out.println("frontTime: " + frontTime);
		System.out.println("isStop: " + isStop);
		
		gg.drawImage(numImg[backTime], bx, by, FightCanvas.getInstacne()); // 카운트 다운 뒤에 숫자
		gg.drawImage(numImg[frontTime], fx, fy, FightCanvas.getInstacne()); // 카운트 다운 앞에 숫자

		if ((backTime == 0 && frontTime == 0) || Character.isBearDrop()) { // 시간 초 끝나면 GameOver이미지 출력
			gg.drawImage(gameOverImg, 400, 100, FightCanvas.getInstacne()); // 카운트 다운 00 되면 gameover화면 나옴
			isStop = false;
			backTime = 0;
			frontTime = 0;
		}

		g.drawImage(buf, 0, 0, this);

	}

	public static FightCanvas getInstacne() {
		return fightCanvas;
	}

	public static IntroCanvas getInstance() {
		return introCanvas;
	}

}