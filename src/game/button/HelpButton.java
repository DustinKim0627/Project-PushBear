package game.button;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.interFace.Movable;
import game.ui.IntroCanvas;

public class HelpButton implements Movable {

	public static final int STATE_OFF = 0; // 버튼 범위에서 벗어날 때
	public static final int STATE_ON = 1; // 버튼 범위에 들어올 때
	public static final int STATE_CLICK = 2; // 버튼 클릭할 때
	public static final int STATE_PRESS = 3; // 버튼 클릭한 순간
	public static final int STATE_RELEASE = 4; // 버튼에서 클릭 후 띌 때

	private double x;
	private double y;
	private int width;
	private int height;
	private int stateValue;
	private int timeout;
	private int imgIndex;

	private Image img;

	public HelpButton() {

		x = 1140;
		y = 10;
		width = 221;
		height = 220;
		try {

			img = ImageIO.read(new File("res/images/Intro5.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		if (timeout-- == 0) {
			imgIndex++;
			imgIndex %= 5;
			timeout = 7;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, (int) x, (int) y, (int) x + width, (int) y + height, imgIndex * width, 0,
				imgIndex * width + width, height, IntroCanvas.getInstance());
	}

	public boolean contatins(int x, int y) {
		if ((this.x < x && x < this.x + width) && (this.y < y && y < this.y + height)) {
			return true;
		} else {
			return false;
		}
	}

	public void state(int stateValue) {
		this.stateValue = stateValue; // 이걸로 마우스올리면 색상 변함
	}

}
