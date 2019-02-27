package com.game.src.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityF;

public class ChompFX extends GameObject implements EntityF {

	ArrayList<Integer> chompWidth = new ArrayList<Integer>();
	ArrayList<Integer> chompHeight = new ArrayList<Integer>();
	ArrayList<Integer> chompX = new ArrayList<Integer>();
	ArrayList<Integer> chompY = new ArrayList<Integer>();
	ArrayList<Long> chompTimer = new ArrayList<Long>();
	private int width = 0;
	private int height = 0;
	Graphics2D g2d = null;
	String chomped = "";
	public ChompFX(double x, double y, String chomped) {
		super(x, y);
		Random rand = new Random();
		this.chomped = chomped;
		width = 10;
		height = 10;
		int chompStackSize = rand.nextInt(15)+3;
		for(int i = 0; i <= chompStackSize; i++) {
			chompWidth.add(rand.nextInt(7)); 
			chompHeight.add(rand.nextInt(7)); 
			chompX.add((int)x+rand.nextInt(13)-rand.nextInt(13));
			chompY.add((int)y+rand.nextInt(13)-rand.nextInt(13));
			chompTimer.add(System.currentTimeMillis() + 1000 + (long)rand.nextInt(1000));
		}
	}
	
	public void tick() {
		Random rand = new Random();
		for(int i = 0; i <= chompWidth.size()-1; i++) {
			if(chompWidth.get(i).equals(0) || chompHeight.get(i).equals(0) || chompTimer.get(i) < System.currentTimeMillis()) {
				chompWidth.remove(i);
				chompHeight.remove(i);
				chompX.remove(i);
				chompY.remove(i);
				chompTimer.remove(i);
			}
			else {
				if(rand.nextInt(5) == 0) {
					chompWidth.set(i, chompWidth.get(i)-1);
				}
				if(rand.nextInt(5) == 0) {
					chompHeight.set(i, chompHeight.get(i)-1);
				}
			}
		}
	}

	public void render(Graphics g) {
		Random rand = new Random();
		if(g2d == null)
			g2d = (Graphics2D)g.create();
		if(System.currentTimeMillis() % 60 == 0)
			tick();
		if(chomped.equals("Goomba1")) {
			Color brown = new Color(184,40,0);
			Color orange = new Color(248,136,0);
			Color lightOrange = new Color(248,192,0);
			Color yellow = new Color(248,248,0);
			if((g2d.getColor() != brown || g2d.getColor() != orange || g2d.getColor() != lightOrange ||
					 g2d.getColor() != yellow || g2d.getColor() != Color.BLACK || g2d.getColor() != Color.WHITE)) {
				int iColor = rand.nextInt(6);
				if(iColor == 0)
					g2d.setColor(brown);
				else if(iColor == 1)
					g2d.setColor(orange);
				else if(iColor == 2)
					g2d.setColor(lightOrange);
				else if(iColor == 3)
					g2d.setColor(yellow);
				else if(iColor == 4)
					g2d.setColor(Color.BLACK);
				else
					g2d.setColor(Color.WHITE);
			}
		}
		else if(chomped.equals("Goomba2")) {
			Color brown = new Color(255,174,76);
			Color pink = new Color(255,186,170);
			if((g2d.getColor() != brown || g2d.getColor() != pink || g2d.getColor() != Color.BLACK)) {
				int iColor = rand.nextInt(3);
				if(iColor == 0)
					g2d.setColor(brown);
				else if(iColor == 1)
					g2d.setColor(pink);
				else
					g2d.setColor(Color.BLACK);
			}
		}
		else if(chomped.equals("Goomba3")) {
			Color brown = new Color(217,74,0);
			Color pink = new Color(255,198,181);
			if((g2d.getColor() != brown || g2d.getColor() != pink || g2d.getColor() != Color.BLACK)) {
				int iColor = rand.nextInt(3);
				if(iColor == 0)
					g2d.setColor(brown);
				else if(iColor == 1)
					g2d.setColor(pink);
				else
					g2d.setColor(Color.BLACK);
			}
		}
		else if(chomped.equals("GreenShell") || chomped.equals("GreenShellCircle")) {
			if((g2d.getColor() != Color.GREEN || g2d.getColor() != Color.WHITE || g2d.getColor() != Color.BLACK)) {
				int iColor = rand.nextInt(3);
				if(iColor == 0)
					g2d.setColor(Color.GREEN);
				else if(iColor == 1)
					g2d.setColor(Color.WHITE);
				else
					g2d.setColor(Color.BLACK);
			}
		}
		else if(chomped.equals("BulletBill")) {
			if((g2d.getColor() != Color.RED || g2d.getColor() != Color.ORANGE ||
					 g2d.getColor() != Color.YELLOW || g2d.getColor() != Color.BLACK || g2d.getColor() != Color.WHITE)) {
				int iColor = rand.nextInt(5);
				if(iColor == 0)
					g2d.setColor(Color.RED);
				else if(iColor == 1)
					g2d.setColor(Color.ORANGE );
				else if(iColor == 2)
					g2d.setColor(Color.YELLOW);
				else if(iColor == 3)
					g2d.setColor(Color.BLACK);
				else
					g2d.setColor(Color.WHITE);
			}
		}
		else if(chomped.equals("BuzzyBeetleShell")) {
			Color blue1 = new Color(0,120,120);
			Color blue2 = new Color(0,80,80);
			if((g2d.getColor() != Color.WHITE || g2d.getColor() != Color.BLACK ||g2d.getColor() != blue1 || 
					g2d.getColor() != blue2)) {
				int iColor = rand.nextInt(5);
				if(iColor == 0)
					g2d.setColor(Color.WHITE);
				else if(iColor == 1)
					g2d.setColor(Color.BLACK );
				else if(iColor == 2)
					g2d.setColor(blue1);
				else
					g2d.setColor(blue2);
			}
		}
		else if(chomped.equals("Fireball")) {
			if((g2d.getColor() != Color.RED && g2d.getColor() != Color.ORANGE)) {
				int iColor = rand.nextInt(3);
				if(iColor == 0)
					g2d.setColor(Color.RED);
				else if(iColor == 1)
					g2d.setColor(Color.ORANGE);
				else
					g2d.setColor(Color.RED);
			}
		}
		for(int i = 0; i <= chompWidth.size()-1;i++) {
			g2d.fillOval((int)chompX.get(i), (int)chompY.get(i), chompWidth.get(i), chompHeight.get(i));
		}
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

	public void close() {
		
	}

}
