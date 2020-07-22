package game.ui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;

import game.button.BackButton;
import game.button.ExitButton;
import game.button.HelpButton;
import game.button.MultiButton;
import game.button.OnlineButton;
import game.button.SingleButton;
import game.interFace.Movable;
import game.main.Music;

public class IntroCanvas extends Canvas {
	private static IntroCanvas introCanvas;
	private StartView startView;

	private GameFrame frame;
	private Music music;
	
	private SingleButton singleButton;
	private MultiButton multiButton;
	private ExitButton exitButton;
	private BackButton backButton;
	private HelpButton helpButton;
	private OnlineButton onlineButton;
	
	private Movable[] items;
	private int unitIndex = 0;

	public void introCanvasMusic(){
	
		
//		introMusic.musicStop(); //음악 끝내기
	}
	
	public IntroCanvas() {
		items = new Movable[100];

		singleButton = new SingleButton();
		startView = new StartView();
		multiButton = new MultiButton();
		onlineButton = new OnlineButton();
		exitButton = new ExitButton();
		helpButton = new HelpButton();
		
		items[unitIndex++] = startView;
		items[unitIndex++] = singleButton;
		items[unitIndex++] = multiButton;
		items[unitIndex++] = onlineButton;
		items[unitIndex++] = exitButton;
		items[unitIndex++] = helpButton;

		this.addMouseListener(new MouseAdapter() { // 마우스클릭
			@Override
			public void mouseClicked(MouseEvent e) {
				if (singleButton.contatins(e.getX(), e.getY())) {
					singleButton.state(singleButton.STATE_CLICK);
					GameFrame.getInstance().changeCanvas(1);
				} else if (multiButton.contatins(e.getX(), e.getY())) {
					multiButton.state(multiButton.STATE_CLICK);
					GameFrame.getInstance().changeCanvas(2);
				} else if (onlineButton.contatins(e.getX(), e.getY())) {
					onlineButton.state(onlineButton.STATE_CLICK);
					GameFrame.getInstance().changeCanvas(3);
				} else if (exitButton.contatins(e.getX(), e.getY())) {
					exitButton.state(ExitButton.STATE_CLICK);
					System.exit(0);
				} else if (helpButton.contatins(e.getX(), e.getY())) {
					helpButton.state(HelpButton.STATE_CLICK);
					JOptionPane.showMessageDialog(IntroCanvas.this, " 　　　　　　　　♬♪ How to Play ♪♬\r\n" + " \n" + "상대편 곰을 빙판 밖으로 밀어서 떨어뜨리면 이기는 게임입니다. \r\n"
	                           + "◆ 게임 조작 방법 \r\n" + "  ▶ 혼자하기 \r\n" + "   - 게임 시간은 30초 입니다.\r\n"
	                           + "   -  게임 작동 키는 키보드 a(왼쪽), s&d(오른쪽)로 작동합니다. \r\n"
	                           + "   - 아이템은 랜덤으로 얻을 수 있으며, 즉시 사용됩니다. \r\n"
	                           + "   -  산타가 주는 선물은 ...... / 올라프가 던지는 눈은 뒤로 밀려납니다.\r\n" + "  ▶ 같이하기 \r\n"
	                           + "   - 게임 시간은 30초 입니다.\r\n" + "   - Player 1 게임 작동 키는 키보드 화살표로 작동합니다.\r\n"
	                           + "   - Player 2 게임 작동 키는 키보드 a(왼쪽), s&d(오른쪽)로 작동합니다.\r\n"
	                           + "   - 아이템은 랜덤으로 얻을 수 있으며, 즉시 사용됩니다. \r\n"
	                           + "   -  산타가 주는 선물은 ...... / 올라프가 던지는 눈은 뒤로 밀려납니다.\r\n" + "  ▶ 온라인 같이하기 \r\n"
	                           + "   - 게임 시간은 30초 입니다.\r\n" + "   - Player 1 게임 작동 키는 키보드 화살표로 작동합니다.\r\n"
	                           + "   - Player 2 게임 작동 키는 키보드 a(왼쪽), s&d(오른쪽)로 작동합니다.\r\n"
	                           + "   - 아이템은 랜덤으로 얻을 수 있으며, 즉시 사용됩니다. \r\n"
	                           + "   - 산타가 주는 선물은 ...... / 올라프가 던지는 눈은 뒤로 밀려납니다.\r\n" + "Exit 버튼 누르면 게임이 종료 됩니다.\r\n"
	                           + " \n" + "　　　　　　　　　　WISH TO LUCK ! \r\n");
					return;
				}

			}
		});

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (singleButton.contatins(e.getX(), e.getY())) {
					singleButton.state(singleButton.STATE_PRESS);
				} else if (multiButton.contatins(e.getX(), e.getY())) {
					multiButton.state(multiButton.STATE_PRESS);
				} else if (onlineButton.contatins(e.getX(), e.getY())) {
					onlineButton.state(onlineButton.STATE_PRESS);
				} else if (exitButton.contatins(e.getX(), e.getY())) {
					exitButton.state(ExitButton.STATE_PRESS);
				}
			}
		});

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (singleButton.contatins(e.getX(), e.getY())) {
					singleButton.state(singleButton.STATE_RELEASE);
				}
				if (multiButton.contatins(e.getX(), e.getY())) {
					multiButton.state(multiButton.STATE_RELEASE);
				}
				if (onlineButton.contatins(e.getX(), e.getY())) {
					onlineButton.state(onlineButton.STATE_RELEASE);
				}
				if (exitButton.contatins(e.getX(), e.getY())) {
					exitButton.state(ExitButton.STATE_RELEASE);
				}
			}

		});

		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {


				if (singleButton.contatins(e.getX(), e.getY())) { // SingleButton
					singleButton.state(singleButton.STATE_ON);
				} else if (!(singleButton.contatins(e.getX(), e.getY())))
					singleButton.state(singleButton.STATE_OFF);

				if (multiButton.contatins(e.getX(), e.getY())) { // MultiButton
					multiButton.state(multiButton.STATE_ON);
				} else if (!(multiButton.contatins(e.getX(), e.getY())))
					multiButton.state(multiButton.STATE_OFF);

				if (onlineButton.contatins(e.getX(), e.getY())) { // MultiButton
					onlineButton.state(onlineButton.STATE_ON);
				} else if (!(onlineButton.contatins(e.getX(), e.getY())))
					onlineButton.state(onlineButton.STATE_OFF);

				if (exitButton.contatins(e.getX(), e.getY())) { // ExitButton
					exitButton.state(ExitButton.STATE_ON);
				} else if (!(exitButton.contatins(e.getX(), e.getY())))
					exitButton.state(ExitButton.STATE_OFF);

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}

	

	public void start() {
		Thread gameThread = new Thread(new Runnable() {

			@Override
			public void run() { // gameThread.start() 호출하면 실행됨
				while (true) {
					moveUpdate(); // 단위벡터 단위의 움직임, 점진적인 업데이트
					repaint();

					try {
						Thread.sleep(17); // 60프레임 맞추기 위함 (1초에 60번 돌기)
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		gameThread.start();

	}

	public void moveUpdate() {
		for (int i = 0; i < unitIndex; i++)
			items[i].update();
	}

	@Override
	public void update(Graphics g) { // 화면 깜빡이지 않게 하기 위하여 부모의 update는 실행하지 않고 paint 바로 실행
		paint(g);
	}

	@Override
	public void paint(Graphics g) {
		Image buf = createImage(this.getWidth(), this.getHeight());
		Graphics gg = buf.getGraphics();

		// 모든 유닛은 이미지 버퍼에 그림을 그리게 하고
		for (int i = 0; i < unitIndex; i++)
			items[i].draw(gg);

		// 모든그림이 다그려지면 한번만 화면 버퍼에 그리자.
		g.drawImage(buf, 0, 0, this);
	}
	
	public static IntroCanvas getInstance() {
		return introCanvas;
	}

}
