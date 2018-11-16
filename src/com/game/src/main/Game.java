package com.game.src.main;
import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityD;
import com.game.src.main.classes.EntityE;
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
	private static boolean paused = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage spriteSheetNES = null;
	private BufferedImage spriteSheetNES3 = null;
	private BufferedImage spriteSheetSNESFireLuigi = null;
	private BufferedImage marioEntranceSprites = null;
	private BufferedImage marioNES3EntranceSprites = null;
	private BufferedImage marioSNESFireLuigiEntranceSprites = null;
	private BufferedImage animatedStar = null;
	private BufferedImage animatedShootingStar = null;
	private BufferedImage mario1StarSpriteSheet = null;
	private BufferedImage marioItemsSpriteSheet = null;
	private BufferedImage background = null;
	private BufferedImage marioLives = null;
	private BufferedImage bowserSpriteSheet = null;
	private BufferedImage bulletBillSpriteSheet = null;
	private BufferedImage marioPlayerStarAnimations = null;
	private BufferedImage marioItemAnimationSheet = null;
	private BufferedImage marioItemAnimationBackgroundSheet = null;
	private BufferedImage bigMarioItemAnimationSheet = null;
	private BufferedImage bigMario2ItemAnimationSheet = null;
	private BufferedImage bigMario3ItemAnimationSheet = null;
	private BufferedImage bigMario4ItemAnimationSheet = null;
	private BufferedImage itemSilhouetteSheet = null;
	private BufferedImage currentItemImg = null;
	private BufferedImage chainChompItemGettingBiggerSheet = null;
	private BufferedImage chainChompSheet = null;
	private BufferedImage chainChompDisintegrate = null;
	private BufferedImage marioSlowingDownSprites = null;
	private BufferedImage fullMarioSpriteSheet = null;
	private BufferedImage fullMarioSpriteSheetBlack = null;
	private BufferedImage marioAdvanceSpriteSheet = null;
	private BufferedImage marioPaintSpriteSheet = null;
	private BufferedImage marioPaintSilhouetteSpriteSheet = null;
	private BufferedImage mario3FontNumbersSmallSpriteSheet = null;
	private BufferedImage mario3FontSpriteSheet = null;
	private BufferedImage goombaDeathSpriteSheet = null;
	private BufferedImage backButtonTitle = null;
	private BufferedImage setScoreTitle = null;
	public static BufferedImage setScoreTitleBigger = null;
	private BufferedImage textIndicator = null;
	private BufferedImage leaderboardTitle = null;
	private BufferedImage leaderboardTitleBigger = null;
	private BufferedImage helpTitle = null;
	private BufferedImage settingsTitle = null;
	public static BufferedImage settingsTitleBigger = null;
	public static BufferedImage playerNameImage = null;
	private BufferedImage deletedLetter = null;
	private BufferedImage transparentBlocks = null;
	private BufferedImage volumeSlider = null;
	private BufferedImage volumeSliderGlow = null;
	private BufferedImage volumeSliderClicked = null;
	private BufferedImage volumeSliderSelected = null;
	private BufferedImage volumeSliderSelectedNormal = null;
	private BufferedImage volumeSliderSelectedClicked = null;
	private BufferedImage emptyVolumeSlider = null;
	private BufferedImage areYouSure = null;
	
	public static ArrayList<BufferedImage> leaderboardImage = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> itemBackground = new ArrayList<BufferedImage>();
	private int backgroundTraverse = 0;
	
	Animation starAnim;
	Animation shootingStarAnim;
	Animation transparentBlocksAnim;
	Animation currentItem;
	Animation marioTurningWithItem;
	Animation bulletBillSpawnSmokeL;
	Animation bulletBillSpawnSmokeR;
	
	private boolean shootingStarFrameStop = false;
	private boolean xLBoolean = false;
	private boolean xRBoolean = false;
	private boolean yUBoolean = false;
	private boolean yDBoolean = false;
	private boolean isShooting = false;
	public static boolean spawnDone = false;
	private boolean spawnDone2 = false;
	private boolean spawnDone3 = false;
	private boolean spawnDone4 = false;
	private boolean youWon = false;
	private boolean scoreEntered = false;
	private boolean marioHasBeenInvincible = false;
	private boolean slowingDownFromPause = false;
	private boolean keepRunningAfterPauseL = false;
	private boolean keepRunningAfterPauseR = false;
	private boolean dontRunAfterPause = false;
	public static boolean askToSkipSequence = false;
	public static boolean skipSequence = false;
	public static boolean writeOnceToSettings = false;
	public static boolean writeOnceToSettingswithPoints = false;
	public static boolean settingsSetup = false;
	public static boolean playHighlighted = false;
	public static boolean shopHighlighted = false;
	public static boolean exitHighlighted = false;
	public static boolean resumeHighlighted = false;
	public static boolean homeHighlighted = false;
	public static boolean backHighlighted = false;
	public static boolean setScoreHighlighted = false;
	public static boolean leaderboardHighlighted = false;
	public static boolean helpHighlighted = false;
	public static boolean settingsHighlighted = false;
	public static boolean volumeSliderHighlighted = false;
	public static boolean sfxMusicSliderHighlighted = false;
	public static boolean checkMarkHighlighted = false;
	public static boolean resetStatsHighlighted = false;
	public static boolean yesHighlighted = false;
	public static boolean noHighlighted = false;
	public static boolean skipHighlighted = false;
	public static boolean playClicked = false;
	public static boolean backOnPlay = false;
	public static boolean shopClicked = false;
	public static boolean backOnShop = false;
	public static boolean exitClicked = false;
	public static boolean backOnExit = false;
	public static boolean resumeClicked = false;
	public static boolean backOnResume = false;
	public static boolean homeClicked = false;
	public static boolean backOnHome = false;
	public static boolean backClicked = false;
	public static boolean backOnBack = false;
	public static boolean setScoreClicked = false;
	public static boolean backOnSetScore = false;
	public static boolean leaderboardClicked = false;
	public static boolean backOnLeaderboard = false;
	public static boolean helpClicked = false;
	public static boolean backOnHelp = false;
	public static boolean settingsClicked = false;
	public static boolean backOnSettings = false;
	public static boolean volumeSliderBooleanClicked = false;
	public static boolean backOnVolumeSlider = false;
	public static boolean sfxMusicSliderBooleanClicked = false;
	public static boolean backOnSFXMusicSlider = false;
	public static boolean resetStatsClicked = false;
	public static boolean backOnResetStats = false;
	public static boolean checkMarkClicked = false;
	public static boolean backOnCheckMark = false;
	public static boolean yesClicked = false;
	public static boolean backOnYes = false;
	public static boolean noClicked = false;
	public static boolean backOnNo = false;
	public static boolean skipClicked = false;
	public static boolean backOnSkip = false;
	public static boolean arrowL1Highlighted = false;
	public static boolean arrowR1Highlighted = false;
	public static boolean arrowL2Highlighted = false;
	public static boolean arrowR2Highlighted = false;
	public static boolean arrowL3Highlighted = false;
	public static boolean arrowR3Highlighted = false;
	public static boolean arrowL4Highlighted = false;
	public static boolean arrowR4Highlighted = false;
	public static boolean arrowL1Clicked = false;
	public static boolean arrowR1Clicked = false;
	public static boolean arrowL2Clicked = false;
	public static boolean arrowR2Clicked = false;
	public static boolean arrowL3Clicked = false;
	public static boolean arrowR3Clicked = false;
	public static boolean arrowL4Clicked = false;
	public static boolean arrowR4Clicked = false;
	public static boolean backOnArrowL1 = false;
	public static boolean backOnArrowR1 = false;
	public static boolean backOnArrowL2 = false;
	public static boolean backOnArrowR2 = false;
	public static boolean backOnArrowL3 = false;
	public static boolean backOnArrowR3 = false;
	public static boolean backOnArrowL4 = false;
	public static boolean backOnArrowR4 = false;
	public static boolean buy1Highlighted = false;
	public static boolean buy2Highlighted = false;
	public static boolean buy3Highlighted = false;
	public static boolean buy4Highlighted = false;
	public static boolean buy1Clicked = false;
	public static boolean buy2Clicked = false;
	public static boolean buy3Clicked = false;
	public static boolean buy4Clicked = false;
	public static boolean backOnBuy1 = false;
	public static boolean backOnBuy2 = false;
	public static boolean backOnBuy3 = false;
	public static boolean backOnBuy4 = false;
	public static boolean set1Highlighted = false;
	public static boolean set2Highlighted = false;
	public static boolean set3Highlighted = false;
	public static boolean set4Highlighted = false;
	public static boolean set1Clicked = false;
	public static boolean set2Clicked = false;
	public static boolean set3Clicked = false;
	public static boolean set4Clicked = false;
	public static boolean backOnSet1 = false;
	public static boolean backOnSet2 = false;
	public static boolean backOnSet3 = false;
	public static boolean backOnSet4 = false;
	public static boolean skin1Unlocked = false;
	public static boolean skin2Unlocked = false;
	public static boolean skin3Unlocked = false;
	public static boolean track1Unlocked = false;
	public static boolean fireball1Unlocked = false;
	public static boolean item1Unlocked = false;
	public static boolean item2Unlocked = false;
	public static boolean currentSkinLocked = false;
	public static boolean currentTrackLocked = false;
	public static boolean currentFireballLocked = false;
	public static boolean currentItemLocked = false;
	public static boolean mouseIsClickedDown = false;
	public static boolean mouseIsOffClickedObjectAndHeldDown = false;
	public static boolean keysAreInUse = false;
	public static boolean escapePressedNegateAction = false;
	public static boolean enterButtonPushedDown = false;
	public static boolean skipAnimations = false;
	public static boolean areYouSureBoolean = false;
	public static boolean starExplode = false;
	public static int mx = 0;
	public static int my = 0;
	public static int totalPoints = 0;
	public static int selectorButtonPosition = 0;
	public static int characterSkinPosition = 0;
	public static int trackPosition = 0;
	public static int fireballPosition = 0;
	public static int itemPosition = 0;
	public static int currentlySelectedCharacterSkin = 0;
	public static int currentlySelectedTrack = 0;
	public static int currentlySelectedFireball = 0;
	public static int currentlySelectedItem = 0;
	public static int volumeSliderPosition = 3;
	public static int sfxMusicSliderPosition = 3;
	public static int hudSFXPosition = 0;
	public static String writeOnceProperty = "";
	public static String writeOnceString = "";
	public static String writeOnceUnlock = "";
	private double myTime = 0.0;
	private String itemName;
	private int numberOfFireBallsShot = 0;
	private int numberOfFireBallsShotDecoy = 0;
	private char postLetter = '=';
	private float imageTranslucent = 0;
	private double imageTranslucentVelocity = 0;
	private long imageTranslucentTimer = 0;
	private long traverseTime = 0;
	private boolean imageIsGone = false;
	long imageTranslucentTimer2=0;
	boolean imageStayOn = false;
	boolean shiftOn = false;
	public static ArrayList<String> playerName = new ArrayList<String>();
	public static int postLetterXPosition = 0;
	public static int postLetterXPositionBeginning = 200;
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
	public static long starExplosionTimer = 0;
	private long soundFXTimer = 0;
	private long enemyHitPauseTimer = 0;
	private boolean enemyHitPauseBoolean = false;
	private boolean spawnItem = false;
	private boolean spawnBulletBill = false;
	private boolean spawn1BulletBillAtATime = false;
	private long bowserBulletBillSpawningTimer = 0;
	private long transitionTimer = 0;
	private long gameStartSoundTimer = 0;
	private static long pauseSoundFXTimer = 0;
	private long visualPauseTimer = 0;
	private long marioDancePosePauseTimer = 0;
	private long marioGrowthPosePauseTimer = 0;
	private long marioLetsGoPauseTimer = 0;
	private long itemWaitTimer = 0;
	private long itemFlyingTimer1 = 0;
	private double itemFlyingAwayY = 0;
	private double itemFlyingAwayX = 0;
	private boolean fileScoreWritten = false;
	public static boolean dontStartOver = false;
	public static boolean backToGameOver = false;
	private static boolean userHasPaused = false;
	private static boolean soundFXisPlaying = false;
	private boolean soundFXBoolean = false;
	private boolean soundFXClip1Reset = false;
	private boolean marioDancePosePause = false;
	private boolean marioGrowthPosePause = false;
	private boolean marioLetsGoPause = false;
	private int soundRandomizer = 0;
	private int menuSoundLoopRandomizer = 0;
	private int marioVoiceRandomizer = 0;
	private int spawningEnemiesinTransition = 0;
	private int spawningEnemiesDanceSync = 0;
	public static double currentEECollisionX = 0;
	public static double currentEECollisionY = 0;
	public static double currentEECollisionWidth = 0;
	private boolean menuSoundSet = false;
	private boolean chainChompDeathSoundPauseBoolean = false;
	private boolean bulletBillDeathSoundPauseBoolean = false;
	private boolean goombaDeathSoundPauseBoolean = false;
	private boolean coinSoundPauseBoolean = false;
	private boolean starDingPauseBoolean = false;
	private boolean goomba3DeathSoundPauseBoolean = false;
	private boolean goomba3DeathSmokeSoundPauseBoolean = false;
	private boolean brickBreakingSFXSoundPauseBoolean = false;
	LinkedList<SoundLoops> menuSoundLoops = new LinkedList<SoundLoops>();
	LinkedList<SoundLoops> gameSoundLoops = new LinkedList<SoundLoops>();
	public static LinkedList<SoundLoops> marioDanceSoundLoops = new LinkedList<SoundLoops>();
	public static LinkedList<SoundLoops> marioVoices = new LinkedList<SoundLoops>();
	public static LinkedList<SoundLoops> luigiVoices = new LinkedList<SoundLoops>();
	public static LinkedList<SoundLoops> hudSFX = new LinkedList<SoundLoops>();
	public static LinkedList<SoundLoops> brickBreakingSFX = new LinkedList<SoundLoops>();
	LinkedList<SoundLoops> chainChompDeathSoundLoop = new LinkedList<SoundLoops>();
	LinkedList<SoundLoops> bulletBillDeathSoundLoop = new LinkedList<SoundLoops>();
	LinkedList<SoundLoops> goombaDeathSoundLoop = new LinkedList<SoundLoops>();
	LinkedList<SoundLoops> coinSoundLoop = new LinkedList<SoundLoops>();
	LinkedList<SoundLoops> starDingSoundLoop = new LinkedList<SoundLoops>();
	LinkedList<SoundLoops> goomba3DeathSoundLoop = new LinkedList<SoundLoops>();
	LinkedList<SoundLoops> goomba3DeathSmokeSoundLoop = new LinkedList<SoundLoops>();
	public static SoundLoops fireballSFX;
	public static SoundLoops pauseSoundFXSoundLoop;
	public static SoundLoops gameOverSoundLoop;
	public static SoundLoops gameOverWinningSoundLoop;
	public static SoundLoops gameOverIrisSoundLoop;
	public static SoundLoops marioStarSoundLoop;
	public static SoundLoops soundFXClip1SoundLoop;
	public static SoundLoops soundFXClip2SoundLoop;
	public static SoundLoops marioSpinningSoundLoop;
	public static SoundLoops marioDeathSoundLoop;
	public static SoundLoops itemSwooshSoundLoop;
	public static SoundLoops smb3CoinSoundLoop;
	public static SoundLoops smb3BumpSoundLoop;
	public static SoundLoops smb3Bump2SoundLoop;
	public static SoundLoops smb3KickSoundLoop;
	public static SoundLoops smb3TailSoundLoop;
	public static SoundLoops smb3OpenSoundLoop;
	public static SoundLoops smb31PupSoundLoop;
	public static SoundLoops smb3ItemSoundLoop;
	public static SoundLoops smb3BeepSoundLoop;
	public static SoundLoops smb3CheckmarkSoundLoop;
	public static SoundLoops smb3Checkmark2SoundLoop;
	public static SoundLoops smwErrorSoundLoop;
	private Player p;
	private Controller c;
	private Enemy e;
	private Textures tex;
	private HUD hud;
	private Menu menu;
	private GameOver gameOver;
	private BasicBlocks bb;													//BLOCKS
	private LeaderboardController leaderboard;
	private ShopController shop;
	private MouseLocator mouseLocator;
	private StarExplosion starExplosion;
	
	public static BufferedImage title = null;
	public static BufferedImage gameOverTitle = null;
	private BufferedImage playTitle = null;
	public static BufferedImage shopTitle = null;
	private BufferedImage exitTitle = null;
	private BufferedImage resumeTitle = null;
	private BufferedImage homeTitle = null;
	private BufferedImage playTitleGlow = null;
	private BufferedImage shopTitleGlow = null;
	private BufferedImage exitTitleGlow = null;
	private BufferedImage resumeTitleGlow = null;
	private BufferedImage homeTitleGlow = null;
	private BufferedImage setScoreTitleGlow = null;
	private BufferedImage leaderboardTitleGlow = null;
	private BufferedImage backButtonTitleGlow = null;
	private BufferedImage helpTitleGlow = null;
	private BufferedImage settingsTitleGlow = null;
	private BufferedImage playTitleClicked = null;
	private BufferedImage shopTitleClicked = null;
	private BufferedImage exitTitleClicked = null;
	private BufferedImage resumeTitleClicked = null;
	private BufferedImage homeTitleClicked = null;
	private BufferedImage setScoreTitleClicked = null;
	private BufferedImage leaderboardTitleClicked = null;
	private BufferedImage backButtonTitleClicked = null;
	private BufferedImage helpTitleClicked = null;
	private BufferedImage settingsTitleClicked = null;
	private BufferedImage playTitleSelected = null;
	private BufferedImage playTitleSelectedClicked = null;
	private BufferedImage playTitleSelectedNormal = null;
	private BufferedImage resumeTitleSelected = null;
	private BufferedImage resumeTitleSelectedClicked = null;
	private BufferedImage resumeTitleSelectedNormal = null;
	private BufferedImage homeTitleSelected = null;
	private BufferedImage homeTitleSelectedClicked = null;
	private BufferedImage homeTitleSelectedNormal = null;
	private BufferedImage backTitleSelected = null;
	private BufferedImage backTitleSelectedClicked = null;
	private BufferedImage backTitleSelectedNormal = null;
	private BufferedImage setScoreTitleSelected = null;
	private BufferedImage setScoreTitleSelectedClicked = null;
	private BufferedImage setScoreTitleSelectedNormal = null;
	private BufferedImage leaderboardTitleSelected = null;
	private BufferedImage leaderboardTitleSelectedClicked = null;
	private BufferedImage leaderboardTitleSelectedNormal = null;
	private BufferedImage helpTitleSelected = null;
	private BufferedImage helpTitleSelectedClicked = null;
	private BufferedImage helpTitleSelectedNormal = null;
	private BufferedImage settingsTitleSelected = null;
	private BufferedImage settingsTitleSelectedClicked = null;
	private BufferedImage settingsTitleSelectedNormal = null;
	private BufferedImage arrowL = null;//Linked lists?
	private BufferedImage arrowR = null;
	private BufferedImage arrowLGlow = null;
	private BufferedImage arrowRGlow = null;
	private BufferedImage arrowLClicked = null;
	private BufferedImage arrowRClicked = null;
	private BufferedImage arrowSelected = null;
	private BufferedImage arrowSelectedClicked = null;
	private BufferedImage arrowSelectedNormal = null;
	private BufferedImage buyTitle = null;
	private BufferedImage buyTitleGlow = null;
	private BufferedImage buyTitleClicked = null;
	private BufferedImage buyTitleSelected = null;
	private BufferedImage buyTitleSelectedClicked = null;
	private BufferedImage buyTitleSelectedNormal = null;
	private BufferedImage setTitle = null;
	private BufferedImage setTitleGlow = null;
	private BufferedImage setTitleClicked = null;
	private BufferedImage setTitleSelected = null;
	private BufferedImage setTitleSelectedClicked = null;
	private BufferedImage setTitleSelectedNormal = null;
	private BufferedImage checkMarkTitle = null;
	private BufferedImage checkMarkTitleGlow = null;
	private BufferedImage checkMarkTitleClicked = null;
	private BufferedImage noCheckMarkTitle = null;
	private BufferedImage noCheckMarkTitleGlow = null;
	private BufferedImage noCheckMarkTitleClicked = null;
	private BufferedImage resetStatsTitle = null;
	private BufferedImage resetStatsTitleGlow = null;
	private BufferedImage resetStatsTitleClicked = null;
	private BufferedImage resetStatsTitleSelected = null;
	private BufferedImage resetStatsTitleSelectedClicked = null;
	private BufferedImage resetStatsTitleSelectedNormal = null;
	private BufferedImage yesTitle = null;
	private BufferedImage yesTitleGlow = null;
	private BufferedImage yesTitleClicked = null;
	private BufferedImage yesTitleSelected = null;
	private BufferedImage yesTitleSelectedClicked = null;
	private BufferedImage yesTitleSelectedNormal = null;
	private BufferedImage noTitle = null;
	private BufferedImage noTitleGlow = null;
	private BufferedImage noTitleClicked = null;
	private BufferedImage noTitleSelected = null;
	private BufferedImage noTitleSelectedClicked = null;
	private BufferedImage noTitleSelectedNormal = null;
	private BufferedImage skipTitle = null;
	private BufferedImage skipTitleGlow = null;
	private BufferedImage skipTitleClicked = null;
	private BufferedImage skipTitleSelected = null;
	private BufferedImage skipTitleSelectedClicked = null;
	private BufferedImage skipTitleSelectedNormal = null;
	private BufferedImage currentlySelected10x10 = null;
	public static BufferedImage skinTitle = null;
	public static BufferedImage tracksTitle = null;
	public static BufferedImage fireballsTitle = null;
	public static BufferedImage itemsTitle = null;
	public static BufferedImage volumeTitle = null;
	public static BufferedImage sfxMusicTitle = null;
	public static BufferedImage skipAnimationsTitle = null;
	public static BufferedImage totalPointsImage = null;
	public static BufferedImage skinNumber = null;
	public static BufferedImage trackNumber = null;
	public static BufferedImage fireballNumber = null;
	public static BufferedImage itemNumber = null;
	
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	public LinkedList<EntityC> ec;
	public LinkedList<EntityD> ed;
	public LinkedList<EntityE> ee;
	
	public static int Health = 100;
	
	public static enum STATE{
		MENU,
		TRANSITION_ENTRANCE,
		TRANSITION_ITEM,
		TRANSITION_DEATH,
		TRANSITION_WIN,
		GAME,
		PAUSE,
		GAMEOVER,
		SET_SCORE,
		LEADERBOARD,
		SHOP,
		SETTINGS,
		HELP,
		RESET
	};
	public static STATE State = STATE.MENU;
	
	public void init(){
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			spriteSheet = loader.loadImage("/AnimationSpriteSheetNew.png");
			spriteSheetNES = loader.loadImage("/AnimationSpriteSheetNewNES.png");
			spriteSheetNES3 = loader.loadImage("/AnimationSpriteSheetNewNES3.png");
			spriteSheetSNESFireLuigi = loader.loadImage("/AnimationSpriteSheetNewSNESFireLuigi.png");
			marioEntranceSprites = loader.loadImage("/marioentrancesprites.png");
			marioNES3EntranceSprites = loader.loadImage("/mario2entrancesprites.png");
			marioSNESFireLuigiEntranceSprites = loader.loadImage("/mario3entrancesprites.png");
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
			bigMario2ItemAnimationSheet = loader.loadImage("/mario-big2.png");
			bigMario3ItemAnimationSheet = loader.loadImage("/mario-big3.png");
			bigMario4ItemAnimationSheet = loader.loadImage("/mario-big4.png");
			itemSilhouetteSheet = loader.loadImage("/marioitemssilhouette.png");
			chainChompItemGettingBiggerSheet = loader.loadImage("/Items/chainChompItemGettingBig.png");
			chainChompSheet = loader.loadImage("/Items/Chain_Chomp_spritesss.png");
			chainChompDisintegrate = loader.loadImage("/Items/chainchompdisintegrate.png");
			marioSlowingDownSprites = loader.loadImage("/firemarioslidingeffect.png");
			fullMarioSpriteSheet = loader.loadImage("/mario.png");
			fullMarioSpriteSheetBlack = loader.loadImage("/mario-black.png");
			marioAdvanceSpriteSheet = loader.loadImage("/marioadvancespritesheet.png");
			marioPaintSpriteSheet = loader.loadImage("/mariopaintsheet.png");
			marioPaintSilhouetteSpriteSheet = loader.loadImage("/mariopaintsheetsilhouette.png");
			mario3FontNumbersSmallSpriteSheet = loader.loadImage("/mario3fonteNUMBERSSMALLERR.png");
			mario3FontSpriteSheet = loader.loadImage("/mario3fonteLAYEDOUT.png");
			goombaDeathSpriteSheet = loader.loadImage("/goombadeath.png");
			title = loader.loadImage("/koopasinvaderstitlebigger.png");
			gameOverTitle = loader.loadImage("/gameover1bigger.png");
			playTitle = loader.loadImage("/newplaybutton.png");
			shopTitle = loader.loadImage("/newshopbutton.png");
			exitTitle = loader.loadImage("/newexitbutton.png");
			backButtonTitle = loader.loadImage("/backButtonSmaller.png");
			resumeTitle = loader.loadImage("/newresumebutton.png");
			homeTitle = loader.loadImage("/newhomebutton.png");
			setScoreTitle = loader.loadImage("/setScoreSmaller.png");
			setScoreTitleBigger= loader.loadImage("/setScore.png");
			leaderboardTitle = loader.loadImage("/leaderboardSmaller.png");
			leaderboardTitleBigger = loader.loadImage("/leaderboard.png");
			helpTitle = loader.loadImage("/newhelpbuttonSmaller.png");
			settingsTitle = loader.loadImage("/newsettingsbutton.png");
			settingsTitleBigger = loader.loadImage("/newsettingstitle.png");
			playTitleGlow = loader.loadImage("/newplaybuttonglow.png");
			shopTitleGlow = loader.loadImage("/newshopbuttonglow.png");
			exitTitleGlow = loader.loadImage("/newexitbuttonglow.png");
			resumeTitleGlow = loader.loadImage("/newresumebuttonglow.png");
			homeTitleGlow = loader.loadImage("/newhomebuttonglow.png");
			setScoreTitleGlow = loader.loadImage("/setScoreSmallerglow.png");
			leaderboardTitleGlow = loader.loadImage("/leaderboardSmallerglow.png");
			backButtonTitleGlow = loader.loadImage("/backButtonSmallerglow.png");
			helpTitleGlow = loader.loadImage("/newhelpbuttonSmallerglow.png");
			settingsTitleGlow = loader.loadImage("/newsettingsbuttonglow.png");
			playTitleClicked = loader.loadImage("/newplaybuttonclicked.png");
			shopTitleClicked = loader.loadImage("/newshopbuttonclicked.png");
			exitTitleClicked = loader.loadImage("/newexitbuttonclicked.png");
			resumeTitleClicked = loader.loadImage("/newresumebuttonclicked.png");
			homeTitleClicked = loader.loadImage("/newhomebuttonclicked.png");
			setScoreTitleClicked = loader.loadImage("/setScoreSmallerclicked.png");
			leaderboardTitleClicked = loader.loadImage("/leaderboardSmallerclicked.png");
			backButtonTitleClicked = loader.loadImage("/backButtonSmallerclicked.png");
			helpTitleClicked = loader.loadImage("/newhelpbuttonSmallerclicked.png");
			settingsTitleClicked = loader.loadImage("/newsettingsbuttonclicked.png");
			playTitleSelected = loader.loadImage("/newplaybuttonselected.png");
			playTitleSelectedClicked = loader.loadImage("/newplaybuttonselectedclicked.png");
			playTitleSelectedNormal = loader.loadImage("/newplaybuttonselectednormal.png");
			resumeTitleSelected = loader.loadImage("/newresumebuttonselected.png");
			resumeTitleSelectedClicked = loader.loadImage("/newresumebuttonselectedclicked.png");
			resumeTitleSelectedNormal = loader.loadImage("/newresumebuttonselectednormal.png");
			homeTitleSelected = loader.loadImage("/newhomebuttonselected.png");
			homeTitleSelectedClicked = loader.loadImage("/newhomebuttonselectedclicked.png");
			homeTitleSelectedNormal = loader.loadImage("/newhomebuttonselectednormal.png");
			backTitleSelected = loader.loadImage("/backButtonSmallerselected.png");
			backTitleSelectedClicked = loader.loadImage("/backButtonSmallerselectedclicked.png");
			backTitleSelectedNormal = loader.loadImage("/backButtonSmallerselectednormal.png");
			setScoreTitleSelected = loader.loadImage("/setScoreSmallerselected.png");
			setScoreTitleSelectedClicked = loader.loadImage("/setScoreSmallerselectedclicked.png");
			setScoreTitleSelectedNormal = loader.loadImage("/setScoreSmallerselectednormal.png");
			leaderboardTitleSelected = loader.loadImage("/leaderboardSmallerselected.png");
			leaderboardTitleSelectedClicked = loader.loadImage("/leaderboardSmallerselectedclicked.png");
			leaderboardTitleSelectedNormal = loader.loadImage("/leaderboardSmallerselectednormal.png");
			helpTitleSelected = loader.loadImage("/newhelpbuttonSmallerselected.png");
			helpTitleSelectedClicked = loader.loadImage("/newhelpbuttonSmallerselectedclicked.png");
			helpTitleSelectedNormal = loader.loadImage("/newhelpbuttonSmallerselectednormal.png");
			settingsTitleSelected = loader.loadImage("/newsettingsbuttonselected.png");
			settingsTitleSelectedClicked = loader.loadImage("/newsettingsbuttonselectedclicked.png");
			settingsTitleSelectedNormal = loader.loadImage("/newsettingsbuttonselectednormal.png");
			arrowL = loader.loadImage("/arrowL.png");
			arrowR = loader.loadImage("/arrowR.png");
			arrowLGlow = loader.loadImage("/arrowLglow.png"); 
			arrowRGlow = loader.loadImage("/arrowRglow.png"); 
			arrowLClicked = loader.loadImage("/arrowLclicked.png"); 
			arrowRClicked = loader.loadImage("/arrowRclicked.png"); 
			arrowSelected = loader.loadImage("/arrowselected.png");
			arrowSelectedClicked = loader.loadImage("/arrowselectedclicked.png");
			arrowSelectedNormal = loader.loadImage("/arrowselectednormal.png");
			buyTitle = loader.loadImage("/newbuybutton.png");
			buyTitleGlow = loader.loadImage("/newbuybuttonglow.png");
			buyTitleClicked = loader.loadImage("/newbuybuttonclicked.png");
			buyTitleSelected = loader.loadImage("/newbuybuttonselected.png");
			buyTitleSelectedClicked = loader.loadImage("/newbuybuttonselectedclicked.png");
			buyTitleSelectedNormal = loader.loadImage("/newbuybuttonselectednormal.png"); 
			setTitle = loader.loadImage("/newsetbutton.png");
			setTitleGlow = loader.loadImage("/newsetbuttonglow.png");
			setTitleClicked = loader.loadImage("/newsetbuttonclicked.png");
			setTitleSelected = loader.loadImage("/newsetbuttonselected.png");
			setTitleSelectedClicked = loader.loadImage("/newsetbuttonselectedclicked.png");
			setTitleSelectedNormal = loader.loadImage("/newsetbuttonselectednormal.png");
			checkMarkTitle = loader.loadImage("/checkmark.png");
			checkMarkTitleGlow = loader.loadImage("/checkmarkglow.png");
			checkMarkTitleClicked = loader.loadImage("/checkmarkclicked.png");
			noCheckMarkTitle = loader.loadImage("/nocheckmark.png");
			noCheckMarkTitleGlow = loader.loadImage("/nocheckmarkglow.png");
			noCheckMarkTitleClicked = loader.loadImage("/nocheckmarkclicked.png");
			resetStatsTitle = loader.loadImage("/newresetstatsbutton.png");
			resetStatsTitleGlow = loader.loadImage("/newresetstatsbuttonglow.png");
			resetStatsTitleClicked = loader.loadImage("/newresetstatsbuttonclicked.png");
			resetStatsTitleSelected = loader.loadImage("/newresetstatsbuttonselected.png");
			resetStatsTitleSelectedClicked = loader.loadImage("/newresetstatsbuttonselectedclicked.png");
			resetStatsTitleSelectedNormal = loader.loadImage("/newresetstatsbuttonselectednormal.png");
			yesTitle = loader.loadImage("/newyesbutton.png");
			yesTitleGlow = loader.loadImage("/newyesbuttonglow.png");
			yesTitleClicked = loader.loadImage("/newyesbuttonclicked.png");
			yesTitleSelected = loader.loadImage("/newyesbuttonselected.png");
			yesTitleSelectedClicked = loader.loadImage("/newyesbuttonselectedclicked.png");
			yesTitleSelectedNormal = loader.loadImage("/newyesbuttonselectednormal.png");
			noTitle = loader.loadImage("/newnobutton.png");
			noTitleGlow = loader.loadImage("/newnobuttonglow.png");
			noTitleClicked = loader.loadImage("/newnobuttonclicked.png");
			noTitleSelected = loader.loadImage("/newnobuttonselected.png");
			noTitleSelectedClicked = loader.loadImage("/newnobuttonselectedclicked.png");
			noTitleSelectedNormal = loader.loadImage("/newnobuttonselectednormal.png");
			skipTitle = loader.loadImage("/newskipbutton.png");
			skipTitleGlow = loader.loadImage("/newskipbuttonglow.png");
			skipTitleClicked = loader.loadImage("/newskipbuttonclicked.png");
			skipTitleSelected = loader.loadImage("/newskipbuttonselected.png");
			skipTitleSelectedClicked = loader.loadImage("/newskipbuttonselectedclicked.png");
			skipTitleSelectedNormal = loader.loadImage("/newskipbuttonselectednormal.png");
			volumeSlider = loader.loadImage("/volumeslider.png");
			volumeSliderGlow = loader.loadImage("/volumesliderglow.png");
			volumeSliderClicked = loader.loadImage("/volumesliderclicked.png");
			volumeSliderSelected = loader.loadImage("/volumesliderselected.png");
			volumeSliderSelectedNormal = loader.loadImage("/volumesliderselectednormal.png");
			volumeSliderSelectedClicked = loader.loadImage("/volumesliderselectedclicked.png");
			emptyVolumeSlider = loader.loadImage("/emptyvolumeslider.png");
			areYouSure = loader.loadImage("/areyousure.png");
			currentlySelected10x10 = loader.loadImage("/currentlyselected10x10.png");
			skinTitle = loader.loadImage("/newskinbutton.png"); 
			tracksTitle = loader.loadImage("/newtracksbutton.png");
			fireballsTitle = loader.loadImage("/newfireballsbutton.png");
			itemsTitle = loader.loadImage("/newitemsbutton.png");
			volumeTitle = loader.loadImage("/newvolumebutton.png");
			sfxMusicTitle = loader.loadImage("/newsfxmusicbutton.png");
			skipAnimationsTitle = loader.loadImage("/newskipanimationsbutton.png");
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
		leaderboard = new LeaderboardController(this);
		shop = new ShopController(tex,this);
		mouseLocator = new MouseLocator(this);
		starExplosion = new StarExplosion();
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		ec = c.getEntityC();
		ed = c.getEntityD();
		ee = c.getEntityE();
		
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
		
		marioTurningWithItem = new Animation(6, tex.marioItemAnimationBeginning[0],tex.marioItemAnimationBeginning[0],
				tex.marioItemAnimationBeginning[1],tex.marioItemAnimationBeginning[11],tex.marioItemAnimationBeginning[12],
				tex.marioItemAnimationBeginning[13],tex.marioItemAnimationBeginning[14],tex.marioItemAnimationBeginning[15],
				tex.marioItemAnimationBeginning[16],tex.marioItemAnimationBeginning[17]);
		

		bulletBillSpawnSmokeR = new Animation(4,tex.bulletBillSpawnSmokeR[0],tex.bulletBillSpawnSmokeR[1],
				tex.bulletBillSpawnSmokeR[2],tex.bulletBillSpawnSmokeR[3],tex.bulletBillSpawnSmokeR[4],
				tex.bulletBillSpawnSmokeR[5],tex.bulletBillSpawnSmokeR[6],tex.bulletBillSpawnSmokeR[7],
				tex.bulletBillSpawnSmokeR[8],tex.bulletBillSpawnSmokeR[9]);
		
		bulletBillSpawnSmokeL = new Animation(4,tex.bulletBillSpawnSmokeL[0],tex.bulletBillSpawnSmokeL[1],
				tex.bulletBillSpawnSmokeL[2],tex.bulletBillSpawnSmokeL[3],tex.bulletBillSpawnSmokeL[4],
				tex.bulletBillSpawnSmokeL[5],tex.bulletBillSpawnSmokeL[6],tex.bulletBillSpawnSmokeL[7],
				tex.bulletBillSpawnSmokeL[8],tex.bulletBillSpawnSmokeL[9]);
		
		textIndicator = tex.textIndicator;
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
				bb.tick();
			}
		}
		if(!(State == STATE.TRANSITION_ITEM))
			starAnim.runAnimation();
		if(State == STATE.TRANSITION_ENTRANCE)
			c.tick();
		transparentBlocksAnim.runAnimation();
		if((myTime / 10) == (int)(myTime/10) && shootingStarFrameStop == false)//(myTime > 10 && myTime < 22 || myTime > 30 && myTime < 42)
			shootingStarAnim.runAnimation();
		if(System.currentTimeMillis() < starExplosionTimer) 
			starExplosion.tick();
		if(spawnBulletBill && !paused) {
			if((enemyHitRightBarrier || bulletBillSpawnSmokeR.getCount() != 0)&& bulletBillSpawnSmokeL.getCount() == 0)
				bulletBillSpawnSmokeR.runAnimation();
			else if(bulletBillSpawnSmokeR.getCount() == 0)
				bulletBillSpawnSmokeL.runAnimation();
		}
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
		
		if (gameOverBoolean == true && State != STATE.TRANSITION_DEATH) {
			State = STATE.TRANSITION_DEATH;
			if(LeaderboardController.getFromSettings("Total Points: ").equals("")) {
				if(hud.getScore() < 999999)
					LeaderboardController.writeToSettings("Total Points: ", String.valueOf(hud.getScore()));
				else
					LeaderboardController.writeToSettings("Total Points: ", "999999");
			}
			else {
				int i = Integer.valueOf(LeaderboardController.getFromSettings("Total Points: "));
				i += hud.getScore();
				if(i < 999999){}
				else
					i = 999999;
				LeaderboardController.writeToSettings("Total Points: ", String.valueOf(i));
			}
			if(!LeaderboardController.getFromSettings("Total Points: ").equals("")) {
				totalPointsImage = HUD.stringToMario3FontImage(LeaderboardController.getFromSettings("Total Points: "));
				totalPointsImage = Game.resize(HUD.stringToMario3FontImage(LeaderboardController.getFromSettings("Total Points: ")), totalPointsImage.getWidth()/2, totalPointsImage.getHeight()/2);
			}
		}
		if (HUD.getTimer2() <= 0 && p.gameOver == false)
			State = STATE.TRANSITION_WIN;
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
					this.gameSoundLoops.get(soundRandomizer).stop();
					this.gameSoundLoops.get(soundRandomizer).setSoundLoopBoolean(true);
					this.gameSoundLoops.get(soundRandomizer).setFramePosition(0);
					this.gameSoundLoops.get(soundRandomizer).play();
					soundSet = true;
				}
				if(System.currentTimeMillis() > pauseSoundFXTimer && pauseSoundFXSoundLoop.getSoundLoopBoolean() == true){
					paused = false;
					pauseSoundFXSoundLoop.setSoundLoopBoolean(false);
					this.gameSoundLoops.get(soundRandomizer).continuePlaying();
					if(slowingDownTimerLong != 0 && slowingDownFromPause == true) {
						slowingDownTimerLong = System.currentTimeMillis() + 200;
					}
					else if(slowingDownTimerLong != 0) {
						slowingDownTimerLong += System.currentTimeMillis();
					}
					else if(dontRunAfterPause) {
						p.setVelX(0);
					}
					slowingDownFromPause = false;
					keepRunningAfterPauseL = false;
					keepRunningAfterPauseR = false;
					dontRunAfterPause = false;
				}
			}
			if(p.getMarioInvincible() == false && marioHasBeenInvincible == true){		//Setting up SoundFX in between Audio Clips
				if(Game.marioStarSoundLoop.getSoundLoopBoolean() == true){
					Game.marioStarSoundLoop.stop();
					Game.marioStarSoundLoop.setSoundLoopBoolean(false);
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
					this.gameSoundLoops.get(soundRandomizer).continuePlaying();
				}
				
				if(System.currentTimeMillis() > soundFXTimer && soundTimerSet == true){							//checking if paused for soundFX Timer
					paused = false;
					soundFXisPlaying = false;
				}
				
				if(!paused && soundTimerSet == true){
					this.soundFXClip1SoundLoop.stop();
					soundFXClip1Reset = false;
					if(this.gameSoundLoops.get(this.soundRandomizer).getSoundLoopBoolean() == true && this.soundFXClip1SoundLoop.getSoundLoopBoolean() == true){
						this.gameSoundLoops.get(this.soundRandomizer).continuePlaying();
						Game.soundFXClip1SoundLoop.setSoundLoopBoolean(false);
					}
					soundTimerSet = false;
				}
				else if(paused == true && soundTimerSet == true){
					transparentBlocksAnim.drawAnimation(g,p.getX(), p.getY(), 0);
					//add visual effect
					if(!soundFXClip1Reset){
						Game.soundFXClip1SoundLoop.setSoundLoopBoolean(true);
						Game.soundFXClip1SoundLoop.play();
						soundFXClip1Reset = true;
					}
					Game.soundFXClip1SoundLoop.loop();
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
					Game.marioStarSoundLoop.continuePlaying();
					Game.marioStarSoundLoop.setSoundLoopBoolean(true);
				}
				
				if(System.currentTimeMillis() > soundFXTimer && soundFXisPlaying == true){							//checking if paused for soundFX Timer
					paused = false;
					soundFXisPlaying = false;
				}
				if(!paused && soundTimerSet == false){
					this.soundFXClip2SoundLoop.stop();
					soundFXClip1Reset = false;
					this.marioStarSoundLoop.setFramePosition(0);
					this.marioStarSoundLoop.play();
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
				}/*
				if(System.currentTimeMillis() < this.getEnemyHitPauseTimer() && this.marioStarSoundLoop.clipIsActive())
					this.marioStarSoundLoop.stop();
				else if(!this.marioStarSoundLoop.clipIsActive() && this.getEnemyHitPauseTimer() != 0 && !this.isPaused())
					this.marioStarSoundLoop.continuePlaying();*/
			}					
																				//Setting up next song to play after a song ends
			if(!this.gameSoundLoops.get(this.soundRandomizer).clipIsActive() && this.gameSoundLoops.get(this.soundRandomizer).getSoundLoopBoolean() == true && State == STATE.GAME && !isPaused() && 
					!p.getMarioInvincible() && pauseSoundFXSoundLoop.getSoundLoopBoolean() == false && userHasPaused == false && !pauseSoundFXSoundLoop.clipIsActive() &&
					gameSoundLoops.get(soundRandomizer).getLongFramePosition() > 0 && gameSoundLoops.get(soundRandomizer).endsSoon()){	
				if(this.gameSoundLoops.get(this.soundRandomizer).getSoundLoopBoolean() == true){
					this.gameSoundLoops.get(this.soundRandomizer).stop();
					this.gameSoundLoops.get(this.soundRandomizer).setSoundLoopBoolean(false);
				}
				soundSet = false;
				if(soundSet == false){	
					Random rand = new Random();
					soundRandomizer = rand.nextInt(2);
					this.gameSoundLoops.get(soundRandomizer).setSoundLoopBoolean(true);
					this.gameSoundLoops.get(soundRandomizer).setFramePosition(0);
					this.gameSoundLoops.get(soundRandomizer).play();
					soundSet = true;
				}
					
			}
			if(runningTimerActivated == true){											//Checking to see if mario should be in running slide animation
				runningTimerLong = System.currentTimeMillis();
				runningTimerActivated = false;
				runningTimerActivatedResponse = true;
			}
			if(slowingDownTimerLong <= System.currentTimeMillis() && slowingDownTimerLong != 0 && !paused && !userHasPaused){
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
			else if(slowingDownTimerLong == 0 && (slowingDownActivatedl == true || slowingDownActivatedr == true)){
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
				if(this.marioStarSoundLoop.clipIsActive())
					this.marioStarSoundLoop.stop();
				paused = true;
				enemyHitPauseBoolean = true;
			}
			else if(enemyHitPauseBoolean == true){
				paused = false;
				enemyHitPauseBoolean = false;
				if(p.getMarioInvincible() && !this.marioStarSoundLoop.clipIsActive())
					this.marioStarSoundLoop.continuePlaying();
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
				for(int i = 0; i < (Game.WIDTH * Game.SCALE); i+=64){
					c.addEntity(new Enemy(i,26, tex, c , this));
					}
				for(int i = 0; i < (Game.WIDTH * Game.SCALE); i+=64){
					c.addEntity(new Enemy(i,52, tex, c , this));
					}
				spawnDone = true;
			}
			if(eb.isEmpty() && spawnDone2 == false){
				this.enemyHitRightBarrier = false;
				this.enemySpeedIncrease = 1.0;
				for(int i = 0; i < (Game.WIDTH * Game.SCALE); i+=64){
					c.addEntity(new Enemy2(i,0, tex, c , this));
				}
				for(int i = 0; i < (Game.WIDTH * Game.SCALE); i+=64){
					c.addEntity(new Enemy2(i,26, tex, c , this));
				}
				for(int i = 0; i < (Game.WIDTH * Game.SCALE); i+=64){
					c.addEntity(new Enemy2(i,52, tex, c , this));
				}
				spawnDone2 = true;
			}
			if(eb.isEmpty() && spawnDone3 == false){
				this.enemyHitRightBarrier = false;
				this.enemySpeedIncrease = 1.2;
				for(int i = 0; i < (Game.WIDTH * Game.SCALE); i+=64){
					c.addEntity(new Enemy3(i,0, tex, c , this));
				}
				for(int i = 0; i < (Game.WIDTH * Game.SCALE); i+=64){
					c.addEntity(new Enemy3(i,26, tex, c , this));
				}
				for(int i = 0; i < (Game.WIDTH * Game.SCALE); i+=64){
					c.addEntity(new Enemy3(i,52, tex, c , this));
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
				if(spawnBulletBill) {
					if((enemyHitRightBarrier ||bulletBillSpawnSmokeR.getCount() != 0)  && bulletBillSpawnSmokeL.getCount() == 0)
						this.bulletBillSpawnSmokeR.drawAnimation(g, eb.getLast().getX()+64,eb.getLast().getY() + 55 -8, 0);
					else
						this.bulletBillSpawnSmokeL.drawAnimation(g, eb.getLast().getX()-29,eb.getLast().getY() + 55 -8, 0);
				}
			}
			if(userHasPaused){
				if((System.currentTimeMillis() % 500 == 0) && System.currentTimeMillis() > visualPauseTimer)
					visualPauseTimer = System.currentTimeMillis() + 500;
				if(visualPauseTimer > System.currentTimeMillis() || pauseSoundFXTimer > System.currentTimeMillis()){
					bb.draw(g2d);													//BLOCKS
					p.render(g);
					c.render(g);
					g.drawImage(marioLives, 0, (Game.HEIGHT * 2), null);
					if(spawnBulletBill) {
						if((enemyHitRightBarrier ||bulletBillSpawnSmokeR.getCount() != 0)  && bulletBillSpawnSmokeL.getCount() == 0)
							this.bulletBillSpawnSmokeR.drawAnimation(g, eb.getLast().getX()+64,eb.getLast().getY() + 55 -8, 0);
						else
							this.bulletBillSpawnSmokeL.drawAnimation(g, eb.getLast().getX()-29,eb.getLast().getY() + 55 -8, 0);
					}
				}
				mouseLocator.locateMouse();
				HUD.clickyButton(g, resumeTitle, resumeTitleGlow, resumeTitleClicked, Game.resumeHighlighted, Game.resumeClicked, Game.backOnResume, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH / 2 + 80, 100);
				HUD.clickyButton(g, homeTitle, homeTitleGlow, homeTitleClicked, Game.homeHighlighted, Game.homeClicked, Game.backOnHome, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH / 2 + 114, 200);
				HUD.clickyButton(g, exitTitle, exitTitleGlow, exitTitleClicked, Game.exitHighlighted, Game.exitClicked, Game.backOnExit, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH / 2 + 121, 300);
				/*
				if(!this.resumeHighlighted)
					g.drawImage(resumeTitle,Game.WIDTH / 2 + 80, 100,null);
				else
					g.drawImage(resumeTitleGlow,Game.WIDTH / 2 + 80, 100,null);
				if(!this.homeHighlighted)
					g.drawImage(homeTitle,Game.WIDTH / 2 + 114, 200,null);
				else
					g.drawImage(homeTitleGlow,Game.WIDTH / 2 + 114, 200,null);
				if(!this.exitHighlighted)
					g.drawImage(exitTitle, Game.WIDTH / 2 + 121, 300, null);
				else
					g.drawImage(exitTitleGlow, Game.WIDTH / 2 + 121, 300, null);
					*/
				if(Game.keysAreInUse) {
					if(Game.resumeHighlighted || Game.homeHighlighted || Game.exitHighlighted ||
					   Game.resumeClicked || Game.homeClicked || Game.exitClicked) {
						Game.resumeHighlighted = false;
						Game.homeHighlighted = false;
						Game.exitHighlighted = false;
						Game.resumeClicked = false;
						Game.homeClicked = false;
						Game.exitClicked = false;
					}
					if(!this.pauseSoundFXSoundLoop.clipIsActive() && Game.enterButtonPushedDown && !Game.escapePressedNegateAction) {
						switch(Game.selectorButtonPosition) {
						case 0:
							g.drawImage(resumeTitleSelectedClicked,Game.WIDTH / 2 + 80 -18, 100 -18,null);
							g.drawImage(resumeTitleClicked,Game.WIDTH / 2 + 80, 100,null);
							break;
						case 1:
							g.drawImage(homeTitleSelectedClicked,Game.WIDTH / 2 + 114 -18, 200 -18,null);
							g.drawImage(homeTitleClicked,Game.WIDTH / 2 + 114, 200,null);
							break;
						case 2: 
							g.drawImage(playTitleSelectedClicked,Game.WIDTH / 2 + 121 -18, 300 -18,null);
							g.drawImage(exitTitleClicked,Game.WIDTH / 2 + 121, 300,null);
							break;
						}
					}
					else if(this.pauseSoundFXSoundLoop.clipIsActive() || Game.enterButtonPushedDown && Game.escapePressedNegateAction) {
						switch(Game.selectorButtonPosition) {
						case 0:
							g.drawImage(resumeTitleSelectedNormal,Game.WIDTH / 2 + 80 -18, 100 -18,null);
							g.drawImage(resumeTitle,Game.WIDTH / 2 + 80, 100,null);
							break;
						case 1:
							g.drawImage(homeTitleSelectedNormal,Game.WIDTH / 2 + 114 -18, 200 -18,null);
							g.drawImage(homeTitle,Game.WIDTH / 2 + 114, 200,null);
							break;
						case 2: 
							g.drawImage(playTitleSelectedNormal,Game.WIDTH / 2 + 121 -18, 300 -18,null);
							g.drawImage(exitTitle,Game.WIDTH / 2 + 121, 300,null);
							break;
						}
					}
					else if(!this.pauseSoundFXSoundLoop.clipIsActive()) {
						switch(Game.selectorButtonPosition) {
							case 0:
								g.drawImage(resumeTitleSelected,Game.WIDTH / 2 + 80 -18, 100 -18,null);
								g.drawImage(resumeTitleGlow,Game.WIDTH / 2 + 80, 100,null);
								break;
							case 1:
								g.drawImage(homeTitleSelected,Game.WIDTH / 2 + 114 -18, 200 -18,null);
								g.drawImage(homeTitleGlow,Game.WIDTH / 2 + 114, 200,null);
								break;
							case 2: 
								g.drawImage(playTitleSelected,Game.WIDTH / 2 + 121 -18, 300 -18,null);
								g.drawImage(exitTitleGlow,Game.WIDTH / 2 + 121, 300,null);
								break;
						}
					}
				}
			}
			
		//if (i == rand.nextInt())
			if(ec.isEmpty() && !eb.isEmpty() && spawnDone4 == false){	
				boolean spawnShell = true;
				Random rand = new Random();
				int i = rand.nextInt(eb.size());
				if(eb.get(i).getEntityBDead() == false) {
					for(int j =0; j < eb.size(); j++) {
						if((eb.get(i).getX() <= eb.get(j).getX() + eb.get(j).getWidth() && eb.get(i).getX() >= eb.get(j).getX() && eb.get(j).getEntityBDead() == false) ||
						(eb.get(i).getX() + eb.get(i).getWidth() >= eb.get(j).getX() && eb.get(j).getX() >= eb.get(i).getX() && eb.get(j).getEntityBDead() == false) ||
						(eb.get(i).getX() == eb.get(j).getX() && eb.get(j).getEntityBDead() == false)) {
							if(eb.get(i).getY() >= eb.get(j).getY())
								spawnShell = true;
							else {
								spawnShell = false;
								break;
							}
						}
					}
					if (spawnShell) {
						for(int k = eb.size() - 1; k >= 0; k--) {
							if((eb.get(i).getX() <= eb.get(k).getX() + eb.get(k).getWidth() && eb.get(i).getX() > eb.get(k).getX() && eb.get(k).getEntityBDead() == false) ||
							(eb.get(i).getX() + eb.get(i).getWidth() >= eb.get(k).getX() && eb.get(k).getX() >= eb.get(i).getX() && eb.get(k).getEntityBDead() == false) ||
							(eb.get(i).getX() == eb.get(k).getX() && eb.get(k).getEntityBDead() == false)) {
								if(eb.get(i).getY() >= eb.get(k).getY())
									spawnShell = true;
								else {
									spawnShell = false;
									break;
								}
							}
						}
					}
					if (spawnShell)
						c.addEntity(new GreenShell(eb.get(i).getX(),eb.get(i).getY() - 32,tex, this));
				}
			}
			if(spawnDone4 == true){												//Spawning Bowser Mechanics
				hud.render(g);
				if((int)HUD.getTimer1() <= 0){
					//SPAWN BULLET BILLS
					if(bowserBulletBillSpawningTimer < System.currentTimeMillis()) {
						Random rand = new Random();
						int i = rand.nextInt(2);//20000
						if(i == 1 && ec.size() < 6 && !spawnBulletBill){
							bulletBillSpawnSmokeL.nextFrame();
							bulletBillSpawnSmokeL.setCount(0);
							bulletBillSpawnSmokeR.nextFrame();
							bulletBillSpawnSmokeR.setCount(0);
							spawnBulletBill = true;
							//if(enemyHitRightBarrier)
								//this.bulletBillSpawnSmokeR.runAnimation();
								//c.addEntity(new BulletBill(eb.getLast().getX()+64,eb.getLast().getY() + 55 -8,tex, this));
							//else
								//c.addEntity(new BulletBill(eb.getLast().getX()-4,eb.getLast().getY() + 55 -8,tex, this));
						}
						bowserBulletBillSpawningTimer = System.currentTimeMillis() + 2000;
					}
					if((bulletBillSpawnSmokeL.getCount() == 2 || bulletBillSpawnSmokeR.getCount() == 2) && !spawn1BulletBillAtATime) {
						if(bulletBillSpawnSmokeR.getCount() == 2)
							c.addEntity(new BulletBill(eb.getLast().getX()+64,eb.getLast().getY() + 55 -8,tex, this));
						else { 
							if(eb.getLast().getX()-4 < p.getX())
								c.addEntity(new BulletBill(eb.getLast().getX()-14,eb.getLast().getY() + 55 -8,tex, this));
							else
								c.addEntity(new BulletBill(eb.getLast().getX()-4,eb.getLast().getY() + 55 -8,tex, this));
						}
						spawn1BulletBillAtATime = true;
					}
					else if(bulletBillSpawnSmokeL.getCount() == 10 || bulletBillSpawnSmokeR.getCount() == 10) {
						bulletBillSpawnSmokeL.nextFrame();
						bulletBillSpawnSmokeL.setCount(0);
						bulletBillSpawnSmokeR.nextFrame();
						bulletBillSpawnSmokeR.setCount(0);
						spawnBulletBill = false;
						spawn1BulletBillAtATime = false;
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
			int j = rand.nextInt(4);//400000
			if(j < 2 && ed.size() < 1 && p.getMarioInvincible() == false && hud.getItemObtained() == false){
				int k = rand.nextInt(2);
				if(k == 0){
					if(j == 0)
						c.addEntity(new ChainChompItem(-16,this.playerY() - 32,tex, this));
					else if(j == 1)
						c.addEntity(new ChainChompItem(-16,this.playerY() - 32,tex, this));
				}
				else{
					if(j == 0)
						c.addEntity(new ChainChompItem((Game.WIDTH * 2) + 16,this.playerY() - 32,tex, this));
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
				if(LeaderboardController.getFromSettings("Total Points: ").equals("")) {
					if(hud.getScore() < 999999)
						LeaderboardController.writeToSettings("Total Points: ", String.valueOf(hud.getScore()));
					else
						LeaderboardController.writeToSettings("Total Points: ", "999999");
				}
				else {
					int i = Integer.valueOf(LeaderboardController.getFromSettings("Total Points: "));
					i += hud.getScore();
					if(i < 999999){}
					else
						i = 999999;
					LeaderboardController.writeToSettings("Total Points: ", String.valueOf(i));
				}
				if(!LeaderboardController.getFromSettings("Total Points: ").equals("")) {
					totalPointsImage = HUD.stringToMario3FontImage(LeaderboardController.getFromSettings("Total Points: "));
					totalPointsImage = Game.resize(HUD.stringToMario3FontImage(LeaderboardController.getFromSettings("Total Points: ")), totalPointsImage.getWidth()/2, totalPointsImage.getHeight()/2);
				}
			}
			hud.render(g);
		}else if(State == STATE.MENU){													//Menu
			//menu.render(g);
			if(!settingsSetup) {
				leaderboard.settingsSetup();
				settingsSetup = true;
			}
			if(menuSoundSet == false){
				Random rand = new Random();
				menuSoundLoopRandomizer = rand.nextInt(2);
				VolumeSlider.adjustVolume(menuSoundLoops, gameSoundLoops);
				this.menuSoundLoops.get(menuSoundLoopRandomizer).loop();
				this.menuSoundLoops.get(menuSoundLoopRandomizer).setSoundLoopBoolean(true);
				menuSoundSet = true;
			}
			if(starExplode) {
				starExplosion.StarExplosionSetup(Game.mx,Game.my);
				starExplosionTimer = System.currentTimeMillis() + 2000;
				starExplode = false;
				//starExplode = false;
			}
			if(System.currentTimeMillis() < starExplosionTimer) {
				starExplosion.Explosion(g);
			}
			//this.addMouseMotionListener(new MouseMotionListener);
			mouseLocator.locateMouse();
			g.drawImage(title, 70, 100, null);
			/*
			if((!this.playHighlighted && !Game.playClicked && !Game.backOnPlay) || Game.mouseIsOffClickedObjectAndHeldDown && !Game.backOnPlay)
				g.drawImage(playTitle, Game.WIDTH / 2 + 120, 200, null);
			else if(!Game.playClicked && !Game.mouseIsOffClickedObjectAndHeldDown && !Game.mouseIsClickedDown)
				g.drawImage(playTitleGlow, Game.WIDTH / 2 + 120, 200, null);
			else if(!Game.mouseIsOffClickedObjectAndHeldDown && Game.playClicked || Game.backOnPlay && Game.playClicked)
				g.drawImage(playTitleClicked, Game.WIDTH / 2 + 120, 200, null);
				*/
			
			//MAKE THIS INTO FUNCTION (Static HUD?)
			/*
			if(!Game.mouseIsOffClickedObjectAndHeldDown && Game.playClicked || Game.backOnPlay && Game.playClicked)
				g.drawImage(playTitleClicked, Game.WIDTH / 2 + 120, 200, null);
			else if(this.playHighlighted && !Game.playClicked && !Game.mouseIsOffClickedObjectAndHeldDown && !Game.mouseIsClickedDown)
				g.drawImage(playTitleGlow, Game.WIDTH / 2 + 120, 200, null);
			else
				g.drawImage(playTitle, Game.WIDTH / 2 + 120, 200, null);
				*/
			//MAKE THIS INTO FUNCTION
			HUD.clickyButton(g, playTitle, playTitleGlow, playTitleClicked, Game.playHighlighted, Game.playClicked, Game.backOnPlay, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH / 2 + 120, 200);
			HUD.clickyButton(g, shopTitle, shopTitleGlow, shopTitleClicked, Game.shopHighlighted, Game.shopClicked, Game.backOnShop, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH / 2 + 120, 300);
			HUD.clickyButton(g, exitTitle, exitTitleGlow, exitTitleClicked, Game.exitHighlighted, Game.exitClicked, Game.backOnExit, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH / 2 + 120, 400);
			HUD.clickyButton(g, helpTitle, helpTitleGlow, helpTitleClicked, Game.helpHighlighted, Game.helpClicked, Game.backOnHelp, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 54, 20);
			HUD.clickyButton(g, settingsTitle, settingsTitleGlow, settingsTitleClicked, Game.settingsHighlighted, Game.settingsClicked, Game.backOnSettings, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 280 + 32, 20);
			HUD.clickyButton(g, leaderboardTitle, leaderboardTitleGlow, leaderboardTitleClicked, Game.leaderboardHighlighted, Game.leaderboardClicked, Game.backOnLeaderboard, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH /2 + 380, 20);
			
			if(Game.keysAreInUse) {
				if(Game.playHighlighted || Game.shopHighlighted || Game.exitHighlighted ||
				   Game.helpHighlighted || Game.settingsHighlighted || Game.leaderboardHighlighted ||
				   Game.playClicked || Game.shopClicked || Game.exitClicked ||
				   Game.helpClicked || Game.settingsClicked || Game.leaderboardClicked) {
					Game.playHighlighted = false;
					Game.shopHighlighted = false;
					Game.exitHighlighted = false;
					Game.helpHighlighted = false;
					Game.settingsHighlighted = false;
					Game.leaderboardHighlighted = false;
					Game.playClicked = false;
					Game.shopClicked = false;
					Game.exitClicked = false;
					Game.helpClicked = false;
					Game.settingsClicked = false;
					Game.leaderboardClicked = false;
				}
				if(Game.enterButtonPushedDown && !Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -3:
						g.drawImage(leaderboardTitleSelectedClicked,Game.WIDTH /2 + 380 -7, 20 -7,null);
						g.drawImage(leaderboardTitleClicked,Game.WIDTH /2 + 380, 20,null);
						break;
					case -2:
						g.drawImage(settingsTitleSelectedClicked, 280 + 32 - 7, 20 -7,null);
						g.drawImage(settingsTitleClicked,280 + 32, 20,null);
						break;
					case -1:
						g.drawImage(helpTitleSelectedClicked,54 -7, 20 -7,null);
						g.drawImage(helpTitleClicked,54, 20,null);
						break;
					case 0:
						g.drawImage(playTitleSelectedClicked,Game.WIDTH / 2 + 120 -18, 200 -18,null);
						g.drawImage(playTitleClicked,Game.WIDTH / 2 + 120, 200,null);
						break;
					case 1:
						g.drawImage(playTitleSelectedClicked,Game.WIDTH / 2 + 120 -18, 300 -18,null);
						g.drawImage(shopTitleClicked,Game.WIDTH / 2 + 120, 300,null);
						break;
					case 2: 
						g.drawImage(playTitleSelectedClicked,Game.WIDTH / 2 + 120 -18, 400 -18,null);
						g.drawImage(exitTitleClicked,Game.WIDTH / 2 + 120, 400,null);
						break;
					}
				}
				else if(Game.enterButtonPushedDown && Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -3:
						g.drawImage(leaderboardTitleSelectedNormal,Game.WIDTH /2 + 380 -7, 20 -7,null);
						g.drawImage(leaderboardTitle,Game.WIDTH /2 + 380, 20,null);
						break;
					case -2:
						g.drawImage(settingsTitleSelectedNormal,280 + 32 -7, 20 -7,null);
						g.drawImage(settingsTitle,280 + 32, 20,null);
						break;
					case -1:
						g.drawImage(helpTitleSelectedNormal,54 -7, 20 -7,null);
						g.drawImage(helpTitle,54, 20,null);
						break;
					case 0:
						g.drawImage(playTitleSelectedNormal,Game.WIDTH / 2 + 120 -18, 200 -18,null);
						g.drawImage(playTitle,Game.WIDTH / 2 + 120, 200,null);
						break;
					case 1:
						g.drawImage(playTitleSelectedNormal,Game.WIDTH / 2 + 120 -18, 300 -18,null);
						g.drawImage(shopTitle,Game.WIDTH / 2 + 120, 300,null);
						break;
					case 2: 
						g.drawImage(playTitleSelectedNormal,Game.WIDTH / 2 + 120 -18, 400 -18,null);
						g.drawImage(exitTitle,Game.WIDTH / 2 + 120, 400,null);
						break;
					}
				}
				else {
					switch(Game.selectorButtonPosition) {
						case -3:
							g.drawImage(leaderboardTitleSelected,Game.WIDTH /2 + 380 -7, 20 -7,null);
							g.drawImage(leaderboardTitle,Game.WIDTH /2 + 380, 20,null);
							break;
						case -2:
							g.drawImage(settingsTitleSelected,280 + 32 -7, 20 -7,null);
							g.drawImage(settingsTitle,280 + 32, 20,null);
							break;
						case -1:
							g.drawImage(helpTitleSelected,54 -7, 20 -7,null);
							g.drawImage(helpTitle,54, 20,null);
							break;
						case 0:
							g.drawImage(playTitleSelected,Game.WIDTH / 2 + 120 -18, 200 -18,null);
							g.drawImage(playTitleGlow,Game.WIDTH / 2 + 120, 200,null);
							break;
						case 1:
							g.drawImage(playTitleSelected,Game.WIDTH / 2 + 120 -18, 300 -18,null);
							g.drawImage(shopTitleGlow,Game.WIDTH / 2 + 120, 300,null);
							break;
						case 2: 
							g.drawImage(playTitleSelected,Game.WIDTH / 2 + 120 -18, 400 -18,null);
							g.drawImage(exitTitleGlow,Game.WIDTH / 2 + 120, 400,null);
							break;
					}
				}
			}
			/*if(!this.helpHighlighted)
				g.drawImage(helpTitle, Game.WIDTH / 2 + 120, 300, null);
			else
				g.drawImage(helpTitleGlow, Game.WIDTH / 2 + 120, 300, null);
			if(!this.exitHighlighted)
				g.drawImage(exitTitle, Game.WIDTH / 2 + 120, 400, null);
			else
				g.drawImage(exitTitleGlow, Game.WIDTH / 2 + 120, 400, null);*/
		}else if (State == STATE.TRANSITION_ENTRANCE){
			if(this.menuSoundLoops.get(this.menuSoundLoopRandomizer).getSoundLoopBoolean() == true){
				this.menuSoundLoops.get(this.menuSoundLoopRandomizer).stop();
				this.menuSoundLoops.get(this.menuSoundLoopRandomizer).setSoundLoopBoolean(false);
				menuSoundSet = false;
			}
			if(starExplode) {
				starExplosion.StarExplosionSetup(Game.mx,Game.my);
				starExplosionTimer = System.currentTimeMillis() + 2000;
				starExplode = false;
				//starExplode = false;
			}
			if(System.currentTimeMillis() < starExplosionTimer) {
				starExplosion.Explosion(g);
			}
			if(skipSequence) {
				if(!spawnDone) {
					if(!c.getEntityB().isEmpty()) {
						for(int i = 0; i <= c.getEntityB().size()-1; i++) {
							c.removeEntity(c.getEntityB().get(i));
							i--;
						}
					}
				}
				Game.State = STATE.GAME;
				skipSequence = false;
				askToSkipSequence = false;
			}
			if(askToSkipSequence) 
				HUD.clickyButton(g, skipTitle, skipTitleGlow, skipTitleClicked, skipHighlighted, skipClicked, backOnSkip, mouseIsOffClickedObjectAndHeldDown, mouseIsClickedDown, Game.WIDTH -73, Game.HEIGHT-32);
			mouseLocator.locateMouse();
			p.render(g);
			c.render(g);
			if(Game.keysAreInUse) {
				if(Game.skipHighlighted) 
					Game.skipHighlighted = false;
				if(Game.enterButtonPushedDown && !Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case 0:
						g.drawImage(skipTitleSelectedClicked,Game.WIDTH -96, Game.HEIGHT-55,null);
						g.drawImage(skipTitleClicked,Game.WIDTH -73, Game.HEIGHT-32,null);
						break;
					}
				}
				else if(Game.enterButtonPushedDown && Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case 0:
						g.drawImage(skipTitleSelectedNormal,Game.WIDTH -96, Game.HEIGHT-55,null);
						g.drawImage(skipTitle,Game.WIDTH -73, Game.HEIGHT-32,null);
						break;
					}
				}
				else {
					switch(Game.selectorButtonPosition) {
						case 0:
							g.drawImage(skipTitleSelected,Game.WIDTH -90, Game.HEIGHT-49,null);
							g.drawImage(skipTitleGlow,Game.WIDTH -73, Game.HEIGHT-32,null);
							break;
					}
				}
			}
			int y = 0;
			int x = 0;
			spawningEnemiesDanceSync = p.getDanceProgressionCount();
			if(p.getDanceProgressionCount() >= 5) {
				spawningEnemiesDanceSync = p.getDanceProgressionCount()+1;
				if(p.getDanceProgressionCount() >= 10) {
					spawningEnemiesDanceSync = p.getDanceProgressionCount()+2;
					if(p.getDanceProgressionCount() >= 15) 
						spawningEnemiesDanceSync = p.getDanceProgressionCount()+3;
				}
			}
			for(int i = 0; i <= spawningEnemiesDanceSync; i++) {
				if(!p.isSpinningAnimationFinished())
					break;
				x = i*64;
				if(i >= 10) {
					y = 26;
					x = (i*64) - 640;
				}
				if(i >= 20) {
					y = 52;
					x = (i*64) - 1280;
				}
				if(spawningEnemiesinTransition == i) {
					if(spawningEnemiesinTransition >= 29)
						spawnDone = true;
					c.addEntity(new Enemy(x, y, tex,this.c,this));
					spawningEnemiesinTransition++;
				}
			}
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
					if(currentlySelectedCharacterSkin == 3) {
						Game.luigiVoices.get(marioVoiceRandomizer).play();
					}
					else {
						Game.marioVoices.get(marioVoiceRandomizer).play();
					}
					marioLetsGoPause = false;
				}
			}
		}else if(State == STATE.TRANSITION_ITEM){
			if(this.gameSoundLoops.get(this.soundRandomizer).clipIsActive()){
				this.gameSoundLoops.get(this.soundRandomizer).stop();
				this.gameSoundLoops.get(this.soundRandomizer).setSoundLoopBoolean(false);
				this.itemSwooshSoundLoop.play();
				switch(currentlySelectedCharacterSkin){
					case 0:
						marioTurningWithItem = new Animation(6, tex.marioItemAnimationBeginning[0],tex.marioItemAnimationBeginning[0],
								tex.marioItemAnimationBeginning[1],tex.marioItemAnimationBeginning[11],tex.marioItemAnimationBeginning[12],
								tex.marioItemAnimationBeginning[13],tex.marioItemAnimationBeginning[14],tex.marioItemAnimationBeginning[15],
								tex.marioItemAnimationBeginning[16],tex.marioItemAnimationBeginning[17]);
						break;
					case 1:
						marioTurningWithItem = new Animation(6, tex.marioItemAnimationBeginning2[0],tex.marioItemAnimationBeginning2[0],
							tex.marioItemAnimationBeginning2[1],tex.marioItemAnimationBeginning2[11],tex.marioItemAnimationBeginning2[12],
							tex.marioItemAnimationBeginning2[13],tex.marioItemAnimationBeginning2[14],tex.marioItemAnimationBeginning2[15],
							tex.marioItemAnimationBeginning2[16],tex.marioItemAnimationBeginning2[17]);
						break;
					case 2:
						marioTurningWithItem = new Animation(6, tex.marioItemAnimationBeginning3[0],tex.marioItemAnimationBeginning3[0],
							tex.marioItemAnimationBeginning3[1],tex.marioItemAnimationBeginning3[11],tex.marioItemAnimationBeginning3[12],
							tex.marioItemAnimationBeginning3[13],tex.marioItemAnimationBeginning3[14],tex.marioItemAnimationBeginning3[15],
							tex.marioItemAnimationBeginning3[16],tex.marioItemAnimationBeginning3[17]);
						break;
					case 3:
						marioTurningWithItem = new Animation(6, tex.marioItemAnimationBeginning4[0],tex.marioItemAnimationBeginning4[0],
							tex.marioItemAnimationBeginning4[1],tex.marioItemAnimationBeginning4[11],tex.marioItemAnimationBeginning4[12],
							tex.marioItemAnimationBeginning4[13],tex.marioItemAnimationBeginning4[14],tex.marioItemAnimationBeginning4[15],
							tex.marioItemAnimationBeginning4[16],tex.marioItemAnimationBeginning4[17]);
						break;
					default:
						break;
				}
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
					//traverseTime = System.currentTimeMillis();
			}
			if(backgroundTraverse < itemBackground.size()-1 && System.currentTimeMillis() % 80 == 0 && traverseTime != System.currentTimeMillis()){
				//System.out.println(backgroundTraverse);
				backgroundTraverse++;
				traverseTime = System.currentTimeMillis();
			}
			else if(itemWaitTimer == 0 && backgroundTraverse >= itemBackground.size()-1)
				itemWaitTimer = System.currentTimeMillis() + 400;
			if(backgroundTraverse < itemBackground.size())
				g.drawImage(itemBackground.get(backgroundTraverse), 0, 0, null);
			if(skipSequence) {
				spawnItem = true;
				enemyHitPauseTimer = System.currentTimeMillis() + 800;
				backgroundTraverse = 0;
				itemWaitTimer = 0;
				itemFlyingTimer1 = 0;
				itemFlyingAwayX = 0;
				itemFlyingAwayY = 0;
				traverseTime = 0;
				marioVoices.get(0).setSoundLoopBoolean(false);
				luigiVoices.get(0).setSoundLoopBoolean(false);
				marioTurningWithItem.setCount(0);
				State = STATE.GAME;
				skipSequence = false;
				askToSkipSequence = false;
			}
			if(askToSkipSequence) 
				HUD.clickyButton(g, skipTitle, skipTitleGlow, skipTitleClicked, skipHighlighted, skipClicked, backOnSkip, mouseIsOffClickedObjectAndHeldDown, mouseIsClickedDown, Game.WIDTH -73, Game.HEIGHT-32);
			mouseLocator.locateMouse();
			if(Game.keysAreInUse) {
				if(Game.skipHighlighted) 
					Game.skipHighlighted = false;
				if(Game.enterButtonPushedDown && !Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case 0:
						g.drawImage(skipTitleSelectedClicked,Game.WIDTH -96, Game.HEIGHT-55,null);
						g.drawImage(skipTitleClicked,Game.WIDTH -73, Game.HEIGHT-32,null);
						break;
					}
				}
				else if(Game.enterButtonPushedDown && Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case 0:
						g.drawImage(skipTitleSelectedNormal,Game.WIDTH -96, Game.HEIGHT-55,null);
						g.drawImage(skipTitle,Game.WIDTH -73, Game.HEIGHT-32,null);
						break;
					}
				}
				else {
					switch(Game.selectorButtonPosition) {
						case 0:
							g.drawImage(skipTitleSelected,Game.WIDTH -90, Game.HEIGHT-49,null);
							g.drawImage(skipTitleGlow,Game.WIDTH -73, Game.HEIGHT-32,null);
							break;
					}
				}
			}
			
			if(itemWaitTimer != 0 && marioTurningWithItem.getCount() < 10 && System.currentTimeMillis() % 16 == 0 && traverseTime != System.currentTimeMillis()){
				marioTurningWithItem.runAnimation();
				traverseTime = System.currentTimeMillis();
			}
			if(itemWaitTimer != 0 && marioVoices.get(0).getSoundLoopBoolean() == false && currentlySelectedCharacterSkin != 3){
				marioVoices.get(0).play();
				marioVoices.get(0).setSoundLoopBoolean(true);
			}
			else if(itemWaitTimer != 0 && luigiVoices.get(0).getSoundLoopBoolean() == false && currentlySelectedCharacterSkin == 3) {
				luigiVoices.get(0).play();
				luigiVoices.get(0).setSoundLoopBoolean(true);
			}
			if(itemWaitTimer != 0 && System.currentTimeMillis() < itemWaitTimer - 100 && traverseTime != System.currentTimeMillis()){
				if(marioTurningWithItem.getCount()<4 && System.currentTimeMillis() % 4 == 0)
					itemFlyingAwayY -= .016;
				itemFlyingTimer1 = System.currentTimeMillis() + 300;
				traverseTime = System.currentTimeMillis();
			}
			else if(System.currentTimeMillis() < itemFlyingTimer1 && traverseTime != System.currentTimeMillis()){
				if(System.currentTimeMillis() % 2 == 0){
					itemFlyingAwayX += .12;
					itemFlyingAwayY += .1;
				}
				if(System.currentTimeMillis() % 3 == 0){
					itemFlyingAwayY += .1;
				}
				traverseTime = System.currentTimeMillis();
			}
			else if(itemFlyingTimer1 != 0 && currentItemImg.getWidth() < 200){
				if(traverseTime != System.currentTimeMillis()){
					if(currentItem.getCount() < 9 && System.currentTimeMillis() % 35 == 0)//MIGHT NEED TO ADD TRAVERSETIME HERE
						currentItemImg = resize(currentItemImg,currentItemImg.getWidth()+20,currentItemImg.getHeight()+20);
						//currentItem.runAnimation();
					itemFlyingAwayX -= 0.8;
					itemFlyingAwayY -= 1;
					traverseTime = System.currentTimeMillis();
				}
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
				traverseTime = 0;
				marioVoices.get(0).setSoundLoopBoolean(false);
				luigiVoices.get(0).setSoundLoopBoolean(false);
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

			if(this.gameSoundLoops.get(this.soundRandomizer).getSoundLoopBoolean() == true){
				this.gameSoundLoops.get(this.soundRandomizer).stop();
				this.gameSoundLoops.get(this.soundRandomizer).setSoundLoopBoolean(false);
				soundSet = false;
			}
			if(this.gameOverIrisSoundLoop.getSoundLoopBoolean() == false) {
				this.gameOverIrisSoundLoop.play();
				this.gameOverIrisSoundLoop.setSoundLoopBoolean(true);
			}
			if(skipSequence) {
				Game.State = STATE.GAMEOVER;
				skipSequence = false;
				askToSkipSequence = false;
			}
			if(askToSkipSequence) 
				HUD.clickyButton(g, skipTitle, skipTitleGlow, skipTitleClicked, skipHighlighted, skipClicked, backOnSkip, mouseIsOffClickedObjectAndHeldDown, mouseIsClickedDown, Game.WIDTH -73, Game.HEIGHT-32);
			mouseLocator.locateMouse();
			p.render(g);
			if(Game.keysAreInUse) {
				if(Game.skipHighlighted) 
					Game.skipHighlighted = false;
				if(Game.enterButtonPushedDown && !Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case 0:
						g.drawImage(skipTitleSelectedClicked,Game.WIDTH -96, Game.HEIGHT-55,null);
						g.drawImage(skipTitleClicked,Game.WIDTH -73, Game.HEIGHT-32,null);
						break;
					}
				}
				else if(Game.enterButtonPushedDown && Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case 0:
						g.drawImage(skipTitleSelectedNormal,Game.WIDTH -96, Game.HEIGHT-55,null);
						g.drawImage(skipTitle,Game.WIDTH -73, Game.HEIGHT-32,null);
						break;
					}
				}
				else {
					switch(Game.selectorButtonPosition) {
						case 0:
							g.drawImage(skipTitleSelected,Game.WIDTH -90, Game.HEIGHT-49,null);
							g.drawImage(skipTitleGlow,Game.WIDTH -73, Game.HEIGHT-32,null);
							break;
					}
				}
			}
			if(youWon == false)
				youWon = true;
			if(p.gameOver == true) {
				State = STATE.GAMEOVER;
				if(LeaderboardController.getFromSettings("Total Points: ").equals("")) {
					if(hud.getScore() < 999999)
						LeaderboardController.writeToSettings("Total Points: ", String.valueOf(hud.getScore()));
					else
						LeaderboardController.writeToSettings("Total Points: ", "999999");
				}
				else {
					int i = Integer.valueOf(LeaderboardController.getFromSettings("Total Points: "));
					i += hud.getScore();
					if(i < 999999){}
					else
						i = 999999;
					LeaderboardController.writeToSettings("Total Points: ", String.valueOf(i));
				}
				if(!LeaderboardController.getFromSettings("Total Points: ").equals("")) {
					totalPointsImage = HUD.stringToMario3FontImage(LeaderboardController.getFromSettings("Total Points: "));
					totalPointsImage = Game.resize(HUD.stringToMario3FontImage(LeaderboardController.getFromSettings("Total Points: ")), totalPointsImage.getWidth()/2, totalPointsImage.getHeight()/2);
				}
				//p.setGameOver(false);
			}
		}else if (State == STATE.GAMEOVER){												//GameOver
			//wait a lil bit
			//bs.show();
			if(this.gameSoundLoops.get(this.soundRandomizer).getSoundLoopBoolean() == true){
				this.gameSoundLoops.get(this.soundRandomizer).stop();
				this.gameSoundLoops.get(this.soundRandomizer).setSoundLoopBoolean(false);
				soundSet = false;
			}
			if(!backToGameOver)
				backToGameOver = true;
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
			if(leaderboard.getVelY() != 0 || leaderboard.getY() != 0 ||
			   leaderboard.getDissapearingImageIsOff() != false || leaderboard.getDissapearingImageY() != 0 ||
			   leaderboard.getLeaderboardBeginningTimer() != 0 || leaderboard.getLeaderboardEndingTimer() != 0) {
					leaderboard.setVelY(0);
					leaderboard.setY(0);
					leaderboard.setDissapearingImageIsOff(false);
					leaderboard.setDissapearingImageY(0);
					leaderboard.setLeaderboardBeginningTimer(0);
					leaderboard.setLeaderboardEndingTimer(0);
			}
			if(starExplode) {
				starExplosion.StarExplosionSetup(Game.mx,Game.my);
				starExplosionTimer = System.currentTimeMillis() + 2000;
				starExplode = false;
				//starExplode = false;
			}
			if(System.currentTimeMillis() < starExplosionTimer) {
				starExplosion.Explosion(g);
			}
			numberOfFireBallsShot = numberOfFireBallsShot - numberOfFireBallsShotDecoy;
			mouseLocator.locateMouse();
			g.drawImage(gameOverTitle, 170, 100, null);									//Buttons
			HUD.clickyButton(g, playTitle, playTitleGlow, playTitleClicked, Game.playHighlighted, Game.playClicked, Game.backOnPlay, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH / 2 + 120, 200);
			HUD.clickyButton(g, homeTitle, homeTitleGlow, homeTitleClicked, Game.homeHighlighted, Game.homeClicked, Game.backOnHome, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH / 2 + 114, 300);
			HUD.clickyButton(g, exitTitle, exitTitleGlow, exitTitleClicked, Game.exitHighlighted, Game.exitClicked, Game.backOnExit, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH / 2 + 120, 400);
			HUD.clickyButton(g, setScoreTitle, setScoreTitleGlow, setScoreTitleClicked, Game.setScoreHighlighted, Game.setScoreClicked, Game.backOnSetScore, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 40, 20);
			HUD.clickyButton(g, leaderboardTitle, leaderboardTitleGlow, leaderboardTitleClicked, Game.leaderboardHighlighted, Game.leaderboardClicked, Game.backOnLeaderboard, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,Game.WIDTH /2 + 380, 20);
			
			/*
			if(!this.playHighlighted)
				g.drawImage(playTitle, Game.WIDTH / 2 + 120, 200, null);
			else
				g.drawImage(playTitleGlow, Game.WIDTH / 2 + 120, 200, null);
			if(!this.helpHighlighted)
				g.drawImage(helpTitle, Game.WIDTH / 2 + 120, 300, null);
			else
				g.drawImage(helpTitleGlow, Game.WIDTH / 2 + 120, 300, null);
			if(!this.exitHighlighted)
				g.drawImage(exitTitle, Game.WIDTH / 2 + 120, 400, null);
			else
				g.drawImage(exitTitleGlow, Game.WIDTH / 2 + 120, 400, null);
			if(!this.setScoreHighlighted)
				g.drawImage(setScoreTitle, 40, 20, null);
			else
				g.drawImage(setScoreTitleGlow, 40, 20, null);
			if(!this.leaderboardHighlighted)
				g.drawImage(leaderboardTitle, Game.WIDTH /2 + 380, 20, null);
			else
				g.drawImage(leaderboardTitleGlow, Game.WIDTH /2 + 380, 20, null);
				*/
			if(Game.keysAreInUse) {
				if(Game.setScoreHighlighted || Game.leaderboardHighlighted || Game.playHighlighted ||
				   Game.homeHighlighted || Game.exitHighlighted ||
				   Game.setScoreClicked || Game.leaderboardClicked || Game.playClicked ||
				   Game.homeClicked || Game.exitClicked	) {
					Game.setScoreHighlighted = false;
					Game.leaderboardHighlighted = false;
					Game.playHighlighted = false;
					Game.homeHighlighted = false;
					Game.exitHighlighted = false;
					Game.setScoreClicked = false;
					Game.leaderboardClicked = false;
					Game.playClicked = false;
					Game.homeClicked = false;
					Game.exitClicked = false;
				}
				if(Game.enterButtonPushedDown && !Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -2:
						g.drawImage(leaderboardTitleSelectedClicked,Game.WIDTH /2 + 380 -7, 20 -7,null);
						g.drawImage(leaderboardTitleClicked,Game.WIDTH /2 + 380, 20,null);
						break;
					case -1:
						g.drawImage(setScoreTitleSelectedClicked,40 -7, 20 -7,null);
						g.drawImage(setScoreTitleClicked,40, 20,null);
						break;
					case 0:
						g.drawImage(playTitleSelectedClicked,Game.WIDTH / 2 + 120 -18, 200 -18,null);
						g.drawImage(playTitleClicked,Game.WIDTH / 2 + 120, 200,null);
						break;
					case 1:
						g.drawImage(homeTitleSelectedClicked,Game.WIDTH / 2 + 120 -23, 300 -18,null);
						g.drawImage(homeTitleClicked,Game.WIDTH / 2 + 114, 300,null);
						break;
					case 2: 
						g.drawImage(playTitleSelectedClicked,Game.WIDTH / 2 + 120 -18, 400 -18,null);
						g.drawImage(exitTitleClicked,Game.WIDTH / 2 + 120, 400,null);
						break;
					}
				}
				else if(Game.enterButtonPushedDown && Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -2:
						g.drawImage(leaderboardTitleSelectedNormal,Game.WIDTH /2 + 380 -7, 20 -7,null);
						g.drawImage(leaderboardTitle,Game.WIDTH /2 + 380, 20,null);
						break;
					case -1:
						g.drawImage(setScoreTitleSelectedNormal,40 -7, 20 -7,null);
						g.drawImage(setScoreTitle,40, 20,null);
						break;
					case 0:
						g.drawImage(playTitleSelectedNormal,Game.WIDTH / 2 + 120 -18, 200 -18,null);
						g.drawImage(playTitle,Game.WIDTH / 2 + 120, 200,null);
						break;
					case 1:
						g.drawImage(homeTitleSelectedNormal,Game.WIDTH / 2 + 120 -23, 300 -18,null);
						g.drawImage(homeTitle,Game.WIDTH / 2 + 114, 300,null);
						break;
					case 2: 
						g.drawImage(playTitleSelectedNormal,Game.WIDTH / 2 + 120 -18, 400 -18,null);
						g.drawImage(exitTitle,Game.WIDTH / 2 + 120, 400,null);
						break;
					}
				}
				else {
					switch(Game.selectorButtonPosition) {
					case -2:
						g.drawImage(leaderboardTitleSelected,Game.WIDTH /2 + 380 -7, 20 -7,null);
						g.drawImage(leaderboardTitleGlow,Game.WIDTH /2 + 380, 20,null);
						break;
					case -1:
						g.drawImage(setScoreTitleSelected,40 -7, 20 -7,null);
						g.drawImage(setScoreTitleGlow,40, 20,null);
						break;
					case 0:
						g.drawImage(playTitleSelected,Game.WIDTH / 2 + 120 -18, 200 -18,null);
						g.drawImage(playTitleGlow,Game.WIDTH / 2 + 120, 200,null);
						break;
					case 1:
						g.drawImage(homeTitleSelected,Game.WIDTH / 2 + 120 -23, 300 -18,null);
						g.drawImage(homeTitleGlow,Game.WIDTH / 2 + 114, 300,null);
						break;
					case 2: 
						g.drawImage(playTitleSelected,Game.WIDTH / 2 + 120 -18, 400 -18,null);
						g.drawImage(exitTitleGlow,Game.WIDTH / 2 + 120, 400,null);
						break;
					}
				}
			}
			//gameOver.render(g);
			
			//g.dispose();
			//g2d.dispose();
		}else if(State == STATE.SET_SCORE) {
			if(starExplode) {
				starExplosion.StarExplosionSetup(Game.mx,Game.my);
				starExplosionTimer = System.currentTimeMillis() + 2000;
				starExplode = false;
				//starExplode = false;
			}
			if(System.currentTimeMillis() < starExplosionTimer) {
				starExplosion.Explosion(g);
			}
			mouseLocator.locateMouse();
			g.drawImage(setScoreTitleBigger, Game.WIDTH / 2 + 35 , 20, null);
			HUD.clickyButton(g, backButtonTitle, backButtonTitleGlow, backButtonTitleClicked, Game.backHighlighted, Game.backClicked, Game.backOnBack, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 40, 20);
			/*
			if(!this.backHighlighted)
				g.drawImage(backButtonTitle, 40, 20, null);
			else
				g.drawImage(backButtonTitleGlow, 40, 20, null);
			*/
			if(Game.keysAreInUse) {
				if(Game.backHighlighted || Game.backClicked) {
					Game.backHighlighted = false;
					Game.backClicked = false;
				}
				if(Game.enterButtonPushedDown && !Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -1:
						g.drawImage(backTitleSelectedClicked,40 -7, 20 -7,null);
						g.drawImage(backButtonTitleClicked,40, 20,null);
						break;
					}
				}
				else if(Game.enterButtonPushedDown && Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -1:
						g.drawImage(backTitleSelectedNormal,40 -7, 20 -7,null);
						g.drawImage(backButtonTitle,40, 20,null);
						break;
					}
				}
				else {
					switch(Game.selectorButtonPosition) {
					case -1:
						g.drawImage(backTitleSelected,40 -7, 20 -7,null);
						g.drawImage(backButtonTitleGlow,40, 20,null);
						break;
					}
				}
			}
			if(System.currentTimeMillis() < imageTranslucentTimer2) {
				
			}
			else if(imageTranslucent >= 0.99 && imageTranslucentTimer < System.currentTimeMillis()){
				if(imageStayOn == false) {
					imageTranslucentTimer2 = System.currentTimeMillis() + 1050;
					imageStayOn = true;
				}
				imageTranslucentVelocity += -0.01;
				imageIsGone = false;
			}
			else if(imageTranslucent <= 0.01 && imageTranslucentTimer < System.currentTimeMillis()){
				imageTranslucentVelocity += 0.01;
				imageStayOn = false;
			}
			
			if(imageTranslucent <= 0.01 && imageIsGone == false){
				imageTranslucentTimer = System.currentTimeMillis() + 500;
				imageIsGone = true;
			}
			if(imageTranslucent + imageTranslucentVelocity <= 1 &&
					imageTranslucent + imageTranslucentVelocity >= 0)
				imageTranslucent += imageTranslucentVelocity;
			Graphics2D g2d2 = (Graphics2D)g.create();
			g2d2.setComposite(makeComposite(imageTranslucent));
			if(!Game.keysAreInUse)
				if(200 <= postLetterXPositionBeginning)
					g2d2.drawImage(textIndicator,200+postLetterXPosition,200,null);
				else 
					g2d2.drawImage(textIndicator,postLetterXPositionBeginning+postLetterXPosition,200,null);
			while(postLetter != '=') {
				if(postLetterXPositionBeginning-44 < 0 && Game.WIDTH * Game.SCALE < postLetterXPosition + postLetterXPositionBeginning + 44 && postLetter != '+' && postLetter != '~') {//Max Characters
					postLetter = '=';
					break;
				}
				if(postLetter == '~') {//ENTER
					if(fileScoreWritten == false) {
						leaderboard.fileScoreNotWritten();
						fileScoreWritten = true;
					}
					if(!playerName.isEmpty() && this.scoreEntered == false) {
						leaderboard.getNames().clear();
						leaderboard.getScores().clear();
						leaderboard.setNameDecoy("");
						leaderboard.setScoreDecoy("");
						leaderboard.setStringDecoy("");
						leaderboard.writeScore();
						scoreEntered = true;
						if(leaderboard.getInitalized() == true)
							leaderboard.setInitalized(false);
						Game.selectorButtonPosition = 0;
						State = STATE.GAMEOVER;
						postLetter = '=';
						break;
					}
					else if(this.scoreEntered == true) {
						Game.selectorButtonPosition = 0;
						State = STATE.GAMEOVER;
						postLetter = '=';
						break;
					}
					else {
						postLetter = '=';
						break;
					}
				}
				else if(postLetter == '+') {//BACKSPACE
					if(playerName.size() == 1 || playerNameImage == null) {
						playerName.clear();
						playerNameImage = null;
						postLetterXPosition = 0;
						postLetterXPositionBeginning = 200;
						postLetter = '=';
						break;
					}
					deletedLetter = hud.mario3FontCharBufferedImage(playerName.get(playerName.size()-1).charAt(0));
					playerName.remove(playerName.size()-1);
					playerNameImage = Game.cutoffResize(playerNameImage, playerNameImage.getWidth() - deletedLetter.getWidth(), playerNameImage.getHeight());
					postLetterXPosition = playerNameImage.getWidth();
					postLetter = '=';
					break;
				}
				playerNameImage = hud.attachImages(playerNameImage, hud.mario3FontCharBufferedImage(postLetter));
				playerName.add(String.valueOf(postLetter));
				deletedLetter = hud.mario3FontCharBufferedImage(playerName.get(playerName.size()-1).charAt(0));
				if(Game.WIDTH * Game.SCALE < postLetterXPosition + 244 && !(postLetterXPositionBeginning-44 < 0)) {//(200 = starting position  & 44 to space it out)
					postLetterXPositionBeginning = postLetterXPositionBeginning - deletedLetter.getWidth();
					postLetterXPosition = playerNameImage.getWidth();
				}
				else
					postLetterXPosition = playerNameImage.getWidth();
				postLetter = '=';
			}
			g.drawImage(playerNameImage, postLetterXPositionBeginning, 200, null);
		}else if(State == STATE.LEADERBOARD) {
			if(Game.selectorButtonPosition != -1)
				Game.selectorButtonPosition = -1;
			if(starExplode) {
				starExplosion.StarExplosionSetup(Game.mx,Game.my);
				starExplosionTimer = System.currentTimeMillis() + 2000;
				starExplode = false;
				//starExplode = false;
			}
			if(System.currentTimeMillis() < starExplosionTimer) {
				starExplosion.Explosion(g);
			}
			mouseLocator.locateMouse();
			g.drawImage(leaderboardTitleBigger, Game.WIDTH / 2, 20, null);
			HUD.clickyButton(g, backButtonTitle, backButtonTitleGlow, backButtonTitleClicked, Game.backHighlighted, Game.backClicked, Game.backOnBack, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 40, 20);
			/*
			if(!this.backHighlighted)
				g.drawImage(backButtonTitle, 40, 20, null);
			else
				g.drawImage(backButtonTitleGlow, 40, 20, null);
				*/
			if(Game.keysAreInUse) {
				if(Game.backHighlighted || Game.backClicked) {
					Game.backHighlighted = false;
					Game.backClicked = false;
				}
				if(Game.enterButtonPushedDown && !Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -1:
						g.drawImage(backTitleSelectedClicked,40 -7, 20 -7,null);
						g.drawImage(backButtonTitleClicked,40, 20,null);
						break;
					}
				}
				else if(Game.enterButtonPushedDown && Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -1:
						g.drawImage(backTitleSelectedNormal,40 -7, 20 -7,null);
						g.drawImage(backButtonTitle,40, 20,null);
						break;
					}
				}
				else {
					switch(Game.selectorButtonPosition) {
					case -1:
						g.drawImage(backTitleSelected,40 -7, 20 -7,null);
						g.drawImage(backButtonTitleGlow,40, 20,null);
						break;
					}
				}
			}
				
			if(leaderboard.getInitalized() == false) {
				leaderboard.initialize();
			}
			leaderboard.drawLeaderboard(g,this.getLeaderboardImage());

			
		}else if(State == STATE.SHOP) {
			if(skinNumber == null) {
				skinNumber = resize(HUD.stringToMario3FontImage(Integer.toString(characterSkinPosition+1)), 10, 10);
				trackNumber = resize(HUD.stringToMario3FontImage(Integer.toString(trackPosition+1)), 10, 10);
				fireballNumber = resize(HUD.stringToMario3FontImage(Integer.toString(fireballPosition+1)), 10, 10);
				itemNumber = resize(HUD.stringToMario3FontImage(Integer.toString(itemPosition+1)), 10, 10);
				//LeaderboardController.writeToSettings("Total Score: ", "100");
				//System.out.println(LeaderboardController.getFromSettings("Total Score: "));
				if(writeOnceToSettings){
					if(writeOnceProperty.equals("") || writeOnceString.equals("")){
						writeOnceProperty = "";
						writeOnceString = "";
						writeOnceToSettings = false;
					}
					else{
						if(writeOnceToSettingswithPoints && !Game.writeOnceUnlock.equals("")){
							LeaderboardController.writeToSettings("Total Points: ", Integer.toString(totalPoints));
							LeaderboardController.writeToSettings(Game.writeOnceUnlock, "true");
							Game.writeOnceUnlock = "";
							writeOnceToSettingswithPoints = false;
						}
						LeaderboardController.writeToSettings(writeOnceProperty, writeOnceString);
						if((writeOnceProperty.toLowerCase().contains("skin".toLowerCase()))) 
							p.changeAnimations(characterSkinPosition);
						writeOnceProperty = "";
						writeOnceString = "";
						writeOnceToSettings = false;
					}
				}
				if(!settingsSetup){
					LeaderboardController.gameUnlocksToSettings();
					leaderboard.settingsSetup();
					settingsSetup = true;
				}
				if(!LeaderboardController.getFromSettings("Total Points: ").equals("")) {
					totalPointsImage = HUD.stringToMario3FontImage(LeaderboardController.getFromSettings("Total Points: "));
					totalPointsImage = Game.resize(HUD.stringToMario3FontImage(LeaderboardController.getFromSettings("Total Points: ")), totalPointsImage.getWidth()/2, totalPointsImage.getHeight()/2);
				}
			}
			if(writeOnceToSettings){
				if(writeOnceProperty.equals("") || writeOnceString.equals("")){
					writeOnceProperty = "";
					writeOnceString = "";
					writeOnceToSettings = false;
				}
				else{
					if(writeOnceToSettingswithPoints && !Game.writeOnceUnlock.equals("")){
						LeaderboardController.writeToSettings("Total Points: ", Integer.toString(totalPoints));
						LeaderboardController.writeToSettings(Game.writeOnceUnlock, "true");
						Game.writeOnceUnlock = "";
						writeOnceToSettingswithPoints = false;
					}
					LeaderboardController.writeToSettings(writeOnceProperty, writeOnceString);
					if(writeOnceProperty.toLowerCase().contains("skin".toLowerCase())) 
						p.changeAnimations(characterSkinPosition);
					writeOnceProperty = "";
					writeOnceString = "";
					writeOnceToSettings = false;
				}
			}
			if(!settingsSetup) {
				LeaderboardController.gameUnlocksToSettings();
				leaderboard.settingsSetup();
				settingsSetup = true;
			}
			if(starExplode) {
				starExplosion.StarExplosionSetup(Game.mx,Game.my);
				starExplosionTimer = System.currentTimeMillis() + 2000;
				starExplode = false;
				//starExplode = false;
			}
			if(System.currentTimeMillis() < starExplosionTimer) {
				starExplosion.Explosion(g);
			}
			g.drawImage(shopTitle, Game.WIDTH - 54, 20, null);
			g.drawImage(skinTitle, 20, 120, null);
			g.drawImage(tracksTitle, 20, 220, null);
			g.drawImage(fireballsTitle, 20, 320, null);
			g.drawImage(itemsTitle, 20, 420, null);
			if(!LeaderboardController.getFromSettings("Total Points: ").equals("")) {
				g.drawImage(totalPointsImage, Game.WIDTH * Game.SCALE - totalPointsImage.getWidth() - 60, 20, null);
				g.drawImage(ShopController.pointsImage,Game.WIDTH * Game.SCALE - 55, 20 + totalPointsImage.getHeight()/2 -3, null);
			}
			if(currentlySelectedCharacterSkin == characterSkinPosition)
				g.drawImage(currentlySelected10x10,Game.WIDTH -2, 120-19,null);
			g.drawImage(skinNumber,Game.WIDTH + 2, 120-15,null);
			if(currentlySelectedTrack == trackPosition)
				g.drawImage(currentlySelected10x10,Game.WIDTH -2, 220-19,null);
			g.drawImage(trackNumber,Game.WIDTH + 2, 220-15,null);
			if(currentlySelectedFireball == fireballPosition)
				g.drawImage(currentlySelected10x10,Game.WIDTH -2, 320-19,null);
			g.drawImage(fireballNumber,Game.WIDTH + 2, 320-15,null);
			//if(currentlySelectedItem == itemPosition)
			if(!currentItemLocked)
				g.drawImage(currentlySelected10x10,Game.WIDTH -2, 420-19,null);
			g.drawImage(itemNumber,Game.WIDTH + 2, 420-15,null);
			mouseLocator.locateMouse();
			HUD.clickyButton(g, backButtonTitle, backButtonTitleGlow, backButtonTitleClicked, Game.backHighlighted, Game.backClicked, Game.backOnBack, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 40, 20);
			HUD.clickyButton(g, arrowL, arrowLGlow, arrowLClicked, Game.arrowL1Highlighted, Game.arrowL1Clicked, Game.backOnArrowL1, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH  - 48, 120);
			HUD.clickyButton(g, arrowR, arrowRGlow, arrowRClicked, Game.arrowR1Highlighted, Game.arrowR1Clicked, Game.backOnArrowR1, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH  + 48, 120);
			HUD.clickyButton(g, setTitle, setTitleGlow, setTitleClicked, Game.set1Highlighted, Game.set1Clicked, Game.backOnSet1, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH  + 108, 128);
			HUD.clickyButton(g, arrowL, arrowLGlow, arrowLClicked, Game.arrowL2Highlighted, Game.arrowL2Clicked, Game.backOnArrowL2, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - 48, 220);
			HUD.clickyButton(g, arrowR, arrowRGlow, arrowRClicked, Game.arrowR2Highlighted, Game.arrowR2Clicked, Game.backOnArrowR2, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH + 48, 220);
			HUD.clickyButton(g, setTitle, setTitleGlow, setTitleClicked, Game.set2Highlighted, Game.set2Clicked, Game.backOnSet2, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH  + 108, 228);
			HUD.clickyButton(g, arrowL, arrowLGlow, arrowLClicked, Game.arrowL3Highlighted, Game.arrowL3Clicked, Game.backOnArrowL3, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - 48, 320);
			HUD.clickyButton(g, arrowR, arrowRGlow, arrowRClicked, Game.arrowR3Highlighted, Game.arrowR3Clicked, Game.backOnArrowR3, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH + 48, 320);
			HUD.clickyButton(g, setTitle, setTitleGlow, setTitleClicked, Game.set3Highlighted, Game.set3Clicked, Game.backOnSet3, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH  + 108, 328);
			HUD.clickyButton(g, arrowL, arrowLGlow, arrowLClicked, Game.arrowL4Highlighted, Game.arrowL4Clicked, Game.backOnArrowL4, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - 48, 420);
			HUD.clickyButton(g, arrowR, arrowRGlow, arrowRClicked, Game.arrowR4Highlighted, Game.arrowR4Clicked, Game.backOnArrowR4, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH + 48, 420);
			//HUD.clickyButton(g, setTitle, setTitleGlow, setTitleClicked, Game.set4Highlighted, Game.set4Clicked, Game.backOnSet4, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH  + 108, 428);
			shop.drawShop(g);
			
			if(Game.keysAreInUse) {
				if(Game.arrowL1Highlighted || Game.arrowL2Highlighted || Game.arrowL3Highlighted || Game.arrowL4Highlighted ||
				   Game.arrowR1Highlighted || Game.arrowR2Highlighted || Game.arrowR3Highlighted || Game.arrowR4Highlighted ||
				   Game.set1Highlighted || Game.set2Highlighted || Game.set3Highlighted || Game.set4Highlighted ||
				   Game.buy1Highlighted || Game.buy2Highlighted || Game.buy3Highlighted || Game.buy4Highlighted ||
				   Game.backHighlighted ||
				   Game.arrowL1Clicked || Game.arrowL2Clicked|| Game.arrowL3Clicked || Game.arrowL4Clicked ||
				   Game.arrowR1Clicked || Game.arrowR2Clicked || Game.arrowR3Clicked || Game.arrowR4Clicked ||
				   Game.set1Clicked || Game.set2Clicked || Game.set3Clicked || Game.set4Clicked ||
				   Game.buy1Clicked || Game.buy2Clicked || Game.buy3Clicked || Game.buy4Clicked ||
				   Game.backClicked) {
					Game.arrowL1Highlighted = false;
					Game.arrowL2Highlighted = false;
					Game.arrowL3Highlighted = false;
					Game.arrowL4Highlighted = false;
					Game.arrowR1Highlighted = false;
					Game.arrowR2Highlighted = false;
					Game.arrowR3Highlighted = false;
					Game.arrowR4Highlighted = false;
					Game.set1Highlighted = false;
					Game.set2Highlighted = false;
					Game.set3Highlighted = false;
					Game.set4Highlighted = false;
					Game.buy1Highlighted = false;
					Game.buy2Highlighted = false;
					Game.buy3Highlighted = false;
					Game.buy4Highlighted = false;
					Game.backHighlighted = false;
					Game.arrowL1Clicked = false;
					Game.arrowL2Clicked = false;
					Game.arrowL3Clicked = false;
					Game.arrowL4Clicked = false;
					Game.arrowR1Clicked = false;
					Game.arrowR2Clicked = false;
					Game.arrowR3Clicked = false;
					Game.arrowR4Clicked = false;
					Game.set1Clicked = false;
					Game.set2Clicked = false;
					Game.set3Clicked = false;
					Game.set4Clicked = false;
					Game.buy1Clicked = false;
					Game.buy2Clicked = false;
					Game.buy3Clicked = false;
					Game.buy4Clicked = false;
					Game.backClicked = false;
					
				}
				if(Game.enterButtonPushedDown && !Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -13:
						g.drawImage(setTitleSelectedClicked,Game.WIDTH  + 108 - 7, 228 - 7,null);
						g.drawImage(setTitleClicked,Game.WIDTH  + 108, 228,null);
						break;
					case -12:
						g.drawImage(arrowSelectedClicked,Game.WIDTH  + 48 - 7, 420 - 7,null);
						g.drawImage(arrowRClicked,Game.WIDTH  + 48, 420,null);
						break;
					case -11:
						g.drawImage(buyTitleSelectedClicked,Game.WIDTH - 12, 420,null);
						g.drawImage(buyTitleClicked,Game.WIDTH - 5, 420 + 7,null);
						break;
					case -10:
						g.drawImage(setTitleSelectedClicked,Game.WIDTH  + 108 - 7, 328 - 7,null);
						g.drawImage(setTitleClicked,Game.WIDTH  + 108, 328,null);
						break;
					case -9:
						g.drawImage(arrowSelectedClicked,Game.WIDTH  + 48 - 7, 320 - 7,null);
						g.drawImage(arrowRClicked,Game.WIDTH  + 48, 320,null);
						break;
					case -8:
						g.drawImage(buyTitleSelectedClicked,Game.WIDTH - 12, 320,null);
						g.drawImage(buyTitleClicked,Game.WIDTH - 5, 320 + 7,null);
						break;
					case -7:
						g.drawImage(setTitleSelectedClicked,Game.WIDTH  + 108 - 7, 228 - 7,null);
						g.drawImage(setTitleClicked,Game.WIDTH  + 108, 228,null);
						break;
					case -6:
						g.drawImage(arrowSelectedClicked,Game.WIDTH  + 48 - 7, 220 - 7,null);
						g.drawImage(arrowRClicked,Game.WIDTH  + 48, 220,null);
						break;
					case -5:
						g.drawImage(buyTitleSelectedClicked,Game.WIDTH - 12, 220,null);
						g.drawImage(buyTitleClicked,Game.WIDTH - 5, 220 + 7,null);
						break;
					case -4:
						g.drawImage(setTitleSelectedClicked,Game.WIDTH  + 108 - 7, 128 - 7,null);
						g.drawImage(setTitleClicked,Game.WIDTH  + 108, 128,null);
						break;
					case -3:
						g.drawImage(arrowSelectedClicked,Game.WIDTH  + 48 - 7, 120 - 7,null);
						g.drawImage(arrowRClicked,Game.WIDTH  + 48, 120,null);
						break;
					case -2:
						g.drawImage(buyTitleSelectedClicked,Game.WIDTH - 12, 120,null);
						g.drawImage(buyTitleClicked,Game.WIDTH - 5, 120 + 7,null);
						break;
					case -1:
						g.drawImage(backTitleSelectedClicked,40 -7, 20 -7,null);
						g.drawImage(backButtonTitleClicked,40, 20,null);
						break;
					case 0:
						g.drawImage(arrowSelectedClicked,Game.WIDTH  - 48 - 7, 120 - 7,null);
						g.drawImage(arrowLClicked,Game.WIDTH  - 48, 120,null);
						break;
					case 1:
						g.drawImage(arrowSelectedClicked,Game.WIDTH  - 48 - 7, 220 - 7,null);
						g.drawImage(arrowLClicked,Game.WIDTH  - 48, 220,null);
						break;
					case 2:
						g.drawImage(arrowSelectedClicked,Game.WIDTH  - 48 - 7, 320 - 7,null);
						g.drawImage(arrowLClicked,Game.WIDTH  - 48, 320,null);
						break;
					case 3:
						g.drawImage(arrowSelectedClicked,Game.WIDTH  - 48 - 7, 420 - 7,null);
						g.drawImage(arrowLClicked,Game.WIDTH  - 48, 420,null);
						break;
					}
				}
				else if(Game.enterButtonPushedDown && Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -13:
						g.drawImage(setTitleSelectedNormal,Game.WIDTH  + 108 - 7, 428 - 7,null);
						g.drawImage(setTitle,Game.WIDTH  + 108, 428,null);
						break;
					case -12:
						g.drawImage(arrowSelectedNormal,Game.WIDTH  + 48 - 7, 420 - 7,null);
						g.drawImage(arrowR,Game.WIDTH  + 48, 420,null);
						break;
					case -11:
						g.drawImage(buyTitleSelectedNormal,Game.WIDTH - 12, 420,null);
						g.drawImage(buyTitle,Game.WIDTH - 5, 420 + 7,null);
						break;
					case -10:
						g.drawImage(setTitleSelectedNormal,Game.WIDTH  + 108 - 7, 328 - 7,null);
						g.drawImage(setTitle,Game.WIDTH  + 108, 328,null);
						break;
					case -9:
						g.drawImage(arrowSelectedNormal,Game.WIDTH  + 48 - 7, 320 - 7,null);
						g.drawImage(arrowR,Game.WIDTH  + 48, 320,null);
						break;
					case -8:
						g.drawImage(buyTitleSelectedNormal,Game.WIDTH - 12, 320,null);
						g.drawImage(buyTitle,Game.WIDTH - 5, 320 + 7,null);
						break;
					case -7:
						g.drawImage(setTitleSelectedNormal,Game.WIDTH  + 108 - 7, 228 - 7,null);
						g.drawImage(setTitle,Game.WIDTH  + 108, 228,null);
						break;
					case -6:
						g.drawImage(arrowSelectedNormal,Game.WIDTH  + 48 - 7, 220 - 7,null);
						g.drawImage(arrowR,Game.WIDTH  + 48, 220,null);
						break;
					case -5:
						g.drawImage(buyTitleSelectedNormal,Game.WIDTH - 12, 220,null);
						g.drawImage(buyTitle,Game.WIDTH - 5, 220 + 7,null);
						break;
					case -4:
						g.drawImage(setTitleSelectedNormal,Game.WIDTH  + 108 - 7, 128 - 7,null);
						g.drawImage(setTitle,Game.WIDTH  + 108, 128,null);
						break;
					case -3:
						g.drawImage(arrowSelectedNormal,Game.WIDTH  + 48 - 7, 120 - 7,null);
						g.drawImage(arrowR,Game.WIDTH  + 48, 120,null);
						break;
					case -2:
						g.drawImage(buyTitleSelectedNormal,Game.WIDTH - 12, 120,null);
						g.drawImage(buyTitle,Game.WIDTH - 5, 120 + 7,null);
						break;
					case -1:
						g.drawImage(backTitleSelectedNormal,40 -7, 20 -7,null);
						g.drawImage(backButtonTitle,40, 20,null);
						break;
					case 0:
						g.drawImage(arrowSelectedNormal,Game.WIDTH  - 48 - 7, 120 - 7,null);
						g.drawImage(arrowL,Game.WIDTH  - 48, 120,null);
						break;
					case 1:
						g.drawImage(arrowSelectedNormal,Game.WIDTH  - 48 - 7, 220 - 7,null);
						g.drawImage(arrowL,Game.WIDTH  - 48, 220,null);
						break;
					case 2:
						g.drawImage(arrowSelectedNormal,Game.WIDTH  - 48 - 7, 320 - 7,null);
						g.drawImage(arrowL,Game.WIDTH  - 48, 320,null);
						break;
					case 3:
						g.drawImage(arrowSelectedNormal,Game.WIDTH  - 48 - 7, 420 - 7,null);
						g.drawImage(arrowL,Game.WIDTH  - 48, 420,null);
						break;
					}
				}
				else {
					switch(Game.selectorButtonPosition) {
					case -13:
						g.drawImage(setTitleSelected,Game.WIDTH  + 108 - 7, 428 - 7,null);
						g.drawImage(setTitleGlow,Game.WIDTH  + 108, 428,null);
						break;
					case -12:
						g.drawImage(arrowSelected,Game.WIDTH  + 48 - 7, 420 - 7,null);
						g.drawImage(arrowRGlow,Game.WIDTH  + 48, 420,null);
						break;
					case -11:
						g.drawImage(buyTitleSelected,Game.WIDTH - 12, 420,null);
						g.drawImage(buyTitleGlow,Game.WIDTH - 5, 420 + 7,null);
						break;
					case -10:
						g.drawImage(setTitleSelected,Game.WIDTH  + 108 - 7, 328 - 7,null);
						g.drawImage(setTitleGlow,Game.WIDTH  + 108, 328,null);
						break;
					case -9:
						g.drawImage(arrowSelected,Game.WIDTH  + 48 - 7, 320 - 7,null);
						g.drawImage(arrowRGlow,Game.WIDTH  + 48, 320,null);
						break;
					case -8:
						g.drawImage(buyTitleSelected,Game.WIDTH - 12, 320,null);
						g.drawImage(buyTitleGlow,Game.WIDTH - 5, 320 + 7,null);
						break;
					case -7:
						g.drawImage(setTitleSelected,Game.WIDTH  + 108 - 7, 228 - 7,null);
						g.drawImage(setTitleGlow,Game.WIDTH  + 108, 228,null);
						break;
					case -6:
						g.drawImage(arrowSelected,Game.WIDTH  + 48 - 7, 220 - 7,null);
						g.drawImage(arrowRGlow,Game.WIDTH  + 48, 220,null);
						break;
					case -5:
						g.drawImage(buyTitleSelected,Game.WIDTH - 12, 220,null);
						g.drawImage(buyTitleGlow,Game.WIDTH - 5, 220 + 7,null);
						break;
					case -4:
						g.drawImage(setTitleSelected,Game.WIDTH  + 108 - 7, 128 - 7,null);
						g.drawImage(setTitleGlow,Game.WIDTH  + 108, 128,null);
						break;
					case -3:
						g.drawImage(arrowSelected,Game.WIDTH  + 48 - 7, 120 - 7,null);
						g.drawImage(arrowRGlow,Game.WIDTH  + 48, 120,null);
						break;
					case -2:
						g.drawImage(buyTitleSelected,Game.WIDTH - 12, 120,null);
						g.drawImage(buyTitleGlow,Game.WIDTH - 5, 120 + 7,null);
						break;
					case -1:
						g.drawImage(backTitleSelected,40 -7, 20 -7,null);
						g.drawImage(backButtonTitleGlow,40, 20,null);
						break;
					case 0:
						g.drawImage(arrowSelected,Game.WIDTH  - 48 - 7, 120 - 7,null);
						g.drawImage(arrowLGlow,Game.WIDTH  - 48, 120,null);
						break;
					case 1:
						g.drawImage(arrowSelected,Game.WIDTH  - 48 - 7, 220 - 7,null);
						g.drawImage(arrowLGlow,Game.WIDTH  - 48, 220,null);
						break;
					case 2:
						g.drawImage(arrowSelected,Game.WIDTH  - 48 - 7, 320 - 7,null);
						g.drawImage(arrowLGlow,Game.WIDTH  - 48, 320,null);
						break;
					case 3:
						g.drawImage(arrowSelected,Game.WIDTH  - 48 - 7, 420 - 7,null);
						g.drawImage(arrowLGlow,Game.WIDTH  - 48, 420,null);
						break;
					}
				}
			}
		}else if(State == STATE.HELP) {
			if(starExplode) {
				starExplosion.StarExplosionSetup(Game.mx,Game.my);
				starExplosionTimer = System.currentTimeMillis() + 2000;
				starExplode = false;
				//starExplode = false;
			}
			if(System.currentTimeMillis() < starExplosionTimer) {
				starExplosion.Explosion(g);
			}
			mouseLocator.locateMouse();
			HUD.clickyButton(g, backButtonTitle, backButtonTitleGlow, backButtonTitleClicked, Game.backHighlighted, Game.backClicked, Game.backOnBack, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 40, 20);
			if(Game.keysAreInUse) {
				if(Game.backHighlighted ||
				   Game.backClicked) {
					backHighlighted = false;
					backClicked = false;
				}
				if(Game.enterButtonPushedDown && !Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -1:
						g.drawImage(backTitleSelectedClicked,40 -7, 20 -7,null);
						g.drawImage(backButtonTitleClicked,40, 20,null);
						break;
					}
				}
				else if(Game.enterButtonPushedDown && Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -1:
						g.drawImage(backTitleSelectedNormal,40 -7, 20 -7,null);
						g.drawImage(backButtonTitle,40, 20,null);
						break;
					}
				}
				else {
					switch(Game.selectorButtonPosition) {
					case -1:
						g.drawImage(backTitleSelected,40 -7, 20 -7,null);
						g.drawImage(backButtonTitleGlow,40, 20,null);
						break;
					}
				}
			}
		}else if(State == STATE.SETTINGS) {
			if(!settingsSetup) {
				LeaderboardController.gameUnlocksToSettings();
				leaderboard.settingsSetup();
				settingsSetup = true;
			}/*
			if(writeToSettings) {
				if(skipAnimations)
					LeaderboardController.writeToSettings("skipAnimations", "true");
				else
					LeaderboardController.writeToSettings("skipAnimations", "false");
				writeToSettings = false;
			}*/
			if(VolumeSlider.volumeSliderChangingVolume) {
				VolumeSlider.adjustVolume(this.menuSoundLoops,this.gameSoundLoops);
				LeaderboardController.writeToSettings("volumeSliderPosition", Integer.toString(Game.volumeSliderPosition));
			}
			if(!VolumeSlider.volumeSliderSetup) {
				VolumeSlider.x = Game.WIDTH + 68 - 8 - ((5-volumeSliderPosition) *32);
				VolumeSlider.y = 121;
				VolumeSlider.volumeSliderSetup = true;
			}
			if(VolumeSlider.sfxMusicSliderChangingVolume) {
				VolumeSlider.adjustVolume(this.menuSoundLoops,this.gameSoundLoops);
				LeaderboardController.writeToSettings("sfxMusicSliderPosition", Integer.toString(Game.sfxMusicSliderPosition));
			}
			if(!VolumeSlider.sfxMusicSliderSetup) {
				VolumeSlider.x2 = Game.WIDTH + 68 - 8 - ((5-sfxMusicSliderPosition) *32);
				VolumeSlider.y2 = 221;
				VolumeSlider.sfxMusicSliderSetup = true;
			}
			if(starExplode) {
				starExplosion.StarExplosionSetup(Game.mx,Game.my);
				starExplosionTimer = System.currentTimeMillis() + 2000;
				starExplode = false;
				//starExplode = false;
			}
			if(System.currentTimeMillis() < starExplosionTimer) {
				starExplosion.Explosion(g);
			}
			mouseLocator.locateMouse();
			g.drawImage(settingsTitleBigger,Game.WIDTH-128,20,null);
			g.drawImage(emptyVolumeSlider,Game.WIDTH - 69,120,null);
			g.drawImage(emptyVolumeSlider,Game.WIDTH - 69,220,null);
			g.drawImage(volumeTitle,20,120,null);
			g.drawImage(sfxMusicTitle,20,220,null);
			g.drawImage(skipAnimationsTitle,20,320,null);
			HUD.clickyButton(g, backButtonTitle, backButtonTitleGlow, backButtonTitleClicked, Game.backHighlighted, Game.backClicked, Game.backOnBack, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 40, 20);
			HUD.clickyButton(g, arrowL, arrowLGlow, arrowLClicked, Game.arrowL1Highlighted, Game.arrowL1Clicked, Game.backOnArrowL1, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH  - 69 - 40 - 16, 121);
			HUD.clickyButton(g, arrowR, arrowRGlow, arrowRClicked, Game.arrowR1Highlighted, Game.arrowR1Clicked, Game.backOnArrowR1, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH  + 69 + 40, 121);
			HUD.clickyButton(g, volumeSlider, volumeSliderGlow, volumeSliderClicked, Game.volumeSliderHighlighted, Game.volumeSliderBooleanClicked, Game.backOnVolumeSlider, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, VolumeSlider.x, VolumeSlider.y);
			HUD.clickyButton(g, arrowL, arrowLGlow, arrowLClicked, Game.arrowL2Highlighted, Game.arrowL2Clicked, Game.backOnArrowL2, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH  - 69 - 40 - 16, 221);
			HUD.clickyButton(g, arrowR, arrowRGlow, arrowRClicked, Game.arrowR2Highlighted, Game.arrowR2Clicked, Game.backOnArrowR2, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH  + 69 + 40, 221);
			HUD.clickyButton(g, volumeSlider, volumeSliderGlow, volumeSliderClicked, Game.sfxMusicSliderHighlighted, Game.sfxMusicSliderBooleanClicked, Game.backOnSFXMusicSlider, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, VolumeSlider.x2, VolumeSlider.y2);
			HUD.clickyButton(g, resetStatsTitle, resetStatsTitleGlow, resetStatsTitleClicked, Game.resetStatsHighlighted, Game.resetStatsClicked, Game.backOnResetStats, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - (resetStatsTitle.getWidth()/2), 420);
			if(!Game.skipAnimations) 
				HUD.clickyButton(g, noCheckMarkTitle, noCheckMarkTitleGlow, noCheckMarkTitleClicked, Game.checkMarkHighlighted, Game.checkMarkClicked, Game.backOnCheckMark, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - (checkMarkTitle.getWidth()/2), 320);
			else 
				HUD.clickyButton(g, checkMarkTitle, checkMarkTitleGlow, checkMarkTitleClicked, Game.checkMarkHighlighted, Game.checkMarkClicked, Game.backOnCheckMark, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - (checkMarkTitle.getWidth()/2), 320);
			if(areYouSureBoolean) {
				g.drawImage(areYouSure,0,0,null);
				HUD.clickyButton(g, noTitle, noTitleGlow, noTitleClicked, Game.noHighlighted, Game.noClicked, Game.backOnNo, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - 64 - 18,375);
				HUD.clickyButton(g, yesTitle, yesTitleGlow, yesTitleClicked, Game.yesHighlighted, Game.yesClicked, Game.backOnYes, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH + 18,375);
			}
			if(Game.keysAreInUse) {
				if(Game.backHighlighted ||
				   Game.backClicked) {
					backHighlighted = false;
					backClicked = false;
				}//0 = arrowL1, 1 = arrowL2, 2 = skip animations, 3 = reset stats, -2 = arrowR1, -3 = arrowR2
				if(areYouSureBoolean) {
					if(Game.enterButtonPushedDown && !Game.escapePressedNegateAction) {
						switch(Game.selectorButtonPosition) {
						case 0:
							g.drawImage(noTitleSelectedClicked,Game.WIDTH - 64 - 36,375-18,null);
							g.drawImage(noTitleClicked,Game.WIDTH - 64 - 18,375,null);
							break;
						case 1:
							g.drawImage(yesTitleSelectedClicked,Game.WIDTH,375 - 18,null);
							g.drawImage(yesTitleClicked,Game.WIDTH + 18,375,null);
							break;
						default:
							break;
						}
					}
					else if(Game.enterButtonPushedDown && Game.escapePressedNegateAction) {
						switch(Game.selectorButtonPosition) {
						case 0:
							g.drawImage(noTitleSelectedNormal,Game.WIDTH - 64 - 36,375-18,null);
							g.drawImage(noTitle,Game.WIDTH - 64 - 18,375,null);
							break;
						case 1:
							g.drawImage(yesTitleSelectedNormal,Game.WIDTH,375 - 18,null);
							g.drawImage(yesTitle,Game.WIDTH + 18,375,null);
							break;
						default:
							break;
						}
					}
					else {
						switch(Game.selectorButtonPosition) {
						case 0:
							g.drawImage(noTitleSelected,Game.WIDTH - 64 - 36,375-18,null);
							g.drawImage(noTitleGlow,Game.WIDTH - 64 - 18,375,null);
							break;
						case 1:
							g.drawImage(yesTitleSelected,Game.WIDTH,375 - 18,null);
							g.drawImage(yesTitleGlow,Game.WIDTH + 18,375,null);
							break;
						default:
							break;
						}
					}
				}
				else {
					
					if(Game.enterButtonPushedDown && !Game.escapePressedNegateAction) {
						switch(Game.selectorButtonPosition) {
						case -3:
							g.drawImage(arrowSelectedClicked,40 +Game.WIDTH  + 69 - 7, 221 -7,null);
							g.drawImage(arrowRClicked,40 +Game.WIDTH  + 69, 221,null);
							break;
						case -2:
							g.drawImage(arrowSelectedClicked,40 +Game.WIDTH  + 69 - 7, 121 -7,null);
							g.drawImage(arrowRClicked,40 +Game.WIDTH  + 69, 121,null);
							break;
						case -1:
							g.drawImage(backTitleSelectedClicked,40 -7, 20 -7,null);
							g.drawImage(backButtonTitleClicked,40, 20,null);
							break;
						case 0:
							g.drawImage(arrowSelectedClicked,Game.WIDTH  - 69 - 40 - 16 - 7, 121 - 7,null);
							g.drawImage(arrowLClicked,Game.WIDTH  - 69 - 40 - 16, 121,null);
							break;
						case 1:
							g.drawImage(arrowSelectedClicked,Game.WIDTH  - 69 - 40 - 16 - 7, 221 - 7,null);
							g.drawImage(arrowLClicked,Game.WIDTH  - 69 - 40 - 16, 221,null);
							break;
						case 2:
							if(!Game.skipAnimations) 
								g.drawImage(noCheckMarkTitleClicked,Game.WIDTH - (checkMarkTitle.getWidth()/2), 320,null);
							else 
								g.drawImage(checkMarkTitleClicked,Game.WIDTH - (checkMarkTitle.getWidth()/2), 320,null);
							break;
						case 3:
							g.drawImage(resetStatsTitleSelectedClicked,Game.WIDTH - (resetStatsTitle.getWidth()/2) - 7, 420 - 7,null);
							g.drawImage(resetStatsTitleClicked,Game.WIDTH - (resetStatsTitle.getWidth()/2), 420,null);
							break;
						}
						
					}
					else if(Game.enterButtonPushedDown && Game.escapePressedNegateAction) {
						switch(Game.selectorButtonPosition) {
						case -3:
							g.drawImage(arrowSelectedNormal,40 +Game.WIDTH  + 69 - 7, 221 -7,null);
							g.drawImage(arrowR,40 +Game.WIDTH  + 69, 221,null);
							break;
						case -2:
							g.drawImage(arrowSelectedNormal,40 +Game.WIDTH  + 69 - 7, 121 -7,null);
							g.drawImage(arrowR,40 +Game.WIDTH  + 69, 121,null);
							break;
						case -1:
							g.drawImage(backTitleSelectedNormal,40 -7, 20 -7,null);
							g.drawImage(backButtonTitle,40, 20,null);
							break;
						case 0:
							g.drawImage(arrowSelectedNormal,Game.WIDTH  - 69 - 40 - 16 - 7, 121 - 7,null);
							g.drawImage(arrowL,Game.WIDTH  - 69 - 40 - 16, 121,null);
							break;
						case 1:
							g.drawImage(arrowSelectedNormal,Game.WIDTH  - 69 - 40 - 16 - 7, 221 - 7,null);
							g.drawImage(arrowL,Game.WIDTH  - 69 - 40 - 16, 221,null);
							break;
						case 2:
							if(!Game.skipAnimations) 
								g.drawImage(noCheckMarkTitle,Game.WIDTH - (checkMarkTitle.getWidth()/2), 320,null);
							else 
								g.drawImage(checkMarkTitle,Game.WIDTH - (checkMarkTitle.getWidth()/2), 320,null);
							break;
						case 3:
							g.drawImage(resetStatsTitleSelectedNormal,Game.WIDTH - (resetStatsTitle.getWidth()/2) - 7, 420 - 7,null);
							g.drawImage(resetStatsTitle,Game.WIDTH - (resetStatsTitle.getWidth()/2), 420,null);
							break;
						}
					}
					else {
						switch(Game.selectorButtonPosition) {
						case -3:
							g.drawImage(arrowSelected,40 +Game.WIDTH  + 69 - 7, 221 -7,null);
							g.drawImage(arrowRGlow,40 +Game.WIDTH  + 69, 221,null);
							break;
						case -2:
							g.drawImage(arrowSelected,40 +Game.WIDTH  + 69 - 7, 121 -7,null);
							g.drawImage(arrowRGlow,40 +Game.WIDTH  + 69, 121,null);
							break;
						case -1:
							g.drawImage(backTitleSelected,40 -7, 20 -7,null);
							g.drawImage(backButtonTitleGlow,40, 20,null);
							break;
						case 0:
							g.drawImage(arrowSelected,Game.WIDTH  - 69 - 40 - 16 - 7, 121 - 7,null);
							g.drawImage(arrowLGlow,Game.WIDTH  - 69 - 40 - 16, 121,null);
							break;
						case 1:
							g.drawImage(arrowSelected,Game.WIDTH  - 69 - 40 - 16 - 7, 221 - 7,null);
							g.drawImage(arrowLGlow,Game.WIDTH  - 69 - 40 - 16, 221,null);
							break;
						case 2:
							if(!Game.skipAnimations) 
								g.drawImage(noCheckMarkTitleGlow,Game.WIDTH - (checkMarkTitle.getWidth()/2), 320,null);
							else 
								g.drawImage(checkMarkTitleGlow,Game.WIDTH - (checkMarkTitle.getWidth()/2), 320,null);
							break;
						case 3:
							g.drawImage(resetStatsTitleSelected,Game.WIDTH - (resetStatsTitle.getWidth()/2) - 7, 420 - 7,null);
							g.drawImage(resetStatsTitleGlow,Game.WIDTH - (resetStatsTitle.getWidth()/2), 420,null);
							break;
						}
					}
				}
			}
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
			for(int i = 0; i < this.luigiVoices.size(); i++) {
				luigiVoices.get(i).setSoundLoopBoolean(false);
				luigiVoices.get(i).setFramePosition(0);
			}
			for(int i = 0; i < this.gameSoundLoops.size(); i++) {
				gameSoundLoops.get(i).resetVolume();
				VolumeSlider.adjustMusic(gameSoundLoops.get(i));
				gameSoundLoops.get(i).setSoundLoopBoolean(false);
				gameSoundLoops.get(i).setFramePosition(0);
			}
			for(int i = 0; i < this.menuSoundLoops.size(); i++) {
				menuSoundLoops.get(i).setSoundLoopBoolean(false);
				menuSoundLoops.get(i).setFramePosition(0);
			}
			this.gameOverSoundLoop.setSoundLoopBoolean(false);
			this.gameOverSoundLoop.setFramePosition(0);
			this.gameOverWinningSoundLoop.setSoundLoopBoolean(false);
			this.gameOverWinningSoundLoop.setFramePosition(0);
			this.marioHasBeenInvincible = false;
			this.pauseSoundFXTimer = 0;
			this.menuSoundLoopRandomizer = 0;
			this.marioVoiceRandomizer = 0;
			this.soundRandomizer = 0;
			this.soundFXTimer = 0;
			this.soundFXBoolean = false;
			this.soundFXClip1Reset = false;
			this.soundFXClip1SoundLoop.setFramePosition(0);
			this.soundFXClip1SoundLoop.setSoundLoopBoolean(false);
			this.soundFXClip2SoundLoop.setFramePosition(0);
			this.soundFXClip2SoundLoop.setSoundLoopBoolean(false);
			this.soundSet = false;
			this.menuSoundSet = false;
			this.soundTimerSet = false;
			this.soundFXisPlaying = false;
			this.animationTimer1 = 0;
			this.enemyHitPauseTimer = 0;
			this.marioLetsGoPauseTimer = 0;
			this.pauseSoundFXTimer = 0;
			this.visualPauseTimer = 0;
			this.enemyHitPauseBoolean = false;
			this.paused = false;
			this.fileScoreWritten = false;
			this.userHasPaused = false;
			this.enemyHitRightBarrier = false;
			this.enemySpeedIncrease = 0.5;
			this.scoreEntered = false;
			this.playerNameImage = null;
			this.postLetterXPosition = 0;
			this.postLetterXPositionBeginning = 200;
			this.postLetter = '=';
			this.xLBoolean = false;
			this.xRBoolean = false;
			this.slowingDownActivatedl = false;
			this.slowingDownActivatedr = false;
			this.slowingDown = 0;
			this.slowingDownTimerLong = 0;
			this.runningTimerActivated = false;
			this.runningTimerActivatedResponse = false;
			this.runningTimerLong = 0;
			this.slowingDownFromPause = false;
			this.keepRunningAfterPauseL = false;
			this.keepRunningAfterPauseR = false;
			this.dontRunAfterPause = false;
			playerName.clear();
			settingsSetup = false;
			skinNumber = null;
			spawnDone = false;
			spawnDone2 = false;
			spawnDone3 = false;
			spawnDone4 = false;
			gameOverSoundBoolean = false;
			gameOverBoolean = false;
			backToGameOver = false;
			Health = 100;
			spawningEnemiesinTransition = 0;
			starExplode = false;
			starExplosionTimer = 0;
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
			leaderboard.setInitalized(false);
			leaderboard.getNames().clear();
			leaderboard.getScores().clear();
			leaderboard.setNameDecoy("");
			leaderboard.setScoreDecoy("");
			leaderboard.setStringDecoy("");
			paused = false;
			tex = new Textures(this);
			bb = new BasicBlocks(tex,this);												//BLOCKS
			c = new Controller(tex, this);
			p = new Player(Game.WIDTH,(Game.HEIGHT * SCALE) - MARIO_HEIGHT,tex,this,c);
			hud = new HUD(tex,this);
			menu = new Menu();
			
			ea = c.getEntityA();
			eb = c.getEntityB();
			ec = c.getEntityC();
			ed = c.getEntityD();
			ee = c.getEntityE();
			
			if(!Game.dontStartOver) {
				if(skipAnimations)
					State = STATE.GAME;
				else
					State = STATE.TRANSITION_ENTRANCE;
			}
			else {
				Game.setDontStartOver(false);
				State = STATE.MENU;
			}
		}
		
		//**************DRAW**************//
		g.dispose();
		bs.show();
	}
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(State == STATE.GAME && !Game.userHasPaused){
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
					if(fireballSFX.clipIsActive())
						fireballSFX.stop();
					fireballSFX.play();
				}
			}
			if(key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_P){
				if(paused == false && soundFXisPlaying == false && !this.pauseSoundFXSoundLoop.clipIsActive()){
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
				else if(paused == true && soundFXisPlaying == false && !this.pauseSoundFXSoundLoop.clipIsActive()){
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

				if(slowingDownTimerLong != 0) {
					slowingDownTimerLong = slowingDownTimerLong - System.currentTimeMillis();
				}
				else if(System.currentTimeMillis() - runningTimerLong > 666/2){
					if(xLBoolean == true)
						keepRunningAfterPauseL = true;
					else if(xRBoolean == true)
						keepRunningAfterPauseR = true;
				}
			}
		}
		else if(State == STATE.SET_SCORE) {
			if(Game.keysAreInUse) {
				switch(key) {
					case KeyEvent.VK_W: case KeyEvent.VK_UP:
					case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
						if(!Game.keysAreInUse) {
							Game.keysAreInUse = true;
							break;
						}
						if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
						break;
					case KeyEvent.VK_ENTER:
						if(!Game.keysAreInUse) {
							Game.keysAreInUse = true;
							break;
						}else {
							Game.enterButtonPushedDown = true;
							break;
						}
					case KeyEvent.VK_ESCAPE:
					case KeyEvent.VK_S: case KeyEvent.VK_RIGHT:
					case KeyEvent.VK_D: case KeyEvent.VK_DOWN:
						Game.keysAreInUse = false;
						break;
				}
			}
			else {
				switch(key) {
					case KeyEvent.VK_SHIFT:
						shiftOn = true;
						break;
					case KeyEvent.VK_A:
						postLetter = 'A';
						break;
					case KeyEvent.VK_B:
						postLetter = 'B';
						break;
					case KeyEvent.VK_C:
						postLetter = 'C';
						break;
					case KeyEvent.VK_D:
						postLetter = 'D';
						break;
					case KeyEvent.VK_E:
						postLetter = 'E';
						break;
					case KeyEvent.VK_F:
						postLetter = 'F';
						break;
					case KeyEvent.VK_G:
						postLetter = 'G';
						break;
					case KeyEvent.VK_H:
						postLetter = 'H';
						break;
					case KeyEvent.VK_I:
						postLetter = 'I';
						break;
					case KeyEvent.VK_J:
						postLetter = 'J';
						break;
					case KeyEvent.VK_K:
						postLetter = 'K';
						break;
					case KeyEvent.VK_L:
						postLetter = 'L';
						break;
					case KeyEvent.VK_M:
						postLetter = 'M';
						break;
					case KeyEvent.VK_N:
						postLetter = 'N';
						break;
					case KeyEvent.VK_O:
						postLetter = 'O';
						break;
					case KeyEvent.VK_P:
						postLetter = 'P';
						break;
					case KeyEvent.VK_Q:
						postLetter = 'Q';
						break;
					case KeyEvent.VK_R:
						postLetter = 'R';
						break;
					case KeyEvent.VK_S:
						postLetter = 'S';
						break;
					case KeyEvent.VK_T:
						postLetter = 'T';
						break;
					case KeyEvent.VK_U:
						postLetter = 'U';
						break;
					case KeyEvent.VK_V:
						postLetter = 'V';
						break;
					case KeyEvent.VK_W:
						postLetter = 'W';
						break;
					case KeyEvent.VK_X:
						postLetter = 'X';
						break;
					case KeyEvent.VK_Y:
						postLetter = 'Y';
						break;
					case KeyEvent.VK_Z:
						postLetter = 'Z';
						break;
					case KeyEvent.VK_1:
						if(shiftOn)
							postLetter = '!';
						else
							postLetter = '1';
						break;
					case KeyEvent.VK_2:
						postLetter = '2';
						break;
					case KeyEvent.VK_3:
						postLetter = '3';
						break;
					case KeyEvent.VK_4:
						postLetter = '4';
						break;
					case KeyEvent.VK_5:
						postLetter = '5';
						break;
					case KeyEvent.VK_6:
						postLetter = '6';
						break;
					case KeyEvent.VK_7:
						postLetter = '7';
						break;
					case KeyEvent.VK_8:
						postLetter = '8';
						break;
					case KeyEvent.VK_9:
						postLetter = '9';
						break;
					case KeyEvent.VK_0:
						postLetter = '0';
						break;
					case KeyEvent.VK_PERIOD:
						postLetter = '.';
						break;
					case KeyEvent.VK_BACK_QUOTE: case KeyEvent.VK_QUOTE:
						postLetter = '\'';
						break;
					case KeyEvent.VK_EXCLAMATION_MARK:
						postLetter = '!';
						break;
					case KeyEvent.VK_SPACE:
						postLetter = ' ';
						break;
					case KeyEvent.VK_BACK_SPACE:
						postLetter = '+';
						break;
					case KeyEvent.VK_ENTER:
						break;
					case KeyEvent.VK_ESCAPE:
						Game.keysAreInUse = true;
						break;
					default:
						break;
				}
			}
		}
		
		else if(State == STATE.MENU) {
			switch(key) {
				case KeyEvent.VK_SHIFT:
					break;
				case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
					if(!Game.keysAreInUse) {
						Game.keysAreInUse = true;
						break;
					}
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.selectorButtonPosition == 0) {
						Game.selectorButtonPosition = -1;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						hudSFX.get(hudSFXPosition).play();
					}
					else if(Game.selectorButtonPosition < -1 && Game.selectorButtonPosition >= -3) {
						Game.selectorButtonPosition++;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						hudSFX.get(hudSFXPosition).play();
					}
					break;
				case KeyEvent.VK_B:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_C:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
					if(!Game.keysAreInUse) {
						Game.keysAreInUse = true;
						break;
					}
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.selectorButtonPosition == 0) {
						Game.selectorButtonPosition = -3;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						hudSFX.get(hudSFXPosition).play();
					}
					else if(Game.selectorButtonPosition <= -1 && Game.selectorButtonPosition > -3) {
						Game.selectorButtonPosition--;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						hudSFX.get(hudSFXPosition).play();
					}
					break;
				case KeyEvent.VK_E:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_F:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_G:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_H:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_I:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_J:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_K:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_L:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_M:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_N:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_O:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_P:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_Q:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_R:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_S: case KeyEvent.VK_DOWN:
					if(!Game.keysAreInUse) {
						Game.keysAreInUse = true;
						break;
					}
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.selectorButtonPosition < 0) {
						Game.selectorButtonPosition = 0;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						hudSFX.get(hudSFXPosition).play();
					}
					else if(Game.selectorButtonPosition < 2) {
						Game.selectorButtonPosition++;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						hudSFX.get(hudSFXPosition).play();
					}
					break;
				case KeyEvent.VK_T:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_U:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_V:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_W: case KeyEvent.VK_UP:
					if(!Game.keysAreInUse) {
						Game.keysAreInUse = true;
						break;
					}
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.selectorButtonPosition > 0) {
						Game.selectorButtonPosition--;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						hudSFX.get(hudSFXPosition).play();
					}
					else if(Game.selectorButtonPosition == 0) {
						Game.selectorButtonPosition = -2;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						hudSFX.get(hudSFXPosition).play();
					}
					break;
				case KeyEvent.VK_X:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_Y:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_Z:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_1:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_2:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_3:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_4:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_5:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_6:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_7:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_8:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_9:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_0:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_PERIOD:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_BACK_QUOTE: case KeyEvent.VK_QUOTE:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_EXCLAMATION_MARK:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_SPACE:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_BACK_SPACE:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_ENTER:
					if(!Game.keysAreInUse) {
						Game.keysAreInUse = true;
						break;
					}else {
						Game.enterButtonPushedDown = true;
						break;
					}
				case KeyEvent.VK_ESCAPE:
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					break;
				default:
					break;
			}
		}else if(State == STATE.TRANSITION_ENTRANCE || State == STATE.TRANSITION_ITEM || State == STATE.TRANSITION_WIN) {
			switch(key) {
			case KeyEvent.VK_ENTER: case KeyEvent.VK_SPACE:
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
				}
				else
					Game.enterButtonPushedDown = true;
				break;
			default:
				Game.keysAreInUse = true;
				break;
			}
		}else if(State == STATE.GAMEOVER) {
			switch(key) {
			case KeyEvent.VK_SHIFT:
				break;
			case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}
				if(Game.selectorButtonPosition == -2 || Game.selectorButtonPosition == 0) {
					Game.selectorButtonPosition = -1;
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
				break;
			case KeyEvent.VK_B:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_C:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}
				if(Game.selectorButtonPosition == -1 || Game.selectorButtonPosition == 0) {
					Game.selectorButtonPosition = -2;
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
				break;
			case KeyEvent.VK_E:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_F:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_G:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_H:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_I:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_J:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_K:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_L:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_M:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_N:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_O:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_P:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_Q:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_R:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_S: case KeyEvent.VK_DOWN:
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(Game.selectorButtonPosition == -1 || Game.selectorButtonPosition == -2) {
					Game.selectorButtonPosition = 0;
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
				else if(Game.selectorButtonPosition < 2) {
					Game.selectorButtonPosition++;
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
				break;
			case KeyEvent.VK_T:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_U:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_V:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_W: case KeyEvent.VK_UP:
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(Game.selectorButtonPosition > -1) {
					Game.selectorButtonPosition--;
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
				break;
			case KeyEvent.VK_X:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_Y:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_Z:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_1:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_2:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_3:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_4:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_5:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_6:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_7:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_8:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_9:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_0:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_PERIOD:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_BACK_QUOTE: case KeyEvent.VK_QUOTE:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_EXCLAMATION_MARK:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_SPACE:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_BACK_SPACE:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_ENTER:
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}else {
					Game.enterButtonPushedDown = true;
					break;
				}
			case KeyEvent.VK_ESCAPE:
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				else if(Game.keysAreInUse)
					Game.keysAreInUse = false;
				else
					Game.keysAreInUse = true;
				break;
			default:
				break;
			}
		}else if(State == STATE.GAME && Game.userHasPaused){
			switch(key) {
			case KeyEvent.VK_SHIFT:
				break;
			case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_B:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_C:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_E:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_F:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_G:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_H:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_I:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_J:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_K:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_L:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_M:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_N:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_O:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_Q:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_R:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_S: case KeyEvent.VK_DOWN:
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(Game.selectorButtonPosition < 2) {
					Game.selectorButtonPosition++;
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
				break;
			case KeyEvent.VK_T:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_U:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_V:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_W: case KeyEvent.VK_UP:
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(Game.selectorButtonPosition > 0) {
					Game.selectorButtonPosition--;
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
				break;
			case KeyEvent.VK_X:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_Y:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_Z:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_1:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_2:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_3:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_4:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_5:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_6:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_7:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_8:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_9:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_0:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_PERIOD:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_BACK_QUOTE: case KeyEvent.VK_QUOTE:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_EXCLAMATION_MARK:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_SPACE:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_BACK_SPACE:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_ENTER:
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}else {
					Game.enterButtonPushedDown = true;
					break;
				}
			case KeyEvent.VK_ESCAPE: case KeyEvent.VK_P:
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				else if(paused == true && soundFXisPlaying == false && !this.pauseSoundFXSoundLoop.clipIsActive()){
						Game.escapePressedNegateAction = false;
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
				break;
			default:
				break;
			}
		}else if(State == STATE.LEADERBOARD) {
			switch(key) {
			case KeyEvent.VK_W: case KeyEvent.VK_UP:
			case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
			case KeyEvent.VK_S: case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D: case KeyEvent.VK_DOWN:
			case KeyEvent.VK_P: case KeyEvent.VK_ESCAPE:
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				break;
			case KeyEvent.VK_ENTER:
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}else {
					Game.enterButtonPushedDown = true;
					break;
				}
			}
		}else if(State == STATE.SHOP) {
			int selector = Game.selectorButtonPosition;
			switch(key) {
			case KeyEvent.VK_W: case KeyEvent.VK_UP:
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				/*if(Game.selectorButtonPosition >-1 && Game.selectorButtonPosition < -5)
					Game.selectorButtonPosition = 1;
				else */
				if(Game.selectorButtonPosition > -1)
					Game.selectorButtonPosition--;
				else if(Game.selectorButtonPosition == -2 || Game.selectorButtonPosition == -3 || Game.selectorButtonPosition == -4)
					Game.selectorButtonPosition = -1;
				else if(Game.selectorButtonPosition == -5) {
					if(Game.currentSkinLocked)
						Game.selectorButtonPosition = -2;
					else
						Game.selectorButtonPosition = 0;
				}
				else if(Game.selectorButtonPosition == -6)
					Game.selectorButtonPosition = -3;
				else if(Game.selectorButtonPosition == -7)
					Game.selectorButtonPosition = -4;
				else if(Game.selectorButtonPosition == -8) {
					if(Game.currentTrackLocked)
						Game.selectorButtonPosition = -5;
					else
						Game.selectorButtonPosition = 1;
				}
				else if(Game.selectorButtonPosition == -9)
					Game.selectorButtonPosition = -6;
				else if(Game.selectorButtonPosition == -10)
					Game.selectorButtonPosition = -7;
				else if(Game.selectorButtonPosition == -11) {
					if(Game.currentFireballLocked)
						Game.selectorButtonPosition = -8;
					else
						Game.selectorButtonPosition = 2;
				}
				else if(Game.selectorButtonPosition == -12)
					Game.selectorButtonPosition = -9;
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
				break;
			case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(Game.selectorButtonPosition == -3) {
					if(Game.currentSkinLocked)
						Game.selectorButtonPosition = -2;
					else
						Game.selectorButtonPosition = 0;
				}
				else if(Game.selectorButtonPosition == -2)
					Game.selectorButtonPosition = 0;
				else if(Game.selectorButtonPosition == -5)
					Game.selectorButtonPosition = 1;
				else if(Game.selectorButtonPosition == -8)
					Game.selectorButtonPosition = 2;
				else if(Game.selectorButtonPosition == -11)
					Game.selectorButtonPosition = 3;
				else if(Game.selectorButtonPosition == 0)
					Game.selectorButtonPosition--;
				else if(Game.selectorButtonPosition == -6) {
					if(Game.currentTrackLocked)
						Game.selectorButtonPosition = -5;
					else
						Game.selectorButtonPosition = 1;
				}
				else if(Game.selectorButtonPosition == -9) {
					if(Game.currentFireballLocked)
						Game.selectorButtonPosition = -8;
					else
						Game.selectorButtonPosition = 2;
				}
				else if(Game.selectorButtonPosition == -12) {
					if(Game.currentItemLocked)
						Game.selectorButtonPosition = -11;
					else
						Game.selectorButtonPosition = 3;
				}
				else if(Game.selectorButtonPosition < -2 && Game.selectorButtonPosition >= -4 ||
						Game.selectorButtonPosition < -5 && Game.selectorButtonPosition >= -7 ||
						Game.selectorButtonPosition < -8 && Game.selectorButtonPosition >= -10 ||
						Game.selectorButtonPosition < -11 && Game.selectorButtonPosition >= -13 ) {
					Game.selectorButtonPosition++;
				}
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
				break;
			case KeyEvent.VK_S: case KeyEvent.VK_DOWN:
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				/*if(Game.selectorButtonPosition >-1 && Game.selectorButtonPosition < -4)
					Game.selectorButtonPosition = 1;
				else */
				if(Game.selectorButtonPosition < 3 && Game.selectorButtonPosition > -1)
					Game.selectorButtonPosition++;
				else if(Game.selectorButtonPosition == -1)
					Game.selectorButtonPosition = 0;
				else if(Game.selectorButtonPosition == -2) {
					if(Game.currentTrackLocked)
						Game.selectorButtonPosition = -5;
					else
						Game.selectorButtonPosition = 1;
				}
				else if(Game.selectorButtonPosition == -3)
					Game.selectorButtonPosition = -6;
				else if(Game.selectorButtonPosition == -4)
					Game.selectorButtonPosition = -7;
				else if(Game.selectorButtonPosition == -5) {
					if(Game.currentFireballLocked)
						Game.selectorButtonPosition = -8;
					else
						Game.selectorButtonPosition = 2;
				}
				else if(Game.selectorButtonPosition == -6)
					Game.selectorButtonPosition = -9;
				else if(Game.selectorButtonPosition == -7)
					Game.selectorButtonPosition = -10;
				else if(Game.selectorButtonPosition == -8) {
					if(Game.currentItemLocked)
						Game.selectorButtonPosition = -11;
					else
						Game.selectorButtonPosition = 3;
				}
				else if(Game.selectorButtonPosition == -9)
					Game.selectorButtonPosition = -12;
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
				break;
			case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(Game.selectorButtonPosition == 0) {
					if(Game.currentSkinLocked)
						Game.selectorButtonPosition = -2;
					else
						Game.selectorButtonPosition = -3;
				}
				else if(Game.selectorButtonPosition == -1)
					Game.selectorButtonPosition = 0;
				else if(Game.selectorButtonPosition == 1) {
					if(Game.currentTrackLocked)
						Game.selectorButtonPosition = -5;
					else
						Game.selectorButtonPosition = -6;
				}
				else if(Game.selectorButtonPosition == 2) {
					if(Game.currentFireballLocked)
						Game.selectorButtonPosition = -8;
					else
						Game.selectorButtonPosition = -9;
				}
				else if(Game.selectorButtonPosition == 3) {
					if(Game.currentItemLocked)
						Game.selectorButtonPosition = -11;
					else
						Game.selectorButtonPosition = -12;
				}
				else if(Game.selectorButtonPosition <= -2 && Game.selectorButtonPosition > -4 ||
						Game.selectorButtonPosition <=-5 && Game.selectorButtonPosition > -7 ||
						Game.selectorButtonPosition <=-8 && Game.selectorButtonPosition > -10 ||
						Game.selectorButtonPosition <=-11 && Game.selectorButtonPosition > -12) {
					Game.selectorButtonPosition--;
				}
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
				break;
			case KeyEvent.VK_ESCAPE:
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				break;
			case KeyEvent.VK_ENTER:
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}else {
					Game.enterButtonPushedDown = true;
					break;
				}
			}
		}else if(State == STATE.HELP) {
			switch(key) {
			case KeyEvent.VK_W: case KeyEvent.VK_UP:
			case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
			case KeyEvent.VK_S: case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D: case KeyEvent.VK_DOWN:
			case KeyEvent.VK_P: case KeyEvent.VK_ESCAPE:
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					Game.selectorButtonPosition = -1;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				break;
			case KeyEvent.VK_ENTER:
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					Game.selectorButtonPosition = -1;
					break;
				}else {
					Game.enterButtonPushedDown = true;
					break;
				}
			}
		}else if(State == STATE.SETTINGS) {
			int selector = Game.selectorButtonPosition;
			switch(key) {//0 = arrowL1, 1 = arrowL2, 2 = skip animations, 3 = reset stats, -2 = arrowR1, -3 = arrowR2
			case KeyEvent.VK_W: case KeyEvent.VK_UP:
				if(!Game.keysAreInUse) {
					if(areYouSureBoolean)
						Game.selectorButtonPosition = 0;
					Game.keysAreInUse = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(areYouSureBoolean) {
					
				}
				else {
					if(selectorButtonPosition <= 3 && selectorButtonPosition > -1)
						selectorButtonPosition--;
					else if(selectorButtonPosition == -3)
						selectorButtonPosition = -2;
					else if(selectorButtonPosition == -2)
						selectorButtonPosition = -1;
				}
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
				break;
			case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
				if(!Game.keysAreInUse) {
					if(areYouSureBoolean)
						Game.selectorButtonPosition = 0;
					Game.keysAreInUse = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(areYouSureBoolean) {
					if(selectorButtonPosition == 1)
						selectorButtonPosition = 0;
				}
				else {
					if(selectorButtonPosition == -2)
						selectorButtonPosition = 0;
					else if(selectorButtonPosition == -3)
						selectorButtonPosition = 1;
				}
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
				break;
			case KeyEvent.VK_S: case KeyEvent.VK_DOWN:
				if(!Game.keysAreInUse) {
					if(areYouSureBoolean)
						Game.selectorButtonPosition = 0;
					Game.keysAreInUse = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(areYouSureBoolean) {
					
				}
				else {
					if(selectorButtonPosition >= -1 && selectorButtonPosition < 3)
						selectorButtonPosition++;
					else if(selectorButtonPosition == -2)
						selectorButtonPosition = -3;
					else if(selectorButtonPosition == -3)
						selectorButtonPosition = 2;
				}
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
				break;
			case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
				if(!Game.keysAreInUse) {
					if(areYouSureBoolean)
						Game.selectorButtonPosition = 0;
					Game.keysAreInUse = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(areYouSureBoolean) {
					if(selectorButtonPosition == 0)
						selectorButtonPosition = 1;
				}
				else {
					if(selectorButtonPosition == 0)
						selectorButtonPosition = -2;
					else if(selectorButtonPosition == 1)
						selectorButtonPosition = -3;
				}
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
				break;
			case KeyEvent.VK_P: case KeyEvent.VK_ESCAPE:
				if(!Game.keysAreInUse) {
					if(areYouSureBoolean)
						Game.selectorButtonPosition = 0;
					Game.keysAreInUse = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				break;
			case KeyEvent.VK_ENTER:
				if(!Game.keysAreInUse) {
					if(areYouSureBoolean)
						Game.selectorButtonPosition = 0;
					Game.keysAreInUse = true;
					break;
				}else {
					if(Game.selectorButtonPosition == 2 && !Game.enterButtonPushedDown) {
						if(smb3CheckmarkSoundLoop.clipIsActive())
							smb3CheckmarkSoundLoop.stop();
						if(smb3Checkmark2SoundLoop.clipIsActive())
							smb3Checkmark2SoundLoop.stop();
						smb3CheckmarkSoundLoop.play();
					}
					Game.enterButtonPushedDown = true;
					break;
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if(State == STATE.GAME && !Game.userHasPaused){
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
					if(skipAnimations) {
						//Play a sound
						spawnItem = true;
						//enemyHitPauseTimer = System.currentTimeMillis() + 800;
					}
					else {
						State = STATE.TRANSITION_ITEM;
						Game.keysAreInUse = false;
					}
					this.itemName = hud.getItemName();
					hud.setItemObtained(false);
				}
			} else if(key == KeyEvent.VK_ESCAPE) {
				if(!Game.keysAreInUse)
					Game.keysAreInUse = true;
			}
		}
		else if(State == STATE.TRANSITION_ENTRANCE || State == STATE.TRANSITION_WIN) {
			switch(key) {
				case KeyEvent.VK_ESCAPE:
					if(Game.enterButtonPushedDown) {
						Game.escapePressedNegateAction = true;
						break;
					}
					if(!askToSkipSequence) {
						askToSkipSequence = true;
					}
					else {
						askToSkipSequence = false;
						Game.keysAreInUse = false;
					}
					break;
				case KeyEvent.VK_SPACE: case KeyEvent.VK_ENTER:
					if(askToSkipSequence && Game.escapePressedNegateAction == false)
						skipSequence = true;
					Game.keysAreInUse = true;
					break;
				default:
					if(!askToSkipSequence) {
						askToSkipSequence = true;
					}
					Game.keysAreInUse = true;
					break;
				}
			if(!askToSkipSequence) {
				askToSkipSequence = true;
			}
			
		}
		else if(State == STATE.TRANSITION_ITEM) {
			p.setVelX(0);
			this.xLBoolean = false;
			this.xRBoolean = false;
			this.slowingDownActivatedl = false;
			this.slowingDownActivatedr = false;
			this.slowingDown = 0;
			this.slowingDownTimerLong = 0;
			this.runningTimerActivated = false;
			this.runningTimerActivatedResponse = false;
			this.runningTimerLong = 0;
			switch(key) {
				case KeyEvent.VK_ESCAPE:
					if(Game.enterButtonPushedDown) {
						Game.escapePressedNegateAction = true;
						break;
					}
					if(!askToSkipSequence) {
						askToSkipSequence = true;
					}
					else {
						askToSkipSequence = false;
						Game.keysAreInUse = false;
					}
					break;
				case KeyEvent.VK_SPACE: case KeyEvent.VK_ENTER:
					if(askToSkipSequence && Game.escapePressedNegateAction == false)
						skipSequence = true;
					Game.keysAreInUse = true;
					break;
				default:
					if(!askToSkipSequence) {
						askToSkipSequence = true;
					}
					Game.keysAreInUse = true;
					break;
				}
			if(!askToSkipSequence) {
				askToSkipSequence = true;
			}
			
			/*
			if(xLBoolean || xRBoolean) {
				if(this.slowingDownActivatedl) {
					
				}
				else if(this.slowingDownActivatedr) {
					
				}
				else {
					
				}
			}*/
		}
		else if(State == STATE.SET_SCORE) {
			if(Game.keysAreInUse) {
				switch(key) {
				case KeyEvent.VK_ENTER:
					Game.enterButtonPushedDown = false;
					if(!Game.escapePressedNegateAction) {
						Game.selectorButtonPosition = -2;
						Game.State = Game.STATE.GAMEOVER;
					}
					Game.escapePressedNegateAction = false;
					break;
				}
			}
			else {
				switch(key) {
					case KeyEvent.VK_SHIFT:
						shiftOn = false;
						break;
					case KeyEvent.VK_ENTER:
						postLetter = '~';
						Game.enterButtonPushedDown = false;
						Game.escapePressedNegateAction = false;
						break;
				}
			}
		}
		else if(State == STATE.MENU) {
			switch(key) {
			case KeyEvent.VK_ENTER: case KeyEvent.VK_SPACE:
				Game.enterButtonPushedDown = false;
				if(!Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
						case -3:
							Game.selectorButtonPosition = -1;
							State = STATE.LEADERBOARD;
							if(smb3OpenSoundLoop.clipIsActive())
								smb3OpenSoundLoop.stop();
							smb3OpenSoundLoop.play();
							break;
						case -2:
							Game.selectorButtonPosition = -1;
							State = STATE.SETTINGS;
							if(smb3OpenSoundLoop.clipIsActive())
								smb3OpenSoundLoop.stop();
							smb3OpenSoundLoop.play();
							break;
						case -1:
							Game.selectorButtonPosition = -1;
							State = STATE.HELP;
							if(smb3OpenSoundLoop.clipIsActive())
								smb3OpenSoundLoop.stop();
							smb3OpenSoundLoop.play();
							break;
						case 0:
							if(skipAnimations)
								State = STATE.GAME;
							else
								State = STATE.TRANSITION_ENTRANCE;
							Game.keysAreInUse = false;
							smb3CoinSoundLoop.play();
							break;
						case 1:
							Game.selectorButtonPosition = -1;
							State = STATE.SHOP;
							if(smb3OpenSoundLoop.clipIsActive())
								smb3OpenSoundLoop.stop();
							smb3OpenSoundLoop.play();
							break;
						case 2:
							System.exit(1);
							break;
					}
				}
				Game.escapePressedNegateAction = false;
				break;
			}
		}else if(State == STATE.GAMEOVER) {
			switch(key) {
			case KeyEvent.VK_ENTER:
				Game.enterButtonPushedDown = false;
				if(!Game.escapePressedNegateAction && Game.keysAreInUse) {
					switch(Game.selectorButtonPosition) {
						case -2:
							State = STATE.LEADERBOARD;
							Game.keysAreInUse = false;
							if(smb3OpenSoundLoop.clipIsActive())
								smb3OpenSoundLoop.stop();
							smb3OpenSoundLoop.play();
							break;
						case -1:
							State = STATE.SET_SCORE;
							Game.keysAreInUse = false;
							if(smb3KickSoundLoop.clipIsActive())
								smb3KickSoundLoop.stop();
							smb3KickSoundLoop.play();
							break;
						case 0:
							State = STATE.RESET;
							smb3CoinSoundLoop.play();
							break;
						case 1:
							Game.setDontStartOver(true);
							Game.State = Game.STATE.RESET;
							if(smb3OpenSoundLoop.clipIsActive())
								smb3OpenSoundLoop.stop();
							smb3OpenSoundLoop.play();
							break;
						case 2:
							System.exit(1);
							break;
					}
				}
				Game.escapePressedNegateAction = false;
				break;
			}
		}else if(State == STATE.GAME && Game.userHasPaused) {
			switch(key) {
			case KeyEvent.VK_ENTER:
				Game.enterButtonPushedDown = false;
				if(!Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
						case 0:
							if(Game.isPaused() == true && Game.getSoundFXisPlaying() == false){
								if(Game.getPauseSoundFXTimer() < System.currentTimeMillis()){
									Game.getPauseSoundFXSoundLoop().play();
									Game.setPauseSoundFXTimer(System.currentTimeMillis() + 685);
									Game.getPauseSoundFXSoundLoop().setSoundLoopBoolean(true);
								}
								Game.setUserHasPaused(false);
							}
							break;
						case 1:
							Game.setDontStartOver(true);
							Game.State = Game.STATE.RESET;
							if(smb3OpenSoundLoop.clipIsActive())
								smb3OpenSoundLoop.stop();
							smb3OpenSoundLoop.play();
							break;
						case 2:
							System.exit(1);
							break;
					}
				}
				Game.escapePressedNegateAction = false;
				break;
			case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
				if(!keepRunningAfterPauseR) {
					dontRunAfterPause = true;
					xLBoolean = false;
				}
				if(keepRunningAfterPauseL){														//This activates sliding animation for left side
					slowingDownActivatedl = true;
					slowingDownTimerLong = System.currentTimeMillis() + 200;
					slowingDown = -1.73;
					p.setVelX(slowingDown);
					runningTimerLong = 0;
					runningTimerActivatedResponse = false;
					slowingDownFromPause = true;
				}
				break;
			case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
				if(!keepRunningAfterPauseL) {
					dontRunAfterPause = true;
					xRBoolean = false;
				}
				if(keepRunningAfterPauseR){														//This activates sliding animation for left side
					slowingDownActivatedr = true;
					slowingDownTimerLong = System.currentTimeMillis() + 200;
					slowingDown = 1.73;
					p.setVelX(slowingDown);
					runningTimerLong = 0;
					runningTimerActivatedResponse = false;
					slowingDownFromPause = true;
				}
				break;
			}
		}else if(State == STATE.LEADERBOARD) {
			switch(key) {
				case KeyEvent.VK_ENTER:
					Game.enterButtonPushedDown = false;
					if(!Game.escapePressedNegateAction) {
						if(Game.backToGameOver) {
							Game.selectorButtonPosition = -2;
							Game.State = Game.STATE.GAMEOVER;
						}
						else {
							Game.selectorButtonPosition = -3;
							Game.State = Game.STATE.MENU;
						}
						if(smb3KickSoundLoop.clipIsActive())
							smb3KickSoundLoop.stop();
						smb3KickSoundLoop.play();
					}
					Game.escapePressedNegateAction = false;
					break;
			}
		}else if(State == STATE.SHOP) {
			switch(key) {
			case KeyEvent.VK_ENTER:
				Game.enterButtonPushedDown = false;
				if(!Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -12:
						if(Game.itemPosition == 2)//Max Items
							Game.itemPosition = 0;
						else
							Game.itemPosition++;
						Game.itemNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.itemPosition+1)), 10, 10);
						if(smb3Bump2SoundLoop.clipIsActive())
							smb3Bump2SoundLoop.stop();
						smb3Bump2SoundLoop.play();
						break;
					case -11:
						//Item Buy
						if(smb31PupSoundLoop.clipIsActive())
							smb31PupSoundLoop.stop();
						smb31PupSoundLoop.play();
						break;
					case -10:
						//Fireballs Set
						if(smb3ItemSoundLoop.clipIsActive())
							smb3ItemSoundLoop.stop();
						smb3ItemSoundLoop.play();
						break;
					case -9:
						if(Game.fireballPosition == 1)//Max Fireballs
							Game.fireballPosition = 0;
						else
							Game.fireballPosition++;
						Game.fireballNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.fireballPosition+1)), 10, 10);
						if(smb3Bump2SoundLoop.clipIsActive())
							smb3Bump2SoundLoop.stop();
						smb3Bump2SoundLoop.play();
						break;
					case -8:
						//Fireballs Buy
						if(smb31PupSoundLoop.clipIsActive())
							smb31PupSoundLoop.stop();
						smb31PupSoundLoop.play();
						break;
					case -7:
						//Tracks Set
						if(smb3ItemSoundLoop.clipIsActive())
							smb3ItemSoundLoop.stop();
						smb3ItemSoundLoop.play();
						break;
					case -6:
						if(Game.trackPosition == 1)//Max Tracks
							Game.trackPosition = 0;
						else 
							Game.trackPosition++;
						Game.trackNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.trackPosition+1)), 10, 10);
						if(smb3Bump2SoundLoop.clipIsActive())
							smb3Bump2SoundLoop.stop();
						smb3Bump2SoundLoop.play();
						break;
					case -5:
						//Tracks Buy
						if(smb31PupSoundLoop.clipIsActive())
							smb31PupSoundLoop.stop();
						smb31PupSoundLoop.play();
						break;
					case -4:
						//Skin Set
						if(!Game.currentSkinLocked) {
							if(Game.currentlySelectedCharacterSkin != Game.characterSkinPosition) {
								if(Game.smb3ItemSoundLoop.clipIsActive())
									Game.smb3ItemSoundLoop.stop();
								Game.smb3ItemSoundLoop.play();
							}
							Game.currentlySelectedCharacterSkin = Game.characterSkinPosition;
							Game.writeOnceToSettings = true;
							Game.writeOnceProperty = "currentlySelectedCharacterSkin";
							Game.writeOnceString = Integer.toString(Game.characterSkinPosition);
							//Game.settingsSetup = false;
						}
						break;
					case -3:
						if(Game.characterSkinPosition == 3)//Max Skins
							Game.characterSkinPosition = 0;
						else {
							Game.characterSkinPosition++;
						}
						Game.skinNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.characterSkinPosition+1)), 10, 10);
						if(smb3Bump2SoundLoop.clipIsActive())
							smb3Bump2SoundLoop.stop();
						smb3Bump2SoundLoop.play();
						break;
					case -2:
						//Skin Buy
						if(currentSkinLocked) {
							switch(characterSkinPosition){
								case 1:
									if(totalPoints >= 100){
										Game.skin1Unlocked = true;
										//Game.settingsSetup = false;
										Game.writeOnceToSettings = true;
										Game.writeOnceProperty = "currentlySelectedCharacterSkin";
										Game.writeOnceString = Integer.toString(Game.characterSkinPosition);
										Game.writeOnceToSettingswithPoints = true;
										Game.writeOnceUnlock = "skin1Unlocked";
										Game.characterSkinPosition = 1;
										Game.currentlySelectedCharacterSkin = 1;
										currentSkinLocked = false;
										skinNumber = null;
										totalPoints -= 100;
										if(smb31PupSoundLoop.clipIsActive())
											smb31PupSoundLoop.stop();
										smb31PupSoundLoop.play();
										Game.selectorButtonPosition = -3;
									}
									else{
										if(smwErrorSoundLoop.clipIsActive())
											smwErrorSoundLoop.stop();
										smwErrorSoundLoop.play();
									}
									break;
								case 2:
									if(totalPoints >= 1000){
										Game.skin2Unlocked = true;
										//Game.settingsSetup = false;
										Game.writeOnceToSettings = true;
										Game.writeOnceProperty = "currentlySelectedCharacterSkin";
										Game.writeOnceString = Integer.toString(Game.characterSkinPosition);
										Game.writeOnceToSettingswithPoints = true;
										Game.writeOnceUnlock = "skin2Unlocked";
										Game.characterSkinPosition = 2;
										Game.currentlySelectedCharacterSkin = 2;
										currentSkinLocked = false;
										skinNumber = null;
										totalPoints -= 1000;
										if(smb31PupSoundLoop.clipIsActive())
											smb31PupSoundLoop.stop();
										smb31PupSoundLoop.play();
										Game.selectorButtonPosition = -3;
									}
									else{
										if(smwErrorSoundLoop.clipIsActive())
											smwErrorSoundLoop.stop();
										smwErrorSoundLoop.play();
									}
									break;
								case 3:
									if(totalPoints >= 10000){
										Game.skin3Unlocked = true;
										//Game.settingsSetup = false;
										Game.writeOnceToSettings = true;
										Game.writeOnceProperty = "currentlySelectedCharacterSkin";
										Game.writeOnceString = Integer.toString(Game.characterSkinPosition);
										Game.writeOnceToSettingswithPoints = true;
										Game.writeOnceUnlock = "skin3Unlocked";
										Game.characterSkinPosition = 3;
										Game.currentlySelectedCharacterSkin = 3;
										currentSkinLocked = false;
										skinNumber = null;
										totalPoints -= 10000;
										if(smb31PupSoundLoop.clipIsActive())
											smb31PupSoundLoop.stop();
										smb31PupSoundLoop.play();
										Game.selectorButtonPosition = -3;
									}
									else{
										if(smwErrorSoundLoop.clipIsActive())
											smwErrorSoundLoop.stop();
										smwErrorSoundLoop.play();
									}
									break;
								default:
									break;
							}
							
						}
						//else
							//Play error noise
						break;
					case -1:
						Game.selectorButtonPosition = 1;
						Game.State = Game.STATE.MENU;
						if(smb3KickSoundLoop.clipIsActive())
							smb3KickSoundLoop.stop();
						smb3KickSoundLoop.play();
						break;
					case 0:
						if(Game.characterSkinPosition > 0)
							Game.characterSkinPosition--;
						else
							Game.characterSkinPosition = 3;//Set to Max Skins
						Game.skinNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.characterSkinPosition+1)), 10, 10);
						if(smb3Bump2SoundLoop.clipIsActive())
							smb3Bump2SoundLoop.stop();
						smb3Bump2SoundLoop.play();
						break;
					case 1:
						if(Game.trackPosition > 0)
							Game.trackPosition--;
						else
							Game.trackPosition = 1;//Set to Max Tracks
						Game.trackNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.trackPosition+1)), 10, 10);
						if(smb3Bump2SoundLoop.clipIsActive())
							smb3Bump2SoundLoop.stop();
						smb3Bump2SoundLoop.play();
						break;
					case 2:
						if(Game.fireballPosition > 0)
							Game.fireballPosition--;
						else
							Game.fireballPosition = 1;//Set to Max Fireballs
						Game.fireballNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.fireballPosition+1)), 10, 10);
						if(smb3Bump2SoundLoop.clipIsActive())
							smb3Bump2SoundLoop.stop();
						smb3Bump2SoundLoop.play();
						break;
					case 3:
						if(Game.itemPosition > 0)
							Game.itemPosition--;
						else
							Game.itemPosition = 2;//Set to Max Items
						Game.itemNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.itemPosition+1)), 10, 10);
						if(smb3Bump2SoundLoop.clipIsActive())
							smb3Bump2SoundLoop.stop();
						smb3Bump2SoundLoop.play();
						break;
					}
				}
				Game.escapePressedNegateAction = false;
				break;
		}
		}else if(State == STATE.HELP) {
			switch(key) {
			case KeyEvent.VK_ENTER:
				Game.enterButtonPushedDown = false;
				if(!Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -1:
						Game.selectorButtonPosition = -1;
						Game.State = Game.STATE.MENU;
						break;	
					}
					if(smb3KickSoundLoop.clipIsActive())
						smb3KickSoundLoop.stop();
					smb3KickSoundLoop.play();
				}
				Game.escapePressedNegateAction = false;
				break;
			}
		}else if(State == STATE.SETTINGS) {
			switch(key) {
			case KeyEvent.VK_ENTER:
				Game.enterButtonPushedDown = false;
				if(!Game.escapePressedNegateAction) {
					if(areYouSureBoolean) {
						switch(Game.selectorButtonPosition) {
						case 0:
							Game.selectorButtonPosition = 3;
							areYouSureBoolean = false;
							smb3KickSoundLoop.play();
							break;	
						case 1:
							Game.skin1Unlocked = false;
							Game.skin2Unlocked = false;
							Game.skin3Unlocked = false;
							Game.track1Unlocked = false;
							Game.fireball1Unlocked = false;
							Game.item1Unlocked = false;
							Game.item2Unlocked = false;
							Game.currentlySelectedCharacterSkin = 0;
							Game.currentlySelectedTrack = 0;
							Game.currentlySelectedFireball = 0;
							Game.currentlySelectedItem = 0;
							Game.volumeSliderPosition = 3;
							Game.sfxMusicSliderPosition = 3;
							Game.characterSkinPosition = 0;
							Game.trackPosition = 0;
							Game.fireballPosition = 0;
							Game.itemPosition = 0;
							skinNumber = null;
							Game.totalPoints = 0;
							Game.skipAnimations = false;
							try {
								LeaderboardController.resetScore();
							} catch (IOException e1) {
								
							}
							VolumeSlider.volumeSliderChangingVolume = true;
							VolumeSlider.sfxMusicSliderChangingVolume = true;
							Game.settingsSetup = false;
							Game.selectorButtonPosition = 3;
							Game.areYouSureBoolean = false;
							smb3TailSoundLoop.play();
							break;	
						default:
							break;
						}
					}
					else {
						switch(Game.selectorButtonPosition) {
						case -3:
							if(sfxMusicSliderPosition < 5) {
								Game.sfxMusicSliderPosition++;
								VolumeSlider.sfxMusicSliderChangingVolume = true;
							}
							if(smb3BumpSoundLoop.clipIsActive())
								smb3BumpSoundLoop.stop();
							smb3BumpSoundLoop.play();
							break;
						case -2:
							if(volumeSliderPosition < 5) {
								Game.volumeSliderPosition++;
								VolumeSlider.volumeSliderChangingVolume = true;
							}
							if(smb3BumpSoundLoop.clipIsActive())
								smb3BumpSoundLoop.stop();
							smb3BumpSoundLoop.play();
							break;
						case -1:
							Game.selectorButtonPosition = -2;
							Game.State = Game.STATE.MENU;
							if(smb3KickSoundLoop.clipIsActive())
								smb3KickSoundLoop.stop();
							smb3KickSoundLoop.play();
							break;	
						case 0:
							if(volumeSliderPosition > 1) {
								Game.volumeSliderPosition--;
								VolumeSlider.volumeSliderChangingVolume = true;
							}
							if(smb3BumpSoundLoop.clipIsActive())
								smb3BumpSoundLoop.stop();
							smb3BumpSoundLoop.play();
							break;
						case 1:
							if(sfxMusicSliderPosition > 1) {
								Game.sfxMusicSliderPosition--;
								VolumeSlider.sfxMusicSliderChangingVolume = true;
							}
							if(smb3BumpSoundLoop.clipIsActive())
								smb3BumpSoundLoop.stop();
							smb3BumpSoundLoop.play();
							break;
						case 2:
							if(skipAnimations) {
								skipAnimations = false;
								settingsSetup = false;
							}
							else {
								skipAnimations = true;
								settingsSetup = false;
							}
							if(smb3Checkmark2SoundLoop.clipIsActive())
								smb3Checkmark2SoundLoop.stop();
							if(smb3CheckmarkSoundLoop.clipIsActive())
								smb3CheckmarkSoundLoop.stop();
							smb3Checkmark2SoundLoop.play();
							break;	
						case 3:
							Game.selectorButtonPosition = 0;
							areYouSureBoolean = true;
							hudSFX.get(4).play();
							break;	
						default:
							break;
						}
					}
				}
				Game.escapePressedNegateAction = false;
				break;
			}
		}
		if(key == KeyEvent.VK_ENTER) {
			Game.enterButtonPushedDown = false;
			Game.escapePressedNegateAction = false;
		}
	}
	public static Boolean isPixelTransparentinBufferedImage(BufferedImage img, int x, int y) {
		//System.out.println(img.getWidth()+"x is "+x);
		if(x >= img.getWidth() || x < 0)
			return false;
		if(y >= img.getHeight() || y < 0)
			return false;
		int p = img.getRGB(x, y);
		int a = (p>>24) & 0xff;
		if (a == 0)
			return true;
		else
			return false;
	}
	private AlphaComposite makeComposite(float alpha) {
		  int type = AlphaComposite.SRC_OVER;
		  return(AlphaComposite.getInstance(type, alpha));
	}
	
	public static BufferedImage cutoffResize(BufferedImage img, int newW, int newH) {
		  return img.getSubimage(0, 0, newW, newH);
		}
	
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
	
	 public static BufferedImage resize(BufferedImage source,
             int width, int height) {
         return commonResize(source, width, height,
                 RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
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
		String gameOverWinningAudioFile = "res/Sounds/SFX/smb_stage_clear.wav";
		String gameOverIrisAudioFile = "res/Sounds/SFX/smw_goal_iris-out.wav";
		String marioStarAudioFile = "res/Sounds/SFX/mariowhistle.wav";
		String soundFXClip1 = "res/Sounds/SFX/riseupacoustic1cWAVE.wav";
		String soundFXClip2 = "res/Sounds/SFX/MariopowerupSFX.wav";
		String fireballFXFile = "res/Sounds/SFX/smb3_fireball.wav";
		String pauseSoundFXFile = "res/Sounds/SFX/smb_pause.wav";
		String marioSpinningFile = "res/Sounds/SFX/smw_feather_get.wav";
		String marioDeathFile = "res/Sounds/SFX/smb3_player_down.wav";
		String itemSwooshFile = "res/Sounds/SFX/spacewhooshsfxMARIO.wav";
		String smb3CoinFile = "res/Sounds/SFX/smb3_coin.wav";
		String smb3BumpFile = "res/Sounds/SFX/smb3_bump.wav";
		String smb3Bump2File = "res/Sounds/SFX/smb3_bump2.wav";
		String smb3KickFile = "res/Sounds/SFX/smb3_kick.wav";
		String smb3TailFile = "res/Sounds/SFX/smb3_tail.wav";
		String smb3OpenFile = "res/Sounds/SFX/smb3_open.wav";
		String smb31PupFile = "res/Sounds/SFX/smb3_1pup.wav";
		String smb3ItemFile = "res/Sounds/SFX/smb3_item.wav";
		String smb3BeepFile = "res/Sounds/SFX/smb3_beep.wav";
		String smb3CheckmarkFile = "res/Sounds/SFX/checkmarksound1.wav";
		String smb3Checkmark2File = "res/Sounds/SFX/checkmarksound2.wav";
		String smwErrorFile = "res/Sounds/SFX/smw_lemmy_wendy_incorrect.wav";
		String marioVoiceLetsGoFile = "res/Sounds/SFX/MarioVoice/mk64_mario02.wav";
		String marioVoiceHereWeGoFile = "res/Sounds/SFX/MarioVoice/ssbm_dr_mario_33_mario_27.wav";
		String marioVoiceYelpFile = "res/Sounds/SFX/MarioVoice/ssbm_dr_mario_22_mario_16.wav";
		String marioVoiceWoohooFile = "res/Sounds/SFX/MarioVoice/ssbm_dr_mario_26_mario_20.wav";
		String luigiVoiceLetsGoFile = "res/Sounds/SFX/LuigiVoice/ssbm_luigi_25_31.wav";
		String luigiVoiceYipeeFile = "res/Sounds/SFX/LuigiVoice/ssbm_luigi_15.wav";
		String luigiVoiceYahFile = "res/Sounds/SFX/LuigiVoice/ssbm_luigi_16.wav";
		String luigiVoiceAhhaFile = "res/Sounds/SFX/LuigiVoice/ssbm_luigi_18.wav";
		String luigiVoiceWoohooFile = "res/Sounds/SFX/LuigiVoice/ssbm_luigi_24.wav";
		String hudSFXFile1 = "res/Sounds/SFX/hudeffectnoises1.wav";
		String hudSFXFile2 = "res/Sounds/SFX/hudeffectnoises2.wav";
		String hudSFXFile3 = "res/Sounds/SFX/hudeffectnoises3.wav";
		String hudSFXFile4 = "res/Sounds/SFX/hudeffectnoises4.wav";
		String hudSFXFile5 = "res/Sounds/SFX/hudeffectnoises5.wav";
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
		SoundLoops fireballFXSoundLoop = new SoundLoops(fireballFXFile);
		SoundLoops pauseSoundFXSoundLoop = new SoundLoops(pauseSoundFXFile);
		SoundLoops marioSpinningSoundLoop = new SoundLoops(marioSpinningFile);
		SoundLoops marioDeathSoundLoop = new SoundLoops(marioDeathFile);
		SoundLoops itemSwooshSoundLoop = new SoundLoops(itemSwooshFile);
		SoundLoops smb3CoinSoundLoop = new SoundLoops(smb3CoinFile);
		SoundLoops smb3BumpSoundLoop = new SoundLoops(smb3BumpFile);
		SoundLoops smb3Bump2SoundLoop = new SoundLoops(smb3Bump2File);
		SoundLoops smb3KickSoundLoop = new SoundLoops(smb3KickFile);
		SoundLoops smb3TailSoundLoop = new SoundLoops(smb3TailFile);
		SoundLoops smb3OpenSoundLoop = new SoundLoops(smb3OpenFile);
		SoundLoops smb31PupSoundLoop = new SoundLoops(smb31PupFile);
		SoundLoops smb3ItemSoundLoop = new SoundLoops(smb3ItemFile);
		SoundLoops smb3BeepSoundLoop = new SoundLoops(smb3BeepFile);
		SoundLoops smb3CheckmarkSoundLoop = new SoundLoops(smb3CheckmarkFile);
		SoundLoops smb3Checkmark2SoundLoop = new SoundLoops(smb3Checkmark2File);
		SoundLoops smwErrorSoundLoop = new SoundLoops(smwErrorFile);
		SoundLoops marioVoiceLetsGoSoundLoop = new SoundLoops(marioVoiceLetsGoFile);
		SoundLoops marioVoiceHereWeGoSoundLoop = new SoundLoops(marioVoiceHereWeGoFile);
		SoundLoops marioVoiceYelpSoundLoop = new SoundLoops(marioVoiceYelpFile);
		SoundLoops marioVoiceWoohooSoundLoop = new SoundLoops(marioVoiceWoohooFile);
		SoundLoops luigiVoiceLetsGoSoundLoop = new SoundLoops(luigiVoiceLetsGoFile);
		SoundLoops luigiVoiceYipeeSoundLoop = new SoundLoops(luigiVoiceYipeeFile);
		SoundLoops luigiVoiceYahSoundLoop = new SoundLoops(luigiVoiceYahFile);
		SoundLoops luigiVoiceAhhaSoundLoop = new SoundLoops(luigiVoiceAhhaFile);
		SoundLoops luigiVoiceWoohooSoundLoop = new SoundLoops(luigiVoiceWoohooFile);
		SoundLoops hudSFXSoundLoop1 = new SoundLoops(hudSFXFile1);
		SoundLoops hudSFXSoundLoop2 = new SoundLoops(hudSFXFile2);
		SoundLoops hudSFXSoundLoop3 = new SoundLoops(hudSFXFile3);
		SoundLoops hudSFXSoundLoop4 = new SoundLoops(hudSFXFile4);
		SoundLoops hudSFXSoundLoop5 = new SoundLoops(hudSFXFile5);
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
		game.luigiVoices.add(luigiVoiceLetsGoSoundLoop);
		game.luigiVoices.add(luigiVoiceYipeeSoundLoop);
		game.luigiVoices.add(luigiVoiceYahSoundLoop);
		game.luigiVoices.add(luigiVoiceAhhaSoundLoop);
		game.luigiVoices.add(luigiVoiceWoohooSoundLoop);
		game.hudSFX.add(hudSFXSoundLoop1);
		game.hudSFX.add(hudSFXSoundLoop2);
		game.hudSFX.add(hudSFXSoundLoop3);
		game.hudSFX.add(hudSFXSoundLoop4);
		game.hudSFX.add(hudSFXSoundLoop5);
		game.marioStarSoundLoop = marioStarSoundLoop;
		game.soundFXClip1SoundLoop = soundFXClip1SoundLoop;
		game.soundFXClip2SoundLoop = soundFXClip2SoundLoop;
		game.fireballSFX = fireballFXSoundLoop;
		game.pauseSoundFXSoundLoop = pauseSoundFXSoundLoop;
		game.marioSpinningSoundLoop = marioSpinningSoundLoop;
		game.marioDeathSoundLoop = marioDeathSoundLoop;
		game.itemSwooshSoundLoop = itemSwooshSoundLoop;
		game.smb3CoinSoundLoop = smb3CoinSoundLoop;
		game.smb3BumpSoundLoop = smb3BumpSoundLoop;
		game.smb3Bump2SoundLoop = smb3Bump2SoundLoop;
		game.smb3KickSoundLoop = smb3KickSoundLoop;
		game.smb3TailSoundLoop = smb3TailSoundLoop;
		game.smb3OpenSoundLoop = smb3OpenSoundLoop;
		game.smb31PupSoundLoop = smb31PupSoundLoop;
		game.smb3ItemSoundLoop = smb3ItemSoundLoop;
		game.smb3BeepSoundLoop = smb3BeepSoundLoop;
		game.smb3CheckmarkSoundLoop = smb3CheckmarkSoundLoop;
		game.smb3Checkmark2SoundLoop = smb3Checkmark2SoundLoop;
		game.smwErrorSoundLoop = smwErrorSoundLoop;
		game.gameOverSoundLoop = gameOverSoundLoop;
		game.gameOverWinningSoundLoop = gameOverWinningSoundLoop;
		game.gameOverIrisSoundLoop = gameOverIrisSoundLoop;
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
		
	}
	
	public Player getPlayer() {
		return p;
	}
	
	public int playerX(){
		return (int)p.getX();
	}
	
	public int playerY(){
		return (int)p.getY();
	}

	public ArrayList<String> getPlayerName() {
		return playerName;
	}

	public void setPlayerName(ArrayList<String> playerName) {
		this.playerName = playerName;
	}

	public boolean getFileScoreWritten() {
		return fileScoreWritten;
	}

	public void setFileScoreWritten(boolean fileScoreWritten) {
		this.fileScoreWritten = fileScoreWritten;
	}
	
	public int getSoundRandomizer() {
		return soundRandomizer;
	}

	public int animationTimer1(){
		return animationTimer1;
	}
	
	public static boolean isPaused(){
		return paused;
	}
	
	public static void setPaused(boolean paused){
		Game.paused = paused;
	}
	
	public static boolean getUserHasPaused() {
		return userHasPaused;
	}

	public static void setUserHasPaused(boolean userHasPaused) {
		Game.userHasPaused = userHasPaused;
	}
	
	public static boolean getSoundFXisPlaying(){
		return soundFXisPlaying;
	}

	public static void setSoundFXisPlaying(boolean soundFXisPlaying){
		Game.soundFXisPlaying = soundFXisPlaying;
	}

	public static boolean getDontStartOver() {
		return dontStartOver;
	}

	public static void setDontStartOver(boolean dontStartOver) {
		Game.dontStartOver = dontStartOver;
	}

	public boolean isMarioInvincible(){
		return p.getMarioInvincible();
	}
	
	public static long getPauseSoundFXTimer() {
		return pauseSoundFXTimer;
	}

	public static void setPauseSoundFXTimer(long pauseSoundFXTimer) {
		Game.pauseSoundFXTimer = pauseSoundFXTimer;
	}
	
	public long getVisualPauseTimer() {
		return visualPauseTimer;
	}

	public void setVisualPauseTimer(long visualPauseTimer) {
		this.visualPauseTimer = visualPauseTimer;
	}
	
	public long getEnemyHitPauseTimer() {
		return enemyHitPauseTimer;
	}

	public void setEnemyHitPauseTimer(long enemyHitPauseTimer) {
		this.enemyHitPauseTimer = enemyHitPauseTimer;
	}

	public boolean getSpawnDone4(){
		return spawnDone4;
	}
	
	public boolean getMarioDancePosePause() {
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

	public boolean getMarioGrowthPosePause() {
		return marioGrowthPosePause;
	}

	public void setMarioGrowthPosePause(boolean marioGrowthPosePause) {
		this.marioGrowthPosePause = marioGrowthPosePause;
	}

	public boolean getChainChompDeathSoundPauseBoolean() {
		return chainChompDeathSoundPauseBoolean;
	}

	public void setChainChompDeathSoundPauseBoolean(boolean chainChompDeathSoundPauseBoolean) {
		this.chainChompDeathSoundPauseBoolean = chainChompDeathSoundPauseBoolean;
	}

	public boolean getBulletBillDeathSoundPauseBoolean() {
		return bulletBillDeathSoundPauseBoolean;
	}

	public void setBulletBillDeathSoundPauseBoolean(boolean bulletBillDeathSoundPauseBoolean) {
		this.bulletBillDeathSoundPauseBoolean = bulletBillDeathSoundPauseBoolean;
	}

	public boolean getGoombaDeathSoundPauseBoolean() {
		return goombaDeathSoundPauseBoolean;
	}

	public void setGoombaDeathSoundPauseBoolean(boolean goombaDeathSoundPauseBoolean) {
		this.goombaDeathSoundPauseBoolean = goombaDeathSoundPauseBoolean;
	}
	
	public boolean getCoinSoundPauseBoolean() {
		return coinSoundPauseBoolean;
	}

	public void setCoinSoundPauseBoolean(boolean coinSoundPauseBoolean) {
		this.coinSoundPauseBoolean = coinSoundPauseBoolean;
	}

	public boolean getStarDingPauseBoolean() {
		return starDingPauseBoolean;
	}

	public void setStarDingPauseBoolean(boolean starDingPauseBoolean) {
		this.starDingPauseBoolean = starDingPauseBoolean;
	}

	public boolean getGoomba3DeathSoundPauseBoolean() {
		return goomba3DeathSoundPauseBoolean;
	}

	public void setGoomba3DeathSoundPauseBoolean(boolean goomba3DeathSoundPauseBoolean) {
		this.goomba3DeathSoundPauseBoolean = goomba3DeathSoundPauseBoolean;
	}

	public boolean getGoomba3DeathSmokeSoundPauseBoolean() {
		return goomba3DeathSmokeSoundPauseBoolean;
	}

	public void setGoomba3DeathSmokeSoundPauseBoolean(boolean goomba3DeathSmokeSoundPauseBoolean) {
		this.goomba3DeathSmokeSoundPauseBoolean = goomba3DeathSmokeSoundPauseBoolean;
	}
	
	public boolean getBrickBreakingSFXSoundPauseBoolean() {
		return brickBreakingSFXSoundPauseBoolean;
	}

	public void setBrickBreakingSFXSoundPauseBoolean(boolean brickBreakingSFXSoundPauseBoolean) {
		this.brickBreakingSFXSoundPauseBoolean = brickBreakingSFXSoundPauseBoolean;
	}

	public boolean getPlayHighlighted() {
		return playHighlighted;
	}

	public void setPlayHighlighted(boolean playHighlighted) {
		this.playHighlighted = playHighlighted;
	}

	public boolean getShopHighlighted() {
		return shopHighlighted;
	}

	public void setShopHighlighted(boolean shopHighlighted) {
		this.shopHighlighted = shopHighlighted;
	}

	public boolean getExitHighlighted() {
		return exitHighlighted;
	}

	public void setExitHighlighted(boolean exitHighlighted) {
		this.exitHighlighted = exitHighlighted;
	}

	public boolean getResumeHighlighted() {
		return resumeHighlighted;
	}

	public void setResumeHighlighted(boolean resumeHighlighted) {
		this.resumeHighlighted = resumeHighlighted;
	}

	public boolean getHomeHighlighted() {
		return homeHighlighted;
	}

	public void setHomeHighlighted(boolean homeHighlighted) {
		this.homeHighlighted = homeHighlighted;
	}

	public boolean getBackHighlighted() {
		return backHighlighted;
	}

	public void setBackHighlighted(boolean backHighlighted) {
		this.backHighlighted = backHighlighted;
	}

	public boolean getSetScoreHighlighted() {
		return setScoreHighlighted;
	}

	public void setSetScoreHighlighted(boolean setScoreHighlighted) {
		this.setScoreHighlighted = setScoreHighlighted;
	}

	public boolean getLeaderboardHighlighted() {
		return leaderboardHighlighted;
	}

	public void setLeaderboardHighlighted(boolean leaderboardHighlighted) {
		this.leaderboardHighlighted = leaderboardHighlighted;
	}

	public static boolean getPlayClicked() {
		return playClicked;
	}

	public static void setPlayClicked(boolean playClicked) {
		Game.playClicked = playClicked;
	}

	public static boolean getBackOnPlay() {
		return backOnPlay;
	}

	public static void setBackOnPlay(boolean backOnPlay) {
		Game.backOnPlay = backOnPlay;
	}
	
	public static boolean getMouseIsClickedDown() {
		return mouseIsClickedDown;
	}

	public static void setMouseIsClickedDown(boolean mouseIsClickedDown) {
		Game.mouseIsClickedDown = mouseIsClickedDown;
	}
	
	public static boolean getMouseIsOffClickedObjectAndHeldDown() {
		return mouseIsOffClickedObjectAndHeldDown;
	}

	public static void setMouseIsOffClickedObjectAndHeldDown(boolean mouseIsOffClickedObjectAndHeldDown) {
		Game.mouseIsOffClickedObjectAndHeldDown = mouseIsOffClickedObjectAndHeldDown;
	}
	
	public boolean getScoreEntered() {
		return scoreEntered;
	}

	public void setScoreEntered(boolean scoreEntered) {
		this.scoreEntered = scoreEntered;
	}

	public BasicBlocks getBb() {
		return bb;
	}

	public void setBb(BasicBlocks bb) {
		this.bb = bb;
	}

	public HUD getHUD(){
		return this.hud;
	}
	
	public BufferedImage getSpriteSheet(){
		return spriteSheet;
	}
	
	public BufferedImage getSpriteSheetNES(){
		return spriteSheetNES;
	}
	
	public BufferedImage getSpriteSheetNES3(){
		return spriteSheetNES3;
	}
	
	public BufferedImage getSpriteSheetSNESFireLuigi(){
		return spriteSheetSNESFireLuigi;
	}
	
	public BufferedImage getMarioEntranceSprites(){
		return marioEntranceSprites;
	}
	
	public BufferedImage getMarioNES3EntranceSprites(){
		return marioNES3EntranceSprites;
	}
	
	public BufferedImage getMarioSNESFireLuigiEntranceSprites(){
		return marioSNESFireLuigiEntranceSprites;
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
	
	public BufferedImage getMarioItemsSpriteSheet(){
		return marioItemsSpriteSheet;
	}
	
	public BufferedImage getBigMarioItemAnimationSheet(){
		return bigMarioItemAnimationSheet;
	}
	
	public BufferedImage getBigMario2ItemAnimationSheet(){
		return bigMario2ItemAnimationSheet;
	}
	
	public BufferedImage getBigMario3ItemAnimationSheet(){
		return bigMario3ItemAnimationSheet;
	}
	
	public BufferedImage getBigMario4ItemAnimationSheet(){
		return bigMario4ItemAnimationSheet;
	}
	
	public BufferedImage getItemSilhouetteSheet(){
		return itemSilhouetteSheet;
	}
	
	public BufferedImage getChainChompItemGettingBiggerSheet(){
		return chainChompItemGettingBiggerSheet;
	}
	
	public BufferedImage getChainChompSheet(){
		return chainChompSheet;
	}
	
	public BufferedImage getChainChompDisintegrate() {
		return chainChompDisintegrate;
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
	
	public BufferedImage getMarioItemAnimationSheet(){
		return marioItemAnimationSheet;
	}
	
	public BufferedImage getMarioSlowingDownSprites(){
		return marioSlowingDownSprites;
	}

	public BufferedImage getFullMarioSpriteSheet(){
		return fullMarioSpriteSheet;
	}

	public BufferedImage getFullMarioSpriteSheetBlack(){
		return fullMarioSpriteSheetBlack;
	}
	
	public BufferedImage getMarioAdvanceSpriteSheet() {
		return marioAdvanceSpriteSheet;
	}
	
	public BufferedImage getMarioPaintSpriteSheet() {
		return marioPaintSpriteSheet;
	}
	
	public BufferedImage getMarioPaintSilhouetteSpriteSheet() {
		return marioPaintSilhouetteSpriteSheet;
	}
	
	public BufferedImage getMario3FontNumbersSmallSpriteSheet() {
		return mario3FontNumbersSmallSpriteSheet;
	}
	
	public BufferedImage getMario3FontSpriteSheet() {
		return mario3FontSpriteSheet;
	}
	
	public BufferedImage getGoombaDeathSpriteSheet() {
		return goombaDeathSpriteSheet;
	}

	public BufferedImage getBuyTitle() {
		return buyTitle;
	}
	
	public BufferedImage getBuyTitleGlow() {
		return buyTitleGlow;
	}
	
	public BufferedImage getBuyTitleClicked() {
		return buyTitleClicked;
	}
	
	public ArrayList<BufferedImage> getLeaderboardImage() {
		return leaderboardImage;
	}

	public void setLeaderboardImage(ArrayList<BufferedImage> leaderboardImage) {
		this.leaderboardImage = leaderboardImage;
	}

	public SoundLoops getMarioStarSoundLoop() {
		return marioStarSoundLoop;
	}

	public static SoundLoops getPauseSoundFXSoundLoop() {
		return pauseSoundFXSoundLoop;
	}

	public static void setPauseSoundFXSoundLoop(SoundLoops pauseSoundFXSoundLoop) {
		Game.pauseSoundFXSoundLoop = pauseSoundFXSoundLoop;
	}

	public void setMarioStarSoundLoop(SoundLoops marioStarSoundLoop) {
		this.marioStarSoundLoop = marioStarSoundLoop;
	}
	
	public BufferedImage getTransparentBlocks(){
		return transparentBlocks;
	}
	
	public BufferedImage getVolumeSlider() {
		return volumeSlider;
	}

	public void setVolumeSlider(BufferedImage volumeSlider) {
		this.volumeSlider = volumeSlider;
	}

	public BufferedImage getVolumeSliderGlow() {
		return volumeSliderGlow;
	}

	public void setVolumeSliderGlow(BufferedImage volumeSliderGlow) {
		this.volumeSliderGlow = volumeSliderGlow;
	}

	public BufferedImage getVolumeSliderClicked() {
		return volumeSliderClicked;
	}

	public void setVolumeSliderClicked(BufferedImage volumeSliderClicked) {
		this.volumeSliderClicked = volumeSliderClicked;
	}

	public BufferedImage getVolumeSliderSelected() {
		return volumeSliderSelected;
	}

	public void setVolumeSliderSelected(BufferedImage volumeSliderSelected) {
		this.volumeSliderSelected = volumeSliderSelected;
	}

	public BufferedImage getVolumeSliderSelectedNormal() {
		return volumeSliderSelectedNormal;
	}

	public void setVolumeSliderSelectedNormal(BufferedImage volumeSliderSelectedNormal) {
		this.volumeSliderSelectedNormal = volumeSliderSelectedNormal;
	}

	public BufferedImage getVolumeSliderSelectedClicked() {
		return volumeSliderSelectedClicked;
	}

	public void setVolumeSliderSelectedClicked(BufferedImage volumeSliderSelectedClicked) {
		this.volumeSliderSelectedClicked = volumeSliderSelectedClicked;
	}

	public BufferedImage getEmptyVolumeSlider() {
		return emptyVolumeSlider;
	}

	public void setEmptyVolumeSlider(BufferedImage emptyVolumeSlider) {
		this.emptyVolumeSlider = emptyVolumeSlider;
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
	
	public void addEntity(EntityC block){
		ec.add(block);
	}
	
	public void removeEntity(EntityC block){
		ec.remove(block);
	}
	
	public void addEntity(EntityD block){
		ed.add(block);
	}
	
	public void removeEntity(EntityD block){
		ed.remove(block);
	}
}
