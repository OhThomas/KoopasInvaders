package com.game.src.main;

import java.awt.Graphics;
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
import java.util.Properties;
import java.util.Scanner;

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
	private double y = 0;
	private double dissapearingImageY = 0;
	private boolean dissapearingImageIsOff = false;
	private boolean initalized = false;
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
			for(int i = 0; i <= game.getPlayerName().size() - 1; i++) {
				string = string + game.getPlayerName().get(i);
			}
			if(file.exists() && filee.exists() && score.exists()) {
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
				    fileWriter.write(previousFileString);
				    fileWriter.newLine();
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
    				if(dissapearingImageIsOff == false && dissapearingImageY < 16 && System.currentTimeMillis() % 15 == 0)
    					dissapearingImageY+=1;
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
    		if(velY < .00009)
    			velY = -.021;
    		else if(velY < .000009)
    			velY-= 0.000008;
    		y += velY;
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
    	String item1Unlocked = "";
    	String item2Unlocked = "";
    	String currentlySelectedCharacterSkin = "";
    	String currentlySelectedTrack = "";
    	String currentlySelectedFireball = "";
    	String currentlySelectedItem = "";
    	String volumeSliderPosition = "";
    	String sfxMusicSliderPosition = "";
    	String skipAnimations = "";
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
				item1Unlocked = prop.getProperty("item1Unlocked");
				item2Unlocked = prop.getProperty("item2Unlocked");
				currentlySelectedCharacterSkin = prop.getProperty("currentlySelectedCharacterSkin");
				currentlySelectedTrack = prop.getProperty("currentlySelectedTrack");
				currentlySelectedFireball = prop.getProperty("currentlySelectedFireball");
				currentlySelectedItem = prop.getProperty("currentlySelectedItem");
				volumeSliderPosition = prop.getProperty("volumeSliderPosition");
				sfxMusicSliderPosition = prop.getProperty("sfxMusicSliderPosition");
				skipAnimations = prop.getProperty("skipAnimations");
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
				if(item1Unlocked.equals("true"))
					Game.item1Unlocked = true;
				else
					Game.item1Unlocked = false;
				if(item2Unlocked.equals("true"))
					Game.item2Unlocked = true;
				else
					Game.item2Unlocked = false;
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
			}
			
    	} catch(FileNotFoundException e) {
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
				if(Game.item1Unlocked == true)
					settings.setProperty("item1Unlocked","true");
				else
					settings.setProperty("item1Unlocked","false");
				if(Game.item2Unlocked == true)
					settings.setProperty("item2Unlocked","true");
				else
					settings.setProperty("item2Unlocked","false");
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
				settings.setProperty("volumeSliderPosition", volumeSliderIntPosition);
				settings.setProperty("sfxMusicSliderPosition", sfxMusicSliderIntPosition);
				if(Game.skipAnimations == true)
					settings.setProperty("skipAnimations","true");
				else
					settings.setProperty("skipAnimations","false");
				settings.setProperty("Total Points: ", totalPoints);
				settings.store(output, null);
			
			
    	} catch(FileNotFoundException e) {
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
    	}
    }
    public static void resetScore() throws IOException{
    	try {
    		InputStream input = new FileInputStream("settings.properties");
			settings.load(input);
			OutputStream output = new FileOutputStream("settings.properties");
			settings.setProperty("Total Points: ", "");
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
    	}
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