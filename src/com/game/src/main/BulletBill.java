package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityC;
import com.game.src.main.libs.Animation;

public class BulletBill extends GameObject implements EntityC{

	private boolean barrier = false;
	
	private Textures tex;
	private Game game;
	private float timer1 = 100;										//timer for chasing sequence
	public double speedIncrease = 0.1;								//way to set speed
	private boolean bulletBillisDead = false;						//way to play death animation without killing Mario
	private boolean stayTracked = false;							//will always track/follow mario if true
	private boolean flicker = false;								//flicker triggers the render to not run every 60th millisecond
	private long flickerTimer1 = 0;									//flicker timer to keep track of time on
	private long flickerTimer2 = 0;									//flicker timer to keep track of time off
	private int bulletBillXDirectionAfterDeath = 0;					//determines where the smoke effect goes after bb dies
	private int smokeCount = 0;										//produces smoke
	private String entityName = "BulletBill";						//name of entity
	
	Animation animD;
	Animation animDL;
	Animation animDR;
	Animation animExplosion;

	SoundLoops bulletBillDeathSoundLoop;
	SoundLoops bulletBillSpawnSoundLoop;

	BulletBillSmokeFXRed smoke;
	
	public BulletBill(double x, double y, Textures tex, Game game){
		super(x,y);
		this.tex = tex;
		this.game = game;
		
		animD = new Animation(6, tex.bulletBillD[0], tex.bulletBillD[1], tex.bulletBillD[2], tex.bulletBillD[3],
				 tex.bulletBillD[4], tex.bulletBillD[5], tex.bulletBillD[6], tex.bulletBillD[7], tex.bulletBillD[8],
				 tex.bulletBillD[9], tex.bulletBillD[10], tex.bulletBillD[11], tex.bulletBillD[12], tex.bulletBillD[13],
				 tex.bulletBillD[14], tex.bulletBillD[15], tex.bulletBillD[16]);
		
		animDL = new Animation(6, tex.bulletBillDL[0], tex.bulletBillDL[1], tex.bulletBillDL[2], tex.bulletBillDL[3],
				 tex.bulletBillDL[4], tex.bulletBillDL[5], tex.bulletBillDL[6], tex.bulletBillDL[7],
				 tex.bulletBillDL[8], tex.bulletBillDL[9]);
		
		animDR = new Animation(6, tex.bulletBillDR[0], tex.bulletBillDR[1], tex.bulletBillDR[2], tex.bulletBillDR[3],
				 tex.bulletBillDR[4], tex.bulletBillDR[5], tex.bulletBillDR[6], tex.bulletBillDR[7],
				 tex.bulletBillDR[8], tex.bulletBillDR[9]);
		
		animExplosion = new Animation(5, tex.bulletBillExplosion[0],tex.bulletBillExplosion[1],
				tex.bulletBillExplosion[2],tex.bulletBillExplosion[3],tex.bulletBillExplosion[4],
				tex.bulletBillExplosion[5],tex.bulletBillExplosion[6]);

		Random rand = new Random();
		if(rand.nextInt(2)==0)
			stayTracked = true;
		String bulletBillDeathFile = "res/Sounds/SFX/smb2_bbdeathsfx.wav";
		String bulletBillSpawnFile = "res/Sounds/SFX/nsmbwiiBulletBillCannon2.wav";
		SoundLoops bulletBillSpawnSoundLoop = new SoundLoops(bulletBillSpawnFile);
		SoundLoops bulletBillDeathSoundLoop = new SoundLoops(bulletBillDeathFile);
		VolumeSlider.adjustSFX(bulletBillSpawnSoundLoop);
		VolumeSlider.adjustSFX(bulletBillDeathSoundLoop);
		this.bulletBillDeathSoundLoop = bulletBillDeathSoundLoop;
		this.bulletBillSpawnSoundLoop = bulletBillSpawnSoundLoop;
		this.bulletBillSpawnSoundLoop.play();
	}
	
	public void tick(){
		if((int)timer1 > 0){									//Chases Player
			if(!bulletBillisDead){
				if(this.getX() > game.playerX())
					x--;
				else
					x++;
				if(this.getY() > game.playerY())
					y++;
				else
					y++;
				smokeCount++;
				if(smokeCount > 1) {
					Random rand = new Random();
					if(this.getX() < game.playerX()-6.4)
						smoke = new BulletBillSmokeFXRed(x-3,y,game);
					else if(this.getX() > game.playerX()+6.4)
						smoke = new BulletBillSmokeFXRed(x+11,y-1,game);
					else
						smoke = new BulletBillSmokeFXRed(x+4+rand.nextInt(3)-rand.nextInt(3),y-4,game);
					game.getController().addEntity(smoke);
					smokeCount = 0;
				}
			}
			else{
				if(bulletBillXDirectionAfterDeath == -1)
					x = x - 0.25;
				else if(bulletBillXDirectionAfterDeath == 1)
					x = x + 0.25;
				if(this.getY() > game.playerY())
					y = y + 0.25;
				else
					y = y + 0.25;
			}
			if(!stayTracked)
				timer1--;
		}
		else{
			Random rand = new Random();
			int i = rand.nextInt(130);
			if(!bulletBillisDead) {
				y++;
				smokeCount++;
				if(smokeCount > 1) {
					smoke = new BulletBillSmokeFXRed(x+4+rand.nextInt(3)-rand.nextInt(3),y-4,game);
					game.getController().addEntity(smoke);
				}
			}
			else
				y = y + 0.25;
			if (i == 18 && !bulletBillisDead){										//Resets timer1 so he chases Player
				timer1 = 100;
			}
		}
		if (this.getX()-30 >= (Game.WIDTH * Game.SCALE)){			//Removing BulletBill from Game
			game.ec.remove(this);
		}
		
		if (x+30 <= 0){
			game.ec.remove(this);
		}
		
		if (y >= game.getHeight()){
			game.ec.remove(this);
		}
		
		for(int i = 0; i < game.ea.size(); i++){
			EntityA tempEnt = game.ea.get(i);
			/*
			if(Physics.Collision(this, tempEnt)){
				c.removeEntity(tempEnt);
				c.removeEntity(this);
			}
			*/
			if(Physics.Collision(this, tempEnt) && !bulletBillisDead){
				if(this.bulletBillDeathSoundLoop.getSoundLoopBoolean() == false){
					for(int j = game.bulletBillDeathSoundLoop.size(); j > 0; j--){
						if(game.bulletBillDeathSoundLoop.get(j-1) != null && !game.bulletBillDeathSoundLoop.get(j-1).clipIsActive()){
							game.bulletBillDeathSoundLoop.remove(j-1);
							//j--;
						}
					}	
					for(int k = 0; k < game.bulletBillDeathSoundLoop.size() || k == 0; k++){
						if(game.bulletBillDeathSoundLoop.isEmpty())
							game.bulletBillDeathSoundLoop.add(this.bulletBillDeathSoundLoop);
						else if (game.bulletBillDeathSoundLoop.get(k) == game.bulletBillDeathSoundLoop.getLast()){
							game.bulletBillDeathSoundLoop.add(this.bulletBillDeathSoundLoop);
							k++;
						}else if(this.bulletBillDeathSoundLoop.getVolume() -1.5f >= this.bulletBillDeathSoundLoop.minimumVolume())
							this.bulletBillDeathSoundLoop.reduceSound(1.5f);
					}
					this.bulletBillDeathSoundLoop.setSoundLoopBoolean(true);
					game.bulletBillDeathSoundLoop.getLast().play();
				}
				game.getHUD().setScore(200);
				bulletBillisDead = true;
				if((int)timer1>0) {
					if(this.getX() > game.playerX()+6.4 && (int)this.timer1 > 0)
						bulletBillXDirectionAfterDeath = -1;
					else if(this.getX() < game.playerX()-6.4 && (int)this.timer1 > 0)
						bulletBillXDirectionAfterDeath = 1;
					else
						bulletBillXDirectionAfterDeath = 0;
				}
				else {
						bulletBillXDirectionAfterDeath = 0;
				}
					
				//game.ec.remove(this);
				if(!game.ea.isEmpty() && Game.currentlySelectedFireball != 3)
					game.ea.remove(game.ea.getLast());
			}
		}
		for(int i = 0; i < game.ec.size(); i ++){
			EntityC tempEnt = game.ec.get(i);
			if(tempEnt == this) {}
			else if(Physics.Collision(this, tempEnt)){
				if(tempEnt.entityName().equals("GreenShell") || tempEnt.entityName().equals("GreenShellCircle") || 
						tempEnt.entityName().equals("BuzzyBeetleShell")) {
					if(this.bulletBillDeathSoundLoop.getSoundLoopBoolean() == false){
						for(int j = game.bulletBillDeathSoundLoop.size(); j > 0; j--){
							if(game.bulletBillDeathSoundLoop.get(j-1) != null && !game.bulletBillDeathSoundLoop.get(j-1).clipIsActive()){
								game.bulletBillDeathSoundLoop.remove(j-1);
								//j--;
							}
						}	
						for(int k = 0; k < game.bulletBillDeathSoundLoop.size() || k == 0; k++){
							if(game.bulletBillDeathSoundLoop.isEmpty())
								game.bulletBillDeathSoundLoop.add(this.bulletBillDeathSoundLoop);
							else if (game.bulletBillDeathSoundLoop.get(k) == game.bulletBillDeathSoundLoop.getLast()){
								game.bulletBillDeathSoundLoop.add(this.bulletBillDeathSoundLoop);
								k++;
							}else if(this.bulletBillDeathSoundLoop.getVolume() -1.5f >= this.bulletBillDeathSoundLoop.minimumVolume())
								this.bulletBillDeathSoundLoop.reduceSound(1.5f);
						}
						this.bulletBillDeathSoundLoop.setSoundLoopBoolean(true);
						game.bulletBillDeathSoundLoop.getLast().play();
					}
					bulletBillisDead = true;
				}
			}
		}
		if(bulletBillisDead){
			if(game.getBulletBillDeathSoundPauseBoolean() == true && !game.isPaused()){
				for(int l = game.bulletBillDeathSoundLoop.size()-1; l >= 0; l--){
					if(!game.bulletBillDeathSoundLoop.get(l).clipIsActive())
						game.bulletBillDeathSoundLoop.get(l).continuePlaying();
				}
				game.setBulletBillDeathSoundPauseBoolean(false);
			}
			animExplosion.runAnimation();
		}
		if(animExplosion.getCount() == 7){
			game.ec.remove(this);
		}
		animD.runAnimation();
		animDL.runAnimation();
		animDR.runAnimation();
	}
	
	public void render(Graphics g){
		if(flicker) {
			if(flickerTimer1 == 0 && flickerTimer2 == 0)
				flickerTimer1 = System.currentTimeMillis() + 250;
			if(flickerTimer1 < System.currentTimeMillis() && flickerTimer2 == 0) {
				flickerTimer1 = 0;
				flickerTimer2 = System.currentTimeMillis() + 250;
			}
			if(flickerTimer2 < System.currentTimeMillis() && flickerTimer1 == 0) {
				flickerTimer2 = 0;
				flickerTimer1 = System.currentTimeMillis() + 250;
			}
			if(flickerTimer1 < flickerTimer2) 
				return;
		}/*
		if(flicker) {
			if(!(System.currentTimeMillis() % 200 == 0) || flickerTimer1 > System.currentTimeMillis()) {
				if(flickerTimer1+200 < System.currentTimeMillis())
					flickerTimer1 = System.currentTimeMillis() + 200;
				return;
			}
		}*/
		if(bulletBillisDead)
			animExplosion.drawAnimation(g, x, y, 0);
		else if(this.getX() > game.playerX()+6.4 && (int)this.timer1 > 0)
			animDL.drawAnimation(g, x, y, 0);
		else if(this.getX() < game.playerX()-6.4 && (int)this.timer1 > 0)
			animDR.drawAnimation(g, x, y, 0);
		else// if(this.getX() == game.playerX() || (int)this.timer1 <= 0)
			animD.drawAnimation(g, x, y, 0);
		
		if(game.isPaused()){
			if(game.getBulletBillDeathSoundPauseBoolean() == false){
				for(int i = 0; i < game.bulletBillDeathSoundLoop.size();i++){
					if(game.bulletBillDeathSoundLoop.get(i).clipIsActive())
						game.bulletBillDeathSoundLoop.get(i).stop();
				}
				game.setBulletBillDeathSoundPauseBoolean(true);
			}
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public void setSpeed(double speed){
		this.speedIncrease = speed;
	}
	
	public double getX() {
		return x;
	}

	public double getY(){
		return y;
	}
	
	public int getWidth() {
		return 16;
	}

	public int getHeight() {
		return 16;
	}
	
	public boolean getEntityCDead() {
		return bulletBillisDead;
	}

	public void setBulletBillisDead(boolean bulletBillisDead) {
		if(this.bulletBillDeathSoundLoop.getSoundLoopBoolean() == false){
			for(int j = game.bulletBillDeathSoundLoop.size(); j > 0; j--){
				if(game.bulletBillDeathSoundLoop.get(j-1) != null && !game.bulletBillDeathSoundLoop.get(j-1).clipIsActive()){
					game.bulletBillDeathSoundLoop.remove(j-1);
					//j--;
				}
			}	
			for(int k = 0; k < game.bulletBillDeathSoundLoop.size() || k == 0; k++){
				if(game.bulletBillDeathSoundLoop.isEmpty())
					game.bulletBillDeathSoundLoop.add(this.bulletBillDeathSoundLoop);
				else if (game.bulletBillDeathSoundLoop.get(k) == game.bulletBillDeathSoundLoop.getLast()){
					game.bulletBillDeathSoundLoop.add(this.bulletBillDeathSoundLoop);
					k++;
				}else if(this.bulletBillDeathSoundLoop.getVolume() -1.5f >= this.bulletBillDeathSoundLoop.minimumVolume())
					this.bulletBillDeathSoundLoop.reduceSound(1.5f);
			}
			this.bulletBillDeathSoundLoop.setSoundLoopBoolean(true);
			game.bulletBillDeathSoundLoop.getLast().play();
		}
		game.getHUD().setScore(200);
		this.bulletBillisDead = bulletBillisDead;
	}

	public SoundLoops getBulletBillDeathSoundLoop() {
		return bulletBillDeathSoundLoop;
	}

	public void setBulletBillDeathSoundLoop(SoundLoops bulletBillDeathSoundLoop) {
		this.bulletBillDeathSoundLoop = bulletBillDeathSoundLoop;
	}

	public String entityName() {
		return entityName;
	}

	public void setEntityCDead(boolean dead) {
		if(dead) {
			if(this.bulletBillDeathSoundLoop.getSoundLoopBoolean() == false){
				for(int j = game.bulletBillDeathSoundLoop.size(); j > 0; j--){
					if(game.bulletBillDeathSoundLoop.get(j-1) != null && !game.bulletBillDeathSoundLoop.get(j-1).clipIsActive()){
						game.bulletBillDeathSoundLoop.remove(j-1);
						//j--;
					}
				}	
				for(int k = 0; k < game.bulletBillDeathSoundLoop.size() || k == 0; k++){
					if(game.bulletBillDeathSoundLoop.isEmpty())
						game.bulletBillDeathSoundLoop.add(this.bulletBillDeathSoundLoop);
					else if (game.bulletBillDeathSoundLoop.get(k) == game.bulletBillDeathSoundLoop.getLast()){
						game.bulletBillDeathSoundLoop.add(this.bulletBillDeathSoundLoop);
						k++;
					}else if(this.bulletBillDeathSoundLoop.getVolume() -1.5f >= this.bulletBillDeathSoundLoop.minimumVolume())
						this.bulletBillDeathSoundLoop.reduceSound(1.5f);
				}
				this.bulletBillDeathSoundLoop.setSoundLoopBoolean(true);
				game.bulletBillDeathSoundLoop.getLast().play();
			}
			game.getHUD().setScore(200);
		}
		bulletBillisDead = dead;
	}

	public void renderFlicker() {
		flicker = true;
	}
	
	public void close() {
		bulletBillDeathSoundLoop.close();
		bulletBillSpawnSoundLoop.close();
	}

}
