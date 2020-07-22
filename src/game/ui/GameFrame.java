package game.ui;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import game.button.BackButton;
import game.button.MultiButton;
import game.interFace.Movable;
import game.item.Character;
import game.main.Music;



public class GameFrame extends Frame { // 라이브러리클래스에 저장된 frame.
	private static GameFrame gameFrame;
	public static Music music;
	
	public static int canvasId;

	private Movable[] items;
	
	private Frame frame;

	private FightCanvas fightCanvas;
	private IntroCanvas introCanvas;
	private StartView startView;
	private BackButton backButton;
	private MultiButton multiButton;
	private SingleCanvas singleCanvas;
	private OnlineCanvas onlineCanvas;
	private Character character;
	
	private int unitIndex = 0;
	private int count = 0;
	

	public GameFrame() {
		gameFrame = this;
		character = new Character();
		introCanvas = new IntroCanvas();
		fightCanvas = new FightCanvas();
//		singleCanvas=new SingleCanvas();
		backButton = new BackButton();
		multiButton = new MultiButton();
		music = new Music();
		music.IntroMusicStart();
		
		items = new Movable[100];

		items[unitIndex++] = backButton;
		
		this.add(introCanvas); // 캔버스를 프레임에 생성해서 붙이는 작업
		introCanvas.setFocusable(true); // 캔버스에 포커스를 맞추어서 게임 시작하면 바로 키보드로 실행 가능.
		introCanvas.start();

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(GameFrame.this, "종료하시겠습니까?"); // Yes or No가 뜸
				// GameFrame.this, "종료?" 라고 하면, 게임 프레임 정중앙에 종료 물어보는 창이뜸.

				if (result == 0) { // yes를 누르면. 임.
					System.exit(0); // 화면 x 누르면 닫힘.
				}

			}
		});
	} // end public GameFrame()



	public void changeCanvas(int canvasId) {
		this.canvasId = canvasId;

		// 메인에서 1번 버튼 선택시 싱글 플레이
		if (canvasId == 1) {
			this.remove(introCanvas);
			singleCanvas = new SingleCanvas();
			this.add(singleCanvas);
			music.introStop();
			music.singleMusicStart();
			singleCanvas.start();
			singleCanvas.setFocusable(true);
			singleCanvas.requestFocus(); // 키보드 입력을 위함.
			this.revalidate(); //
		} 
		else if (canvasId == 2) { // 멀티 플레이
			this.remove(introCanvas);
			music.introStop();
			music.fighterMusicStart();
			fightCanvas = new FightCanvas();
			this.add(fightCanvas);
			fightCanvas.start();
			fightCanvas.setFocusable(true);
			fightCanvas.requestFocus(); // 키보드 입력을 위함.
			this.revalidate(); //
		} 
		else if (canvasId == 3) { // 온라인 플레이
			this.remove(introCanvas);
			music.introStop();
			music.onlineMusicStart();
			onlineCanvas = new OnlineCanvas();
			this.add(onlineCanvas);
			onlineCanvas.start();
			onlineCanvas.setFocusable(true);
			onlineCanvas.requestFocus(); // 키보드 입력을 위함.
			this.revalidate(); //
		}
		else if (canvasId == 4) { // 미정 연결은
			this.remove(singleCanvas);
			music.singleStop();
			music.IntroMusicStart();
			introCanvas = new IntroCanvas();
			this.add(introCanvas);
			introCanvas.start();
			introCanvas.setFocusable(true);
			introCanvas.requestFocus(); // 키보드 입력을 위함.
			this.revalidate(); //
		}
		else if (canvasId == 5) { // 미정 연결은
			this.remove(fightCanvas);
			music.fighterStop();
			music.IntroMusicStart();
			introCanvas = new IntroCanvas();
			this.add(introCanvas);
			introCanvas.start();
			introCanvas.setFocusable(true);
			introCanvas.requestFocus(); // 키보드 입력을 위함.
			this.revalidate(); //
		}
		else if (canvasId == 6) { // 미정 연결은
			this.remove(onlineCanvas);
			music.onlineStop();
			music.IntroMusicStart();
			introCanvas = new IntroCanvas();
			this.add(introCanvas);
			introCanvas.start();
			introCanvas.setFocusable(true);
			introCanvas.requestFocus(); // 키보드 입력을 위함.
			this.revalidate(); //
		}

	}
	
	public static GameFrame getInstance() { // 전역변수의 의미
		return gameFrame;
	}
}

