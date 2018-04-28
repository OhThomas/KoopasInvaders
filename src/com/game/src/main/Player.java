/*
 * 
 */
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

// TODO: Auto-generated Javadoc
/**
 * The Class Player.
 */
public class Player extends GameObject implements EntityA{

	/** The Constant MARIO_WIDTH. */
	public static final int MARIO_WIDTH = 16;
	
	/** The Constant MARIO_HEIGHT. */
	public static final int MARIO_HEIGHT = 28;
	
	/** The vel X. */
	private double velX = 0;
	
	/** The vel Y. */
	private double velY = 0;
	
	/** The tex. */
	private Textures tex;
	
	/** The player. */
	private BufferedImage player;
	
	/** The player small. */
	private BufferedImage playerSmall;
	
	/** The player small dance pose. */
	private BufferedImage playerSmallDancePose;
	
	/** The player growth pose. */
	private BufferedImage playerGrowthPose;
	
	/** The player death. */
	private BufferedImage playerDeath;
	
	/** The player 2. */
	private BufferedImage player2;
	
	/** The player 3. */
	private BufferedImage player3;
	
	/** The player entrance spinning setup. */
	private boolean playerEntranceSpinningSetup = false;
	
	/** The player entrance dancing setup. */
	private boolean playerEntranceDancingSetup = false;
	
	/** The player entrance growing setup. */
	private boolean playerEntranceGrowingSetup = false;
	
	/** The player entrance turning around setup. */
	private boolean playerEntranceTurningAroundSetup = false;
	
	/** The player death setup. */
	private boolean playerDeathSetup = false;
	
	/** The first time animation run. */
	private boolean firstTimeAnimationRun = false;
	
	/** The player win setup. */
	public boolean playerWinSetup = false;

	/** The game over. */
	public boolean gameOver = false;
	
	/** The spinning animation finished. */
	private boolean spinningAnimationFinished = false;
	
	/** The dancing animation finished. */
	private boolean dancingAnimationFinished = false;
	
	/** The growing animation finished. */
	private boolean growingAnimationFinished = false;
	
	/** The turning around animation finished. */
	private boolean turningAroundAnimationFinished = false;
	
	/** The dancing in progress. */
	private boolean dancingInProgress = false;
	
	/** The mario invincible. */
	private boolean marioInvincible = false;	//To make Player invincible w/ star
	
	/** The timer 1. */
	private int timer1 = 100;					//Timer for how long Player is invincible
	
	/** The timer 2. */
	private int timer2 = 0;
	
	/** The dance progression count. */
	private int danceProgressionCount = 0;
	
	/** The animation timer 1. */
	private long animationTimer1 = 0;
	
	/** The mario death timer 1. */
	private long marioDeathTimer1 = 0;
	
	/** The mario death timer 2. */
	private long marioDeathTimer2 = 0;
	
	/** The mario death timer 3. */
	private long marioDeathTimer3 = 0;
	
	/** The mario gravity timer. */
	private long marioGravityTimer = 0;
	
	/** The mario gravity timer 2. */
	private long marioGravityTimer2 = 0;
	
	/** The mario gravity timer 3. */
	private long marioGravityTimer3 = 0;
	
	/** The mario gravity timer 4. */
	private long marioGravityTimer4 = 0;
	
	/** The mario gravity timer 5. */
	private long marioGravityTimer5 = 0;
	
	/** The player win timer. */
	public long playerWinTimer = 0;
	
	/** The r. */
	Random r = new Random();
	
	/** The random. */
	int random = r.nextInt((9-1)+1) + 1;		//int randomNum = rand.nextInt((max - min) + 1) + min;
	
	/** The game. */
	Game game;
	
	/** The controller. */
	Controller controller;
	
	/** The anim. */
	Animation anim;
	
	/** The animl. */
	Animation animl;
	
	/** The animr. */
	Animation animr;
	
	/** The animd. */
	Animation animd;
	
	/** The anim slowing downl. */
	Animation animSlowingDownl;
	
	/** The anim slowing downr. */
	Animation animSlowingDownr;
	
	/** The star anim 1. */
	Animation starAnim1;
	
	/** The star anim 1 l. */
	Animation starAnim1l;
	
	/** The star anim 1 r. */
	Animation starAnim1r;
	
	/** The star anim 1 d. */
	Animation starAnim1d;
	
	/** The star anim 2. */
	Animation starAnim2;
	
	/** The star anim 2 l. */
	Animation starAnim2l;
	
	/** The star anim 2 r. */
	Animation starAnim2r;
	
	/** The star anim 2 d. */
	Animation starAnim2d;
	
	/** The star anim 3. */
	Animation starAnim3;
	
	/** The star anim 3 l. */
	Animation starAnim3l;
	
	/** The star anim 3 r. */
	Animation starAnim3r;
	
	/** The star anim 3 d. */
	Animation starAnim3d;
	
	/** The mario entrance spinning anim. */
	Animation marioEntranceSpinningAnim;
	
	/** The mario entrance dancing anim. */
	Animation marioEntranceDancingAnim;
	
	/** The mario entrance growing anim. */
	Animation marioEntranceGrowingAnim;
	
	/** The mario entrance turning around anim. */
	Animation marioEntranceTurningAroundAnim;
	
	/** The mario death anim. */
	Animation marioDeathAnim;
	
	/**
	 * Instantiates a new player.
	 *
	 * @param x the x
	 * @param y the y
	 * @param tex the tex
	 * @param game the game
	 * @param controller the controller
	 */
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
		animl.nextFrame();
		animr.nextFrame();
	}
	
	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityA#tick()
	 */
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
				if(marioInvincible == false && tempEnt.getEntityBDead() == false)
					game.Health -= 100;
			}
		}
		for(int i = 0; i < game.ec.size(); i ++){
			EntityC tempEnt = game.ec.get(i);
			
			if(Physics.Collision(this, tempEnt)){
				if(marioInvincible == false && tempEnt.getEntityCDead() == false)
					game.Health -= 100;
				controller.removeEntity(tempEnt);
			}
		}
		for(int i = 0; i < game.ed.size(); i ++){
			EntityD tempEnt = game.ed.get(i);
			if(Physics.Collision(this, tempEnt) && tempEnt.getItemName() == "chainChompItem"){
				tempEnt.getItemSoundLoop().play();
				controller.removeEntity(tempEnt);
				game.getHUD().setItemObtained(true);
				game.getHUD().setItemName(tempEnt.getItemName());
			}
			else if(Physics.Collision(this, tempEnt) && tempEnt.getItemName() == "mario1Star"){
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
	
	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityA#getBounds()
	 */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, MARIO_WIDTH, MARIO_HEIGHT);
	}
	
	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityA#render(java.awt.Graphics)
	 */
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
				if(game.marioDanceSoundLoops.getLast().clipIsActive() == false && (int)game.marioDanceSoundLoops.getLast().getLongFramePosition() > 0 && game.getMarioDancePosePause() == false)//|| if sfx ends
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
				if(game.getMarioGrowthPosePause() == false && (System.currentTimeMillis() % 50 == 0 && animationTimer1 < System.currentTimeMillis())){
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
		else if(game.State == STATE.TRANSITION_WIN) {
			g.drawImage(playerGrowthPose,(int)x, (int)y, null);
			if(playerWinSetup == false) {
				playerWinTimer = System.currentTimeMillis() + 1200;
				playerWinSetup = true;
			}
			if(playerWinTimer < System.currentTimeMillis()) {
				gameOver = true;
				Game.State = Game.STATE.GAMEOVER;
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
	
	/**
	 * Y progression.
	 */
	public void yProgression(){
		this.y+=velY;
	}

	/**
	 * X progression.
	 */
	public void xProgression(){
		this.x+=velX;
	}
	
	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityA#getX()
	 */
	public double getX(){
		return x;
	}
	
	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityA#getY()
	 */
	public double getY(){
		return y;
	}
	
	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(double x){
		this.x = x;
	}
	
	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(double y){
		this.y = y;
	}
	
	/**
	 * Sets the vel X.
	 *
	 * @param velX the new vel X
	 */
	public void setVelX(double velX){
		this.velX = velX;
	}
	
	/**
	 * Sets the vel Y.
	 *
	 * @param velY the new vel Y
	 */
	public void setVelY(double velY){
		this.velY = velY;
	}
	
	/**
	 * Gets the mario invincible.
	 *
	 * @return the mario invincible
	 */
	public boolean getMarioInvincible(){
		return marioInvincible;
	}

	/**
	 * Checks if is spinning animation finished.
	 *
	 * @return true, if is spinning animation finished
	 */
	public boolean isSpinningAnimationFinished() {
		return spinningAnimationFinished;
	}

	/**
	 * Sets the spinning animation finished.
	 *
	 * @param spinningAnimationFinished the new spinning animation finished
	 */
	public void setSpinningAnimationFinished(boolean spinningAnimationFinished) {
		this.spinningAnimationFinished = spinningAnimationFinished;
	}

	/**
	 * Checks if is dancing animation finished.
	 *
	 * @return true, if is dancing animation finished
	 */
	public boolean isDancingAnimationFinished() {
		return dancingAnimationFinished;
	}

	/**
	 * Sets the dancing animation finished.
	 *
	 * @param dancingAnimationFinished the new dancing animation finished
	 */
	public void setDancingAnimationFinished(boolean dancingAnimationFinished) {
		this.dancingAnimationFinished = dancingAnimationFinished;
	}

	/**
	 * Checks if is growing animation finished.
	 *
	 * @return true, if is growing animation finished
	 */
	public boolean isGrowingAnimationFinished() {
		return growingAnimationFinished;
	}

	/**
	 * Sets the growing animation finished.
	 *
	 * @param growingAnimationFinished the new growing animation finished
	 */
	public void setGrowingAnimationFinished(boolean growingAnimationFinished) {
		this.growingAnimationFinished = growingAnimationFinished;
	}
	
	/**
	 * Checks if is dancing in progress.
	 *
	 * @return true, if is dancing in progress
	 */
	public boolean isDancingInProgress() {
		return dancingInProgress;
	}

	/**
	 * Sets the dancing in progress.
	 *
	 * @param dancingInProgress the new dancing in progress
	 */
	public void setDancingInProgress(boolean dancingInProgress) {
		this.dancingInProgress = dancingInProgress;
	}
	
	/**
	 * Gets the dance progression count.
	 *
	 * @return the dance progression count
	 */
	public int getDanceProgressionCount() {
		return danceProgressionCount;
	}

	/**
	 * Sets the dance progression count.
	 *
	 * @param danceProgressionCount the new dance progression count
	 */
	public void setDanceProgressionCount(int danceProgressionCount) {
		this.danceProgressionCount = danceProgressionCount;
	}

	/**
	 * Gets the game over.
	 *
	 * @return the game over
	 */
	public boolean getGameOver() {
		return gameOver;
	}

	/**
	 * Sets the game over.
	 *
	 * @param gameOver the new game over
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}


}
