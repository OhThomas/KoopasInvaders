package com.game.src.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityD;
import com.game.src.main.libs.Animation;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public static final int MARIO_WIDTH = 16;
	public static final int MARIO_HEIGHT = 28;
	public final String TITLE = "Koopa's Invaders!";
	
	private boolean running = false;
	private boolean paused = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage animatedStar = null;
	private BufferedImage animatedShootingStar = null;
	private BufferedImage mario1StarSpriteSheet = null;
	private BufferedImage background = null;
	private BufferedImage marioLives = null;
	private BufferedImage bowserSpriteSheet = null;
	private BufferedImage bulletBillSpriteSheet = null;
	private BufferedImage marioPlayerStarAnimations = null;
	private BufferedImage marioSlowingDownSprites = null;
	private BufferedImage fullMarioSpriteSheet = null;
	private BufferedImage transparentBlocks = null;
	
	Animation starAnim;
	Animation shootingStarAnim;
	Animation transparentBlocksAnim;
	
	private boolean shootingStarFrameStop = false;
	private boolean xLBoolean = false;
	private boolean xRBoolean = false;
	private boolean yUBoolean = false;
	private boolean yDBoolean = false;
	private boolean isShooting = false;
	private boolean spawnDone = false;
	private boolean spawnDone2 = false;
	private boolean spawnDone3 = false;
	private boolean spawnDone4 = false;
	private boolean marioHasBeenInvincible = false;
	private double myTime = 0.0;
	private int numberOfFireBallsShot = 0;
	private int numberOfFireBallsShotDecoy = 0;
	private double slowingDown = 0;
	private long slowingDownTimerLong = 0;
	public boolean slowingDownActivatedl = false;
	public boolean slowingDownActivatedr = false;
	public boolean enemyHitRightBarrier = false;
	public boolean gameOverBoolean = false;
	public boolean gameOverSoundBoolean = false;
	public double enemySpeedIncrease = 0.5;
	public int animationTimer1 = 0;
	private long runningTimerLong = 0;
	private boolean runningTimerActivated = false;
	private boolean runningTimerActivatedResponse = false;
	private boolean soundSet = false;
	private boolean soundTimerSet = false;
	private long soundFXTimer = 0;
	private long transitionTimer = 0;
	private long gameStartSoundTimer = 0;
	private long pauseSoundFXTimer = 0;
	private long visualPauseTimer = 0;
	private long marioDancePosePauseTimer = 0;
	private long marioGrowthPosePauseTimer = 0;
	private long marioLetsGoPauseTimer = 0;
	private boolean userHasPaused = false;
	private boolean soundFXisPlaying = false;
	private boolean soundFXBoolean = false;
	private boolean soundFXClip1Reset = false;
	private boolean marioDancePosePause = false;
	private boolean marioGrowthPosePause = false;
	private boolean marioLetsGoPause = false;
	private int soundRandomizer = 0;
	private int menuSoundLoopRandomizer = 0;
	private boolean menuSoundSet = false;
	LinkedList<SoundLoops> menuSoundLoops = new LinkedList<SoundLoops>();
	LinkedList<SoundLoops> gameSoundLoops = new LinkedList<SoundLoops>();
	LinkedList<SoundLoops> marioDanceSoundLoops = new LinkedList<SoundLoops>();
	SoundLoops gameOverSoundLoop;
	SoundLoops marioStarSoundLoop;
	SoundLoops soundFXClip1SoundLoop;
	SoundLoops soundFXClip2SoundLoop;
	SoundLoops pauseSoundFXSoundLoop;
	SoundLoops marioSpinningSoundLoop;
	SoundLoops marioLetsGoSoundLoop;
	private Player p;
	private Controller c;
	private Enemy e;
	private Textures tex;
	private HUD hud;
	private Menu menu;
	private GameOver gameOver;
	private BasicBlocks bb;													//BLOCKS
	
	private BufferedImage title = null;
	private BufferedImage gameOverTitle = null;
	private BufferedImage playTitle = null;
	private BufferedImage helpTitle = null;
	private BufferedImage exitTitle = null;
	
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	public LinkedList<EntityC> ec;
	public LinkedList<EntityD> ed;
	
	public static int Health = 100;
	
	public static enum STATE{
		MENU,
		TRANSITION,
		GAME,
		PAUSE,
		GAMEOVER
	};
	public static STATE State = STATE.MENU;
	
	public void init(){
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			spriteSheet = loader.loadImage("/AnimationSpriteSheetNew.png");
			animatedStar = loader.loadImage("/animatedstar.png");
			animatedShootingStar = loader.loadImage("/shootingstarworadiant.png");
			mario1StarSpriteSheet = loader.loadImage("/mario1starspritesheet.png");
			background = loader.loadImage("/starsbackgroundbigger.png");
			marioLives = loader.loadImage("/mariolivessprite.png");
			bowserSpriteSheet = loader.loadImage("/bowserspritesheet.png");
			bulletBillSpriteSheet = loader.loadImage("/bulletbillspritesheet.png");
			marioPlayerStarAnimations = loader.loadImage("/marioplayerstaranimations.png");
			marioSlowingDownSprites = loader.loadImage("/firemarioslidingeffect.png");
			fullMarioSpriteSheet = loader.loadImage("/mario.png");
			title = loader.loadImage("/koopasinvaderstitlebigger.png");
			gameOverTitle = loader.loadImage("/gameover1bigger.png");
			playTitle = loader.loadImage("/newplaybutton.png");
			helpTitle = loader.loadImage("/newhelpbutton.png");
			exitTitle = loader.loadImage("/newexitbutton.png");
			transparentBlocks = loader.loadImage("/randomtransparentblocks.png");
		}catch(IOException e){
			e.printStackTrace();
		}
		
		tex = new Textures(this);
		bb = new BasicBlocks();												//BLOCKS
		c = new Controller(tex, this);
		p = new Player(Game.WIDTH,(Game.HEIGHT * SCALE) - MARIO_HEIGHT,tex,this,c);
		hud = new HUD();
		menu = new Menu();
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		ec = c.getEntityC();
		ed = c.getEntityD();
		
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());
		
		
		starAnim = new Animation(10, tex.animatedStar[0],tex.animatedStar[1],tex.animatedStar[2],tex.animatedStar[3],
				tex.animatedStar[4],tex.animatedStar[5],tex.animatedStar[6],tex.animatedStar[7],
				tex.animatedStar[8],tex.animatedStar[9],tex.animatedStar[10],tex.animatedStar[11],
				tex.animatedStar[12],tex.animatedStar[13],tex.animatedStar[14],tex.animatedStar[15],
				tex.animatedStar[16],tex.animatedStar[16],tex.animatedStar[18],tex.animatedStar[19]);
		
		shootingStarAnim = new Animation(3, tex.animatedShootingStar[0],tex.animatedShootingStar[1],
				tex.animatedShootingStar[2],tex.animatedShootingStar[3],
				tex.animatedShootingStar[4],tex.animatedShootingStar[5],
				tex.animatedShootingStar[6],tex.animatedShootingStar[7],
				tex.animatedShootingStar[8],tex.animatedShootingStar[9],
				tex.animatedShootingStar[10],tex.animatedShootingStar[11]);
		
		transparentBlocksAnim = new Animation(3, tex.transparentBlocks[0], tex.transparentBlocks[1],
				tex.transparentBlocks[2]);

	}
	
	private synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop(){
		if(!running)
			return;
		
		running = false;
		try{
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	public void run(){
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running){
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			try {
				render();
			} catch (IOException e) {
				e.printStackTrace();
			}
			frames++;
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(updates + " Ticks, FPS " + frames);
				updates = 0;
				frames = 0;
				myTime++;
			}
			
		}
		stop();
	}
	
	private void tick(){
		if(State == STATE.GAME){
			if(!paused){
				p.tick();
				hud.tick();
				c.tick();
			}
		}
		starAnim.runAnimation();
		transparentBlocksAnim.runAnimation();
		if((myTime / 10) == (int)(myTime/10) && shootingStarFrameStop == false)//(myTime > 10 && myTime < 22 || myTime > 30 && myTime < 42)
			shootingStarAnim.runAnimation();
	}
	
	private void render() throws IOException{
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null){
			createBufferStrategy(3);//or 2 
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) bs.getDrawGraphics(); 			//BLOCKS
		//**************DRAW**************//
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		g.drawImage(background, 0, 0, null);
		
		//if((myTime / 10) == (int)(myTime/10) && myTime != 0)
			//shootingStarFrameStop = false;
		
		//draw shooting stars
		if ((myTime / 10) == (int)(myTime/10) && shootingStarFrameStop == false && myTime != 0){ //|| myTime > 30 && myTime < 42){
			shootingStarAnim.drawAnimation(g, 200, 43, 0);
			if (shootingStarAnim.getCount() == 12)
				shootingStarFrameStop = true;
		}
		//draw animated star getting brighter and less bright
		starAnim.drawAnimation(g, 80, 20, 0);
		
		if (gameOverBoolean == true)
			State = STATE.GAMEOVER;
		//State = STATE.GAME;
		if(State == STATE.GAME){
			if(marioHasBeenInvincible == false){					//Setting up music
				if(this.menuSoundLoops.get(this.menuSoundLoopRandomizer).getSoundLoopBoolean() == true){
					this.menuSoundLoops.get(this.menuSoundLoopRandomizer).stop();
					this.menuSoundLoops.get(this.menuSoundLoopRandomizer).setSoundLoopBoolean(false);
					menuSoundSet = false;
				}
				
				if(soundSet == false){	
					Random rand = new Random();
					soundRandomizer = rand.nextInt(2);
					this.gameSoundLoops.get(soundRandomizer).play();
					this.gameSoundLoops.get(soundRandomizer).loop();
					this.gameSoundLoops.get(soundRandomizer).setSoundLoopBoolean(true);
					soundSet = true;
				}
				if(System.currentTimeMillis() > pauseSoundFXTimer && pauseSoundFXSoundLoop.getSoundLoopBoolean() == true){
					paused = false;
					pauseSoundFXSoundLoop.setSoundLoopBoolean(false);
					this.gameSoundLoops.get(soundRandomizer).loop();
				}
			}
			if(p.getMarioInvincible() == false && marioHasBeenInvincible == true){		//Setting up SoundFX in between Audio Clips
				this.marioStarSoundLoop.stop();
				
				if(soundFXBoolean == true){
					paused = true;
					soundFXTimer = System.currentTimeMillis() + 500;
					soundFXBoolean = false;
					soundFXisPlaying = true;
				}

				if(System.currentTimeMillis() > pauseSoundFXTimer && pauseSoundFXSoundLoop.getSoundLoopBoolean() == true){
					paused = false;
					pauseSoundFXSoundLoop.setSoundLoopBoolean(false);
					this.gameSoundLoops.get(soundRandomizer).loop();
				}
				
				if(System.currentTimeMillis() > soundFXTimer && soundTimerSet == true){							//checking if paused for soundFX Timer
					paused = false;
					soundFXisPlaying = false;
				}
				
				if(!paused && soundTimerSet == true){
					this.soundFXClip1SoundLoop.stop();
					soundFXClip1Reset = false;
					if(this.gameSoundLoops.get(this.soundRandomizer).getSoundLoopBoolean() == true && this.soundFXClip1SoundLoop.getSoundLoopBoolean() == true){
						this.gameSoundLoops.get(this.soundRandomizer).loop();
						this.soundFXClip1SoundLoop.setSoundLoopBoolean(false);
					}
					soundTimerSet = false;
			}
				else if(paused == true && soundTimerSet == true){
					transparentBlocksAnim.drawAnimation(g,p.getX(), p.getY(), 0);
					//add visual effect
					if(!soundFXClip1Reset){
						this.soundFXClip1SoundLoop.setSoundLoopBoolean(true);
						this.soundFXClip1SoundLoop.play();
						soundFXClip1Reset = true;
					}
					this.soundFXClip1SoundLoop.loop();
				}
			}
			if(p.getMarioInvincible() == true){											//Setting up Star Sound
				
				if(this.gameSoundLoops.get(this.soundRandomizer).getSoundLoopBoolean() == true)
					this.gameSoundLoops.get(this.soundRandomizer).stop();
				
				if(soundFXBoolean == false){
					paused = true;
					soundFXTimer = System.currentTimeMillis() + 990;
					soundFXBoolean = true;
					soundFXisPlaying = true;
				}

				if(System.currentTimeMillis() > pauseSoundFXTimer && pauseSoundFXSoundLoop.getSoundLoopBoolean() == true){
					paused = false;
					pauseSoundFXSoundLoop.setSoundLoopBoolean(false);
					this.marioStarSoundLoop.loop();
				}
				
				if(System.currentTimeMillis() > soundFXTimer && soundFXisPlaying == true){							//checking if paused for soundFX Timer
					paused = false;
					soundFXisPlaying = false;
				}
				if(!paused && soundTimerSet == false){
					this.soundFXClip2SoundLoop.stop();
					soundFXClip1Reset = false;
					this.marioStarSoundLoop.play();
					this.marioStarSoundLoop.loop();
					soundTimerSet = true;
					marioHasBeenInvincible = true;
				}

				else if(paused == true && soundTimerSet == false){
					transparentBlocksAnim.drawAnimation(g, p.getX(), p.getY(), 0);
					//add visual effect
					if(!soundFXClip1Reset){
						this.soundFXClip2SoundLoop.play();
						soundFXClip1Reset = true;
					}
					this.soundFXClip2SoundLoop.loop();
				}
			}					
																				//Setting up next song to play after a song ends
			if(((int)this.gameSoundLoops.get(this.soundRandomizer).getLongFramePosition() >= this.gameSoundLoops.get(this.soundRandomizer).getFrameLength()-(441*4) && this.gameSoundLoops.get(this.soundRandomizer).getSoundLoopBoolean() == true && State == STATE.GAME)){	
				if(this.gameSoundLoops.get(this.soundRandomizer).getSoundLoopBoolean() == true){
					this.gameSoundLoops.get(this.soundRandomizer).stop();
					this.gameSoundLoops.get(this.soundRandomizer).setSoundLoopBoolean(false);
				}
				soundSet = false;
				if(soundSet == false){	
					Random rand = new Random();
					soundRandomizer = rand.nextInt(2);
					this.gameSoundLoops.get(soundRandomizer).loop();
					this.gameSoundLoops.get(soundRandomizer).setFramePosition(0);
					this.gameSoundLoops.get(soundRandomizer).setSoundLoopBoolean(true);
					soundSet = true;
				}
					
			}
			if(runningTimerActivated == true){											//Checking to see if mario should be in running slide animation
				runningTimerLong = System.currentTimeMillis();
				runningTimerActivated = false;
				runningTimerActivatedResponse = true;
			}
			if(slowingDownTimerLong <= System.currentTimeMillis() && slowingDownTimerLong != 0){
				slowingDownTimerLong = 0;
			}
			if(slowingDownTimerLong > System.currentTimeMillis()){
				if (xLBoolean == true){
					p.setVelX(-5);
					slowingDownTimerLong = 0;
					slowingDownActivatedl = false;
					slowingDownActivatedr = false;
				}
				if (xRBoolean == true){
					p.setVelX(5);
					slowingDownTimerLong = 0;
					slowingDownActivatedl = false;
					slowingDownActivatedr = false;
				}
				//slowingDown+= 0.13;
				p.setVelX(slowingDown);
			}
			else if(slowingDownTimerLong == 0 && slowingDownActivatedl == true || slowingDownActivatedr == true){
				p.setVelX(0);
				slowingDownActivatedl = false;
				slowingDownActivatedr = false;
			}
			//SPAWN ENEMIES
			/*
			if (spawnDone == false){													//Spawning enemies
			for(int i = 0; i < (Game.WIDTH * Game.SCALE); i+=64){
				c.addEntity(new Enemy(i,0, tex, c , this));
				}
				spawnDone = true;
			}
			if(eb.isEmpty() && spawnDone2 == false){
				this.enemyHitRightBarrier = false;
				this.enemySpeedIncrease = 1.0;
				for(int i = 0; i < (Game.WIDTH * Game.SCALE); i+=64){
					c.addEntity(new Enemy2(i,0, tex, c , this));
				}
				spawnDone2 = true;
			}
			if(eb.isEmpty() && spawnDone3 == false){
				this.enemyHitRightBarrier = false;
				this.enemySpeedIncrease = 1.2;
				for(int i = 0; i < (Game.WIDTH * Game.SCALE); i+=64){
					c.addEntity(new Enemy3(i,0, tex, c , this));
				}
				spawnDone3 = true;
			}*/
			if(eb.isEmpty() && spawnDone4 == false){								//Spawning Bowser
				this.enemyHitRightBarrier = false;
				this.enemySpeedIncrease = 1.0;
				c.addEntity(new Bowser(0,50, tex, c , this));
				spawnDone4 = true;
			}
			//SPAWN ENEMIES FINISHED
			if(!userHasPaused){
				bb.draw(g2d);													//BLOCKS
				p.render(g);
				c.render(g);
				g.drawImage(marioLives, 0, (Game.HEIGHT * 2), null);
			}
			if(userHasPaused){
				if((System.currentTimeMillis() % 500 == 0) && System.currentTimeMillis() > visualPauseTimer)
					visualPauseTimer = System.currentTimeMillis() + 500;
				if(visualPauseTimer > System.currentTimeMillis() || pauseSoundFXTimer > System.currentTimeMillis()){
					bb.draw(g2d);													//BLOCKS
					p.render(g);
					c.render(g);
					g.drawImage(marioLives, 0, (Game.HEIGHT * 2), null);
				}
			}
			
				//if (i == rand.nextInt())
					if(ec.isEmpty() && !eb.isEmpty() && spawnDone4 == false){								
						Random rand = new Random();
						int i = rand.nextInt(eb.size());
						c.addEntity(new GreenShell(eb.get(i).getX(),eb.get(i).getY() - 32,tex, this));
					}
					if(spawnDone4 == true){												//Spawning Bowser Mechanics
						hud.render(g);
						if((int)hud.getTimer() <= 0){
							//SPAWN BULLET BILLS
							
							Random rand = new Random();
							int i = rand.nextInt(20000);
							if(i == 1 && ec.size() < 6){
								c.addEntity(new BulletBill(eb.getLast().getX(),eb.getLast().getY() - 32,tex, this));
							}
							
							//SPAWN GREEN SHELLS
							if(numberOfFireBallsShot % 6==0)
							{
								numberOfFireBallsShot += 1;
								numberOfFireBallsShotDecoy += 1;
								c.addEntity(new GreenShell(eb.getLast().getX()+32,eb.getLast().getY() - 32,tex, this));
							}
							
							//SPAWN STARS
							int j = rand.nextInt(4);//400000
							if(j == 2 && ed.size() < 1 && p.getMarioInvincible() == false)
								c.addEntity(new Mario1Star(-16,this.playerY() - 32,tex, this));
						}
					}
			if (animationTimer1 != 0){													//if they shoot a fireball this stops them
				p.setVelX(0);
				p.setVelY(0);
				if (animationTimer1 == 1){
					if (xLBoolean == true)												//if they're still holding down the button
						p.setVelX(-5);
					if (xRBoolean == true)
						p.setVelX(5);
				}
			}
			if (Health > 0){
				//draw x 1
			}
			else{
				// - lives
				// if lives = 0 then State = STATE.GAMEOVER
				//draw x 0
				State = STATE.GAMEOVER;
			}
		}else if(State == STATE.MENU){													//Menu
			//menu.render(g);
			if(menuSoundSet == false){
				Random rand = new Random();
				menuSoundLoopRandomizer = rand.nextInt(2);
				this.menuSoundLoops.get(menuSoundLoopRandomizer).loop();
				this.menuSoundLoops.get(menuSoundLoopRandomizer).setSoundLoopBoolean(true);
				menuSoundSet = true;
			}
			g.drawImage(title, 70, 100, null);
			g.drawImage(playTitle, Game.WIDTH / 2 + 120, 200, null);
			g.drawImage(helpTitle, Game.WIDTH / 2 + 120, 300, null);
			g.drawImage(exitTitle, Game.WIDTH / 2 + 120, 400, null);
		}else if (State == STATE.TRANSITION){
			if(this.menuSoundLoops.get(this.menuSoundLoopRandomizer).getSoundLoopBoolean() == true){
				this.menuSoundLoops.get(this.menuSoundLoopRandomizer).stop();
				this.menuSoundLoops.get(this.menuSoundLoopRandomizer).setSoundLoopBoolean(false);
				menuSoundSet = false;
			}
			p.render(g);
			if(p.isSpinningAnimationFinished() == false && this.marioSpinningSoundLoop.getSoundLoopBoolean() == false){
				this.marioSpinningSoundLoop.play();
				this.marioSpinningSoundLoop.setSoundLoopBoolean(true);
			}
			else if (p.isSpinningAnimationFinished() == true && p.isDancingAnimationFinished() == false){
				if(this.marioSpinningSoundLoop.getSoundLoopBoolean() == true)
					this.marioSpinningSoundLoop.setSoundLoopBoolean(false);
				if(p.getDanceProgressionCount() == 0){//just here to use both manual and progression w/o sound cuts so I can use either one later
					if(p.marioEntranceDancingAnim.getCount() >= 0 && p.isDancingInProgress() == false)//manual progression
						this.marioDanceSoundLoops.get(p.getDanceProgressionCount()).play();
				}
				else if (p.getDanceProgressionCount() < 26){
					if(p.getDanceProgressionCount() < 26 && this.marioDanceSoundLoops.get(p.getDanceProgressionCount()-1).endsSoon() == true)//progression without cuts in sound
						this.marioDanceSoundLoops.get(p.getDanceProgressionCount()).play();
				}
				else if(p.marioEntranceDancingAnim.getCount() == 26 && marioDancePosePause == false){
					p.marioEntranceDancingAnim.setCount(0);
					p.marioEntranceSpinningAnim.setCount(0);
					marioDancePosePauseTimer = System.currentTimeMillis() + 600;
					marioDancePosePause = true;
				}
				if(marioDancePosePause == true && marioDancePosePauseTimer < System.currentTimeMillis())
					marioDancePosePause = false;
			}
			else if(p.isDancingAnimationFinished() == true && p.isGrowingAnimationFinished() == false && this.soundFXClip2SoundLoop.getSoundLoopBoolean() == false){
				this.soundFXClip2SoundLoop.play();
				this.soundFXClip2SoundLoop.setSoundLoopBoolean(true);
			}
			else if(p.isGrowingAnimationFinished() == true){
				if(marioGrowthPosePauseTimer == 0 && marioGrowthPosePause == false){
					marioGrowthPosePauseTimer = System.currentTimeMillis() + 1300;
					marioGrowthPosePause = true;
				}
				else if(marioGrowthPosePauseTimer < System.currentTimeMillis() && marioGrowthPosePause == true){
					marioLetsGoPauseTimer = System.currentTimeMillis() + 100;
					marioLetsGoPause = true;
					marioGrowthPosePause = false;
				}
				if(marioLetsGoPause == true && marioLetsGoPauseTimer < System.currentTimeMillis()){
					this.marioLetsGoSoundLoop.play();
					marioLetsGoPause = false;
				}
			}
		}else if (State == STATE.GAMEOVER){												//GameOver
			//wait a lil bit
			//bs.show();
			if(this.gameSoundLoops.get(this.soundRandomizer).getSoundLoopBoolean() == true){
				this.gameSoundLoops.get(this.soundRandomizer).stop();
				this.gameSoundLoops.get(this.soundRandomizer).setSoundLoopBoolean(false);
				soundSet = false;
			}

			if(gameOverSoundBoolean == false){
				this.gameOverSoundLoop.play();
				gameOverSoundBoolean = true;
			}
			numberOfFireBallsShot = numberOfFireBallsShot - numberOfFireBallsShotDecoy;
			
			g.drawImage(gameOverTitle, 170, 100, null);									//Buttons
			g.drawImage(playTitle, Game.WIDTH / 2 + 120, 200, null);
			g.drawImage(helpTitle, Game.WIDTH / 2 + 120, 300, null);
			g.drawImage(exitTitle, Game.WIDTH / 2 + 120, 400, null);
			//gameOver.render(g);
			
			//g.dispose();
			//g2d.dispose();
		}
		
		//**************DRAW**************//
		g.dispose();
		bs.show();
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(State == STATE.GAME){
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
			//p.setVelY(-5);
			//yUBoolean = true;
		} else if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
			p.setVelX(-5);
			xLBoolean = true;
			slowingDownTimerLong = 0;
			slowingDownActivatedl = false;
			slowingDownActivatedr = false;
			if(runningTimerActivatedResponse == false)
				runningTimerActivated = true;
		} else if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
			//p.setVelY(5);
			//yDBoolean = true;
		} else if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
			p.setVelX(5);
			xRBoolean = true;
			slowingDownTimerLong = 0;
			slowingDownActivatedl = false;
			slowingDownActivatedr = false;
			if(runningTimerActivatedResponse == false)
				runningTimerActivated = true;
		}
		if (key == KeyEvent.VK_SPACE && !isShooting){											//Fireballs
			isShooting = true;
			if(ea.isEmpty() && !paused){
				c.addEntity(new Fireball(p.getX(),p.getY() + 32,tex, this));
				animationTimer1 = 10;
				numberOfFireBallsShot++;
			}
		}
		if(key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_P){
			if(paused == false && soundFXisPlaying == false){
				if(p.getMarioInvincible() == true)
					this.marioStarSoundLoop.stop();
				else
					this.gameSoundLoops.get(soundRandomizer).stop();
				this.pauseSoundFXSoundLoop.setFramePosition(0);
				this.pauseSoundFXSoundLoop.play();
				pauseSoundFXTimer = System.currentTimeMillis() + 685;
				paused = true;
				userHasPaused = true;
			}
			else if(paused == true && soundFXisPlaying == false){
				if(pauseSoundFXTimer < System.currentTimeMillis()){
				/*if(p.getMarioInvincible() == true)
					this.marioStarSoundLoop.loop();
				else
					this.gameSoundLoops.get(soundRandomizer).loop();
				paused = false;*/
				this.pauseSoundFXSoundLoop.play();
				pauseSoundFXTimer = System.currentTimeMillis() + 685;
				this.pauseSoundFXSoundLoop.setSoundLoopBoolean(true);
				}
				userHasPaused = false;
			}
		}
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
			/*
			p.setVelY(0);
			yUBoolean = false;
			if(yDBoolean == true)
				p.setVelY(5);
			*/
		} else if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
			p.setVelX(0);
			xLBoolean = false;
			if(xRBoolean == true){
				p.setVelX(5);
				runningTimerActivated = true;
			}
			else{
				if(System.currentTimeMillis() - runningTimerLong > 666/2){														//This activates sliding animation for left side
					slowingDownActivatedl = true;
					slowingDownTimerLong = System.currentTimeMillis() + 200;
					slowingDown = -1.73;
					p.setVelX(slowingDown);
					runningTimerLong = 0;
					runningTimerActivatedResponse = false;
				}
			}

			runningTimerActivatedResponse = false;
		} else if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
			/*
			p.setVelY(0);
			yDBoolean = false;
			if(yUBoolean == true)
				p.setVelY(-5);
			*/
		} else if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
			p.setVelX(0);
			xRBoolean = false;
			if(xLBoolean == true){
				p.setVelX(-5);
				runningTimerActivated = true;
			}
			else{
				if(System.currentTimeMillis() - runningTimerLong > 666/2){														//This activates sliding animation for right side
					slowingDownActivatedr = true;
					slowingDownTimerLong = System.currentTimeMillis() + 200;
					slowingDown = 1.73;
					p.setVelX(slowingDown);
					runningTimerLong = 0;
					runningTimerActivatedResponse = false;
				}
			}

			runningTimerActivatedResponse = false;
		} else if(key == KeyEvent.VK_SPACE){
			isShooting = false;
		}
	}
	
	public static float clamp(float var, float min, float max){
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String args[])
		throws Exception{
		String gameAudioFile = "res/Sounds/Music/mario1remix.wav";													//Loading in Music
		String gameAudioFile2 = "res/Sounds/Music/marioremixbitch1c.wav";
		String menuAudioFile = "res/Sounds/Music/supermarioworldremix1.wav";
		String menuAudioFile2 = "res/Sounds/Music/menuloop2.wav";
		String gameOverAudioFile = "res/Sounds/SFX/smw_game_over.wav";
		String marioStarAudioFile = "res/Sounds/SFX/mariowhistle.wav";
		String soundFXClip1 = "res/Sounds/SFX/riseupacoustic1cWAVE.wav";
		String soundFXClip2 = "res/Sounds/SFX/MariopowerupSFX.wav";
		String pauseSoundFXFile = "res/Sounds/SFX/smb_pause.wav";
		String marioSpinningFile = "res/Sounds/SFX/smw_feather_get.wav";
		String marioLetsGoFile = "res/Sounds/SFX/mk64_mario02.wav";
		String marioDanceSoundFXFile1 = "res/Sounds/SFX/DanceSFX/mariodancepart1.wav";
		String marioDanceSoundFXFile2 = "res/Sounds/SFX/DanceSFX/mariodancepart2.wav";
		String marioDanceSoundFXFile3 = "res/Sounds/SFX/DanceSFX/mariodancepart3.wav";
		String marioDanceSoundFXFile4 = "res/Sounds/SFX/DanceSFX/mariodancepart4.wav";
		String marioDanceSoundFXFile5 = "res/Sounds/SFX/DanceSFX/mariodancepart5.wav";
		String marioDanceSoundFXFile6 = "res/Sounds/SFX/DanceSFX/mariodancepart6.wav";
		String marioDanceSoundFXFile7 = "res/Sounds/SFX/DanceSFX/mariodancepart7.wav";
		String marioDanceSoundFXFile8 = "res/Sounds/SFX/DanceSFX/mariodancepart8.wav";
		String marioDanceSoundFXFile9 = "res/Sounds/SFX/DanceSFX/mariodancepart9.wav";
		String marioDanceSoundFXFile10 = "res/Sounds/SFX/DanceSFX/mariodancepart10.wav";
		String marioDanceSoundFXFile11 = "res/Sounds/SFX/DanceSFX/mariodancepart11.wav";
		String marioDanceSoundFXFile12 = "res/Sounds/SFX/DanceSFX/mariodancepart12.wav";
		String marioDanceSoundFXFile13 = "res/Sounds/SFX/DanceSFX/mariodancepart13.wav";
		String marioDanceSoundFXFile14 = "res/Sounds/SFX/DanceSFX/mariodancepart14.wav";
		String marioDanceSoundFXFile15 = "res/Sounds/SFX/DanceSFX/mariodancepart15.wav";
		String marioDanceSoundFXFile16 = "res/Sounds/SFX/DanceSFX/mariodancepart16.wav";
		String marioDanceSoundFXFile17 = "res/Sounds/SFX/DanceSFX/mariodancepart17.wav";
		String marioDanceSoundFXFile18 = "res/Sounds/SFX/DanceSFX/mariodancepart18.wav";
		String marioDanceSoundFXFile19 = "res/Sounds/SFX/DanceSFX/mariodancepart19.wav";
		String marioDanceSoundFXFile20 = "res/Sounds/SFX/DanceSFX/mariodancepart20.wav";
		String marioDanceSoundFXFile21 = "res/Sounds/SFX/DanceSFX/mariodancepart21.wav";
		String marioDanceSoundFXFile22 = "res/Sounds/SFX/DanceSFX/mariodancepart22.wav";
		String marioDanceSoundFXFile23 = "res/Sounds/SFX/DanceSFX/mariodancepart23.wav";
		String marioDanceSoundFXFile24 = "res/Sounds/SFX/DanceSFX/mariodancepart24.wav";
		String marioDanceSoundFXFile25 = "res/Sounds/SFX/DanceSFX/mariodancepart25.wav";
		String marioDanceSoundFXFile26 = "res/Sounds/SFX/DanceSFX/mariodancepart26.wav";
		SoundLoops menuSoundLoop = new SoundLoops(menuAudioFile);
		SoundLoops menuSoundLoop2 = new SoundLoops(menuAudioFile2);
		SoundLoops gameSoundLoop = new SoundLoops(gameAudioFile);
		SoundLoops gameSoundLoop2 = new SoundLoops(gameAudioFile2);
		SoundLoops gameOverSoundLoop = new SoundLoops(gameOverAudioFile);
		SoundLoops marioStarSoundLoop = new SoundLoops(marioStarAudioFile);
		SoundLoops soundFXClip1SoundLoop = new SoundLoops(soundFXClip1);
		SoundLoops soundFXClip2SoundLoop = new SoundLoops(soundFXClip2);
		SoundLoops pauseSoundFXSoundLoop = new SoundLoops(pauseSoundFXFile);
		SoundLoops marioSpinningSoundLoop = new SoundLoops(marioSpinningFile);
		SoundLoops marioLetsGoSoundLoop = new SoundLoops(marioLetsGoFile);
		SoundLoops marioDanceSoundFXSoundLoop1 = new SoundLoops(marioDanceSoundFXFile1);
		SoundLoops marioDanceSoundFXSoundLoop2 = new SoundLoops(marioDanceSoundFXFile2);
		SoundLoops marioDanceSoundFXSoundLoop3 = new SoundLoops(marioDanceSoundFXFile3);
		SoundLoops marioDanceSoundFXSoundLoop4 = new SoundLoops(marioDanceSoundFXFile4);
		SoundLoops marioDanceSoundFXSoundLoop5 = new SoundLoops(marioDanceSoundFXFile5);
		SoundLoops marioDanceSoundFXSoundLoop6 = new SoundLoops(marioDanceSoundFXFile6);
		SoundLoops marioDanceSoundFXSoundLoop7 = new SoundLoops(marioDanceSoundFXFile7);
		SoundLoops marioDanceSoundFXSoundLoop8 = new SoundLoops(marioDanceSoundFXFile8);
		SoundLoops marioDanceSoundFXSoundLoop9 = new SoundLoops(marioDanceSoundFXFile9);
		SoundLoops marioDanceSoundFXSoundLoop10 = new SoundLoops(marioDanceSoundFXFile10);
		SoundLoops marioDanceSoundFXSoundLoop11 = new SoundLoops(marioDanceSoundFXFile11);
		SoundLoops marioDanceSoundFXSoundLoop12 = new SoundLoops(marioDanceSoundFXFile12);
		SoundLoops marioDanceSoundFXSoundLoop13 = new SoundLoops(marioDanceSoundFXFile13);
		SoundLoops marioDanceSoundFXSoundLoop14 = new SoundLoops(marioDanceSoundFXFile14);
		SoundLoops marioDanceSoundFXSoundLoop15 = new SoundLoops(marioDanceSoundFXFile15);
		SoundLoops marioDanceSoundFXSoundLoop16 = new SoundLoops(marioDanceSoundFXFile16);
		SoundLoops marioDanceSoundFXSoundLoop17 = new SoundLoops(marioDanceSoundFXFile17);
		SoundLoops marioDanceSoundFXSoundLoop18 = new SoundLoops(marioDanceSoundFXFile18);
		SoundLoops marioDanceSoundFXSoundLoop19 = new SoundLoops(marioDanceSoundFXFile19);
		SoundLoops marioDanceSoundFXSoundLoop20 = new SoundLoops(marioDanceSoundFXFile20);
		SoundLoops marioDanceSoundFXSoundLoop21 = new SoundLoops(marioDanceSoundFXFile21);
		SoundLoops marioDanceSoundFXSoundLoop22 = new SoundLoops(marioDanceSoundFXFile22);
		SoundLoops marioDanceSoundFXSoundLoop23 = new SoundLoops(marioDanceSoundFXFile23);
		SoundLoops marioDanceSoundFXSoundLoop24 = new SoundLoops(marioDanceSoundFXFile24);
		SoundLoops marioDanceSoundFXSoundLoop25 = new SoundLoops(marioDanceSoundFXFile25);
		SoundLoops marioDanceSoundFXSoundLoop26 = new SoundLoops(marioDanceSoundFXFile26);
		
		Game game = new Game();																						//Setting up Game
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.menuSoundLoops.add(menuSoundLoop);
		game.menuSoundLoops.add(menuSoundLoop2);
		game.gameSoundLoops.add(gameSoundLoop);
		game.gameSoundLoops.add(gameSoundLoop2);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop1);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop2);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop3);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop4);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop5);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop6);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop7);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop8);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop9);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop10);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop11);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop12);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop13);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop14);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop15);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop16);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop17);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop18);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop19);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop20);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop21);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop22);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop23);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop24);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop25);
		game.marioDanceSoundLoops.add(marioDanceSoundFXSoundLoop26);
		game.marioStarSoundLoop = marioStarSoundLoop;
		game.soundFXClip1SoundLoop = soundFXClip1SoundLoop;
		game.soundFXClip2SoundLoop = soundFXClip2SoundLoop;
		game.pauseSoundFXSoundLoop = pauseSoundFXSoundLoop;
		game.marioSpinningSoundLoop = marioSpinningSoundLoop;
		game.marioLetsGoSoundLoop = marioLetsGoSoundLoop;
		game.gameOverSoundLoop = gameOverSoundLoop;
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
		
	}
	
	public int playerX(){
		return (int)p.getX();
	}
	
	public int playerY(){
		return (int)p.getY();
	}
	
	public int animationTimer1(){
		return animationTimer1;
	}
	
	public boolean isPaused(){
		return paused;
	}
	
	public boolean soundFXisPlaying(){
		return soundFXisPlaying;
	}

	
	public boolean isMarioDancePosePause() {
		return marioDancePosePause;
	}

	public void setMarioDancePosePause(boolean marioDancePosePause) {
		this.marioDancePosePause = marioDancePosePause;
	}

	public long getMarioGrowthPosePauseTimer() {
		return marioGrowthPosePauseTimer;
	}

	public void setMarioGrowthPosePauseTimer(long marioGrowthPosePauseTimer) {
		this.marioGrowthPosePauseTimer = marioGrowthPosePauseTimer;
	}

	public boolean isMarioGrowthPosePause() {
		return marioGrowthPosePause;
	}

	public void setMarioGrowthPosePause(boolean marioGrowthPosePause) {
		this.marioGrowthPosePause = marioGrowthPosePause;
	}

	
	public BufferedImage getSpriteSheet(){
		return spriteSheet;
	}
	
	public BufferedImage getAnimatedStar(){
		return animatedStar;
	}
	
	public BufferedImage getAnimatedShootingStar(){
		return animatedShootingStar;
	}
	
	public BufferedImage getMario1StarSpriteSheet(){
		return mario1StarSpriteSheet;
	}
	
	public BufferedImage getBowserSpriteSheet(){
		return bowserSpriteSheet;
	}
	
	public BufferedImage getBulletBillSpriteSheet(){
		return bulletBillSpriteSheet;
	}
	
	public BufferedImage getMarioPlayerStarAnimations(){
		return marioPlayerStarAnimations;
	}
	
	public BufferedImage getMarioSlowingDownSprites(){
		return marioSlowingDownSprites;
	}

	public BufferedImage getFullMarioSpriteSheet(){
		return fullMarioSpriteSheet;
	}
	
	public BufferedImage getTransparentBlocks(){
		return transparentBlocks;
	}

	public void addEntity(EntityA block){
		ea.add(block);
	}
	
	public void removeEntity(EntityA block){
		ea.remove(block);
	}
	
	public void addEntity(EntityB block){
		eb.add(block);
	}
	
	public void removeEntity(EntityB block){
		eb.remove(block);
	}
	
	public void addEntity(EntityD block){
		ed.add(block);
	}
	
	public void removeEntity(EntityD block){
		ed.remove(block);
	}
}
