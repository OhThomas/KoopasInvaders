package com.game.src.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class HUD {

	private Textures tex;
	private Game game;
	public static float HEALTH = 0;
	private float greenValue = 255;
	private long score = 0;
	private String scoreString;
	public static String currentScoreFromChainChomp = "";
	private int level = 1;
	private boolean itemObtained = false;
	private String itemName = null;
	private static double timer1 = 100;
	private static double timer2 = 100;
	private float imageTranslucent = 0;
	private double imageTranslucentVelocity = 0;
	private long imageTranslucentTimer = 0;
	private boolean imageIsGone = false;
	private long enemyHitPauseTimer = 0;
	private BufferedImage[] marioNumbersSmall = new BufferedImage[10];
	private BufferedImage[] mario3FontNumbersSmall = new BufferedImage[11];
	public static BufferedImage[] mario3Font = new BufferedImage[40];
	private BufferedImage marioAdditionSign = null;
	private BufferedImage itemFrame = null;
	private BufferedImage chainChompItemFrameDisplay = null;
	private BufferedImage bulletBillItemFrameDisplay = null;
	private BufferedImage bombOmbItemFrameDisplay = null;
	private BufferedImage cheepCheepsItemFrameDisplay = null;
	private BufferedImage ampItemFrameDisplay = null;
	private BufferedImage wigglerItemFrameDisplay = null;
	private BufferedImage lakituItemFrameDisplay = null;
	private BufferedImage pressE = null;
	private BufferedImage pressX = null;
	
	private SoundLoops itemEnemySoundLoop = null;
	
	public HUD(Textures tex, Game game){
		this.tex = tex;
		this.game = game;
		this.marioNumbersSmall = tex.marioNumbersSmall;
		this.mario3FontNumbersSmall = tex.mario3FontNumbersSmall;
		this.mario3Font = tex.mario3Font;
		this.marioAdditionSign = tex.marioNumbersSmall[11];
		this.itemFrame = tex.itemFrame;
		this.chainChompItemFrameDisplay = tex.chainChompItemFrameDisplay;
		this.bulletBillItemFrameDisplay = tex.bulletBillItemFrameDisplay;
		this.bombOmbItemFrameDisplay = tex.bombOmbItemFrameDisplay;
		this.cheepCheepsItemFrameDisplay = tex.cheepCheepsItemFrameDisplay;
		this.ampItemFrameDisplay = tex.ampItemFrameDisplay;
		this.wigglerItemFrameDisplay = tex.wigglerItemFrameDisplay;
		this.lakituItemFrameDisplay = tex.lakituItemFrameDisplay;
		this.pressE = tex.pressE;
		this.pressX = tex.pressX;
		String itemEnemyFile = "res/Sounds/SFX/smb3_sound_effects_inventory_open_close.wav";
		SoundLoops itemEnemySoundLoops = new SoundLoops(itemEnemyFile);
		VolumeSlider.adjustSFX(itemEnemySoundLoops);
		this.itemEnemySoundLoop = itemEnemySoundLoops;
		
	}
	
	public void tick(){
		if(game.getSpawnDone4()){
			if(timer1 > 0){
				HEALTH++;
				timer1--;
			}
			if(timer1 <= 0 && HEALTH <= 0){
				if(timer2 > 0)
					timer2--;
			}
			HEALTH = Game.clamp(HEALTH, 0, 100);
			greenValue = Game.clamp(greenValue, 0, 255);
			greenValue = HEALTH*2;
		}
		if(itemObtained){
			if(imageTranslucent >= 0.99 && imageTranslucentTimer < System.currentTimeMillis()){
				imageTranslucentVelocity += -0.01;
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
	}
	
	public void render(Graphics g){
		if(!game.getUserHasPaused()){
			if(game.getSpawnDone4()){
				//HEALTH BAR APPEARS
				if(timer1 > 0){
					g.setColor(Color.gray);
					g.fillRect(15, 15, (int)HEALTH * 2, 32); //(x,x,200,x)
					g.setColor(new Color(75,(int)greenValue,0));
					g.fillRect(15, 15, (int)HEALTH * 2, 32);
					g.setColor(Color.white);
					g.drawRect(15, 15, (int)HEALTH * 2, 32); //(x,x,200,x)
					g.drawString(HEALTH+"%", 15, 15);
				}
				else if (timer1 <= 0 && HEALTH > 0){
					g.setColor(Color.gray);
					g.fillRect(15, 15, 200, 32); //(x,x,200,x)
					g.setColor(new Color(75,(int)greenValue,0));
					g.fillRect(15, 15, (int)HEALTH * 2, 32);
					g.setColor(Color.white);
					g.drawRect(15, 15, 200, 32); //(x,x,200,x)
					g.drawString(HEALTH+"%", 15, 15);
				}
				//HEALTH BAR DEPLETES
				else if(timer1 <= 0 && HEALTH <= 0){
					if(timer2 <= 0)
						return;
					g.setColor(Color.gray);
					g.fillRect(15, 15, (int)timer2 * 2, 32); //(x,x,200,x)
					g.setColor(Color.white);
					g.drawRect(15, 15, (int)timer2 * 2, 32); //(x,x,200,x)
				}
			}
			scoreString = String.format("%d", this.score);
			stringToScore(g, scoreString);
			g.drawImage(itemFrame, Game.WIDTH,0, null);
			if(itemObtained == true){
					switch(itemName) {
					case "chainChompItem":
						g.drawImage(chainChompItemFrameDisplay, Game.WIDTH+2,2, null);
						break;
					case "bulletBillItem":
						g.drawImage(bulletBillItemFrameDisplay, Game.WIDTH+2,2, null);
						break;
					case "bombOmbItem":
						g.drawImage(bombOmbItemFrameDisplay, Game.WIDTH+2,2, null);
						break;
					case "cheepCheepsItem":
						g.drawImage(cheepCheepsItemFrameDisplay, Game.WIDTH+2,2, null);
						break;
					case "ampItem":
						g.drawImage(ampItemFrameDisplay, Game.WIDTH+2,2, null);
						break;
					case "wigglerItem":
						g.drawImage(wigglerItemFrameDisplay, Game.WIDTH+2,2, null);
						break;
					case "lakituItem":
						g.drawImage(lakituItemFrameDisplay, Game.WIDTH+2,2, null);
						break;
					default:
						break;
					}
				
				Graphics2D g2d = (Graphics2D)g.create();
				g2d.setComposite(makeComposite(imageTranslucent));
				//g2d.drawImage(pressE,Game.WIDTH-10,28,null);
				if(Game.gameControllerInUse)
					g2d.drawImage(pressX,Game.WIDTH-((pressE.getWidth()-26)/2),28,null);
				else
					g2d.drawImage(pressE,Game.WIDTH-((pressE.getWidth()-26)/2),28,null);
			}
			if(enemyHitPauseTimer != 0 && System.currentTimeMillis() < enemyHitPauseTimer){
				if(!this.itemEnemySoundLoop.clipIsActive()) {
					VolumeSlider.adjustSFX(itemEnemySoundLoop);
					this.itemEnemySoundLoop.play();
				}
				if(currentScoreFromChainChomp.equals("null0")) {}
				else if(currentScoreFromChainChomp.equals(""))
					stringToScore(g, this.marioNumbersSmall, (int)(Game.currentEECollisionX + Game.currentEECollisionWidth), (int)Game.currentEECollisionY, "200", false);
				else 
					stringToScore(g, this.marioNumbersSmall, (int)(Game.currentEECollisionX + Game.currentEECollisionWidth), (int)Game.currentEECollisionY, currentScoreFromChainChomp, false);
			}
			else if(enemyHitPauseTimer != 0) {
				currentScoreFromChainChomp = "";
				enemyHitPauseTimer = 0;
			}
				
		}
		else if(game.getVisualPauseTimer() > System.currentTimeMillis() || game.getPauseSoundFXTimer() > System.currentTimeMillis()){
			if(game.getSpawnDone4()) {
				if(timer1 > 0){
					g.setColor(Color.gray);
					g.fillRect(15, 15, (int)HEALTH * 2, 32); //(x,x,200,x)
					g.setColor(new Color(75,(int)greenValue,0));
					g.fillRect(15, 15, (int)HEALTH * 2, 32);
					g.setColor(Color.white);
					g.drawRect(15, 15, (int)HEALTH * 2, 32); //(x,x,200,x)
					g.drawString(HEALTH+"%", 15, 15);
				}
				else if (timer1 <= 0 && HEALTH > 0){
					g.setColor(Color.gray);
					g.fillRect(15, 15, 200, 32); //(x,x,200,x)
					g.setColor(new Color(75,(int)greenValue,0));
					g.fillRect(15, 15, (int)HEALTH * 2, 32);
					g.setColor(Color.white);
					g.drawRect(15, 15, 200, 32); //(x,x,200,x)
					g.drawString(HEALTH+"%", 15, 15);
				}
				//HEALTH BAR DEPLETES
				else if(timer1 <= 0 && HEALTH <= 0){
					if(timer2 <= 0)
						return;
					g.setColor(Color.gray);
					g.fillRect(15, 15, (int)timer2 * 2, 32); //(x,x,200,x)
					g.setColor(Color.white);
					g.drawRect(15, 15, (int)timer2 * 2, 32); //(x,x,200,x)
				}
			}
			stringToScore(g, scoreString);
			g.drawImage(itemFrame, Game.WIDTH,0, null);
			if(itemObtained == true){
				switch(itemName) {
				case "chainChompItem":
					g.drawImage(chainChompItemFrameDisplay, Game.WIDTH+2,2, null);
					break;
				case "bulletBillItem":
					g.drawImage(bulletBillItemFrameDisplay, Game.WIDTH+2,2, null);
					break;
				case "bombOmbItem":
					g.drawImage(bombOmbItemFrameDisplay, Game.WIDTH+2,2, null);
					break;
				case "cheepCheepsItem":
					g.drawImage(cheepCheepsItemFrameDisplay, Game.WIDTH+2,2, null);
					break;
				case "ampItem":
					g.drawImage(ampItemFrameDisplay, Game.WIDTH+2,2, null);
					break;
				case "wigglerItem":
					g.drawImage(wigglerItemFrameDisplay, Game.WIDTH+2,2, null);
					break;
				case "lakituItem":
					g.drawImage(lakituItemFrameDisplay, Game.WIDTH+2,2, null);
					break;
				default:
					break;
				}
				//if (itemName == "chainChompItem")
					//g.drawImage(chainChompItemFrameDisplay, Game.WIDTH+2,2, null);
				Graphics2D g2d = (Graphics2D)g.create();
				g2d.setComposite(makeComposite(imageTranslucent));
				//g2d.drawImage(pressE,Game.WIDTH-10,28,null);
				if(Game.gameControllerInUse)
					g2d.drawImage(pressX,Game.WIDTH-((pressE.getWidth()-26)/2),28,null);
				else
					g2d.drawImage(pressE,Game.WIDTH-((pressE.getWidth()-26)/2),28,null);
			}
		}
			
		//BufferedImage img = stringToBufferedImage(marioNumbersSmall, scoreString);
		//g.drawImage(img, Game.WIDTH, 0, null);
		//g.drawString("Level: " + level, 170, 64);
	}
	public void stringToScore(Graphics g, String string){
		int x = Game.WIDTH * Game.SCALE;
		int y = 0;
		int k = 0;
		for(int i = string.length() - 1; i >= 0; i--){
			if(k % 3 == 0 && k != 0){
				x -= mario3FontNumbersSmall[10].getWidth();
				g.drawImage(mario3FontNumbersSmall[10], x, y, null);
			}
			
			if(string.charAt(i) == '0'){
				x -= mario3FontNumbersSmall[0].getWidth();
				g.drawImage(mario3FontNumbersSmall[0], x, y, null);
			}else if(string.charAt(i) == '1'){
				x -= mario3FontNumbersSmall[1].getWidth();
				g.drawImage(mario3FontNumbersSmall[1], x, y, null);
			}else if(string.charAt(i) == '2'){
				x -= mario3FontNumbersSmall[2].getWidth();
				g.drawImage(mario3FontNumbersSmall[2], x, y, null);
			}else if(string.charAt(i) == '3'){
				x -= mario3FontNumbersSmall[3].getWidth();
				g.drawImage(mario3FontNumbersSmall[3], x, y, null);
			}else if(string.charAt(i) == '4'){
				x -= mario3FontNumbersSmall[4].getWidth();
				g.drawImage(mario3FontNumbersSmall[4], x, y, null);
			}else if(string.charAt(i) == '5'){
				x -= mario3FontNumbersSmall[5].getWidth();
				g.drawImage(mario3FontNumbersSmall[5], x, y, null);
			}else if(string.charAt(i) == '6'){
				x -= mario3FontNumbersSmall[6].getWidth();
				g.drawImage(mario3FontNumbersSmall[6], x, y, null);
			}else if(string.charAt(i) == '7'){
				x -= mario3FontNumbersSmall[7].getWidth();
				g.drawImage(mario3FontNumbersSmall[7], x, y, null);
			}else if(string.charAt(i) == '8'){
				x -= mario3FontNumbersSmall[8].getWidth();
				g.drawImage(mario3FontNumbersSmall[8], x, y, null);
			}else if(string.charAt(i) == '9'){
				x -= mario3FontNumbersSmall[9].getWidth();
				g.drawImage(mario3FontNumbersSmall[9], x, y, null);
			}
			k++;
		}
	}
	//COMMAS DISABLED vvv
	public void stringToScore(Graphics g, BufferedImage[] font, int xPosition, int yPosition, String string, Boolean b){
		//int k = 0;
		if(b == false){
			for(int i = string.length() - 1; i >= 0; i--){
				/*if(k % 3 == 0 && k != 0){
					xPosition -= font[10].getWidth();
					g.drawImage(font[10], xPosition, y, null);
				}*/
				/*USE SWITCH STATEMENTS INSTEAD W/ FIXED VARIABLES!!
				switch(string.charAt(i)){
					case 0:
						xPosition -= font[0].getWidth();
						g.drawImage(font[0], xPosition, yPosition, null);
						break;
					case 1:
				}
				*/
				if(string.charAt(i) == '0'){
					g.drawImage(font[0], xPosition, yPosition, null);
					xPosition -= font[0].getWidth();
				}else if(string.charAt(i) == '1'){
					g.drawImage(font[1], xPosition, yPosition, null);
					xPosition -= font[1].getWidth();
				}else if(string.charAt(i) == '2'){
					g.drawImage(font[2], xPosition, yPosition, null);
					xPosition -= font[2].getWidth();
				}else if(string.charAt(i) == '3'){
					g.drawImage(font[3], xPosition, yPosition, null);
					xPosition -= font[3].getWidth();
				}else if(string.charAt(i) == '4'){
					g.drawImage(font[4], xPosition, yPosition, null);
					xPosition -= font[4].getWidth();
				}else if(string.charAt(i) == '5'){
					g.drawImage(font[5], xPosition, yPosition, null);
					xPosition -= font[5].getWidth();
				}else if(string.charAt(i) == '6'){
					g.drawImage(font[6], xPosition, yPosition, null);
					xPosition -= font[6].getWidth();
				}else if(string.charAt(i) == '7'){
					g.drawImage(font[7], xPosition, yPosition, null);
					xPosition -= font[7].getWidth();
				}else if(string.charAt(i) == '8'){
					g.drawImage(font[8], xPosition, yPosition, null);
					xPosition -= font[8].getWidth();
				}else if(string.charAt(i) == '9'){
					g.drawImage(font[9], xPosition, yPosition, null);
					xPosition -= font[9].getWidth();
				}
				//k++;
			}
			g.drawImage(this.marioAdditionSign, xPosition-3, yPosition, null);
			xPosition -= this.marioAdditionSign.getWidth();
		}
		else{
			g.drawImage(this.marioAdditionSign, xPosition, yPosition, null);
			xPosition += this.marioAdditionSign.getWidth();
			for(int i = 0; i < string.length(); i++){
				/*if(k % 3 == 0 && k != 0){
					xPosition += font[10].getWidth();
					g.drawImage(font[10], xPosition, y, null);
				}*/
				if(string.charAt(i) == '0'){
					g.drawImage(font[0], xPosition, yPosition, null);
					xPosition += font[0].getWidth();
				}else if(string.charAt(i) == '1'){
					g.drawImage(font[1], xPosition, yPosition, null);
					xPosition += font[1].getWidth();
				}else if(string.charAt(i) == '2'){
					g.drawImage(font[2], xPosition, yPosition, null);
					xPosition += font[2].getWidth();
				}else if(string.charAt(i) == '3'){
					g.drawImage(font[3], xPosition, yPosition, null);
					xPosition += font[3].getWidth();
				}else if(string.charAt(i) == '4'){
					g.drawImage(font[4], xPosition, yPosition, null);
					xPosition += font[4].getWidth();
				}else if(string.charAt(i) == '5'){
					g.drawImage(font[5], xPosition, yPosition, null);
					xPosition += font[5].getWidth();
				}else if(string.charAt(i) == '6'){
					g.drawImage(font[6], xPosition, yPosition, null);
					xPosition += font[6].getWidth();
				}else if(string.charAt(i) == '7'){
					g.drawImage(font[7], xPosition, yPosition, null);
					xPosition += font[7].getWidth();
				}else if(string.charAt(i) == '8'){
					g.drawImage(font[8], xPosition, yPosition, null);
					xPosition += font[8].getWidth();
				}else if(string.charAt(i) == '9'){
					g.drawImage(font[9], xPosition, yPosition, null);
					xPosition += font[9].getWidth();
				}
				//k++;
			}
		}
	}
	public static BufferedImage mario3FontCharBufferedImage(char c) {
		switch(c) {
		case 'a': case 'A':
			return mario3Font[0];
		case 'b': case 'B':
			return mario3Font[1];
		case 'c': case 'C':
			return mario3Font[2];
		case 'd': case 'D':
			return mario3Font[3];
		case 'e': case 'E':
			return mario3Font[4];
		case 'f': case 'F':
			return mario3Font[5];
		case 'g': case 'G':
			return mario3Font[6];
		case 'h': case 'H':
			return mario3Font[7];
		case 'i': case 'I':
			return mario3Font[8];
		case 'j': case 'J':
			return mario3Font[9];
		case 'k': case 'K':
			return mario3Font[10];
		case 'l': case 'L':
			return mario3Font[11];
		case 'm': case 'M':
			return mario3Font[12];
		case 'n': case 'N':
			return mario3Font[13];
		case 'o': case 'O':
			return mario3Font[14];
		case 'p': case 'P':
			return mario3Font[15];
		case 'q': case 'Q':
			return mario3Font[16];
		case 'r': case 'R':
			return mario3Font[17];
		case 's': case 'S':
			return mario3Font[18];
		case 't': case 'T':
			return mario3Font[19];
		case 'u': case 'U':
			return mario3Font[20];
		case 'v': case 'V':
			return mario3Font[21];
		case 'w': case 'W':
			return mario3Font[22];
		case 'x': case 'X':
			return mario3Font[23];
		case 'y': case 'Y':
			return mario3Font[24];
		case 'z': case 'Z':
			return mario3Font[25];
		case '1':
			return mario3Font[26];
		case '2':
			return mario3Font[27];
		case '3':
			return mario3Font[28];
		case '4':
			return mario3Font[29];
		case '5':
			return mario3Font[30];
		case '6':
			return mario3Font[31];
		case '7':
			return mario3Font[32];
		case '8':
			return mario3Font[33];
		case '9':
			return mario3Font[34];
		case '0':
			return mario3Font[35];
		case '.': 
			return mario3Font[36];
		case '\'':
			return mario3Font[37];
		case '!':
			return mario3Font[38];
		case ':':
			return mario3Font[39];
		case ',':
			return mario3Font[40];
		case ' ':
			return mario3Font[41];
		case '\n':
			return mario3Font[41];
		default: return mario3Font[36];
		}
	}
	public static BufferedImage mario3FontCharBufferedImage(int i) {//possibly change name if player name distorts
		switch(i) {
		case 0:
			return mario3Font[0];
		case 1:
			return mario3Font[1];
		case 2:
			return mario3Font[2];
		case 3:
			return mario3Font[3];
		case 4:
			return mario3Font[4];
		case 5:
			return mario3Font[5];
		case 6:
			return mario3Font[6];
		case 7:
			return mario3Font[7];
		case 8:
			return mario3Font[8];
		case 9:
			return mario3Font[9];
		case 10:
			return mario3Font[10];
		case 11:
			return mario3Font[11];
		case 12:
			return mario3Font[12];
		case 13:
			return mario3Font[13];
		case 14:
			return mario3Font[14];
		case 15:
			return mario3Font[15];
		case 16:
			return mario3Font[16];
		case 17:
			return mario3Font[17];
		case 18:
			return mario3Font[18];
		case 19:
			return mario3Font[19];
		case 20:
			return mario3Font[20];
		case 21:
			return mario3Font[21];
		case 22:
			return mario3Font[22];
		case 23:
			return mario3Font[23];
		case 24:
			return mario3Font[24];
		case 25:
			return mario3Font[25];
		case 26:// 1
			return mario3Font[26];
		case 27:// 2
			return mario3Font[27];
		case 28:// 3
			return mario3Font[28];
		case 29:// 4
			return mario3Font[29];
		case 30:// 5
			return mario3Font[30];
		case 31:// 6
			return mario3Font[31];
		case 32:// 7
			return mario3Font[32];
		case 33:// 8
			return mario3Font[33];
		case 34:// 9
			return mario3Font[34];
		case 35:// 0
			return mario3Font[35];
		case 36:// .
			return mario3Font[36];
		case 37:// /
			return mario3Font[37];
		case 38:// !
			return mario3Font[38];
		case 39:// :
			return mario3Font[39];
		case 40:// ,
			return mario3Font[40];
		case 41:// ' '
			return mario3Font[41];
		case 42:// /n
			return mario3Font[41];
		default: return mario3Font[36];
		}
	}
	public static BufferedImage stringToMario3FontImage(String string) {
		BufferedImage previousImage = null;
		BufferedImage nextImage = null;
		for(int i = 0; i < string.length(); i++) {
			nextImage = mario3FontCharBufferedImage(string.charAt(i));
			previousImage = attachImages(previousImage, nextImage);
		}
		return previousImage;
	}
	public BufferedImage stringToBufferedImage(BufferedImage[] font, String string){
		BufferedImage previousImage = this.marioAdditionSign;
		BufferedImage nextImage = null;
		//int k = 1;
			for(int i = 0; i < string.length(); i++){
				/*if(k % 3 == 0 && k != 0){
					xPosition -= font[10].getWidth();
					g.drawImage(font[10], xPosition, y, null);
				}*/
				if(string.charAt(i) == '0'){
					nextImage = font[0];
				}else if(string.charAt(i) == '1'){
					nextImage = font[1];
				}else if(string.charAt(i) == '2'){
					nextImage = font[2];
				}else if(string.charAt(i) == '3'){
					nextImage = font[3];
				}else if(string.charAt(i) == '4'){
					nextImage = font[4];
				}else if(string.charAt(i) == '5'){
					nextImage = font[5];
				}else if(string.charAt(i) == '6'){
					nextImage = font[6];
				}else if(string.charAt(i) == '7'){
					nextImage = font[7];
				}else if(string.charAt(i) == '8'){
					nextImage = font[8];
				}else if(string.charAt(i) == '9'){
					nextImage = font[9];
				}
				previousImage = attachImages(previousImage, nextImage);
				//k++;
			}
		return previousImage;
	}
	public static void clickyButton(Graphics g, BufferedImage img, BufferedImage highlightedImg, BufferedImage clickedImg,
			boolean buttonHighlighted, boolean buttonClicked, boolean backOnButton, boolean mouseIsOffClickedObjectAndHeldDown,
			boolean mouseIsClickedDown, int x, int y) {
		if(!mouseIsOffClickedObjectAndHeldDown && buttonClicked || backOnButton && buttonClicked)
			g.drawImage(clickedImg,x, y, null);
		else if(buttonHighlighted && !buttonClicked && !mouseIsOffClickedObjectAndHeldDown && !mouseIsClickedDown)
			g.drawImage(highlightedImg, x, y, null);
		else
			g.drawImage(img, x, y, null);
	}
	public static BufferedImage attachImages(BufferedImage img1, BufferedImage img2){
			if(img1 == null)
				return img2;
	        BufferedImage resultImage = new BufferedImage(img1.getWidth() +
	                img2.getWidth(),img2.getHeight(),
	                BufferedImage.TYPE_INT_ARGB);
	        Graphics g = resultImage.getGraphics();
	        g.drawImage(img1, 0, 0, null);
	        g.drawImage(img2, img1.getWidth(), 0, null);
	        return resultImage;
	         
	}
	
	private AlphaComposite makeComposite(float alpha) {
		  int type = AlphaComposite.SRC_OVER;
		  return(AlphaComposite.getInstance(type, alpha));
	}
	
	public void setScore(long score){
		this.score = this.score + score;
		if(this.score < 0)
			this.score = 0;
	}
	
	public void resetScore() {
		this.score = 0;
	}
	
	public long getScore(){
		return score;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public int getLevel(){
		return level;
	}

	public boolean getItemObtained() {
		return itemObtained;
	}

	public void setItemObtained(boolean itemObtained) {
		this.itemObtained = itemObtained;
	}
	
	public long getEnemyHitPauseTimer() {
		return enemyHitPauseTimer;
	}

	public void setEnemyHitPauseTimer(long enemyHitPauseTimer) {
		this.enemyHitPauseTimer = enemyHitPauseTimer;
	}
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public static double getTimer1() {
		return HUD.timer1;
	}
	
	public static double getTimer2() {
		return HUD.timer2;
	}
	
	public void setTimer1(double timer) {
		HUD.timer1 = timer;
	}
	
	public void setTimer2(double timer) {
		HUD.timer2 = timer;
	}
	
	public void setPressE(BufferedImage img) {
		this.pressE = img;
	}
	
	public void setPressX(BufferedImage img) {
		this.pressX = img;
	}
}
