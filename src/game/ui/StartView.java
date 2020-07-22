package game.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.interFace.Movable;

public class StartView implements Movable {
	private Image img;
	private double y;

	public StartView() {
		try {

			img = ImageIO.read(new File("res/images/IntroBack.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, 0, (int) y, IntroCanvas.getInstance());

		g.setColor(Color.darkGray);
		g.setFont(new Font("시작", Font.BOLD, 15));
		g.drawString("ver 4.02", 790, 740);
		g.drawString("Program by 전완기 임경원 김성령 ", 1170, 740);
	}

}
