package com.game.src.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.game.src.main.libs.Animation;

public class NakedLakituCredits {
	Textures tex;
	Game game;
	private BufferedImage nakedLaki;
	private float rotateVariable = 0;
	private float rotateVariableVelocity = 0.15f;
	private double x = 0;
	private double y = 0;
	private int setup = 0;
	public NakedLakituCredits(Textures tex, Game game, int setup) {
		this.tex = tex;
		this.game = game;
		nakedLaki = CreditsController.resizeSmooth(tex.lakituItemDeath[0],(int)(tex.lakituItemDeath[0].getWidth()*2),(int)(tex.lakituItemDeath[0].getHeight()*2));
		//x = CreditsController.creditsImageX.get(1)+200;
		this.setup = setup;
		if(setup == 0)
			x= ((double) Game.WIDTH - (CreditsController.creditsImage.get(17).getWidth()/2) - 32-30);
		else
			x= ((double) Game.WIDTH + (CreditsController.creditsImage.get(17).getWidth()/2)+30);
		y = CreditsController.creditsImageY.get(17);//-(CreditsController.creditsImage.get(1).getHeight()/2);
	}
	public void tick() {
//		System.out.println(rotateVariable);
//		System.out.println(CreditsController.creditsImageY.get(19));
		y +=-0.53;
		if(setup == 0)
			rotateVariable+=rotateVariableVelocity;
		else
			rotateVariable-=rotateVariableVelocity;
	}
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g.create();
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.rotate(rotateVariable*Math.PI/6, nakedLaki.getWidth()/2, nakedLaki.getHeight()/2);
        g2d.drawRenderedImage(nakedLaki, at);
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
