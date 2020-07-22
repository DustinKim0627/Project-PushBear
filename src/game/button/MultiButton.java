package game.button;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.interFace.Movable;

public class MultiButton implements Movable {

	public static final int STATE_OFF = 0;
	public static final int STATE_ON = 1; // 약속된 변수로 public사용.
	public static final int STATE_CLICK = 2;
	public static final int STATE_PRESS = 3;
	public static final int STATE_RELEASE = 4;

	public static final Color COLOR_OFF = new Color(145, 175, 215);
	public static final Color COLOR_ON = new Color(115, 180, 235);
	public static final Color COLOR_CLICK = new Color(0, 255, 0);
	public static final Color COLOR_PRESS = new Color(185, 130, 230);
	public static final Color COLOR_RELEASE = new Color(185, 130, 230);

	private double x;
	private double y;
	private int width;
	private int height;
	private int stateValue;

	@Override
	public void update() {
		x = 550;
		y = 370;
		width = 270;
		height = 110;
	}

	@Override
	public void draw(Graphics g) {
		int x = (int) this.x;
		int y = (int) this.y;
		Color buttonColor;

		if (stateValue == STATE_ON) {
			buttonColor = COLOR_ON;
		} else if (stateValue == STATE_OFF) {
			buttonColor = COLOR_OFF;
		} else if (stateValue == STATE_PRESS) {
			buttonColor = COLOR_PRESS;
			g.setColor(Color.pink);
			g.fillRoundRect(x + 5, y + 5, width, height, 100, 100);
		} else if (stateValue == STATE_RELEASE) {
			buttonColor = COLOR_RELEASE;
		} else {
			buttonColor = COLOR_CLICK;
		}
		g.setColor(buttonColor);
		g.fillRoundRect(x, y, width, height, 100, 100);
		g.setColor(Color.darkGray);
		g.setFont(new Font("시작", Font.BOLD | Font.ITALIC, 50));
		g.drawString("같이하기", x + 20, y + 75);

	}

	public boolean contatins(int x, int y) {
		if ((this.x < x && x < this.x + width) && (this.y < y && y < this.y + height)) {
			return true;
		} else {
			return false;
		}

	}

	public void state(int stateValue) {
		this.stateValue = stateValue;
	}

}
