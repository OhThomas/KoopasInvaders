package com.game.src.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityF;

public class BlockBreakingFX  extends GameObject implements EntityF{
	private Game game;
	private int width = 0;
	private int height = 0;
	private double velX = 0;
	private double velY = 0;
	private long timer = 0;
	Graphics2D g2d = null;
	public BlockBreakingFX(double x, double y, Game game, boolean thwimpSide) {
		super(x, y);
		this.game = game;
		if(thwimpSide)
			velX = -2;
		else
			velX = 2;
		Random rand = new Random();
		timer = System.currentTimeMillis() + 2000;
		this.width = rand.nextInt(5)+2;
		this.height = rand.nextInt(5)+2;
	}

	public void tick() {
		Random rand = new Random();
		if(width == 0 || height == 0) {
			game.getController().removeEntity(this);
			return;
		}
		if(rand.nextInt(5) == 0) {
			width--;
		}
		if(rand.nextInt(5) == 0) {
			height--;
		}
		if(rand.nextInt(30) == 0) {
			game.getController().removeEntity(this);
			return;
		}
		if(timer < System.currentTimeMillis()) {
			game.getController().removeEntity(this);
			return;
		}

		if(velX < 0) {
			if(velX < -1)
				velX+=0.08;
		}
		else {
			if(1 < velX)
				velX-=0.08;
		}
		if(velY < 4)
			velY += 0.1;
		x+=velX;
		y+=velY;
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		if(g2d == null)
			g2d = (Graphics2D)g.create();
		g2d.drawRect((int)x, (int)y, width, height);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public double getVelX() {
		return 0;
	}

	public double getVelY() {
		return 0;
	}

	public String entityName() {
		return "blockBreakingFX";
	}

	public void close() {
		//g2d.dispose();
	}

}
