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
	private Animation fireball1Silhouette;
	private Animation item0;
	private Animation item1;
	private Animation item1Silhouette;
	private BufferedImage[] songTrackImages = new BufferedImage[12];
	private BufferedImage[] songTrackImagesSilhouette = new BufferedImage[12];
	private BufferedImage skinPriceImage;
	private BufferedImage trackPriceImage;
	private BufferedImage fireballPriceImage;
	private BufferedImage itemPriceImage;
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
	private int item1Price = 100;
	private int item2Price = 1000;
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
		fireball1Silhouette = new Animation(6,tex.fireball1Silhouette[0],tex.fireball1Silhouette[1],tex.fireball1Silhouette[2],tex.fireball1Silhouette[3]);
		item0 = new Animation(1, tex.chainChompItem[0], tex.chainChompItem[1], tex.chainChompItem[2], tex.chainChompItem[3],
				tex.chainChompItem[4], tex.chainChompItem[5], tex.chainChompItem[6], tex.chainChompItem[7],
				tex.chainChompItem[8], tex.chainChompItem[9], tex.chainChompItem[10], tex.chainChompItem[11],
				tex.chainChompItem[12], tex.chainChompItem[13], tex.chainChompItem[14], tex.chainChompItem[15],
				tex.chainChompItem[16], tex.chainChompItem[17], tex.chainChompItem[18], tex.chainChompItem[19],
				tex.chainChompItem[20], tex.chainChompItem[21], tex.chainChompItem[22], tex.chainChompItem[23],
				tex.chainChompItem[24]);
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
			this.marioSkin0.setCount(0);
			this.marioSkin1.nextFrame();
			this.marioSkin1.setCount(0);
			this.marioSkin2.nextFrame();
			this.marioSkin2.setCount(0);
			this.marioSkin3.nextFrame();
			this.marioSkin3.setCount(0);
			this.marioSkin1Silhouette.nextFrame();
			this.marioSkin1Silhouette.setCount(0);
			this.marioSkin2Silhouette.nextFrame();
			this.marioSkin2Silhouette.setCount(0);
			this.marioSkin3Silhouette.nextFrame();
			this.marioSkin3Silhouette.setCount(0);
			this.fireball0.nextFrame();
			this.fireball0.setCount(0);
			this.fireball1.nextFrame();
			this.fireball1.setCount(0);
			this.fireball1Silhouette.nextFrame();
			this.fireball1Silhouette.setCount(0);
			this.item0.nextFrame();
			this.item0.setCount(0);
			this.item1Silhouette.nextFrame();
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
						skinPriceImage = Game.resize(HUD.stringToMario3FontImage(Integer.toString(skin2Price)), 30, 15);
						currentSkinPrice = skin2Price;
					}
					if(System.currentTimeMillis() % 30 == 0)
						this.marioSkin2Silhouette.runAnimation();
					this.marioSkin2Silhouette.drawAnimation(g, Game.WIDTH , 120,0);
					HUD.clickyButton(g, buyTitle, buyTitleGlow, buyTitleClicked, Game.buy1Highlighted, Game.buy1Clicked, Game.backOnBuy1, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - 5, 120 + 7);
					g.drawImage(skinPriceImage, Game.WIDTH - 6, 120 + 35, null);
					g.drawImage(pointsImage, Game.WIDTH - 6 + skinPriceImage.getWidth() + 4, 120 + 35 + 7, null);
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
						skinPriceImage = Game.resize(HUD.stringToMario3FontImage(Integer.toString(skin3Price)), 30, 15);
						currentSkinPrice = skin3Price;
					}
					if(System.currentTimeMillis() % 30 == 0)
						this.marioSkin3Silhouette.runAnimation();
					this.marioSkin3Silhouette.drawAnimation(g, Game.WIDTH , 120,0);
					HUD.clickyButton(g, buyTitle, buyTitleGlow, buyTitleClicked, Game.buy1Highlighted, Game.buy1Clicked, Game.backOnBuy1, Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown, Game.WIDTH - 5, 120 + 7);
					g.drawImage(skinPriceImage, Game.WIDTH - 6, 120 + 35, null);
					g.drawImage(pointsImage, Game.WIDTH - 6 + skinPriceImage.getWidth() + 4, 120 + 35 + 7, null);
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
			if(Game.item1Unlocked == false) {
				if(!Game.currentItemLocked) 
					Game.currentItemLocked = true;
				if(item1Price != currentItemPrice) {
					itemPriceImage = Game.resize(HUD.stringToMario3FontImage(Integer.toString(item1Price)), 30, 15);
					currentItemPrice = item1Price;
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
				break;
			}
		case 2:
			if(Game.item2Unlocked == false) {
				if(!Game.currentItemLocked) 
					Game.currentItemLocked = true;
				if(item2Price != currentItemPrice) {
					itemPriceImage = Game.resize(HUD.stringToMario3FontImage(Integer.toString(item2Price)), 40, 15);
					currentItemPrice = item2Price;
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
				break;
			}
			default:
				break;
	}
	}
	
}
