package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.game.src.main.libs.Animation;

public class CloudCredits {
	Textures tex;
	Game game;
	private Animation cloudy;
	private Animation cloudyDisappearing;
	private double x = 0;
	private double y = 0;
	private double randX = 0;
	private double randY = 0;
	private double randXVel = 0;
	private double randYVel = 0;
	private int setup = 0;
	private int animationSpeed = 0;
	private int frameCounter = 0;
	private boolean colliding = false;
	private boolean detecting = false;
	private boolean detectingDone = false;
	private boolean reappeared = true;
	private boolean lastReversedImage = false;
	public CloudCredits(Textures tex, Game game, int setup) {
		this.tex = tex;
		this.game = game;
		animationSpeed = 6;
		BufferedImage imgTemp1 = CreditsController.resizeSmooth(tex.lakituItem[9],(int)(tex.lakituItem[9].getWidth()*2),(int)(tex.lakituItem[9].getHeight()*2));
		BufferedImage imgTemp2 = CreditsController.resizeSmooth(tex.lakituItem[10],(int)(tex.lakituItem[10].getWidth()*2),(int)(tex.lakituItem[10].getHeight()*2));
		BufferedImage imgTemp3 = CreditsController.resizeSmooth(tex.lakituItem[11],(int)(tex.lakituItem[11].getWidth()*2),(int)(tex.lakituItem[11].getHeight()*2));
		BufferedImage imgTemp4 = CreditsController.resizeSmooth(tex.lakituItem[12],(int)(tex.lakituItem[12].getWidth()*2),(int)(tex.lakituItem[12].getHeight()*2));
		//x = CreditsController.creditsImageX.get(1)+200;
		this.setup = setup;
		if(setup == 0) {
			x= ((double) Game.WIDTH - (CreditsController.creditsImage.get(19).getWidth()/2) - 32-30);
			cloudy = new Animation(6,imgTemp1,imgTemp2,imgTemp3,imgTemp4);
		}
		else {
			x= ((double) Game.WIDTH + (CreditsController.creditsImage.get(19).getWidth()/2)+10);
			cloudy = new Animation(6,imgTemp4,imgTemp3,imgTemp2,imgTemp1);
		}
		imgTemp1 = CreditsController.resizeSmooth(tex.cloudDisappearing[0], (int)(tex.cloudDisappearing[0].getWidth()*2),(int)(tex.cloudDisappearing[0].getHeight()*2));
		imgTemp2 = CreditsController.resizeSmooth(tex.cloudDisappearing[1], (int)(tex.cloudDisappearing[1].getWidth()*2),(int)(tex.cloudDisappearing[1].getHeight()*2));
		imgTemp3 = CreditsController.resizeSmooth(tex.cloudDisappearing[2], (int)(tex.cloudDisappearing[2].getWidth()*2),(int)(tex.cloudDisappearing[2].getHeight()*2));
		imgTemp4 = CreditsController.resizeSmooth(tex.cloudDisappearing[3], (int)(tex.cloudDisappearing[3].getWidth()*2),(int)(tex.cloudDisappearing[3].getHeight()*2));
		BufferedImage imgTemp5 = CreditsController.resizeSmooth(tex.cloudDisappearing[4], (int)(tex.cloudDisappearing[4].getWidth()*2),(int)(tex.cloudDisappearing[4].getHeight()*2));
		BufferedImage imgTemp6 = CreditsController.resizeSmooth(tex.cloudDisappearing[5], (int)(tex.cloudDisappearing[5].getWidth()*2),(int)(tex.cloudDisappearing[5].getHeight()*2));
		y = CreditsController.creditsImageY.get(19)+8;//-(CreditsController.creditsImage.get(1).getHeight()/2);
		cloudyDisappearing = new Animation(6,imgTemp1,imgTemp2,imgTemp3,imgTemp4,imgTemp5,imgTemp6);
		cloudy.nextFrame();
		cloudy.setCount(0);
		cloudyDisappearing.nextFrame();
		cloudyDisappearing.setCount(0);
	}
	public void tick() {
		if(!this.getBounds().intersects(CreditsController.spikey.getBounds()) && colliding) {
			colliding = false;
			detecting = false;
			reappeared = false;
			detectingDone = true;
		}
		else if(this.getBounds().intersects(CreditsController.spikey.getBounds()) && !colliding)
			colliding = true;
		if(detectingDone == false) {
			if(this.getDetection().intersects(CreditsController.spikey.getBounds()) && !detecting)
				detecting = true;
			else if(!this.getDetection().intersects(CreditsController.spikey.getBounds()) && detecting)
				detecting = false;
		}
		else if(!this.getDetection().intersects(CreditsController.spikey.getBounds()))
			detectingDone = false;
		
		if(colliding) {//detecting) {
			frameCounter++;
			if(frameCounter > animationSpeed){
				frameCounter = 0;
				Random rand = new Random();
				randXVel = rand.nextInt(3);
				randYVel = rand.nextInt(3);
				if(rand.nextInt(2) == 0)
					randXVel *= -1;
				if(rand.nextInt(2) == 0)
					randYVel *= -1;
				if(cloudyDisappearing.getCount() >= 4) {
					if(cloudyDisappearing.getCount() == 6)
						cloudyDisappearing.setCount(4);
					cloudyDisappearing.nextFrame();
				}
			}
			if(cloudyDisappearing.getCount() == 6)
				cloudyDisappearing.setCount(4);
			if(cloudyDisappearing.getCount() < 4)
				cloudyDisappearing.runAnimation();
		}
		else if(!reappeared && cloudyDisappearing.getCount() != 0) {
			frameCounter++;
			if(frameCounter > animationSpeed){
				frameCounter = 0;
				if(lastReversedImage && cloudyDisappearing.getCount() == 0)
					lastReversedImage = false;
				else if(lastReversedImage == false && cloudyDisappearing.getCount() != 0)
					lastReversedImage = true;
				if(cloudyDisappearing.getCount() != 0) {
					if(cloudyDisappearing.getCount() == 1)
						cloudyDisappearing.setCount(6);
					else
						cloudyDisappearing.setCount(cloudyDisappearing.getCount()-2);
					cloudyDisappearing.nextFrame();
				}
				Random rand = new Random();
				randXVel = rand.nextInt(3);
				randYVel = rand.nextInt(3);
				if(rand.nextInt(2) == 0)
					randXVel *= -1;
				if(rand.nextInt(2) == 0)
					randYVel *= -1;
			}
			if(lastReversedImage && cloudyDisappearing.getCount() == 0)
				lastReversedImage = false;
			if(cloudyDisappearing.getCount() == 0 && !lastReversedImage)
				reappeared = true;
		}
		else if(!reappeared && cloudyDisappearing.getCount() == 0 && lastReversedImage == false)
			reappeared = true;
		else {
			frameCounter++;
			if(frameCounter > animationSpeed){
				frameCounter = 0;
				Random rand = new Random();
				randXVel = rand.nextInt(2);
				randYVel = rand.nextInt(2);
				if(rand.nextInt(2) == 0)
					randXVel *= -1;
				if(rand.nextInt(2) == 0)
					randYVel *= -1;
			}
			cloudy.runAnimation();
		}
		y +=-0.53;
//		if(randX > -5 && randX < 5)
//			randX += (randXVel/1.5);
//		else if(randX <= -5) {
//			randX+=1;
//			randXVel = 0;
//		}
//		else if(randX >=5) {
//			randX-=1;
//			randXVel = 0;
//		}
//		if(randY > -5 && randY < 5)
//			randY += (randYVel/1.5);
//		else if(randY <= -5) {
//			randY+=1;
//			randYVel = 0;
//		}
//		else if(randY >=5) {
//			randY-=1;
//			randYVel = 0;
//		}
	}
	public void render(Graphics g) {
//		if(detecting || !reappeared) {
//			this.cloudyDisappearing.drawAnimation(g, x, y, 0);
//		}
		if(colliding || !reappeared) {
			if(setup == 0)
				this.cloudyDisappearing.drawAnimation(g, x+6-randXVel, y+5+randYVel, 0);
			else
				this.cloudyDisappearing.drawAnimation(g, x+6+randXVel, y+5+randYVel, 0);
		}
		else
			cloudy.drawAnimation(g, x+randXVel, y+randYVel, 0);
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 19*2, 17*2);
	}
	public Rectangle getDetection() {
		return new Rectangle((int)x, (int)y, 19*6, 17*6);
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
}
