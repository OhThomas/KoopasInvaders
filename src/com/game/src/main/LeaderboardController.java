package com.game.src.main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.github.strikerx3.jxinput.XInputDevice;
import com.github.strikerx3.jxinput.natives.XInputConstants;

public class LeaderboardController {
	
	private Game game;
	private static String text;
	private ArrayList<String> names = new ArrayList<String>();
	private ArrayList<String> scores = new ArrayList<String>();
	private String stringDecoy;
	private String nameDecoy = "";
	private String scoreDecoy = "";
	private long leaderboardBeginningTimer = 0;
	private long leaderboardEndingTimer = 0;
	private double velY = 0;
	public static double y = 0;
	private double dissapearingImageY = 0;
	private boolean dissapearingImageIsOff = false;
	private boolean initalized = false;
	private long traverseTime = 0;
	public static Properties settings = new Properties();
	
	public LeaderboardController(Game game) {
		this.game = game;
	}
	
	public void fileScoreNotWritten() {
		try {
			File file = new File("Score.txt");
			if(!file.exists())
				file.createNewFile();
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
			fileWriter.write(String.valueOf((int)this.game.getHUD().getScore()));
			fileWriter.flush();
			fileWriter.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeScore() {
		try {
			String string = "";
			String scoreString = "";
			String previousFileString = "";
			String previousFileStringg = "";
			File score = new File("Score.txt");
			File file = new File("Leaderboard.txt");
			File filee = new File("NewLeaderboard.txt");
		    List<String> lines = new ArrayList<String>();
			for(int i = 0; i <= game.getPlayerName().size() - 1; i++) {
				string = string + game.getPlayerName().get(i);
			}
			if(file.exists() && filee.exists() && score.exists()) {
				if(game.getScoreEntered()) {
					
				}
				Scanner scoreInput = new Scanner(score);
				while(scoreInput.hasNextLine()) {
					scoreString = scoreInput.nextLine();
				}
				scoreInput.close();
				Scanner input = new Scanner(file);
				BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filee));
				while (input.hasNextLine()) {
				    //previousFileString += "\n";
				    previousFileString = input.nextLine();
				    if(!game.getScoreEntered()) {
				    	fileWriter.write(previousFileString);
				    	fileWriter.newLine();
				    }
				    else
				    	lines.add(previousFileString);
				}
				if(game.getScoreEntered()) {
					for(int i =0; i <= lines.size()-2; i++) {
						fileWriter.write(lines.get(i));
				    	fileWriter.newLine();
					}
				}
				input.close();
				if(previousFileString == null) {
					if(string == null)
						fileWriter.write("Nobody: " + scoreString);
					else
						fileWriter.write(string.trim() + ": " + scoreString);
				}
				else {
					if(string == null)
						fileWriter.write("Nobody: " + scoreString);
					else
						fileWriter.write(string.trim() + ": " + scoreString);
				}
				fileWriter.flush();
				fileWriter.close();
	
				Scanner inputt = new Scanner(filee);
				BufferedWriter fileWriterr = new BufferedWriter(new FileWriter(file));
				while (inputt.hasNextLine()) {
				    previousFileStringg = inputt.nextLine();
				    fileWriterr.write(previousFileStringg);
				    fileWriterr.newLine();
				}
				inputt.close();
				fileWriterr.flush();
				fileWriterr.close();
			}
			else {
				file.createNewFile();
				filee.createNewFile();
				score.createNewFile();
				Scanner scoreInput = new Scanner(score);
				while(scoreInput.hasNextLine()) {
					scoreString = scoreInput.nextLine();
				}
				scoreInput.close();
				BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filee));
				BufferedWriter fileWriterr = new BufferedWriter(new FileWriter(file));
	
				if(string == null) {
					fileWriter.write("Nobody: " + scoreString);
					fileWriterr.write("Nobody: " + scoreString);
				}
				else {
					fileWriter.write(string.trim() + ": " + scoreString);
					fileWriterr.write(string.trim() + ": " + scoreString);
				}
	
				fileWriter.flush();
				fileWriter.close();
				fileWriterr.flush();
				fileWriterr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void setTexttt(String text) {
        LeaderboardController.text = text;
    }
    
    static public String getTexttt() {
        return LeaderboardController.text;
    }
	
    public void setupText() throws IOException {
		File file = new File("Leaderboard.txt");
		if(!file.exists())
			file.createNewFile();
		Scanner input = new Scanner(file);
		while(input.hasNextLine()) {
			stringDecoy = input.nextLine();
			for(int i = 0; i < stringDecoy.length(); i++) {
				if(!nameDecoy.contains(": "))
					nameDecoy += stringDecoy.charAt(i);
				else
					scoreDecoy += stringDecoy.charAt(i);
			}
			names.add(nameDecoy);
			scores.add(scoreDecoy);
			nameDecoy = "";
			scoreDecoy = "";
		}
    }
    
    public void sortText() {
    	boolean swapped = true;
        while(swapped) {
            swapped = false;
            for(int i = 0; i < scores.size() - 1; i++) {
                if(Integer.valueOf(scores.get(i)) > Integer.valueOf(scores.get(i + 1))) {
                    String temp = scores.get(i);
                    scores.set(i, scores.get(i + 1));
                    scores.set(i + 1, temp);
                    String tempName = names.get(i);
                    names.set(i, names.get(i + 1));
                    names.set(i + 1, tempName);
                    swapped = true;
                }
            }
        }
    }
    
    public void addCommas() {
    	for(int i = scores.size() - 1; i >= 0; i --) {
    		if(i < scores.size() - 25)
    			break;
    		for(int k = scores.get(i).length() -1; k >= 0; k--) {
    			if(k % 3 == 0 && k != 0){
    				scores.set(i, LeaderboardController.insertChar(scores.get(i), ',', scores.get(i).length() -k));
    			}
    		}
    	}
    }
    
    public void displayText() {
    	int rankNumber = 1;
    	String leaderboardString = "";
    	ArrayList<String> leaderboardArrayList = new ArrayList<String>();
    	ArrayList<BufferedImage> leaderboardImage = new ArrayList<BufferedImage>();
    	for(int i = scores.size() - 1; i >= 0; i --) {
    		if(i < scores.size() - 25)//ONLY TOP 25
    			break;
    		leaderboardString = (rankNumber + ". " + names.get(i) + scores.get(i) + "\n");
    		if(leaderboardString != "")
    			leaderboardArrayList.add(leaderboardString);
    		//System.out.println(rankNumber + ". " + names.get(i) + scores.get(i) + "\n");
    		//textArea.setText(names.get(i) + scores.get(i) + "\n");
    		rankNumber++;
    		leaderboardString = "";
    	}
    	for(int i = 0; i <= leaderboardArrayList.size() -1; i++) {
    		leaderboardString = leaderboardArrayList.get(i);
    		leaderboardImage.add(game.resize(
    				game.getHUD().stringToMario3FontImage(leaderboardString),
    				game.getHUD().stringToMario3FontImage(leaderboardString).getWidth()/2,
    				game.getHUD().stringToMario3FontImage(leaderboardString).getHeight()/4));
    		leaderboardString = "";
    	}
    	game.setLeaderboardImage(leaderboardImage);
    }
    
    public void drawLeaderboard(Graphics g,ArrayList<BufferedImage> img) {
    	if(img.isEmpty())
    		return;
    	if(img.get(img.size()-1).getHeight() + ((img.size()-1) * 20) + 105 < (Game.HEIGHT * Game.SCALE)) {
    		for(int i = 0; i <= img.size()-1; i++) {
    			g.drawImage(img.get(i), 44, (i*20) + 105, null);
    		}
    	}
    	else {
    		if(leaderboardBeginningTimer == 0) {
    			leaderboardBeginningTimer = System.currentTimeMillis() + 2000;
    		}
    		if(System.currentTimeMillis() < leaderboardBeginningTimer) {
    			velY = 0;
    			y = 0;
    		}
    		if(((img.size()-1)*20) + 105 + (int)y < 90 && leaderboardEndingTimer == 0) 
    			leaderboardEndingTimer = System.currentTimeMillis() + 2000;
    		if(leaderboardEndingTimer != 0 && leaderboardEndingTimer < System.currentTimeMillis()) {
    			leaderboardEndingTimer = 0;
    			velY = 0;
    			y = 0;
    			leaderboardBeginningTimer = 0;
    		}
    		for(int i = 0; i <= img.size()-1; i++) {
    			//g.drawImage(img.get(i), 44, (i*20) + 200 + (int)y, null);
    			if( (i*20) + 105 + (int)y < 100 && (i*20) + 105 + (int)y > 90) {
    				if(dissapearingImageIsOff == false && dissapearingImageY < 16 && System.currentTimeMillis() % 15 == 0 && traverseTime != System.currentTimeMillis()) {
    					dissapearingImageY+=1;
    					traverseTime = System.currentTimeMillis();
    				}
    				else if(dissapearingImageIsOff)
    					dissapearingImageY = 0;
    				if(img.get(i).getHeight()-(int)dissapearingImageY <= 0) {
    					dissapearingImageIsOff = true;
    				}
    				else if(dissapearingImageIsOff == false)
    					g.drawImage(img.get(i).getSubimage(0,(int)dissapearingImageY,
        					img.get(i).getWidth(), img.get(i).getHeight()-(int)dissapearingImageY),
        					44, (i*20) + 105 + (int)y, null);
    			}
    			else if((i*20) + 105 + (int)y >= 101)
        			g.drawImage(img.get(i), 44, (i*20) + 105 + (int)y, null);
    			else if((i*20) + 105 + (int)y == 100) {
        			g.drawImage(img.get(i), 44, (i*20) + 105 + (int)y, null);
        			dissapearingImageIsOff = false;
    			}
    		}
        	if(traverseTime != System.currentTimeMillis()) {
	    		if(velY < .00009)
	    			velY = -.021;
	    		else if(velY < .000009)
	    			velY-= 0.000008;
	    		y += velY;
	        	traverseTime = System.currentTimeMillis();
    	}
    	}
	}
    /*LEADERBOARD THAT CUTS OFF RIGHT UNDER LEADERBOARD TITLE IMG
    public void drawLeaderboard(Graphics g,ArrayList<BufferedImage> img) {
    	if(img.get(img.size()-1).getHeight() + ((img.size()-1) * 20) + 200 < (Game.HEIGHT * Game.SCALE)) {
    		for(int i = 0; i <= img.size()-1; i++) {
    			g.drawImage(img.get(i), 44, (i*20) + 200, null);
    		}
    	}
    	else {
    		for(int i = 0; i <= img.size()-1; i++) {
    			//g.drawImage(img.get(i), 44, (i*20) + 200 + (int)y, null);
    			if( (i*20) + 200 + (int)y < 100 && (i*20) + 200 + (int)y > 90) {
    				if(dissapearingImageY < 1)
    					dissapearingImageY+=.001;
        			g.drawImage(img.get(i).getSubimage(0,1,
        					img.get(i).getWidth(), img.get(i).getHeight()-1),
        					44, (i*20) + 200 + (int)y, null);
    			}
    			else if((i*20) + 200 + (int)y >= 100)
        			g.drawImage(img.get(i), 44, (i*20) + 200 + (int)y, null);
    		}
    		if(velY < .000009)
    			velY-= 0.000008;
    		y += velY;
    	}
	}*/
    
    public static String insertChar(String word, char letter, int position) {
        char[] chars = word.toCharArray();
        char[] newchars = new char[word.length() + 1];

        for (int i = 0; i < word.length(); i++) {
            if (i < position)
                newchars[i] = chars[i];
            else
                newchars[i + 1] = chars[i];
        }
        newchars[position] = letter;
        return new String(newchars);
    }
    
    public void changePressXImage() {
    	if(XInputDevice.a == XInputConstants.XINPUT_GAMEPAD_X) {
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_A);
			game.getHUD().setPressX(game.getTextures().pressX);
    	}
    	else if(XInputDevice.b == XInputConstants.XINPUT_GAMEPAD_X) {
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_B);
			game.getHUD().setPressX(game.getTextures().pressX);
    	}else if(XInputDevice.x == XInputConstants.XINPUT_GAMEPAD_X) {
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_X);
			game.getHUD().setPressX(game.getTextures().pressX);
    	}else if(XInputDevice.y == XInputConstants.XINPUT_GAMEPAD_X) {
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_Y);
			game.getHUD().setPressX(game.getTextures().pressX);
    	}else if(XInputDevice.up == XInputConstants.XINPUT_GAMEPAD_X) {
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_DPAD_UP);
			game.getHUD().setPressX(game.getTextures().pressX);
    	}else if(XInputDevice.down == XInputConstants.XINPUT_GAMEPAD_X) {
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN);
			game.getHUD().setPressX(game.getTextures().pressX);
    	}else if(XInputDevice.left == XInputConstants.XINPUT_GAMEPAD_X) {
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT);
			game.getHUD().setPressX(game.getTextures().pressX);
    	}else if(XInputDevice.right == XInputConstants.XINPUT_GAMEPAD_X) {
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT);
			game.getHUD().setPressX(game.getTextures().pressX);
    	}else if(XInputDevice.lShoulder == XInputConstants.XINPUT_GAMEPAD_X) {
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER);
			game.getHUD().setPressX(game.getTextures().pressX);
    	}else if(XInputDevice.rShoulder == XInputConstants.XINPUT_GAMEPAD_X) {
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER);
			game.getHUD().setPressX(game.getTextures().pressX);
    	}else if(XInputDevice.lThumb == XInputConstants.XINPUT_GAMEPAD_X) {
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB);
			game.getHUD().setPressX(game.getTextures().pressX);
    	}else if(XInputDevice.rThumb == XInputConstants.XINPUT_GAMEPAD_X) {
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB);
			game.getHUD().setPressX(game.getTextures().pressX);
    	}else if(XInputDevice.start == XInputConstants.XINPUT_GAMEPAD_X) {
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_START);
			game.getHUD().setPressX(game.getTextures().pressX);
    	}else if(XInputDevice.back == XInputConstants.XINPUT_GAMEPAD_X) {
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_BACK);
			game.getHUD().setPressX(game.getTextures().pressX);
    	}else if(XInputDevice.guide == XInputConstants.XINPUT_GAMEPAD_X) {
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON);
			game.getHUD().setPressX(game.getTextures().pressX);
    	}else if(XInputDevice.unknown == XInputConstants.XINPUT_GAMEPAD_X) {
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_UNKNOWN);
			game.getHUD().setPressX(game.getTextures().pressX);
    	}else {
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_X);
			game.getHUD().setPressX(game.getTextures().pressX);
    	}
//    	switch(XInputDevice.x) {
//		case XInputConstants.XINPUT_GAMEPAD_A:
//			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_A);
//			game.getHUD().setPressX(game.getTextures().pressX);
//			break;
//		case XInputConstants.XINPUT_GAMEPAD_B:
//			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_B);
//			game.getHUD().setPressX(game.getTextures().pressX);
//			break;
//		case XInputConstants.XINPUT_GAMEPAD_X:
//			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_X);
//			game.getHUD().setPressX(game.getTextures().pressX);
//			break;
//		case XInputConstants.XINPUT_GAMEPAD_Y:
//			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_Y);
//			game.getHUD().setPressX(game.getTextures().pressX);
//			break;
//		case XInputConstants.XINPUT_GAMEPAD_BACK:
//			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_BACK);
//			game.getHUD().setPressX(game.getTextures().pressX);
//			break;
//		case XInputConstants.XINPUT_GAMEPAD_START:
//			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_START);
//			game.getHUD().setPressX(game.getTextures().pressX);
//			break;
//		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
//			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER);
//			game.getHUD().setPressX(game.getTextures().pressX);
//			break;
//		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
//			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER);
//			game.getHUD().setPressX(game.getTextures().pressX);
//			break;
//		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
//			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB);
//			game.getHUD().setPressX(game.getTextures().pressX);
//			break;
//		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
//			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB);
//			game.getHUD().setPressX(game.getTextures().pressX);
//			break;
//		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
//			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_DPAD_UP);
//			game.getHUD().setPressX(game.getTextures().pressX);
//			break;
//		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
//			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN);
//			game.getHUD().setPressX(game.getTextures().pressX);
//			break;
//		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
//			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT);
//			game.getHUD().setPressX(game.getTextures().pressX);
//			break;
//		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
//			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT);
//			game.getHUD().setPressX(game.getTextures().pressX);
//			break;
//		default:
//			break;
//		}
    }
    
    public static void writeToSettings(String property, String s) throws IOException {
    	InputStream input = null;
		OutputStream output;
		try {
			input = new FileInputStream("settings.properties");
			settings.load(input);
			output = new FileOutputStream("settings.properties");
			settings.setProperty(property, s);
			settings.store(output, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public static String getFromSettings(String property) throws IOException {
    	Properties prop = new Properties();
    	InputStream input = null;
    	String s = "";
		try {
			input = new FileInputStream("settings.properties");
			prop.load(input);
			s = prop.getProperty(property);
		} catch (FileNotFoundException e) {
			OutputStream output = new FileOutputStream("settings.properties");
			settings.setProperty(property, s);
			settings.store(output, null);
			//e.printStackTrace();
		}
		return s;
    }
    public void settingsSetupUnlockPositions() {
    	Game.characterSkinPosition = Game.currentlySelectedCharacterSkin;
    	game.getPlayer().changeAnimations(Game.currentlySelectedCharacterSkin);
    	
    	Game.trackPosition = Game.currentlySelectedTrack;
    	Game.fireballPosition = Game.currentlySelectedFireball;
    	Game.itemPosition = Game.currentlySelectedItem;
    }
    public void settingsSetup() throws IOException {
    	Properties prop = new Properties();
    	InputStream input = null;
    	String s = "";
    	String totalPoints = "";
    	String skin1Unlocked = "";
    	String skin2Unlocked = "";
    	String skin3Unlocked = "";
    	String track1Unlocked = "";
    	String fireball1Unlocked = "";
    	String fireball2Unlocked = "";
    	String fireball3Unlocked = "";
    	String item4Unlocked = "";
    	String item5Unlocked = "";
    	String item6Unlocked = "";
    	String currentlySelectedCharacterSkin = "";
    	String currentlySelectedTrack = "";
    	String currentlySelectedFireball = "";
    	String currentlySelectedItem = "";
    	String volumeSliderPosition = "";
    	String sfxMusicSliderPosition = "";
    	String skipAnimations = "";
    	String upKey = "";
    	String downKey = "";
    	String leftKey = "";
    	String rightKey = "";
    	String shootKey = "";
    	String itemKey = "";
    	String pauseKey = "";
    	String cancelKey = "";
    	String aButton = "";
    	String bButton = "";
    	String xButton = "";
    	String yButton = "";
    	String backButton = "";
    	String startButton = "";
    	String lShoulderButton = "";
    	String rShoulderButton = "";
    	String lThumbButton = "";
    	String rThumbButton = "";
    	String guideButton = "";
    	String unknownButton = "";
    	String upButton = "";
    	String downButton = "";
    	String leftButton = "";
    	String rightButton = "";
    	try {
    		input = new FileInputStream("settings.properties");
			prop.load(input);
			if(!prop.isEmpty()){
				totalPoints = prop.getProperty("Total Points: ");
				skin1Unlocked = prop.getProperty("skin1Unlocked");
				skin2Unlocked = prop.getProperty("skin2Unlocked");
				skin3Unlocked = prop.getProperty("skin3Unlocked");
				track1Unlocked = prop.getProperty("track1Unlocked");
				fireball1Unlocked = prop.getProperty("fireball1Unlocked");
				fireball2Unlocked = prop.getProperty("fireball2Unlocked");
				fireball3Unlocked = prop.getProperty("fireball3Unlocked");
				item4Unlocked = prop.getProperty("item4Unlocked");
				item5Unlocked = prop.getProperty("item5Unlocked");
				item6Unlocked = prop.getProperty("item6Unlocked");
				currentlySelectedCharacterSkin = prop.getProperty("currentlySelectedCharacterSkin");
				currentlySelectedTrack = prop.getProperty("currentlySelectedTrack");
				currentlySelectedFireball = prop.getProperty("currentlySelectedFireball");
				currentlySelectedItem = prop.getProperty("currentlySelectedItem");
				volumeSliderPosition = prop.getProperty("volumeSliderPosition");
				sfxMusicSliderPosition = prop.getProperty("sfxMusicSliderPosition");
				skipAnimations = prop.getProperty("skipAnimations");
				upKey = prop.getProperty("upKey");
				downKey = prop.getProperty("downKey");
				leftKey = prop.getProperty("leftKey");
				rightKey = prop.getProperty("rightKey");
				shootKey = prop.getProperty("shootKey");
				itemKey = prop.getProperty("itemKey");
				pauseKey = prop.getProperty("pauseKey");
				cancelKey = prop.getProperty("cancelKey");
				aButton = prop.getProperty("aButton");
				bButton = prop.getProperty("bButton");
				xButton = prop.getProperty("xButton");
				yButton = prop.getProperty("yButton");
				backButton = prop.getProperty("backButton");
				startButton = prop.getProperty("startButton");
				lShoulderButton = prop.getProperty("lShoulderButton");
				rShoulderButton = prop.getProperty("rShoulderButton");
				lThumbButton = prop.getProperty("lThumbButton");
				rThumbButton = prop.getProperty("rThumbButton");
				guideButton = prop.getProperty("guideButton");
				unknownButton = prop.getProperty("unknownButton");
				upButton = prop.getProperty("upButton");
				downButton = prop.getProperty("downButton");
				leftButton = prop.getProperty("leftButton");
				rightButton = prop.getProperty("rightButton");
				if(!totalPoints.equals(""))
					Game.totalPoints = Integer.valueOf(totalPoints);
				if(skin1Unlocked.equals("true"))
					Game.skin1Unlocked = true;
				else
					Game.skin1Unlocked = false;
				if(skin2Unlocked.equals("true"))
					Game.skin2Unlocked = true;
				else
					Game.skin2Unlocked = false;
				if(skin3Unlocked.equals("true"))
					Game.skin3Unlocked = true;
				else
					Game.skin3Unlocked = false;
				if(track1Unlocked.equals("true"))
					Game.track1Unlocked = true;
				else
					Game.track1Unlocked = false;
				if(fireball1Unlocked.equals("true"))
					Game.fireball1Unlocked = true;
				else
					Game.fireball1Unlocked = false;
				if(fireball2Unlocked.equals("true"))
					Game.fireball2Unlocked = true;
				else
					Game.fireball2Unlocked = false;
				if(fireball3Unlocked.equals("true"))
					Game.fireball3Unlocked = true;
				else
					Game.fireball3Unlocked = false;
				if(item4Unlocked.equals("true"))
					Game.item4Unlocked = true;
				else
					Game.item4Unlocked = false;
				if(item5Unlocked.equals("true"))
					Game.item5Unlocked = true;
				else
					Game.item5Unlocked = false;
				if(item6Unlocked.equals("true"))
					Game.item6Unlocked = true;
				else
					Game.item6Unlocked = false;
				switch(currentlySelectedCharacterSkin) {
					case "0":
						Game.currentlySelectedCharacterSkin = 0;
						break;
					case "1":
						Game.currentlySelectedCharacterSkin = 1;
						break;
					case "2":
						Game.currentlySelectedCharacterSkin = 2;
						break;
					case "3":
						Game.currentlySelectedCharacterSkin = 3;
						break;
					default: 
						break;
				}
				switch(currentlySelectedTrack) {
				case "0":
					Game.currentlySelectedTrack = 0;
					break;
				case "1":
					Game.currentlySelectedTrack = 1;
					break;
				case "2":
					Game.currentlySelectedTrack = 2;
					break;
				case "3":
					Game.currentlySelectedTrack = 3;
					break;
				default: 
					break;
				}
				switch(currentlySelectedFireball) {
				case "0":
					Game.currentlySelectedFireball = 0;
					break;
				case "1":
					Game.currentlySelectedFireball = 1;
					break;
				case "2":
					Game.currentlySelectedFireball = 2;
					break;
				case "3":
					Game.currentlySelectedFireball = 3;
					break;
				default: 
					break;
				}
				switch(currentlySelectedItem) {
				case "0":
					Game.currentlySelectedItem = 0;
					break;
				case "1":
					Game.currentlySelectedItem = 1;
					break;
				case "2":
					Game.currentlySelectedItem = 2;
					break;
				case "3":
					Game.currentlySelectedItem = 3;
					break;
				case "4":
					Game.currentlySelectedItem = 4;
					break;
				case "5":
					Game.currentlySelectedItem = 5;
					break;
				case "6":
					Game.currentlySelectedItem = 6;
					break;
				default: 
					break;
				}
				switch(volumeSliderPosition) {
				case "1":
					Game.volumeSliderPosition = 1;
					break;
				case "2":
					Game.volumeSliderPosition = 2;
					break;
				case "3":
					Game.volumeSliderPosition = 3;
					break;
				case "4":
					Game.volumeSliderPosition = 4;
					break;
				case "5":
					Game.volumeSliderPosition = 5;
					break;
				default: 
					break;
			}
				switch(sfxMusicSliderPosition) {
				case "1":
					Game.sfxMusicSliderPosition = 1;
					break;
				case "2":
					Game.sfxMusicSliderPosition = 2;
					break;
				case "3":
					Game.sfxMusicSliderPosition = 3;
					break;
				case "4":
					Game.sfxMusicSliderPosition = 4;
					break;
				case "5":
					Game.sfxMusicSliderPosition = 5;
					break;
				default: 
					break;
			}
				if(skipAnimations.equals("true"))
					Game.skipAnimations = true;
				else
					Game.skipAnimations = false;
				Game.upKey = Integer.valueOf(upKey);
				Game.downKey = Integer.valueOf(downKey);
				Game.leftKey = Integer.valueOf(leftKey);
				Game.rightKey = Integer.valueOf(rightKey);
				Game.shootKey = Integer.valueOf(shootKey);
				Game.itemKey = Integer.valueOf(itemKey);
				switch(Game.itemKey) {
				case KeyEvent.VK_A:
					game.getTextures().changePressE(0);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_B:
					game.getTextures().changePressE(1);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_C:
					game.getTextures().changePressE(2);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_D:
					game.getTextures().changePressE(3);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_E:
					game.getTextures().changePressE(4);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_F:
					game.getTextures().changePressE(5);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_G:
					game.getTextures().changePressE(6);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_H:
					game.getTextures().changePressE(7);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_I:
					game.getTextures().changePressE(8);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_J:
					game.getTextures().changePressE(9);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_K:
					game.getTextures().changePressE(10);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_L:
					game.getTextures().changePressE(11);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_M:
					game.getTextures().changePressE(12);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_N:
					game.getTextures().changePressE(13);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_O:
					game.getTextures().changePressE(14);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_P:
					game.getTextures().changePressE(15);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_Q:
					game.getTextures().changePressE(16);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_R:
					game.getTextures().changePressE(17);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_S:
					game.getTextures().changePressE(18);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_T:
					game.getTextures().changePressE(19);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_U:
					game.getTextures().changePressE(20);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_V:
					game.getTextures().changePressE(21);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_W:
					game.getTextures().changePressE(22);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_X:
					game.getTextures().changePressE(23);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_Y:
					game.getTextures().changePressE(24);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_Z:
					game.getTextures().changePressE(25);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_0:
					game.getTextures().changePressE(26);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_1:
					game.getTextures().changePressE(27);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_2:
					game.getTextures().changePressE(28);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_3:
					game.getTextures().changePressE(29);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_4:
					game.getTextures().changePressE(30);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_5:
					game.getTextures().changePressE(31);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_6:
					game.getTextures().changePressE(32);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_7:
					game.getTextures().changePressE(33);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_8:
					game.getTextures().changePressE(34);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_9:
					game.getTextures().changePressE(35);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_NUMPAD0:
					game.getTextures().changePressE(36);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_NUMPAD1:
					game.getTextures().changePressE(37);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_NUMPAD2:
					game.getTextures().changePressE(38);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_NUMPAD3:
					game.getTextures().changePressE(39);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_NUMPAD4:
					game.getTextures().changePressE(40);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_NUMPAD5:
					game.getTextures().changePressE(41);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_NUMPAD6:
					game.getTextures().changePressE(42);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_NUMPAD7:
					game.getTextures().changePressE(43);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_NUMPAD8:
					game.getTextures().changePressE(44);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_NUMPAD9:
					game.getTextures().changePressE(45);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_SPACE:
					game.getTextures().changePressE(46);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_BACK_SPACE:
					game.getTextures().changePressE(47);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_ESCAPE:
					game.getTextures().changePressE(48);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_SHIFT:
					game.getTextures().changePressE(49);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_ENTER:
					game.getTextures().changePressE(50);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_COMMA:
					game.getTextures().changePressE(51);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_PERIOD:
					game.getTextures().changePressE(52);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_SLASH:
					game.getTextures().changePressE(53);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_UP: case KeyEvent.VK_KP_UP:
					game.getTextures().changePressE(54);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_DOWN: case KeyEvent.VK_KP_DOWN:
					game.getTextures().changePressE(55);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_LEFT: case KeyEvent.VK_KP_LEFT:
					game.getTextures().changePressE(56);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				case KeyEvent.VK_RIGHT: case KeyEvent.VK_KP_RIGHT:
					game.getTextures().changePressE(57);
					game.getHUD().setPressE(game.getTextures().pressE);
					break;
				default:
					break;
				}
				Game.pauseKey = Integer.valueOf(pauseKey);
				Game.cancelKey = Integer.valueOf(cancelKey);
				XInputDevice.a = Short.valueOf(aButton);//shoot
				XInputDevice.b = Short.valueOf(bButton);
				XInputDevice.x = Short.valueOf(xButton);//item
				changePressXImage();
				XInputDevice.y = Short.valueOf(yButton);
				XInputDevice.back = Short.valueOf(backButton);//cancel
				XInputDevice.start = Short.valueOf(startButton);//pause
				XInputDevice.lShoulder = Short.valueOf(lShoulderButton);
				XInputDevice.rShoulder = Short.valueOf(rShoulderButton);
				XInputDevice.lThumb = Short.valueOf(lThumbButton);
				XInputDevice.rThumb = Short.valueOf(rThumbButton);
				XInputDevice.guide = Short.valueOf(guideButton);
				XInputDevice.unknown = Short.valueOf(unknownButton);
				XInputDevice.up = Short.valueOf(upButton);//up
				XInputDevice.down = Short.valueOf(downButton);//down
				XInputDevice.left = Short.valueOf(leftButton);//left
				XInputDevice.right = Short.valueOf(rightButton);//right
				game.getControlsController().updateControls();
			}
			
    	} catch(FileNotFoundException e) {
			OutputStream output = new FileOutputStream("settings.properties");
			settings.setProperty("skin1Unlocked","false");
			settings.setProperty("skin2Unlocked","false");
			settings.setProperty("skin3Unlocked","false");
			settings.setProperty("track1Unlocked","false");
			settings.setProperty("fireball1Unlocked","false");
			settings.setProperty("fireball2Unlocked","false");
			settings.setProperty("fireball3Unlocked","false");
			settings.setProperty("item4Unlocked","false");
			settings.setProperty("item5Unlocked","false");
			settings.setProperty("item6Unlocked","false");
			settings.setProperty("currentlySelectedCharacterSkin", "0");
			settings.setProperty("currentlySelectedTrack", "0");
			settings.setProperty("currentlySelectedFireball", "0");
			settings.setProperty("currentlySelectedItem", "0");
			settings.setProperty("volumeSliderPosition","3");
			settings.setProperty("sfxMusicSliderPosition","3");
			settings.setProperty("skipAnimations","false");
			settings.setProperty("upKey", String.valueOf(KeyEvent.VK_W));
			settings.setProperty("downKey", String.valueOf(KeyEvent.VK_S));
			settings.setProperty("leftKey", String.valueOf(KeyEvent.VK_A));
			settings.setProperty("rightKey", String.valueOf(KeyEvent.VK_D));
			settings.setProperty("shootKey", String.valueOf(KeyEvent.VK_SPACE));
			settings.setProperty("itemKey", String.valueOf(KeyEvent.VK_E));
			settings.setProperty("pauseKey", String.valueOf(KeyEvent.VK_ENTER));
			settings.setProperty("cancelKey", String.valueOf(KeyEvent.VK_ESCAPE));
			settings.setProperty("aButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_A));
			settings.setProperty("bButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_B));
			settings.setProperty("xButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_X));
			settings.setProperty("yButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_Y));
			settings.setProperty("backButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_BACK));
			settings.setProperty("startButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_START));
			settings.setProperty("lShoulderButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER));
			settings.setProperty("rShoulderButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER));
			settings.setProperty("lThumbButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB));
			settings.setProperty("rThumbButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB));
			settings.setProperty("guideButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON));
			settings.setProperty("unknownButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_UNKNOWN));
			settings.setProperty("upButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_UP));
			settings.setProperty("downButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN));
			settings.setProperty("leftButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT));
			settings.setProperty("rightButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT));
			settings.setProperty("Total Points: ","");
			settings.store(output, null);
    	}
    	settingsSetupUnlockPositions();
    }
    
    public static void gameUnlocksToSettings() throws IOException{
    	try {
			InputStream input = new FileInputStream("settings.properties");
			settings.load(input);
			OutputStream output = new FileOutputStream("settings.properties");
			String totalPoints;
			if(Game.totalPoints > 0)
				totalPoints = String.valueOf(Game.totalPoints);
			else
				totalPoints = settings.getProperty("Total Points: ");
			String volumeSliderIntPosition = String.valueOf(Game.volumeSliderPosition);
			String sfxMusicSliderIntPosition = String.valueOf(Game.sfxMusicSliderPosition);
			String upKey = String.valueOf(Game.upKey);
			String downKey = String.valueOf(Game.downKey);
			String leftKey = String.valueOf(Game.leftKey);
			String rightKey = String.valueOf(Game.rightKey);
			String shootKey = String.valueOf(Game.shootKey);
			String itemKey = String.valueOf(Game.itemKey);
			String pauseKey = String.valueOf(Game.pauseKey);
			String cancelKey = String.valueOf(Game.cancelKey);
			String aButton = String.valueOf(XInputDevice.a);
			String bButton = String.valueOf(XInputDevice.b);
			String xButton = String.valueOf(XInputDevice.x);
			String yButton = String.valueOf(XInputDevice.y);
			String backButton = String.valueOf(XInputDevice.back);
			String startButton = String.valueOf(XInputDevice.start);
			String lShoulderButton = String.valueOf(XInputDevice.lShoulder);
			String rShoulderButton = String.valueOf(XInputDevice.rShoulder);
			String lThumbButton = String.valueOf(XInputDevice.lThumb);
			String rThumbButton = String.valueOf(XInputDevice.rThumb);
			String guideButton = String.valueOf(XInputDevice.guide);
			String unknownButton = String.valueOf(XInputDevice.unknown);
			String upButton = String.valueOf(XInputDevice.up);
			String downButton = String.valueOf(XInputDevice.down);
			String leftButton = String.valueOf(XInputDevice.left);
			String rightButton = String.valueOf(XInputDevice.right);
				if(Game.skin1Unlocked == true)
					settings.setProperty("skin1Unlocked","true");
				else
					settings.setProperty("skin1Unlocked","false");
				if(Game.skin2Unlocked == true)
					settings.setProperty("skin2Unlocked","true");
				else
					settings.setProperty("skin2Unlocked","false");
				if(Game.skin3Unlocked == true)
					settings.setProperty("skin3Unlocked","true");
				else
					settings.setProperty("skin3Unlocked","false");
				if(Game.track1Unlocked == true)
					settings.setProperty("track1Unlocked","true");
				else
					settings.setProperty("track1Unlocked","false");
				if(Game.fireball1Unlocked == true)
					settings.setProperty("fireball1Unlocked","true");
				else
					settings.setProperty("fireball1Unlocked","false");
				if(Game.fireball2Unlocked == true)
					settings.setProperty("fireball2Unlocked","true");
				else
					settings.setProperty("fireball2Unlocked","false");
				if(Game.fireball3Unlocked == true)
					settings.setProperty("fireball3Unlocked","true");
				else
					settings.setProperty("fireball3Unlocked","false");
				if(Game.item4Unlocked == true)
					settings.setProperty("item4Unlocked","true");
				else
					settings.setProperty("item4Unlocked","false");
				if(Game.item5Unlocked == true)
					settings.setProperty("item5Unlocked","true");
				else
					settings.setProperty("item5Unlocked","false");
				if(Game.item6Unlocked == true)
					settings.setProperty("item6Unlocked","true");
				else
					settings.setProperty("item6Unlocked","false");
				switch(Game.currentlySelectedCharacterSkin) {
					case 0:
						settings.setProperty("currentlySelectedCharacterSkin","0");
						break;
					case 1:
						settings.setProperty("currentlySelectedCharacterSkin","1");
						break;
					case 2:
						settings.setProperty("currentlySelectedCharacterSkin","2");
						break;
					case 3:
						settings.setProperty("currentlySelectedCharacterSkin","3");
						break;
					default:
						break;
				}
				switch(Game.currentlySelectedTrack) {
				case 0:
					settings.setProperty("currentlySelectedTrack","0");
					break;
				case 1:
					settings.setProperty("currentlySelectedTrack","1");
					break;
				case 2:
					settings.setProperty("currentlySelectedTrack","2");
					break;
				case 3:
					settings.setProperty("currentlySelectedTrack","3");
					break;
				default:
					break;
				}
				switch(Game.currentlySelectedFireball) {
				case 0:
					settings.setProperty("currentlySelectedFireball","0");
					break;
				case 1:
					settings.setProperty("currentlySelectedFireball","1");
					break;
				case 2:
					settings.setProperty("currentlySelectedFireball","2");
					break;
				case 3:
					settings.setProperty("currentlySelectedFireball","3");
					break;
				default:
					break;
				}
				switch(Game.currentlySelectedItem) {
				case 0:
					settings.setProperty("currentlySelectedItem","0");
					break;
				case 1:
					settings.setProperty("currentlySelectedItem","1");
					break;
				case 2:
					settings.setProperty("currentlySelectedItem","2");
					break;
				case 3:
					settings.setProperty("currentlySelectedItem","3");
					break;
				case 4:
					settings.setProperty("currentlySelectedItem","4");
					break;
				case 5:
					settings.setProperty("currentlySelectedItem","5");
					break;
				case 6:
					settings.setProperty("currentlySelectedItem","6");
					break;
				default:
					break;
				}
				settings.setProperty("volumeSliderPosition", volumeSliderIntPosition);
				settings.setProperty("sfxMusicSliderPosition", sfxMusicSliderIntPosition);
				if(Game.skipAnimations == true)
					settings.setProperty("skipAnimations","true");
				else
					settings.setProperty("skipAnimations","false");
				settings.setProperty("upKey", upKey);
				settings.setProperty("downKey", downKey);
				settings.setProperty("leftKey", leftKey);
				settings.setProperty("rightKey", rightKey);
				settings.setProperty("shootKey", shootKey);
				settings.setProperty("itemKey", itemKey);
				settings.setProperty("pauseKey", pauseKey);
				settings.setProperty("cancelKey", cancelKey);
				settings.setProperty("aButton", aButton);
				settings.setProperty("bButton", bButton);
				settings.setProperty("xButton", xButton);
				settings.setProperty("yButton", yButton);
				settings.setProperty("backButton", backButton);
				settings.setProperty("startButton", startButton);
				settings.setProperty("lShoulderButton", lShoulderButton);
				settings.setProperty("rShoulderButton", rShoulderButton);
				settings.setProperty("lThumbButton", lThumbButton);
				settings.setProperty("rThumbButton", rThumbButton);
				settings.setProperty("guideButton", guideButton);
				settings.setProperty("unknownButton", unknownButton);
				settings.setProperty("upButton", upButton);
				settings.setProperty("downButton", downButton);
				settings.setProperty("leftButton", leftButton);
				settings.setProperty("rightButton", rightButton);
				settings.setProperty("Total Points: ", totalPoints);
				settings.store(output, null);
			
			
    	} catch(FileNotFoundException e) {
			OutputStream output = new FileOutputStream("settings.properties");
			settings.setProperty("skin1Unlocked","false");
			settings.setProperty("skin2Unlocked","false");
			settings.setProperty("skin3Unlocked","false");
			settings.setProperty("track1Unlocked","false");
			settings.setProperty("fireball1Unlocked","false");
			settings.setProperty("fireball2Unlocked","false");
			settings.setProperty("fireball3Unlocked","false");
			settings.setProperty("item4Unlocked","false");
			settings.setProperty("item5Unlocked","false");
			settings.setProperty("item6Unlocked","false");
			settings.setProperty("currentlySelectedCharacterSkin", "0");
			settings.setProperty("currentlySelectedTrack", "0");
			settings.setProperty("currentlySelectedFireball", "0");
			settings.setProperty("currentlySelectedItem", "0");
			settings.setProperty("volumeSliderPosition","3");
			settings.setProperty("sfxMusicSliderPosition","3");
			settings.setProperty("skipAnimations","false");
			settings.setProperty("upKey", String.valueOf(KeyEvent.VK_W));
			settings.setProperty("downKey", String.valueOf(KeyEvent.VK_S));
			settings.setProperty("leftKey", String.valueOf(KeyEvent.VK_A));
			settings.setProperty("rightKey", String.valueOf(KeyEvent.VK_D));
			settings.setProperty("shootKey", String.valueOf(KeyEvent.VK_SPACE));
			settings.setProperty("itemKey", String.valueOf(KeyEvent.VK_E));
			settings.setProperty("pauseKey", String.valueOf(KeyEvent.VK_ENTER));
			settings.setProperty("cancelKey", String.valueOf(KeyEvent.VK_ESCAPE));
			settings.setProperty("aButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_A));
			settings.setProperty("bButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_B));
			settings.setProperty("xButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_X));
			settings.setProperty("yButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_Y));
			settings.setProperty("backButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_BACK));
			settings.setProperty("startButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_START));
			settings.setProperty("lShoulderButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER));
			settings.setProperty("rShoulderButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER));
			settings.setProperty("lThumbButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB));
			settings.setProperty("rThumbButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB));
			settings.setProperty("guideButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON));
			settings.setProperty("unknownButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_UNKNOWN));
			settings.setProperty("upButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_UP));
			settings.setProperty("downButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN));
			settings.setProperty("leftButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT));
			settings.setProperty("rightButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT));
			settings.setProperty("Total Points: ","");
			settings.store(output, null);
    	}
    }
    public static void resetScore() throws IOException{
    	//try {
    		File file = new File("settings.properties");
    		file.delete();
    		OutputStream output = new FileOutputStream("settings.properties");
			settings.setProperty("skin1Unlocked","false");
			settings.setProperty("skin2Unlocked","false");
			settings.setProperty("skin3Unlocked","false");
			settings.setProperty("track1Unlocked","false");
			settings.setProperty("fireball1Unlocked","false");
			settings.setProperty("fireball2Unlocked","false");
			settings.setProperty("fireball3Unlocked","false");
			settings.setProperty("item4Unlocked","false");
			settings.setProperty("item5Unlocked","false");
			settings.setProperty("item6Unlocked","false");
			settings.setProperty("currentlySelectedCharacterSkin", "0");
			settings.setProperty("currentlySelectedTrack", "0");
			settings.setProperty("currentlySelectedFireball", "0");
			settings.setProperty("currentlySelectedItem", "0");
			settings.setProperty("volumeSliderPosition","3");
			settings.setProperty("sfxMusicSliderPosition","3");
			settings.setProperty("skipAnimations","false");
			settings.setProperty("upKey", String.valueOf(KeyEvent.VK_W));
			settings.setProperty("downKey", String.valueOf(KeyEvent.VK_S));
			settings.setProperty("leftKey", String.valueOf(KeyEvent.VK_A));
			settings.setProperty("rightKey", String.valueOf(KeyEvent.VK_D));
			settings.setProperty("shootKey", String.valueOf(KeyEvent.VK_SPACE));
			settings.setProperty("itemKey", String.valueOf(KeyEvent.VK_E));
			settings.setProperty("pauseKey", String.valueOf(KeyEvent.VK_ENTER));
			settings.setProperty("cancelKey", String.valueOf(KeyEvent.VK_ESCAPE));
			settings.setProperty("aButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_A));
			settings.setProperty("bButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_B));
			settings.setProperty("xButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_X));
			settings.setProperty("yButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_Y));
			settings.setProperty("backButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_BACK));
			settings.setProperty("startButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_START));
			settings.setProperty("lShoulderButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER));
			settings.setProperty("rShoulderButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER));
			settings.setProperty("lThumbButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB));
			settings.setProperty("rThumbButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB));
			settings.setProperty("guideButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON));
			settings.setProperty("unknownButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_UNKNOWN));
			settings.setProperty("upButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_UP));
			settings.setProperty("downButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN));
			settings.setProperty("leftButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT));
			settings.setProperty("rightButton",String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT));
			settings.setProperty("Total Points: ","");
			settings.store(output, null);
    		/*
    		InputStream input = new FileInputStream("settings.properties");
			settings.load(input);
			OutputStream output = new FileOutputStream("settings.properties");
			System.out.println(settings.getProperty("Total Points: "));
			settings.setProperty("Total Points: ", "");
			System.out.println(settings.getProperty("Total Points: "));
			settings.store(output, null);
    	}catch(FileNotFoundException e) {
    		OutputStream output = new FileOutputStream("settings.properties");
			settings.setProperty("skin1Unlocked","false");
			settings.setProperty("skin2Unlocked","false");
			settings.setProperty("skin3Unlocked","false");
			settings.setProperty("track1Unlocked","false");
			settings.setProperty("fireball1Unlocked","false");
			settings.setProperty("item1Unlocked","false");
			settings.setProperty("item2Unlocked","false");
			settings.setProperty("currentlySelectedCharacterSkin", "0");
			settings.setProperty("currentlySelectedTrack", "0");
			settings.setProperty("currentlySelectedFireball", "0");
			settings.setProperty("currentlySelectedItem", "0");
			settings.setProperty("volumeSliderPosition","3");
			settings.setProperty("sfxMusicSliderPosition","3");
			settings.setProperty("skipAnimations","false");
			settings.setProperty("Total Points: ","");
			settings.store(output, null);
    	}*/
    }
	public void initialize() {
		try {
			setupText();
			sortText();
			addCommas();
			displayText();
			initalized = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVelY() {
		return velY;
	}
	
	public void setVelY(double velY){
		this.velY = velY;
	}
	
	public ArrayList<String> getNames() {
		return names;
	}

	public void setNames(ArrayList<String> names) {
		this.names = names;
	}

	public ArrayList<String> getScores() {
		return scores;
	}

	public void setScores(ArrayList<String> scores) {
		this.scores = scores;
	}

	public String getStringDecoy() {
		return stringDecoy;
	}

	public void setStringDecoy(String stringDecoy) {
		this.stringDecoy = stringDecoy;
	}

	public String getNameDecoy() {
		return nameDecoy;
	}

	public void setNameDecoy(String nameDecoy) {
		this.nameDecoy = nameDecoy;
	}

	public String getScoreDecoy() {
		return scoreDecoy;
	}

	public void setScoreDecoy(String scoreDecoy) {
		this.scoreDecoy = scoreDecoy;
	}

	public long getLeaderboardBeginningTimer() {
		return leaderboardBeginningTimer;
	}

	public void setLeaderboardBeginningTimer(long leaderboardBeginningTimer) {
		this.leaderboardBeginningTimer = leaderboardBeginningTimer;
	}

	public long getLeaderboardEndingTimer() {
		return leaderboardEndingTimer;
	}

	public void setLeaderboardEndingTimer(long leaderboardEndingTimer) {
		this.leaderboardEndingTimer = leaderboardEndingTimer;
	}

	public double getDissapearingImageY() {
		return dissapearingImageY;
	}

	public void setDissapearingImageY(double dissapearingImageY) {
		this.dissapearingImageY = dissapearingImageY;
	}

	public boolean getDissapearingImageIsOff() {
		return dissapearingImageIsOff;
	}

	public void setDissapearingImageIsOff(boolean dissapearingImageIsOff) {
		this.dissapearingImageIsOff = dissapearingImageIsOff;
	}

	public boolean getInitalized() {
		return initalized;
	}

	public void setInitalized(boolean initalized) {
		this.initalized = initalized;
	}
}