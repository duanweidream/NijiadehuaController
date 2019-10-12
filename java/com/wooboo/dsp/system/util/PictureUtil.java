package com.wooboo.dsp.system.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class PictureUtil {

	
	public static BufferedImage createImg(){
		
		BufferedImage image = new BufferedImage(29, 29, BufferedImage.TYPE_INT_RGB);  
//        //获取图形上下文   
//	     Graphics g = image.getGraphics(); 
//        //设定背景色   
//	     Color color =  new Color(Integer.parseInt("4b77be", 16)) ; 
//	     g.setColor(color);   
//	     g.fillRect(0, 0, 29, 29); 
//	     g.setFont(new Font("宋体",Font.BOLD,20));  
//	     g.setColor(Color.white);     
//         g.drawString("乔",5,24);  
//	     g.dispose();
	     
	     
		Graphics2D g2=image.createGraphics();
		//g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);  
		
		
		g2.setColor(new Color(Integer.parseInt("4b77be", 16)));
		g2.fillRect(0, 0, 100, 100); 
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("宋体",Font.PLAIN,16));  
		g2.drawString("F", (29-16)/2, (29-16)/2+16);
		//g2.setColor(new Color(Integer.parseInt("4b77be", 16)));
		//g2.fill(sha);
		g2.dispose();
	   //g2.fill(sha);
	   g2.dispose();
		 return image;
	}
	
	
}
