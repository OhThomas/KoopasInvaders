package com.game.src.main;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image){
		this.image = image;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height){
		
		BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
		return img;
	}
	
	public BufferedImage grabSmallImage(int col, int row, int width, int height){
		
		BufferedImage imgs = image.getSubimage((col * 16) - 16, (row * 16) - 16, width, height);
		return imgs;
	}
	
public BufferedImage grabExactImage(int col, int row, int width, int height){
		
		BufferedImage imge = image.getSubimage(col, row, width, height);
		return imge;
	}
	
	public BufferedImage grabMarioImage(int col, int row, int width, int height){
		
		BufferedImage imgm = image.getSubimage((col * 16) - 16, (row * 28) - 28, width, height);
		return imgm;
	}
	
	public BufferedImage grab6pxImage(int col, int row, int width, int height){
		
		BufferedImage img6px = image.getSubimage((col * 6) - 6, (row * 6) - 6, width, height);
		return img6px;
	}
}
