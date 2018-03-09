package com.game.src.main;

import java.awt.image.BufferedImage;

public class Textures {

	public BufferedImage[] player = new BufferedImage[16];
	public BufferedImage[] marioStar1 = new BufferedImage[16];
	public BufferedImage[] marioStar2 = new BufferedImage[16];
	public BufferedImage[] marioStar3 = new BufferedImage[16];
	public BufferedImage[] marioSlowingDown = new BufferedImage[2];
	public BufferedImage[] fireball = new BufferedImage[4];
	public BufferedImage[] enemy = new BufferedImage[2];
	public BufferedImage[] enemy2 = new BufferedImage[2];
	public BufferedImage[] enemy3 = new BufferedImage[2];
	public BufferedImage[] greenShell = new BufferedImage[4];
	public BufferedImage[] animatedStar = new BufferedImage[20];
	public BufferedImage[] animatedShootingStar = new BufferedImage[12];
	public BufferedImage[] mario1Star = new BufferedImage[4];
	public BufferedImage[] bowserEntrance = new BufferedImage[8];
	public BufferedImage[] bowser = new BufferedImage[2];
	public BufferedImage[] bowserHit = new BufferedImage[2];
	public BufferedImage[] bowserShip = new BufferedImage[8];
	public BufferedImage[] bowserShipHit = new BufferedImage[4];
	public BufferedImage[] bulletBillD = new BufferedImage[17];
	public BufferedImage[] bulletBillDL = new BufferedImage[10];
	public BufferedImage[] bulletBillDR = new BufferedImage[10];
	public BufferedImage[] marioEntrance = new BufferedImage[21];
	public BufferedImage[] marioDeath = new BufferedImage[2];
	public BufferedImage[] transparentBlocks = new BufferedImage[3];
	public BufferedImage gameover;
	
	public static final int MARIO_WIDTH = 16;
	public static final int MARIO_HEIGHT = 28;
	public static final int BOWSER_WIDTH = 64;
	public static final int BOWSER_HEIGHT = 41;
	public static final int BOWSER_HIT_WIDTH = 64;
	public static final int BOWSER_HIT_HEIGHT = 50;
	public static final int BOWSER_SHIP_WIDTH = 64;
	public static final int BOWSER_SHIP_HEIGHT = 55;
	public static final int SHOOTING_STAR_WIDTH = 399;
	public static final int SHOOTING_STAR_HEIGHT = 150;
	
	private SpriteSheet ss;
	private SpriteSheet starSprites;
	private SpriteSheet shootingStarSprites;
	private SpriteSheet mario1StarSprites;
	private SpriteSheet bowserSprites;
	private SpriteSheet bulletBillSprites;
	private SpriteSheet marioPlayerAnimationsSprites;
	private SpriteSheet marioSlowingDownSprites;
	private SpriteSheet fullMarioSprites;
	private SpriteSheet transparentBlocksSprites;
	
	public Textures(Game game){
		ss = new SpriteSheet(game.getSpriteSheet());
		starSprites = new SpriteSheet(game.getAnimatedStar());
		shootingStarSprites = new SpriteSheet(game.getAnimatedShootingStar());
		mario1StarSprites = new SpriteSheet(game.getMario1StarSpriteSheet());
		bowserSprites = new SpriteSheet(game.getBowserSpriteSheet());
		bulletBillSprites = new SpriteSheet(game.getBulletBillSpriteSheet());
		marioPlayerAnimationsSprites = new SpriteSheet(game.getMarioPlayerStarAnimations());
		marioSlowingDownSprites = new SpriteSheet(game.getMarioSlowingDownSprites());
		fullMarioSprites = new SpriteSheet(game.getFullMarioSpriteSheet());
		transparentBlocksSprites = new SpriteSheet(game.getTransparentBlocks());
		
		getTextures();
	}
	
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
		
		gameover = ss.grabExactImage(144, 208, 80, 16);
	}
}
