package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.libs.Animation;

public class ChainChompItem extends GameObject implements EntityE {

	private Textures tex;
	private Game game;
	private int xDestination = 10000;
	private int ecMarked = -1;
	private int ebMarked = -1;
	private int xDirection = -1;
	private long movingTimer1 = 0;
	private long movingTimer2 = 0;
	private long movingTimer3 = 0;
	private boolean movingTimer2Boolean, movingTimer3Boolean = false;	
	private boolean chainChompisDead = false;
	private boolean keepMoving = false;
	protected double velX, velY;
	Animation anim;
	Animation animDisintegrate;
	SoundLoops itemSoundLoop;
	SoundLoops chainChompDeathSoundLoop;
	
	public ChainChompItem(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.game = game;
		this.tex = tex;
		velX = 0;
		velY = 0;
		
		anim = new Animation(6,tex.chainChompItem[0], tex.chainChompItem[0],tex.chainChompItem[1]);
		animDisintegrate = new Animation(6,tex.chainChompDisintegrate[0],tex.chainChompDisintegrate[1],
				tex.chainChompDisintegrate[2],tex.chainChompDisintegrate[3],tex.chainChompDisintegrate[4],
				tex.chainChompDisintegrate[5],tex.chainChompDisintegrate[6],tex.chainChompDisintegrate[7]);
		anim.nextFrame();
		animDisintegrate.setCount(0);
		animDisintegrate.nextFrame();
		String itemFile = "res/Sounds/SFX/Items/sm64_chain_chomp.wav";
		String deathFile = "res/Sounds/SFX/Items/sm64_chainchompdisintegrating.wav";
		SoundLoops itemSoundLoop = new SoundLoops(itemFile);
		SoundLoops deathSoundLoop = new SoundLoops(deathFile);
		itemSoundLoop.reduceSound(10f);
		deathSoundLoop.reduceSound(7f);
		VolumeSlider.adjustSFX(itemSoundLoop);
		VolumeSlider.adjustSFX(deathSoundLoop);
		this.itemSoundLoop = itemSoundLoop;
		this.chainChompDeathSoundLoop = deathSoundLoop;
		movingTimer1 = System.currentTimeMillis() + 2000;
		movingTimer2 = System.currentTimeMillis() + 2000;
		movingTimer3 = System.currentTimeMillis() + 2000;
	}

	public void tick(){
		if(animDisintegrate.getCount() == 8) {
			game.ee.remove(this);
		}
		else if(chainChompisDead) {
			animDisintegrate.runAnimation();

			if(movingTimer2 >= movingTimer3 && !keepMoving) {
				keepMoving = true;
				velX = 0;
				velY = 0;
			}
			if(xDirection == 1) {
				if(velX> -0.8)
					velX = velX - 0.009;
			}else if(xDirection == 0) {
				if(velX<0.8)
					velX = velX + 0.009;
			}
			if(velY < 0.9)
				velY = velY + 0.009;
			if(keepMoving) {
				x += velX;
				y += velY;
			}
			if(game.getChainChompDeathSoundPauseBoolean() == true && !game.isPaused()){
				for(int l = game.chainChompDeathSoundLoop.size()-1; l >= 0; l--){
					if(!game.chainChompDeathSoundLoop.get(l).clipIsActive())
						game.chainChompDeathSoundLoop.get(l).continuePlaying();
				}
				game.setChainChompDeathSoundPauseBoolean(false);
			}
		}
		else {
			y += velY;
			if(velY != 0)
				x += velX;
			else
				velX = 0;
			if(y < -18)
				game.ee.remove(this);
			if(movingTimer1 < System.currentTimeMillis()) {
				if(movingTimer2 >= movingTimer3) {
					if(movingTimer2Boolean == false) {
						movingTimer2 += 24;
						movingTimer2Boolean = true;
						movingTimer3Boolean = false;
					}
					if(velY> -3)
						velY--;
					if(xDirection == 0) {
						if(velX> -2)
							velX = velX - 0.2;
					}else if(xDirection == 1) {
						if(velX<2)
							velX = velX + 0.2;
					}
					movingTimer3++;
				}else {
					if(movingTimer3Boolean == false) {
						movingTimer3 += 72;
						movingTimer3Boolean = true;
						movingTimer2Boolean = false;
					}
					if(velY<0)
						velY++;
					if(xDirection == 0) {
						if(velX> -2)
							velX = velX - 0.2;
					}else if(xDirection == 1) {
						if(velX<2)
							velX = velX + 0.2;
					}
					movingTimer2++;
				}
			}
			//System.out.println("xDirection = " + xDirection + "\n velX = "+ velX);
			if(!game.eb.isEmpty()){
				if(ebMarked != -1 && ebMarked < game.eb.size()){
					if(game.eb.get(ebMarked).getX() < this.getX())
						xDirection = 0;//x--
					else
						xDirection = 1;
				}
				for(int i = 0; i < game.eb.size(); i++){
	
					EntityB tempEnt = game.eb.get(i);
					if((Math.abs((int)this.getX() - (int)game.eb.get(i).getX()) < xDestination) || game.eb.size() == 1){
							xDestination = Math.abs((int)this.getX() - (int)game.eb.get(i).getX());
							ebMarked = i;
					}
					if(Physics.Collision(this, tempEnt)){
						if(!tempEnt.enemyType().equals("Bowser")) {
							if(this.itemSoundLoop.getSoundLoopBoolean() == false) {
								this.itemSoundLoop.play();
								this.itemSoundLoop.setSoundLoopBoolean(true);
							}
							game.eb.remove(i);
							Game.currentEECollisionX = this.x;
							Game.currentEECollisionY = this.y;
							Game.currentEECollisionWidth = this.getBounds().getWidth();
							game.setEnemyHitPauseTimer(System.currentTimeMillis() + 500);
							game.getHUD().setEnemyHitPauseTimer(System.currentTimeMillis() + 300);
							ebMarked = -1;
						}
						else {
							HUD.HEALTH -= 4;
							chainChompisDead = true;
							//MAKE EXPLOSION SOUND
							if(this.chainChompDeathSoundLoop.getSoundLoopBoolean() == false){
								for(int j = game.chainChompDeathSoundLoop.size(); j > 0; j--){
									if(game.chainChompDeathSoundLoop.get(j-1) != null && !game.chainChompDeathSoundLoop.get(j-1).clipIsActive()){
										game.chainChompDeathSoundLoop.remove(j-1);
										//j--;
									}
								}	
								for(int k = 0; k < game.chainChompDeathSoundLoop.size() || k == 0; k++){
									if(game.chainChompDeathSoundLoop.isEmpty())
										game.chainChompDeathSoundLoop.add(this.chainChompDeathSoundLoop);
									else if (game.chainChompDeathSoundLoop.get(k) == game.chainChompDeathSoundLoop.getLast()){
										game.chainChompDeathSoundLoop.add(this.chainChompDeathSoundLoop);
										k++;
									}else if(this.chainChompDeathSoundLoop.getVolume() -1.5f >= this.chainChompDeathSoundLoop.minimumVolume())
										this.chainChompDeathSoundLoop.reduceSound(1.5f);
								}
								this.chainChompDeathSoundLoop.setSoundLoopBoolean(true);
								game.chainChompDeathSoundLoop.getLast().play();
							}
						}
					}
				}
			}
			if(!game.ec.isEmpty()){
				if(ecMarked != -1 && ecMarked < game.ec.size() && game.ec.get(ecMarked).getY() < this.getY()){
					if(game.ec.get(ecMarked).getX() < this.getX())
						xDirection = 0;//x--
					else
						xDirection = 1;
				}
				for(int i = 0; i < game.ec.size(); i++){
	
					EntityC tempEnt = game.ec.get(i);
					if((Math.abs((int)this.getX() - (int)game.ec.get(i).getX()) < xDestination) || game.ec.size() == 1){
							xDestination = Math.abs((int)this.getX() - (int)game.ec.get(i).getX());
							ecMarked = i;
					}
					if(Physics.Collision(this, tempEnt)){
						if(this.itemSoundLoop.getSoundLoopBoolean() == false) {
							this.itemSoundLoop.play();
							this.itemSoundLoop.setSoundLoopBoolean(true);
						}
						if(tempEnt.entityName().equals("BuzzyBeetleShell")) {
							HUD.currentScoreFromChainChomp = "500";
							game.getHUD().setScore(500);
						}
						else if(tempEnt.entityName().equals("BulletBill")) {
							HUD.currentScoreFromChainChomp = "200";
							game.getHUD().setScore(500);
						}
						game.ec.remove(i);
						Game.currentEECollisionX = this.x;
						Game.currentEECollisionY = this.y;
						Game.currentEECollisionWidth = this.getBounds().getWidth();
						game.setEnemyHitPauseTimer(System.currentTimeMillis() + 500);
						game.getHUD().setEnemyHitPauseTimer(System.currentTimeMillis() + 300);
						ecMarked = -1;
					}
				}
			}
			else if(game.eb.isEmpty()){
				xDirection = -1;
				velX = 0;
			}
			anim.runAnimation();
		}
	}
	
	public void render(Graphics g){
		if(chainChompisDead)
			animDisintegrate.drawAnimation(g, x, y, 0);
		else
			anim.drawAnimation(g, x, y, 0);
		
		if(game.isPaused()){
			if(game.getChainChompDeathSoundPauseBoolean() == false){
				for(int i = 0; i < game.chainChompDeathSoundLoop.size();i++){
					if(game.chainChompDeathSoundLoop.get(i).clipIsActive())
						game.chainChompDeathSoundLoop.get(i).stop();
				}
				game.setChainChompDeathSoundPauseBoolean(true);
			}
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
