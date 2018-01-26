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

import sun.audio.AudioStream;

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
	private BufferedImage transparentBlocks = null;
	
	Animation starAnim;
	Animation shootingStarAnim;
	Animation transparentBlocksAnim;
	
	private boolean shootingStarFrameStop = false;
	private boolean shootingStarActivation = false;
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
	private int slowingDownTimer = 0;
	public boolean slowingDownActivatedl = false;
	public boolean slowingDownActivatedr = false;
	public boolean enemyHitRightBarrier = false;
	public boolean gameOverBoolean = false;
	public boolean gameOverSoundBoolean = false;
	public double enemySpeedIncrease = 0.5;
	public int animationTimer1 = 0;
	public int runningTimer = 0;
	public boolean runningTimerActivated = false;
	private boolean gameSoundLoopBoolean = false;
	private boolean gameSoundLoopBoolean2 = false;
	private boolean soundSet = false;
	private boolean soundTimerSet = false;
	private double soundTimer = 0;
	private double soundStartTime = 0;
	private long soundFXTimer = 0;
	private boolean soundFXBoolean = false;
	private boolean soundFXClip1Reset = false;
	private int soundRandomizer = 0;
	private int menuSoundLoopRandomizer = 0;
	private boolean menuSoundLoopBoolean = false;
	private boolean menuSoundLoopBoolean2 = false;
	private boolean menuSoundSet = false;
	
	AudioStream gameAudioStream;
	SoundLoops menuSoundLoop;
	SoundLoops menuSoundLoop2;
	SoundLoops gameSoundLoop;
	SoundLoops gameSoundLoop2;
	SoundLoops gameOverSoundLoop;
	SoundLoops marioStarSoundLoop;
	SoundLoops soundFXClip1SoundLoop;
	SoundLoops soundFXClip2SoundLoop;
	Clip menuLoop;
	private Player p;
	private Controller c;
	private Enemy e;
	private Textures tex;
	private HUD hud;
	private Menu menu;
	private GameOver gameOver;
	private SoundEffect soundEffect;
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
		//SoundEffect.init();
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
				soundTimer--;
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
				if(menuSoundLoopBoolean == true){
					this.menuSoundLoop.stop();
					menuSoundLoopBoolean = false;
				}
				else if(menuSoundLoopBoolean2 == true){
					this.menuSoundLoop2.stop();
					menuSoundLoopBoolean2 = false;
				}
				//REPEAT THIS AT THE END OF STATE.GAME
				if(soundSet == false){	
					Random rand = new Random();
					soundRandomizer = rand.nextInt(3);
					if (soundRandomizer == 0){
						this.gameSoundLoop.play();
						this.gameSoundLoop.loop();
						gameSoundLoopBoolean = true;
						soundTimer = 87;
						soundSet = true;
						soundStartTime = myTime;
						//set up timer
					}
					else{
						this.gameSoundLoop2.play();
						this.gameSoundLoop2.loop();
						gameSoundLoopBoolean2 = true;
						soundTimer = 109;
						soundSet = true;
						soundStartTime = myTime;
						//set up timer
					}
				}//REPEAT THIS AT THE END OF STATE.GAME
			}
			if(p.getMarioInvincible() == false && marioHasBeenInvincible == true){		//Setting up SoundFX in between Audio Clips
				this.marioStarSoundLoop.stop();
				
				if(soundFXBoolean == true){
					paused = true;
					soundFXTimer = System.currentTimeMillis() + 500;
					soundTimer = soundTimer + 0.50;
					soundFXBoolean = false;
				}

				if(System.currentTimeMillis() > soundFXTimer)							//checking if paused for soundFX Timer
					paused = false;
				
				if(!paused){
					this.soundFXClip1SoundLoop.stop();
					soundFXClip1Reset = false;
					if(gameSoundLoopBoolean == true)
						this.gameSoundLoop.loop();
					if(gameSoundLoopBoolean2 == true)
						this.gameSoundLoop2.loop();
					if(soundTimerSet == true)
						soundTimerSet = false;
			}
				else if(paused == true && soundTimerSet == true){
					transparentBlocksAnim.drawAnimation(g,p.getX(), p.getY(), 0);
					//add visual effect
					if(!soundFXClip1Reset){
						this.soundFXClip1SoundLoop.play();
						soundFXClip1Reset = true;
					}
					this.soundFXClip1SoundLoop.loop();
				}
			}
			if(p.getMarioInvincible() == true){											//Setting up Star Sound
				
				if(gameSoundLoopBoolean == true)
					this.gameSoundLoop.stop();
				if(gameSoundLoopBoolean2 == true)
					this.gameSoundLoop2.stop();
				
				if(soundFXBoolean == false){
					paused = true;
					soundFXTimer = System.currentTimeMillis() + 990;
					soundTimer = soundTimer + 0.99;
					soundFXBoolean = true;
				}

				if(System.currentTimeMillis() > soundFXTimer)							//checking if paused for soundFX Timer
					paused = false;
				if(!paused){
					this.soundFXClip2SoundLoop.stop();
					soundFXClip1Reset = false;
					this.marioStarSoundLoop.loop();
					if (soundTimerSet == false){
						soundTimer += 11;
						soundTimerSet = true;
					}
					marioHasBeenInvincible = true;
				}

				else if(paused == true && soundTimerSet == false){
					transparentBlocksAnim.drawAnimation(g,p.getX(), p.getY(), 0);
					//add visual effect
					if(!soundFXClip1Reset){
						this.soundFXClip2SoundLoop.play();
						soundFXClip1Reset = true;
					}
					this.soundFXClip2SoundLoop.loop();
				}
			}
			//System.out.println(soundTimer);
			if(soundTimer <= 0){														//Setting up next song to play after a song ends
				if(gameSoundLoopBoolean == true){
					this.gameSoundLoop.stop();
					gameSoundLoopBoolean = false;
				}
				if(gameSoundLoopBoolean2 == true){
					this.gameSoundLoop2.stop();
					gameSoundLoopBoolean2 = false;
				}
				soundSet = false;
				if(soundSet == false){	
					Random rand = new Random();
					soundRandomizer = rand.nextInt(3);
					if (soundRandomizer == 0){
						this.gameSoundLoop.play();
						this.gameSoundLoop.loop();
						gameSoundLoopBoolean = true;
						soundTimer = 87;
						soundSet = true;
						soundStartTime = myTime;
						//set up timer
					}
					else{
						this.gameSoundLoop2.play();
						this.gameSoundLoop2.loop();
						gameSoundLoopBoolean2 = true;
						soundTimer = 109;
						soundSet = true;
						soundStartTime = myTime;
						//set up timer
					}
				}
					
			}
			if(runningTimerActivated == true){											//Checking to see if mario should be in running slide animation
				runningTimer++;
			}
			else if(runningTimerActivated == false)
				runningTimer = 0;
			if(slowingDownTimer > 0){
				if (xLBoolean == true){
					p.setVelX(-5);
					slowingDownTimer = 0;
					slowingDownActivatedl = false;
					slowingDownActivatedr = false;
				}
				if (xRBoolean == true){
					p.setVelX(5);
					slowingDownTimer = 0;
					slowingDownActivatedl = false;
					slowingDownActivatedr = false;
				}
				slowingDownTimer--;
				//slowingDown+= 0.13;
				p.setVelX(slowingDown);
			}
			else if(slowingDownTimer == 0 && slowingDownActivatedl == true || slowingDownActivatedr == true){
				p.setVelX(0);
				slowingDownActivatedl = false;
				slowingDownActivatedr = false;
			}
			//AudioPlayer.player.start(gameAudioStream);
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
			bb.draw(g2d);													//BLOCKS
			p.render(g);
			c.render(g);
			g.drawImage(marioLives, 0, (Game.HEIGHT * 2), null);
			
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
			//AudioPlayer.player.start(menuAudioStream);
			if(menuSoundSet == false){
				Random rand = new Random();
				menuSoundLoopRandomizer = rand.nextInt(2);
				if(menuSoundLoopRandomizer == 0){
					this.menuSoundLoop.loop();
					menuSoundLoopBoolean = true;
					menuSoundLoopBoolean2 = false;
					menuSoundSet = true;
				}
				else{
					this.menuSoundLoop2.loop();
					menuSoundLoopBoolean2 = true;
					menuSoundLoopBoolean = false;
					menuSoundSet = true;
				}
			}
			g.drawImage(title, 70, 100, null);
			g.drawImage(playTitle, Game.WIDTH / 2 + 120, 200, null);
			g.drawImage(helpTitle, Game.WIDTH / 2 + 120, 300, null);
			g.drawImage(exitTitle, Game.WIDTH / 2 + 120, 400, null);
		}else if (State == STATE.GAMEOVER){												//GameOver
			//wait a lil bit
			//bs.show();
			if(gameSoundLoopBoolean == true)
				this.gameSoundLoop.stop();
			if(gameSoundLoopBoolean2 == true)
				this.gameSoundLoop2.stop();

			if(gameOverSoundBoolean == false){
				this.gameOverSoundLoop.play();
				gameOverSoundBoolean = true;
			}
			//AudioPlayer.player.stop(gameAudioStream); 
			//this.gameAudioStream.close();
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
			runningTimerActivated = true;
			slowingDownTimer = 0;
			slowingDownActivatedl = false;
			slowingDownActivatedr = false;
		} else if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
			//p.setVelY(5);
			//yDBoolean = true;
		} else if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
			p.setVelX(5);
			xRBoolean = true;
			runningTimerActivated = true;
			slowingDownTimer = 0;
			slowingDownActivatedl = false;
			slowingDownActivatedr = false;
		}
		if (key == KeyEvent.VK_SPACE && !isShooting){											//Fireballs
			isShooting = true;
			if(ea.isEmpty()){
				c.addEntity(new Fireball(p.getX(),p.getY() + 32,tex, this));
				animationTimer1 = 10;
				numberOfFireBallsShot++;
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
			runningTimerActivated = false;
			if(xRBoolean == true){
				p.setVelX(5);
				runningTimerActivated = true;
			}
			else{
				if(runningTimer > 666){														//This activates sliding animation for left side
					slowingDownActivatedl = true;
					slowingDownTimer = 400;
					slowingDown = -1.73;
					p.setVelX(slowingDown);
				}
			}
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
			runningTimerActivated = false;
			if(xLBoolean == true){
				p.setVelX(-5);
				runningTimerActivated = true;
			}
			else{
				if(runningTimer > 666){														//This activates sliding animation for right side
					slowingDownActivatedr = true;
					slowingDownTimer = 400;
					slowingDown = 1.73;
					p.setVelX(slowingDown);
				}
			}
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
		SoundLoops menuSoundLoop = new SoundLoops(menuAudioFile);
		SoundLoops menuSoundLoop2 = new SoundLoops(menuAudioFile2);
		SoundLoops gameSoundLoop = new SoundLoops(gameAudioFile);
		SoundLoops gameSoundLoop2 = new SoundLoops(gameAudioFile2);
		SoundLoops gameOverSoundLoop = new SoundLoops(gameOverAudioFile);
		SoundLoops marioStarSoundLoop = new SoundLoops(marioStarAudioFile);
		SoundLoops soundFXClip1SoundLoop = new SoundLoops(soundFXClip1);
		SoundLoops soundFXClip2SoundLoop = new SoundLoops(soundFXClip2);
		
		// open the sound file as a Java input stream
		//InputStream gameStream = new FileInputStream(gameAudioFile);
		// create an audiostream from the inputstream
		//AudioStream gameAudioStream = new AudioStream(gameStream);
		// play the audio clip with the audioplayer class
		
		
		Game game = new Game();																						//Setting up Game
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		//game.gameAudioStream = gameAudioStream;
		game.menuSoundLoop = menuSoundLoop;
		game.menuSoundLoop2 = menuSoundLoop2;
		game.gameSoundLoop = gameSoundLoop;
		game.gameSoundLoop2 = gameSoundLoop2;
		game.marioStarSoundLoop = marioStarSoundLoop;
		game.soundFXClip1SoundLoop = soundFXClip1SoundLoop;
		game.soundFXClip2SoundLoop = soundFXClip2SoundLoop;
		game.gameOverSoundLoop = gameOverSoundLoop;//CREATE BOOLEANS TO PLAY SOUND ONCE
		//CREATE INTEGERS WITH APPROPRIATE TIME LENGTH FOR EACH SONG TO PLAY FROM SELECTION OF SONGS AT RANDOM
		
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
