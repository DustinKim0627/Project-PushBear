package game.button;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.interFace.Movable;

public class ExitButton implements Movable {
	public static final int STATE_OFF = 0; // 버튼 범위에서 벗어날 때
	public static final int STATE_ON = 1; // 버튼 범위에 들어올 때
	public static final int STATE_CLICK = 2; // 버튼 클릭할 때
	public static final int STATE_PRESS = 3; // 버튼 클릭한 순간
	public static final int STATE_RELEASE = 4; // 버튼에서 클릭 후 띌 때
	public static final int STATE_CLOSE = 5;

	public static final Color COLOR_OFF = new Color(145, 175, 215);
	public static final Color COLOR_ON = new Color(255, 50, 50);
	public static final Color COLOR_CLICK = new Color(0, 255, 0);
	public static final Color COLOR_PRESS = new Color(255, 50, 50);
	public static final Color COLOR_RELEASE = new Color(185, 130, 230);

	private double x;
	private double y;
	private int width;
	private int height;
	private int stateValue;

	@Override
	public void update() {
		x = 550;
		y = 500;
		width = 550;
		height = 100;
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
		g.setFont(new Font("시작", Font.BOLD | Font.ITALIC, 60));
		g.drawString("종료....?", x + 160, y + 75);
	}

	public boolean contatins(int x, int y) {
		if ((this.x < x && x < this.x + width) && (this.y < y && y < this.y + height)) {
			return true;
		} else {
			return false;
		}
	}

	public void state(int stateValue) {
		this.stateValue = stateValue; // 마우스를 버튼위에 위치하면 색상 변하게 하기 위한 매개변수값 저장
	}

}
