package com.game.src.main;
import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyboardFocusManager;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.net.*;
import java.security.SecureRandom;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.game.src.main.Game.STATE;
import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityD;
import com.game.src.main.classes.EntityE;
import com.game.src.main.classes.EntityF;
import com.game.src.main.libs.Animation;
import com.github.strikerx3.jxinput.XInputAxes;
import com.github.strikerx3.jxinput.XInputAxesDelta;
import com.github.strikerx3.jxinput.XInputButtons;
import com.github.strikerx3.jxinput.XInputButtonsDelta;
import com.github.strikerx3.jxinput.XInputComponents;
import com.github.strikerx3.jxinput.XInputComponentsDelta;
import com.github.strikerx3.jxinput.XInputDevice;
import com.github.strikerx3.jxinput.XInputDevice14;
import com.github.strikerx3.jxinput.enums.XInputButton;
import com.github.strikerx3.jxinput.natives.XInputConstants;

import net.java.games.input.Component;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.DirectAndRawInputEnvironmentPlugin;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;
import net.java.games.input.SwitchedValues;
import net.java.games.input.example.ReadAllEvents;
public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	public static final long TIME_OUT = 60000;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public static final int MARIO_WIDTH = 16;
	public static final int MARIO_HEIGHT = 28;
	public final String TITLE = "Koopa's Invaders!";
	public final String KIVersion = "KIv1";
	private boolean running = false;
	private static boolean paused = false;
	private Thread thread;

	XInputDevice device;
	XInputDevice14 device14;
	static ReadAllEvents directInputEvents;
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage spriteSheetNES = null;
	private BufferedImage spriteSheetNES3 = null;
	private BufferedImage spriteSheetSNESFireLuigi = null;
	private BufferedImage spriteSheetNESMikeTyson = null;
	private BufferedImage spriteSheetNESContra = null;
	private BufferedImage marioEntranceSprites = null;
	private BufferedImage marioNES3EntranceSprites = null;
	private BufferedImage marioSNESFireLuigiEntranceSprites = null;
	private BufferedImage marioNESMikeTysonEntranceSprites = null;
	private BufferedImage marioNESContraEntranceSprites = null;
	private BufferedImage victoryPoseSpinningSprites = null;
	private BufferedImage animatedStar = null;
	private BufferedImage animatedShootingStar = null;
	private BufferedImage ufoSprites = null;
	private BufferedImage xboxButtonsSpriteSheet = null;
	private BufferedImage directInputButtonsSpriteSheet = null;
	private BufferedImage mario1StarSpriteSheet = null;
	private BufferedImage marioItemsSpriteSheet = null;
	private BufferedImage background = null;
	private BufferedImage marioLives = null;
	private BufferedImage bowserSpriteSheet = null;
	private BufferedImage bulletBillSpriteSheet = null;
	private BufferedImage bombOmbSpriteSheet = null;
	private BufferedImage marioPlayerStarAnimations = null;
	private BufferedImage marioItemAnimationSheet = null;
	private BufferedImage marioItemAnimationBackgroundSheet = null;
	private BufferedImage bigMarioItemAnimationSheet = null;
	private BufferedImage bigMario2ItemAnimationSheet = null;
	private BufferedImage bigMario3ItemAnimationSheet = null;
	private BufferedImage bigMario4ItemAnimationSheet = null;
	private BufferedImage bigMario5ItemAnimationSheet = null;
	private BufferedImage bigMario6ItemAnimationSheet = null;
	private BufferedImage lakituDeathAnimationSheet = null;
	private BufferedImage itemSilhouetteSheet = null;
	private BufferedImage currentItemImg = null;
	private BufferedImage dancingAnimationSheet = null;
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
	private BufferedImage wasdButtonImagesSpriteSheet = null;
	private BufferedImage goombaDeathSpriteSheet = null;
	private BufferedImage yoshiSpriteSheet = null;
	private BufferedImage backButtonTitle = null;
	private BufferedImage setScoreTitle = null;
	public static BufferedImage scoreTitle = null;
	public static BufferedImage zeroImage = null;
	public static BufferedImage setScoreTitleBigger = null;
	private BufferedImage textIndicator = null;
	private BufferedImage leaderboardTitle = null;
	public static BufferedImage leaderboardTitleBigger = null;
	private BufferedImage helpTitle = null;
	private BufferedImage settingsTitle = null;
	public static BufferedImage helpLinesLast = null;
	public static BufferedImage controlsTitle = null;
	public static BufferedImage tracklistTitle = null;
	public static BufferedImage settingsTitleBigger = null;
	public static BufferedImage playerNameImage = null;
	public static BufferedImage gamepadLetterImage = null;
	public static BufferedImage trackSetBox = null;
	public static BufferedImage serverErrorMessageImage = null;
	public static BufferedImage updateToConnectImage = null;
	public static BufferedImage lockedImage = null;
	public static BufferedImage redBowserIcon = null;
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
	private BufferedImage congrats = null;
	public static BufferedImage connectingTransparent = null;
	private BufferedImage dotdotdot = null;
	public static ArrayList<BufferedImage> leaderboardImage = new ArrayList<BufferedImage>();
	public static ArrayList<BufferedImage> helpLines = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> itemBackground = new ArrayList<BufferedImage>();
	private int backgroundTraverse = 0;
    public static Socket clientSocket;
    public static PrintWriter out;
    public static BufferedReader in;
    //public static BufferedInputStream inStream;
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
	public static boolean scoreEntered = false;
	public static boolean bEntityRemoved = false;
	private boolean marioHasBeenInvincible = false;
	private boolean slowingDownFromPause = false;
	private boolean keepRunningAfterPauseL = false;
	private boolean keepRunningAfterPauseR = false;
	private boolean dontRunAfterPause = false;
	private boolean sceneAcknowledgement = false;
	private boolean pauseHoldOff = false;
	private boolean windowMinimized = false;
	public volatile static boolean serverErrorMessage = false;
	public volatile static boolean updateToConnect = false;
	public volatile static boolean connectingToServer = false;
	private boolean doneConnectingToServer = false;
	public static boolean firstTimeRunning = true;
	public static boolean firstTimeBeating = false;
	public static boolean menuMusicStopped = false;
	public static boolean trackCurrentlyPlaying = false;
	public static boolean askToSkipSequence = false;
	public static boolean skipSequence = false;
	public static boolean sendToServer = true;
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
	public static boolean resetHighlighted = false;
	public static boolean yesHighlighted = false;
	public static boolean noHighlighted = false;
	public static boolean skipHighlighted = false;
	public static boolean submitHighlighted = false;
	public static boolean gamepadImageHighlighted = false;
	public static boolean noteImageHighlighted = false;
	public static boolean localHighlighted = false;
	public static boolean globalHighlighted = false;
	public static boolean uploadHighlighted = false;
	public static boolean creditsHighlighted = false;
	public static boolean resetLeaderboardHighlighted = false;
	public static boolean rescanHighlighted = false;
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
	public static boolean resetClicked = false;
	public static boolean backOnReset = false;
	public static boolean checkMarkClicked = false;
	public static boolean backOnCheckMark = false;
	public static boolean yesClicked = false;
	public static boolean backOnYes = false;
	public static boolean noClicked = false;
	public static boolean backOnNo = false;
	public static boolean skipClicked = false;
	public static boolean backOnSkip = false;
	public static boolean submitClicked = false;
	public static boolean backOnSubmit = false;
	public static boolean gamepadImageClicked = false;
	public static boolean backOnGamepadImage = false;
	public static boolean noteImageClicked = false;
	public static boolean backOnNoteImage = false;
	public static boolean localClicked = false;
	public static boolean backOnLocal = false;
	public static boolean globalClicked = false;
	public static boolean backOnGlobal = false;
	public static boolean uploadClicked = false;
	public static boolean backOnUpload = false;
	public static boolean creditsClicked = false;
	public static boolean backOnCredits = false;
	public static boolean resetLeaderboardClicked = false;
	public static boolean backOnResetLeaderboard = false;
	public static boolean rescanClicked = false;
	public static boolean backOnRescan = false;
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
	public static boolean trackPlayButton1Highlighted = false;
	public static boolean trackPauseButton1Highlighted = false;
	public static boolean trackPlayButton2Highlighted = false;
	public static boolean trackPauseButton2Highlighted = false;
	public static boolean trackPlayButton1Clicked = false;
	public static boolean trackPauseButton1Clicked = false;
	public static boolean trackPlayButton2Clicked = false;
	public static boolean trackPauseButton2Clicked = false;
	public static boolean backOnTrackPlayButton1 = false;
	public static boolean backOnTrackPauseButton1 = false;
	public static boolean backOnTrackPlayButton2 = false;
	public static boolean backOnTrackPauseButton2 = false;
	public static boolean skin1Unlocked = false;
	public static boolean skin2Unlocked = false;
	public static boolean skin3Unlocked = false;
	public static boolean track4Unlocked = false;
	public static boolean track5Unlocked = false;
	public static boolean track6Unlocked = false;
	public static boolean fireball1Unlocked = false;
	public static boolean fireball2Unlocked = false;
	public static boolean fireball3Unlocked = false;
	public static boolean item4Unlocked = false;
	public static boolean item5Unlocked = false;
	public static boolean item6Unlocked = false;
	public static boolean menuTrack4Unlocked = false;
	public static boolean gameTrack1Set = true;
	public static boolean gameTrack2Set = true;
	public static boolean gameTrack3Set = true;
	public static boolean gameTrack4Set = true;
	public static boolean gameTrack5Set = false;
	public static boolean gameTrack6Set = false;
	public static boolean gameTrack7Set = false;
	public static boolean gameTrack8Set = true;
	public static boolean menuTrack1Set = true;
	public static boolean menuTrack2Set = true;
	public static boolean menuTrack3Set = true;
	public static boolean menuTrack4Set = true;
	public static boolean menuTrack5Set = false;
	public static boolean currentSkinLocked = false;
	public static boolean currentTrackLocked = false;
	public static boolean currentFireballLocked = false;
	public static boolean currentItemLocked = false;
	public static boolean mouseIsClickedDown = false;
	public static boolean mouseIsOffClickedObjectAndHeldDown = false;
	public static boolean keysAreInUse = false;
	public static boolean gameControllerInUse = false;
	public static boolean gameControllerInUseDI = false;
	public static boolean gameControllerSwitchBack = false;
	public static boolean gameControllerSwitchBackDI = false;
	public static boolean keysAreInUseSwitchBack = false;
	public static boolean escapePressedNegateAction = false;
	public static boolean enterButtonPushedDown = false;
	public static boolean skipAnimations = false;
	public static boolean areYouSureBoolean = false;
//	public static boolean revertControllerSettings = false;
	public static boolean scoreFollowingBoolean = false;
	public static boolean starExplode = false;
	public static boolean ufoSpawned = false;
	public static boolean alienisDead = false;
	public static int upKey = KeyEvent.VK_W;
	public static int downKey = KeyEvent.VK_S;
	public static int leftKey = KeyEvent.VK_A;
	public static int rightKey = KeyEvent.VK_D;
	public static int shootKey = KeyEvent.VK_SPACE;
	public static int itemKey = KeyEvent.VK_E;
	public static int pauseKey = KeyEvent.VK_ENTER;
	public static int cancelKey = KeyEvent.VK_ESCAPE;
	public static int mx = 0;
	public static int my = 0;
	public static int totalPoints = 0;
	public static int highScore = 0;
	public static int selectorButtonPosition = 0;
	public static int gameTrackPosition = 0;
	public static int gameTrackCurrentlyPlaying = -1;
	public static int menuTrackPosition = 0;
	public static int menuTrackCurrentlyPlaying = -1;
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
	public static int gamepadKeyboardLetterPosition = 0;
	public static String writeOnceProperty = "";
	public static ArrayList<String> writeMultipleProperty = new ArrayList<String>();
	public static String writeOnceString = "";
	public static ArrayList<String> writeMultipleString = new ArrayList<String>();
	public static String writeOnceUnlock = "";
	public static int selectorBPMP = 0;
	public static int helpLineRandomizer = 0;
	public static int rescanButtonCounter = 0;
	private double myTime = 0.0;
	private String itemName;
	private int numberOfFireBallsShot = 0;
	private int numberOfFireBallsShotDecoy = 0;
	private int fireworksShot = 0;
	public static char postLetter = '=';
	public static float imageTranslucent = 0;
	private double imageTranslucentVelocity = 0;
	private long imageTranslucentTimer = 0;
	public volatile long serverErrorMessageTimer = 0;
	public volatile long dotdotdotTimer = 0;
	private long itemAnimationTimer = 0;
	private long itemAnimationTimer2 = 0;
	private long itemAnimationTimer3 = 0;
	private long traverseTime = 0;
	private long traverseTime2 = 0;
	private long controllerSensitivityTimer = 0;
	private long slowDownForAnalogTimer = 0;
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
	private boolean itemTransitionSetup = false;
	private boolean spawnItem = false;
	private boolean spawnBulletBill = false;
	private boolean spawn1BulletBillAtATime = false;
	private boolean powerUpFirework1 = false;
	private boolean powerUpFirework2 = false;
	private boolean powerUpFirework3 = false;
	private long bowserBulletBillSpawningTimer = 0;
	private long transitionTimer = 0;
	private long gameStartSoundTimer = 0;
	public volatile static long lastDisconnectedTimer = 0;
	private static long pauseSoundFXTimer = 0;
	private long visualPauseTimer = 0;
	private long marioDancePosePauseTimer = 0;
	private long marioGrowthPosePauseTimer = 0;
	private long marioLetsGoPauseTimer = 0;
	private long itemWaitTimer = 0;
	private long itemFlyingTimer1 = 0;
	private long joystickTimer = 0;
	private long waitToPause = 0;
	private long cheatTimer = 0;
	private String cheatString = "";
	public static String saveName = "";
	private double itemFlyingAwayY = 0;
	private double itemFlyingAwayX = 0;
	private boolean fileScoreWritten = false;
	public static boolean serverVerified = false;
	public static boolean allUnlockedScreen = false;
	public static boolean dontStartOver = false;
	public static boolean backToGameOver = false;
	private static boolean userHasPaused = false;
	private static boolean soundFXisPlaying = false;
	private boolean soundFXBoolean = false;
	private boolean soundFXClip1Reset = false;
	private boolean marioDancePosePause = false;
	private boolean marioGrowthPosePause = false;
	private boolean marioLetsGoPause = false;
	public static int soundRandomizer = 0;
	public static int menuSoundLoopRandomizer = 0;
	private int spawningEnemiesinTransition = 0;
	private int spawningEnemiesDanceSync = 0;
	public static int marioVoiceRandomizer = 0;
	public static int sceneSkipCount = 0;
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
	private boolean bowserSpawnSetupBoolean = false;
	private long bowserSpawnSetup = 0;
	long julian = 0;
	long juliann = 0;
	int julianii = 0;
	public static LinkedList<SoundLoops> menuSoundLoops = new LinkedList<SoundLoops>();
	public static LinkedList<SoundLoops> gameSoundLoops = new LinkedList<SoundLoops>();
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
	LinkedList<SoundLoops> pointsSoundLoop = new LinkedList<SoundLoops>();
	public static SoundLoops creditsSong;
	public static SoundLoops fireballSFX;
	public static SoundLoops fireballPopSFX;
	public static SoundLoops pauseSoundFXSoundLoop;
	public static SoundLoops gameOverSoundLoop;
	public static SoundLoops gameOverWinningSoundLoop;
	public static SoundLoops gameOverWinningMikeTysonSoundLoop;
	public static SoundLoops gameOverWinningContraSoundLoop;
	public static SoundLoops gameOverIrisSoundLoop;
	public static SoundLoops marioStarSoundLoop;
	public static SoundLoops soundFXClip1SoundLoop;
	public static SoundLoops soundFXClip2SoundLoop;
	public static SoundLoops marioSpinningSoundLoop;
	public static SoundLoops marioDeathSoundLoop;
	public static SoundLoops itemSwooshSoundLoop;
	public static SoundLoops itemPauseSoundLoop;
	public static SoundLoops smbCoinPointsSoundLoop;
	public static SoundLoops smbCoinPoints2SoundLoop;
	public static SoundLoops smbFireworkSoundLoop;
	public static SoundLoops smb3CoinSoundLoop;
	public static SoundLoops smbPopSoundLoop;
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
	public static SoundLoops smb3TextSoundLoop;
	public static SoundLoops smwErrorSoundLoop;
	public static SoundLoops smwCheatSoundLoop;
	public static SoundLoops smwCheat2SoundLoop;
	public static SoundLoops smwCheat3SoundLoop;
	public static SoundLoops smwCheat4SoundLoop;
	public static SoundLoops smwCheat5SoundLoop;
	public static SoundLoops smwCheat6SoundLoop;
	public static SoundLoops smwCheat7SoundLoop;
	public static SoundLoops smwCheat8SoundLoop;
	public static SoundLoops smwCheat9SoundLoop;
	public static SoundLoops smwCheat10SoundLoop;
	public static SoundLoops smwCheatFullSoundLoop;
	//public static LinkedList<Clip> clipGarbageCollection = new LinkedList<Clip>();
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
	private ControlsController controlsController;
	private SineDraw sinewaves;
	private TrackController trackController;
	private CreditsController creditsController;
	private UFO ufo;
	
	public static BufferedImage title = null;
	public static BufferedImage gameOverTitle = null;
	private BufferedImage playTitle = null;
	public static BufferedImage shopTitle = null;
	public static BufferedImage helpTitleBigger = null;
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
	private BufferedImage resetTitle = null;
	private BufferedImage resetTitleGlow = null;
	private BufferedImage resetTitleClicked = null;
	private BufferedImage resetTitleSelected = null;
	private BufferedImage resetTitleSelectedClicked = null;
	private BufferedImage resetTitleSelectedNormal = null;
	private BufferedImage rescanTitle = null;
	private BufferedImage rescanTitleGlow = null;
	private BufferedImage rescanTitleClicked = null;
	private BufferedImage rescanTitleSelected = null;
	private BufferedImage rescanTitleSelectedClicked = null;
	private BufferedImage rescanTitleSelectedNormal = null;
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
	private BufferedImage submitTitle = null;
	private BufferedImage submitTitleGlow = null;
	private BufferedImage submitTitleClicked = null;
	private BufferedImage submitTitleSelected = null;
	private BufferedImage submitTitleSelectedClicked = null;
	private BufferedImage submitTitleSelectedNormal = null;
	private BufferedImage gamepadImageTitle = null;
	private BufferedImage gamepadImageTitleGlow = null;
	private BufferedImage gamepadImageTitleClicked = null;
	private BufferedImage gamepadImageTitleSelected = null;
	private BufferedImage gamepadImageTitleSelectedClicked = null;
	private BufferedImage gamepadImageTitleSelectedNormal = null;
	private BufferedImage noteImageTitle = null;
	private BufferedImage noteImageTitleGlow = null;
	private BufferedImage noteImageTitleClicked = null;
	private BufferedImage noteImageTitleSelected = null;
	private BufferedImage noteImageTitleSelectedClicked = null;
	private BufferedImage noteImageTitleSelectedNormal = null;
	private BufferedImage localTitle = null;
	private BufferedImage localTitleGlow = null;
	private BufferedImage localTitleClicked = null;
	private BufferedImage localTitleSelected = null;
	private BufferedImage localTitleSelectedClicked = null;
	private BufferedImage localTitleSelectedNormal = null;
	private BufferedImage globalTitle = null;
	private BufferedImage globalTitleGlow = null;
	private BufferedImage globalTitleClicked = null;
	private BufferedImage globalTitleSelected = null;
	private BufferedImage globalTitleSelectedClicked = null;
	private BufferedImage globalTitleSelectedNormal = null;
	private BufferedImage uploadTitle = null;
	private BufferedImage uploadTitleGlow = null;
	private BufferedImage uploadTitleClicked = null;
	private BufferedImage uploadTitleSelected = null;
	private BufferedImage uploadTitleSelectedClicked = null;
	private BufferedImage uploadTitleSelectedNormal = null;
	private BufferedImage resetLeaderboardTitle = null;
	private BufferedImage resetLeaderboardTitleGlow = null;
	private BufferedImage resetLeaderboardTitleClicked = null;
	private BufferedImage resetLeaderboardTitleSelected = null;
	private BufferedImage resetLeaderboardTitleSelectedClicked = null;
	private BufferedImage resetLeaderboardTitleSelectedNormal = null;
	private BufferedImage creditsTitle = null;
	private BufferedImage creditsTitleGlow = null;
	private BufferedImage creditsTitleClicked = null;
	private BufferedImage creditsTitleSelected = null;
	private BufferedImage creditsTitleSelectedClicked = null;
	private BufferedImage creditsTitleSelectedNormal = null;
	private BufferedImage trackPlayButtonTitle = null;
	private BufferedImage trackPlayButtonTitleGlow = null;
	private BufferedImage trackPlayButtonTitleClicked = null;
	private BufferedImage trackPauseButtonTitle = null;
	private BufferedImage trackPauseButtonTitleGlow = null;
	private BufferedImage trackPauseButtonTitleClicked = null;
	private BufferedImage trackStopButtonTitle = null;
	private BufferedImage trackStopButtonTitleGlow = null;
	private BufferedImage trackStopButtonTitleClicked = null;
	private BufferedImage trackButtonTitleSelected = null;
	private BufferedImage trackButtonTitleSelectedClicked = null;
	private BufferedImage trackButtonTitleSelectedNormal = null;
	private BufferedImage aButtonImage = null;
	private BufferedImage aButtonImageGlow = null;
	private BufferedImage aButtonImageClicked = null;
	private BufferedImage ltButtonImage = null;
	private BufferedImage ltButtonImageGlow = null;
	private BufferedImage ltButtonImageClicked = null;
	private BufferedImage gamepadButtonHolder = null;
	private BufferedImage gamepadButtonHolderGlow = null;
	private BufferedImage gamepadButtonHolderClicked = null;
	private BufferedImage dotSelectorPositionOrange = null;
	private BufferedImage dotSelectorPositionWhite = null;
	private BufferedImage currentlySelected10x10 = null;
	public static BufferedImage skinTitle = null;
	public static BufferedImage tracksTitle = null;
	public static BufferedImage fireballsTitle = null;
	public static BufferedImage itemsTitle = null;
	public static BufferedImage volumeTitle = null;
	public static BufferedImage sfxMusicTitle = null;
	public static BufferedImage skipAnimationsTitle = null;
	public static BufferedImage sendToServerTitle = null;
	public static BufferedImage gameTitle = null;
	public static BufferedImage menuTitle = null;
	public static BufferedImage keyboardTitle = null;
	public static BufferedImage xInputTitle = null;
	public static BufferedImage directInputTitle = null;
	public static BufferedImage upImageTitle = null;
	public static BufferedImage downImageTitle = null;
	public static BufferedImage leftImageTitle = null;
	public static BufferedImage rightImageTitle = null;
	public static BufferedImage shootImageTitle = null;
	public static BufferedImage itemImageTitle = null;
	public static BufferedImage pauseImageTitle = null;
	public static BufferedImage cancelImageTitle = null;
	public static BufferedImage totalPointsImage = null;
	public static BufferedImage gameTrackNumber = null;
	public static BufferedImage menuTrackNumber = null;
	public static BufferedImage skinNumber = null;
	public static BufferedImage trackNumber = null;
	public static BufferedImage fireballNumber = null;
	public static BufferedImage itemNumber = null;
	public static BufferedImage scoreImage = null;
	
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	public LinkedList<EntityC> ec;
	public LinkedList<EntityD> ed;
	public LinkedList<EntityE> ee;
	public LinkedList<EntityF> ef;
	
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
		CREDITS,
		SHOP,
		SETTINGS,
		CONTROLS,
		TRACKLIST,
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
			spriteSheetNESMikeTyson = loader.loadImage("/AnimationSpriteSheetNewNESMikeTyson.png");
			spriteSheetNESContra = loader.loadImage("/AnimationSpriteSheetNewNESContra.png");
			marioEntranceSprites = loader.loadImage("/marioentrancesprites.png");
			marioNES3EntranceSprites = loader.loadImage("/mario2entrancesprites.png");
			marioSNESFireLuigiEntranceSprites = loader.loadImage("/mario3entrancesprites.png");
			marioNESMikeTysonEntranceSprites = loader.loadImage("/mario4entrancesprites.png");
			marioNESContraEntranceSprites = loader.loadImage("/mario5entrancesprites.png");
			victoryPoseSpinningSprites = loader.loadImage("/winningposeflippinganimations.png");
			animatedStar = loader.loadImage("/animatedstar.png");
			animatedShootingStar = loader.loadImage("/shootingstarworadiant.png");
			ufoSprites = loader.loadImage("/ufoalien.png");
			xboxButtonsSpriteSheet = loader.loadImage("/xboxButtonsSpriteSheet.png");
			directInputButtonsSpriteSheet = loader.loadImage("/directInputButtonsSpriteSheet.png");
			mario1StarSpriteSheet = loader.loadImage("/mario1starspritesheet.png");
			marioItemsSpriteSheet = loader.loadImage("/marioItemssmaller.png");
			background = loader.loadImage("/starsbackgroundbigger.png");
			marioLives = loader.loadImage("/mariolivessprite.png");
			bowserSpriteSheet = loader.loadImage("/bowserspritesheet.png");
			bulletBillSpriteSheet = loader.loadImage("/bulletbillspritesheet.png");
			bombOmbSpriteSheet = loader.loadImage("/bombombbb.png");
			marioPlayerStarAnimations = loader.loadImage("/marioplayerstaranimations.png");
			marioItemAnimationSheet = loader.loadImage("/marioitemanimations.png");
			marioItemAnimationBackgroundSheet = loader.loadImage("/BackgroundBlur/starsbackgroundbiggerblur14.png");
			bigMarioItemAnimationSheet = loader.loadImage("/mario-big.png");
			bigMario2ItemAnimationSheet = loader.loadImage("/mario-big2.png");
			bigMario3ItemAnimationSheet = loader.loadImage("/mario-big3.png");
			bigMario4ItemAnimationSheet = loader.loadImage("/mario-big4.png");
			bigMario5ItemAnimationSheet = loader.loadImage("/mario-big5.png");
			bigMario6ItemAnimationSheet = loader.loadImage("/mario-big6.png");
			lakituDeathAnimationSheet = loader.loadImage("/lakitudeathanimation.png");
			dancingAnimationSheet = loader.loadImage("/dancinganimations.png");
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
			wasdButtonImagesSpriteSheet = loader.loadImage("/mario3fonteLAYEDOUTSMALLERSpriteSheet.png");
			goombaDeathSpriteSheet = loader.loadImage("/goombadeath.png");
			yoshiSpriteSheet = loader.loadImage("/yoshisprites.png");
			title = loader.loadImage("/koopasinvaderstitlebigger.png");
			gameOverTitle = loader.loadImage("/gameover1bigger.png");
			playTitle = loader.loadImage("/newplaybutton.png");
			shopTitle = loader.loadImage("/newshopbutton.png");
			exitTitle = loader.loadImage("/newexitbutton.png");
			helpTitleBigger = loader.loadImage("/help.png");
			backButtonTitle = loader.loadImage("/backButtonSmaller.png");
			resumeTitle = loader.loadImage("/newresumebutton.png");
			homeTitle = loader.loadImage("/newhomebutton.png");
			setScoreTitle = loader.loadImage("/setScoreSmaller.png");
			setScoreTitleBigger= loader.loadImage("/setScore.png");
			scoreTitle= loader.loadImage("/scoretitle.png");
			zeroImage = loader.loadImage("/zeroimage.png");
			leaderboardTitle = loader.loadImage("/leaderboardSmaller.png");
			leaderboardTitleBigger = loader.loadImage("/leaderboard.png");
			helpTitle = loader.loadImage("/newhelpbuttonSmaller.png");
			settingsTitle = loader.loadImage("/newsettingsbutton.png");
			settingsTitleBigger = loader.loadImage("/newsettingstitle.png");
			controlsTitle = loader.loadImage("/newcontrolstitle.png");
			tracklistTitle = loader.loadImage("/newtrackstitle.png");
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
			resetTitle = loader.loadImage("/newresetbutton.png");
			resetTitleGlow = loader.loadImage("/newresetbuttonglow.png");
			resetTitleClicked = loader.loadImage("/newresetbuttonclicked.png");
			resetTitleSelected = loader.loadImage("/newresetbuttonselected.png");
			resetTitleSelectedClicked = loader.loadImage("/newresetbuttonselectedclicked.png");
			resetTitleSelectedNormal = loader.loadImage("/newresetbuttonselectednormal.png");
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
			submitTitle = loader.loadImage("/newsubmitbutton.png");
			submitTitleGlow = loader.loadImage("/newsubmitbuttonglow.png");
			submitTitleClicked = loader.loadImage("/newsubmitbuttonclicked.png");
			submitTitleSelected = loader.loadImage("/newsubmitbuttonselected.png");
			submitTitleSelectedClicked = loader.loadImage("/newsubmitbuttonselectedclicked.png");
			submitTitleSelectedNormal = loader.loadImage("/newsubmitbuttonselectednormal.png");
			gamepadImageTitle = loader.loadImage("/gamepadImage.png");
			gamepadImageTitleGlow = loader.loadImage("/gamepadImageglow.png");
			gamepadImageTitleClicked = loader.loadImage("/gamepadImageclicked.png");
			gamepadImageTitleSelected = loader.loadImage("/gamepadImageselected.png");
			gamepadImageTitleSelectedClicked = loader.loadImage("/gamepadImageselectedclicked.png");
			gamepadImageTitleSelectedNormal = loader.loadImage("/gamepadImageselectednormal.png");
			noteImageTitle = loader.loadImage("/noteImage.png");
			noteImageTitleGlow = loader.loadImage("/noteImageglow.png");
			noteImageTitleClicked = loader.loadImage("/noteImageclicked.png");
			noteImageTitleSelected = loader.loadImage("/noteImageselected.png");
			noteImageTitleSelectedClicked = loader.loadImage("/noteImageselectedclicked.png");
			noteImageTitleSelectedNormal = loader.loadImage("/noteImageselectednormal.png");
			localTitle = loader.loadImage("/newlocalbutton.png");
			localTitleGlow = loader.loadImage("/newlocalbuttonglow.png");
			localTitleClicked = loader.loadImage("/newlocalbuttonclicked.png");
			localTitleSelected = loader.loadImage("/newlocalbuttonselected.png");
			localTitleSelectedClicked = loader.loadImage("/newlocalbuttonselectedclicked.png");
			localTitleSelectedNormal = loader.loadImage("/newlocalbuttonselectednormal.png");
			globalTitle = loader.loadImage("/newglobalbutton.png");
			globalTitleGlow = loader.loadImage("/newglobalbuttonglow.png");
			globalTitleClicked = loader.loadImage("/newglobalbuttonclicked.png");
			globalTitleSelected = loader.loadImage("/newglobalbuttonselected.png");
			globalTitleSelectedClicked = loader.loadImage("/newglobalbuttonselectedclicked.png");
			globalTitleSelectedNormal = loader.loadImage("/newglobalbuttonselectednormal.png");
			uploadTitle = loader.loadImage("/newuploadbutton.png");
			uploadTitleGlow = loader.loadImage("/newuploadbuttonglow.png");
			uploadTitleClicked = loader.loadImage("/newuploadbuttonclicked.png");
			uploadTitleSelected = loader.loadImage("/newuploadbuttonselected.png");
			uploadTitleSelectedClicked = loader.loadImage("/newuploadbuttonselectedclicked.png");
			uploadTitleSelectedNormal = loader.loadImage("/newuploadbuttonselectednormal.png");
			resetLeaderboardTitle = loader.loadImage("/newresetleaderboardbutton.png");
			resetLeaderboardTitleGlow = loader.loadImage("/newresetleaderboardbuttonglow.png");
			resetLeaderboardTitleClicked = loader.loadImage("/newresetleaderboardbuttonclicked.png");
			resetLeaderboardTitleSelected = loader.loadImage("/newresetleaderboardbuttonselected.png");
			resetLeaderboardTitleSelectedClicked = loader.loadImage("/newresetleaderboardbuttonselectedclicked.png");
			resetLeaderboardTitleSelectedNormal = loader.loadImage("/newresetleaderboardbuttonselectednormal.png");
			rescanTitle = loader.loadImage("/newrescanbutton.png");
			rescanTitleGlow = loader.loadImage("/newrescanbuttonglow.png");
			rescanTitleClicked = loader.loadImage("/newrescanbuttonclicked.png");
			rescanTitleSelected = loader.loadImage("/newrescanbuttonselected.png");
			rescanTitleSelectedClicked = loader.loadImage("/newrescanbuttonselectedclicked.png");
			rescanTitleSelectedNormal = loader.loadImage("/newrescanbuttonselectednormal.png");
			creditsTitle = loader.loadImage("/newcreditsbutton.png");
			creditsTitleGlow = loader.loadImage("/newcreditsbuttonglow.png");
			creditsTitleClicked = loader.loadImage("/newcreditsbuttonclicked.png");
			creditsTitleSelected = loader.loadImage("/newcreditsbuttonselected.png");
			creditsTitleSelectedClicked = loader.loadImage("/newcreditsbuttonselectedclicked.png");
			creditsTitleSelectedNormal = loader.loadImage("/newcreditsbuttonselectednormal.png");
			trackPlayButtonTitle = loader.loadImage("/trackplaybutton.png");
			trackPlayButtonTitleGlow = loader.loadImage("/trackplaybuttonglow.png");
			trackPlayButtonTitleClicked = loader.loadImage("/trackplaybuttonclicked.png");
			trackPauseButtonTitle = loader.loadImage("/trackpausebutton.png");
			trackPauseButtonTitleGlow = loader.loadImage("/trackpausebuttonglow.png");
			trackPauseButtonTitleClicked = loader.loadImage("/trackpausebuttonclicked.png");
			trackStopButtonTitle = loader.loadImage("/trackstopbutton.png");
			trackStopButtonTitleGlow = loader.loadImage("/trackstopbuttonglow.png");
			trackStopButtonTitleClicked = loader.loadImage("/trackstopbuttonclicked.png");
			trackButtonTitleSelected = loader.loadImage("/trackbuttonselected.png");
			trackButtonTitleSelectedNormal = loader.loadImage("/trackbuttonselectednormal.png");
			trackButtonTitleSelectedClicked = loader.loadImage("/trackbuttonselectedclicked.png");
			dotSelectorPositionOrange = loader.loadImage("/escapedenterdot.png");
			dotSelectorPositionWhite = loader.loadImage("/escapedenterdotwhite.png");
			aButtonImage = loader.loadImage("/aButtonImage.png");
			ltButtonImage = loader.loadImage("/ltButtonImage.png");
			gamepadButtonHolder = loader.loadImage("/gamepadButtonHolder.png");
			serverErrorMessageImage = loader.loadImage("/cantconnecttotheserverdots.png");
			updateToConnectImage = loader.loadImage("/updatetoconnecttotheserver.png");
			lockedImage = loader.loadImage("/lockedImage.png");
			trackSetBox = loader.loadImage("/tracksetselectedbox.png");
			redBowserIcon = loader.loadImage("/redbowserredoutlinesmallerr.png");
			volumeSlider = loader.loadImage("/volumeslider.png");
			volumeSliderGlow = loader.loadImage("/volumesliderglow.png");
			volumeSliderClicked = loader.loadImage("/volumesliderclicked.png");
			volumeSliderSelected = loader.loadImage("/volumesliderselected.png");
			volumeSliderSelectedNormal = loader.loadImage("/volumesliderselectednormal.png");
			volumeSliderSelectedClicked = loader.loadImage("/volumesliderselectedclicked.png");
			emptyVolumeSlider = loader.loadImage("/emptyvolumeslider.png");
			areYouSure = loader.loadImage("/areyousure.png");
			congrats = loader.loadImage("/congratulationsyouhaveunlockedanewtrack.png");
			connectingTransparent = loader.loadImage("/connectingtransparent.png");
			dotdotdot = loader.loadImage("/dotdotdot.png");
			currentlySelected10x10 = loader.loadImage("/currentlyselected10x10.png");
			skinTitle = loader.loadImage("/newskinbutton.png"); 
			tracksTitle = loader.loadImage("/newtracksbutton.png");
			fireballsTitle = loader.loadImage("/newfireballsbutton.png");
			itemsTitle = loader.loadImage("/newitemsbutton.png");
			volumeTitle = loader.loadImage("/newvolumebutton.png");
			sfxMusicTitle = loader.loadImage("/newsfxmusicbutton.png");
			gameTitle = loader.loadImage("/newgamebutton.png");
			menuTitle = loader.loadImage("/newmenubutton.png");
			skipAnimationsTitle = loader.loadImage("/newskipanimationsbutton.png");
			sendToServerTitle = loader.loadImage("/sendtoserverimage.png");
			keyboardTitle = loader.loadImage("/newkeyboardbutton.png");
			xInputTitle = loader.loadImage("/newxinputbutton.png");
			directInputTitle = loader.loadImage("/newdirectinputbutton.png");
			upImageTitle = loader.loadImage("/newupbutton.png");
			downImageTitle = loader.loadImage("/newdownbutton.png");
			leftImageTitle = loader.loadImage("/newleftbutton.png");
			rightImageTitle = loader.loadImage("/newrightbutton.png");
			shootImageTitle = loader.loadImage("/newshootselectbutton.png");
			itemImageTitle = loader.loadImage("/newuseitembutton.png");
			pauseImageTitle = loader.loadImage("/newpausestartbutton.png");
			cancelImageTitle = loader.loadImage("/newcancelbutton.png");
			transparentBlocks = loader.loadImage("/randomtransparentblocks.png");
			itemBackground.add(loader.loadImage("/BackgroundBlur/starsbackgroundbiggerblur1.png"));
			itemBackground.add(loader.loadImage("/BackgroundBlur/starsbackgroundbiggerblur2.png"));
			itemBackground.add(loader.loadImage("/BackgroundBlur/starsbackgroundbiggerblur3.png"));
			itemBackground.add(loader.loadImage("/BackgroundBlur/starsbackgroundbiggerblur4.png"));
			itemBackground.add(loader.loadImage("/BackgroundBlur/starsbackgroundbiggerblur5.png"));
			itemBackground.add(loader.loadImage("/BackgroundBlur/starsbackgroundbiggerblur6.png"));
			itemBackground.add(loader.loadImage("/BackgroundBlur/starsbackgroundbiggerblur7.png"));
			itemBackground.add(loader.loadImage("/BackgroundBlur/starsbackgroundbiggerblur8.png"));
			itemBackground.add(loader.loadImage("/BackgroundBlur/starsbackgroundbiggerblur9.png"));
			itemBackground.add(loader.loadImage("/BackgroundBlur/starsbackgroundbiggerblur10.png"));
			itemBackground.add(loader.loadImage("/BackgroundBlur/starsbackgroundbiggerblur11.png"));
			itemBackground.add(loader.loadImage("/BackgroundBlur/starsbackgroundbiggerblur12.png"));
			itemBackground.add(loader.loadImage("/BackgroundBlur/starsbackgroundbiggerblur13.png"));
			itemBackground.add(loader.loadImage("/BackgroundBlur/starsbackgroundbiggerblur14.png"));
			//itemBackground = loader.loadImagesfromFolder("/res/BackgroundBlur");
			
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
		controlsController = new ControlsController(tex,this);
		sinewaves = new SineDraw(this);
		trackController = new TrackController(this, sinewaves);
		creditsController = new CreditsController(tex, this);
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		ec = c.getEntityC();
		ed = c.getEntityD();
		ee = c.getEntityE();
		ef = c.getEntityF();
		
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
		this.closeGame();
		System.exit(1);
	}
	
	public void run(){
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;//game pace(lower = slower/ higher = faster)
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
			if(!this.hasFocus() && !userHasPaused && !paused) {
				if(!windowMinimized) {
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
						if(System.currentTimeMillis() < bowserSpawnSetup && bowserSpawnSetupBoolean == true) {
							bowserSpawnSetup = 0;
							bowserSpawnSetupBoolean = false;
						}
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


					if(xRBoolean == true){
						p.setRunningStartUp(1.2);
						p.setVelX(p.getRunningStartUp());
						runningTimerActivated = true;
						p.setRunningStartR(true);
					}
					else{
						if((System.currentTimeMillis() - runningTimerLong > 666/2 || (p.getVelX() <= -5)) && p.getVelX() != 0){														//This activates sliding animation for left side
							slowingDownActivatedl = true;
							slowingDownTimerLong = System.currentTimeMillis() + 200;
							slowingDown = -1.73;
							p.setVelX(slowingDown);
							runningTimerLong = 0;
							runningTimerActivatedResponse = false;
						}
						else
							p.setVelX(0);
					}
					
					if(xLBoolean == true){
						p.setRunningStartUp(-1.2);
						p.setVelX(p.getRunningStartUp());
						runningTimerActivated = true;
						p.setRunningStartL(true);
					}
					else{
						if((System.currentTimeMillis() - runningTimerLong > 666/2 || (p.getVelX() >= 5)) && p.getVelX() != 0){														//This activates sliding animation for right side
							slowingDownActivatedr = true;
							slowingDownTimerLong = System.currentTimeMillis() + 200;
							slowingDown = 1.73;
							p.setVelX(slowingDown);
							runningTimerLong = 0;
							runningTimerActivatedResponse = false;
						}
						else
							p.setVelX(0);
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
					
					xLBoolean = false;
					xRBoolean = false;
					runningTimerLong = 0;
					runningTimerActivated = false;
					runningTimerActivatedResponse = false;
					keepRunningAfterPauseL = false;
					keepRunningAfterPauseR = false;
					p.setRunningStartL(false);
					p.setRunningStartR(false);
					
					windowMinimized = true;
				}
			}
			else if(windowMinimized)
				windowMinimized = false;
			if(!paused){
				p.tick();
				hud.tick();
				c.tick();
				bb.tick();
				//SPAWN SHELLS
				if(ec.isEmpty() && !eb.isEmpty() && spawnDone4 == false && traverseTime2 != System.currentTimeMillis()){	
					boolean spawnShell = true;
					Random rand = new Random();
					int i = rand.nextInt(eb.size());
					if(eb.get(i).getEntityBDead() == false && rand.nextInt(78) == 1) {
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
					traverseTime2 = System.currentTimeMillis();
				}
				if(spawnDone4 == true){												//Spawning Bowser Mechanics
					//hud.render(g);
					if((int)HUD.getTimer1() <= 0){
						//SPAWN BULLET BILLS
						if(bowserBulletBillSpawningTimer < System.currentTimeMillis() && c.getEntityB().get(0).getEntityBDead() == false) {
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
				if(hud.getItemObtained() == false && ed.size() < 1) {
					Random rand = new Random();
					int j = rand.nextInt(1000);//400000
//					int j = 0;
					if(j < 2) { //&& ed.size() < 1) { //&& p.getMarioInvincible() == false && hud.getItemObtained() == false){
						int pp = rand.nextInt(16);
						if(pp == 0) {
							if(p.getMarioInvincible() == false) { //&& hud.getItemObtained() == false) {
								int p = rand.nextInt(2);
								if(p == 0)
									c.addEntity(new Mario1Star(-16,this.playerY() - 32,tex, this));//new Mario1Star
								else
									c.addEntity(new Mario1Star((Game.WIDTH * 2) + 16,this.playerY() - 32,tex, this));//new Mario1Star
							}
						}
						else {//if(hud.getItemObtained() == false){
							int p = rand.nextInt(2);
							if(p == 0)
								c.addEntity(new ItemBall(-16,this.playerY() - 32,tex, this));
							else
								c.addEntity(new ItemBall((Game.WIDTH * 2) + 16,this.playerY() - 32,tex, this));
						}
					}
				}
			}
		}
		else if(State == State.MENU) {
			Random rand = new Random();
			if(rand.nextInt(50000) == 1 && ufoSpawned == false) {
				if(rand.nextInt(2) == 1) {
					switch(rand.nextInt(4)) {
						case 0:
							c.addEntity(new UFO(Game.WIDTH * Game.SCALE,70,tex,c,this));
							break;
						case 1:
							c.addEntity(new UFO(Game.WIDTH * Game.SCALE,170,tex,c,this));
							break;
						case 2:
							c.addEntity(new UFO(Game.WIDTH * Game.SCALE,270,tex,c,this));
							break;
						case 3:
							c.addEntity(new UFO(Game.WIDTH * Game.SCALE,370,tex,c,this));
							break;
						default:
							c.addEntity(new UFO(Game.WIDTH * Game.SCALE,70,tex,c,this));
							break;
					}
				}
				else {
					switch(rand.nextInt(4)) {
						case 0:
							c.addEntity(new UFO(-40,70,tex,c,this));
							break;
						case 1:
							c.addEntity(new UFO(-40,170,tex,c,this));
							break;
						case 2:
							c.addEntity(new UFO(-40,270,tex,c,this));
							break;
						case 3:
							c.addEntity(new UFO(-40,370,tex,c,this));
							break;
						default:
							c.addEntity(new UFO(-40,70,tex,c,this));
							break;
					}
				}
				ufoSpawned = true;
			}
			if(ufoSpawned)
				c.tick();
		}
		else if(State != STATE.TRANSITION_ENTRANCE && ufoSpawned)
			c.tick();
		if(!(State == STATE.TRANSITION_ITEM))
			starAnim.runAnimation();
		if(State == STATE.TRANSITION_ENTRANCE)
			c.tick();
		if(State == STATE.HELP) {
			if(imageTranslucent >= 0.99 && imageTranslucentTimer < System.currentTimeMillis()){
				if(imageIsGone) {
					imageIsGone = false;
					imageTranslucentTimer2 = System.currentTimeMillis() + 5000;
				}
				if(imageTranslucentTimer2 < System.currentTimeMillis())
					imageTranslucentVelocity += -0.01;
			}
			else if(imageTranslucent <= 0.01 && imageTranslucentTimer < System.currentTimeMillis()){
				imageTranslucentVelocity += 0.01;
			}
			
			if(imageTranslucent <= 0.01 && imageIsGone == false){
				imageTranslucentTimer = System.currentTimeMillis() + 3500;
				imageIsGone = true;
				helpLinesLastSetup();
			}
			if(imageTranslucent + imageTranslucentVelocity <= 1 &&
					imageTranslucent + imageTranslucentVelocity >= 0)
				imageTranslucent += imageTranslucentVelocity;
			
		}
		if(State == STATE.CREDITS) {
			creditsController.tick();
			//c.tick();
		}
		if(State == STATE.CONTROLS) {
			if(ControlsController.buttonChangeTimer < System.currentTimeMillis() && gameControllerSwitchBackDI) {
				if(gameControllerInUseDI)
					gameControllerInUseDI = false;
				else
					gameControllerInUseDI = true;
				gameControllerSwitchBackDI = false;
			}
			if(ControlsController.buttonChangeTimer < System.currentTimeMillis() && gameControllerSwitchBack) {
				if(gameControllerInUse)
					gameControllerInUse = false;
				else
					gameControllerInUse = true;
				gameControllerSwitchBack = false;
			}
			if(ControlsController.buttonChangeTimer < System.currentTimeMillis() && keysAreInUseSwitchBack) {
				if(keysAreInUse)
					keysAreInUse = false;
				else
					keysAreInUse = true;
				keysAreInUseSwitchBack = false;
			}
			//c.tick();
		}
		if(State == STATE.SHOP) {
			shop.tick();
		}
		if(State == STATE.TRACKLIST) {
			sinewaves.tick();
			trackController.tick();
		}else if(State != STATE.GAME && State != STATE.TRANSITION_ENTRANCE && menuMusicStopped) {
			if(Game.gameTrackCurrentlyPlaying != -1) {
				if(!Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).clipIsActive()) {
					Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).stop();
					Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).setFramePosition(0);
					Game.gameTrackCurrentlyPlaying = -1;
					Game.trackCurrentlyPlaying = false;
					Game.menuMusicStopped = false;
					Random rand = new Random();
					menuSoundLoopRandomizer = rand.nextInt(5);
					VolumeSlider.adjustVolume(menuSoundLoops, gameSoundLoops);
					menuSoundLoopRandomizer = VolumeSlider.menuTrackSetup(menuSoundLoopRandomizer);
					if(menuSoundLoopRandomizer == -2)
						menuSoundLoopRandomizer = 5;
					menuSoundLoops.get(menuSoundLoopRandomizer).setFramePosition(0);
					if(menuSoundLoopRandomizer == 2) 
//						menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(864007, 1728014);//Seconds.millisec * 44100
						menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(432004, 864007);//Seconds.millisec * 22050
					else if(menuSoundLoopRandomizer == 3) 
//						menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(464196, 1949705);//Seconds.millisec * 44100
						menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(232098, 974853);//Seconds.millisec * 22050
					else if(menuSoundLoopRandomizer == 4)
//						menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(545561, 2291391);//Seconds.millisec * 44100
						menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(272781, 1145696);//Seconds.millisec * 22050
					else
						this.menuSoundLoops.get(menuSoundLoopRandomizer).loop();
					this.menuSoundLoops.get(menuSoundLoopRandomizer).setSoundLoopBoolean(true);
					menuSoundSet = true;
				}
			}
			else if(Game.menuTrackCurrentlyPlaying != -1) {
				if(!Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).clipIsActive()) {
					Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).stop();
					Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).setFramePosition(0);
					Game.menuTrackCurrentlyPlaying = -1;
					Game.trackCurrentlyPlaying = false;
					Game.menuMusicStopped = false;
					Random rand = new Random();
					menuSoundLoopRandomizer = rand.nextInt(5);
					VolumeSlider.adjustVolume(menuSoundLoops, gameSoundLoops);
					menuSoundLoopRandomizer = VolumeSlider.menuTrackSetup(menuSoundLoopRandomizer);
					if(menuSoundLoopRandomizer == -2)
						menuSoundLoopRandomizer = 5;
					menuSoundLoops.get(menuSoundLoopRandomizer).setFramePosition(0);
					if(menuSoundLoopRandomizer == 2) 
//						menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(864007, 1728014);//Seconds.millisec * 44100
						menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(432004, 864007);//Seconds.millisec * 22050
					else if(menuSoundLoopRandomizer == 3) 
//						menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(464196, 1949705);//Seconds.millisec * 44100
						menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(232098, 974853);//Seconds.millisec * 22050
					else if(menuSoundLoopRandomizer == 4)
//						menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(545561, 2291391);//Seconds.millisec * 44100
						menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(272781, 1145696);//Seconds.millisec * 22050
					else
						this.menuSoundLoops.get(menuSoundLoopRandomizer).loop();
					this.menuSoundLoops.get(menuSoundLoopRandomizer).setSoundLoopBoolean(true);
					menuSoundSet = true;
				}
			}
		}
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
		if(serverErrorMessage && State != STATE.HELP && State != STATE.GAME) {
			//System.out.println("STILL HERE");
			if(serverErrorMessageTimer < System.currentTimeMillis()) {
				if(imageTranslucent >= 0.99 && imageTranslucentTimer < System.currentTimeMillis()){
					imageTranslucentVelocity += -0.01;
				}
				if(imageTranslucent <= 0.01){
					imageTranslucentTimer = System.currentTimeMillis() + 500;
					//serverErrorMessage = false;
				}
				if(imageTranslucent + imageTranslucentVelocity <= 1 &&
						imageTranslucent + imageTranslucentVelocity >= 0)
					imageTranslucent += imageTranslucentVelocity;
			}
			else {
				imageTranslucent = 1;
			}
		}
		if(device.poll()) 
			deviceInput();
		device.poll();
		//CHANGE THIS
		if(gameControllerInUseDI) {
			ReadAllEvents.tick();
		}
		//CHANGE THIS
//		if(ReadAllEvents.event != null)
//			directInput();
//		/* Create an event object for the underlying plugin to populate */
//		Event event = new Event();
//
//		/* Get the available controllers */
//		net.java.games.input.Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
//		for (int i = 0; i < controllers.length; i++) {
//		    /* Remember to poll each one */
//		    controllers[i].poll();
//
//		    /* Get the controllers event queue */
//		    EventQueue queue = controllers[i].getEventQueue();
//
//		    /* For each object in the queue */
//		    while (queue.getNextEvent(event)) {
//		        /* Get event component */
//		        Component comp = event.getComponent();
//
//		        /* Process event (your awesome code) */
//		        System.out.println(controllers[i].getName() + comp.getIdentifier());
//		    }
//		}
	}
	private void renderConnectingMessage() throws IOException{
		BufferStrategy bs = this.getBufferStrategy();

		if(bs == null){
			createBufferStrategy(3);//or 2 
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(connectingTransparent, 0, 0, null);
		if(System.currentTimeMillis()+2000 < dotdotdotTimer) {
			g.drawImage(Game.resize(tex.dotdotdot[0], tex.dotdotdot[0].getWidth()*2, tex.dotdotdot[0].getHeight()*2),Game.WIDTH-(tex.dotdotdot[0].getWidth()),313,null);
		}else if(System.currentTimeMillis()+1000 < dotdotdotTimer) {
			g.drawImage(Game.resize(tex.dotdotdot[1], tex.dotdotdot[1].getWidth()*2, tex.dotdotdot[1].getHeight()*2),Game.WIDTH-(tex.dotdotdot[1].getWidth()),313,null);
		}else if(System.currentTimeMillis() < dotdotdotTimer) {
			g.drawImage(Game.resize(tex.dotdotdot[2], tex.dotdotdot[2].getWidth()*2, tex.dotdotdot[2].getHeight()*2),Game.WIDTH-(tex.dotdotdot[2].getWidth()),313,null);
		}else {
			dotdotdotTimer = System.currentTimeMillis() + 3000;
		}
//		g.dispose();
//		if(connectingToServer)
//			System.out.println(connectingToServer);
	}
	private void render() throws IOException{
		BufferStrategy bs = this.getBufferStrategy();
		
		//System.out.println(connectingToServer);
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
		if(serverErrorMessage) {
			Graphics2D g2dd = (Graphics2D) bs.getDrawGraphics(); 			//BLOCKS

			g2dd.setComposite(makeComposite(imageTranslucent));
			if(!updateToConnect)
				g2dd.drawImage(serverErrorMessageImage, 0, 0, null);
			else
				g2dd.drawImage(updateToConnectImage, 0, 0, null);

			if(((double)imageTranslucent <= 0.015 && imageTranslucent != 0) || serverErrorMessageTimer+6000 < System.currentTimeMillis()){
				imageStayOn = false;
				imageIsGone = false;
				imageTranslucent = 0;
				imageTranslucentVelocity = 0;
				imageTranslucentTimer = 0;
				imageTranslucentTimer2 = 0;
				serverErrorMessageTimer = 0;
				g2dd.setComposite(makeComposite(1));
				g2dd.dispose();
				serverErrorMessage = false;
				updateToConnect = false;
			}
		}
		if (gameOverBoolean == true && State != STATE.TRANSITION_DEATH) {
			State = STATE.TRANSITION_DEATH;
			if(LeaderboardController.getFromSettings("Total Points: ").equals("")) {
				if(hud.getScore() < 999999) {
					LeaderboardController.writeToSettings("Total Points: ", String.valueOf(hud.getScore()));
					if(highScore < 999999)
						LeaderboardController.writeToSettings("High Score: ", String.valueOf(hud.getScore()));
				}
				else {
					LeaderboardController.writeToSettings("Total Points: ", "999999");
					if(highScore < hud.getScore())
						LeaderboardController.writeToSettings("High Score: ", String.valueOf(hud.getScore()));
				}
			}
			else {
				int i = Integer.valueOf(LeaderboardController.getFromSettings("Total Points: "));
				i += hud.getScore();
				if(i < 999999){}
				else
					i = 999999;
				LeaderboardController.writeToSettings("Total Points: ", String.valueOf(i));
				if(highScore < hud.getScore())
					LeaderboardController.writeToSettings("High Score: ", String.valueOf(hud.getScore()));
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
				if(trackCurrentlyPlaying) {
					menuSoundLoops.get(this.menuSoundLoopRandomizer).setFramePosition(0);
					menuSoundLoops.get(this.menuSoundLoopRandomizer).setSoundLoopBoolean(false);
					menuSoundSet = false;
					if(gameTrackCurrentlyPlaying != -1) {
						gameSoundLoops.get(gameTrackCurrentlyPlaying).stop();
						gameSoundLoops.get(gameTrackCurrentlyPlaying).setFramePosition(0);
						gameTrackCurrentlyPlaying = -1;
					}
					else if(menuTrackCurrentlyPlaying != -1) {
						menuSoundLoops.get(menuTrackCurrentlyPlaying).stop();
						menuSoundLoops.get(menuTrackCurrentlyPlaying).setFramePosition(0);
						menuTrackCurrentlyPlaying = -1;
					}
					menuMusicStopped = false;
					trackCurrentlyPlaying = false;
				}
				if(soundSet == false){	
					for(int i = 0; i <= marioVoices.size()-1; i++) {
						marioVoices.get(i).stop();
						marioVoices.get(i).setFramePosition(0);
						marioVoices.get(i).setSoundLoopBoolean(false);
					}for(int i = 0; i <= luigiVoices.size()-1; i++) {
						luigiVoices.get(i).stop();
						luigiVoices.get(i).setFramePosition(0);
						luigiVoices.get(i).setSoundLoopBoolean(false);
					}
					int randomIPlus = 0;
					if(Game.track4Unlocked)
						randomIPlus++;
					if(Game.track5Unlocked)
						randomIPlus++;
					if(Game.track6Unlocked)
						randomIPlus++;
					Random rand = new Random();
					soundRandomizer = rand.nextInt(7) + (-3+randomIPlus);
					soundRandomizer = VolumeSlider.gameTrackSetup(soundRandomizer);
					//setsetup[VolumeSlider.TrackSetup]
					if(soundRandomizer != -4) {
						this.gameSoundLoops.get(soundRandomizer).stop();
						this.gameSoundLoops.get(soundRandomizer).setSoundLoopBoolean(true);
						this.gameSoundLoops.get(soundRandomizer).setFramePosition(0);
						this.gameSoundLoops.get(soundRandomizer).play();
						soundSet = true;
					}
					else {
						soundRandomizer = 8;
						this.gameSoundLoops.get(soundRandomizer).stop();
						this.gameSoundLoops.get(soundRandomizer).setSoundLoopBoolean(true);
						this.gameSoundLoops.get(soundRandomizer).setFramePosition(0);
						this.gameSoundLoops.get(soundRandomizer).play();
						soundSet = true;
					}
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
					soundFXTimer = System.currentTimeMillis() + 784;
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
					if(System.currentTimeMillis() % 60 == 0) {
//						chompedStarFX(p.getX(), p.getY(),p.getBounds().width,p.getBounds().height);
//						Game.starExplode = true;
//						Game.mx = (int)p.getX();
//						Game.my =(int) p.getY();
					}
//					System.out.println(soundFXClip1SoundLoop.getLongFramePosition());
//					System.out.println(powerUpFirework2);
					if(gameTrack1Set == false && gameTrack2Set == false && gameTrack3Set == false &&
							gameTrack4Set == false && gameTrack5Set == false && gameTrack6Set == false &&
							gameTrack7Set == false && gameTrack8Set == true && bowserSpawnSetupBoolean == true)
								Game.gameSoundLoops.get(soundRandomizer).stop();
					if(System.currentTimeMillis() < bowserSpawnSetup && bowserSpawnSetupBoolean == true) {
						bowserSpawnSetup = 0;
						bowserSpawnSetupBoolean = false;
					}
					
					if(soundFXClip1SoundLoop.getLongFramePosition() > (926) && powerUpFirework1 == false) {
						Game.starExplode = true;
						Game.mx = (int)(p.getX()+(p.getBounds().width/2));
						Game.my = (int)(p.getY()+(p.getBounds().height/3));
						powerUpFirework1 = true;
					}else if(soundFXClip1SoundLoop.getLongFramePosition() > (6791) && powerUpFirework3 == false) {
						Game.starExplode = true;
						Game.mx = (int)(p.getX()+(p.getBounds().width/2));
						Game.my = (int)(p.getY()+(p.getBounds().height/3));
						powerUpFirework3 = true;
					}
					else if(soundFXClip1SoundLoop.getLongFramePosition() > (12657) && powerUpFirework2 == false) {
						Game.starExplode = true;
						Game.mx = (int)(p.getX()+(p.getBounds().width/2));
						Game.my = (int)(p.getY()+(p.getBounds().height/3));
						powerUpFirework2 = true;
					}/*else if(soundFXClip1SoundLoop.getLongFramePosition() > 24431 && powerUpFirework3 == false) {
						System.out.println("WE HERE");
						Game.starExplode = true;
						Game.mx = (int)p.getX();
						Game.my =(int) p.getY();
						powerUpFirework3 = true;
					}*/
					transparentBlocksAnim.drawAnimation(g,p.getX(), p.getY(), 0);
					//add visual effect
					if(!soundFXClip1Reset){
						Game.soundFXClip1SoundLoop.setSoundLoopBoolean(true);
						Game.soundFXClip1SoundLoop.play();
						soundFXClip1Reset = true;
						powerUpFirework1 = false;
						powerUpFirework2 = false;
						powerUpFirework3 = false;
					}
					//Game.soundFXClip1SoundLoop.loop();
				}
			}
			if(p.getMarioInvincible() == true){											//Setting up Star Sound
				
				if(this.gameSoundLoops.get(this.soundRandomizer).clipIsActive() && !(gameTrack1Set == false && gameTrack2Set == false && gameTrack3Set == false &&
						gameTrack4Set == false && gameTrack5Set == false && gameTrack6Set == false &&
						gameTrack7Set == false && gameTrack8Set == true))
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

					if(gameTrack1Set == true || gameTrack2Set == true || gameTrack3Set == true ||
						gameTrack4Set == true || gameTrack5Set == true || gameTrack6Set == true ||
						gameTrack7Set == true)//soundRandomizer != 8)
						Game.marioStarSoundLoop.continuePlaying();
					else if(gameTrack1Set == false && gameTrack2Set == false && gameTrack3Set == false &&
							gameTrack4Set == false && gameTrack5Set == false && gameTrack6Set == false &&
							gameTrack7Set == false && gameTrack8Set == true && bowserSpawnSetupBoolean == true)
								this.gameSoundLoops.get(soundRandomizer).continuePlaying();
					Game.marioStarSoundLoop.setSoundLoopBoolean(true);
				}
				
				if(System.currentTimeMillis() > soundFXTimer && soundFXisPlaying == true){							//checking if paused for soundFX Timer
					paused = false;
					soundFXisPlaying = false;
				}
				if(!paused && soundTimerSet == false){
					this.soundFXClip2SoundLoop.stop();
					if(gameTrack1Set == false && gameTrack2Set == false && gameTrack3Set == false &&
							gameTrack4Set == false && gameTrack5Set == false && gameTrack6Set == false &&
							gameTrack7Set == false && gameTrack8Set == true && bowserSpawnSetupBoolean == true)
								Game.gameSoundLoops.get(soundRandomizer).stop();
					soundFXClip1Reset = false;
					this.marioStarSoundLoop.setFramePosition(0);
					if(gameTrack1Set == true || gameTrack2Set == true || gameTrack3Set == true ||
						gameTrack4Set == true || gameTrack5Set == true || gameTrack6Set == true ||
						gameTrack7Set == true)//soundRandomizer != 8)
						this.marioStarSoundLoop.play();
					else if(gameTrack1Set == false && gameTrack2Set == false && gameTrack3Set == false &&
							gameTrack4Set == false && gameTrack5Set == false && gameTrack6Set == false &&
							gameTrack7Set == false && gameTrack8Set == true && bowserSpawnSetupBoolean == true)
								this.gameSoundLoops.get(soundRandomizer).continuePlaying();
					this.marioStarSoundLoop.setSoundLoopBoolean(true);
					soundTimerSet = true;
					marioHasBeenInvincible = true;
				}

				else if(paused == true && soundTimerSet == false){
					if(gameTrack1Set == false && gameTrack2Set == false && gameTrack3Set == false &&
							gameTrack4Set == false && gameTrack5Set == false && gameTrack6Set == false &&
							gameTrack7Set == false && gameTrack8Set == true && bowserSpawnSetupBoolean == true)
								Game.gameSoundLoops.get(soundRandomizer).stop();
					if(System.currentTimeMillis() % 60 == 0) {
//						chompedStarFX(p.getX(), p.getY(),p.getBounds().width,p.getBounds().height);
					}//Seconds.millisec * 44100
					if(System.currentTimeMillis() < bowserSpawnSetup && bowserSpawnSetupBoolean == true) {
						bowserSpawnSetup = 0;
						bowserSpawnSetupBoolean = false;
					}
					if(soundFXClip2SoundLoop.getLongFramePosition() > (706/2) && powerUpFirework1 == false) {
						Game.starExplode = true;
						Game.mx = (int)(p.getX()+(p.getBounds().width/2));
						Game.my = (int)(p.getY()+(p.getBounds().height/3));
						powerUpFirework1 = true;
					}
					else if(soundFXClip2SoundLoop.getLongFramePosition() > (11025/2) && powerUpFirework2 == false) {
						Game.starExplode = true;
						Game.mx = (int)(p.getX()+(p.getBounds().width/2));
						Game.my = (int)(p.getY()+(p.getBounds().height/3));
						powerUpFirework2 = true;
					}else if(soundFXClip2SoundLoop.getLongFramePosition() > (25710/2) && powerUpFirework3 == false) {
						Game.starExplode = true;
						Game.mx = (int)(p.getX()+(p.getBounds().width/2));
						Game.my = (int)(p.getY()+(p.getBounds().height/3));
						powerUpFirework3 = true;
					}
					transparentBlocksAnim.drawAnimation(g, p.getX(), p.getY(), 0);
					//add visual effect
					if(!soundFXClip1Reset){
						this.soundFXClip2SoundLoop.play();
						soundFXClip1Reset = true;
						powerUpFirework1 = false;
						powerUpFirework2 = false;
						powerUpFirework3 = false;
					}
//					this.soundFXClip2SoundLoop.loop();
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
					int soundRandomizerNoRepeat = soundRandomizer;
					int randomIPlus = 0;
					if(Game.track4Unlocked)
						randomIPlus++;
					if(Game.track5Unlocked)
						randomIPlus++;
					if(Game.track6Unlocked)
						randomIPlus++;
					Random rand = new Random();
					soundRandomizer = rand.nextInt(7) + (-3+randomIPlus);
					System.out.println("before"+soundRandomizer);
					if(soundRandomizer == soundRandomizerNoRepeat && soundRandomizer == 6)
						soundRandomizer = 0;
					else if(soundRandomizer == soundRandomizerNoRepeat && soundRandomizer != 7)
						soundRandomizer++;
					soundRandomizer = VolumeSlider.gameTrackSetup(soundRandomizer,soundRandomizerNoRepeat);
					//setsetup[VolumeSlider.TrackSetup]
//					System.out.println("after"+soundRandomizer);
					if(soundRandomizer == -4)
						soundRandomizer = 8;
					if(soundRandomizer != -1) {
						this.gameSoundLoops.get(soundRandomizer).setSoundLoopBoolean(true);
						this.gameSoundLoops.get(soundRandomizer).setFramePosition(0);
						this.gameSoundLoops.get(soundRandomizer).play();
						soundSet = true;
					}
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
				if(itemName == null) {
					if(hud.getItemName() == null)
						itemName = "chainChompItem";
					else
						itemName = hud.getItemName();
				}
				switch(this.itemName){
				case "chainChompItem":
					c.addEntity(new ChainChompItem(p.getX(),p.getY()-50, tex, this));
					//spawnChainChomp
					break;
				case "bulletBillItem":
					c.addEntity(new BulletBillItem(p.getX(),p.getY()-50, tex, this));
					//spawnBulletBill
					break;
				case "bombOmbItem":
					c.addEntity(new BombOmbItem(p.getX(),p.getY()-50, tex, this));
					//spawnBombOmb
					break;
				case "cheepCheepsItem":
					c.addEntity(new CheepCheepsItemSpawner(p.getX(),p.getY()-50, tex, this));
					//spawnCheepCheeps
					break;
				case "ampItem":
					c.addEntity(new AmpItem(p.getX(),p.getY()-50, tex, this));
					//spawnCheepCheeps
					break;
				case "wigglerItem":
					c.addEntity(new WigglerItem(p.getX(),p.getY()-50, tex, this));
					//spawnCheepCheeps
					break;
				case "lakituItem":
					c.addEntity(new LakituItem(p.getX(),p.getY()-50, tex, this));
					//spawnCheepCheeps
					break;
				default:
					c.addEntity(new BulletBillItem(p.getX(),p.getY()-50, tex, this));
					//spawnBulletBill
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
			if(eb.isEmpty() && spawnDone4 == false && !userHasPaused){								//Spawning Bowser
				if(bowserSpawnSetupBoolean == false) {
					bowserSpawnSetup = System.currentTimeMillis() + 2000;
					bowserSpawnSetupBoolean = true;
				}
				if(gameTrack8Set && (gameSoundLoops.get(soundRandomizer).clipIsActive() && gameSoundLoops.get(soundRandomizer).getVolume() >= -70f) && System.currentTimeMillis() % 60 == 0){
					gameSoundLoops.get(soundRandomizer).shiftVolume(gameSoundLoops.get(soundRandomizer).getVolume(), gameSoundLoops.get(soundRandomizer).getVolume()-1f, 200);
				}
				if(!(System.currentTimeMillis() < bowserSpawnSetup)) {
					//soundRandomizer = rand.nextInt(1)+2;
					if(gameTrack8Set) {
						gameSoundLoops.get(soundRandomizer).stop();
						gameSoundLoops.get(soundRandomizer).setSoundLoopBoolean(false);
						//Random rand = new Random();
						soundRandomizer = 7;
//						gameSoundLoops.get(soundRandomizer).loopSegmentFromStart(3860558, 5248605);
						gameSoundLoops.get(soundRandomizer).loopSegmentFromStart(1930279, 2624303);
						gameSoundLoops.get(soundRandomizer).setSoundLoopBoolean(true);
					}
					this.enemyHitRightBarrier = false;
					this.enemySpeedIncrease = 1.0;
					c.addEntity(new Bowser(0,50, tex, c , this));
					spawnDone4 = true;
				}
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
			//MOVED ALL CODE COMMENTED OUT BELOW UP TO TICK
		//if (i == rand.nextInt())
//			if(ec.isEmpty() && !eb.isEmpty() && spawnDone4 == false && traverseTime2 != System.currentTimeMillis()){	
//				boolean spawnShell = true;
//				Random rand = new Random();
//				int i = rand.nextInt(eb.size());
//				if(eb.get(i).getEntityBDead() == false && rand.nextInt(780) == 1) {
//					for(int j =0; j < eb.size(); j++) {
//						if((eb.get(i).getX() <= eb.get(j).getX() + eb.get(j).getWidth() && eb.get(i).getX() >= eb.get(j).getX() && eb.get(j).getEntityBDead() == false) ||
//						(eb.get(i).getX() + eb.get(i).getWidth() >= eb.get(j).getX() && eb.get(j).getX() >= eb.get(i).getX() && eb.get(j).getEntityBDead() == false) ||
//						(eb.get(i).getX() == eb.get(j).getX() && eb.get(j).getEntityBDead() == false)) {
//							if(eb.get(i).getY() >= eb.get(j).getY())
//								spawnShell = true;
//							else {
//								spawnShell = false;
//								break;
//							}
//						}
//					}
//					if (spawnShell) {
//						for(int k = eb.size() - 1; k >= 0; k--) {
//							if((eb.get(i).getX() <= eb.get(k).getX() + eb.get(k).getWidth() && eb.get(i).getX() > eb.get(k).getX() && eb.get(k).getEntityBDead() == false) ||
//							(eb.get(i).getX() + eb.get(i).getWidth() >= eb.get(k).getX() && eb.get(k).getX() >= eb.get(i).getX() && eb.get(k).getEntityBDead() == false) ||
//							(eb.get(i).getX() == eb.get(k).getX() && eb.get(k).getEntityBDead() == false)) {
//								if(eb.get(i).getY() >= eb.get(k).getY())
//									spawnShell = true;
//								else {
//									spawnShell = false;
//									break;
//								}
//							}
//						}
//					}
//					if (spawnShell)
//						c.addEntity(new GreenShell(eb.get(i).getX(),eb.get(i).getY() - 32,tex, this));
//				}
//				traverseTime2 = System.currentTimeMillis();
//			}
//			if(spawnDone4 == true){												//Spawning Bowser Mechanics
//				//hud.render(g);
//				if((int)HUD.getTimer1() <= 0){
//					//SPAWN BULLET BILLS
//					if(bowserBulletBillSpawningTimer < System.currentTimeMillis() && c.getEntityB().get(0).getEntityBDead() == false) {
//						Random rand = new Random();
//						int i = rand.nextInt(2);//20000
//						if(i == 1 && ec.size() < 6 && !spawnBulletBill){
//							bulletBillSpawnSmokeL.nextFrame();
//							bulletBillSpawnSmokeL.setCount(0);
//							bulletBillSpawnSmokeR.nextFrame();
//							bulletBillSpawnSmokeR.setCount(0);
//							spawnBulletBill = true;
//							//if(enemyHitRightBarrier)
//								//this.bulletBillSpawnSmokeR.runAnimation();
//								//c.addEntity(new BulletBill(eb.getLast().getX()+64,eb.getLast().getY() + 55 -8,tex, this));
//							//else
//								//c.addEntity(new BulletBill(eb.getLast().getX()-4,eb.getLast().getY() + 55 -8,tex, this));
//						}
//						bowserBulletBillSpawningTimer = System.currentTimeMillis() + 2000;
//					}
//					if((bulletBillSpawnSmokeL.getCount() == 2 || bulletBillSpawnSmokeR.getCount() == 2) && !spawn1BulletBillAtATime) {
//						if(bulletBillSpawnSmokeR.getCount() == 2)
//							c.addEntity(new BulletBill(eb.getLast().getX()+64,eb.getLast().getY() + 55 -8,tex, this));
//						else { 
//							if(eb.getLast().getX()-4 < p.getX())
//								c.addEntity(new BulletBill(eb.getLast().getX()-14,eb.getLast().getY() + 55 -8,tex, this));
//							else
//								c.addEntity(new BulletBill(eb.getLast().getX()-4,eb.getLast().getY() + 55 -8,tex, this));
//						}
//						spawn1BulletBillAtATime = true;
//					}
//					else if(bulletBillSpawnSmokeL.getCount() == 10 || bulletBillSpawnSmokeR.getCount() == 10) {
//						bulletBillSpawnSmokeL.nextFrame();
//						bulletBillSpawnSmokeL.setCount(0);
//						bulletBillSpawnSmokeR.nextFrame();
//						bulletBillSpawnSmokeR.setCount(0);
//						spawnBulletBill = false;
//						spawn1BulletBillAtATime = false;
//					}
//					//SPAWN GREEN SHELLS
//					if(numberOfFireBallsShot % 6==0){
//						numberOfFireBallsShot += 1;
//						numberOfFireBallsShotDecoy += 1;
//						c.addEntity(new GreenShell(eb.getLast().getX()+32,eb.getLast().getY() - 32,tex, this));
//					}
//				}
//			}
//			//SPAWN STARS & ITEMS
//			if(hud.getItemObtained() == false && ed.size() < 1) {
//				Random rand = new Random();
//				int j = rand.nextInt(400000);//400000
//				if(j < 2) { //&& ed.size() < 1) { //&& p.getMarioInvincible() == false && hud.getItemObtained() == false){
//					int pp = rand.nextInt(16);
//					if(pp == 0) {
//						if(p.getMarioInvincible() == false) { //&& hud.getItemObtained() == false) {
//							int p = rand.nextInt(2);
//							if(p == 0)
//								c.addEntity(new Mario1Star(-16,this.playerY() - 32,tex, this));//new Mario1Star
//							else
//								c.addEntity(new Mario1Star((Game.WIDTH * 2) + 16,this.playerY() - 32,tex, this));//new Mario1Star
//						}
//					}
//					else {//if(hud.getItemObtained() == false){
//						int p = rand.nextInt(2);
//						if(p == 0)
//							c.addEntity(new ItemBall(-16,this.playerY() - 32,tex, this));
//						else
//							c.addEntity(new ItemBall((Game.WIDTH * 2) + 16,this.playerY() - 32,tex, this));
//					}
//					
//	//				int k = rand.nextInt(2);
//	//				if(k == 0){
//	//					if(j == 0)
//	//						c.addEntity(new ItemBall(-16,this.playerY() - 32,tex, this));//new Mario1Star
//	//					else if(j == 1)
//	//						c.addEntity(new ItemBall(-16,this.playerY() - 32,tex, this));
//	//				}
//	//				else{
//	//					if(j == 0)
//	//						c.addEntity(new ItemBall((Game.WIDTH * 2) + 16,this.playerY() - 32,tex, this));//new Mario1Star
//	//					else if(j == 1)
//	//						c.addEntity(new ItemBall((Game.WIDTH * 2) + 16,this.playerY() - 32,tex, this));
//	//				}
//				}
//			}
			if (animationTimer1 != 0){													//if they shoot a fireball this stops them
				p.setVelX(0);
				p.setVelY(0);
				runningTimerLong = 0;													//RESTART RUNNING
				runningTimerActivatedResponse = false;									//RESTART RUNNING
				if (animationTimer1 == 1){
					if (xLBoolean == true) {												//if they're still holding down the button
						p.setRunningStartUp(-1.2);										//RESTART RUNNING
						p.setVelX(p.getRunningStartUp());								//RESTART RUNNING
						runningTimerActivated = true;									//RESTART RUNNING
						p.setRunningStartL(true);										//RESTART RUNNING
//						p.setVelX(-5);													//KEEP RUNNING
//		    			slowDownForAnalogTimer = System.currentTimeMillis() + 200;		//KEEP RUNNING
					}
					if (xRBoolean == true) {
						p.setRunningStartUp(1.2);										//RESTART RUNNING
						p.setVelX(p.getRunningStartUp());								//RESTART RUNNING
						runningTimerActivated = true;									//RESTART RUNNING
						p.setRunningStartR(true);										//RESTART RUNNING
//						p.setVelX(5);													//KEEP RUNNING
//		    			slowDownForAnalogTimer = System.currentTimeMillis() + 200;		//KEEP RUNNING
					}
				}
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
			if (Health > 0){
				//draw x 1
			}
			else{
				// - lives
				// if lives = 0 then State = STATE.GAMEOVER
				//draw x 0
				State = STATE.TRANSITION_DEATH;
				if(LeaderboardController.getFromSettings("Total Points: ").equals("")) {
					if(hud.getScore() < 999999) {
						LeaderboardController.writeToSettings("Total Points: ", String.valueOf(hud.getScore()));
						if(highScore < 999999)
							LeaderboardController.writeToSettings("High Score: ", String.valueOf(hud.getScore()));
					}
					else {
						LeaderboardController.writeToSettings("Total Points: ", "999999");
						if(highScore < hud.getScore())
								LeaderboardController.writeToSettings("High Score: ", String.valueOf(hud.getScore()));
					}
				}
				else {
					int i = Integer.valueOf(LeaderboardController.getFromSettings("Total Points: "));
					i += hud.getScore();
					if(i < 999999){}
					else
						i = 999999;
					LeaderboardController.writeToSettings("Total Points: ", String.valueOf(i));
					if(highScore < hud.getScore())
						LeaderboardController.writeToSettings("High Score: ", String.valueOf(hud.getScore()));
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
				//if([function checking if settings has any null values, returns true if so] == true){
				//load backup settings to settings}
				//else{
				//store settings to backup}
				if(leaderboard.checkForDataCorruption("settings.properties") == true && leaderboard.checkForDataCorruption("./src/settingsBackup.properties") == true) {
//					System.out.println("DATA CORRUPTION IN SETTINGS & BACKUP");
				}
				else if(leaderboard.checkForDataCorruption("settings.properties") == true) {
//					System.out.println("DATA CORRUPTION IN SETTINGS");
					leaderboard.loadSettingsIntoSettings("./src/settingsBackup.properties","settings.properties");
					leaderboard.loadSettingsIntoSettings("settings.properties","./src/settingsBackup.properties");
				}
				else {
//					System.out.println("NO DATA CORRUPTION");
					leaderboard.loadSettingsIntoSettings("settings.properties","./src/settingsBackup.properties");
				}
				leaderboard.settingsSetup();
				settingsSetup = true;
			}
			if(menuSoundSet == false){
				Random rand = new Random();
				if(firstTimeRunning) {
					menuSoundLoopRandomizer = 2;//rand.nextInt(5);
					firstTimeRunning = false;
					LeaderboardController.writeToSettings("firstTimeRunning", "false");
				}
				else
					menuSoundLoopRandomizer = rand.nextInt(5);
				VolumeSlider.adjustVolume(menuSoundLoops, gameSoundLoops);
				menuSoundLoopRandomizer = VolumeSlider.menuTrackSetup(menuSoundLoopRandomizer);
				if(menuSoundLoopRandomizer == -2)
					menuSoundLoopRandomizer = 5;
				if(menuSoundLoopRandomizer == 2) 
//					menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(864007, 1728014);//Seconds.millisec * 44100
					menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(432004, 864007);//Seconds.millisec * 22050
				else if(menuSoundLoopRandomizer == 3) 
//					menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(464196, 1949705);//Seconds.millisec * 44100
					menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(232098, 974853);//Seconds.millisec * 22050
				else if(menuSoundLoopRandomizer == 4)
//					menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(545561, 2291391);//Seconds.millisec * 44100
					menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(272781, 1145696);//Seconds.millisec * 22050
				else
					this.menuSoundLoops.get(menuSoundLoopRandomizer).loop();
				this.menuSoundLoops.get(menuSoundLoopRandomizer).setSoundLoopBoolean(true);
				menuSoundSet = true;
			}
//			System.out.println("FrameRate: " + menuSoundLoops.get(menuSoundLoopRandomizer).getFrameRate());
//			System.out.println(menuSoundLoops.get(menuSoundLoopRandomizer).getLongFramePosition());
			if(cheatTimer < System.currentTimeMillis() && !cheatString.equals(""))
				cheatString = "";
			if(starExplode) {
				starExplosion.StarExplosionSetup(Game.mx,Game.my);
				starExplosionTimer = System.currentTimeMillis() + 2000;
				starExplode = false;
				//starExplode = false;
			}
			if(System.currentTimeMillis() < starExplosionTimer) {
				starExplosion.Explosion(g);
			}
			if(ufoSpawned) {
				c.render(g);
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
//			j.main();
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
							g.drawImage(leaderboardTitleGlow,Game.WIDTH /2 + 380, 20,null);
							break;
						case -2:
							g.drawImage(settingsTitleSelected,280 + 32 -7, 20 -7,null);
							g.drawImage(settingsTitleGlow,280 + 32, 20,null);
							break;
						case -1:
							g.drawImage(helpTitleSelected,54 -7, 20 -7,null);
							g.drawImage(helpTitleGlow,54, 20,null);
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
			if(trackCurrentlyPlaying) {
				menuSoundLoops.get(this.menuSoundLoopRandomizer).setFramePosition(0);
				menuSoundLoops.get(this.menuSoundLoopRandomizer).setSoundLoopBoolean(false);
				menuSoundSet = false;
				if(gameTrackCurrentlyPlaying != -1) {
					gameSoundLoops.get(gameTrackCurrentlyPlaying).stop();
					gameSoundLoops.get(gameTrackCurrentlyPlaying).setFramePosition(0);
					gameTrackCurrentlyPlaying = -1;
				}
				else if(menuTrackCurrentlyPlaying != -1) {
					menuSoundLoops.get(menuTrackCurrentlyPlaying).stop();
					menuSoundLoops.get(menuTrackCurrentlyPlaying).setFramePosition(0);
					menuTrackCurrentlyPlaying = -1;
				}
				menuMusicStopped = false;
				trackCurrentlyPlaying = false;
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
				for(int i = 0; i <= marioVoices.size()-1; i++) {
					marioVoices.get(i).stop();
					marioVoices.get(i).setFramePosition(0);
					marioVoices.get(i).setSoundLoopBoolean(false);
				}for(int i = 0; i <= luigiVoices.size()-1; i++) {
					luigiVoices.get(i).stop();
					luigiVoices.get(i).setFramePosition(0);
					luigiVoices.get(i).setSoundLoopBoolean(false);
				}
			}
			if(askToSkipSequence) 
				HUD.clickyButton(g, skipTitle, skipTitleGlow, skipTitleClicked, skipHighlighted, skipClicked, backOnSkip, mouseIsOffClickedObjectAndHeldDown, mouseIsClickedDown, Game.WIDTH -73, Game.HEIGHT-32);
			mouseLocator.locateMouse();
			p.render(g);
			c.render(g);
			bb.drawEntrance(g2d);
			if(Game.keysAreInUse && askToSkipSequence) {
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
			if(p.getDanceProgressionCount() >= 15 && spawningEnemiesDanceSync != p.getDanceProgressionCount()+3)
				spawningEnemiesDanceSync = p.getDanceProgressionCount()+3;
			else if(p.getDanceProgressionCount() < 15 && p.getDanceProgressionCount() >= 10 && spawningEnemiesDanceSync != p.getDanceProgressionCount()+2)
				spawningEnemiesDanceSync = p.getDanceProgressionCount()+2;
			else if(p.getDanceProgressionCount() < 10 && p.getDanceProgressionCount() >= 5 && spawningEnemiesDanceSync != p.getDanceProgressionCount()+1)
				spawningEnemiesDanceSync = p.getDanceProgressionCount()+1;
			else if(p.getDanceProgressionCount() < 5 && spawningEnemiesDanceSync != p.getDanceProgressionCount())
				spawningEnemiesDanceSync = p.getDanceProgressionCount();
			/*
			if(spawningEnemiesDanceSync != p.getDanceProgressionCount())
				spawningEnemiesDanceSync = p.getDanceProgressionCount();
			if(p.getDanceProgressionCount() >= 5) {
				if(spawningEnemiesDanceSync != p.getDanceProgressionCount()+1)
					spawningEnemiesDanceSync = p.getDanceProgressionCount()+1;
				if(p.getDanceProgressionCount() >= 10) {
					if(spawningEnemiesDanceSync != p.getDanceProgressionCount()+2)
						spawningEnemiesDanceSync = p.getDanceProgressionCount()+2;
					if(p.getDanceProgressionCount() >= 15 && spawningEnemiesDanceSync != p.getDanceProgressionCount()+3) 
						spawningEnemiesDanceSync = p.getDanceProgressionCount()+3;
				}
			}*/
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
					if(p.getDanceProgressionCount() < 26 && this.marioDanceSoundLoops.get(p.getDanceProgressionCount()-1).endsSoon(2) == true)//progression without cuts in sound
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
						if(currentlySelectedCharacterSkin == 4)
							Game.marioVoices.get(4).play();
						else if(currentlySelectedCharacterSkin == 5)
							Game.marioVoices.get(6).play();
						else
							Game.marioVoices.get(marioVoiceRandomizer).play();
					}
					marioLetsGoPause = false;
				}
			}
		}else if(State == STATE.TRANSITION_ITEM){
			if(this.gameSoundLoops.get(this.soundRandomizer).clipIsActive()) { //|| marioTurningWithItem.getCount() == 0){
				this.gameSoundLoops.get(this.soundRandomizer).stop();
				this.gameSoundLoops.get(this.soundRandomizer).setSoundLoopBoolean(false);
				this.itemSwooshSoundLoop.play();
			}
			else if(this.marioStarSoundLoop.clipIsActive()) {
				this.marioStarSoundLoop.stop();
				this.marioStarSoundLoop.setSoundLoopBoolean(false);
				this.itemSwooshSoundLoop.play();
			}
			if(!itemTransitionSetup) {
				askToSkipSequence = false;
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
					case 4:
						marioTurningWithItem = new Animation(6, tex.marioItemAnimationBeginning5[0],tex.marioItemAnimationBeginning5[0],
							tex.marioItemAnimationBeginning5[1],tex.marioItemAnimationBeginning5[11],tex.marioItemAnimationBeginning5[12],
							tex.marioItemAnimationBeginning5[13],tex.marioItemAnimationBeginning5[14],tex.marioItemAnimationBeginning5[15],
							tex.marioItemAnimationBeginning5[16],tex.marioItemAnimationBeginning5[17]);
						break;
					case 5:
						marioTurningWithItem = new Animation(6, tex.marioItemAnimationBeginning6[0],tex.marioItemAnimationBeginning6[0],
							tex.marioItemAnimationBeginning6[1],tex.marioItemAnimationBeginning6[11],tex.marioItemAnimationBeginning6[12],
							tex.marioItemAnimationBeginning6[13],tex.marioItemAnimationBeginning6[14],tex.marioItemAnimationBeginning6[15],
							tex.marioItemAnimationBeginning6[16],tex.marioItemAnimationBeginning6[17]);
						break;
					default:
						break;
				}
				marioTurningWithItem.nextFrame();
					switch(hud.getItemName()){
						case "chainChompItem":
							currentItem = new Animation(6,tex.bigChainChompItemBall[0],
									tex.bigChainChompItemBall[1],tex.bigChainChompItemBall[2],tex.bigChainChompItemBall[3],
									tex.bigChainChompItemBall[4],tex.bigChainChompItemBall[5],tex.bigChainChompItemBall[6],
									tex.bigChainChompItemBall[7],tex.bigChainChompItemBall[8]);
							currentItem.nextFrame();
							currentItemImg = tex.bigChainChompItemBall[0];
							//useChainChompAnimation
							break;
						case "bulletBillItem":
							currentItem = new Animation(6,tex.bigBulletBillItemBall,
									tex.bigBulletBillItemBall,tex.bigBulletBillItemBall,tex.bigBulletBillItemBall,
									tex.bigBulletBillItemBall,tex.bigBulletBillItemBall,tex.bigBulletBillItemBall,
									tex.bigBulletBillItemBall,tex.bigBulletBillItemBall);
							currentItem.nextFrame();
							currentItemImg = tex.bigBulletBillItemBall;
							break;
						case "bombOmbItem":
							currentItem = new Animation(6,tex.bigBombOmbItemBall,
									tex.bigBombOmbItemBall,tex.bigBombOmbItemBall,tex.bigBombOmbItemBall,
									tex.bigBombOmbItemBall,tex.bigBombOmbItemBall,tex.bigBombOmbItemBall,
									tex.bigBombOmbItemBall,tex.bigBombOmbItemBall);
							currentItem.nextFrame();
							currentItemImg = tex.bigBombOmbItemBall;
							break;
						case "cheepCheepsItem":
							currentItem = new Animation(6,tex.bigCheepCheepsItemBall,
								tex.bigCheepCheepsItemBall,tex.bigCheepCheepsItemBall,tex.bigCheepCheepsItemBall,
								tex.bigCheepCheepsItemBall,tex.bigCheepCheepsItemBall,tex.bigCheepCheepsItemBall,
								tex.bigCheepCheepsItemBall,tex.bigCheepCheepsItemBall);
							currentItem.nextFrame();
							currentItemImg = tex.bigCheepCheepsItemBall;
							break;
						case "ampItem":
							currentItem = new Animation(6,tex.bigAmpItemBall,
									tex.bigAmpItemBall,tex.bigAmpItemBall,tex.bigAmpItemBall,
									tex.bigAmpItemBall,tex.bigAmpItemBall,tex.bigAmpItemBall,
									tex.bigAmpItemBall,tex.bigAmpItemBall);
							currentItem.nextFrame();
							currentItemImg = tex.bigAmpItemBall;
							break;
						case "wigglerItem":
							currentItem = new Animation(6,tex.bigWigglerItemBall,
									tex.bigWigglerItemBall,tex.bigWigglerItemBall,tex.bigWigglerItemBall,
									tex.bigWigglerItemBall,tex.bigWigglerItemBall,tex.bigWigglerItemBall,
									tex.bigWigglerItemBall,tex.bigWigglerItemBall);
							currentItem.nextFrame();
							currentItemImg = tex.bigWigglerItemBall;
							break;
						case "lakituItem":
							currentItem = new Animation(6,tex.bigLakituItemBall,
									tex.bigLakituItemBall,tex.bigLakituItemBall,tex.bigLakituItemBall,
									tex.bigLakituItemBall,tex.bigLakituItemBall,tex.bigLakituItemBall,
									tex.bigLakituItemBall,tex.bigLakituItemBall);
							currentItem.nextFrame();
							currentItemImg = tex.bigLakituItemBall;
							break;
						default:
							currentItem = new Animation(6,tex.bigChainChompItemBall[0],
									tex.bigChainChompItemBall[1],tex.bigChainChompItemBall[2],tex.bigChainChompItemBall[3],
									tex.bigChainChompItemBall[4],tex.bigChainChompItemBall[5],tex.bigChainChompItemBall[6],
									tex.bigChainChompItemBall[7],tex.bigChainChompItemBall[8]);
							currentItem.nextFrame();
							currentItemImg = tex.bigChainChompItemBall[0];
							hud.setItemName("chainChompItem");
							break;
					}
					//traverseTime = System.currentTimeMillis();
					itemTransitionSetup = true;
			}
				
			if(backgroundTraverse < itemBackground.size()-1 && traverseTime < System.currentTimeMillis()){
				//System.out.println(backgroundTraverse);
				backgroundTraverse++;
				traverseTime = System.currentTimeMillis()+80;
			}
			else if(itemWaitTimer == 0 && backgroundTraverse >= itemBackground.size()-1)
				itemWaitTimer = System.currentTimeMillis() + 400;
			if(backgroundTraverse < itemBackground.size())
				g.drawImage(itemBackground.get(backgroundTraverse), 0, 0, null);
			if(skipSequence) {
				spawnItem = true;
				enemyHitPauseTimer = System.currentTimeMillis() + 800;
				backgroundTraverse = 0;
				itemAnimationTimer = 0;
				itemAnimationTimer2 = 0;
				itemAnimationTimer3 = 0;
				itemWaitTimer = 0;
				itemFlyingTimer1 = 0;
				itemFlyingAwayX = 0;
				itemFlyingAwayY = 0;
				traverseTime = 0;
				currentItem.setCount(0);
				marioVoices.get(0).setSoundLoopBoolean(false);
				luigiVoices.get(0).setSoundLoopBoolean(false);
				marioVoices.get(5).setSoundLoopBoolean(false);
				marioVoices.get(7).setSoundLoopBoolean(false);
				marioTurningWithItem.setCount(0);
				State = STATE.GAME;
				itemTransitionSetup = false;
				skipSequence = false;
				askToSkipSequence = false;
			}
			if(askToSkipSequence) 
				HUD.clickyButton(g, skipTitle, skipTitleGlow, skipTitleClicked, skipHighlighted, skipClicked, backOnSkip, mouseIsOffClickedObjectAndHeldDown, mouseIsClickedDown, Game.WIDTH -73, Game.HEIGHT-32);
			mouseLocator.locateMouse();
			if(Game.keysAreInUse && askToSkipSequence) {
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
			
			if(itemWaitTimer != 0 && marioTurningWithItem.getCount() < 10 && itemAnimationTimer < System.currentTimeMillis()){
				marioTurningWithItem.runAnimation();
				itemAnimationTimer = System.currentTimeMillis()+16;
			}
			if(itemWaitTimer != 0 && marioVoices.get(0).getSoundLoopBoolean() == false && (currentlySelectedCharacterSkin != 3 &&
					currentlySelectedCharacterSkin != 4 && currentlySelectedCharacterSkin != 5)){
				marioVoices.get(0).play();
				marioVoices.get(0).setSoundLoopBoolean(true);
			}
			else if(itemWaitTimer != 0 && luigiVoices.get(0).getSoundLoopBoolean() == false && currentlySelectedCharacterSkin == 3) {
				luigiVoices.get(0).play();
				luigiVoices.get(0).setSoundLoopBoolean(true);
			}
			else if(itemWaitTimer != 0 && marioVoices.get(5).getSoundLoopBoolean() == false && currentlySelectedCharacterSkin == 4) {
				marioVoices.get(5).play();
				marioVoices.get(5).setSoundLoopBoolean(true);
			}
			else if(itemWaitTimer != 0 && marioVoices.get(7).getSoundLoopBoolean() == false && currentlySelectedCharacterSkin == 5) {
				marioVoices.get(7).play();
				marioVoices.get(7).setSoundLoopBoolean(true);
			}
			if(itemWaitTimer != 0 && System.currentTimeMillis() < itemWaitTimer - 100 && traverseTime != System.currentTimeMillis()){
				if(marioTurningWithItem.getCount()<4 && itemAnimationTimer2 < System.currentTimeMillis()) {
					itemFlyingAwayY -= .016;
					itemAnimationTimer2 = System.currentTimeMillis()+4;
				}
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
			else if(itemWaitTimer != 0 && itemWaitTimer < System.currentTimeMillis() && itemFlyingTimer1 != 0 && 
					currentItemImg != null && currentItemImg.getWidth() < 200){
				if(traverseTime != System.currentTimeMillis()){
					if(currentItem.getCount() < 9 && itemAnimationTimer3 < System.currentTimeMillis()) {//MIGHT NEED TO ADD TRAVERSETIME HERE
						currentItemImg = resize(currentItemImg,currentItemImg.getWidth()+20,currentItemImg.getHeight()+20);
						itemAnimationTimer3 = System.currentTimeMillis() + 35;
					}
						//currentItem.runAnimation();
					itemFlyingAwayX -= 0.8;
					itemFlyingAwayY -= 1;
					traverseTime = System.currentTimeMillis();
				}
				marioTurningWithItem.drawAnimation(g, Game.WIDTH, (Game.HEIGHT * Game.SCALE+120) - (backgroundTraverse * 22), 0);
				g.drawImage(currentItemImg ,(int)(Game.WIDTH+42 + itemFlyingAwayX), (int)((Game.HEIGHT * Game.SCALE+120) - (backgroundTraverse * 22)-19 + itemFlyingAwayY),null);
				//currentItem.drawAnimation(g, Game.WIDTH+42 + itemFlyingAwayX, (Game.HEIGHT * Game.SCALE+120) - (backgroundTraverse * 22)-19 + itemFlyingAwayY,0);
			}
			else if(currentItemImg != null && currentItemImg.getWidth() >= 200){
				spawnItem = true;
				enemyHitPauseTimer = System.currentTimeMillis() + 800;
				backgroundTraverse = 0;
				itemAnimationTimer = 0;
				itemAnimationTimer2 = 0;
				itemAnimationTimer3 = 0;
				itemWaitTimer = 0;
				itemFlyingTimer1 = 0;
				itemFlyingAwayX = 0;
				itemFlyingAwayY = 0;
				traverseTime = 0;
				currentItem.setCount(0);
				marioVoices.get(0).setSoundLoopBoolean(false);
				luigiVoices.get(0).setSoundLoopBoolean(false);
				marioVoices.get(5).setSoundLoopBoolean(false);
				marioVoices.get(7).setSoundLoopBoolean(false);
				marioTurningWithItem.setCount(0);
				itemTransitionSetup = false;
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
			if(this.marioStarSoundLoop.clipIsActive()) 
				marioStarSoundLoop.stop();
			if (paused == false)
				paused = true;
			if(marioDeathSoundLoop.getSoundLoopBoolean() == false){
				marioDeathSoundLoop.play();
				marioDeathSoundLoop.setSoundLoopBoolean(true);
			}
			if(marioDeathSoundLoop.soundPlaying() == false){
				marioDeathSoundLoop.setSoundLoopBoolean(false);
				if(highScore <= hud.getScore())
					State = State.SET_SCORE;
				else
					State = STATE.GAMEOVER;
				if(Game.gameControllerInUse) {
					joystickTimer = 0;
					Game.keysAreInUse = true;
				}
				Game.selectorButtonPosition = 0;
			}
			
			bb.draw(g2d);													//BLOCKS
			p.render(g);
			c.render(g);
		}else if(State == STATE.TRANSITION_WIN) {
			if(this.gameSoundLoops.get(this.soundRandomizer).getSoundLoopBoolean() == true){
				this.gameSoundLoops.get(this.soundRandomizer).stop();
				this.gameSoundLoops.get(this.soundRandomizer).setSoundLoopBoolean(false);
				soundSet = false;
				askToSkipSequence = false;
			}
			if(this.gameSoundLoops.get(this.soundRandomizer).clipIsActive()) {
				gameSoundLoops.get(this.soundRandomizer).stop();
				gameSoundLoops.get(this.soundRandomizer).setFramePosition(0);
				gameSoundLoops.get(this.soundRandomizer).setSoundLoopBoolean(false);
			}
			if(this.marioStarSoundLoop.clipIsActive()) {
				this.marioStarSoundLoop.stop();
				this.marioStarSoundLoop.setFramePosition(0);
				this.marioStarSoundLoop.setSoundLoopBoolean(false);
			}
			if(currentlySelectedCharacterSkin != 4 && currentlySelectedCharacterSkin != 5 && 
					this.gameOverIrisSoundLoop.getSoundLoopBoolean() == false) {
				this.gameOverIrisSoundLoop.play();
				this.gameOverIrisSoundLoop.setSoundLoopBoolean(true);
				askToSkipSequence = false;
			}
			else if(currentlySelectedCharacterSkin == 4 && marioVoices.get(4).getSoundLoopBoolean() == false) {
				marioVoices.get(4).play();
				marioVoices.get(4).setSoundLoopBoolean(true);
				askToSkipSequence = false;
			}
			else if(currentlySelectedCharacterSkin == 5 && marioVoices.get(8).getSoundLoopBoolean() == false) {
				marioVoices.get(8).play();
				marioVoices.get(8).setSoundLoopBoolean(true);
				askToSkipSequence = false;
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
			if(youWon == false)
				youWon = true;
			if(julian == 0) {
				if(!bb.wall.isEmpty())
					julian = System.currentTimeMillis() + 5000;
				else
					julian = 0;
				julianii = bb.wall.size()-1;
			}
			if(System.currentTimeMillis() < julian && (!bb.wall.isEmpty() || hud.getScore() > 100000)) {
				if(bb.wall.isEmpty() && hud.getScore() > 100000) {
					if(bb.wall.isEmpty() && juliann == 0)
						juliann = System.currentTimeMillis() + 500;
					if(bb.wall.isEmpty() && !smbCoinPointsSoundLoop.clipIsActive() && !smbCoinPoints2SoundLoop.clipIsActive()
							&& julian < System.currentTimeMillis() + 2500 && juliann < System.currentTimeMillis()) {
						if(State != STATE.GAMEOVER)
							pointUpdate();
						if(highScore <= hud.getScore())
							State = State.SET_SCORE;
						else
							State = STATE.GAMEOVER;
					}else if(bb.wall.isEmpty() && !smbCoinPointsSoundLoop.clipIsActive() && !smbCoinPoints2SoundLoop.clipIsActive()
							&& julian < System.currentTimeMillis() + 4500 && fireworksShot == 0) {
						Random rand = new Random();
						if(smbFireworkSoundLoop.clipIsActive())
							smbFireworkSoundLoop.stop();
						smbFireworkSoundLoop.play();
						Game.starExplode = true;
						Game.mx = rand.nextInt(Game.WIDTH * Game.SCALE);
						Game.my = rand.nextInt((Game.HEIGHT * Game.SCALE)/2);
						fireworksShot = 1;
						//firework
					}else if(bb.wall.isEmpty() && !smbCoinPointsSoundLoop.clipIsActive() && !smbCoinPoints2SoundLoop.clipIsActive()
							&& julian < System.currentTimeMillis() + 3800 && fireworksShot == 1) {
						Random rand = new Random();
						if(smbFireworkSoundLoop.clipIsActive())
							smbFireworkSoundLoop.stop();
						smbFireworkSoundLoop.play();
						Game.starExplode = true;
						Game.mx = rand.nextInt(Game.WIDTH * Game.SCALE);
						Game.my = rand.nextInt((Game.HEIGHT * Game.SCALE)/2);
						fireworksShot = 2;
						//firework
					}else if(bb.wall.isEmpty() && !smbCoinPointsSoundLoop.clipIsActive() && !smbCoinPoints2SoundLoop.clipIsActive()
							&& julian < System.currentTimeMillis() + 3100 && fireworksShot == 2) {
						Random rand = new Random();
						if(smbFireworkSoundLoop.clipIsActive())
							smbFireworkSoundLoop.stop();
						smbFireworkSoundLoop.play();
						Game.starExplode = true;
						Game.mx = rand.nextInt(Game.WIDTH * Game.SCALE);
						Game.my = rand.nextInt((Game.HEIGHT * Game.SCALE)/2);
						fireworksShot = 3;
						//firework
					}
				}
				else if(bb.wall.isEmpty()){
					if(bb.wall.isEmpty() && !smbCoinPointsSoundLoop.clipIsActive() && !smbCoinPoints2SoundLoop.clipIsActive()
							&& julian < System.currentTimeMillis() + 4000 ) {
						if(bb.wall.isEmpty() && juliann == 0)
							juliann = System.currentTimeMillis() + 500;
						if(juliann < System.currentTimeMillis()) {
							if(State != STATE.GAMEOVER)
								pointUpdate();
							if(highScore <= hud.getScore())
								State = State.SET_SCORE;
							else
								State = STATE.GAMEOVER;
						}
					}
				}
//				for(int i = bb.wall.size()-1; i >= 0; i--) {
//					bb.wall.remove(i);
//				}
				if(julian < System.currentTimeMillis() + 4999) {
					if(!bb.wall.isEmpty()) {
						bb.wall.remove(julianii);
						julianii--;
						hud.setScore(20);
//						smbCoinPointsSoundLoop.play();
//						if(gameOverIrisSoundLoop.clipIsActive() || marioVoices.get(4).clipIsActive() || 
//								marioVoices.get(8).clipIsActive())
							
						if(smbCoinPoints2SoundLoop.clipIsActive())
							smbCoinPointsSoundLoop.play();
						else if(!smbCoinPointsSoundLoop.clipIsActive())
							smbCoinPoints2SoundLoop.play();
//							for(int j = pointsSoundLoop.size(); j > 0; j--){
//								if(pointsSoundLoop.get(j-1) != null && !pointsSoundLoop.get(j-1).clipIsActive()){
//									pointsSoundLoop.remove(j-1);
//									//j--;
//								}
//							}	
//							for(int k = 0; k < pointsSoundLoop.size() || k == 0; k++){
//								if(pointsSoundLoop.isEmpty()) {
//									VolumeSlider.adjustSFX(smbCoinPointsSoundLoop);
//									pointsSoundLoop.add(smbCoinPointsSoundLoop);
//								}
//								else if (pointsSoundLoop.get(k) == pointsSoundLoop.getLast()){
//									VolumeSlider.adjustSFX(smbCoinPointsSoundLoop);
//									if(smbCoinPointsSoundLoop.getVolume() - (1.5f*k) >= smbCoinPointsSoundLoop.minimumVolume())
//										smbCoinPointsSoundLoop.reduceSound(1.5f*k);
//									pointsSoundLoop.add(smbCoinPointsSoundLoop);
//									k++;
//								}
//							}
//							if(pointsSoundLoop.getLast().clipIsActive())
//								smbCoinPointsSoundLoop.play();
//							else
//								pointsSoundLoop.getLast().play();
					}
					if(julian < System.currentTimeMillis() + 5000 && !bb.wall.isEmpty())
						julian = System.currentTimeMillis() + 5000;
				}
				bb.draw((Graphics2D) g);
				p.render(g);
				hud.render(g);
			}
			else {
				if(skipSequence) {
					gameOverIrisSoundLoop.setSoundLoopBoolean(false);
					marioVoices.get(4).setSoundLoopBoolean(false);
					marioVoices.get(8).setSoundLoopBoolean(false);
					if(State != STATE.GAMEOVER)
						pointUpdate();
					if(highScore <= hud.getScore())
						State = State.SET_SCORE;
					else
						State = STATE.GAMEOVER;
					if(Game.gameControllerInUse) {
						joystickTimer = 0;
						Game.keysAreInUse = true;
					}
					Game.selectorButtonPosition = 0;
					skipSequence = false;
					askToSkipSequence = false;
				}
				if(askToSkipSequence) 
					HUD.clickyButton(g, skipTitle, skipTitleGlow, skipTitleClicked, skipHighlighted, skipClicked, backOnSkip, mouseIsOffClickedObjectAndHeldDown, mouseIsClickedDown, Game.WIDTH -73, Game.HEIGHT-32);
				mouseLocator.locateMouse();
				p.render(g);
				hud.render(g);
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
				if(p.gameOver == true) {
					if(Game.gameControllerInUse) {
						joystickTimer = 0;
						Game.keysAreInUse = true;
					}
					Game.selectorButtonPosition = 0;
					if(bb.wall.isEmpty() && juliann == 0)
						juliann = System.currentTimeMillis() + 500;
					if(juliann < System.currentTimeMillis()) {
						if(State != STATE.GAMEOVER)
							pointUpdate();
						if(highScore <= hud.getScore())
							State = State.SET_SCORE;
						else
							State = STATE.GAMEOVER;
						gameOverIrisSoundLoop.setSoundLoopBoolean(false);
						marioVoices.get(4).setSoundLoopBoolean(false);
						marioVoices.get(8).setSoundLoopBoolean(false);
					}
					//p.setGameOver(false);
				}
			}
			hud.stringToScore(g, String.valueOf(hud.getScore()));
			if(State == STATE.GAMEOVER && firstTimeBeating == false) {
				firstTimeBeating = true;
				if(!backToGameOver)
					backToGameOver = true;
				State = STATE.CREDITS;
				Game.skipSequence = false;
				Game.askToSkipSequence = false;
				Game.keysAreInUse = false;
				LeaderboardController.writeToSettings("firstTimeBeating", "true");
			}
			//hud.render(g);
		}else if(State == STATE.CREDITS) {
			if(this.gameSoundLoops.get(this.soundRandomizer).getSoundLoopBoolean() == true){
				gameOverIrisSoundLoop.setSoundLoopBoolean(false);
				marioVoices.get(4).setSoundLoopBoolean(false);
				marioVoices.get(8).setSoundLoopBoolean(false);
				this.gameSoundLoops.get(this.soundRandomizer).stop();
				this.gameSoundLoops.get(this.soundRandomizer).setSoundLoopBoolean(false);
				soundSet = false;
			}
			if(this.gameSoundLoops.get(this.soundRandomizer).clipIsActive()) {
				gameSoundLoops.get(this.soundRandomizer).stop();
				gameSoundLoops.get(this.soundRandomizer).setFramePosition(0);
				gameSoundLoops.get(this.soundRandomizer).setSoundLoopBoolean(false);
			}
			if(this.marioStarSoundLoop.clipIsActive()) {
				this.marioStarSoundLoop.stop();
				this.marioStarSoundLoop.setFramePosition(0);
				this.marioStarSoundLoop.setSoundLoopBoolean(false);
			}
			creditsController.render(g);
			if(askToSkipSequence) {
				mouseLocator.locateMouse();
				HUD.clickyButton(g, skipTitle, skipTitleGlow, skipTitleClicked, skipHighlighted, skipClicked, backOnSkip, mouseIsOffClickedObjectAndHeldDown, mouseIsClickedDown, Game.WIDTH -73, Game.HEIGHT-32);
			}
			if(Game.keysAreInUse) {
				if(gameControllerInUse && !askToSkipSequence)
					askToSkipSequence = true;
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
			if(skipSequence) {
				sceneSkipCount = 0;
				askToSkipSequence = false;
				sceneAcknowledgement = false;
				skipSequence = false;
				creditsController.reset();
				if(!backToGameOver) {
					Game.selectorButtonPosition = 0;
					Game.resetMenuSound();
					State = STATE.MENU;
				}
				else {
					if(highScore <= hud.getScore())
						State = State.SET_SCORE;
					else
						State = STATE.GAMEOVER;
				}
			}
			//c.render(g);
		}else if (State == STATE.GAMEOVER){												//GameOver
			//wait a lil bit
			//bs.show();
			if(this.gameSoundLoops.get(this.soundRandomizer).getSoundLoopBoolean() == true){
				gameOverIrisSoundLoop.setSoundLoopBoolean(false);
				marioVoices.get(4).setSoundLoopBoolean(false);
				marioVoices.get(8).setSoundLoopBoolean(false);
				this.gameSoundLoops.get(this.soundRandomizer).stop();
				this.gameSoundLoops.get(this.soundRandomizer).setSoundLoopBoolean(false);
				soundSet = false;
			}
			if(this.marioStarSoundLoop.clipIsActive()) {
				this.marioStarSoundLoop.stop();
				this.marioStarSoundLoop.setFramePosition(0);
				this.marioStarSoundLoop.setSoundLoopBoolean(false);
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
					if(currentlySelectedCharacterSkin == 4)
						this.gameOverWinningMikeTysonSoundLoop.play();
					else if(currentlySelectedCharacterSkin == 5)
						this.gameOverWinningContraSoundLoop.play();
					else
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
//				hud.setScore(1000);
//				totalPointsImage = HUD.stringToMario3FontImage(String.valueOf(hud.getScore()));
//				totalPointsImage = Game.resize(HUD.stringToMario3FontImage(String.valueOf(hud.getScore())), totalPointsImage.getWidth()/2, totalPointsImage.getHeight()/2);
			}
			numberOfFireBallsShot = numberOfFireBallsShot - numberOfFireBallsShotDecoy;
			mouseLocator.locateMouse();
			g.drawImage(gameOverTitle, 170, 100, null);									//Buttons
			//System.out.println(hud.getScore());
			if(scoreImage == null) {
				String s = "";
				s = String.format("%d", hud.getScore());
				scoreImage = HUD.stringToMario3FontImage(s);
				scoreImage = Game.resize(scoreImage, scoreImage.getWidth()/2, scoreImage.getHeight()/2);
			}
			if(hud.getScore() != 0) {
				g.drawImage(scoreTitle, (Game.WIDTH-scoreImage.getWidth()/2)-42,30,null);
				g.drawImage(scoreImage, ((Game.WIDTH-scoreImage.getWidth()/2)+scoreTitle.getWidth())-42,30,null);//scoreImage
			}
			else {
				g.drawImage(scoreTitle, Game.WIDTH-scoreTitle.getWidth()/2,30,null);
				g.drawImage(zeroImage, Game.WIDTH+scoreTitle.getWidth()/2,30,null);
			}
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
//			if(Game.keysAreInUse && connectingToServer)
//				Game.keysAreInUse = false;
			mouseLocator.locateMouse();
			g.drawImage(setScoreTitleBigger, Game.WIDTH / 2 + 35 , 20, null);
			g.drawImage(sendToServerTitle,  Game.WIDTH - (sendToServerTitle.getWidth()/2) , 370, null);
			if(scoreImage == null) {
				String s = "";
				s = String.format("%d", hud.getScore());
				scoreImage = HUD.stringToMario3FontImage(s);
				scoreImage = Game.resize(scoreImage, scoreImage.getWidth()/2, scoreImage.getHeight()/2);
			}
			if(hud.getScore() != 0) {
				g.drawImage(scoreTitle, (Game.WIDTH-((scoreTitle.getWidth()+scoreImage.getWidth())/2)),130,null);
				g.drawImage(scoreImage, ((Game.WIDTH-((scoreTitle.getWidth()+scoreImage.getWidth())/2))+scoreTitle.getWidth()),130,null);//scoreImage
			}
			else {
				g.drawImage(scoreTitle, Game.WIDTH-scoreTitle.getWidth()/2,130,null);
				g.drawImage(zeroImage, Game.WIDTH+scoreTitle.getWidth()/2,130,null);
			}
			HUD.clickyButton(g, backButtonTitle, backButtonTitleGlow, backButtonTitleClicked, Game.backHighlighted, Game.backClicked, Game.backOnBack, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 40, 20);
			HUD.clickyButton(g, submitTitle, submitTitleGlow, submitTitleClicked, Game.submitHighlighted, Game.submitClicked, Game.backOnSubmit, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH-51, 300);
			if(!Game.sendToServer) 
				HUD.clickyButton(g, noCheckMarkTitle, noCheckMarkTitleGlow, noCheckMarkTitleClicked, Game.checkMarkHighlighted, Game.checkMarkClicked, Game.backOnCheckMark, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - (checkMarkTitle.getWidth()/2), 410);
			else 
				HUD.clickyButton(g, checkMarkTitle, checkMarkTitleGlow, checkMarkTitleClicked, Game.checkMarkHighlighted, Game.checkMarkClicked, Game.backOnCheckMark, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - (checkMarkTitle.getWidth()/2), 410);
			
			/*
			if(!this.backHighlighted)
				g.drawImage(backButtonTitle, 40, 20, null);
			else
				g.drawImage(backButtonTitleGlow, 40, 20, null);
			*/
			if(Game.keysAreInUse) {
				if(Game.backHighlighted || Game.backClicked || Game.submitHighlighted || Game.submitClicked || /*Game.checkMarkHighlighted ||*/ 
						Game.checkMarkClicked) {
					Game.backHighlighted = false;
					Game.backClicked = false;
					Game.submitHighlighted = false;
					Game.submitClicked = false;
					Game.checkMarkHighlighted = false;
					Game.checkMarkClicked = false;
				}
				if(Game.enterButtonPushedDown && !Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -1:
						g.drawImage(backTitleSelectedClicked,40 -7, 20 -7,null);
						g.drawImage(backButtonTitleClicked,40, 20,null);
						break;
					case 1:
						g.drawImage(submitTitleSelectedClicked,Game.WIDTH-51-7, 300 -7,null);
						g.drawImage(submitTitleClicked, Game.WIDTH-51, 300,null);
						break;
					case 2:
						if(!Game.sendToServer) 
							g.drawImage(noCheckMarkTitleClicked,Game.WIDTH - (checkMarkTitle.getWidth()/2), 410,null);
						else 
							g.drawImage(checkMarkTitleClicked,Game.WIDTH - (checkMarkTitle.getWidth()/2), 410,null);
						break;
					}
				}
				else if(Game.enterButtonPushedDown && Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -1:
						g.drawImage(backTitleSelectedNormal,40 -7, 20 -7,null);
						g.drawImage(backButtonTitle,40, 20,null);
						break;
					case 1:
						g.drawImage(submitTitleSelectedNormal,Game.WIDTH-51-7, 300 -7,null);
						g.drawImage(submitTitle, Game.WIDTH-51, 300,null);
						break;
					case 2:
						g.drawImage(dotSelectorPositionWhite,Game.WIDTH - (checkMarkTitle.getWidth()/2)+14,404,null);
						if(!Game.sendToServer) 
							g.drawImage(noCheckMarkTitle,Game.WIDTH - (checkMarkTitle.getWidth()/2), 410,null);
						else 
							g.drawImage(checkMarkTitle,Game.WIDTH - (checkMarkTitle.getWidth()/2), 410,null);
						break;
					}
				}
				else {
					switch(Game.selectorButtonPosition) {
					case -1:
						g.drawImage(backTitleSelected,40 -7, 20 -7,null);
						g.drawImage(backButtonTitleGlow,40, 20,null);
						break;
					case 1:
						g.drawImage(submitTitleSelected,Game.WIDTH-51-7, 300 -7,null);
						g.drawImage(submitTitleGlow, Game.WIDTH-51, 300,null);
						break;
					case 2:
						if(!Game.sendToServer) 
							g.drawImage(noCheckMarkTitleGlow,Game.WIDTH - (checkMarkTitle.getWidth()/2), 410,null);
						else 
							g.drawImage(checkMarkTitleGlow,Game.WIDTH - (checkMarkTitle.getWidth()/2), 410,null);
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
			if(!Game.keysAreInUse) {
				if(200 <= postLetterXPositionBeginning && !gameControllerInUse)
					g2d2.drawImage(textIndicator,200+postLetterXPosition,200,null);
				else if(!gameControllerInUse)
					g2d2.drawImage(textIndicator,postLetterXPositionBeginning+postLetterXPosition,200,null);
				if(gameControllerInUse) {
					//draw translucent letter
					if(gamepadLetterImage == null)
						gamepadLetterImage = HUD.mario3FontCharBufferedImage(gamepadKeyboardLetterPosition);
					if(200 <= postLetterXPositionBeginning)
						g2d2.drawImage(gamepadLetterImage,200+postLetterXPosition,200,null);
					else 
						g2d2.drawImage(gamepadLetterImage,postLetterXPositionBeginning+postLetterXPosition,200,null);
				}
			}
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
					if(!playerName.isEmpty() && !playerName.isEmpty() && !playerName.get(0).equals("")&& !playerName.get(0).equals(" ") && this.scoreEntered == false) {
						if(!leaderboard.globalList) {
							leaderboard.getNames().clear();
							leaderboard.getScores().clear();
						}
						leaderboard.setNameDecoy("");
						leaderboard.setScoreDecoy("");
						leaderboard.setStringDecoy("");
						leaderboard.writeScore();
						scoreEntered = true;
						if(sendToServer && 0 < HUD.score) {
//							System.out.println("WANKER");
							String s = "";
//							if(!saveName.equals("")) {
//								s="DELETE:DELETE:\nDELETE:DELETE:"+saveName+"\n";
//								//saveName = "";
//							}
//							
//							s=s+HUD.nameEntered+": "+Long.toString((hud.getScore()));
							if(!saveName.equals("")) {
								s=this.KIVersion+"\nDELETE:DELETE:"+saveName+"\n";
								//saveName = "";
							}
							s=s+"UPLOAD:UPLOAD:\n";
							s=s+HUD.nameEntered+": "+Long.toString((hud.getScore()));
							//saveName = s;
							clientToServer(s);
						}
						postLetter = '=';
						if(leaderboard.getInitalized() == true)
							leaderboard.setInitalized(false);
						if(!connectingToServer) {
							Game.selectorButtonPosition = 0;
							State = STATE.GAMEOVER;
							if(gameControllerInUse)
								Game.keysAreInUse = true;
							if(!serverErrorMessage) {
								if(Game.smb31PupSoundLoop.clipIsActive())
									Game.smb31PupSoundLoop.stop();
								Game.smb31PupSoundLoop.play();
							}
						}
						break;
					}
					else if(this.scoreEntered == true && !playerName.isEmpty() && !playerName.get(0).equals("")&& !playerName.get(0).equals(" ")) {
						leaderboard.setNameDecoy("");
						leaderboard.setScoreDecoy("");
						leaderboard.setStringDecoy("");
						Game.selectorButtonPosition = 0;
						leaderboard.writeScore();
						postLetter = '=';
						if(sendToServer && 0 < HUD.score) {
							String s = "";
							if(!saveName.equals("")) {
								s=this.KIVersion+"\nDELETE:DELETE:"+saveName+"\n";
								//saveName = "";
							}
							s=s+"UPLOAD:UPLOAD:\n";
							s=s+HUD.nameEntered+": "+Long.toString((hud.getScore()));
							clientToServer(s);
						}
						if(!saveName.equals("") && !saveName.equals(HUD.nameEntered+": "+Long.toString(HUD.score)))
							LeaderboardController.writeToSettings("saveName", saveName);
						if(!connectingToServer) {
							State = STATE.GAMEOVER;
							if(gameControllerInUse)
								Game.keysAreInUse = true;
							if(!serverErrorMessage) {
								if(Game.smb31PupSoundLoop.clipIsActive())
									Game.smb31PupSoundLoop.stop();
								Game.smb31PupSoundLoop.play();
							}
						}
						break;
					}
					else {
						postLetter = '=';
						Game.connectingToServer = false;
						doneConnectingToServer = false;
						Game.keysAreInUse = false;
						Game.selectorButtonPosition = 0;
						if(Game.State != STATE.SET_SCORE)
							Game.State = STATE.SET_SCORE;
						if(Game.smwErrorSoundLoop.clipIsActive())
							Game.smwErrorSoundLoop.stop();
						Game.smwErrorSoundLoop.play();
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
			if(connectingToServer) {
				if(!doneConnectingToServer)
					doneConnectingToServer = true;
				this.renderConnectingMessage();
			}
			if(!connectingToServer && doneConnectingToServer) {
				Game.selectorButtonPosition = 0;
				State = STATE.GAMEOVER;
				if(gameControllerInUse)
					Game.keysAreInUse = true;
				if(!serverErrorMessage && serverErrorMessageTimer+2000 < System.currentTimeMillis() ) {
					if(Game.smb31PupSoundLoop.clipIsActive())
						Game.smb31PupSoundLoop.stop();
					Game.smb31PupSoundLoop.play();
				}
				doneConnectingToServer = false;
			}
		}else if(State == STATE.LEADERBOARD) {
			if(starExplode) {
				starExplosion.StarExplosionSetup(Game.mx,Game.my);
				starExplosionTimer = System.currentTimeMillis() + 2000;
				starExplode = false;
				//starExplode = false;
			}
			if(System.currentTimeMillis() < starExplosionTimer) {
				starExplosion.Explosion(g);
			}
			if(ufoSpawned) {
				c.render(g);
			}
			mouseLocator.locateMouse();
			g.drawImage(leaderboardTitleBigger, Game.WIDTH / 2, 20, null);
			HUD.clickyButton(g, backButtonTitle, backButtonTitleGlow, backButtonTitleClicked, Game.backHighlighted, Game.backClicked, Game.backOnBack, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 40, 20);
			if(leaderboard.globalList) {
				HUD.clickyButton(g, localTitle, localTitleGlow, localTitleClicked, Game.localHighlighted, Game.localClicked, Game.backOnLocal, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 40, 50);
				HUD.clickyButton(g, uploadTitle, uploadTitleGlow, uploadTitleClicked, Game.uploadHighlighted, Game.uploadClicked, Game.backOnUpload, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,537, 50);
			}
			else {
				HUD.clickyButton(g, resetLeaderboardTitle, resetLeaderboardTitleGlow, resetLeaderboardTitleClicked, Game.resetLeaderboardHighlighted, Game.resetLeaderboardClicked, Game.backOnResetLeaderboard, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 40, 50);
				HUD.clickyButton(g, globalTitle, globalTitleGlow, globalTitleClicked, Game.globalHighlighted, Game.globalClicked, Game.backOnGlobal, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,537, 50);
			}
			/*
			if(!this.backHighlighted)
				g.drawImage(backButtonTitle, 40, 20, null);
			else
				g.drawImage(backButtonTitleGlow, 40, 20, null);
				*/
			
			if(leaderboard.getInitalized() == false && leaderboard.globalList == false) {
				leaderboard.resetLeaderboard();
				leaderboard.resetLeaderboardImage();
				leaderboard.initialize();
			}
			if(LeaderboardController.resetTrigger) {
				leaderboard.resetLeaderboard();
				leaderboard.resetLeaderboardImage();
				leaderboard.initialize();
				leaderboard.setupText();
				LeaderboardController.resetTrigger = false;
			}
			if(LeaderboardController.deleteTrigger) {
				LeaderboardController.deleteLeaderboardFile();
				leaderboard.resetLeaderboard();
				LeaderboardController.deleteTrigger = false;
        		leaderboard.displayText();
        		highScore = 0;
			}
			leaderboard.drawLeaderboard(g,this.getLeaderboardImage());
			if(areYouSureBoolean) {
				g.drawImage(areYouSure,0,0,null);
				HUD.clickyButton(g, noTitle, noTitleGlow, noTitleClicked, Game.noHighlighted, Game.noClicked, Game.backOnNo, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - 64 - 18,375);
				HUD.clickyButton(g, yesTitle, yesTitleGlow, yesTitleClicked, Game.yesHighlighted, Game.yesClicked, Game.backOnYes, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH + 18,375);
			}
			if(Game.keysAreInUse) {
				if(Game.backHighlighted || Game.backClicked || 
						Game.globalHighlighted || Game.globalClicked ||
						Game.uploadHighlighted || Game.uploadClicked ||
						Game.localHighlighted || Game.localClicked   ||
						Game.resetLeaderboardHighlighted) {
					Game.backHighlighted = false;
					Game.backClicked = false;
					Game.globalHighlighted = false;
					Game.globalClicked = false;
					Game.uploadHighlighted = false;
					Game.uploadClicked = false;
					Game.localHighlighted = false;
					Game.localClicked = false;
					Game.resetLeaderboardClicked = false;
				}
				if(areYouSureBoolean) {
					if(Game.selectorButtonPosition < 0 || 1 < Game.selectorButtonPosition)
						Game.selectorButtonPosition = 0;
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
					if(Game.selectorButtonPosition < -1 || 1 < Game.selectorButtonPosition)
						Game.selectorButtonPosition = -1;
					if(Game.enterButtonPushedDown && !Game.escapePressedNegateAction) {
						switch(Game.selectorButtonPosition) {
						case -1:
							g.drawImage(backTitleSelectedClicked,40 -7, 20 -7,null);
							g.drawImage(backButtonTitleClicked,40, 20,null);
							break;
						case 0:
							if(LeaderboardController.globalList) {
								g.drawImage(localTitleSelectedClicked,40 -7, 50 -7,null);
								g.drawImage(localTitleClicked,40, 50,null);
							}
							else {
								g.drawImage(resetLeaderboardTitleSelectedClicked,40 -7, 50 -7,null);
								g.drawImage(resetLeaderboardTitleClicked,40, 50,null);
							}
							break;
						case 1:
							if(LeaderboardController.globalList) {
								g.drawImage(uploadTitleSelectedClicked,537 -7, 50 -7,null);
								g.drawImage(uploadTitleClicked,537, 50,null);
							}
							else {
								g.drawImage(globalTitleSelectedClicked,537 -7, 50 -7,null);
								g.drawImage(globalTitleClicked,537, 50,null);
							}
							break;
						}
					}
					else if(Game.enterButtonPushedDown && Game.escapePressedNegateAction) {
						switch(Game.selectorButtonPosition) {
						case -1:
							g.drawImage(backTitleSelectedNormal,40 -7, 20 -7,null);
							g.drawImage(backButtonTitle,40, 20,null);
							break;
						case 0:
							if(LeaderboardController.globalList) {
								g.drawImage(localTitleSelectedNormal,40 -7, 50 -7,null);
								g.drawImage(localTitle,40, 50,null);
							}
							else {
								g.drawImage(resetLeaderboardTitleSelectedNormal,40 -7, 50 -7,null);
								g.drawImage(resetLeaderboardTitle,40, 50,null);
							}
							break;
						case 1:
							if(LeaderboardController.globalList) {
								g.drawImage(uploadTitleSelectedNormal,537 -7, 50 -7,null);
								g.drawImage(uploadTitle,537, 50,null);
							}
							else {
								g.drawImage(globalTitleSelectedNormal,537 -7, 50 -7,null);
								g.drawImage(globalTitle,537, 50,null);
							}
							break;
						}
					}
					else {
						switch(Game.selectorButtonPosition) {
						case -1:
							g.drawImage(backTitleSelected,40 -7, 20 -7,null);
							g.drawImage(backButtonTitleGlow,40, 20,null);
							break;
						case 0:
							if(LeaderboardController.globalList) {
								g.drawImage(localTitleSelected,40 -7, 50 -7,null);
								g.drawImage(localTitleGlow,40, 50,null);
							}
							else {
								g.drawImage(resetLeaderboardTitleSelected,40 -7, 50 -7,null);
								g.drawImage(resetLeaderboardTitleGlow,40, 50,null);
							}
							break;
						case 1:
							if(LeaderboardController.globalList) {
								g.drawImage(uploadTitleSelected,537 -7, 50 -7,null);
								g.drawImage(uploadTitleGlow,537, 50,null);
							}
							else {
								g.drawImage(globalTitleSelected,537 -7, 50 -7,null);
								g.drawImage(globalTitleGlow,537, 50,null);
							}
							break;
						}
					}
				}
			}
			if(connectingToServer)
				this.renderConnectingMessage();
//			if(globalClicked)
			
		}else if(State == STATE.SHOP) {
			if(skinNumber == null) {
				if(currentlySelectedCharacterSkin == 5)
					skinNumber = resize(HUD.stringToMario3FontImage(Integer.toString(characterSkinPosition)), 10, 10);
				else
					skinNumber = resize(HUD.stringToMario3FontImage(Integer.toString(characterSkinPosition+1)), 10, 10);
				trackNumber = resize(HUD.stringToMario3FontImage(Integer.toString(trackPosition+1)), 10, 10);
				if(currentlySelectedFireball == 5)
					fireballNumber = resize(HUD.stringToMario3FontImage(Integer.toString(fireballPosition)), 10, 10);
				else
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
			if(ufoSpawned) {
				c.render(g);
			}
			g.drawImage(shopTitle, Game.WIDTH - 54, 20, null);
			g.drawImage(skinTitle, 20, 120, null);
			g.drawImage(tracksTitle, 20, 220, null);
			g.drawImage(fireballsTitle, 20, 320, null);
			g.drawImage(itemsTitle, 20, 420, null);
			if(totalPoints != 0) {//if(!LeaderboardController.getFromSettings("Total Points: ").equals("")) {
				g.drawImage(totalPointsImage, Game.WIDTH * Game.SCALE - totalPointsImage.getWidth() - 60, 20, null);
				g.drawImage(ShopController.pointsImage,Game.WIDTH * Game.SCALE - 55, 20 + totalPointsImage.getHeight()/2 -3, null);
			}
			if(currentlySelectedCharacterSkin == characterSkinPosition)
				g.drawImage(currentlySelected10x10,Game.WIDTH -2, 120-19,null);
			g.drawImage(skinNumber,Game.WIDTH + 2, 120-15,null);
			switch(trackPosition) {
			case 0:
				if(this.gameTrack1Set == true)
					g.drawImage(currentlySelected10x10,Game.WIDTH -2, 220-19,null);
				break;
			case 1:
				if(this.gameTrack2Set == true)
					g.drawImage(currentlySelected10x10,Game.WIDTH -2, 220-19,null);
				break;
			case 2:
				if(this.gameTrack3Set == true)
					g.drawImage(currentlySelected10x10,Game.WIDTH -2, 220-19,null);
				break;
			case 3:
				if(this.gameTrack4Set == true)
					g.drawImage(currentlySelected10x10,Game.WIDTH -2, 220-19,null);
				break;
			case 4:
				if(this.gameTrack5Set == true)
					g.drawImage(currentlySelected10x10,Game.WIDTH -2, 220-19,null);
				break;
			case 5:
				if(this.gameTrack6Set == true)
					g.drawImage(currentlySelected10x10,Game.WIDTH -2, 220-19,null);
				break;
			case 6:
				if(this.gameTrack7Set == true)
					g.drawImage(currentlySelected10x10,Game.WIDTH -2, 220-19,null);
				break;
			case 7:
				if(this.gameTrack8Set == true)
					g.drawImage(currentlySelected10x10,Game.WIDTH -2, 220-19,null);
				break;
			default:
				break;
			}
			//if(currentlySelectedTrack == trackPosition)
				//g.drawImage(currentlySelected10x10,Game.WIDTH -2, 220-19,null);
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
			//shop.tick();
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
			if(allUnlockedScreen)
				g.drawImage(congrats,0,0,null);
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
			if(ufoSpawned) {
				c.render(g);
			}
			if(helpLines.isEmpty()) {
				BufferedImage firstline = HUD.stringToMario3FontImage("Help Mario fight off Koopa's Invaders!");
				firstline = Game.resize(firstline, (firstline.getWidth()/2), (firstline.getHeight()/2));
				BufferedImage secondline = HUD.stringToMario3FontImage("Don't let the enemy land or strike you");
				secondline = Game.resize(secondline, (secondline.getWidth()/2), (secondline.getHeight()/2));
				BufferedImage thirdline = HUD.stringToMario3FontImage("Use " + KeyEvent.getKeyText(leftKey) + " and " + 
				KeyEvent.getKeyText(rightKey)+ " to move");
				thirdline = Game.resize(thirdline, thirdline.getWidth()/2, thirdline.getHeight()/2);
				BufferedImage fourthline = HUD.stringToMario3FontImage("Use " + KeyEvent.getKeyText(shootKey) + " to shoot and " +
				KeyEvent.getKeyText(itemKey) + " to use items");
				fourthline = Game.resize(fourthline, (fourthline.getWidth()/2), (fourthline.getHeight()/2));
				BufferedImage fifthline = HUD.stringToMario3FontImage("Points can be used in the shop");
				fifthline = Game.resize(fifthline, (fifthline.getWidth()/2), (fifthline.getHeight()/2));
				BufferedImage sixthline = HUD.stringToMario3FontImage("Stars make you invulnerable and faster");
				sixthline = Game.resize(sixthline, (sixthline.getWidth()/2), (sixthline.getHeight()/2));
				helpLines.add(firstline);
				helpLines.add(secondline);
				helpLines.add(thirdline);
				helpLines.add(fourthline);
				helpLines.add(fifthline);
				helpLines.add(sixthline);
				this.helpLinesLastSetup();
			}
			g.drawImage(helpTitleBigger, Game.WIDTH - (helpTitleBigger.getWidth()/2)/*+14*/, 20, null);
			if(firstTimeBeating) 
				HUD.clickyButton(g, creditsTitle, creditsTitleGlow, creditsTitleClicked, Game.creditsHighlighted, Game.creditsClicked, Game.backOnCredits, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,477, 38);
			
			if(!helpLines.isEmpty()) {
				for(int i = 0; i < helpLines.size(); i++) {
					g.drawImage(helpLines.get(i),Game.WIDTH - (helpLines.get(i).getWidth()/2),((i+1)*50)+70,null);
				}
			}
			Graphics2D g2dd = (Graphics2D)g.create();
			g2dd.setComposite(makeComposite(imageTranslucent));
			g2dd.drawImage(helpLinesLast,Game.WIDTH - (helpLinesLast.getWidth()/2)+4,420,null);
//			g.drawImage(firstline,Game.WIDTH - (firstline.getWidth()/2),120,null);
			mouseLocator.locateMouse();
			HUD.clickyButton(g, backButtonTitle, backButtonTitleGlow, backButtonTitleClicked, Game.backHighlighted, Game.backClicked, Game.backOnBack, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 40, 20);
			if(Game.keysAreInUse) {
				if(Game.backHighlighted ||
				   Game.backClicked		||
				   Game.creditsHighlighted	||
				   Game.creditsClicked) {
					backHighlighted = false;
					backClicked = false;
					creditsHighlighted = false;
					creditsClicked = false;
				}
				if(Game.enterButtonPushedDown && !Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -1:
						g.drawImage(backTitleSelectedClicked,40 -7, 20 -7,null);
						g.drawImage(backButtonTitleClicked,40, 20,null);
						break;
					case 0:
						if(firstTimeBeating) {
							g.drawImage(creditsTitleSelectedClicked,477 -7, 38 -7,null);
							g.drawImage(creditsTitleClicked,477, 38,null);
						}
						break;
					}
				}
				else if(Game.enterButtonPushedDown && Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -1:
						g.drawImage(backTitleSelectedNormal,40 -7, 20 -7,null);
						g.drawImage(backButtonTitle,40, 20,null);
						break;
					case 0:
						if(firstTimeBeating) {
							g.drawImage(creditsTitleSelectedNormal,477 -7, 38 -7,null);
							g.drawImage(creditsTitle,477, 38,null);
						}
						break;
					}
				}
				else {
					switch(Game.selectorButtonPosition) {
					case -1:
						g.drawImage(backTitleSelected,40 -7, 20 -7,null);
						g.drawImage(backButtonTitleGlow,40, 20,null);
						break;
					case 0:
						if(firstTimeBeating) {
							g.drawImage(creditsTitleSelected,477 -7, 38 -7,null);
							g.drawImage(creditsTitleGlow,477, 38,null);
						}
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
			if(ufoSpawned) {
				c.render(g);
			}
			mouseLocator.locateMouse();
			g.drawImage(settingsTitleBigger,Game.WIDTH-128,20,null);
			g.drawImage(emptyVolumeSlider,Game.WIDTH - 69,120,null);
			g.drawImage(emptyVolumeSlider,Game.WIDTH - 69,220,null);
			g.drawImage(volumeTitle,20,120,null);
			g.drawImage(sfxMusicTitle,20,220,null);
			g.drawImage(skipAnimationsTitle,20,320,null);
			//g.drawImage(gamepadImageTitle,Game.WIDTH+168,42,null);
			//g.drawImage(wasdImageTitle,Game.WIDTH+247,34,null);
			//g.drawImage(noteImageTitle,Game.WIDTH+306,42,null);
			//g.drawImage(gamepadImageTitle,Game.WIDTH+178,42,null);
			//g.drawImage(noteImageTitle,Game.WIDTH+268,41,null);
			//g.drawImage(gamepadImageTitleSelectedNormal, Game.WIDTH+171, 35, null);
			//g.drawImage(noteImageTitleSelectedNormal, Game.WIDTH+261, 34, null);
			HUD.clickyButton(g, backButtonTitle, backButtonTitleGlow, backButtonTitleClicked, Game.backHighlighted, Game.backClicked, Game.backOnBack, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 40, 20);
			HUD.clickyButton(g, arrowL, arrowLGlow, arrowLClicked, Game.arrowL1Highlighted, Game.arrowL1Clicked, Game.backOnArrowL1, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH  - 69 - 40 - 16, 121);
			HUD.clickyButton(g, arrowR, arrowRGlow, arrowRClicked, Game.arrowR1Highlighted, Game.arrowR1Clicked, Game.backOnArrowR1, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH  + 69 + 40, 121);
			HUD.clickyButton(g, volumeSlider, volumeSliderGlow, volumeSliderClicked, Game.volumeSliderHighlighted, Game.volumeSliderBooleanClicked, Game.backOnVolumeSlider, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, VolumeSlider.x, VolumeSlider.y);
			HUD.clickyButton(g, arrowL, arrowLGlow, arrowLClicked, Game.arrowL2Highlighted, Game.arrowL2Clicked, Game.backOnArrowL2, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH  - 69 - 40 - 16, 221);
			HUD.clickyButton(g, arrowR, arrowRGlow, arrowRClicked, Game.arrowR2Highlighted, Game.arrowR2Clicked, Game.backOnArrowR2, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH  + 69 + 40, 221);
			HUD.clickyButton(g, volumeSlider, volumeSliderGlow, volumeSliderClicked, Game.sfxMusicSliderHighlighted, Game.sfxMusicSliderBooleanClicked, Game.backOnSFXMusicSlider, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, VolumeSlider.x2, VolumeSlider.y2);
			HUD.clickyButton(g, resetStatsTitle, resetStatsTitleGlow, resetStatsTitleClicked, Game.resetStatsHighlighted, Game.resetStatsClicked, Game.backOnResetStats, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - (resetStatsTitle.getWidth()/2), 420);
			HUD.clickyButton(g, gamepadImageTitle, gamepadImageTitleGlow, gamepadImageTitleClicked, Game.gamepadImageHighlighted, Game.gamepadImageClicked, Game.backOnGamepadImage, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH+178,42);
			HUD.clickyButton(g, noteImageTitle, noteImageTitleGlow, noteImageTitleClicked, Game.noteImageHighlighted, Game.noteImageClicked, Game.backOnNoteImage, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,Game.WIDTH+268,41);
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
				if(Game.backHighlighted || Game.arrowL1Highlighted || Game.arrowL2Highlighted ||
				   Game.arrowR1Highlighted || Game.arrowR2Highlighted || Game.checkMarkHighlighted ||
				   Game.resetStatsHighlighted || Game.gamepadImageHighlighted || Game.noteImageHighlighted ||
				   Game.backClicked || Game.arrowL1Clicked || Game.arrowL2Clicked || Game.arrowR1Clicked ||
				   Game.arrowR2Clicked || Game.checkMarkClicked || Game.resetStatsClicked || 
				   Game.gamepadImageClicked || Game.noteImageClicked) {
					backHighlighted = false;
					backClicked = false;
					arrowL1Highlighted = false;
					arrowL1Clicked = false;
					arrowL2Highlighted = false;
					arrowL2Clicked = false;
					arrowR1Highlighted = false;
					arrowR1Clicked = false;
					arrowR2Highlighted = false;
					arrowR2Clicked = false;
					checkMarkHighlighted = false;
					checkMarkClicked = false;
					resetStatsHighlighted = false;
					resetStatsClicked = false;
					gamepadImageHighlighted = false;
					gamepadImageClicked = false;
					noteImageHighlighted = false;
					noteImageClicked = false;
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
						case -5:
							g.drawImage(noteImageTitleSelectedClicked, Game.WIDTH+261, 34, null);
							g.drawImage(noteImageTitleClicked,Game.WIDTH+268,41,null);
							break;
						case -4:
							g.drawImage(gamepadImageTitleSelectedClicked, Game.WIDTH+171, 35, null);
							g.drawImage(gamepadImageTitleClicked,Game.WIDTH+178,42,null);
							break;
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
						case -5:
							g.drawImage(noteImageTitleSelectedNormal, Game.WIDTH+261, 34, null);
							g.drawImage(noteImageTitle,Game.WIDTH+268,41,null);
							break;
						case -4:
							g.drawImage(gamepadImageTitleSelectedNormal, Game.WIDTH+171, 35, null);
							g.drawImage(gamepadImageTitle,Game.WIDTH+178,42,null);
							break;
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
							g.drawImage(dotSelectorPositionWhite,Game.WIDTH - (checkMarkTitle.getWidth()/2)+14,310,null);
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
						case -5:
							g.drawImage(noteImageTitleSelected, Game.WIDTH+261, 34, null);
							g.drawImage(noteImageTitleGlow,Game.WIDTH+268,41,null);
							break;
						case -4:
							g.drawImage(gamepadImageTitleSelected, Game.WIDTH+171, 35, null);
							g.drawImage(gamepadImageTitleGlow,Game.WIDTH+178,42,null);
							break;
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
		}
		else if(State == STATE.CONTROLS) {
			if(writeOnceToSettings){
				if(writeOnceProperty.equals("") || writeOnceString.equals("")){
					writeOnceProperty = "";
					writeOnceString = "";
					writeOnceToSettings = false;
				}
				else{
					LeaderboardController.writeToSettings(writeOnceProperty, writeOnceString);
					writeOnceProperty = "";
					writeOnceString = "";
					writeOnceToSettings = false;
					if(!writeMultipleProperty.isEmpty()) {
						for(int i = 0; i <= writeMultipleProperty.size()-1; i++) {
							LeaderboardController.writeToSettings(writeMultipleProperty.get(i), writeMultipleString.get(i));
							writeMultipleProperty.set(i, null);
							writeMultipleString.set(i, null);
							writeMultipleProperty.remove(i);
							writeMultipleString.remove(i);
							i--;
						}
						writeMultipleProperty.clear();
						writeMultipleString.clear();
						controlsController.updateControls();
					}
				}
			}
//    		if(revertControllerSettings == true && ControlsController.buttonChangeTimer < System.currentTimeMillis()) {
//    			revertControllerSettings = false;
//    			leaderboard.settingsSetup();
//    		}
			if(starExplode) {
				starExplosion.StarExplosionSetup(Game.mx,Game.my);
				starExplosionTimer = System.currentTimeMillis() + 2000;
				starExplode = false;
				//starExplode = false;
			}
			if(System.currentTimeMillis() < starExplosionTimer) {
				starExplosion.Explosion(g);
			}
			if(ufoSpawned) {
				c.render(g);
			}
			if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
				mouseLocator.locateMouse();
			g.drawImage(controlsTitle,Game.WIDTH-130,20,null);
			g.drawImage(keyboardTitle, Game.WIDTH-73, 94, null);
			g.drawImage(xInputTitle, 373, 94, null);
			g.drawImage(directInputTitle, 470, 94, null);
			g.drawImage(upImageTitle, 20, 120, null);
			g.drawImage(downImageTitle, 20, 165, null);
			g.drawImage(leftImageTitle, 20, 210, null);
			g.drawImage(rightImageTitle, 20, 255, null);
			g.drawImage(shootImageTitle, 20, 300, null);
			g.drawImage(itemImageTitle, 20, 345, null);
			g.drawImage(pauseImageTitle, 20, 390, null);
			g.drawImage(cancelImageTitle, 20, 435, null);
			controlsController.draw(g);
			//g.drawImage(gamepadButtonHolder,Game.WIDTH-51,305,null);
			//g.drawImage(tex.ltButtonImage[0],Game.WIDTH-48,308,null);
			//g.drawImage(aButtonImage,Game.WIDTH-44,308,null);
			
			//g.drawImage(gamepadButtonHolder,Game.WIDTH-91,157,null);
			//g.drawImage(ltButtonImage,Game.WIDTH-88,160,null);
			//g.drawImage(aButtonImage,Game.WIDTH-84,160,null);
			HUD.clickyButton(g, backButtonTitle, backButtonTitleGlow, backButtonTitleClicked, Game.backHighlighted, Game.backClicked, Game.backOnBack, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 40, 20);
			HUD.clickyButton(g, resetTitle, resetTitleGlow, resetTitleClicked, Game.resetHighlighted, Game.resetClicked, Game.backOnReset, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 564, 443);
			HUD.clickyButton(g, rescanTitle, rescanTitleGlow, rescanTitleClicked, Game.rescanHighlighted, Game.rescanClicked, Game.backOnRescan, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 489, 65);
			//g.drawImage(resetTitle,524+40,443,null);
			//g.drawImage(resetTitleSelectedNormal,524+33,436,null);
			if(Game.keysAreInUse) {
				if(Game.backHighlighted || Game.resetHighlighted || Game.rescanHighlighted || ControlsController.gamepadButtonHolderHighlighted[0] ||
						ControlsController.gamepadButtonHolderHighlighted[1] || ControlsController.gamepadButtonHolderHighlighted[2] ||
						ControlsController.gamepadButtonHolderHighlighted[3] ||ControlsController.gamepadButtonHolderHighlighted[4] ||
						ControlsController.gamepadButtonHolderHighlighted[5] ||ControlsController.gamepadButtonHolderHighlighted[6] ||
						ControlsController.gamepadButtonHolderHighlighted[7] ||ControlsController.gamepadButtonHolderHighlighted[8] ||
						ControlsController.gamepadButtonHolderHighlighted[9] ||ControlsController.gamepadButtonHolderHighlighted[10] ||
						ControlsController.gamepadButtonHolderHighlighted[11] ||ControlsController.gamepadButtonHolderHighlighted[12] ||
						ControlsController.gamepadButtonHolderHighlighted[13] ||ControlsController.gamepadButtonHolderHighlighted[14] ||
						ControlsController.gamepadButtonHolderHighlighted[15] ||ControlsController.gamepadButtonHolderHighlighted[16] ||
						ControlsController.gamepadButtonHolderHighlighted[17] ||ControlsController.gamepadButtonHolderHighlighted[18] ||
						ControlsController.gamepadButtonHolderHighlighted[19] ||ControlsController.gamepadButtonHolderHighlighted[20] ||
						ControlsController.gamepadButtonHolderHighlighted[21] ||ControlsController.gamepadButtonHolderHighlighted[22] ||
						ControlsController.gamepadButtonHolderHighlighted[23] ||
						Game.backClicked || Game.resetClicked || Game.rescanClicked || ControlsController.gamepadButtonHolderClicked[0] ||
						ControlsController.gamepadButtonHolderClicked[1] || ControlsController.gamepadButtonHolderClicked[2] ||
						ControlsController.gamepadButtonHolderClicked[3] ||ControlsController.gamepadButtonHolderClicked[4] ||
						ControlsController.gamepadButtonHolderClicked[5] ||ControlsController.gamepadButtonHolderClicked[6] ||
						ControlsController.gamepadButtonHolderClicked[7] ||ControlsController.gamepadButtonHolderClicked[8] ||
						ControlsController.gamepadButtonHolderClicked[9] ||ControlsController.gamepadButtonHolderClicked[10] ||
						ControlsController.gamepadButtonHolderClicked[11] ||ControlsController.gamepadButtonHolderClicked[12] ||
						ControlsController.gamepadButtonHolderClicked[13] ||ControlsController.gamepadButtonHolderClicked[14] ||
						ControlsController.gamepadButtonHolderClicked[15] ||ControlsController.gamepadButtonHolderClicked[16] ||
						ControlsController.gamepadButtonHolderClicked[17] ||ControlsController.gamepadButtonHolderClicked[18] ||
						ControlsController.gamepadButtonHolderClicked[19] ||ControlsController.gamepadButtonHolderClicked[20] ||
						ControlsController.gamepadButtonHolderClicked[21] ||ControlsController.gamepadButtonHolderClicked[22] ||
						ControlsController.gamepadButtonHolderClicked[23] ) {
					for(int i = 0; i <= ControlsController.gamepadButtonHolderHighlighted.length-1; i++) {
						ControlsController.gamepadButtonHolderHighlighted[i] = false;
						ControlsController.gamepadButtonHolderClicked[i] = false;
					}
					backHighlighted = false;
					backClicked = false;
					resetHighlighted = false;
					resetClicked = false;
					rescanHighlighted = false;
					rescanClicked = false;
				}
				if(Game.enterButtonPushedDown && !Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case 0:
						g.drawImage(rescanTitleSelectedClicked,489 -6, 65 -6,null);
						g.drawImage(rescanTitleClicked, 489, 65,null);
						break;
					case -1:
						g.drawImage(backTitleSelectedClicked,40 -7, 20 -7,null);
						g.drawImage(backButtonTitleClicked,40, 20,null);
						break;
					case -2:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], Game.WIDTH - 51, 125, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getUpKeyWASD()[2],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getUpKeyWASD()[0].getWidth())/2), 125+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getUpKeyWASD()[0].getHeight())/2), null);
						break;
					case -3:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], Game.WIDTH - 51, 170, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getDownKeyWASD()[2],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getDownKeyWASD()[0].getWidth())/2), 170+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getDownKeyWASD()[0].getHeight())/2), null);
						break;
					case -4:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], Game.WIDTH - 51, 215, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getLeftKeyWASD()[2],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getLeftKeyWASD()[0].getWidth())/2), 215+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getLeftKeyWASD()[0].getHeight())/2), null);
						break;
					case -5:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], Game.WIDTH - 51, 260, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getRightKeyWASD()[2],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getRightKeyWASD()[0].getWidth())/2), 260+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getRightKeyWASD()[0].getHeight())/2), null);
						break;
					case -6:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], Game.WIDTH - 51, 305, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getShootKeyWASD()[2],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getShootKeyWASD()[0].getWidth())/2), 305+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getShootKeyWASD()[0].getHeight())/2), null);
						break;
					case -7:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], Game.WIDTH - 51, 350, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getItemKeyWASD()[2],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getItemKeyWASD()[0].getWidth())/2), 350+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getItemKeyWASD()[0].getHeight())/2), null);
						break;
					case -8:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], Game.WIDTH - 51, 395, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getPauseKeyWASD()[2],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getPauseKeyWASD()[0].getWidth())/2), 395+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getPauseKeyWASD()[0].getHeight())/2), null);
						break;
					case -9:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], Game.WIDTH - 51, 440, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getCancelKeyWASD()[2],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getCancelKeyWASD()[0].getWidth())/2), 440+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getCancelKeyWASD()[0].getHeight())/2), null);
						break;
					case -10:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], 385, 125, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getUpKeyXDevice()[2],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getUpKeyXDevice()[0].getWidth())/2), 125+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getUpKeyXDevice()[0].getHeight())/2), null);
						break;
					case -11:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], 385, 170, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getDownKeyXDevice()[2],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getDownKeyXDevice()[0].getWidth())/2), 170+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getDownKeyXDevice()[0].getHeight())/2), null);
						break;
					case -12:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], 385, 215, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getLeftKeyXDevice()[2],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getLeftKeyXDevice()[0].getWidth())/2), 215+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getLeftKeyXDevice()[0].getHeight())/2), null);
						break;
					case -13:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], 385, 260, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getRightKeyXDevice()[2],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getRightKeyXDevice()[0].getWidth())/2), 260+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getRightKeyXDevice()[0].getHeight())/2), null);
						break;
					case -14:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], 385, 305, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getShootKeyXDevice()[2],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getShootKeyXDevice()[0].getWidth())/2), 305+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getShootKeyXDevice()[0].getHeight())/2), null);
						break;
					case -15:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], 385, 350, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getItemKeyXDevice()[2],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getItemKeyXDevice()[0].getWidth())/2), 350+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getItemKeyXDevice()[0].getHeight())/2), null);
						break;
					case -16:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], 385, 395, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getPauseKeyXDevice()[2],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getPauseKeyXDevice()[0].getWidth())/2), 395+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getPauseKeyXDevice()[0].getHeight())/2), null);
						break;
					case -17:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], 385, 440, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getCancelKeyXDevice()[2],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getCancelKeyXDevice()[0].getWidth())/2), 440+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getCancelKeyXDevice()[0].getHeight())/2), null);
						break;
					case -18:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], 501, 125, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getUpKeyDirectInput()[2],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getUpKeyDirectInput()[0].getWidth())/2), 125+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getUpKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -19:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], 501, 170, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getDownKeyDirectInput()[2],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getDownKeyDirectInput()[0].getWidth())/2), 170+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getDownKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -20:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], 501, 215, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getLeftKeyDirectInput()[2],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getLeftKeyDirectInput()[0].getWidth())/2), 215+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getLeftKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -21:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], 501, 260, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getRightKeyDirectInput()[2],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getRightKeyDirectInput()[0].getWidth())/2), 260+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getRightKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -22:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], 501, 305, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getShootKeyDirectInput()[2],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getShootKeyDirectInput()[0].getWidth())/2), 305+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getShootKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -23:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], 501, 350, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getItemKeyDirectInput()[2],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getItemKeyDirectInput()[0].getWidth())/2), 350+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getItemKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -24:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], 501, 395, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getPauseKeyDirectInput()[2],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getPauseKeyDirectInput()[0].getWidth())/2), 395+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getPauseKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -25:
						g.drawImage(controlsController.getGamepadButtonHolder()[2], 501, 440, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getCancelKeyDirectInput()[2],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getCancelKeyDirectInput()[0].getWidth())/2), 440+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getCancelKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -26:
						//RESET BUTTON
						g.drawImage(resetTitleSelectedClicked,564-7,443-7,null);
						g.drawImage(resetTitleClicked,564,443,null);
						break;
					}
				}
				else if(Game.enterButtonPushedDown && Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case 0:
						g.drawImage(rescanTitleSelectedNormal,489 -6, 65 -6,null);
						g.drawImage(rescanTitle, 489, 65,null);
						break;
					case -1:
						g.drawImage(backTitleSelectedNormal,40 -7, 20 -7,null);
						g.drawImage(backButtonTitle,40, 20,null);
						break;
					case -2:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], Game.WIDTH - 51, 125, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getUpKeyWASD()[0],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getUpKeyWASD()[0].getWidth())/2), 125+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getUpKeyWASD()[0].getHeight())/2), null);
						break;
					case -3:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], Game.WIDTH - 51, 170, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getDownKeyWASD()[0],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getDownKeyWASD()[0].getWidth())/2), 170+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getDownKeyWASD()[0].getHeight())/2), null);
						break;
					case -4:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], Game.WIDTH - 51, 215, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getLeftKeyWASD()[0],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getLeftKeyWASD()[0].getWidth())/2), 215+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getLeftKeyWASD()[0].getHeight())/2), null);
						break;
					case -5:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], Game.WIDTH - 51, 260, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getRightKeyWASD()[0],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getRightKeyWASD()[0].getWidth())/2), 260+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getRightKeyWASD()[0].getHeight())/2), null);
						break;
					case -6:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], Game.WIDTH - 51, 305, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getShootKeyWASD()[0],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getShootKeyWASD()[0].getWidth())/2), 305+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getShootKeyWASD()[0].getHeight())/2), null);
						break;
					case -7:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], Game.WIDTH - 51, 350, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getItemKeyWASD()[0],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getItemKeyWASD()[0].getWidth())/2), 350+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getItemKeyWASD()[0].getHeight())/2), null);
						break;
					case -8:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], Game.WIDTH - 51, 395, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getPauseKeyWASD()[0],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getPauseKeyWASD()[0].getWidth())/2), 395+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getPauseKeyWASD()[0].getHeight())/2), null);
						break;
					case -9:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], Game.WIDTH - 51, 440, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getCancelKeyWASD()[0],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getCancelKeyWASD()[0].getWidth())/2), 440+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getCancelKeyWASD()[0].getHeight())/2), null);
						break;
					case -10:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], 385, 125, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getUpKeyXDevice()[0],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getUpKeyXDevice()[0].getWidth())/2), 125+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getUpKeyXDevice()[0].getHeight())/2), null);
						break;
					case -11:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], 385, 170, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getDownKeyXDevice()[0],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getDownKeyXDevice()[0].getWidth())/2), 170+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getDownKeyXDevice()[0].getHeight())/2), null);
						break;
					case -12:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], 385, 215, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getLeftKeyXDevice()[0],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getLeftKeyXDevice()[0].getWidth())/2), 215+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getLeftKeyXDevice()[0].getHeight())/2), null);
						break;
					case -13:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], 385, 260, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getRightKeyXDevice()[0],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getRightKeyXDevice()[0].getWidth())/2), 260+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getRightKeyXDevice()[0].getHeight())/2), null);
						break;
					case -14:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], 385, 305, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getShootKeyXDevice()[0],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getShootKeyXDevice()[0].getWidth())/2), 305+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getShootKeyXDevice()[0].getHeight())/2), null);
						break;
					case -15:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], 385, 350, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getItemKeyXDevice()[0],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getItemKeyXDevice()[0].getWidth())/2), 350+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getItemKeyXDevice()[0].getHeight())/2), null);
						break;
					case -16:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], 385, 395, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getPauseKeyXDevice()[0],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getPauseKeyXDevice()[0].getWidth())/2), 395+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getPauseKeyXDevice()[0].getHeight())/2), null);
						break;
					case -17:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], 385, 440, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getCancelKeyXDevice()[0],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getCancelKeyXDevice()[0].getWidth())/2), 440+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getCancelKeyXDevice()[0].getHeight())/2), null);
						break;
					case -18:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], 501, 125, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getUpKeyDirectInput()[0],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getUpKeyDirectInput()[0].getWidth())/2), 125+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getUpKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -19:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], 501, 170, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getDownKeyDirectInput()[0],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getDownKeyDirectInput()[0].getWidth())/2), 170+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getDownKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -20:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], 501, 215, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getLeftKeyDirectInput()[0],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getLeftKeyDirectInput()[0].getWidth())/2), 215+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getLeftKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -21:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], 501, 260, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getRightKeyDirectInput()[0],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getRightKeyDirectInput()[0].getWidth())/2), 260+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getRightKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -22:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], 501, 305, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getShootKeyDirectInput()[0],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getShootKeyDirectInput()[0].getWidth())/2), 305+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getShootKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -23:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], 501, 350, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getItemKeyDirectInput()[0],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getItemKeyDirectInput()[0].getWidth())/2), 350+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getItemKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -24:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], 501, 395, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getPauseKeyDirectInput()[0],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getPauseKeyDirectInput()[0].getWidth())/2), 395+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getPauseKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -25:
						g.drawImage(controlsController.getGamepadButtonHolder()[0], 501, 440, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getCancelKeyDirectInput()[0],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getCancelKeyDirectInput()[0].getWidth())/2), 440+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getCancelKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -26:
						//RESET BUTTON
						g.drawImage(resetTitleSelectedNormal,564-7,443-7,null);
						g.drawImage(resetTitle,564,443,null);
						break;
					}
				}
				else {
					switch(Game.selectorButtonPosition) {
					case 0:
						g.drawImage(rescanTitleSelected,489 -6, 65 -6,null);
						g.drawImage(rescanTitleGlow, 489, 65,null);
						break;
					case -1:
						g.drawImage(backTitleSelected,40 -7, 20 -7,null);
						g.drawImage(backButtonTitleGlow,40, 20,null);
						break;
					case -2:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], Game.WIDTH - 51, 125, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getUpKeyWASD()[1],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getUpKeyWASD()[0].getWidth())/2), 125+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getUpKeyWASD()[0].getHeight())/2), null);
						break;
					case -3:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], Game.WIDTH - 51, 170, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getDownKeyWASD()[1],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getDownKeyWASD()[0].getWidth())/2), 170+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getDownKeyWASD()[0].getHeight())/2), null);
						break;
					case -4:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], Game.WIDTH - 51, 215, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getLeftKeyWASD()[1],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getLeftKeyWASD()[0].getWidth())/2), 215+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getLeftKeyWASD()[0].getHeight())/2), null);
						break;
					case -5:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], Game.WIDTH - 51, 260, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getRightKeyWASD()[1],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getRightKeyWASD()[0].getWidth())/2), 260+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getRightKeyWASD()[0].getHeight())/2), null);
						break;
					case -6:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], Game.WIDTH - 51, 305, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getShootKeyWASD()[1],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getShootKeyWASD()[0].getWidth())/2), 305+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getShootKeyWASD()[0].getHeight())/2), null);
						break;
					case -7:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], Game.WIDTH - 51, 350, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getItemKeyWASD()[1],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getItemKeyWASD()[0].getWidth())/2), 350+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getItemKeyWASD()[0].getHeight())/2), null);
						break;
					case -8:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], Game.WIDTH - 51, 395, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getPauseKeyWASD()[1],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getPauseKeyWASD()[0].getWidth())/2), 395+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getPauseKeyWASD()[0].getHeight())/2), null);
						break;
					case -9:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], Game.WIDTH - 51, 440, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getCancelKeyWASD()[1],Game.WIDTH - 51 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getCancelKeyWASD()[0].getWidth())/2), 440+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getCancelKeyWASD()[0].getHeight())/2), null);
						break;
					case -10:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], 385, 125, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getUpKeyXDevice()[1],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getUpKeyXDevice()[0].getWidth())/2), 125+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getUpKeyXDevice()[0].getHeight())/2), null);
						break;
					case -11:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], 385, 170, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getDownKeyXDevice()[1],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getDownKeyXDevice()[0].getWidth())/2), 170+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getDownKeyXDevice()[0].getHeight())/2), null);
						break;
					case -12:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], 385, 215, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getLeftKeyXDevice()[1],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getLeftKeyXDevice()[0].getWidth())/2), 215+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getLeftKeyXDevice()[0].getHeight())/2), null);
						break;
					case -13:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], 385, 260, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getRightKeyXDevice()[1],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getRightKeyXDevice()[0].getWidth())/2), 260+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getRightKeyXDevice()[0].getHeight())/2), null);
						break;
					case -14:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], 385, 305, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getShootKeyXDevice()[1],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getShootKeyXDevice()[0].getWidth())/2), 305+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getShootKeyXDevice()[0].getHeight())/2), null);
						break;
					case -15:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], 385, 350, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getItemKeyXDevice()[1],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getItemKeyXDevice()[0].getWidth())/2), 350+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getItemKeyXDevice()[0].getHeight())/2), null);
						break;
					case -16:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], 385, 395, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getPauseKeyXDevice()[1],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getPauseKeyXDevice()[0].getWidth())/2), 395+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getPauseKeyXDevice()[0].getHeight())/2), null);
						break;
					case -17:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], 385, 440, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getCancelKeyXDevice()[1],385 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getCancelKeyXDevice()[0].getWidth())/2), 440+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getCancelKeyXDevice()[0].getHeight())/2), null);
						break;
					case -18:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], 501, 125, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getUpKeyDirectInput()[1],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getUpKeyDirectInput()[0].getWidth())/2), 125+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getUpKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -19:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], 501, 170, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getDownKeyDirectInput()[1],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getDownKeyDirectInput()[0].getWidth())/2), 170+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getDownKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -20:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], 501, 215, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getLeftKeyDirectInput()[1],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getLeftKeyDirectInput()[0].getWidth())/2), 215+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getLeftKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -21:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], 501, 260, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getRightKeyDirectInput()[1],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getRightKeyDirectInput()[0].getWidth())/2), 260+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getRightKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -22:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], 501, 305, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getShootKeyDirectInput()[1],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getShootKeyDirectInput()[0].getWidth())/2), 305+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getShootKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -23:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], 501, 350, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getItemKeyDirectInput()[1],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getItemKeyDirectInput()[0].getWidth())/2), 350+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getItemKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -24:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], 501, 395, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getPauseKeyDirectInput()[1],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getPauseKeyDirectInput()[0].getWidth())/2), 395+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getPauseKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -25:
						g.drawImage(controlsController.getGamepadButtonHolder()[1], 501, 440, null);
						if(!(System.currentTimeMillis() < ControlsController.buttonChangeTimer))
							g.drawImage(controlsController.getCancelKeyDirectInput()[1],501 +((controlsController.getGamepadButtonHolder()[0].getWidth()-controlsController.getCancelKeyDirectInput()[0].getWidth())/2), 440+((controlsController.getGamepadButtonHolder()[0].getHeight()-controlsController.getCancelKeyDirectInput()[0].getHeight())/2), null);
						break;
					case -26:
						//RESET BUTTON
						g.drawImage(resetTitleSelected,564-7,443-7,null);
						g.drawImage(resetTitleGlow,564,443,null);
						break;
					}
				}
			}
			
		}
		else if(State == STATE.TRACKLIST) {
			if(gameTrackNumber == null) {
				gameTrackNumber = makeTransparent(resize(HUD.stringToMario3FontImage(Integer.toString(this.gameTrackPosition+1)), 20, 20));
				menuTrackNumber = makeTransparent(resize(HUD.stringToMario3FontImage(Integer.toString(this.menuTrackPosition+1)), 20, 20));
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
			if(ufoSpawned) {
				c.render(g);
			}
			if(this.gameTrackPosition == 7) {
				if(this.gameTrack8Set == true)
					g.drawImage(redBowserIcon,Game.WIDTH - 161, 116, null);
				else
					g.drawImage(redBowserIcon,Game.WIDTH - 161, 124, null);
			}
			trackController.drawTrack(g);
			mouseLocator.locateMouse();
			g.drawImage(tracklistTitle,Game.WIDTH-102,20,null);
			g.drawImage(gameTitle,20,150,null);
			g.drawImage(menuTitle,20,350,null);
			g.drawImage(gameTrackNumber,Game.WIDTH  - 159,155,null);
			g.drawImage(menuTrackNumber,Game.WIDTH  - 159,355,null);
			HUD.clickyButton(g, backButtonTitle, backButtonTitleGlow, backButtonTitleClicked, Game.backHighlighted, Game.backClicked, Game.backOnBack, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 40, 20);
			if(Game.trackCurrentlyPlaying && Game.gameTrackCurrentlyPlaying == Game.gameTrackPosition && !trackController.gameTrackPaused)
				HUD.clickyButton(g, trackPauseButtonTitle, trackPauseButtonTitleGlow, trackPauseButtonTitleClicked, Game.trackPlayButton1Highlighted, Game.trackPlayButton1Clicked, Game.backOnTrackPlayButton1, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 250, 158);
			else
				HUD.clickyButton(g, trackPlayButtonTitle, trackPlayButtonTitleGlow, trackPlayButtonTitleClicked, Game.trackPlayButton1Highlighted, Game.trackPlayButton1Clicked, Game.backOnTrackPlayButton1, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 250, 158);
			HUD.clickyButton(g, trackStopButtonTitle, trackStopButtonTitleGlow, trackStopButtonTitleClicked, Game.trackPauseButton1Highlighted, Game.trackPauseButton1Clicked, Game.backOnTrackPauseButton1, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 280, 158);
			if(Game.trackCurrentlyPlaying && Game.menuTrackCurrentlyPlaying == Game.menuTrackPosition && !trackController.gameTrackPaused)
				HUD.clickyButton(g, trackPauseButtonTitle, trackPauseButtonTitleGlow, trackPauseButtonTitleClicked, Game.trackPlayButton2Highlighted, Game.trackPlayButton2Clicked, Game.backOnTrackPlayButton2, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 250, 358);
			else
				HUD.clickyButton(g, trackPlayButtonTitle, trackPlayButtonTitleGlow, trackPlayButtonTitleClicked, Game.trackPlayButton2Highlighted, Game.trackPlayButton2Clicked, Game.backOnTrackPlayButton2, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 250, 358);
			HUD.clickyButton(g, trackStopButtonTitle, trackStopButtonTitleGlow, trackStopButtonTitleClicked, Game.trackPauseButton2Highlighted, Game.trackPauseButton2Clicked, Game.backOnTrackPauseButton2, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, 280, 358);
			HUD.clickyButton(g, arrowL, arrowLGlow, arrowLClicked, Game.arrowL1Highlighted, Game.arrowL1Clicked, Game.backOnArrowL1, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH  - 129 - 40 - 16, 150);
			HUD.clickyButton(g, arrowR, arrowRGlow, arrowRClicked, Game.arrowR1Highlighted, Game.arrowR1Clicked, Game.backOnArrowR1, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH  - 129, 150);
			HUD.clickyButton(g, arrowL, arrowLGlow, arrowLClicked, Game.arrowL2Highlighted, Game.arrowL2Clicked, Game.backOnArrowL2, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH  - 129 - 40 - 16, 350);
			HUD.clickyButton(g, arrowR, arrowRGlow, arrowRClicked, Game.arrowR2Highlighted, Game.arrowR2Clicked, Game.backOnArrowR2, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH  - 129, 350);
			HUD.clickyButton(g, setTitle, setTitleGlow, setTitleClicked, Game.set1Highlighted, Game.set1Clicked, Game.backOnSet1, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH  + 18, 158);
			HUD.clickyButton(g, setTitle, setTitleGlow, setTitleClicked, Game.set2Highlighted, Game.set2Clicked, Game.backOnSet2, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH  + 18, 358);
			switch(Game.gameTrackPosition) {
			case 0:
				if(Game.gameTrack1Set)
					g.drawImage(trackSetBox,Game.WIDTH  - 159-6,155 -8,null);
				break;
			case 1:
				if(Game.gameTrack2Set)
					g.drawImage(trackSetBox,Game.WIDTH  - 159-6,155 -8,null);
				break;
			case 2:
				if(Game.gameTrack3Set)
					g.drawImage(trackSetBox,Game.WIDTH  - 159-6,155 -8,null);
				break;
			case 3:
				if(Game.gameTrack4Set)
					g.drawImage(trackSetBox,Game.WIDTH  - 159-6,155 -8,null);
				break;
			case 4:
				if(Game.gameTrack5Set && Game.track4Unlocked)
					g.drawImage(trackSetBox,Game.WIDTH  - 159-6,155 -8,null);
				else if(!Game.track4Unlocked)
					g.drawImage(lockedImage,Game.WIDTH - 159 +2,155-20,null);
				break;
			case 5:
				if(Game.gameTrack6Set && Game.track5Unlocked)
					g.drawImage(trackSetBox,Game.WIDTH  - 159-6,155 -8,null);
				else if(!Game.track5Unlocked)
					g.drawImage(lockedImage,Game.WIDTH - 159 +2,155-20,null);
				break;
			case 6:
				if(Game.gameTrack7Set && Game.track6Unlocked)
					g.drawImage(trackSetBox,Game.WIDTH  - 159-6,155 -8,null);
				else if(!Game.track6Unlocked)
					g.drawImage(lockedImage,Game.WIDTH - 159 +2,155-20,null);
				break;
			case 7:
				if(Game.gameTrack8Set)
					g.drawImage(trackSetBox,Game.WIDTH  - 159-6,155 -8,null);
				break;
			default:
				break;
			}
			switch(Game.menuTrackPosition) {
			case 0:
				if(Game.menuTrack1Set)
					g.drawImage(trackSetBox,Game.WIDTH  - 159-6,355 -8,null);
				break;
			case 1:
				if(Game.menuTrack2Set)
					g.drawImage(trackSetBox,Game.WIDTH  - 159-6,355 -8,null);
				break;
			case 2:
				if(Game.menuTrack3Set)
					g.drawImage(trackSetBox,Game.WIDTH  - 159-6,355 -8,null);
				break;
			case 3:
				if(Game.menuTrack4Set)
					g.drawImage(trackSetBox,Game.WIDTH  - 159-6,355 -8,null);
				break;
			case 4:
				if(Game.menuTrack5Set && Game.menuTrack4Unlocked)
					g.drawImage(trackSetBox,Game.WIDTH  - 159-6,355 -8,null);
				else if(!Game.menuTrack4Unlocked)
					g.drawImage(lockedImage,Game.WIDTH - 159 +2,355-20,null);
				break;
			default:
				break;
			}
			if(Game.keysAreInUse) {
				if(Game.selectorButtonPosition < -11 || -1 < Game.selectorButtonPosition)
					Game.selectorButtonPosition = -1;
				if(Game.backHighlighted || Game.arrowL1Highlighted || Game.arrowL2Highlighted || Game.arrowR1Highlighted ||
				   Game.backClicked		|| Game.arrowL1Clicked     || Game.arrowL2Clicked     || Game.arrowR1Clicked	 ||
				   Game.arrowR2Highlighted || Game.set1Highlighted || Game.set2Highlighted 	  || Game.trackPlayButton1Highlighted ||
				   Game.arrowR2Clicked	   || Game.set1Clicked     || Game.set2Clicked        || Game.trackPlayButton1Clicked     ||
				   Game.trackPlayButton2Highlighted ||
				   Game.trackPlayButton2Clicked) {
					backHighlighted = false;
					backClicked = false;
					arrowL1Highlighted = false;
					arrowL1Clicked = false;
					arrowL2Highlighted = false;
					arrowL2Clicked = false;
					arrowR1Highlighted = false;
					arrowR1Clicked = false;
					arrowR2Highlighted = false;
					arrowR2Clicked = false;
					set1Highlighted = false;
					set1Clicked = false;
					set2Highlighted = false;
					set2Clicked = false;
					trackPlayButton1Highlighted = false;
					trackPlayButton1Clicked = false;
					trackPlayButton2Highlighted = false;
					trackPlayButton2Clicked = false;
				}
				if(Game.enterButtonPushedDown && !Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -11:
						g.drawImage(setTitleSelectedClicked,Game.WIDTH  + 18 -7, 358 -7,null);
						g.drawImage(setTitleClicked,Game.WIDTH  + 18, 358,null);
						break;
					case -10:
						g.drawImage(setTitleSelectedClicked,Game.WIDTH  + 18 -7, 158 -7,null);
						g.drawImage(setTitleClicked,Game.WIDTH  + 18, 158,null);
						break;
					case -9:
//						g.drawImage(arrowSelectedClicked,Game.WIDTH - 129 -7, 350 -7,null);
						g.drawImage(trackStopButtonTitleClicked,280, 358,null);
						break;
					case -8:
//						g.drawImage(arrowSelectedClicked,Game.WIDTH - 129 -7, 350 -7,null);
						g.drawImage(trackStopButtonTitleClicked,280, 158,null);
						break;
					case -7:
//						g.drawImage(this.playbuttonplayButtonSelectedClicked,Game.WIDTH - 129 -7, 350 -7,null);
						if(Game.trackCurrentlyPlaying && Game.menuTrackCurrentlyPlaying == Game.menuTrackPosition && !trackController.gameTrackPaused)
							g.drawImage(trackPauseButtonTitleClicked,250, 358,null);
						else
							g.drawImage(trackPlayButtonTitleClicked,250, 358,null);
						break;
					case -6:
//						g.drawImage(this.playbuttonplayButtonSelectedClicked,Game.WIDTH - 129 -7, 350 -7,null);
						if(Game.trackCurrentlyPlaying && Game.gameTrackCurrentlyPlaying == Game.gameTrackPosition && !trackController.gameTrackPaused)
							g.drawImage(trackPauseButtonTitleClicked,250, 158,null);
						else
							g.drawImage(trackPlayButtonTitleClicked,250, 158,null);
						break;
					case -5:
//						g.drawImage(arrowSelectedClicked,Game.WIDTH - 129 -7, 350 -7,null);
						g.drawImage(arrowRClicked,Game.WIDTH - 129, 350,null);
						break;
					case -4:
//						g.drawImage(arrowSelectedClicked,Game.WIDTH - 129 -7, 150 -7,null);
						g.drawImage(arrowRClicked,Game.WIDTH - 129, 150,null);
						break;
					case -3:
//						g.drawImage(arrowSelectedClicked,Game.WIDTH  - 129 - 40 - 16 -7, 350 -7,null);
						g.drawImage(arrowLClicked,Game.WIDTH  - 129 - 40 - 16, 350,null);
						break;
					case -2:
//						g.drawImage(arrowSelectedClicked,Game.WIDTH  - 129 - 40 - 16 -7, 150 -7,null);
						g.drawImage(arrowLClicked,Game.WIDTH  - 129 - 40 - 16, 150,null);
						break;
					case -1:
						g.drawImage(backTitleSelectedClicked,40 -7, 20 -7,null);
						g.drawImage(backButtonTitleClicked,40, 20,null);
						break;
					}
				}
				else if(Game.enterButtonPushedDown && Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -11:
						g.drawImage(setTitleSelectedNormal,Game.WIDTH  + 18 -7, 358 -7,null);
						g.drawImage(setTitle,Game.WIDTH  + 18, 358,null);
						break;
					case -10:
						g.drawImage(setTitleSelectedNormal,Game.WIDTH  + 18 -7, 158 -7,null);
						g.drawImage(setTitle,Game.WIDTH  + 18, 158,null);
						break;
					case -9:
						g.drawImage(dotSelectorPositionWhite,280+6,350,null);
						break;
					case -8:
						g.drawImage(dotSelectorPositionWhite,280+6,150,null);
						break;
					case -7:
						if(Game.trackCurrentlyPlaying && Game.menuTrackCurrentlyPlaying == Game.menuTrackPosition && !trackController.gameTrackPaused)
							g.drawImage(dotSelectorPositionWhite,250+6,350,null);
						else
							g.drawImage(dotSelectorPositionWhite,250+2,350,null);
						break;
					case -6:
						if(Game.trackCurrentlyPlaying && Game.gameTrackCurrentlyPlaying == Game.gameTrackPosition && !trackController.gameTrackPaused)
							g.drawImage(dotSelectorPositionWhite,250+6,150,null);
						else
							g.drawImage(dotSelectorPositionWhite,250+2,150,null);
						break;
					case -5:
						g.drawImage(dotSelectorPositionOrange,Game.WIDTH - 129+2,340,null);
						break;
					case -4:
						g.drawImage(dotSelectorPositionOrange,Game.WIDTH - 129+2,140,null);
						break;
					case -3:
						g.drawImage(dotSelectorPositionOrange,Game.WIDTH  - 129 - 40 - 16+10,340,null);
						break;
					case -2:
						g.drawImage(dotSelectorPositionOrange,Game.WIDTH  - 129 - 40 - 16+10,140,null);
						break;
					case -1:
						g.drawImage(backTitleSelectedNormal,40 -7, 20 -7,null);
						g.drawImage(backButtonTitle,40, 20,null);
						break;
					}
				}
				else {
					switch(Game.selectorButtonPosition) {
					case -11:
						g.drawImage(setTitleSelected,Game.WIDTH  + 18 -7, 358 -7,null);
						g.drawImage(setTitleGlow,Game.WIDTH  + 18, 358,null);
						break;
					case -10:
						g.drawImage(setTitleSelected,Game.WIDTH  + 18 -7, 158 -7,null);
						g.drawImage(setTitleGlow,Game.WIDTH  + 18, 158,null);
						break;
					case -9:
						g.drawImage(trackStopButtonTitleGlow,280, 358,null);
						break;
					case -8:
						g.drawImage(trackStopButtonTitleGlow,280, 158,null);
						break;
					case -7:
						if(Game.trackCurrentlyPlaying && Game.menuTrackCurrentlyPlaying == Game.menuTrackPosition && !trackController.gameTrackPaused)
							g.drawImage(trackPauseButtonTitleGlow,250, 358,null);
						else
							g.drawImage(trackPlayButtonTitleGlow,250, 358,null);
						break;
					case -6:
						if(Game.trackCurrentlyPlaying && Game.gameTrackCurrentlyPlaying == Game.gameTrackPosition && !trackController.gameTrackPaused)
							g.drawImage(trackPauseButtonTitleGlow,250, 158,null);
						else
							g.drawImage(trackPlayButtonTitleGlow,250, 158,null);
						break;
					case -5:
						g.drawImage(arrowRGlow,Game.WIDTH - 129, 350,null);
						break;
					case -4:
						g.drawImage(arrowRGlow,Game.WIDTH - 129, 150,null);
						break;
					case -3:
//						g.drawImage(arrowSelectedClicked,Game.WIDTH  - 129 - 40 - 16 -7, 350 -7,null);
						g.drawImage(arrowLGlow,Game.WIDTH  - 129 - 40 - 16, 350,null);
						break;
					case -2:
//						g.drawImage(arrowSelectedClicked,Game.WIDTH  - 129 - 40 - 16 -7, 150 -7,null);
						g.drawImage(arrowLGlow,Game.WIDTH  - 129 - 40 - 16, 150,null);
						break;
					case -1:
						g.drawImage(backTitleSelected,40 -7, 20 -7,null);
						g.drawImage(backButtonTitleGlow,40, 20,null);
						break;
					}
				}
			}
		}
		else if(State == STATE.RESET) {
			if(gameOverSoundLoop.clipIsActive())
				gameOverSoundLoop.stop();
			if(gameOverWinningSoundLoop.clipIsActive())
				gameOverWinningSoundLoop.stop();
			if(marioStarSoundLoop.clipIsActive())
				marioStarSoundLoop.stop();
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
			this.marioStarSoundLoop.setSoundLoopBoolean(false);
			this.marioStarSoundLoop.setFramePosition(0);
			this.gameOverSoundBoolean = false;
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
			this.trackCurrentlyPlaying = false;
			this.menuMusicStopped = false;
			this.menuSoundSet = false;
			this.soundTimerSet = false;
			this.soundFXisPlaying = false;
			this.animationTimer1 = 0;
			this.enemyHitPauseTimer = 0;
			this.marioLetsGoPauseTimer = 0;
			this.marioLetsGoPause = false;
			this.pauseSoundFXTimer = 0;
			this.visualPauseTimer = 0;
			this.slowDownForAnalogTimer = 0;
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
			this.pauseHoldOff = false;
			this.windowMinimized = false;
			this.julian = 0;
			this.juliann = 0;
			this.julianii = 0;
			this.fireworksShot = 0;
			this.spawnItem = false;
			this.enemyHitPauseTimer = 0;
			this.backgroundTraverse = 0;
			this.itemWaitTimer = 0;
			this.itemFlyingTimer1 = 0;
			this.itemFlyingAwayX = 0;
			this.itemFlyingAwayY = 0;
			this.traverseTime = 0;
			this.marioTurningWithItem.setCount(0);
			this.currentItem = null;
			this.currentItemImg = null;
			this.itemName = null;
			this.itemTransitionSetup = false;
			
			hud.reset();
			playerName.clear();
			if(scoreImage != null)
				scoreImage.flush();
			scoreImage = null;
			gamepadLetterImage = null;
			gamepadKeyboardLetterPosition = 0;
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
			traverseTime = 0;
			traverseTime2 = 0;
			waitToPause = 0;
			bowserBulletBillSpawningTimer = 0;
			spawnBulletBill = false;
			this.youWon = false;
			this.gameOverIrisSoundLoop.setFramePosition(0);
			this.gameOverIrisSoundLoop.setSoundLoopBoolean(false);
			this.marioSpinningSoundLoop.setFramePosition(0);
			this.marioSpinningSoundLoop.setSoundLoopBoolean(false);
			this.marioGrowthPosePause = false;
			this.marioGrowthPosePauseTimer = 0;
			this.marioDancePosePause = false;
			this.marioDancePosePauseTimer = 0;
			this.getHUD().resetScore();
			this.getHUD().HEALTH = 0;
			this.getHUD().setTimer1(100);
			this.getHUD().setTimer2(100);
			p.setX(Game.WIDTH);
			p.setY((Game.HEIGHT * SCALE) - MARIO_HEIGHT);
			p.setVelX(0);
			p.setVelY(0);
			p.gameOver = false;
			p.playerWinSetup = false;
			p.playerWinTimer = 0;
			p.setMarioInvincible(false);
			p.setPlayerDeathSetup(false);
			p.setDanceProgressionCount(0);
			p.setDancingInProgress(false);
			p.setGrowingAnimationFinished(false);
			p.setSpinningAnimationFinished(false);
			p.setDancingAnimationFinished(false);
			p.setTurningAroundAnimationFinished(false);
			p.marioEntranceDancingAnim.nextFrame();
			p.marioEntranceDancingAnim.setCount(0);
			p.marioEntranceGrowingAnim.nextFrame();
			p.marioEntranceGrowingAnim.setCount(0);
			p.marioEntranceSpinningAnim.nextFrame();
			p.marioEntranceSpinningAnim.setCount(0);
			p.marioEntranceTurningAroundAnim.nextFrame();
			p.marioEntranceTurningAroundAnim.setCount(0);
			p.marioDeathAnim.nextFrame();
			p.marioDeathAnim.setCount(0);
			p.setRunningStartL(false);
			p.setRunningStartR(false);
			p.setRunningStartUp(0);
			p.setAnimationTimer1(0);
			p.setTurnAroundTimer(0);
			p.setTurnAroundTimerAnimation(0);
			p.setSpinningTimer(0);
			p.setFirstTimeAnimationRun(false);
			p.setMarioGravityTimer(0);
			p.setMarioDeathTimer1(0);
			p.setMarioDeathTimer2(0);
			p.setMarioDeathTimer3(0);
			p.setTraverseTime(0);
			p.setTimer1(100);
			p.setTimer2(0);
			bb.reset();
//			if(clientSocket != null) {
//				clientSocket.close();
//				clientSocket = null;
//			}if(out != null) {
//				out.close();
//				out = null;
//			}if(in != null) {
//				in.close();
//				in = null;
//			}
			leaderboard.setInitalized(false);
			if(!leaderboard.globalList) {
				leaderboard.getNames().clear();
				leaderboard.getScores().clear();
//				if(clientSocket != null) {
//					clientSocket.close();
//					clientSocket = null;
//				}if(out != null) {
//					out.close();
//					out = null;
//				}if(in != null) {
//					in.close();
//					in = null;
//				}
			}
			leaderboard.setNameDecoy("");
			leaderboard.setScoreDecoy("");
			leaderboard.setStringDecoy("");
			sceneAcknowledgement = false;
			paused = false;
			bowserSpawnSetupBoolean = false;
			bowserSpawnSetup = 0;
			sceneSkipCount = 0;
			askToSkipSequence = false;
			c.reset();
			//tex.flush();
			/*
			tex = new Textures(this);
			bb = new BasicBlocks(tex,this);												//BLOCKS
			c = new Controller(tex, this);
			p = new Player(Game.WIDTH,(Game.HEIGHT * SCALE) - MARIO_HEIGHT,tex,this,c);
			hud = new HUD(tex,this);
			menu = new Menu();
			*/
			ea = c.getEntityA();
			eb = c.getEntityB();
			ec = c.getEntityC();
			ed = c.getEntityD();
			ee = c.getEntityE();
			ef = c.getEntityF();
	        System.gc(); 
			if(!Game.dontStartOver) {
				LeaderboardController.gameUnlocksToSettings();
				leaderboard.settingsSetup();
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
		if(connectingToServer || gameControllerInUse)
			return;
		if(!this.hasFocus())
			return;
		int key = e.getKeyCode();
		if(State != STATE.SET_SCORE && ControlsController.buttonChangeTimer < System.currentTimeMillis()) {
			/*
			if(key == KeyEvent.VK_W && upKey != KeyEvent.VK_W)
				key = -1;
			else if(key == KeyEvent.VK_S && downKey != KeyEvent.VK_S)
				key = -1;
			else if(key == KeyEvent.VK_A && leftKey != KeyEvent.VK_A)
				key = -1;
			else if(key == KeyEvent.VK_D && rightKey != KeyEvent.VK_D)
				key = -1;
			else if(key == KeyEvent.VK_SPACE && shootKey != KeyEvent.VK_SPACE)
				key = -1;
			else if(key == KeyEvent.VK_E && itemKey != KeyEvent.VK_E)
				key = -1;
			else if(key == KeyEvent.VK_ENTER && pauseKey != KeyEvent.VK_ENTER)
				key = -1;
			else if(key == KeyEvent.VK_ESCAPE && cancelKey != KeyEvent.VK_ESCAPE)
				key = -1;
			*/
			if(key == upKey) {
				key = KeyEvent.VK_W;
			}else if(key == downKey) {
				key = KeyEvent.VK_S;
			}else if(key == leftKey) {
				key = KeyEvent.VK_A;
			}else if(key == rightKey) {
				key = KeyEvent.VK_D;
			}else if(key == shootKey) {
				key = KeyEvent.VK_SPACE;
			}else if(key == itemKey) {
				key = KeyEvent.VK_E;
			//}else if(key == pauseKey && State == STATE.GAME && !userHasPaused && !paused) {
				//key = KeyEvent.VK_P;
			}else if(key == pauseKey) {
				key = KeyEvent.VK_ENTER;
			}else if(key == cancelKey) {
				key = KeyEvent.VK_ESCAPE;
			}else if(key == KeyEvent.VK_W || key == KeyEvent.VK_S || key == KeyEvent.VK_A || key == KeyEvent.VK_D ||
					key == KeyEvent.VK_SPACE || key == KeyEvent.VK_E || key == KeyEvent.VK_ENTER || 
					key == KeyEvent.VK_ESCAPE)
				key = -1;
		}
		if(State == STATE.GAME && !(Game.userHasPaused) && !(System.currentTimeMillis() < pauseSoundFXTimer) ){
			if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
				//p.setVelY(-5);
				//yUBoolean = true;
			} else if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
				p.setRunningStartR(false);
				p.setRunningStartL(true);
				if(p.getVelX() == 0 || (slowingDownActivatedl || slowingDownActivatedr))
					p.setRunningStartUp(0);
				//if(runningStartUpTimer != System.currentTimeMillis()) {
					/*
					if(runningStartUp >= 0)
						runningStartUp = -0.666;
					if(runningStartUp >= -2)
						runningStartUp -= 1;
					else if(runningStartUp <= -2 && runningStartUp >= -3)
						runningStartUp -= 0.09;
					else if(runningStartUp <= -3 && runningStartUp >= -4)
						runningStartUp -= 0.14;
					else
						runningStartUp -= 0.02;
					if(runningStartUp < -5)
						runningStartUp = -5;
						*/
				/*
					if(runningStartUp >= 0) {
						p.animl.setSpeed(10);
						runningStartUp = -1.2;
					}
					if((runningStartUp <= -3 && runningStartUp >= -5)) {
						p.animl.setSpeed(8);
						runningStartUp -= 0.33;
					}
					else
						runningStartUp -= 0.13;
					if(runningStartUp <= -5) {
						p.animl.setSpeed(6);
						runningStartUp = -5;
					}
					runningStartUpTimer = System.currentTimeMillis();
				}*/
				//System.out.println(runningStartUp);
				//p.setVelX(runningStartUp);
				xLBoolean = true;
				slowingDownTimerLong = 0;
				slowingDownActivatedl = false;
				slowingDownActivatedr = false;
				if(runningTimerActivatedResponse == false)
					runningTimerActivated = true;
				if(p.getVelX() <= -5)/*Slide after change*/
					slowDownForAnalogTimer = System.currentTimeMillis() + 200;/*Slide after change*/
			} else if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
				//p.setVelY(5);
				//yDBoolean = true;
			} else if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
				p.setRunningStartR(true);
				p.setRunningStartL(false);
				if(p.getVelX() == 0 || (slowingDownActivatedl || slowingDownActivatedr))
					p.setRunningStartUp(0);
				/*
				if(runningStartUpTimer != System.currentTimeMillis()) {
					if(runningStartUp <= 0) {
						p.animl.setSpeed(10);
						runningStartUp = 1.2;
					}
					if((runningStartUp >= 3 && runningStartUp <= 5)) {
						p.animl.setSpeed(8);
						runningStartUp += 0.33;
					}
					else
						runningStartUp += 0.13;
					if(runningStartUp >= 5) {
						p.animl.setSpeed(6);
						runningStartUp = 5;
					}
					runningStartUpTimer = System.currentTimeMillis();
				}*/
				//p.setVelX(runningStartUp);
				xRBoolean = true;
				slowingDownTimerLong = 0;
				slowingDownActivatedl = false;
				slowingDownActivatedr = false;
				if(runningTimerActivatedResponse == false)
					runningTimerActivated = true;
				if(5 <= p.getVelX())/*Slide after change*/
					slowDownForAnalogTimer = System.currentTimeMillis() + 200;/*Slide after change*/
			}
			if (key == KeyEvent.VK_SPACE && !isShooting){											//Fireballs
				isShooting = true;
				if(ea.isEmpty() && !paused){
					switch(currentlySelectedFireball) {
						case 0:
							c.addEntity(new Fireball(p.getX(),p.getY() + 32,tex, this));
							break;
						case 1:
							c.addEntity(new GreenShellFireball(p.getX(),p.getY()+32,tex,p.getVelX(),this));
							break;
						case 2:
							c.addEntity(new RedShellFireball(p.getX(),p.getY()+32,tex,p.getVelX(),this));
							break;
						case 3:
							c.addEntity(new BuzzyBeetleShellFireball(p.getX(),p.getY()+32,tex,p.getVelX(),this));
							break;
						case 4:
							c.addEntity(new GloveFireball(p.getX(),p.getY()+32,tex,this));
							break;
						case 5:
							c.addEntity(new ContraFireball(p.getX(),p.getY()+32,tex,this));
							break;
						default:
							break;
					}
					animationTimer1 = 10;
					numberOfFireBallsShot++;
					if(fireballSFX.clipIsActive()) {
						fireballSFX.stop();
						fireballSFX.setFramePosition(0);
					}
					fireballSFX.play();
				}
			}
			if(key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_P || key == KeyEvent.VK_ENTER){
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
					if(System.currentTimeMillis() < bowserSpawnSetup && bowserSpawnSetupBoolean == true) {
						bowserSpawnSetup = 0;
						bowserSpawnSetupBoolean = false;
					}
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
				Game.keysAreInUse = true;
			}
		}
		else if(State == STATE.SET_SCORE) {
			if(Game.keysAreInUse) {
				/*
				if(key == KeyEvent.VK_W && upKey != KeyEvent.VK_W)
					key = -1;
				else if(key == KeyEvent.VK_S && downKey != KeyEvent.VK_S)
					key = -1;
				else if(key == KeyEvent.VK_A && leftKey != KeyEvent.VK_A)
					key = -1;
				else if(key == KeyEvent.VK_D && rightKey != KeyEvent.VK_D)
					key = -1;
				else if(key == KeyEvent.VK_SPACE && shootKey != KeyEvent.VK_SPACE)
					key = -1;
				else if(key == KeyEvent.VK_E && itemKey != KeyEvent.VK_E)
					key = -1;
				else if(key == KeyEvent.VK_ENTER && pauseKey != KeyEvent.VK_ENTER)
					key = -1;
				else if(key == KeyEvent.VK_ESCAPE && cancelKey != KeyEvent.VK_ESCAPE)
					key = -1;
				*/
				if(key == upKey) {
					key = KeyEvent.VK_W;
				}else if(key == downKey) {
					key = KeyEvent.VK_S;
				}else if(key == leftKey) {
					key = KeyEvent.VK_A;
				}else if(key == rightKey) {
					key = KeyEvent.VK_D;
				}else if(key == shootKey) {
					key = KeyEvent.VK_SPACE;
				}else if(key == itemKey) {
					key = KeyEvent.VK_E;
				//}else if(key == pauseKey && State == STATE.GAME && !userHasPaused && !paused) {
					//key = KeyEvent.VK_P;
				}else if(key == pauseKey) {
					key = KeyEvent.VK_ENTER;
				}else if(key == cancelKey) {
					key = KeyEvent.VK_ESCAPE;
				}else if(key == KeyEvent.VK_W || key == KeyEvent.VK_S || key == KeyEvent.VK_A || key == KeyEvent.VK_D ||
						key == KeyEvent.VK_SPACE || key == KeyEvent.VK_E || key == KeyEvent.VK_ENTER || 
						key == KeyEvent.VK_ESCAPE)
					key = -1;
				switch(key) {
					case KeyEvent.VK_ENTER: case KeyEvent.VK_SPACE:
						if(!Game.keysAreInUse) {
							Game.keysAreInUse = true;
							break;
						}else {
							if(Game.selectorButtonPosition == 2 && !Game.enterButtonPushedDown) {
								if(Game.smb3CheckmarkSoundLoop.clipIsActive())
									Game.smb3CheckmarkSoundLoop.stop();
								if(Game.smb3Checkmark2SoundLoop.clipIsActive())
									Game.smb3Checkmark2SoundLoop.stop();
								Game.smb3CheckmarkSoundLoop.play();
							}
							Game.enterButtonPushedDown = true;
							break;
						}
					case KeyEvent.VK_ESCAPE:
						if(Game.mouseIsClickedDown) {
							if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.backHighlighted ||
									Game.submitHighlighted))
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.backClicked = false;
							Game.submitClicked = false;
							Game.settingsClicked = false;
							Game.leaderboardClicked = false;
							Game.escapePressedNegateAction = true;
							break;
						}
						if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
						break;
					case KeyEvent.VK_UP: case KeyEvent.VK_W:
						if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
						if(!Game.keysAreInUse) {
							Game.keysAreInUse = true;
							Game.selectorButtonPosition = -1;
							break;
						}
						if(Game.selectorButtonPosition == 1) {
							Game.keysAreInUse = false;
							Game.selectorButtonPosition--;
						}
						if(Game.selectorButtonPosition > -1)
							Game.selectorButtonPosition--;
						break;
					case KeyEvent.VK_DOWN: case KeyEvent.VK_S:
						if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
						if(!Game.keysAreInUse) {
							Game.keysAreInUse = true;
							Game.selectorButtonPosition = 1;
							break;
						}
						if(Game.selectorButtonPosition == -1) {
							Game.keysAreInUse = false;
							Game.selectorButtonPosition++;
						}
						if(Game.selectorButtonPosition < 2)
							Game.selectorButtonPosition++;
						break;
					default:
						break;
				}
			}
			else {
				switch(key) {
					case KeyEvent.VK_SHIFT:
						shiftOn = true;
						break;
					case KeyEvent.VK_A:
						if(postLetter == '=')
							postLetter = 'A';
						break;
					case KeyEvent.VK_B:
						if(postLetter == '=')
							postLetter = 'B';
						break;
					case KeyEvent.VK_C:
						if(postLetter == '=')
							postLetter = 'C';
						break;
					case KeyEvent.VK_D:
						if(postLetter == '=')
							postLetter = 'D';
						break;
					case KeyEvent.VK_E:
						if(postLetter == '=')
							postLetter = 'E';
						break;
					case KeyEvent.VK_F:
						if(postLetter == '=')
							postLetter = 'F';
						break;
					case KeyEvent.VK_G:
						if(postLetter == '=')
							postLetter = 'G';
						break;
					case KeyEvent.VK_H:
						if(postLetter == '=')
							postLetter = 'H';
						break;
					case KeyEvent.VK_I:
						if(postLetter == '=')
							postLetter = 'I';
						break;
					case KeyEvent.VK_J:
						if(postLetter == '=')
							postLetter = 'J';
						break;
					case KeyEvent.VK_K:
						if(postLetter == '=')
							postLetter = 'K';
						break;
					case KeyEvent.VK_L:
						if(postLetter == '=')
							postLetter = 'L';
						break;
					case KeyEvent.VK_M:
						if(postLetter == '=')
							postLetter = 'M';
						break;
					case KeyEvent.VK_N:
						if(postLetter == '=')
							postLetter = 'N';
						break;
					case KeyEvent.VK_O:
						if(postLetter == '=')
							postLetter = 'O';
						break;
					case KeyEvent.VK_P:
						if(postLetter == '=')
							postLetter = 'P';
						break;
					case KeyEvent.VK_Q:
						if(postLetter == '=')
							postLetter = 'Q';
						break;
					case KeyEvent.VK_R:
						if(postLetter == '=')
							postLetter = 'R';
						break;
					case KeyEvent.VK_S:
						if(postLetter == '=')
							postLetter = 'S';
						break;
					case KeyEvent.VK_T:
						if(postLetter == '=')
							postLetter = 'T';
						break;
					case KeyEvent.VK_U:
						if(postLetter == '=')
							postLetter = 'U';
						break;
					case KeyEvent.VK_V:
						if(postLetter == '=')
							postLetter = 'V';
						break;
					case KeyEvent.VK_W:
						if(postLetter == '=')
							postLetter = 'W';
						break;
					case KeyEvent.VK_X:
						if(postLetter == '=')
							postLetter = 'X';
						break;
					case KeyEvent.VK_Y:
						if(postLetter == '=')
							postLetter = 'Y';
						break;
					case KeyEvent.VK_Z:
						if(postLetter == '=')
							postLetter = 'Z';
						break;
					case KeyEvent.VK_1:
						if(postLetter == '=') {
							if(shiftOn)
								postLetter = '!';
							else
								postLetter = '1';
							}
						break;
					case KeyEvent.VK_2:
						if(postLetter == '=')
							postLetter = '2';
						break;
					case KeyEvent.VK_3:
						if(postLetter == '=')
							postLetter = '3';
						break;
					case KeyEvent.VK_4:
						if(postLetter == '=')
							postLetter = '4';
						break;
					case KeyEvent.VK_5:
						if(postLetter == '=')
							postLetter = '5';
						break;
					case KeyEvent.VK_6:
						if(postLetter == '=')
							postLetter = '6';
						break;
					case KeyEvent.VK_7:
						if(postLetter == '=')
							postLetter = '7';
						break;
					case KeyEvent.VK_8:
						if(postLetter == '=')
							postLetter = '8';
						break;
					case KeyEvent.VK_9:
						if(postLetter == '=')
							postLetter = '9';
						break;
					case KeyEvent.VK_0:
						if(postLetter == '=')
							postLetter = '0';
						break;
					case KeyEvent.VK_PERIOD:
						if(postLetter == '=')
							postLetter = '.';
						break;
					case KeyEvent.VK_BACK_QUOTE: case KeyEvent.VK_QUOTE:
						if(postLetter == '=')
							postLetter = '\'';
						break;
					case KeyEvent.VK_EXCLAMATION_MARK:
						if(postLetter == '=')
							postLetter = '!';
						break;
					case KeyEvent.VK_SPACE:
						if(postLetter == '=')
							postLetter = ' ';
						break;
					case KeyEvent.VK_BACK_SPACE:
						if(postLetter == '=')
							postLetter = '+';
						break;
					case KeyEvent.VK_ENTER:
						Game.enterButtonPushedDown = true;
						break;
					case KeyEvent.VK_ESCAPE:
						if(Game.mouseIsClickedDown) {
							if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.backHighlighted ||
									Game.submitHighlighted))
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.backClicked = false;
							Game.submitClicked = false;
							Game.settingsClicked = false;
							Game.leaderboardClicked = false;
							Game.escapePressedNegateAction = true;
							break;
						}
						if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
						break;
					case KeyEvent.VK_UP:
						if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
						if(!Game.keysAreInUse) {
							Game.keysAreInUse = true;
							Game.selectorButtonPosition = -1;
							break;
						}
						if(Game.selectorButtonPosition == 1) {
							Game.keysAreInUse = false;
							Game.selectorButtonPosition--;
						}
						if(Game.selectorButtonPosition > -1)
							Game.selectorButtonPosition--;
						break;
					case KeyEvent.VK_DOWN:
						if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
						if(!Game.keysAreInUse) {
							Game.keysAreInUse = true;
							Game.selectorButtonPosition = 1;
							break;
						}
						if(Game.selectorButtonPosition == -1) {
							Game.keysAreInUse = false;
							Game.selectorButtonPosition++;
						}
						if(Game.selectorButtonPosition < 2)
							Game.selectorButtonPosition++;
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
				case KeyEvent.VK_BACK_SPACE:
					Game.keysAreInUse = true;
					break;
				case KeyEvent.VK_ENTER:  case KeyEvent.VK_SPACE:
					if(!Game.keysAreInUse) {
						break;
					}else {
						Game.enterButtonPushedDown = true;
						break;
					}
				case KeyEvent.VK_ESCAPE:
					if(Game.mouseIsClickedDown) {
						if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.playHighlighted ||
								Game.shopHighlighted || Game.exitHighlighted || Game.helpHighlighted ||
								Game.settingsHighlighted || Game.leaderboardHighlighted))
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.playClicked = false;
						Game.shopClicked = false;
						Game.exitClicked = false;
						Game.helpClicked = false;
						Game.settingsClicked = false;
						Game.leaderboardClicked = false;
						Game.escapePressedNegateAction = true;
						break;
					}
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					break;
				default:
					break;
			}
		}else if(State == STATE.TRANSITION_ENTRANCE || State == STATE.TRANSITION_ITEM || State == STATE.TRANSITION_WIN ||
				State == STATE.CREDITS) {
			switch(key) {
			case KeyEvent.VK_W: case KeyEvent.VK_UP:
			case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
			case KeyEvent.VK_S: case KeyEvent.VK_DOWN:
			case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				break;
				/*
			case KeyEvent.VK_ESCAPE:
				if(Game.mouseIsClickedDown) {
					if(!Game.mouseIsOffClickedObjectAndHeldDown && Game.skipHighlighted)
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.skipClicked = false;
					Game.escapePressedNegateAction = true;
					break;
				}
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					sceneAcknowledgement = true;
					break;
				}
				else {
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				//else if(Game.keysAreInUse)
					//Game.keysAreInUse = false;
				//else
					//Game.keysAreInUse = true;
				}
				break;*/
			case KeyEvent.VK_ENTER: case KeyEvent.VK_SPACE:
				if(Game.escapePressedNegateAction == false)
					Game.enterButtonPushedDown = true;
				if(Game.askToSkipSequence) {
					if(!Game.keysAreInUse) {
						Game.keysAreInUse = true;
					}
					sceneAcknowledgement = true;
					Game.enterButtonPushedDown = true;
				}
				break;
			default:
				//Game.keysAreInUse = true;
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
			case KeyEvent.VK_BACK_SPACE:
				Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_ENTER:  case KeyEvent.VK_SPACE:
				if(!Game.keysAreInUse) {
//					Game.keysAreInUse = true;
					break;
				}else {
					Game.enterButtonPushedDown = true;
					break;
				}
			case KeyEvent.VK_ESCAPE:
				if(Game.mouseIsClickedDown) {
					if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.setScoreHighlighted ||
							Game.playHighlighted || Game.exitHighlighted || Game.homeHighlighted|| 
							Game.leaderboardHighlighted))
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.playClicked = false;
					Game.homeClicked = false;
					Game.exitClicked = false;
					Game.setScoreClicked = false;
					Game.leaderboardClicked = false;
					Game.escapePressedNegateAction = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				//else if(Game.keysAreInUse)
					//Game.keysAreInUse = false;
				//else
					//Game.keysAreInUse = true;
				break;
			default:
				break;
			}
		}else if(State == STATE.GAME && (Game.userHasPaused || System.currentTimeMillis() < pauseSoundFXTimer) ){
			switch(key) {
			case KeyEvent.VK_SHIFT:
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
			case KeyEvent.VK_ENTER:  case KeyEvent.VK_SPACE:
				if(!Game.keysAreInUse) {
					Game.enterButtonPushedDown = true;
					Game.keysAreInUse = true;
					break;
				}else {
					Game.enterButtonPushedDown = true;
					break;
				}
			case KeyEvent.VK_ESCAPE: case KeyEvent.VK_P:
				if(Game.mouseIsClickedDown && key != KeyEvent.VK_P) {
					if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.resumeHighlighted ||
							Game.homeHighlighted || Game.exitHighlighted))
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.resumeClicked = false;
					Game.homeClicked = false;
					Game.exitClicked = false;
					Game.escapePressedNegateAction = true;
					break;
				}
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
    		int selector = Game.selectorButtonPosition;
			switch(key) {
			case KeyEvent.VK_W: case KeyEvent.VK_UP:
				if(!Game.keysAreInUse) {
					if(areYouSureBoolean)
						Game.selectorButtonPosition = 0;
					Game.keysAreInUse = true;
					break;
				}
				if(areYouSureBoolean) {}
				else {
					if(Game.selectorButtonPosition == 0)
						Game.selectorButtonPosition = -1;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				break;
			case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
				if(!Game.keysAreInUse) {
					if(areYouSureBoolean)
						Game.selectorButtonPosition = 0;
					Game.keysAreInUse = true;
					break;
				}
				
				if(areYouSureBoolean) {
					if(Game.selectorButtonPosition != 0)
						Game.selectorButtonPosition = 0;
				}
				else {
					if(Game.selectorButtonPosition == 1)
						Game.selectorButtonPosition = 0;
					else if(Game.selectorButtonPosition == 0)
						Game.selectorButtonPosition = -1;
				}
				
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				break;
			case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
				if(!Game.keysAreInUse) {
					if(areYouSureBoolean)
						Game.selectorButtonPosition = 0;
					Game.keysAreInUse = true;
					break;
				}

				if(areYouSureBoolean) {
					if(Game.selectorButtonPosition != 1)
						Game.selectorButtonPosition = 1;
				}
				else {
					if(Game.selectorButtonPosition == -1 || Game.selectorButtonPosition == 0)
						Game.selectorButtonPosition = 1;
				}
				
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				break;
			case KeyEvent.VK_S: case KeyEvent.VK_DOWN:
				if(!Game.keysAreInUse) {
					if(areYouSureBoolean)
						Game.selectorButtonPosition = 0;
					Game.keysAreInUse = true;
					break;
				}
				if(areYouSureBoolean) {}
				else {
					if(Game.selectorButtonPosition == -1)
						Game.selectorButtonPosition = 0;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				break;
			case KeyEvent.VK_ESCAPE:
				if(Game.mouseIsClickedDown) {
					if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.backHighlighted ||
							Game.localHighlighted || Game.globalHighlighted || Game.uploadHighlighted))
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.backClicked = false;
					Game.globalClicked = false;
					Game.uploadClicked = false;
					Game.localClicked = false;
					Game.escapePressedNegateAction = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				break;
			case KeyEvent.VK_ENTER:  case KeyEvent.VK_SPACE:
				if(!Game.keysAreInUse) {
					if(areYouSureBoolean)
						Game.selectorButtonPosition = 0;
					Game.keysAreInUse = true;
					break;
				}else {
					Game.enterButtonPushedDown = true;
					break;
				}
			}
		}else if(State == STATE.SHOP) {
			if(Game.allUnlockedScreen)
				return;
			int selector = Game.selectorButtonPosition;
			switch(key) {
			case KeyEvent.VK_W: case KeyEvent.VK_UP:
				if(!Game.keysAreInUse) {
					if(Game.selectorButtonPosition == -2 && currentSkinLocked == false)
						Game.selectorButtonPosition = 0;
					else if(Game.selectorButtonPosition == -5 && currentTrackLocked == false)
						Game.selectorButtonPosition = 1;
					else if(Game.selectorButtonPosition == -8 && currentFireballLocked == false)
						Game.selectorButtonPosition = 2;
					else if(Game.selectorButtonPosition == -11 && currentItemLocked == false)
						Game.selectorButtonPosition = 3;
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
					if(Game.selectorButtonPosition == -2 && currentSkinLocked == false)
						Game.selectorButtonPosition = 0;
					else if(Game.selectorButtonPosition == -5 && currentTrackLocked == false)
						Game.selectorButtonPosition = 1;
					else if(Game.selectorButtonPosition == -8 && currentFireballLocked == false)
						Game.selectorButtonPosition = 2;
					else if(Game.selectorButtonPosition == -11 && currentItemLocked == false)
						Game.selectorButtonPosition = 3;
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
					if(Game.selectorButtonPosition == -2 && currentSkinLocked == false)
						Game.selectorButtonPosition = 0;
					else if(Game.selectorButtonPosition == -5 && currentTrackLocked == false)
						Game.selectorButtonPosition = 1;
					else if(Game.selectorButtonPosition == -8 && currentFireballLocked == false)
						Game.selectorButtonPosition = 2;
					else if(Game.selectorButtonPosition == -11 && currentItemLocked == false)
						Game.selectorButtonPosition = 3;
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
					if(Game.selectorButtonPosition == -2 && currentSkinLocked == false)
						Game.selectorButtonPosition = 0;
					else if(Game.selectorButtonPosition == -5 && currentTrackLocked == false)
						Game.selectorButtonPosition = 1;
					else if(Game.selectorButtonPosition == -8 && currentFireballLocked == false)
						Game.selectorButtonPosition = 2;
					else if(Game.selectorButtonPosition == -11 && currentItemLocked == false)
						Game.selectorButtonPosition = 3;
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
				if(Game.mouseIsClickedDown) {
					if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.backHighlighted ||
							Game.arrowL1Highlighted || Game.arrowR1Highlighted ||
							Game.arrowL2Highlighted || Game.arrowR2Highlighted ||
							Game.arrowL3Highlighted || Game.arrowR3Highlighted ||
							Game.arrowL4Highlighted || Game.arrowR4Highlighted ||
							Game.set1Highlighted || Game.buy1Highlighted ||
							Game.set2Highlighted || Game.buy2Highlighted ||
							Game.set3Highlighted || Game.buy3Highlighted ||
							Game.set4Highlighted || Game.buy4Highlighted))
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.backClicked = false;
					Game.arrowL1Clicked = false;
					Game.arrowR1Clicked = false;
					Game.arrowL2Clicked = false;
					Game.arrowR2Clicked = false;
					Game.arrowL3Clicked = false;
					Game.arrowR3Clicked = false;
					Game.arrowL4Clicked = false;
					Game.arrowR4Clicked = false;
					Game.set1Clicked = false;
					Game.buy1Clicked = false;
					Game.set2Clicked = false;
					Game.buy2Clicked = false;
					Game.set3Clicked = false;
					Game.buy3Clicked = false;
					Game.set4Clicked = false;
					Game.buy4Clicked = false;
					Game.escapePressedNegateAction = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				break;
			case KeyEvent.VK_ENTER:  case KeyEvent.VK_SPACE:
				if(!Game.keysAreInUse) {
					break;
				}else {
					Game.enterButtonPushedDown = true;
					break;
				}
			}
		}else if(State == STATE.HELP) {
			int selector = Game.selectorButtonPosition;
			switch(key) {
			case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
				if(!Game.keysAreInUse) {
					if(Game.selectorButtonPosition == 0 && firstTimeBeating == false)
						Game.selectorButtonPosition = -1;
					Game.keysAreInUse = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				//shit here
				if(Game.firstTimeBeating) {
					if(Game.selectorButtonPosition != -1)
						Game.selectorButtonPosition = -1;
				}
				//shit here
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
					if(Game.selectorButtonPosition == 0 && firstTimeBeating == false)
						Game.selectorButtonPosition = -1;
					Game.keysAreInUse = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				//shit here
				if(Game.firstTimeBeating) {
					if(Game.selectorButtonPosition != 0)
						Game.selectorButtonPosition = 0;
				}
				//shit here
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
				break;
			case KeyEvent.VK_W: case KeyEvent.VK_UP:
			case KeyEvent.VK_S: case KeyEvent.VK_DOWN:
				if(!Game.keysAreInUse) {
					if(Game.selectorButtonPosition == 0 && firstTimeBeating == false)
						Game.selectorButtonPosition = -1;
					Game.keysAreInUse = true;
					break;
				}
				if(Game.mouseIsClickedDown) {
					if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.backHighlighted || Game.creditsHighlighted))
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.backClicked = false;
					Game.creditsClicked = false;
					Game.escapePressedNegateAction = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				break;
			case KeyEvent.VK_ENTER:  case KeyEvent.VK_SPACE:
				if(!Game.keysAreInUse) {
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
						selectorButtonPosition = -4;
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
					else if(selectorButtonPosition == -3 || selectorButtonPosition == 2)
						selectorButtonPosition = 1;
					else if(selectorButtonPosition == -4)
						selectorButtonPosition = -1;
					else if(selectorButtonPosition == -5)
						selectorButtonPosition = -4;
						
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
					else if(selectorButtonPosition == -4 || selectorButtonPosition == -5)
						selectorButtonPosition = -2;
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
					else if(selectorButtonPosition == 1 || selectorButtonPosition == 2)
						selectorButtonPosition = -3;
					else if(selectorButtonPosition == -2 || selectorButtonPosition == -1)
						selectorButtonPosition = -4;
					else if(selectorButtonPosition == -4)
						selectorButtonPosition = -5;
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
				if(Game.mouseIsClickedDown) {
					if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.yesHighlighted || Game.noHighlighted ||
							Game.arrowL1Highlighted || Game.arrowR1Highlighted ||
							Game.arrowL2Highlighted || Game.arrowR2Highlighted ||
							Game.resetStatsHighlighted || Game.checkMarkHighlighted ||
							Game.gamepadImageHighlighted || Game.noteImageHighlighted ||
							Game.backHighlighted))
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.yesClicked = false;
					Game.noClicked = false;
					Game.arrowL1Clicked = false;
					Game.arrowR1Clicked = false;
					Game.arrowL2Clicked = false;
					Game.arrowR2Clicked = false;
					Game.resetStatsClicked = false;
					Game.checkMarkClicked = false;
					Game.gamepadImageClicked = false;
					Game.noteImageClicked = false;
					Game.backClicked = false;
					Game.escapePressedNegateAction = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				break;
			case KeyEvent.VK_ENTER:  case KeyEvent.VK_SPACE:
				if(!Game.keysAreInUse) {
					if(areYouSureBoolean)
						Game.selectorButtonPosition = 0;
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
		else if(State == STATE.CONTROLS) {
			if(System.currentTimeMillis() < ControlsController.buttonChangeTimer) {
			}
			else {
				switch(key) {
				case KeyEvent.VK_W: case KeyEvent.VK_UP:
					if(!Game.keysAreInUse) {
						Game.keysAreInUse = true;
						break;
					}
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					else {
						if(Game.selectorButtonPosition == -2 || Game.selectorButtonPosition == -10) {
							Game.selectorButtonPosition = -1;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							hudSFX.get(hudSFXPosition).play();
						}
						else if(Game.selectorButtonPosition == -18) {
							Game.selectorButtonPosition = 0;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							hudSFX.get(hudSFXPosition).play();
						}
						else if(Game.selectorButtonPosition != -1 && Game.selectorButtonPosition != 0){
							Game.selectorButtonPosition++;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							hudSFX.get(hudSFXPosition).play();
						}
					}
					break;
				case KeyEvent.VK_S: case KeyEvent.VK_DOWN:
					if(!Game.keysAreInUse) {
						Game.keysAreInUse = true;
						break;
					}
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					else {
						if(Game.selectorButtonPosition == -9 || Game.selectorButtonPosition == -17 ||
								Game.selectorButtonPosition == -25)
							break;
						else if(Game.selectorButtonPosition == 0){
							Game.selectorButtonPosition = -18;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							hudSFX.get(hudSFXPosition).play();
						}
						else if(Game.selectorButtonPosition != -26){
							Game.selectorButtonPosition--;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							hudSFX.get(hudSFXPosition).play();
						}
					}
					break;
				case KeyEvent.VK_A: case KeyEvent.VK_LEFT:
					if(!Game.keysAreInUse) {
						Game.keysAreInUse = true;
						break;
					}
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					else {
						if(Game.selectorButtonPosition == -2 || Game.selectorButtonPosition == 0) {
							Game.selectorButtonPosition = -1;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							hudSFX.get(hudSFXPosition).play();
						}
						else if(Game.selectorButtonPosition == -26){
							Game.selectorButtonPosition = -25;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							hudSFX.get(hudSFXPosition).play();
						}
						else if (Game.selectorButtonPosition < -9){
							Game.selectorButtonPosition += 8;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							hudSFX.get(hudSFXPosition).play();
						}
					}
					break;
				case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
					if(!Game.keysAreInUse) {
						Game.keysAreInUse = true;
						break;
					}
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					else {
						if(Game.selectorButtonPosition == -1) {
							Game.selectorButtonPosition = -2;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							hudSFX.get(hudSFXPosition).play();
						}
						else if(Game.selectorButtonPosition == -25){
							Game.selectorButtonPosition = -26;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							hudSFX.get(hudSFXPosition).play();
						}
						else if (Game.selectorButtonPosition > -18 && Game.selectorButtonPosition != 0){
							Game.selectorButtonPosition -= 8;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							hudSFX.get(hudSFXPosition).play();
						}
					}
					break;
				case KeyEvent.VK_ESCAPE:
					if(Game.mouseIsClickedDown) {
						if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.backHighlighted || Game.resetHighlighted))
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						else if(!Game.mouseIsOffClickedObjectAndHeldDown) {
							for(int i = 0; i <= ControlsController.gamepadButtonHolderHighlighted.length-1; i++) {
								if(ControlsController.gamepadButtonHolderHighlighted[i] == true) {
									Game.mouseIsOffClickedObjectAndHeldDown = true;
									break;
								}
							}
						}
						for(int i = 0; i <= ControlsController.gamepadButtonHolderClicked.length-1; i++) {
							ControlsController.gamepadButtonHolderClicked[i] = false;
						}
						Game.backClicked = false;
						Game.resetClicked = false;
						Game.escapePressedNegateAction = true;
						break;
					}
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					break;
				case KeyEvent.VK_ENTER:  case KeyEvent.VK_SPACE:
					if(!Game.keysAreInUse) {
						break;
					}else {
						Game.enterButtonPushedDown = true;
						break;
					}
				default:
					break;
				}
			}
		}
		else if(State == STATE.TRACKLIST) {
			int selector = Game.selectorButtonPosition;
			switch(key) {
			case KeyEvent.VK_W: case KeyEvent.VK_UP:
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				//shit here
				switch(Game.selectorButtonPosition) {
				case -11:
					Game.selectorButtonPosition = -10;
					break;
				case -9:
					Game.selectorButtonPosition = -8;
					break;
				case -7:
					Game.selectorButtonPosition = -6;
					break;
				case -5:
					Game.selectorButtonPosition = -4;
					break;
				case -3:
					Game.selectorButtonPosition = -2;
					break;
				case -2: case -4: case -6: case -8: case -10:
					Game.selectorButtonPosition = -1;
					break;
				default:
					break;
				}
				//shit here
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
				//shit here
				switch(Game.selectorButtonPosition) {
				case -10:
					Game.selectorButtonPosition = -11;
					break;
				case -8:
					Game.selectorButtonPosition = -9;
					break;
				case -6:
					Game.selectorButtonPosition = -7;
					break;
				case -4:
					Game.selectorButtonPosition = -5;
					break;
				case -2:
					Game.selectorButtonPosition = -3;
					break;
				case -1:
					Game.selectorButtonPosition = -2;
					break;
				default:
					break;
				}
				//shit here
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
				//shit here
				if(Game.selectorButtonPosition < -3)
					Game.selectorButtonPosition+=2;
				else if(Game.selectorButtonPosition != -2 && Game.selectorButtonPosition != -3)
					Game.selectorButtonPosition = -1;
				//shit here
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
				//shit here
				if(-9 <= Game.selectorButtonPosition && Game.selectorButtonPosition != -1)
					Game.selectorButtonPosition-=2;
				else if(Game.selectorButtonPosition != -10 && Game.selectorButtonPosition != -1)
					Game.selectorButtonPosition = -11;
				//shit here
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
				break;
			case KeyEvent.VK_ESCAPE:
				if(Game.mouseIsClickedDown) {
					if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.backHighlighted ||
							Game.arrowL1Highlighted || Game.arrowL2Highlighted || Game.arrowR1Highlighted ||
							Game.arrowR2Highlighted || Game.trackPauseButton1Highlighted || Game.trackPauseButton2Highlighted || 
							Game.trackPlayButton1Highlighted || Game.trackPlayButton2Highlighted || Game.set1Highlighted ||
							Game.set2Highlighted))
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.backClicked = false;
					Game.arrowL1Clicked = false;
					Game.arrowL2Clicked = false;
					Game.arrowR1Clicked = false;
					Game.arrowR2Clicked = false;
					Game.trackPauseButton1Clicked = false;
					Game.trackPauseButton2Clicked = false;
					Game.trackPlayButton1Clicked = false;
					Game.trackPlayButton2Clicked = false;
					Game.set1Clicked = false;
					Game.set2Clicked = false;
					Game.escapePressedNegateAction = true;
					break;
				}
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				break;
			case KeyEvent.VK_ENTER:  case KeyEvent.VK_SPACE:
				if(!Game.keysAreInUse) {
					break;
				}else {
					Game.enterButtonPushedDown = true;
					break;
				}
			default:
				break;
			}
		}
		if(gameControllerInUse)
			gameControllerInUse = false;
		if(gameControllerInUseDI)
			gameControllerInUseDI = false;
	}
	
	public void keyReleased(KeyEvent e){
		if(connectingToServer)
			return;
		if(gameControllerInUse && !gameControllerSwitchBack) {
			gameControllerInUse = false;
			gameControllerInUseDI = false;
			return;
		}
		int key = e.getKeyCode();
		if(State != STATE.SET_SCORE && ControlsController.buttonChangeTimer < System.currentTimeMillis()) {
			/*
			if(key == KeyEvent.VK_W && upKey != KeyEvent.VK_W)
				key = -1;
			else if(key == KeyEvent.VK_S && downKey != KeyEvent.VK_S)
				key = -1;
			else if(key == KeyEvent.VK_A && leftKey != KeyEvent.VK_A)
				key = -1;
			else if(key == KeyEvent.VK_D && rightKey != KeyEvent.VK_D)
				key = -1;
			else if(key == KeyEvent.VK_SPACE && shootKey != KeyEvent.VK_SPACE)
				key = -1;
			else if(key == KeyEvent.VK_E && itemKey != KeyEvent.VK_E)
				key = -1;
			else if(key == KeyEvent.VK_ENTER && pauseKey != KeyEvent.VK_ENTER)
				key = -1;
			else if(key == KeyEvent.VK_ESCAPE && cancelKey != KeyEvent.VK_ESCAPE)
				key = -1;
			*/
			if(key == upKey) {
				key = KeyEvent.VK_W;
			}else if(key == downKey) {
				key = KeyEvent.VK_S;
			}else if(key == leftKey) {
				key = KeyEvent.VK_A;
			}else if(key == rightKey) {
				key = KeyEvent.VK_D;
			}else if(key == shootKey) {
				key = KeyEvent.VK_SPACE;
				if(State == STATE.GAME && isShooting)
					setIsShooting(false);
			}else if(key == itemKey) {
				key = KeyEvent.VK_E;
			//}else if(key == pauseKey && State == STATE.GAME && !userHasPaused && !paused) {
				//key = KeyEvent.VK_P;
			}else if(key == pauseKey) {
				key = KeyEvent.VK_ENTER;
			}else if(key == cancelKey) {
				key = KeyEvent.VK_ESCAPE;
			}else if(key == KeyEvent.VK_W || key == KeyEvent.VK_S || key == KeyEvent.VK_A || key == KeyEvent.VK_D ||
					key == KeyEvent.VK_SPACE || key == KeyEvent.VK_E || key == KeyEvent.VK_ENTER || 
					key == KeyEvent.VK_ESCAPE)
				key = -1;
		}
		if(State == STATE.GAME && !(Game.userHasPaused) && !(System.currentTimeMillis() < pauseSoundFXTimer) ){
			if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
				/*
				p.setVelY(0);
				yUBoolean = false;
				if(yDBoolean == true)
					p.setVelY(5);
				*/
			} else if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
				xLBoolean = false;
				p.setRunningStartL(false);
				if(xRBoolean == true){
					p.setRunningStartUp(1.2);
					p.setVelX(p.getRunningStartUp());
					runningTimerActivated = true;
					p.setRunningStartR(true);
				}
				else{
					if((System.currentTimeMillis() - runningTimerLong > 666/2 || (p.getVelX() <= -5) ||
							/*Slide after change*/System.currentTimeMillis() < slowDownForAnalogTimer/*Slide after change*/
							) && p.getVelX() != 0){														//This activates sliding animation for left side
						slowingDownActivatedl = true;
						slowingDownTimerLong = System.currentTimeMillis() + 200;
						slowingDown = -1.73;
						p.setVelX(slowingDown);
						runningTimerLong = 0;
						runningTimerActivatedResponse = false;
					}
					else
						p.setVelX(0);
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
				xRBoolean = false;
				p.setRunningStartR(false);
				if(xLBoolean == true){
					p.setRunningStartUp(-1.2);
					p.setVelX(p.getRunningStartUp());
					runningTimerActivated = true;
					p.setRunningStartL(true);
				}
				else{
					if((System.currentTimeMillis() - runningTimerLong > 666/2 || (p.getVelX() >= 5) || 
							/*Slide after change*/System.currentTimeMillis() < slowDownForAnalogTimer/*Slide after change*/
							) && p.getVelX() != 0){														//This activates sliding animation for right side
						slowingDownActivatedr = true;
						slowingDownTimerLong = System.currentTimeMillis() + 200;
						slowingDown = 1.73;
						p.setVelX(slowingDown);
						runningTimerLong = 0;
						runningTimerActivatedResponse = false;
					}
					else
						p.setVelX(0);
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
						askToSkipSequence = false;
						sceneAcknowledgement = false;
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
					if(Game.mouseIsClickedDown) {
						break;
					}
					if(Game.enterButtonPushedDown)
						break;
					if(!askToSkipSequence) {
						Game.keysAreInUse = true;
						askToSkipSequence = true;
					}
					else {
						askToSkipSequence = false;
						Game.keysAreInUse = false;
						sceneAcknowledgement = false;
					}
					break;
				case KeyEvent.VK_SPACE: case KeyEvent.VK_ENTER:
						if(!askToSkipSequence) {
							askToSkipSequence = true;
							Game.keysAreInUse = true;
							break;
						}
						if(askToSkipSequence && Game.escapePressedNegateAction == false) {
							skipSequence = true;
							if(sceneSkipCount < 1)
								sceneSkipCount++;
							else {
								try {
									LeaderboardController.writeToSettings("skipAnimations", "true");
								} catch (IOException e1) {
								}
								skipAnimations = true;
							}
						}
						Game.keysAreInUse = true;
						Game.enterButtonPushedDown = false;
						Game.escapePressedNegateAction = false;
						sceneAcknowledgement = false;
					
					break;
				default:
					if(sceneAcknowledgement) {
						if(!askToSkipSequence) {
							askToSkipSequence = true;
						}
						Game.keysAreInUse = true;
						sceneAcknowledgement = false;
					}
					break;
				}
			//if(!askToSkipSequence) {
			//	askToSkipSequence = true;
			//}
			
		}else if(State == STATE.CREDITS) {
			switch(key) {
			case KeyEvent.VK_ESCAPE:
				if(Game.mouseIsClickedDown) {
					break;
				}
				if(Game.enterButtonPushedDown)
					break;
				if(!askToSkipSequence) {
					askToSkipSequence = true;
					Game.keysAreInUse = true;
				}
				else {
					askToSkipSequence = false;
					Game.keysAreInUse = false;
					sceneAcknowledgement = false;
				}
				break;
			case KeyEvent.VK_SPACE: case KeyEvent.VK_ENTER:
					if(!askToSkipSequence) {
						if(!Game.keysAreInUse) {
							Game.keysAreInUse = true;
						}
						askToSkipSequence = true;
						break;
					}
					if(askToSkipSequence && Game.escapePressedNegateAction == false) {
						skipSequence = true;
					}
					Game.keysAreInUse = true;
					Game.enterButtonPushedDown = false;
					Game.escapePressedNegateAction = false;
					sceneAcknowledgement = false;
				
				break;
			default:
				if(sceneAcknowledgement) {
					if(!askToSkipSequence) {
						askToSkipSequence = true;
					}
					Game.keysAreInUse = true;
					sceneAcknowledgement = false;
				}
				break;
			}
		//if(!askToSkipSequence) {
		//	askToSkipSequence = true;
		//}
		
		}
		else if(State == STATE.TRANSITION_ITEM) {
			if(p.getVelX() != 0)
				p.setVelX(0);
			if(xLBoolean == true)
				this.xLBoolean = false;
			if(xRBoolean == true)
				this.xRBoolean = false;
			if(slowingDownActivatedl == true)
				this.slowingDownActivatedl = false;
			if(slowingDownActivatedr == true)
				this.slowingDownActivatedr = false;
			if(slowingDown != 0)
				this.slowingDown = 0;
			if(slowingDownTimerLong != 0)
				this.slowingDownTimerLong = 0;
			if(runningTimerActivated == true)
				this.runningTimerActivated = false;
			if(runningTimerActivatedResponse == true)
				this.runningTimerActivatedResponse = false;
			if(runningTimerLong != 0)
				this.runningTimerLong = 0;
			switch(key) {
				case KeyEvent.VK_ESCAPE:
					if(Game.mouseIsClickedDown) {
						break;
					}
					if(Game.enterButtonPushedDown)
						break;
					if(!askToSkipSequence) {
						askToSkipSequence = true;
						Game.keysAreInUse = true;
					}
					else {
						askToSkipSequence = false;
						Game.keysAreInUse = false;
						sceneAcknowledgement = false;
					}
					break;
//					if(Game.mouseIsClickedDown) {
//						break;
//					}
//					if(sceneAcknowledgement) {
//						if(Game.enterButtonPushedDown)
//							break;
//						if(!askToSkipSequence) {
//							askToSkipSequence = true;
//						}
//						else {
//							askToSkipSequence = false;
//							Game.keysAreInUse = false;
//						}
//						sceneAcknowledgement = false;
//					}
//					break;
				case KeyEvent.VK_SPACE: case KeyEvent.VK_ENTER:
						if(!askToSkipSequence) {
							askToSkipSequence = true;
							break;
						}
						if(askToSkipSequence && Game.escapePressedNegateAction == false) {
							skipSequence = true;
							if(sceneSkipCount < 1)
								sceneSkipCount++;
							else {
								try {
									LeaderboardController.writeToSettings("skipAnimations", "true");
								} catch (IOException e1) {
								}
								skipAnimations = true;
							}
						}
						Game.keysAreInUse = true;
						Game.enterButtonPushedDown = false;
						Game.escapePressedNegateAction = false;
						sceneAcknowledgement = false;
					break;
				default:
					if(sceneAcknowledgement) {
						if(!askToSkipSequence) {
							askToSkipSequence = true;
						}
						Game.keysAreInUse = true;
						sceneAcknowledgement = false;
					}
					break;
			}
			//if(!askToSkipSequence) {
			//	askToSkipSequence = true;
			//}
			
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
				/*
				if(key == KeyEvent.VK_W && upKey != KeyEvent.VK_W)
					key = -1;
				else if(key == KeyEvent.VK_S && downKey != KeyEvent.VK_S)
					key = -1;
				else if(key == KeyEvent.VK_A && leftKey != KeyEvent.VK_A)
					key = -1;
				else if(key == KeyEvent.VK_D && rightKey != KeyEvent.VK_D)
					key = -1;
				else if(key == KeyEvent.VK_SPACE && shootKey != KeyEvent.VK_SPACE)
					key = -1;
				else if(key == KeyEvent.VK_E && itemKey != KeyEvent.VK_E)
					key = -1;
				else if(key == KeyEvent.VK_ENTER && pauseKey != KeyEvent.VK_ENTER)
					key = -1;
				else if(key == KeyEvent.VK_ESCAPE && cancelKey != KeyEvent.VK_ESCAPE)
					key = -1;
				*/
				if(key == upKey) {
					key = KeyEvent.VK_W;
				}else if(key == downKey) {
					key = KeyEvent.VK_S;
				}else if(key == leftKey) {
					key = KeyEvent.VK_A;
				}else if(key == rightKey) {
					key = KeyEvent.VK_D;
				}else if(key == shootKey) {
					key = KeyEvent.VK_SPACE;
				}else if(key == itemKey) {
					key = KeyEvent.VK_E;
				//}else if(key == pauseKey && State == STATE.GAME && !userHasPaused && !paused) {
					//key = KeyEvent.VK_P;
				}else if(key == pauseKey) {
					key = KeyEvent.VK_ENTER;
				}else if(key == cancelKey) {
					key = KeyEvent.VK_ESCAPE;
				}else if(key == KeyEvent.VK_W || key == KeyEvent.VK_S || key == KeyEvent.VK_A || key == KeyEvent.VK_D ||
						key == KeyEvent.VK_SPACE || key == KeyEvent.VK_E || key == KeyEvent.VK_ENTER || 
						key == KeyEvent.VK_ESCAPE)
					key = -1;
				switch(key) {
				case KeyEvent.VK_ENTER: case KeyEvent.VK_SPACE:
					Game.enterButtonPushedDown = false;
					if(!Game.escapePressedNegateAction) {
						switch(Game.selectorButtonPosition) {
							case -1:
								Game.selectorButtonPosition = -1;
								Game.State = Game.STATE.GAMEOVER;
								break;
							case 1:
								postLetter = '~';
								Game.selectorButtonPosition = 0;
								if(Game.sendToServer && HUD.score != 0)
									Game.connectingToServer = true;
								if(Game.gameControllerInUse) {
									Game.keysAreInUse = true;
									Game.selectorButtonPosition = 0;
								}
								if(!LeaderboardController.globalList)
									LeaderboardController.resetTrigger = true;
								break;
							case 2:
								if(Game.sendToServer)
									Game.sendToServer = false;
								else
									Game.sendToServer = true;
								try {
									if(Game.sendToServer)
										LeaderboardController.writeToSettings("sendToServer", "true");
									else
										LeaderboardController.writeToSettings("sendToServer", "false");
								} catch (IOException e1) {
								}
								if(Game.smb3CheckmarkSoundLoop.clipIsActive())
									Game.smb3CheckmarkSoundLoop.stop();
								if(Game.smb3Checkmark2SoundLoop.clipIsActive())
									Game.smb3Checkmark2SoundLoop.stop();
								Game.smb3Checkmark2SoundLoop.play();
								break;
							default:
								break;
						}
					}
					Game.escapePressedNegateAction = false;
					break;
				case KeyEvent.VK_ESCAPE:
					if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
						Game.selectorBPMP = Game.selectorButtonPosition;
						Game.escapePressedNegateAction = false;
						if(Game.keysAreInUse)
							Game.keysAreInUse = false;
						else
							Game.keysAreInUse = true;
						break;
					}
					break;
				}
			}
			else {
				switch(key) {
					case KeyEvent.VK_SHIFT:
						shiftOn = false;
						break;
					case KeyEvent.VK_ENTER:
						if(!Game.escapePressedNegateAction) {
							postLetter = '~';
							if(Game.sendToServer && HUD.score != 0)
								Game.connectingToServer = true;
							if(!LeaderboardController.globalList)
								LeaderboardController.resetTrigger = true;
							Game.selectorButtonPosition = 0;
						}
						Game.enterButtonPushedDown = false;
						Game.escapePressedNegateAction = false;
						break;
					case KeyEvent.VK_ESCAPE:
						Game.keysAreInUse = true;
						Game.selectorButtonPosition = -1;
				}
			}
		}
		else if(State == STATE.MENU) {
			switch(key) {
			case KeyEvent.VK_ENTER: case KeyEvent.VK_SPACE:
				if(System.currentTimeMillis() < cheatTimer) {
					cheatString = "";
					cheatTimer = System.currentTimeMillis() + 1500;
				}
				Game.enterButtonPushedDown = false;
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}
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
							this.closeGame();
							System.exit(1);
							break;
					}
				}
				Game.escapePressedNegateAction = false;
				break;
			case KeyEvent.VK_ESCAPE:
				if(System.currentTimeMillis() < cheatTimer) {
					cheatString = "";
					cheatTimer = System.currentTimeMillis() + 1500;
				}
				if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
					Game.selectorBPMP = Game.selectorButtonPosition;
					Game.escapePressedNegateAction = false;
					if(Game.keysAreInUse)
						Game.keysAreInUse = false;
					else
						Game.keysAreInUse = true;
					break;
				}
				break;
			case KeyEvent.VK_NUMPAD0:
				if(cheatTimer < System.currentTimeMillis()) {//first
					if(smwCheatSoundLoop.clipIsActive())
						smwCheatSoundLoop.stop();
					smwCheatSoundLoop.play();
					cheatString = "0";
					cheatTimer = System.currentTimeMillis() + 1500;
				}
				else if(cheatString.equals("0")){//second
					if(smwCheatSoundLoop.clipIsActive())
						smwCheatSoundLoop.stop();
					if(smwCheat2SoundLoop.clipIsActive())
						smwCheat2SoundLoop.stop();
					smwCheat2SoundLoop.play();
					cheatString = "00";
					cheatTimer = System.currentTimeMillis() + 1500;
				}
				else if(System.currentTimeMillis() < cheatTimer && !cheatString.equals("0")) {
					cheatString = "";
					cheatTimer = System.currentTimeMillis() + 1500;
				}
				break;
			case KeyEvent.VK_NUMPAD7:
				if(cheatString.equals("00") && System.currentTimeMillis() < cheatTimer) {//third
					if(smwCheat2SoundLoop.clipIsActive())
						smwCheat2SoundLoop.stop();
					if(smwCheat3SoundLoop.clipIsActive())
						smwCheat3SoundLoop.stop();
					smwCheat3SoundLoop.play();
					cheatString = "007";
					cheatTimer = System.currentTimeMillis() + 1500;
				}
				else if(cheatString.equals("0073") && System.currentTimeMillis() < cheatTimer) {//fifth
					if(smwCheat4SoundLoop.clipIsActive())
						smwCheat4SoundLoop.stop();
					if(smwCheat5SoundLoop.clipIsActive())
						smwCheat5SoundLoop.stop();
					smwCheat5SoundLoop.play();
					cheatString = "00737";
					cheatTimer = System.currentTimeMillis() + 1500;
				}
				else if(System.currentTimeMillis() < cheatTimer) {
					cheatString = "";
					cheatTimer = System.currentTimeMillis() + 1500;
				}
				break;
			case KeyEvent.VK_NUMPAD3:
				if(cheatString.equals("007") && System.currentTimeMillis() < cheatTimer) {//fourth
					if(smwCheat3SoundLoop.clipIsActive())
						smwCheat3SoundLoop.stop();
					if(smwCheat4SoundLoop.clipIsActive())
						smwCheat4SoundLoop.stop();
					smwCheat4SoundLoop.play();
					cheatString = "0073";
					cheatTimer = System.currentTimeMillis() + 1500;
				}
				else if(cheatString.equals("00737") && System.currentTimeMillis() < cheatTimer) {//six
					if(smwCheat5SoundLoop.clipIsActive())
						smwCheat5SoundLoop.stop();
					if(smwCheat6SoundLoop.clipIsActive())
						smwCheat6SoundLoop.stop();
					smwCheat6SoundLoop.play();
					cheatString = "007373";
					cheatTimer = System.currentTimeMillis() + 1500;
				}
				else if(cheatString.equals("007373596") && System.currentTimeMillis() < cheatTimer) {//ninth
					if(smwCheat9SoundLoop.clipIsActive())
						smwCheat9SoundLoop.stop();
					if(smwCheatFullSoundLoop.clipIsActive())
						smwCheatFullSoundLoop.stop();
					smwCheatFullSoundLoop.play();
					cheatString = "";
					cheatTimer = System.currentTimeMillis() + 1500;
					currentlySelectedFireball = 4;
					currentlySelectedCharacterSkin = 4;
					fireballPosition = 4;
					characterSkinPosition = 4;
					p.changeAnimations(4);
					if(fireballSFX != null)
						fireballSFX.close();
					if(fireballPopSFX != null)
						fireballPopSFX.close();
					fireballSFX = new SoundLoops("res/Sounds/SFX/miketysonpunchoutglovesfx.wav");
					fireballPopSFX = new SoundLoops("res/Sounds/SFX/miketysonpunchoutglovepopsfx.wav");
					VolumeSlider.adjustSFX(fireballSFX);
					VolumeSlider.adjustSFX(fireballPopSFX);
					skinNumber = null;
//					Game.writeOnceToSettings = true;
//					writeMultipleProperty.add("currentlySelectedCharacterSkin");
//					writeMultipleString.add("4");
//					writeMultipleProperty.add("currentlySelectedFireball");
//					writeMultipleString.add("4");
				}
				else if(System.currentTimeMillis() < cheatTimer) {
					cheatString = "";
					cheatTimer = System.currentTimeMillis() + 1500;
				}
				break;
			case KeyEvent.VK_NUMPAD5:
				if(cheatString.equals("007373") && System.currentTimeMillis() < cheatTimer) {//seventh
					if(smwCheat6SoundLoop.clipIsActive())
						smwCheat6SoundLoop.stop();
					if(smwCheat7SoundLoop.clipIsActive())
						smwCheat7SoundLoop.stop();
					smwCheat7SoundLoop.play();
					cheatString = "0073735";
					cheatTimer = System.currentTimeMillis() + 1500;
				}
				else if(System.currentTimeMillis() < cheatTimer) {
					cheatString = "";
					cheatTimer = System.currentTimeMillis() + 1500;
				}
				break;
			case KeyEvent.VK_NUMPAD9:
				if(cheatString.equals("0073735") && System.currentTimeMillis() < cheatTimer) {//eighth
					if(smwCheat7SoundLoop.clipIsActive())
						smwCheat7SoundLoop.stop();
					if(smwCheat8SoundLoop.clipIsActive())
						smwCheat8SoundLoop.stop();
					smwCheat8SoundLoop.play();
					cheatString = "00737359";
					cheatTimer = System.currentTimeMillis() + 1500;
				}
				else if(System.currentTimeMillis() < cheatTimer) {
					cheatString = "";
					cheatTimer = System.currentTimeMillis() + 1500;
				}
				break;
			case KeyEvent.VK_NUMPAD6:
				if(cheatString.equals("00737359") && System.currentTimeMillis() < cheatTimer) {//ninth
					if(smwCheat8SoundLoop.clipIsActive())
						smwCheat8SoundLoop.stop();
					if(smwCheat9SoundLoop.clipIsActive())
						smwCheat9SoundLoop.stop();
					smwCheat9SoundLoop.play();
					cheatString = "007373596";
					cheatTimer = System.currentTimeMillis() + 1500;
				}
				else if(System.currentTimeMillis() < cheatTimer) {
					cheatString = "";
					cheatTimer = System.currentTimeMillis() + 1500;
				}
				break;
			default:
				if(System.currentTimeMillis() < cheatTimer) {
					cheatString = "";
					cheatTimer = System.currentTimeMillis() + 1500;
				}
				break;
			}
		}else if(State == STATE.GAMEOVER) {
			switch(key) {
			case KeyEvent.VK_ENTER:  case KeyEvent.VK_SPACE:
				Game.enterButtonPushedDown = false;
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}
				if(!Game.escapePressedNegateAction && Game.keysAreInUse) {
					switch(Game.selectorButtonPosition) {
						case -2:
							State = STATE.LEADERBOARD;
							Game.selectorButtonPosition = -1;
							if(smb3OpenSoundLoop.clipIsActive())
								smb3OpenSoundLoop.stop();
							smb3OpenSoundLoop.play();
							break;
						case -1:
							State = STATE.SET_SCORE;
							Game.keysAreInUse = false;
							Game.selectorButtonPosition = 0;
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
							this.closeGame();
							System.exit(1);
							break;
					}
				}
				Game.escapePressedNegateAction = false;
				break;
			case KeyEvent.VK_ESCAPE:
				if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
					Game.selectorBPMP = Game.selectorButtonPosition;
					Game.escapePressedNegateAction = false;
					if(Game.keysAreInUse)
						Game.keysAreInUse = false;
					else
						Game.keysAreInUse = true;
					break;
				}
				break;
			}
		}else if(State == STATE.GAME && (Game.userHasPaused || System.currentTimeMillis() < pauseSoundFXTimer) ) {
			switch(key) {
			case KeyEvent.VK_ENTER:  case KeyEvent.VK_SPACE:
				Game.enterButtonPushedDown = false;
				if(key == KeyEvent.VK_SPACE)
					setIsShooting(false);
				if(pauseSoundFXSoundLoop.clipIsActive())
					break;
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}
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
							Game.keysAreInUse = false;
							break;
						case 1:
							Game.setDontStartOver(true);
							Game.State = Game.STATE.RESET;
							if(smb3OpenSoundLoop.clipIsActive())
								smb3OpenSoundLoop.stop();
							smb3OpenSoundLoop.play();
							Game.selectorButtonPosition = 0;
							break;
						case 2:
							this.closeGame();
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
					p.setRunningStartL(false);
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
				if(!Game.keysAreInUse) 
					Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:
				if(!keepRunningAfterPauseL) {
					dontRunAfterPause = true;
					xRBoolean = false;
					p.setRunningStartR(false);
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
				if(!Game.keysAreInUse) 
					Game.keysAreInUse = true;
				break;
			case KeyEvent.VK_ESCAPE:
				if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
					Game.selectorBPMP = Game.selectorButtonPosition;
					Game.escapePressedNegateAction = false;
					//if(Game.keysAreInUse)
						//Game.keysAreInUse = false;
					//else
						//Game.keysAreInUse = true;
					break;
				}
				break;
			}
		}else if(State == STATE.LEADERBOARD) {
			switch(key) {
				case KeyEvent.VK_ENTER:  case KeyEvent.VK_SPACE:
					Game.enterButtonPushedDown = false;
					if(!Game.keysAreInUse) {
						Game.keysAreInUse = true;
						break;
					}
					if(!Game.escapePressedNegateAction) {
						if(Game.areYouSureBoolean) {
							switch(Game.selectorButtonPosition) {
							case 0:
								Game.selectorButtonPosition = 0;
								Game.areYouSureBoolean = false;
								Game.smb3KickSoundLoop.play();
								break;	
							case 1:
								LeaderboardController.deleteTrigger = true;
								Game.selectorButtonPosition = 0;
								Game.areYouSureBoolean = false;
								if(Game.smb3TailSoundLoop.clipIsActive())
									Game.smb3TailSoundLoop.stop();
								Game.smb3TailSoundLoop.play();
								break;
							}
						}
						else {
							switch(Game.selectorButtonPosition) {
							case -1:
								//Back button
								if(Game.backToGameOver) {
									Game.selectorButtonPosition = -2;
									Game.State = Game.STATE.GAMEOVER;
								}
								else {
									Game.selectorButtonPosition = -3;
									Game.State = Game.STATE.MENU;
								}
								if(Game.smb3KickSoundLoop.clipIsActive())
									Game.smb3KickSoundLoop.stop();
								Game.smb3KickSoundLoop.play();
								break;
							case 0:
								if(LeaderboardController.globalList) {
									//Local button
									Game.selectorButtonPosition = 1;
									LeaderboardController.globalList = false;
									LeaderboardController.listTrigger = true;
									if(Game.smb3ItemSoundLoop.clipIsActive())
										Game.smb3ItemSoundLoop.stop();
									Game.smb3ItemSoundLoop.play();
								}
								else {
									//Reset Leaderboard Button
									if(LeaderboardController.checkLeaderboard()) {
										Game.selectorButtonPosition = 0;
										Game.areYouSureBoolean = true;
										Game.hudSFX.get(4).play();
									}
									else {
										if(Game.smwErrorSoundLoop.clipIsActive())
											Game.smwErrorSoundLoop.stop();
										Game.smwErrorSoundLoop.play();
									}
									break;	
								}
								break;
							case 1:
								if(LeaderboardController.globalList) {
									//Upload button
									if(!LeaderboardController.checkLeaderboard()) {
										if(Game.smwErrorSoundLoop.clipIsActive())
											Game.smwErrorSoundLoop.stop();
										Game.smwErrorSoundLoop.play();
									}
									else {
										String s = "";
										if(Game.scoreEntered && 0 < HUD.score) {
											s=HUD.nameEntered;
											s=s+": "+Long.toString((HUD.score));
											//Game.saveName = s;
										}
										LeaderboardController.uploadTrigger = true;
									}
								}
								else {
									//Global button
									LeaderboardController.globalList = true;
									LeaderboardController.listTrigger = true;
								}
								break;
							}
						}
					}
					Game.escapePressedNegateAction = false;
					break;
				case KeyEvent.VK_ESCAPE:
					if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
						Game.selectorBPMP = Game.selectorButtonPosition;
						Game.escapePressedNegateAction = false;
						if(Game.keysAreInUse)
							Game.keysAreInUse = false;
						else
							Game.keysAreInUse = true;
						if(Game.areYouSureBoolean && Game.selectorButtonPosition != 0)
							Game.selectorButtonPosition = 0;
						break;
					}
					break;
			}
		}else if(State == STATE.SHOP) {
			switch(key) {
			case KeyEvent.VK_ENTER:  case KeyEvent.VK_SPACE:
				if(allUnlockedScreen) {
					allUnlockedScreen = false;
					return;
				}
				Game.enterButtonPushedDown = false;
				if(!Game.keysAreInUse) {
					if(Game.selectorButtonPosition == -2 && currentSkinLocked == false)
						Game.selectorButtonPosition = 0;
					else if(Game.selectorButtonPosition == -5 && currentTrackLocked == false)
						Game.selectorButtonPosition = 1;
					else if(Game.selectorButtonPosition == -8 && currentFireballLocked == false)
						Game.selectorButtonPosition = 2;
					else if(Game.selectorButtonPosition == -11 && currentItemLocked == false)
						Game.selectorButtonPosition = 3;
					Game.keysAreInUse = true;
					break;
				}
				if(!Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -12:
						if(Game.itemPosition == 6)//Max Items
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
						if(currentItemLocked) {
							switch(itemPosition){
								case 4:
									if(totalPoints >= 1000){
										Game.item4Unlocked = true;
										//Game.settingsSetup = false;
										Game.writeOnceToSettings = true;
										Game.writeOnceProperty = "currentlySelectedItem";
										Game.writeOnceString = Integer.toString(Game.itemPosition);
										Game.writeOnceToSettingswithPoints = true;
										Game.writeOnceUnlock = "item4Unlocked";
										Game.itemPosition = 4;
										Game.currentlySelectedItem = 4;
										currentItemLocked = false;
										skinNumber = null;
										totalPoints -= 1000;
										Game.starExplode = true;
										Game.mx = Game.WIDTH +9;
										Game.my = 136;
										Game.checkIfAllUnlocked();
										if(smb31PupSoundLoop.clipIsActive())
											smb31PupSoundLoop.stop();
										smb31PupSoundLoop.play();
										Game.selectorButtonPosition = -12;
									}
									else{
										if(smwErrorSoundLoop.clipIsActive())
											smwErrorSoundLoop.stop();
										smwErrorSoundLoop.play();
									}
									break;
								case 5:
									if(totalPoints >= 10000){
										Game.item5Unlocked = true;
										//Game.settingsSetup = false;
										Game.writeOnceToSettings = true;
										Game.writeOnceProperty = "currentlySelectedItem";
										Game.writeOnceString = Integer.toString(Game.itemPosition);
										Game.writeOnceToSettingswithPoints = true;
										Game.writeOnceUnlock = "item5Unlocked";
										Game.itemPosition = 5;
										Game.currentlySelectedItem = 5;
										currentItemLocked = false;
										skinNumber = null;
										totalPoints -= 10000;
										Game.starExplode = true;
										Game.mx = Game.WIDTH +9;
										Game.my = 136;
										Game.checkIfAllUnlocked();
										if(smb31PupSoundLoop.clipIsActive())
											smb31PupSoundLoop.stop();
										smb31PupSoundLoop.play();
										Game.selectorButtonPosition = -12;
									}
									else{
										if(smwErrorSoundLoop.clipIsActive())
											smwErrorSoundLoop.stop();
										smwErrorSoundLoop.play();
									}
									break;
								case 6:
									if(totalPoints >= 100000){
										Game.item6Unlocked = true;
										//Game.settingsSetup = false;
										Game.writeOnceToSettings = true;
										Game.writeOnceProperty = "currentlySelectedItem";
										Game.writeOnceString = Integer.toString(Game.itemPosition);
										Game.writeOnceToSettingswithPoints = true;
										Game.writeOnceUnlock = "item6Unlocked";
										Game.itemPosition = 6;
										Game.currentlySelectedItem = 6;
										currentItemLocked = false;
										skinNumber = null;
										totalPoints -= 100000;
										Game.starExplode = true;
										Game.mx = Game.WIDTH +9;
										Game.my = 136;
										Game.checkIfAllUnlocked();
										if(smb31PupSoundLoop.clipIsActive())
											smb31PupSoundLoop.stop();
										smb31PupSoundLoop.play();
										Game.selectorButtonPosition = -12;
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
						break;
					case -10:
						//Fireballs Set
						if(!Game.currentFireballLocked) {
							if(Game.currentlySelectedFireball != Game.fireballPosition) {
								if(Game.smb3ItemSoundLoop.clipIsActive())
									Game.smb3ItemSoundLoop.stop();
								Game.smb3ItemSoundLoop.play();
							}
							else {
								if(Game.smb3Checkmark2SoundLoop.clipIsActive())
									Game.smb3Checkmark2SoundLoop.stop();
								Game.smb3Checkmark2SoundLoop.play();
							}
							Game.currentlySelectedFireball = Game.fireballPosition;
							Game.fireballSoundSet();
							Game.writeOnceToSettings = true;
							Game.writeOnceProperty = "currentlySelectedFireball";
							Game.writeOnceString = Integer.toString(Game.fireballPosition);
							//Game.settingsSetup = false;
						}
						else {
							if(Game.smwErrorSoundLoop.clipIsActive())
								Game.smwErrorSoundLoop.stop();
							Game.smwErrorSoundLoop.play();
						}
						break;
					case -9:
						if(Game.fireballPosition == 3 && Game.currentlySelectedFireball == 4)//Max Fireballs
							Game.fireballPosition = 4;
						else if(Game.fireballPosition == 3 && Game.currentlySelectedFireball == 5) {
							Game.fireballPosition = 5;
							Game.skinNumber = null;
						}
						else if(Game.fireballPosition == 3 || Game.fireballPosition == 4 || Game.fireballPosition == 5)
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
						if(currentFireballLocked) {
							switch(fireballPosition){
								case 1:
									if(totalPoints >= 1000){
										Game.fireball1Unlocked = true;
										//Game.settingsSetup = false;
										Game.writeOnceToSettings = true;
										Game.writeOnceProperty = "currentlySelectedFireball";
										Game.writeOnceString = Integer.toString(Game.fireballPosition);
										Game.writeOnceToSettingswithPoints = true;
										Game.writeOnceUnlock = "fireball1Unlocked";
										Game.fireballPosition = 1;
										Game.currentlySelectedFireball = 1;
										currentFireballLocked = false;
										skinNumber = null;
										totalPoints -= 1000;
										Game.starExplode = true;
										Game.mx = Game.WIDTH +9;
										Game.my = 336;
										Game.checkIfAllUnlocked();
										if(smb31PupSoundLoop.clipIsActive())
											smb31PupSoundLoop.stop();
										smb31PupSoundLoop.play();
										Game.selectorButtonPosition = -9;
									}
									else{
										if(smwErrorSoundLoop.clipIsActive())
											smwErrorSoundLoop.stop();
										smwErrorSoundLoop.play();
									}
									break;
								case 2:
									if(totalPoints >= 10000){
										Game.fireball2Unlocked = true;
										//Game.settingsSetup = false;
										Game.writeOnceToSettings = true;
										Game.writeOnceProperty = "currentlySelectedFireball";
										Game.writeOnceString = Integer.toString(Game.fireballPosition);
										Game.writeOnceToSettingswithPoints = true;
										Game.writeOnceUnlock = "fireball2Unlocked";
										Game.fireballPosition = 2;
										Game.currentlySelectedFireball = 2;
										currentFireballLocked = false;
										skinNumber = null;
										totalPoints -= 10000;
										Game.starExplode = true;
										Game.mx = Game.WIDTH +9;
										Game.my = 336;
										Game.checkIfAllUnlocked();
										if(smb31PupSoundLoop.clipIsActive())
											smb31PupSoundLoop.stop();
										smb31PupSoundLoop.play();
										Game.selectorButtonPosition = -9;
									}
									else{
										if(smwErrorSoundLoop.clipIsActive())
											smwErrorSoundLoop.stop();
										smwErrorSoundLoop.play();
									}
									break;
								case 3:
									if(totalPoints >= 100000){
										Game.fireball3Unlocked = true;
										//Game.settingsSetup = false;
										Game.writeOnceToSettings = true;
										Game.writeOnceProperty = "currentlySelectedFireball";
										Game.writeOnceString = Integer.toString(Game.fireballPosition);
										Game.writeOnceToSettingswithPoints = true;
										Game.writeOnceUnlock = "fireball3Unlocked";
										Game.fireballPosition = 3;
										Game.currentlySelectedFireball = 3;
										currentFireballLocked = false;
										skinNumber = null;
										totalPoints -= 100000;
										Game.starExplode = true;
										Game.mx = Game.WIDTH +9;
										Game.my = 336;
										Game.checkIfAllUnlocked();
										if(smb31PupSoundLoop.clipIsActive())
											smb31PupSoundLoop.stop();
										smb31PupSoundLoop.play();
										Game.selectorButtonPosition = -9;
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
							Game.fireballSoundSet();
						}
						//else
							//Play error noise
						break;
					case -7:
						//Tracks Set
						if(!Game.currentTrackLocked) {
							boolean b = false;
							String s = "";
							String ss = "";
							switch(Game.trackPosition) {
							case 0:
								s = "gameTrack1Set";
								if(Game.gameTrack1Set) {
									Game.gameTrack1Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack1Set = true;
									ss = "true";
								}
								break;
							case 1:
								s = "gameTrack2Set";
								if(Game.gameTrack2Set) {
									Game.gameTrack2Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack2Set = true;
									ss = "true";
								}
								break;
							case 2:
								s = "gameTrack3Set";
								if(Game.gameTrack3Set) {
									Game.gameTrack3Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack3Set = true;
									ss = "true";
								}
								break;
							case 3:
								s = "gameTrack4Set";
								if(Game.gameTrack4Set) {
									Game.gameTrack4Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack4Set = true;
									ss = "true";
								}
								break;
							case 4:
								s = "gameTrack5Set";
								if(Game.gameTrack5Set || !Game.track4Unlocked) {
									if(!Game.track4Unlocked) {
										if(Game.smwErrorSoundLoop.clipIsActive())
											Game.smwErrorSoundLoop.stop();
										Game.smwErrorSoundLoop.play();
									}
									Game.gameTrack5Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack5Set = true;
									ss = "true";
								}
								break;
							case 5:
								s = "gameTrack6Set";
								if(Game.gameTrack6Set || !Game.track5Unlocked) {
									if(!Game.track5Unlocked) {
										if(Game.smwErrorSoundLoop.clipIsActive())
											Game.smwErrorSoundLoop.stop();
										Game.smwErrorSoundLoop.play();
									}
									Game.gameTrack6Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack6Set = true;
									ss = "true";
								}
								break;
							case 6:
								s = "gameTrack7Set";
								if(Game.gameTrack7Set || !Game.track6Unlocked) {
									if(!Game.track6Unlocked) {
										if(Game.smwErrorSoundLoop.clipIsActive())
											Game.smwErrorSoundLoop.stop();
										Game.smwErrorSoundLoop.play();
									}
									Game.gameTrack7Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack7Set = true;
									ss = "true";
								}
								break;
							case 7:
								s = "gameTrack8Set";
								if(Game.gameTrack8Set) {
									Game.gameTrack8Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack8Set = true;
									ss = "true";
								}
								break;
							default:
								break;
							}
							if(b) {
								if(Game.smb3ItemSoundLoop.clipIsActive())
									Game.smb3ItemSoundLoop.stop();
								Game.smb3ItemSoundLoop.play();
							}
							if(!s.equals("") && !ss.equals("")) {
								try {
									LeaderboardController.writeToSettings(s, ss);
								} catch (IOException e1) {
								}
								if(ss.equals("true") && Game.soundRandomizer == 8 && Game.gameTrackCurrentlyPlaying == -1  && Game.menuTrackCurrentlyPlaying == -1) {
									int randomIPlus = 0;
									if(Game.track4Unlocked)
										randomIPlus++;
									if(Game.track5Unlocked)
										randomIPlus++;
									if(Game.track6Unlocked)
										randomIPlus++;
									Random rand = new Random();
									Game.soundRandomizer = rand.nextInt(7) + (-3+randomIPlus);
									Game.soundRandomizer = VolumeSlider.gameTrackSetup(Game.soundRandomizer);
									//setsetup[VolumeSlider.TrackSetup]
									if(Game.soundRandomizer != -4) {
										Game.gameSoundLoops.get(Game.soundRandomizer).stop();
										Game.gameSoundLoops.get(Game.soundRandomizer).setSoundLoopBoolean(true);
										Game.gameSoundLoops.get(Game.soundRandomizer).setFramePosition(0);
//										Game.gameSoundLoops.get(Game.soundRandomizer).play();
									}
									else
										Game.soundRandomizer = 8;
								}
								else if(ss.equals("false")) {  
									if(Game.gameTrack1Set == false && Game.gameTrack2Set == false && Game.gameTrack3Set == false && 
										Game.gameTrack4Set == false && Game.gameTrack5Set == false && Game.gameTrack6Set == false && 
										Game.gameTrack7Set == false && Game.gameTrack8Set == false && Game.soundRandomizer != 8 &&
										Game.soundRandomizer != -4) 
									Game.soundRandomizer = 8;
									if(Game.smbPopSoundLoop.clipIsActive())
										Game.smbPopSoundLoop.stop();
									Game.smbPopSoundLoop.play();
								}
							}
						}
						else {
							if(Game.smwErrorSoundLoop.clipIsActive())
								Game.smwErrorSoundLoop.stop();
							Game.smwErrorSoundLoop.play();
						}
						break;
					case -6:
						if(Game.trackPosition == 7)//Max Tracks
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
						if(Game.currentTrackLocked) {
							switch(Game.trackPosition){
								case 4:
									if(Game.totalPoints >= 1000){
										Game.track4Unlocked = true;
										//Game.settingsSetup = false;
										Game.writeOnceToSettings = true;
										Game.writeOnceProperty = "gameTrack5Set";
										Game.writeOnceString = "true";
										Game.writeOnceToSettingswithPoints = true;
										Game.writeOnceUnlock = "track4Unlocked";
										Game.trackPosition = 4;
										Game.currentlySelectedTrack = 4;
										Game.currentTrackLocked = false;
										Game.skinNumber = null;
										Game.totalPoints -= 1000;
										Game.starExplode = true;
										Game.track4Unlocked = true;
										Game.gameTrack5Set = true;
										int randomIPlus = 0;
										if(Game.track4Unlocked)
											randomIPlus++;
										if(Game.track5Unlocked)
											randomIPlus++;
										if(Game.track6Unlocked)
											randomIPlus++;
										Random rand = new Random();
										Game.soundRandomizer = rand.nextInt(7) + (-3+randomIPlus);
										Game.soundRandomizer = VolumeSlider.gameTrackSetup(Game.soundRandomizer);
										Game.mx = Game.WIDTH +9;
										Game.my = 136;
										Game.checkIfAllUnlocked();
										if(Game.smb31PupSoundLoop.clipIsActive())
											Game.smb31PupSoundLoop.stop();
										Game.smb31PupSoundLoop.play();
										Game.selectorButtonPosition = -6;
									}
									else{
										if(Game.smwErrorSoundLoop.clipIsActive())
											Game.smwErrorSoundLoop.stop();
										Game.smwErrorSoundLoop.play();
									}
									break;
								case 5:
									if(Game.totalPoints >= 10000){
										Game.track5Unlocked = true;
										//Game.settingsSetup = false;
										Game.writeOnceToSettings = true;
										Game.writeOnceProperty = "gameTrack6Set";
										Game.writeOnceString = "true";
										Game.writeOnceToSettingswithPoints = true;
										Game.writeOnceUnlock = "track5Unlocked";
										Game.trackPosition = 5;
										Game.currentlySelectedTrack = 5;
										Game.currentTrackLocked = false;
										Game.skinNumber = null;
										Game.totalPoints -= 10000;
										Game.starExplode = true;
										Game.track5Unlocked = true;
										Game.gameTrack6Set = true;
										int randomIPlus = 0;
										if(Game.track4Unlocked)
											randomIPlus++;
										if(Game.track5Unlocked)
											randomIPlus++;
										if(Game.track6Unlocked)
											randomIPlus++;
										Random rand = new Random();
										Game.soundRandomizer = rand.nextInt(7) + (-3+randomIPlus);
										Game.soundRandomizer = VolumeSlider.gameTrackSetup(Game.soundRandomizer);
										Game.mx = Game.WIDTH +9;
										Game.my = 136;
										Game.checkIfAllUnlocked();
										if(Game.smb31PupSoundLoop.clipIsActive())
											Game.smb31PupSoundLoop.stop();
										Game.smb31PupSoundLoop.play();
										Game.selectorButtonPosition = -6;
									}
									else{
										if(Game.smwErrorSoundLoop.clipIsActive())
											Game.smwErrorSoundLoop.stop();
										Game.smwErrorSoundLoop.play();
									}
									break;
								case 6:
									if(Game.totalPoints >= 100000){
										Game.track6Unlocked = true;
										//Game.settingsSetup = false;
										Game.writeOnceToSettings = true;
										Game.writeOnceProperty = "gameTrack7Set";
										Game.writeOnceString = "true";
										Game.writeOnceToSettingswithPoints = true;
										Game.writeOnceUnlock = "track6Unlocked";
										Game.trackPosition = 6;
										Game.currentlySelectedTrack = 6;
										Game.currentTrackLocked = false;
										Game.skinNumber = null;
										Game.totalPoints -= 100000;
										Game.starExplode = true;
										Game.track6Unlocked = true;
										Game.gameTrack7Set = true;
										int randomIPlus = 0;
										if(Game.track4Unlocked)
											randomIPlus++;
										if(Game.track5Unlocked)
											randomIPlus++;
										if(Game.track6Unlocked)
											randomIPlus++;
										Random rand = new Random();
										Game.soundRandomizer = rand.nextInt(7) + (-3+randomIPlus);
										Game.soundRandomizer = VolumeSlider.gameTrackSetup(Game.soundRandomizer);
										Game.mx = Game.WIDTH +9;
										Game.my = 136;
										Game.checkIfAllUnlocked();
										if(Game.smb31PupSoundLoop.clipIsActive())
											Game.smb31PupSoundLoop.stop();
										Game.smb31PupSoundLoop.play();
										Game.selectorButtonPosition = -6;
									}
									else{
										if(Game.smwErrorSoundLoop.clipIsActive())
											Game.smwErrorSoundLoop.stop();
										Game.smwErrorSoundLoop.play();
									}
									break;
								default:
									break;
							}
						}
						break;
					case -4:
						//Skin Set
						if(!Game.currentSkinLocked) {
							if(Game.currentlySelectedCharacterSkin != Game.characterSkinPosition) {
								if(Game.smb3ItemSoundLoop.clipIsActive())
									Game.smb3ItemSoundLoop.stop();
								Game.smb3ItemSoundLoop.play();
							}
							else {
								if(Game.smb3Checkmark2SoundLoop.clipIsActive())
									Game.smb3Checkmark2SoundLoop.stop();
								Game.smb3Checkmark2SoundLoop.play();
							}
							Game.currentlySelectedCharacterSkin = Game.characterSkinPosition;
							Game.writeOnceToSettings = true;
							Game.writeOnceProperty = "currentlySelectedCharacterSkin";
							Game.writeOnceString = Integer.toString(Game.characterSkinPosition);
							//Game.settingsSetup = false;
						}
						else {
							if(Game.smwErrorSoundLoop.clipIsActive())
								Game.smwErrorSoundLoop.stop();
							Game.smwErrorSoundLoop.play();
						}
						break;
					case -3:
						if(Game.characterSkinPosition == 3 && Game.currentlySelectedCharacterSkin == 4)//Max Skins
							Game.characterSkinPosition = 4;
						else if(Game.characterSkinPosition == 3 && Game.currentlySelectedCharacterSkin == 5) 
							Game.characterSkinPosition = 5;
						else if (Game.characterSkinPosition == 3 || Game.characterSkinPosition == 4 || Game.characterSkinPosition == 5)
							Game.characterSkinPosition = 0;
						else {
							Game.characterSkinPosition++;
						}
						if(Game.currentlySelectedCharacterSkin == 5 && Game.characterSkinPosition == 5)
							Game.skinNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.characterSkinPosition)), 10, 10);
						else
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
									if(totalPoints >= 1000){
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
										totalPoints -= 1000;
										Game.starExplode = true;
										Game.mx = Game.WIDTH +9;
										Game.my = 136;
										Game.checkIfAllUnlocked();
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
									if(totalPoints >= 10000){
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
										totalPoints -= 10000;
										Game.starExplode = true;
										Game.mx = Game.WIDTH +9;
										Game.my = 136;
										Game.checkIfAllUnlocked();
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
									if(totalPoints >= 100000){
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
										totalPoints -= 100000;
										Game.starExplode = true;
										Game.mx = Game.WIDTH +9;
										Game.my = 136;
										Game.checkIfAllUnlocked();
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
						if(Game.characterSkinPosition == 5)
							Game.characterSkinPosition-=2;
						else if(Game.characterSkinPosition > 0)
							Game.characterSkinPosition--;
						else if(Game.currentlySelectedCharacterSkin == 4)
							Game.characterSkinPosition = 4;
						else if(Game.currentlySelectedCharacterSkin == 5) 
							Game.characterSkinPosition = 5;
						else
							Game.characterSkinPosition = 3;//Set to Max Skins
						if(Game.currentlySelectedCharacterSkin == 5 && Game.characterSkinPosition == 5)
							Game.skinNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.characterSkinPosition)), 10, 10);
						else
							Game.skinNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.characterSkinPosition+1)), 10, 10);
						if(smb3Bump2SoundLoop.clipIsActive())
							smb3Bump2SoundLoop.stop();
						smb3Bump2SoundLoop.play();
						break;
					case 1:
						if(Game.trackPosition > 0)
							Game.trackPosition--;
						else
							Game.trackPosition = 7;//Set to Max Tracks
						Game.trackNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.trackPosition+1)), 10, 10);
						if(smb3Bump2SoundLoop.clipIsActive())
							smb3Bump2SoundLoop.stop();
						smb3Bump2SoundLoop.play();
						break;
					case 2:
						if(Game.fireballPosition == 5)
							Game.fireballPosition-=2;
						else if(Game.fireballPosition > 0)
							Game.fireballPosition--;
						else if(Game.currentlySelectedFireball == 4)
							Game.fireballPosition = 4;
						else if(Game.currentlySelectedFireball == 5) {
							Game.fireballPosition = 5;
							Game.skinNumber = null;
						}
						else
							Game.fireballPosition = 3;//Set to Max Fireballs
						Game.fireballNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.fireballPosition+1)), 10, 10);
						if(smb3Bump2SoundLoop.clipIsActive())
							smb3Bump2SoundLoop.stop();
						smb3Bump2SoundLoop.play();
						break;
					case 3:
						if(Game.itemPosition > 0)
							Game.itemPosition--;
						else
							Game.itemPosition = 6;//Set to Max Items
						Game.itemNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.itemPosition+1)), 10, 10);
						if(smb3Bump2SoundLoop.clipIsActive())
							smb3Bump2SoundLoop.stop();
						smb3Bump2SoundLoop.play();
						break;
					}
				}
				Game.escapePressedNegateAction = false;
				break;
			case KeyEvent.VK_ESCAPE:
				if(allUnlockedScreen) {
					allUnlockedScreen = false;
					return;
				}
				if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
					if(Game.selectorButtonPosition == -2 && currentSkinLocked == false)
						Game.selectorButtonPosition = 0;
					else if(Game.selectorButtonPosition == -5 && currentTrackLocked == false)
						Game.selectorButtonPosition = 1;
					else if(Game.selectorButtonPosition == -8 && currentFireballLocked == false)
						Game.selectorButtonPosition = 2;
					else if(Game.selectorButtonPosition == -11 && currentItemLocked == false)
						Game.selectorButtonPosition = 3;
					Game.selectorBPMP = Game.selectorButtonPosition;
					Game.escapePressedNegateAction = false;
					if(Game.keysAreInUse)
						Game.keysAreInUse = false;
					else
						Game.keysAreInUse = true;
					break;
				}
				break;
		}
		}else if(State == STATE.HELP) {
			switch(key) {
			case KeyEvent.VK_ENTER:  case KeyEvent.VK_SPACE:
				Game.enterButtonPushedDown = false;
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}
				if(!Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -1:
						if(!helpLines.isEmpty())
							helpLines.clear();
						Game.selectorButtonPosition = -1;
						Game.State = Game.STATE.MENU;
						if(smb3KickSoundLoop.clipIsActive())
							smb3KickSoundLoop.stop();
						smb3KickSoundLoop.play();
						break;	
					case 0:
						if(firstTimeBeating) {
							Game.State = STATE.CREDITS;
							if(Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).clipIsActive()) {
								Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).stop();
	//							Game.menuMusicStopped = true;
	//							Game.trackCurrentlyPlaying = false;
								
							}
							Game.keysAreInUse = false;
							Game.skipSequence = false;
							Game.askToSkipSequence = false;
							Game.selectorButtonPosition = 0;
						}
						break;
					default:
						break;
					}
				}
				Game.escapePressedNegateAction = false;
				break;
			case KeyEvent.VK_ESCAPE:
				if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
					Game.selectorBPMP = Game.selectorButtonPosition;
					Game.escapePressedNegateAction = false;
					if(Game.keysAreInUse)
						Game.keysAreInUse = false;
					else
						Game.keysAreInUse = true;
					if(Game.selectorButtonPosition != -1)
						Game.selectorButtonPosition = -1;
					break;
				}
				break;
			}
		}else if(State == STATE.SETTINGS) {
			switch(key) {
			case KeyEvent.VK_ENTER:  case KeyEvent.VK_SPACE:
				Game.enterButtonPushedDown = false;
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}
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
							Game.track4Unlocked = false;
							Game.track5Unlocked = false;
							Game.track6Unlocked = false;
							Game.fireball1Unlocked = false;
							Game.fireball2Unlocked = false;
							Game.fireball3Unlocked = false;
							Game.item4Unlocked = false;
							Game.item5Unlocked = false;
							Game.item6Unlocked = false;
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
							Game.fireballSoundSet();
							skinNumber = null;
							XInputDevice.a = XInputConstants.XINPUT_GAMEPAD_A;
							XInputDevice.b = XInputConstants.XINPUT_GAMEPAD_B;
							XInputDevice.x = XInputConstants.XINPUT_GAMEPAD_X;
							XInputDevice.y = XInputConstants.XINPUT_GAMEPAD_Y;
							XInputDevice.back = XInputConstants.XINPUT_GAMEPAD_BACK;
							XInputDevice.start = XInputConstants.XINPUT_GAMEPAD_START;
							XInputDevice.lShoulder = XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER;
							XInputDevice.rShoulder = XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER;
							XInputDevice.lThumb = XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB;
							XInputDevice.rThumb = XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB;
							XInputDevice.guide = XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON;
							XInputDevice.unknown = XInputConstants.XINPUT_GAMEPAD_UNKNOWN;
							XInputDevice.up = XInputConstants.XINPUT_GAMEPAD_DPAD_UP;
							XInputDevice.down = XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN;
							XInputDevice.left = XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT;
							XInputDevice.right = XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT;
							XInputDevice.lTrigger = XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER;
							XInputDevice.rTrigger = XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER;
							Game.totalPoints = 0;
							Game.highScore = 0;
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
						case -5:
							Game.selectorButtonPosition = -1;
							if(Game.trackCurrentlyPlaying == false)
								TrackController.reset();
							Game.State = Game.STATE.TRACKLIST;
							if(smb3OpenSoundLoop.clipIsActive())
								smb3OpenSoundLoop.stop();
							smb3OpenSoundLoop.play();
							break;
						case -4:
							Game.selectorButtonPosition = -1;
							Game.State = Game.STATE.CONTROLS;
							if(smb3OpenSoundLoop.clipIsActive())
								smb3OpenSoundLoop.stop();
							smb3OpenSoundLoop.play();
							break;
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
			case KeyEvent.VK_ESCAPE:
				if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
					Game.selectorBPMP = Game.selectorButtonPosition;
					Game.escapePressedNegateAction = false;
					if(Game.keysAreInUse)
						Game.keysAreInUse = false;
					else 
						Game.keysAreInUse = true;
					if(areYouSureBoolean && Game.selectorButtonPosition != 0)
						Game.selectorButtonPosition = 0;
					break;
				}
				//if(areYouSureBoolean && Game.selectorButtonPosition == 0) { 
					//Game.selectorButtonPosition = 3;
					//areYouSureBoolean = false;
				//}
				//else if(areYouSureBoolean)
					//Game.selectorButtonPosition = 0;
					
				break;
			}
		}
		else if(State == STATE.CONTROLS) {
			if(System.currentTimeMillis() < ControlsController.buttonChangeTimer) {
				if(controlsController.buttonToChange <= -2 && -9 <= controlsController.buttonToChange)
					switch(key) {
					case KeyEvent.VK_A: case KeyEvent.VK_B: case KeyEvent.VK_C: case KeyEvent.VK_D:
					case KeyEvent.VK_E: case KeyEvent.VK_F: case KeyEvent.VK_G: case KeyEvent.VK_H:
					case KeyEvent.VK_I: case KeyEvent.VK_J: case KeyEvent.VK_K: case KeyEvent.VK_L:
					case KeyEvent.VK_M: case KeyEvent.VK_N: case KeyEvent.VK_O: case KeyEvent.VK_P:
					case KeyEvent.VK_Q: case KeyEvent.VK_R: case KeyEvent.VK_S: case KeyEvent.VK_T:
					case KeyEvent.VK_U: case KeyEvent.VK_V: case KeyEvent.VK_W: case KeyEvent.VK_X:
					case KeyEvent.VK_Y: case KeyEvent.VK_Z: case KeyEvent.VK_SPACE: case KeyEvent.VK_BACK_SPACE:
					case KeyEvent.VK_ESCAPE: case KeyEvent.VK_SHIFT: case KeyEvent.VK_ENTER:  case KeyEvent.VK_0:
					case KeyEvent.VK_1: case KeyEvent.VK_2: case KeyEvent.VK_3: case KeyEvent.VK_4: 
					case KeyEvent.VK_5: case KeyEvent.VK_6: case KeyEvent.VK_7: case KeyEvent.VK_8:
					case KeyEvent.VK_9: case KeyEvent.VK_NUMPAD0: case KeyEvent.VK_NUMPAD1: case KeyEvent.VK_NUMPAD2:
					case KeyEvent.VK_NUMPAD3: case KeyEvent.VK_NUMPAD4: case KeyEvent.VK_NUMPAD5: case KeyEvent.VK_NUMPAD6:
					case KeyEvent.VK_NUMPAD7: case KeyEvent.VK_NUMPAD8: case KeyEvent.VK_NUMPAD9: case KeyEvent.VK_COMMA:
					case KeyEvent.VK_PERIOD: /*case KeyEvent.VK_BACK_SLASH:*/ case KeyEvent.VK_UP: case KeyEvent.VK_DOWN:
					case KeyEvent.VK_LEFT: case KeyEvent.VK_RIGHT: /*case KeyEvent.VK_KP_UP: case KeyEvent.VK_KP_DOWN:
					case KeyEvent.VK_KP_LEFT: case KeyEvent.VK_KP_RIGHT:*/
						controlsController.changeButton(key);
						Game.escapePressedNegateAction = false;
						Game.enterButtonPushedDown = false;
						break;
					default:
						break;
					}
			}
			else {
				switch(key) {
				case KeyEvent.VK_ENTER: case KeyEvent.VK_SPACE:
					Game.enterButtonPushedDown = false;
					if(!Game.keysAreInUse) {
						Game.keysAreInUse = true;
						break;
					}
					if(!Game.escapePressedNegateAction) {
						if(Game.selectorButtonPosition == -1) {
							Game.selectorButtonPosition = -4;
							Game.State = Game.STATE.SETTINGS;
							if(Game.smb3KickSoundLoop.clipIsActive())
								Game.smb3KickSoundLoop.stop();
							Game.smb3KickSoundLoop.play();
						}
						else if(Game.selectorButtonPosition == 0) {
							if(Game.rescanButtonCounter < 10) {
								ReadAllEvents.resettingControllerEnvironment = true;
								DirectAndRawInputEnvironmentPlugin directEnv = new DirectAndRawInputEnvironmentPlugin();
								if(directEnv != null & directEnv.isSupported())
									ControllerEnvironment.setDefaultEnvironment(directEnv);
								ReadAllEvents.controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
								if(Game.smb3Checkmark2SoundLoop.clipIsActive())
									Game.smb3Checkmark2SoundLoop.stop();
								Game.smb3Checkmark2SoundLoop.play();
								Game.rescanButtonCounter++;
							}
							else {
								if(Game.smwErrorSoundLoop.clipIsActive())
									Game.smwErrorSoundLoop.stop();
								Game.smwErrorSoundLoop.play();
							}
						}
						else if(Game.selectorButtonPosition == -26) {
							Game.upKey = KeyEvent.VK_W;
							Game.downKey = KeyEvent.VK_S;
							Game.leftKey = KeyEvent.VK_A;
							Game.rightKey = KeyEvent.VK_D;
							Game.shootKey = KeyEvent.VK_SPACE;
							Game.itemKey = KeyEvent.VK_E;
							Game.pauseKey = KeyEvent.VK_ENTER;
							Game.cancelKey = KeyEvent.VK_ESCAPE;
							XInputDevice.upKey = XInputConstants.XINPUT_GAMEPAD_DPAD_UP;
							XInputDevice.downKey = XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN;
							XInputDevice.leftKey = XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT;
							XInputDevice.rightKey = XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT;
							XInputDevice.shootKey = XInputConstants.XINPUT_GAMEPAD_A;
							XInputDevice.itemKey = XInputConstants.XINPUT_GAMEPAD_X;
							XInputDevice.pauseKey = XInputConstants.XINPUT_GAMEPAD_START;
							XInputDevice.cancelKey = XInputConstants.XINPUT_GAMEPAD_B;
							XInputDevice.up = XInputConstants.XINPUT_GAMEPAD_DPAD_UP;
							XInputDevice.down = XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN;
							XInputDevice.left = XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT;
							XInputDevice.right = XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT;
							XInputDevice.a = XInputConstants.XINPUT_GAMEPAD_A;
							XInputDevice.b = XInputConstants.XINPUT_GAMEPAD_B;
							XInputDevice.x = XInputConstants.XINPUT_GAMEPAD_X;
							XInputDevice.y = XInputConstants.XINPUT_GAMEPAD_Y;
							XInputDevice.lShoulder = XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER;
							XInputDevice.rShoulder = XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER;
							XInputDevice.lThumb = XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB;
							XInputDevice.rThumb = XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB;
							XInputDevice.start = XInputConstants.XINPUT_GAMEPAD_START;
							XInputDevice.back = XInputConstants.XINPUT_GAMEPAD_BACK;
							XInputDevice.guide = XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON;
							XInputDevice.unknown = XInputConstants.XINPUT_GAMEPAD_UNKNOWN;
							XInputDevice.lTrigger = XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER;
							XInputDevice.rTrigger = XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER;
							SwitchedValues.resetControlsfromResetButton();
							Game.writeOnceToSettings = true;
							Game.writeOnceProperty = "upKey";
							Game.writeOnceString = String.valueOf(KeyEvent.VK_W);
							Game.writeMultipleProperty.add("downKey");
							Game.writeMultipleString.add(String.valueOf(KeyEvent.VK_S));
							Game.writeMultipleProperty.add("leftKey");
							Game.writeMultipleString.add(String.valueOf(KeyEvent.VK_A));
							Game.writeMultipleProperty.add("rightKey");
							Game.writeMultipleString.add(String.valueOf(KeyEvent.VK_D));
							Game.writeMultipleProperty.add("shootKey");
							Game.writeMultipleString.add(String.valueOf(KeyEvent.VK_SPACE));
							Game.writeMultipleProperty.add("itemKey");
							Game.writeMultipleString.add(String.valueOf(KeyEvent.VK_E));
							Game.writeMultipleProperty.add("pauseKey");
							Game.writeMultipleString.add(String.valueOf(KeyEvent.VK_ENTER));
							Game.writeMultipleProperty.add("cancelKey");
							Game.writeMultipleString.add(String.valueOf(KeyEvent.VK_ESCAPE));
							Game.writeMultipleProperty.add("upKeyXInput");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_UP));
							Game.writeMultipleProperty.add("downKeyXInput");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN));
							Game.writeMultipleProperty.add("leftKeyXInput");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT));
							Game.writeMultipleProperty.add("rightKeyXInput");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT));
							Game.writeMultipleProperty.add("shootKeyXInput");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_A));
							Game.writeMultipleProperty.add("itemKeyXInput");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_X));
							Game.writeMultipleProperty.add("pauseKeyXInput");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_START));
							Game.writeMultipleProperty.add("cancelKeyXInput");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_B));
							Game.writeMultipleProperty.add("upButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_UP));
							Game.writeMultipleProperty.add("downButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN));
							Game.writeMultipleProperty.add("leftButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT));
							Game.writeMultipleProperty.add("rightButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT));
							Game.writeMultipleProperty.add("aButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_A));
							Game.writeMultipleProperty.add("bButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_B));
							Game.writeMultipleProperty.add("xButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_X));
							Game.writeMultipleProperty.add("yButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_Y));
							Game.writeMultipleProperty.add("lShoulderButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER));
							Game.writeMultipleProperty.add("rShoulderButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER));
							Game.writeMultipleProperty.add("lThumbButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB));
							Game.writeMultipleProperty.add("rThumbButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB));
							Game.writeMultipleProperty.add("startButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_START));
							Game.writeMultipleProperty.add("backButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_BACK));
							Game.writeMultipleProperty.add("guideButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON));
							Game.writeMultipleProperty.add("unknownButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_UNKNOWN));
							Game.writeMultipleProperty.add("lTriggerButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER));
							Game.writeMultipleProperty.add("rTriggerButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER));
							Game.writeMultipleProperty.add("upKeyDirectInput");
							Game.writeMultipleString.add(SwitchedValues.upKey);
							Game.writeMultipleProperty.add("downKeyDirectInput");
							Game.writeMultipleString.add(SwitchedValues.downKey);
							Game.writeMultipleProperty.add("leftKeyDirectInput");
							Game.writeMultipleString.add(SwitchedValues.leftKey);
							Game.writeMultipleProperty.add("rightKeyDirectInput");
							Game.writeMultipleString.add(SwitchedValues.rightKey);
							Game.writeMultipleProperty.add("shootKeyDirectInput");
							Game.writeMultipleString.add(SwitchedValues.shootKey);
							Game.writeMultipleProperty.add("itemKeyDirectInput");
							Game.writeMultipleString.add(SwitchedValues.itemKey);
							Game.writeMultipleProperty.add("pauseKeyDirectInput");
							Game.writeMultipleString.add(SwitchedValues.pauseKey);
							Game.writeMultipleProperty.add("cancelKeyDirectInput");
							Game.writeMultipleString.add(SwitchedValues.cancelKey);
							Game.settingsSetup = false;
							if(Game.smb3TailSoundLoop.clipIsActive())
								Game.smb3TailSoundLoop.stop();
							Game.smb3TailSoundLoop.play();
						}
						else if(Game.selectorButtonPosition < -1 && Game.selectorButtonPosition > -26) {
							if(Game.selectorButtonPosition<= -18 && -25 <= Game.selectorButtonPosition) {
								Game.gameControllerInUse = true;
								Game.gameControllerInUseDI = true;
								Game.gameControllerSwitchBack = true;
								Game.gameControllerSwitchBackDI = true;
							}
							else if(Game.selectorButtonPosition<= -10 && -17 <= Game.selectorButtonPosition) {
								Game.gameControllerInUse = true;
								Game.gameControllerSwitchBack = true;
							}
							ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
							ControlsController.buttonToChange = Game.selectorButtonPosition;
						}
					}
					Game.escapePressedNegateAction = false;
					break;
				case KeyEvent.VK_ESCAPE:
					if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
						Game.selectorBPMP = Game.selectorButtonPosition;
						Game.escapePressedNegateAction = false;
						if(Game.keysAreInUse)
							Game.keysAreInUse = false;
						else
							Game.keysAreInUse = true;
						break;
					}
					break;
				default:
					break;
				}
			}
		}
		else if(State == STATE.TRACKLIST) {
			switch(key) {
			case KeyEvent.VK_ENTER: case KeyEvent.VK_SPACE:
				Game.enterButtonPushedDown = false;
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
					break;
				}
				if(!Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -1:
						Game.selectorButtonPosition = -5;
						Game.State = Game.STATE.SETTINGS;
						if(Game.smb3KickSoundLoop.clipIsActive())
							Game.smb3KickSoundLoop.stop();
						Game.smb3KickSoundLoop.play();
						break;
					case -2:
						if(Game.gameTrackPosition > 0)
							Game.gameTrackPosition--;
						else
							Game.gameTrackPosition = 7;//Set to Max Tracks
						Game.gameTrackNumber = Game.makeTransparent(Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.gameTrackPosition+1)), 20, 20));
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
						break;
					case -3:
						if(Game.menuTrackPosition > 0)
							Game.menuTrackPosition--;
						else
							Game.menuTrackPosition = 4;//Set to Max Tracks
						Game.menuTrackNumber = Game.makeTransparent(Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.menuTrackPosition+1)), 20, 20));
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
						break;
					case -4:
						if(Game.gameTrackPosition == 7)//Max Tracks
							Game.gameTrackPosition = 0;
						else 
							Game.gameTrackPosition++;
						Game.gameTrackNumber = Game.makeTransparent(Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.gameTrackPosition+1)), 20, 20));
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
						break;
					case -5:
						if(Game.menuTrackPosition == 4)//Max Tracks
							Game.menuTrackPosition = 0;
						else 
							Game.menuTrackPosition++;
						Game.menuTrackNumber = Game.makeTransparent(Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.menuTrackPosition+1)), 20, 20));
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
						break;
					case -6://play1
						boolean b = true;
						switch(Game.gameTrackPosition) {
						case 4:
							if(!Game.track4Unlocked) {
								if(Game.smwErrorSoundLoop.clipIsActive())
									Game.smwErrorSoundLoop.stop();
								Game.smwErrorSoundLoop.play();
								b = false;
							}
							break;
						case 5:
							if(!Game.track5Unlocked) {
								if(Game.smwErrorSoundLoop.clipIsActive())
									Game.smwErrorSoundLoop.stop();
								Game.smwErrorSoundLoop.play();
								b = false;
							}
							break;
						case 6:
							if(!Game.track6Unlocked) {
								if(Game.smwErrorSoundLoop.clipIsActive())
									Game.smwErrorSoundLoop.stop();
								Game.smwErrorSoundLoop.play();
								b = false;
							}
							break;
						default:
							break;
						}
						if(b)
							TrackController.readyToSwitch1 = true;
						break;
					case -7://play2
						boolean bb = true;
						switch(Game.menuTrackPosition) {
						case 4:
							if(!Game.menuTrack4Unlocked) {
								if(Game.smwErrorSoundLoop.clipIsActive())
									Game.smwErrorSoundLoop.stop();
								Game.smwErrorSoundLoop.play();
								bb = false;
							}
							break;
						default:
							break;
						}
						if(bb)
							TrackController.readyToSwitch2 = true;
						break;
					case -8://stop1
						if(Game.gameTrackCurrentlyPlaying != -1) {// && Game.gameTrackCurrentlyPlaying == Game.gameTrackPosition) {
							TrackController.trackSetup = false;
							Game.trackCurrentlyPlaying = false;
							Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).stop();
							Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).setFramePosition(0);
						}
						else
							Game.gameSoundLoops.get(Game.gameTrackPosition).setFramePosition(0);
						if(Game.smb3Checkmark2SoundLoop.clipIsActive())
							Game.smb3Checkmark2SoundLoop.stop();
						Game.smb3Checkmark2SoundLoop.play();
						break;
					case -9://stop2
						if(Game.trackCurrentlyPlaying && Game.menuTrackCurrentlyPlaying != -1) { //&& Game.menuTrackCurrentlyPlaying == Game.menuTrackPosition) {
							TrackController.trackSetup = false;
							Game.trackCurrentlyPlaying = false;
							Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).stop();
							Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).setFramePosition(0);
						}
						else if(Game.menuTrackCurrentlyPlaying != -1)//Game.menuTrackPosition != Game.menuSoundLoopRandomizer)
							Game.menuSoundLoops.get(Game.menuTrackPosition).setFramePosition(0);
						if(Game.smb3Checkmark2SoundLoop.clipIsActive())
							Game.smb3Checkmark2SoundLoop.stop();
						Game.smb3Checkmark2SoundLoop.play();
						break;
					case -10://set1
						String s = "";
						String ss = "";
						switch(Game.gameTrackPosition) {
						case 0:
							s = "gameTrack1Set";
							if(Game.gameTrack1Set) {
								Game.gameTrack1Set = false;
								ss = "false";
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.gameTrack1Set = true;
								ss = "true";
							}
							break;
						case 1:
							s = "gameTrack2Set";
							if(Game.gameTrack2Set) {
								Game.gameTrack2Set = false;
								ss = "false";
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.gameTrack2Set = true;
								ss = "true";
							}
							break;
						case 2:
							s = "gameTrack3Set";
							if(Game.gameTrack3Set) {
								Game.gameTrack3Set = false;
								ss = "false";
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.gameTrack3Set = true;
								ss = "true";
							}
							break;
						case 3:
							s = "gameTrack4Set";
							if(Game.gameTrack4Set) {
								Game.gameTrack4Set = false;
								ss = "false";
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.gameTrack4Set = true;
								ss = "true";
							}
							break;
						case 4:
							s = "gameTrack5Set";
							if(Game.gameTrack5Set || !Game.track4Unlocked) {
								if(!Game.track4Unlocked) {
									if(Game.smwErrorSoundLoop.clipIsActive())
										Game.smwErrorSoundLoop.stop();
									Game.smwErrorSoundLoop.play();
								}
								else {
									if(Game.smbPopSoundLoop.clipIsActive())
										Game.smbPopSoundLoop.stop();
									Game.smbPopSoundLoop.play();
								}
								Game.gameTrack5Set = false;
								ss = "false";
							}
							else {
								Game.gameTrack5Set = true;
								ss = "true";
							}
							break;
						case 5:
							s = "gameTrack6Set";
							if(Game.gameTrack6Set || !Game.track5Unlocked) {
								if(!Game.track5Unlocked) {
									if(Game.smwErrorSoundLoop.clipIsActive())
										Game.smwErrorSoundLoop.stop();
									Game.smwErrorSoundLoop.play();
								}
								else {
									if(Game.smbPopSoundLoop.clipIsActive())
										Game.smbPopSoundLoop.stop();
									Game.smbPopSoundLoop.play();
								}
								Game.gameTrack6Set = false;
								ss = "false";
							}
							else {
								Game.gameTrack6Set = true;
								ss = "true";
							}
							break;
						case 6:
							s = "gameTrack7Set";
							if(Game.gameTrack7Set || !Game.track6Unlocked) {
								if(!Game.track6Unlocked) {
									if(Game.smwErrorSoundLoop.clipIsActive())
										Game.smwErrorSoundLoop.stop();
									Game.smwErrorSoundLoop.play();
								}
								else {
									if(Game.smbPopSoundLoop.clipIsActive())
										Game.smbPopSoundLoop.stop();
									Game.smbPopSoundLoop.play();
								}
								Game.gameTrack7Set = false;
								ss = "false";
							}
							else {
								Game.gameTrack7Set = true;
								ss = "true";
							}
							break;
						case 7:
							s = "gameTrack8Set";
							if(Game.gameTrack8Set) {
								Game.gameTrack8Set = false;
								ss = "false";
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.gameTrack8Set = true;
								ss = "true";
							}
							break;
						default:
							break;
						}
						if(ss.equals("true")) {
							if(Game.smb3ItemSoundLoop.clipIsActive())
								Game.smb3ItemSoundLoop.stop();
							Game.smb3ItemSoundLoop.play();
						}
						if(!s.equals("") && !ss.equals("")) {
							try {
								LeaderboardController.writeToSettings(s, ss);
							} catch (IOException e1) {
							}
							if(ss.equals("true") && Game.soundRandomizer == 8 && Game.gameTrackCurrentlyPlaying == -1  && Game.menuTrackCurrentlyPlaying == -1) {
								int randomIPlus = 0;
								if(Game.track4Unlocked)
									randomIPlus++;
								if(Game.track5Unlocked)
									randomIPlus++;
								if(Game.track6Unlocked)
									randomIPlus++;
								Random rand = new Random();
								Game.soundRandomizer = rand.nextInt(7) + (-3+randomIPlus);
								Game.soundRandomizer = VolumeSlider.gameTrackSetup(Game.soundRandomizer);
								//setsetup[VolumeSlider.TrackSetup]
								if(Game.soundRandomizer != -4) {
									Game.gameSoundLoops.get(Game.soundRandomizer).stop();
									Game.gameSoundLoops.get(Game.soundRandomizer).setSoundLoopBoolean(true);
									Game.gameSoundLoops.get(Game.soundRandomizer).setFramePosition(0);
//									Game.gameSoundLoops.get(Game.soundRandomizer).play();
								}
								else
									Game.soundRandomizer = 8;
							}
							else if(ss.equals("false")) {  
								if(Game.gameTrack1Set == false && Game.gameTrack2Set == false && Game.gameTrack3Set == false && 
									Game.gameTrack4Set == false && Game.gameTrack5Set == false && Game.gameTrack6Set == false && 
									Game.gameTrack7Set == false && Game.gameTrack8Set == false && Game.soundRandomizer != 8 &&
									Game.soundRandomizer != -4) 
								Game.soundRandomizer = 8;
							}
						}
						break;
					case -11://set2
						String string1 = "";
						String string2 = "";
						switch(Game.menuTrackPosition) {
						case 0:
							string1 = "menuTrack1Set";
							if(Game.menuTrack1Set) {
								Game.menuTrack1Set = false;
								string2 = "false";
								if(Game.menuSoundLoopRandomizer == 0) 
									Game.resetMenuSound();
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.menuTrack1Set = true;
								string2 = "true";
							}
							break;
						case 1:
							string1 = "menuTrack2Set";
							if(Game.menuTrack2Set) {
								Game.menuTrack2Set = false;
								string2 = "false";
								if(Game.menuSoundLoopRandomizer == 1) 
									Game.resetMenuSound();
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.menuTrack2Set = true;
								string2 = "true";
							}
							break;
						case 2:
							string1 = "menuTrack3Set";
							if(Game.menuTrack3Set) {
								Game.menuTrack3Set = false;
								string2 = "false";
								if(Game.menuSoundLoopRandomizer == 2) 
									Game.resetMenuSound();
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.menuTrack3Set = true;
								string2 = "true";
							}
							break;
						case 3:
							string1 = "menuTrack4Set";
							if(Game.menuTrack4Set) {
								Game.menuTrack4Set = false;
								string2 = "false";
								if(Game.menuSoundLoopRandomizer == 3) 
									Game.resetMenuSound();
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.menuTrack4Set = true;
								string2 = "true";
							}
							break;
						case 4:
							string1 = "menuTrack5Set";
							if(Game.menuTrack5Set || !Game.menuTrack4Unlocked) {
								if(!Game.menuTrack4Unlocked) {
									if(Game.smwErrorSoundLoop.clipIsActive())
										Game.smwErrorSoundLoop.stop();
									Game.smwErrorSoundLoop.play();
								}
								else {
									if(Game.smbPopSoundLoop.clipIsActive())
										Game.smbPopSoundLoop.stop();
									Game.smbPopSoundLoop.play();
								}
								Game.menuTrack5Set = false;
								string2 = "false";
								if(Game.menuSoundLoopRandomizer == 4) 
									Game.resetMenuSound();
							}
							else {
								Game.menuTrack5Set = true;
								string2 = "true";
							}
							break;
						default:
							break;
						}
						if(string2.equals("true")) {
							if(Game.smb3ItemSoundLoop.clipIsActive())
								Game.smb3ItemSoundLoop.stop();
							Game.smb3ItemSoundLoop.play();
						}
						if(!string1.equals("") && !string2.equals("")) {
							try {
								LeaderboardController.writeToSettings(string1, string2);
							} catch (IOException e1) {
							}

							if(string2.equals("true") && Game.menuSoundLoopRandomizer == 5 && Game.gameTrackCurrentlyPlaying == -1  && Game.menuTrackCurrentlyPlaying == -1) {
								Random rand = new Random();
								Game.menuSoundLoopRandomizer = rand.nextInt(5);
								VolumeSlider.adjustVolume(Game.menuSoundLoops, Game.gameSoundLoops);
								Game.menuSoundLoopRandomizer = VolumeSlider.menuTrackSetup(Game.menuSoundLoopRandomizer);
								if(Game.menuSoundLoopRandomizer == -2)
									Game.menuSoundLoopRandomizer = 5;
								if(Game.menuSoundLoopRandomizer == 2) 
//									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(864007, 1728014);//Seconds.millisec * 44100
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(432004, 864007);//Seconds.millisec * 22050
								else if(Game.menuSoundLoopRandomizer == 3) 
//									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(464196, 1949705);//Seconds.millisec * 44100
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(232098, 974853);//Seconds.millisec * 22050
								else if(Game.menuSoundLoopRandomizer == 4)
//									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(545561, 2291391);//Seconds.millisec * 44100
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(272781, 1145696);//Seconds.millisec * 22050
								else
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loop();
								Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).setSoundLoopBoolean(true);
							}
							else if(string2.equals("false")) {
								if(Game.gameTrackCurrentlyPlaying == -1  && Game.menuTrackCurrentlyPlaying == -1 && 
									Game.menuTrack1Set == false && Game.menuTrack2Set == false && Game.menuTrack3Set == false && 
									Game.menuTrack4Set == false && Game.menuTrack5Set == false && Game.menuSoundLoopRandomizer != 5 &&
									Game.menuSoundLoopRandomizer != -2) {
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).stop();
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).setFramePosition(0);
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).setSoundLoopBoolean(false);
									Game.menuSoundLoopRandomizer = 5;
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loop();
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).setSoundLoopBoolean(true);
								}
							}
						}
						break;
					}
				}
				Game.escapePressedNegateAction = false;
				break;
			case KeyEvent.VK_ESCAPE:
				if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
					Game.selectorBPMP = Game.selectorButtonPosition;
					Game.escapePressedNegateAction = false;
					if(Game.keysAreInUse)
						Game.keysAreInUse = false;
					else
						Game.keysAreInUse = true;
					break;
				}
				break;
			default:
				break;
			}
		}
		if(key == KeyEvent.VK_ENTER || key == KeyEvent.VK_SPACE) {
			Game.enterButtonPushedDown = false;
			Game.escapePressedNegateAction = false;
		}
	}
	public void deviceInput() {
		if(connectingToServer)
			return;
		XInputComponents components = device.getComponents();
	    XInputButtons buttons = components.getButtons();
	    XInputAxes axes = components.getAxes();
	    XInputComponentsDelta delta = device.getDelta();
	    XInputButtonsDelta buttonsDelta = delta.getButtons();
	    XInputAxesDelta axesDelta = delta.getAxes();
	    int lTrigger = axes.ltRaw;
	    int rTrigger = axes.rtRaw;
	    float lX = axes.lx;
	    float lY = axes.ly;
	    float rX = axes.rx;
	    float rY = axes.ry;
	    float lTriggerFloat = axes.lt;
	    float rTriggerFloat = axes.rt;
	    float lXDelta = axesDelta.getLXDelta();
	    float lYDelta = axesDelta.getLYDelta();
	    float rXDelta = axesDelta.getRXDelta();
	    float rYDelta = axesDelta.getRYDelta();
    	//TRIGGERS
    	if(0.3 < lTrigger && (!XInputDevice.lTriggerPressed || !XInputDevice.lTriggerReleased)) {
	    	XInputDevice.lTriggerPressed = true;
	    	XInputDevice.lTriggerReleased = true;
	    }
	    else if(lTrigger ==0 && XInputDevice.lTriggerPressed){
	    	XInputDevice.lTriggerPressed = false;
	    }
	    if(0.3 < rTrigger && (!XInputDevice.rTriggerPressed || !XInputDevice.rTriggerReleased)) {
	    	XInputDevice.rTriggerPressed = true;
	    	XInputDevice.rTriggerReleased = true;
	    }
	    else if(rTrigger ==0 && XInputDevice.rTriggerPressed){
	    	XInputDevice.rTriggerPressed = false;
	    }
    	//TRIGGERS
	    if(gameControllerInUse == false || gameControllerInUseDI == true) {
	    	joystickTimer = 0;
	    	if(lX > 0.15 || lX < -0.15 || rX > 0.15 || rX < -0.15 || lY > 0.15 || lY < -0.15 || rY > 0.15 || rY < -0.15) {
	 	    	if(Game.State == STATE.SET_SCORE && selectorButtonPosition == 0 ||
	 	    		((State == STATE.TRANSITION_ENTRANCE || State == STATE.TRANSITION_WIN || State == STATE.TRANSITION_ITEM)))
	 	    		Game.keysAreInUse = false;
	 	    	else
	 	    		Game.keysAreInUse = true;
	    		gameControllerInUse = true;
	    		gameControllerInUseDI = false;
	    		XInputDevice.reset();
	    	}
	    	if((!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased) || 
	    			(!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)) {
	    		if(Game.State == STATE.SET_SCORE && selectorButtonPosition == 0 ||
		 	    		((State == STATE.TRANSITION_ENTRANCE || State == STATE.TRANSITION_WIN || State == STATE.TRANSITION_ITEM)))
		 	    		Game.keysAreInUse = false;
	 	    	else
	 	    		Game.keysAreInUse = true;
	    		gameControllerInUse = true;
	    		gameControllerInUseDI = false;
	    		XInputDevice.reset();
	    	}
	    	if(buttonsDelta.isReleased(XInputButton.A) || buttonsDelta.isReleased(XInputButton.B) ||
	    		buttonsDelta.isReleased(XInputButton.X) || buttonsDelta.isReleased(XInputButton.Y) ||
	    		buttonsDelta.isReleased(XInputButton.BACK) || buttonsDelta.isReleased(XInputButton.START) ||
	    		buttonsDelta.isPressed(XInputButton.DPAD_DOWN) || buttonsDelta.isPressed(XInputButton.DPAD_UP) ||		
	    		buttonsDelta.isPressed(XInputButton.DPAD_LEFT) || buttonsDelta.isPressed(XInputButton.DPAD_RIGHT) ||
	    		buttonsDelta.isReleased(XInputButton.LEFT_THUMBSTICK) || buttonsDelta.isReleased(XInputButton.RIGHT_THUMBSTICK) ||
	    		buttonsDelta.isReleased(XInputButton.LEFT_SHOULDER) || buttonsDelta.isReleased(XInputButton.RIGHT_SHOULDER)) {
	    		if(Game.State == STATE.SET_SCORE && selectorButtonPosition == 0 ||
		 	    		((State == STATE.TRANSITION_ENTRANCE || State == STATE.TRANSITION_WIN || State == STATE.TRANSITION_ITEM)))
		 	    		Game.keysAreInUse = false;
	 	    	else {
	 	    		if(buttonsDelta.isPressed(XInputButton.DPAD_UP)) {
	 	    			XInputDevice.upHeld = true;
		 		    	XInputDevice.downHeld = false;
		 		    	XInputDevice.leftHeld = false;
		 		    	XInputDevice.rightHeld = false;
	 					if(joystickTimer == 0)
	 						joystickTimer = System.currentTimeMillis() + 500;
	 					else
	 						joystickTimer = System.currentTimeMillis() + 30;
	 	    		}
	 	    		else if(buttonsDelta.isPressed(XInputButton.DPAD_DOWN)) {
	 	    			XInputDevice.downHeld = true;
		 	   	    	XInputDevice.upHeld = false;
		 		    	XInputDevice.leftHeld = false;
		 		    	XInputDevice.rightHeld = false;
	 					if(joystickTimer == 0)
	 						joystickTimer = System.currentTimeMillis() + 500;
	 					else
	 						joystickTimer = System.currentTimeMillis() + 30;
	 	    		}
	 	    		else if(buttonsDelta.isPressed(XInputButton.DPAD_LEFT)) {
	 	    			XInputDevice.leftHeld = true;
		 	   	    	XInputDevice.upHeld = false;
		 		    	XInputDevice.downHeld = false;
		 		    	XInputDevice.rightHeld = false;
	 					if(joystickTimer == 0)
	 						joystickTimer = System.currentTimeMillis() + 500;
	 					else
	 						joystickTimer = System.currentTimeMillis() + 30;
	 	    		}
	 	    		else if(buttonsDelta.isPressed(XInputButton.DPAD_RIGHT)) {
	 	    			XInputDevice.rightHeld = true;
		 	   	    	XInputDevice.upHeld = false;
		 		    	XInputDevice.downHeld = false;
		 		    	XInputDevice.leftHeld = false;
	 					if(joystickTimer == 0)
	 						joystickTimer = System.currentTimeMillis() + 500;
	 					else
	 						joystickTimer = System.currentTimeMillis() + 30;
	 	    		}
	 	    		Game.keysAreInUse = true;
	 	    	}
	    		gameControllerInUse = true;
	    		gameControllerInUseDI = false;
	    		XInputDevice.reset();
	    	}
	    	XInputDevice.upHeld = false;
	    	XInputDevice.downHeld = false;
	    	XInputDevice.leftHeld = false;
	    	XInputDevice.rightHeld = false;
    		return;
	    }
	    if(gameControllerInUseDI)
	    	return;
//	    System.out.println("lTriggerPressed = "+XInputDevice.lTriggerPressed+" lTriggerReleased = "+XInputDevice.lTriggerReleased);
	    //TEST
    	if(ControlsController.buttonChangeTimer < System.currentTimeMillis()) {
    		XInputDevice.emulatedControls(this,lTrigger,rTrigger);
    	}
//	    if(0.3 < lTrigger && (!XInputDevice.lTriggerPressed || !XInputDevice.lTriggerReleased)) {
//	    	XInputDevice.lTriggerPressed = true;
//	    	XInputDevice.lTriggerReleased = true;
//	    }
//	    else if(lTrigger == 0 && XInputDevice.lTriggerPressed){
//	    	XInputDevice.lTriggerPressed = false;
//	    }
//	    if(0.3 < rTrigger && (!XInputDevice.rTriggerPressed || !XInputDevice.rTriggerReleased)) {
//	    	XInputDevice.rTriggerPressed = true;
//	    	XInputDevice.rTriggerReleased = true;
//	    }
//	    else if(rTrigger == 0 && XInputDevice.rTriggerPressed){
//	    	XInputDevice.rTriggerPressed = false;
//	    }
//	    if(!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased){
//	    	if(XInputDevice.a == XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER)
//	    		XInputButtons
//	    }
	    //TEST
//	    if(device.getDelta().getButtons().isPressed(XInputButton.DPAD_UP)) {
//			XInputDevice.upHeld = true;
//		}
//		if(device.getDelta().getButtons().isPressed(XInputButton.DPAD_DOWN)) {
//			XInputDevice.downHeld = true;
//		}
//		if(device.getDelta().getButtons().isPressed(XInputButton.DPAD_LEFT)) {
//			XInputDevice.leftHeld = true;
//		}
//		if(device.getDelta().getButtons().isPressed(XInputButton.DPAD_RIGHT)) {
//			XInputDevice.rightHeld = true;
//		}
//	    if(device.getDelta().getButtons().isReleased(XInputButton.DPAD_UP)) {
//			XInputDevice.upHeld = false;
//		}
//		if(device.getDelta().getButtons().isReleased(XInputButton.DPAD_DOWN)) {
//			XInputDevice.downHeld = false;
//		}
//		if(device.getDelta().getButtons().isReleased(XInputButton.DPAD_LEFT)) {
//			XInputDevice.leftHeld = false;
//		}
//		if(device.getDelta().getButtons().isReleased(XInputButton.DPAD_RIGHT)) {
//			XInputDevice.rightHeld = false;
//		}
	    if(State == STATE.MENU && gameControllerInUse) {
	    	//if((lY > .65 || (device.getComponents().getButtons().up)) && joystickTimer < System.currentTimeMillis()) {
	    	if((lY > .65 || (XInputDevice.upHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		if((XInputDevice.upHeld) && cheatTimer < System.currentTimeMillis()) {
	    			cheatString = "u";
	    			cheatTimer = System.currentTimeMillis() + 1500;
	    		}
	    		else if((XInputDevice.upHeld) && System.currentTimeMillis() < cheatTimer && cheatString.equals("u")) {
	    			cheatString = "uu";
	    			cheatTimer = System.currentTimeMillis() + 1500;
	    		}
	    		else if((XInputDevice.upHeld) && System.currentTimeMillis() < cheatTimer) {
	    			cheatString = "";
	    			cheatTimer = System.currentTimeMillis() + 1500;
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
				if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	//else if((lY < -.65 || (device.getComponents().getButtons().down)) && joystickTimer < System.currentTimeMillis()) {
	    	else if((lY < -.65 || (XInputDevice.downHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		if((XInputDevice.downHeld) && System.currentTimeMillis() < cheatTimer && cheatString.equals("uu")) {
	    			cheatString = "uud";
	    			cheatTimer = System.currentTimeMillis() + 1500;
	    		}
	    		else if((XInputDevice.downHeld) && System.currentTimeMillis() < cheatTimer && cheatString.equals("uud")) {
	    			cheatString = "uudd";
	    			cheatTimer = System.currentTimeMillis() + 1500;
	    		}
	    		else if((XInputDevice.downHeld) && System.currentTimeMillis() < cheatTimer) {
	    			cheatString = "";
	    			cheatTimer = System.currentTimeMillis() + 1500;
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
				if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	//if((lX < -.65 || (device.getComponents().getButtons().left)) && joystickTimer < System.currentTimeMillis()) {
	    	if((lX < -.65 || (XInputDevice.leftHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		if((XInputDevice.leftHeld) && System.currentTimeMillis() < cheatTimer && cheatString.equals("uudd")) {
	    			cheatString = "uuddl";
	    			cheatTimer = System.currentTimeMillis() + 1500;
	    		}
	    		else if((XInputDevice.leftHeld) && System.currentTimeMillis() < cheatTimer && cheatString.equals("uuddlr")) {
	    			cheatString = "uuddlrl";
	    			cheatTimer = System.currentTimeMillis() + 1500;
	    		}
	    		else if((XInputDevice.leftHeld) && System.currentTimeMillis() < cheatTimer) {
	    			cheatString = "";
	    			cheatTimer = System.currentTimeMillis() + 1500;
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
				if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	//else if((lX > .65 || (device.getComponents().getButtons().right)) && joystickTimer < System.currentTimeMillis()) {
	    	else if((lX > .65 || (XInputDevice.rightHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		if((XInputDevice.rightHeld) && System.currentTimeMillis() < cheatTimer && cheatString.equals("uuddl")) {
	    			cheatString = "uuddlr";
	    			cheatTimer = System.currentTimeMillis() + 1500;
	    		}
	    		else if((XInputDevice.rightHeld) && System.currentTimeMillis() < cheatTimer && cheatString.equals("uuddlrl")) {
	    			cheatString = "uuddlrlr";
	    			cheatTimer = System.currentTimeMillis() + 1500;
	    		}
	    		else if((XInputDevice.rightHeld) && System.currentTimeMillis() < cheatTimer) {
	    			cheatString = "";
	    			cheatTimer = System.currentTimeMillis() + 1500;
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
				if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
//	    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
//	    		!(device.getComponents().getButtons().right) && !(device.getComponents().getButtons().left)&&
//	    		!(device.getComponents().getButtons().up) && !(device.getComponents().getButtons().down)) {
//	    		joystickTimer = 0;
//	    	}
	    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
	    		!(XInputDevice.rightHeld) && !(XInputDevice.leftHeld)&&
	    		!(XInputDevice.upHeld) && !(XInputDevice.downHeld)) {
	    		joystickTimer = 0;
		    }
	    	if(XInputDevice.shootPressed ||
		    	XInputDevice.pausePressed) {
	    		if(!cheatString.equals("uuddlrlrb") && !cheatString.equals("uuddlrlrba"))
	    			Game.enterButtonPushedDown = true;
	    	}
	    	if((!XInputDevice.shootPressed && XInputDevice.shootReleased)||
	    			(!XInputDevice.pausePressed && XInputDevice.pauseReleased)) {
//	    		if(device.getDelta().getButtons().isReleased(XInputButton.B) &&
	    		if((!XInputDevice.shootPressed && XInputDevice.shootReleased) && 
	    				System.currentTimeMillis() < cheatTimer && cheatString.equals("uuddlrlrb")) {
	    			cheatString = "uuddlrlrba";
	    			cheatTimer = System.currentTimeMillis() + 1500;
	    		}
	    		else if((!XInputDevice.pausePressed && XInputDevice.pauseReleased) && 
	    				System.currentTimeMillis() < cheatTimer && cheatString.equals("uuddlrlrba")) {
	    			if(smwCheatFullSoundLoop.clipIsActive())
						smwCheatFullSoundLoop.stop();
					smwCheatFullSoundLoop.play();
					cheatString = "";
					cheatTimer = System.currentTimeMillis() + 1500;
					currentlySelectedFireball = 5;
					currentlySelectedCharacterSkin = 5;
					fireballPosition = 5;
					characterSkinPosition = 5;
					p.changeAnimations(5);
					if(fireballSFX != null)
						fireballSFX.close();
					if(fireballPopSFX != null)
						fireballPopSFX.close();
					fireballSFX = new SoundLoops("res/Sounds/SFX/contrashootsfx.wav");
					fireballPopSFX = new SoundLoops("res/Sounds/SFX/contrafireballpopsfx.wav");
					VolumeSlider.adjustSFX(fireballSFX);
					VolumeSlider.adjustSFX(fireballPopSFX);
					skinNumber = null;
	    			cheatTimer = System.currentTimeMillis() + 1500;
	    			return;
	    		}
	    		else {
	    			if(System.currentTimeMillis() < cheatTimer) {
		    		cheatString = "";
		    		cheatTimer = System.currentTimeMillis() + 1500;
	    			}
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
								this.closeGame();
								System.exit(1);
								break;
						}
					}
					Game.escapePressedNegateAction = false;
	    		}
	    	}
	    	if(XInputDevice.cancelPressed || XInputDevice.itemPressed) {
	    		if(!cheatString.equals("uuddlrlr")) {
		    		if(Game.mouseIsClickedDown) {
						if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.playHighlighted ||
								Game.shopHighlighted || Game.exitHighlighted || Game.helpHighlighted ||
								Game.settingsHighlighted || Game.leaderboardHighlighted))
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.playClicked = false;
						Game.shopClicked = false;
						Game.exitClicked = false;
						Game.helpClicked = false;
						Game.settingsClicked = false;
						Game.leaderboardClicked = false;
						Game.escapePressedNegateAction = true;
		    		}
		    		else if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
	    		}
	    	}
	    	if((!XInputDevice.cancelPressed && XInputDevice.cancelReleased) || 
	    			(!XInputDevice.itemPressed && XInputDevice.itemReleased)) {
//	    		if(device.getDelta().getButtons().isReleased(XInputButton.B) &&
	    		if((!XInputDevice.cancelPressed && XInputDevice.cancelReleased) &&
	    				System.currentTimeMillis() < cheatTimer && cheatString.equals("uuddlrlr")) {
	    			cheatString = "uuddlrlrb";
	    			cheatTimer = System.currentTimeMillis() + 1500;
	    		}
//	    		else if(System.currentTimeMillis() < cheatTimer) {
//	    			cheatString = "";
//	    			cheatTimer = System.currentTimeMillis() + 1500;
//	    		}
	    		else {
	    			cheatString = "";
	    			cheatTimer = System.currentTimeMillis() + 1500;
		    		if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
						Game.selectorBPMP = Game.selectorButtonPosition;
						Game.escapePressedNegateAction = false;
						if(Game.gameControllerInUse) {
							Game.gameControllerInUse = false;
							Game.keysAreInUse = false;
							if(gameControllerInUseDI)
								gameControllerInUseDI = false;
						}
						else {
							Game.gameControllerInUse = true;
							Game.keysAreInUse = true;
						}
		    		}
	    		}
	    	}
	    	/*
	    	if(device.getDelta().getButtons().isReleased(XInputButton.A)) {
	    		System.out.println(device.x);
	    		XInputDevice.a = XInputConstants.XINPUT_GAMEPAD_X;
	    		//device.changeButton("x", XInputConstants.XINPUT_GAMEPAD_A);
	    		
	    		try {
					LeaderboardController.writeToSettings("aButton", String.valueOf(XInputConstants.XINPUT_GAMEPAD_X));
				} catch (IOException e) {}
				Game.settingsSetup = false;
	    	}*/
	    }
	    else if(State == STATE.GAME && !(Game.userHasPaused) && !(System.currentTimeMillis() < pauseSoundFXTimer) && gameControllerInUse) {
	    	if(lX > .65 && lXDelta > -1) {
	    		if(pauseHoldOff) {
	    			if(this.slowingDownTimerLong < System.currentTimeMillis())
	    				pauseHoldOff = false;
	    		}
	    		else {
		    		/*
		    		p.setRunningStartR(true);
					p.setRunningStartL(false);
					if(p.getVelX() == 0 || (slowingDownActivatedl || slowingDownActivatedr))
						p.setRunningStartUp(0);
					*/
		    		if(p.getVelX() < 1.2 || xLBoolean) {
		    			p.setVelX(1.2);
		    			p.animr.setSpeed(10);
		    		}
		    		else if(p.getVelX() >= 3 && p.getVelX() <= 5) {
		    			p.setVelX(p.getVelX() + lX/4);//lX/3
		    			p.animr.setSpeed(8);
		    		}
		    		else if(!xLBoolean)
		    			p.setVelX(p.getVelX() + lX/7);//lX/6
		    		if(p.getVelX() >= 5) {
		    			p.setVelX(5);
		    			p.animr.setSpeed(6);
		    			slowDownForAnalogTimer = System.currentTimeMillis() + 200;
		    		}
		    		/*
		    		if(lX*7 > 5) 
		    			p.setVelX(5);
		    		if(p.getVelX() < 5)
		    			p.setVelX(lX*7);
		    		*/
					xRBoolean = true;
					if(xLBoolean) {
//						runningTimerLong = System.currentTimeMillis();	//No slide after change
//						slowDownForAnalogTimer = 0;						//No slide after change
						xLBoolean = false;
					}
					slowingDownTimerLong = 0;
					slowingDownActivatedl = false;
					slowingDownActivatedr = false;
					if(runningTimerActivatedResponse == false)
						runningTimerActivated = true;
	    		}
	    	}
	    	else if(lX < -0.65 && lXDelta < 1) {
	    		if(pauseHoldOff) {
	    			if(this.slowingDownTimerLong < System.currentTimeMillis())
	    				pauseHoldOff = false;
	    		}
	    		else {
		    		/*
		    		p.setRunningStartR(false);
					p.setRunningStartL(true);
					if(p.getVelX() == 0 || (slowingDownActivatedl || slowingDownActivatedr))
						p.setRunningStartUp(0);
						*/
		    		if(p.getVelX() > -1.2  || xRBoolean) {
		    			p.setVelX(-1.2);
		    			p.animl.setSpeed(10);
		    		}
		    		else if(p.getVelX() <= -3 && p.getVelX() >= -5) {
		    			p.setVelX(p.getVelX()+ lX/4);//lX/3
		    			p.animl.setSpeed(8);
		    		}
		    		else if(!xRBoolean)
		    			p.setVelX(p.getVelX() +lX/7);//lX/6
		    		if(p.getVelX() <= -5) {
		    			p.setVelX(-5);
		    			p.animl.setSpeed(6);
		    			slowDownForAnalogTimer = System.currentTimeMillis() + 200;
		    		}
		    		/*
		    		if(lX*7 < -5)
		    			p.setVelX(-5);
		    		if(p.getVelX() > -5)
		    			p.setVelX(lX*7);
		    			*/
					xLBoolean = true;
					if(xRBoolean) {
//						runningTimerLong = System.currentTimeMillis();	//No slide after change
//						slowDownForAnalogTimer = 0;						//No slide after change
						xRBoolean = false;
					}
					slowingDownTimerLong = 0;
					slowingDownActivatedl = false;
					slowingDownActivatedr = false;
					if(runningTimerActivatedResponse == false)
						runningTimerActivated = true;
	    		}
	    	}
	    	else if(lX < .65 && lX > 0.145 && lXDelta > -.14) {
	    		if(pauseHoldOff) {
	    			if(this.slowingDownTimerLong < System.currentTimeMillis())
	    				pauseHoldOff = false;
	    		}
	    		else {
		    		p.setVelX(lX*5);
		    		p.animr.setSpeed(10);
		    		if(controllerSensitivityTimer == 0) 
		    			controllerSensitivityTimer = System.currentTimeMillis() + 150;
		    		if(controllerSensitivityTimer < System.currentTimeMillis()) {
		    			runningTimerLong = System.currentTimeMillis();
						runningTimerActivatedResponse = false;
		    		}
		    		xRBoolean = true;
		    		xLBoolean = false;
		    		//xRBoolean = false;
		    		//xLBoolean = false;
	    		}
	    	}
	    	else if(lX > -0.65 && lX < -0.145 && lXDelta < 0.14) {
	    		if(pauseHoldOff) {
	    			if(this.slowingDownTimerLong < System.currentTimeMillis())
	    				pauseHoldOff = false;
	    		}
	    		else {
		    		p.setVelX(lX*5);
		    		p.animl.setSpeed(10);
		    		if(controllerSensitivityTimer == 0) 
		    			controllerSensitivityTimer = System.currentTimeMillis() + 150;
		    		if(controllerSensitivityTimer < System.currentTimeMillis()) {
		    			runningTimerLong = System.currentTimeMillis();
						runningTimerActivatedResponse = false;
		    		}
		    		xRBoolean = false;
		    		xLBoolean = true;
		    		//xLBoolean = false;
		    		//xRBoolean = false;
	    		}
	    	}
	    	else if(XInputDevice.leftHeld && !pauseHoldOff) {
	    		p.setRunningStartR(false);
				p.setRunningStartL(true);
				if(p.getVelX() == 0 || (slowingDownActivatedl || slowingDownActivatedr))
					p.setRunningStartUp(0);
				xLBoolean = true;
				xRBoolean = false;
				slowingDownTimerLong = 0;
				slowingDownActivatedl = false;
				slowingDownActivatedr = false;
				if(runningTimerActivatedResponse == false)
					runningTimerActivated = true;
				if(animationTimer1 != 0) {
					p.setX(p.getX()-p.getVelX());
				}
				/*RESTARTS RUNNING SPEED
				if(animationTimer1 != 0) {
					p.setVelX(0);
					p.setRunningStartR(false);
					p.setRunningStartL(false);
				}*/
	    	}
	    	else if(XInputDevice.rightHeld && !pauseHoldOff) {//device.getComponents().getButtons().right) {
	    		p.setRunningStartR(true);
				p.setRunningStartL(false);
				if(p.getVelX() == 0 || (slowingDownActivatedl || slowingDownActivatedr))
					p.setRunningStartUp(0);
				xRBoolean = true;
				xLBoolean = false;
				slowingDownTimerLong = 0;
				slowingDownActivatedl = false;
				slowingDownActivatedr = false;
				if(runningTimerActivatedResponse == false)
					runningTimerActivated = true;
				if(animationTimer1 != 0) {
					p.setX(p.getX()-p.getVelX());
				}
				/*
				if(animationTimer1 != 0) {
					p.setVelX(0);
					p.setRunningStartR(false);
					p.setRunningStartL(false);
				}*/
	    	}
	    	else if(XInputDevice.leftReleased) {
				xLBoolean = false;
				p.setRunningStartL(false);
				if(xRBoolean == true){
					p.setRunningStartUp(1.2);
					p.setVelX(p.getRunningStartUp());
					runningTimerActivated = true;
					p.setRunningStartR(true);
				}
				else{
					if((System.currentTimeMillis() - runningTimerLong > 666/2 || (p.getVelX() <= -5)) && p.getVelX() != 0){													//This activates sliding animation for left side
						slowingDownActivatedl = true;
						slowingDownTimerLong = System.currentTimeMillis() + 200;
						slowingDown = -1.73;
						p.setVelX(slowingDown);
						runningTimerLong = 0;
						runningTimerActivatedResponse = false;
					}
					else
						p.setVelX(0);
				}
				runningTimerActivatedResponse = false;
				if(pauseHoldOff)
					pauseHoldOff = false;
	    	}
	    	else if(XInputDevice.rightReleased) {
				xRBoolean = false;
				p.setRunningStartR(false);
				if(xLBoolean == true){
					p.setRunningStartUp(-1.2);
					p.setVelX(p.getRunningStartUp());
					runningTimerActivated = true;
					p.setRunningStartL(true);
				}
				else{
					if((System.currentTimeMillis() - runningTimerLong > 666/2 || (p.getVelX() >= 5)) && p.getVelX() != 0){														//This activates sliding animation for right side
						slowingDownActivatedr = true;
						slowingDownTimerLong = System.currentTimeMillis() + 200;
						slowingDown = 1.73;
						p.setVelX(slowingDown);
						runningTimerLong = 0;
						runningTimerActivatedResponse = false;
					}
					else
						p.setVelX(0);
				}
				runningTimerActivatedResponse = false;
				if(pauseHoldOff)
					pauseHoldOff = false;
	    	}
	    	else {
	    		if(xLBoolean) {
	    			p.setVelX(0);
					xLBoolean = false;
					if(xRBoolean == true){
						p.setVelX(1.2);
					}
					else{
						if(System.currentTimeMillis() - runningTimerLong > 666/2 || (p.getVelX() <= -5) ||
								System.currentTimeMillis() < slowDownForAnalogTimer){														//This activates sliding animation for left side
							slowingDownActivatedl = true;
							slowingDownTimerLong = System.currentTimeMillis() + 200;
							slowingDown = -1.73;
							p.setVelX(slowingDown);
							runningTimerLong = 0;
							runningTimerActivatedResponse = false;
							controllerSensitivityTimer = 0;
						}
					}
					runningTimerActivatedResponse = false;
	    		}
	    		if(xRBoolean) {
	    			p.setVelX(0);
					xRBoolean = false;
					if(xLBoolean == true){
						p.setVelX(-1.2);
					}
					else{
						if(System.currentTimeMillis() - runningTimerLong > 666/2 || (p.getVelX() >= 5) ||
								System.currentTimeMillis() < slowDownForAnalogTimer){														//This activates sliding animation for right side
							slowingDownActivatedr = true;
							slowingDownTimerLong = System.currentTimeMillis() + 200;
							slowingDown = 1.73;
							p.setVelX(slowingDown);
							runningTimerLong = 0;
							runningTimerActivatedResponse = false;
							controllerSensitivityTimer = 0;
						}
					}
		
					runningTimerActivatedResponse = false;
	    		}
	    		if(p.getVelX() != 0 && p.getVelX() != slowingDown) {
					p.setRunningStartUp(0);			//notsure
					p.setRunningStartR(false);		//notsure
					p.setRunningStartL(false);		//notsure
					runningTimerActivated = false;	//notsure
					runningTimerLong = 0;			//notsure
	    			p.setVelX(0);
					controllerSensitivityTimer = 0;
	    		}
//				if(pauseHoldOff && lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
//			    		!(device.getComponents().getButtons().right) && !(device.getComponents().getButtons().left)&&
//			    		!(device.getComponents().getButtons().up) && !(device.getComponents().getButtons().down))
//					pauseHoldOff = false;
	    		if(pauseHoldOff && lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
			    		!(XInputDevice.rightHeld) && !(XInputDevice.leftHeld)&&
			    		!(XInputDevice.upHeld) && !(XInputDevice.downHeld))
					pauseHoldOff = false;
	    	}
	    	if(controllerSensitivityTimer != 0 && p.getVelX() == 0)
	    		controllerSensitivityTimer = 0;
	    	if (XInputDevice.shootPressed && !isShooting){											//Fireballs
				isShooting = true;
				if(ea.isEmpty() && !paused){
					switch(currentlySelectedFireball) {
						case 0:
							c.addEntity(new Fireball(p.getX(),p.getY() + 32,tex, this));
							break;
						case 1:
							c.addEntity(new GreenShellFireball(p.getX(),p.getY()+32,tex,p.getVelX(),this));
							break;
						case 2:
							c.addEntity(new RedShellFireball(p.getX(),p.getY()+32,tex,p.getVelX(),this));
							break;
						case 3:
							c.addEntity(new BuzzyBeetleShellFireball(p.getX(),p.getY()+32,tex,p.getVelX(),this));
							break;
						case 4:
							c.addEntity(new GloveFireball(p.getX(),p.getY() + 32,tex, this));
							break;
						case 5:
							c.addEntity(new ContraFireball(p.getX(),p.getY() + 32,tex, this));
							break;
						default:
							break;
					}
					animationTimer1 = 10;
					numberOfFireBallsShot++;
					if(fireballSFX.clipIsActive()) {
						fireballSFX.stop();
						fireballSFX.setFramePosition(0);
					}
					fireballSFX.play();
				}
			}
			if(XInputDevice.pausePressed){
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
					if(System.currentTimeMillis() < bowserSpawnSetup && bowserSpawnSetupBoolean == true) {
						bowserSpawnSetup = 0;
						bowserSpawnSetupBoolean = false;
					}
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
	    	if(XInputDevice.shootReleased){
				isShooting = false;
			} 
	    	else if(!XInputDevice.itemPressed && XInputDevice.itemReleased && !paused){
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
						sceneAcknowledgement = false;
						askToSkipSequence = false;
					}
					this.itemName = hud.getItemName();
					hud.setItemObtained(false);
				}
			}
	    	else if(XInputDevice.pauseReleased) {
				if(!Game.keysAreInUse)
					Game.keysAreInUse = true;
			}
	    }
	    else if(State == STATE.GAME && (Game.userHasPaused || System.currentTimeMillis() < pauseSoundFXTimer) && gameControllerInUse) {
	    	if((lY > .65 || (XInputDevice.upHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
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
				if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	else if((lY < -.65 || (XInputDevice.downHeld)) && joystickTimer < System.currentTimeMillis()) {
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
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
				if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	else if(((lX > -0.65 && lX < -0.145 && lXDelta < 0.14) || (XInputDevice.leftReleased) ||
	    			(lX > .145 && keepRunningAfterPauseL)) && joystickTimer < System.currentTimeMillis()) {
	    		if(!keepRunningAfterPauseR) {
					dontRunAfterPause = true;
					xLBoolean = false;
					p.setRunningStartL(false);
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
				if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	else if(((lX < .65 && lX > 0.145 && lXDelta > -.14) || (XInputDevice.rightReleased) ||
	    			(lX < -.145 && keepRunningAfterPauseR)) && joystickTimer < System.currentTimeMillis()) {
	    		if(!keepRunningAfterPauseL) {
					dontRunAfterPause = true;
					xRBoolean = false;
					p.setRunningStartR(false);
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
				if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	else if(lX < -0.65) {
		    		if(slowingDownActivatedl) {
		    			slowingDownActivatedl = false;
		    			slowingDownTimerLong = 0;
						runningTimerLong = System.currentTimeMillis() + (666/2);
						runningTimerActivatedResponse = true;
						slowingDownFromPause = false;
						p.setVelX(-5);
						p.setRunningStartUp(-5);
						p.setRunningStartL(true);
						dontRunAfterPause = false;
		    		}
		    		if(slowingDownActivatedr) {
		    			slowingDownTimerLong = System.currentTimeMillis() + 200;
						slowingDown = 1.73;
						p.setVelX(slowingDown);
						runningTimerLong = 0;
						runningTimerActivatedResponse = false;
						slowingDownFromPause = true;
		    		}
		    		//keepRunningAfterPauseL = true;
	    		
	    	}else if(lX > 0.65) {
		    		if(slowingDownActivatedr) {
		    			slowingDownActivatedr = false;
		    			slowingDownTimerLong = 0;
						runningTimerLong = System.currentTimeMillis() + (666/2);
						runningTimerActivatedResponse = true;
						slowingDownFromPause = false;
						p.setVelX(5);
						p.setRunningStartUp(5);
						p.setRunningStartR(true);
						dontRunAfterPause = false;
		    		}if(slowingDownActivatedl) {
		    			slowingDownTimerLong = System.currentTimeMillis() + 200;
						slowingDown = -1.73;
						p.setVelX(slowingDown);
						runningTimerLong = 0;
						runningTimerActivatedResponse = false;
						slowingDownFromPause = true;
		    		}
		    		//keepRunningAfterPauseR = true;
	    		
	    	}
	    	if((XInputDevice.leftHeld || XInputDevice.rightHeld ||
	    		lX > .145 || lX < -.145) && !xLBoolean && !xRBoolean && !pauseHoldOff)
	    		pauseHoldOff = true;
	    	/*
	    	if((keepRunningAfterPauseL && lX > .145)) {
	    		slowingDownTimerLong = System.currentTimeMillis() + 200;
				slowingDown = -1.73;
				p.setVelX(slowingDown);
				runningTimerLong = 0;
				runningTimerActivatedResponse = false;
				slowingDownFromPause = true;
				slowingDownActivatedl = true;
				pauseHoldOff = true;
	    	}
	    	if((keepRunningAfterPauseR && lX < -.145)) {
	    		slowingDownTimerLong = System.currentTimeMillis() + 200;
				slowingDown = 1.73;
				p.setVelX(slowingDown);
				runningTimerLong = 0;
				runningTimerActivatedResponse = false;
				slowingDownFromPause = true;
				slowingDownActivatedr = true;
	    		pauseHoldOff = true;
	    	}*/
	    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
	    		!(XInputDevice.rightHeld) && !(XInputDevice.leftHeld)&&
	    		!(XInputDevice.upHeld) && !(XInputDevice.downHeld)) {
	    		joystickTimer = 0;
		    }
	    	if(XInputDevice.shootPressed) {
				if(!Game.keysAreInUse) {
					Game.keysAreInUse = true;
				}else {
					Game.enterButtonPushedDown = true;
				}
	    	}
	    	if(XInputDevice.pausePressed ||
		    		XInputDevice.cancelPressed) {
	    		if(Game.mouseIsClickedDown && !XInputDevice.pausePressed) {
					if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.resumeHighlighted ||
							Game.homeHighlighted || Game.exitHighlighted))
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.resumeClicked = false;
					Game.homeClicked = false;
					Game.exitClicked = false;
					Game.escapePressedNegateAction = true;
				}
	    		else if(Game.enterButtonPushedDown && !XInputDevice.pausePressed)
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
	    	}
	    	if(!XInputDevice.shootPressed && XInputDevice.shootReleased) {
	    		Game.enterButtonPushedDown = false;
				setIsShooting(false);
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
							Game.selectorButtonPosition = 0;
							break;
						case 2:
							this.closeGame();
							System.exit(1);
							break;
					}
				}
				Game.escapePressedNegateAction = false;
	    	}
	    }
	    else if((State == STATE.TRANSITION_ENTRANCE || State == STATE.TRANSITION_WIN || State == STATE.CREDITS) && gameControllerInUse) {
	    	if((lY > .65 || (XInputDevice.upHeld) ||
	    		lY < -.65 || (XInputDevice.downHeld) ||
	    		lX > .65 || (XInputDevice.leftHeld) ||
	    		lX < -.65 || (XInputDevice.rightHeld)) && joystickTimer < System.currentTimeMillis()){
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
	    		!(XInputDevice.rightHeld) && !(XInputDevice.leftHeld)&&
	    		!(XInputDevice.upHeld) && !(XInputDevice.downHeld)) {
	    		joystickTimer = 0;
		    }
	    	if(XInputDevice.cancelPressed ||
	    			XInputDevice.itemPressed
		    		) {
		    		if(Game.mouseIsClickedDown) {
						if(!Game.mouseIsOffClickedObjectAndHeldDown && Game.skipHighlighted)
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.skipClicked = false;
						Game.escapePressedNegateAction = true;
					}
					if(!Game.keysAreInUse) {//|| sceneAcknowledgement == true){
						Game.keysAreInUse = true;
						sceneAcknowledgement = true;
					}
					else {
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					}
		    	}
		    	if(XInputDevice.pausePressed ||
			    		XInputDevice.shootPressed) {
		    		if(!Game.keysAreInUse) {//|| sceneAcknowledgement == true){
						Game.keysAreInUse = true;
						sceneAcknowledgement = true;
						Game.enterButtonPushedDown = true;
					}
					else if(Game.escapePressedNegateAction == false)
						Game.enterButtonPushedDown = true;
		    	}
		    	if((!XInputDevice.cancelPressed && XInputDevice.cancelReleased) || 
		    			(!XInputDevice.itemPressed && XInputDevice.itemReleased)
			    		) {
		    		if(Game.mouseIsClickedDown || Game.enterButtonPushedDown) {
					}
		    		else if(!askToSkipSequence) {
						askToSkipSequence = true;
					}
					else {
						askToSkipSequence = false;
						Game.keysAreInUse = false;
						Game.gameControllerInUse = false;
						Game.gameControllerInUseDI = false;
						sceneAcknowledgement = false;
					}
		    	}
		    	if((!XInputDevice.pausePressed && XInputDevice.pauseReleased) || 
		    			(!XInputDevice.shootPressed && XInputDevice.shootReleased)) {
		    		if(!askToSkipSequence) {
		    			enterButtonPushedDown = false;
						askToSkipSequence = true;
					}
		    		else {
		    			if(askToSkipSequence && Game.escapePressedNegateAction == false) {
							skipSequence = true;
							if(State != STATE.CREDITS) {
								if(sceneSkipCount < 1)
									sceneSkipCount++;
								else {
									try {
										LeaderboardController.writeToSettings("skipAnimations", "true");
									} catch (IOException e1) {
									}
									skipAnimations = true;
								}
							}
						}
						Game.keysAreInUse = true;
						Game.enterButtonPushedDown = false;
						Game.escapePressedNegateAction = false;
						sceneAcknowledgement = false;
		    		}
		    	}
	    }
	    else if(State == STATE.TRANSITION_ITEM && gameControllerInUse) {
	    	if((lY > .65 || (XInputDevice.upHeld) ||
	    		lY < -.65 || (XInputDevice.downHeld) ||
	    		lX > .65 || (XInputDevice.leftHeld) ||
	    		lX < -.65 || (XInputDevice.rightHeld)) && joystickTimer < System.currentTimeMillis()){
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
	    		!(XInputDevice.rightHeld) && !(XInputDevice.leftHeld)&&
	    		!(XInputDevice.upHeld) && !(XInputDevice.downHeld)) {
	    		joystickTimer = 0;
		    }
	    	if(XInputDevice.cancelPressed ||
	    			XInputDevice.itemPressed
	    		) {
	    		if(Game.mouseIsClickedDown) {
					if(!Game.mouseIsOffClickedObjectAndHeldDown && Game.skipHighlighted)
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.skipClicked = false;
					Game.escapePressedNegateAction = true;
				}
				if(!Game.keysAreInUse) {//|| sceneAcknowledgement == true){
					Game.keysAreInUse = true;
					sceneAcknowledgement = true;
				}
				else {
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				}
	    	}
	    	if(XInputDevice.pausePressed ||
	    			XInputDevice.shootPressed) {
	    		if(!Game.keysAreInUse) {//|| sceneAcknowledgement == true){
					Game.keysAreInUse = true;
					sceneAcknowledgement = true;
					Game.enterButtonPushedDown = true;
				}
				else if(Game.escapePressedNegateAction == false)
					Game.enterButtonPushedDown = true;
	    	}
	    	if(p.getVelX() != 0)
				p.setVelX(0);
			if(xLBoolean == true)
				this.xLBoolean = false;
			if(xRBoolean == true)
				this.xRBoolean = false;
			if(slowingDownActivatedl == true)
				this.slowingDownActivatedl = false;
			if(slowingDownActivatedr == true)
				this.slowingDownActivatedr = false;
			if(slowingDown != 0)
				this.slowingDown = 0;
			if(slowingDownTimerLong != 0)
				this.slowingDownTimerLong = 0;
			if(runningTimerActivated == true)
				this.runningTimerActivated = false;
			if(runningTimerActivatedResponse == true)
				this.runningTimerActivatedResponse = false;
			if(runningTimerLong != 0)
				this.runningTimerLong = 0;
			if((!XInputDevice.cancelPressed && XInputDevice.cancelReleased) || 
					(!XInputDevice.itemPressed && XInputDevice.itemReleased)
		    		) {
				if(Game.mouseIsClickedDown) {
				}
				else if(sceneAcknowledgement) {
					if(Game.enterButtonPushedDown) {}
					else if(!askToSkipSequence) {
						askToSkipSequence = true;
					}
					else {
						askToSkipSequence = false;
						Game.keysAreInUse = false;
					}
					if(!Game.enterButtonPushedDown)
						sceneAcknowledgement = false;
				}
			}
			if((!XInputDevice.pausePressed && XInputDevice.pauseReleased) || 
					(!XInputDevice.shootPressed && XInputDevice.shootReleased)) {
				if(!askToSkipSequence) {
	    			enterButtonPushedDown = false;
					askToSkipSequence = true;
				}
				else {
					if(askToSkipSequence && Game.escapePressedNegateAction == false) {
						skipSequence = true;
						if(sceneSkipCount < 1)
							sceneSkipCount++;
						else {
							try {
								LeaderboardController.writeToSettings("skipAnimations", "true");
							} catch (IOException e1) {
							}
							skipAnimations = true;
						}
					}
					Game.keysAreInUse = true;
					Game.enterButtonPushedDown = false;
					Game.escapePressedNegateAction = false;
					sceneAcknowledgement = false;
				}
			}
	    }
	    else if(State == STATE.GAMEOVER && gameControllerInUse) {
	    	if((lX < -.65 || (XInputDevice.leftHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		if(Game.selectorButtonPosition == -2 || Game.selectorButtonPosition == 0) {
	    			Game.selectorButtonPosition = -1;
	    			if(Game.hudSFXPosition == 3)
	    				Game.hudSFXPosition = 0;
	    			else
	    				Game.hudSFXPosition++;
	    			hudSFX.get(hudSFXPosition).play();
	    		}
				if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	if((lX > .65 || (XInputDevice.rightHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		if(Game.selectorButtonPosition == -1 || Game.selectorButtonPosition == 0) {
					Game.selectorButtonPosition = -2;
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
				if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	if((lY < -.65 || (XInputDevice.downHeld)) && joystickTimer < System.currentTimeMillis()){
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
				if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	if((lY > .65 || (XInputDevice.upHeld)) && joystickTimer < System.currentTimeMillis()){
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
				if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
	    		!(XInputDevice.rightHeld) && !(XInputDevice.leftHeld)&&
	    		!(XInputDevice.upHeld) && !(XInputDevice.downHeld)) {
	    		joystickTimer = 0;
		    }
	    	if(XInputDevice.pausePressed ||
	    			XInputDevice.shootPressed) {
				Game.enterButtonPushedDown = true;
	    	}
	    	if(XInputDevice.cancelPressed ||
	    			XInputDevice.itemPressed){
	    		if(Game.mouseIsClickedDown) {
					if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.setScoreHighlighted ||
							Game.playHighlighted || Game.exitHighlighted || Game.homeHighlighted|| 
							Game.leaderboardHighlighted))
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.playClicked = false;
					Game.homeClicked = false;
					Game.exitClicked = false;
					Game.setScoreClicked = false;
					Game.leaderboardClicked = false;
					Game.escapePressedNegateAction = true;
				}
	    		else if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
	    	}
	    	if((!XInputDevice.pausePressed && XInputDevice.pauseReleased) || 
	    			(!XInputDevice.shootPressed && XInputDevice.shootReleased)) {
	    		Game.enterButtonPushedDown = false;
				if(!Game.escapePressedNegateAction && Game.keysAreInUse) {
					switch(Game.selectorButtonPosition) {
						case -2:
							State = STATE.LEADERBOARD;
							Game.selectorButtonPosition = -1;
							if(smb3OpenSoundLoop.clipIsActive())
								smb3OpenSoundLoop.stop();
							smb3OpenSoundLoop.play();
							break;
						case -1:
							State = STATE.SET_SCORE;
							Game.selectorButtonPosition = 0;
							Game.keysAreInUse = false;//CHANGE FOR VIRTUAL KEYBOARD
							//Game.gameControllerInUse = false;//CHANGE FOR VIRTUAL KEYBOARD
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
							this.closeGame();
							System.exit(1);
							break;
					}
				}
				Game.escapePressedNegateAction = false;
	    	}
	    	if((!XInputDevice.cancelPressed && XInputDevice.cancelReleased) || 
	    			(!XInputDevice.itemPressed && XInputDevice.itemReleased)){
	    		if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
					Game.selectorBPMP = Game.selectorButtonPosition;
					Game.escapePressedNegateAction = false;
					if(Game.gameControllerInUse) {
						Game.gameControllerInUse = false;
						Game.keysAreInUse = false;
					}
					else {
						Game.gameControllerInUse = true;
						Game.keysAreInUse = true;
					}
				}
	    	}
	    }
	    else if(State == STATE.SET_SCORE && gameControllerInUse) {
	    	if(Game.keysAreInUse) {
	    		if((lY > .65 || (XInputDevice.upHeld)) && joystickTimer < System.currentTimeMillis()) {
	    			if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(!Game.keysAreInUse) {
						Game.keysAreInUse = true;
						Game.selectorButtonPosition = -1;
					}
					else {
						if(Game.selectorButtonPosition == 1) {
							Game.keysAreInUse = false;
							Game.selectorButtonPosition--;
						}
						if(Game.selectorButtonPosition > -1) {
							Game.selectorButtonPosition--;
						}
					}
					if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
	    		if((lY < -.65 || (XInputDevice.downHeld)) && joystickTimer < System.currentTimeMillis()){
	    			if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(!Game.keysAreInUse) {
						Game.keysAreInUse = true;
						Game.selectorButtonPosition = 1;
					}
					else {
						if(Game.selectorButtonPosition == -1) {
							Game.keysAreInUse = false;
							Game.selectorButtonPosition++;
						}
						if(Game.selectorButtonPosition < 2)
							Game.selectorButtonPosition++;
					}
					if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
	    		}
	    		if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
    	    		!(XInputDevice.rightHeld) && !(XInputDevice.leftHeld)&&
    	    		!(XInputDevice.upHeld) && !(XInputDevice.downHeld)) {
    	    		joystickTimer = 0;
    		    }
		    	if(XInputDevice.pausePressed ||
		    			XInputDevice.shootPressed) {
		    		if(Game.selectorButtonPosition == 2 && !Game.enterButtonPushedDown) {
						if(Game.smb3CheckmarkSoundLoop.clipIsActive())
							Game.smb3CheckmarkSoundLoop.stop();
						if(Game.smb3Checkmark2SoundLoop.clipIsActive())
							Game.smb3Checkmark2SoundLoop.stop();
						Game.smb3CheckmarkSoundLoop.play();
					}
					Game.enterButtonPushedDown = true;
		    	}
		    	if(XInputDevice.cancelPressed ||
		    			XInputDevice.itemPressed) {
		    		if(Game.mouseIsClickedDown) {
						if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.backHighlighted ||
								Game.submitHighlighted))
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.backClicked = false;
						Game.submitClicked = false;
						Game.settingsClicked = false;
						Game.leaderboardClicked = false;
						Game.escapePressedNegateAction = true;
					}
		    		else if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
		    	}
		    	if((!XInputDevice.pausePressed && XInputDevice.pauseReleased) || 
		    			(!XInputDevice.shootPressed && XInputDevice.shootReleased)) {
		    		Game.enterButtonPushedDown = false;
					if(!Game.escapePressedNegateAction) {
						switch(Game.selectorButtonPosition) {
							case -1:
								Game.selectorButtonPosition = -1;
								Game.State = Game.STATE.GAMEOVER;
								break;
							case 1:
								Game.postLetter = '~';
								Game.selectorButtonPosition = 0;
								if(Game.sendToServer && HUD.score != 0)
									Game.connectingToServer = true;
								if(Game.gameControllerInUse) {
									Game.keysAreInUse = true;
									Game.selectorButtonPosition = 0;
								}
								if(!LeaderboardController.globalList)
									LeaderboardController.resetTrigger = true;
								break;
							case 2:
								if(Game.sendToServer)
									Game.sendToServer = false;
								else
									Game.sendToServer = true;
								try {
									if(Game.sendToServer)
										LeaderboardController.writeToSettings("sendToServer", "true");
									else
										LeaderboardController.writeToSettings("sendToServer", "false");
								} catch (IOException e1) {
								}
								if(Game.smb3CheckmarkSoundLoop.clipIsActive())
									Game.smb3CheckmarkSoundLoop.stop();
								if(Game.smb3Checkmark2SoundLoop.clipIsActive())
									Game.smb3Checkmark2SoundLoop.stop();
								Game.smb3Checkmark2SoundLoop.play();
								break;
							default:
								break;
						}
					}
					Game.escapePressedNegateAction = false;
		    	}
		    	if((!XInputDevice.cancelPressed && XInputDevice.cancelReleased) || 
		    			(!XInputDevice.itemPressed && XInputDevice.itemReleased)) {
		    		if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
						Game.selectorBPMP = Game.selectorButtonPosition;
						Game.escapePressedNegateAction = false;
						if(Game.keysAreInUse) {
							Game.keysAreInUse = false;
						}else
							Game.keysAreInUse = true;
						/*
						if(Game.gameControllerInUse) {
							Game.gameControllerInUse = false;
							Game.keysAreInUse = false;
							//Game.selectorButtonPosition = 0;
						}
						else {
							Game.gameControllerInUse = true;
							Game.keysAreInUse = true;
						}*/
					}
		    	}
	    	}
	    	else {
	    		if((lY > .65 || (XInputDevice.upHeld)) && joystickTimer < System.currentTimeMillis()) {
	    			if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(!Game.keysAreInUse) {
						Game.keysAreInUse = true;
						Game.selectorButtonPosition = -1;
					}
					else {
						if(Game.selectorButtonPosition == 1) {
							Game.keysAreInUse = false;
							Game.selectorButtonPosition--;
						}
						if(Game.selectorButtonPosition > -1)
							Game.selectorButtonPosition--;
					}
	    			if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
	    		}
	    		if((lY < -.65 || (XInputDevice.downHeld)) && joystickTimer < System.currentTimeMillis()){
	    			if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(!Game.keysAreInUse) {
						Game.keysAreInUse = true;
						Game.selectorButtonPosition = 1;
					}
					else {
						if(Game.selectorButtonPosition == -1) {
							Game.keysAreInUse = false;
							Game.selectorButtonPosition++;
						}
						if(Game.selectorButtonPosition < 1)
							Game.selectorButtonPosition++;
					}
	    			if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
	    		}
	    		if((lX < -.65 || (XInputDevice.leftHeld)) && joystickTimer < System.currentTimeMillis()) {
					//TRAVERSE LETTERS
	    			if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
	    			if(gamepadKeyboardLetterPosition == 0)
	    				gamepadKeyboardLetterPosition = 41;
	    			else
	    				gamepadKeyboardLetterPosition--;
	    			gamepadLetterImage = null;
	    			if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
	    		}
	    		if((lX > .65 || (XInputDevice.rightHeld)) && joystickTimer < System.currentTimeMillis()) {
	    			//TRAVERSE LETTERS
	    			if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
	    			if(gamepadKeyboardLetterPosition == 41)
	    				gamepadKeyboardLetterPosition = 0;
	    			else
	    				gamepadKeyboardLetterPosition++;
	    			gamepadLetterImage = null;
	    			if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
	    		}
	    		if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
    	    		!(XInputDevice.rightHeld) && !(XInputDevice.leftHeld)&&
    	    		!(XInputDevice.upHeld) && !(XInputDevice.downHeld)) {
    	    		joystickTimer = 0;
    		    }
	    		if(XInputDevice.shootPressed) {
	    			//about to enter letter
	    			//gamepadKeyboardLetterPosition
	    			//CREATE NEW LETTER WHEN PRESSED, DON'T CREATE ONE UNTIL THIS IS PRESSED
	    			//
	    			//OR MAKE TRANSPARENT LETTER(TWICE AS FAST AS TEXTINDICATOR) AND DON'T ENTER IT UNTIL THIS IS PRESSED
	    		}
	    		if(XInputDevice.pausePressed) {
					Game.enterButtonPushedDown = true;
	    		}
	    		if(XInputDevice.cancelPressed) {
	    			if(Game.mouseIsClickedDown) {
						if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.backHighlighted ||
								Game.submitHighlighted))
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.backClicked = false;
						Game.submitClicked = false;
						Game.settingsClicked = false;
						Game.leaderboardClicked = false;
						Game.escapePressedNegateAction = true;
					}
	    			else if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
	    		}
	    		if(device.getDelta().getButtons().isReleased(XInputButton.LEFT_THUMBSTICK)) {
	    			if(shiftOn)
	    				shiftOn = false;
	    			else
	    				shiftOn = true;
	    		}
	    		if(!XInputDevice.itemPressed && XInputDevice.itemReleased) {
	    			postLetter = '+';
	    			//about to delete letter
	    		}
	    		if(!XInputDevice.shootPressed && XInputDevice.shootReleased) {
	    			if(postLetter == '=') {
		    			switch(gamepadKeyboardLetterPosition) {
		    			case 0:
		    				postLetter = 'A';
		    				break;
		    			case 1:
		    				postLetter = 'B';
		    				break;
		    			case 2:
		    				postLetter = 'C';
		    				break;
		    			case 3:
		    				postLetter = 'D';
		    				break;
		    			case 4:
		    				postLetter = 'E';
		    				break;
		    			case 5:
		    				postLetter = 'F';
		    				break;
		    			case 6:
		    				postLetter = 'G';
		    				break;
		    			case 7:
		    				postLetter = 'H';
		    				break;
		    			case 8:
		    				postLetter = 'I';
		    				break;
		    			case 9:
		    				postLetter = 'J';
		    				break;
		    			case 10:
		    				postLetter = 'K';
		    				break;
		    			case 11:
		    				postLetter = 'L';
		    				break;
		    			case 12:
		    				postLetter = 'M';
		    				break;
		    			case 13:
		    				postLetter = 'N';
		    				break;
		    			case 14:
		    				postLetter = 'O';
		    				break;
		    			case 15:
		    				postLetter = 'P';
		    				break;
		    			case 16:
		    				postLetter = 'Q';
		    				break;
		    			case 17:
		    				postLetter = 'R';
		    				break;
		    			case 18:
		    				postLetter = 'S';
		    				break;
		    			case 19:
		    				postLetter = 'T';
		    				break;
		    			case 20:
		    				postLetter = 'U';
		    				break;
		    			case 21:
		    				postLetter = 'V';
		    				break;
		    			case 22:
		    				postLetter = 'W';
		    				break;
		    			case 23:
		    				postLetter = 'X';
		    				break;
		    			case 24:
		    				postLetter = 'Y';
		    				break;
		    			case 25:
		    				postLetter = 'Z';
		    				break;
		    			case 26:
		    				postLetter = '1';
		    				break;
		    			case 27:
		    				postLetter = '2';
		    				break;
		    			case 28:
		    				postLetter = '3';
		    				break;
		    			case 29:
		    				postLetter = '4';
		    				break;
		    			case 30:
		    				postLetter = '5';
		    				break;
		    			case 31:
		    				postLetter = '6';
		    				break;
		    			case 32:
		    				postLetter = '7';
		    				break;
		    			case 33:
		    				postLetter = '8';
		    				break;
		    			case 34:
		    				postLetter = '9';
		    				break;
		    			case 35:
		    				postLetter = '0';
		    				break;
		    			case 36:
		    				postLetter = '.';
		    				break;
		    			case 37:
		    				postLetter = '\'';
		    				break;
		    			case 38:
		    				postLetter = '!';
		    				break;
		    			case 39:
		    				postLetter = ':';
		    				break;
		    			case 40:
		    				postLetter = ',';
		    				break;
		    			case 41:
		    				postLetter = ' ';
		    				break;
		    			default:
		    				break;
		    			}
	    			}
	    		}
	    		if(!XInputDevice.pausePressed && XInputDevice.pauseReleased) {
	    			if(!Game.escapePressedNegateAction) {
						postLetter = '~';
						if(Game.sendToServer && HUD.score != 0)
							Game.connectingToServer = true;
						if(!LeaderboardController.globalList)
							LeaderboardController.resetTrigger = true;
						Game.selectorButtonPosition = 0;
	    			}
					Game.enterButtonPushedDown = false;
					Game.escapePressedNegateAction = false;
					//Game.keysAreInUse = true;
					//Game.selectorButtonPosition = 0;
	    		}
	    		if(!XInputDevice.cancelPressed && XInputDevice.cancelReleased) {
	    			Game.keysAreInUse = true;
					Game.selectorButtonPosition = -1;
	    		}
	    	}
	    }//START AGAIN HERE!
	    else if(State == STATE.LEADERBOARD && gameControllerInUse) {
	    	if((lY < -0.65 || (XInputDevice.upHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		int selector = Game.selectorButtonPosition;
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(Game.areYouSureBoolean) {}
				else {
					if(Game.selectorButtonPosition == 0)
						Game.selectorButtonPosition = -1;
				}
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					Game.hudSFX.get(Game.hudSFXPosition).play();
				}
	    		if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	else if((0.65 < lY  || (XInputDevice.downHeld)) && joystickTimer < System.currentTimeMillis()) {
		    		int selector = Game.selectorButtonPosition;
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.areYouSureBoolean) {}
					else {
						if(Game.selectorButtonPosition == -1)
							Game.selectorButtonPosition = 0;
					}
					if(Game.selectorButtonPosition != selector) {
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
		    		if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
	    	if((lX < -.65 || (XInputDevice.leftHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		int selector = Game.selectorButtonPosition;
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(Game.areYouSureBoolean) {
					if(Game.selectorButtonPosition != 0)
						Game.selectorButtonPosition = 0;
				}
				else {
					if(Game.selectorButtonPosition == 1)
						Game.selectorButtonPosition = 0;
					else if(Game.selectorButtonPosition == 0)
						Game.selectorButtonPosition = -1;
				}
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					Game.hudSFX.get(Game.hudSFXPosition).play();
				}
	    		if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	else if((lX > .65 || (XInputDevice.rightHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		int selector = Game.selectorButtonPosition;
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(Game.areYouSureBoolean) {
					if(Game.selectorButtonPosition != 1)
						Game.selectorButtonPosition = 1;
				}
				else {
					if(Game.selectorButtonPosition == -1 || Game.selectorButtonPosition == 0)
						Game.selectorButtonPosition = 1;
				}
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					Game.hudSFX.get(Game.hudSFXPosition).play();
				}
	    		if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
		    		!(XInputDevice.rightHeld) && !(XInputDevice.leftHeld)&&
		    		!(XInputDevice.upHeld) && !(XInputDevice.downHeld)) {
		    		joystickTimer = 0;
		    }
	    	if(XInputDevice.pausePressed ||
	    			XInputDevice.shootPressed
		    		) {
				Game.enterButtonPushedDown = true;
	    	}
	    	if(XInputDevice.cancelPressed ||
	    			XInputDevice.itemPressed
		    		) {
	    		if(Game.mouseIsClickedDown) {
					if(!Game.mouseIsOffClickedObjectAndHeldDown && Game.backHighlighted)
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.backClicked = false;
					Game.escapePressedNegateAction = true;
				}
	    		else if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
	    	}
	    	if((!XInputDevice.pausePressed && XInputDevice.pauseReleased) || 
	    			(!XInputDevice.shootPressed && XInputDevice.shootReleased)
		    		) {
	    		Game.enterButtonPushedDown = false;
				if(!Game.escapePressedNegateAction) {
					if(Game.areYouSureBoolean) {
						switch(Game.selectorButtonPosition) {
						case 0:
							Game.selectorButtonPosition = 0;
							Game.areYouSureBoolean = false;
							Game.smb3KickSoundLoop.play();
							break;	
						case 1:
							LeaderboardController.deleteTrigger = true;
							Game.selectorButtonPosition = 0;
							Game.areYouSureBoolean = false;
							if(Game.smb3TailSoundLoop.clipIsActive())
								Game.smb3TailSoundLoop.stop();
							Game.smb3TailSoundLoop.play();
							break;
						}
					}
					else {
						switch(Game.selectorButtonPosition) {
						case -1:
							//Back button
							if(Game.backToGameOver) {
								Game.selectorButtonPosition = -2;
								Game.State = Game.STATE.GAMEOVER;
							}
							else {
								Game.selectorButtonPosition = -3;
								Game.State = Game.STATE.MENU;
							}
							if(Game.smb3KickSoundLoop.clipIsActive())
								Game.smb3KickSoundLoop.stop();
							Game.smb3KickSoundLoop.play();
							break;
						case 0:
							if(LeaderboardController.globalList) {
								//Local button
								Game.selectorButtonPosition = 1;
								LeaderboardController.globalList = false;
								LeaderboardController.listTrigger = true;
								if(Game.smb3ItemSoundLoop.clipIsActive())
									Game.smb3ItemSoundLoop.stop();
								Game.smb3ItemSoundLoop.play();
							}
							else {
								//Reset Leaderboard Button
								if(LeaderboardController.checkLeaderboard()) {
									Game.selectorButtonPosition = 0;
									Game.areYouSureBoolean = true;
									Game.hudSFX.get(4).play();
								}
								else {
									if(Game.smwErrorSoundLoop.clipIsActive())
										Game.smwErrorSoundLoop.stop();
									Game.smwErrorSoundLoop.play();
								}
								break;	
							}
							break;
						case 1:
							if(LeaderboardController.globalList) {
								//Upload button
								if(!LeaderboardController.checkLeaderboard()) {
									if(Game.smwErrorSoundLoop.clipIsActive())
										Game.smwErrorSoundLoop.stop();
									Game.smwErrorSoundLoop.play();
								}
								else {
									String s = "";
									if(Game.scoreEntered && 0 < HUD.score) {
										s=HUD.nameEntered;
										s=s+": "+Long.toString((HUD.score));
										//Game.saveName = s;
									}
									LeaderboardController.uploadTrigger = true;
								}
							}
							else {
								//Global button
								LeaderboardController.globalList = true;
								LeaderboardController.listTrigger = true;
							}
							break;
						}
					}
				}
				Game.escapePressedNegateAction = false;
			}
	    	if((!XInputDevice.cancelPressed && XInputDevice.cancelReleased) || 
	    			(!XInputDevice.itemPressed && XInputDevice.itemReleased)
		    		) {
	    		if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
					Game.selectorBPMP = Game.selectorButtonPosition;
					Game.escapePressedNegateAction = false;
					if(Game.gameControllerInUse) {
						Game.gameControllerInUse = false;
						Game.gameControllerInUseDI = false;
						Game.keysAreInUse = false;
					}
					else {
						Game.gameControllerInUse = true;
						Game.keysAreInUse = true;
					}
					if(Game.areYouSureBoolean && Game.selectorButtonPosition != 0)
						Game.selectorButtonPosition = 0;
	    		}
	    	}
	    }
	    else if(State == STATE.SHOP && gameControllerInUse) {
	    	if((lY > .65 || (XInputDevice.upHeld)) && joystickTimer < System.currentTimeMillis()) {
				if(allUnlockedScreen) {
					return;
				}
				int selector = Game.selectorButtonPosition;
				if(Game.selectorButtonPosition == -2 && currentSkinLocked == false)
					Game.selectorButtonPosition = 0;
				else if(Game.selectorButtonPosition == -5 && currentTrackLocked == false)
					Game.selectorButtonPosition = 1;
				else if(Game.selectorButtonPosition == -8 && currentFireballLocked == false)
					Game.selectorButtonPosition = 2;
				else if(Game.selectorButtonPosition == -11 && currentItemLocked == false)
					Game.selectorButtonPosition = 3;
				
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
	    		if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	else if((lY < -.65 || (XInputDevice.downHeld)) && joystickTimer < System.currentTimeMillis()) {
				if(allUnlockedScreen) {
					return;
				}
	    		int selector = Game.selectorButtonPosition;
				if(Game.selectorButtonPosition == -2 && currentSkinLocked == false)
					Game.selectorButtonPosition = 0;
				else if(Game.selectorButtonPosition == -5 && currentTrackLocked == false)
					Game.selectorButtonPosition = 1;
				else if(Game.selectorButtonPosition == -8 && currentFireballLocked == false)
					Game.selectorButtonPosition = 2;
				else if(Game.selectorButtonPosition == -11 && currentItemLocked == false)
					Game.selectorButtonPosition = 3;
				
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
	    		if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	if((lX < -.65 || (XInputDevice.leftHeld)) && joystickTimer < System.currentTimeMillis()) {
				if(allUnlockedScreen) {
					return;
				}
	    		int selector = Game.selectorButtonPosition;
				if(Game.selectorButtonPosition == -2 && currentSkinLocked == false)
					Game.selectorButtonPosition = 0;
				else if(Game.selectorButtonPosition == -5 && currentTrackLocked == false)
					Game.selectorButtonPosition = 1;
				else if(Game.selectorButtonPosition == -8 && currentFireballLocked == false)
					Game.selectorButtonPosition = 2;
				else if(Game.selectorButtonPosition == -11 && currentItemLocked == false)
					Game.selectorButtonPosition = 3;
				
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
	    		if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	else if((lX > .65 || (XInputDevice.rightHeld)) && joystickTimer < System.currentTimeMillis()) {
				if(allUnlockedScreen) {
					return;
				}
	    		int selector = Game.selectorButtonPosition;
				if(Game.selectorButtonPosition == -2 && currentSkinLocked == false)
					Game.selectorButtonPosition = 0;
				else if(Game.selectorButtonPosition == -5 && currentTrackLocked == false)
					Game.selectorButtonPosition = 1;
				else if(Game.selectorButtonPosition == -8 && currentFireballLocked == false)
					Game.selectorButtonPosition = 2;
				else if(Game.selectorButtonPosition == -11 && currentItemLocked == false)
					Game.selectorButtonPosition = 3;
				
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
	    		if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
	    		!(XInputDevice.rightHeld) && !(XInputDevice.leftHeld)&&
	    		!(XInputDevice.upHeld) && !(XInputDevice.downHeld)) {
	    		joystickTimer = 0;
		    }

	    	if(XInputDevice.pausePressed ||
	    			XInputDevice.shootPressed
	    		) {
				if(!Game.allUnlockedScreen) 
					Game.enterButtonPushedDown = true;
	    	}
	    	if(XInputDevice.cancelPressed ||
	    			XInputDevice.itemPressed
	    		) {
	    		if(Game.mouseIsClickedDown) {
					if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.backHighlighted ||
							Game.arrowL1Highlighted || Game.arrowR1Highlighted ||
							Game.arrowL2Highlighted || Game.arrowR2Highlighted ||
							Game.arrowL3Highlighted || Game.arrowR3Highlighted ||
							Game.arrowL4Highlighted || Game.arrowR4Highlighted ||
							Game.set1Highlighted || Game.buy1Highlighted ||
							Game.set2Highlighted || Game.buy2Highlighted ||
							Game.set3Highlighted || Game.buy3Highlighted ||
							Game.set4Highlighted || Game.buy4Highlighted))
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.backClicked = false;
					Game.arrowL1Clicked = false;
					Game.arrowR1Clicked = false;
					Game.arrowL2Clicked = false;
					Game.arrowR2Clicked = false;
					Game.arrowL3Clicked = false;
					Game.arrowR3Clicked = false;
					Game.arrowL4Clicked = false;
					Game.arrowR4Clicked = false;
					Game.set1Clicked = false;
					Game.buy1Clicked = false;
					Game.set2Clicked = false;
					Game.buy2Clicked = false;
					Game.set3Clicked = false;
					Game.buy3Clicked = false;
					Game.set4Clicked = false;
					Game.buy4Clicked = false;
					Game.escapePressedNegateAction = true;
				}
	    		else if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
	    	}
	    	if((!XInputDevice.pausePressed && XInputDevice.pauseReleased) || 
	    			(!XInputDevice.shootPressed && XInputDevice.shootReleased)
		    		) {
				if(allUnlockedScreen) {
					allUnlockedScreen = false;
					XInputDevice.reset();
					return;
				}
	    		Game.enterButtonPushedDown = false;
				if(Game.selectorButtonPosition == -2 && currentSkinLocked == false)
					Game.selectorButtonPosition = 0;
				else if(Game.selectorButtonPosition == -5 && currentTrackLocked == false)
					Game.selectorButtonPosition = 1;
				else if(Game.selectorButtonPosition == -8 && currentFireballLocked == false)
					Game.selectorButtonPosition = 2;
				else if(Game.selectorButtonPosition == -11 && currentItemLocked == false)
					Game.selectorButtonPosition = 3;

				if(!Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -12:
						if(Game.itemPosition == 6)//Max Items
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
						if(currentItemLocked) {
							switch(itemPosition){
								case 4:
									if(totalPoints >= 1000){
										Game.item4Unlocked = true;
										//Game.settingsSetup = false;
										Game.writeOnceToSettings = true;
										Game.writeOnceProperty = "currentlySelectedItem";
										Game.writeOnceString = Integer.toString(Game.itemPosition);
										Game.writeOnceToSettingswithPoints = true;
										Game.writeOnceUnlock = "item4Unlocked";
										Game.itemPosition = 4;
										Game.currentlySelectedItem = 4;
										currentItemLocked = false;
										skinNumber = null;
										totalPoints -= 1000;
										Game.starExplode = true;
										Game.mx = Game.WIDTH +9;
										Game.my = 136;
										Game.checkIfAllUnlocked();
										if(smb31PupSoundLoop.clipIsActive())
											smb31PupSoundLoop.stop();
										smb31PupSoundLoop.play();
										Game.selectorButtonPosition = -12;
									}
									else{
										if(smwErrorSoundLoop.clipIsActive())
											smwErrorSoundLoop.stop();
										smwErrorSoundLoop.play();
									}
									break;
								case 5:
									if(totalPoints >= 10000){
										Game.item5Unlocked = true;
										//Game.settingsSetup = false;
										Game.writeOnceToSettings = true;
										Game.writeOnceProperty = "currentlySelectedItem";
										Game.writeOnceString = Integer.toString(Game.itemPosition);
										Game.writeOnceToSettingswithPoints = true;
										Game.writeOnceUnlock = "item5Unlocked";
										Game.itemPosition = 5;
										Game.currentlySelectedItem = 5;
										currentItemLocked = false;
										skinNumber = null;
										totalPoints -= 10000;
										Game.starExplode = true;
										Game.mx = Game.WIDTH +9;
										Game.my = 136;
										Game.checkIfAllUnlocked();
										if(smb31PupSoundLoop.clipIsActive())
											smb31PupSoundLoop.stop();
										smb31PupSoundLoop.play();
										Game.selectorButtonPosition = -12;
									}
									else{
										if(smwErrorSoundLoop.clipIsActive())
											smwErrorSoundLoop.stop();
										smwErrorSoundLoop.play();
									}
									break;
								case 6:
									if(totalPoints >= 100000){
										Game.item6Unlocked = true;
										//Game.settingsSetup = false;
										Game.writeOnceToSettings = true;
										Game.writeOnceProperty = "currentlySelectedItem";
										Game.writeOnceString = Integer.toString(Game.itemPosition);
										Game.writeOnceToSettingswithPoints = true;
										Game.writeOnceUnlock = "item6Unlocked";
										Game.itemPosition = 6;
										Game.currentlySelectedItem = 6;
										currentItemLocked = false;
										skinNumber = null;
										totalPoints -= 100000;
										Game.starExplode = true;
										Game.mx = Game.WIDTH +9;
										Game.my = 136;
										Game.checkIfAllUnlocked();
										if(smb31PupSoundLoop.clipIsActive())
											smb31PupSoundLoop.stop();
										smb31PupSoundLoop.play();
										Game.selectorButtonPosition = -12;
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
						break;
					case -10:
						//Fireballs Set
						if(!Game.currentFireballLocked) {
							if(Game.currentlySelectedFireball != Game.fireballPosition) {
								if(Game.smb3ItemSoundLoop.clipIsActive())
									Game.smb3ItemSoundLoop.stop();
								Game.smb3ItemSoundLoop.play();
							}
							else {
								if(Game.smb3Checkmark2SoundLoop.clipIsActive())
									Game.smb3Checkmark2SoundLoop.stop();
								Game.smb3Checkmark2SoundLoop.play();
							}
							Game.currentlySelectedFireball = Game.fireballPosition;
							Game.fireballSoundSet();
							Game.writeOnceToSettings = true;
							Game.writeOnceProperty = "currentlySelectedFireball";
							Game.writeOnceString = Integer.toString(Game.fireballPosition);
							//Game.settingsSetup = false;
						}
						else {
							if(Game.smwErrorSoundLoop.clipIsActive())
								Game.smwErrorSoundLoop.stop();
							Game.smwErrorSoundLoop.play();
						}
						break;
					case -9:
						if(Game.fireballPosition == 3 && Game.currentlySelectedFireball == 4)//Max Fireballs
							Game.fireballPosition = 4;
						else if(Game.fireballPosition == 3 && Game.currentlySelectedFireball == 5) {
							Game.fireballPosition = 5;
							skinNumber = null;
						}
						else if(Game.fireballPosition == 3 || Game.fireballPosition == 4 || Game.fireballPosition == 5)
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
						if(currentFireballLocked) {
							switch(fireballPosition){
								case 1:
									if(totalPoints >= 1000){
										Game.fireball1Unlocked = true;
										//Game.settingsSetup = false;
										Game.writeOnceToSettings = true;
										Game.writeOnceProperty = "currentlySelectedFireball";
										Game.writeOnceString = Integer.toString(Game.fireballPosition);
										Game.writeOnceToSettingswithPoints = true;
										Game.writeOnceUnlock = "fireball1Unlocked";
										Game.fireballPosition = 1;
										Game.currentlySelectedFireball = 1;
										currentFireballLocked = false;
										skinNumber = null;
										totalPoints -= 1000;
										Game.starExplode = true;
										Game.mx = Game.WIDTH +9;
										Game.my = 336;
										Game.checkIfAllUnlocked();
										if(smb31PupSoundLoop.clipIsActive())
											smb31PupSoundLoop.stop();
										smb31PupSoundLoop.play();
										Game.selectorButtonPosition = -9;
									}
									else{
										if(smwErrorSoundLoop.clipIsActive())
											smwErrorSoundLoop.stop();
										smwErrorSoundLoop.play();
									}
									break;
								case 2:
									if(totalPoints >= 10000){
										Game.fireball2Unlocked = true;
										//Game.settingsSetup = false;
										Game.writeOnceToSettings = true;
										Game.writeOnceProperty = "currentlySelectedFireball";
										Game.writeOnceString = Integer.toString(Game.fireballPosition);
										Game.writeOnceToSettingswithPoints = true;
										Game.writeOnceUnlock = "fireball2Unlocked";
										Game.fireballPosition = 2;
										Game.currentlySelectedFireball = 2;
										currentFireballLocked = false;
										skinNumber = null;
										totalPoints -= 10000;
										Game.starExplode = true;
										Game.mx = Game.WIDTH +9;
										Game.my = 336;
										Game.checkIfAllUnlocked();
										if(smb31PupSoundLoop.clipIsActive())
											smb31PupSoundLoop.stop();
										smb31PupSoundLoop.play();
										Game.selectorButtonPosition = -9;
									}
									else{
										if(smwErrorSoundLoop.clipIsActive())
											smwErrorSoundLoop.stop();
										smwErrorSoundLoop.play();
									}
									break;
								case 3:
									if(totalPoints >= 100000){
										Game.fireball3Unlocked = true;
										//Game.settingsSetup = false;
										Game.writeOnceToSettings = true;
										Game.writeOnceProperty = "currentlySelectedFireball";
										Game.writeOnceString = Integer.toString(Game.fireballPosition);
										Game.writeOnceToSettingswithPoints = true;
										Game.writeOnceUnlock = "fireball3Unlocked";
										Game.fireballPosition = 3;
										Game.currentlySelectedFireball = 3;
										currentFireballLocked = false;
										skinNumber = null;
										totalPoints -= 100000;
										Game.starExplode = true;
										Game.mx = Game.WIDTH +9;
										Game.my = 336;
										Game.checkIfAllUnlocked();
										if(smb31PupSoundLoop.clipIsActive())
											smb31PupSoundLoop.stop();
										smb31PupSoundLoop.play();
										Game.selectorButtonPosition = -9;
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
							Game.fireballSoundSet();
						}
						//else
							//Play error noise
						break;
					case -7:
						//Tracks Set
						if(!Game.currentTrackLocked) {
							boolean b = false;
							String s = "";
							String ss = "";
							switch(Game.trackPosition) {
							case 0:
								s = "gameTrack1Set";
								if(Game.gameTrack1Set) {
									Game.gameTrack1Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack1Set = true;
									ss = "true";
								}
								break;
							case 1:
								s = "gameTrack2Set";
								if(Game.gameTrack2Set) {
									Game.gameTrack2Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack2Set = true;
									ss = "true";
								}
								break;
							case 2:
								s = "gameTrack3Set";
								if(Game.gameTrack3Set) {
									Game.gameTrack3Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack3Set = true;
									ss = "true";
								}
								break;
							case 3:
								s = "gameTrack4Set";
								if(Game.gameTrack4Set) {
									Game.gameTrack4Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack4Set = true;
									ss = "true";
								}
								break;
							case 4:
								s = "gameTrack5Set";
								if(Game.gameTrack5Set || !Game.track4Unlocked) {
									if(!Game.track4Unlocked) {
										if(Game.smwErrorSoundLoop.clipIsActive())
											Game.smwErrorSoundLoop.stop();
										Game.smwErrorSoundLoop.play();
									}
									Game.gameTrack5Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack5Set = true;
									ss = "true";
								}
								break;
							case 5:
								s = "gameTrack6Set";
								if(Game.gameTrack6Set || !Game.track5Unlocked) {
									if(!Game.track5Unlocked) {
										if(Game.smwErrorSoundLoop.clipIsActive())
											Game.smwErrorSoundLoop.stop();
										Game.smwErrorSoundLoop.play();
									}
									Game.gameTrack6Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack6Set = true;
									ss = "true";
								}
								break;
							case 6:
								s = "gameTrack7Set";
								if(Game.gameTrack7Set || !Game.track6Unlocked) {
									if(!Game.track6Unlocked) {
										if(Game.smwErrorSoundLoop.clipIsActive())
											Game.smwErrorSoundLoop.stop();
										Game.smwErrorSoundLoop.play();
									}
									Game.gameTrack7Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack7Set = true;
									ss = "true";
								}
								break;
							case 7:
								s = "gameTrack8Set";
								if(Game.gameTrack8Set) {
									Game.gameTrack8Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack8Set = true;
									ss = "true";
								}
								break;
							default:
								break;
							}
							if(b) {
								if(Game.smb3ItemSoundLoop.clipIsActive())
									Game.smb3ItemSoundLoop.stop();
								Game.smb3ItemSoundLoop.play();
							}
							if(!s.equals("") && !ss.equals("")) {
								try {
									LeaderboardController.writeToSettings(s, ss);
								} catch (IOException e1) {
								}
								if(ss.equals("true") && Game.soundRandomizer == 8 && Game.gameTrackCurrentlyPlaying == -1  && Game.menuTrackCurrentlyPlaying == -1) {
									int randomIPlus = 0;
									if(Game.track4Unlocked)
										randomIPlus++;
									if(Game.track5Unlocked)
										randomIPlus++;
									if(Game.track6Unlocked)
										randomIPlus++;
									Random rand = new Random();
									Game.soundRandomizer = rand.nextInt(7) + (-3+randomIPlus);
									Game.soundRandomizer = VolumeSlider.gameTrackSetup(Game.soundRandomizer);
									//setsetup[VolumeSlider.TrackSetup]
									if(Game.soundRandomizer != -4) {
										Game.gameSoundLoops.get(Game.soundRandomizer).stop();
										Game.gameSoundLoops.get(Game.soundRandomizer).setSoundLoopBoolean(true);
										Game.gameSoundLoops.get(Game.soundRandomizer).setFramePosition(0);
//										Game.gameSoundLoops.get(Game.soundRandomizer).play();
									}
									else
										Game.soundRandomizer = 8;
								}
								else if(ss.equals("false")) {  
									if(Game.gameTrack1Set == false && Game.gameTrack2Set == false && Game.gameTrack3Set == false && 
										Game.gameTrack4Set == false && Game.gameTrack5Set == false && Game.gameTrack6Set == false && 
										Game.gameTrack7Set == false && Game.gameTrack8Set == false && Game.soundRandomizer != 8 &&
										Game.soundRandomizer != -4) 
									Game.soundRandomizer = 8;
									if(Game.smbPopSoundLoop.clipIsActive())
										Game.smbPopSoundLoop.stop();
									Game.smbPopSoundLoop.play();
								}
							}
						}
						else {
							if(Game.smwErrorSoundLoop.clipIsActive())
								Game.smwErrorSoundLoop.stop();
							Game.smwErrorSoundLoop.play();
						}
						break;
					case -6:
						if(Game.trackPosition == 7)//Max Tracks
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
						if(currentTrackLocked) {
							switch(trackPosition){
								case 4:
									if(Game.totalPoints >= 1000){
										Game.track4Unlocked = true;
										//Game.settingsSetup = false;
										Game.writeOnceToSettings = true;
										Game.writeOnceProperty = "gameTrack5Set";
										Game.writeOnceString = "true";
										Game.writeOnceToSettingswithPoints = true;
										Game.writeOnceUnlock = "track4Unlocked";
										Game.trackPosition = 4;
										Game.currentlySelectedTrack = 4;
										Game.currentTrackLocked = false;
										Game.skinNumber = null;
										Game.totalPoints -= 1000;
										Game.starExplode = true;
										Game.track4Unlocked = true;
										Game.gameTrack5Set = true;
										int randomIPlus = 0;
										if(Game.track4Unlocked)
											randomIPlus++;
										if(Game.track5Unlocked)
											randomIPlus++;
										if(Game.track6Unlocked)
											randomIPlus++;
										Random rand = new Random();
										Game.soundRandomizer = rand.nextInt(7) + (-3+randomIPlus);
										Game.soundRandomizer = VolumeSlider.gameTrackSetup(Game.soundRandomizer);
										Game.mx = Game.WIDTH +9;
										Game.my = 136;
										Game.checkIfAllUnlocked();
										if(Game.smb31PupSoundLoop.clipIsActive())
											Game.smb31PupSoundLoop.stop();
										Game.smb31PupSoundLoop.play();
										Game.selectorButtonPosition = -6;
									}
									else{
										if(Game.smwErrorSoundLoop.clipIsActive())
											Game.smwErrorSoundLoop.stop();
										Game.smwErrorSoundLoop.play();
									}
									break;
								case 5:
									if(Game.totalPoints >= 10000){
										Game.track5Unlocked = true;
										//Game.settingsSetup = false;
										Game.writeOnceToSettings = true;
										Game.writeOnceProperty = "gameTrack6Set";
										Game.writeOnceString = "true";
										Game.writeOnceToSettingswithPoints = true;
										Game.writeOnceUnlock = "track5Unlocked";
										Game.trackPosition = 5;
										Game.currentlySelectedTrack = 5;
										Game.currentTrackLocked = false;
										Game.skinNumber = null;
										Game.totalPoints -= 10000;
										Game.starExplode = true;
										Game.track5Unlocked = true;
										Game.gameTrack6Set = true;
										int randomIPlus = 0;
										if(Game.track4Unlocked)
											randomIPlus++;
										if(Game.track5Unlocked)
											randomIPlus++;
										if(Game.track6Unlocked)
											randomIPlus++;
										Random rand = new Random();
										Game.soundRandomizer = rand.nextInt(7) + (-3+randomIPlus);
										Game.soundRandomizer = VolumeSlider.gameTrackSetup(Game.soundRandomizer);
										Game.mx = Game.WIDTH +9;
										Game.my = 136;
										Game.checkIfAllUnlocked();
										if(Game.smb31PupSoundLoop.clipIsActive())
											Game.smb31PupSoundLoop.stop();
										Game.smb31PupSoundLoop.play();
										Game.selectorButtonPosition = -6;
									}
									else{
										if(Game.smwErrorSoundLoop.clipIsActive())
											Game.smwErrorSoundLoop.stop();
										Game.smwErrorSoundLoop.play();
									}
									break;
								case 6:
									if(Game.totalPoints >= 100000){
										Game.track6Unlocked = true;
										//Game.settingsSetup = false;
										Game.writeOnceToSettings = true;
										Game.writeOnceProperty = "gameTrack7Set";
										Game.writeOnceString = "true";
										Game.writeOnceToSettingswithPoints = true;
										Game.writeOnceUnlock = "track6Unlocked";
										Game.trackPosition = 6;
										Game.currentlySelectedTrack = 6;
										Game.currentTrackLocked = false;
										Game.skinNumber = null;
										Game.totalPoints -= 100000;
										Game.starExplode = true;
										Game.track6Unlocked = true;
										Game.gameTrack7Set = true;
										int randomIPlus = 0;
										if(Game.track4Unlocked)
											randomIPlus++;
										if(Game.track5Unlocked)
											randomIPlus++;
										if(Game.track6Unlocked)
											randomIPlus++;
										Random rand = new Random();
										Game.soundRandomizer = rand.nextInt(7) + (-3+randomIPlus);
										Game.soundRandomizer = VolumeSlider.gameTrackSetup(Game.soundRandomizer);
										Game.mx = Game.WIDTH +9;
										Game.my = 136;
										Game.checkIfAllUnlocked();
										if(Game.smb31PupSoundLoop.clipIsActive())
											Game.smb31PupSoundLoop.stop();
										Game.smb31PupSoundLoop.play();
										Game.selectorButtonPosition = -6;
									}
									else{
										if(Game.smwErrorSoundLoop.clipIsActive())
											Game.smwErrorSoundLoop.stop();
										Game.smwErrorSoundLoop.play();
									}
									break;
								default:
									break;
							}
						}
						break;
					case -4:
						//Skin Set
						if(!Game.currentSkinLocked) {
							if(Game.currentlySelectedCharacterSkin != Game.characterSkinPosition) {
								if(Game.smb3ItemSoundLoop.clipIsActive())
									Game.smb3ItemSoundLoop.stop();
								Game.smb3ItemSoundLoop.play();
							}
							else {
								if(Game.smb3Checkmark2SoundLoop.clipIsActive())
									Game.smb3Checkmark2SoundLoop.stop();
								Game.smb3Checkmark2SoundLoop.play();
							}
							Game.currentlySelectedCharacterSkin = Game.characterSkinPosition;
							Game.writeOnceToSettings = true;
							Game.writeOnceProperty = "currentlySelectedCharacterSkin";
							Game.writeOnceString = Integer.toString(Game.characterSkinPosition);
							//Game.settingsSetup = false;
						}
						else {
							if(Game.smwErrorSoundLoop.clipIsActive())
								Game.smwErrorSoundLoop.stop();
							Game.smwErrorSoundLoop.play();
						}
						break;
					case -3:
						if(Game.characterSkinPosition == 3 && Game.currentlySelectedCharacterSkin == 4)//Max Skins
							Game.characterSkinPosition = 4;
						else if(Game.characterSkinPosition == 3 && Game.currentlySelectedCharacterSkin == 5) 
							Game.characterSkinPosition = 5;
						else if (Game.characterSkinPosition == 3 || Game.characterSkinPosition == 4 || Game.characterSkinPosition == 5)
							Game.characterSkinPosition = 0;
						else {
							Game.characterSkinPosition++;
						}
						if(Game.currentlySelectedCharacterSkin == 5 && characterSkinPosition == 5)
							Game.skinNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.characterSkinPosition)), 10, 10);
						else
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
									if(totalPoints >= 1000){
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
										totalPoints -= 1000;
										Game.starExplode = true;
										Game.mx = Game.WIDTH +9;
										Game.my = 136;
										Game.checkIfAllUnlocked();
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
									if(totalPoints >= 10000){
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
										totalPoints -= 10000;
										Game.starExplode = true;
										Game.mx = Game.WIDTH +9;
										Game.my = 136;
										Game.checkIfAllUnlocked();
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
									if(totalPoints >= 100000){
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
										totalPoints -= 100000;
										Game.starExplode = true;
										Game.mx = Game.WIDTH +9;
										Game.my = 136;
										Game.checkIfAllUnlocked();
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
						if(Game.characterSkinPosition == 5)
							Game.characterSkinPosition-=2;
						else if(Game.characterSkinPosition > 0)
							Game.characterSkinPosition--;
						else if(Game.currentlySelectedCharacterSkin == 4)
							Game.characterSkinPosition = 4;
						else if(Game.currentlySelectedCharacterSkin == 5) 
							Game.characterSkinPosition = 5;
						else
							Game.characterSkinPosition = 3;//Set to Max Skins
						if(Game.currentlySelectedCharacterSkin == 5 && characterSkinPosition == 5)
							Game.skinNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.characterSkinPosition)), 10, 10);
						else
							Game.skinNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.characterSkinPosition+1)), 10, 10);
						if(smb3Bump2SoundLoop.clipIsActive())
							smb3Bump2SoundLoop.stop();
						smb3Bump2SoundLoop.play();
						break;
					case 1:
						if(Game.trackPosition > 0)
							Game.trackPosition--;
						else
							Game.trackPosition = 7;//Set to Max Tracks
						Game.trackNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.trackPosition+1)), 10, 10);
						if(smb3Bump2SoundLoop.clipIsActive())
							smb3Bump2SoundLoop.stop();
						smb3Bump2SoundLoop.play();
						break;
					case 2:
						if(Game.fireballPosition == 5)
							Game.fireballPosition-=2;
						else if(Game.fireballPosition > 0)
							Game.fireballPosition--;
						else if(Game.currentlySelectedFireball == 4)
							Game.fireballPosition = 4;
						else if(Game.currentlySelectedFireball == 5) {
							Game.fireballPosition = 5;
							skinNumber = null;
						}
						else
							Game.fireballPosition = 3;//Set to Max Fireballs
						Game.fireballNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.fireballPosition+1)), 10, 10);
						if(smb3Bump2SoundLoop.clipIsActive())
							smb3Bump2SoundLoop.stop();
						smb3Bump2SoundLoop.play();
						break;
					case 3:
						if(Game.itemPosition > 0)
							Game.itemPosition--;
						else
							Game.itemPosition = 6;//Set to Max Items
						Game.itemNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.itemPosition+1)), 10, 10);
						if(smb3Bump2SoundLoop.clipIsActive())
							smb3Bump2SoundLoop.stop();
						smb3Bump2SoundLoop.play();
						break;
					}
				}
				Game.escapePressedNegateAction = false;
	    	}
	    	if((!XInputDevice.cancelPressed && XInputDevice.cancelReleased) || 
	    			(!XInputDevice.itemPressed && XInputDevice.itemReleased)
		    		) {
				if(allUnlockedScreen) {
					allUnlockedScreen = false;
					return;
				}
	    		if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
					if(Game.selectorButtonPosition == -2 && currentSkinLocked == false)
						Game.selectorButtonPosition = 0;
					else if(Game.selectorButtonPosition == -5 && currentTrackLocked == false)
						Game.selectorButtonPosition = 1;
					else if(Game.selectorButtonPosition == -8 && currentFireballLocked == false)
						Game.selectorButtonPosition = 2;
					else if(Game.selectorButtonPosition == -11 && currentItemLocked == false)
						Game.selectorButtonPosition = 3;
					Game.selectorBPMP = Game.selectorButtonPosition;
					Game.escapePressedNegateAction = false;
					if(Game.gameControllerInUse) {
						Game.gameControllerInUse = false;
						Game.keysAreInUse = false;
					}
					else {
						Game.gameControllerInUse = true;
						Game.keysAreInUse = true;
					}
	    		}
	    	}
	    }
	    else if(State == STATE.SETTINGS && gameControllerInUse) {
	    	if((lY > .65 || (XInputDevice.upHeld)) && joystickTimer < System.currentTimeMillis()) {
				int selector = Game.selectorButtonPosition;
	    		if(areYouSureBoolean)
					Game.selectorButtonPosition = 0;
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(areYouSureBoolean) {}
				else {
					if(selectorButtonPosition <= 3 && selectorButtonPosition > -1)
						selectorButtonPosition--;
					else if(selectorButtonPosition == -3)
						selectorButtonPosition = -2;
					else if(selectorButtonPosition == -2)
						selectorButtonPosition = -4;
				}
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
				if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	else if((lY < -.65 || (XInputDevice.downHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		int selector = Game.selectorButtonPosition;
	    		if(areYouSureBoolean)
					Game.selectorButtonPosition = 0;
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(areYouSureBoolean) {}
				else {
					if(selectorButtonPosition >= -1 && selectorButtonPosition < 3)
						selectorButtonPosition++;
					else if(selectorButtonPosition == -2)
						selectorButtonPosition = -3;
					else if(selectorButtonPosition == -3)
						selectorButtonPosition = 2;
					else if(selectorButtonPosition == -4 || selectorButtonPosition == -5)
						selectorButtonPosition = -2;
				}
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
				if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	if((lX < -.65 || (XInputDevice.leftHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		int selector = Game.selectorButtonPosition;
				if(areYouSureBoolean)
					Game.selectorButtonPosition = 0;
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(areYouSureBoolean) {
					if(selectorButtonPosition == 1)
						selectorButtonPosition = 0;
				}
				else {
					if(selectorButtonPosition == -2)
						selectorButtonPosition = 0;
					else if(selectorButtonPosition == -3 || Game.selectorButtonPosition == 2)
						selectorButtonPosition = 1;
					else if(selectorButtonPosition == -4)
						selectorButtonPosition = -1;
					else if(selectorButtonPosition == -5)
						selectorButtonPosition = -4;
				}
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
	    		if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	else if((lX > .65 || (XInputDevice.rightHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		int selector = Game.selectorButtonPosition;
				if(areYouSureBoolean)
					Game.selectorButtonPosition = 0;
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(areYouSureBoolean) {
					if(selectorButtonPosition == 0)
						selectorButtonPosition = 1;
				}
				else {
					if(selectorButtonPosition == 0)
						selectorButtonPosition = -2;
					else if(selectorButtonPosition == 1 || Game.selectorButtonPosition == 2)
						selectorButtonPosition = -3;
					else if(selectorButtonPosition == -2 || selectorButtonPosition == -1)
						selectorButtonPosition = -4;
					else if(selectorButtonPosition == -4)
						selectorButtonPosition = -5;
				}
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					hudSFX.get(hudSFXPosition).play();
				}
	    		if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
	    		!(XInputDevice.rightHeld) && !(XInputDevice.leftHeld)&&
	    		!(XInputDevice.upHeld) && !(XInputDevice.downHeld)) {
	    		joystickTimer = 0;
	    	}
	    	if(XInputDevice.pausePressed ||
	    			XInputDevice.shootPressed){
				if(Game.selectorButtonPosition == 2 && !Game.enterButtonPushedDown) {
					if(smb3CheckmarkSoundLoop.clipIsActive())
						smb3CheckmarkSoundLoop.stop();
					if(smb3Checkmark2SoundLoop.clipIsActive())
						smb3Checkmark2SoundLoop.stop();
					smb3CheckmarkSoundLoop.play();
				}
				Game.enterButtonPushedDown = true;
		    }
	    	if(XInputDevice.cancelPressed ||
	    			XInputDevice.itemPressed
	    		) {
	    		if(Game.mouseIsClickedDown) {
					if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.yesHighlighted || Game.noHighlighted ||
							Game.arrowL1Highlighted || Game.arrowR1Highlighted ||
							Game.arrowL2Highlighted || Game.arrowR2Highlighted ||
							Game.resetStatsHighlighted || Game.checkMarkHighlighted ||
							Game.gamepadImageHighlighted || Game.noteImageHighlighted ||
							Game.backHighlighted))
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.yesClicked = false;
					Game.noClicked = false;
					Game.arrowL1Clicked = false;
					Game.arrowR1Clicked = false;
					Game.arrowL2Clicked = false;
					Game.arrowR2Clicked = false;
					Game.resetStatsClicked = false;
					Game.checkMarkClicked = false;
					Game.gamepadImageClicked = false;
					Game.noteImageClicked = false;
					Game.backClicked = false;
					Game.escapePressedNegateAction = true;
				}
	    		else if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
	    	}
	    	if((!XInputDevice.pausePressed && XInputDevice.pauseReleased) || 
	    			(!XInputDevice.shootPressed && XInputDevice.shootReleased)){
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
							Game.track4Unlocked = false;
							Game.track5Unlocked = false;
							Game.track6Unlocked = false;
							Game.fireball1Unlocked = false;
							Game.fireball2Unlocked = false;
							Game.fireball3Unlocked = false;
							Game.item4Unlocked = false;
							Game.item5Unlocked = false;
							Game.item6Unlocked = false;
							Game.menuTrack4Unlocked = false;
							Game.menuTrack5Set = false;
							Game.firstTimeBeating = false;
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
//							XInputDevice.a = XInputConstants.XINPUT_GAMEPAD_A;
//							XInputDevice.b = XInputConstants.XINPUT_GAMEPAD_B;
//							XInputDevice.x = XInputConstants.XINPUT_GAMEPAD_X;
//							XInputDevice.y = XInputConstants.XINPUT_GAMEPAD_Y;
//							XInputDevice.back = XInputConstants.XINPUT_GAMEPAD_BACK;
//							XInputDevice.start = XInputConstants.XINPUT_GAMEPAD_START;
//							XInputDevice.lShoulder = XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER;
//							XInputDevice.rShoulder = XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER;
//							XInputDevice.lThumb = XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB;
//							XInputDevice.rThumb = XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB;
//							XInputDevice.guide = XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON;
//							XInputDevice.unknown = XInputConstants.XINPUT_GAMEPAD_UNKNOWN;
//							XInputDevice.up = XInputConstants.XINPUT_GAMEPAD_DPAD_UP;
//							XInputDevice.down = XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN;
//							XInputDevice.left = XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT;
//							XInputDevice.right = XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT;
							Game.totalPoints = 0;
							Game.highScore = 0;
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
						case -5:
							Game.selectorButtonPosition = -1;
							if(Game.trackCurrentlyPlaying == false)
								TrackController.reset();
							Game.State = Game.STATE.TRACKLIST;
							if(smb3OpenSoundLoop.clipIsActive())
								smb3OpenSoundLoop.stop();
							smb3OpenSoundLoop.play();
							break;
						case -4:
							Game.selectorButtonPosition = -1;
							Game.State = Game.STATE.CONTROLS;
							if(smb3OpenSoundLoop.clipIsActive())
								smb3OpenSoundLoop.stop();
							smb3OpenSoundLoop.play();
							break;
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
	    	}
	    	if((!XInputDevice.cancelPressed && XInputDevice.cancelReleased) || 
	    			(!XInputDevice.itemPressed && XInputDevice.itemReleased)
	    		) {
	    		if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
					Game.selectorBPMP = Game.selectorButtonPosition;
					Game.escapePressedNegateAction = false;
					if(Game.gameControllerInUse) {
						Game.gameControllerInUse = false;
						Game.keysAreInUse = false;
					}
					else {
						Game.gameControllerInUse = true;
						Game.keysAreInUse = true;
					}
					if(areYouSureBoolean && Game.selectorButtonPosition != 0)
						Game.selectorButtonPosition = 0;
				}
	    	}
	    }
	    //START AGAIN HERE
	    else if(State == STATE.CONTROLS) {
	    	if((lY > .65 || (XInputDevice.upHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		if(System.currentTimeMillis() < ControlsController.buttonChangeTimer) {
				}
				else {
		    		if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
		    		else {
						if(Game.selectorButtonPosition == -2 || Game.selectorButtonPosition == -10) {
							Game.selectorButtonPosition = -1;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							hudSFX.get(hudSFXPosition).play();
						}
						else if(Game.selectorButtonPosition == -18) {
							Game.selectorButtonPosition = 0;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							Game.hudSFX.get(Game.hudSFXPosition).play();
						}
						else if(Game.selectorButtonPosition != -1 && Game.selectorButtonPosition != 0){
							Game.selectorButtonPosition++;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							hudSFX.get(hudSFXPosition).play();
						}
		    		}
		    		if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
				}
	    	}
	    	else if((lY < -.65 || (XInputDevice.downHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		if(System.currentTimeMillis() < ControlsController.buttonChangeTimer) {
				}
				else {
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					else {
						if(Game.selectorButtonPosition == -9 || Game.selectorButtonPosition == -17 ||
								Game.selectorButtonPosition == -25)
						{}
						else if(Game.selectorButtonPosition == 0){
							Game.selectorButtonPosition = -18;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							Game.hudSFX.get(Game.hudSFXPosition).play();
						}
						else if(Game.selectorButtonPosition != -26){
							Game.selectorButtonPosition--;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							hudSFX.get(hudSFXPosition).play();
						}
					}
					if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
				}
	    	}
	    	if((lX < -.65 || (XInputDevice.leftHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		if(System.currentTimeMillis() < ControlsController.buttonChangeTimer) {
				}
				else {
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					else {
						if(Game.selectorButtonPosition == -2 || Game.selectorButtonPosition == 0) {
							Game.selectorButtonPosition = -1;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							hudSFX.get(hudSFXPosition).play();
						}
						else if(Game.selectorButtonPosition == -26){
							Game.selectorButtonPosition = -25;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							hudSFX.get(hudSFXPosition).play();
						}
						else if (Game.selectorButtonPosition < -9){
							Game.selectorButtonPosition += 8;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							hudSFX.get(hudSFXPosition).play();
						}
					}
					if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
				}
	    	}
	    	else if((lX > .65 || (XInputDevice.rightHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		if(System.currentTimeMillis() < ControlsController.buttonChangeTimer) {
				}
				else {
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					else {
						if(Game.selectorButtonPosition == -1) {
							Game.selectorButtonPosition = -2;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							hudSFX.get(hudSFXPosition).play();
						}
						else if(Game.selectorButtonPosition == -25){
							Game.selectorButtonPosition = -26;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							hudSFX.get(hudSFXPosition).play();
						}
						else if (Game.selectorButtonPosition > -18 && Game.selectorButtonPosition != 0){
							Game.selectorButtonPosition -= 8;
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							hudSFX.get(hudSFXPosition).play();
						}
					}
					if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
				}
	    	}
	    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
	    		!(XInputDevice.rightHeld) && !(XInputDevice.leftHeld)&&
	    		!(XInputDevice.upHeld) && !(XInputDevice.downHeld)) {
	    		joystickTimer = 0;
	    	}
//    		if(device.getDelta().getButtons().isPressed(XInputButton.DPAD_UP)) {
//    			XInputDevice.upHeld = true;
//    		}
//    		if(device.getDelta().getButtons().isPressed(XInputButton.DPAD_DOWN)) {
//    			XInputDevice.downHeld = true;
//    		}
//    		if(device.getDelta().getButtons().isPressed(XInputButton.DPAD_LEFT)) {
//    			XInputDevice.leftHeld = true;
//    		}
//    		if(device.getDelta().getButtons().isPressed(XInputButton.DPAD_RIGHT)) {
//    			XInputDevice.rightHeld = true;
//    		}
	    	if(XInputDevice.pausePressed ||
	    			XInputDevice.shootPressed){
				Game.enterButtonPushedDown = true;
	    	}
	    	if(XInputDevice.cancelPressed ||
	    			XInputDevice.itemPressed
	    		) {
	    		if(Game.mouseIsClickedDown) {
					if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.backHighlighted || Game.resetHighlighted))
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					else if(!Game.mouseIsOffClickedObjectAndHeldDown) {
						for(int i = 0; i <= ControlsController.gamepadButtonHolderHighlighted.length-1; i++) {
							if(ControlsController.gamepadButtonHolderHighlighted[i] == true) {
								Game.mouseIsOffClickedObjectAndHeldDown = true;
								break;
							}
						}
					}
					for(int i = 0; i <= ControlsController.gamepadButtonHolderClicked.length-1; i++) {
						ControlsController.gamepadButtonHolderClicked[i] = false;
					}
					Game.backClicked = false;
					Game.resetClicked = false;
					Game.escapePressedNegateAction = true;
				}
	    		else if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
	    	}
	    	if(System.currentTimeMillis() < ControlsController.buttonChangeTimer) {
	    		if(0.3 < lTrigger && (!XInputDevice.lTriggerPressed || !XInputDevice.lTriggerReleased)) {
	    			XInputDevice.lTriggerPressed = true;
	    			XInputDevice.lTriggerReleased = true;
	    	    }
	    	    else if(lTrigger == 0 && XInputDevice.lTriggerPressed){
	    	    	XInputDevice.lTriggerPressed = false;
	    	    }
	    		if(0.3 < rTrigger && (!XInputDevice.rTriggerPressed || !XInputDevice.rTriggerReleased)) {
	    			XInputDevice.rTriggerPressed = true;
	    			XInputDevice.rTriggerReleased = true;
	    	    }
	    	    else if(rTrigger == 0 && XInputDevice.rTriggerPressed){
	    	    	XInputDevice.rTriggerPressed = false;
	    	    }
	    		short aFake = 0;
	    		short bFake = 0;
	    		short xFake = 0;
	    		short yFake = 0;
	    		short backFake = 0;
	    		short startFake = 0;
	    		short lShoulderFake = 0;
	    		short rShoulderFake = 0;
	    		short lThumbFake = 0;
	    		short rThumbFake = 0;
	    		short guideFake = 0;
	    		short unknownFake = 0;
	    		short upFake = 0;
	    		short downFake = 0;
	    		short leftFake = 0;
	    		short rightFake = 0;
	    		if(ControlsController.buttonToChange <= -10 && -17 <= ControlsController.buttonToChange) {
//		    		if(revertControllerSettings == false) {
//		    			device.aFake = XInputDevice.a;
//		    			device.bFake = XInputDevice.b;
//		    			device.xFake = XInputDevice.x;
//		    			device.yFake = XInputDevice.y;
//		    			device.backFake = XInputDevice.back;
//		    			device.startFake = XInputDevice.start;
//		    			device.lShoulderFake = XInputDevice.lShoulder;
//		    			device.rShoulderFake = XInputDevice.rShoulder;
//		    			device.lThumbFake = XInputDevice.lThumb;
//		    			device.rThumbFake = XInputDevice.rThumb;
//		    			device.guideFake = XInputDevice.guide;
//		    			device.unknownFake = XInputDevice.unknown;
//		    			device.upFake = XInputDevice.up;
//		    			device.downFake = XInputDevice.down;
//		    			device.leftFake = XInputDevice.left;
//		    			device.rightFake = XInputDevice.right;
//		    			device.lTriggerFake = XInputDevice.lTrigger;
//		    			device.rTriggerFake = XInputDevice.rTrigger;
//			    		XInputDevice.a = XInputConstants.XINPUT_GAMEPAD_A;
//			    		XInputDevice.b = XInputConstants.XINPUT_GAMEPAD_B;
//			    		XInputDevice.x = XInputConstants.XINPUT_GAMEPAD_X;
//			    		XInputDevice.y = XInputConstants.XINPUT_GAMEPAD_Y;
//			    		XInputDevice.back = XInputConstants.XINPUT_GAMEPAD_BACK;
//			    		XInputDevice.start = XInputConstants.XINPUT_GAMEPAD_START;
//			    		XInputDevice.lShoulder = XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER;
//			    		XInputDevice.rShoulder = XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER;
//			    		XInputDevice.lThumb = XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB;
//			    		XInputDevice.rThumb = XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB;
//			    		XInputDevice.guide = XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON;
//			    		XInputDevice.unknown = XInputConstants.XINPUT_GAMEPAD_UNKNOWN;
//			    		XInputDevice.up = XInputConstants.XINPUT_GAMEPAD_DPAD_UP;
//			    		XInputDevice.down = XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN;
//			    		XInputDevice.left = XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT;
//			    		XInputDevice.right = XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT;
//			    		XInputDevice.lTrigger = XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER;
//			    		XInputDevice.rTrigger = XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER;
//			    		revertControllerSettings = true;
//		    		}
	    		//if(ControlsController.buttonToChange <= -10 && -17 <= ControlsController.buttonToChange) {
	    			try {
		    		if(device.getDelta().getButtons().isReleased(XInputButton.A)) {
//		    			controlsController.changeButtonXDevice(XInputDevice.a);
						controlsController.changeButtonXDeviceEmulated(XInputConstants.XINPUT_GAMEPAD_A);
						
		    		}
		    		if(device.getDelta().getButtons().isReleased(XInputButton.B)) {
//		    			controlsController.changeButtonXDevice(XInputDevice.b);
		    			controlsController.changeButtonXDeviceEmulated(XInputConstants.XINPUT_GAMEPAD_B);
		    		}
		    		if(device.getDelta().getButtons().isReleased(XInputButton.X)) {
//		    			controlsController.changeButtonXDevice(XInputDevice.x);
		    			controlsController.changeButtonXDeviceEmulated(XInputConstants.XINPUT_GAMEPAD_X);
		    		}
		    		if(device.getDelta().getButtons().isReleased(XInputButton.Y)) {
//		    			controlsController.changeButtonXDevice(XInputDevice.y);
		    			controlsController.changeButtonXDeviceEmulated(XInputConstants.XINPUT_GAMEPAD_Y);
		    		}
		    		if(device.getDelta().getButtons().isReleased(XInputButton.BACK)) {
//		    			controlsController.changeButtonXDevice(XInputDevice.back);
		    			controlsController.changeButtonXDeviceEmulated(XInputConstants.XINPUT_GAMEPAD_BACK);
		    		}
		    		if(device.getDelta().getButtons().isReleased(XInputButton.START)) {
//		    			controlsController.changeButtonXDevice(XInputDevice.start);
		    			controlsController.changeButtonXDeviceEmulated(XInputConstants.XINPUT_GAMEPAD_START);
		    		}
		    		if(device.getDelta().getButtons().isReleased(XInputButton.LEFT_SHOULDER)) {
//		    			controlsController.changeButtonXDevice(XInputDevice.lShoulder);
		    			controlsController.changeButtonXDeviceEmulated(XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER);
		    		}
		    		if(device.getDelta().getButtons().isReleased(XInputButton.RIGHT_SHOULDER)) {
//		    			controlsController.changeButtonXDevice(XInputDevice.rShoulder);
		    			controlsController.changeButtonXDeviceEmulated(XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER);
		    		}
		    		if(device.getDelta().getButtons().isReleased(XInputButton.LEFT_THUMBSTICK)) {
//		    			controlsController.changeButtonXDevice(XInputDevice.lThumb);
		    			controlsController.changeButtonXDeviceEmulated(XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB);
		    		}
		    		if(device.getDelta().getButtons().isReleased(XInputButton.RIGHT_THUMBSTICK)) {
//		    			controlsController.changeButtonXDevice(XInputDevice.rThumb);
		    			controlsController.changeButtonXDeviceEmulated(XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB);
		    		}
		    		if(device.getDelta().getButtons().isReleased(XInputButton.GUIDE_BUTTON)) {
//		    			controlsController.changeButtonXDevice(XInputDevice.guide);
		    			controlsController.changeButtonXDeviceEmulated(XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON);
		    		}
		    		if(device.getDelta().getButtons().isReleased(XInputButton.UNKNOWN)) {
//		    			controlsController.changeButtonXDevice(XInputDevice.unknown);
		    			controlsController.changeButtonXDeviceEmulated(XInputConstants.XINPUT_GAMEPAD_UNKNOWN);
		    		}
		    		if(device.getDelta().getButtons().isReleased(XInputButton.DPAD_UP)) {
//		    			controlsController.changeButtonXDevice(XInputDevice.up);
		    			controlsController.changeButtonXDeviceEmulated(XInputConstants.XINPUT_GAMEPAD_DPAD_UP);
		    		}
		    		if(device.getDelta().getButtons().isReleased(XInputButton.DPAD_DOWN)) {
//		    			controlsController.changeButtonXDevice(XInputDevice.down);
		    			controlsController.changeButtonXDeviceEmulated(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN);
		    		}
		    		if(device.getDelta().getButtons().isReleased(XInputButton.DPAD_LEFT)) {
//		    			controlsController.changeButtonXDevice(XInputDevice.left);
		    			controlsController.changeButtonXDeviceEmulated(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT);
		    		}
		    		if(device.getDelta().getButtons().isReleased(XInputButton.DPAD_RIGHT)) {
//		    			controlsController.changeButtonXDevice(XInputDevice.right);
		    			controlsController.changeButtonXDeviceEmulated(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT);
		    		}
		    	    if(!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased) {
//		    			controlsController.changeButtonXDevice(XInputDevice.lTrigger);
		    			controlsController.changeButtonXDeviceEmulated(XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER);
		    			//add method
		    	    }
		    	    if(!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased) {
//		    			controlsController.changeButtonXDevice(XInputDevice.rTrigger);
		    			controlsController.changeButtonXDeviceEmulated(XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER);
		    			//add method
		    	    }
		    	    } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    		if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	else {
//	    		if(device.getDelta().getButtons().isReleased(XInputButton.DPAD_UP)) {
//	    			XInputDevice.upHeld = false;
//	    		}
//	    		if(device.getDelta().getButtons().isReleased(XInputButton.DPAD_DOWN)) {
//	    			XInputDevice.downHeld = false;
//	    		}
//	    		if(device.getDelta().getButtons().isReleased(XInputButton.DPAD_LEFT)) {
//	    			XInputDevice.leftHeld = false;
//	    		}
//	    		if(device.getDelta().getButtons().isReleased(XInputButton.DPAD_RIGHT)) {
//	    			XInputDevice.rightHeld = false;
//	    		}
	    		if((!XInputDevice.pausePressed && XInputDevice.pauseReleased) || 
	    				(!XInputDevice.shootPressed && XInputDevice.shootReleased)){
					Game.enterButtonPushedDown = false;
	    			if(!Game.escapePressedNegateAction) {
						if(Game.selectorButtonPosition == -1) {
							Game.selectorButtonPosition = -4;
							Game.State = Game.STATE.SETTINGS;
							if(Game.smb3KickSoundLoop.clipIsActive())
								Game.smb3KickSoundLoop.stop();
							Game.smb3KickSoundLoop.play();
						}
						else if(Game.selectorButtonPosition == 0) {
							if(Game.rescanButtonCounter < 10) {
								ReadAllEvents.resettingControllerEnvironment = true;
								DirectAndRawInputEnvironmentPlugin directEnv = new DirectAndRawInputEnvironmentPlugin();
								if(directEnv != null & directEnv.isSupported())
									ControllerEnvironment.setDefaultEnvironment(directEnv);
								ReadAllEvents.controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
								if(Game.smb3Checkmark2SoundLoop.clipIsActive())
									Game.smb3Checkmark2SoundLoop.stop();
								Game.smb3Checkmark2SoundLoop.play();
								Game.rescanButtonCounter++;
							}
							else {
								if(Game.smwErrorSoundLoop.clipIsActive())
									Game.smwErrorSoundLoop.stop();
								Game.smwErrorSoundLoop.play();
							}
						}
						else if(Game.selectorButtonPosition == -26) {
							Game.upKey = KeyEvent.VK_W;
							Game.downKey = KeyEvent.VK_S;
							Game.leftKey = KeyEvent.VK_A;
							Game.rightKey = KeyEvent.VK_D;
							Game.shootKey = KeyEvent.VK_SPACE;
							Game.itemKey = KeyEvent.VK_E;
							Game.pauseKey = KeyEvent.VK_ENTER;
							Game.cancelKey = KeyEvent.VK_ESCAPE;
							XInputDevice.upKey = XInputConstants.XINPUT_GAMEPAD_DPAD_UP;
							XInputDevice.downKey = XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN;
							XInputDevice.leftKey = XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT;
							XInputDevice.rightKey = XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT;
							XInputDevice.shootKey = XInputConstants.XINPUT_GAMEPAD_A;
							XInputDevice.itemKey = XInputConstants.XINPUT_GAMEPAD_X;
							XInputDevice.pauseKey = XInputConstants.XINPUT_GAMEPAD_START;
							XInputDevice.cancelKey = XInputConstants.XINPUT_GAMEPAD_B;
							XInputDevice.up = XInputConstants.XINPUT_GAMEPAD_DPAD_UP;
							XInputDevice.down = XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN;
							XInputDevice.left = XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT;
							XInputDevice.right = XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT;
							XInputDevice.a = XInputConstants.XINPUT_GAMEPAD_A;
							XInputDevice.b = XInputConstants.XINPUT_GAMEPAD_B;
							XInputDevice.x = XInputConstants.XINPUT_GAMEPAD_X;
							XInputDevice.y = XInputConstants.XINPUT_GAMEPAD_Y;
							XInputDevice.lShoulder = XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER;
							XInputDevice.rShoulder = XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER;
							XInputDevice.lThumb = XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB;
							XInputDevice.rThumb = XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB;
							XInputDevice.start = XInputConstants.XINPUT_GAMEPAD_START;
							XInputDevice.back = XInputConstants.XINPUT_GAMEPAD_BACK;
							XInputDevice.guide = XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON;
							XInputDevice.unknown = XInputConstants.XINPUT_GAMEPAD_UNKNOWN;
							XInputDevice.lTrigger = XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER;
							XInputDevice.rTrigger = XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER;
							SwitchedValues.resetControlsfromResetButton();
							Game.writeOnceToSettings = true;
							Game.writeOnceProperty = "upKey";
							Game.writeOnceString = String.valueOf(KeyEvent.VK_W);
							Game.writeMultipleProperty.add("downKey");
							Game.writeMultipleString.add(String.valueOf(KeyEvent.VK_S));
							Game.writeMultipleProperty.add("leftKey");
							Game.writeMultipleString.add(String.valueOf(KeyEvent.VK_A));
							Game.writeMultipleProperty.add("rightKey");
							Game.writeMultipleString.add(String.valueOf(KeyEvent.VK_D));
							Game.writeMultipleProperty.add("shootKey");
							Game.writeMultipleString.add(String.valueOf(KeyEvent.VK_SPACE));
							Game.writeMultipleProperty.add("itemKey");
							Game.writeMultipleString.add(String.valueOf(KeyEvent.VK_E));
							Game.writeMultipleProperty.add("pauseKey");
							Game.writeMultipleString.add(String.valueOf(KeyEvent.VK_ENTER));
							Game.writeMultipleProperty.add("cancelKey");
							Game.writeMultipleString.add(String.valueOf(KeyEvent.VK_ESCAPE));
							Game.writeMultipleProperty.add("upKeyXInput");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_UP));
							Game.writeMultipleProperty.add("downKeyXInput");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN));
							Game.writeMultipleProperty.add("leftKeyXInput");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT));
							Game.writeMultipleProperty.add("rightKeyXInput");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT));
							Game.writeMultipleProperty.add("shootKeyXInput");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_A));
							Game.writeMultipleProperty.add("itemKeyXInput");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_X));
							Game.writeMultipleProperty.add("pauseKeyXInput");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_START));
							Game.writeMultipleProperty.add("cancelKeyXInput");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_B));
							Game.writeMultipleProperty.add("upButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_UP));
							Game.writeMultipleProperty.add("downButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN));
							Game.writeMultipleProperty.add("leftButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT));
							Game.writeMultipleProperty.add("rightButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT));
							Game.writeMultipleProperty.add("aButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_A));
							Game.writeMultipleProperty.add("bButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_B));
							Game.writeMultipleProperty.add("xButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_X));
							Game.writeMultipleProperty.add("yButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_Y));
							Game.writeMultipleProperty.add("lShoulderButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER));
							Game.writeMultipleProperty.add("rShoulderButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER));
							Game.writeMultipleProperty.add("lThumbButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB));
							Game.writeMultipleProperty.add("rThumbButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB));
							Game.writeMultipleProperty.add("startButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_START));
							Game.writeMultipleProperty.add("backButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_BACK));
							Game.writeMultipleProperty.add("guideButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON));
							Game.writeMultipleProperty.add("unknownButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_UNKNOWN));
							Game.writeMultipleProperty.add("lTriggerButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER));
							Game.writeMultipleProperty.add("rTriggerButton");
							Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER));
							Game.writeMultipleProperty.add("upKeyDirectInput");
							Game.writeMultipleString.add(SwitchedValues.upKey);
							Game.writeMultipleProperty.add("downKeyDirectInput");
							Game.writeMultipleString.add(SwitchedValues.downKey);
							Game.writeMultipleProperty.add("leftKeyDirectInput");
							Game.writeMultipleString.add(SwitchedValues.leftKey);
							Game.writeMultipleProperty.add("rightKeyDirectInput");
							Game.writeMultipleString.add(SwitchedValues.rightKey);
							Game.writeMultipleProperty.add("shootKeyDirectInput");
							Game.writeMultipleString.add(SwitchedValues.shootKey);
							Game.writeMultipleProperty.add("itemKeyDirectInput");
							Game.writeMultipleString.add(SwitchedValues.itemKey);
							Game.writeMultipleProperty.add("pauseKeyDirectInput");
							Game.writeMultipleString.add(SwitchedValues.pauseKey);
							Game.writeMultipleProperty.add("cancelKeyDirectInput");
							Game.writeMultipleString.add(SwitchedValues.cancelKey);
							Game.settingsSetup = false;
							if(Game.smb3TailSoundLoop.clipIsActive())
								Game.smb3TailSoundLoop.stop();
							Game.smb3TailSoundLoop.play();
						}
						else if(Game.selectorButtonPosition < -1 && Game.selectorButtonPosition > -26) {
							if(Game.selectorButtonPosition<= -18 && -25 <= Game.selectorButtonPosition) {
								Game.gameControllerInUseDI = true;
								Game.gameControllerSwitchBackDI = true;
							}
							ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
							ControlsController.buttonToChange = Game.selectorButtonPosition;
						}
					}
					Game.escapePressedNegateAction = false;
	    		}
	    		if((!XInputDevice.cancelPressed && XInputDevice.cancelReleased) || 
	    				(!XInputDevice.itemPressed && XInputDevice.itemReleased)) {
	    			if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
	    				Game.selectorBPMP = Game.selectorButtonPosition;
						Game.escapePressedNegateAction = false;
						if(Game.gameControllerInUse) {
							Game.gameControllerInUse = false;
							Game.keysAreInUse = false;
						}
						else {
							Game.gameControllerInUse = true;
							Game.keysAreInUse = true;
						}
					}
	    		}
	    	}
	    }//START AGAIN HERE
	    else if(State == STATE.TRACKLIST && gameControllerInUse) {
	    	if((0.65 < lY  || (XInputDevice.upHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		int selector = Game.selectorButtonPosition;
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				switch(Game.selectorButtonPosition) {
				case -11:
					Game.selectorButtonPosition = -10;
					break;
				case -9:
					Game.selectorButtonPosition = -8;
					break;
				case -7:
					Game.selectorButtonPosition = -6;
					break;
				case -5:
					Game.selectorButtonPosition = -4;
					break;
				case -3:
					Game.selectorButtonPosition = -2;
					break;
				case -2: case -4: case -6: case -8: case -10:
					Game.selectorButtonPosition = -1;
					break;
				default:
					break;
				}
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					Game.hudSFX.get(Game.hudSFXPosition).play();
				}
	    		if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	else if((lY < -0.65 || (XInputDevice.downHeld)) && joystickTimer < System.currentTimeMillis()) {
		    		int selector = Game.selectorButtonPosition;
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					switch(Game.selectorButtonPosition) {
					case -10:
						Game.selectorButtonPosition = -11;
						break;
					case -8:
						Game.selectorButtonPosition = -9;
						break;
					case -6:
						Game.selectorButtonPosition = -7;
						break;
					case -4:
						Game.selectorButtonPosition = -5;
						break;
					case -2:
						Game.selectorButtonPosition = -3;
						break;
					case -1:
						Game.selectorButtonPosition = -2;
						break;
					default:
						break;
					}
					if(Game.selectorButtonPosition != selector) {
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
		    		if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
	    	if((lX < -.65 || (XInputDevice.leftHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		int selector = Game.selectorButtonPosition;
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(Game.selectorButtonPosition < -3)
					Game.selectorButtonPosition+=2;
				else if(Game.selectorButtonPosition != -2 && Game.selectorButtonPosition != -3)
					Game.selectorButtonPosition = -1;
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					Game.hudSFX.get(Game.hudSFXPosition).play();
				}
	    		if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	else if((lX > .65 || (XInputDevice.rightHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		int selector = Game.selectorButtonPosition;
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(-9 <= Game.selectorButtonPosition && Game.selectorButtonPosition != -1)
					Game.selectorButtonPosition-=2;
				else if(Game.selectorButtonPosition != -10 && Game.selectorButtonPosition != -1)
					Game.selectorButtonPosition = -11;
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					Game.hudSFX.get(Game.hudSFXPosition).play();
				}
	    		if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
	    		!(XInputDevice.rightHeld) && !(XInputDevice.leftHeld)&&
	    		!(XInputDevice.upHeld) && !(XInputDevice.downHeld)) {
	    		joystickTimer = 0;
		    }
	    	if(XInputDevice.pausePressed ||
	    			XInputDevice.shootPressed) {
				Game.enterButtonPushedDown = true;
	    	}
	    	if(XInputDevice.cancelPressed ||
	    			XInputDevice.itemPressed){
	    		if(Game.mouseIsClickedDown) {
					if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.backHighlighted))
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.backClicked = false;
					Game.escapePressedNegateAction = true;
				}
	    		else if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
	    	}

	    	if((!XInputDevice.pausePressed && XInputDevice.pauseReleased) ||
	    		(!XInputDevice.shootPressed && XInputDevice.shootReleased)
		    		) {
	    		Game.enterButtonPushedDown = false;
				if(!Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -1:
						Game.selectorButtonPosition = -5;
						Game.State = Game.STATE.SETTINGS;
						if(Game.smb3KickSoundLoop.clipIsActive())
							Game.smb3KickSoundLoop.stop();
						Game.smb3KickSoundLoop.play();
						break;
					case -2:
						if(Game.gameTrackPosition > 0)
							Game.gameTrackPosition--;
						else
							Game.gameTrackPosition = 7;//Set to Max Tracks
						Game.gameTrackNumber = Game.makeTransparent(Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.gameTrackPosition+1)), 20, 20));
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
						break;
					case -3:
						if(Game.menuTrackPosition > 0)
							Game.menuTrackPosition--;
						else
							Game.menuTrackPosition = 4;//Set to Max Tracks
						Game.menuTrackNumber = Game.makeTransparent(Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.menuTrackPosition+1)), 20, 20));
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
						break;
					case -4:
						if(Game.gameTrackPosition == 7)//Max Tracks
							Game.gameTrackPosition = 0;
						else 
							Game.gameTrackPosition++;
						Game.gameTrackNumber = Game.makeTransparent(Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.gameTrackPosition+1)), 20, 20));
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
						break;
					case -5:
						if(Game.menuTrackPosition == 4)//Max Tracks
							Game.menuTrackPosition = 0;
						else 
							Game.menuTrackPosition++;
						Game.menuTrackNumber = Game.makeTransparent(Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.menuTrackPosition+1)), 20, 20));
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
						break;
					case -6://play1
						boolean b = true;
						switch(Game.gameTrackPosition) {
						case 4:
							if(!Game.track4Unlocked) {
								if(Game.smwErrorSoundLoop.clipIsActive())
									Game.smwErrorSoundLoop.stop();
								Game.smwErrorSoundLoop.play();
								b = false;
							}
							break;
						case 5:
							if(!Game.track5Unlocked) {
								if(Game.smwErrorSoundLoop.clipIsActive())
									Game.smwErrorSoundLoop.stop();
								Game.smwErrorSoundLoop.play();
								b = false;
							}
							break;
						case 6:
							if(!Game.track6Unlocked) {
								if(Game.smwErrorSoundLoop.clipIsActive())
									Game.smwErrorSoundLoop.stop();
								Game.smwErrorSoundLoop.play();
								b = false;
							}
							break;
						default:
							break;
						}
						if(b)
							TrackController.readyToSwitch1 = true;
						break;
					case -7://play2
						boolean bb = true;
						switch(Game.menuTrackPosition) {
						case 4:
							if(!Game.menuTrack4Unlocked) {
								if(Game.smwErrorSoundLoop.clipIsActive())
									Game.smwErrorSoundLoop.stop();
								Game.smwErrorSoundLoop.play();
								bb = false;
							}
							break;
						default:
							break;
						}
						if(bb)
							TrackController.readyToSwitch2 = true;
						break;
					case -8://stop1
						if(Game.gameTrackCurrentlyPlaying != -1) {// && Game.gameTrackCurrentlyPlaying == Game.gameTrackPosition) {
							TrackController.trackSetup = false;
							Game.trackCurrentlyPlaying = false;
							Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).stop();
							Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).setFramePosition(0);
						}
						else
							Game.gameSoundLoops.get(Game.gameTrackPosition).setFramePosition(0);
						if(Game.smb3Checkmark2SoundLoop.clipIsActive())
							Game.smb3Checkmark2SoundLoop.stop();
						Game.smb3Checkmark2SoundLoop.play();
						break;
					case -9://stop2
						if(Game.trackCurrentlyPlaying && Game.menuTrackCurrentlyPlaying != -1) { //&& Game.menuTrackCurrentlyPlaying == Game.menuTrackPosition) {
							TrackController.trackSetup = false;
							Game.trackCurrentlyPlaying = false;
							Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).stop();
							Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).setFramePosition(0);
						}
						else if(Game.menuTrackCurrentlyPlaying != -1)//Game.menuTrackPosition != Game.menuSoundLoopRandomizer)
							Game.menuSoundLoops.get(Game.menuTrackPosition).setFramePosition(0);
						if(Game.smb3Checkmark2SoundLoop.clipIsActive())
							Game.smb3Checkmark2SoundLoop.stop();
						Game.smb3Checkmark2SoundLoop.play();
						break;
					case -10://set1
						String s = "";
						String ss = "";
						switch(Game.gameTrackPosition) {
						case 0:
							s = "gameTrack1Set";
							if(Game.gameTrack1Set) {
								Game.gameTrack1Set = false;
								ss = "false";
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.gameTrack1Set = true;
								ss = "true";
							}
							break;
						case 1:
							s = "gameTrack2Set";
							if(Game.gameTrack2Set) {
								Game.gameTrack2Set = false;
								ss = "false";
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.gameTrack2Set = true;
								ss = "true";
							}
							break;
						case 2:
							s = "gameTrack3Set";
							if(Game.gameTrack3Set) {
								Game.gameTrack3Set = false;
								ss = "false";
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.gameTrack3Set = true;
								ss = "true";
							}
							break;
						case 3:
							s = "gameTrack4Set";
							if(Game.gameTrack4Set) {
								Game.gameTrack4Set = false;
								ss = "false";
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.gameTrack4Set = true;
								ss = "true";
							}
							break;
						case 4:
							s = "gameTrack5Set";
							if(Game.gameTrack5Set || !Game.track4Unlocked) {
								if(!Game.track4Unlocked) {
									if(Game.smwErrorSoundLoop.clipIsActive())
										Game.smwErrorSoundLoop.stop();
									Game.smwErrorSoundLoop.play();
								}
								else {
									if(Game.smbPopSoundLoop.clipIsActive())
										Game.smbPopSoundLoop.stop();
									Game.smbPopSoundLoop.play();
								}
								Game.gameTrack5Set = false;
								ss = "false";
							}
							else {
								Game.gameTrack5Set = true;
								ss = "true";
							}
							break;
						case 5:
							s = "gameTrack6Set";
							if(Game.gameTrack6Set || !Game.track5Unlocked) {
								if(!Game.track5Unlocked) {
									if(Game.smwErrorSoundLoop.clipIsActive())
										Game.smwErrorSoundLoop.stop();
									Game.smwErrorSoundLoop.play();
								}
								else {
									if(Game.smbPopSoundLoop.clipIsActive())
										Game.smbPopSoundLoop.stop();
									Game.smbPopSoundLoop.play();
								}
								Game.gameTrack6Set = false;
								ss = "false";
							}
							else {
								Game.gameTrack6Set = true;
								ss = "true";
							}
							break;
						case 6:
							s = "gameTrack7Set";
							if(Game.gameTrack7Set || !Game.track6Unlocked) {
								if(!Game.track6Unlocked) {
									if(Game.smwErrorSoundLoop.clipIsActive())
										Game.smwErrorSoundLoop.stop();
									Game.smwErrorSoundLoop.play();
								}
								else {
									if(Game.smbPopSoundLoop.clipIsActive())
										Game.smbPopSoundLoop.stop();
									Game.smbPopSoundLoop.play();
								}
								Game.gameTrack7Set = false;
								ss = "false";
							}
							else {
								Game.gameTrack7Set = true;
								ss = "true";
							}
							break;
						case 7:
							s = "gameTrack8Set";
							if(Game.gameTrack8Set) {
								Game.gameTrack8Set = false;
								ss = "false";
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.gameTrack8Set = true;
								ss = "true";
							}
							break;
						default:
							break;
						}
						if(ss.equals("true")) {
							if(Game.smb3ItemSoundLoop.clipIsActive())
								Game.smb3ItemSoundLoop.stop();
							Game.smb3ItemSoundLoop.play();
						}
						if(!s.equals("") && !ss.equals("")) {
							try {
								LeaderboardController.writeToSettings(s, ss);
							} catch (IOException e1) {
							}
							if(ss.equals("true") && Game.soundRandomizer == 8 && Game.gameTrackCurrentlyPlaying == -1  && Game.menuTrackCurrentlyPlaying == -1) {
								int randomIPlus = 0;
								if(Game.track4Unlocked)
									randomIPlus++;
								if(Game.track5Unlocked)
									randomIPlus++;
								if(Game.track6Unlocked)
									randomIPlus++;
								Random rand = new Random();
								Game.soundRandomizer = rand.nextInt(7) + (-3+randomIPlus);
								Game.soundRandomizer = VolumeSlider.gameTrackSetup(Game.soundRandomizer);
								//setsetup[VolumeSlider.TrackSetup]
								if(Game.soundRandomizer != -4) {
									Game.gameSoundLoops.get(Game.soundRandomizer).stop();
									Game.gameSoundLoops.get(Game.soundRandomizer).setSoundLoopBoolean(true);
									Game.gameSoundLoops.get(Game.soundRandomizer).setFramePosition(0);
//									Game.gameSoundLoops.get(Game.soundRandomizer).play();
								}
								else
									Game.soundRandomizer = 8;
							}
							else if(ss.equals("false")) {  
								if(Game.gameTrack1Set == false && Game.gameTrack2Set == false && Game.gameTrack3Set == false && 
									Game.gameTrack4Set == false && Game.gameTrack5Set == false && Game.gameTrack6Set == false && 
									Game.gameTrack7Set == false && Game.gameTrack8Set == false && Game.soundRandomizer != 8 &&
									Game.soundRandomizer != -4) 
								Game.soundRandomizer = 8;
							}
						}
						break;
					case -11://set2
						String string1 = "";
						String string2 = "";
						switch(Game.menuTrackPosition) {
						case 0:
							string1 = "menuTrack1Set";
							if(Game.menuTrack1Set) {
								Game.menuTrack1Set = false;
								string2 = "false";
								if(Game.menuSoundLoopRandomizer == 0) 
									Game.resetMenuSound();
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.menuTrack1Set = true;
								string2 = "true";
							}
							break;
						case 1:
							string1 = "menuTrack2Set";
							if(Game.menuTrack2Set) {
								Game.menuTrack2Set = false;
								string2 = "false";
								if(Game.menuSoundLoopRandomizer == 1) 
									Game.resetMenuSound();
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.menuTrack2Set = true;
								string2 = "true";
							}
							break;
						case 2:
							string1 = "menuTrack3Set";
							if(Game.menuTrack3Set) {
								Game.menuTrack3Set = false;
								string2 = "false";
								if(Game.menuSoundLoopRandomizer == 2) 
									Game.resetMenuSound();
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.menuTrack3Set = true;
								string2 = "true";
							}
							break;
						case 3:
							string1 = "menuTrack4Set";
							if(Game.menuTrack4Set) {
								Game.menuTrack4Set = false;
								string2 = "false";
								if(Game.menuSoundLoopRandomizer == 3) 
									Game.resetMenuSound();
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.menuTrack4Set = true;
								string2 = "true";
							}
							break;
						case 4:
							string1 = "menuTrack5Set";
							if(Game.menuTrack5Set || !Game.menuTrack4Unlocked) {
								if(!Game.menuTrack4Unlocked) {
									if(Game.smwErrorSoundLoop.clipIsActive())
										Game.smwErrorSoundLoop.stop();
									Game.smwErrorSoundLoop.play();
								}
								else {
									if(Game.smbPopSoundLoop.clipIsActive())
										Game.smbPopSoundLoop.stop();
									Game.smbPopSoundLoop.play();
								}
								Game.menuTrack5Set = false;
								string2 = "false";
								if(Game.menuSoundLoopRandomizer == 4) 
									Game.resetMenuSound();
							}
							else {
								Game.menuTrack5Set = true;
								string2 = "true";
							}
							break;
						default:
							break;
						}
						if(string2.equals("true")) {
							if(Game.smb3ItemSoundLoop.clipIsActive())
								Game.smb3ItemSoundLoop.stop();
							Game.smb3ItemSoundLoop.play();
						}
						if(!string1.equals("") && !string2.equals("")) {
							try {
								LeaderboardController.writeToSettings(string1, string2);
							} catch (IOException e1) {
							}

							if(string2.equals("true") && Game.menuSoundLoopRandomizer == 5 && Game.gameTrackCurrentlyPlaying == -1  && Game.menuTrackCurrentlyPlaying == -1) {
								Random rand = new Random();
								Game.menuSoundLoopRandomizer = rand.nextInt(5);
								VolumeSlider.adjustVolume(Game.menuSoundLoops, Game.gameSoundLoops);
								Game.menuSoundLoopRandomizer = VolumeSlider.menuTrackSetup(Game.menuSoundLoopRandomizer);
								if(Game.menuSoundLoopRandomizer == -2)
									Game.menuSoundLoopRandomizer = 5;
								if(Game.menuSoundLoopRandomizer == 2) 
//									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(864007, 1728014);//Seconds.millisec * 44100
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(432004, 864007);//Seconds.millisec * 22050
								else if(Game.menuSoundLoopRandomizer == 3) 
//									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(464196, 1949705);//Seconds.millisec * 44100
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(232098, 974853);//Seconds.millisec * 22050
								else if(Game.menuSoundLoopRandomizer == 4)
//									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(545561, 2291391);//Seconds.millisec * 44100
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(272781, 1145696);//Seconds.millisec * 22050
								else
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loop();
								Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).setSoundLoopBoolean(true);
							}
							else if(string2.equals("false")) {
								if(Game.gameTrackCurrentlyPlaying == -1  && Game.menuTrackCurrentlyPlaying == -1 && 
									Game.menuTrack1Set == false && Game.menuTrack2Set == false && Game.menuTrack3Set == false && 
									Game.menuTrack4Set == false && Game.menuTrack5Set == false && Game.menuSoundLoopRandomizer != 5 &&
									Game.menuSoundLoopRandomizer != -2) {
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).stop();
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).setFramePosition(0);
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).setSoundLoopBoolean(false);
									Game.menuSoundLoopRandomizer = 5;
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loop();
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).setSoundLoopBoolean(true);
								}
							}
						}
						break;
					}
				}
				Game.escapePressedNegateAction = false;
			}
	    	if((!XInputDevice.itemPressed && XInputDevice.itemReleased) ||
		    	(!XInputDevice.cancelPressed && XInputDevice.cancelReleased)
		    		) {
	    		if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
					Game.selectorBPMP = Game.selectorButtonPosition;
					Game.escapePressedNegateAction = false;
					if(Game.gameControllerInUse) {
						Game.gameControllerInUse = false;
						Game.keysAreInUse = false;
					}
					else {
						Game.gameControllerInUse = true;
						Game.keysAreInUse = true;
					}
	    		}
	    	}
	    }
	    else if(State == STATE.HELP && gameControllerInUse) {
	    	if((lX < -.65 || (XInputDevice.leftHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		int selector = Game.selectorButtonPosition;
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(Game.firstTimeBeating) {
					if(Game.selectorButtonPosition != -1)
						Game.selectorButtonPosition = -1;
				}
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					Game.hudSFX.get(Game.hudSFXPosition).play();
				}
	    		if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	else if((lX > .65 || (XInputDevice.rightHeld)) && joystickTimer < System.currentTimeMillis()) {
	    		int selector = Game.selectorButtonPosition;
				if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
				if(Game.firstTimeBeating) {
					if(Game.selectorButtonPosition != 0)
						Game.selectorButtonPosition = 0;
				}
				if(Game.selectorButtonPosition != selector) {
					if(Game.hudSFXPosition == 3)
						Game.hudSFXPosition = 0;
					else
						Game.hudSFXPosition++;
					Game.hudSFX.get(Game.hudSFXPosition).play();
				}
	    		if(joystickTimer == 0)
					joystickTimer = System.currentTimeMillis() + 500;
				else
					joystickTimer = System.currentTimeMillis() + 30;
	    	}
	    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
		    		!(XInputDevice.rightHeld) && !(XInputDevice.leftHeld)&&
		    		!(XInputDevice.upHeld) && !(XInputDevice.downHeld)) {
		    		joystickTimer = 0;
		    }
	    	if(XInputDevice.pausePressed ||
			    	XInputDevice.shootPressed) {
				Game.enterButtonPushedDown = true;
	    	}
	    	if(XInputDevice.cancelPressed ||
	    			XInputDevice.itemPressed){
	    		if(Game.mouseIsClickedDown) {
					if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.backHighlighted))
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.backClicked = false;
					Game.escapePressedNegateAction = true;
				}
	    		else if(Game.enterButtonPushedDown)
					Game.escapePressedNegateAction = true;
	    	}

	    	if((!XInputDevice.pausePressed && XInputDevice.pauseReleased) ||
		    	(!XInputDevice.shootPressed && XInputDevice.shootReleased)
		    		) {
	    		Game.enterButtonPushedDown = false;
				if(!Game.escapePressedNegateAction) {
					switch(Game.selectorButtonPosition) {
					case -1:
						Game.selectorButtonPosition = -1;
						Game.State = Game.STATE.MENU;
						if(Game.smb3KickSoundLoop.clipIsActive())
							Game.smb3KickSoundLoop.stop();
						Game.smb3KickSoundLoop.play();
						break;
					case 0:
						if(Game.firstTimeBeating) {
							Game.State = STATE.CREDITS;
							if(Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).clipIsActive()) {
								Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).stop();
							}
							Game.keysAreInUse = false;
							Game.gameControllerInUse = false;
							Game.gameControllerInUseDI = false;
							Game.skipSequence = false;
							Game.askToSkipSequence = false;
							Game.selectorButtonPosition = 0;
						}
						break;
					}
				}
				Game.escapePressedNegateAction = false;
			}
	    	if((!XInputDevice.itemPressed && XInputDevice.itemReleased) ||
			    (!XInputDevice.cancelPressed && XInputDevice.cancelReleased)
	    		) {
	    		if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
					Game.selectorBPMP = Game.selectorButtonPosition;
					Game.escapePressedNegateAction = false;
					if(Game.gameControllerInUse) {
						Game.gameControllerInUse = false;
						Game.keysAreInUse = false;
					}
					else {
						Game.gameControllerInUse = true;
						Game.keysAreInUse = true;
					}
	    		}
	    	}
	    }
		 if(!XInputDevice.shootPressed && XInputDevice.shootReleased)
			 XInputDevice.shootReleased = false;
		 if(!XInputDevice.itemPressed && XInputDevice.itemReleased)
			 XInputDevice.itemReleased = false;
		 if(!XInputDevice.pausePressed && XInputDevice.pauseReleased)
			 XInputDevice.pauseReleased = false;
		 if(!XInputDevice.cancelPressed && XInputDevice.cancelReleased)
			 XInputDevice.cancelReleased = false;
		 if(!XInputDevice.upHeld && XInputDevice.upReleased)
			 XInputDevice.upReleased = false;
		 if(!XInputDevice.downHeld && XInputDevice.downReleased)
			 XInputDevice.downReleased = false;
		 if(!XInputDevice.leftHeld && XInputDevice.leftReleased)
			 XInputDevice.leftReleased = false;
		 if(!XInputDevice.rightHeld && XInputDevice.rightReleased)
			 XInputDevice.rightReleased = false;
//	    //TEST
		 if(!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
			 XInputDevice.lTriggerReleased = false;
		 if(!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
			 XInputDevice.rTriggerReleased = false;
//	    //TEST
//	    if(device.getDelta().getButtons().isReleased(XInputButton.DPAD_UP)) {
//			XInputDevice.upHeld = false;
//		}
//		if(device.getDelta().getButtons().isReleased(XInputButton.DPAD_DOWN)) {
//			XInputDevice.downHeld = false;
//		}
//		if(device.getDelta().getButtons().isReleased(XInputButton.DPAD_LEFT)) {
//			XInputDevice.leftHeld = false;
//		}
//		if(device.getDelta().getButtons().isReleased(XInputButton.DPAD_RIGHT)) {
//			XInputDevice.rightHeld = false;
//		}
		//gameControllerInUse = true;//activate this when you actually do something
	}
//	public static void directInput() {
//		Component comp = ReadAllEvents.event.getComponent();
//		float value = ReadAllEvents.event.getValue();
//		if(comp == null)
//			return;
//		if(comp.isAnalog() && !comp.isRelative()) {
//			if(!Game.keysAreInUse)
//				Game.keysAreInUse = true;
//			//CONTROLLER ANALOG STICK
//			if(comp.getName().equals("Y Axis") ||comp.getName().equals("y")) {
//				if(0 < value) {
//					Game.selectorButtonPosition--;
//				}
//			}
//		}
//	}
	public static void helpLinesLastSetup() {
		BufferedImage line = null;
		int noRepeatingLines = helpLineRandomizer;
		Random rand = new Random();
		int randomIPlus = 0;
		if(Game.item4Unlocked)
			randomIPlus++;
		if(Game.item5Unlocked)
			randomIPlus++;
		if(Game.item6Unlocked)
			randomIPlus++;
		helpLineRandomizer = rand.nextInt(16) + (-3+randomIPlus);
		if(helpLineRandomizer == noRepeatingLines && helpLineRandomizer == 15)
			helpLineRandomizer = 0;
		else if(helpLineRandomizer == noRepeatingLines && helpLineRandomizer != 16)
			helpLineRandomizer++;
//		helpLineRandomizer=0;
		switch(helpLineRandomizer) {
		case 0:
//			line = HUD.stringToMario3FontImage("Blocks give no points for stopping enemies");
			line = HUD.stringToMario3FontImage("Enemies give no points if stopped by blocks");
			line = Game.resize(line, (int)(line.getWidth()/1.1), (line.getHeight()));
			break;
		case 1: case -1:
			line = HUD.stringToMario3FontImage("Blocks are worth more points if saved");
			break;
		case 2: case -2:
			line = HUD.stringToMario3FontImage("Skipping scenes activates skip animations");
			line = Game.resize(line, (int)(line.getWidth()/1.1), (line.getHeight()));
			break;
		case 3: case -3:
			line = HUD.stringToMario3FontImage("You can set certain songs in settings");
			break;
		case 4: case -4:
			line = HUD.stringToMario3FontImage("You can customize controls in settings");
			break;
		case 5: case -5:
			line = HUD.stringToMario3FontImage("Post your scores online in leaderboard");
			break;
		case 6: case -6:
			switch(Game.characterSkinPosition) {
			case 3:
				line = HUD.stringToMario3FontImage("Luigi can turn when time's stopped");
				break;
			case 4:
				line = HUD.stringToMario3FontImage("Mike can turn when time's stopped");
				break;
			case 5:
				line = HUD.stringToMario3FontImage("Bill can turn when time's stopped");
				break;
			default:
				line = HUD.stringToMario3FontImage("Mario can turn when time's stopped");
				break;
			}
			break;
		case 7: case -7:
			switch(Game.characterSkinPosition) {
			case 3:
				line = HUD.stringToMario3FontImage("Luigi slides when changing direction");
				break;
			case 4:
				line = HUD.stringToMario3FontImage("Mike slides when changing direction");
				break;
			case 5:
				line = HUD.stringToMario3FontImage("Bill slides when changing direction");
				break;
			default:
				line = HUD.stringToMario3FontImage("Mario slides when changing direction");
				break;
			}
			break;
		case 8: case -8:
			switch(Game.fireballPosition) {
			case 1:
				line = HUD.stringToMario3FontImage("Green shells carry your momentum");
				break;
			case 2:
				line = HUD.stringToMario3FontImage("Red shells track enemies");
				break;
			case 3:
				line = HUD.stringToMario3FontImage("Buzzy beetle shells can endure impacts");
				break;
			case 4:
				line = HUD.stringToMario3FontImage("Gloves go faster if you're Mike");
				break;
			case 5:
				line = HUD.stringToMario3FontImage("Contra shots are the smallest projectiles");
				line = Game.resize(line, (int)(line.getWidth()/1.1), (line.getHeight()));
				break;
			default:
				line = HUD.stringToMario3FontImage("Fireballs always shoot straight ahead");
				break;
			}
			break;
		case 9://chainchomp
			line = HUD.stringToMario3FontImage("Chain chomp can eat buzzy beetle shells");
			break;
		case 10://bulletbill
			line = HUD.stringToMario3FontImage("Bullet bill might withstand a collision");
			break;
		case 11://bombombs
			line = HUD.stringToMario3FontImage("Watch out for bombombs schrapnel");
			break;
		case 12://cheepcheeps
			line = HUD.stringToMario3FontImage("Cheep cheeps will help for 15 seconds");
			break;
		/******************UNLOCKS*************************/
		case 13://amp wiggler lakitu
			if(item4Unlocked)
				line = HUD.stringToMario3FontImage("Buzzy beetle shells will destroy amp");
			else if(item5Unlocked)
				line = HUD.stringToMario3FontImage("Wiggler appears below important enemies");
			else if(item6Unlocked)
				line = HUD.stringToMario3FontImage("Watch out for items lakitu drops");
			else
				line = HUD.stringToMario3FontImage("Created by Thomas Rader");
			break;
		case 14://wiggler lakitu
			if(item5Unlocked)
				line = HUD.stringToMario3FontImage("Wiggler appears below important enemies");
			else if(item6Unlocked)
				line = HUD.stringToMario3FontImage("Watch out for items lakitu drops");
			else
				line = HUD.stringToMario3FontImage("Created by Thomas Rader");
			break;
		case 15://lakitu
			if(item6Unlocked)
				line = HUD.stringToMario3FontImage("Watch out for items lakitu drops");
			else
				line = HUD.stringToMario3FontImage("Created by Thomas Rader");
			break;
		default:
			line = HUD.stringToMario3FontImage("Created by Thomas Rader");
			break;
		}
		line = Game.resize(line, (line.getWidth()/2), (line.getHeight()/2));
		helpLinesLast = line;
	}
	public static void checkIfAllUnlocked() {
		if(skin1Unlocked && skin2Unlocked && skin3Unlocked &&
			fireball1Unlocked && fireball2Unlocked && fireball3Unlocked && 
			track4Unlocked && track5Unlocked && track6Unlocked && 
			item4Unlocked && item5Unlocked && item6Unlocked) {
			allUnlockedScreen = true;
			menuTrack4Unlocked = true;
			menuTrack5Set = true;
			try {
				LeaderboardController.writeToSettings("menuTrack4Unlocked", "true");
				LeaderboardController.writeToSettings("menuTrack5Set", "true");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void resetMenuSound() {
		if(menuSoundLoopRandomizer >= 0 && menuSoundLoopRandomizer < 6) {
			menuSoundLoops.get(menuSoundLoopRandomizer).stop();
			menuSoundLoops.get(menuSoundLoopRandomizer).setFramePosition(0);
			menuSoundLoops.get(menuSoundLoopRandomizer).setSoundLoopBoolean(false);
		}
		Random rand = new Random();
		menuSoundLoopRandomizer = rand.nextInt(5);
		VolumeSlider.adjustVolume(menuSoundLoops, gameSoundLoops);
		menuSoundLoopRandomizer = VolumeSlider.menuTrackSetup(menuSoundLoopRandomizer);
		if(menuSoundLoopRandomizer == -2)
			menuSoundLoopRandomizer = 5;
		if(!Game.trackCurrentlyPlaying) {//MAYBE DELETE THIS IF STATEMENT
			if(menuSoundLoopRandomizer == 2) 
//				menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(864007, 1728014);//Seconds.millisec * 44100
				menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(432004, 864007);//Seconds.millisec * 22050
			else if(menuSoundLoopRandomizer == 3) 
//				menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(464196, 1949705);//Seconds.millisec * 44100
				menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(232098, 974853);//Seconds.millisec * 22050
			else if(menuSoundLoopRandomizer == 4)
//				menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(545561, 2291391);//Seconds.millisec * 44100
				menuSoundLoops.get(menuSoundLoopRandomizer).loopSegmentFromStart(272781, 1145696);//Seconds.millisec * 22050
			else
				menuSoundLoops.get(menuSoundLoopRandomizer).loop();
			menuSoundLoops.get(menuSoundLoopRandomizer).setSoundLoopBoolean(true);
		}
	}
	private void chompedStarFX(double x, double y, int width, int height) {
//		for(int i = 0; i < this.getController().getEntityF().size(); i ++){
//			EntityF tempEnt = this.getController().getEntityF().get(i);
//			if(this.getController().getEntityF().get(i).getY() > ((Game.HEIGHT * Game.SCALE) - MARIO_HEIGHT - 30) && 
//					this.getController().getEntityF().get(i).entityName().equals("chompFX")) {
//				this.getController().getEntityF().remove(tempEnt);
//			}
//		}
		ChompFX chomped;
		Random rand = new Random();
		int randInt = rand.nextInt(10);
		switch(randInt) {
		case 0:
			chomped = new ChompFX(this,x+(width/2),y+(height/2),"Fireball");
			break;
		case 1:
			chomped = new ChompFX(this,x+(width/2),y+(height/2),"Flower");
			break;
		case 2:
			chomped = new ChompFX(this,x+(width/2),y+(height/2),"Goomba");
			break;
		case 3:
			chomped = new ChompFX(this,x+(width/2),y+(height/2),"GreenShell");
			break;
		case 4:
			chomped = new ChompFX(this,x+(width/2),y+(height/2),"BuzzyBeetleShell");
			break;
		case 5:
			chomped = new ChompFX(this,x+(width/2),y+(height/2),"GloveFireball");
			break;
		case 6:
			chomped = new ChompFX(this,x+(width/2),y+(height/2),"Wiggler");
			break;
		case 7:
			chomped = new ChompFX(this,x+(width/2),y+(height/2),"Cloud");
			break;
		case 8:
			chomped = new ChompFX(this,x+(width/2),y+(height/2),"CheepCheeps");
			break;
		case 9:
			chomped = new ChompFX(this,x+(width/2),y+(height/2),"Lakitu");
			break;
		default:
			chomped = new ChompFX(this,x+(width/2),y+(height/2),"Flower");
			break;
		}
		c.addEntity(chomped);
	}
	public void fireballSplash(double x, double y) {
		if(Game.currentlySelectedFireball == 0) {
			this.getController().addEntity(new ChompFX(this,x+4,y+4,"Fireball"));
		}
		else if(Game.currentlySelectedFireball == 1) {
			this.getController().addEntity(new ChompFX(this,x+4,y+4,"GreenShell"));
		}
		else if(Game.currentlySelectedFireball == 2) {
			this.getController().addEntity(new ChompFX(this,x+4,y+4,"RedShell"));
		}
		else if(Game.currentlySelectedFireball == 3) {
			this.getController().addEntity(new ChompFX(this,x+4,y+4,"BuzzyBeetleShell"));
		}
		else if(Game.currentlySelectedFireball == 4) {
			this.getController().addEntity(new ChompFX(this,x+4,y+4,"GloveFireball"));
		}
		else if(Game.currentlySelectedFireball == 5) {
			this.getController().addEntity(new ChompFX(this,x+4,y+4,"ContraFireball"));
		}
	}
	public void pointUpdate() throws IOException {
		if(LeaderboardController.getFromSettings("Total Points: ").equals("")) {
			if(hud.getScore() < 999999) {
				LeaderboardController.writeToSettings("Total Points: ", String.valueOf(hud.getScore()));
				if(highScore < 999999)
					LeaderboardController.writeToSettings("High Score: ", String.valueOf(hud.getScore()));
			}
			else {
				LeaderboardController.writeToSettings("Total Points: ", "999999");
				if(highScore < hud.getScore())
					LeaderboardController.writeToSettings("High Score: ", String.valueOf(hud.getScore()));
			}
		}
		else {
			int i = Integer.valueOf(LeaderboardController.getFromSettings("Total Points: "));
			i += hud.getScore();
			if(i < 999999){}
			else
				i = 999999;
			if(highScore < hud.getScore())
				LeaderboardController.writeToSettings("High Score: ", String.valueOf(hud.getScore()));
			LeaderboardController.writeToSettings("Total Points: ", String.valueOf(i));
		}
		if(!LeaderboardController.getFromSettings("Total Points: ").equals("")) {
			totalPointsImage = HUD.stringToMario3FontImage(LeaderboardController.getFromSettings("Total Points: "));
			totalPointsImage = Game.resize(HUD.stringToMario3FontImage(LeaderboardController.getFromSettings("Total Points: ")), totalPointsImage.getWidth()/2, totalPointsImage.getHeight()/2);
		}
	}
	public static void soundPop() {
		if(smbPopSoundLoop.clipIsActive()) {
			smbPopSoundLoop.stop();
			smbPopSoundLoop.setFramePosition(0);
		}
		smbPopSoundLoop.play();
	}
	public static void soundFireballPop() {
		if(fireballPopSFX.clipIsActive()) {
			fireballPopSFX.stop();
			fireballPopSFX.setFramePosition(0);
		}
		fireballPopSFX.play();
	}
	public static void fireballSoundSet() {
		if(fireballSFX != null)
			fireballSFX.close();
		if(fireballPopSFX != null)
			fireballPopSFX.close();
		switch(currentlySelectedFireball) {
		case 0:
			fireballSFX = new SoundLoops("res/Sounds/SFX/smb3_fireball.wav");
			fireballPopSFX = new SoundLoops("res/Sounds/SFX/mariofireballsfx.wav");
			break;
		case 1: case 2: case 3:
			fireballSFX = new SoundLoops("res/Sounds/SFX/nsmbwiiMenuCancel.wav");
			fireballPopSFX = new SoundLoops("res/Sounds/SFX/ssbm_shell.wav");
			break;
		case 4:
			fireballSFX = new SoundLoops("res/Sounds/SFX/miketysonpunchoutglovesfx.wav");
			fireballPopSFX = new SoundLoops("res/Sounds/SFX/miketysonpunchoutglovepopsfx.wav");
			break;
		case 5:
			fireballSFX = new SoundLoops("res/Sounds/SFX/contrashootsfx.wav");
			fireballPopSFX = new SoundLoops("res/Sounds/SFX/contrafireballpopsfx.wav");
			break;
		default:
			fireballSFX = new SoundLoops("res/Sounds/SFX/smb3_fireball.wav");
			fireballPopSFX = new SoundLoops("res/Sounds/SFX/mariofireballsfx.wav");
			break;
		}
		VolumeSlider.adjustSFX(fireballSFX);
		VolumeSlider.adjustSFX(fireballPopSFX);
	}
	public static BufferedImage makeTransparent(BufferedImage img) {
		BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		for(int i = 0; i < result.getWidth();i++) {
	        for(int j = 0; j < result.getHeight(); j ++){
	        	Color color = new Color(img.getRGB(i, j));
	        	Color colornext = new Color(img.getRGB(i, j));
	        	Color colorprev = new Color(img.getRGB(i, j));
	        	if(i == result.getWidth() -1 )
	        		colornext = new Color(img.getRGB(i, j));
	        	else if(i == result.getWidth() -2)
	        		colornext = new Color(img.getRGB(i+1, j));
	        	else if(i == result.getWidth() -3)
	        		colornext = new Color(img.getRGB(i+2, j));
	        	else
	        		colornext = new Color(img.getRGB(i+3, j));
	        	if(i == 0)
	        		colorprev = new Color(img.getRGB(i, j));
	        	else if(i == 1)
	        		colorprev = new Color(img.getRGB(i-1, j));
	        	else if(i == 2)
	        		colorprev = new Color(img.getRGB(i-2, j));
	        	else
	        		colorprev = new Color(img.getRGB(i-3, j));
	        	if((color.getRed() > 20 || color.getGreen() > 20 || color.getBlue() > 20) ||
	        		(colornext.getRed() > 20 || colornext.getGreen() > 20 || colornext.getBlue() > 20) ||
	        		(colorprev.getRed() > 20 || colorprev.getGreen() > 20 || colorprev.getBlue() > 20))
	        		result.setRGB(i, j, color.getRGB());
	        	else
	        		result.setRGB(i, j, (new Color(0.0f, 0.0f, 0.0f, 0.0f)).getRGB());
	        }
		}
		return result;
	}
	public static Boolean isPixelTransparentinBufferedImage(BufferedImage img, int x, int y) {
		//System.out.println(img.getWidth()+"x is "+x);
		if(x == img.getWidth())
			x--;
		if(y == img.getHeight())
			y--;
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
	private byte[] makeIV() {
		SecureRandom random = new SecureRandom();
		byte iv[] = new byte[16];//generate random 16 byte IV AES is always 16bytes
        random.nextBytes(iv);
        return iv;
	}
	private String encryptMessageAESKey(String gay) {
		String encodedKey = Base64.getEncoder().encodeToString(EncryptionAES.secretKeyPublic.getEncoded());
		byte[] ivBytes = makeIV();
		byte[] hi = EncryptionAES.encryptCBC2Byte(gay, encodedKey, ivBytes);
		byte[] concatBytes = new byte[ivBytes.length+hi.length];
		System.arraycopy(ivBytes, 0, concatBytes, 0, ivBytes.length);
		System.arraycopy(hi, 0, concatBytes, ivBytes.length, hi.length);
		String message = Base64.getEncoder().encodeToString(concatBytes);
		return message;
	}
	private String decryptMessageAESKey(String gay) {
		String encodedKey = Base64.getEncoder().encodeToString(EncryptionAES.secretKeyPublic.getEncoded());
		String ivs = gay.substring(0,16);
		gay = gay.substring(16, gay.length());
		if(gay.length() % 8 != 0) {
			if(gay.length()+1 % 8 == 0)
				gay = gay.substring(0, gay.length()+1);
			else
				gay = gay.substring(0, gay.length()-1);
		}
		if(EncryptionAES.decryptCBC2(gay,encodedKey,ivs) != null) {
			return EncryptionAES.decryptCBC2(gay,encodedKey,ivs);
		}
		else
			return null;
	}
	public String sendMessage(String msg, BufferedReader in) throws IOException {
		String t="";
		String resp = "";
		String decoy = "";
		try {
	        out.println(msg);
//	        while((decoy = in.readLine()) != null) {
//	        	System.out.println(decoy);
//	        	resp=resp+"\n"+decoy;
//	        	if(decoy.contains("END:END:"))
//	        		break;
//	        	//decoy
//	        }
	        resp = in.readLine();
	        return resp;
		} catch (IOException ex){
			System.out.println("Can't send message, IOException: " + ex);
			return t;
		}
    }
	public String getIP() {
		java.net.URL url = null;
		String username = "user";
        String password = "gitpwd";
        String file = "";
        try {
            url = new java.net.URL("https://raw.githubusercontent.com/OhThomas/data/main/KoopasInvaders/serverIP");
            java.net.URLConnection uc;
            uc = url.openConnection();

            uc.setRequestProperty("X-Requested-With", "Curl");
            java.util.ArrayList<String> list = new java.util.ArrayList<String>();
            String userpass = username + ":" + password;
            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));//needs Base64 encoder, apache.commons.codec
            uc.setRequestProperty("Authorization", basicAuth);

            BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null)
            	file = file + line;//file = file + line + "\n";
            return file;

        } catch (IOException e) {
            //System.out.println("Wrong username and password");
            return "75.1.199.32"; 
        }
	}
	public void clientToServer(String s) throws UnknownHostException, IOException {
		//if(s.length() <= 60) {
		final Thread outThread = new Thread() {
			@Override
			public void run() {
			while(connectingToServer) {
			try {
				renderConnectingMessage();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			boolean b = false;
			boolean versionCheck = false;
			String line = "";
			//String ip = "192.168.1.235";
			String ip = getIP();
			int port = 8591;
			int i = 0;
			int j = 0;
    		Scanner input = new Scanner(s);
			try {
				//put run animation here for image progression in renderConnectingMessage()
				if(clientSocket == null)
				clientSocket = new Socket(ip , port);
				//clientSocket.setReuseAddress(true);
				if(out == null)
		        out = new PrintWriter(clientSocket.getOutputStream(), true);
				if(in == null)
		        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		        //String response =  sendMessage(s,in);
		        //Check version with server
				String gay = "";
				if(!serverVerified) {
					ServerVerify serverVerify = new ServerVerify();
					if(serverVerify.serverVerify() == -1)
						throw new IOException("Verification failed.");
				}
				else if((gay = in.readLine()) == null) {
					gay = "";
					//serverVerified = false;
        			//throw new IOException("Server is down.");
        		}
//				if((gay = in.readLine()) == null) {
//					serverVerified = false;
//        			throw new IOException("Server is down.");
//        		}
//				System.out.println("sending ki key ");
				out.println(encryptMessageAESKey(KIVersion));
				//String gay = "";
				if((gay = in.readLine()) == null) {
					serverVerified = false;
        			throw new IOException("Server is down.");
        		}
				if(gay.equals("END:END:") && ((gay = in.readLine()) == null)) {
					serverVerified = false;
        			throw new IOException("Server is down.");
        		}
//				System.out.println("s = "+s);
//				System.out.println("gay = "+gay+" size = "+gay.length());
				gay = decryptMessageAESKey(gay);
//				System.out.println("LENGTH = "+Base64.getMimeDecoder().decode(gay).length);
//				System.out.println("decrypted... "+decryptMessageAESKey(gay));
			 if(gay.contains(KIVersion)) {
//				 System.out.println("We are sending: "+s);
			        if(s.contains("UPLOAD:UPLOAD:")) {
	    		        if(s.contains("DELETE:DELETE:")) {
		    	        	saveName = "";
		    	        	LeaderboardController.writeToSettings("saveName", "");
	    		        }
			        	while (input.hasNextLine()) {
			        		if (i == 0) {input.nextLine();}
			        		else {
			        			line = input.nextLine();
			        			if(line.contains("UPLOAD:UPLOAD:")) {
			        				i++;
			        				continue;
			        			}
			        			line = line.replace("\n", "");
//			        			System.out.println("line = "+ line);
//			        			if(line.equals("") || line.equals("\n"))
//			        				break;
			    		        out.println(encryptMessageAESKey(line));//send input
			    		        line = in.readLine();//receive input
//			    		        System.out.println("received line = "+line);
			    		        if(input.hasNextLine()) {
			    		        	out.println(encryptMessageAESKey(KIVersion));
			    		        	line = in.readLine();
			    		        }
			        		}
			        		i++;
			        	}
//			        	if(State == STATE.LEADERBOARD) {
			        	//change leaderboard
			        	leaderboard.resetLeaderboard();
						out.println(encryptMessageAESKey(KIVersion));
						if((gay = in.readLine()) == null) {
		        			throw new IOException("Server is down.");
		        		}
		        		out.println(encryptMessageAESKey("SEND:SEND:"));
		        		while((line = in.readLine()) != null) {
		        			line = decryptMessageAESKey(line);
//				        	System.out.println(line);
		        			line = line.replace("\n", "");
		        			if(!line.equals("END:END:") && !Character.isDigit(line.charAt(line.length()-1)))
		        				line = line.substring(0,line.length()-1);
//				        	System.out.println(line);
				        	if(line.contains("END:END:") || line.contains(s))
				        		break;
				        	if(!line.contains("END:END:")) {
				        		leaderboard.setupText(line);
				        	}
				        	
		        		}
		        		leaderboard.sortText();
		        		leaderboard.addCommas();
		        		leaderboard.displayText();
//			        	}
						if(Game.smb3ItemSoundLoop.clipIsActive())
							Game.smb3ItemSoundLoop.stop();
						Game.smb3ItemSoundLoop.play();
			        	//
			        }
			        else if(s.contains("DELETE:DELETE:")) {
			        	while (input.hasNextLine()) {
			        		if (i == 0) {input.nextLine();}
			        		else {
			    		        out.println(input.nextLine());//send input
			    		        line = in.readLine();//receive input
			    		        if(input.hasNextLine()) {
									out.println(KIVersion);
									if((gay = in.readLine()) == null) {
					        			throw new IOException("Server is down.");
					        		}
			    		        }
			        		}
			        		i++;
			        	}
				        if(Game.State == STATE.SET_SCORE && LeaderboardController.globalList == true) {
				        	//change leaderboard
//					        	leaderboard.resetLeaderboard();
//					        	leaderboard.setupText(HUD.nameEntered+": "+HUD.score);
//				        		leaderboard.sortText();
//				        		leaderboard.displayText();
//					        	leaderboard.initialize();
				        	for(int i2 = 0; i2 <= leaderboard.getNames().size()-1; i2++) {
				        		//System.out.println(leaderboard.getNames().get(i2).toString());
				        		//System.out.println("saveName = "+saveName);
				        		if(saveName.equals(leaderboard.getNames().get(i2)+Long.toString(HUD.score))) {
				        			leaderboard.getNames().remove(i2);
				        			leaderboard.getScores().remove(i2);
				        			i2--;
				        		}
				        	}
				        	leaderboard.removeCommas();
				        	//leaderboard.getNames().add(HUD.nameEntered);
				        	//leaderboard.getScores().add(Long.toString(HUD.score));
				        	leaderboard.setupText(HUD.nameEntered+": "+HUD.score);
			        		leaderboard.sortText();
			        		leaderboard.addCommas();
			        		leaderboard.displayText();
				        }
			        	saveName = "";
			        	LeaderboardController.writeToSettings("saveName", "");
			        }
			        else {
//			        out.println(encryptMessageAESKey(s));
			        if(s.contains("DELETE:DELETE:")) {
	    	        	saveName = "";
	    	        	LeaderboardController.writeToSettings("saveName", "");
			        }
			        if(Game.State == STATE.LEADERBOARD) {
			        	out.println(encryptMessageAESKey(s));
				        while((line = in.readLine()) != null) {
				        	line = decryptMessageAESKey(line);
//				        	System.out.println(line);
		        			line = line.replace("\n", "");
		        			if(!line.equals("END:END:") && !Character.isDigit(line.charAt(line.length()-1)))
		        				line = line.substring(0,line.length()-1);
//				        	System.out.println(line);
				        	if(line.contains("END:END:") || line.contains(s)) {
//				        	System.out.println("Broke away with = "+line);
				        		break;
				        	}
				        	if(s.contains("SEND:SEND:") && !line.contains("END:END:")) {
				        		leaderboard.setupText(line);
				        	}
				        	//System.out.println(line);
				        }
			        }
			        else if(Game.State == STATE.SET_SCORE) {
			        	String fago = s+"\n";
			        	out.println(encryptMessageAESKey(fago));
//				        while((line = in.readLine()) != null) {
			        	line = in.readLine();
				        	line = decryptMessageAESKey(line);
		        			line = line.replace("\n", "");
		        			fago = fago.replace("\n", "");
//		        			System.out.println("line = "+ line);
//		    		        System.out.println("received line = "+line);
//		        			if(line.contains(fago))
//		        				break;
//		    		        out.println(encryptMessageAESKey(line));//send input
//			        		line = in.readLine();//receive input
//				        }
							out.println(encryptMessageAESKey(KIVersion));
							if((gay = in.readLine()) == null) {
			        			throw new IOException("Server is down.");
			        		}
			        		out.println(encryptMessageAESKey("SEND:SEND:"));
			        		while((line = in.readLine()) != null) {
			        			line = decryptMessageAESKey(line);
//					        	System.out.println(line);
			        			line = line.replace("\n", "");
			        			if(!line.equals("END:END:") && !Character.isDigit(line.charAt(line.length()-1)))
			        				line = line.substring(0,line.length()-1);
//					        	System.out.println(line);
					        	if(line.contains("END:END:") || line.contains(s))
					        		break;
					        	
			        		}
//							out.println(encryptMessageAESKey(KIVersion));
//							if((gay = in.readLine()) == null) {
//			        			throw new IOException("Server is down.");
//			        		}
//			        		out.println(encryptMessageAESKey("SEND:SEND:"));
//			        		while((line = in.readLine()) != null) {
//			        			line = decryptMessageAESKey(line);
//					        	System.out.println(line);
//			        			line = line.replace("\n", "");
//			        			if(!line.equals("END:END:") && !Character.isDigit(line.charAt(line.length()-1)))
//			        				line = line.substring(0,line.length()-1);
//					        	System.out.println(line);
//					        	if(line.contains("END:END:") || line.contains(s))
//					        		break;
//					        	
//			        		}
			        }
				        if(Game.State == STATE.SET_SCORE && LeaderboardController.globalList == true) {
				        	//change leaderboard
//					        	leaderboard.resetLeaderboard();
//					        	leaderboard.setupText(HUD.nameEntered+": "+HUD.score);
//				        		leaderboard.sortText();
//				        		leaderboard.displayText();
//					        	leaderboard.initialize();
				        	for(int i2 = 0; i2 <= leaderboard.getNames().size()-1; i2++) {
				        		if(saveName.equals(leaderboard.getNames().get(i2)+Long.toString(HUD.score))) {
				        			leaderboard.getNames().remove(i2);
				        			leaderboard.getScores().remove(i2);
				        			i2--;
				        		}
				        	}
				        	leaderboard.removeCommas();
//					        	leaderboard.getNames().add(HUD.nameEntered+": ");
//					        	leaderboard.getScores().add(Long.toString(HUD.score));
				        	leaderboard.setupText(HUD.nameEntered+": "+HUD.score);
			        		leaderboard.sortText();
			        		leaderboard.addCommas();
			        		leaderboard.displayText();
//					        	leaderboard.resetLeaderboard();
//				        		out.println("SEND:SEND:");
//				        		while((line = in.readLine()) != null) {
//						        	if(line.contains("END:END:"))
//						        		break;
//						        	if(!line.contains("END:END:")) {
//						        		leaderboard.setupText(line);
//						        	}
//						        	
//				        		}
//				        		leaderboard.listTrigger = true;
//				        		leaderboard.globalListStayOn = true;
				        }
				        else if(Game.State == STATE.LEADERBOARD) {
					        if(s.contains("SEND:SEND:")){
				        		leaderboard.sortText();
				        		leaderboard.addCommas();
				        		leaderboard.displayText();
					        }
							if(Game.smb3ItemSoundLoop.clipIsActive())
								Game.smb3ItemSoundLoop.stop();
							Game.smb3ItemSoundLoop.play();
						}
			        }
			        }
			        else {
		        	System.out.println("Koopa's Invaders Version Error");
					serverVerified = false;
					serverErrorMessage = true;
					updateToConnect = true;
					serverErrorMessageTimer = System.currentTimeMillis() + 1000;
					if(Game.State == STATE.LEADERBOARD) {
						if(LeaderboardController.uploadTrigger) {
							leaderboard.resetLeaderboard();
							try {
								leaderboard.setupText();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			        		leaderboard.sortText();
			        		leaderboard.addCommas();
			        		leaderboard.displayText();
							leaderboard.resetLeaderboardImage();
						}
							LeaderboardController.globalList = false;
							LeaderboardController.listTrigger = false;
							LeaderboardController.uploadTrigger = false;
						if(Game.smb3ItemSoundLoop.clipIsActive())
							Game.smb3ItemSoundLoop.stop();
						Game.smwErrorSoundLoop.play();
					}
					else if(Game.State == STATE.SET_SCORE) {
						if(Game.smb3OpenSoundLoop.clipIsActive())
							Game.smb3OpenSoundLoop.stop();
						Game.smwErrorSoundLoop.play();
					}
					connectingToServer = false;
//			        clientSocket.close();
//			        out.close();
//			        in.close();
					break;
		        }
//		        clientSocket.close();
//		        out.close();
//		        in.close();
		        if(!s.contains("SEND:SEND:") && !HUD.nameEntered.isEmpty() && scoreEntered == true && !serverErrorMessage) {
					saveName = HUD.nameEntered+": "+HUD.score;
		        	//System.out.println(saveName);
		        }
				connectingToServer = false;
				break;
			} catch(UnknownHostException ex) {
				System.out.println("UnknownHostException: "+ex);
				serverErrorMessage = true;
				serverErrorMessageTimer = System.currentTimeMillis() + 1000;
				serverVerified = false;
				if(Game.State == STATE.LEADERBOARD) {
					if(LeaderboardController.uploadTrigger) {
						leaderboard.resetLeaderboard();
						try {
							leaderboard.setupText();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        		leaderboard.sortText();
		        		leaderboard.addCommas();
		        		leaderboard.displayText();
						leaderboard.resetLeaderboardImage();
					}
						LeaderboardController.globalList = false;
						LeaderboardController.listTrigger = false;
						LeaderboardController.uploadTrigger = false;
					if(Game.smb3ItemSoundLoop.clipIsActive())
						Game.smb3ItemSoundLoop.stop();
					Game.smwErrorSoundLoop.play();
				}
				else if(Game.State == STATE.SET_SCORE) {
					if(Game.smb3OpenSoundLoop.clipIsActive())
						Game.smb3OpenSoundLoop.stop();
					Game.smwErrorSoundLoop.play();
				}
				if(clientSocket != null)
					try {
						clientSocket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				clientSocket = null;
				if(out != null)
					out.close();
				out = null;
				if(in != null)
					try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				in = null;
				if(lastDisconnectedTimer != 0)
					lastDisconnectedTimer = 0;
				connectingToServer = false;
				break;
				
			} catch(IOException ex) {
				if(Game.lastDisconnectedTimer != 0 && System.currentTimeMillis() < Game.lastDisconnectedTimer + TIME_OUT) {
					//System.out.println("HERE");
					try {
						clientToServer(s);
					} catch (UnknownHostException e) {
						System.out.println("UnknownHostException: "+ex);
						serverErrorMessage = true;
						serverErrorMessageTimer = System.currentTimeMillis() + 1000;
						serverVerified = false;
						if(Game.State == STATE.LEADERBOARD) {
							if(LeaderboardController.uploadTrigger) {
								leaderboard.resetLeaderboard();
								try {
									leaderboard.setupText();
								} catch (IOException zee) {
									// TODO Auto-generated catch block
									zee.printStackTrace();
								}
				        		leaderboard.sortText();
				        		leaderboard.addCommas();
				        		leaderboard.displayText();
								leaderboard.resetLeaderboardImage();
							}
								LeaderboardController.globalList = false;
								LeaderboardController.listTrigger = false;
								LeaderboardController.uploadTrigger = false;
							if(Game.smb3ItemSoundLoop.clipIsActive())
								Game.smb3ItemSoundLoop.stop();
							Game.smwErrorSoundLoop.play();
						}
						else if(Game.State == STATE.SET_SCORE) {
							if(Game.smb3OpenSoundLoop.clipIsActive())
								Game.smb3OpenSoundLoop.stop();
							Game.smwErrorSoundLoop.play();
						}
						if(clientSocket != null)
							try {
								clientSocket.close();
							} catch (IOException f) {
								// TODO Auto-generated catch block
								f.printStackTrace();
							}
						clientSocket = null;
						if(out != null)
							out.close();
						out = null;
						if(in != null)
							try {
								in.close();
							} catch (IOException ee) {
								// TODO Auto-generated catch block
								ee.printStackTrace();
							}
						in = null;
						if(lastDisconnectedTimer != 0)
							lastDisconnectedTimer = 0;
						connectingToServer = false;
						break;
					} catch (IOException e) {
						System.out.println("Can't connect to host, IOException: " + ex);
						serverErrorMessage = true;
						serverErrorMessageTimer = System.currentTimeMillis() + 1000;
						serverVerified = false;
						if(Game.State == STATE.LEADERBOARD) {
							if(LeaderboardController.uploadTrigger) {
								leaderboard.resetLeaderboard();
								try {
									leaderboard.setupText();
								} catch (IOException eee) {
									// TODO Auto-generated catch block
									eee.printStackTrace();
								}
				        		leaderboard.sortText();
				        		leaderboard.addCommas();
				        		leaderboard.displayText();
								leaderboard.resetLeaderboardImage();
							}
							LeaderboardController.globalList = false;
							LeaderboardController.listTrigger = false;
							LeaderboardController.uploadTrigger = false;
							if(Game.smb3ItemSoundLoop.clipIsActive())
								Game.smb3ItemSoundLoop.stop();
							Game.smwErrorSoundLoop.play();
						}
						else if(Game.State == STATE.SET_SCORE) {
							if(Game.smb3OpenSoundLoop.clipIsActive())
								Game.smb3OpenSoundLoop.stop();
							Game.smwErrorSoundLoop.play();
						}
						if(clientSocket != null)
							try {
								clientSocket.close();
							} catch (IOException ioo) {
								// TODO Auto-generated catch block
								ioo.printStackTrace();
							}
						clientSocket = null;
						if(out != null)
							out.close();
						out = null;
						if(in != null)
							try {
								in.close();
							} catch (IOException iooo) {
								// TODO Auto-generated catch block
								iooo.printStackTrace();
							}
						in = null;
						if(lastDisconnectedTimer != 0)
							lastDisconnectedTimer = 0;
						connectingToServer = false;
						break;
					}
					return;
				}
				System.out.println("Can't connect to host, IOException: " + ex);
				serverErrorMessage = true;
				serverErrorMessageTimer = System.currentTimeMillis() + 1000;
				serverVerified = false;
				if(Game.State == STATE.LEADERBOARD) {
//					if(LeaderboardController.uploadTrigger) {
						leaderboard.resetLeaderboard();
						try {
							leaderboard.setupText();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        		leaderboard.sortText();
		        		leaderboard.addCommas();
		        		leaderboard.displayText();
						leaderboard.resetLeaderboardImage();
//					}
					LeaderboardController.globalList = false;
					LeaderboardController.listTrigger = false;
					LeaderboardController.uploadTrigger = false;
					if(Game.smb3ItemSoundLoop.clipIsActive())
						Game.smb3ItemSoundLoop.stop();
					Game.smwErrorSoundLoop.play();
				}
				else if(Game.State == STATE.SET_SCORE) {
					if(Game.smb3OpenSoundLoop.clipIsActive())
						Game.smb3OpenSoundLoop.stop();
					Game.smwErrorSoundLoop.play();
				}
				if(clientSocket != null)
					try {
						clientSocket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				clientSocket = null;
				if(out != null)
					out.close();
				out = null;
				if(in != null)
					try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				in = null;
				if(lastDisconnectedTimer != 0)
					lastDisconnectedTimer = 0;
				connectingToServer = false;
				break;
			}
			}
			};
		};
		outThread.start();
	        //assertEquals("hello client", response);
		//}
	}
	public static void closeGame() {
	    	if(clientSocket != null) {
				try {
					clientSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Game.lastDisconnectedTimer = System.currentTimeMillis();
	    	}
	    	else if(Game.lastDisconnectedTimer != 0)
	    		Game.lastDisconnectedTimer = 0;
	    	if(out != null)
	    		out.close();
	    	if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	try {
				if(LeaderboardController.checkForDataCorruption("settings.properties") == true && LeaderboardController.checkForDataCorruption("./src/settingsBackup.properties") == true) {
//				System.out.println("DATA CORRUPTION IN SETTINGS & BACKUP");
				}
				else if(LeaderboardController.checkForDataCorruption("settings.properties") == true) {
//				System.out.println("DATA CORRUPTION IN SETTINGS");
					LeaderboardController.loadSettingsIntoSettings("./src/settingsBackup.properties","settings.properties");
					LeaderboardController.loadSettingsIntoSettings("settings.properties","./src/settingsBackup.properties");
				}
				else {
//				System.out.println("NO DATA CORRUPTION");
					LeaderboardController.loadSettingsIntoSettings("settings.properties","./src/settingsBackup.properties");
				}
				LeaderboardController.gameUnlocksToSettings();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	public static void main(String args[])
		throws Exception{
		String gameAudioFile = "res/Sounds/Music/mario1remix.wav";													//Loading in Music
		String gameAudioFile2 = "res/Sounds/Music/marioremixbitch1c.wav";
		String gameAudioFile3 = "res/Sounds/Music/icedlandmarioremixxx2bbb.wav";
		String gameAudioFile4 = "res/Sounds/Music/nokibay2gggextranote.wav";
		String gameAudioFile5 = "res/Sounds/Music/snooopy2ii.wav";
		String gameAudioFile6 = "res/Sounds/Music/beatboREmixxx1aDIFFTURNEDINTOMARIOREMIX1e.wav";
		String gameAudioFile7 = "res/Sounds/Music/anothermariomixxx5dSLOW4d.wav";
		String gameAudioFile8 = "res/Sounds/Music/bowser1bbnolaughextended2.wav";//bowsertrack
		String gameAudioFile9 = "res/Sounds/Music/10secondsofsilence.wav";//silence
		String menuAudioFile = "res/Sounds/Music/supermarioworldremix1.wav";
		String menuAudioFile2 = "res/Sounds/Music/menuloop2.wav";
		String menuAudioFile3 = "res/Sounds/Music/mariosaunas1cexported.wav";
		String menuAudioFile4 = "res/Sounds/Music/hmmm2delfino2DIFFOG2b.wav";
		String menuAudioFile5 = "res/Sounds/Music/hmmm2delfino2DIFFdddddddnoooohigher2e.wav";
		String menuAudioFile6 = "res/Sounds/Music/10secondsofsilencee.wav";//silence
		String gameOverAudioFile = "res/Sounds/SFX/smw_game_over.wav";
		String gameOverWinningAudioFile = "res/Sounds/SFX/smb_stage_clear.wav";
		String gameOverWinningMikeTysonAudioFile = "res/Sounds/SFX/MarioVoice/miketysonsfxwin.wav";
		String gameOverWinningContraAudioFile = "res/Sounds/SFX/MarioVoice/contrasfxwin.wav";
		String gameOverIrisAudioFile = "res/Sounds/SFX/smw_goal_iris-out.wav";
		String creditsSongAudioFile = "res/Sounds/Music/hammertimefileselectremix2guitar4cc.wav";
		String marioStarAudioFile = "res/Sounds/SFX/mariowhistle.wav";
		String soundFXClip1 = "res/Sounds/SFX/smb_pipe.wav";//"res/Sounds/SFX/riseupacoustic1cWAVE.wav";
		String soundFXClip2 = "res/Sounds/SFX/MariopowerupSFX.wav";
		String fireballFXFile = "res/Sounds/SFX/smb3_fireball.wav";
		String fireballPopFXFile = "res/Sounds/SFX/mariofireballsfx.wav";
		String pauseSoundFXFile = "res/Sounds/SFX/smb_pause.wav";
		String marioSpinningFile = "res/Sounds/SFX/smw_feather_get.wav";
		String marioDeathFile = "res/Sounds/SFX/smb3_player_down.wav";
		String itemSwooshFile = "res/Sounds/SFX/spacewhooshsfxMARIO.wav";
		String itemPauseFile = "res/Sounds/SFX/spacewhooseshortened.wav";
		String smb3CoinFile = "res/Sounds/SFX/smb3_coin.wav";
		String smb3BumpFile = "res/Sounds/SFX/smb3_bump.wav";
		String smb3Bump2File = "res/Sounds/SFX/smb3_bump2.wav";
		String smb3KickFile = "res/Sounds/SFX/smb3_kick.wav";
		String smb3TailFile = "res/Sounds/SFX/smb3_tail.wav";
		String smb3OpenFile = "res/Sounds/SFX/smb3_open.wav";
		String smb31PupFile = "res/Sounds/SFX/smb3_1pup.wav";
		String smb3ItemFile = "res/Sounds/SFX/smb3_item.wav";
		String smb3BeepFile = "res/Sounds/SFX/smb3_beep.wav";
		String smbCoinPointsFile = "res/Sounds/SFX/smb_coin.wav";
		String smbPopFile = "res/Sounds/SFX/mariopopsfx.wav";
		String smbFireworkFile = "res/Sounds/SFX/smb_fireworks.wav";
		String smb3CheckmarkFile = "res/Sounds/SFX/checkmarksound1.wav";
		String smb3Checkmark2File = "res/Sounds/SFX/checkmarksound2.wav";
		String smb3TextFile = "res/Sounds/SFX/smb3_text.wav";
		String smwErrorFile = "res/Sounds/SFX/smw_lemmy_wendy_incorrect.wav";
		String smwCheatFile = "res/Sounds/SFX/CheatSFX/smw2_flower_get_short.wav";
		String smwCheat2File = "res/Sounds/SFX/CheatSFX/smw2_flower_get_short2.wav";
		String smwCheat3File = "res/Sounds/SFX/CheatSFX/smw2_flower_get_short3.wav";
		String smwCheat4File = "res/Sounds/SFX/CheatSFX/smw2_flower_get_short4.wav";
		String smwCheat5File = "res/Sounds/SFX/CheatSFX/smw2_flower_get_short5.wav";
		String smwCheat6File = "res/Sounds/SFX/CheatSFX/smw2_flower_get_short6.wav";
		String smwCheat7File = "res/Sounds/SFX/CheatSFX/smw2_flower_get_short7.wav";
		String smwCheat8File = "res/Sounds/SFX/CheatSFX/smw2_flower_get_short8.wav";
		String smwCheat9File = "res/Sounds/SFX/CheatSFX/smw2_flower_get_short9.wav";
		String smwCheat10File = "res/Sounds/SFX/CheatSFX/smw2_flower_get_short10.wav";
		String smwCheatFullFile = "res/Sounds/SFX/CheatSFX/smw2_flower_get_shortfull.wav";
		String marioVoiceLetsGoFile = "res/Sounds/SFX/MarioVoice/mk64_mario02.wav";
		String marioVoiceHereWeGoFile = "res/Sounds/SFX/MarioVoice/ssbm_dr_mario_33_mario_27.wav";
		String marioVoiceYelpFile = "res/Sounds/SFX/MarioVoice/ssbm_dr_mario_22_mario_16.wav";
		String marioVoiceWoohooFile = "res/Sounds/SFX/MarioVoice/ssbm_dr_mario_26_mario_20.wav";
		String luigiVoiceLetsGoFile = "res/Sounds/SFX/LuigiVoice/ssbm_luigi_25_31.wav";
		String luigiVoiceYipeeFile = "res/Sounds/SFX/LuigiVoice/ssbm_luigi_15.wav";
		String luigiVoiceYahFile = "res/Sounds/SFX/LuigiVoice/ssbm_luigi_16.wav";
		String luigiVoiceAhhaFile = "res/Sounds/SFX/LuigiVoice/ssbm_luigi_18.wav";
		String luigiVoiceWoohooFile = "res/Sounds/SFX/LuigiVoice/ssbm_luigi_24.wav";
		String mikeTysonVoice1File = "res/Sounds/SFX/MarioVoice/miketysonsfx1.wav";
		String mikeTysonVoice2File = "res/Sounds/SFX/MarioVoice/miketysonsfx2.wav";
		String contraVoice1File = "res/Sounds/SFX/MarioVoice/contrasfx1.wav";
		String contraVoice2File = "res/Sounds/SFX/MarioVoice/contrasfx2.wav";
		String contraVoice3File = "res/Sounds/SFX/MarioVoice/contrasfx3.wav";
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
		SoundLoops menuSoundLoop3 = new SoundLoops(menuAudioFile3);
		SoundLoops menuSoundLoop4 = new SoundLoops(menuAudioFile4);
		SoundLoops menuSoundLoop5 = new SoundLoops(menuAudioFile5);
		SoundLoops menuSoundLoop6 = new SoundLoops(menuAudioFile6);
		SoundLoops gameSoundLoop = new SoundLoops(gameAudioFile);
		SoundLoops gameSoundLoop2 = new SoundLoops(gameAudioFile2);
		SoundLoops gameSoundLoop3 = new SoundLoops(gameAudioFile3);
		SoundLoops gameSoundLoop4 = new SoundLoops(gameAudioFile4);
		SoundLoops gameSoundLoop5 = new SoundLoops(gameAudioFile5);
		SoundLoops gameSoundLoop6 = new SoundLoops(gameAudioFile6);
		SoundLoops gameSoundLoop7 = new SoundLoops(gameAudioFile7);
		SoundLoops gameSoundLoop8 = new SoundLoops(gameAudioFile8);
		SoundLoops gameSoundLoop9 = new SoundLoops(gameAudioFile9);
		SoundLoops gameOverSoundLoop = new SoundLoops(gameOverAudioFile);
		SoundLoops gameOverWinningSoundLoop = new SoundLoops(gameOverWinningAudioFile);
		SoundLoops gameOverWinningMikeTysonSoundLoop = new SoundLoops(gameOverWinningMikeTysonAudioFile);
		SoundLoops gameOverWinningContraSoundLoop = new SoundLoops(gameOverWinningContraAudioFile);
		SoundLoops gameOverIrisSoundLoop = new SoundLoops(gameOverIrisAudioFile);
		SoundLoops creditsSongSoundLoop = new SoundLoops(creditsSongAudioFile);
		SoundLoops marioStarSoundLoop = new SoundLoops(marioStarAudioFile);
		SoundLoops soundFXClip1SoundLoop = new SoundLoops(soundFXClip1);
		SoundLoops soundFXClip2SoundLoop = new SoundLoops(soundFXClip2);
		SoundLoops fireballFXSoundLoop = new SoundLoops(fireballFXFile);
		SoundLoops fireballPopFXSoundLoop = new SoundLoops(fireballPopFXFile);
		SoundLoops pauseSoundFXSoundLoop = new SoundLoops(pauseSoundFXFile);
		SoundLoops marioSpinningSoundLoop = new SoundLoops(marioSpinningFile);
		SoundLoops marioDeathSoundLoop = new SoundLoops(marioDeathFile);
		SoundLoops itemSwooshSoundLoop = new SoundLoops(itemSwooshFile);
		SoundLoops itemPauseSoundLoop = new SoundLoops(itemPauseFile);
		SoundLoops smb3CoinSoundLoop = new SoundLoops(smb3CoinFile);
		SoundLoops smb3BumpSoundLoop = new SoundLoops(smb3BumpFile);
		SoundLoops smb3Bump2SoundLoop = new SoundLoops(smb3Bump2File);
		SoundLoops smb3KickSoundLoop = new SoundLoops(smb3KickFile);
		SoundLoops smb3TailSoundLoop = new SoundLoops(smb3TailFile);
		SoundLoops smb3OpenSoundLoop = new SoundLoops(smb3OpenFile);
		SoundLoops smb31PupSoundLoop = new SoundLoops(smb31PupFile);
		SoundLoops smb3ItemSoundLoop = new SoundLoops(smb3ItemFile);
		SoundLoops smb3BeepSoundLoop = new SoundLoops(smb3BeepFile);
		SoundLoops smbCoinPointsSoundLoop = new SoundLoops(smbCoinPointsFile);
		SoundLoops smbPopSoundLoop = new SoundLoops(smbPopFile);
		SoundLoops smbFireworkSoundLoop = new SoundLoops(smbFireworkFile);
		SoundLoops smb3CheckmarkSoundLoop = new SoundLoops(smb3CheckmarkFile);
		SoundLoops smb3Checkmark2SoundLoop = new SoundLoops(smb3Checkmark2File);
		SoundLoops smb3TextSoundLoop = new SoundLoops(smb3TextFile);
		SoundLoops smwErrorSoundLoop = new SoundLoops(smwErrorFile);
		SoundLoops smwCheatSoundLoop = new SoundLoops(smwCheatFile);
		SoundLoops smwCheat2SoundLoop = new SoundLoops(smwCheat2File);
		SoundLoops smwCheat3SoundLoop = new SoundLoops(smwCheat3File);
		SoundLoops smwCheat4SoundLoop = new SoundLoops(smwCheat4File);
		SoundLoops smwCheat5SoundLoop = new SoundLoops(smwCheat5File);
		SoundLoops smwCheat6SoundLoop = new SoundLoops(smwCheat6File);
		SoundLoops smwCheat7SoundLoop = new SoundLoops(smwCheat7File);
		SoundLoops smwCheat8SoundLoop = new SoundLoops(smwCheat8File);
		SoundLoops smwCheat9SoundLoop = new SoundLoops(smwCheat9File);
		SoundLoops smwCheat10SoundLoop = new SoundLoops(smwCheat10File);
		SoundLoops smwCheatFullSoundLoop = new SoundLoops(smwCheatFullFile);
		SoundLoops marioVoiceLetsGoSoundLoop = new SoundLoops(marioVoiceLetsGoFile);
		SoundLoops marioVoiceHereWeGoSoundLoop = new SoundLoops(marioVoiceHereWeGoFile);
		SoundLoops marioVoiceYelpSoundLoop = new SoundLoops(marioVoiceYelpFile);
		SoundLoops marioVoiceWoohooSoundLoop = new SoundLoops(marioVoiceWoohooFile);
		SoundLoops luigiVoiceLetsGoSoundLoop = new SoundLoops(luigiVoiceLetsGoFile);
		SoundLoops luigiVoiceYipeeSoundLoop = new SoundLoops(luigiVoiceYipeeFile);
		SoundLoops luigiVoiceYahSoundLoop = new SoundLoops(luigiVoiceYahFile);
		SoundLoops luigiVoiceAhhaSoundLoop = new SoundLoops(luigiVoiceAhhaFile);
		SoundLoops luigiVoiceWoohooSoundLoop = new SoundLoops(luigiVoiceWoohooFile);
		SoundLoops mikeTysonVoice1SoundLoop = new SoundLoops(mikeTysonVoice1File);
		SoundLoops mikeTysonVoice2SoundLoop = new SoundLoops(mikeTysonVoice2File);
		SoundLoops contraVoice1SoundLoop = new SoundLoops(contraVoice1File);
		SoundLoops contraVoice2SoundLoop = new SoundLoops(contraVoice2File);
		SoundLoops contraVoice3SoundLoop = new SoundLoops(contraVoice3File);
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
		game.menuSoundLoops.add(menuSoundLoop3);
		game.menuSoundLoops.add(menuSoundLoop4);
		game.menuSoundLoops.add(menuSoundLoop5);
		game.menuSoundLoops.add(menuSoundLoop6);
		game.gameSoundLoops.add(gameSoundLoop);
		game.gameSoundLoops.add(gameSoundLoop2);
		game.gameSoundLoops.add(gameSoundLoop3);
		game.gameSoundLoops.add(gameSoundLoop4);
		game.gameSoundLoops.add(gameSoundLoop5);
		game.gameSoundLoops.add(gameSoundLoop6);
		game.gameSoundLoops.add(gameSoundLoop7);
		game.gameSoundLoops.add(gameSoundLoop8);
		game.gameSoundLoops.add(gameSoundLoop9);
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
		game.marioVoices.add(mikeTysonVoice1SoundLoop);
		game.marioVoices.add(mikeTysonVoice2SoundLoop);
		game.marioVoices.add(contraVoice1SoundLoop);
		game.marioVoices.add(contraVoice2SoundLoop);
		game.marioVoices.add(contraVoice3SoundLoop);
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
		game.fireballPopSFX = fireballPopFXSoundLoop;
		game.pauseSoundFXSoundLoop = pauseSoundFXSoundLoop;
		game.marioSpinningSoundLoop = marioSpinningSoundLoop;
		game.marioDeathSoundLoop = marioDeathSoundLoop;
		game.itemSwooshSoundLoop = itemSwooshSoundLoop;
		game.itemPauseSoundLoop = itemPauseSoundLoop;
		game.smb3CoinSoundLoop = smb3CoinSoundLoop;
		game.smb3BumpSoundLoop = smb3BumpSoundLoop;
		game.smb3Bump2SoundLoop = smb3Bump2SoundLoop;
		game.smb3KickSoundLoop = smb3KickSoundLoop;
		game.smb3TailSoundLoop = smb3TailSoundLoop;
		game.smb3OpenSoundLoop = smb3OpenSoundLoop;
		game.smb31PupSoundLoop = smb31PupSoundLoop;
		game.smb3ItemSoundLoop = smb3ItemSoundLoop;
		game.smb3BeepSoundLoop = smb3BeepSoundLoop;
		game.smbCoinPointsSoundLoop = smbCoinPointsSoundLoop;
		game.smbCoinPoints2SoundLoop = smbCoinPointsSoundLoop;
		game.smbPopSoundLoop = smbPopSoundLoop;
		game.smbFireworkSoundLoop = smbFireworkSoundLoop;
		game.smb3CheckmarkSoundLoop = smb3CheckmarkSoundLoop;
		game.smb3Checkmark2SoundLoop = smb3Checkmark2SoundLoop;
		game.smb3TextSoundLoop = smb3TextSoundLoop;
		game.smwErrorSoundLoop = smwErrorSoundLoop;
		game.smwCheatSoundLoop = smwCheatSoundLoop;
		game.smwCheat2SoundLoop = smwCheat2SoundLoop;
		game.smwCheat3SoundLoop = smwCheat3SoundLoop;
		game.smwCheat4SoundLoop = smwCheat4SoundLoop;
		game.smwCheat5SoundLoop = smwCheat5SoundLoop;
		game.smwCheat6SoundLoop = smwCheat6SoundLoop;
		game.smwCheat7SoundLoop = smwCheat7SoundLoop;
		game.smwCheat8SoundLoop = smwCheat8SoundLoop;
		game.smwCheat9SoundLoop = smwCheat9SoundLoop;
		game.smwCheat10SoundLoop = smwCheat10SoundLoop;
		game.smwCheatFullSoundLoop = smwCheatFullSoundLoop;
		game.gameOverSoundLoop = gameOverSoundLoop;
		game.gameOverWinningSoundLoop = gameOverWinningSoundLoop;
		game.gameOverWinningMikeTysonSoundLoop = gameOverWinningMikeTysonSoundLoop;
		game.gameOverWinningContraSoundLoop = gameOverWinningContraSoundLoop;
		game.gameOverIrisSoundLoop = gameOverIrisSoundLoop;
		game.creditsSong = creditsSongSoundLoop;
//		System.setProperty("java.library.path", System.getProperty("java.library.path") + File.pathSeparator + "./src");
		String path = new File(".").getCanonicalPath();
		System.setProperty("net.java.games.input.librarypath", path+"/src/");
		XInputDevice[] devices = XInputDevice.getAllDevices();
		XInputDevice device = XInputDevice.getDeviceFor(0);
		//XInputDevice14[] devices14 = XInputDevice14.getAllDevices();
		//XInputDevice14 device14 = XInputDevice14.getDeviceFor(0);
		game.device = device;
		//game.device14 = device14;
		//LeaderboardController.settingsBackup();
//		System.out.println("LIB ="+System.getProperty("java.library.path"));
		//if([function checking if settings has any null values, returns true if so] == true){
		//load backup settings to settings}
		//else{
		//store settings to backup}
		JFrame frame = new JFrame(game.TITLE);
		ImageIcon img = new ImageIcon("res/mario_jump.png");
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()
		//DO THIS WITH BOTH EXIT BUTTONS!!
		{
		    @Override
		    public void windowClosing(WindowEvent we)
		    {
		    	game.closeGame();
		        //Code goes here
		    }
		});
		//DO THIS WITH BOTH EXIT BUTTONS!!
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setIconImage(img.getImage());
		game.start();
		XInputDevice.game = game;
		ReadAllEvents.game = game;
		final Thread outThread = new Thread() {
			@Override
			public void run() {
		try {
//		ReadAllEvents j = new ReadAllEvents();
		directInputEvents = new ReadAllEvents();
		}catch(Exception e) {
			System.out.println(e.toString());
			throw e;
		}
			};
		};
		outThread.start();
//		EncryptionRSA.keyGenerator();
//		EncryptionRSA rsa = new EncryptionRSA();
//		System.out.println(rsa.encrypt("gay", rsa.getPublicKey()));
//		System.out.println(EncryptionRSA.decrypt(EncryptionRSA.encrypt("gay", rsa.getPublicKey()),rsa.getPrivateKey()));
		
//		System.out.println(EncryptionAES.encryptLocal("haha"));
//		System.out.println(EncryptionAES.decryptLocal(EncryptionAES.encryptLocal("haha")));
//		Runnable controllerDIUpdate = new Runnable() {
//			@Override
//		    public void run() {
//				if(!gameControllerInUseDI) {
////				    DirectAndRawInputEnvironmentPlugin directEnv = new DirectAndRawInputEnvironmentPlugin();
////					if(directEnv != null & directEnv.isSupported())
////						ControllerEnvironment.setDefaultEnvironment(directEnv);
//					ReadAllEvents.controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
//					ReadAllEvents.resettingControllerEnvironment = true;
////					//THIS KILLS THE THREAD
////					final Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
////					int i = 0;
////					Thread threadtoDelete = null;
////					for (final Thread thread : threadSet) {
////					  final String name = thread.getClass().getName();
////					  System.out.println(name);
////					  if (name.equals("net.java.games.input.RawInputEventQueue$QueueThread") && 0 < i && threadtoDelete != null) {
////						  threadtoDelete.interrupt();
////						    try {
////						    	threadtoDelete.join();
////						    } catch (final InterruptedException e) {
////						    	threadtoDelete.interrupt();
////						    }
////						    i = 0;
////						  }
////					  if (name.equals("net.java.games.input.RawInputEventQueue$QueueThread")) {
////						  threadtoDelete = thread;
////						  i++;
////					  }
////					}
//				}
//		    }
//		};
//		
//
//		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//		executor.scheduleAtFixedRate(controllerDIUpdate, 0, 10, TimeUnit.SECONDS);
	}
	
	public Textures getTextures() {
		return tex;
	}
	
	public Player getPlayer() {
		return p;
	}
	
	public ControlsController getControlsController() {
		return controlsController;
	}
	
	public CreditsController getCreditsController() {
		return creditsController;
	}
	
	public LeaderboardController getLeaderboard() {
		return leaderboard;
	}
	
	public XInputDevice getDevice() {
		return device;
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
	
	public int getMenuSoundRandomizer() {
		return menuSoundLoopRandomizer;
	}
	
	public int getAnimationTimer1() {
		return animationTimer1;
	}
	
	public int getNumberOfFireBallsShot() {
		return numberOfFireBallsShot;
	}

	public void setNumberOfFireBallsShot(int numberOfFireBallsShot) {
		this.numberOfFireBallsShot = numberOfFireBallsShot;
	}

	public int getNumberOfFireBallsShotDecoy() {
		return numberOfFireBallsShotDecoy;
	}

	public void setNumberOfFireBallsShotDecoy(int numberOfFireBallsShotDecoy) {
		this.numberOfFireBallsShotDecoy = numberOfFireBallsShotDecoy;
	}

	public void setAnimationTimer1(int animationTimer1) {
		this.animationTimer1 = animationTimer1;
	}
	
	public double getSlowingDown() {
		return slowingDown;
	}

	public void setSlowingDown(double slowingDown) {
		this.slowingDown = slowingDown;
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
	
	public boolean getSceneAcknowledgement() {
		return sceneAcknowledgement;
	}

	public void setSceneAcknowledgement(boolean sceneAcknowledgement) {
		this.sceneAcknowledgement = sceneAcknowledgement;
	}

	public boolean getKeepRunningAfterPauseL() {
		return keepRunningAfterPauseL;
	}

	public void setKeepRunningAfterPauseL(boolean keepRunningAfterPauseL) {
		this.keepRunningAfterPauseL = keepRunningAfterPauseL;
	}

	public boolean getKeepRunningAfterPauseR() {
		return keepRunningAfterPauseR;
	}

	public void setKeepRunningAfterPauseR(boolean keepRunningAfterPauseR) {
		this.keepRunningAfterPauseR = keepRunningAfterPauseR;
	}
	
	public boolean getDontRunAfterPause() {
		return dontRunAfterPause;
	}

	public void setDontRunAfterPause(boolean dontRunAfterPause) {
		this.dontRunAfterPause = dontRunAfterPause;
	}
	
	public boolean getSlowingDownFromPause() {
		return slowingDownFromPause;
	}

	public void setSlowingDownFromPause(boolean slowingDownFromPause) {
		this.slowingDownFromPause = slowingDownFromPause;
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

	public boolean getIsShooting() {
		return isShooting;
	}

	public void setIsShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

	public boolean getSpawnItem() {
		return spawnItem;
	}

	public void setSpawnItem(boolean spawnItem) {
		this.spawnItem = spawnItem;
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
	
	public long getWaitToPause() {
		return waitToPause;
	}

	public void setWaitToPause(long waitToPause) {
		this.waitToPause = waitToPause;
	}

	public long getSlowingDownTimerLong() {
		return slowingDownTimerLong;
	}

	public void setSlowingDownTimerLong(long slowingDownTimerLong) {
		this.slowingDownTimerLong = slowingDownTimerLong;
	}
	
	public long getRunningTimerLong() {
		return runningTimerLong;
	}

	public void setRunningTimerLong(long runningTimerLong) {
		this.runningTimerLong = runningTimerLong;
	}

	public long getJoystickTimer() {
		return joystickTimer;
	}

	public void setJoystickTimer(long joystickTimer) {
		this.joystickTimer = joystickTimer;
	}

	public long getSlowDownForAnalogTimer() {
		return slowDownForAnalogTimer;
	}

	public void setSlowDownForAnalogTimer(long slowDownForAnalogTimer) {
		this.slowDownForAnalogTimer = slowDownForAnalogTimer;
	}

	public long getCheatTimer() {
		return cheatTimer;
	}

	public void setCheatTimer(long cheatTimer) {
		this.cheatTimer = cheatTimer;
	}
	
	public String getCheatString() {
		return cheatString;
	}
	
	public void setCheatString(String cheatString) {
		this.cheatString = cheatString;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public boolean getPauseHoldOff() {
		return pauseHoldOff;
	}

	public void setPauseHoldOff(boolean pauseHoldOff) {
		this.pauseHoldOff = pauseHoldOff;
	}
	
	public boolean getxLBoolean() {
		return xLBoolean;
	}

	public void setxLBoolean(boolean xLBoolean) {
		this.xLBoolean = xLBoolean;
	}

	public boolean getxRBoolean() {
		return xRBoolean;
	}

	public void setxRBoolean(boolean xRBoolean) {
		this.xRBoolean = xRBoolean;
	}
	
	public boolean getSlowingDownActivatedl() {
		return slowingDownActivatedl;
	}

	public void setSlowingDownActivatedl(boolean slowingDownActivatedl) {
		this.slowingDownActivatedl = slowingDownActivatedl;
	}

	public boolean getSlowingDownActivatedr() {
		return slowingDownActivatedr;
	}

	public void setSlowingDownActivatedr(boolean slowingDownActivatedr) {
		this.slowingDownActivatedr = slowingDownActivatedr;
	}

	public boolean getRunningTimerActivated() {
		return runningTimerActivated;
	}

	public void setRunningTimerActivated(boolean runningTimerActivated) {
		this.runningTimerActivated = runningTimerActivated;
	}

	public boolean getRunningTimerActivatedResponse() {
		return runningTimerActivatedResponse;
	}

	public void setRunningTimerActivatedResponse(boolean runningTimerActivatedResponse) {
		this.runningTimerActivatedResponse = runningTimerActivatedResponse;
	}

	public boolean getSpawnDone(){
		return spawnDone;
	}
	
	public boolean getSpawnDone2(){
		return spawnDone2;
	}
	
	public boolean getSpawnDone3(){
		return spawnDone3;
	}
	
	public boolean getSpawnDone4(){
		return spawnDone4;
	}

	public boolean getShiftOn() {
		return shiftOn;
	}

	public void setShiftOn(boolean shiftOn) {
		this.shiftOn = shiftOn;
	}

	public boolean getBowserSpawnSetupBoolean() {
		return bowserSpawnSetupBoolean;
	}

	public void setBowserSpawnSetupBoolean(boolean bowserSpawnSetupBoolean) {
		this.bowserSpawnSetupBoolean = bowserSpawnSetupBoolean;
	}

	public long getBowserSpawnSetup() {
		return bowserSpawnSetup;
	}

	public void setBowserSpawnSetup(long bowserSpawnSetup) {
		this.bowserSpawnSetup = bowserSpawnSetup;
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
	
	public BufferedImage getSpriteSheetNESMikeTyson(){
		return spriteSheetNESMikeTyson;
	}
	
	public BufferedImage getSpriteSheetNESContra(){
		return spriteSheetNESContra;
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
	
	public BufferedImage getMarioNESMikeTysonEntranceSprites(){
		return marioNESMikeTysonEntranceSprites;
	}
	
	public BufferedImage getMarioNESContraEntranceSprites(){
		return marioNESContraEntranceSprites;
	}
	
	public BufferedImage getVictoryPoseSpinningSprites(){
		return victoryPoseSpinningSprites;
	}
	
	public BufferedImage getAnimatedStar(){
		return animatedStar;
	}
	
	public BufferedImage getAnimatedShootingStar(){
		return animatedShootingStar;
	}
	
	public BufferedImage getUFOSprites(){
		return ufoSprites;
	}
	
	public BufferedImage getXboxButtonsSpriteSheet(){
		return xboxButtonsSpriteSheet;
	}
	
	public BufferedImage getDirectInputButtonsSpriteSheet(){
		return directInputButtonsSpriteSheet;
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
	
	public BufferedImage getBigMario5ItemAnimationSheet(){
		return bigMario5ItemAnimationSheet;
	}
	
	public BufferedImage getBigMario6ItemAnimationSheet(){
		return bigMario6ItemAnimationSheet;
	}
	
	public BufferedImage getLakituDeathAnimationSheet(){
		return lakituDeathAnimationSheet;
	}

	public BufferedImage getDancingAnimationSheet(){
		return dancingAnimationSheet;
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

	public BufferedImage getBombOmbSpriteSheet(){
		return bombOmbSpriteSheet;
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
	
	public BufferedImage getWASDButtonImagesSpriteSheet() {
		return wasdButtonImagesSpriteSheet;
	}
	
	public BufferedImage getGoombaDeathSpriteSheet() {
		return goombaDeathSpriteSheet;
	}
	
	public BufferedImage getYoshiSpriteSheet() {
		return yoshiSpriteSheet;
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

	public BufferedImage getDotDotDot() {
		return dotdotdot;
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
	
	public void addEntity(EntityE block){
		ee.add(block);
	}
	
	public void removeEntity(EntityE block){
		ee.remove(block);
	}
	
	public void addEntity(EntityF block){
		ef.add(block);
	}
	
	public void removeEntity(EntityF block){
		ef.remove(block);
	}

	public Controller getController() {
		return c;
	}

	public void setController(Controller c) {
		this.c = c;
	}

}
