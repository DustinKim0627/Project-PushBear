package game.item;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.interFace.Movable;
import game.ui.FightCanvas;

public class IceBerg implements Movable {
	private static Image iceBergImage; // 빙산 이미지
	private static IceBerg iceBerg;
	
	/* +++++++++++++빙산 변수++++++++++++++ */
	private int iceBergX, iceBergY; // 빙산 x,y좌표
	
	static {
		
		try {
			iceBergImage = ImageIO.read(new File("res/images/iceice.png"));
		} catch (IOException e) {
			e.printStackTrace();
		} // 빙산 이미지
		
	}
	
	public IceBerg() {
		iceBerg=this;
		
		/* +++++++++++++빙산 초기화 설정++++++++++++++ */
		iceBergX = 750; // 빙산 x축
		iceBergY = 220; // 빙산 y축
	}

	public static IceBerg getIceBerg() {
		return iceBerg;
	}
	
	public int getIceBergX() {
		return iceBergX;
	}

	public int getIceBergY() {
		return iceBergY;
	}

	public void plusIceBergX(int vx) {
		this.iceBergX+=vx;
	}
	
	public void minusIceBergX(int vx) {
		this.iceBergX-=vx;
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		
		/* +++++++++++ 빙산 이미지 그리기 +++++++++++++ */
//		System.out.println("iceBergX: "+iceBergX);
		int offsetX = 183 / 2; // 빙산 이미지의 x축 중심점 이동위해서 선언
		int offsetY = 400 / 2; // 빙산 이미지의 y축 중심점 이동위해서 선언

		int dx1 = (int) (iceBergX - offsetX);
		int dy1 = (int) (iceBergY - offsetY);
		int dx2 = (int) (iceBergX + 183 - offsetX);
		int dy2 = (int) (iceBergY + 400 - offsetY);
		int wallIndex = 0;
		int widthR=160;
//		g.drawImage(wallImg, (int) wallX - offsetWall, (int) wallY, FightCanvas.getInstacne());
		g.drawImage(iceBergImage, dx1, dy1 + 150, dx2, dy2 + 200,
				wallIndex * widthR, 0, wallIndex * widthR + 183, 400,
				FightCanvas.getInstance());
	}
	
	

	
	
}
