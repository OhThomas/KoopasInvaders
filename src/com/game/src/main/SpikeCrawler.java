package com.game.src.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.game.src.main.libs.Animation;

public class SpikeCrawler {
	Textures tex;
	Game game;
	private Animation spikey;
	private float rotateVariable = 0;
	private float rotateVariableFake = 0;
	private float rotateVariableVelocity = 0.05f;
	private double x = 0;
	private double y = 0;
	private double velY = 0;
	private double velX = 0;
	private boolean rotate = false;//creditsImage.get(19)
	public SpikeCrawler(Textures tex, Game game) {
		this.tex = tex;
		this.game = game;
		BufferedImage imgTemp1 = CreditsController.resizeSmooth(tex.spikey[0],(int)(tex.spikey[0].getWidth()*2),(int)(tex.spikey[0].getHeight()*2));
		BufferedImage imgTemp2 = CreditsController.resizeSmooth(tex.spikey[1],(int)(tex.spikey[1].getWidth()*2),(int)(tex.spikey[1].getHeight()*2));
		x = CreditsController.creditsImageX.get(19)+200;
		y = CreditsController.creditsImageY.get(19)-this.getBounds().height;//-(CreditsController.creditsImage.get(19).getHeight()/2);
		spikey = new Animation(6,imgTemp1,imgTemp2);
		spikey.nextFrame();
		spikey.setCount(0);
	}
	public void tick() {
//		System.out.println(rotateVariable);
//		System.out.println(CreditsController.creditsImageY.get(19));
		y +=-0.53;
		if(rotate) {
			if(rotateVariableFake < 3) {
				rotateVariableFake+=rotateVariableVelocity;
				rotateVariable+=rotateVariableVelocity;
			}else {
				rotateVariable = Math.round(rotateVariable);
				rotate = false;
			}
		}else if(rotateVariableFake != 0) {
			rotateVariableFake = 0;
		}
		if(CreditsController.creditsImageX.get(19)+CreditsController.creditsImage.get(19).getWidth() < x && 
				y < CreditsController.creditsImageY.get(19)+CreditsController.creditsImage.get(19).getHeight() && velY != 1) {
			velY = 1;
			rotate = true;
		}
		else if(CreditsController.creditsImageX.get(19)+CreditsController.creditsImage.get(19).getWidth() < x && 
				CreditsController.creditsImageY.get(19)+CreditsController.creditsImage.get(19).getHeight() < y && velY != 0) {
			velY = 0;
			rotate = true;
		}
		if(x+this.getBounds().width <= CreditsController.creditsImageX.get(19)&& 
				 CreditsController.creditsImageY.get(19)+CreditsController.creditsImage.get(19).getHeight() <= y && velY != -1) {
			velY = -1;
			rotate = true;
		}
		else if(x+this.getBounds().width <= CreditsController.creditsImageX.get(19)&& 
				y < CreditsController.creditsImageY.get(19)-(CreditsController.creditsImage.get(19).getHeight()/2) && velY != 0) {
			velY = 0;
			rotate = true;
		}
		
		if(CreditsController.creditsImageX.get(19) < x+this.getBounds().width && 
				CreditsController.creditsImageY.get(19)+CreditsController.creditsImage.get(19).getHeight() < y && velY == 0)
			velX = -1;
//		else
//			velX = 0;
		else if(x-this.getBounds().width <= CreditsController.creditsImageX.get(19)+CreditsController.creditsImage.get(19).getWidth() &&
				y <= CreditsController.creditsImageY.get(19) && velY == 0)
			velX = 1;
		else
			velX = 0;
		y+=velY;
		x+=velX;
		spikey.runAnimation();
	}
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g.create();
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.rotate(rotateVariable*Math.PI/6, spikey.getCurrentImage().getWidth()/2, spikey.getCurrentImage().getHeight()/2);
        g2d.drawRenderedImage(spikey.getCurrentImage(), at);
		//g2d.translate(50, 50);
	    //g2d.rotate(30.0 * Math.PI / 180.0);
//		g2d.translate(300, 250);
//		g2d.rotate(rotateVariable*Math.PI/180);
//		g2d.translate(-300, -250);
		//spikey.drawAnimation(g2d, x, y, 0);
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16*2, 16*2);
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
	public double getVelY() {
		return velY;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}
	public double getVelX() {
		return velX;
	}
	public void setVelX(double velX) {
		this.velX = velX;
	}
}
