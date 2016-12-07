package com.nimei;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import com.swetake.util.Qrcode;

public class MakeQrcode {
	
	/**
	 * 生成二维码(QRCode)图片
	 * @param content 二维码图片的内容
	 * @param imgPath 生成二维码图片完整的路径
	 * @param ccbpath 二维码图片中间的logo路径
	 */
	public static int createQRCode(String content, String imgPath, String ccbPath) {
		try {
			Qrcode qrcodeHandler = new Qrcode();
			qrcodeHandler.setQrcodeErrorCorrect('M');
			qrcodeHandler.setQrcodeEncodeMode('B');
			qrcodeHandler.setQrcodeVersion(7);
			byte[] contentBytes = content.getBytes("gb2312");
			BufferedImage bufImg = new BufferedImage(140, 140, BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, 140, 140);
			// 设定图像颜色 > BLACK
			gs.setColor(Color.BLACK);
			// 设置偏移量 不设置可能导致解析出错
			int pixoff = 2;
			// 输出内容 > 二维码
			if (contentBytes.length > 0 && contentBytes.length < 120) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				System.err.println("QRCode content bytes length = "
						+ contentBytes.length + " not in [ 0,120 ]. ");
				return -1;
			}
			Image img = ImageIO.read(new File(ccbPath));// 实例化一个Image对象。
			gs.drawImage(img, 55, 55, null);
			gs.dispose();
			bufImg.flush();
			// 生成二维码QRCode图片
			File imgFile = new File(imgPath);
			ImageIO.write(bufImg, "png", imgFile);
		} catch (Exception e) {
			e.printStackTrace();
			return -100;
		}
		return 0;
	}
	
	/**
	 * javaee生成二维码输出到页面
	 * 页面上用此方法接收二维码图片url var link = "......../makeQRcode.do";
	 * @param link
	 * @param modelMap
	 * @param request
	 * @param response
	 */
	public void makeQRcode(String link, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		try {
			Qrcode qrcodeHandler = new Qrcode();
			qrcodeHandler.setQrcodeErrorCorrect('M');
			qrcodeHandler.setQrcodeEncodeMode('B');
			qrcodeHandler.setQrcodeVersion(7);
			byte[] contentBytes = link.getBytes("gb2312");
			BufferedImage bufImg = new BufferedImage(140, 140, BufferedImage.TYPE_INT_ARGB);
			Graphics2D gs = bufImg.createGraphics();
			gs.setBackground(Color.WHITE);
			gs.setClip(0, 0, 140, 140);
			// 设定图像颜色 > BLACK               
			gs.setColor(Color.BLACK);
			// 设置偏移量 不设置可能导致解析出错             
/*不需要二维码中间的logo*/
			int pixoff = 0;//  输出内容 > 二维码
			if (contentBytes.length > 0 && contentBytes.length < 120) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			}
/*-----------------------------------------------*/
/*需要二维码中间的logo*/		
			int pixoff = 2;//  输出内容 > 二维码
			if (contentBytes.length > 0 && contentBytes.length < 120) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			}
			File file = new File("/opt/resources/img/site/mobile/know_index/mama100logo.png");
			BufferedImage img = ImageIO.read(file);
			gs.drawImage(img, 50, 50, 40, 40, null);
/*-----------------------------------------------*/
			
			gs.dispose();
			bufImg.flush();

			InputStream is = null;
			bufImg.flush();
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ImageOutputStream imOut;
			imOut = ImageIO.createImageOutputStream(bs);
			ImageIO.write(bufImg, "png", imOut);
			is = new ByteArrayInputStream(bs.toByteArray());
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[1024];
			while (is.read(b) != -1) {
				os.write(b);
			}
			is.close();
			os.flush();
			os.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
}
}
