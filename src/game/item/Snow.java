package game.item;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.interFace.Movable;
import game.ui.FightCanvas;

public class Snow implements Movable {
	private static Image image;
	private static Snow snow;

	private double x;
	private double y;
	private double vx; // 이동할 단위 위치
	private double vy;
	
	private int width;
	private int height;

	private int dx1;// 이미지 화면에 출력 될 x축
	private int dy1;// 이미지 화면에 출력 될 y축
	private int dx2;// 이미지 화면에 출력 될 대각선 x축
	private int dy2;// 이미지 화면에 출력 될 대각선 y축
	private int offsetX;
	private int offsetY;

	private int timeout;
	private int imgIndex;

	private boolean visible = true;

	static { // 함수 호출과 상관없이 프로그램이 실행되면서 한번 초기화됨
		try {
			image = ImageIO.read(new File("res/images/snow.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Snow() {
		dx1 = 0;
		dx2 = 0;
		dy1 = 0;
		dy2 = 0;
	}

	public Snow(double x) { // 올라프 있는 x좌표
		snow = this;
		width = (image.getWidth(FightCanvas.getInstacne())) / 6; // ImageObserver(this)는 Canvas 클래스내에서 혹은 상속받은 상태에서 가능
		height = image.getHeight(FightCanvas.getInstacne());

		this.x = x - 50;
		y = 71;

		vy = 3;
		imgIndex = 0;

	}

	public Snow getSnow() {
		return snow;
	}


	@Override
	public void update() {
		this.x -= vx;
		this.y += vy;

		if (timeout-- == 0) { // 산타 출현 간격
			imgIndex++;
			imgIndex %= 6; // 0~11까지 -> 이미지 인덱스가 바뀌면서 ufo가 회전하게됨
			timeout = 40;
		}

		offsetX = width / 2; // 이미지의 x축 중심점 이동위해서 선언
		offsetY = height / 2; // 이미지의 y축 중심점 이동위해서 선언

		dx1 = (int) (x - offsetX);
		dy1 = (int) (y - offsetY);
		dx2 = (int) (x + width - offsetX);
		dy2 = (int) (y + height - offsetY);
	}

	@Override
	public void draw(Graphics g) {

		// 좌측 곰 눈덩이 맞을 때
		if (((Character.getBearImg_leftDx1() - 15 < dx1 && dx1 < Character.getBearImg_leftDx1() - 15 + 126))
				&& (Character.getBearImg_leftDy1() + 30 < dy2 - 140 && dy2 - 140 < 615)) { // 좌측 곰 머리~발 아이템 닿으면
			visible = false;

			// 아이템 사라짐
			g.drawImage(image, 0, 0, 0, 0, imgIndex * width, 0, imgIndex * width + width, 0 + height,
					FightCanvas.getInstacne());
			Character.getCharacter().bearL_freezing();
		} else if (visible)
			g.drawImage(image, dx1, dy1, dx2 - 124, dy2 - 124, imgIndex * width, 0, imgIndex * width + width,
					0 + height, FightCanvas.getInstacne());

		// 우측 곰 눈덩이 맞을 때
		if (((Character.getBearImg_rightDx1() + 15 < dx1 && dx1 < Character.getBearImg_rightDx1() + 125))
				&& (Character.getBearImg_rightDy1() + 15 < dy2 - 140 && dy2 - 124 < 615)) { // 우측 곰 머리~발 아이템 닿으면
			visible = false;
			// 아이템 사라짐
			g.drawImage(image, 0, 0, 0, 0, imgIndex * width, 0, imgIndex * width + width, 0 + height,
					FightCanvas.getInstacne());
				Character.getCharacter().bearR_freezing();
				
		} else if (visible)
			g.drawImage(image, dx1, dy1, dx2 - 124, dy2 - 124, imgIndex * width, 0, imgIndex * width + width,
					0 + height, FightCanvas.getInstacne());

	} // end draw
	
	

	public int getDx1() {
		return dx1;
	}

	public void setDx1(int dx1) {
		this.dx1 = dx1;
	}

	public int getDy1() {
		return dy1;
	}

	public void setDy1(int dy1) {
		this.dy1 = dy1;
	}

	public int getDx2() {
		return dx2;
	}

	public void setDx2(int dx2) {
		this.dx2 = dx2;
	}

	public int getDy2() {
		return dy2;
	}

	public void setDy2(int dy2) {
		this.dy2 = dy2;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}
