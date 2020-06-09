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
	private Game game;
	ArrayList<Integer> chompWidth = new ArrayList<Integer>();
	ArrayList<Integer> chompHeight = new ArrayList<Integer>();
	ArrayList<Integer> chompX = new ArrayList<Integer>();
	ArrayList<Integer> chompY = new ArrayList<Integer>();
	ArrayList<Long> chompTimer = new ArrayList<Long>();
	private int width = 0;
	private int height = 0;
	Graphics2D g2d = null;
	String chomped = "";
	public ChompFX(Game game, double x, double y, String chomped) {
		super(x, y);
		this.game = game;
		Random rand = new Random();
		this.chomped = chomped;
		String s = Character.toString(chomped.charAt(0));
		String ss = s.toUpperCase();
		this.chomped.replaceFirst(s, ss);
		//System.out.println(this.chomped);
		width = 10;
		height = 10;
		int chompStackSize = rand.nextInt(15)+3;
		if(chomped.equals("ContraFireball") || chomped.equals("Flower")){
			for(int i = 0; i <= chompStackSize; i++) {
				chompWidth.add(rand.nextInt(4)); 
				chompHeight.add(rand.nextInt(4)); 
				chompX.add((int)x+rand.nextInt(6)-rand.nextInt(6));
				chompY.add((int)y+rand.nextInt(14)-rand.nextInt(5));
				chompTimer.add(System.currentTimeMillis() + 1000 + (long)rand.nextInt(1000));
			}
		}
		else if(chomped.equals("LavaBubble") || chomped.equals("Lava") || chomped.equals("ZigzagLavaBubble")){
			for(int i = 0; i <= chompStackSize; i++) {
				chompWidth.add(7+rand.nextInt(6)); 
				chompHeight.add(7+rand.nextInt(6)); 
				chompX.add((int)x+rand.nextInt(8)-rand.nextInt(8));
				chompY.add((int)y+rand.nextInt(8)-rand.nextInt(8));
				chompTimer.add(System.currentTimeMillis() + 1000 + (long)rand.nextInt(1000));
			}
		}
		else if(chomped.equals("ChainChompChainDead")){
			for(int i = 0; i <= chompStackSize; i++) {
				chompWidth.add(rand.nextInt(4)); 
				chompHeight.add(rand.nextInt(4)); 
				chompX.add((int)x+rand.nextInt(5)-rand.nextInt(5));
				chompY.add((int)y+rand.nextInt(7)-rand.nextInt(4));
				chompTimer.add(System.currentTimeMillis() + 1000 + (long)rand.nextInt(1000));
			}
		}
		else {
			for(int i = 0; i <= chompStackSize; i++) {
				chompWidth.add(rand.nextInt(7)); 
				chompHeight.add(rand.nextInt(7)); 
				chompX.add((int)x+rand.nextInt(13)-rand.nextInt(13));
				chompY.add((int)y+rand.nextInt(13)-rand.nextInt(13));
				chompTimer.add(System.currentTimeMillis() + 1000 + (long)rand.nextInt(1000));
			}
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
		if(chompWidth.isEmpty()) {
			if(g2d != null)
				g2d.dispose();
			game.getController().getEntityF().remove(this);
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
		else if(chomped.equals("RedShell") || chomped.equals("RedShellCircle") || chomped.equals("LakituRedShell")) {
			if((g2d.getColor() != Color.RED || g2d.getColor() != Color.WHITE || g2d.getColor() != Color.BLACK)) {
				int iColor = rand.nextInt(3);
				if(iColor == 0)
					g2d.setColor(Color.RED);
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
		else if(chomped.equals("GloveFireball")) {
			Color red = new Color(216,40,0);
			if((g2d.getColor() != red && g2d.getColor() != Color.BLACK)) {
				int iColor = rand.nextInt(300);
				if(iColor == 0)
					g2d.setColor(red);
				else if(iColor == 28)
					g2d.setColor(Color.BLACK);
				else
					g2d.setColor(red);
			}
		}
		else if(chomped.equals("ContraFireball")) {
			Color red = new Color(216,40,0);
			Color red2 = new Color(252,116,96);
			Color white = new Color(248,248,248);
			if((g2d.getColor() != red && g2d.getColor() != red2 && g2d.getColor() != white)) {
				int iColor = rand.nextInt(3);
				if(iColor == 0)
					g2d.setColor(red);
				else if(iColor == 1)
					g2d.setColor(white);
				else
					g2d.setColor(red2);
			}
		}
		else if(chomped.equals("Flower") || chomped.equals("FlowerFX")) {
			if((g2d.getColor() != Color.GREEN && g2d.getColor() != Color.YELLOW && g2d.getColor() != Color.WHITE)) {
				int iColor = rand.nextInt(3);
				if(iColor == 0)
					g2d.setColor(Color.GREEN);
				else if(iColor == 1)
					g2d.setColor(Color.WHITE);
				else
					g2d.setColor(Color.YELLOW);
			}
		}
		else if(chomped.equals("Wiggler") || chomped.equals("WigglerBody")) {
			Color red = new Color(184,40,0);
			Color orange = new Color(248,136,0);
			Color yellow = new Color(248,248,0);
			if((g2d.getColor() != red && g2d.getColor() != orange && g2d.getColor() != yellow)) {
				int iColor = rand.nextInt(3);
				if(iColor == 0)
					g2d.setColor(red);
				else if(iColor == 1)
					g2d.setColor(orange);
				else
					g2d.setColor(yellow);
			}
		}
		else if(chomped.equals("CheepCheeps")) {
			Color red = new Color(184,40,0);
			Color yellow = new Color(248,248,0);
			Color white = new Color(248,248,248);
			if((g2d.getColor() != red && g2d.getColor() != white && g2d.getColor() != yellow)) {
				int iColor = rand.nextInt(3);
				if(iColor == 0)
					g2d.setColor(red);
				else if(iColor == 1)
					g2d.setColor(white);
				else
					g2d.setColor(yellow);
			}
		}
		else if(chomped.equals("Cloud")) {
			Color white = new Color(248,248,248);
			if((g2d.getColor() != white && g2d.getColor() != Color.WHITE && g2d.getColor() != Color.BLACK)) {
				int iColor = rand.nextInt(3);
				if(iColor == 0)
					g2d.setColor(white);
				else if(iColor == 1)
					g2d.setColor(Color.WHITE);
				else
					g2d.setColor(Color.BLACK);
			}
		}
		else if(chomped.equals("Lakitu") || chomped.equals("NakedLakituFX")) {
			Color orange = new Color(248,136,0);
			Color red = new Color(184,40,0);
			Color white = new Color(248,248,248);
			if((g2d.getColor() != orange && g2d.getColor() != red && g2d.getColor() != white)) {
				int iColor = rand.nextInt(6);
				if(iColor == 0 || iColor == 1 || iColor == 2)
					g2d.setColor(orange);
				else if(iColor == 3 || iColor == 4)
					g2d.setColor(red);
				else
					g2d.setColor(white);
			}
		}
		else if(chomped.equals("Fish") || chomped.equals("LakituFish")) {
			Color orange = new Color(248,136,0);
			Color red = new Color(184,40,0);
			Color lightBlue = new Color(136,136,248);
			Color darkBlue = new Color(64,64,216);
			Color white = new Color(248,248,248);
			if((g2d.getColor() != orange && g2d.getColor() != red && g2d.getColor() != white && g2d.getColor() != lightBlue
					 && g2d.getColor() != darkBlue)) {
				int iColor = rand.nextInt(7);
				if(iColor == 0)
					g2d.setColor(orange);
				else if(iColor == 1)
					g2d.setColor(red);
				else if(iColor == 2 || iColor == 3)
					g2d.setColor(lightBlue);
				else if(iColor == 4 || iColor == 5)
					g2d.setColor(darkBlue);
				else
					g2d.setColor(white);
			}
		}
		else if(chomped.equals("Spike") || chomped.equals("LakituSpike")) {
			Color orange = new Color(248,136,0);
			Color red = new Color(184,40,0);
			Color red2 = new Color(248,0,0);
			Color darkRed = new Color(136,0,0);
			Color white = new Color(248,248,248);
			if((g2d.getColor() != orange && g2d.getColor() != red && g2d.getColor() != white && g2d.getColor() != darkRed &&
					g2d.getColor() != red2)) {
				int iColor = rand.nextInt(5);
				if(iColor == 0)
					g2d.setColor(orange);
				else if(iColor == 1)
					g2d.setColor(red);
				else if(iColor == 2)
					g2d.setColor(red2);
				else if(iColor == 3)
					g2d.setColor(darkRed);
				else
					g2d.setColor(white);
			}
		}else if(chomped.equals("Amp")) {
			Color grey = new Color(107,105,107);
			Color darkGrey = new Color(82,81,82);
			Color yellow = new Color(255,227,115);
			Color orange = new Color(255,203,57);
			Color lightGrey = new Color(206,203,206);
			if((g2d.getColor() != orange && g2d.getColor() != grey && g2d.getColor() != yellow && g2d.getColor() != lightGrey &&
					g2d.getColor() != darkGrey)) {
				int iColor = rand.nextInt(5);
				if(iColor == 0)
					g2d.setColor(orange);
				else if(iColor == 1)
					g2d.setColor(lightGrey);
				else if(iColor == 2)
					g2d.setColor(yellow);
				else if(iColor == 3)
					g2d.setColor(darkGrey);
				else
					g2d.setColor(grey);
			}
		}else if(chomped.equals("BombOmb") || chomped.equals("LakituBombOmb")) {
			Color darkBlue = new Color(53,50,95);
			Color yellow = new Color(237,173,54);
			Color red = new Color(217,43,69);
			Color darkRed = new Color(171,31,52);
			Color moreYellow = new Color(248,216,64);
			if((g2d.getColor() != darkBlue && g2d.getColor() != yellow && g2d.getColor() != red && g2d.getColor() != darkRed &&
					g2d.getColor() != moreYellow)) {
				int iColor = rand.nextInt(5);
				if(iColor == 0)
					g2d.setColor(darkBlue);
				else if(iColor == 1)
					g2d.setColor(yellow);
				else if(iColor == 2)
					g2d.setColor(red);
				else if(iColor == 3)
					g2d.setColor(darkRed);
				else
					g2d.setColor(moreYellow);
			}
		}else if(chomped.equals("BombOmbShrapnel1") || chomped.equals("BombOmbShrapnel2")) {
			Color darkBlue = new Color(53,50,95);
			Color blue = new Color(82,99,167);
			Color lightBlue = new Color(151,179,206);
			if((g2d.getColor() != darkBlue && g2d.getColor() != blue && g2d.getColor() != lightBlue )) {
				int iColor = rand.nextInt(5);
				if(iColor == 0 || iColor == 1 || iColor == 2)
					g2d.setColor(darkBlue);
				else if(iColor == 3)
					g2d.setColor(blue);
				else if(iColor == 4)
					g2d.setColor(lightBlue);
			}
		}else if(chomped.equals("ChainChomp")) {
			Color darkBlue = new Color(59,68,99);
			Color grey = new Color(80,88,115);
			Color white = new Color(242,242,242);
			if((g2d.getColor() != darkBlue && g2d.getColor() != grey && g2d.getColor() != white )) {
				int iColor = rand.nextInt(3);
				if(iColor == 0)
					g2d.setColor(darkBlue);
				else if(iColor == 1)
					g2d.setColor(grey);
				else if(iColor == 2)
					g2d.setColor(white);
			}
		}else if(chomped.equals("ChainChompChain") || chomped.equals("ChainChompChainDead")) {
			Color darkBlue = new Color(59,68,99);
			Color grey = new Color(80,88,115);
			Color darkerBlue = new Color(31,36,54);
			if((g2d.getColor() != darkBlue && g2d.getColor() != grey && g2d.getColor() != darkerBlue )) {
				int iColor = rand.nextInt(3);
				if(iColor == 0)
					g2d.setColor(darkBlue);
				else if(iColor == 1)
					g2d.setColor(grey);
				else if(iColor == 2)
					g2d.setColor(darkerBlue);
			}
		}else if(chomped.equals("Parachute")) {
			Color lightBlue = new Color(136,136,248);
			Color purple = new Color(104,104,216);
			Color blue = new Color(64,64,216);
			Color white = new Color(248,248,248);
			if((g2d.getColor() != lightBlue && g2d.getColor() != purple && g2d.getColor() != blue && g2d.getColor() != white )) {
				int iColor = rand.nextInt(4);
				if(iColor == 0)
					g2d.setColor(lightBlue);
				else if(iColor == 1)
					g2d.setColor(purple);
				else if(iColor == 2)
					g2d.setColor(blue);
				else if(iColor == 3)
					g2d.setColor(white);
			}
		}else if(chomped.equals("LakituBat") || chomped.equals("Bat") || chomped.equals("Swoop")) {
			Color lightGreen = new Color(0,248,0);
			Color green = new Color(0,184,0);
			Color darkGreen = new Color(0,120,0);
			Color orange = new Color(248,136,0);
			Color red = new Color(184,40,0);
			if((g2d.getColor() != lightGreen && g2d.getColor() != green && g2d.getColor() != darkGreen && g2d.getColor() != orange 
					&& g2d.getColor() != red)) {
				int iColor = rand.nextInt(5);
				if(iColor == 0)
					g2d.setColor(lightGreen);
				else if(iColor == 1)
					g2d.setColor(green);
				else if(iColor == 2)
					g2d.setColor(darkGreen);
				else if(iColor == 3)
					g2d.setColor(orange);
				else if(iColor == 4)
					g2d.setColor(red);
			}
		}else if(chomped.equals("LavaBubble") || chomped.equals("Lava")) {
			Color darkRed = new Color(184,40,0);
			Color orange = new Color(248,136,0);
			Color yellow = new Color(248,192,0);
			if((g2d.getColor() != darkRed && g2d.getColor() != orange && g2d.getColor() != yellow )) {
				int iColor = rand.nextInt(3);
				if(iColor == 0)
					g2d.setColor(darkRed);
				else if(iColor == 1)
					g2d.setColor(orange);
				else if(iColor == 2)
					g2d.setColor(yellow);
			}
		}else if(chomped.equals("ZigzagLavaBubble")) {
			Color lightOrange = new Color(248,192,0);
			Color orange = new Color(248,120,0);
			Color yellow = new Color(248,248,0);
			Color darkRed = new Color(136,0,0);
			Color lightRed = new Color(248,0,0);
			Color red = new Color(184,0,0);
			if((g2d.getColor() != lightOrange && g2d.getColor() != orange && g2d.getColor() != yellow && g2d.getColor() != darkRed
					&& g2d.getColor() != lightRed && g2d.getColor() != red)) {
				int iColor = rand.nextInt(6);
				if(iColor == 0)
					g2d.setColor(lightOrange);
				else if(iColor == 1)
					g2d.setColor(orange);
				else if(iColor == 2)
					g2d.setColor(yellow);
				else if(iColor == 3)
					g2d.setColor(darkRed);
				else if(iColor == 4)
					g2d.setColor(lightRed);
				else if(iColor == 5)
					g2d.setColor(red);
			}
		}else if(chomped.equals("Thwimp")) {
			Color grey1 = new Color(248,248,248);
			Color grey2 = new Color(224,224,224);
			Color grey3 = new Color(192,192,192);
			Color grey4 = new Color(160,160,160);
			Color grey5 = new Color(112,112,112);
			if((g2d.getColor() != grey1 && g2d.getColor() != grey2 && g2d.getColor() != grey3 && g2d.getColor() != grey4
					&& g2d.getColor() != grey5)) {
				int iColor = rand.nextInt(5);
				if(iColor == 0)
					g2d.setColor(grey1);
				else if(iColor == 1)
					g2d.setColor(grey2);
				else if(iColor == 2)
					g2d.setColor(grey3);
				else if(iColor == 3)
					g2d.setColor(grey4);
				else if(iColor == 4)
					g2d.setColor(grey5);
			}
		}else if(chomped.equals("Mechakoopa")) {
			Color darkGreen = new Color(0,120,0);
			Color lightGreen = new Color(0,184,0);
			Color orange = new Color(248,136,0);
			Color red = new Color(184,40,0);
			if((g2d.getColor() != darkGreen && g2d.getColor() != lightGreen && g2d.getColor() != orange && g2d.getColor() != red)) {
				int iColor = rand.nextInt(4);
				if(iColor == 0)
					g2d.setColor(darkGreen);
				else if(iColor == 1)
					g2d.setColor(lightGreen);
				else if(iColor == 2)
					g2d.setColor(orange);
				else if(iColor == 3)
					g2d.setColor(red);
			}
		}
		for(int i = 0; i <= chompWidth.size()-1;i++) {
			g2d.fillOval((int)chompX.get(i), (int)chompY.get(i), chompWidth.get(i), chompHeight.get(i));
		}
//		for(int i = 0; i <= chompWidth.size()-1;i++) {
//			g2d.fillOval((int)chompX.get(i), (int)chompY.get(i), chompWidth.get(i), chompHeight.get(i));
//		}
//		for(int i = 0; i <= chompWidth.size()-1;i++) {
//			g2d.fillOval((int)chompX.get(i), (int)chompY.get(i), chompWidth.get(i), chompHeight.get(i));
//		}
//		for(int i = 0; i <= chompWidth.size()-1;i++) {
//			g2d.fillOval((int)chompX.get(i), (int)chompY.get(i), chompWidth.get(i), chompHeight.get(i));
//		}
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
		return "chompFX";
	}

	public void close() {
		if(g2d != null)
			g2d.dispose();
	}

}
