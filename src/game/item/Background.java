package game.item;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.interFace.Movable;
import game.ui.FightCanvas;

public class Background implements Movable {
	private Image stadiumImg;
	private Image cloudImg;
	private double x;
	private double vx;
	private int cloudImg_x;
	private int cloudImg_vx;
	private int stadiumImg_x;
	private int stadiumImg_vx;

	public Background() {
		try {
			stadiumImg = ImageIO.read(new File("res/images/realbackground.png"));
			cloudImg = ImageIO.read(new File("res/images/newcloud.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		x = 0;
		vx = 1;
		cloudImg_x = 0;
		cloudImg_vx = 1;
		stadiumImg_x=1450;
		stadiumImg_vx = 1;
	}
	public void update() {
		this.cloudImg_x -= cloudImg_vx;
		this.stadiumImg_x -= stadiumImg_vx;
	}

	public void draw(Graphics g) {
		g.drawImage(cloudImg, (int) cloudImg_x, 0, FightCanvas.getInstacne());
		g.drawImage(cloudImg, (int) stadiumImg_x, 0, FightCanvas.getInstacne());
		g.drawImage(stadiumImg, (int) x, 300, FightCanvas.getInstacne());
		if (cloudImg_x == -1450)
			cloudImg_x = 1450;
		if(stadiumImg_x==-1450)
			stadiumImg_x=1450;
	}
}