/*
 * SPACE FIGHTERS PROGRAM
 */
package com.game.src.main;
import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityD;
import com.game.src.main.classes.EntityE;
import com.game.src.main.libs.Animation;

import SwingFX.InteropSharedModelFrame;
import SwingFX.MenuMusicController;
import SwingFX.fxmlWindow;
import SwingFX.fxmlWindowFrame;

// TODO: Auto-generated Javadoc
/**
 * The Class Game.
 */
public class Game extends Canvas implements Runnable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant WIDTH. */
	public static final int WIDTH = 320;
	
	/** The Constant HEIGHT. */
	public static final int HEIGHT = WIDTH / 12 * 9;
	
	/** The Constant SCALE. */
	public static final int SCALE = 2;
	
	/** The Constant MARIO_WIDTH. */
	public static final int MARIO_WIDTH = 16;
	
	/** The Constant MARIO_HEIGHT. */
	public static final int MARIO_HEIGHT = 28;
	
	/** The title. */
	public final String TITLE = "Koopa's Invaders!";
	
	/** The running. */
	private boolean running = false;
	
	/** The paused. */
	private boolean paused = false;
	
	/** The thread. */
	private Thread thread;
	
	/** The image. */
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	/** The sprite sheet. */
	private BufferedImage spriteSheet = null;
	
	/** The animated star. */
	private BufferedImage animatedStar = null;
	
	/** The animated shooting star. */
	private BufferedImage animatedShootingStar = null;
	
	/** The mario 1 star sprite sheet. */
	private BufferedImage mario1StarSpriteSheet = null;
	
	/** The mario items sprite sheet. */
	private BufferedImage marioItemsSpriteSheet = null;
	
	/** The background. */
	private BufferedImage background = null;
	
	/** The mario lives. */
	private BufferedImage marioLives = null;
	
	/** The bowser sprite sheet. */
	private BufferedImage bowserSpriteSheet = null;
	
	/** The bullet bill sprite sheet. */
	private BufferedImage bulletBillSpriteSheet = null;
	
	/** The mario player star animations. */
	private BufferedImage marioPlayerStarAnimations = null;
	
	/** The mario item animation sheet. */
	private BufferedImage marioItemAnimationSheet = null;
	
	/** The mario item animation background sheet. */
	private BufferedImage marioItemAnimationBackgroundSheet = null;
	
	/** The big mario item animation sheet. */
	private BufferedImage bigMarioItemAnimationSheet = null;
	
	/** The current item img. */
	private BufferedImage currentItemImg = null;
	
	/** The chain chomp item getting bigger sheet. */
	private BufferedImage chainChompItemGettingBiggerSheet = null;
	
	/** The chain chomp sheet. */
	private BufferedImage chainChompSheet = null;
	
	/** The mario slowing down sprites. */
	private BufferedImage marioSlowingDownSprites = null;
	
	/** The full mario sprite sheet. */
	private BufferedImage fullMarioSpriteSheet = null;
	
	/** The mario advance sprite sheet. */
	private BufferedImage marioAdvanceSpriteSheet = null;
	
	/** The mario 3 font numbers small sprite sheet. */
	private BufferedImage mario3FontNumbersSmallSpriteSheet = null;
	
	/** The goomba death sprite sheet. */
	private BufferedImage goombaDeathSpriteSheet = null;
	
	/** The set score title. */
	private BufferedImage setScoreTitle = null;
	
	/** The leaderboard title. */
	private BufferedImage leaderboardTitle = null;
	
	/** The transparent blocks. */
	private BufferedImage transparentBlocks = null;
	
	/** The item background. */
	private ArrayList<BufferedImage> itemBackground = new ArrayList<BufferedImage>();
	
	/** The background traverse. */
	private int backgroundTraverse = 0;
	
	/** The star anim. */
	Animation starAnim;
	
	/** The shooting star anim. */
	Animation shootingStarAnim;
	
	/** The transparent blocks anim. */
	Animation transparentBlocksAnim;
	
	/** The current item. */
	Animation currentItem;
	
	/** The mario turning with item. */
	Animation marioTurningWithItem;
	
	/** The shooting star frame stop. */
	private boolean shootingStarFrameStop = false;
	
	/** The x L boolean. */
	private boolean xLBoolean = false;
	
	/** The x R boolean. */
	private boolean xRBoolean = false;
	
	/** The y U boolean. */
	private boolean yUBoolean = false;
	
	/** The y D boolean. */
	private boolean yDBoolean = false;
	
	/** The is shooting. */
	private boolean isShooting = false;
	
	/** The spawn done. */
	private boolean spawnDone = false;
	
	/** The spawn done 2. */
	private boolean spawnDone2 = false;
	
	/** The spawn done 3. */
	private boolean spawnDone3 = false;
	
	/** The spawn done 4. */
	private boolean spawnDone4 = false;
	
	/** The you won. */
	private boolean youWon = false;
	
	/** The mario has been invincible. */
	private boolean marioHasBeenInvincible = false;
	
	/** The my time. */
	private double myTime = 0.0;
	
	/** The item name. */
	private String itemName;
	
	/** The number of fire balls shot. */
	private int numberOfFireBallsShot = 0;
	
	/** The number of fire balls shot decoy. */
	private int numberOfFireBallsShotDecoy = 0;
	
	/** The volume slider double. */
	public double volumeSliderDouble = 0;
	
	/** The slowing down. */
	private double slowingDown = 0;
	
	/** The slowing down timer long. */
	private long slowingDownTimerLong = 0;
	
	/** The slowing down activatedl. */
	public boolean slowingDownActivatedl = false;
	
	/** The slowing down activatedr. */
	public boolean slowingDownActivatedr = false;
	
	/** The enemy hit right barrier. */
	public boolean enemyHitRightBarrier = false;
	
	/** The game over boolean. */
	public boolean gameOverBoolean = false;
	
	/** The game over sound boolean. */
	public boolean gameOverSoundBoolean = false;
	
	/** The enemy speed increase. */
	public double enemySpeedIncrease = 0.5;
	
	/** The animation timer 1. */
	public int animationTimer1 = 0;
	
	/** The running timer long. */
	private long runningTimerLong = 0;
	
	/** The running timer activated. */
	private boolean runningTimerActivated = false;
	
	/** The running timer activated response. */
	private boolean runningTimerActivatedResponse = false;
	
	/** The sound set. */
	private boolean soundSet = false;
	
	/** The sound timer set. */
	private boolean soundTimerSet = false;
	
	/** The sound FX timer. */
	private long soundFXTimer = 0;
	
	/** The enemy hit pause timer. */
	private long enemyHitPauseTimer = 0;
	
	/** The enemy hit pause boolean. */
	private boolean enemyHitPauseBoolean = false;
	
	/** The spawn item. */
	private boolean spawnItem = false;
	
	/** The transition timer. */
	private long transitionTimer = 0;
	
	/** The game start sound timer. */
	private long gameStartSoundTimer = 0;
	
	/** The pause sound FX timer. */
	private long pauseSoundFXTimer = 0;
	
	/** The visual pause timer. */
	private long visualPauseTimer = 0;
	
	/** The mario dance pose pause timer. */
	private long marioDancePosePauseTimer = 0;
	
	/** The mario growth pose pause timer. */
	private long marioGrowthPosePauseTimer = 0;
	
	/** The mario lets go pause timer. */
	private long marioLetsGoPauseTimer = 0;
	
	/** The item wait timer. */
	private long itemWaitTimer = 0;
	
	/** The item flying timer 1. */
	private long itemFlyingTimer1 = 0;
	
	/** The item flying away Y. */
	private double itemFlyingAwayY = 0;
	
	/** The item flying away X. */
	private double itemFlyingAwayX = 0;
	
	/** The file score written. */
	private boolean fileScoreWritten = false;
	
	/** The user has paused. */
	private boolean userHasPaused = false;
	
	/** The sound F xis playing. */
	private boolean soundFXisPlaying = false;
	
	/** The sound FX boolean. */
	private boolean soundFXBoolean = false;
	
	/** The sound FX clip 1 reset. */
	private boolean soundFXClip1Reset = false;
	
	/** The mario dance pose pause. */
	private boolean marioDancePosePause = false;
	
	/** The mario growth pose pause. */
	private boolean marioGrowthPosePause = false;
	
	/** The mario lets go pause. */
	private boolean marioLetsGoPause = false;
	
	/** The sound randomizer. */
	public int soundRandomizer = 0;
	
	/** The menu sound loop randomizer. */
	public int menuSoundLoopRandomizer = 0;
	
	/** The mario voice randomizer. */
	private int marioVoiceRandomizer = 0;
	
	/** The menu sound set. */
	private boolean menuSoundSet = false;
	
	/** The bullet bill death sound pause boolean. */
	private boolean bulletBillDeathSoundPauseBoolean = false;
	
	/** The goomba death sound pause boolean. */
	private boolean goombaDeathSoundPauseBoolean = false;
	
	/** The coin sound pause boolean. */
	private boolean coinSoundPauseBoolean = false;
	
	/** The star ding pause boolean. */
	private boolean starDingPauseBoolean = false;
	
	/** The goomba 3 death sound pause boolean. */
	private boolean goomba3DeathSoundPauseBoolean = false;
	
	/** The goomba 3 death smoke sound pause boolean. */
	private boolean goomba3DeathSmokeSoundPauseBoolean = false;
	
	/** The menu sound loops. */
	public LinkedList<SoundLoops> menuSoundLoops = new LinkedList<SoundLoops>();
	
	/** The game sound loops. */
	public LinkedList<SoundLoops> gameSoundLoops = new LinkedList<SoundLoops>();
	
	/** The mario dance sound loops. */
	LinkedList<SoundLoops> marioDanceSoundLoops = new LinkedList<SoundLoops>();
	
	/** The mario voices. */
	LinkedList<SoundLoops> marioVoices = new LinkedList<SoundLoops>();
	
	/** The bullet bill death sound loop. */
	LinkedList<SoundLoops> bulletBillDeathSoundLoop = new LinkedList<SoundLoops>();
	
	/** The goomba death sound loop. */
	LinkedList<SoundLoops> goombaDeathSoundLoop = new LinkedList<SoundLoops>();
	
	/** The coin sound loop. */
	LinkedList<SoundLoops> coinSoundLoop = new LinkedList<SoundLoops>();
	
	/** The star ding sound loop. */
	LinkedList<SoundLoops> starDingSoundLoop = new LinkedList<SoundLoops>();
	
	/** The goomba 3 death sound loop. */
	LinkedList<SoundLoops> goomba3DeathSoundLoop = new LinkedList<SoundLoops>();
	
	/** The goomba 3 death smoke sound loop. */
	LinkedList<SoundLoops> goomba3DeathSmokeSoundLoop = new LinkedList<SoundLoops>();
	
	/** The game over sound loop. */
	SoundLoops gameOverSoundLoop;
	
	/** The game over winning sound loop. */
	SoundLoops gameOverWinningSoundLoop;
	
	/** The game over iris sound loop. */
	SoundLoops gameOverIrisSoundLoop;
	
	/** The mario star sound loop. */
	SoundLoops marioStarSoundLoop;
	
	/** The sound FX clip 1 sound loop. */
	SoundLoops soundFXClip1SoundLoop;
	
	/** The sound FX clip 2 sound loop. */
	SoundLoops soundFXClip2SoundLoop;
	
	/** The pause sound FX sound loop. */
	SoundLoops pauseSoundFXSoundLoop;
	
	/** The mario spinning sound loop. */
	SoundLoops marioSpinningSoundLoop;
	
	/** The mario death sound loop. */
	SoundLoops marioDeathSoundLoop;
	
	/** The item swoosh sound loop. */
	SoundLoops itemSwooshSoundLoop;
	
	/** The p. */
	private Player p;
	
	/** The c. */
	private Controller c;
	
	/** The e. */
	private Enemy e;
	
	/** The tex. */
	private Textures tex;
	
	/** The hud. */
	private HUD hud;
	
	/** The menu. */
	private Menu menu;
	
	/** The game over. */
	private GameOver gameOver;
	
	/** The bb. */
	private BasicBlocks bb;													//BLOCKS
	
	/** The music menu frame. */
	private fxmlWindowFrame musicMenuFrame;
	
	/** The m. */
	private MenuMusicController m;
	
	/** The title. */
	private BufferedImage title = null;
	
	/** The game over title. */
	private BufferedImage gameOverTitle = null;
	
	/** The play title. */
	private BufferedImage playTitle = null;
	
	/** The help title. */
	private BufferedImage helpTitle = null;
	
	/** The exit title. */
	private BufferedImage exitTitle = null;
	
	/** The ea. */
	public LinkedList<EntityA> ea;
	
	/** The eb. */
	public LinkedList<EntityB> eb;
	
	/** The ec. */
	public LinkedList<EntityC> ec;
	
	/** The ed. */
	public LinkedList<EntityD> ed;
	
	/** The ee. */
	public LinkedList<EntityE> ee;
	
	/** The Health. */
	public static int Health = 100;
	
	/**
	 * The Enum STATE.
	 */
	public static enum STATE{
		
		/** The menu. */
		MENU,
		
		/** The transition entrance. */
		TRANSITION_ENTRANCE,
		
		/** The transition item. */
		TRANSITION_ITEM,
		
		/** The transition death. */
		TRANSITION_DEATH,
		
		/** The transition win. */
		TRANSITION_WIN,
		
		/** The game. */
		GAME,
		
		/** The pause. */
		PAUSE,
		
		/** The gameover. */
		GAMEOVER,
		
		/** The reset. */
		RESET
	};
	
	/** The State. */
	public static STATE State = STATE.MENU;
	
	/**
	 * Inits the.
	 */
	public void init(){
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			spriteSheet = loader.loadImage("/AnimationSpriteSheetNew.png");
			animatedStar = loader.loadImage("/animatedstar.png");
			animatedShootingStar = loader.loadImage("/shootingstarworadiant.png");
			mario1StarSpriteSheet = loader.loadImage("/mario1starspritesheet.png");
			marioItemsSpriteSheet = loader.loadImage("/marioItemssmaller.png");
			background = loader.loadImage("/starsbackgroundbigger.png");
			marioLives = loader.loadImage("/mariolivessprite.png");
			bowserSpriteSheet = loader.loadImage("/bowserspritesheet.png");
			bulletBillSpriteSheet = loader.loadImage("/bulletbillspritesheet.png");
			marioPlayerStarAnimations = loader.loadImage("/marioplayerstaranimations.png");
			marioItemAnimationSheet = loader.loadImage("/marioitemanimations.png");
			marioItemAnimationBackgroundSheet = loader.loadImage("/BackgroundBlur/starsbackgroundbiggerblur14.png");
			bigMarioItemAnimationSheet = loader.loadImage("/mario-big.png");
			chainChompItemGettingBiggerSheet = loader.loadImage("/Items/chainChompItemGettingBig.png");
			chainChompSheet = loader.loadImage("/Items/Chain_Chomp_spritesss.png");
			marioSlowingDownSprites = loader.loadImage("/firemarioslidingeffect.png");
			fullMarioSpriteSheet = loader.loadImage("/mario.png");
			marioAdvanceSpriteSheet = loader.loadImage("/marioadvancespritesheet.png");
			mario3FontNumbersSmallSpriteSheet = loader.loadImage("/mario3fonteNUMBERSSMALLERR.png");
			goombaDeathSpriteSheet = loader.loadImage("/goombadeath.png");
			title = loader.loadImage("/koopasinvaderstitlebigger.png");
			gameOverTitle = loader.loadImage("/gameover1bigger.png");
			playTitle = loader.loadImage("/newplaybutton.png");
			helpTitle = loader.loadImage("/newhelpbutton.png");
			exitTitle = loader.loadImage("/newexitbutton.png");
			setScoreTitle = loader.loadImage("/setScoreSmaller.png");
			leaderboardTitle = loader.loadImage("/leaderboardSmaller.png");
			transparentBlocks = loader.loadImage("/randomtransparentblocks.png");
			itemBackground = loader.loadImagesfromFolder("/res/BackgroundBlur");
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		tex = new Textures(this);
		bb = new BasicBlocks(tex,this);												//BLOCKS
		c = new Controller(tex, this);
		p = new Player(Game.WIDTH,(Game.HEIGHT * SCALE) - MARIO_HEIGHT,tex,this,c);
		hud = new HUD(tex,this);
		menu = new Menu();

		musicMenuFrame = new fxmlWindowFrame("Music Menu","musicMenu.fxml");
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		ec = c.getEntityC();
		ed = c.getEntityD();
		ee = c.getEntityE();
		
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput(this));
		
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
		
		marioTurningWithItem = new Animation(6, tex.marioItemAnimationBeginning[0],tex.marioItemAnimationBeginning[0],
				tex.marioItemAnimationBeginning[1],tex.marioItemAnimationBeginning[11],tex.marioItemAnimationBeginning[12],
				tex.marioItemAnimationBeginning[13],tex.marioItemAnimationBeginning[14],tex.marioItemAnimationBeginning[15],
				tex.marioItemAnimationBeginning[16],tex.marioItemAnimationBeginning[17]);

	}
	
	/**
	 * Start.
	 */
	private synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Stop.
	 */
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
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
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
	
	/**
	 * Tick.
	 */
	private void tick(){
		if(State == STATE.GAME){
			if(!paused){
				p.tick();
				hud.tick();
				c.tick();
				bb.tick();
			}
		}
		if(!(State == STATE.TRANSITION_ITEM))
			starAnim.runAnimation();
		transparentBlocksAnim.runAnimation();
		if((myTime / 10) == (int)(myTime/10) && shootingStarFrameStop == false)//(myTime > 10 && myTime < 22 || myTime > 30 && myTime < 42)
			shootingStarAnim.runAnimation();
		//System.out.println(m.getVolumeSliderDouble());
		//this.menuSoundLoops.get(this.menuSoundLoopRandomizer).setVolume((float)m.getVolumeSliderDouble());
		//System.out.println(this.menuSoundLoops.get(this.menuSoundLoopRandomizer).getVolume());
	}
	
	/**
	 * Render.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
		if(!(State == STATE.TRANSITION_ITEM))
			g.drawImage(background, 0, 0, null);
		else
			g.drawImage(marioItemAnimationBackgroundSheet, 0, 0, null);
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
			State = STATE.TRANSITION_DEATH;
		if (HUD.getTimer2() <= 0 && p.gameOver == false)
			State = STATE.TRANSITION_WIN;
		
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
				if(this.marioStarSoundLoop.getSoundLoopBoolean() == true){
					this.marioStarSoundLoop.stop();
					this.marioStarSoundLoop.setSoundLoopBoolean(false);
				}
				
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
				
				if(this.gameSoundLoops.get(this.soundRandomizer).clipIsActive())
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
					this.marioStarSoundLoop.setSoundLoopBoolean(true);
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
					this.marioStarSoundLoop.setSoundLoopBoolean(true);
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
			if(spawnItem == true){
				switch(this.itemName){
				case "chainChompItem":
					c.addEntity(new ChainChomp(p.getX(),p.getY()-50, tex, this));
					//spawnChainChomp
					break;
				default:
					break;
				}
				spawnItem = false;
			}
			if(System.currentTimeMillis() < enemyHitPauseTimer){
				if(this.gameSoundLoops.get(soundRandomizer).clipIsActive())
					this.gameSoundLoops.get(soundRandomizer).stop();
				paused = true;
				enemyHitPauseBoolean = true;
			}
			else if(enemyHitPauseBoolean == true){
				paused = false;
				enemyHitPauseBoolean = false;
				if(!this.gameSoundLoops.get(soundRandomizer).clipIsActive()){
					this.gameSoundLoops.get(this.soundRandomizer).setSoundLoopBoolean(true);
					this.gameSoundLoops.get(soundRandomizer).loop();
				}
			}
			//SPAWN ENEMIES
			/**/
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
			}
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
				if(eb.get(i).getEntityBDead() == false)
					c.addEntity(new GreenShell(eb.get(i).getX(),eb.get(i).getY() - 32,tex, this));
			}
			if(spawnDone4 == true){												//Spawning Bowser Mechanics
				hud.render(g);
				if((int)HUD.getTimer1() <= 0){
					//SPAWN BULLET BILLS
					Random rand = new Random();
					int i = rand.nextInt(20000);
					if(i == 1 && ec.size() < 6){
						c.addEntity(new BulletBill(eb.getLast().getX(),eb.getLast().getY() - 32,tex, this));
					}
					
					//SPAWN GREEN SHELLS
					if(numberOfFireBallsShot % 6==0){
						numberOfFireBallsShot += 1;
						numberOfFireBallsShotDecoy += 1;
						c.addEntity(new GreenShell(eb.getLast().getX()+32,eb.getLast().getY() - 32,tex, this));
					}
				}
			}
			//SPAWN STARS & ITEMS
			Random rand = new Random();
			int j = rand.nextInt(400000);//400000
			if(j < 2 && ed.size() < 1 && p.getMarioInvincible() == false && hud.getItemObtained() == false){
				int k = rand.nextInt(2);
				if(k == 0){
					if(j == 0)
						c.addEntity(new Mario1Star(-16,this.playerY() - 32,tex, this));
					else if(j == 1)
						c.addEntity(new ChainChompItem(-16,this.playerY() - 32,tex, this));
				}
				else{
					if(j == 0)
						c.addEntity(new Mario1Star((Game.WIDTH * 2) + 16,this.playerY() - 32,tex, this));
					else if(j == 1)
						c.addEntity(new ChainChompItem((Game.WIDTH * 2) + 16,this.playerY() - 32,tex, this));
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
				State = STATE.TRANSITION_DEATH;
			}
			hud.render(g);
		}else if(State == STATE.MENU){													//Menu
			//menu.render(g);

			if(menuSoundSet == false){
				Random rand = new Random();
				menuSoundLoopRandomizer = rand.nextInt(2);
				this.menuSoundLoops.get(menuSoundLoopRandomizer).loop();
				this.menuSoundLoops.get(menuSoundLoopRandomizer).setSoundLoopBoolean(true);
				menuSoundSet = true;
			}
			//this.menuSoundLoops.get(this.menuSoundLoopRandomizer).setVolume((float)this.m.getVolumeSliderDouble());
			//System.out.println(this.volumeSliderDouble);
			g.drawImage(title, 70, 100, null);
			g.drawImage(playTitle, Game.WIDTH / 2 + 120, 200, null);
			g.drawImage(helpTitle, Game.WIDTH / 2 + 120, 300, null);
			g.drawImage(exitTitle, Game.WIDTH / 2 + 120, 400, null);
		}else if (State == STATE.TRANSITION_ENTRANCE){
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
					Random rand = new Random();
					marioVoiceRandomizer = rand.nextInt(4);
					this.marioVoices.get(marioVoiceRandomizer).play();
					this.marioVoices.get(marioVoiceRandomizer).setSoundLoopBoolean(true);
					marioLetsGoPause = false;
				}
			}
		}else if(State == STATE.TRANSITION_ITEM){
			if(this.gameSoundLoops.get(this.soundRandomizer).clipIsActive()){
				this.gameSoundLoops.get(this.soundRandomizer).stop();
				this.gameSoundLoops.get(this.soundRandomizer).setSoundLoopBoolean(false);
				this.itemSwooshSoundLoop.play();
				marioTurningWithItem.nextFrame();
					switch(hud.getItemName()){
						case "chainChompItem":
							currentItem = new Animation(6,tex.bigChainChompItem[0],
									tex.bigChainChompItem[1],tex.bigChainChompItem[2],tex.bigChainChompItem[3],
									tex.bigChainChompItem[4],tex.bigChainChompItem[5],tex.bigChainChompItem[6],
									tex.bigChainChompItem[7],tex.bigChainChompItem[8]);
							currentItem.nextFrame();
							currentItemImg = tex.bigChainChompItem[0];
							//useChainChompAnimation
							break;
						default:
							break;
					}
			}
			if(backgroundTraverse < itemBackground.size()-1 && System.currentTimeMillis() % 80 == 0){
				backgroundTraverse++;
			}
			else if(itemWaitTimer == 0 && backgroundTraverse >= itemBackground.size()-1)
				itemWaitTimer = System.currentTimeMillis() + 400;
			if(backgroundTraverse < itemBackground.size())
				g.drawImage(itemBackground.get(backgroundTraverse), 0, 0, null);
			if(itemWaitTimer != 0 && marioTurningWithItem.getCount() < 10 && System.currentTimeMillis() % 16 == 0){
				marioTurningWithItem.runAnimation();
			}
			if(itemWaitTimer != 0 && marioVoices.get(0).getSoundLoopBoolean() == false){
				marioVoices.get(0).play();
				marioVoices.get(0).setSoundLoopBoolean(true);
			}
			if(itemWaitTimer != 0 && System.currentTimeMillis() < itemWaitTimer - 100){
				if(marioTurningWithItem.getCount()<4 && System.currentTimeMillis() % 4 == 0)
					itemFlyingAwayY -= .016;
				itemFlyingTimer1 = System.currentTimeMillis() + 300;
			}
			else if(System.currentTimeMillis() < itemFlyingTimer1){
				if(System.currentTimeMillis() % 2 == 0){
					itemFlyingAwayX += .12;
					itemFlyingAwayY += .1;
				}
				if(System.currentTimeMillis() % 3 == 0){
					itemFlyingAwayY += .1;
				}
			}
			else if(itemFlyingTimer1 != 0 && currentItemImg.getWidth() < 200){
				if(currentItem.getCount() < 9 && System.currentTimeMillis() % 35 == 0)
					currentItemImg = resize(currentItemImg,currentItemImg.getWidth()+20,currentItemImg.getHeight()+20);
					//currentItem.runAnimation();
				itemFlyingAwayX -= 0.8;
				itemFlyingAwayY -= 1;
				marioTurningWithItem.drawAnimation(g, Game.WIDTH, (Game.HEIGHT * Game.SCALE+120) - (backgroundTraverse * 22), 0);
				g.drawImage(currentItemImg ,(int)(Game.WIDTH+42 + itemFlyingAwayX), (int)((Game.HEIGHT * Game.SCALE+120) - (backgroundTraverse * 22)-19 + itemFlyingAwayY),null);
				//currentItem.drawAnimation(g, Game.WIDTH+42 + itemFlyingAwayX, (Game.HEIGHT * Game.SCALE+120) - (backgroundTraverse * 22)-19 + itemFlyingAwayY,0);
			}
			else if(currentItemImg.getWidth() >= 200){
				spawnItem = true;
				enemyHitPauseTimer = System.currentTimeMillis() + 800;
				backgroundTraverse = 0;
				itemWaitTimer = 0;
				itemFlyingTimer1 = 0;
				itemFlyingAwayX = 0;
				itemFlyingAwayY = 0;
				marioVoices.get(0).setSoundLoopBoolean(false);
				marioTurningWithItem.setCount(0);
				State = STATE.GAME;
			}
			
			if(itemFlyingTimer1 == 0 || System.currentTimeMillis() < itemFlyingTimer1){
				currentItem.drawAnimation(g, Game.WIDTH+42 + itemFlyingAwayX, (Game.HEIGHT * Game.SCALE+120) - (backgroundTraverse * 22)-19 + itemFlyingAwayY,0);
				marioTurningWithItem.drawAnimation(g, Game.WIDTH, (Game.HEIGHT * Game.SCALE+120) - (backgroundTraverse * 22), 0);
			}
			//else
				//g.drawImage(tex.marioItemAnimationBeginning[0], Game.WIDTH, (Game.HEIGHT * Game.SCALE+120) - ((backgroundTraverse-1) * 25), null);
		}else if(State == STATE.TRANSITION_DEATH){
			if(this.gameSoundLoops.get(this.soundRandomizer).getSoundLoopBoolean() == true){
				this.gameSoundLoops.get(this.soundRandomizer).stop();
				this.gameSoundLoops.get(this.soundRandomizer).setSoundLoopBoolean(false);
				soundSet = false;
			}
			
			if (paused == false)
				paused = true;
			
			if(marioDeathSoundLoop.getSoundLoopBoolean() == false){
				marioDeathSoundLoop.play();
				marioDeathSoundLoop.setSoundLoopBoolean(true);
			}
			
			if(marioDeathSoundLoop.soundPlaying() == false){
				marioDeathSoundLoop.setSoundLoopBoolean(false);
				State = STATE.GAMEOVER;
			}
			
			bb.draw(g2d);													//BLOCKS
			p.render(g);
			c.render(g);
		}else if(State == STATE.TRANSITION_WIN) {

			p.render(g);
			if(this.gameSoundLoops.get(this.soundRandomizer).getSoundLoopBoolean() == true){
				this.gameSoundLoops.get(this.soundRandomizer).stop();
				this.gameSoundLoops.get(this.soundRandomizer).setSoundLoopBoolean(false);
				soundSet = false;
			}
			if(this.gameOverIrisSoundLoop.getSoundLoopBoolean() == false) {
				this.gameOverIrisSoundLoop.play();
				this.gameOverIrisSoundLoop.setSoundLoopBoolean(true);
			}
			if(youWon == false)
				youWon = true;
			if(p.gameOver == true) {
				State = STATE.GAMEOVER;
				//p.setGameOver(false);
			}
		}else if (State == STATE.GAMEOVER){												//GameOver
			//wait a lil bit
			//bs.show();
			if(fileScoreWritten == false) {
				File file = new File("Score.txt");
				if(!file.exists())
					file.createNewFile();
				BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
				fileWriter.write(String.valueOf((int)this.getHUD().getScore()));
				fileWriter.flush();
				fileWriter.close();
				fileScoreWritten = true;
			}
			
			if(this.gameSoundLoops.get(this.soundRandomizer).getSoundLoopBoolean() == true){
				this.gameSoundLoops.get(this.soundRandomizer).stop();
				this.gameSoundLoops.get(this.soundRandomizer).setSoundLoopBoolean(false);
				soundSet = false;
			}
			
			if(youWon == false) {
				if(gameOverSoundBoolean == false){
					this.gameOverSoundLoop.play();
					gameOverSoundBoolean = true;
				}
			}
			else {
				if(gameOverSoundBoolean == false) {
					this.gameOverWinningSoundLoop.play();
					gameOverSoundBoolean = true;
				}
			}
			numberOfFireBallsShot = numberOfFireBallsShot - numberOfFireBallsShotDecoy;
			
			g.drawImage(gameOverTitle, 170, 100, null);									//Buttons
			g.drawImage(playTitle, Game.WIDTH / 2 + 120, 200, null);
			g.drawImage(helpTitle, Game.WIDTH / 2 + 120, 300, null);
			g.drawImage(exitTitle, Game.WIDTH / 2 + 120, 400, null);
			g.drawImage(setScoreTitle, 40, 20, null);
			g.drawImage(leaderboardTitle, Game.WIDTH /2 + 380, 20, null);
			//gameOver.render(g);
			
			//g.dispose();
			//g2d.dispose();
		}else if(State == STATE.RESET) {
			if(gameOverSoundLoop.clipIsActive())
				gameOverSoundLoop.stop();
			if(gameOverWinningSoundLoop.clipIsActive())
				gameOverWinningSoundLoop.stop();
			for(int i = 0; i < this.marioDanceSoundLoops.size(); i++) {
				marioDanceSoundLoops.get(i).setSoundLoopBoolean(false);
				marioDanceSoundLoops.get(i).setFramePosition(0);
			}
			for(int i = 0; i < this.marioVoices.size(); i++) {
				marioVoices.get(i).setSoundLoopBoolean(false);
				marioVoices.get(i).setFramePosition(0);
			}
			for(int i = 0; i < this.gameSoundLoops.size(); i++) {
				gameSoundLoops.get(i).setSoundLoopBoolean(false);
				gameSoundLoops.get(i).setFramePosition(0);
			}
			this.marioHasBeenInvincible = false;
			this.pauseSoundFXTimer = 0;
			this.soundFXBoolean = false;
			this.soundFXClip1Reset = false;
			this.soundFXClip1SoundLoop.setFramePosition(0);
			this.soundFXClip1SoundLoop.setSoundLoopBoolean(false);
			this.soundFXClip2SoundLoop.setFramePosition(0);
			this.soundFXClip2SoundLoop.setSoundLoopBoolean(false);
			this.soundFXisPlaying = false;
			this.slowingDownTimerLong = 0;
			this.animationTimer1 = 0;
			this.enemyHitPauseTimer = 0;
			this.marioLetsGoPauseTimer = 0;
			this.pauseSoundFXTimer = 0;
			this.visualPauseTimer = 0;
			this.enemyHitPauseBoolean = false;
			this.paused = false;
			this.userHasPaused = false;
			this.enemyHitRightBarrier = false;
			this.enemySpeedIncrease = 0.5;
			spawnDone = false;
			spawnDone2 = false;
			spawnDone3 = false;
			spawnDone4 = false;
			gameOverSoundBoolean = false;
			gameOverBoolean = false;
			Health = 100;
			this.gameOverIrisSoundLoop.setSoundLoopBoolean(false);
			this.marioSpinningSoundLoop.setSoundLoopBoolean(false);
			this.marioGrowthPosePause = false;
			this.marioGrowthPosePauseTimer = 0;
			this.marioDancePosePause = false;
			this.marioDancePosePauseTimer = 0;
			this.getHUD().setScore(0);
			this.getHUD().HEALTH = 100;
			this.getHUD().setTimer1(100);
			this.getHUD().setTimer2(100);
			p.setX(Game.WIDTH);
			p.setY((Game.HEIGHT * SCALE) - MARIO_HEIGHT);
			p.gameOver = false;
			p.playerWinSetup = false;
			p.playerWinTimer = 0;
			p.setDanceProgressionCount(0);
			p.setDancingInProgress(false);
			p.setGrowingAnimationFinished(false);
			p.setSpinningAnimationFinished(false);
			p.setDancingAnimationFinished(false);
			p.marioEntranceDancingAnim.setCount(0);
			p.marioEntranceGrowingAnim.setCount(0);
			p.marioEntranceSpinningAnim.setCount(0);
			p.marioEntranceTurningAroundAnim.setCount(0);
			bb.reset();
			paused = false;
			tex = new Textures(this);
			bb = new BasicBlocks(tex,this);												//BLOCKS
			c = new Controller(tex, this);
			p = new Player(Game.WIDTH,(Game.HEIGHT * SCALE) - MARIO_HEIGHT,tex,this,c);
			hud = new HUD(tex,this);
			menu = new Menu();

			musicMenuFrame = new fxmlWindowFrame("Music Menu","musicMenu.fxml");
			
			ea = c.getEntityA();
			eb = c.getEntityB();
			ec = c.getEntityC();
			ed = c.getEntityD();
			ee = c.getEntityE();
			State = STATE.TRANSITION_ENTRANCE;
		}
		
		//**************DRAW**************//
		g.dispose();
		bs.show();
	}
	
	/**
	 * Key pressed.
	 *
	 * @param e the e
	 */
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
	
	/**
	 * Key released.
	 *
	 * @param e the e
	 */
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if(State == STATE.GAME){
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
			} else if(key == KeyEvent.VK_E && !paused){
				if(hud.getItemObtained() == true){
					/*
					switch(hud.getItemName()){
						case "chainChompItem":
							useChainChompAnimation
							break;
						default:
							break;
					}DO LATER IN TRANSITION*/
					State = STATE.TRANSITION_ITEM;
					this.itemName = hud.getItemName();
					hud.setItemObtained(false);
				}
			}
		}
	}
	
	/**
	 * Common resize.
	 *
	 * @param source the source
	 * @param width the width
	 * @param height the height
	 * @param hint the hint
	 * @return the buffered image
	 */
	private static BufferedImage commonResize(BufferedImage source,
            int width, int height, Object hint) {
        BufferedImage img = new BufferedImage(width, height,
                source.getType());
        Graphics2D g = img.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g.drawImage(source, 0, 0, width, height, null);
        } finally {
            g.dispose();
        }
        return img;
    }
	
	 /**
 	 * Resize.
 	 *
 	 * @param source the source
 	 * @param width the width
 	 * @param height the height
 	 * @return the buffered image
 	 */
 	public BufferedImage resize(BufferedImage source,
             int width, int height) {
         return commonResize(source, width, height,
                 RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
     }
	 
	 /**
 	 * Show GUI.
 	 *
 	 * @param title the title
 	 * @param fxmlFile the fxml file
 	 */
 	public static void showGUI(String title, String fxmlFile){
		 InteropSharedModelFrame frame = new InteropSharedModelFrame(title,fxmlFile);
	     frame.setVisible(true);
	 }
	 
	 /**
 	 * Show FXML window.
 	 *
 	 * @param title the title
 	 * @param fxmlFile the fxml file
 	 */
 	public static void showFXMLWindow(String title, String fxmlFile){
		 fxmlWindowFrame frame = new fxmlWindowFrame(title,fxmlFile);
	     frame.setVisible(true);
	 }
	
	 /**
 	 * Use music menu FXML window.
 	 */
 	public void useMusicMenuFXMLWindow(){
		 this.musicMenuFrame.setVisible(true);
	 }
	 
	/**
	 * Clamp.
	 *
	 * @param var the var
	 * @param min the min
	 * @param max the max
	 * @return the float
	 */
	public static float clamp(float var, float min, float max){
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String args[])
		throws Exception{
		String gameAudioFile = "res/Sounds/Music/mario1remix.wav";													//Loading in Music
		String gameAudioFile2 = "res/Sounds/Music/marioremixbitch1c.wav";
		String menuAudioFile = "res/Sounds/Music/supermarioworldremix1.wav";
		String menuAudioFile2 = "res/Sounds/Music/menuloop2.wav";
		String gameOverAudioFile = "res/Sounds/SFX/smw_game_over.wav";
		String gameOverWinningAudioFile = "res/Sounds/SFX/smb_stage_clear.wav";
		String gameOverIrisAudioFile = "res/Sounds/SFX/smw_goal_iris-out.wav";
		String marioStarAudioFile = "res/Sounds/SFX/mariowhistle.wav";
		String soundFXClip1 = "res/Sounds/SFX/riseupacoustic1cWAVE.wav";
		String soundFXClip2 = "res/Sounds/SFX/MariopowerupSFX.wav";
		String pauseSoundFXFile = "res/Sounds/SFX/smb_pause.wav";
		String marioSpinningFile = "res/Sounds/SFX/smw_feather_get.wav";
		String marioDeathFile = "res/Sounds/SFX/smb3_player_down.wav";
		String itemSwooshFile = "res/Sounds/SFX/spacewhooshsfxMARIO.wav";
		String marioVoiceLetsGoFile = "res/Sounds/SFX/MarioVoice/mk64_mario02.wav";
		String marioVoiceHereWeGoFile = "res/Sounds/SFX/MarioVoice/ssbm_dr_mario_33_mario_27.wav";
		String marioVoiceYelpFile = "res/Sounds/SFX/MarioVoice/ssbm_dr_mario_22_mario_16.wav";
		String marioVoiceWoohooFile = "res/Sounds/SFX/MarioVoice/ssbm_dr_mario_26_mario_20.wav";
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
		SoundLoops gameOverWinningSoundLoop = new SoundLoops(gameOverWinningAudioFile);
		SoundLoops gameOverIrisSoundLoop = new SoundLoops(gameOverIrisAudioFile);
		SoundLoops marioStarSoundLoop = new SoundLoops(marioStarAudioFile);
		SoundLoops soundFXClip1SoundLoop = new SoundLoops(soundFXClip1);
		SoundLoops soundFXClip2SoundLoop = new SoundLoops(soundFXClip2);
		SoundLoops pauseSoundFXSoundLoop = new SoundLoops(pauseSoundFXFile);
		SoundLoops marioSpinningSoundLoop = new SoundLoops(marioSpinningFile);
		SoundLoops marioDeathSoundLoop = new SoundLoops(marioDeathFile);
		SoundLoops itemSwooshSoundLoop = new SoundLoops(itemSwooshFile);
		SoundLoops marioVoiceLetsGoSoundLoop = new SoundLoops(marioVoiceLetsGoFile);
		SoundLoops marioVoiceHereWeGoSoundLoop = new SoundLoops(marioVoiceHereWeGoFile);
		SoundLoops marioVoiceYelpSoundLoop = new SoundLoops(marioVoiceYelpFile);
		SoundLoops marioVoiceWoohooSoundLoop = new SoundLoops(marioVoiceWoohooFile);
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
		game.marioVoices.add(marioVoiceLetsGoSoundLoop);
		game.marioVoices.add(marioVoiceHereWeGoSoundLoop);
		game.marioVoices.add(marioVoiceYelpSoundLoop);
		game.marioVoices.add(marioVoiceWoohooSoundLoop);
		game.marioStarSoundLoop = marioStarSoundLoop;
		game.soundFXClip1SoundLoop = soundFXClip1SoundLoop;
		game.soundFXClip2SoundLoop = soundFXClip2SoundLoop;
		game.pauseSoundFXSoundLoop = pauseSoundFXSoundLoop;
		game.marioSpinningSoundLoop = marioSpinningSoundLoop;
		game.marioDeathSoundLoop = marioDeathSoundLoop;
		game.itemSwooshSoundLoop = itemSwooshSoundLoop;
		game.gameOverSoundLoop = gameOverSoundLoop;
		game.gameOverWinningSoundLoop = gameOverWinningSoundLoop;
		game.gameOverIrisSoundLoop = gameOverIrisSoundLoop;

        //SwingUtilities.invokeLater(Game::showGUI);   //USE TO BRING UP NEW JAVAFX WINDOW
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
		
	}
	
	/**
	 * Player X.
	 *
	 * @return the int
	 */
	public int playerX(){
		return (int)p.getX();
	}
	
	/**
	 * Player Y.
	 *
	 * @return the int
	 */
	public int playerY(){
		return (int)p.getY();
	}
	
	/**
	 * Animation timer 1.
	 *
	 * @return the int
	 */
	public int animationTimer1(){
		return animationTimer1;
	}
	
	/**
	 * Checks if is paused.
	 *
	 * @return true, if is paused
	 */
	public boolean isPaused(){
		return paused;
	}
	
	/**
	 * Gets the user has paused.
	 *
	 * @return the user has paused
	 */
	public boolean getUserHasPaused() {
		return userHasPaused;
	}

	/**
	 * Sets the user has paused.
	 *
	 * @param userHasPaused the new user has paused
	 */
	public void setUserHasPaused(boolean userHasPaused) {
		this.userHasPaused = userHasPaused;
	}

	
	/**
	 * Sound F xis playing.
	 *
	 * @return true, if successful
	 */
	public boolean soundFXisPlaying(){
		return soundFXisPlaying;
	}

	/**
	 * Checks if is mario invincible.
	 *
	 * @return true, if is mario invincible
	 */
	public boolean isMarioInvincible(){
		return p.getMarioInvincible();
	}
	
	/**
	 * Gets the pause sound FX timer.
	 *
	 * @return the pause sound FX timer
	 */
	public long getPauseSoundFXTimer() {
		return pauseSoundFXTimer;
	}

	/**
	 * Sets the pause sound FX timer.
	 *
	 * @param pauseSoundFXTimer the new pause sound FX timer
	 */
	public void setPauseSoundFXTimer(long pauseSoundFXTimer) {
		this.pauseSoundFXTimer = pauseSoundFXTimer;
	}
	
	/**
	 * Gets the visual pause timer.
	 *
	 * @return the visual pause timer
	 */
	public long getVisualPauseTimer() {
		return visualPauseTimer;
	}

	/**
	 * Sets the visual pause timer.
	 *
	 * @param visualPauseTimer the new visual pause timer
	 */
	public void setVisualPauseTimer(long visualPauseTimer) {
		this.visualPauseTimer = visualPauseTimer;
	}

	
	/**
	 * Gets the enemy hit pause timer.
	 *
	 * @return the enemy hit pause timer
	 */
	public long getEnemyHitPauseTimer() {
		return enemyHitPauseTimer;
	}

	/**
	 * Sets the enemy hit pause timer.
	 *
	 * @param enemyHitPauseTimer the new enemy hit pause timer
	 */
	public void setEnemyHitPauseTimer(long enemyHitPauseTimer) {
		this.enemyHitPauseTimer = enemyHitPauseTimer;
	}

	/**
	 * Gets the spawn done 4.
	 *
	 * @return the spawn done 4
	 */
	public boolean getSpawnDone4(){
		return spawnDone4;
	}
	
	/**
	 * Gets the mario dance pose pause.
	 *
	 * @return the mario dance pose pause
	 */
	public boolean getMarioDancePosePause() {
		return marioDancePosePause;
	}

	/**
	 * Sets the mario dance pose pause.
	 *
	 * @param marioDancePosePause the new mario dance pose pause
	 */
	public void setMarioDancePosePause(boolean marioDancePosePause) {
		this.marioDancePosePause = marioDancePosePause;
	}

	/**
	 * Gets the mario growth pose pause timer.
	 *
	 * @return the mario growth pose pause timer
	 */
	public long getMarioGrowthPosePauseTimer() {
		return marioGrowthPosePauseTimer;
	}

	/**
	 * Sets the mario growth pose pause timer.
	 *
	 * @param marioGrowthPosePauseTimer the new mario growth pose pause timer
	 */
	public void setMarioGrowthPosePauseTimer(long marioGrowthPosePauseTimer) {
		this.marioGrowthPosePauseTimer = marioGrowthPosePauseTimer;
	}

	/**
	 * Gets the mario growth pose pause.
	 *
	 * @return the mario growth pose pause
	 */
	public boolean getMarioGrowthPosePause() {
		return marioGrowthPosePause;
	}

	/**
	 * Sets the mario growth pose pause.
	 *
	 * @param marioGrowthPosePause the new mario growth pose pause
	 */
	public void setMarioGrowthPosePause(boolean marioGrowthPosePause) {
		this.marioGrowthPosePause = marioGrowthPosePause;
	}

	/**
	 * Gets the bullet bill death sound pause boolean.
	 *
	 * @return the bullet bill death sound pause boolean
	 */
	public boolean getBulletBillDeathSoundPauseBoolean() {
		return bulletBillDeathSoundPauseBoolean;
	}

	/**
	 * Sets the bullet bill death sound pause boolean.
	 *
	 * @param bulletBillDeathSoundPauseBoolean the new bullet bill death sound pause boolean
	 */
	public void setBulletBillDeathSoundPauseBoolean(boolean bulletBillDeathSoundPauseBoolean) {
		this.bulletBillDeathSoundPauseBoolean = bulletBillDeathSoundPauseBoolean;
	}

	/**
	 * Gets the goomba death sound pause boolean.
	 *
	 * @return the goomba death sound pause boolean
	 */
	public boolean getGoombaDeathSoundPauseBoolean() {
		return goombaDeathSoundPauseBoolean;
	}

	/**
	 * Sets the goomba death sound pause boolean.
	 *
	 * @param goombaDeathSoundPauseBoolean the new goomba death sound pause boolean
	 */
	public void setGoombaDeathSoundPauseBoolean(boolean goombaDeathSoundPauseBoolean) {
		this.goombaDeathSoundPauseBoolean = goombaDeathSoundPauseBoolean;
	}
	
	/**
	 * Gets the coin sound pause boolean.
	 *
	 * @return the coin sound pause boolean
	 */
	public boolean getCoinSoundPauseBoolean() {
		return coinSoundPauseBoolean;
	}

	/**
	 * Sets the coin sound pause boolean.
	 *
	 * @param coinSoundPauseBoolean the new coin sound pause boolean
	 */
	public void setCoinSoundPauseBoolean(boolean coinSoundPauseBoolean) {
		this.coinSoundPauseBoolean = coinSoundPauseBoolean;
	}

	/**
	 * Gets the star ding pause boolean.
	 *
	 * @return the star ding pause boolean
	 */
	public boolean getStarDingPauseBoolean() {
		return starDingPauseBoolean;
	}

	/**
	 * Sets the star ding pause boolean.
	 *
	 * @param starDingPauseBoolean the new star ding pause boolean
	 */
	public void setStarDingPauseBoolean(boolean starDingPauseBoolean) {
		this.starDingPauseBoolean = starDingPauseBoolean;
	}

	/**
	 * Gets the goomba 3 death sound pause boolean.
	 *
	 * @return the goomba 3 death sound pause boolean
	 */
	public boolean getGoomba3DeathSoundPauseBoolean() {
		return goomba3DeathSoundPauseBoolean;
	}

	/**
	 * Sets the goomba 3 death sound pause boolean.
	 *
	 * @param goomba3DeathSoundPauseBoolean the new goomba 3 death sound pause boolean
	 */
	public void setGoomba3DeathSoundPauseBoolean(boolean goomba3DeathSoundPauseBoolean) {
		this.goomba3DeathSoundPauseBoolean = goomba3DeathSoundPauseBoolean;
	}

	/**
	 * Gets the goomba 3 death smoke sound pause boolean.
	 *
	 * @return the goomba 3 death smoke sound pause boolean
	 */
	public boolean getGoomba3DeathSmokeSoundPauseBoolean() {
		return goomba3DeathSmokeSoundPauseBoolean;
	}

	/**
	 * Sets the goomba 3 death smoke sound pause boolean.
	 *
	 * @param goomba3DeathSmokeSoundPauseBoolean the new goomba 3 death smoke sound pause boolean
	 */
	public void setGoomba3DeathSmokeSoundPauseBoolean(boolean goomba3DeathSmokeSoundPauseBoolean) {
		this.goomba3DeathSmokeSoundPauseBoolean = goomba3DeathSmokeSoundPauseBoolean;
	}

	/**
	 * Gets the bb.
	 *
	 * @return the bb
	 */
	public BasicBlocks getBb() {
		return bb;
	}

	/**
	 * Sets the bb.
	 *
	 * @param bb the new bb
	 */
	public void setBb(BasicBlocks bb) {
		this.bb = bb;
	}

	/**
	 * Gets the hud.
	 *
	 * @return the hud
	 */
	public HUD getHUD(){
		return this.hud;
	}
	
	/**
	 * Gets the sprite sheet.
	 *
	 * @return the sprite sheet
	 */
	public BufferedImage getSpriteSheet(){
		return spriteSheet;
	}
	
	/**
	 * Gets the animated star.
	 *
	 * @return the animated star
	 */
	public BufferedImage getAnimatedStar(){
		return animatedStar;
	}
	
	/**
	 * Gets the animated shooting star.
	 *
	 * @return the animated shooting star
	 */
	public BufferedImage getAnimatedShootingStar(){
		return animatedShootingStar;
	}
	
	/**
	 * Gets the mario 1 star sprite sheet.
	 *
	 * @return the mario 1 star sprite sheet
	 */
	public BufferedImage getMario1StarSpriteSheet(){
		return mario1StarSpriteSheet;
	}
	
	/**
	 * Gets the mario items sprite sheet.
	 *
	 * @return the mario items sprite sheet
	 */
	public BufferedImage getMarioItemsSpriteSheet(){
		return marioItemsSpriteSheet;
	}
	
	/**
	 * Gets the big mario item animation sheet.
	 *
	 * @return the big mario item animation sheet
	 */
	public BufferedImage getBigMarioItemAnimationSheet(){
		return bigMarioItemAnimationSheet;
	}
	
	/**
	 * Gets the chain chomp item getting bigger sheet.
	 *
	 * @return the chain chomp item getting bigger sheet
	 */
	public BufferedImage getChainChompItemGettingBiggerSheet(){
		return chainChompItemGettingBiggerSheet;
	}
	
	/**
	 * Gets the chain chomp sheet.
	 *
	 * @return the chain chomp sheet
	 */
	public BufferedImage getChainChompSheet(){
		return chainChompSheet;
	}
	
	/**
	 * Gets the bowser sprite sheet.
	 *
	 * @return the bowser sprite sheet
	 */
	public BufferedImage getBowserSpriteSheet(){
		return bowserSpriteSheet;
	}
	
	/**
	 * Gets the bullet bill sprite sheet.
	 *
	 * @return the bullet bill sprite sheet
	 */
	public BufferedImage getBulletBillSpriteSheet(){
		return bulletBillSpriteSheet;
	}
	
	/**
	 * Gets the mario player star animations.
	 *
	 * @return the mario player star animations
	 */
	public BufferedImage getMarioPlayerStarAnimations(){
		return marioPlayerStarAnimations;
	}
	
	/**
	 * Gets the mario item animation sheet.
	 *
	 * @return the mario item animation sheet
	 */
	public BufferedImage getMarioItemAnimationSheet(){
		return marioItemAnimationSheet;
	}
	
	/**
	 * Gets the mario slowing down sprites.
	 *
	 * @return the mario slowing down sprites
	 */
	public BufferedImage getMarioSlowingDownSprites(){
		return marioSlowingDownSprites;
	}

	/**
	 * Gets the full mario sprite sheet.
	 *
	 * @return the full mario sprite sheet
	 */
	public BufferedImage getFullMarioSpriteSheet(){
		return fullMarioSpriteSheet;
	}

	/**
	 * Gets the mario advance sprite sheet.
	 *
	 * @return the mario advance sprite sheet
	 */
	public BufferedImage getMarioAdvanceSpriteSheet() {
		return marioAdvanceSpriteSheet;
	}
	
	/**
	 * Gets the mario 3 font numbers small sprite sheet.
	 *
	 * @return the mario 3 font numbers small sprite sheet
	 */
	public BufferedImage getMario3FontNumbersSmallSpriteSheet() {
		return mario3FontNumbersSmallSpriteSheet;
	}
	
	/**
	 * Gets the goomba death sprite sheet.
	 *
	 * @return the goomba death sprite sheet
	 */
	public BufferedImage getGoombaDeathSpriteSheet() {
		return goombaDeathSpriteSheet;
	}
	
	/**
	 * Gets the transparent blocks.
	 *
	 * @return the transparent blocks
	 */
	public BufferedImage getTransparentBlocks(){
		return transparentBlocks;
	}

	/**
	 * Adds the entity.
	 *
	 * @param block the block
	 */
	public void addEntity(EntityA block){
		ea.add(block);
	}
	
	/**
	 * Removes the entity.
	 *
	 * @param block the block
	 */
	public void removeEntity(EntityA block){
		ea.remove(block);
	}
	
	/**
	 * Adds the entity.
	 *
	 * @param block the block
	 */
	public void addEntity(EntityB block){
		eb.add(block);
	}
	
	/**
	 * Removes the entity.
	 *
	 * @param block the block
	 */
	public void removeEntity(EntityB block){
		eb.remove(block);
	}
	
	/**
	 * Adds the entity.
	 *
	 * @param block the block
	 */
	public void addEntity(EntityD block){
		ed.add(block);
	}
	
	/**
	 * Removes the entity.
	 *
	 * @param block the block
	 */
	public void removeEntity(EntityD block){
		ed.remove(block);
	}
}
