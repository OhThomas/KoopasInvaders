package com.game.src.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import com.game.src.main.Game.STATE;
import com.game.src.main.libs.Animation;

public class CreditsController {
	private Game game;
	private Textures tex;
	public static Yoshi yoshi;
	public static SpikeCrawler spikey;
	public NakedLakituCredits nakedLaki;
	public NakedLakituCredits nakedLaki2;
	public MarioItemBallCredits marioBall;
	public MarioItemBallCredits marioBall2;
	public CloudCredits cloudy;
	public CloudCredits cloudy2;
	public StarExplosion starExplosion;
	private PlayerSpinning p;
	private SoundLoops seeYouNextTime;
	private SoundLoops spinSFX;
	private Animation openingDance;
	private Animation headBob1;
	private Animation headBob2;
	private Animation dance3;
	private Animation wormDance4;
	private Animation wormDance5;
	private Animation buttDance6;
	private Animation buttDance7;
	private Animation marioRunning;
	private Animation shell;
	private Animation marioWorming;
	private Animation marioMoonWalking;
	public static ArrayList<BufferedImage> creditsImage = new ArrayList<BufferedImage>();
	private ArrayList<Animation> creditsImage2 = new ArrayList<Animation>();
	public static ArrayList<Double> creditsImageX = new ArrayList<Double>();
	private ArrayList<Double> creditsImageX2 = new ArrayList<Double>();
	public static ArrayList<Double> creditsImageY = new ArrayList<Double>();
	private ArrayList<Double> creditsImageY2 = new ArrayList<Double>();
	private float imageTranslucent = 0;
	private long starExplosionTimer = 0;
	private long seeYouNextTimePause = 0;
	private long creditsPause = 0;
	private long songPause = 0;
	private long endPause = 0;
	private long imageTranslucentTimer = 0;
	private double velY = 0;
	private double marioWormingX = (Game.WIDTH*Game.SCALE)+120;
	private double marioRunningX = -150;
	private double marioMoonWalkingX = -150;
	private double shellX = -80;
	private double marioRunningVelX = 5;
	private double marioMoonWalkingVelX = 5;
	private double shellVelX = 6;
	private double imageTranslucentVelocity = 0;
	private int starExplosionX = 0;
	private int starExplosionY = 0;
	private int starExplosionX2 = 0;
	private int starExplosionY2 = 0;
	private int fireworksShot = 0;
	private int smallFireworksShot = 0;
	private int marioMoonWalkCounter = 0;
	private int marioMoonWalkCounter2 = 0;
	private boolean starExplode = false;
	private boolean smallExplode = false;
	private boolean imageIsGone = true;
	private boolean fadeSequence = false;
	private boolean firstFadeOut = false;
	private boolean fadeIn = false;
	private boolean fadeIn2 = false;
	private boolean fadeIn3 = false;
	private boolean fadeIn4 = false;
	private boolean fadeIn5 = false;
	private boolean fadeIn6 = false;
	private boolean fadeIn7 = false;
	private boolean fadeIn8 = false;
	private boolean fadeIn9 = false;
	private boolean fadeIn10 = false;
	private boolean fadeIn11 = false;
	private boolean fadeIn12 = false;
	private boolean fadeIn13 = false;
	private boolean fadeIn14 = false;
	private boolean fadeIn15 = false;
	private boolean fadeIn16 = false;
	private boolean fadeInBoolean = false;
	private boolean fadeInBoolean2 = false;
	private boolean fadeInBoolean3 = false;
	private boolean fadeInBoolean4 = false;
	private boolean fadeInBoolean5 = false;
	private boolean fadeInBoolean6 = false;
	private boolean fadeInBoolean7 = false;
	private boolean fadeInBoolean8 = false;
	private boolean fadeInBoolean9 = false;
	private boolean fadeInBoolean10 = false;
	private boolean fadeInBoolean11 = false;
	private boolean fadeInBoolean12 = false;
	private boolean fadeInBoolean13 = false;
	private boolean fadeInBoolean14 = false;
	private boolean fadeInBoolean15 = false;
	private boolean fadeInBoolean16 = false;
	private boolean creditsSetup = false;

	public CreditsController(Textures tex, Game game) {
		this.game = game;
		this.tex = tex;
		starExplosion = new StarExplosion();
		seeYouNextTime = new SoundLoops("res/Sounds/SFX/seeyounexttime.wav");
		spinSFX = new SoundLoops("res/Sounds/SFX/mariospinsfx2.wav");
		VolumeSlider.adjustSFX(seeYouNextTime);
		VolumeSlider.adjustSFX(spinSFX);
	}
	
	public void tick() {
		if(!creditsSetup) {
			p = new PlayerSpinning(tex, game, game.getPlayer().getX(), game.getPlayer().getY());
			BufferedImage imgTemp1 = resizeSmooth(tex.marioItemAnimationBeginning[18],(int)(tex.marioItemAnimationBeginning[18].getWidth()*3),(int)(tex.marioItemAnimationBeginning[18].getHeight()*3));
			BufferedImage imgTemp2 = resizeSmooth(tex.marioItemAnimationBeginning[19],(int)(tex.marioItemAnimationBeginning[19].getWidth()*3),(int)(tex.marioItemAnimationBeginning[19].getHeight()*3));
			openingDance = new Animation(6,imgTemp1,imgTemp2,
					imgTemp1);
			imgTemp1 = resizeSmooth(tex.marioHeadBob[0],(int)(tex.marioHeadBob[0].getWidth()*3),(int)(tex.marioHeadBob[0].getHeight()*3));
			imgTemp2 = resizeSmooth(tex.marioHeadBob[1],(int)(tex.marioHeadBob[1].getWidth()*3),(int)(tex.marioHeadBob[1].getHeight()*3));
			BufferedImage imgTemp3 = resizeSmooth(tex.marioHeadBob[2],(int)(tex.marioHeadBob[2].getWidth()*3),(int)(tex.marioHeadBob[2].getHeight()*3));
			BufferedImage imgTemp4 = resizeSmooth(tex.marioHeadBob[3],(int)(tex.marioHeadBob[3].getWidth()*3),(int)(tex.marioHeadBob[3].getHeight()*3));
			BufferedImage imgTemp5 = resizeSmooth(tex.marioHeadBob[4],(int)(tex.marioHeadBob[4].getWidth()*3),(int)(tex.marioHeadBob[4].getHeight()*3));
			BufferedImage imgTemp6 = resizeSmooth(tex.marioHeadBob[5],(int)(tex.marioHeadBob[5].getWidth()*3),(int)(tex.marioHeadBob[5].getHeight()*3));
			BufferedImage imgTemp7 = resizeSmooth(tex.marioHeadBob[6],(int)(tex.marioHeadBob[6].getWidth()*3),(int)(tex.marioHeadBob[6].getHeight()*3));
			headBob1 = new Animation(1,imgTemp1,imgTemp2,imgTemp3,imgTemp4,imgTemp5,imgTemp6,imgTemp7);
			imgTemp1 = resizeSmooth(tex.marioHeadBob[7],(int)(tex.marioHeadBob[7].getWidth()*3),(int)(tex.marioHeadBob[7].getHeight()*3));
			imgTemp2 = resizeSmooth(tex.marioHeadBob[8],(int)(tex.marioHeadBob[8].getWidth()*3),(int)(tex.marioHeadBob[8].getHeight()*3));
			imgTemp3 = resizeSmooth(tex.marioHeadBob[9],(int)(tex.marioHeadBob[9].getWidth()*3),(int)(tex.marioHeadBob[9].getHeight()*3));
			imgTemp4 = resizeSmooth(tex.marioHeadBob[10],(int)(tex.marioHeadBob[10].getWidth()*3),(int)(tex.marioHeadBob[10].getHeight()*3));
			imgTemp5 = resizeSmooth(tex.marioHeadBob[11],(int)(tex.marioHeadBob[11].getWidth()*3),(int)(tex.marioHeadBob[11].getHeight()*3));
			imgTemp6 = resizeSmooth(tex.marioHeadBob[12],(int)(tex.marioHeadBob[12].getWidth()*3),(int)(tex.marioHeadBob[12].getHeight()*3));
			imgTemp7 = resizeSmooth(tex.marioHeadBob[13],(int)(tex.marioHeadBob[13].getWidth()*3),(int)(tex.marioHeadBob[13].getHeight()*3));
			headBob2 = new Animation(1,imgTemp1,imgTemp2,imgTemp3,imgTemp4,imgTemp5,imgTemp6,imgTemp7);
			imgTemp1 = resizeSmooth(tex.marioItemAnimationBeginning[12],(int)(tex.marioItemAnimationBeginning[12].getWidth()*3),(int)(tex.marioItemAnimationBeginning[12].getHeight()*3));
			imgTemp2 = resizeSmooth(tex.marioItemAnimationBeginning[20],(int)(tex.marioItemAnimationBeginning[20].getWidth()*3),(int)(tex.marioItemAnimationBeginning[20].getHeight()*3));
			dance3 = new Animation(6,imgTemp1,imgTemp2,imgTemp1);
			imgTemp1 = resizeSmooth(tex.marioWormDance[0],(int)(tex.marioWormDance[0].getWidth()*3),(int)(tex.marioWormDance[0].getHeight()*3));
			imgTemp2 = resizeSmooth(tex.marioWormDance[1],(int)(tex.marioWormDance[1].getWidth()*3),(int)(tex.marioWormDance[1].getHeight()*3));
			imgTemp3 = resizeSmooth(tex.marioWormDance[2],(int)(tex.marioWormDance[2].getWidth()*3),(int)(tex.marioWormDance[2].getHeight()*3));
			imgTemp4 = resizeSmooth(tex.marioWormDance[3],(int)(tex.marioWormDance[3].getWidth()*3),(int)(tex.marioWormDance[3].getHeight()*3));
			imgTemp5 = resizeSmooth(tex.marioWormDance[4],(int)(tex.marioWormDance[4].getWidth()*3),(int)(tex.marioWormDance[4].getHeight()*3));
			imgTemp6 = resizeSmooth(tex.marioWormDance[5],(int)(tex.marioWormDance[5].getWidth()*3),(int)(tex.marioWormDance[5].getHeight()*3));
			imgTemp7 = resizeSmooth(tex.marioWormDance[6],(int)(tex.marioWormDance[6].getWidth()*3),(int)(tex.marioWormDance[6].getHeight()*3));
			wormDance4 = new Animation(2,imgTemp1,imgTemp2,imgTemp3,imgTemp4,imgTemp5,imgTemp6,imgTemp7);
			imgTemp1 = resizeSmooth(tex.marioWormDance[10],(int)(tex.marioWormDance[10].getWidth()*3),(int)(tex.marioWormDance[10].getHeight()*3));
			marioWorming = new Animation(6,imgTemp3,imgTemp4,imgTemp5,imgTemp6,imgTemp7,imgTemp1);
			imgTemp1 = resizeSmooth(tex.marioWormDance[7],(int)(tex.marioWormDance[7].getWidth()*3),(int)(tex.marioWormDance[7].getHeight()*3));
			imgTemp2 = resizeSmooth(tex.marioWormDance[8],(int)(tex.marioWormDance[8].getWidth()*3),(int)(tex.marioWormDance[8].getHeight()*3));
			imgTemp3 = resizeSmooth(tex.marioWormDance[9],(int)(tex.marioWormDance[9].getWidth()*3),(int)(tex.marioWormDance[9].getHeight()*3));
			wormDance5 = new Animation(2,imgTemp1,imgTemp2,imgTemp3);
			imgTemp1 = resizeSmooth(tex.marioButtShake[0],(int)(tex.marioButtShake[0].getWidth()*3),(int)(tex.marioButtShake[0].getHeight()*3));
			imgTemp2 = resizeSmooth(tex.marioButtShake[1],(int)(tex.marioButtShake[1].getWidth()*3),(int)(tex.marioButtShake[1].getHeight()*3));
			imgTemp3 = resizeSmooth(tex.marioButtShake[2],(int)(tex.marioButtShake[2].getWidth()*3),(int)(tex.marioButtShake[2].getHeight()*3));
			imgTemp4 = resizeSmooth(tex.marioButtShake[3],(int)(tex.marioButtShake[3].getWidth()*3),(int)(tex.marioButtShake[3].getHeight()*3));
			imgTemp5 = resizeSmooth(tex.marioButtShake[4],(int)(tex.marioButtShake[4].getWidth()*3),(int)(tex.marioButtShake[4].getHeight()*3));
			imgTemp6 = resizeSmooth(tex.marioButtShake[5],(int)(tex.marioButtShake[5].getWidth()*3),(int)(tex.marioButtShake[5].getHeight()*3));
			buttDance6 = new Animation(2,imgTemp1,imgTemp2,imgTemp3,imgTemp4,imgTemp5,imgTemp6);
			buttDance7 = new Animation(2,imgTemp4,imgTemp5,imgTemp4,imgTemp5);
			imgTemp1 = resizeSmooth(tex.greenShell[0],(int)(tex.greenShell[0].getWidth()*3),(int)(tex.greenShell[0].getHeight()*3));
			imgTemp2 = resizeSmooth(tex.greenShell[1],(int)(tex.greenShell[1].getWidth()*3),(int)(tex.greenShell[1].getHeight()*3));
			imgTemp3 = resizeSmooth(tex.greenShell[2],(int)(tex.greenShell[2].getWidth()*3),(int)(tex.greenShell[2].getHeight()*3));
			imgTemp4 = resizeSmooth(tex.greenShell[3],(int)(tex.greenShell[3].getWidth()*3),(int)(tex.greenShell[3].getHeight()*3));
			shell = new Animation(6, imgTemp1, imgTemp2, imgTemp3, imgTemp4);
			imgTemp1 = resizeSmooth(tex.player[4],(int)(tex.player[4].getWidth()*3),(int)(tex.player[4].getHeight()*3));
			imgTemp2 = resizeSmooth(tex.player[5],(int)(tex.player[5].getWidth()*3),(int)(tex.player[5].getHeight()*3));
			imgTemp3 = resizeSmooth(tex.player[6],(int)(tex.player[6].getWidth()*3),(int)(tex.player[6].getHeight()*3));
			imgTemp4 = resizeSmooth(tex.extraMario[0],(int)(tex.extraMario[0].getWidth()*3),(int)(tex.extraMario[0].getHeight()*3));
			imgTemp5 = resizeSmooth(tex.extraMario[1],(int)(tex.extraMario[1].getWidth()*3),(int)(tex.extraMario[1].getHeight()*3));
			marioMoonWalking = new Animation(9,imgTemp1,imgTemp2,imgTemp3,imgTemp2,imgTemp1,imgTemp4,imgTemp3,imgTemp5);
			marioRunning = new Animation(6, tex.marioRunningBigger[0], tex.marioRunningBigger[1], tex.marioRunningBigger[2], tex.marioRunningBigger[1]);
			Animation animationTemp1 = new Animation(2, tex.chainChompDancing[0], tex.chainChompDancing[1], tex.chainChompDancing[3],
					tex.chainChompDancing[4],tex.chainChompDancing[5],tex.chainChompDancing[6],tex.chainChompDancing[6],tex.chainChompDancing[5],
					tex.chainChompDancing[4],tex.chainChompDancing[3],tex.chainChompDancing[2],tex.chainChompDancing[1],tex.chainChompDancing[0],
					tex.chainChompDancing[0],tex.chainChompDancing[0],tex.chainChompDancing[0],tex.chainChompDancing[7],tex.chainChompDancing[7],
					tex.chainChompDancing[7],tex.chainChompDancing[7],tex.chainChompDancing[0],tex.chainChompDancing[0],tex.chainChompDancing[0],
					tex.chainChompDancing[7],tex.chainChompDancing[7],tex.chainChompDancing[7],tex.chainChompDancing[7],tex.chainChompDancing[0],
					tex.chainChompDancing[8],tex.chainChompDancing[0]);
			creditsImage2.add(animationTemp1);
			animationTemp1 = new Animation(4, tex.chainChompDancing2[0], tex.chainChompDancing2[1], tex.chainChompDancing2[0], tex.chainChompDancing2[1],
					tex.chainChompDancing2[2], tex.chainChompDancing2[3], tex.chainChompDancing2[4], tex.chainChompDancing2[5],tex.chainChompDancing2[6],
					tex.chainChompDancing2[7],tex.chainChompDancing2[8],tex.chainChompDancing2[9],tex.chainChompDancing2[10],tex.chainChompDancing2[11],
					tex.chainChompDancing2[12],tex.chainChompDancing2[13],tex.chainChompDancing2[12],tex.chainChompDancing2[14],tex.chainChompDancing2[15],
					tex.chainChompDancing2[16],tex.chainChompDancing2[17],tex.chainChompDancing2[18],tex.chainChompDancing2[19],tex.chainChompDancing2[20],
					tex.chainChompDancing2[21],tex.chainChompDancing2[22],tex.chainChompDancing2[23]);
			creditsImage2.add(animationTemp1);
			imgTemp1 = resizeSmooth(tex.ampItem[4],(int)(tex.ampItem[4].getWidth()*2),(int)(tex.ampItem[4].getHeight()*2));
			imgTemp2 = resizeSmooth(tex.ampItem[5],(int)(tex.ampItem[5].getWidth()*2),(int)(tex.ampItem[5].getHeight()*2));
			imgTemp3 = resizeSmooth(tex.ampItem[6],(int)(tex.ampItem[6].getWidth()*2),(int)(tex.ampItem[6].getHeight()*2));
			imgTemp4 = resizeSmooth(tex.ampItem[7],(int)(tex.ampItem[7].getWidth()*2),(int)(tex.ampItem[7].getHeight()*2));
			imgTemp5 = resizeSmooth(tex.ampItem[8],(int)(tex.ampItem[8].getWidth()*2),(int)(tex.ampItem[8].getHeight()*2));
			imgTemp6 = resizeSmooth(tex.ampItem[9],(int)(tex.ampItem[9].getWidth()*2),(int)(tex.ampItem[9].getHeight()*2));
			imgTemp7 = resizeSmooth(tex.ampItem[10],(int)(tex.ampItem[10].getWidth()*2),(int)(tex.ampItem[10].getHeight()*2));
			BufferedImage imgTemp8 = resizeSmooth(tex.ampItem[11],(int)(tex.ampItem[11].getWidth()*2),(int)(tex.ampItem[11].getHeight()*2));
			animationTemp1 = new Animation(4, imgTemp1,imgTemp2,imgTemp3,imgTemp4,imgTemp5,imgTemp6,imgTemp7,imgTemp8);
			creditsImage2.add(animationTemp1);
			imgTemp1 = resizeSmooth(tex.ampItem[12],(int)(tex.ampItem[12].getWidth()*2),(int)(tex.ampItem[12].getHeight()*2));
			imgTemp2 = resizeSmooth(tex.ampItem[13],(int)(tex.ampItem[13].getWidth()*2),(int)(tex.ampItem[13].getHeight()*2));
			imgTemp3 = resizeSmooth(tex.ampItem[14],(int)(tex.ampItem[14].getWidth()*2),(int)(tex.ampItem[14].getHeight()*2));
			imgTemp4 = resizeSmooth(tex.ampItem[15],(int)(tex.ampItem[15].getWidth()*2),(int)(tex.ampItem[15].getHeight()*2));
			imgTemp5 = resizeSmooth(tex.ampItem[16],(int)(tex.ampItem[16].getWidth()*2),(int)(tex.ampItem[16].getHeight()*2));
			imgTemp6 = resizeSmooth(tex.ampItem[17],(int)(tex.ampItem[17].getWidth()*2),(int)(tex.ampItem[17].getHeight()*2));
			imgTemp7 = resizeSmooth(tex.ampItem[18],(int)(tex.ampItem[18].getWidth()*2),(int)(tex.ampItem[18].getHeight()*2));
			imgTemp8 = resizeSmooth(tex.ampItem[19],(int)(tex.ampItem[19].getWidth()*2),(int)(tex.ampItem[19].getHeight()*2));
			animationTemp1 = new Animation(4, imgTemp4, imgTemp3, imgTemp2, imgTemp1, imgTemp8, imgTemp7, imgTemp6, imgTemp5);
			creditsImage2.add(animationTemp1);
			imgTemp1 = resizeSmooth(tex.luigiDance[13],(int)(tex.luigiDance[13].getWidth()*2),(int)(tex.luigiDance[13].getHeight()*2));
			imgTemp2 = resizeSmooth(tex.playerSNESFireLuigi[11],(int)(tex.playerSNESFireLuigi[11].getWidth()*2),(int)(tex.playerSNESFireLuigi[11].getHeight()*2));
			imgTemp3 = resizeSmooth(tex.playerSNESFireLuigi[10],(int)(tex.playerSNESFireLuigi[10].getWidth()*2),(int)(tex.playerSNESFireLuigi[10].getHeight()*2));
			imgTemp4 = resizeSmooth(tex.playerSNESFireLuigi[9],(int)(tex.playerSNESFireLuigi[9].getWidth()*2),(int)(tex.playerSNESFireLuigi[9].getHeight()*2));
			imgTemp5 = resizeSmooth(tex.playerSNESFireLuigi[8],(int)(tex.playerSNESFireLuigi[8].getWidth()*2),(int)(tex.playerSNESFireLuigi[8].getHeight()*2));
			imgTemp6 = resizeSmooth(tex.playerSNESFireLuigi[17],(int)(tex.playerSNESFireLuigi[17].getWidth()*2),(int)(tex.playerSNESFireLuigi[17].getHeight()*2));
			imgTemp7 = resizeSmooth(tex.playerSNESFireLuigi[4],(int)(tex.playerSNESFireLuigi[4].getWidth()*2),(int)(tex.playerSNESFireLuigi[4].getHeight()*2));
			imgTemp8 = resizeSmooth(tex.playerSNESFireLuigi[5],(int)(tex.playerSNESFireLuigi[5].getWidth()*2),(int)(tex.playerSNESFireLuigi[5].getHeight()*2));
			BufferedImage imgTemp9 = resizeSmooth(tex.playerSNESFireLuigi[6],(int)(tex.playerSNESFireLuigi[6].getWidth()*2),(int)(tex.playerSNESFireLuigi[6].getHeight()*2));
			BufferedImage imgTemp10 = resizeSmooth(tex.playerSNESFireLuigi[7],(int)(tex.playerSNESFireLuigi[7].getWidth()*2),(int)(tex.playerSNESFireLuigi[7].getHeight()*2));
			BufferedImage imgTemp11 = resizeSmooth(tex.playerSNESFireLuigi[16],(int)(tex.playerSNESFireLuigi[16].getWidth()*2),(int)(tex.playerSNESFireLuigi[16].getHeight()*2));
			BufferedImage imgTemp12 = resizeSmooth(tex.luigiDance[11],(int)(tex.luigiDance[11].getWidth()*2),(int)(tex.luigiDance[11].getHeight()*2));
			BufferedImage imgTemp13 = resizeSmooth(tex.luigiDance[12],(int)(tex.luigiDance[12].getWidth()*2),(int)(tex.luigiDance[12].getHeight()*2));
			animationTemp1 = new Animation(6, imgTemp1, imgTemp2, imgTemp3, imgTemp4, imgTemp5, imgTemp6, imgTemp7, imgTemp8,
					imgTemp9, imgTemp10, imgTemp11, imgTemp12, imgTemp13);
			creditsImage2.add(animationTemp1);
			imgTemp1 = resizeSmooth(tex.luigiDance[0],(int)(tex.luigiDance[0].getWidth()*2),(int)(tex.luigiDance[0].getHeight()*2));
			imgTemp2 = resizeSmooth(tex.luigiDance[1],(int)(tex.luigiDance[1].getWidth()*2),(int)(tex.luigiDance[1].getHeight()*2));
			imgTemp3 = resizeSmooth(tex.luigiDance[2],(int)(tex.luigiDance[2].getWidth()*2),(int)(tex.luigiDance[2].getHeight()*2));
			imgTemp4 = resizeSmooth(tex.luigiDance[3],(int)(tex.luigiDance[3].getWidth()*2),(int)(tex.luigiDance[3].getHeight()*2));
			imgTemp5 = resizeSmooth(tex.luigiDance[4],(int)(tex.luigiDance[4].getWidth()*2),(int)(tex.luigiDance[4].getHeight()*2));
			imgTemp6 = resizeSmooth(tex.luigiDance[5],(int)(tex.luigiDance[5].getWidth()*2),(int)(tex.luigiDance[5].getHeight()*2));
			imgTemp7 = resizeSmooth(tex.luigiDance[6],(int)(tex.luigiDance[6].getWidth()*2),(int)(tex.luigiDance[6].getHeight()*2));
			imgTemp8 = resizeSmooth(tex.luigiDance[7],(int)(tex.luigiDance[7].getWidth()*2),(int)(tex.luigiDance[7].getHeight()*2));
			imgTemp9 = resizeSmooth(tex.luigiDance[8],(int)(tex.luigiDance[8].getWidth()*2),(int)(tex.luigiDance[8].getHeight()*2));
			imgTemp10 = resizeSmooth(tex.luigiDance[9],(int)(tex.luigiDance[9].getWidth()*2),(int)(tex.luigiDance[9].getHeight()*2));
			imgTemp11 = resizeSmooth(tex.luigiDance[10],(int)(tex.luigiDance[10].getWidth()*2),(int)(tex.luigiDance[10].getHeight()*2));
			animationTemp1 = new Animation(6, imgTemp1, imgTemp2, imgTemp3, imgTemp4, imgTemp5, imgTemp6, imgTemp7, imgTemp8,
					imgTemp9, imgTemp10, imgTemp11);
			creditsImage2.add(animationTemp1);
			imgTemp1 = resizeSmooth(tex.marioEntranceNES3[0],(int)(tex.marioEntranceNES3[0].getWidth()*2),(int)(tex.marioEntranceNES3[0].getHeight()*2));
			imgTemp2 = resizeSmooth(tex.marioEntranceNES3[1],(int)(tex.marioEntranceNES3[1].getWidth()*2),(int)(tex.marioEntranceNES3[1].getHeight()*2));
			imgTemp3 = resizeSmooth(tex.marioEntranceNES3[2],(int)(tex.marioEntranceNES3[2].getWidth()*2),(int)(tex.marioEntranceNES3[2].getHeight()*2));
			imgTemp4 = resizeSmooth(tex.marioEntranceNES3[3],(int)(tex.marioEntranceNES3[3].getWidth()*2),(int)(tex.marioEntranceNES3[3].getHeight()*2));
			imgTemp5 = resizeSmooth(tex.marioEntranceNES3[4],(int)(tex.marioEntranceNES3[4].getWidth()*2),(int)(tex.marioEntranceNES3[4].getHeight()*2));
			imgTemp6 = resizeSmooth(tex.marioEntranceNES3[5],(int)(tex.marioEntranceNES3[5].getWidth()*2),(int)(tex.marioEntranceNES3[5].getHeight()*2));
			imgTemp7 = resizeSmooth(tex.marioEntranceNES3[6],(int)(tex.marioEntranceNES3[6].getWidth()*2),(int)(tex.marioEntranceNES3[6].getHeight()*2));
			imgTemp8 = resizeSmooth(tex.marioEntranceNES3[7],(int)(tex.marioEntranceNES3[7].getWidth()*2),(int)(tex.marioEntranceNES3[7].getHeight()*2));
			imgTemp9 = resizeSmooth(tex.marioEntranceNES3[8],(int)(tex.marioEntranceNES3[8].getWidth()*2),(int)(tex.marioEntranceNES3[8].getHeight()*2));
			animationTemp1 = new Animation(6, imgTemp1,imgTemp2,imgTemp3,imgTemp4,imgTemp5,imgTemp6,imgTemp7,imgTemp8,imgTemp9);
			creditsImage2.add(animationTemp1);
			animationTemp1 = new Animation(6, imgTemp1,imgTemp4,imgTemp3,imgTemp2,imgTemp5,imgTemp8,imgTemp9,imgTemp6,imgTemp7);
			creditsImage2.add(animationTemp1);
			imgTemp1 = resizeSmooth(tex.toadDance[0],(int)(tex.toadDance[0].getWidth()*2),(int)(tex.toadDance[0].getHeight()*2));
			imgTemp2 = resizeSmooth(tex.toadDance[1],(int)(tex.toadDance[1].getWidth()*2),(int)(tex.toadDance[1].getHeight()*2));
			imgTemp3 = resizeSmooth(tex.toadDance[2],(int)(tex.toadDance[2].getWidth()*2),(int)(tex.toadDance[2].getHeight()*2));
			imgTemp4 = resizeSmooth(tex.toadDance[3],(int)(tex.toadDance[3].getWidth()*2),(int)(tex.toadDance[3].getHeight()*2));
			imgTemp5 = resizeSmooth(tex.toadDance[4],(int)(tex.toadDance[4].getWidth()*2),(int)(tex.toadDance[4].getHeight()*2));
			imgTemp6 = resizeSmooth(tex.toadDance[5],(int)(tex.toadDance[5].getWidth()*2),(int)(tex.toadDance[5].getHeight()*2));
			imgTemp7 = resizeSmooth(tex.toadDance[6],(int)(tex.toadDance[6].getWidth()*2),(int)(tex.toadDance[6].getHeight()*2));
			imgTemp8 = resizeSmooth(tex.toadDance[7],(int)(tex.toadDance[7].getWidth()*2),(int)(tex.toadDance[7].getHeight()*2));
			imgTemp9 = resizeSmooth(tex.toadDance[8],(int)(tex.toadDance[8].getWidth()*2),(int)(tex.toadDance[8].getHeight()*2));
			imgTemp10 = resizeSmooth(tex.toadDance[9],(int)(tex.toadDance[9].getWidth()*2),(int)(tex.toadDance[9].getHeight()*2));
			imgTemp11 = resizeSmooth(tex.toadDance[10],(int)(tex.toadDance[10].getWidth()*2),(int)(tex.toadDance[10].getHeight()*2));
			imgTemp12 = resizeSmooth(tex.toadDance[11],(int)(tex.toadDance[11].getWidth()*2),(int)(tex.toadDance[11].getHeight()*2));
			imgTemp13 = resizeSmooth(tex.toadDance[12],(int)(tex.toadDance[12].getWidth()*2),(int)(tex.toadDance[12].getHeight()*2));
			BufferedImage imgTemp14 = resizeSmooth(tex.toadDance[13],(int)(tex.toadDance[13].getWidth()*2),(int)(tex.toadDance[13].getHeight()*2));
			BufferedImage imgTemp15 = resizeSmooth(tex.toadDance[14],(int)(tex.toadDance[14].getWidth()*2),(int)(tex.toadDance[14].getHeight()*2));
			animationTemp1 = new Animation(6, imgTemp1,imgTemp2,imgTemp3,imgTemp4,imgTemp5,imgTemp6,imgTemp7,imgTemp8,
					imgTemp9,imgTemp10,imgTemp11,imgTemp12,imgTemp13,imgTemp14,imgTemp15);
			creditsImage2.add(animationTemp1);
			imgTemp1 = resizeSmooth(tex.toadDance[15],(int)(tex.toadDance[15].getWidth()*2),(int)(tex.toadDance[15].getHeight()*2));
			imgTemp2 = resizeSmooth(tex.toadDance[16],(int)(tex.toadDance[16].getWidth()*2),(int)(tex.toadDance[16].getHeight()*2));
			imgTemp3 = resizeSmooth(tex.toadDance[17],(int)(tex.toadDance[17].getWidth()*2),(int)(tex.toadDance[17].getHeight()*2));
			imgTemp4 = resizeSmooth(tex.toadDance[18],(int)(tex.toadDance[18].getWidth()*2),(int)(tex.toadDance[18].getHeight()*2));
			imgTemp5 = resizeSmooth(tex.toadDance[19],(int)(tex.toadDance[19].getWidth()*2),(int)(tex.toadDance[19].getHeight()*2));
			imgTemp6 = resizeSmooth(tex.toadDance[20],(int)(tex.toadDance[20].getWidth()*2),(int)(tex.toadDance[20].getHeight()*2));
			imgTemp7 = resizeSmooth(tex.toadDance[21],(int)(tex.toadDance[21].getWidth()*2),(int)(tex.toadDance[21].getHeight()*2));
			animationTemp1 = new Animation(18, imgTemp1,imgTemp2,imgTemp3,imgTemp4,imgTemp5,imgTemp6,imgTemp7,imgTemp7);
			creditsImage2.add(animationTemp1);
			openingDance.nextFrame();
			openingDance.setCount(0);
			headBob1.nextFrame();
			headBob1.setCount(0);
			headBob2.nextFrame();
			headBob2.setCount(0);
			dance3.nextFrame();
			dance3.setCount(0);
			wormDance4.nextFrame();
			wormDance4.setCount(0);
			wormDance5.nextFrame();
			wormDance5.setCount(0);
			buttDance6.nextFrame();
			buttDance6.setCount(0);
			buttDance7.nextFrame();
			buttDance7.setCount(0);
			shell.nextFrame();
			shell.setCount(0);
			marioMoonWalking.nextFrame();
			marioMoonWalking.setCount(0);
			marioRunning.nextFrame();
			marioRunning.setCount(0);
			marioWorming.nextFrame();
			marioWorming.setCount(0);
			for(int i = 0; i <= creditsImage2.size()-1; i++) {
				creditsImage2.get(i).nextFrame();
				creditsImage2.get(i).setCount(0);
			}
			creditsSetup = true;
		}
		if(endPause != 0 && endPause < System.currentTimeMillis()) {
			reset();
			if(Game.backToGameOver)
				Game.State = STATE.GAMEOVER;
			else {
				Game.selectorButtonPosition = 0;
				Game.resetMenuSound();
				Game.State = STATE.MENU;
			}
			return;
		}
		if(Game.creditsSong.getSoundLoopBoolean() == true && !Game.creditsSong.clipIsActive() && seeYouNextTime.getSoundLoopBoolean() == false) {
			if(seeYouNextTimePause == 0)
				seeYouNextTimePause = System.currentTimeMillis() + 2500;
			if(seeYouNextTimePause < System.currentTimeMillis()) {
				seeYouNextTime.play();
				seeYouNextTime.setSoundLoopBoolean(true);
				endPause = System.currentTimeMillis() + 2000;
			}
		}
		if(!spinSFX.getSoundLoopBoolean()) {
			spinSFX.play();
			spinSFX.setSoundLoopBoolean(true);
			songPause = System.currentTimeMillis() + 4500;
		}
		if(Game.creditsSong.getSoundLoopBoolean() == false && !spinSFX.clipIsActive() && songPause != 0 && songPause < System.currentTimeMillis()) {
			imageIsGone = true;
			imageTranslucentVelocity = 0;
			imageTranslucent = 0;
			imageTranslucentTimer = 0;
			fadeIn = true;
			fadeInBoolean = true;
			Game.creditsSong.play();
			Game.creditsSong.setSoundLoopBoolean(true);
		}
		if(!fadeSequence) {
			if(!firstFadeOut) {
				if(imageIsGone) {
					if(imageTranslucent >= 0.99 && imageTranslucentTimer < System.currentTimeMillis()){
						imageTranslucentVelocity = 0;//+= -0.01;
						imageIsGone = false;
					}
					else if(imageTranslucent <= 0.01 && imageTranslucentTimer < System.currentTimeMillis()){
						imageTranslucentVelocity += 0.01;
					}
					
					if(imageTranslucent <= 0.01 && imageIsGone == false){
						imageTranslucentTimer = System.currentTimeMillis() + 500;
						imageIsGone = true;
					}
					if(imageTranslucent + imageTranslucentVelocity <= 1 &&
							imageTranslucent + imageTranslucentVelocity >= 0)
						imageTranslucent += imageTranslucentVelocity;
				}
				else
					firstFadeOut = true;
				
			}
			fader(fadeIn);
			fader(fadeIn2);
			fader(fadeIn3);
			fader(fadeIn4);
			fader(fadeIn5);
			fader(fadeIn6);
			fader(fadeIn7);
			fader(fadeIn8);
			fader(fadeIn9);
			fader(fadeIn10);
			fader(fadeIn11);
			if(Game.creditsSong.getLongFramePosition() > (47804/2) && fadeInBoolean2 == false) {
				resetTranslucenticity();
				openingDance.nextFrame();
				openingDance.nextFrame();
				fadeIn2 = true;
				fadeInBoolean2 = true;
			}
			if(Game.creditsSong.getLongFramePosition() > (79689/2) && fadeInBoolean3 == false) {
				resetTranslucenticity();
				openingDance.nextFrame();
				fadeIn3 = true;
				fadeInBoolean3 = true;
			}
			if(Game.creditsSong.getLongFramePosition() > (127537/2) && fadeInBoolean4 == false) {
				resetTranslucenticity();
				fadeIn4 = true;
				fadeInBoolean4 = true;
			}
			if(Game.creditsSong.getLongFramePosition() > (175342/2) && fadeInBoolean5 == false) {
				resetTranslucenticity();
				fadeIn5 = true;
				fadeInBoolean5 = true;
			}
			if(Game.creditsSong.getLongFramePosition() > (207226/2) && fadeInBoolean6 == false) {
				resetTranslucenticity();
				fadeIn6 = true;
				fadeInBoolean6 = true;
			}
			if(Game.creditsSong.getLongFramePosition() > (255030/2) && fadeInBoolean7 == false) {
				resetTranslucenticity();
				fadeIn7 = true;
				fadeInBoolean7 = true;
			}
			if(Game.creditsSong.getLongFramePosition() > (302835/2) && fadeInBoolean8 == false) {
				resetTranslucenticity();
				dance3.nextFrame();
				dance3.nextFrame();
				fadeIn8 = true;
				fadeInBoolean8 = true;
			}
			if(Game.creditsSong.getLongFramePosition() > (334719/2) && fadeInBoolean9 == false) {
				resetTranslucenticity();
				dance3.nextFrame();
				fadeIn9 = true;
				fadeInBoolean9 = true;
			}
			if(Game.creditsSong.getLongFramePosition() > (382568/2) && fadeInBoolean10 == false) {
				resetTranslucenticity();
				fadeIn10 = true;
				fadeInBoolean10 = true;
			}
			if(Game.creditsSong.getLongFramePosition() > (430372/2) && fadeInBoolean11 == false) {
				resetTranslucenticity();
				fadeIn11 = true;
				fadeInBoolean11 = true;
			}
			if(Game.creditsSong.getLongFramePosition() > (462256/2) && fadeInBoolean12 == false) {//FASTER
				resetTranslucenticity();
				fadeIn12 = true;
				fadeInBoolean12 = true;
			}
			if(Game.creditsSong.getLongFramePosition() > (470238/2) && fadeInBoolean13 == false) {
				resetTranslucenticity();
				fadeIn13 = true;
				fadeInBoolean13 = true;
			}
			if(Game.creditsSong.getLongFramePosition() > (478176/2) && fadeInBoolean14 == false) {
				resetTranslucenticity();
				fadeIn14 = true;
				fadeInBoolean14 = true;
			}
			if(Game.creditsSong.getLongFramePosition() > (486158/2) && fadeInBoolean15 == false) {
				resetTranslucenticity();
				fadeIn15 = true;
				fadeInBoolean15 = true;
			}
			if(Game.creditsSong.getLongFramePosition() > (494141/2) && fadeInBoolean16 == false) {
				resetTranslucenticity();
				fadeIn16 = true;
				fadeInBoolean16 = true;
			}
			if(Game.creditsSong.getLongFramePosition() > (510061/2)) {
				fadeSequence = true;
				velY = -0.53;
				creditsPause = System.currentTimeMillis() + 3000;
//				creditsImage.add(HUD.stringToMario3FontImage("CREDITS:"));
//				Game.resize(creditsImage.get(0), (creditsImage.get(0).getWidth()*2), (creditsImage.get(0).getHeight()*2));
				//LOAD DURING WOOSHING SOUND?
				BufferedImage imgTemp = HUD.stringToMario3FontImage("CREDITS"); 
				creditsImage.add(Game.resize(imgTemp, (int) (imgTemp.getWidth()*1.5), (int) (imgTemp.getHeight()*1.5)));
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(0).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT - (creditsImage.get(0).getHeight()/2)));
				imgTemp = HUD.stringToMario3FontImage("CREATED BY:");
				creditsImage.add(Game.resize(imgTemp, (int) (imgTemp.getWidth()*1.3), (int) (imgTemp.getHeight()*1.3)));
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(1).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE) + (creditsImage.get(1).getHeight()/2));
//				creditsImage.add(HUD.stringToMario3FontImage("CREATED BY:"));
//				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(1).getWidth()/2));
//				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE) + (creditsImage.get(1).getHeight()/2));
				creditsImage.add(HUD.stringToMario3FontImage("Thomas Rader"));
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(2).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(creditsImage.get(1).getHeight()) + 20 + 10);
				imgTemp = HUD.stringToMario3FontImage("SPRITE RIPPERS");
				creditsImage.add(Game.resize(imgTemp, (int) (imgTemp.getWidth()*1), (int) (imgTemp.getHeight()*1)));
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(3).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(2 * creditsImage.get(1).getHeight()) + (2 *20) + 10 + 100);
				creditsImage.add(HUD.stringToMario3FontImage("+"));
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(4).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(2 * creditsImage.get(1).getHeight()) + (2 *20)+ (10+64) + 100);
				imgTemp = HUD.stringToMario3FontImage("ADDITIONAL ART:");
				creditsImage.add(Game.resize(imgTemp, (int) (imgTemp.getWidth()*1), (int) (imgTemp.getHeight()*1)));
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(5).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(2 * creditsImage.get(1).getHeight()) + (2 *20)+ (10+64+64) + 100);
//				creditsImage.add(HUD.stringToMario3FontImage("SPRITE RIPPERS /"));
//				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(3).getWidth()/2));
//				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
//						(2 * creditsImage.get(1).getHeight()) + (2 *20) + 10 + 100);
//				creditsImage.add(HUD.stringToMario3FontImage("ADDITIONAL ART:"));
//				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(4).getWidth()/2));
//				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
//						(3 * creditsImage.get(1).getHeight()) + (3 *20)+ (2 *10) + 100);
				creditsImage.add(HUD.stringToMario3FontImage("Medaforcer"));//Photos - Chain_Chomp_sprites.png
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(6).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(3 * creditsImage.get(1).getHeight()) + (3 *20)+ (10+64+64) + 100);
				
				creditsImageX2.add((double) Game.WIDTH - (creditsImage.get(6).getWidth()/2)-creditsImage2.get(0).getCurrentImage().getWidth()-20);
				creditsImageY2.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(3 * creditsImage.get(1).getHeight()) + (3 *20)+ (10+64+64) + 100 + (creditsImage.get(6).getHeight()-creditsImage2.get(0).getCurrentImage().getHeight()));
				creditsImageX2.add((double) Game.WIDTH + (creditsImage.get(6).getWidth()/2)+20);
				creditsImageY2.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(3 * creditsImage.get(1).getHeight()) + (3 *20)+ (10+64+64) + 100 + (creditsImage.get(6).getHeight()-creditsImage2.get(1).getCurrentImage().getHeight()));
				
				
				creditsImage.add(HUD.stringToMario3FontImage("balladofwindfishes"));//Photos - Chain_Chomp_sprites.png
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(7).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(4 * creditsImage.get(1).getHeight()) + (4 *20)+ (10+64+64) + 100);
				creditsImage.add(HUD.stringToMario3FontImage("A.J. Nitro"));//Photos - Chain_Chomp_sprites.png // Photos - amp.png
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(8).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(5 * creditsImage.get(1).getHeight()) + (5 *20)+ (10+64+64) + 100);
				
				creditsImageX2.add((double) Game.WIDTH - (creditsImage.get(8).getWidth()/2)- creditsImage2.get(2).getCurrentImage().getWidth()-30);
				creditsImageY2.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(5 * creditsImage.get(1).getHeight()) + (5 *20)+ (10+64+64) + 100 + ((creditsImage.get(8).getHeight()-creditsImage2.get(2).getCurrentImage().getHeight())/2));
				creditsImageX2.add((double) Game.WIDTH + (creditsImage.get(8).getWidth()/2)+30);
				creditsImageY2.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(5 * creditsImage.get(1).getHeight()) + (5 *20)+ (10+64+64) + 100 + ((creditsImage.get(8).getHeight()-creditsImage2.get(2).getCurrentImage().getHeight())/2));
				
				creditsImage.add(HUD.stringToMario3FontImage("evildevil"));//Photos - punchout2.png
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(9).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(6 * creditsImage.get(1).getHeight()) + (6 *20)+ (10+64+64) + 100);
				creditsImage.add(HUD.stringToMario3FontImage("AwesomeZack"));//Photos - luigi-5.png
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(10).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(7 * creditsImage.get(1).getHeight()) + (7 *20)+ (10+64+64) + 100);
				
				creditsImageX2.add((double) Game.WIDTH - (creditsImage.get(10).getWidth()/2)- creditsImage2.get(4).getCurrentImage().getWidth()-20);
				creditsImageY2.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(7 * creditsImage.get(1).getHeight()) + (7 *20)+ (10+64+64) + 100 + ((creditsImage.get(10).getHeight()-creditsImage2.get(4).getCurrentImage().getHeight())/2));
				creditsImageX2.add((double) Game.WIDTH + (creditsImage.get(10).getWidth()/2)+20);
				creditsImageY2.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(7 * creditsImage.get(1).getHeight()) + (7 *20)+ (10+64+64) + 100 + ((creditsImage.get(10).getHeight()-creditsImage2.get(4).getCurrentImage().getHeight())/2));
				
				creditsImage.add(HUD.stringToMario3FontImage("Toadkarter"));//Photos - bob_omb_sprite_sheet_by_t3hteeks.png // Photos - amp.gif
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(11).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(8 * creditsImage.get(1).getHeight()) + (8 *20)+ (10+64+64) + 100);
				creditsImage.add(HUD.stringToMario3FontImage("Drshnaps"));//Photos - 4230.png
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(12).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(9 * creditsImage.get(1).getHeight()) + (9 *20)+ (10+64+64) + 100);
				
				creditsImageX2.add((double) Game.WIDTH - (creditsImage.get(12).getWidth()/2)- creditsImage2.get(6).getCurrentImage().getWidth()-20);
				creditsImageY2.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(9 * creditsImage.get(1).getHeight()) + (9 *20)+ (10+64+64) + 100 + ((creditsImage.get(12).getHeight()-creditsImage2.get(6).getCurrentImage().getHeight())/2));
				creditsImageX2.add((double) Game.WIDTH + (creditsImage.get(12).getWidth()/2)+20);
				creditsImageY2.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(9 * creditsImage.get(1).getHeight()) + (9 *20)+ (10+64+64) + 100 + ((creditsImage.get(12).getHeight()-creditsImage2.get(6).getCurrentImage().getHeight())/2));
				
				creditsImage.add(HUD.stringToMario3FontImage("Random Talking"));//Photos - smwwish.png
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(13).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(10 * creditsImage.get(1).getHeight()) + (10 *20)+ (10+64+64) + 100);
				creditsImage.add(HUD.stringToMario3FontImage("Bush"));//Photos - smwwish.png
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(14).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(10 * creditsImage.get(1).getHeight()) + (10 *20)+ (10+64+64+64) + 100);
				creditsImage.add(HUD.stringToMario3FontImage("jdaster64"));//marioadvancespritesheet.png
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(15).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(11 * creditsImage.get(1).getHeight()) + (11 *20)+ (10+64+64+64) + 100);

				creditsImageX2.add((double) Game.WIDTH - (creditsImage.get(15).getWidth()/2)- creditsImage2.get(8).getCurrentImage().getWidth()-20);
				creditsImageY2.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(11 * creditsImage.get(1).getHeight()) + (11 *20)+ (10+64+64+64) + 100 + ((creditsImage.get(15).getHeight()-creditsImage2.get(8).getCurrentImage().getHeight())/2));
				creditsImageX2.add((double) Game.WIDTH + (creditsImage.get(15).getWidth()/2)+20);
				creditsImageY2.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(11 * creditsImage.get(1).getHeight()) + (11 *20)+ (10+64+64+64) + 100 + ((creditsImage.get(15).getHeight()-creditsImage2.get(8).getCurrentImage().getHeight())/2));
				
				creditsImage.add(HUD.stringToMario3FontImage("Ant19831983"));//objects-effects.png
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(16).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(12 * creditsImage.get(1).getHeight()) + (12 *20)+ (10+64+64+64) + 100);
				creditsImage.add(HUD.stringToMario3FontImage("Kevin Huff"));//lakituandspiny.gif
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(17).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(13* creditsImage.get(1).getHeight()) + (13 *20)+ (10+64+64+64) + 100);
				
//				creditsImageX2.add((double) Game.WIDTH - (creditsImage.get(17).getWidth()/2)- creditsImage2.get(10).getCurrentImage().getWidth()-20);
//				creditsImageY2.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
//						(13 * creditsImage.get(1).getHeight()) + (13 *20)+ (10+64+64+64) + 100 + ((creditsImage.get(17).getHeight()-creditsImage2.get(10).getCurrentImage().getHeight())/2));
//				creditsImageX2.add((double) Game.WIDTH + (creditsImage.get(17).getWidth()/2)+20);
//				creditsImageY2.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
//						(13 * creditsImage.get(1).getHeight()) + (13 *20)+ (10+64+64+64) + 100 + ((creditsImage.get(17).getHeight()-creditsImage2.get(10).getCurrentImage().getHeight())/2));
				
				creditsImage.add(HUD.stringToMario3FontImage("Deathbringer"));//objects-effects.png
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(18).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(14 * creditsImage.get(1).getHeight()) + (14 *20)+ (10+64+64+64) + 100);
				creditsImage.add(HUD.stringToMario3FontImage("Tonberry2k"));//supermarioworldenemies2.png
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(19).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(15 * creditsImage.get(1).getHeight()) + (15 *20)+ (10+64+64+64) + 100);
				creditsImage.add(HUD.stringToMario3FontImage("Beam"));//smb1_mis_sprites.png
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(20).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(16 * creditsImage.get(1).getHeight()) + (16 *20)+ (10+64+64+64) + 100);
				creditsImage.add(HUD.stringToMario3FontImage("Shawario"));//Mario_Party_Online_Sprites-4.png
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(21).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(17 * creditsImage.get(1).getHeight()) + (17 *20)+ (10+64+64+64) + 100);
				creditsImage.add(HUD.stringToMario3FontImage("SPECIAL THANKS:"));
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(22).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(18 * creditsImage.get(1).getHeight()) + (18 *20)+ (10+64+64+64) + (2 *100));
				creditsImage.add(HUD.stringToMario3FontImage("Tyler Thomas"));
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(23).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(19 * creditsImage.get(1).getHeight()) + (19 *20)+ (10+64+64+64) + (2 *100));
				creditsImage.add(HUD.stringToMario3FontImage("Zack Berenger"));
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(24).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(20 * creditsImage.get(1).getHeight()) + (20 *20)+ (10+64+64+64) + (2 *100));
				creditsImage.add(HUD.stringToMario3FontImage("...AND"));
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(25).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(21 * creditsImage.get(1).getHeight()) + (21 *20)+ (10+64+64+64) + (2 *100));
				creditsImage.add(HUD.stringToMario3FontImage("Madi"));
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(26).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(22 * creditsImage.get(1).getHeight()) + (22 *20)+ (10+64+64+64) + (2 *100));
				creditsImage.add(HUD.stringToMario3FontImage("ALL PROPERTIES ARE"));
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(27).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(23 * creditsImage.get(1).getHeight()) + (23 *20)+ (10+(64*3)) + (3 *100));
				creditsImage.add(HUD.stringToMario3FontImage("OWNED BY NINTENDO"));
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(28).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(23 * creditsImage.get(1).getHeight()) + (23 *20)+ (10+(64*4)) + (3 *100));
				creditsImage.add(HUD.stringToMario3FontImage("AND ARE BEING USED"));
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(29).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(23 * creditsImage.get(1).getHeight()) + (23 *20)+ (10+(64*5)) + (3 *100));
				creditsImage.add(HUD.stringToMario3FontImage("UNDER FAIR USE AS"));
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(30).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) +
						(23 * creditsImage.get(1).getHeight()) + (23 *20)+ (10+(64*6)) + (3 *100));
				creditsImage.add(HUD.stringToMario3FontImage("OUTLINED IN TITLE"));
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(31).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(23 * creditsImage.get(1).getHeight()) + (23 *20)+ (10+(64*7)) + (3 *100));
				creditsImage.add(HUD.stringToMario3FontImage("17 OF THE U.S.C."));
				creditsImageX.add((double) Game.WIDTH - (creditsImage.get(32).getWidth()/2));
				creditsImageY.add((double) (Game.HEIGHT * Game.SCALE)+ (creditsImage.get(1).getHeight()/2) + 
						(23 * creditsImage.get(1).getHeight()) + (23 *20)+ (10+(64*8)) + (3 *100));
				nakedLaki = new NakedLakituCredits(tex,game,0);
				nakedLaki2 = new NakedLakituCredits(tex,game,1);
				cloudy = new CloudCredits(tex,game,0);
				cloudy2 = new CloudCredits(tex,game,1);
				spikey = new SpikeCrawler(tex,game);
				yoshi = new Yoshi(tex,game);
				marioBall = new MarioItemBallCredits(tex,game,0);
				marioBall2 = new MarioItemBallCredits(tex,game,1);
				resetTranslucenticity();
			}
			if(Game.creditsSong.getSoundLoopBoolean()) {
				if((127537/2) <= Game.creditsSong.getLongFramePosition() && Game.creditsSong.getLongFramePosition() < (175342/2)) {
					if(headBob1.getCount() != 7) {
						headBob1.runAnimation();
						headBob1.runAnimation();
					}
				}
				else if((175342/2) <= Game.creditsSong.getLongFramePosition() && Game.creditsSong.getLongFramePosition() < (207226/2)) {
					if(headBob2.getCount() != 7) {
						headBob2.runAnimation();
						headBob2.runAnimation();
					}
					if(headBob1.getCount() != 0)
						headBob1.setCount(0);
				}
				else if((207226/2) <= Game.creditsSong.getLongFramePosition() && Game.creditsSong.getLongFramePosition() < (255030/2)) {
					if(headBob1.getCount() != 7) {
						headBob1.runAnimation();
						headBob1.runAnimation();
					}
					if(headBob2.getCount() != 0)
						headBob2.setCount(0);
				}
				else if((382568/2) <= Game.creditsSong.getLongFramePosition() && Game.creditsSong.getLongFramePosition() < (430372/2)) {
					if(wormDance4.getCount() != 2) {
						wormDance4.runAnimation();
						wormDance4.runAnimation();
					}
				}else if((430372/2) <= Game.creditsSong.getLongFramePosition() && Game.creditsSong.getLongFramePosition() < (462256/2)) {
					if(wormDance5.getCount() != 2) {
						wormDance5.runAnimation();
						wormDance5.runAnimation();
					}
				}else if((462256/2) <= Game.creditsSong.getLongFramePosition() && Game.creditsSong.getLongFramePosition() < (470238/2)) {
					if(buttDance6.getCount() != 6) {
						buttDance6.runAnimation();
						buttDance6.runAnimation();
					}
				}else if(Game.creditsSong.getLongFramePosition() < (510061/2)) {
					buttDance7.runAnimation();
//					if(buttDance6.getSpeed() != 1)
//						buttDance6.setSpeed(1);
//					if(buttDance6.getCount() >= 5)
//						buttDance6.setCount(4);
//					else {
//						buttDance6.runAnimation();
//						buttDance6.runAnimation();
//						buttDance6.runAnimation();
//					}
				}
//				else if(470238 <= Game.creditsSong.getLongFramePosition() && Game.creditsSong.getLongFramePosition() < 478176) {
//					if(buttDance6.getCount() != 4) {
//						buttDance6.setCount(4);
//					}
//				}else if(478176 <= Game.creditsSong.getLongFramePosition() && Game.creditsSong.getLongFramePosition() < 486158) {
//					if(buttDance6.getCount() != 5) {
//						buttDance6.setCount(5);
//					}
//				}else if(486158 <= Game.creditsSong.getLongFramePosition() && Game.creditsSong.getLongFramePosition() < 510061) {
//					if(buttDance6.getCount() != 6) {
//						buttDance6.setCount(6);
//					}
//				}
			}
		}
		else {
			if(creditsPause < System.currentTimeMillis()) {
				if(50 < creditsImageY.get(27).intValue()) {
					for(int i = 0; i <= creditsImageY.size() - 1; i++) {
						if(yoshi.madiCaught && i == 26) {}
						else
							creditsImageY.set(i, creditsImageY.get(i)+velY);
					}
					for(int i = 0; i <= creditsImageY2.size() - 1; i++) {
						creditsImageY2.set(i, creditsImageY2.get(i)+velY);
					}
					nakedLaki.tick();
					nakedLaki2.tick();
					cloudy.tick();
					cloudy2.tick();
					spikey.tick();
					yoshi.tick();
					marioBall.tick();
					marioBall2.tick();
				}
				if(-70 < creditsImageY.get(12)) {
					if(creditsImage2.get(0).getCount() == 28 || creditsImage2.get(0).getCount() == 30 ) {
						Random rand = new Random();
						if(rand.nextInt(20) == 0)
							creditsImage2.get(0).runAnimation();
					}
					else
						creditsImage2.get(0).runAnimation();
					if(creditsImage2.get(1).getCount() == 3 || creditsImage2.get(1).getCount() == 17) {
						Random rand = new Random();
						if(rand.nextInt(20) == 0)
							creditsImage2.get(1).runAnimation();
					}
					else
						creditsImage2.get(1).runAnimation();
					creditsImage2.get(2).runAnimation();
					creditsImage2.get(3).runAnimation();
					if(creditsImageY.get(10) - 110 < 0) {
						if(creditsImage2.get(4).getCount() != 13)
							creditsImage2.get(4).runAnimation();
					}
					else if(creditsImage2.get(4).getCount() == 12) {
						creditsImage2.get(4).setCount(0);
					}else if(creditsImage2.get(4).getCount() == 1 || creditsImage2.get(4).getCount() == 7) {//standing
						Random rand = new Random();
						if(rand.nextInt(8) != 0)
							creditsImage2.get(4).runAnimation();
					}else if(creditsImage2.get(4).getCount() == 5 || creditsImage2.get(4).getCount() == 10) {//running
						Random rand = new Random();
						if(rand.nextInt(20) != 0)
							creditsImage2.get(4).runAnimation();
						else {
							if(creditsImage2.get(4).getCount() == 5)
								creditsImage2.get(4).setCount(1);
							else
								creditsImage2.get(4).setCount(6);
						}
					}else if(creditsImage2.get(4).getCount() == 6 || creditsImage2.get(4).getCount() == 11) {//slide
						Random rand = new Random();
						if(rand.nextInt(2) == 0)
							creditsImage2.get(4).runAnimation();
					}
					else
						creditsImage2.get(4).runAnimation();

					if(Game.HEIGHT * Game.SCALE < creditsImageY.get(10) + 55) {
						
					}
					else if(creditsImage2.get(5).getCount() == 11)
						creditsImage2.get(5).setCount(2);
					else
						creditsImage2.get(5).runAnimation();
					if(creditsImage2.get(6).getCount() == 7) {
						Random rand = new Random();
						if(rand.nextInt(22) == 0) {
							creditsImage2.get(6).setCount(8);
							creditsImage2.get(7).setCount(8);
						}
						else {
							creditsImage2.get(6).setCount(5);
							creditsImage2.get(7).setCount(5);
						}
					}
					else {
						creditsImage2.get(6).runAnimation();
						creditsImage2.get(7).runAnimation();
					}
//					if(creditsImage2.get(7).getCount() == 7) {
//						Random rand = new Random();
//						if(rand.nextInt(5) == 0)
//							creditsImage2.get(7).nextFrame();
//						else
//							creditsImage2.get(7).setCount(5);
//					}
//					else
//						creditsImage2.get(7).runAnimation();
					if(creditsImage2.get(8).getCount() == 2) {
						Random rand = new Random();
						if(rand.nextInt(5) == 0)
							creditsImage2.get(8).setCount(3);
						else
							creditsImage2.get(8).setCount(1);
					}
					else if(creditsImage2.get(8).getCount() == 11) {
						Random rand = new Random();
						if(rand.nextInt(10) == 0)
							creditsImage2.get(8).setCount(12);
						else
							creditsImage2.get(8).setCount(8);
					}
					else if(creditsImage2.get(8).getCount() == 15) {
						Random rand = new Random();
						if(rand.nextInt(8) == 0)
							creditsImage2.get(8).setCount(1);
						else
							creditsImage2.get(8).setCount(12);
					}
					else
						creditsImage2.get(8).runAnimation();
					
					if(Game.HEIGHT * Game.SCALE < creditsImageY.get(15) + 105) {
						if(creditsImage2.get(9).getCount() == 2) {
							Random rand = new Random();
							if(rand.nextInt(5) == 0)
								creditsImage2.get(9).setCount(0);
						}
						else
							creditsImage2.get(9).runAnimation();
					}
					else{
						if(creditsImage2.get(9).getCount() == 4) {
							Random rand = new Random();
							if(rand.nextInt(5) == 0) {
								creditsImage2.get(9).setCount(5);
							}
							else
								creditsImage2.get(9).setCount(3);
							if(creditsImage2.get(9).getCount() != 5)
								creditsImage2.get(9).runAnimation();
						}
						else if(creditsImage2.get(9).getCount() == 8)
							creditsImage2.get(9).setCount(5);
						else
							creditsImage2.get(9).runAnimation();
					}
				}
				else {
					if(creditsImage2.get(8).getCount() == 2) {
						Random rand = new Random();
						if(rand.nextInt(5) == 0)
							creditsImage2.get(8).setCount(3);
						else
							creditsImage2.get(8).setCount(1);
					}
					else if(creditsImage2.get(8).getCount() == 11) {
						Random rand = new Random();
						if(rand.nextInt(10) == 0)
							creditsImage2.get(8).setCount(12);
						else
							creditsImage2.get(8).setCount(8);
					}
					else if(creditsImage2.get(8).getCount() == 15) {
						Random rand = new Random();
						if(rand.nextInt(8) == 0)
							creditsImage2.get(8).setCount(1);
						else
							creditsImage2.get(8).setCount(12);
					}
					else
						creditsImage2.get(8).runAnimation();
					
					if(Game.HEIGHT * Game.SCALE < creditsImageY.get(15) + 105) {
						if(creditsImage2.get(9).getCount() == 2) {
							Random rand = new Random();
							if(rand.nextInt(5) == 0)
								creditsImage2.get(9).setCount(0);
						}
						else
							creditsImage2.get(9).runAnimation();
					}
					else{
						if(creditsImage2.get(9).getCount() == 4) {
							Random rand = new Random();
							if(rand.nextInt(5) == 0) {
								creditsImage2.get(9).setCount(5);
							}
							else
								creditsImage2.get(9).setCount(3);
							if(creditsImage2.get(9).getCount() != 5)
								creditsImage2.get(9).runAnimation();
						}
						else if(creditsImage2.get(9).getCount() == 8)
							creditsImage2.get(9).setCount(5);
						else
							creditsImage2.get(9).runAnimation();
					}

					if(!(50 < creditsImageY.get(27).intValue())) {
						nakedLaki.tick();
						nakedLaki2.tick();
						cloudy.tick();
						cloudy2.tick();
						spikey.tick();
						yoshi.tick();
						marioBall.tick();
						marioBall2.tick();
					}
					if(starExplosionTimer != 0 && this.starExplosionTimer-1250 < System.currentTimeMillis() && fireworksShot == 1 && smallFireworksShot == 0) {
						Random rand = new Random();
						int xAdd = 0;
						starExplosionX = (Game.WIDTH/2)-90;
						xAdd = rand.nextInt(40);
						if(rand.nextBoolean() == true)
							xAdd *= -1;
						starExplosionX += xAdd;
						int yAdd = 0;
						starExplosionY = (Game.HEIGHT/2)+40;
						yAdd = rand.nextInt(20);
						if(rand.nextBoolean() == true)
							yAdd *= -1;
						starExplosionY += yAdd;
						smallFireworksShot = 1;
						starExplode = true;
						smallExplode = true;
					}
					if(starExplosionTimer != 0 && this.starExplosionTimer-1222 < System.currentTimeMillis() && fireworksShot == 1 && smallFireworksShot == 1) {
						Random rand = new Random();
						int xAdd = 0;
						starExplosionX = (Game.WIDTH + (Game.WIDTH/2))+90;
						xAdd = rand.nextInt(40);
						if(rand.nextBoolean() == true)
							xAdd *= -1;
						starExplosionX += xAdd;
						int yAdd = 0;
						starExplosionY = (Game.HEIGHT/2)+40;
						yAdd = rand.nextInt(20);
						if(rand.nextBoolean() == true)
							yAdd *= -1;
						starExplosionY += yAdd;
						smallFireworksShot = 2;
						starExplode = true;
						smallExplode = true;
					}
					if(starExplosionTimer != 0 && this.starExplosionTimer-1250 < System.currentTimeMillis() && fireworksShot == 2 && smallFireworksShot == 2) {
						Random rand = new Random();
						int xAdd = 0;
						starExplosionX = (Game.WIDTH + (Game.WIDTH/2))+90;
						xAdd = rand.nextInt(40);
						if(rand.nextBoolean() == true)
							xAdd *= -1;
						starExplosionX += xAdd;
						int yAdd = 0;
						starExplosionY = (Game.HEIGHT/2)+40;
						yAdd = rand.nextInt(20);
						if(rand.nextBoolean() == true)
							yAdd *= -1;
						starExplosionY += yAdd;
						smallFireworksShot = 3;
						starExplode = true;
						smallExplode = true;
					}
					if(starExplosionTimer != 0 && this.starExplosionTimer-1222 < System.currentTimeMillis() && fireworksShot == 2 && smallFireworksShot == 3) {
						Random rand = new Random();
						int xAdd = 0;
						starExplosionX = (Game.WIDTH/2)-90;
						xAdd = rand.nextInt(40);
						if(rand.nextBoolean() == true)
							xAdd *= -1;
						starExplosionX += xAdd;
						int yAdd = 0;
						starExplosionY = (Game.HEIGHT/2)+40;
						yAdd = rand.nextInt(20);
						if(rand.nextBoolean() == true)
							yAdd *= -1;
						starExplosionY += yAdd;
						smallFireworksShot = 4;
						starExplode = true;
						smallExplode = true;
					}
					
					if(fireworksShot == 2 && Game.HEIGHT/2 >= creditsImageY.get(24).intValue()+creditsImage.get(24).getHeight()/2) {
						Random rand = new Random();
						int xAdd = 0;
						starExplosionX = Game.WIDTH/2;
						xAdd = rand.nextInt(40);
						if(rand.nextBoolean() == true)
							xAdd *= -1;
						starExplosionX += xAdd;
						int xAdd2 = 0;
						starExplosionX2 = (Game.WIDTH + (Game.WIDTH/2));
						xAdd2 = rand.nextInt(40);
						if(rand.nextBoolean() == true)
							xAdd2 *= -1;
						starExplosionX2 += xAdd2;
						int yAdd = 0;
						int yAdd2 = 0;
						starExplosionY = Game.HEIGHT/2;
						starExplosionY2 = Game.HEIGHT/2;
						yAdd = rand.nextInt(40);
						yAdd2 = rand.nextInt(40);
						if(rand.nextBoolean() == true)
							yAdd *= -1;
						if(rand.nextBoolean() == true)
							yAdd2 *= -1;
						starExplosionY += yAdd;
						starExplosionY2 += yAdd2;
						if(creditsImageX.get(24)-20 < starExplosionX)
							starExplosionX = (int)(creditsImageX.get(24)-20);
						if(starExplosionX2 < creditsImageX.get(24)+creditsImage.get(24).getWidth()+20)
							starExplosionX2 = (int)(creditsImageX.get(24)+creditsImage.get(24).getWidth()+20);
						fireworksShot = 3;
						smallExplode = false;
						starExplode = true;
					}
					if(fireworksShot == 1 && Game.HEIGHT/2 >= creditsImageY.get(23).intValue()+creditsImage.get(23).getHeight()/2) {
						Random rand = new Random();
						int xAdd = 0;
						starExplosionX = Game.WIDTH/2;
						xAdd = rand.nextInt(40);
						if(rand.nextBoolean() == true)
							xAdd *= -1;
						starExplosionX += xAdd;
						int xAdd2 = 0;
						starExplosionX2 = (Game.WIDTH + (Game.WIDTH/2));
						xAdd2 = rand.nextInt(40);
						if(rand.nextBoolean() == true)
							xAdd2 *= -1;
						starExplosionX2 += xAdd2;
						int yAdd = 0;
						int yAdd2 = 0;
						starExplosionY = Game.HEIGHT/2;
						starExplosionY2 = Game.HEIGHT/2;
						yAdd = rand.nextInt(40);
						yAdd2 = rand.nextInt(40);
						if(rand.nextBoolean() == true)
							yAdd *= -1;
						if(rand.nextBoolean() == true)
							yAdd2 *= -1;
						starExplosionY += yAdd;
						starExplosionY2 += yAdd2;
						if(creditsImageX.get(23)-20 < starExplosionX)
							starExplosionX = (int)(creditsImageX.get(23)-20);
						if(starExplosionX2 < creditsImageX.get(23)+creditsImage.get(23).getWidth()+20)
							starExplosionX2 = (int)(creditsImageX.get(23)+creditsImage.get(23).getWidth()+20);
						fireworksShot = 2;
						smallExplode = false;
						starExplode = true;
					}
					if(Game.HEIGHT/2 >= creditsImageY.get(22).intValue()+creditsImage.get(22).getHeight()/2 && fireworksShot == 0) {
						Random rand = new Random();
						int xAdd = 0;
						starExplosionX = Game.WIDTH/2;
						xAdd = rand.nextInt(40);
						if(rand.nextBoolean() == true)
							xAdd *= -1;
						starExplosionX += xAdd;
						int xAdd2 = 0;
						starExplosionX2 = (Game.WIDTH + (Game.WIDTH/2));
						xAdd2 = rand.nextInt(40);
						if(rand.nextBoolean() == true)
							xAdd2 *= -1;
						starExplosionX2 += xAdd2;
						int yAdd = 0;
						int yAdd2 = 0;
						starExplosionY = Game.HEIGHT/2;
						starExplosionY2 = Game.HEIGHT/2;
						yAdd = rand.nextInt(40);
						yAdd2 = rand.nextInt(40);
						if(rand.nextBoolean() == true)
							yAdd *= -1;
						if(rand.nextBoolean() == true)
							yAdd2 *= -1;
						starExplosionY += yAdd;
						starExplosionY2 += yAdd2;
						if(creditsImageX.get(22)-20 < starExplosionX)
							starExplosionX = (int)(creditsImageX.get(22)-20);
						if(starExplosionX2 < creditsImageX.get(22)+creditsImage.get(22).getWidth()+20)
							starExplosionX2 = (int)(creditsImageX.get(22)+creditsImage.get(22).getWidth()+20);
						fireworksShot = 1;
						smallExplode = false;
						starExplode = true;
					}
					
				}
			}
			if ((1340061/2) <= Game.creditsSong.getLongFramePosition() && marioRunningX < (Game.WIDTH * Game.SCALE) + 50) {
				marioRunningX+=marioRunningVelX;
				shellX+=shellVelX;
				marioRunning.runAnimation();
				shell.runAnimation();
			}
			else if((2340061/2) <= Game.creditsSong.getLongFramePosition() && 0 < marioWormingX +420) {
				marioWormingX-=3;
				marioWorming.runAnimation();
			}
			else if((3750061/2) <= Game.creditsSong.getLongFramePosition() && marioMoonWalkingX < (Game.WIDTH * Game.SCALE) + 150) {
				if(marioMoonWalking.getCount() != this.marioMoonWalkCounter && marioMoonWalking.getCount() % 2 == 0) {
					marioMoonWalkingVelX=(5/2);
					marioMoonWalkCounter = marioMoonWalking.getCount();
				}
				marioMoonWalkingX+=marioMoonWalkingVelX;
				marioMoonWalkingVelX-=(0.12/2);
//				marioMoonWalkingX+=1;
				if(marioMoonWalking.getCount() == 5)
					marioMoonWalkingX -= (0.11);
				if(marioMoonWalking.getCount() == 2 || marioMoonWalking.getCount() == 4) {
					if(marioMoonWalkCounter2 == 1) {
						marioMoonWalkCounter2 = 0;
						marioMoonWalking.runAnimation();
					}
					else
						marioMoonWalkCounter2++;
				}
				else
					marioMoonWalking.runAnimation();
			}
			
			else if((5531110/2) <= Game.creditsSong.getLongFramePosition() && imageTranslucent != 1) {
				imageTranslucent+=0.0021;
				//fadeout
			}
		}
//		if(imageTranslucent >= 0.99 && imageTranslucentTimer < System.currentTimeMillis()){
//			imageTranslucentVelocity += -0.01;
//			imageIsGone = false;
//		}
//		else if(imageTranslucent <= 0.01 && imageTranslucentTimer < System.currentTimeMillis()){
//			imageTranslucentVelocity += 0.01;
//		}
//		
//		if(imageTranslucent <= 0.01 && imageIsGone == false){
//			imageTranslucentTimer = System.currentTimeMillis() + 500;
//			imageIsGone = true;
//		}
//		if(imageTranslucent + imageTranslucentVelocity <= 1 &&
//				imageTranslucent + imageTranslucentVelocity >= 0)
//			imageTranslucent += imageTranslucentVelocity;
		if(0 < p.getY() + 40)
			p.tick();
		if(System.currentTimeMillis() < starExplosionTimer)
			starExplosion.tick();
	}
	private void resetTranslucenticity() {
		imageIsGone = true;
		imageTranslucentVelocity = 0;
		imageTranslucent = 0;
		imageTranslucentTimer = 0;
	}
	private void fader(boolean fade) {
		if(fade) {
			if(imageIsGone) {
				if(imageTranslucent >= 0.99 && imageTranslucentTimer < System.currentTimeMillis()){
					imageTranslucentVelocity = 0;//+= -0.01;
					imageIsGone = false;
				}
				else if(imageTranslucent <= 0.01 && imageTranslucentTimer < System.currentTimeMillis()){
					imageTranslucentVelocity += 0.01;
				}
				if(imageTranslucent + imageTranslucentVelocity <= 1 &&
						imageTranslucent + imageTranslucentVelocity >= 0)
					imageTranslucent += imageTranslucentVelocity;
			}
			else
				fade = false;
		}
	}
	private AlphaComposite makeComposite(float alpha) {
		  int type = AlphaComposite.SRC_OVER;
		  return(AlphaComposite.getInstance(type, alpha));
	}
	public static BufferedImage resizeSmooth(BufferedImage img, int newW, int newH) {
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
	    return dimg;
	}
	public void render(Graphics g) {
		if(creditsSetup && 0 < p.getY() + 40 )
			p.render(g);
		if(starExplode) {
			if(smallExplode)
				starExplosion.StarExplosionSmallSetup(starExplosionX,starExplosionY);//MODIFY
			else
				starExplosion.StarExplosionSetup(starExplosionX,starExplosionY,starExplosionX2,starExplosionY2);//MODIFY
			starExplosionTimer = System.currentTimeMillis() + 2000;
			starExplode = false;
			if(smallExplode)
				smallExplode = false;
			//starExplode = false;
		}
		if(System.currentTimeMillis() < starExplosionTimer) 
			starExplosion.Explosion(g);
		if(Game.creditsSong.getSoundLoopBoolean()) {
			if(Game.creditsSong.getLongFramePosition() < (127537/2))
				openingDance.drawAnimation(g, Game.WIDTH -(openingDance.getCurrentImage().getWidth()/2) , (Game.HEIGHT * Game.SCALE) - 280, 0);
			else if((Game.creditsSong.getLongFramePosition() < (175342/2)||((207226/2) <= Game.creditsSong.getLongFramePosition() && Game.creditsSong.getLongFramePosition() < (255030/2)))) {
				int x = 0;
				if(headBob1.getCount() == 3)
					x = 1;
				else if(headBob1.getCount() == 4)
					x = 2;
				else if(headBob1.getCount() == 5)
					x = 5;
				else if(headBob1.getCount() == 6)
					x = 11;
				else if(headBob1.getCount() == 7)
					x = 8;
				headBob1.drawAnimation(g, Game.WIDTH - (headBob1.getCurrentImage().getWidth()/2)-x, (Game.HEIGHT * Game.SCALE) - 280, 0);
			}
			else if(Game.creditsSong.getLongFramePosition() < (207226/2)) {
				int x = 0;
				if(headBob2.getCount() == 3)
					x = 1;
				else if(headBob2.getCount() == 4)
					x = 2;
				else if(headBob2.getCount() == 5)
					x = 5;
				else if(headBob2.getCount() == 6)
					x = 11;
				else if(headBob2.getCount() == 7)
					x = 8;
				headBob2.drawAnimation(g, Game.WIDTH - (headBob2.getCurrentImage().getWidth()/2)+x, (Game.HEIGHT * Game.SCALE) - 280, 0);
			}
			else if(Game.creditsSong.getLongFramePosition() < (382568/2)) {
				dance3.drawAnimation(g, Game.WIDTH -(dance3.getCurrentImage().getWidth()/2) , (Game.HEIGHT * Game.SCALE) - 280, 0);
			}
			else if(Game.creditsSong.getLongFramePosition() < (430372/2)) {
				int x = 0;
				int y = 0;
				if(wormDance4.getCount() == 2) {
					x = 4;
					y = 4;
				}
				if(wormDance4.getCount() == 3) {
					x = 16;
					y = 12;
				}
				else if(wormDance4.getCount() == 4) {
					x = 36;
					y = 44;
				}
				else if(wormDance4.getCount() == 5) {
					x = 36;
					y = 38;
				}
				else if(wormDance4.getCount() == 6) {
					x = 24;
					y = 47;
				}
				else if(wormDance4.getCount() == 7) {
					x = 20;
					y = 43;
				}
				wormDance4.drawAnimation(g, Game.WIDTH -(wormDance4.getCurrentImage().getWidth()/2)-x , (Game.HEIGHT * Game.SCALE) - 280 + y, 0);
			}else if(Game.creditsSong.getLongFramePosition() < (462256/2)) {
				int x = 0;
				int y = 0;
				if(wormDance5.getCount() == 2) {
					x = 4;
					y = 4;
				}
				if(wormDance5.getCount() == 3) {
					x = 16;
					y = 12;
				}
				wormDance5.drawAnimation(g, Game.WIDTH -(wormDance5.getCurrentImage().getWidth()/2)-x , (Game.HEIGHT * Game.SCALE) - 280 + y, 0);
			}else if(Game.creditsSong.getLongFramePosition() < (470238/2)) {
				buttDance6.drawAnimation(g, Game.WIDTH -(buttDance6.getCurrentImage().getWidth()/2), (Game.HEIGHT * Game.SCALE) - 280, 0);
			}
			else if(Game.creditsSong.getLongFramePosition() < (510061/2)) {
				int y = 0;
				if((470238/2) <= Game.creditsSong.getLongFramePosition() && Game.creditsSong.getLongFramePosition() < (478176/2))
					y = -50;
				else if((478176/2) <= Game.creditsSong.getLongFramePosition() && Game.creditsSong.getLongFramePosition() < (486158/2))
					y = -70;
				else if((486158/2) <= Game.creditsSong.getLongFramePosition() && Game.creditsSong.getLongFramePosition() < (510061/2))
					y = -100;
				buttDance7.drawAnimation(g, Game.WIDTH -(buttDance7.getCurrentImage().getWidth()/2) , (Game.HEIGHT * Game.SCALE) - 280 + y, 0);
			}else if((1340061/2) <= Game.creditsSong.getLongFramePosition() && marioRunningX < (Game.WIDTH * Game.SCALE) + 50) {
				marioRunning.drawAnimation(g, marioRunningX, 250-marioRunning.getCurrentImage().getHeight(), 0);
				shell.drawAnimation(g, shellX, 250-shell.getCurrentImage().getHeight(), 0);
			}else if((2340061/2) <= Game.creditsSong.getLongFramePosition() && 0 < marioWormingX +420) {
				int y = 0;
				if(marioWorming.getCount() == 6)
					y = 20;
				marioWorming.drawAnimation(g, marioWormingX, 350-marioWorming.getCurrentImage().getHeight()+y, 0);
			}else if((3750061/2) <= Game.creditsSong.getLongFramePosition() && marioMoonWalkingX < (Game.WIDTH * Game.SCALE) + 50) {
				marioMoonWalking.drawAnimation(g, marioMoonWalkingX, 350-marioMoonWalking.getCurrentImage().getHeight(), 0);
			}
		}
		if(!creditsImage.isEmpty()) {
			for(int i = 0; i <= creditsImage.size() - 1; i++) {
				if(-128 < creditsImageY.get(i).intValue() && creditsImageY.get(i).intValue() < (Game.HEIGHT * Game.SCALE) + 128 &&
						(!(i==26 && yoshi.madiEaten == true)))
					g.drawImage(creditsImage.get(i),creditsImageX.get(i).intValue(),creditsImageY.get(i).intValue(),null);
				if(i <= creditsImage2.size()-1)
					creditsImage2.get(i).drawAnimation(g,creditsImageX2.get(i).intValue(), creditsImageY2.get(i).intValue(), 0);
			}
			nakedLaki.render(g);
			nakedLaki2.render(g);
			cloudy.render(g);
			cloudy2.render(g);
			spikey.render(g);
			yoshi.render(g);
			if(yoshi.madiCaught && !yoshi.madiEaten)
				g.drawImage(creditsImage.get(26),creditsImageX.get(26).intValue(),creditsImageY.get(26).intValue(),null);
			marioBall.render(g);
			marioBall2.render(g);
		}
		Graphics2D g2d = (Graphics2D)g.create();
		if(imageTranslucent != 1 && 0.96 < imageTranslucent)
			imageTranslucent = 1;
//		System.out.println(imageTranslucent);
		g2d.setComposite(makeComposite(imageTranslucent));
		//g2d.drawImage(pressE,Game.WIDTH-10,28,null);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, (Game.WIDTH * Game.SCALE) + 40, (Game.HEIGHT * Game.SCALE) + 40);
	}
	public void reset() {
		seeYouNextTime.stop();
		seeYouNextTime.setFramePosition(0);
		seeYouNextTime.setSoundLoopBoolean(false);
		spinSFX.stop();
		spinSFX.setFramePosition(0);
		spinSFX.setSoundLoopBoolean(false);
		seeYouNextTime.close();
		spinSFX.close();
		Game.creditsSong.stop();
		Game.creditsSong.setFramePosition(0);
		Game.creditsSong.setSoundLoopBoolean(false);
		Game.starExplode = false;
		endPause = 0;
		seeYouNextTimePause = 0;
		songPause = 0;
		creditsPause = 0;
		creditsSetup = false;
		p = null;
		cloudy = null;
		cloudy2 = null;
		marioBall = null;
		marioBall2 = null;
		nakedLaki = null;
		nakedLaki2 = null;
		spikey = null;
		yoshi = null;
		for(int i = 0; i <= creditsImage.size()-1; i++) {
			creditsImage.get(i).flush(); 
			creditsImage.set(i, null);
			creditsImage.remove(i);
			i--;
		}
		for(int i = 0; i <= creditsImage2.size()-1; i++) {
			creditsImage2.get(i).flush(); 
			creditsImage2.set(i, null);
			creditsImage2.remove(i);
			i--;
		}
		creditsImage.clear();
		creditsImage2.clear();
		creditsImageX.clear();
		creditsImageY.clear();
		creditsImageX2.clear();
		creditsImageY2.clear();
		imageTranslucent = 0;
		starExplosionTimer = 0;
		seeYouNextTimePause = 0;
		creditsPause = 0;
		songPause = 0;
		endPause = 0;
		imageTranslucentTimer = 0;
		velY = 0;
		marioWormingX = (Game.WIDTH*Game.SCALE)+120;
		marioRunningX = -150;
		marioMoonWalkingX = -150;
		shellX = -80;
		marioRunningVelX = 5;
		marioMoonWalkingVelX = 5;
		shellVelX = 6;
		imageTranslucentVelocity = 0;
		starExplosionX = 0;
		starExplosionY = 0;
		starExplosionX2 = 0;
		starExplosionY2 = 0;
		fireworksShot = 0;
		smallFireworksShot = 0;
		marioMoonWalkCounter = 0;
		marioMoonWalkCounter2 = 0;
		starExplode = false;
		smallExplode = false;
		imageIsGone = true;
		fadeSequence = false;
		firstFadeOut = false;
		fadeIn = false;
		fadeIn2 = false;
		fadeIn3 = false;
		fadeIn4 = false;
		fadeIn5 = false;
		fadeIn6 = false;
		fadeIn7 = false;
		fadeIn8 = false;
		fadeIn9 = false;
		fadeIn10 = false;
		fadeIn11 = false;
		fadeIn12 = false;
		fadeIn13 = false;
		fadeIn14 = false;
		fadeIn15 = false;
		fadeIn16 = false;
		fadeInBoolean = false;
		fadeInBoolean2 = false;
		fadeInBoolean3 = false;
		fadeInBoolean4 = false;
		fadeInBoolean5 = false;
		fadeInBoolean6 = false;
		fadeInBoolean7 = false;
		fadeInBoolean8 = false;
		fadeInBoolean9 = false;
		fadeInBoolean10 = false;
		fadeInBoolean11 = false;
		fadeInBoolean12 = false;
		fadeInBoolean13 = false;
		fadeInBoolean14 = false;
		fadeInBoolean15 = false;
		fadeInBoolean16 = false;
		creditsSetup = false;
	}
}
