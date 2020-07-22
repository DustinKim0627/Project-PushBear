package game.item;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.interFace.Movable;
import game.ui.FightCanvas;
import game.ui.GameFrame;
import game.ui.OnlineCanvas;
import game.ui.SingleCanvas;

public class Sun implements Movable {
	private static Image sunImg, sunImg2; // 곰 이미지
	private static Sun sun;
	public static boolean contain;
	
	/* +++++++++++++해 변수++++++++++++++ */
	private int sunX, sunY;

	static {

		try {
			sunImg = ImageIO.read(new File("res/images/sunrise.png")); // 해 이미지
			sunImg2 = ImageIO.read(new File("res/images/sunrise2.png")); // 강사님 이미지
		} catch (IOException e) {
			e.printStackTrace();
		} // 빙산 이미지
	}
	
	public Sun() {
		sunX = 10;
		sunY = 10;
		contain = false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		/* +++++++++++ 해 이미지 그리기 +++++++++++++ */
		
		if (GameFrame.canvasId == 1) {
			System.out.println("GameFrame.canvasId==1:" + GameFrame.canvasId);
			if (sun.contain == true)
				g.drawImage(sunImg2, sunX + 1130, sunY - 200, SingleCanvas.getInstacne());

			else
				g.drawImage(sunImg, sunX + 1130, sunY - 200, SingleCanvas.getInstacne());
		}
		else if(GameFrame.canvasId==2) {
			System.out.println("GameFrame.canvasId==2:" + GameFrame.canvasId);
			if (sun.contain == true)
				g.drawImage(sunImg2, sunX + 1130, sunY - 200, FightCanvas.getInstacne());

			else
				g.drawImage(sunImg, sunX + 1130, sunY - 200, FightCanvas.getInstacne());
		}
		else if(GameFrame.canvasId==3) {
			System.out.println("GameFrame.canvasId==3:" + GameFrame.canvasId);
			if (sun.contain == true)
				g.drawImage(sunImg2, sunX + 1130, sunY - 200, OnlineCanvas.getInstacne());

			else
				g.drawImage(sunImg, sunX + 1130, sunY - 200, OnlineCanvas.getInstacne());
		}
	}

}
