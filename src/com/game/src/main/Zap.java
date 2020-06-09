package com.game.src.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import com.game.src.main.classes.EntityF;
import com.game.src.main.libs.Animation;

public class Zap extends GameObject implements EntityF{
	Textures tex;
	Game game;
	private int width = 0;
	private int height = 0;
	ArrayList<Integer> zapWidth = new ArrayList<Integer>();
	ArrayList<Integer> zapHeight = new ArrayList<Integer>();
	ArrayList<Integer> zapX = new ArrayList<Integer>();
	ArrayList<Integer> zapY = new ArrayList<Integer>();
	ArrayList<Long> zapCircleTimer = new ArrayList<Long>();
	ArrayList<Integer> zapLines = new ArrayList<Integer>();
	ArrayList<Integer> zapLinesX = new ArrayList<Integer>();
	ArrayList<Integer> zapLinesY = new ArrayList<Integer>();
	ArrayList<Integer> zapLinesX2 = new ArrayList<Integer>();
	ArrayList<Integer> zapLinesY2 = new ArrayList<Integer>();
	ArrayList<Long> zapLinesTimer = new ArrayList<Long>();
	ArrayList<Integer> zapLinez = new ArrayList<Integer>();
	ArrayList<Integer> zapLinezX = new ArrayList<Integer>();
	ArrayList<Integer> zapLinezY = new ArrayList<Integer>();
	ArrayList<Integer> zapLinezX2 = new ArrayList<Integer>();
	ArrayList<Integer> zapLinezY2 = new ArrayList<Integer>();
	ArrayList<Long> zapLinezTimer = new ArrayList<Long>();
	Animation zap;
	Graphics2D g2d = null;
	public Zap(double x, double y, double ampX, double ampY, Textures tex, Game game) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		Random rand = new Random();
		if(rand.nextInt(7) > 5)
			zap = new Animation(5, tex.ampItemZap[0], tex.ampItemZap[1], tex.ampItemZap[2], tex.ampItemZap[3],
					tex.ampItemZap[4], tex.ampItemZap[5], tex.ampItemZap[6], tex.ampItemZap[7]);
		else
			zap = new Animation(5, tex.empty, tex.empty, tex.empty, tex.empty,
					tex.empty, tex.empty, tex.empty, tex.empty);
		int zapStackSize = rand.nextInt(60);
		for(int i = 0; i <= zapStackSize; i++) {
			zapWidth.add(rand.nextInt(3)); 
			zapHeight.add(rand.nextInt(3)); 
			zapX.add((int)x+rand.nextInt(27)-rand.nextInt(27));
			zapY.add((int)y+rand.nextInt(27)-rand.nextInt(27));
			zapCircleTimer.add(System.currentTimeMillis() + 1000 + (long)rand.nextInt(1000));
		}
		zapStackSize = rand.nextInt(45);
		for(int i = 0; i <= zapStackSize; i++) {
			zapWidth.add(rand.nextInt(10)); 
			zapHeight.add(rand.nextInt(10)); 
			zapX.add((int)ampX+rand.nextInt(27)-rand.nextInt(27));
			zapY.add((int)ampY+rand.nextInt(27)-rand.nextInt(27));
			zapCircleTimer.add(System.currentTimeMillis() + 1000 + (long)rand.nextInt(1000));
		}
		zapStackSize = rand.nextInt(30);
		for(int i = 0; i <= zapStackSize; i++) {
			zapLines.add(i);
			zapLinesX.add((int)ampX+rand.nextInt(12)-rand.nextInt(12));
			zapLinesY.add((int)ampY+rand.nextInt(12)-rand.nextInt(12));
			zapLinesX2.add(zapLinesX.get(i)+rand.nextInt(28)-rand.nextInt(28));
			zapLinesY2.add(zapLinesY.get(i)+rand.nextInt(28)-rand.nextInt(28));
			zapLinesTimer.add(System.currentTimeMillis() + 1000 + (long)rand.nextInt(1000));
		}
		zapStackSize = rand.nextInt(30);
		for(int i = 0; i <= zapStackSize; i++) {
			zapLinez.add(i);
			zapLinezX.add((int)x+rand.nextInt(12)-rand.nextInt(12));
			zapLinezY.add((int)y+rand.nextInt(12)-rand.nextInt(12));
			zapLinezX2.add(zapLinezX.get(i)+rand.nextInt(8)-rand.nextInt(8));
			zapLinezY2.add(zapLinezY.get(i)+rand.nextInt(8)-rand.nextInt(8));
			zapLinezTimer.add(System.currentTimeMillis() + 1000 + (long)rand.nextInt(1000));
		}
		zapStackSize = rand.nextInt(30);
		for(int i = 0; i <= zapStackSize; i++) {
			zapLinez.add(i);
			zapLinezX.add((int)ampX+rand.nextInt(12)-rand.nextInt(12));
			zapLinezY.add((int)ampY+rand.nextInt(12)-rand.nextInt(12));
			zapLinezX2.add(zapLinezX.get(i)+rand.nextInt(8)-rand.nextInt(8));
			zapLinezY2.add(zapLinezY.get(i)+rand.nextInt(8)-rand.nextInt(8));
			zapLinezTimer.add(System.currentTimeMillis() + 1000 + (long)rand.nextInt(1000));
		}
	}

	public void tick() {
		Random rand = new Random();
		for(int i = 0; i <= zapWidth.size()-1; i++) {
			if(zapWidth.get(i).equals(0) || zapHeight.get(i).equals(0) || zapCircleTimer.get(i) < System.currentTimeMillis()) {
				zapWidth.remove(i);
				zapHeight.remove(i);
				zapX.remove(i);
				zapY.remove(i);
				zapCircleTimer.remove(i);
			}
			else {
				if(rand.nextInt(5) == 0) {
					zapWidth.set(i, zapWidth.get(i)-1);
				}
				if(rand.nextInt(5) == 0) {
					zapHeight.set(i, zapHeight.get(i)-1);
				}
			}
		}
		for(int i = 0; i <= zapLines.size()-1; i++) {
			if(zapLinesX.get(i).equals(zapLinesX2.get(i)) ||zapLinesY.get(i).equals(zapLinesY2.get(i)) || zapLinesTimer.get(i) < System.currentTimeMillis()) {
				zapLines.remove(i);
				zapLinesX.remove(i);
				zapLinesY.remove(i);
				zapLinesX2.remove(i);
				zapLinesY2.remove(i);
				zapLinesTimer.remove(i);
			}
			else {
				if(zapLinesX2.get(i) < zapLinesX.get(i)) {
					zapLinesX2.set(i, zapLinesX2.get(i)-1);
					zapLinesX.set(i, zapLinesX.get(i)-2);
				}
				else {
					zapLinesX2.set(i, zapLinesX2.get(i)+1);
					zapLinesX.set(i, zapLinesX.get(i)+2);
				}
				if(zapLinesY2.get(i) < zapLinesY.get(i)) {
					zapLinesY2.set(i, zapLinesY2.get(i)-1);
					zapLinesY.set(i, zapLinesY.get(i)-2);
				}
				else {
					zapLinesY2.set(i, zapLinesY2.get(i)+1);
					zapLinesY.set(i, zapLinesY.get(i)+2);
				}
			}
				
		}
		for(int i = 0; i <= zapLinez.size()-1; i++) {
			if(zapLinezX.get(i).equals(zapLinezX2.get(i)) ||zapLinezY.get(i).equals(zapLinezY2.get(i)) || zapLinezTimer.get(i) < System.currentTimeMillis() ) {
				zapLinez.remove(i);
				zapLinezX.remove(i);
				zapLinezY.remove(i);
				zapLinezX2.remove(i);
				zapLinezY2.remove(i);
				zapLinezTimer.remove(i);
			}
			else {
				if(zapLinezX2.get(i) > zapLinezX.get(i)) 
					zapLinezX2.set(i, zapLinezX2.get(i)-rand.nextInt(4)-1);
				else 
					zapLinezX2.set(i, zapLinezX2.get(i)+rand.nextInt(4)+1);
				if(zapLinezY2.get(i) > zapLinezY.get(i)) 
					zapLinezY2.set(i, zapLinezY2.get(i)-rand.nextInt(4)-1);
				else 
					zapLinezY2.set(i, zapLinezY2.get(i)+rand.nextInt(4)+1);
			}
				
		}
		if(zap.getCount() == 8)
			game.getController().removeEntity(this);
		zap.runAnimation();
	}

	public void render(Graphics g) {
		Random rand = new Random();
		if(g2d == null)
			g2d = (Graphics2D)g.create();
		//g2d.setColor(Color.YELLOW);
		int iColor = rand.nextInt(3);
		if((!Game.isPaused() || game.getEnemyHitPauseTimer() > System.currentTimeMillis()) && (g2d.getColor() != Color.YELLOW || g2d.getColor() != Color.ORANGE || g2d.getColor() != Color.WHITE)) {
			if(iColor == 0)
				g2d.setColor(Color.YELLOW);
			else if(iColor == 1)
				g2d.setColor(Color.ORANGE);
			else
				g2d.setColor(Color.WHITE);
		}
		for(int i = 0; i <= zapWidth.size()-1;i++) {
			g2d.fillOval((int)zapX.get(i), (int)zapY.get(i), zapWidth.get(i), zapHeight.get(i));
		}
		for(int i = 0; i <= zapLines.size()-1;i++) {
			g2d.drawLine((int)zapLinesX.get(i), (int)zapLinesY.get(i), (int)zapLinesX2.get(i), (int)zapLinesY2.get(i));
		}
		for(int i = 0; i <= zapLinez.size()-1;i++) {
			g2d.drawLine((int)zapLinezX.get(i), (int)zapLinezY.get(i), (int)zapLinezX2.get(i), (int)zapLinezY2.get(i));
		}
		if(System.currentTimeMillis() % 60 == 0 && Game.isPaused())
			tick();
		zap.drawAnimation(g, x, y, 0);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}

	public double getX() {
		return 0;
	}

	public double getY() {
		return 0;
	}
	
	public double getVelX() {
		return 0;
	}

	public double getVelY() {
		return 0;
	}

	public String entityName() {
		return "zapFX";
	}

	public void close() {
		
	}

}
