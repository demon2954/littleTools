package beanmaker.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 340, HEIGHT = 120, COMBOX_WIDTH = 100;
	
	

	public MyPanel() {
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width; // 获取屏幕的宽
		int screenHeight = screenSize.height; // 获取屏幕的高
		int x = 0, y = 0;
		x = screenWidth / 2 - WIDTH / 2;
		y = screenHeight / 2 - HEIGHT / 2;
		this.setBounds(x, y, WIDTH, HEIGHT);
		this.setLayout(null);
		
		JTextField textFiels = new JTextField();
		textFiels.setBounds(0, 0, WIDTH - COMBOX_WIDTH, 25);
		textFiels.setText("请输入表名");
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("Double");
		comboBox.addItem("BigDecimal");
		comboBox.setBounds(WIDTH - COMBOX_WIDTH, 0, COMBOX_WIDTH, 25);
		this.add(comboBox);
		
		JButton button = new JButton();
		button.setBounds(0, 30, WIDTH, 60);
		button.setText("运行");
		this.add(button);
		button.addActionListener(new ButtonListener(textFiels, comboBox));
		
		

		this.add(textFiels);
	}
	
}
