package game.item;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.interFace.Movable;
import game.ui.FightCanvas;
import game.ui.SingleCanvas;

public class Present implements Movable { //산타가 던지는 선물 아이템
	private static Image image;
	private double x;
	private double y;
	private double vx; //이동할 단위 위치
	private double vy;

	private int width;
	private int height;
	private int imgIndex;
	
	private boolean visible=true;
	
	static { //함수 호출과 상관없이 프로그램이 실행되면서 한번 초기화됨 
		try {
			image=ImageIO.read(new File("res/images/present.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	
	public Present() {
		
		x=1430;
		y=71;
		
		vx=2;
		vy=2;
		
		imgIndex=0;
		
	}

	public Present(double x) { //산타 있는 x좌표
		
		width=image.getWidth(FightCanvas.getInstacne()); //ImageObserver(this)는 Canvas 클래스내에서 혹은 상속받은 상태에서 가능
		height=image.getHeight(FightCanvas.getInstacne());
		
		this.x=x+50;
		y=71;
		
		vy=2;
		imgIndex=0;
	}

	@Override
	public void update() {
		if((200<x&&x<1300)&&y==565) { //빙하 필드위에 떨어지면 멈춤
			vy=0;
			vx=0;
		}
		
		this.x-=vx;
		this.y+=vy;
	}
	
	
	@Override
	public void draw(Graphics g) {
		int offsetX = width / 2; // 이미지의 x축 중심점 이동위해서 선언
		int offsetY = height / 2; // 이미지의 y축 중심점 이동위해서 선언
		int dx1 = (int) (x - offsetX);
		int dy1 = (int) (y - offsetY);
		int dx2 = (int) (x+width - offsetX);
		int dy2 = (int) (y + height - offsetY);
		
		//좌측 곰 아이템 획득시
		if(((Character.getBearImg_leftDx2()-50<dx2&&dx2<Character.getBearImg_leftDx2()))&&
				(Character.getBearImg_leftDy1()+45<dy2&&dy2<615)) { //좌측 곰 머리~발 아이템 닿으면
			visible=false;
			
			//아이템 사라짐
			g.drawImage(image, 0, 0, 0, 0,  
					imgIndex*width, 0, imgIndex*width+width, 0+height, FightCanvas.getInstacne());
		}else if(visible) 
			g.drawImage(image, dx1, dy1, dx2-45, dy2-45, 
				imgIndex*width, 0, imgIndex*width+width, 0+height, FightCanvas.getInstacne());	
		
		//우측 곰 아이템 획득시
		if(((Character.getBearImg_rightDx2()-50<dx2&&dx2<Character.getBearImg_rightDx2()))&&
				(Character.getBearImg_rightDy1()+45<dy2&&dy2<615)) { //우측 곰 머리~발 아이템 닿으면
			visible=false;
			
			//아이템 사라짐
			g.drawImage(image, 0, 0, 0, 0, 
					imgIndex*width, 0, imgIndex*width+width, 0+height, FightCanvas.getInstacne());
		}else if(visible) 
			g.drawImage(image, dx1, dy1, dx2-45, dy2-45, 
				imgIndex*width, 0, imgIndex*width+width, 0+height, FightCanvas.getInstacne());	
		
				
	}
	
	
}
