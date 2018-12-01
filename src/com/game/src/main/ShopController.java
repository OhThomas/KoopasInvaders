package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.src.main.libs.Animation;

public class ShopController {

	private Game game;
	private Textures tex;
	private Animation marioSkin0;
	private Animation marioSkin1;
	private Animation marioSkin2;
	private Animation marioSkin3;
	private Animation marioSkin1Silhouette;
	private Animation marioSkin2Silhouette;
	private Animation marioSkin3Silhouette;
	private Animation track1;
	private Animation fireball0;
	private Animation fireball1;
	private Animation fireball2;
	private Animation fireball3;
	private Animation fireball1Silhouette;
	private Animation fireball3Silhouette;
	private Animation item0;
	private Animation item1;
	private Animation item2;
	private Animation item3;
	private Animation item4;
	private Animation item5;
	private Animation item6;
	private Animation item1Silhouette;
	public static BufferedImage[] songTrackImages = new BufferedImage[12];
	private BufferedImage[] songTrackImagesSilhouette = new BufferedImage[12];
	public static BufferedImage skinPriceImage;
	public static BufferedImage trackPriceImage;
	public static BufferedImage fireballPriceImage;
	public static BufferedImage itemPriceImage;
	private BufferedImage buyTitle;
	private BufferedImage buyTitleGlow;
	private BufferedImage buyTitleClicked;
	public static BufferedImage pointsImage;
	private boolean imagesSetup = false;
	private int skin1Price = 100;//each int has 10 width
	private int skin2Price = 1000;
	private int skin3Price = 10000;
	private int track1Price = 100;
	private int fireball1Price = 100;
	private int fireball2Price = 1000;
	private int fireball3Price = 10000;
	private int item4Price = 100;
	private int item5Price = 1000;
	private int item6Price = 10000;
	public static int currentSkinPrice = 0;
	public static int currentTrackPrice = 0;
	public static int currentFireballPrice = 0;
	public static int currentItemPrice = 0;
	
	public ShopController(Textures tex, Game game) {
		this.game = game;
		this.tex = tex;
		marioSkin0 = new Animation(6,tex.marioSkin1[0],tex.marioSkin1[1],tex.marioSkin1[2],tex.marioSkin1[1]);
		marioSkin1 = new Animation(6,tex.playerNES[8],tex.playerNES[9],tex.playerNES[10],tex.playerNES[11]);
		marioSkin2 = new Animation(6,tex.player3NES[8],tex.player3NES[9],tex.player3NES[10],tex.player3NES[11]);
		marioSkin3 = new Animation(6,tex.playerSNESFireLuigi[8],tex.playerSNESFireLuigi[9],tex.playerSNESFireLuigi[10],tex.playerSNESFireLuigi[11]);
		marioSkin1Silhouette = new Animation(6,tex.marioSilhouette[0],tex.marioSilhouette[1],tex.marioSilhouette[2],tex.marioSilhouette[1]);
		marioSkin2Silhouette = new Animation(6,tex.marioSilhouette2[0],tex.marioSilhouette2[1],tex.marioSilhouette2[2],tex.marioSilhouette2[1]);
		marioSkin3Silhouette = new Animation(6,tex.marioSilhouette3[0],tex.marioSilhouette3[1],tex.marioSilhouette3[2],tex.marioSilhouette3[1]);
		fireball0 = new Animation(6,tex.fireball[0],tex.fireball[1],tex.fireball[2],tex.fireball[3]);
		fireball1 = new Animation(4,tex.greenShell[0],tex.greenShell[1],tex.greenShell[2],tex.greenShell[3]);
		fireball2 = new Animation(4,tex.redShell[0],tex.redShell[1],tex.redShell[2],tex.redShell[3]);
		fireball3 = new Animation(4,tex.buzzyBeetleShell[0],tex.buzzyBeetleShell[1],tex.buzzyBeetleShell[2],tex.buzzyBeetleShell[3]);
		fireball1Silhouette = new Animation(6,tex.fireball1Silhouette[0],tex.fireball1Silhouette[1],tex.fireball1Silhouette[2],tex.fireball1Silhouette[3]);
		fireball3Silhouette = new Animation(6,tex.fireball3Silhouette[0],tex.fireball3Silhouette[1],tex.fireball3Silhouette[2],tex.fireball3Silhouette[3]);
		item0 = new Animation(1, tex.chainChompItemBall[0], tex.chainChompItemBall[1], tex.chainChompItemBall[2], tex.chainChompItemBall[3],
				tex.chainChompItemBall[4], tex.chainChompItemBall[5], tex.chainChompItemBall[6], tex.chainChompItemBall[7],
				tex.chainChompItemBall[8], tex.chainChompItemBall[9], tex.chainChompItemBall[10], tex.chainChompItemBall[11],
				tex.chainChompItemBall[12], tex.chainChompItemBall[13], tex.chainChompItemBall[14], tex.chainChompItemBall[15],
				tex.chainChompItemBall[16], tex.chainChompItemBall[17], tex.chainChompItemBall[18], tex.chainChompItemBall[19],
				tex.chainChompItemBall[20], tex.chainChompItemBall[21], tex.chainChompItemBall[22], tex.chainChompItemBall[23],
				tex.chainChompItemBall[24]);
		item1 = new Animation(1, tex.bulletBillItemBall[0], tex.bulletBillItemBall[1], tex.bulletBillItemBall[2], tex.bulletBillItemBall[3],
				tex.bulletBillItemBall[4], tex.bulletBillItemBall[5], tex.bulletBillItemBall[6], tex.bulletBillItemBall[7],
				tex.bulletBillItemBall[8], tex.bulletBillItemBall[9], tex.bulletBillItemBall[10], tex.bulletBillItemBall[11],
				tex.bulletBillItemBall[12], tex.bulletBillItemBall[13], tex.bulletBillItemBall[14], tex.bulletBillItemBall[15],
				tex.bulletBillItemBall[16], tex.bulletBillItemBall[17], tex.bulletBillItemBall[18], tex.bulletBillItemBall[19],
				tex.bulletBillItemBall[20], tex.bulletBillItemBall[21], tex.bulletBillItemBall[22], tex.bulletBillItemBall[23]);
		item2 = new Animation(1, tex.bombOmbItemBall[0], tex.bombOmbItemBall[1], tex.bombOmbItemBall[2], tex.bombOmbItemBall[3],
				tex.bombOmbItemBall[4], tex.bombOmbItemBall[5], tex.bombOmbItemBall[6], tex.bombOmbItemBall[7],
				tex.bombOmbItemBall[8], tex.bombOmbItemBall[9], tex.bombOmbItemBall[10], tex.bombOmbItemBall[11],
				tex.bombOmbItemBall[12], tex.bombOmbItemBall[13], tex.bombOmbItemBall[14], tex.bombOmbItemBall[15],
				tex.bombOmbItemBall[16], tex.bombOmbItemBall[17], tex.bombOmbItemBall[18], tex.bombOmbItemBall[19],
				tex.bombOmbItemBall[20], tex.bombOmbItemBall[21], tex.bombOmbItemBall[22], tex.bombOmbItemBall[23]);
		item3 = new Animation(1, tex.cheepCheepsItemBall[0], tex.cheepCheepsItemBall[1], tex.cheepCheepsItemBall[2], tex.cheepCheepsItemBall[3],
				tex.cheepCheepsItemBall[4], tex.cheepCheepsItemBall[5], tex.cheepCheepsItemBall[6], tex.cheepCheepsItemBall[7],
				tex.cheepCheepsItemBall[8], tex.cheepCheepsItemBall[9], tex.cheepCheepsItemBall[10], tex.cheepCheepsItemBall[11],
				tex.cheepCheepsItemBall[12], tex.cheepCheepsItemBall[13], tex.cheepCheepsItemBall[14], tex.cheepCheepsItemBall[15],
				tex.cheepCheepsItemBall[16], tex.cheepCheepsItemBall[17], tex.cheepCheepsItemBall[18], tex.cheepCheepsItemBall[19],
				tex.cheepCheepsItemBall[20], tex.cheepCheepsItemBall[21], tex.cheepCheepsItemBall[22], tex.cheepCheepsItemBall[23]);
		item4 = new Animation(1, tex.ampItemBall[0], tex.ampItemBall[1], tex.ampItemBall[2], tex.ampItemBall[3],
				tex.ampItemBall[4], tex.ampItemBall[5], tex.ampItemBall[6], tex.ampItemBall[7],
				tex.ampItemBall[8], tex.ampItemBall[9], tex.ampItemBall[10], tex.ampItemBall[11],
				tex.ampItemBall[12], tex.ampItemBall[13], tex.ampItemBall[14], tex.ampItemBall[15],
				tex.ampItemBall[16], tex.ampItemBall[17], tex.ampItemBall[18], tex.ampItemBall[19],
				tex.ampItemBall[20], tex.ampItemBall[21], tex.ampItemBall[22], tex.ampItemBall[23]);
		item5 = new Animation(1, tex.wigglerItemBall[0], tex.wigglerItemBall[1], tex.wigglerItemBall[2], tex.wigglerItemBall[3],
				tex.wigglerItemBall[4], tex.wigglerItemBall[5], tex.wigglerItemBall[6], tex.wigglerItemBall[7],
				tex.wigglerItemBall[8], tex.wigglerItemBall[9], tex.wigglerItemBall[10], tex.wigglerItemBall[11],
				tex.wigglerItemBall[12], tex.wigglerItemBall[13], tex.wigglerItemBall[14], tex.wigglerItemBall[15],
				tex.wigglerItemBall[16], tex.wigglerItemBall[17], tex.wigglerItemBall[18], tex.wigglerItemBall[19],
				tex.wigglerItemBall[20], tex.wigglerItemBall[21], tex.wigglerItemBall[22], tex.wigglerItemBall[23]);
		item6 = new Animation(1, tex.lakituItemBall[0], tex.lakituItemBall[1], tex.lakituItemBall[2], tex.lakituItemBall[3],
				tex.lakituItemBall[4], tex.lakituItemBall[5], tex.lakituItemBall[6], tex.lakituItemBall[7],
				tex.lakituItemBall[8], tex.lakituItemBall[9], tex.lakituItemBall[10], tex.lakituItemBall[11],
				tex.lakituItemBall[12], tex.lakituItemBall[13], tex.lakituItemBall[14], tex.lakituItemBall[15],
				tex.lakituItemBall[16], tex.lakituItemBall[17], tex.lakituItemBall[18], tex.lakituItemBall[19],
				tex.lakituItemBall[20], tex.lakituItemBall[21], tex.lakituItemBall[22], tex.lakituItemBall[23]);
		item1Silhouette = new Animation(6,tex.itemSilhouette[0],tex.itemSilhouette[1],tex.itemSilhouette[2]
				,tex.itemSilhouette[3],tex.itemSilhouette[4],tex.itemSilhouette[5]);
		this.songTrackImages = tex.songTrackImages;
		this.songTrackImagesSilhouette = tex.songTrackSilhouetteImages;
		this.buyTitle = game.getBuyTitle();
		this.buyTitleGlow = game.getBuyTitleGlow();
		this.buyTitleClicked = game.getBuyTitleClicked();
		this.pointsImage = Game.resize(HUD.stringToMario3FontImage("points"), 55, 7);
	}
	
	public void drawShop(Graphics g) {
		if(!imagesSetup) {
			this.marioSkin0.nextFrame();
			this.marioSkin0.setSpeed(10);
			this.marioSkin0.setCount(0);
			this.marioSkin1.nextFrame();
			this.marioSkin1.setSpeed(10);
			this.marioSkin1.setCount(0);
			this.marioSkin2.nextFrame();
			this.marioSkin2.setSpeed(10);
			this.marioSkin2.setCount(0);
			this.marioSkin3.nextFrame();
			this.marioSkin3.setSpeed(10);
			this.marioSkin3.setCount(0);
			this.marioSkin1Silhouette.nextFrame();
			this.marioSkin1Silhouette.setSpeed(10);
			this.marioSkin1Silhouette.setCount(0);
			this.marioSkin2Silhouette.nextFrame();
			this.marioSkin2Silhouette.setSpeed(10);
			this.marioSkin2Silhouette.setCount(0);
			this.marioSkin3Silhouette.nextFrame();
			this.marioSkin3Silhouette.setSpeed(10);
			this.marioSkin3Silhouette.setCount(0);
			this.fireball0.nextFrame();
			this.fireball0.setSpeed(10);
			this.fireball0.setCount(0);
			this.fireball1.nextFrame();
			this.fireball1.setSpeed(10);
			this.fireball1.setCount(0);
			this.fireball2.nextFrame();
			this.fireball2.setSpeed(10);
			this.fireball2.setCount(0);
			this.fireball3.nextFrame();
			this.fireball3.setSpeed(10);
			this.fireball3.setCount(0);
			this.fireball1Silhouette.nextFrame();
			this.fireball1Silhouette.setSpeed(10);
			this.fireball1Silhouette.setCount(0);
			this.fireball3Silhouette.nextFrame();
			this.fireball3Silhouette.setSpeed(10);
			this.fireball3Silhouette.setCount(0);
			this.item0.nextFrame();
			this.item0.setSpeed(5);
			this.item0.setCount(0);
			this.item1.nextFrame();
			this.item1.setSpeed(5);
			this.item1.setCount(0);
			this.item2.nextFrame();
			this.item2.setSpeed(5);
			this.item2.setCount(0);
			this.item3.nextFrame();
			this.item3.setSpeed(5);
			this.item3.setCount(0);
			this.item4.nextFrame();
			this.item4.setSpeed(5);
			this.item4.setCount(0);
			this.item5.nextFrame();
			this.item5.setSpeed(5);
			this.item5.setCount(0);
			this.item6.nextFrame();
			this.item6.setSpeed(5);
			this.item6.setCount(0);
			this.item1Silhouette.nextFrame();
			this.item1Silhouette.setSpeed(5);
			this.item1Silhouette.setCount(0);
			imagesSetup = true;
		}
		switch(Game.characterSkinPosition) {
			case 0:
				if(Game.currentSkinLocked)
					Game.currentSkinLocked = false;
				if(System.currentTimeMillis() % 30 == 0)
					this.marioSkin0.runAnimation();
				this.marioSkin0.drawAnimation(g, Game.WIDTH , 120,0);
				break;
			case 1:
				if(Game.skin1Unlocked == false) {
					if(!Game.currentSkinLocked) 
						Game.currentSkinLocked = true;
					if(skin1Price != currentSkinPrice) {
						skinPriceImage = Game.resize(HUD.stringToMario3FontImage(Integer.toString(skin1Price)), 30, 15);
						currentSkinPrice = skin1Price;
					}
					if(System.currentTimeMillis() % 30 == 0)
						this.marioSkin1Silhouette.runAnimation();
					this.marioSkin1Silhouette.drawAnimation(g, Game.WIDTH , 120,0);
					HUD.clickyButton(g, buyTitle, buyTitleGlow, buyTitleClicked, Game.buy1Highlighted, Game.buy1Clicked, Game.backOnBuy1, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - 5, 120 + 7);
					g.drawImage(skinPriceImage, Game.WIDTH - 6, 120 + 35, null);
					g.drawImage(pointsImage, Game.WIDTH - 6 + skinPriceImage.getWidth() + 4, 120 + 35 + 7, null);
					break;
				}
				else {
					if(Game.currentSkinLocked)
						Game.currentSkinLocked = false;
					if(System.currentTimeMillis() % 30 == 0)
						this.marioSkin1.runAnimation();
					this.marioSkin1.drawAnimation(g, Game.WIDTH , 120,0);
					break;
				}
			case 2:
				if(Game.skin2Unlocked == false) {
					if(!Game.currentSkinLocked) 
						Game.currentSkinLocked = true;
					if(skin2Price != currentSkinPrice) {
						skinPriceImage = Game.resize(HUD.stringToMario3FontImage(Integer.toString(skin2Price)), 40, 15);
						currentSkinPrice = skin2Price;
					}
					if(System.currentTimeMillis() % 30 == 0)
						this.marioSkin2Silhouette.runAnimation();
					this.marioSkin2Silhouette.drawAnimation(g, Game.WIDTH , 120,0);
					HUD.clickyButton(g, buyTitle, buyTitleGlow, buyTitleClicked, Game.buy1Highlighted, Game.buy1Clicked, Game.backOnBuy1, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - 5, 120 + 7);
					g.drawImage(skinPriceImage, Game.WIDTH - 11, 120 + 35, null);
					g.drawImage(pointsImage, Game.WIDTH - 11 + skinPriceImage.getWidth() + 4, 120 + 35 + 7, null);
					break;
				}
				else {
					if(Game.currentSkinLocked)
						Game.currentSkinLocked = false;
					if(System.currentTimeMillis() % 30 == 0)
						this.marioSkin2.runAnimation();
					this.marioSkin2.drawAnimation(g, Game.WIDTH , 120,0);
					break;
				}
			case 3:
				if(Game.skin3Unlocked == false) {
					if(!Game.currentSkinLocked) 
						Game.currentSkinLocked = true;
					if(skin3Price != currentSkinPrice) {
						skinPriceImage = Game.resize(HUD.stringToMario3FontImage(Integer.toString(skin3Price)), 50, 15);
						currentSkinPrice = skin3Price;
					}
					if(System.currentTimeMillis() % 30 == 0)
						this.marioSkin3Silhouette.runAnimation();
					this.marioSkin3Silhouette.drawAnimation(g, Game.WIDTH , 120,0);
					HUD.clickyButton(g, buyTitle, buyTitleGlow, buyTitleClicked, Game.buy1Highlighted, Game.buy1Clicked, Game.backOnBuy1, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - 5, 120 + 7);
					g.drawImage(skinPriceImage, Game.WIDTH - 16, 120 + 35, null);
					g.drawImage(pointsImage, Game.WIDTH - 16 + skinPriceImage.getWidth() + 4, 120 + 35 + 7, null);
					break;
				}
				else {
					if(Game.currentSkinLocked)
						Game.currentSkinLocked = false;
					if(System.currentTimeMillis() % 30 == 0)
						this.marioSkin3.runAnimation();
					this.marioSkin3.drawAnimation(g, Game.WIDTH , 120,0);
					break;
				}
			default:
				break;
		}
		switch(Game.trackPosition) {
			case 0:
				if(Game.currentTrackLocked)
					Game.currentTrackLocked = false;
				g.drawImage(songTrackImages[0], Game.WIDTH + 2, 220 + 7,null);
				break;
			case 1:
				if(Game.track1Unlocked == false) {
					if(!Game.currentTrackLocked)
						Game.currentTrackLocked = true;
					if(track1Price != currentTrackPrice) {
						trackPriceImage = Game.resize(HUD.stringToMario3FontImage(Integer.toString(track1Price)), 30, 15);
						currentTrackPrice = track1Price;
					}
					g.drawImage(songTrackImagesSilhouette[1], Game.WIDTH, 220 + 3,null);
					HUD.clickyButton(g, buyTitle, buyTitleGlow, buyTitleClicked, Game.buy2Highlighted, Game.buy2Clicked, Game.backOnBuy2, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - 5, 220 + 7);
					g.drawImage(trackPriceImage, Game.WIDTH - 6, 220 + 35, null);
					g.drawImage(pointsImage, Game.WIDTH - 6 + trackPriceImage.getWidth() + 4, 220 + 35 + 7, null);
					break;
				}
				else {
					break;
				}
			default:
				break;
		}
		switch(Game.fireballPosition) {
			case 0:
				if(Game.currentFireballLocked)
					Game.currentFireballLocked = false;
				if(System.currentTimeMillis() % 30 == 0)
					this.fireball0.runAnimation();
				this.fireball0.drawAnimation(g, Game.WIDTH , 320 + 8,0);
				break;
			case 1:
				if(Game.fireball1Unlocked == false) {
					if(!Game.currentFireballLocked) 
						Game.currentFireballLocked = true;
					if(fireball1Price != currentFireballPrice) {
						fireballPriceImage = Game.resize(HUD.stringToMario3FontImage(Integer.toString(fireball1Price)), 30, 15);
						currentFireballPrice = fireball1Price;
					}
					if(System.currentTimeMillis() % 30 == 0)
						this.fireball1Silhouette.runAnimation();
					this.fireball1Silhouette.drawAnimation(g, Game.WIDTH , 320 + 8,0);
					HUD.clickyButton(g, buyTitle, buyTitleGlow, buyTitleClicked, Game.buy3Highlighted, Game.buy3Clicked, Game.backOnBuy3, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - 5, 320 + 7);
					g.drawImage(fireballPriceImage, Game.WIDTH - 6, 320 + 35, null);
					g.drawImage(pointsImage, Game.WIDTH - 6 + fireballPriceImage.getWidth() + 4, 320 + 35 + 7, null);
					break;
				}
				else {
					if(Game.currentFireballLocked)
						Game.currentFireballLocked = false;
					if(System.currentTimeMillis() % 30 == 0)
						this.fireball1.runAnimation();
					this.fireball1.drawAnimation(g, Game.WIDTH , 320+8,0);
					break;
				}
			case 2:
				if(Game.fireball2Unlocked == false) {
					if(!Game.currentFireballLocked) 
						Game.currentFireballLocked = true;
					if(fireball2Price != currentFireballPrice) {
						fireballPriceImage = Game.resize(HUD.stringToMario3FontImage(Integer.toString(fireball2Price)), 40, 15);
						currentFireballPrice = fireball2Price;
					}
					if(System.currentTimeMillis() % 30 == 0)
						this.fireball1Silhouette.runAnimation();
					this.fireball1Silhouette.drawAnimation(g, Game.WIDTH , 320+8,0);
					HUD.clickyButton(g, buyTitle, buyTitleGlow, buyTitleClicked, Game.buy3Highlighted, Game.buy3Clicked, Game.backOnBuy3, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - 5, 320 + 7);
					g.drawImage(fireballPriceImage, Game.WIDTH - 11, 320 + 35, null);
					g.drawImage(pointsImage, Game.WIDTH - 11 + fireballPriceImage.getWidth() + 4, 320 + 35 + 7, null);
					break;
				}
				else {
					if(Game.currentFireballLocked)
						Game.currentFireballLocked = false;
					if(System.currentTimeMillis() % 30 == 0)
						this.fireball2.runAnimation();
					this.fireball2.drawAnimation(g, Game.WIDTH , 320+8,0);
					break;
				}
			case 3:
				if(Game.fireball3Unlocked == false) {
					if(!Game.currentFireballLocked) 
						Game.currentFireballLocked = true;
					if(fireball3Price != currentFireballPrice) {
						fireballPriceImage = Game.resize(HUD.stringToMario3FontImage(Integer.toString(fireball3Price)), 50, 15);
						currentFireballPrice = fireball3Price;
					}
					if(System.currentTimeMillis() % 30 == 0)
						this.fireball3Silhouette.runAnimation();
					this.fireball3Silhouette.drawAnimation(g, Game.WIDTH , 320+9,0);
					HUD.clickyButton(g, buyTitle, buyTitleGlow, buyTitleClicked, Game.buy3Highlighted, Game.buy3Clicked, Game.backOnBuy3, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - 5, 320 + 7);
					g.drawImage(fireballPriceImage, Game.WIDTH - 16, 320 + 35, null);
					g.drawImage(pointsImage, Game.WIDTH - 16 + fireballPriceImage.getWidth() + 4, 320 + 35 + 7, null);
					break;
				}
				else {
					if(Game.currentFireballLocked)
						Game.currentFireballLocked = false;
					if(System.currentTimeMillis() % 30 == 0)
						this.fireball3.runAnimation();
					this.fireball3.drawAnimation(g, Game.WIDTH , 320+9,0);
					break;
				}
				default:
					break;
		}
		switch(Game.itemPosition) {
		case 0:
			if(Game.currentItemLocked)
				Game.currentItemLocked = false;
			if(System.currentTimeMillis() % 30 == 0)
				this.item0.runAnimation();
			this.item0.drawAnimation(g, Game.WIDTH , 420 + 8,0);
			break;
		case 1:
			if(Game.currentItemLocked)
				Game.currentItemLocked = false;
			if(System.currentTimeMillis() % 30 == 0)
				this.item1.runAnimation();
			this.item1.drawAnimation(g, Game.WIDTH , 420 + 8,0);
			break;
		case 2:
			if(Game.currentItemLocked)
				Game.currentItemLocked = false;
			if(System.currentTimeMillis() % 30 == 0)
				this.item2.runAnimation();
			this.item2.drawAnimation(g, Game.WIDTH , 420 + 8,0);
			break;
		case 3:
			if(Game.currentItemLocked)
				Game.currentItemLocked = false;
			if(System.currentTimeMillis() % 30 == 0)
				this.item3.runAnimation();
			this.item3.drawAnimation(g, Game.WIDTH , 420 + 8,0);
			break;
		case 4:
			if(Game.item4Unlocked == false) {
				if(!Game.currentItemLocked) 
					Game.currentItemLocked = true;
				if(item4Price != currentItemPrice) {
					itemPriceImage = Game.resize(HUD.stringToMario3FontImage(Integer.toString(item4Price)), 30, 15);
					currentItemPrice = item4Price;
				}
				if(System.currentTimeMillis() % 30 == 0)
					this.item1Silhouette.runAnimation();
				this.item1Silhouette.drawAnimation(g, Game.WIDTH, 420 + 6,0);
				HUD.clickyButton(g, buyTitle, buyTitleGlow, buyTitleClicked, Game.buy4Highlighted, Game.buy4Clicked, Game.backOnBuy4, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - 5, 420 + 7);
				g.drawImage(itemPriceImage, Game.WIDTH - 6, 420 + 35, null);
				g.drawImage(pointsImage, Game.WIDTH - 6 + itemPriceImage.getWidth() + 4, 420 + 35 + 7, null);
				break;
			}
			else {
				if(Game.currentItemLocked)
					Game.currentItemLocked = false;
				if(System.currentTimeMillis() % 30 == 0)
					this.item4.runAnimation();
				this.item4.drawAnimation(g, Game.WIDTH , 420+8,0);
				break;
			}
		case 5:
			if(Game.item5Unlocked == false) {
				if(!Game.currentItemLocked) 
					Game.currentItemLocked = true;
				if(item5Price != currentItemPrice) {
					itemPriceImage = Game.resize(HUD.stringToMario3FontImage(Integer.toString(item5Price)), 40, 15);
					currentItemPrice = item5Price;
				}
				if(System.currentTimeMillis() % 30 == 0)
					this.item1Silhouette.runAnimation();
				this.item1Silhouette.drawAnimation(g, Game.WIDTH, 420 + 6,0);
				HUD.clickyButton(g, buyTitle, buyTitleGlow, buyTitleClicked, Game.buy4Highlighted, Game.buy4Clicked, Game.backOnBuy4, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - 5, 420 + 7);
				g.drawImage(itemPriceImage, Game.WIDTH - 11, 420 + 35, null);
				g.drawImage(pointsImage, Game.WIDTH - 11 + itemPriceImage.getWidth() + 4, 420 + 35 + 7, null);
				break;
			}
			else {
				if(Game.currentItemLocked)
					Game.currentItemLocked = false;
				if(System.currentTimeMillis() % 30 == 0)
					this.item5.runAnimation();
				this.item5.drawAnimation(g, Game.WIDTH , 420+8,0);
				break;
			}
		case 6:
			if(Game.item6Unlocked == false) {
				if(!Game.currentItemLocked) 
					Game.currentItemLocked = true;
				if(item6Price != currentItemPrice) {
					itemPriceImage = Game.resize(HUD.stringToMario3FontImage(Integer.toString(item6Price)), 50, 15);
					currentItemPrice = item6Price;
				}
				if(System.currentTimeMillis() % 30 == 0)
					this.item1Silhouette.runAnimation();
				this.item1Silhouette.drawAnimation(g, Game.WIDTH, 420 + 6,0);
				HUD.clickyButton(g, buyTitle, buyTitleGlow, buyTitleClicked, Game.buy4Highlighted, Game.buy4Clicked, Game.backOnBuy4, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - 5, 420 + 7);
				g.drawImage(itemPriceImage, Game.WIDTH - 16, 420 + 35, null);
				g.drawImage(pointsImage, Game.WIDTH - 16 + itemPriceImage.getWidth() + 4, 420 + 35 + 7, null);
				break;
			}
			else {
				if(Game.currentItemLocked)
					Game.currentItemLocked = false;
				if(System.currentTimeMillis() % 30 == 0)
					this.item6.runAnimation();
				this.item6.drawAnimation(g, Game.WIDTH , 420+8,0);
				break;
			}
			default:
				break;
	}
	}
	
}
