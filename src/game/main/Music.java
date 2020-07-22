package game.main;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {
	private AudioInputStream fighterStream;
	private AudioInputStream ahStream;
	private AudioInputStream stream;
	private AudioInputStream introStream;
	private AudioInputStream singleStream;
	private AudioInputStream onlineStream;
	private AudioInputStream wooStream;
	public Clip introClip;
	public Clip ahClip;
	public Clip singleClip;
	public Clip onlineClip;
	public Clip fighterClip;
	public Clip wooClip;
	private File introFile;
	private File ahFile;
	private File singleFile;
	private File onlineFile;
	private File fightFile;
	private File effectFile;
	private Clip effectclip;
	private File wooFile;

//	private boolean isLoop; // 곡을 무한히 재생할건지 한번만 할건지 지정해주는 역활을 한다.
	public Music() {
		introFile = new File("res/music/bgm/introBGM-fortress.wav");
		ahFile = new File("res/music/bgm/Ahahh.wav");
		singleFile = new File("res/music/bgm/BGM_Single.wav");
		onlineFile = new File("res/music/bgm/BGM_Multi2.wav");
		fightFile = new File("res/music/bgm/BGM_Multi.wav");
		effectFile = new File("res/music/bgm/Bell3.wav");
		wooFile = new File("res/music/bgm/wooo.wav");
	}

	public void wooStart() { // 음악 시작

		try {
			wooStream = AudioSystem.getAudioInputStream(wooFile);
			wooClip = AudioSystem.getClip();
			wooClip.open(wooStream);

		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wooClip.start();

	}
	public void ahStart() { // 음악 시작

		try {
			ahStream = AudioSystem.getAudioInputStream(ahFile);
			ahClip = AudioSystem.getClip();
			ahClip.open(ahStream);

		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ahClip.start();

	}

	public void IntroMusicStart() { // 음악 시작

		try {
			introStream = AudioSystem.getAudioInputStream(introFile);
			introClip = AudioSystem.getClip();
			introClip.open(introStream);

		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		introClip.start();

	}

	public void singleMusicStart() { // 음악 시작

		try {
			singleStream = AudioSystem.getAudioInputStream(singleFile);
			singleClip = AudioSystem.getClip();
			singleClip.open(singleStream);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		singleClip.start();

	}

	public void onlineMusicStart() { // 음악 시작

		try {
			onlineStream = AudioSystem.getAudioInputStream(onlineFile);
			onlineClip = AudioSystem.getClip();
			onlineClip.open(onlineStream);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		onlineClip.start();

	}

	public void fighterMusicStart() { // 음악 시작

		try {
			fighterStream = AudioSystem.getAudioInputStream(fightFile);
			fighterClip = AudioSystem.getClip();
			fighterClip.open(fighterStream);
			fighterClip.start();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void effectMusic() { // 음악 시작

		try {
			stream = AudioSystem.getAudioInputStream(effectFile);
			effectclip = AudioSystem.getClip();
			effectclip.open(stream);
			effectclip.start();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void introStop() { // 음악 끝내기
		introClip.stop();
		introClip.close();
	}

	public void singleStop() { // 음악 끝내기
		singleClip.stop();
		singleClip.close();
	}

	public void fighterStop() { // 음악 끝내기
		fighterClip.stop();
		fighterClip.close();
	}
	public void ahStop() { // 음악 끝내기
		ahClip.stop();
		ahClip.close();
	}
	public void wooStop() { // 음악 끝내기
		wooClip.stop();
		wooClip.close();
	}

	public void onlineStop() { // 음악 끝내기
		onlineClip.stop();
		onlineClip.close();
	}
	

}

//package game.main;
//
//import java.io.File;
//import java.io.IOException;
//
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;
//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.UnsupportedAudioFileException;
//
//public class Music {
//
////	private boolean isLoop; // 곡을 무한히 재생할건지 한번만 할건지 지정해주는 역활을 한다.
//	
//	private AudioInputStream stream; 
//	private File file;
////	private Clip clip;
//	public   Clip clip;
//	
//	public static Music singleMusic;
//	public static Music multiMusic;
//
//	public Music(String name, boolean isLoop) {
////		this.isLoop = isLoop;
//
//		try {
//			File file = new File("res/music/bgm/"+name); // 음악 파일명
//			stream = AudioSystem.getAudioInputStream(file);
//			clip = AudioSystem.getClip();
//
//		} catch (UnsupportedAudioFileException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (LineUnavailableException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		if (name.equals("BGM_Single.wav")) {
//			singleMusic = this;
//		} else if (name.equals("BMG_Multi.wav")) {
//			multiMusic = this;
//		}
//
//	}
//
//	public void musicStart() { //음악 시작
//		try {
//			clip.open(stream);
//		} catch (LineUnavailableException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		clip.start();
//	}
//	
//	public void musicStop() { //음악 끝내기
//		clip.stop();
//		clip.close();
//	}
//	
//	public static Music getSingleInstance() {
//		return singleMusic;
//	}
//
//	public static Music getMultiInstance() {
//		return multiMusic;
//	}
//}
//		fis=new FileInputStream(file);
//		bis=new BufferedInputStream(fis);
//		player=new Player(bis); //입력 스트림을 받아서 직접 찾은 오디오 장치에 디코딩한 MP3 내용을 출력하는 것

//	public static void Main_Sound(String file) {
//		File file= new File("/Music/"+name);
//		AudioInputStream audioInputStream=
//				AudioSystem.getAudioInputStream(file)
//	}

//	private Player player; //jar 삽입해서 사용할수 있는 인스턴스 변수 이다
//	private boolean isLoop; // 곡을 무한히 재생할건지 한번만 할건지 지정해주는 역활을 한다.
//	private File file;
//	private FileInputStream fis;
//	private BufferedInputStream bis;

//	public Music(String name, boolean isLoop) {
//		try {
//			this.isLoop=isLoop;
//			file = new File(Program.class.getResource("/Music/"+name).toURI());
//		fis=new FileInputStream(file);
//		bis=new BufferedInputStream(fis);
//		player=new Player(bis); //입력 스트림을 받아서 직접 찾은 오디오 장치에 디코딩한 MP3 내용을 출력하는 것
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
//	
//	public int getTime() {
//		if(player==null)
//			return 0;
//		return player.getPosition();
//	}
//	
//	public void close() {
//		isLoop=false;
//
//		player.close();
//		this.interrupt();
//	}
//	
//	
//	public void run() {
//		try {
//			do {
//				player.play();
//				fis=new FileInputStream(file); //FileInputStream 클래스는 
//				//InputStream 클래스를 상속받은 후손 클래스로 하드 디스크상에 존재하는 파일로부터 
//				//바이트 단위의 입력을 받는 클래스이다. 
//				//이 클래스는 출발 지점과 도착 지점을 연결하는 통로, 즉 스트림을 생성하는 클래스이다.
//				bis=new BufferedInputStream(fis);
//				//원하는 자료를 1바이트 단위로 읽는 read() 메소드를 수행하면 시스템 내부적으로 
//				//버퍼를 준비하고 이 버퍼를 이용하여 지정된 파일로부터 버퍼의 크기만큼 한꺼번에 많은 데이터를 가져온다.
//				player=new Player(bis);
//			}while(isLoop);
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}

