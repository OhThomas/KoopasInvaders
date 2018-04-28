/*
 * 
 */
package com.game.src.main;

import java.awt.image.BufferedImage;

// TODO: Auto-generated Javadoc
/**
 * The Class Textures.
 */
public class Textures {

	/** The player. */
	public BufferedImage[] player = new BufferedImage[16];
	
	/** The mario star 1. */
	public BufferedImage[] marioStar1 = new BufferedImage[16];
	
	/** The mario star 2. */
	public BufferedImage[] marioStar2 = new BufferedImage[16];
	
	/** The mario star 3. */
	public BufferedImage[] marioStar3 = new BufferedImage[16];
	
	/** The mario slowing down. */
	public BufferedImage[] marioSlowingDown = new BufferedImage[2];
	
	/** The fireball. */
	public BufferedImage[] fireball = new BufferedImage[4];
	
	/** The enemy. */
	public BufferedImage[] enemy = new BufferedImage[2];
	
	/** The enemy 2. */
	public BufferedImage[] enemy2 = new BufferedImage[2];
	
	/** The enemy 3. */
	public BufferedImage[] enemy3 = new BufferedImage[2];
	
	/** The green shell. */
	public BufferedImage[] greenShell = new BufferedImage[4];
	
	/** The animated star. */
	public BufferedImage[] animatedStar = new BufferedImage[20];
	
	/** The animated shooting star. */
	public BufferedImage[] animatedShootingStar = new BufferedImage[12];
	
	/** The mario 1 star. */
	public BufferedImage[] mario1Star = new BufferedImage[4];
	
	/** The mario item animation beginning. */
	public BufferedImage[] marioItemAnimationBeginning = new BufferedImage[18];
	
	/** The big chain chomp item. */
	public BufferedImage[] bigChainChompItem = new BufferedImage[9];
	
	/** The chain chomp. */
	public BufferedImage[] chainChomp = new BufferedImage[2];
	
	/** The chain chomp item. */
	public BufferedImage[] chainChompItem = new BufferedImage[25];
	
	/** The bowser entrance. */
	public BufferedImage[] bowserEntrance = new BufferedImage[8];
	
	/** The bowser. */
	public BufferedImage[] bowser = new BufferedImage[2];
	
	/** The bowser hit. */
	public BufferedImage[] bowserHit = new BufferedImage[2];
	
	/** The bowser ship. */
	public BufferedImage[] bowserShip = new BufferedImage[8];
	
	/** The bowser ship hit. */
	public BufferedImage[] bowserShipHit = new BufferedImage[4];
	
	/** The bullet bill D. */
	public BufferedImage[] bulletBillD = new BufferedImage[17];
	
	/** The bullet bill DL. */
	public BufferedImage[] bulletBillDL = new BufferedImage[10];
	
	/** The bullet bill DR. */
	public BufferedImage[] bulletBillDR = new BufferedImage[10];
	
	/** The bullet bill explosion. */
	public BufferedImage[] bulletBillExplosion = new BufferedImage[7];
	
	/** The enemy explosion. */
	public BufferedImage[] enemyExplosion = new BufferedImage[3];
	
	/** The enemy 2 death L. */
	public BufferedImage[] enemy2DeathL = new BufferedImage[10];
	
	/** The enemy 2 death R. */
	public BufferedImage[] enemy2DeathR = new BufferedImage[10];
	
	/** The enemy 3 death. */
	public BufferedImage[] enemy3Death = new BufferedImage[3];
	
	/** The enemy 3 explosion. */
	public BufferedImage[] enemy3Explosion = new BufferedImage[13];
	
	/** The star. */
	public BufferedImage[] star = new BufferedImage[20];
	
	/** The coin. */
	public BufferedImage[] coin = new BufferedImage[3];
	
	/** The mario entrance. */
	public BufferedImage[] marioEntrance = new BufferedImage[21];
	
	/** The mario death. */
	public BufferedImage[] marioDeath = new BufferedImage[2];
	
	/** The transparent blocks. */
	public BufferedImage[] transparentBlocks = new BufferedImage[3];
	
	/** The mario numbers small. */
	public BufferedImage[] marioNumbersSmall = new BufferedImage[12];
	
	/** The mario 3 font numbers small. */
	public BufferedImage[] mario3FontNumbersSmall = new BufferedImage[11];
	
	/** The item frame. */
	public BufferedImage itemFrame;
	
	/** The chain chomp item frame display. */
	public BufferedImage chainChompItemFrameDisplay;
	
	/** The press E. */
	public BufferedImage pressE;
	
	/** The gameover. */
	public BufferedImage gameover;
	
	/** The Constant MARIO_WIDTH. */
	public static final int MARIO_WIDTH = 16;
	
	/** The Constant MARIO_HEIGHT. */
	public static final int MARIO_HEIGHT = 28;
	
	/** The Constant BOWSER_WIDTH. */
	public static final int BOWSER_WIDTH = 64;
	
	/** The Constant BOWSER_HEIGHT. */
	public static final int BOWSER_HEIGHT = 41;
	
	/** The Constant BOWSER_HIT_WIDTH. */
	public static final int BOWSER_HIT_WIDTH = 64;
	
	/** The Constant BOWSER_HIT_HEIGHT. */
	public static final int BOWSER_HIT_HEIGHT = 50;
	
	/** The Constant BOWSER_SHIP_WIDTH. */
	public static final int BOWSER_SHIP_WIDTH = 64;
	
	/** The Constant BOWSER_SHIP_HEIGHT. */
	public static final int BOWSER_SHIP_HEIGHT = 55;
	
	/** The Constant SHOOTING_STAR_WIDTH. */
	public static final int SHOOTING_STAR_WIDTH = 399;
	
	/** The Constant SHOOTING_STAR_HEIGHT. */
	public static final int SHOOTING_STAR_HEIGHT = 150;
	
	/** The ss. */
	private SpriteSheet ss;
	
	/** The star sprites. */
	private SpriteSheet starSprites;
	
	/** The shooting star sprites. */
	private SpriteSheet shootingStarSprites;
	
	/** The mario 1 star sprites. */
	private SpriteSheet mario1StarSprites;
	
	/** The mario items sprites. */
	private SpriteSheet marioItemsSprites;
	
	/** The bowser sprites. */
	private SpriteSheet bowserSprites;
	
	/** The bullet bill sprites. */
	private SpriteSheet bulletBillSprites;
	
	/** The mario player animations sprites. */
	private SpriteSheet marioPlayerAnimationsSprites;
	
	/** The mario item animation sprites. */
	private SpriteSheet marioItemAnimationSprites;
	
	/** The big mario item animation sprites. */
	private SpriteSheet bigMarioItemAnimationSprites;
	
	/** The chain chomp item getting bigger sprites. */
	private SpriteSheet chainChompItemGettingBiggerSprites;
	
	/** The chain chomp sprites. */
	private SpriteSheet chainChompSprites;
	
	/** The mario slowing down sprites. */
	private SpriteSheet marioSlowingDownSprites;
	
	/** The full mario sprites. */
	private SpriteSheet fullMarioSprites;
	
	/** The mario advance sprites. */
	private SpriteSheet marioAdvanceSprites;
	
	/** The mario 3 font numbers small sprites. */
	private SpriteSheet mario3FontNumbersSmallSprites;
	
	/** The goomba death sprites. */
	private SpriteSheet goombaDeathSprites;
	
	/** The transparent blocks sprites. */
	private SpriteSheet transparentBlocksSprites;
	
	/**
	 * Instantiates a new textures.
	 *
	 * @param game the game
	 */
	public Textures(Game game){
		ss = new SpriteSheet(game.getSpriteSheet());
		starSprites = new SpriteSheet(game.getAnimatedStar());
		shootingStarSprites = new SpriteSheet(game.getAnimatedShootingStar());
		mario1StarSprites = new SpriteSheet(game.getMario1StarSpriteSheet());
		marioItemsSprites = new SpriteSheet(game.getMarioItemsSpriteSheet());
		bowserSprites = new SpriteSheet(game.getBowserSpriteSheet());
		bulletBillSprites = new SpriteSheet(game.getBulletBillSpriteSheet());
		marioPlayerAnimationsSprites = new SpriteSheet(game.getMarioPlayerStarAnimations());
		marioItemAnimationSprites = new SpriteSheet(game.getMarioItemAnimationSheet());
		bigMarioItemAnimationSprites = new SpriteSheet(game.getBigMarioItemAnimationSheet());
		chainChompItemGettingBiggerSprites = new SpriteSheet(game.getChainChompItemGettingBiggerSheet());
		chainChompSprites = new SpriteSheet(game.getChainChompSheet());
		marioSlowingDownSprites = new SpriteSheet(game.getMarioSlowingDownSprites());
		fullMarioSprites = new SpriteSheet(game.getFullMarioSpriteSheet());
		marioAdvanceSprites = new SpriteSheet(game.getMarioAdvanceSpriteSheet());
		mario3FontNumbersSmallSprites = new SpriteSheet(game.getMario3FontNumbersSmallSpriteSheet());
		goombaDeathSprites = new SpriteSheet(game.getGoombaDeathSpriteSheet());
		transparentBlocksSprites = new SpriteSheet(game.getTransparentBlocks());
		
		getTextures();
	}
	
	/**
	 * Gets the textures.
	 *
	 * @return the textures
	 */
	private void getTextures(){
		player[0] = ss.grabMarioImage(1, 1, MARIO_WIDTH, MARIO_HEIGHT);
		player[1] = ss.grabMarioImage(2, 1, MARIO_WIDTH, MARIO_HEIGHT);
		player[2] = ss.grabMarioImage(3, 1, MARIO_WIDTH, MARIO_HEIGHT);
		player[3] = ss.grabMarioImage(4, 1, MARIO_WIDTH, MARIO_HEIGHT);
		
		player[4] = ss.grabMarioImage(1, 2, MARIO_WIDTH, MARIO_HEIGHT);
		player[5] = ss.grabMarioImage(2, 2, MARIO_WIDTH, MARIO_HEIGHT);
		player[6] = ss.grabMarioImage(3, 2, MARIO_WIDTH, MARIO_HEIGHT);
		player[7] = ss.grabMarioImage(4, 2, MARIO_WIDTH, MARIO_HEIGHT);
		
		player[8] = ss.grabMarioImage(1, 4, MARIO_WIDTH, MARIO_HEIGHT);
		player[9] = ss.grabMarioImage(2, 4, MARIO_WIDTH, MARIO_HEIGHT);
		player[10] = ss.grabMarioImage(3, 4, MARIO_WIDTH, MARIO_HEIGHT);
		player[11] = ss.grabMarioImage(4, 4, MARIO_WIDTH, MARIO_HEIGHT);
		
		player[12] = ss.grabMarioImage(1, 3, MARIO_WIDTH, MARIO_HEIGHT);
		player[13] = ss.grabMarioImage(2, 3, MARIO_WIDTH, MARIO_HEIGHT);
		player[14] = ss.grabMarioImage(3, 3, MARIO_WIDTH, MARIO_HEIGHT);
		player[15] = ss.grabMarioImage(4, 3, MARIO_WIDTH, MARIO_HEIGHT);
		
		marioStar1[0] = marioPlayerAnimationsSprites.grabMarioImage(1, 1, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar1[1] = marioPlayerAnimationsSprites.grabMarioImage(2, 1, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar1[2] = marioPlayerAnimationsSprites.grabMarioImage(3, 1, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar1[3] = marioPlayerAnimationsSprites.grabMarioImage(4, 1, MARIO_WIDTH, MARIO_HEIGHT);
		
		marioStar1[4] = marioPlayerAnimationsSprites.grabMarioImage(1, 2, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar1[5] = marioPlayerAnimationsSprites.grabMarioImage(2, 2, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar1[6] = marioPlayerAnimationsSprites.grabMarioImage(3, 2, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar1[7] = marioPlayerAnimationsSprites.grabMarioImage(4, 2, MARIO_WIDTH, MARIO_HEIGHT);
		
		marioStar1[8] = marioPlayerAnimationsSprites.grabMarioImage(1, 4, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar1[9] = marioPlayerAnimationsSprites.grabMarioImage(2, 4, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar1[10] = marioPlayerAnimationsSprites.grabMarioImage(3, 4, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar1[11] = marioPlayerAnimationsSprites.grabMarioImage(4, 4, MARIO_WIDTH, MARIO_HEIGHT);
		
		marioStar1[12] = marioPlayerAnimationsSprites.grabMarioImage(1, 3, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar1[13] = marioPlayerAnimationsSprites.grabMarioImage(2, 3, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar1[14] = marioPlayerAnimationsSprites.grabMarioImage(3, 3, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar1[15] = marioPlayerAnimationsSprites.grabMarioImage(4, 3, MARIO_WIDTH, MARIO_HEIGHT);
		
		marioStar2[0] = marioPlayerAnimationsSprites.grabMarioImage(5, 1, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar2[1] = marioPlayerAnimationsSprites.grabMarioImage(6, 1, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar2[2] = marioPlayerAnimationsSprites.grabMarioImage(7, 1, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar2[3] = marioPlayerAnimationsSprites.grabMarioImage(8, 1, MARIO_WIDTH, MARIO_HEIGHT);
		
		marioStar2[4] = marioPlayerAnimationsSprites.grabMarioImage(5, 2, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar2[5] = marioPlayerAnimationsSprites.grabMarioImage(6, 2, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar2[6] = marioPlayerAnimationsSprites.grabMarioImage(7, 2, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar2[7] = marioPlayerAnimationsSprites.grabMarioImage(8, 2, MARIO_WIDTH, MARIO_HEIGHT);
		
		marioStar2[8] = marioPlayerAnimationsSprites.grabMarioImage(5, 4, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar2[9] = marioPlayerAnimationsSprites.grabMarioImage(6, 4, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar2[10] = marioPlayerAnimationsSprites.grabMarioImage(7, 4, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar2[11] = marioPlayerAnimationsSprites.grabMarioImage(8, 4, MARIO_WIDTH, MARIO_HEIGHT);
		
		marioStar2[12] = marioPlayerAnimationsSprites.grabMarioImage(5, 3, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar2[13] = marioPlayerAnimationsSprites.grabMarioImage(6, 3, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar2[14] = marioPlayerAnimationsSprites.grabMarioImage(7, 3, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar2[15] = marioPlayerAnimationsSprites.grabMarioImage(8, 3, MARIO_WIDTH, MARIO_HEIGHT);
		
		marioStar3[0] = marioPlayerAnimationsSprites.grabMarioImage(9, 1, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar3[1] = marioPlayerAnimationsSprites.grabMarioImage(10, 1, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar3[2] = marioPlayerAnimationsSprites.grabMarioImage(11, 1, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar3[3] = marioPlayerAnimationsSprites.grabMarioImage(12, 1, MARIO_WIDTH, MARIO_HEIGHT);
		
		marioStar3[4] = marioPlayerAnimationsSprites.grabMarioImage(9, 2, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar3[5] = marioPlayerAnimationsSprites.grabMarioImage(10, 2, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar3[6] = marioPlayerAnimationsSprites.grabMarioImage(11, 2, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar3[7] = marioPlayerAnimationsSprites.grabMarioImage(12, 2, MARIO_WIDTH, MARIO_HEIGHT);
		
		marioStar3[8] = marioPlayerAnimationsSprites.grabMarioImage(9, 4, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar3[9] = marioPlayerAnimationsSprites.grabMarioImage(10, 4, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar3[10] = marioPlayerAnimationsSprites.grabMarioImage(11, 4, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar3[11] = marioPlayerAnimationsSprites.grabMarioImage(12, 4, MARIO_WIDTH, MARIO_HEIGHT);
		
		marioStar3[12] = marioPlayerAnimationsSprites.grabMarioImage(9, 3, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar3[13] = marioPlayerAnimationsSprites.grabMarioImage(10, 3, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar3[14] = marioPlayerAnimationsSprites.grabMarioImage(11, 3, MARIO_WIDTH, MARIO_HEIGHT);
		marioStar3[15] = marioPlayerAnimationsSprites.grabMarioImage(12, 3, MARIO_WIDTH, MARIO_HEIGHT);
		
		marioSlowingDown[0] = marioSlowingDownSprites.grabExactImage(0, 0, MARIO_WIDTH, MARIO_HEIGHT);
		marioSlowingDown[1] = marioSlowingDownSprites.grabExactImage(MARIO_WIDTH, 0, MARIO_WIDTH, MARIO_HEIGHT);
		
		animatedStar[0] = starSprites.grab6pxImage(1, 1, 6, 6);
		animatedStar[1] = starSprites.grab6pxImage(2, 1, 6, 6);
		animatedStar[2] = starSprites.grab6pxImage(3, 1, 6, 6);
		animatedStar[3] = starSprites.grab6pxImage(4, 1, 6, 6);
		
		animatedStar[4] = starSprites.grab6pxImage(5, 1, 6, 6);
		animatedStar[5] = starSprites.grab6pxImage(6, 1, 6, 6);
		animatedStar[6] = starSprites.grab6pxImage(7, 1, 6, 6);
		animatedStar[7] = starSprites.grab6pxImage(8, 1, 6, 6);
		
		animatedStar[8] = starSprites.grab6pxImage(9, 1, 6, 6);
		animatedStar[9] = starSprites.grab6pxImage(10, 1, 6, 6);
		animatedStar[10] = starSprites.grab6pxImage(11, 1, 6, 6);
		animatedStar[11] = starSprites.grab6pxImage(12, 1, 6, 6);
		
		animatedStar[12] = starSprites.grab6pxImage(13, 1, 6, 6);
		animatedStar[13] = starSprites.grab6pxImage(14, 1, 6, 6);
		animatedStar[14] = starSprites.grab6pxImage(15, 1, 6, 6);
		animatedStar[15] = starSprites.grab6pxImage(16, 1, 6, 6);
		
		animatedStar[16] = starSprites.grab6pxImage(17, 1, 6, 6);
		animatedStar[17] = starSprites.grab6pxImage(18, 1, 6, 6);
		animatedStar[18] = starSprites.grab6pxImage(19, 1, 6, 6);
		animatedStar[19] = starSprites.grab6pxImage(20, 1, 6, 6);
		
		animatedShootingStar[0] = shootingStarSprites.grabExactImage((400 * 1) - 400, 0, 399, 150);
		animatedShootingStar[1] = shootingStarSprites.grabExactImage((400 * 2) - 400, 0, 399, 150);
		animatedShootingStar[2] = shootingStarSprites.grabExactImage((400 * 3) - 400, 0, 399, 150);
		animatedShootingStar[3] = shootingStarSprites.grabExactImage((400 * 4) - 400, 0, 399, 150);
		
		animatedShootingStar[4] = shootingStarSprites.grabExactImage((400 * 5) - 400, 0, 399, 150);
		animatedShootingStar[5] = shootingStarSprites.grabExactImage((400 * 6) - 400, 0, 399, 150);
		animatedShootingStar[6] = shootingStarSprites.grabExactImage((400 * 7) - 400, 0, 399, 150);
		animatedShootingStar[7] = shootingStarSprites.grabExactImage((400 * 8) - 400, 0, 399, 150);
		
		animatedShootingStar[8] = shootingStarSprites.grabExactImage((400 * 9) - 400, 0, 399, 150);
		animatedShootingStar[9] = shootingStarSprites.grabExactImage((400 * 10) - 400, 0, 399, 150);
		animatedShootingStar[10] = shootingStarSprites.grabExactImage((400 * 11) - 400, 0, 399, 150);
		animatedShootingStar[11] = shootingStarSprites.grabExactImage((400 * 12) - 400, 0, 399, 150);
		
		mario1Star[0] = mario1StarSprites.grabExactImage((14 * 1) - 14, (16 * 1) - 16, 14, 16);
		mario1Star[1] = mario1StarSprites.grabExactImage((14 * 2) - 14, (16 * 1) - 16, 14, 16);
		mario1Star[2] = mario1StarSprites.grabExactImage((14 * 3) - 14, (16 * 1) - 16, 14, 16);
		mario1Star[3] = mario1StarSprites.grabExactImage((14 * 4) - 14, (16 * 1) - 16, 14, 16);
		
		marioItemAnimationBeginning[0] = marioItemAnimationSprites.grabExactImage(1920, 1860, 80, 140);
		marioItemAnimationBeginning[1] = bigMarioItemAnimationSprites.grabExactImage(171 * 4, 755 * 4, 21 * 4, 29 * 4);
		marioItemAnimationBeginning[2] = bigMarioItemAnimationSprites.grabExactImage(8 * 4, 156 * 4, 16 * 4, 28 * 4);
		marioItemAnimationBeginning[3] = bigMarioItemAnimationSprites.grabExactImage(169 * 4, 436 * 4, 15 * 4, 28 * 4);
		marioItemAnimationBeginning[4] = bigMarioItemAnimationSprites.grabExactImage(8 * 4, 196 * 4, 17 * 4, 27 * 4);
		marioItemAnimationBeginning[5] = bigMarioItemAnimationSprites.grabExactImage(48 * 4, 196 * 4, 16 * 4, 28 * 4);
		marioItemAnimationBeginning[6] = bigMarioItemAnimationSprites.grabExactImage(232 * 4, 715 * 4, 20 * 4, 29 * 4);
		marioItemAnimationBeginning[7] = bigMarioItemAnimationSprites.grabExactImage(204 * 4, 715 * 4, 16 * 4, 29 * 4);
		marioItemAnimationBeginning[8] = bigMarioItemAnimationSprites.grabExactImage(172 * 4, 715 * 4, 20 * 4, 29 * 4);
		marioItemAnimationBeginning[9] = bigMarioItemAnimationSprites.grabExactImage(264 * 4, 715 * 4, 16 * 4, 29 * 4);
		marioItemAnimationBeginning[10] = bigMarioItemAnimationSprites.grabExactImage(144 * 4, 715 * 4, 16 * 4, 29 * 4);
		marioItemAnimationBeginning[11] = bigMarioItemAnimationSprites.grabExactImage(8, 2720, 80, 116);
		marioItemAnimationBeginning[12] = bigMarioItemAnimationSprites.grabExactImage(92, 2720, 88, 116);
		marioItemAnimationBeginning[13] = bigMarioItemAnimationSprites.grabExactImage(1376, 2708, 76, 116);
		marioItemAnimationBeginning[14] = bigMarioItemAnimationSprites.grabExactImage(1452, 2708, 64, 116);
		marioItemAnimationBeginning[15] = bigMarioItemAnimationSprites.grabExactImage(1496, 2828, 64, 116);
		marioItemAnimationBeginning[16] = bigMarioItemAnimationSprites.grabExactImage(1496, 2944, 64, 116);
		marioItemAnimationBeginning[17] = bigMarioItemAnimationSprites.grabExactImage(8, 3020, 64, 116);
		
		bigChainChompItem[0] = marioItemAnimationSprites.grabExactImage(1776, 1841, 50, 48);
		bigChainChompItem[1] = chainChompItemGettingBiggerSprites.grabExactImage(8, 288, 50, 48);
		bigChainChompItem[2] = chainChompItemGettingBiggerSprites.grabExactImage(5, 211, 59, 56);
		bigChainChompItem[3] = chainChompItemGettingBiggerSprites.grabExactImage(4, 146, 62, 59);
		bigChainChompItem[4] = chainChompItemGettingBiggerSprites.grabExactImage(2, 74, 67, 66);
		bigChainChompItem[5] = chainChompItemGettingBiggerSprites.grabExactImage(0, 0, 74, 69);
		bigChainChompItem[6] = chainChompItemGettingBiggerSprites.grabExactImage(78, 0, 79, 71);
		bigChainChompItem[7] = chainChompItemGettingBiggerSprites.grabExactImage(77, 73, 89, 80);
		bigChainChompItem[8] = chainChompItemGettingBiggerSprites.grabExactImage(77, 154, 100, 90);
		
		chainChompItem[0] = marioItemsSprites.grabExactImage(457, 0, 16, 16);
		chainChompItem[1] = marioItemsSprites.grabExactImage(457, 19, 16, 16);
		chainChompItem[2] = marioItemsSprites.grabExactImage(458, 39, 16, 15);
		chainChompItem[3] = marioItemsSprites.grabExactImage(458, 58, 16, 16);
		chainChompItem[4] = marioItemsSprites.grabExactImage(457, 77, 17, 16);
		chainChompItem[5] = marioItemsSprites.grabExactImage(458, 96, 15, 16);
		chainChompItem[6] = marioItemsSprites.grabExactImage(458, 114, 16, 17);
		chainChompItem[7] = marioItemsSprites.grabExactImage(458, 133, 16, 17);
		chainChompItem[8] = marioItemsSprites.grabExactImage(458, 152, 15, 17);
		chainChompItem[9] = marioItemsSprites.grabExactImage(458, 172, 15, 16);
		chainChompItem[10] = marioItemsSprites.grabExactImage(458, 191, 15, 16);
		chainChompItem[11] = marioItemsSprites.grabExactImage(458, 211, 16, 15);
		chainChompItem[12] = marioItemsSprites.grabExactImage(457, 230, 16, 16);
		chainChompItem[13] = marioItemsSprites.grabExactImage(457, 249, 16, 16);
		chainChompItem[14] = marioItemsSprites.grabExactImage(457, 268, 16, 16);
		chainChompItem[15] = marioItemsSprites.grabExactImage(457, 288, 16, 16);
		chainChompItem[16] = marioItemsSprites.grabExactImage(457, 307, 16, 16);
		chainChompItem[17] = marioItemsSprites.grabExactImage(457, 326, 16, 16);
		chainChompItem[18] = marioItemsSprites.grabExactImage(457, 345, 16, 16);
		chainChompItem[19] = marioItemsSprites.grabExactImage(457, 364, 16, 16);
		chainChompItem[20] = marioItemsSprites.grabExactImage(457, 384, 16, 16);
		chainChompItem[21] = marioItemsSprites.grabExactImage(457, 403, 16, 16);
		chainChompItem[22] = marioItemsSprites.grabExactImage(457, 422, 16, 16);
		chainChompItem[23] = marioItemsSprites.grabExactImage(457, 441, 16, 16);
		chainChompItem[24] = marioItemsSprites.grabExactImage(457, 460, 16, 16);
		
		chainChomp[0] = chainChompSprites.grabExactImage(362, 324, 19, 18);
		chainChomp[1] = chainChompSprites.grabExactImage(362, 344, 19, 18);
		
		fireball[0] = ss.grabSmallImage(5, 1, 16, 16);
		fireball[1] = ss.grabSmallImage(6, 1, 16, 16);
		fireball[2] = ss.grabSmallImage(7, 1, 16, 16);
		fireball[3] = ss.grabSmallImage(8, 1, 16, 16);
		
		greenShell[0] = ss.grabSmallImage(5, 2, 16, 16);
		greenShell[1] = ss.grabSmallImage(6, 2, 16, 16);
		greenShell[2] = ss.grabSmallImage(7, 2, 16, 16);
		greenShell[3] = ss.grabSmallImage(8, 2, 16, 16);
		
		//enemy[0] = ss.grabMarioImage(5, 5, 16, 16);
		enemy[0] = ss.grabSmallImage(5, 8, 16, 16);
		enemy[1] = ss.grabSmallImage(6, 8, 16, 16);

		enemy2[0] = ss.grabSmallImage(5, 9, 16, 16);
		enemy2[1] = ss.grabSmallImage(6, 9, 16, 16);

		enemy3[0] = ss.grabSmallImage(5, 10, 16, 16);
		enemy3[1] = ss.grabSmallImage(6, 10, 16, 16);
		
		bowserEntrance[0] = bowserSprites.grabExactImage((BOWSER_WIDTH * 1) - BOWSER_WIDTH, (BOWSER_HEIGHT * 1) - BOWSER_HEIGHT, BOWSER_WIDTH, BOWSER_HEIGHT);
		bowserEntrance[1] = bowserSprites.grabExactImage((BOWSER_WIDTH * 2) - BOWSER_WIDTH, (BOWSER_HEIGHT * 1) - BOWSER_HEIGHT, BOWSER_WIDTH, BOWSER_HEIGHT);
		bowserEntrance[2] = bowserSprites.grabExactImage((BOWSER_WIDTH * 3) - BOWSER_WIDTH, (BOWSER_HEIGHT * 1) - BOWSER_HEIGHT, BOWSER_WIDTH, BOWSER_HEIGHT);
		bowserEntrance[3] = bowserSprites.grabExactImage((BOWSER_WIDTH * 4) - BOWSER_WIDTH, (BOWSER_HEIGHT * 1) - BOWSER_HEIGHT, BOWSER_WIDTH, BOWSER_HEIGHT);
		
		bowserEntrance[4] = bowserSprites.grabExactImage((BOWSER_WIDTH * 5) - BOWSER_WIDTH, (BOWSER_HEIGHT * 1) - BOWSER_HEIGHT, BOWSER_WIDTH, BOWSER_HEIGHT);
		bowserEntrance[5] = bowserSprites.grabExactImage((BOWSER_WIDTH * 6) - BOWSER_WIDTH, (BOWSER_HEIGHT * 1) - BOWSER_HEIGHT, BOWSER_WIDTH, BOWSER_HEIGHT);
		bowserEntrance[6] = bowserSprites.grabExactImage((BOWSER_WIDTH * 4) - BOWSER_WIDTH, (BOWSER_HEIGHT * 1) - BOWSER_HEIGHT, BOWSER_WIDTH, BOWSER_HEIGHT);
		bowserEntrance[7] = bowserSprites.grabExactImage((BOWSER_WIDTH * 6) - BOWSER_WIDTH, (BOWSER_HEIGHT * 1) - BOWSER_HEIGHT, BOWSER_WIDTH, BOWSER_HEIGHT);
		
		bowser[0] = bowserSprites.grabExactImage((BOWSER_WIDTH * 6) - BOWSER_WIDTH, (BOWSER_HEIGHT * 1) - BOWSER_HEIGHT, BOWSER_WIDTH, BOWSER_HEIGHT);
		bowser[1] = bowserSprites.grabExactImage((BOWSER_WIDTH * 7) - BOWSER_WIDTH, (BOWSER_HEIGHT * 1) - BOWSER_HEIGHT, BOWSER_WIDTH, BOWSER_HEIGHT);
		
		bowserHit[0] = bowserSprites.grabExactImage((BOWSER_WIDTH * 8) - BOWSER_WIDTH, (BOWSER_HEIGHT * 1) - BOWSER_HEIGHT, BOWSER_WIDTH, BOWSER_HEIGHT);
		bowserHit[1] = bowserSprites.grabExactImage((BOWSER_WIDTH * 9) - BOWSER_WIDTH, (BOWSER_HEIGHT * 1) - BOWSER_HEIGHT, BOWSER_WIDTH, BOWSER_HEIGHT);
		
		bowserShip[0] = bowserSprites.grabExactImage((BOWSER_SHIP_WIDTH * 1) - BOWSER_SHIP_WIDTH, (BOWSER_SHIP_HEIGHT * 2) - BOWSER_SHIP_HEIGHT, BOWSER_SHIP_WIDTH, BOWSER_SHIP_HEIGHT);
		bowserShip[1] = bowserSprites.grabExactImage((BOWSER_SHIP_WIDTH * 2) - BOWSER_SHIP_WIDTH, (BOWSER_SHIP_HEIGHT * 2) - BOWSER_SHIP_HEIGHT, BOWSER_SHIP_WIDTH, BOWSER_SHIP_HEIGHT);
		bowserShip[2] = bowserSprites.grabExactImage((BOWSER_SHIP_WIDTH * 3) - BOWSER_SHIP_WIDTH, (BOWSER_SHIP_HEIGHT * 2) - BOWSER_SHIP_HEIGHT, BOWSER_SHIP_WIDTH, BOWSER_SHIP_HEIGHT);
		bowserShip[3] = bowserSprites.grabExactImage((BOWSER_SHIP_WIDTH * 4) - BOWSER_SHIP_WIDTH, (BOWSER_SHIP_HEIGHT * 2) - BOWSER_SHIP_HEIGHT, BOWSER_SHIP_WIDTH, BOWSER_SHIP_HEIGHT);
		bowserShip[4] = bowserSprites.grabExactImage((BOWSER_SHIP_WIDTH * 5) - BOWSER_SHIP_WIDTH, (BOWSER_SHIP_HEIGHT * 2) - BOWSER_SHIP_HEIGHT, BOWSER_SHIP_WIDTH, BOWSER_SHIP_HEIGHT);
		bowserShip[5] = bowserSprites.grabExactImage((BOWSER_SHIP_WIDTH * 6) - BOWSER_SHIP_WIDTH, (BOWSER_SHIP_HEIGHT * 2) - BOWSER_SHIP_HEIGHT, BOWSER_SHIP_WIDTH, BOWSER_SHIP_HEIGHT);
		bowserShip[6] = bowserSprites.grabExactImage((BOWSER_SHIP_WIDTH * 7) - BOWSER_SHIP_WIDTH, (BOWSER_SHIP_HEIGHT * 2) - BOWSER_SHIP_HEIGHT, BOWSER_SHIP_WIDTH, BOWSER_SHIP_HEIGHT);
		bowserShip[7] = bowserSprites.grabExactImage((BOWSER_SHIP_WIDTH * 8) - BOWSER_SHIP_WIDTH, (BOWSER_SHIP_HEIGHT * 2) - BOWSER_SHIP_HEIGHT, BOWSER_SHIP_WIDTH, BOWSER_SHIP_HEIGHT);
		
		bowserShipHit[0] = bowserSprites.grabExactImage((BOWSER_SHIP_WIDTH * 1) - BOWSER_SHIP_WIDTH, (BOWSER_SHIP_HEIGHT * 3) - BOWSER_SHIP_HEIGHT, BOWSER_SHIP_WIDTH, BOWSER_SHIP_HEIGHT);
		bowserShipHit[1] = bowserSprites.grabExactImage((BOWSER_SHIP_WIDTH * 2) - BOWSER_SHIP_WIDTH, (BOWSER_SHIP_HEIGHT * 3) - BOWSER_SHIP_HEIGHT, BOWSER_SHIP_WIDTH, BOWSER_SHIP_HEIGHT);
		bowserShipHit[2] = bowserSprites.grabExactImage((BOWSER_SHIP_WIDTH * 3) - BOWSER_SHIP_WIDTH, (BOWSER_SHIP_HEIGHT * 3) - BOWSER_SHIP_HEIGHT, BOWSER_SHIP_WIDTH, BOWSER_SHIP_HEIGHT);
		bowserShipHit[3] = bowserSprites.grabExactImage((BOWSER_SHIP_WIDTH * 4) - BOWSER_SHIP_WIDTH, (BOWSER_SHIP_HEIGHT * 3) - BOWSER_SHIP_HEIGHT, BOWSER_SHIP_WIDTH, BOWSER_SHIP_HEIGHT);
		
		bulletBillD[0] = bulletBillSprites.grabSmallImage(1, 1, 16, 16);
		bulletBillD[1] = bulletBillSprites.grabSmallImage(2, 1, 16, 16);
		bulletBillD[2] = bulletBillSprites.grabSmallImage(3, 1, 16, 16);
		bulletBillD[3] = bulletBillSprites.grabSmallImage(4, 1, 16, 16);
		
		bulletBillD[4] = bulletBillSprites.grabSmallImage(5, 1, 16, 16);
		bulletBillD[5] = bulletBillSprites.grabSmallImage(6, 1, 16, 16);
		bulletBillD[6] = bulletBillSprites.grabSmallImage(7, 1, 16, 16);
		bulletBillD[7] = bulletBillSprites.grabSmallImage(8, 1, 16, 16);
		
		bulletBillD[8] = bulletBillSprites.grabSmallImage(9, 1, 16, 16);
		bulletBillD[9] = bulletBillSprites.grabSmallImage(10, 1, 16, 16);
		bulletBillD[10] = bulletBillSprites.grabSmallImage(11, 1, 16, 16);
		bulletBillD[11] = bulletBillSprites.grabSmallImage(12, 1, 16, 16);
		
		bulletBillD[12] = bulletBillSprites.grabSmallImage(13, 1, 16, 16);
		bulletBillD[13] = bulletBillSprites.grabSmallImage(14, 1, 16, 16);
		bulletBillD[14] = bulletBillSprites.grabSmallImage(15, 1, 16, 16);
		bulletBillD[15] = bulletBillSprites.grabSmallImage(16, 1, 16, 16);
		bulletBillD[16] = bulletBillSprites.grabSmallImage(17, 1, 16, 16);

		bulletBillDL[0] = bulletBillSprites.grabSmallImage(1, 2, 16, 16);
		bulletBillDL[1] = bulletBillSprites.grabSmallImage(2, 2, 16, 16);
		bulletBillDL[2] = bulletBillSprites.grabSmallImage(3, 2, 16, 16);
		bulletBillDL[3] = bulletBillSprites.grabSmallImage(4, 2, 16, 16);
		
		bulletBillDL[4] = bulletBillSprites.grabSmallImage(5, 2, 16, 16);
		bulletBillDL[5] = bulletBillSprites.grabSmallImage(6, 2, 16, 16);
		bulletBillDL[6] = bulletBillSprites.grabSmallImage(7, 2, 16, 16);
		bulletBillDL[7] = bulletBillSprites.grabSmallImage(8, 2, 16, 16);
		
		bulletBillDL[8] = bulletBillSprites.grabSmallImage(9, 2, 16, 16);
		bulletBillDL[9] = bulletBillSprites.grabSmallImage(10, 2, 16, 16);
		
		bulletBillDR[0] = bulletBillSprites.grabSmallImage(1, 3, 16, 16);
		bulletBillDR[1] = bulletBillSprites.grabSmallImage(2, 3, 16, 16);
		bulletBillDR[2] = bulletBillSprites.grabSmallImage(3, 3, 16, 16);
		bulletBillDR[3] = bulletBillSprites.grabSmallImage(4, 3, 16, 16);
		
		bulletBillDR[4] = bulletBillSprites.grabSmallImage(5, 3, 16, 16);
		bulletBillDR[5] = bulletBillSprites.grabSmallImage(6, 3, 16, 16);
		bulletBillDR[6] = bulletBillSprites.grabSmallImage(7, 3, 16, 16);
		bulletBillDR[7] = bulletBillSprites.grabSmallImage(8, 3, 16, 16);
		
		bulletBillDR[8] = bulletBillSprites.grabSmallImage(9, 3, 16, 16);
		bulletBillDR[9] = bulletBillSprites.grabSmallImage(10, 3, 16, 16);
		
		bulletBillExplosion[0] = marioAdvanceSprites.grabExactImage(555, 47, 7, 7);
		bulletBillExplosion[1] = marioAdvanceSprites.grabExactImage(518, 26, 5, 5);
		bulletBillExplosion[2] = marioAdvanceSprites.grabExactImage(509, 24, 7, 8);
		bulletBillExplosion[3] = marioAdvanceSprites.grabExactImage(491, 21, 14, 14);
		bulletBillExplosion[4] = marioAdvanceSprites.grabExactImage(475,20,15,15);
		bulletBillExplosion[5] = marioAdvanceSprites.grabExactImage(585, 92, 18, 16);
		bulletBillExplosion[6] = marioAdvanceSprites.grabExactImage(455, 19, 16, 16);
		
		enemyExplosion[0] = goombaDeathSprites.grabExactImage(70, 67, 16, 15);
		enemyExplosion[1] = goombaDeathSprites.grabExactImage(89, 67, 12, 14);
		enemyExplosion[2] = goombaDeathSprites.grabExactImage(105, 67, 14, 14);
		
		star[0] = goombaDeathSprites.grabExactImage(80,109,7,7);
		star[1] = goombaDeathSprites.grabExactImage(96,109,7,7);
		star[2] = goombaDeathSprites.grabExactImage(112,96,5,5);
		star[3] = goombaDeathSprites.grabExactImage(112,110,5,5);
		star[4] = goombaDeathSprites.grabExactImage(113,119,2,2);
		star[5] = goombaDeathSprites.grabExactImage(113,124,1,1);
		
		coin[0] = marioAdvanceSprites.grabExactImage(303, 99, 10, 16);
		coin[1] = marioAdvanceSprites.grabExactImage(322, 99, 8, 16);
		coin[2] = marioAdvanceSprites.grabExactImage(341, 99, 6, 16);
		
		enemy2DeathL[0] = goombaDeathSprites.grabExactImage(124, 99, 16, 16);
		enemy2DeathL[1] = goombaDeathSprites.grabExactImage(125, 84, 15, 15);
		enemy2DeathL[2] = goombaDeathSprites.grabExactImage(127, 71, 13, 13);
		enemy2DeathL[3] = goombaDeathSprites.grabExactImage(129, 60, 11, 11);
		enemy2DeathL[4] = goombaDeathSprites.grabExactImage(131, 51, 9, 9);
		enemy2DeathL[5] = goombaDeathSprites.grabExactImage(132, 43, 8, 8);
		enemy2DeathL[6] = goombaDeathSprites.grabExactImage(134, 37, 6, 6);
		enemy2DeathL[7] = goombaDeathSprites.grabExactImage(135, 31, 5, 5);
		enemy2DeathL[8] = goombaDeathSprites.grabExactImage(136, 27, 4, 3);
		enemy2DeathL[9] = goombaDeathSprites.grabExactImage(136, 24, 2, 2);
		
		enemy2DeathR[0] = goombaDeathSprites.grabExactImage(140, 99, 16, 16);
		enemy2DeathR[1] = goombaDeathSprites.grabExactImage(140, 84, 15, 15);
		enemy2DeathR[2] = goombaDeathSprites.grabExactImage(140, 71, 13, 13);
		enemy2DeathR[3] = goombaDeathSprites.grabExactImage(140, 60, 11, 11);
		enemy2DeathR[4] = goombaDeathSprites.grabExactImage(140, 51, 9, 9);
		enemy2DeathR[5] = goombaDeathSprites.grabExactImage(140, 43, 8, 8);
		enemy2DeathR[6] = goombaDeathSprites.grabExactImage(140, 37, 6, 6);
		enemy2DeathR[7] = goombaDeathSprites.grabExactImage(140, 31, 5, 5);
		enemy2DeathR[8] = goombaDeathSprites.grabExactImage(140, 27, 4, 3);
		enemy2DeathR[9] = goombaDeathSprites.grabExactImage(142, 24, 2, 2);
		
		enemy3Explosion[0] = goombaDeathSprites.grabExactImage(175, 105, 10, 9);
		enemy3Explosion[1] = goombaDeathSprites.grabExactImage(190, 103, 12, 11);
		enemy3Explosion[2] = goombaDeathSprites.grabExactImage(207, 102, 13, 11);
		enemy3Explosion[3] = goombaDeathSprites.grabExactImage(225, 100, 14, 14);
		enemy3Explosion[4] = goombaDeathSprites.grabExactImage(244, 99, 14, 15);
		enemy3Explosion[5] = goombaDeathSprites.grabExactImage(263, 99, 16, 15);
		enemy3Explosion[6] = goombaDeathSprites.grabExactImage(283, 98, 16, 16);
		enemy3Explosion[7] = goombaDeathSprites.grabExactImage(303, 98, 16, 16);
		enemy3Explosion[8] = goombaDeathSprites.grabExactImage(324, 98, 15, 16);
		enemy3Explosion[9] = goombaDeathSprites.grabExactImage(344, 97, 16, 17);
		enemy3Explosion[10] = goombaDeathSprites.grabExactImage(364, 98, 15, 16);
		enemy3Explosion[11] = goombaDeathSprites.grabExactImage(384, 98, 13, 16);
		enemy3Explosion[12] = goombaDeathSprites.grabExactImage(402, 101, 13, 13);
		
		enemy3Death[0] = goombaDeathSprites.grabExactImage(157, 106, 16, 8);
		enemy3Death[1] = goombaDeathSprites.grabExactImage(159, 97, 11, 8);
		enemy3Death[2] = goombaDeathSprites.grabExactImage(159, 92, 11, 4);
				
		marioEntrance[0] = fullMarioSprites.grabExactImage(129, 0, 14, 19);
		marioEntrance[1] = fullMarioSprites.grabExactImage(169, 0, 14, 19);
		marioEntrance[2] = fullMarioSprites.grabExactImage(89, 0, 14, 19);
		marioEntrance[3] = fullMarioSprites.grabExactImage(209, 0, 14, 19);
		marioEntrance[4] = fullMarioSprites.grabExactImage(249, 0, 14, 19);
		marioEntrance[5] = fullMarioSprites.grabExactImage(24, 760, 16, 20);
		marioEntrance[6] = fullMarioSprites.grabExactImage(324, 760, 16, 20);
		marioEntrance[7] = fullMarioSprites.grabExactImage(54, 760, 16, 20);
		marioEntrance[8] = fullMarioSprites.grabExactImage(84, 760, 16, 20);
		marioEntrance[9] = fullMarioSprites.grabExactImage(231, 755, 21, 29);
		marioEntrance[10] = fullMarioSprites.grabExactImage(171, 755, 21, 29);
		marioEntrance[11] = fullMarioSprites.grabExactImage(8, 156, 16, 28);
		marioEntrance[12] = fullMarioSprites.grabExactImage(169, 436, 15, 28);
		marioEntrance[13] = fullMarioSprites.grabExactImage(8, 196, 17, 27);
		marioEntrance[14] = fullMarioSprites.grabExactImage(48, 196, 16, 28);
		marioEntrance[15] = fullMarioSprites.grabExactImage(232, 715, 20, 29);
		marioEntrance[16] = fullMarioSprites.grabExactImage(204, 715, 16, 29);
		marioEntrance[17] = fullMarioSprites.grabExactImage(172, 715, 20, 29);
		marioEntrance[18] = fullMarioSprites.grabExactImage(264, 715, 16, 29);
		marioEntrance[19] = fullMarioSprites.grabExactImage(144, 715, 16, 29);
		marioEntrance[20] = fullMarioSprites.grabExactImage(288, 436, 16, 28);
		
		marioDeath[0] = fullMarioSprites.grabExactImage(8, 38, 16, 24);
		marioDeath[1] = fullMarioSprites.grabExactImage(368, 38, 16, 24);
		
		transparentBlocks[0] = transparentBlocksSprites.grabExactImage(40, 210, 16, 28);
		transparentBlocks[1] = transparentBlocksSprites.grabExactImage(140, 210, 16, 28);
		transparentBlocks[2] = transparentBlocksSprites.grabExactImage(240, 210, 16, 28);
		
		marioNumbersSmall[0] = marioAdvanceSprites.grabExactImage(519, 206, 9, 10);
		marioNumbersSmall[1] = marioAdvanceSprites.grabExactImage(505, 194, 7, 10);
		marioNumbersSmall[2] = marioAdvanceSprites.grabExactImage(513, 194, 9, 10);
		marioNumbersSmall[3] = marioAdvanceSprites.grabExactImage(523, 194, 7, 10);
		marioNumbersSmall[4] = marioAdvanceSprites.grabExactImage(458, 206, 10, 10);
		marioNumbersSmall[5] = marioAdvanceSprites.grabExactImage(469, 206, 9, 10);
		marioNumbersSmall[6] = marioAdvanceSprites.grabExactImage(479, 206, 9, 10);
		marioNumbersSmall[7] = marioAdvanceSprites.grabExactImage(489, 206, 9, 10);
		marioNumbersSmall[8] = marioAdvanceSprites.grabExactImage(499, 206, 9, 10);
		marioNumbersSmall[9] = marioAdvanceSprites.grabExactImage(509, 206, 9, 10);
		marioNumbersSmall[10] = marioAdvanceSprites.grabExactImage(529, 206, 6, 10);
		marioNumbersSmall[11] = marioAdvanceSprites.grabExactImage(529, 182, 11, 11);
		
		mario3FontNumbersSmall[0] = mario3FontNumbersSmallSprites.grabExactImage(64, 16, 16, 16);
		mario3FontNumbersSmall[1] = mario3FontNumbersSmallSprites.grabExactImage(0, 0, 16, 16);
		mario3FontNumbersSmall[2] = mario3FontNumbersSmallSprites.grabExactImage(16, 0, 16, 16);
		mario3FontNumbersSmall[3] = mario3FontNumbersSmallSprites.grabExactImage(32, 0, 16, 16);
		mario3FontNumbersSmall[4] = mario3FontNumbersSmallSprites.grabExactImage(48, 0, 16, 16);
		mario3FontNumbersSmall[5] = mario3FontNumbersSmallSprites.grabExactImage(64, 0, 16, 16);
		mario3FontNumbersSmall[6] = mario3FontNumbersSmallSprites.grabExactImage(0, 16, 16, 16);
		mario3FontNumbersSmall[7] = mario3FontNumbersSmallSprites.grabExactImage(16, 16, 16, 16);
		mario3FontNumbersSmall[8] = mario3FontNumbersSmallSprites.grabExactImage(32, 16, 16, 16);
		mario3FontNumbersSmall[9] = mario3FontNumbersSmallSprites.grabExactImage(48, 16, 16, 16);
		mario3FontNumbersSmall[10] = mario3FontNumbersSmallSprites.grabExactImage(32, 32, 12, 16);
		
		itemFrame = marioAdvanceSprites.grabExactImage(308, 268, 26, 26);
		
		chainChompItemFrameDisplay = marioAdvanceSprites.grabExactImage(282, 301, 22, 22);
		
		pressE = marioAdvanceSprites.grabExactImage(450, 285, 47, 11);
		
		gameover = ss.grabExactImage(144, 208, 80, 16);
	}
}
