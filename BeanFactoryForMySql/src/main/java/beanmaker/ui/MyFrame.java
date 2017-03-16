package beanmaker.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 340, HEIGHT = 120, COMBOX_WIDTH = 100;
	
	public MyFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width; // 获取屏幕的宽
		int screenHeight = screenSize.height; // 获取屏幕的高
		int x = 0, y = 0;
		x = screenWidth / 2 - WIDTH / 2;
		y = screenHeight / 2 - HEIGHT / 2;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setBounds(x, y, WIDTH, HEIGHT);
		this.setTitle("一键生Bean");
		this.setResizable(false);
	}
	
	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
		MyPanel panel = new MyPanel();
		frame.add(panel);
		

		
		frame.setVisible(true);
	}
}
