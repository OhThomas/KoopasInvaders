package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.game.src.main.Game.STATE;
import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityD;
import com.game.src.main.libs.Animation;

public class Player extends GameObject implements EntityA{

	public static final int MARIO_WIDTH = 16;
	public static final int MARIO_HEIGHT = 28;
	
	private double velX = 0;
	private double velY = 0;
	
	private Textures tex;
	private BufferedImage player;
	private BufferedImage playerSmall;
	private BufferedImage playerSmallDancePose;
	private BufferedImage playerGrowthPose;
	private BufferedImage playerDeath;
	private BufferedImage player2;
	private BufferedImage player3;
	private boolean playerEntranceSpinningSetup = false;
	private boolean playerEntranceDancingSetup = false;
	private boolean playerEntranceGrowingSetup = false;
	private boolean playerEntranceTurningAroundSetup = false;
	private boolean playerDeathSetup = false;
	private boolean firstTimeAnimationRun = false;

	private boolean spinningAnimationFinished = false;
	private boolean dancingAnimationFinished = false;
	private boolean growingAnimationFinished = false;
	private boolean turningAroundAnimationFinished = false;
	private boolean dancingInProgress = false;
	private boolean marioInvincible = false;	//To make Player invincible w/ star
	private int timer1 = 100;					//Timer for how long Player is invincible
	private int timer2 = 0;
	private int danceProgressionCount = 0;
	private long animationTimer1 = 0;
	private long marioDeathTimer1 = 0;
	private long marioDeathTimer2 = 0;
	private long marioDeathTimer3 = 0;
	private long marioGravityTimer = 0;
	private long marioGravityTimer2 = 0;
	private long marioGravityTimer3 = 0;
	private long marioGravityTimer4 = 0;
	private long marioGravityTimer5 = 0;
	Random r = new Random();
	int random = r.nextInt((9-1)+1) + 1;		//int randomNum = rand.nextInt((max - min) + 1) + min;
	
	Game game;
	Controller controller;
	Animation anim;
	Animation animl;
	Animation animr;
	Animation animd;
	Animation animSlowingDownl;
	Animation animSlowingDownr;
	Animation starAnim1;
	Animation starAnim1l;
	Animation starAnim1r;
	Animation starAnim1d;
	Animation starAnim2;
	Animation starAnim2l;
	Animation starAnim2r;
	Animation starAnim2d;
	Animation starAnim3;
	Animation starAnim3l;
	Animation starAnim3r;
	Animation starAnim3d;
	Animation marioEntranceSpinningAnim;
	Animation marioEntranceDancingAnim;
	Animation marioEntranceGrowingAnim;
	Animation marioEntranceTurningAroundAnim;
	Animation marioDeathAnim;
	
	public Player(double x, double y, Textures tex, Game game, Controller controller){
		super(x,y);
		this.tex = tex;
		this.game = game;
		this.controller = controller;
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		player = ss.grabMarioImage(1, 1, MARIO_WIDTH, MARIO_HEIGHT);
		playerSmall = tex.marioEntrance[0];
		playerSmallDancePose = tex.marioEntrance[5];
		playerGrowthPose = tex.marioEntrance[9];
		playerDeath = tex.marioDeath[0];
		player2 = tex.marioStar2[0];
		player3 = tex.marioStar3[0];
		
		anim = new Animation(6, tex.player[0],tex.player[1],tex.player[2],tex.player[3]);
		animl = new Animation(6, tex.player[4],tex.player[5],tex.player[6],tex.player[7]);
		animr = new Animation(6, tex.player[8],tex.player[9],tex.player[10],tex.player[11]);
		animd = new Animation(6, tex.player[12],tex.player[13],tex.player[14],tex.player[15]);
		
		animSlowingDownl = new Animation(6,tex.marioSlowingDown[0],tex.marioSlowingDown[0]);
		animSlowingDownr = new Animation(6,tex.marioSlowingDown[1],tex.marioSlowingDown[1]);
		
		starAnim1 = new Animation(6, tex.marioStar1[0],tex.marioStar1[1],tex.marioStar1[2],tex.marioStar1[3]);
		starAnim1l = new Animation(6, tex.marioStar1[4],tex.marioStar1[5],tex.marioStar1[6],tex.marioStar1[7]);
		starAnim1r = new Animation(6, tex.marioStar1[8],tex.marioStar1[9],tex.marioStar1[10],tex.marioStar1[11]);
		starAnim1d = new Animation(6, tex.marioStar1[12],tex.marioStar1[13],tex.marioStar1[14],tex.marioStar1[15]);
		
		starAnim2 = new Animation(6, tex.marioStar2[0],tex.marioStar2[1],tex.marioStar2[2],tex.marioStar2[3]);
		starAnim2l = new Animation(6, tex.marioStar2[4],tex.marioStar2[5],tex.marioStar2[6],tex.marioStar2[7]);
		starAnim2r = new Animation(6, tex.marioStar2[8],tex.marioStar2[9],tex.marioStar2[10],tex.marioStar2[11]);
		starAnim2d = new Animation(6, tex.marioStar2[12],tex.marioStar2[13],tex.marioStar2[14],tex.marioStar2[15]);

		starAnim3 = new Animation(6, tex.marioStar3[0],tex.marioStar3[1],tex.marioStar3[2],tex.marioStar3[3]);
		starAnim3l = new Animation(6, tex.marioStar3[4],tex.marioStar3[5],tex.marioStar3[6],tex.marioStar3[7]);
		starAnim3r = new Animation(6, tex.marioStar3[8],tex.marioStar3[9],tex.marioStar3[10],tex.marioStar3[11]);
		starAnim3d = new Animation(6, tex.marioStar3[12],tex.marioStar3[13],tex.marioStar3[14],tex.marioStar3[15]);
		
		marioDeathAnim = new Animation(5, tex.marioDeath[0], tex.marioDeath[1]);
		
		marioEntranceSpinningAnim = new Animation(1, tex.marioEntrance[0],tex.marioEntrance[1],tex.marioEntrance[2],
				tex.marioEntrance[3],tex.marioEntrance[4],tex.marioEntrance[0],
				tex.marioEntrance[1],tex.marioEntrance[2],tex.marioEntrance[3],
				tex.marioEntrance[4],tex.marioEntrance[0],tex.marioEntrance[1],
				tex.marioEntrance[2],tex.marioEntrance[3],tex.marioEntrance[4],tex.marioEntrance[0]);
		
		marioEntranceDancingAnim = new Animation(1, tex.marioEntrance[5],tex.marioEntrance[6],tex.marioEntrance[7],
				tex.marioEntrance[8],tex.marioEntrance[5],tex.marioEntrance[6],
				tex.marioEntrance[7],tex.marioEntrance[8],tex.marioEntrance[5],
				tex.marioEntrance[6],tex.marioEntrance[7],tex.marioEntrance[8],
				tex.marioEntrance[5],tex.marioEntrance[6],tex.marioEntrance[7],
				tex.marioEntrance[8],tex.marioEntrance[5],tex.marioEntrance[6],
				tex.marioEntrance[7],tex.marioEntrance[8],tex.marioEntrance[5],
				tex.marioEntrance[6],tex.marioEntrance[7],tex.marioEntrance[8],
				tex.marioEntrance[6],tex.marioEntrance[5]);
		
		marioEntranceGrowingAnim = new Animation(1, tex.marioEntrance[9],tex.marioEntrance[6],tex.marioEntrance[10],
				tex.marioEntrance[8],tex.marioEntrance[9],tex.marioEntrance[6],
				tex.marioEntrance[10],tex.marioEntrance[8],tex.marioEntrance[9],tex.marioEntrance[9]);
		
		marioEntranceTurningAroundAnim = new Animation(1, tex.marioEntrance[9],tex.marioEntrance[10],
				tex.marioEntrance[11],tex.marioEntrance[12],tex.marioEntrance[13],tex.marioEntrance[14],
				tex.marioEntrance[15],tex.marioEntrance[16],tex.marioEntrance[17],tex.marioEntrance[17],
				tex.marioEntrance[18],tex.marioEntrance[19],tex.marioEntrance[20],tex.marioEntrance[20]);
	}
	
	public void tick(){
		x+=velX;
		y+=velY;
		
		if(x <= 0)
			x = 0;
		if(x >= 640 - 6)
			x = 640 - 6;
		if (y <= 0)
			y = 0;
		if (y >= 480 - MARIO_HEIGHT)
			y = 480 - MARIO_HEIGHT;
		if(marioInvincible == true){
			timer1--;
			timer2++;
		}
		if(timer1 <= 0){
			marioInvincible = false;
		}
		if (timer2 == 5)
		{
			random = r.nextInt((9-1)+1) + 1;
			timer2 = 0;
		}
		if (game.animationTimer1 != 0)
			game.animationTimer1--;
		for(int i = 0; i < game.eb.size(); i ++){
			EntityB tempEnt = game.eb.get(i);
			
			if(Physics.Collision(this, tempEnt)){
				controller.removeEntity(tempEnt);
				if(marioInvincible == false)
					game.Health -= 100;
			}
		}
		for(int i = 0; i < game.ec.size(); i ++){
			EntityC tempEnt = game.ec.get(i);
			
			if(Physics.Collision(this, tempEnt)){
				controller.removeEntity(tempEnt);
				if(marioInvincible == false)
					game.Health -= 100;
			}
		}
		for(int i = 0; i < game.ed.size(); i ++){
			EntityD tempEnt = game.ed.get(i);
			
			if(Physics.Collision(this, tempEnt)){
				controller.removeEntity(tempEnt);
				marioInvincible = true;
				timer1 = 646;
				timer2 = 0;
			}
		}
		//if(velX != 0 || velY != 0)
			anim.runAnimation();
			animl.runAnimation();
			animr.runAnimation();
			animd.runAnimation();
			animSlowingDownl.runAnimation();
			animSlowingDownr.runAnimation();
			starAnim1.runAnimation();
			starAnim1l.runAnimation();
			starAnim1r.runAnimation();
			starAnim1d.runAnimation();
			starAnim2.runAnimation();
			starAnim2l.runAnimation();
			starAnim2r.runAnimation();
			starAnim2d.runAnimation();
			starAnim3.runAnimation();
			starAnim3l.runAnimation();
			starAnim3r.runAnimation();
			starAnim3d.runAnimation();
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, MARIO_WIDTH, MARIO_HEIGHT);
	}
	
	public void render(Graphics g){
		if(game.State == STATE.TRANSITION_ENTRANCE){
			if(spinningAnimationFinished == false){
				if(playerEntranceSpinningSetup == false)
					g.drawImage(playerSmall, (int)x, (int)y, null);
				marioEntranceSpinningAnim.drawAnimation(g, x, y, 0);
				if(System.currentTimeMillis() % 50 == 0 && animationTimer1 < System.currentTimeMillis()){
					animationTimer1 = System.currentTimeMillis();
					marioEntranceSpinningAnim.runAnimation();
					if(playerEntranceSpinningSetup == false)
						playerEntranceSpinningSetup = true;
				}
				if(marioEntranceSpinningAnim.getCount() > 15)//|| if sfx ends
					spinningAnimationFinished = true;
			}
			else if(dancingAnimationFinished == false){
				if(playerEntranceDancingSetup == false)
					g.drawImage(playerSmallDancePose,(int)x, (int)y, null);
				marioEntranceDancingAnim.drawAnimation(g, x, y, 0);
				if(danceProgressionCount < 26 && game.marioDanceSoundLoops.get(danceProgressionCount).clipIsActive() == true){
					dancingInProgress = true;
					marioEntranceDancingAnim.runAnimation();
					marioEntranceDancingAnim.runAnimation();
					if(playerEntranceDancingSetup == false && marioEntranceDancingAnim.getCount() == 1)
						playerEntranceDancingSetup = true;
					danceProgressionCount = danceProgressionCount + 1;
				}
				if(danceProgressionCount > 0){
					if(game.marioDanceSoundLoops.get(danceProgressionCount-1).clipIsActive() == false)
						dancingInProgress = false;
				}
				if(game.marioDanceSoundLoops.getLast().clipIsActive() == false && (int)game.marioDanceSoundLoops.getLast().getLongFramePosition() > 0 && game.isMarioDancePosePause() == false)//|| if sfx ends
					dancingAnimationFinished = true;
			}
			else if(growingAnimationFinished == false){
				if(playerEntranceGrowingSetup == false)
					g.drawImage(playerSmallDancePose,(int)x, (int)y, null);
				marioEntranceGrowingAnim.drawAnimation(g, x, y, 0);
				if(System.currentTimeMillis() % 50 == 0 && animationTimer1 < System.currentTimeMillis()){
					animationTimer1 = System.currentTimeMillis();
					marioEntranceGrowingAnim.runAnimation();
					if(playerEntranceGrowingSetup == false && marioEntranceGrowingAnim.getCount() == 1)
						playerEntranceGrowingSetup = true;
				}
				if(marioEntranceGrowingAnim.getCount() > 8)//|| if sfx ends
					growingAnimationFinished = true;
			}
			else if(turningAroundAnimationFinished == false){
				marioEntranceTurningAroundAnim.drawAnimation(g, x, y, 0);
				if(game.isMarioGrowthPosePause() == false && (System.currentTimeMillis() % 50 == 0 && animationTimer1 < System.currentTimeMillis())){
					if(firstTimeAnimationRun == false)
						marioEntranceTurningAroundAnim.nextFrame();
					animationTimer1 = System.currentTimeMillis();
					marioEntranceTurningAroundAnim.runAnimation();
					firstTimeAnimationRun = true;
				}
				else if(firstTimeAnimationRun == false)
					g.drawImage(playerGrowthPose,(int)x, (int)y, null);
				if(marioEntranceTurningAroundAnim.getCount() > 13)
					turningAroundAnimationFinished = true;
			}
			else if(turningAroundAnimationFinished == true){
				g.drawImage(player, (int)x, (int)y, null);
				firstTimeAnimationRun = false;
				Game.State = Game.STATE.GAME;
			}
		}
		else if(game.State == STATE.TRANSITION_DEATH){
			marioDeathAnim.drawAnimation(g, x, y, 0);
			if(playerDeathSetup == false || (System.currentTimeMillis() % 15 == 0 && animationTimer1 < System.currentTimeMillis())){
				if(playerDeathSetup == false){
					marioDeathAnim.nextFrame();
					marioDeathTimer1 = System.currentTimeMillis() + 300;
					marioDeathTimer2 = System.currentTimeMillis() + 650;
					marioGravityTimer = System.currentTimeMillis() + 850;
					marioGravityTimer2 = System.currentTimeMillis() + 950;
					marioGravityTimer3 = System.currentTimeMillis() + 1000;
					marioGravityTimer4 = System.currentTimeMillis() + 1300;
					marioGravityTimer5 = System.currentTimeMillis() + 1500;
					marioDeathTimer3 = System.currentTimeMillis() + 2000;
				}
				marioDeathAnim.runAnimation();
				animationTimer1 = System.currentTimeMillis();
				playerDeathSetup = true;
			}
			if(System.currentTimeMillis() <= marioDeathTimer1){
			}
			else if(System.currentTimeMillis() <= marioDeathTimer2){
				if(System.currentTimeMillis() % 5 == 0)
					y-= 0.39;
				else if (((marioDeathTimer2 - System.currentTimeMillis()) < 200) && System.currentTimeMillis() % 5 == 0)
					y-= 0.34;
				else if (((marioDeathTimer2 - System.currentTimeMillis()) < 180) && System.currentTimeMillis() % 5 == 0)
					y-= 0.30;
				else if (((marioDeathTimer2 - System.currentTimeMillis()) < 150) && System.currentTimeMillis() % 5 == 0)
					y-= 0.26;
				else if (((marioDeathTimer2 - System.currentTimeMillis()) < 120) && System.currentTimeMillis() % 5 == 0)
					y-= 0.22;
				else if (((marioDeathTimer2 - System.currentTimeMillis()) < 100) && System.currentTimeMillis() % 5 == 0)
					y-= 0.20;
				else if (((marioDeathTimer2 - System.currentTimeMillis()) < 80) && System.currentTimeMillis() % 5 == 0)
					y-= 0.15;
				else if (((marioDeathTimer2 - System.currentTimeMillis()) < 40) && System.currentTimeMillis() % 5 == 0)
					y-= 0.12;
				else if (((marioDeathTimer2 - System.currentTimeMillis()) < 10) && System.currentTimeMillis() % 5 == 0)
					y-= 0.02;
			}
			else if(System.currentTimeMillis() <= marioDeathTimer3){/*
				if(System.currentTimeMillis() <= marioGravityTimer && System.currentTimeMillis() % 5 == 0)
					y += 0.00;
				else if(System.currentTimeMillis() <= marioGravityTimer2 && System.currentTimeMillis() % 5 == 0)
					y+=0.08;
				else if(System.currentTimeMillis() <= marioGravityTimer3 && System.currentTimeMillis() % 5 == 0)
					y+=0.12;
				else if(System.currentTimeMillis() <= marioGravityTimer4 && System.currentTimeMillis() % 5 == 0)
					y+=0.25;
				else if(System.currentTimeMillis() <= marioGravityTimer5 && System.currentTimeMillis() % 5 == 0)
					y+=0.38;
				else if(System.currentTimeMillis() % 5 == 0)
					y+=0.45;*/
				if(System.currentTimeMillis() <= marioGravityTimer && System.currentTimeMillis() % 5 == 0)
					y += 0.00;
				else if(System.currentTimeMillis() % 100 == 0){
					velY += 0.01;
				}
				else
					this.yProgression();
			}
		}
		else if(velY < 0 && game.animationTimer1 == 0){													//CHANGE ANIMATIONS HERE!
			if(marioInvincible == true && !game.isPaused()){
				if(random == 3|| random == 2 || random == 1)
					starAnim1.drawAnimation(g, x, y, 0);
				else if(random == 6|| random == 5 || random == 4)
					starAnim2.drawAnimation(g, x, y, 0);
				else
					starAnim3.drawAnimation(g, x, y, 0);
			}
			else
				anim.drawAnimation(g, x, y, 0);
		}
		else if(velX < 0 && game.animationTimer1 == 0){												//CHANGE ANIMATIONS HERE!
			if(marioInvincible == true && !game.isPaused()){
				if(random == 3|| random == 2 || random == 1)
					starAnim1l.drawAnimation(g, x, y, 0);
				else if(random == 6|| random == 5 || random == 4)
					starAnim2l.drawAnimation(g, x, y, 0);
				else
					starAnim3l.drawAnimation(g, x, y, 0);
			}
			else if(game.slowingDownActivatedl == true)
				animSlowingDownl.drawAnimation(g, x, y, 0);
			else
				animl.drawAnimation(g, x, y, 0);
		}
		else if(velX > 0 && game.animationTimer1 == 0){												//CHANGE ANIMATIONS HERE!
			if(marioInvincible == true && !game.isPaused()){
				if(random == 3 || random == 2 || random == 1)
					starAnim1r.drawAnimation(g, x, y, 0);
				else if(random == 6 || random == 5 || random == 4)
					starAnim2r.drawAnimation(g, x, y, 0);
				else
					starAnim3r.drawAnimation(g, x, y, 0);
			}
			else if(game.slowingDownActivatedr == true)
				animSlowingDownr.drawAnimation(g, x, y, 0);
			else
				animr.drawAnimation(g, x, y, 0);
		}
		else if(velY > 0 && game.animationTimer1 == 0){												//CHANGE ANIMATIONS HERE!
			if(marioInvincible == true && !game.isPaused()){
				if(random == 3 || random == 2 || random == 1)
					starAnim1d.drawAnimation(g, x, y, 0);
				else if(random == 6 || random == 5 || random == 4)
					starAnim2d.drawAnimation(g, x, y, 0);
				else
					starAnim3d.drawAnimation(g, x, y, 0);
			}
			else
				animd.drawAnimation(g, x, y, 0);
		}
		else{
			if(marioInvincible == true && !game.isPaused()){
				if(random == 3|| random == 2 || random == 1)
					starAnim1.drawAnimation(g, x, y, 0);
				else if(random == 6|| random == 5 || random == 4)
					starAnim2.drawAnimation(g, x, y, 0);
				else
					starAnim3.drawAnimation(g, x, y, 0);
			}
			else
				g.drawImage(player, (int)x, (int)y, null);
		}
	}
	
	public void yProgression(){
		this.y+=velY;
	}

	public void xProgression(){
		this.x+=velX;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public void setVelX(double velX){
		this.velX = velX;
	}
	
	public void setVelY(double velY){
		this.velY = velY;
	}
	
	public boolean getMarioInvincible(){
		return marioInvincible;
	}

	public boolean isSpinningAnimationFinished() {
		return spinningAnimationFinished;
	}

	public void setSpinningAnimationFinished(boolean spinningAnimationFinished) {
		this.spinningAnimationFinished = spinningAnimationFinished;
	}

	public boolean isDancingAnimationFinished() {
		return dancingAnimationFinished;
	}

	public void setDancingAnimationFinished(boolean dancingAnimationFinished) {
		this.dancingAnimationFinished = dancingAnimationFinished;
	}

	public boolean isGrowingAnimationFinished() {
		return growingAnimationFinished;
	}

	public void setGrowingAnimationFinished(boolean growingAnimationFinished) {
		this.growingAnimationFinished = growingAnimationFinished;
	}
	
	public boolean isDancingInProgress() {
		return dancingInProgress;
	}

	public void setDancingInProgress(boolean dancingInProgress) {
		this.dancingInProgress = dancingInProgress;
	}
	
	public int getDanceProgressionCount() {
		return danceProgressionCount;
	}

	public void setDanceProgressionCount(int danceProgressionCount) {
		this.danceProgressionCount = danceProgressionCount;
	}

}
