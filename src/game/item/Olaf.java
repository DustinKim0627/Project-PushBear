package game.item;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.interFace.Movable;
import game.ui.FightCanvas;

public class Olaf implements Movable {
	private static Image olafImage; //올라프 이미지
	
	private double x; //올라프 이동 위치
	private double y;
	private double vx; //이동할 단위 위치
	
	private int width;
	private int height;
	private int timeout;
	private int imgIndex;
	
	static {
		try {
			olafImage=ImageIO.read(new File("res/images/olaf.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Olaf() {
		timeout=3;
		
		width=(olafImage.getWidth(FightCanvas.getInstacne())); //ImageObserver(this)는 Canvas 클래스내에서 혹은 상속받은 상태에서 가능
		height=olafImage.getHeight(FightCanvas.getInstacne());
		
		x=0;
		y=100;
		
		vx=5;
		
		imgIndex=0;
		
	}
	
	@Override
	public void update() {
		this.x+=vx;
		
		if (timeout-- == 0) { //올라프 출현 간격
			timeout=40;
		}
	}
	
	public Snow throwSnow() { //눈 던지는 메소드
		return new Snow(x);
	}

	@Override
	public void draw(Graphics g) {
		int offsetX = width / 2; // 이미지의 x축 중심점 이동위해서 선언
		int offsetY = height / 2; // 이미지의 y축 중심점 이동위해서 선언
		int dx1 = (int) (x - offsetX);
		int dy1 = (int) (y - offsetY);
		int dx2 = (int) (x+width - offsetX);
		int dy2 = (int) (y + height - offsetY);
		
		g.drawImage(olafImage, dx1, dy1, dx2, dy2, 
				imgIndex*width, 0, imgIndex*width+width, 0+height, FightCanvas.getInstacne());
		
	}
	
}
