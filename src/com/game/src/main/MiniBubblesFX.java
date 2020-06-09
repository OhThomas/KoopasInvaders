package com.game.src.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityF;

public class MiniBubblesFX  extends GameObject implements EntityF{
	private Game game;
	private int width = 0;
	private int height = 0;
	private double velX = 0;
	private double velY = 0;
	private long timer = 0;
	Graphics2D g2d = null;
	Color red = new Color(184,40,0);
	Color orange = new Color(248,136,0);
	Color yellow = new Color(248,192,0);
	public MiniBubblesFX(double x, double y, Game game, double velX) {
		super(x, y);
		this.game = game;
		this.velX = velX;
		Random rand = new Random();
		timer = System.currentTimeMillis() + 3000;
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
		Random rand = new Random();
		if(g2d == null)
			g2d = (Graphics2D)g.create();

		//Graphics2D g2d = (Graphics2D)g.create();
		if(!game.isPaused() && (g2d.getColor() != yellow || g2d.getColor() != orange|| g2d.getColor() != red)) {
			int i = rand.nextInt(3);
			if(i == 0)
				g2d.setColor(yellow);
			else if(i == 1)
				g2d.setColor(orange);
			else
				g2d.setColor(red);
		}
		g2d.drawOval((int)x, (int)y, width, height);
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
		return "miniBubblesFX";
	}

	public void close() {
		//g2d.dispose();
	}

}
