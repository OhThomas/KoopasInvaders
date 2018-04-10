package com.game.src.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

public class BufferedImageLoader {

	private BufferedImage image;
	private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	private String currentNumberString = "";
	private int currentNumber = 0;
	Pattern p = Pattern.compile("[0-9]");
	
	public BufferedImage loadImage(String path) throws IOException{
		image = ImageIO.read(getClass().getResource(path));
		return image;
	}
	
	public ArrayList<BufferedImage> loadImagesfromFolder(String folder) throws IOException{
		File f = new File("." + folder);
		File[] fa = f.getCanonicalFile().listFiles();
		System.out.println(f.getAbsolutePath());
		for (int i=0; i< fa.length; i++){
			System.out.println(fa[i].toString());
			Matcher m = p.matcher(fa[i].toString());
			while(m.find())
				currentNumberString += m.group();
			currentNumber = Integer.valueOf(currentNumberString);
			BufferedImage img = ImageIO.read(fa[i]);
			while(images.size() <= currentNumber)
				images.add(img);
			images.set(currentNumber, img);
			System.out.println(currentNumber);
			currentNumberString = "";
		}
		return images;
	}
}
