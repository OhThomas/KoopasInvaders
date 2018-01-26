package com.game.src.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	public static float HEALTH = 0;
	
	private float greenValue = 255;
	
	private int score = 0;
	
	private int level = 1;
	
	private double timer1 = 100;
	
	private double timer2 = 100;
	
	public void tick(){
		if(timer1 > 0){
			HEALTH++;
			timer1--;
		}
		if(timer1 <= 0 && HEALTH <= 0){
			if(timer2 > 0)
				timer2--;
		}
		HEALTH = Game.clamp(HEALTH, 0, 100);
		greenValue = Game.clamp(greenValue, 0, 255);
		greenValue = HEALTH*2;
		
		score++;
	}
	
	public void render(Graphics g){
		//HEALTH BAR APPEARS
		if(timer1 > 0){
			g.setColor(Color.gray);
			g.fillRect(15, 15, (int)HEALTH * 2, 32); //(x,x,200,x)
			g.setColor(new Color(75,(int)greenValue,0));
			g.fillRect(15, 15, (int)HEALTH * 2, 32);
			g.setColor(Color.white);
			g.drawRect(15, 15, (int)HEALTH * 2, 32); //(x,x,200,x)
			g.drawString(HEALTH+"%", 15, 15);
		}
		else if (timer1 <= 0 && HEALTH > 0){
			g.setColor(Color.gray);
			g.fillRect(15, 15, 200, 32); //(x,x,200,x)
			g.setColor(new Color(75,(int)greenValue,0));
			g.fillRect(15, 15, (int)HEALTH * 2, 32);
			g.setColor(Color.white);
			g.drawRect(15, 15, 200, 32); //(x,x,200,x)
			g.drawString(HEALTH+"%", 15, 15);
		}
		//HEALTH BAR DEPLETES
		else if(timer1 <= 0 && HEALTH <= 0){
			if(timer2 <= 0)
				return;
			g.setColor(Color.gray);
			g.fillRect(15, 15, (int)timer2 * 2, 32); //(x,x,200,x)
			g.setColor(Color.white);
			g.drawRect(15, 15, (int)timer2 * 2, 32); //(x,x,200,x)
		}
		//g.drawString("Score: " + score, 14, 64);
		//g.drawString("Level: " + level, 170, 64);
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public int getScore(){
		return score;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public int getLevel(){
		return level;
	}
	
	public double getTimer(){
		return timer1;
	}
}
