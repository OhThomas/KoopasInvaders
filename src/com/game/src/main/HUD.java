package com.game.src.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class HUD {

	private Textures tex;
	private Game game;
	public static float HEALTH = 0;
	private float greenValue = 255;
	private long score = 0;
	private String scoreString;
	private int level = 1;
	private boolean itemObtained = false;
	private String itemName = null;
	private double timer1 = 100;
	private double timer2 = 100;
	private float imageTranslucent = 0;
	private double imageTranslucentVelocity = 0;
	private long imageTranslucentTimer = 0;
	private boolean imageIsGone = false;
	private BufferedImage[] marioNumbersSmall = new BufferedImage[10];
	private BufferedImage[] mario3FontNumbersSmall = new BufferedImage[11];
	private BufferedImage marioAdditionSign = null;
	private BufferedImage itemFrame = null;
	private BufferedImage chainChompItemFrameDisplay = null;
	private BufferedImage pressE = null;
	
	public HUD(Textures tex, Game game){
		this.tex = tex;
		this.game = game;
		this.marioNumbersSmall = tex.marioNumbersSmall;
		this.mario3FontNumbersSmall = tex.mario3FontNumbersSmall;
		this.marioAdditionSign = tex.marioNumbersSmall[11];
		this.itemFrame = tex.itemFrame;
		this.chainChompItemFrameDisplay = tex.chainChompItemFrameDisplay;
		this.pressE = tex.pressE;
		
	}
	
	public void tick(){
		if(game.getSpawnDone4()){
			if(timer1 > 0){
				HEALTH++;
				timer1--;
			}
			if(timer1 <= 0 && HEALTH <= 0){
				if(timer2 > 0)
					timer2--;
			}
			HEALTH = Game.clamp(HEALTH, 0, 100);
			greenValue = Game.clamp(greenValue, 0, 255);
			greenValue = HEALTH*2;
		}
		if(itemObtained){
			if(imageTranslucent >= 0.99 && imageTranslucentTimer < System.currentTimeMillis()){
				imageTranslucentVelocity += -0.01;
				imageIsGone = false;
			}
			else if(imageTranslucent <= 0.01 && imageTranslucentTimer < System.currentTimeMillis()){
				imageTranslucentVelocity += 0.01;
			}
			
			if(imageTranslucent <= 0.01 && imageIsGone == false){
				imageTranslucentTimer = System.currentTimeMillis() + 500;
				imageIsGone = true;
			}
			if(imageTranslucent + imageTranslucentVelocity <= 1 &&
					imageTranslucent + imageTranslucentVelocity >= 0)
				imageTranslucent += imageTranslucentVelocity;
		}
	}
	
	public void render(Graphics g){
		if(!game.getUserHasPaused()){
			if(game.getSpawnDone4()){
				//HEALTH BAR APPEARS
				if(timer1 > 0){
					g.setColor(Color.gray);
					g.fillRect(15, 15, (int)HEALTH * 2, 32); //(x,x,200,x)
					g.setColor(new Color(75,(int)greenValue,0));
					g.fillRect(15, 15, (int)HEALTH * 2, 32);
					g.setColor(Color.white);
					g.drawRect(15, 15, (int)HEALTH * 2, 32); //(x,x,200,x)
					g.drawString(HEALTH+"%", 15, 15);
				}
				else if (timer1 <= 0 && HEALTH > 0){
					g.setColor(Color.gray);
					g.fillRect(15, 15, 200, 32); //(x,x,200,x)
					g.setColor(new Color(75,(int)greenValue,0));
					g.fillRect(15, 15, (int)HEALTH * 2, 32);
					g.setColor(Color.white);
					g.drawRect(15, 15, 200, 32); //(x,x,200,x)
					g.drawString(HEALTH+"%", 15, 15);
				}
				//HEALTH BAR DEPLETES
				else if(timer1 <= 0 && HEALTH <= 0){
					if(timer2 <= 0)
						return;
					g.setColor(Color.gray);
					g.fillRect(15, 15, (int)timer2 * 2, 32); //(x,x,200,x)
					g.setColor(Color.white);
					g.drawRect(15, 15, (int)timer2 * 2, 32); //(x,x,200,x)
				}
			}
			scoreString = String.format("%d", this.score);
			stringToScore(g, scoreString);
			g.drawImage(itemFrame, Game.WIDTH,0, null);
			if(itemObtained == true){
				if (itemName == "chainChompItem")
					g.drawImage(chainChompItemFrameDisplay, Game.WIDTH+2,2, null);
				
				Graphics2D g2d = (Graphics2D)g.create();
				g2d.setComposite(makeComposite(imageTranslucent));
				g2d.drawImage(pressE,Game.WIDTH-10,28,null);
			}
		}
		else if(game.getVisualPauseTimer() > System.currentTimeMillis() || game.getPauseSoundFXTimer() > System.currentTimeMillis()){
			if(timer1 > 0){
				g.setColor(Color.gray);
				g.fillRect(15, 15, (int)HEALTH * 2, 32); //(x,x,200,x)
				g.setColor(new Color(75,(int)greenValue,0));
				g.fillRect(15, 15, (int)HEALTH * 2, 32);
				g.setColor(Color.white);
				g.drawRect(15, 15, (int)HEALTH * 2, 32); //(x,x,200,x)
				g.drawString(HEALTH+"%", 15, 15);
			}
			else if (timer1 <= 0 && HEALTH > 0){
				g.setColor(Color.gray);
				g.fillRect(15, 15, 200, 32); //(x,x,200,x)
				g.setColor(new Color(75,(int)greenValue,0));
				g.fillRect(15, 15, (int)HEALTH * 2, 32);
				g.setColor(Color.white);
				g.drawRect(15, 15, 200, 32); //(x,x,200,x)
				g.drawString(HEALTH+"%", 15, 15);
			}
			//HEALTH BAR DEPLETES
			else if(timer1 <= 0 && HEALTH <= 0){
				if(timer2 <= 0)
					return;
				g.setColor(Color.gray);
				g.fillRect(15, 15, (int)timer2 * 2, 32); //(x,x,200,x)
				g.setColor(Color.white);
				g.drawRect(15, 15, (int)timer2 * 2, 32); //(x,x,200,x)
			}
			stringToScore(g, scoreString);
			g.drawImage(itemFrame, Game.WIDTH,0, null);
			if(itemObtained == true){
				if (itemName == "chainChompItem")
					g.drawImage(chainChompItemFrameDisplay, Game.WIDTH+2,2, null);
				
				Graphics2D g2d = (Graphics2D)g.create();
				g2d.setComposite(makeComposite(imageTranslucent));
				g2d.drawImage(pressE,Game.WIDTH-10,28,null);
			}
		}
			
		//BufferedImage img = stringToBufferedImage(marioNumbersSmall, scoreString);
		//g.drawImage(img, Game.WIDTH, 0, null);
		//g.drawString("Level: " + level, 170, 64);
	}
	public void stringToScore(Graphics g, String string){
		int x = Game.WIDTH * Game.SCALE;
		int y = 0;
		int k = 0;
		for(int i = string.length() - 1; i >= 0; i--){
			if(k % 3 == 0 && k != 0){
				x -= mario3FontNumbersSmall[10].getWidth();
				g.drawImage(mario3FontNumbersSmall[10], x, y, null);
			}
			
			if(string.charAt(i) == '0'){
				x -= mario3FontNumbersSmall[0].getWidth();
				g.drawImage(mario3FontNumbersSmall[0], x, y, null);
			}else if(string.charAt(i) == '1'){
				x -= mario3FontNumbersSmall[1].getWidth();
				g.drawImage(mario3FontNumbersSmall[1], x, y, null);
			}else if(string.charAt(i) == '2'){
				x -= mario3FontNumbersSmall[2].getWidth();
				g.drawImage(mario3FontNumbersSmall[2], x, y, null);
			}else if(string.charAt(i) == '3'){
				x -= mario3FontNumbersSmall[3].getWidth();
				g.drawImage(mario3FontNumbersSmall[3], x, y, null);
			}else if(string.charAt(i) == '4'){
				x -= mario3FontNumbersSmall[4].getWidth();
				g.drawImage(mario3FontNumbersSmall[4], x, y, null);
			}else if(string.charAt(i) == '5'){
				x -= mario3FontNumbersSmall[5].getWidth();
				g.drawImage(mario3FontNumbersSmall[5], x, y, null);
			}else if(string.charAt(i) == '6'){
				x -= mario3FontNumbersSmall[6].getWidth();
				g.drawImage(mario3FontNumbersSmall[6], x, y, null);
			}else if(string.charAt(i) == '7'){
				x -= mario3FontNumbersSmall[7].getWidth();
				g.drawImage(mario3FontNumbersSmall[7], x, y, null);
			}else if(string.charAt(i) == '8'){
				x -= mario3FontNumbersSmall[8].getWidth();
				g.drawImage(mario3FontNumbersSmall[8], x, y, null);
			}else if(string.charAt(i) == '9'){
				x -= mario3FontNumbersSmall[9].getWidth();
				g.drawImage(mario3FontNumbersSmall[9], x, y, null);
			}
			k++;
		}
	}
	//COMMAS DISABLED vvv
	public void stringToScore(Graphics g, BufferedImage[] font, int xPosition, int yPosition, String string, Boolean b){
		//int k = 0;
		if(b == false){
			g.drawImage(this.marioAdditionSign, xPosition, yPosition, null);
			xPosition -= this.marioAdditionSign.getWidth();
			for(int i = string.length() - 1; i >= 0; i--){
				/*if(k % 3 == 0 && k != 0){
					xPosition -= font[10].getWidth();
					g.drawImage(font[10], xPosition, y, null);
				}*/
				/*USE SWITCH STATEMENTS INSTEAD W/ FIXED VARIABLES!!
				switch(string.charAt(i)){
					case 0:
						xPosition -= font[0].getWidth();
						g.drawImage(font[0], xPosition, yPosition, null);
						break;
					case 1:
				}
				*/
				if(string.charAt(i) == '0'){
					xPosition -= font[0].getWidth();
					g.drawImage(font[0], xPosition, yPosition, null);
				}else if(string.charAt(i) == '1'){
					xPosition -= font[1].getWidth();
					g.drawImage(font[1], xPosition, yPosition, null);
				}else if(string.charAt(i) == '2'){
					xPosition -= font[2].getWidth();
					g.drawImage(font[2], xPosition, yPosition, null);
				}else if(string.charAt(i) == '3'){
					xPosition -= font[3].getWidth();
					g.drawImage(font[3], xPosition, yPosition, null);
				}else if(string.charAt(i) == '4'){
					xPosition -= font[4].getWidth();
					g.drawImage(font[4], xPosition, yPosition, null);
				}else if(string.charAt(i) == '5'){
					xPosition -= font[5].getWidth();
					g.drawImage(font[5], xPosition, yPosition, null);
				}else if(string.charAt(i) == '6'){
					xPosition -= font[6].getWidth();
					g.drawImage(font[6], xPosition, yPosition, null);
				}else if(string.charAt(i) == '7'){
					xPosition -= font[7].getWidth();
					g.drawImage(font[7], xPosition, yPosition, null);
				}else if(string.charAt(i) == '8'){
					xPosition -= font[8].getWidth();
					g.drawImage(font[8], xPosition, yPosition, null);
				}else if(string.charAt(i) == '9'){
					xPosition -= font[9].getWidth();
					g.drawImage(font[9], xPosition, yPosition, null);
				}
				//k++;
			}
		}
		else{
			g.drawImage(this.marioAdditionSign, xPosition, yPosition, null);
			xPosition += this.marioAdditionSign.getWidth();
			for(int i = 0; i < string.length(); i++){
				/*if(k % 3 == 0 && k != 0){
					xPosition += font[10].getWidth();
					g.drawImage(font[10], xPosition, y, null);
				}*/
				if(string.charAt(i) == '0'){
					xPosition += font[0].getWidth();
					g.drawImage(font[0], xPosition, yPosition, null);
				}else if(string.charAt(i) == '1'){
					xPosition += font[1].getWidth();
					g.drawImage(font[1], xPosition, yPosition, null);
				}else if(string.charAt(i) == '2'){
					xPosition += font[2].getWidth();
					g.drawImage(font[2], xPosition, yPosition, null);
				}else if(string.charAt(i) == '3'){
					xPosition += font[3].getWidth();
					g.drawImage(font[3], xPosition, yPosition, null);
				}else if(string.charAt(i) == '4'){
					xPosition += font[4].getWidth();
					g.drawImage(font[4], xPosition, yPosition, null);
				}else if(string.charAt(i) == '5'){
					xPosition += font[5].getWidth();
					g.drawImage(font[5], xPosition, yPosition, null);
				}else if(string.charAt(i) == '6'){
					xPosition += font[6].getWidth();
					g.drawImage(font[6], xPosition, yPosition, null);
				}else if(string.charAt(i) == '7'){
					xPosition += font[7].getWidth();
					g.drawImage(font[7], xPosition, yPosition, null);
				}else if(string.charAt(i) == '8'){
					xPosition += font[8].getWidth();
					g.drawImage(font[8], xPosition, yPosition, null);
				}else if(string.charAt(i) == '9'){
					xPosition += font[9].getWidth();
					g.drawImage(font[9], xPosition, yPosition, null);
				}
				//k++;
			}
		}
	}
	
	public BufferedImage stringToBufferedImage(BufferedImage[] font, String string){
		BufferedImage previousImage = this.marioAdditionSign;
		BufferedImage nextImage = null;
		//int k = 1;
			for(int i = 0; i < string.length(); i++){
				/*if(k % 3 == 0 && k != 0){
					xPosition -= font[10].getWidth();
					g.drawImage(font[10], xPosition, y, null);
				}*/
				if(string.charAt(i) == '0'){
					nextImage = font[0];
				}else if(string.charAt(i) == '1'){
					nextImage = font[1];
				}else if(string.charAt(i) == '2'){
					nextImage = font[2];
				}else if(string.charAt(i) == '3'){
					nextImage = font[3];
				}else if(string.charAt(i) == '4'){
					nextImage = font[4];
				}else if(string.charAt(i) == '5'){
					nextImage = font[5];
				}else if(string.charAt(i) == '6'){
					nextImage = font[6];
				}else if(string.charAt(i) == '7'){
					nextImage = font[7];
				}else if(string.charAt(i) == '8'){
					nextImage = font[8];
				}else if(string.charAt(i) == '9'){
					nextImage = font[9];
				}
				previousImage = attachImages(previousImage, nextImage);
				//k++;
			}
		return previousImage;
	}
	
	public BufferedImage attachImages(BufferedImage img1, BufferedImage img2){
	        BufferedImage resultImage = new BufferedImage(img1.getWidth() +
	                img2.getWidth(),img2.getHeight(),
	                BufferedImage.TYPE_INT_ARGB);
	        Graphics g = resultImage.getGraphics();
	        g.drawImage(img1, 0, 0, null);
	        g.drawImage(img2, img1.getWidth(), 0, null);
	        return resultImage;
	         
	}
	
	private AlphaComposite makeComposite(float alpha) {
		  int type = AlphaComposite.SRC_OVER;
		  return(AlphaComposite.getInstance(type, alpha));
	}
	
	public void setScore(long score){
		this.score = this.score + score;
		if(this.score < 0)
			this.score = 0;
	}
	
	public float getScore(){
		return score;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public int getLevel(){
		return level;
	}

	public boolean getItemObtained() {
		return itemObtained;
	}

	public void setItemObtained(boolean itemObtained) {
		this.itemObtained = itemObtained;
	}
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getTimer(){
		return timer1;
	}
}
