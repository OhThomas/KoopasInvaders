package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.libs.Animation;

public class Bowser extends GameObject implements EntityB{

	private boolean barrier = false;
	
	private Textures tex;
	private Game game;
	private Controller c;
	private double timer1 = 100;
	private double timer2 = 0;
	private double hitTimer = 0;
	private double deathTimer = 0;
	public double speedIncrease = 0.1;
	private long start = 0;
	private long spawningMoreItemsTimer = 0;
	private boolean pauseSet = false;
	private boolean itemPullout = false;
	private boolean itemSpawning = false;
	private boolean spawningMoreItems = false;
	private boolean greenShellCircleSpawn = false;
	private boolean buzzyBeetleShellSpawn = false;
	private boolean deathTimerSet = false;
	private boolean bowserisDead = false;
	private String enemyType = "Bowser";
	protected double velX, velY;
	Random r = new Random();
	
	Animation anim;
	Animation animEntrance;
	Animation animHit;
	Animation animItemPullout;
	Animation animItemPulloutR;
	Animation bowserShipL;
	Animation bowserShipHitL;
	Animation bowserShipR;
	Animation bowserShipHitR;
	Animation bowserShip2L;
	Animation bowserShipHit2L;
	Animation bowserShip2R;
	Animation bowserShipHit2R;
	Animation bowserShip3L;
	Animation bowserShipHit3L;
	Animation bowserShip3R;
	Animation bowserShipHit3R;
	Animation bowserShip4L;
	Animation bowserShipHit4L;
	Animation bowserShip4R;
	Animation bowserShipHit4R;
	Animation bowserShip5L;
	Animation bowserShipHit5L;
	Animation bowserShip5R;
	Animation bowserShipHit5R;
	Animation bowserShip6L;
	Animation bowserShipHit6L;
	Animation bowserShip6R;
	Animation bowserShipHit6R;
	Animation bowserShip7L;
	Animation bowserShipHit7L;
	Animation bowserShip7R;
	Animation bowserShipHit7R;
	Animation bowserShip8L;
	Animation bowserShipHit8L;
	Animation bowserShip8R;
	Animation bowserShipHit8R;
	Animation bowserShip9L;
	Animation bowserShipHit9L;
	Animation bowserShip9R;
	Animation bowserShipHit9R;
	Animation bowserShip10L;
	Animation bowserShipHit10L;
	Animation bowserShip10R;
	Animation bowserShipHit10R;
	Animation bowserShip11L;
	Animation bowserShipHit11L;
	Animation bowserShip11R;
	Animation bowserShipHit11R;
	Animation bowserShip12L;
	Animation bowserShipHit12L;
	Animation bowserShip12R;
	Animation bowserShipHit12R;
	
	public Bowser(double x, double y, Textures tex, Controller c, Game game){
		super(x,y);
		this.tex = tex;
		this.game = game;
		this.c = c;
		velX = 0;
		velY = 2;
		bowserShipHit12R = new Animation(19, tex.bowserShipHit12[4], tex.bowserShipHit12[5],tex.bowserShipHit12[6], tex.bowserShipHit12[7]);
		bowserShipHit12L = new Animation(19, tex.bowserShipHit12[0], tex.bowserShipHit12[1],tex.bowserShipHit12[3], tex.bowserShipHit12[4]);
		bowserShip12R = new Animation(19, tex.bowserShip12[4], tex.bowserShip12[5], tex.bowserShip12[6], tex.bowserShip12[7]);
		bowserShip12L = new Animation(19, tex.bowserShip12[0], tex.bowserShip12[1], tex.bowserShip12[2], tex.bowserShip12[3]);
		bowserShipHit11R = new Animation(19, tex.bowserShipHit11[4], tex.bowserShipHit11[5],tex.bowserShipHit11[6], tex.bowserShipHit11[7]);
		bowserShipHit11L = new Animation(19, tex.bowserShipHit11[0], tex.bowserShipHit11[1],tex.bowserShipHit11[3], tex.bowserShipHit11[4]);
		bowserShip11R = new Animation(19, tex.bowserShip11[4], tex.bowserShip11[5], tex.bowserShip11[6], tex.bowserShip11[7]);
		bowserShip11L = new Animation(19, tex.bowserShip11[0], tex.bowserShip11[1], tex.bowserShip11[2], tex.bowserShip11[3]);
		bowserShipHit10R = new Animation(19, tex.bowserShipHit10[4], tex.bowserShipHit10[5],tex.bowserShipHit10[6], tex.bowserShipHit10[7]);
		bowserShipHit10L = new Animation(19, tex.bowserShipHit10[0], tex.bowserShipHit10[1],tex.bowserShipHit10[3], tex.bowserShipHit10[4]);
		bowserShip10R = new Animation(19, tex.bowserShip10[4], tex.bowserShip10[5], tex.bowserShip10[6], tex.bowserShip10[7]);
		bowserShip10L = new Animation(19, tex.bowserShip10[0], tex.bowserShip10[1], tex.bowserShip10[2], tex.bowserShip10[3]);
		bowserShipHit9R = new Animation(19, tex.bowserShipHit9[4], tex.bowserShipHit9[5],tex.bowserShipHit9[6], tex.bowserShipHit9[7]);
		bowserShipHit9L = new Animation(19, tex.bowserShipHit9[0], tex.bowserShipHit9[1],tex.bowserShipHit9[3], tex.bowserShipHit9[4]);
		bowserShip9R = new Animation(19, tex.bowserShip9[4], tex.bowserShip9[5], tex.bowserShip9[6], tex.bowserShip9[7]);
		bowserShip9L = new Animation(19, tex.bowserShip9[0], tex.bowserShip9[1], tex.bowserShip9[2], tex.bowserShip9[3]);
		bowserShipHit8R = new Animation(19, tex.bowserShipHit8[4], tex.bowserShipHit8[5],tex.bowserShipHit8[6], tex.bowserShipHit8[7]);
		bowserShipHit8L = new Animation(19, tex.bowserShipHit8[0], tex.bowserShipHit8[1],tex.bowserShipHit8[3], tex.bowserShipHit8[4]);
		bowserShip8R = new Animation(19, tex.bowserShip8[4], tex.bowserShip8[5], tex.bowserShip8[6], tex.bowserShip8[7]);
		bowserShip8L = new Animation(19, tex.bowserShip8[0], tex.bowserShip8[1], tex.bowserShip8[2], tex.bowserShip8[3]);
		bowserShipHit7R = new Animation(19, tex.bowserShipHit7[4], tex.bowserShipHit7[5],tex.bowserShipHit7[6], tex.bowserShipHit7[7]);
		bowserShipHit7L = new Animation(19, tex.bowserShipHit7[0], tex.bowserShipHit7[1],tex.bowserShipHit7[3], tex.bowserShipHit7[4]);
		bowserShip7R = new Animation(19, tex.bowserShip7[4], tex.bowserShip7[5], tex.bowserShip7[6], tex.bowserShip7[7]);
		bowserShip7L = new Animation(19, tex.bowserShip7[0], tex.bowserShip7[1], tex.bowserShip7[2], tex.bowserShip7[3]);
		bowserShipHit6R = new Animation(19, tex.bowserShipHit6[4], tex.bowserShipHit6[5],tex.bowserShipHit6[6], tex.bowserShipHit6[7]);
		bowserShipHit6L = new Animation(19, tex.bowserShipHit6[0], tex.bowserShipHit6[1],tex.bowserShipHit6[3], tex.bowserShipHit6[4]);
		bowserShip6R = new Animation(19, tex.bowserShip6[4], tex.bowserShip6[5], tex.bowserShip6[6], tex.bowserShip6[7]);
		bowserShip6L = new Animation(19, tex.bowserShip6[0], tex.bowserShip6[1], tex.bowserShip6[2], tex.bowserShip6[3]);
		bowserShipHit5R = new Animation(19, tex.bowserShipHit5[4], tex.bowserShipHit5[5],tex.bowserShipHit5[6], tex.bowserShipHit5[7]);
		bowserShipHit5L = new Animation(19, tex.bowserShipHit5[0], tex.bowserShipHit5[1],tex.bowserShipHit5[3], tex.bowserShipHit5[4]);
		bowserShip5R = new Animation(19, tex.bowserShip5[4], tex.bowserShip5[5], tex.bowserShip5[6], tex.bowserShip5[7]);
		bowserShip5L = new Animation(19, tex.bowserShip5[0], tex.bowserShip5[1], tex.bowserShip5[2], tex.bowserShip5[3]);
		bowserShipHit4R = new Animation(19, tex.bowserShipHit4[4], tex.bowserShipHit4[5],tex.bowserShipHit4[6], tex.bowserShipHit4[7]);
		bowserShipHit4L = new Animation(19, tex.bowserShipHit4[0], tex.bowserShipHit4[1],tex.bowserShipHit4[3], tex.bowserShipHit4[4]);
		bowserShip4R = new Animation(19, tex.bowserShip4[4], tex.bowserShip4[5], tex.bowserShip4[6], tex.bowserShip4[7]);
		bowserShip4L = new Animation(19, tex.bowserShip4[0], tex.bowserShip4[1], tex.bowserShip4[2], tex.bowserShip4[3]);
		bowserShipHit3R = new Animation(19, tex.bowserShipHit3[4], tex.bowserShipHit3[5],tex.bowserShipHit3[6], tex.bowserShipHit3[7]);
		bowserShipHit3L = new Animation(19, tex.bowserShipHit3[0], tex.bowserShipHit3[1],tex.bowserShipHit3[3], tex.bowserShipHit3[4]);
		bowserShip3R = new Animation(19, tex.bowserShip3[4], tex.bowserShip3[5], tex.bowserShip3[6], tex.bowserShip3[7]);
		bowserShip3L = new Animation(19, tex.bowserShip3[0], tex.bowserShip3[1], tex.bowserShip3[2], tex.bowserShip3[3]);
		bowserShipHit2R = new Animation(19, tex.bowserShipHit2[4], tex.bowserShipHit2[5],tex.bowserShipHit2[6], tex.bowserShipHit2[7]);
		bowserShipHit2L = new Animation(19, tex.bowserShipHit2[0], tex.bowserShipHit2[1],tex.bowserShipHit2[3], tex.bowserShipHit2[4]);
		bowserShip2R = new Animation(19, tex.bowserShip2[4], tex.bowserShip2[5], tex.bowserShip2[6], tex.bowserShip2[7]);
		bowserShip2L = new Animation(19, tex.bowserShip2[0], tex.bowserShip2[1], tex.bowserShip2[2], tex.bowserShip2[3]);
		bowserShipHitR = new Animation(19, tex.bowserShipHit[4], tex.bowserShipHit[5],tex.bowserShipHit[6], tex.bowserShipHit[7]);
		bowserShipHitL = new Animation(19, tex.bowserShipHit[0], tex.bowserShipHit[1],tex.bowserShipHit[3], tex.bowserShipHit[4]);
		bowserShipR = new Animation(19, tex.bowserShip[4], tex.bowserShip[5], tex.bowserShip[6], tex.bowserShip[7]);
		bowserShipL = new Animation(19, tex.bowserShip[0], tex.bowserShip[1], tex.bowserShip[2], tex.bowserShip[3]);
		animItemPulloutR = new Animation(10, tex.bowser[1],tex.bowserEntrance[10],tex.bowserEntrance[11],
				tex.bowserEntrance[12],tex.bowserEntrance[13],tex.bowserEntrance[15],tex.bowserEntrance[13],
				tex.bowserEntrance[12],tex.bowserEntrance[11],tex.bowserEntrance[10],tex.bowser[1]);
		animItemPullout = new Animation(10, tex.bowserEntrance[2],tex.bowserEntrance[1],tex.bowserEntrance[0],
				tex.bowserEntrance[9],tex.bowserEntrance[8],tex.bowserEntrance[14],tex.bowserEntrance[8],
				tex.bowserEntrance[9],tex.bowserEntrance[0],tex.bowserEntrance[1],tex.bowserEntrance[2]);
		animEntrance = new Animation(20, tex.bowserEntrance[0],tex.bowserEntrance[1],
				tex.bowserEntrance[2],tex.bowserEntrance[3],
				tex.bowserEntrance[4],tex.bowserEntrance[5],
				tex.bowserEntrance[6],tex.bowserEntrance[7]);
		
		animHit = new Animation(6,tex.bowserHit[0], tex.bowserHit[1]);
		anim = new Animation(38, tex.bowser[0],tex.bowser[1]);
		animItemPullout.nextFrame();
		animItemPullout.setCount(0);
		animItemPulloutR.nextFrame();
		animItemPulloutR.setCount(0);
		bowserShipL.nextFrame();
		bowserShipL.setCount(0);
		bowserShipR.nextFrame();
		bowserShipR.setCount(0);
		bowserShip2L.nextFrame();
		bowserShip2L.setCount(0);
		bowserShip2R.nextFrame();
		bowserShip2R.setCount(0);
		bowserShip3L.nextFrame();
		bowserShip3L.setCount(0);
		bowserShip3R.nextFrame();
		bowserShip3R.setCount(0);
		bowserShip4L.nextFrame();
		bowserShip4L.setCount(0);
		bowserShip4R.nextFrame();
		bowserShip4R.setCount(0);
		bowserShip5L.nextFrame();
		bowserShip5L.setCount(0);
		bowserShip5R.nextFrame();
		bowserShip5R.setCount(0);
		bowserShip6L.nextFrame();
		bowserShip6L.setCount(0);
		bowserShip6R.nextFrame();
		bowserShip6R.setCount(0);
		bowserShip7L.nextFrame();
		bowserShip7L.setCount(0);
		bowserShip7R.nextFrame();
		bowserShip7R.setCount(0);
		bowserShip8L.nextFrame();
		bowserShip8L.setCount(0);
		bowserShip8R.nextFrame();
		bowserShip8R.setCount(0);
		bowserShip9L.nextFrame();
		bowserShip9L.setCount(0);
		bowserShip9R.nextFrame();
		bowserShip9R.setCount(0);
		bowserShip10L.nextFrame();
		bowserShip10L.setCount(0);
		bowserShip10R.nextFrame();
		bowserShip10R.setCount(0);
		bowserShip11L.nextFrame();
		bowserShip11L.setCount(0);
		bowserShip11R.nextFrame();
		bowserShip11R.setCount(0);
		bowserShip12L.nextFrame();
		bowserShip12L.setCount(0);
		bowserShip12R.nextFrame();
		bowserShip12R.setCount(0);
		bowserShipHitL.nextFrame();
		bowserShipHitL.setCount(0);
		bowserShipHitR.nextFrame();
		bowserShipHitR.setCount(0);
		bowserShipHit2L.nextFrame();
		bowserShipHit2L.setCount(0);
		bowserShipHit2R.nextFrame();
		bowserShipHit2R.setCount(0);
		bowserShipHit3L.nextFrame();
		bowserShipHit3L.setCount(0);
		bowserShipHit3R.nextFrame();
		bowserShipHit3R.setCount(0);
		bowserShipHit4L.nextFrame();
		bowserShipHit4L.setCount(0);
		bowserShipHit4R.nextFrame();
		bowserShipHit4R.setCount(0);
		bowserShipHit5L.nextFrame();
		bowserShipHit5L.setCount(0);
		bowserShipHit5R.nextFrame();
		bowserShipHit5R.setCount(0);
		bowserShipHit6L.nextFrame();
		bowserShipHit6L.setCount(0);
		bowserShipHit6R.nextFrame();
		bowserShipHit6R.setCount(0);
		bowserShipHit7L.nextFrame();
		bowserShipHit7L.setCount(0);
		bowserShipHit7R.nextFrame();
		bowserShipHit7R.setCount(0);
		bowserShipHit8L.nextFrame();
		bowserShipHit8L.setCount(0);
		bowserShipHit8R.nextFrame();
		bowserShipHit8R.setCount(0);
		bowserShipHit9L.nextFrame();
		bowserShipHit9L.setCount(0);
		bowserShipHit9R.nextFrame();
		bowserShipHit9R.setCount(0);
		bowserShipHit10L.nextFrame();
		bowserShipHit10L.setCount(0);
		bowserShipHit10R.nextFrame();
		bowserShipHit10R.setCount(0);
		bowserShipHit11L.nextFrame();
		bowserShipHit11L.setCount(0);
		bowserShipHit11R.nextFrame();
		bowserShipHit11R.setCount(0);
		bowserShipHit12L.nextFrame();
		bowserShipHit12L.setCount(0);
		bowserShipHit12R.nextFrame();
		bowserShipHit12R.setCount(0);
	}
	
	public void tick(){
		if(bowserisDead) {
			//if(System.currentTimeMillis() % 3 == 0 && game.gameSoundLoops.get(game.getSoundRandomizer()).getVolume() >= -70f) 
			if(game.gameSoundLoops.get(game.getSoundRandomizer()).getVolume() >= -70f)	
				game.gameSoundLoops.get(game.getSoundRandomizer()).shiftVolume(game.gameSoundLoops.get(game.getSoundRandomizer()).getVolume(), game.gameSoundLoops.get(game.getSoundRandomizer()).getVolume()-1f, 200);
			animHit.runAnimation();
			if(HUD.getTimer2() <= 0)
				c.removeEntity(this);
			
		}
		else {
			if(timer1 > 0){
				x += 1;
				//y += velY;
				timer1 = timer1 - 1;
				animEntrance.runAnimation();
			}
			if(timer2 > 0){
				//y += velY;
				timer2--;
				animEntrance.runAnimation();
			}
			if(hitTimer > 0){
				hitTimer--;
				animHit.runAnimation();
			}
			if(HUD.HEALTH <= 0) {
				bowserisDead = true;
			}
			if (game.enemyHitRightBarrier == false){
				x+=game.enemySpeedIncrease; //x+=1;
			}
			
			if (x >= (Game.WIDTH * Game.SCALE)- 55 || game.enemyHitRightBarrier == true){
				int i = r.nextInt(10);
				if (barrier == false && i == 0 && y < ((Game.HEIGHT * Game.SCALE)/2) - 16)
					y += 16;
				barrier = true;
				game.enemyHitRightBarrier = true;
				//y +=16;
			}
			
			if (game.enemyHitRightBarrier == true){
				x-=game.enemySpeedIncrease; //x-= 1;
			}
			
			if (x <= 0 || game.enemyHitRightBarrier == false){
				int i = r.nextInt(10);
				if (barrier == true && i == 0 && y < ((Game.HEIGHT * Game.SCALE)/2) - 16)
					y +=16;
				barrier = false;
				game.enemyHitRightBarrier = false;
			}
			
			if (y >= game.getHeight()){
				game.gameOverBoolean = true;
			}
			if(10000 < (System.currentTimeMillis() - start) && hitTimer <= 0 && timer1 <= 0 && timer2 <= 0) {
				Random rand = new Random();
				int i = rand.nextInt(1);
				if(i == 0) 
					itemPullout = true;
				start = System.currentTimeMillis();
			}
			if(greenShellCircleSpawn) {
				game.addEntity(new GreenShellCircle(x+32,y + 41,tex, game));
				greenShellCircleSpawn = false;
				itemSpawning = false;
				start = System.currentTimeMillis();
				if(!spawningMoreItems && (animItemPullout.getCount() == 6 ||animItemPulloutR.getCount() == 6)) {
					animItemPullout.nextFrame();
					animItemPulloutR.nextFrame();
				}
			}
			else if(buzzyBeetleShellSpawn) {
				game.addEntity(new BuzzyBeetleShell(x+32,y + 41,tex, game));
				buzzyBeetleShellSpawn = false;
				itemSpawning = false;
				start = System.currentTimeMillis();
				if(!spawningMoreItems && (animItemPullout.getCount() == 6 ||animItemPulloutR.getCount() == 6)) {
					animItemPullout.nextFrame();
					animItemPulloutR.nextFrame();
				}
			}
			for(int i = 0; i < game.ea.size(); i++){
				EntityA tempEnt = game.ea.get(i);
				
				if(Physics.Collision(this, tempEnt)){
					if (game.eb.size() == 2)
						game.enemySpeedIncrease+= 0.9;
					game.enemySpeedIncrease+= 0.2; //0.7
					HUD.HEALTH -= 9;//4
					hitTimer = 80;
					if(game.ea.size() >= 1)
						game.ea.removeLast();
					//c.removeEntity(tempEnt);
					//c.removeEntity(this);
				}
			}
		}
		//if itempulloutgetcount == 5
		//itemspawning = true SPAWN ITEMS AND DON'T RUN itemPulloutAnimations if itemspawning == true
		if((animItemPullout.getCount() == 6 || animItemPulloutR.getCount() == 6) && !itemSpawning && spawningMoreItemsTimer < System.currentTimeMillis()) {
			Random rand = new Random();
			int i = rand.nextInt(2);
			switch(i) {
				case 0:
					greenShellCircleSpawn = true;
					break;
				case 1:
					buzzyBeetleShellSpawn = true;
					break;
					
			}
			itemSpawning = true;
			spawningMoreItems = false;
			spawningMoreItemsTimer = 0;
			int j = rand.nextInt(2);
			if(j == 0) {
				spawningMoreItems = true;
				spawningMoreItemsTimer = System.currentTimeMillis() + 500;
			}
		}
		if(animItemPullout.getCount() == 10 || animItemPulloutR.getCount() == 10) {
			itemPullout = false;
			animItemPullout.nextFrame();
			animItemPullout.setCount(0);
			animItemPulloutR.nextFrame();
			animItemPulloutR.setCount(0);
		}
		if(itemPullout && !itemSpawning && !spawningMoreItems) {
			animItemPullout.runAnimation();
			animItemPulloutR.runAnimation();
		}
		if(HUD.HEALTH <= 1) {
			bowserShipHit12R.runAnimation();
			bowserShip12R.runAnimation();
			bowserShipHit12L.runAnimation();
			bowserShip12L.runAnimation();
		}
		else if(HUD.HEALTH <= 10) {
			bowserShipHit11R.runAnimation();
			bowserShip11R.runAnimation();
			bowserShipHit11L.runAnimation();
			bowserShip11L.runAnimation();
		}
		else if(HUD.HEALTH <= 19) {
			bowserShipHit10R.runAnimation();
			bowserShip10R.runAnimation();
			bowserShipHit10L.runAnimation();
			bowserShip10L.runAnimation();
		}
		else if(HUD.HEALTH <= 28) {
			bowserShipHit9R.runAnimation();
			bowserShip9R.runAnimation();
			bowserShipHit9L.runAnimation();
			bowserShip9L.runAnimation();
		}
		else if(HUD.HEALTH <= 37) {
			bowserShipHit8R.runAnimation();
			bowserShip8R.runAnimation();
			bowserShipHit8L.runAnimation();
			bowserShip8L.runAnimation();
		}
		else if(HUD.HEALTH <= 46) {
			bowserShipHit7R.runAnimation();
			bowserShip7R.runAnimation();
			bowserShipHit7L.runAnimation();
			bowserShip7L.runAnimation();
		}
		else if(HUD.HEALTH <= 55) {
			bowserShipHit6R.runAnimation();
			bowserShip6R.runAnimation();
			bowserShipHit6L.runAnimation();
			bowserShip6L.runAnimation();
		}
		else if(HUD.HEALTH <= 64) {
			bowserShipHit5R.runAnimation();
			bowserShip5R.runAnimation();
			bowserShipHit5L.runAnimation();
			bowserShip5L.runAnimation();
		}
		else if(HUD.HEALTH <= 73) {
			bowserShipHit4R.runAnimation();
			bowserShip4R.runAnimation();
			bowserShipHit4L.runAnimation();
			bowserShip4L.runAnimation();
		}
		else if(HUD.HEALTH <=82) {
			bowserShipHit3R.runAnimation();
			bowserShip3R.runAnimation();
			bowserShipHit3L.runAnimation();
			bowserShip3L.runAnimation();
		}
		else if(HUD.HEALTH <= 91) {
			bowserShipHit2R.runAnimation();
			bowserShip2R.runAnimation();
			bowserShipHit2L.runAnimation();
			bowserShip2L.runAnimation();
		}else {
			bowserShipHitR.runAnimation();
			bowserShipR.runAnimation();
			bowserShipHitL.runAnimation();
			bowserShipL.runAnimation();
		}
		if(!(hitTimer > 0) && !itemPullout)
			anim.runAnimation();
		animHit.runAnimation();
		animEntrance.runAnimation();
	}
	
	public void render(Graphics g){
		for(int i = 0; i < game.ea.size(); i++){
			EntityA tempEnt = game.ea.get(i);
			if(Physics.Collision(this, tempEnt)){
				timer2 = 50;
			}
		}
		if(timer1 > 0) {
			animEntrance.drawAnimation(g, x, y, 0);
			bowserShipL.drawAnimation(g, x, y+41, 0);
		}
		else if(hitTimer > 0){
			if(!itemPullout)
				animHit.drawAnimation(g, x, y-9, 0);
			else if(anim.getCount() == 1)
				animItemPullout.drawAnimation(g, x, y, 0);
			else
				animItemPulloutR.drawAnimation(g, x, y, 0);
			if(HUD.HEALTH <= 1) {
				if(anim.getCount() == 1)
					bowserShipHit12L.drawAnimation(g, x, y+41, 0);
				else
					bowserShipHit12R.drawAnimation(g, x, y+41, 0);
			}
			else if(HUD.HEALTH <= 10) {
				if(anim.getCount() == 1)
					bowserShipHit11L.drawAnimation(g, x, y+41, 0);
				else
					bowserShipHit11R.drawAnimation(g, x, y+41, 0);
			}
			else if(HUD.HEALTH <= 19) {
				if(anim.getCount() == 1)
					bowserShipHit10L.drawAnimation(g, x, y+41, 0);
				else
					bowserShipHit10R.drawAnimation(g, x, y+41, 0);
			}
			else if(HUD.HEALTH <= 28) {
				if(anim.getCount() == 1)
					bowserShipHit9L.drawAnimation(g, x, y+41, 0);
				else
					bowserShipHit9R.drawAnimation(g, x, y+41, 0);
			}
			else if(HUD.HEALTH <= 37) {
				if(anim.getCount() == 1)
					bowserShipHit8L.drawAnimation(g, x, y+41, 0);
				else
					bowserShipHit8R.drawAnimation(g, x, y+41, 0);
			}
			else if(HUD.HEALTH <= 46) {
				if(anim.getCount() == 1)
					bowserShipHit7L.drawAnimation(g, x, y+41, 0);
				else
					bowserShipHit7R.drawAnimation(g, x, y+41, 0);
			}
			else if(HUD.HEALTH <= 55) {
				if(anim.getCount() == 1)
					bowserShipHit6L.drawAnimation(g, x, y+41, 0);
				else
					bowserShipHit6R.drawAnimation(g, x, y+41, 0);
			}
			else if(HUD.HEALTH <= 64) {
				if(anim.getCount() == 1)
					bowserShipHit5L.drawAnimation(g, x, y+41, 0);
				else
					bowserShipHit5R.drawAnimation(g, x, y+41, 0);
			}
			else if(HUD.HEALTH <= 73) {
				if(anim.getCount() == 1)
					bowserShipHit4L.drawAnimation(g, x, y+41, 0);
				else
					bowserShipHit4R.drawAnimation(g, x, y+41, 0);
			}
			else if(HUD.HEALTH <= 82) {
				if(anim.getCount() == 1)
					bowserShipHit3L.drawAnimation(g, x, y+41, 0);
				else
					bowserShipHit3R.drawAnimation(g, x, y+41, 0);
			}
			else if(HUD.HEALTH <= 91) {
				if(anim.getCount() == 1)
					bowserShipHit2L.drawAnimation(g, x, y+41, 0);
				else
					bowserShipHit2R.drawAnimation(g, x, y+41, 0);
			}
			else {
				if(anim.getCount() == 1)
					bowserShipHitL.drawAnimation(g, x, y+41, 0);
				else
					bowserShipHitR.drawAnimation(g, x, y+41, 0);
			}
		}
		else {

			if(!itemPullout)
				anim.drawAnimation(g, x, y, 0);
			else if(anim.getCount() == 1)
				animItemPullout.drawAnimation(g, x, y, 0);
			else
				animItemPulloutR.drawAnimation(g, x, y, 0);
			if(HUD.HEALTH <= 1) {
				if(anim.getCount() == 1)
					bowserShip12L.drawAnimation(g, x, y+41, 0);
				else
					bowserShip12R.drawAnimation(g, x, y+41, 0);
			}
			else if(HUD.HEALTH <= 10) {
				if(anim.getCount() == 1)
					bowserShip11L.drawAnimation(g, x, y+41, 0);
				else
					bowserShip11R.drawAnimation(g, x, y+41, 0);
			}
			else if(HUD.HEALTH <= 19) {
				if(anim.getCount() == 1)
					bowserShip10L.drawAnimation(g, x, y+41, 0);
				else
					bowserShip10R.drawAnimation(g, x, y+41, 0);
			}
			else if(HUD.HEALTH <= 28) {
				if(anim.getCount() == 1)
					bowserShip9L.drawAnimation(g, x, y+41, 0);
				else
					bowserShip9R.drawAnimation(g, x, y+41, 0);
			}
			else if(HUD.HEALTH <= 37) {
				if(anim.getCount() == 1)
					bowserShip8L.drawAnimation(g, x, y+41, 0);
				else
					bowserShip8R.drawAnimation(g, x, y+41, 0);
			}
			else if(HUD.HEALTH <= 46) {
				if(anim.getCount() == 1)
					bowserShip7L.drawAnimation(g, x, y+41, 0);
				else
					bowserShip7R.drawAnimation(g, x, y+41, 0);
			}
			else if(HUD.HEALTH <= 55) {
				if(anim.getCount() == 1)
					bowserShip6L.drawAnimation(g, x, y+41, 0);
				else
					bowserShip6R.drawAnimation(g, x, y+41, 0);
			}
			else if(HUD.HEALTH <= 64) {
				if(anim.getCount() == 1)
					bowserShip5L.drawAnimation(g, x, y+41, 0);
				else
					bowserShip5R.drawAnimation(g, x, y+41, 0);
			}
			else if(HUD.HEALTH <= 73) {
				if(anim.getCount() == 1)
					bowserShip4L.drawAnimation(g, x, y+41, 0);
				else
					bowserShip4R.drawAnimation(g, x, y+41, 0);
			}
			else if(HUD.HEALTH <= 82) {
				if(anim.getCount() == 1)
					bowserShip3L.drawAnimation(g, x, y+41, 0);
				else
					bowserShip3R.drawAnimation(g, x, y+41, 0);
			}
			else if(HUD.HEALTH <= 91) {
				if(anim.getCount() == 1)
					bowserShip2L.drawAnimation(g, x, y+41, 0);
				else
					bowserShip2R.drawAnimation(g, x, y+41, 0);
			}
			else {
				if(anim.getCount() == 1)
					bowserShipL.drawAnimation(g, x, y+41, 0);
				else
					bowserShipR.drawAnimation(g, x, y+41, 0);
			}
		}
		if(Game.isPaused()) {
			if(!pauseSet) {
				start = System.currentTimeMillis() - start;
				pauseSet = true;
			}
		}
		else {
			if(pauseSet) {
				start = System.currentTimeMillis() - start;
				pauseSet = false;
			}
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 64, 96);
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
	
	public double getTimer1(){
		return timer1;
	}

	public boolean getEntityBDead() {
		return bowserisDead;
	}

	public int getWidth() {
		return 64;
	}

	public int getHeight() {
		return 50;
	}
	
	public String enemyType() {
		return enemyType;
	}
}
