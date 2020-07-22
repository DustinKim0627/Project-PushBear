package game.main;

import java.awt.Frame;

import game.ui.GameFrame;

public class Program {

	public static void main(String[] args) {
		Frame frame = new GameFrame();
		frame.setVisible(true);
		frame.setSize(1450, 800 );
		frame.setResizable(false);
		frame.setTitle("Server");
		frame.setFocusable(true);
	}

}
