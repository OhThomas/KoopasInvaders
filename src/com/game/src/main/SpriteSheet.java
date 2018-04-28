/*
 * 
 */
package com.game.src.main;

import java.awt.image.BufferedImage;

// TODO: Auto-generated Javadoc
/**
 * The Class SpriteSheet.
 */
public class SpriteSheet {

	/** The image. */
	private BufferedImage image;
	
	/**
	 * Instantiates a new sprite sheet.
	 *
	 * @param image the image
	 */
	public SpriteSheet(BufferedImage image){
		this.image = image;
	}
	
	/**
	 * Grab image.
	 *
	 * @param col the col
	 * @param row the row
	 * @param width the width
	 * @param height the height
	 * @return the buffered image
	 */
	public BufferedImage grabImage(int col, int row, int width, int height){
		
		BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
		return img;
	}
	
	/**
	 * Grab small image.
	 *
	 * @param col the col
	 * @param row the row
	 * @param width the width
	 * @param height the height
	 * @return the buffered image
	 */
	public BufferedImage grabSmallImage(int col, int row, int width, int height){
		
		BufferedImage imgs = image.getSubimage((col * 16) - 16, (row * 16) - 16, width, height);
		return imgs;
	}
	
/**
 * Grab exact image.
 *
 * @param col the col
 * @param row the row
 * @param width the width
 * @param height the height
 * @return the buffered image
 */
public BufferedImage grabExactImage(int col, int row, int width, int height){
		
		BufferedImage imge = image.getSubimage(col, row, width, height);
		return imge;
	}
	
	/**
	 * Grab mario image.
	 *
	 * @param col the col
	 * @param row the row
	 * @param width the width
	 * @param height the height
	 * @return the buffered image
	 */
	public BufferedImage grabMarioImage(int col, int row, int width, int height){
		
		BufferedImage imgm = image.getSubimage((col * 16) - 16, (row * 28) - 28, width, height);
		return imgm;
	}
	
	/**
	 * Grab 6 px image.
	 *
	 * @param col the col
	 * @param row the row
	 * @param width the width
	 * @param height the height
	 * @return the buffered image
	 */
	public BufferedImage grab6pxImage(int col, int row, int width, int height){
		
		BufferedImage img6px = image.getSubimage((col * 6) - 6, (row * 6) - 6, width, height);
		return img6px;
	}
}
