package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.libs.Animation;

public class BombOmbItem extends GameObject implements EntityE {
	Textures tex;
	Game game;
	Animation walking;
	Animation glowing;
	Animation exploding;
	private boolean glowingBoolean = false;
	private boolean explodingBoolean = false;
	private boolean shrapnel1Spawn = false;
	private boolean shrapnel2Spawn = false;
	private boolean shrapnel3Spawn = false;
	private boolean bowserHurt = false;
	public BombOmbItem(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		walking  = new Animation(6, tex.bombOmbItem[0], tex.bombOmbItem[1], tex.bombOmbItem[0], tex.bombOmbItem[2]);
		glowing  = new Animation(6, tex.bombOmbItem[0], tex.bombOmbItem[3], tex.bombOmbItem[4], tex.bombOmbItem[5],
				tex.bombOmbItem[6],tex.bombOmbItem[7],tex.bombOmbItem[6],tex.bombOmbItem[5],tex.bombOmbItem[4],
				tex.bombOmbItem[3],tex.bombOmbItem[0],tex.bombOmbItem[3],tex.bombOmbItem[4],tex.bombOmbItem[5],
				tex.bombOmbItem[6],tex.bombOmbItem[7],tex.bombOmbItem[6],tex.bombOmbItem[5],tex.bombOmbItem[4],
				tex.bombOmbItem[3],tex.bombOmbItem[0],tex.bombOmbItem[3],tex.bombOmbItem[4],tex.bombOmbItem[5],
				tex.bombOmbItem[6],tex.bombOmbItem[7]);
		exploding  = new Animation(6, tex.bombOmbItem[8], tex.bombOmbItem[9], tex.bombOmbItem[10], tex.bombOmbItem[11],
				tex.bombOmbItem[12],tex.bombOmbItem[13],tex.bombOmbItem[14],tex.bombOmbItem[15]);

		walking.nextFrame();
		walking.setCount(0);
		glowing.nextFrame();
		glowing.setCount(0);
		exploding.nextFrame();
		exploding.setCount(0);
	}

	public void tick() {
		if(glowingBoolean == false && explodingBoolean == false) {
			if(y < -18)
				game.ee.remove(this);
			for(int i = 0; i < game.ea.size(); i++){
				EntityA tempEnt = game.ea.get(i);
				if(Physics.Collision(this, tempEnt)){
					glowingBoolean = true;
					explodingBoolean = true;
					if(Game.currentlySelectedFireball != 3)
						game.ea.remove(tempEnt);
					return;
				}
			}
			for(int i = 0; i < game.eb.size(); i++){
				EntityB tempEnt = game.eb.get(i);
				Rectangle rect = new Rectangle((int)x-20, (int)y-20, 40, 40);
				if(Physics.Collision(rect, tempEnt)){
					glowingBoolean = true;
				}
			}
			for(int i = 0; i < game.ec.size(); i++){
				EntityC tempEnt = game.ec.get(i);
				if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityCDead()){
					if(tempEnt.entityName().equals("BuzzyBeetleShell")) {
						//game.getHUD().setScore(500);
						glowingBoolean = true;
						explodingBoolean = true;
						return;
					}
					else if(tempEnt.entityName().equals("BulletBill")) 
						game.getHUD().setScore(200);
					else
						game.getHUD().setScore(200);
					game.ec.remove(tempEnt);
					glowingBoolean = true;
					explodingBoolean = true;
				}
			}
			for(int i = 0; i < game.ee.size(); i++){
				EntityE tempEnt = game.ee.get(i);
				if(Physics.Collision(this, tempEnt) && (tempEnt.entityName().equals("bombOmbShrapnel2") ||
						tempEnt.entityName().equals("bombOmbShrapnel1"))){
					glowingBoolean = true;
					explodingBoolean = true;
					game.ee.remove(tempEnt);
				}
			}
			walking.runAnimation();
			y--;
		}
		else if(glowingBoolean == true && explodingBoolean == false) {
			if(glowing.getCount() == 25) {
				explodingBoolean = true;
			}
			glowing.runAnimation();
			for(int i = 0; i < game.ea.size(); i++){
				EntityA tempEnt = game.ea.get(i);
				if(Physics.Collision(this, tempEnt)){
					explodingBoolean = true;
					if(Game.currentlySelectedFireball != 3)
						game.ea.remove(tempEnt);
					return;
				}
			}
			for(int i = 0; i < game.eb.size(); i++){
				EntityB tempEnt = game.eb.get(i);
				if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityBDead()){
					//game.eb.get(i).setEntityBDead(true);
					if(!tempEnt.enemyType().equals("Bowser")) {
						game.eb.remove(tempEnt);
						if(!game.eb.isEmpty() && game.getSpawnDone2() == false) {
							if (game.eb.size() == 2)
								game.enemySpeedIncrease+= 0.3;
							game.enemySpeedIncrease+= 0.06;
						}else if(!game.eb.isEmpty() && game.getSpawnDone3() == false) {
							if (game.eb.size() == 2)
								game.enemySpeedIncrease+= 0.4;
							game.enemySpeedIncrease+= 0.06; //0.7
						}else if(!game.eb.isEmpty() && game.getSpawnDone4() == false) {
							if (game.eb.size() == 2)
								game.enemySpeedIncrease+= 0.9;
							game.enemySpeedIncrease+= 0.08; //0.7
						}
						game.getHUD().setScore(200);
					}
					explodingBoolean = true;
				}
			}
			for(int i = 0; i < game.ec.size(); i++){
				EntityC tempEnt = game.ec.get(i);
				if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityCDead()){
					if(tempEnt.entityName().equals("BuzzyBeetleShell")) {
						//game.getHUD().setScore(500);
						explodingBoolean = true;
						return;
					}
					else if(tempEnt.entityName().equals("BulletBill")) 
						game.getHUD().setScore(200);
					else
						game.getHUD().setScore(200);
					game.ec.remove(tempEnt);
					explodingBoolean = true;
				}
			}
			for(int i = 0; i < game.ee.size(); i++){
				EntityE tempEnt = game.ee.get(i);
				if(Physics.Collision(this, tempEnt) && (tempEnt.entityName().equals("bombOmbShrapnel2") ||
						tempEnt.entityName().equals("bombOmbShrapnel1"))){
					explodingBoolean = true;
					game.ee.remove(tempEnt);
				}
			}
		}
		else {
			if(exploding.getCount() == 2) {
				y-=0.04;
				x-=0.1;
				Random rand2 = new Random();
				if(rand2.nextInt(70) == 0)
					shrapnel2Spawn = false;
				if(shrapnel2Spawn == false) {
					Random rand = new Random();
					int randy = rand.nextInt(1);
					if(randy == 4) 
						game.getController().addEntity(new BombOmbShrapnel2(x-16,y, tex, game,0));
					else if(randy == 3) 
						game.getController().addEntity(new BombOmbShrapnel2(x+16,y, tex, game,1));
					else if(randy == 2) 
						game.getController().addEntity(new BombOmbShrapnel2(x-16,y+8, tex, game,2));
					else if(randy == 1) 
						game.getController().addEntity(new BombOmbShrapnel2(x+16,y+8, tex, game,3));
					else 
						game.getController().addEntity(new BombOmbShrapnel2(x,y+8, tex, game,4));
					
					shrapnel2Spawn = true;
				}

				if(shrapnel1Spawn == false) {
					boolean leftShrapnelSpawn = false;
					boolean rightShrapnelSpawn = false;
					Random rand = new Random();
					if(rand.nextInt(2) == 0) {
						leftShrapnelSpawn = true;
						game.getController().addEntity(new BombOmbShrapnel(x-8,y-8, tex, game,true));
						//spawnleft
					}
					else if(rand.nextInt(2) == 0) {
						rightShrapnelSpawn = true;
						game.getController().addEntity(new BombOmbShrapnel(x+8,y-8, tex, game,false));
						//spawnright
					}
					if(leftShrapnelSpawn == false && rightShrapnelSpawn == false) {
						if(rand.nextInt(2) == 0) {
							//spawnleft
							game.getController().addEntity(new BombOmbShrapnel(x-8,y-8, tex, game,true));
						}
						else {
							//spawnright
							game.getController().addEntity(new BombOmbShrapnel(x+8,y-8, tex, game,false));
						}
					}
					shrapnel1Spawn = true;
				}
				if(shrapnel3Spawn == false) {
					game.getController().addEntity(new BombOmbShrapnel2(x-16,y, tex, game,0));
					game.getController().addEntity(new BombOmbShrapnel2(x+22,y, tex, game,1));
					shrapnel3Spawn = true;
				}
			}
			else if(exploding.getCount() == 8) {
				game.ee.remove(this);
			}
			exploding.runAnimation();
			for(int i = 0; i < game.eb.size(); i++){
				EntityB tempEnt = game.eb.get(i);
				if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityBDead()){

					if(!tempEnt.enemyType().equals("Bowser")) {
						//game.eb.get(i).setEntityBDead(true);
						game.eb.remove(tempEnt);
						if(!game.eb.isEmpty() && game.getSpawnDone2() == false) {
							if (game.eb.size() == 2)
								game.enemySpeedIncrease+= 0.3;
							game.enemySpeedIncrease+= 0.06;
						}else if(!game.eb.isEmpty() && game.getSpawnDone3() == false) {
							if (game.eb.size() == 2)
								game.enemySpeedIncrease+= 0.4;
							game.enemySpeedIncrease+= 0.06; //0.7
						}else if(!game.eb.isEmpty() && game.getSpawnDone4() == false) {
							if (game.eb.size() == 2)
								game.enemySpeedIncrease+= 0.9;
							game.enemySpeedIncrease+= 0.08; //0.7
						}
						game.getHUD().setScore(200);
					}
					else if(!bowserHurt) {
						HUD.HEALTH -= 4;
						bowserHurt = true;
					}
				}
			}
			for(int i = 0; i < game.ec.size(); i++){
				EntityC tempEnt = game.ec.get(i);
				if(Physics.Collision(this, tempEnt) && !tempEnt.getEntityCDead()){
					if(tempEnt.entityName().equals("BuzzyBeetleShell")) {
						//game.getHUD().setScore(500);
						return;
					}
					else if(tempEnt.entityName().equals("BulletBill")) 
						game.getHUD().setScore(200);
					else
						game.getHUD().setScore(200);
					game.ec.remove(tempEnt);
				}
			}
			for(int i = 0; i < game.ee.size(); i++){
				EntityE tempEnt = game.ee.get(i);
				if(Physics.Collision(this, tempEnt) && (tempEnt.entityName().equals("bombOmbShrapnel2") ||
						tempEnt.entityName().equals("bombOmbShrapnel1"))){
					game.ee.remove(tempEnt);
				}
			}
			y-=0.04;
			x-=0.07;
		}
	}

	public void render(Graphics g) {
		if(glowingBoolean == false && explodingBoolean == false) {
			walking.drawAnimation(g, x, y, 0);
		}
		else if(glowingBoolean == true && explodingBoolean == false) {
			glowing.drawAnimation(g, x, y, 0);
		}
		else {
			exploding.drawAnimation(g, x, y, 0);
		}
	}
	
	public Rectangle getBounds() {
		//if(glowing.getCount() == 0 && exploding.getCount() == 0)
		if(glowingBoolean == false && explodingBoolean == false) {
			//return new Rectangle((int)x-17, (int)y-17, 35, 35);
			return new Rectangle((int)x, (int)y, 12, 19);
		}
		else if(glowingBoolean == true && explodingBoolean == false) {
			if(glowing.getCount() <= 2 || glowing.getCount() == 4 || glowing.getCount() == 6 || (glowing.getCount() >=8 && glowing.getCount() <= 12) ||
					glowing.getCount() == 14 || glowing.getCount() == 16 || (glowing.getCount() >= 18 && glowing.getCount() <= 22) || glowing.getCount() == 24)
				return new Rectangle((int)x, (int)y, 12, 19);
			else if(glowing.getCount() == 3 || glowing.getCount() == 7 || glowing.getCount() == 13 || glowing.getCount() == 17 || glowing.getCount() == 23)
				return new Rectangle((int)x, (int)y, 12, 20);
			else
				return new Rectangle((int)x, (int)y, 12, 18);
		}
		else {
			switch(exploding.getCount()) {
			case 0:
				return new Rectangle((int)x, (int)y, 12, 18);
			case 1:
				return new Rectangle((int)x, (int)y, 14, 16);
			case 2:
				return new Rectangle((int)x, (int)y, 21, 18);
			case 3:
				return new Rectangle((int)x+1, (int)y, 25, 25);//27, 25
			case 4:
				return new Rectangle((int)x, (int)y, 25, 22);
			case 5:
				return new Rectangle((int)x, (int)y, 19, 21);
			case 6:
				return new Rectangle((int)x, (int)y, 17, 14);
			case 7:
				return new Rectangle((int)x, (int)y, 11, 11);
			default:
				break;
			}
		}
		return new Rectangle((int)x, (int)y, 12, 19);//default
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public String entityName() {
		return "bombOmb";
	}
	
	public void setHitIndicator(boolean b) {
		return;
	}
	
	public boolean getHitIndicator() {
		return false;
	}
	
	public void setEntityEDead(boolean dead) {
		this.glowingBoolean = dead;
		this.explodingBoolean = dead;
	}
	
	public boolean getEntityEDead() {
		return explodingBoolean;
	}
	
	public void close() {
		
	}
	
}
