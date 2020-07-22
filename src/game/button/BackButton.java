package game.button;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.interFace.Movable;
import game.ui.FightCanvas;

public class BackButton implements Movable {
	private static Image image;	//back

	private BackButton backButton;

	public static final int STATE_OFF = 0;
	public static final int STATE_ON = 1; // 약속된 변수로 public사용.
	public static final int STATE_CLICK = 2;
	public static final int STATE_PRESS = 3;
	public static final int STATE_RELEASE = 4;

	private double x;
	private double y;
	
	private int width;
	private int height;
	private int stateValue;
	

	
	public BackButton() {
		try {
			image= ImageIO.read(new File("res/images/back.png")); // 설정 이미지
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		x = 1270;
		y = 650;
		width = 100;
		height = 100;
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics g) {
		int x = (int)this.x;
		int y = (int)this.y;
		g.drawImage(image, x+80, y, FightCanvas.getInstacne());
		
	}
	
	public boolean contains(int x, int y) {
		if ((this.x < x && x < this.x + width) && (this.y < y && y < this.y + height)) 
			return true;
		else 
			return false;
	}
	
	public void state(int stateValue) {
		this.stateValue = stateValue;
	}
}
		


