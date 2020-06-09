package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.game.src.main.libs.Animation;

public class Yoshi {
	Textures tex;
	Game game;
	private Animation running;
	private Animation braking;
	private Animation standing;
	private Animation licking;
	private Animation standingUp;
	private Animation runStartUp;
	private Animation franticRun;
	private double x = 0;
	private double y = 0;
	private double velX = 0;
	private double velY = 0;
	private double randVelX = 0;
	private double randVelY = 0;
	private int lickingAnimationCount = 0;
	private int runStartUpAnimationSeen = 0;
	private int runStartUpAnimationTracker = 0;
	private boolean fullStanding = false;
	private boolean fullRunning = false;
	public boolean madiCaught = false;
	public boolean madiEaten = false;
	public Yoshi(Textures tex, Game game) {
		this.tex = tex;
		this.game = game;
		x= ((double) Game.WIDTH - (CreditsController.creditsImage.get(19).getWidth()/2) - 32-430);
		y = Game.HEIGHT - ((tex.yoshi[0].getHeight()*3)/2);
		//y = CreditsController.creditsImageY.get(26)-25;//-(CreditsController.creditsImage.get(1).getHeight()/2);
		BufferedImage imgTemp1 = CreditsController.resizeSmooth(tex.yoshi[0],(int)(tex.yoshi[0].getWidth()*3),(int)(tex.yoshi[0].getHeight()*3));
		BufferedImage imgTemp2 = CreditsController.resizeSmooth(tex.yoshi[1],(int)(tex.yoshi[1].getWidth()*3),(int)(tex.yoshi[1].getHeight()*3));
		//x = CreditsController.creditsImageX.get(1)+200;
		running = new Animation(4,imgTemp1,imgTemp2);
		imgTemp1 = CreditsController.resizeSmooth(tex.yoshi[2], (int)(tex.yoshi[2].getWidth()*3),(int)(tex.yoshi[2].getHeight()*3));
		imgTemp2 = CreditsController.resizeSmooth(tex.yoshi[2], (int)(tex.yoshi[2].getWidth()*3),(int)(tex.yoshi[2].getHeight()*3));
		braking = new Animation(6,imgTemp1,imgTemp2);
		imgTemp1 = CreditsController.resizeSmooth(tex.yoshi[3], (int)(tex.yoshi[3].getWidth()*3),(int)(tex.yoshi[3].getHeight()*3));
		imgTemp2 = CreditsController.resizeSmooth(tex.yoshi[4], (int)(tex.yoshi[4].getWidth()*3),(int)(tex.yoshi[4].getHeight()*3));
		BufferedImage imgTemp3 = CreditsController.resizeSmooth(tex.yoshi[5],(int)(tex.yoshi[5].getWidth()*3),(int)(tex.yoshi[5].getHeight()*3));
		BufferedImage imgTemp4 = CreditsController.resizeSmooth(tex.yoshi[6],(int)(tex.yoshi[6].getWidth()*3),(int)(tex.yoshi[6].getHeight()*3));
		BufferedImage imgTemp5 = CreditsController.resizeSmooth(tex.yoshi[7],(int)(tex.yoshi[7].getWidth()*3),(int)(tex.yoshi[7].getHeight()*3));
		standing = new Animation(9,imgTemp1,imgTemp2,imgTemp3,imgTemp4,imgTemp5,imgTemp4,imgTemp2);
		imgTemp1 = CreditsController.resizeSmooth(tex.yoshi[8], (int)(tex.yoshi[8].getWidth()*3),(int)(tex.yoshi[8].getHeight()*3));
		imgTemp2 = CreditsController.resizeSmooth(tex.yoshi[9], (int)(tex.yoshi[9].getWidth()*3),(int)(tex.yoshi[9].getHeight()*3));
		imgTemp3 = CreditsController.resizeSmooth(tex.yoshi[10], (int)(tex.yoshi[10].getWidth()*3),(int)(tex.yoshi[10].getHeight()*3));
		imgTemp4 = CreditsController.resizeSmooth(tex.yoshi[11], (int)(tex.yoshi[11].getWidth()*3),(int)(tex.yoshi[11].getHeight()*3));
		imgTemp5 = CreditsController.resizeSmooth(tex.yoshi[12], (int)(tex.yoshi[12].getWidth()*3),(int)(tex.yoshi[12].getHeight()*3));
		BufferedImage imgTemp6 = CreditsController.resizeSmooth(tex.yoshi[13], (int)(tex.yoshi[13].getWidth()*3),(int)(tex.yoshi[13].getHeight()*3));
		BufferedImage imgTemp7 = CreditsController.resizeSmooth(tex.yoshi[14], (int)(tex.yoshi[14].getWidth()*3),(int)(tex.yoshi[14].getHeight()*3));
		BufferedImage imgTemp8 = CreditsController.resizeSmooth(tex.yoshi[15], (int)(tex.yoshi[15].getWidth()*3),(int)(tex.yoshi[15].getHeight()*3));
		BufferedImage imgTemp9 = CreditsController.resizeSmooth(tex.yoshi[16], (int)(tex.yoshi[16].getWidth()*3),(int)(tex.yoshi[16].getHeight()*3));
		licking = new Animation(6,imgTemp1,imgTemp2,imgTemp3,imgTemp4,imgTemp5,imgTemp6,imgTemp7,imgTemp8,imgTemp9,imgTemp8,imgTemp7,
				imgTemp6,imgTemp5,imgTemp4,imgTemp3,imgTemp2,imgTemp1);
		imgTemp1 = CreditsController.resizeSmooth(tex.yoshi[17], (int)(tex.yoshi[17].getWidth()*3),(int)(tex.yoshi[17].getHeight()*3));
		imgTemp2 = CreditsController.resizeSmooth(tex.yoshi[18], (int)(tex.yoshi[18].getWidth()*3),(int)(tex.yoshi[18].getHeight()*3));
		standingUp = new Animation(6,imgTemp1,imgTemp2);
		imgTemp1 = CreditsController.resizeSmooth(tex.yoshi[19], (int)(tex.yoshi[19].getWidth()*3),(int)(tex.yoshi[19].getHeight()*3));
		imgTemp2 = CreditsController.resizeSmooth(tex.yoshi[20], (int)(tex.yoshi[20].getWidth()*3),(int)(tex.yoshi[20].getHeight()*3));
		imgTemp3 = CreditsController.resizeSmooth(tex.yoshi[21], (int)(tex.yoshi[21].getWidth()*3),(int)(tex.yoshi[21].getHeight()*3));
		imgTemp4 = CreditsController.resizeSmooth(tex.yoshi[22], (int)(tex.yoshi[22].getWidth()*3),(int)(tex.yoshi[22].getHeight()*3));
		runStartUp = new Animation(6,imgTemp2,imgTemp3,imgTemp2,imgTemp1,imgTemp4);
		imgTemp1 = CreditsController.resizeSmooth(tex.yoshi[23], (int)(tex.yoshi[23].getWidth()*3),(int)(tex.yoshi[23].getHeight()*3));
		imgTemp2 = CreditsController.resizeSmooth(tex.yoshi[24], (int)(tex.yoshi[24].getWidth()*3),(int)(tex.yoshi[24].getHeight()*3));
		franticRun = new Animation(5,imgTemp1,imgTemp2);
		
		running.nextFrame();
		running.setCount(0);
		braking.nextFrame();
		braking.setCount(0);
		standing.nextFrame();
		standing.setCount(0);
		licking.nextFrame();
		licking.setCount(0);
		standingUp.nextFrame();
		standingUp.setCount(0);
		runStartUp.nextFrame();
		runStartUp.setCount(0);
		franticRun.nextFrame();
		franticRun.setCount(0);
	}
	public void tick() {
		if(CreditsController.creditsImageY.get(26)+250 < Game.WIDTH * Game.SCALE && !(CreditsController.creditsImageX.get(26) < this.x + 228))
			velX = 2;
		else if(CreditsController.creditsImageX.get(26) < this.x + 218/*228*/ && !madiEaten) {
			velX = 0;
			standing.runAnimation();
		}
		if(CreditsController.creditsImageY.get(26)-60 < this.y && (licking.getCount() > 8 || !madiCaught)) {
			licking.runAnimation();
			if(lickingAnimationCount != licking.getCount()) {
				lickingAnimationCount = licking.getCount();
//				if(8 < lickingAnimationCount)
//					CreditsController.creditsImageX.set(26,CreditsController.creditsImageX.get(26)-18);
//				if(16 <= lickingAnimationCount && !madiEaten)
//					madiEaten = true;
			}
			if(lickingAnimationCount >= 9 && !madiCaught) {
				madiCaught = true;
				CreditsController.creditsImageX.set(26,CreditsController.creditsImageX.get(26)-1);
			}
		}
//		System.out.println(CreditsController.creditsImage.get(26).getWidth());
		if(madiCaught && !madiEaten) {
			CreditsController.creditsImageX.set(26,CreditsController.creditsImageX.get(26)-3);
//			if(licking.getCount() < 8)
//				CreditsController.creditsImageX.set(26,CreditsController.creditsImageX.get(26)-3);
//			else
//				CreditsController.creditsImageX.set(26,this.x+licking.getCurrentImage().getWidth()-9);
			if(CreditsController.creditsImageX.get(26)+6 < this.x+87 && 36 < CreditsController.creditsImage.get(26).getWidth()) {
				//change image and add to creditsimagex
				BufferedImage j = CreditsController.creditsImage.get(26).getSubimage(6,0,
						CreditsController.creditsImage.get(26).getWidth()-6, CreditsController.creditsImage.get(26).getHeight());
				CreditsController.creditsImage.set(26, j);
				CreditsController.creditsImageX.set(26,CreditsController.creditsImageX.get(26)+6);
				Random rand = new Random();
				randVelX = rand.nextInt(100) * 0.1;
				randVelY = rand.nextInt(100) * 0.1;
				if(rand.nextBoolean() == true)
					randVelY *= -1;
				if(rand.nextBoolean() == true)
					randVelX *= -1;
			}
			else if(!(36 < CreditsController.creditsImage.get(26).getWidth())) {
				if(CreditsController.creditsImageX.get(26) < this.x+39+36)
					madiEaten = true;
			}
			else if(randVelY != 0 || randVelX != 0) {
				randVelX = 0;
				randVelY = 0;
			}
//			else if(!(35 < CreditsController.creditsImage.get(26).getWidth())) {
//				if(CreditsController.creditsImageX.get(26) < this.x+39)
//					madiEaten = true;
//			}
		}
		else if(madiCaught && madiEaten && (standingUp.getCount() >= 1 || !fullStanding)) {
			standingUp.runAnimation();
			if(standingUp.getCount() == 1)
				fullStanding = true;
		}
		if(fullStanding && !fullRunning) {
			if(velX <= 1.2)
				velX = (double)velX + (double)0.04;
			runStartUp.runAnimation();
			if(runStartUp.getCount() >= 4 && runStartUp.getCount() != runStartUpAnimationTracker)
				runStartUpAnimationSeen++;
			if(runStartUp.getCount() != runStartUpAnimationTracker)
				runStartUpAnimationTracker = runStartUp.getCount();
			if(runStartUpAnimationSeen >= 3 && runStartUp.getCount() >= 4)
				fullRunning = true;
		}
		else if(fullRunning) {
			if(velX <= 2.2)
				velX = (double)velX + (double)0.08;
			franticRun.runAnimation();
		}
		if(madiEaten && (randVelX != 0 || randVelY != 0)) {
			randVelX = 0;
			randVelY = 0;
		}
		//y +=-0.53;
		x+=velX;
		running.runAnimation();
	}
	public void render(Graphics g) {
		if(fullRunning)
			franticRun.drawAnimation(g, x, y, 0);
		else if(fullStanding && !fullRunning)
			runStartUp.drawAnimation(g, x, y, 0);
		else if(madiCaught && madiEaten)
			standingUp.drawAnimation(g, x, y, 0);
		else if(CreditsController.creditsImageY.get(26)-60 < this.y) 
			licking.drawAnimation(g, x + randVelX, y + randVelY, 0);
		else if(velX == 0)
			standing.drawAnimation(g, x, y, 0);
		else
			running.drawAnimation(g, x, y, 0);
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
