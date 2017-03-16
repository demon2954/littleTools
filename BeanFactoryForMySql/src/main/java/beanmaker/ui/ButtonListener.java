package beanmaker.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import beanmaker.bean.Bean;
import beanmaker.db.DAO;
import beanmaker.file.Writer;

public class ButtonListener implements ActionListener {
	private JTextField textFiels;
	JComboBox<String> comboBox;

	public ButtonListener(JTextField textFiels) {
		this.textFiels = textFiels;
	}

	public ButtonListener(JTextField textFiels, JComboBox<String> comboBox) {
		this.comboBox = comboBox;
		this.textFiels = textFiels;
	}

	public void actionPerformed(ActionEvent e) {
		boolean success = true;
		if("成功了！看一下D盘的general文件夹吧！".equals(textFiels.getText())){
			textFiels.setText("请输入表名");
			return;
		}
		if("请输入表名".equals(textFiels.getText().trim())){
			textFiels.setText("请输入表名");
			return;
		}
		try {
			String moneyType = comboBox.getSelectedItem()+"";
			List<Bean> beanList = DAO.query(textFiels.getText());
			Writer.writePOJO(beanList, moneyType);
			Writer.writeVO(beanList, moneyType);
		} catch (SQLException e1) {
			e1.printStackTrace();
			success = false;
		} catch (Exception e1) {
			e1.printStackTrace();
			success = false;
		} finally {
			if (success) {
				textFiels.setText("成功了！看一下D盘的general文件夹吧！");
			} else {
				textFiels.setText("失败了！");
			}
		}
	}

}
