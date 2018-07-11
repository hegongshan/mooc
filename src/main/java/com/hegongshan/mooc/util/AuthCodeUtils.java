package com.hegongshan.mooc.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class AuthCodeUtils {
	private static final int AUTHCODE_LENGTH = 4;
	private static final int SINGLECODE_WIDTH = 15;
	private static final int SINGLECODE_HEIGHT = 30;
	private static final int SINGLECODE_GAP = 5;
	private static final int IMG_WIDTH = (SINGLECODE_WIDTH + SINGLECODE_GAP) * AUTHCODE_LENGTH;
	private static final int IMG_HEIGHT = SINGLECODE_HEIGHT;
	
	private static final String randomString = "0123456789abcdefghijknmlopqrstuvwxyzABCDEFGHIJKNMLOPQRSTUVWXYZ";
	
	public static String getAuthCode() {
		String authCode = "";
		for(int i = 0 ; i < AUTHCODE_LENGTH ; i++) {
			authCode += randomString.charAt(new Random().nextInt(62));
		}
		return authCode;
	}
	public static BufferedImage getAuthImg(String authCode) {
		BufferedImage img = new BufferedImage(IMG_WIDTH,IMG_HEIGHT,BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();//得到图片上的一个画笔
		g.setColor(Color.WHITE);//设置画笔的颜色，用来做背景色
		g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);//用画笔填充一个矩形，矩形的左上角坐标，宽，高
		g.setColor(Color.BLACK);//设置画笔的颜色，用来写字
		g.setFont(new Font("宋体",Font.PLAIN,SINGLECODE_HEIGHT + 5));//设置字体、不带格式的、字号
		
		//输出数字
		char c;
		for(int i = 0 ; i < authCode.toCharArray().length ; i++) {
			c = authCode.charAt(i);
			g.drawString(c + "", i * (SINGLECODE_WIDTH + SINGLECODE_GAP) + SINGLECODE_GAP / 2, IMG_HEIGHT);//内容、开始位置、高度
		}
		Random random = new Random();
		//干扰线
		for(int i = 0 ; i < 10 ; i++) {
			int x = random.nextInt(IMG_WIDTH);
			int y = random.nextInt(IMG_HEIGHT);
			int x2 = random.nextInt(IMG_WIDTH);
			int y2 = random.nextInt(IMG_HEIGHT);
			g.drawLine(x, y, x2, y2);
		}
		return img;
	}
}