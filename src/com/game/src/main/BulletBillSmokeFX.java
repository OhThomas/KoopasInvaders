package com.game.src.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityF;

public class BulletBillSmokeFX  extends GameObject implements EntityF{
	private Game game;
	private int width = 0;
	private int height = 0;
	private boolean empty = false;
	private long timer = 0;
	Graphics2D g2d = null;
	public BulletBillSmokeFX(double x, double y, Game game) {
		super(x, y);
		this.game = game;
		Random rand = new Random();
		timer = System.currentTimeMillis() + 3000;
		this.width = rand.nextInt(10)+2;
		this.height = rand.nextInt(10)+2;
		if(rand.nextInt(5) == 0 && width < 8 && height < 8)
			empty = true;
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
			empty = true;
		}
		if(rand.nextInt(30) == 0) {
			game.getController().removeEntity(this);
			return;
		}
		if(timer < System.currentTimeMillis()) {
			game.getController().removeEntity(this);
			return;
		}
	}

	public void render(Graphics g) {
		Random rand = new Random();
		if(g2d == null)
			g2d = (Graphics2D)g.create();

		//Graphics2D g2d = (Graphics2D)g.create();
		if(!game.isPaused() && (g2d.getColor() != Color.WHITE || g2d.getColor() != Color.GRAY)) {
			if(rand.nextInt(2) == 0)
				g2d.setColor(Color.GRAY);
			else
				g2d.setColor(Color.WHITE);
		}
		g2d.drawOval((int)x, (int)y, width, height);
		if(!empty)
			g2d.fillOval((int)x, (int)y, width, height);
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
		return "bulletBillSmokeFX";
	}
	
	public void close() {
		//g2d.dispose();
	}

}
