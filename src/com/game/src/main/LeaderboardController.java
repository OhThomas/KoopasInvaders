package com.game.src.main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.game.src.main.Game.STATE;
import com.github.strikerx3.jxinput.XInputDevice;
import com.github.strikerx3.jxinput.natives.XInputConstants;

import net.java.games.input.SpecialCases;
import net.java.games.input.SwitchedValues;
import net.java.games.input.example.ReadAllEvents;

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
	public static boolean globalList = false;
	public static boolean listTrigger = false;
	public static boolean uploadTrigger = false;
	public static boolean deleteTrigger = false;
	public static boolean resetTrigger = false;
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
			fileWriter.write(EncryptionAES.encryptLocal(String.valueOf((int)this.game.getHUD().getScore())));
			fileWriter.flush();
			fileWriter.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean checkLeaderboard() {
		File file = new File("Leaderboard.txt");
		if(!file.exists())
			return false;
		//decrypt
		String line = "";
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(file));
			line = EncryptionAES.decryptLocal(fileReader.readLine());
			fileReader.close();
			if(line != null && line.contains(": KI"))
				return true;
			else
				return false;
		} catch (IOException e) {
			System.out.println("Could read from Leaderboard.txt file");
		}
		return false;
	}
	private boolean numberCheck(String str){
	     try{
	         Integer.parseInt(str);
	         return true;
	     }
	     catch(NumberFormatException nfe){
	         return false;
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
			HUD.nameEntered = string.trim();
			if(file.exists() && filee.exists() && score.exists()) {
				if(game.getScoreEntered()) {
					
				}
				Scanner scoreInput = new Scanner(score);
				while(scoreInput.hasNextLine()) {
					scoreString = EncryptionAES.decryptLocal(scoreInput.nextLine());
				}
				scoreInput.close();
				Scanner input = new Scanner(file);
				BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filee));
				while (input.hasNextLine()) {
				    //previousFileString += "\n";
				    previousFileString = EncryptionAES.decryptLocal(input.nextLine());
				    if(!game.getScoreEntered()) {
				    	fileWriter.write(EncryptionAES.encryptLocal(previousFileString));
				    	fileWriter.newLine();
				    }
				    else
				    	lines.add(previousFileString);
				}
				if(game.getScoreEntered()) {
					for(int i =0; i <= lines.size()-2; i++) {
						fileWriter.write(EncryptionAES.encryptLocal(lines.get(i)));
				    	fileWriter.newLine();
					}
				}
				input.close();
				if(previousFileString == null) {
					if(string == null)
						fileWriter.write(EncryptionAES.encryptLocal("Nobody: " + scoreString + ": " + game.KIVersion.toString()));
					else
						fileWriter.write(EncryptionAES.encryptLocal(string.trim() + ": " + scoreString + ": " + game.KIVersion.toString()));
				}
				else {
					if(string == null)
						fileWriter.write(EncryptionAES.encryptLocal("Nobody: " + scoreString + ": " + game.KIVersion.toString()));
					else
						fileWriter.write(EncryptionAES.encryptLocal(string.trim() + ": " + scoreString + ": " + game.KIVersion.toString()));
				}
				fileWriter.flush();
				fileWriter.close();
	
				Scanner inputt = new Scanner(filee);
				BufferedWriter fileWriterr = new BufferedWriter(new FileWriter(file));
				while (inputt.hasNextLine()) {
				    previousFileStringg = EncryptionAES.decryptLocal(inputt.nextLine());
				    fileWriterr.write(EncryptionAES.encryptLocal(previousFileStringg));
				    fileWriterr.newLine();
				}
				inputt.close();
				fileWriterr.flush();
				fileWriterr.close();
				//filee.delete();//new
//				score.delete();//new
			}
			else {
				file.createNewFile();
				filee.createNewFile();
				score.createNewFile();
				Scanner scoreInput = new Scanner(score);
				while(scoreInput.hasNextLine()) {
					scoreString = EncryptionAES.decryptLocal(scoreInput.nextLine());
				}
				if(scoreString == null || scoreString.equals("") || !numberCheck(scoreString))
					scoreString = String.valueOf(game.getHUD().getScore());
				if(scoreString == null || scoreString.equals("") || !numberCheck(scoreString))
					scoreString = "0";
				scoreInput.close();
				BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filee));
				BufferedWriter fileWriterr = new BufferedWriter(new FileWriter(file));
	
				if(string == null) {
					fileWriter.write(EncryptionAES.encryptLocal("Nobody: " + scoreString + ": " + game.KIVersion.toString()));
					fileWriterr.write(EncryptionAES.encryptLocal("Nobody: " + scoreString + ": " + game.KIVersion.toString()));
				}
				else {
					fileWriter.write(EncryptionAES.encryptLocal(string.trim() + ": " + scoreString + ": " + game.KIVersion.toString()));
					fileWriterr.write(EncryptionAES.encryptLocal(string.trim() + ": " + scoreString + ": " + game.KIVersion.toString()));
				}
				fileWriter.flush();
				fileWriter.close();
				fileWriterr.flush();
				fileWriterr.close();
				//filee.delete();//new
//				score.delete();//new
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
    public boolean checkForActualScores() throws IOException{
		File file = new File("Leaderboard.txt");
		String versionDecoy = "";
		if(!file.exists())
			return false;
		Scanner input = new Scanner(file);
		String stringDecoy = "";
		String nameDecoy = "";
		String scoreDecoy = "";
		while(input.hasNextLine()) {
			stringDecoy = EncryptionAES.decryptLocal(input.nextLine());
			for(int i = 0; i < stringDecoy.length(); i++) {
				if(!nameDecoy.contains(": "))
					nameDecoy += stringDecoy.charAt(i);
				else if(!scoreDecoy.contains(": "))
					scoreDecoy += stringDecoy.charAt(i);
				else 
					versionDecoy += stringDecoy.charAt(i);
			}
			if(scoreDecoy.toString().equals("0: ")) {
			}
			else {
				return true;
			}
			nameDecoy = "";
			scoreDecoy = "";
			versionDecoy = "";
		}
		input.close();
    	return false;
    }
    public void setupText(String s) throws IOException {
		stringDecoy = s;
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
    
    public void setupText() throws IOException {
		File file = new File("Leaderboard.txt");
		String versionDecoy = "";
		if(!file.exists())
			file.createNewFile();
		Scanner input = new Scanner(file);
		while(input.hasNextLine()) {
			stringDecoy = EncryptionAES.decryptLocal(input.nextLine());
			for(int i = 0; i < stringDecoy.length(); i++) {
				if(!nameDecoy.contains(": "))
					nameDecoy += stringDecoy.charAt(i);
				else if(!scoreDecoy.contains(": "))
					scoreDecoy += stringDecoy.charAt(i);
				else 
					versionDecoy += stringDecoy.charAt(i);
			}
			if(versionDecoy.toString().equals(game.KIVersion.toString())) {
				scoreDecoy = scoreDecoy.substring(0,scoreDecoy.length()-2);
				names.add(nameDecoy);
				scores.add(scoreDecoy);
			}
			nameDecoy = "";
			scoreDecoy = "";
			versionDecoy = "";
		}
		input.close();
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
    
    public void removeCommas() {
    	for(int i = scores.size() - 1; i >= 0; i --) {
    		if(i < scores.size() - 25)
    			break;
        	scores.set(i, scores.get(i).replaceAll(",", "")); 
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
    
    public String sendLeaderboard() {
    	String s = "";
    	String stringDecoyy = "";
    	String nameDecoyy = "";
    	String scoreDecoyy = "";
    	String versionDecoyy = "";
    	ArrayList<String> scoress = new ArrayList<String>();;
    	ArrayList<String> namess = new ArrayList<String>();
		File file = new File("Leaderboard.txt");
		try {
			Scanner fileInput = new Scanner(file);
			while (fileInput.hasNextLine()) {
				stringDecoyy = EncryptionAES.decryptLocal(fileInput.nextLine());
				for(int i = 0; i < stringDecoyy.length(); i++) {
					if(!nameDecoyy.contains(": "))
						nameDecoyy += stringDecoyy.charAt(i);
					else if(!scoreDecoyy.contains(": "))
						scoreDecoyy += stringDecoyy.charAt(i);
					else 
						versionDecoyy += stringDecoyy.charAt(i);
				}
				if(versionDecoyy.toString().equals(game.KIVersion.toString())) {
					scoreDecoyy = scoreDecoyy.substring(0,scoreDecoyy.length()-2);
					namess.add(nameDecoyy);
					scoress.add(scoreDecoyy);
				}
//				else {
//					System.out.println(nameDecoyy+scoreDecoyy+versionDecoyy+": old version");
//				}
				versionDecoyy = "";
				nameDecoyy = "";
				scoreDecoyy = "";
			}
			fileInput.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		boolean swapped = true;
		while(swapped) {
            swapped = false;
            for(int i = 0; i < scoress.size() - 1; i++) {
                if(Integer.valueOf(scoress.get(i)) > Integer.valueOf(scoress.get(i + 1))) {
                    String temp = scoress.get(i);
                    scoress.set(i, scoress.get(i + 1));
                    scoress.set(i + 1, temp);
                    String tempName = namess.get(i);
                    namess.set(i, namess.get(i + 1));
                    namess.set(i + 1, tempName);
                    swapped = true;
                }
            }
        }
		if(!Game.saveName.equals("")) {
			s=game.KIVersion+"\nDELETE:DELETE:"+Game.saveName+"\n";
			//saveName = "";
		}
		s=s+"UPLOAD:UPLOAD:\n";
    	for(int i = scoress.size() - 1; i >= scoress.size() - 1 - 25 && i>=0; i --) {
//			if(i >= 0) {
//				if(Game.scoreEntered)
//					s=s+"\nDELETE:DELETE:"+HUD.nameEntered+": "+HUD.score;
//				break;
//			}
    		if(scoress.get(i).equals("0")) {}
			//if(i == scoress.size() - 1) {
    		else if(i >= 0) {
				s=s+namess.get(i)+scoress.get(i)+"\n";
//				if(i == 0 && Game.scoreEntered && !Game.saveName.isEmpty())
//					s=s+"DELETE:DELETE:"+Game.saveName;
			}
			else if(i >= 1) {
				s=s+game.KIVersion+"\n"+namess.get(i)+scoress.get(i);//+"\n";
//				if(i == 0 && Game.scoreEntered && !Game.saveName.isEmpty())
//					s=s+"DELETE:DELETE:"+Game.saveName;
			}
			//s=s+"UPLOAD:UPLOAD:"+namess.get(i)+scoress.get(i);
			else
				s=s+game.KIVersion+"\n"+namess.get(i)+scoress.get(i);
    	}
		
    	//System.out.print("s = "+s);
    	return s;
    }
    
    public void drawLeaderboard(Graphics g,ArrayList<BufferedImage> img) {
    	if(img.isEmpty() && !listTrigger)
    		return;
    	else if(listTrigger) {
    		scoreDecoy = "";
    		leaderboardBeginningTimer = 0;
    		leaderboardEndingTimer = 0;
    		velY = 0;
    		y = 0;
    		dissapearingImageY = 0;
    		dissapearingImageIsOff = false;
    		traverseTime = 0;
    		names.clear();
    		scores.clear();
    		//displayText();
    		//initalized = false;
    		if(globalList) {
    			try {
    				game.connectingToServer = true;
					game.clientToServer("SEND:SEND:");
				} catch (UnknownHostException e) {
					if(Game.State == STATE.LEADERBOARD) {
						globalList = false;
					}
					e.printStackTrace();
				} catch (IOException e) {
					if(Game.State == STATE.LEADERBOARD) {
						globalList = false;
					}
					e.printStackTrace();
				}
    		}else {
    			initialize();
    		}
    		listTrigger=false;
    	}
    	else if(uploadTrigger) {
    		try {
    			if(checkForActualScores()) {
					game.connectingToServer = true;
	    			String s = "";
					if(!Game.saveName.equals("") && !Game.saveName.equals(HUD.nameEntered+": "+HUD.score)) {
						s=game.KIVersion+"\n"+"DELETE:DELETE:"+Game.saveName+"\n";
						//Game.saveName = "";
					}
					game.clientToServer(sendLeaderboard());
    			}
    		}
    		catch(UnknownHostException e){
    			
    		}
    		catch(IOException e) {
    			
    		}
			//sendLeaderboard();
			uploadTrigger = false;
    	}
    	if(!img.isEmpty() && img.get(img.size()-1).getHeight() + ((img.size()-1) * 20) + 105 < (Game.HEIGHT * Game.SCALE)) {
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
        	if(traverseTime != System.currentTimeMillis() && game.serverErrorMessageTimer+3000 < System.currentTimeMillis()) {
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
    
    public void resetLeaderboard() {
		names.clear();
		scores.clear();
    }
    
    public void resetLeaderboardImage() {
    	leaderboardBeginningTimer = 0;
    	leaderboardEndingTimer = 0;
    	velY = 0;
    	y = 0;
    	dissapearingImageY = 0;
    }
    
    public static void deleteLeaderboardFile() {
//    	System.out.println("AQUA TEEN");
		File file = new File("Leaderboard.txt");
		if(file.exists())
			file.delete();
		file = new File("NewLeaderboard.txt");
		if(file.exists())
			file.delete();
    	try {
			writeToSettings("High Score: ","");
		} catch (IOException e) {
			e.printStackTrace();
		}
    	//delete High Score: too
    }
    
    public void changePressXImageEmulated() {
    	switch(XInputDevice.itemKey) {
    	case XInputConstants.XINPUT_GAMEPAD_X:
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_X);
			game.getHUD().setPressX(game.getTextures().pressX);
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_A:
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_A);
			game.getHUD().setPressX(game.getTextures().pressX);
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_B:
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_B);
			game.getHUD().setPressX(game.getTextures().pressX);
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_Y:
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_Y);
			game.getHUD().setPressX(game.getTextures().pressX);
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER);
			game.getHUD().setPressX(game.getTextures().pressX);
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER);
			game.getHUD().setPressX(game.getTextures().pressX);
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB);
			game.getHUD().setPressX(game.getTextures().pressX);
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB);
			game.getHUD().setPressX(game.getTextures().pressX);
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_DPAD_UP);
			game.getHUD().setPressX(game.getTextures().pressX);
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN);
			game.getHUD().setPressX(game.getTextures().pressX);
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT);
			game.getHUD().setPressX(game.getTextures().pressX);
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT);
			game.getHUD().setPressX(game.getTextures().pressX);
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_BACK:
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_BACK);
			game.getHUD().setPressX(game.getTextures().pressX);
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_START:
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_START);
			game.getHUD().setPressX(game.getTextures().pressX);
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER);
			game.getHUD().setPressX(game.getTextures().pressX);
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER);
			game.getHUD().setPressX(game.getTextures().pressX);
    		break;
    	default:
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_X);
			game.getHUD().setPressX(game.getTextures().pressX);
    		break;
    	}
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
    	}else if(XInputDevice.lTrigger == XInputConstants.XINPUT_GAMEPAD_X) {
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER);
			game.getHUD().setPressX(game.getTextures().pressX);
    	}else if(XInputDevice.rTrigger == XInputConstants.XINPUT_GAMEPAD_X) {
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER);
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
    
    private void changeDirectInputItemImage() {
//    	if(ReadAllEvents.controllerName != null && !ReadAllEvents.controllerName.equals(""))
//    		game.getHUD().setPressDI(img);
    	switch(SwitchedValues.itemKey) {
    	case "":
			game.getTextures().changePressX(XInputConstants.XINPUT_GAMEPAD_A);
			game.getHUD().setPressDI(game.getTextures().pressX);
			break;
    	default:
    		break;
    	}
    }
    
    public static void writeToSettings(String property, String s) throws IOException {
    	InputStream input = null;
		OutputStream output;
		try {
			input = new FileInputStream("settings.properties");
			settings.load(input);
			output = new FileOutputStream("settings.properties");
			settings.setProperty(EncryptionAES.encryptLocal(property), EncryptionAES.encryptLocal(s));
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
			s = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal(property)));
		} catch (FileNotFoundException e) {
			OutputStream output = new FileOutputStream("settings.properties");
			settings.setProperty(EncryptionAES.encryptLocal(property), EncryptionAES.encryptLocal(s));
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
    public static boolean checkForDataCorruption(String fileString) throws IOException{
    	Properties prop = new Properties();
    	InputStream input = null;
    	String s = "";
    	String totalPoints = "";
    	String highScore = "";
    	String controllerName = "";
    	String skin1Unlocked = "";
    	String skin2Unlocked = "";
    	String skin3Unlocked = "";
    	String track4Unlocked = "";
    	String track5Unlocked = "";
    	String track6Unlocked = "";
    	String fireball1Unlocked = "";
    	String fireball2Unlocked = "";
    	String fireball3Unlocked = "";
    	String item4Unlocked = "";
    	String item5Unlocked = "";
    	String item6Unlocked = "";
    	String menuTrack4Unlocked = "";
    	String gameTrack1Set = "";
    	String gameTrack2Set = "";
    	String gameTrack3Set = "";
    	String gameTrack4Set = "";
    	String gameTrack5Set = "";
    	String gameTrack6Set = "";
    	String gameTrack7Set = "";
    	String gameTrack8Set = "";
    	String menuTrack1Set = "";
    	String menuTrack2Set = "";
    	String menuTrack3Set = "";
    	String menuTrack4Set = "";
    	String menuTrack5Set = "";
    	String currentlySelectedCharacterSkin = "";
    	String currentlySelectedTrack = "";
    	String currentlySelectedFireball = "";
    	String currentlySelectedItem = "";
    	String volumeSliderPosition = "";
    	String sfxMusicSliderPosition = "";
    	String skipAnimations = "";
    	String sendToServer = "";
    	String saveName = "";
    	String lastDisconnectedTimer = "";
    	String firstTimeRunning = "";
    	String firstTimeBeating = "";
    	String upKey = "";
    	String downKey = "";
    	String leftKey = "";
    	String rightKey = "";
    	String shootKey = "";
    	String itemKey = "";
    	String pauseKey = "";
    	String cancelKey = "";
    	String upKeyXInput = "";
    	String downKeyXInput = "";
    	String leftKeyXInput = "";
    	String rightKeyXInput = "";
    	String shootKeyXInput = "";
    	String itemKeyXInput = "";
    	String pauseKeyXInput = "";
    	String cancelKeyXInput = "";
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
    	String lTriggerButton = "";
    	String rTriggerButton = "";
    	String guideButton = "";
    	String unknownButton = "";
    	String upButton = "";
    	String downButton = "";
    	String leftButton = "";
    	String rightButton = "";
    	String upKeyDirectInput = "";
    	String downKeyDirectInput = "";
    	String leftKeyDirectInput = "";
    	String rightKeyDirectInput = "";
    	String shootKeyDirectInput = "";
    	String itemKeyDirectInput = "";
    	String pauseKeyDirectInput = "";
    	String cancelKeyDirectInput = "";
    	try {
    		input = new FileInputStream(fileString);
			prop.load(input);
			if(!prop.isEmpty()){
				totalPoints = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("Total Points: ")));
				highScore = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("High Score: ")));
				controllerName = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("Controller Name: ")));
				skin1Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("skin1Unlocked")));
				skin2Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("skin2Unlocked")));
				skin3Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("skin3Unlocked")));
				track4Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("track4Unlocked")));
				track5Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("track5Unlocked")));
				track6Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("track6Unlocked")));
				fireball1Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("fireball1Unlocked")));
				fireball2Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("fireball2Unlocked")));
				fireball3Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("fireball3Unlocked")));
				item4Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("item4Unlocked")));
				item5Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("item5Unlocked")));
				item6Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("item6Unlocked")));
				menuTrack4Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("menuTrack4Unlocked")));
				currentlySelectedCharacterSkin = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("currentlySelectedCharacterSkin")));
				currentlySelectedTrack = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("currentlySelectedTrack")));
				currentlySelectedFireball = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("currentlySelectedFireball")));
				currentlySelectedItem = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("currentlySelectedItem")));
				volumeSliderPosition = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("volumeSliderPosition")));
				sfxMusicSliderPosition = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("sfxMusicSliderPosition")));
				skipAnimations = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("skipAnimations")));
				sendToServer = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("sendToServer")));
				saveName = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("saveName")));
				lastDisconnectedTimer = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("lastDisconnectedTimer")));
				gameTrack1Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("gameTrack1Set")));
				gameTrack2Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("gameTrack2Set")));
				gameTrack3Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("gameTrack3Set")));
				gameTrack4Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("gameTrack4Set")));
				gameTrack5Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("gameTrack5Set")));
				gameTrack6Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("gameTrack6Set")));
				gameTrack7Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("gameTrack7Set")));
				gameTrack8Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("gameTrack8Set")));
				menuTrack1Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("menuTrack1Set")));
				menuTrack2Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("menuTrack2Set")));
				menuTrack3Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("menuTrack3Set")));
				menuTrack4Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("menuTrack4Set")));
				menuTrack5Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("menuTrack5Set")));
				firstTimeRunning = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("firstTimeRunning")));
				firstTimeBeating = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("firstTimeBeating")));
				upKey = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("upKey")));
				downKey = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("downKey")));
				leftKey = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("leftKey")));
				rightKey = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("rightKey")));
				shootKey = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("shootKey")));
				itemKey = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("itemKey")));
				pauseKey = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("pauseKey")));
				cancelKey = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("cancelKey")));
				upKeyXInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("upKeyXInput")));
				downKeyXInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("downKeyXInput")));
				leftKeyXInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("leftKeyXInput")));
				rightKeyXInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("rightKeyXInput")));
				shootKeyXInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("shootKeyXInput")));
				itemKeyXInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("itemKeyXInput")));
				pauseKeyXInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("pauseKeyXInput")));
				cancelKeyXInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("cancelKeyXInput")));
				aButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("aButton")));
				bButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("bButton")));
				xButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("xButton")));
				yButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("yButton")));
				backButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("backButton")));
				startButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("startButton")));
				lShoulderButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("lShoulderButton")));
				rShoulderButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("rShoulderButton")));
				lThumbButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("lThumbButton")));
				rThumbButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("rThumbButton")));
				lTriggerButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("lTriggerButton")));
				rTriggerButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("rTriggerButton")));
				guideButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("guideButton")));
				unknownButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("unknownButton")));
				upButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("upButton")));
				downButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("downButton")));
				leftButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("leftButton")));
				rightButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("rightButton")));
				upKeyDirectInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("upKeyDirectInput")));
				downKeyDirectInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("downKeyDirectInput")));
				leftKeyDirectInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("leftKeyDirectInput")));
				rightKeyDirectInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("rightKeyDirectInput")));
				shootKeyDirectInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("shootKeyDirectInput")));
				itemKeyDirectInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("itemKeyDirectInput")));
				pauseKeyDirectInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("pauseKeyDirectInput")));
				cancelKeyDirectInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("cancelKeyDirectInput")));
				//CORRUPT DATA CHECK
				if(totalPoints == null || skin1Unlocked == null || skin2Unlocked == null || skin3Unlocked == null ||
						track4Unlocked == null || track5Unlocked == null || track6Unlocked == null || fireball1Unlocked == null ||
						fireball2Unlocked == null || fireball3Unlocked == null || item4Unlocked == null || item5Unlocked == null ||
						item6Unlocked == null || menuTrack4Unlocked == null || currentlySelectedCharacterSkin == null || currentlySelectedTrack == null ||
						currentlySelectedFireball == null || currentlySelectedItem == null || volumeSliderPosition == null || sfxMusicSliderPosition == null ||
						skipAnimations == null || gameTrack1Set == null || gameTrack2Set == null || gameTrack3Set == null ||
						gameTrack4Set == null || gameTrack5Set == null || gameTrack6Set == null || gameTrack7Set == null ||
						gameTrack8Set == null || menuTrack1Set == null || menuTrack2Set == null || menuTrack3Set == null ||
						menuTrack4Set == null || menuTrack5Set == null || firstTimeRunning == null || upKey == null ||
						downKey == null || leftKey == null || rightKey == null || shootKey == null ||
						itemKey == null || pauseKey == null || cancelKey == null || aButton == null ||
						bButton == null || xButton == null || yButton == null || backButton == null ||
						startButton == null || lShoulderButton == null || rShoulderButton == null || lThumbButton == null ||
						rThumbButton == null || guideButton == null || unknownButton == null || upButton == null ||
						downButton == null || leftButton == null || rightButton == null || firstTimeBeating == null || sendToServer == null ||
						saveName == null || lastDisconnectedTimer == null || highScore == null || upKeyDirectInput == null || downKeyDirectInput == null ||
						leftKeyDirectInput == null || rightKeyDirectInput == null || shootKeyDirectInput == null || itemKeyDirectInput == null ||
						pauseKeyDirectInput == null || cancelKeyDirectInput == null || controllerName == null || lTriggerButton == null ||
						rTriggerButton == null || upKeyXInput == null || downKeyXInput == null || leftKeyXInput == null || rightKeyXInput == null ||
						shootKeyXInput == null || itemKeyXInput == null || pauseKeyXInput == null || cancelKeyXInput == null) {
					return true;
				}
				//CORRUPT DATA CHECK FOR ENCRYPTION
				if(skin1Unlocked.equals("") || skin2Unlocked.equals("") || skin3Unlocked.equals("") ||
						track4Unlocked.equals("") || track5Unlocked.equals("") || track6Unlocked.equals("") || fireball1Unlocked.equals("") ||
						fireball2Unlocked.equals("") || fireball3Unlocked.equals("") || item4Unlocked.equals("") || item5Unlocked.equals("") ||
						item6Unlocked.equals("") || menuTrack4Unlocked.equals("") || currentlySelectedCharacterSkin.equals("") || currentlySelectedTrack.equals("") ||
						currentlySelectedFireball.equals("") || currentlySelectedItem.equals("") || volumeSliderPosition.equals("") || sfxMusicSliderPosition.equals("") ||
						skipAnimations.equals("") || gameTrack1Set.equals("") || gameTrack2Set.equals("") || gameTrack3Set.equals("") ||
						gameTrack4Set.equals("") || gameTrack5Set.equals("") || gameTrack6Set.equals("") || gameTrack7Set.equals("") ||
						gameTrack8Set.equals("") || menuTrack1Set.equals("") || menuTrack2Set.equals("") || menuTrack3Set.equals("") ||
						menuTrack4Set.equals("") || menuTrack5Set.equals("") || firstTimeRunning.equals("") || upKey.equals("") ||
						downKey.equals("") || leftKey.equals("") || rightKey.equals("") || shootKey.equals("") ||
						itemKey.equals("") || pauseKey.equals("") || cancelKey.equals("") || aButton.equals("") ||
						bButton.equals("") || xButton.equals("") || yButton.equals("") || backButton.equals("") ||
						startButton.equals("") || lShoulderButton.equals("") || rShoulderButton.equals("") || lThumbButton.equals("") ||
						rThumbButton.equals("") || guideButton.equals("") || unknownButton.equals("") || upButton.equals("") ||
						downButton.equals("") || leftButton.equals("") || rightButton.equals("") || firstTimeBeating.equals("") || sendToServer.equals("") ||
						lastDisconnectedTimer.equals("") || upKeyDirectInput.equals("") || downKeyDirectInput.equals("") ||
						leftKeyDirectInput.equals("") || rightKeyDirectInput.equals("") || shootKeyDirectInput.equals("") || itemKeyDirectInput.equals("") ||
						pauseKeyDirectInput.equals("") || cancelKeyDirectInput.equals("") || lTriggerButton.equals("") ||
						rTriggerButton.equals("") || upKeyXInput.equals("") || downKeyXInput.equals("") || leftKeyXInput.equals("") || rightKeyXInput.equals("") ||
						shootKeyXInput.equals("") || itemKeyXInput.equals("") || pauseKeyXInput.equals("") || cancelKeyXInput.equals("")) {
					return true;
				}
			}
    	} catch(FileNotFoundException e) {
    		File file = new File(fileString);
    		file.createNewFile();
    	}
    	return false;
    }
    public static void loadSettingsIntoSettings(String holder,String recipitent) throws IOException{
    	Properties prop = new Properties();
    	InputStream input = null;
    	InputStream input2 = null;
    	OutputStream output = null;
    	String s = "";
    	String totalPoints = "";
    	String highScore = "";
    	String controllerName = "";
    	String skin1Unlocked = "";
    	String skin2Unlocked = "";
    	String skin3Unlocked = "";
    	String track4Unlocked = "";
    	String track5Unlocked = "";
    	String track6Unlocked = "";
    	String fireball1Unlocked = "";
    	String fireball2Unlocked = "";
    	String fireball3Unlocked = "";
    	String item4Unlocked = "";
    	String item5Unlocked = "";
    	String item6Unlocked = "";
    	String menuTrack4Unlocked = "";
    	String gameTrack1Set = "";
    	String gameTrack2Set = "";
    	String gameTrack3Set = "";
    	String gameTrack4Set = "";
    	String gameTrack5Set = "";
    	String gameTrack6Set = "";
    	String gameTrack7Set = "";
    	String gameTrack8Set = "";
    	String menuTrack1Set = "";
    	String menuTrack2Set = "";
    	String menuTrack3Set = "";
    	String menuTrack4Set = "";
    	String menuTrack5Set = "";
    	String currentlySelectedCharacterSkin = "";
    	String currentlySelectedTrack = "";
    	String currentlySelectedFireball = "";
    	String currentlySelectedItem = "";
    	String volumeSliderPosition = "";
    	String sfxMusicSliderPosition = "";
    	String skipAnimations = "";
    	String sendToServer = "";
    	String saveName = "";
    	String lastDisconnectedTimer = "";
    	String firstTimeRunning = "";
    	String firstTimeBeating = "";
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
    	String lTriggerButton = "";
    	String rTriggerButton = "";
    	String guideButton = "";
    	String unknownButton = "";
    	String upButton = "";
    	String downButton = "";
    	String leftButton = "";
    	String rightButton = "";
    	String upKeyDirectInput = "";
    	String downKeyDirectInput = "";
    	String leftKeyDirectInput = "";
    	String rightKeyDirectInput = "";
    	String shootKeyDirectInput = "";
    	String itemKeyDirectInput = "";
    	String pauseKeyDirectInput = "";
    	String cancelKeyDirectInput = "";
    	String upKeyXInput = "";
    	String downKeyXInput = "";
    	String leftKeyXInput = "";
    	String rightKeyXInput = "";
    	String shootKeyXInput = "";
    	String itemKeyXInput = "";
    	String pauseKeyXInput = "";
    	String cancelKeyXInput = "";
    	try {
    		input = new FileInputStream(holder);
			prop.load(input);
			if(!prop.isEmpty()){
				totalPoints = prop.getProperty(EncryptionAES.encryptLocal("Total Points: "));
				highScore = prop.getProperty(EncryptionAES.encryptLocal("High Score: "));
				controllerName = prop.getProperty(EncryptionAES.encryptLocal("Controller Name: "));
				skin1Unlocked = prop.getProperty(EncryptionAES.encryptLocal("skin1Unlocked"));
				skin2Unlocked = prop.getProperty(EncryptionAES.encryptLocal("skin2Unlocked"));
				skin3Unlocked = prop.getProperty(EncryptionAES.encryptLocal("skin3Unlocked"));
				track4Unlocked = prop.getProperty(EncryptionAES.encryptLocal("track4Unlocked"));
				track5Unlocked = prop.getProperty(EncryptionAES.encryptLocal("track5Unlocked"));
				track6Unlocked = prop.getProperty(EncryptionAES.encryptLocal("track6Unlocked"));
				fireball1Unlocked = prop.getProperty(EncryptionAES.encryptLocal("fireball1Unlocked"));
				fireball2Unlocked = prop.getProperty(EncryptionAES.encryptLocal("fireball2Unlocked"));
				fireball3Unlocked = prop.getProperty(EncryptionAES.encryptLocal("fireball3Unlocked"));
				item4Unlocked = prop.getProperty(EncryptionAES.encryptLocal("item4Unlocked"));
				item5Unlocked = prop.getProperty(EncryptionAES.encryptLocal("item5Unlocked"));
				item6Unlocked = prop.getProperty(EncryptionAES.encryptLocal("item6Unlocked"));
				menuTrack4Unlocked = prop.getProperty(EncryptionAES.encryptLocal("menuTrack4Unlocked"));
				currentlySelectedCharacterSkin = prop.getProperty(EncryptionAES.encryptLocal("currentlySelectedCharacterSkin"));
				currentlySelectedTrack = prop.getProperty(EncryptionAES.encryptLocal("currentlySelectedTrack"));
				currentlySelectedFireball = prop.getProperty(EncryptionAES.encryptLocal("currentlySelectedFireball"));
				currentlySelectedItem = prop.getProperty(EncryptionAES.encryptLocal("currentlySelectedItem"));
				volumeSliderPosition = prop.getProperty(EncryptionAES.encryptLocal("volumeSliderPosition"));
				sfxMusicSliderPosition = prop.getProperty(EncryptionAES.encryptLocal("sfxMusicSliderPosition"));
				skipAnimations = prop.getProperty(EncryptionAES.encryptLocal("skipAnimations"));
				sendToServer = prop.getProperty(EncryptionAES.encryptLocal("sendToServer"));
				saveName = prop.getProperty(EncryptionAES.encryptLocal("saveName"));
				lastDisconnectedTimer = prop.getProperty(EncryptionAES.encryptLocal("lastDisconnectedTimer"));
				gameTrack1Set = prop.getProperty(EncryptionAES.encryptLocal("gameTrack1Set"));
				gameTrack2Set = prop.getProperty(EncryptionAES.encryptLocal("gameTrack2Set"));
				gameTrack3Set = prop.getProperty(EncryptionAES.encryptLocal("gameTrack3Set"));
				gameTrack4Set = prop.getProperty(EncryptionAES.encryptLocal("gameTrack4Set"));
				gameTrack5Set = prop.getProperty(EncryptionAES.encryptLocal("gameTrack5Set"));
				gameTrack6Set = prop.getProperty(EncryptionAES.encryptLocal("gameTrack6Set"));
				gameTrack7Set = prop.getProperty(EncryptionAES.encryptLocal("gameTrack7Set"));
				gameTrack8Set = prop.getProperty(EncryptionAES.encryptLocal("gameTrack8Set"));
				menuTrack1Set = prop.getProperty(EncryptionAES.encryptLocal("menuTrack1Set"));
				menuTrack2Set = prop.getProperty(EncryptionAES.encryptLocal("menuTrack2Set"));
				menuTrack3Set = prop.getProperty(EncryptionAES.encryptLocal("menuTrack3Set"));
				menuTrack4Set = prop.getProperty(EncryptionAES.encryptLocal("menuTrack4Set"));
				menuTrack5Set = prop.getProperty(EncryptionAES.encryptLocal("menuTrack5Set"));
				firstTimeRunning = prop.getProperty(EncryptionAES.encryptLocal("firstTimeRunning"));
				firstTimeBeating = prop.getProperty(EncryptionAES.encryptLocal("firstTimeBeating"));
				upKey = prop.getProperty(EncryptionAES.encryptLocal("upKey"));
				downKey = prop.getProperty(EncryptionAES.encryptLocal("downKey"));
				leftKey = prop.getProperty(EncryptionAES.encryptLocal("leftKey"));
				rightKey = prop.getProperty(EncryptionAES.encryptLocal("rightKey"));
				shootKey = prop.getProperty(EncryptionAES.encryptLocal("shootKey"));
				itemKey = prop.getProperty(EncryptionAES.encryptLocal("itemKey"));
				pauseKey = prop.getProperty(EncryptionAES.encryptLocal("pauseKey"));
				cancelKey = prop.getProperty(EncryptionAES.encryptLocal("cancelKey"));
				upKeyXInput = prop.getProperty(EncryptionAES.encryptLocal("upKeyXInput"));
				downKeyXInput = prop.getProperty(EncryptionAES.encryptLocal("downKeyXInput"));
				leftKeyXInput = prop.getProperty(EncryptionAES.encryptLocal("leftKeyXInput"));
				rightKeyXInput = prop.getProperty(EncryptionAES.encryptLocal("rightKeyXInput"));
				shootKeyXInput = prop.getProperty(EncryptionAES.encryptLocal("shootKeyXInput"));
				itemKeyXInput = prop.getProperty(EncryptionAES.encryptLocal("itemKeyXInput"));
				pauseKeyXInput = prop.getProperty(EncryptionAES.encryptLocal("pauseKeyXInput"));
				cancelKeyXInput = prop.getProperty(EncryptionAES.encryptLocal("cancelKeyXInput"));
				aButton = prop.getProperty(EncryptionAES.encryptLocal("aButton"));
				bButton = prop.getProperty(EncryptionAES.encryptLocal("bButton"));
				xButton = prop.getProperty(EncryptionAES.encryptLocal("xButton"));
				yButton = prop.getProperty(EncryptionAES.encryptLocal("yButton"));
				backButton = prop.getProperty(EncryptionAES.encryptLocal("backButton"));
				startButton = prop.getProperty(EncryptionAES.encryptLocal("startButton"));
				lShoulderButton = prop.getProperty(EncryptionAES.encryptLocal("lShoulderButton"));
				rShoulderButton = prop.getProperty(EncryptionAES.encryptLocal("rShoulderButton"));
				lThumbButton = prop.getProperty(EncryptionAES.encryptLocal("lThumbButton"));
				rThumbButton = prop.getProperty(EncryptionAES.encryptLocal("rThumbButton"));
				lTriggerButton = prop.getProperty(EncryptionAES.encryptLocal("lTriggerButton"));
				rTriggerButton = prop.getProperty(EncryptionAES.encryptLocal("rTriggerButton"));
				guideButton = prop.getProperty(EncryptionAES.encryptLocal("guideButton"));
				unknownButton = prop.getProperty(EncryptionAES.encryptLocal("unknownButton"));
				upButton = prop.getProperty(EncryptionAES.encryptLocal("upButton"));
				downButton = prop.getProperty(EncryptionAES.encryptLocal("downButton"));
				leftButton = prop.getProperty(EncryptionAES.encryptLocal("leftButton"));
				rightButton = prop.getProperty(EncryptionAES.encryptLocal("rightButton"));
				upKeyDirectInput = prop.getProperty(EncryptionAES.encryptLocal("upKeyDirectInput"));
				downKeyDirectInput = prop.getProperty(EncryptionAES.encryptLocal("downKeyDirectInput"));
				leftKeyDirectInput = prop.getProperty(EncryptionAES.encryptLocal("leftKeyDirectInput"));
				rightKeyDirectInput = prop.getProperty(EncryptionAES.encryptLocal("rightKeyDirectInput"));
				shootKeyDirectInput = prop.getProperty(EncryptionAES.encryptLocal("shootKeyDirectInput"));
				itemKeyDirectInput = prop.getProperty(EncryptionAES.encryptLocal("itemKeyDirectInput"));
				pauseKeyDirectInput = prop.getProperty(EncryptionAES.encryptLocal("pauseKeyDirectInput"));
				cancelKeyDirectInput = prop.getProperty(EncryptionAES.encryptLocal("cancelKeyDirectInput"));

				if(totalPoints == null || skin1Unlocked == null || skin2Unlocked == null || skin3Unlocked == null ||
						track4Unlocked == null || track5Unlocked == null || track6Unlocked == null || fireball1Unlocked == null ||
						fireball2Unlocked == null || fireball3Unlocked == null || item4Unlocked == null || item5Unlocked == null ||
						item6Unlocked == null || menuTrack4Unlocked == null || currentlySelectedCharacterSkin == null || currentlySelectedTrack == null ||
						currentlySelectedFireball == null || currentlySelectedItem == null || volumeSliderPosition == null || sfxMusicSliderPosition == null ||
						skipAnimations == null || gameTrack1Set == null || gameTrack2Set == null || gameTrack3Set == null ||
						gameTrack4Set == null || gameTrack5Set == null || gameTrack6Set == null || gameTrack7Set == null ||
						gameTrack8Set == null || menuTrack1Set == null || menuTrack2Set == null || menuTrack3Set == null ||
						menuTrack4Set == null || menuTrack5Set == null || firstTimeRunning == null || upKey == null ||
						downKey == null || leftKey == null || rightKey == null || shootKey == null ||
						itemKey == null || pauseKey == null || cancelKey == null || aButton == null ||
						bButton == null || xButton == null || yButton == null || backButton == null ||
						startButton == null || lShoulderButton == null || rShoulderButton == null || lThumbButton == null ||
						rThumbButton == null || guideButton == null || unknownButton == null || upButton == null ||
						downButton == null || leftButton == null || rightButton == null || firstTimeBeating == null || sendToServer == null ||
						saveName == null || lastDisconnectedTimer == null || highScore == null || upKeyDirectInput == null || downKeyDirectInput == null ||
						leftKeyDirectInput == null || rightKeyDirectInput == null || shootKeyDirectInput == null || itemKeyDirectInput == null ||
						pauseKeyDirectInput == null || cancelKeyDirectInput == null || controllerName == null || lTriggerButton == null ||
						rTriggerButton == null || upKeyXInput == null || downKeyXInput == null || leftKeyXInput == null || rightKeyXInput == null ||
						shootKeyXInput == null || itemKeyXInput == null || pauseKeyXInput == null || cancelKeyXInput == null) {
					gameUnlocksToSettings();
					return;
				}
				//CORRUPT DATA CHECK FOR ENCRYPTION
				if(skin1Unlocked.equals("") || skin2Unlocked.equals("") || skin3Unlocked.equals("") ||
						track4Unlocked.equals("") || track5Unlocked.equals("") || track6Unlocked.equals("") || fireball1Unlocked.equals("") ||
						fireball2Unlocked.equals("") || fireball3Unlocked.equals("") || item4Unlocked.equals("") || item5Unlocked.equals("") ||
						item6Unlocked.equals("") || menuTrack4Unlocked.equals("") || currentlySelectedCharacterSkin.equals("") || currentlySelectedTrack.equals("") ||
						currentlySelectedFireball.equals("") || currentlySelectedItem.equals("") || volumeSliderPosition.equals("") || sfxMusicSliderPosition.equals("") ||
						skipAnimations.equals("") || gameTrack1Set.equals("") || gameTrack2Set.equals("") || gameTrack3Set.equals("") ||
						gameTrack4Set.equals("") || gameTrack5Set.equals("") || gameTrack6Set.equals("") || gameTrack7Set.equals("") ||
						gameTrack8Set.equals("") || menuTrack1Set.equals("") || menuTrack2Set.equals("") || menuTrack3Set.equals("") ||
						menuTrack4Set.equals("") || menuTrack5Set.equals("") || firstTimeRunning.equals("") || upKey.equals("") ||
						downKey.equals("") || leftKey.equals("") || rightKey.equals("") || shootKey.equals("") ||
						itemKey.equals("") || pauseKey.equals("") || cancelKey.equals("") || aButton.equals("") ||
						bButton.equals("") || xButton.equals("") || yButton.equals("") || backButton.equals("") ||
						startButton.equals("") || lShoulderButton.equals("") || rShoulderButton.equals("") || lThumbButton.equals("") ||
						rThumbButton.equals("") || guideButton.equals("") || unknownButton.equals("") || upButton.equals("") ||
						downButton.equals("") || leftButton.equals("") || rightButton.equals("") || firstTimeBeating.equals("") || sendToServer.equals("") ||
						lastDisconnectedTimer.equals("") || upKeyDirectInput.equals("") || downKeyDirectInput.equals("") ||
						leftKeyDirectInput.equals("") || rightKeyDirectInput.equals("") || shootKeyDirectInput.equals("") || itemKeyDirectInput.equals("") ||
						pauseKeyDirectInput.equals("") || cancelKeyDirectInput.equals("") || lTriggerButton.equals("") ||
						rTriggerButton.equals("") || upKeyXInput.equals("") || downKeyXInput.equals("") || leftKeyXInput.equals("") || rightKeyXInput.equals("") ||
						shootKeyXInput.equals("") || itemKeyXInput.equals("") || pauseKeyXInput.equals("") || cancelKeyXInput.equals("")) {
					gameUnlocksToSettings();
					return;
				}
				input2 = new FileInputStream(recipitent);
				settings.load(input2);
				output = new FileOutputStream(recipitent);
				if(recipitent.contains("settings.properties")) {
					s = settings.getProperty(EncryptionAES.encryptLocal("skin1Unlocked"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("skin1Unlocked"),skin1Unlocked);
					else
						settings.setProperty(EncryptionAES.encryptLocal("skin1Unlocked"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("skin2Unlocked"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("skin2Unlocked"),skin2Unlocked);
					else
						settings.setProperty(EncryptionAES.encryptLocal("skin2Unlocked"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("skin3Unlocked"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("skin3Unlocked"),skin3Unlocked);
					else
						settings.setProperty(EncryptionAES.encryptLocal("skin3Unlocked"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("track4Unlocked"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("track4Unlocked"),track4Unlocked);
					else
						settings.setProperty(EncryptionAES.encryptLocal("track4Unlocked"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("track5Unlocked"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("track5Unlocked"),track5Unlocked);
					else
						settings.setProperty(EncryptionAES.encryptLocal("track5Unlocked"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("track6Unlocked"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("track6Unlocked"),track6Unlocked);
					else
						settings.setProperty(EncryptionAES.encryptLocal("track6Unlocked"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("fireball1Unlocked"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("fireball1Unlocked"),fireball1Unlocked);
					else
						settings.setProperty(EncryptionAES.encryptLocal("fireball1Unlocked"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("fireball2Unlocked"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("fireball2Unlocked"),fireball2Unlocked);
					else
						settings.setProperty(EncryptionAES.encryptLocal("fireball2Unlocked"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("fireball3Unlocked"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("fireball3Unlocked"),fireball3Unlocked);
					else
						settings.setProperty(EncryptionAES.encryptLocal("fireball3Unlocked"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("item4Unlocked"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("item4Unlocked"),item4Unlocked);
					else
						settings.setProperty(EncryptionAES.encryptLocal("item4Unlocked"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("item5Unlocked"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("item5Unlocked"),item5Unlocked);
					else
						settings.setProperty(EncryptionAES.encryptLocal("item5Unlocked"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("item6Unlocked"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("item6Unlocked"),item6Unlocked);
					else
						settings.setProperty(EncryptionAES.encryptLocal("item6Unlocked"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("menuTrack4Unlocked"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("menuTrack4Unlocked"),menuTrack4Unlocked);
					else
						settings.setProperty(EncryptionAES.encryptLocal("menuTrack4Unlocked"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("currentlySelectedCharacterSkin"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedCharacterSkin"),currentlySelectedCharacterSkin);
					else
						settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedCharacterSkin"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("currentlySelectedTrack"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedTrack"),currentlySelectedTrack);
					else
						settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedTrack"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("currentlySelectedFireball"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedFireball"),currentlySelectedFireball);
					else
						settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedFireball"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("currentlySelectedItem"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedItem"),currentlySelectedItem);
					else
						settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedItem"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("volumeSliderPosition"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("volumeSliderPosition"),volumeSliderPosition);
					else
						settings.setProperty(EncryptionAES.encryptLocal("volumeSliderPosition"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("sfxMusicSliderPosition"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("sfxMusicSliderPosition"),sfxMusicSliderPosition);
					else
						settings.setProperty(EncryptionAES.encryptLocal("sfxMusicSliderPosition"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("skipAnimations"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("skipAnimations"),skipAnimations);
					else
						settings.setProperty(EncryptionAES.encryptLocal("skipAnimations"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("sendToServer"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("sendToServer"),sendToServer);
					else
						settings.setProperty(EncryptionAES.encryptLocal("sendToServer"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("saveName"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("saveName"),saveName);
					else
						settings.setProperty(EncryptionAES.encryptLocal("saveName"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("lastDisconnectedTimer"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("lastDisconnectedTimer"),lastDisconnectedTimer);
					else
						settings.setProperty(EncryptionAES.encryptLocal("lastDisconnectedTimer"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("gameTrack1Set"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("gameTrack1Set"),gameTrack1Set);
					else
						settings.setProperty(EncryptionAES.encryptLocal("gameTrack1Set"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("gameTrack2Set"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("gameTrack2Set"),gameTrack2Set);
					else
						settings.setProperty(EncryptionAES.encryptLocal("gameTrack2Set"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("gameTrack3Set"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("gameTrack3Set"),gameTrack3Set);
					else
						settings.setProperty(EncryptionAES.encryptLocal("gameTrack3Set"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("gameTrack4Set"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("gameTrack4Set"),gameTrack4Set);
					else
						settings.setProperty(EncryptionAES.encryptLocal("gameTrack4Set"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("gameTrack5Set"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("gameTrack5Set"),gameTrack5Set);
					else
						settings.setProperty(EncryptionAES.encryptLocal("gameTrack5Set"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("gameTrack6Set"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("gameTrack6Set"),gameTrack6Set);
					else
						settings.setProperty(EncryptionAES.encryptLocal("gameTrack6Set"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("gameTrack7Set"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("gameTrack7Set"),gameTrack7Set);
					else
						settings.setProperty(EncryptionAES.encryptLocal("gameTrack7Set"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("gameTrack8Set"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("gameTrack8Set"),gameTrack8Set);
					else
						settings.setProperty(EncryptionAES.encryptLocal("gameTrack8Set"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("menuTrack1Set"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("menuTrack1Set"),menuTrack1Set);
					else
						settings.setProperty(EncryptionAES.encryptLocal("menuTrack1Set"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("menuTrack2Set"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("menuTrack2Set"),menuTrack2Set);
					else
						settings.setProperty(EncryptionAES.encryptLocal("menuTrack2Set"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("menuTrack3Set"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("menuTrack3Set"),menuTrack3Set);
					else
						settings.setProperty(EncryptionAES.encryptLocal("menuTrack3Set"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("menuTrack4Set"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("menuTrack4Set"),menuTrack4Set);
					else
						settings.setProperty(EncryptionAES.encryptLocal("menuTrack4Set"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("menuTrack5Set"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("menuTrack5Set"),menuTrack5Set);
					else
						settings.setProperty(EncryptionAES.encryptLocal("menuTrack5Set"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("firstTimeRunning"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("firstTimeRunning"),firstTimeRunning);
					else
						settings.setProperty(EncryptionAES.encryptLocal("firstTimeRunning"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("firstTimeBeating"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("firstTimeBeating"),firstTimeBeating);
					else
						settings.setProperty(EncryptionAES.encryptLocal("firstTimeBeating"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("upKey"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("upKey"),upKey);
					else
						settings.setProperty(EncryptionAES.encryptLocal("upKey"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("downKey"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("downKey"),downKey);
					else
						settings.setProperty(EncryptionAES.encryptLocal("downKey"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("leftKey"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("leftKey"),leftKey);
					else
						settings.setProperty(EncryptionAES.encryptLocal("leftKey"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("rightKey"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("rightKey"),rightKey);
					else
						settings.setProperty(EncryptionAES.encryptLocal("rightKey"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("shootKey"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("shootKey"),shootKey);
					else
						settings.setProperty(EncryptionAES.encryptLocal("shootKey"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("itemKey"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("itemKey"),itemKey);
					else
						settings.setProperty(EncryptionAES.encryptLocal("itemKey"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("pauseKey"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("pauseKey"),pauseKey);
					else
						settings.setProperty(EncryptionAES.encryptLocal("pauseKey"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("cancelKey"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("cancelKey"),cancelKey);
					else
						settings.setProperty(EncryptionAES.encryptLocal("cancelKey"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("aButton"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("aButton"),aButton);
					else
						settings.setProperty(EncryptionAES.encryptLocal("aButton"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("bButton"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("bButton"),bButton);
					else
						settings.setProperty(EncryptionAES.encryptLocal("bButton"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("xButton"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("xButton"),xButton);
					else
						settings.setProperty(EncryptionAES.encryptLocal("xButton"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("yButton"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("yButton"),yButton);
					else
						settings.setProperty(EncryptionAES.encryptLocal("yButton"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("backButton"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("backButton"),backButton);
					else
						settings.setProperty(EncryptionAES.encryptLocal("backButton"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("startButton"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("startButton"),startButton);
					else
						settings.setProperty(EncryptionAES.encryptLocal("startButton"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("lShoulderButton"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("lShoulderButton"),lShoulderButton);
					else
						settings.setProperty(EncryptionAES.encryptLocal("lShoulderButton"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("rShoulderButton"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("rShoulderButton"),rShoulderButton);
					else
						settings.setProperty(EncryptionAES.encryptLocal("rShoulderButton"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("lThumbButton"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("lThumbButton"),lThumbButton);
					else
						settings.setProperty(EncryptionAES.encryptLocal("lThumbButton"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("rThumbButton"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("rThumbButton"),rThumbButton);
					else
						settings.setProperty(EncryptionAES.encryptLocal("rThumbButton"),s);

					s = settings.getProperty(EncryptionAES.encryptLocal("lTriggerButton"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("lTriggerButton"),lTriggerButton);
					else
						settings.setProperty(EncryptionAES.encryptLocal("lTriggerButton"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("rTriggerButton"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("rTriggerButton"),rTriggerButton);
					else
						settings.setProperty(EncryptionAES.encryptLocal("rTriggerButton"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("guideButton"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("guideButton"),guideButton);
					else
						settings.setProperty(EncryptionAES.encryptLocal("guideButton"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("unknownButton"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("unknownButton"),unknownButton);
					else
						settings.setProperty(EncryptionAES.encryptLocal("unknownButton"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("upButton"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("upButton"),upButton);
					else
						settings.setProperty(EncryptionAES.encryptLocal("upButton"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("downButton"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("downButton"),downButton);
					else
						settings.setProperty(EncryptionAES.encryptLocal("downButton"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("leftButton"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("leftButton"),leftButton);
					else
						settings.setProperty(EncryptionAES.encryptLocal("leftButton"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("rightButton"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("rightButton"),rightButton);
					else
						settings.setProperty(EncryptionAES.encryptLocal("rightButton"),s);

					s = settings.getProperty(EncryptionAES.encryptLocal("upKeyDirectInput"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("upKeyDirectInput"),upKeyDirectInput);
					else
						settings.setProperty(EncryptionAES.encryptLocal("upKeyDirectInput"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("downKeyDirectInput"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("downKeyDirectInput"),downKeyDirectInput);
					else
						settings.setProperty(EncryptionAES.encryptLocal("downKeyDirectInput"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("leftKeyDirectInput"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("leftKeyDirectInput"),leftKeyDirectInput);
					else
						settings.setProperty(EncryptionAES.encryptLocal("leftKeyDirectInput"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("rightKeyDirectInput"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("rightKeyDirectInput"),rightKeyDirectInput);
					else
						settings.setProperty(EncryptionAES.encryptLocal("rightKeyDirectInput"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("shootKeyDirectInput"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("shootKeyDirectInput"),shootKeyDirectInput);
					else
						settings.setProperty(EncryptionAES.encryptLocal("shootKeyDirectInput"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("itemKeyDirectInput"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("itemKeyDirectInput"),itemKeyDirectInput);
					else
						settings.setProperty(EncryptionAES.encryptLocal("itemKeyDirectInput"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("pauseKeyDirectInput"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("pauseKeyDirectInput"),pauseKeyDirectInput);
					else
						settings.setProperty(EncryptionAES.encryptLocal("pauseKeyDirectInput"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("cancelKeyDirectInput"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("cancelKeyDirectInput"),cancelKeyDirectInput);
					else
						settings.setProperty(EncryptionAES.encryptLocal("cancelKeyDirectInput"),s);

					s = settings.getProperty(EncryptionAES.encryptLocal("upKeyXInput"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("upKeyXInput"),upKeyXInput);
					else
						settings.setProperty(EncryptionAES.encryptLocal("upKeyXInput"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("downKeyXInput"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("downKeyXInput"),downKeyXInput);
					else
						settings.setProperty(EncryptionAES.encryptLocal("downKeyXInput"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("leftKeyXInput"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("leftKeyXInput"),leftKeyXInput);
					else
						settings.setProperty(EncryptionAES.encryptLocal("leftKeyXInput"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("rightKeyXInput"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("rightKeyXInput"),rightKeyXInput);
					else
						settings.setProperty(EncryptionAES.encryptLocal("rightKeyXInput"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("shootKeyXInput"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("shootKeyXInput"),shootKeyXInput);
					else
						settings.setProperty(EncryptionAES.encryptLocal("shootKeyXInput"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("itemKeyXInput"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("itemKeyXInput"),itemKeyXInput);
					else
						settings.setProperty(EncryptionAES.encryptLocal("itemKeyXInput"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("pauseKeyXInput"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("pauseKeyXInput"),pauseKeyXInput);
					else
						settings.setProperty(EncryptionAES.encryptLocal("pauseKeyXInput"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("cancelKeyXInput"));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("cancelKeyXInput"),cancelKeyXInput);
					else
						settings.setProperty(EncryptionAES.encryptLocal("cancelKeyXInput"),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("Total Points: "));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("Total Points: "),totalPoints);
					else
						settings.setProperty(EncryptionAES.encryptLocal("Total Points: "),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("High Score: "));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("High Score: "),highScore);
					else
						settings.setProperty(EncryptionAES.encryptLocal("High Score: "),s);
					
					s = settings.getProperty(EncryptionAES.encryptLocal("Controller Name: "));
					if(s == null)
						settings.setProperty(EncryptionAES.encryptLocal("Controller Name: "),controllerName);
					else
						settings.setProperty(EncryptionAES.encryptLocal("Controller Name: "),s);
					settings.store(output, null);
				}
				else {
					settings.setProperty(EncryptionAES.encryptLocal("skin1Unlocked"),skin1Unlocked);
					settings.setProperty(EncryptionAES.encryptLocal("skin2Unlocked"),skin2Unlocked);
					settings.setProperty(EncryptionAES.encryptLocal("skin3Unlocked"),skin3Unlocked);
					settings.setProperty(EncryptionAES.encryptLocal("track4Unlocked"),track4Unlocked);
					settings.setProperty(EncryptionAES.encryptLocal("track5Unlocked"),track5Unlocked);
					settings.setProperty(EncryptionAES.encryptLocal("track6Unlocked"),track6Unlocked);
					settings.setProperty(EncryptionAES.encryptLocal("fireball1Unlocked"),fireball1Unlocked);
					settings.setProperty(EncryptionAES.encryptLocal("fireball2Unlocked"),fireball2Unlocked);
					settings.setProperty(EncryptionAES.encryptLocal("fireball3Unlocked"),fireball3Unlocked);
					settings.setProperty(EncryptionAES.encryptLocal("item4Unlocked"),item4Unlocked);
					settings.setProperty(EncryptionAES.encryptLocal("item5Unlocked"),item5Unlocked);
					settings.setProperty(EncryptionAES.encryptLocal("item6Unlocked"),item6Unlocked);
					settings.setProperty(EncryptionAES.encryptLocal("menuTrack4Unlocked"),menuTrack4Unlocked);
					settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedCharacterSkin"),currentlySelectedCharacterSkin);
					settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedTrack"),currentlySelectedTrack);
					settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedFireball"),currentlySelectedFireball);
					settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedItem"),currentlySelectedItem);
					settings.setProperty(EncryptionAES.encryptLocal("volumeSliderPosition"),volumeSliderPosition);
					settings.setProperty(EncryptionAES.encryptLocal("sfxMusicSliderPosition"),sfxMusicSliderPosition);
					settings.setProperty(EncryptionAES.encryptLocal("skipAnimations"),skipAnimations);
					settings.setProperty(EncryptionAES.encryptLocal("sendToServer"),sendToServer);
					settings.setProperty(EncryptionAES.encryptLocal("saveName"),saveName);
					settings.setProperty(EncryptionAES.encryptLocal("lastDisconnectedTimer"),lastDisconnectedTimer);
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack1Set"),gameTrack1Set);
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack2Set"),gameTrack2Set);
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack3Set"),gameTrack3Set);
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack4Set"),gameTrack4Set);
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack5Set"),gameTrack5Set);
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack6Set"),gameTrack6Set);
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack7Set"),gameTrack7Set);
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack8Set"),gameTrack8Set);
					settings.setProperty(EncryptionAES.encryptLocal("menuTrack1Set"),menuTrack1Set);
					settings.setProperty(EncryptionAES.encryptLocal("menuTrack2Set"),menuTrack2Set);
					settings.setProperty(EncryptionAES.encryptLocal("menuTrack3Set"),menuTrack3Set);
					settings.setProperty(EncryptionAES.encryptLocal("menuTrack4Set"),menuTrack4Set);
					settings.setProperty(EncryptionAES.encryptLocal("menuTrack5Set"),menuTrack5Set);
					settings.setProperty(EncryptionAES.encryptLocal("firstTimeRunning"),firstTimeRunning);
					settings.setProperty(EncryptionAES.encryptLocal("firstTimeBeating"),firstTimeBeating);
					settings.setProperty(EncryptionAES.encryptLocal("upKey"),upKey);
					settings.setProperty(EncryptionAES.encryptLocal("downKey"),downKey);
					settings.setProperty(EncryptionAES.encryptLocal("leftKey"),leftKey);
					settings.setProperty(EncryptionAES.encryptLocal("rightKey"),rightKey);
					settings.setProperty(EncryptionAES.encryptLocal("shootKey"),shootKey);
					settings.setProperty(EncryptionAES.encryptLocal("itemKey"),itemKey);
					settings.setProperty(EncryptionAES.encryptLocal("pauseKey"),pauseKey);
					settings.setProperty(EncryptionAES.encryptLocal("cancelKey"),cancelKey);
					settings.setProperty(EncryptionAES.encryptLocal("aButton"),aButton);
					settings.setProperty(EncryptionAES.encryptLocal("bButton"),bButton);
					settings.setProperty(EncryptionAES.encryptLocal("xButton"),xButton);
					settings.setProperty(EncryptionAES.encryptLocal("yButton"),yButton);
					settings.setProperty(EncryptionAES.encryptLocal("backButton"),backButton);
					settings.setProperty(EncryptionAES.encryptLocal("startButton"),startButton);
					settings.setProperty(EncryptionAES.encryptLocal("lShoulderButton"),lShoulderButton);
					settings.setProperty(EncryptionAES.encryptLocal("rShoulderButton"),rShoulderButton);
					settings.setProperty(EncryptionAES.encryptLocal("lThumbButton"),lThumbButton);
					settings.setProperty(EncryptionAES.encryptLocal("rThumbButton"),rThumbButton);
					settings.setProperty(EncryptionAES.encryptLocal("lTriggerButton"),lTriggerButton);
					settings.setProperty(EncryptionAES.encryptLocal("rTriggerButton"),rTriggerButton);
					settings.setProperty(EncryptionAES.encryptLocal("guideButton"),guideButton);
					settings.setProperty(EncryptionAES.encryptLocal("unknownButton"),unknownButton);
					settings.setProperty(EncryptionAES.encryptLocal("upButton"),upButton);
					settings.setProperty(EncryptionAES.encryptLocal("downButton"),downButton);
					settings.setProperty(EncryptionAES.encryptLocal("leftButton"),leftButton);
					settings.setProperty(EncryptionAES.encryptLocal("rightButton"),rightButton);
					settings.setProperty(EncryptionAES.encryptLocal("Total Points: "),totalPoints);
					settings.setProperty(EncryptionAES.encryptLocal("High Score: "),highScore);
					settings.setProperty(EncryptionAES.encryptLocal("upKeyDirectInput"),upKeyDirectInput);
					settings.setProperty(EncryptionAES.encryptLocal("downKeyDirectInput"),downKeyDirectInput);
					settings.setProperty(EncryptionAES.encryptLocal("leftKeyDirectInput"),leftKeyDirectInput);
					settings.setProperty(EncryptionAES.encryptLocal("rightKeyDirectInput"),rightKeyDirectInput);
					settings.setProperty(EncryptionAES.encryptLocal("shootKeyDirectInput"),shootKeyDirectInput);
					settings.setProperty(EncryptionAES.encryptLocal("itemKeyDirectInput"),itemKeyDirectInput);
					settings.setProperty(EncryptionAES.encryptLocal("pauseKeyDirectInput"),pauseKeyDirectInput);
					settings.setProperty(EncryptionAES.encryptLocal("cancelKeyDirectInput"),cancelKeyDirectInput);
					settings.setProperty(EncryptionAES.encryptLocal("upKeyXInput"),upKeyXInput);
					settings.setProperty(EncryptionAES.encryptLocal("downKeyXInput"),downKeyXInput);
					settings.setProperty(EncryptionAES.encryptLocal("leftKeyXInput"),leftKeyXInput);
					settings.setProperty(EncryptionAES.encryptLocal("rightKeyXInput"),rightKeyXInput);
					settings.setProperty(EncryptionAES.encryptLocal("shootKeyXInput"),shootKeyXInput);
					settings.setProperty(EncryptionAES.encryptLocal("itemKeyXInput"),itemKeyXInput);
					settings.setProperty(EncryptionAES.encryptLocal("pauseKeyXInput"),pauseKeyXInput);
					settings.setProperty(EncryptionAES.encryptLocal("cancelKeyXInput"),cancelKeyXInput);
					settings.setProperty(EncryptionAES.encryptLocal("Controller Name: "),controllerName);
					settings.store(output, null);
				}
			}
    	} catch(FileNotFoundException e) {
    		System.out.println("FileNotFoundException: "+e);
    		File file = new File(recipitent);
    		file.createNewFile();
    	}
    	return;
    }
    public void settingsSetup() throws IOException {
    	Properties prop = new Properties();
    	InputStream input = null;
    	String s = "";
    	String totalPoints = "";
    	String highScore = "";
    	String controllerName = "";
    	String skin1Unlocked = "";
    	String skin2Unlocked = "";
    	String skin3Unlocked = "";
    	String track4Unlocked = "";
    	String track5Unlocked = "";
    	String track6Unlocked = "";
    	String fireball1Unlocked = "";
    	String fireball2Unlocked = "";
    	String fireball3Unlocked = "";
    	String item4Unlocked = "";
    	String item5Unlocked = "";
    	String item6Unlocked = "";
    	String menuTrack4Unlocked = "";
    	String gameTrack1Set = "";
    	String gameTrack2Set = "";
    	String gameTrack3Set = "";
    	String gameTrack4Set = "";
    	String gameTrack5Set = "";
    	String gameTrack6Set = "";
    	String gameTrack7Set = "";
    	String gameTrack8Set = "";
    	String menuTrack1Set = "";
    	String menuTrack2Set = "";
    	String menuTrack3Set = "";
    	String menuTrack4Set = "";
    	String menuTrack5Set = "";
    	String currentlySelectedCharacterSkin = "";
    	String currentlySelectedTrack = "";
    	String currentlySelectedFireball = "";
    	String currentlySelectedItem = "";
    	String volumeSliderPosition = "";
    	String sfxMusicSliderPosition = "";
    	String skipAnimations = "";
    	String sendToServer = "";
    	String saveName = "";
    	String lastDisconnectedTimer = "";
    	String firstTimeRunning = "";
    	String firstTimeBeating = "";
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
    	String lTriggerButton = "";
    	String rTriggerButton = "";
    	String guideButton = "";
    	String unknownButton = "";
    	String upButton = "";
    	String downButton = "";
    	String leftButton = "";
    	String rightButton = "";
    	String upKeyDirectInput = "";
    	String downKeyDirectInput = "";
    	String leftKeyDirectInput = "";
    	String rightKeyDirectInput = "";
    	String shootKeyDirectInput = "";
    	String itemKeyDirectInput = "";
    	String pauseKeyDirectInput = "";
    	String cancelKeyDirectInput = "";
    	String upKeyXInput = "";
    	String downKeyXInput = "";
    	String leftKeyXInput = "";
    	String rightKeyXInput = "";
    	String shootKeyXInput = "";
    	String itemKeyXInput = "";
    	String pauseKeyXInput = "";
    	String cancelKeyXInput = "";
    	try {
    		input = new FileInputStream("settings.properties");
			prop.load(input);
			if(!prop.isEmpty()){
				totalPoints = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("Total Points: ")));
				highScore = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("High Score: ")));
				controllerName = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("Controller Name: ")));
				skin1Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("skin1Unlocked")));
				skin2Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("skin2Unlocked")));
				skin3Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("skin3Unlocked")));
				track4Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("track4Unlocked")));
				track5Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("track5Unlocked")));
				track6Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("track6Unlocked")));
				fireball1Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("fireball1Unlocked")));
				fireball2Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("fireball2Unlocked")));
				fireball3Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("fireball3Unlocked")));
				item4Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("item4Unlocked")));
				item5Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("item5Unlocked")));
				item6Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("item6Unlocked")));
				menuTrack4Unlocked = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("menuTrack4Unlocked")));
				currentlySelectedCharacterSkin = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("currentlySelectedCharacterSkin")));
				currentlySelectedTrack = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("currentlySelectedTrack")));
				currentlySelectedFireball = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("currentlySelectedFireball")));
				currentlySelectedItem = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("currentlySelectedItem")));
				volumeSliderPosition = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("volumeSliderPosition")));
				sfxMusicSliderPosition = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("sfxMusicSliderPosition")));
				skipAnimations = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("skipAnimations")));
				sendToServer = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("sendToServer")));
				saveName = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("saveName")));
				lastDisconnectedTimer = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("lastDisconnectedTimer")));
				gameTrack1Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("gameTrack1Set")));
				gameTrack2Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("gameTrack2Set")));
				gameTrack3Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("gameTrack3Set")));
				gameTrack4Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("gameTrack4Set")));
				gameTrack5Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("gameTrack5Set")));
				gameTrack6Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("gameTrack6Set")));
				gameTrack7Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("gameTrack7Set")));
				gameTrack8Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("gameTrack8Set")));
				menuTrack1Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("menuTrack1Set")));
				menuTrack2Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("menuTrack2Set")));
				menuTrack3Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("menuTrack3Set")));
				menuTrack4Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("menuTrack4Set")));
				menuTrack5Set = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("menuTrack5Set")));
				firstTimeRunning = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("firstTimeRunning")));
				firstTimeBeating = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("firstTimeBeating")));
				upKey = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("upKey")));
				downKey = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("downKey")));
				leftKey = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("leftKey")));
				rightKey = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("rightKey")));
				shootKey = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("shootKey")));
				itemKey = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("itemKey")));
				pauseKey = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("pauseKey")));
				cancelKey = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("cancelKey")));
				upKeyXInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("upKeyXInput")));
				downKeyXInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("downKeyXInput")));
				leftKeyXInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("leftKeyXInput")));
				rightKeyXInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("rightKeyXInput")));
				shootKeyXInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("shootKeyXInput")));
				itemKeyXInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("itemKeyXInput")));
				pauseKeyXInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("pauseKeyXInput")));
				cancelKeyXInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("cancelKeyXInput")));
				aButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("aButton")));
				bButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("bButton")));
				xButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("xButton")));
				yButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("yButton")));
				backButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("backButton")));
				startButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("startButton")));
				lShoulderButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("lShoulderButton")));
				rShoulderButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("rShoulderButton")));
				lThumbButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("lThumbButton")));
				rThumbButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("rThumbButton")));
				lTriggerButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("lTriggerButton")));
				rTriggerButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("rTriggerButton")));
				guideButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("guideButton")));
				unknownButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("unknownButton")));
				upButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("upButton")));
				downButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("downButton")));
				leftButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("leftButton")));
				rightButton = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("rightButton")));
				upKeyDirectInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("upKeyDirectInput")));
				downKeyDirectInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("downKeyDirectInput")));
				leftKeyDirectInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("leftKeyDirectInput")));
				rightKeyDirectInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("rightKeyDirectInput")));
				shootKeyDirectInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("shootKeyDirectInput")));
				itemKeyDirectInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("itemKeyDirectInput")));
				pauseKeyDirectInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("pauseKeyDirectInput")));
				cancelKeyDirectInput = EncryptionAES.decryptLocal(prop.getProperty(EncryptionAES.encryptLocal("cancelKeyDirectInput")));
				//CORRUPT DATA CHECK
				if(totalPoints == null || skin1Unlocked == null || skin2Unlocked == null || skin3Unlocked == null ||
						track4Unlocked == null || track5Unlocked == null || track6Unlocked == null || fireball1Unlocked == null ||
						fireball2Unlocked == null || fireball3Unlocked == null || item4Unlocked == null || item5Unlocked == null ||
						item6Unlocked == null || menuTrack4Unlocked == null || currentlySelectedCharacterSkin == null || currentlySelectedTrack == null ||
						currentlySelectedFireball == null || currentlySelectedItem == null || volumeSliderPosition == null || sfxMusicSliderPosition == null ||
						skipAnimations == null || gameTrack1Set == null || gameTrack2Set == null || gameTrack3Set == null ||
						gameTrack4Set == null || gameTrack5Set == null || gameTrack6Set == null || gameTrack7Set == null ||
						gameTrack8Set == null || menuTrack1Set == null || menuTrack2Set == null || menuTrack3Set == null ||
						menuTrack4Set == null || menuTrack5Set == null || firstTimeRunning == null || upKey == null ||
						downKey == null || leftKey == null || rightKey == null || shootKey == null ||
						itemKey == null || pauseKey == null || cancelKey == null || aButton == null ||
						bButton == null || xButton == null || yButton == null || backButton == null ||
						startButton == null || lShoulderButton == null || rShoulderButton == null || lThumbButton == null ||
						rThumbButton == null || guideButton == null || unknownButton == null || upButton == null ||
						downButton == null || leftButton == null || rightButton == null || firstTimeBeating == null || sendToServer == null ||
						saveName == null || lastDisconnectedTimer == null || highScore == null || upKeyDirectInput == null || downKeyDirectInput == null ||
						leftKeyDirectInput == null || rightKeyDirectInput == null || shootKeyDirectInput == null || itemKeyDirectInput == null ||
						pauseKeyDirectInput == null || cancelKeyDirectInput == null || controllerName == null || lTriggerButton == null ||
						rTriggerButton == null || upKeyXInput == null || downKeyXInput == null || leftKeyXInput == null || rightKeyXInput == null ||
						shootKeyXInput == null || itemKeyXInput == null || pauseKeyXInput == null || cancelKeyXInput == null) {
					gameUnlocksToSettings();
					return;
				}
				//CORRUPT DATA CHECK FOR ENCRYPTION
				if(skin1Unlocked.equals("") || skin2Unlocked.equals("") || skin3Unlocked.equals("") ||
						track4Unlocked.equals("") || track5Unlocked.equals("") || track6Unlocked.equals("") || fireball1Unlocked.equals("") ||
						fireball2Unlocked.equals("") || fireball3Unlocked.equals("") || item4Unlocked.equals("") || item5Unlocked.equals("") ||
						item6Unlocked.equals("") || menuTrack4Unlocked.equals("") || currentlySelectedCharacterSkin.equals("") || currentlySelectedTrack.equals("") ||
						currentlySelectedFireball.equals("") || currentlySelectedItem.equals("") || volumeSliderPosition.equals("") || sfxMusicSliderPosition.equals("") ||
						skipAnimations.equals("") || gameTrack1Set.equals("") || gameTrack2Set.equals("") || gameTrack3Set.equals("") ||
						gameTrack4Set.equals("") || gameTrack5Set.equals("") || gameTrack6Set.equals("") || gameTrack7Set.equals("") ||
						gameTrack8Set.equals("") || menuTrack1Set.equals("") || menuTrack2Set.equals("") || menuTrack3Set.equals("") ||
						menuTrack4Set.equals("") || menuTrack5Set.equals("") || firstTimeRunning.equals("") || upKey.equals("") ||
						downKey.equals("") || leftKey.equals("") || rightKey.equals("") || shootKey.equals("") ||
						itemKey.equals("") || pauseKey.equals("") || cancelKey.equals("") || aButton.equals("") ||
						bButton.equals("") || xButton.equals("") || yButton.equals("") || backButton.equals("") ||
						startButton.equals("") || lShoulderButton.equals("") || rShoulderButton.equals("") || lThumbButton.equals("") ||
						rThumbButton.equals("") || guideButton.equals("") || unknownButton.equals("") || upButton.equals("") ||
						downButton.equals("") || leftButton.equals("") || rightButton.equals("") || firstTimeBeating.equals("") || sendToServer.equals("") ||
						lastDisconnectedTimer.equals("") || upKeyDirectInput.equals("") || downKeyDirectInput.equals("") ||
						leftKeyDirectInput.equals("") || rightKeyDirectInput.equals("") || shootKeyDirectInput.equals("") || itemKeyDirectInput.equals("") ||
						pauseKeyDirectInput.equals("") || cancelKeyDirectInput.equals("") || lTriggerButton.equals("") ||
						rTriggerButton.equals("") || upKeyXInput.equals("") || downKeyXInput.equals("") || leftKeyXInput.equals("") || rightKeyXInput.equals("") ||
						shootKeyXInput.equals("") || itemKeyXInput.equals("") || pauseKeyXInput.equals("") || cancelKeyXInput.equals("")) {
					gameUnlocksToSettings();
					return;
				}
				if(!totalPoints.equals(""))
					Game.totalPoints = Integer.valueOf(totalPoints);
				if(!highScore.equals(""))
					Game.highScore = Integer.valueOf(highScore);
				if(!controllerName.equals(""))
					ReadAllEvents.controllerName = String.valueOf(controllerName);
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
				if(track4Unlocked.equals("true"))
					Game.track4Unlocked = true;
				else
					Game.track4Unlocked = false;
				if(track5Unlocked.equals("true"))
					Game.track5Unlocked = true;
				else
					Game.track5Unlocked = false;
				if(track6Unlocked.equals("true"))
					Game.track6Unlocked = true;
				else
					Game.track6Unlocked = false;
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
				if(menuTrack4Unlocked.equals("true"))
					Game.menuTrack4Unlocked = true;
				else
					Game.menuTrack4Unlocked = false;
				if(Game.currentlySelectedCharacterSkin != 4 && Game.currentlySelectedCharacterSkin != 5) {
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
				if(Game.currentlySelectedFireball != 4 && Game.currentlySelectedFireball != 5) {
					switch(currentlySelectedFireball) {
					case "0":
						Game.currentlySelectedFireball = 0;
						if(Game.fireballSFX != null)
							Game.fireballSFX.close();
						if(Game.fireballPopSFX != null)
							Game.fireballPopSFX.close();
						Game.fireballSFX = new SoundLoops("res/Sounds/SFX/smb3_fireball.wav");
						Game.fireballPopSFX = new SoundLoops("res/Sounds/SFX/mariofireballsfx.wav");
						VolumeSlider.adjustSFX(Game.fireballSFX);
						VolumeSlider.adjustSFX(Game.fireballPopSFX);
						break;
					case "1":
						Game.currentlySelectedFireball = 1;
						if(Game.fireballSFX != null)
							Game.fireballSFX.close();
						if(Game.fireballPopSFX != null)
							Game.fireballPopSFX.close();
						Game.fireballSFX = new SoundLoops("res/Sounds/SFX/nsmbwiiMenuCancel.wav");
						Game.fireballPopSFX = new SoundLoops("res/Sounds/SFX/ssbm_shell.wav");
						VolumeSlider.adjustSFX(Game.fireballSFX);
						VolumeSlider.adjustSFX(Game.fireballPopSFX);
						break;
					case "2":
						Game.currentlySelectedFireball = 2;
						if(Game.fireballSFX != null)
							Game.fireballSFX.close();
						if(Game.fireballPopSFX != null)
							Game.fireballPopSFX.close();
						Game.fireballSFX = new SoundLoops("res/Sounds/SFX/nsmbwiiMenuCancel.wav");
						Game.fireballPopSFX = new SoundLoops("res/Sounds/SFX/ssbm_shell.wav");
						VolumeSlider.adjustSFX(Game.fireballSFX);
						VolumeSlider.adjustSFX(Game.fireballPopSFX);
						break;
					case "3":
						Game.currentlySelectedFireball = 3;
						if(Game.fireballSFX != null)
							Game.fireballSFX.close();
						if(Game.fireballPopSFX != null)
							Game.fireballPopSFX.close();
						Game.fireballSFX = new SoundLoops("res/Sounds/SFX/nsmbwiiMenuCancel.wav");
						Game.fireballPopSFX = new SoundLoops("res/Sounds/SFX/ssbm_shell.wav");
						VolumeSlider.adjustSFX(Game.fireballSFX);
						VolumeSlider.adjustSFX(Game.fireballPopSFX);
						break;
					default: 
						break;
					}
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
				if(sendToServer.equals("true"))
					Game.sendToServer = true;
				else
					Game.sendToServer = false;
				if(saveName.equals(""))
					Game.saveName = "";
				else
					Game.saveName = saveName;
				if(System.currentTimeMillis() < Long.valueOf(lastDisconnectedTimer) + Game.TIME_OUT)
					Game.lastDisconnectedTimer = Long.valueOf(lastDisconnectedTimer);
				else 
					Game.lastDisconnectedTimer = 0;
				if(gameTrack1Set.equals("true"))
					Game.gameTrack1Set = true;
				else
					Game.gameTrack1Set = false;
				if(gameTrack2Set.equals("true"))
					Game.gameTrack2Set = true;
				else
					Game.gameTrack2Set = false;
				if(gameTrack3Set.equals("true"))
					Game.gameTrack3Set = true;
				else
					Game.gameTrack3Set = false;
				if(gameTrack4Set.equals("true"))
					Game.gameTrack4Set = true;
				else
					Game.gameTrack4Set = false;
				if(gameTrack5Set.equals("true"))
					Game.gameTrack5Set = true;
				else
					Game.gameTrack5Set = false;
				if(gameTrack6Set.equals("true"))
					Game.gameTrack6Set = true;
				else
					Game.gameTrack6Set = false;
				if(gameTrack7Set.equals("true"))
					Game.gameTrack7Set = true;
				else
					Game.gameTrack7Set = false;
				if(gameTrack8Set.equals("true"))
					Game.gameTrack8Set = true;
				else
					Game.gameTrack8Set = false;
				if(menuTrack1Set.equals("true"))
					Game.menuTrack1Set = true;
				else
					Game.menuTrack1Set = false;
				if(menuTrack2Set.equals("true"))
					Game.menuTrack2Set = true;
				else
					Game.menuTrack2Set = false;
				if(menuTrack3Set.equals("true"))
					Game.menuTrack3Set = true;
				else
					Game.menuTrack3Set = false;
				if(menuTrack4Set.equals("true"))
					Game.menuTrack4Set = true;
				else
					Game.menuTrack4Set = false;
				if(menuTrack5Set.equals("true"))
					Game.menuTrack5Set = true;
				else
					Game.menuTrack5Set = false;
				if(firstTimeRunning.equals("true"))
					Game.firstTimeRunning = true;
				else
					Game.firstTimeRunning = false;
				if(firstTimeBeating.equals("true"))
					Game.firstTimeBeating = true;
				else
					Game.firstTimeBeating = false;
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
				XInputDevice.upKey = Short.valueOf(upKeyXInput);
				XInputDevice.downKey = Short.valueOf(downKeyXInput);
				XInputDevice.leftKey = Short.valueOf(leftKeyXInput);
				XInputDevice.rightKey = Short.valueOf(rightKeyXInput);
				XInputDevice.shootKey = Short.valueOf(shootKeyXInput);
				XInputDevice.itemKey = Short.valueOf(itemKeyXInput);
				XInputDevice.pauseKey = Short.valueOf(pauseKeyXInput);
				XInputDevice.cancelKey = Short.valueOf(cancelKeyXInput);
				XInputDevice.a = Short.valueOf(aButton);//shoot
				XInputDevice.b = Short.valueOf(bButton);
				XInputDevice.x = Short.valueOf(xButton);//item
//				changePressXImage();
				changePressXImageEmulated();
				XInputDevice.y = Short.valueOf(yButton);
				XInputDevice.back = Short.valueOf(backButton);//cancel
				XInputDevice.start = Short.valueOf(startButton);//pause
				XInputDevice.lShoulder = Short.valueOf(lShoulderButton);
				XInputDevice.rShoulder = Short.valueOf(rShoulderButton);
				XInputDevice.lThumb = Short.valueOf(lThumbButton);
				XInputDevice.rThumb = Short.valueOf(rThumbButton);
				XInputDevice.lTrigger = Short.valueOf(lTriggerButton);
				XInputDevice.rTrigger = Short.valueOf(rTriggerButton);
				XInputDevice.guide = Short.valueOf(guideButton);
				XInputDevice.unknown = Short.valueOf(unknownButton);
				XInputDevice.up = Short.valueOf(upButton);//up
				XInputDevice.down = Short.valueOf(downButton);//down
				XInputDevice.left = Short.valueOf(leftButton);//left
				XInputDevice.right = Short.valueOf(rightButton);//right
				SwitchedValues.upKey = String.valueOf(upKeyDirectInput);
				SwitchedValues.downKey = String.valueOf(downKeyDirectInput);
				SwitchedValues.leftKey = String.valueOf(leftKeyDirectInput);
				SwitchedValues.rightKey = String.valueOf(rightKeyDirectInput);
				SwitchedValues.shootKey = String.valueOf(shootKeyDirectInput);
				SwitchedValues.itemKey = String.valueOf(itemKeyDirectInput);
				SwitchedValues.pauseKey = String.valueOf(pauseKeyDirectInput);
				SwitchedValues.cancelKey = String.valueOf(cancelKeyDirectInput);
				if(ReadAllEvents.controllerName != null && !ReadAllEvents.controllerName.equals("")) 
					game.getTextures().changePressDI(SpecialCases.buttonName(ReadAllEvents.controllerName, SwitchedValues.itemKey));
				else
					game.getTextures().changePressDI(SwitchedValues.itemKey);
				game.getHUD().setPressDI(game.getTextures().pressDI);
//				changeDirectInputItemImage();asdf
				game.getControlsController().updateControls();
			}
			
    	} catch(FileNotFoundException e) {
			OutputStream output = new FileOutputStream("settings.properties");
			settings.setProperty(EncryptionAES.encryptLocal("skin1Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("skin2Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("skin3Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("track4Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("track5Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("track6Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("fireball1Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("fireball2Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("fireball3Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("item4Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("item5Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("item6Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("menuTrack4Unlocked"), EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedCharacterSkin"), EncryptionAES.encryptLocal("0"));
			settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedTrack"), EncryptionAES.encryptLocal("0"));
			settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedFireball"), EncryptionAES.encryptLocal("0"));
			settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedItem"), EncryptionAES.encryptLocal("0"));
			settings.setProperty(EncryptionAES.encryptLocal("volumeSliderPosition"),EncryptionAES.encryptLocal("3"));
			settings.setProperty(EncryptionAES.encryptLocal("sfxMusicSliderPosition"),EncryptionAES.encryptLocal("3"));
			settings.setProperty(EncryptionAES.encryptLocal("skipAnimations"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("sendToServer"),EncryptionAES.encryptLocal("true"));
			settings.setProperty(EncryptionAES.encryptLocal("saveName"),EncryptionAES.encryptLocal(""));
			settings.setProperty(EncryptionAES.encryptLocal("lastDisconnectedTimer"),EncryptionAES.encryptLocal("0"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack1Set"), EncryptionAES.encryptLocal("true"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack2Set"), EncryptionAES.encryptLocal("true"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack3Set"), EncryptionAES.encryptLocal("true"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack4Set"), EncryptionAES.encryptLocal("true"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack5Set"), EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack6Set"), EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack7Set"), EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack8Set"), EncryptionAES.encryptLocal("true"));
			settings.setProperty(EncryptionAES.encryptLocal("menuTrack1Set"), EncryptionAES.encryptLocal("true"));
			settings.setProperty(EncryptionAES.encryptLocal("menuTrack2Set"), EncryptionAES.encryptLocal("true"));
			settings.setProperty(EncryptionAES.encryptLocal("menuTrack3Set"), EncryptionAES.encryptLocal("true"));
			settings.setProperty(EncryptionAES.encryptLocal("menuTrack4Set"), EncryptionAES.encryptLocal("true"));
			settings.setProperty(EncryptionAES.encryptLocal("menuTrack5Set"), EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("firstTimeRunning"),EncryptionAES.encryptLocal("true"));
			settings.setProperty(EncryptionAES.encryptLocal("firstTimeBeating"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("upKey"), EncryptionAES.encryptLocal(String.valueOf(KeyEvent.VK_W)));
			settings.setProperty(EncryptionAES.encryptLocal("downKey"), EncryptionAES.encryptLocal(String.valueOf(KeyEvent.VK_S)));
			settings.setProperty(EncryptionAES.encryptLocal("leftKey"), EncryptionAES.encryptLocal(String.valueOf(KeyEvent.VK_A)));
			settings.setProperty(EncryptionAES.encryptLocal("rightKey"), EncryptionAES.encryptLocal(String.valueOf(KeyEvent.VK_D)));
			settings.setProperty(EncryptionAES.encryptLocal("shootKey"), EncryptionAES.encryptLocal(String.valueOf(KeyEvent.VK_SPACE)));
			settings.setProperty(EncryptionAES.encryptLocal("itemKey"), EncryptionAES.encryptLocal(String.valueOf(KeyEvent.VK_E)));
			settings.setProperty(EncryptionAES.encryptLocal("pauseKey"), EncryptionAES.encryptLocal(String.valueOf(KeyEvent.VK_ENTER)));
			settings.setProperty(EncryptionAES.encryptLocal("cancelKey"), EncryptionAES.encryptLocal(String.valueOf(KeyEvent.VK_ESCAPE)));
			settings.setProperty(EncryptionAES.encryptLocal("upKeyXInput"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_UP)));
			settings.setProperty(EncryptionAES.encryptLocal("downKeyXInput"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)));
			settings.setProperty(EncryptionAES.encryptLocal("leftKeyXInput"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)));
			settings.setProperty(EncryptionAES.encryptLocal("rightKeyXInput"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)));
			settings.setProperty(EncryptionAES.encryptLocal("shootKeyXInput"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_A)));
			settings.setProperty(EncryptionAES.encryptLocal("itemKeyXInput"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_X)));
			settings.setProperty(EncryptionAES.encryptLocal("pauseKeyXInput"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_START)));
			settings.setProperty(EncryptionAES.encryptLocal("cancelKeyXInput"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_B)));
			settings.setProperty(EncryptionAES.encryptLocal("aButton"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_A)));
			settings.setProperty(EncryptionAES.encryptLocal("bButton"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_B)));
			settings.setProperty(EncryptionAES.encryptLocal("xButton"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_X)));
			settings.setProperty(EncryptionAES.encryptLocal("yButton"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_Y)));
			settings.setProperty(EncryptionAES.encryptLocal("backButton"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_BACK)));
			settings.setProperty(EncryptionAES.encryptLocal("startButton"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_START)));
			settings.setProperty(EncryptionAES.encryptLocal("lShoulderButton"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER)));
			settings.setProperty(EncryptionAES.encryptLocal("rShoulderButton"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER)));
			settings.setProperty(EncryptionAES.encryptLocal("lThumbButton"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB)));
			settings.setProperty(EncryptionAES.encryptLocal("rThumbButton"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB)));
			settings.setProperty(EncryptionAES.encryptLocal("lTriggerButton"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER)));
			settings.setProperty(EncryptionAES.encryptLocal("rTriggerButton"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER)));
			settings.setProperty(EncryptionAES.encryptLocal("guideButton"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON)));
			settings.setProperty(EncryptionAES.encryptLocal("unknownButton"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_UNKNOWN)));
			settings.setProperty(EncryptionAES.encryptLocal("upButton"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_UP)));
			settings.setProperty(EncryptionAES.encryptLocal("downButton"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)));
			settings.setProperty(EncryptionAES.encryptLocal("leftButton"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)));
			settings.setProperty(EncryptionAES.encryptLocal("rightButton"), EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)));
			settings.setProperty(EncryptionAES.encryptLocal("upKeyDirectInput"), EncryptionAES.encryptLocal(String.valueOf(SwitchedValues.upKey)));
			settings.setProperty(EncryptionAES.encryptLocal("downKeyDirectInput"), EncryptionAES.encryptLocal(String.valueOf(SwitchedValues.downKey)));
			settings.setProperty(EncryptionAES.encryptLocal("leftKeyDirectInput"), EncryptionAES.encryptLocal(String.valueOf(SwitchedValues.leftKey)));
			settings.setProperty(EncryptionAES.encryptLocal("rightKeyDirectInput"), EncryptionAES.encryptLocal(String.valueOf(SwitchedValues.rightKey)));
			settings.setProperty(EncryptionAES.encryptLocal("shootKeyDirectInput"), EncryptionAES.encryptLocal(String.valueOf(SwitchedValues.shootKey)));
			settings.setProperty(EncryptionAES.encryptLocal("itemKeyDirectInput"), EncryptionAES.encryptLocal(String.valueOf(SwitchedValues.itemKey)));
			settings.setProperty(EncryptionAES.encryptLocal("pauseKeyDirectInput"), EncryptionAES.encryptLocal(String.valueOf(SwitchedValues.pauseKey)));
			settings.setProperty(EncryptionAES.encryptLocal("cancelKeyDirectInput"), EncryptionAES.encryptLocal(String.valueOf(SwitchedValues.cancelKey)));
			settings.setProperty(EncryptionAES.encryptLocal("Total Points: "),EncryptionAES.encryptLocal(""));
			settings.setProperty(EncryptionAES.encryptLocal("High Score: "),EncryptionAES.encryptLocal(""));
			settings.setProperty(EncryptionAES.encryptLocal("Controller Name: "),EncryptionAES.encryptLocal(""));
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
			String highScore;
			String controllerName;
			String saveName;
			String lastDisconnectedTimer;
			if(Game.totalPoints > 0)
				totalPoints = String.valueOf(Game.totalPoints);
			else if(settings.getProperty(EncryptionAES.encryptLocal("Total Points: ")) != null) {
				totalPoints = EncryptionAES.decryptLocal(settings.getProperty(EncryptionAES.encryptLocal("Total Points: ")));
			}
			else
				totalPoints = "0";
			if(Game.highScore > 0)
				highScore = String.valueOf(Game.highScore);
			else if(settings.getProperty(EncryptionAES.encryptLocal("High Score: ")) != null) {
				highScore = EncryptionAES.decryptLocal(settings.getProperty(EncryptionAES.encryptLocal("High Score: ")));
			}
			else
				highScore = "0";
			if(ReadAllEvents.controllerName != null)
				controllerName = ReadAllEvents.controllerName;
			else
				controllerName = "";
			saveName = Game.saveName;
			lastDisconnectedTimer = String.valueOf(Game.lastDisconnectedTimer);
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
			String upKeyXInput = String.valueOf(XInputDevice.upKey);
			String downKeyXInput = String.valueOf(XInputDevice.downKey);
			String leftKeyXInput = String.valueOf(XInputDevice.leftKey);
			String rightKeyXInput = String.valueOf(XInputDevice.rightKey);
			String shootKeyXInput = String.valueOf(XInputDevice.shootKey);
			String itemKeyXInput = String.valueOf(XInputDevice.itemKey);
			String pauseKeyXInput = String.valueOf(XInputDevice.pauseKey);
			String cancelKeyXInput = String.valueOf(XInputDevice.cancelKey);
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
			String lTriggerButton = String.valueOf(XInputDevice.lTrigger);
			String rTriggerButton = String.valueOf(XInputDevice.rTrigger);
			String guideButton = String.valueOf(XInputDevice.guide);
			String unknownButton = String.valueOf(XInputDevice.unknown);
			String upButton = String.valueOf(XInputDevice.up);
			String downButton = String.valueOf(XInputDevice.down);
			String leftButton = String.valueOf(XInputDevice.left);
			String rightButton = String.valueOf(XInputDevice.right);
			String upKeyDirectInput = String.valueOf(SwitchedValues.upKey);
			String downKeyDirectInput = String.valueOf(SwitchedValues.downKey);
			String leftKeyDirectInput = String.valueOf(SwitchedValues.leftKey);
			String rightKeyDirectInput = String.valueOf(SwitchedValues.rightKey);
			String shootKeyDirectInput = String.valueOf(SwitchedValues.shootKey);
			String itemKeyDirectInput = String.valueOf(SwitchedValues.itemKey);
			String pauseKeyDirectInput = String.valueOf(SwitchedValues.pauseKey);
			String cancelKeyDirectInput = String.valueOf(SwitchedValues.cancelKey);
				if(Game.skin1Unlocked == true)
					settings.setProperty(EncryptionAES.encryptLocal("skin1Unlocked"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("skin1Unlocked"),EncryptionAES.encryptLocal("false"));
				if(Game.skin2Unlocked == true)
					settings.setProperty(EncryptionAES.encryptLocal("skin2Unlocked"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("skin2Unlocked"),EncryptionAES.encryptLocal("false"));
				if(Game.skin3Unlocked == true)
					settings.setProperty(EncryptionAES.encryptLocal("skin3Unlocked"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("skin3Unlocked"),EncryptionAES.encryptLocal("false"));
				if(Game.track4Unlocked == true)
					settings.setProperty(EncryptionAES.encryptLocal("track4Unlocked"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("track4Unlocked"),EncryptionAES.encryptLocal("false"));
				if(Game.track5Unlocked == true)
					settings.setProperty(EncryptionAES.encryptLocal("track5Unlocked"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("track5Unlocked"),EncryptionAES.encryptLocal("false"));
				if(Game.track6Unlocked == true)
					settings.setProperty(EncryptionAES.encryptLocal("track6Unlocked"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("track6Unlocked"),EncryptionAES.encryptLocal("false"));
				if(Game.fireball1Unlocked == true)
					settings.setProperty(EncryptionAES.encryptLocal("fireball1Unlocked"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("fireball1Unlocked"),EncryptionAES.encryptLocal("false"));
				if(Game.fireball2Unlocked == true)
					settings.setProperty(EncryptionAES.encryptLocal("fireball2Unlocked"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("fireball2Unlocked"),EncryptionAES.encryptLocal("false"));
				if(Game.fireball3Unlocked == true)
					settings.setProperty(EncryptionAES.encryptLocal("fireball3Unlocked"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("fireball3Unlocked"),EncryptionAES.encryptLocal("false"));
				if(Game.item4Unlocked == true)
					settings.setProperty(EncryptionAES.encryptLocal("item4Unlocked"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("item4Unlocked"),EncryptionAES.encryptLocal("false"));
				if(Game.item5Unlocked == true)
					settings.setProperty(EncryptionAES.encryptLocal("item5Unlocked"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("item5Unlocked"),EncryptionAES.encryptLocal("false"));
				if(Game.item6Unlocked == true)
					settings.setProperty(EncryptionAES.encryptLocal("item6Unlocked"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("item6Unlocked"),EncryptionAES.encryptLocal("false"));
				if(Game.menuTrack4Unlocked == true)
					settings.setProperty(EncryptionAES.encryptLocal("menuTrack4Unlocked"),EncryptionAES.encryptLocal( "true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("menuTrack4Unlocked"),EncryptionAES.encryptLocal( "false"));
				switch(Game.currentlySelectedCharacterSkin) {
					case 0:
						settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedCharacterSkin"),EncryptionAES.encryptLocal("0"));
						break;
					case 1:
						settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedCharacterSkin"),EncryptionAES.encryptLocal("1"));
						break;
					case 2:
						settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedCharacterSkin"),EncryptionAES.encryptLocal("2"));
						break;
					case 3:
						settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedCharacterSkin"),EncryptionAES.encryptLocal("3"));
						break;
					default:
						break;
				}
				switch(Game.currentlySelectedTrack) {
				case 0:
					settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedTrack"),EncryptionAES.encryptLocal("0"));
					break;
				case 1:
					settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedTrack"),EncryptionAES.encryptLocal("1"));
					break;
				case 2:
					settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedTrack"),EncryptionAES.encryptLocal("2"));
					break;
				case 3:
					settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedTrack"),EncryptionAES.encryptLocal("3"));
					break;
				default:
					break;
				}
				switch(Game.currentlySelectedFireball) {
				case 0:
					settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedFireball"),EncryptionAES.encryptLocal("0"));
					break;
				case 1:
					settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedFireball"),EncryptionAES.encryptLocal("1"));
					break;
				case 2:
					settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedFireball"),EncryptionAES.encryptLocal("2"));
					break;
				case 3:
					settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedFireball"),EncryptionAES.encryptLocal("3"));
					break;
				default:
					break;
				}
				switch(Game.currentlySelectedItem) {
				case 0:
					settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedItem"),EncryptionAES.encryptLocal("0"));
					break;
				case 1:
					settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedItem"),EncryptionAES.encryptLocal("1"));
					break;
				case 2:
					settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedItem"),EncryptionAES.encryptLocal("2"));
					break;
				case 3:
					settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedItem"),EncryptionAES.encryptLocal("3"));
					break;
				case 4:
					settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedItem"),EncryptionAES.encryptLocal("4"));
					break;
				case 5:
					settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedItem"),EncryptionAES.encryptLocal("5"));
					break;
				case 6:
					settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedItem"),EncryptionAES.encryptLocal("6"));
					break;
				default:
					break;
				}
				settings.setProperty(EncryptionAES.encryptLocal("volumeSliderPosition"),EncryptionAES.encryptLocal( volumeSliderIntPosition));
				settings.setProperty(EncryptionAES.encryptLocal("sfxMusicSliderPosition"),EncryptionAES.encryptLocal( sfxMusicSliderIntPosition));
				if(Game.skipAnimations == true)
					settings.setProperty(EncryptionAES.encryptLocal("skipAnimations"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("skipAnimations"),EncryptionAES.encryptLocal("false"));
				if(Game.sendToServer == true)
					settings.setProperty(EncryptionAES.encryptLocal("sendToServer"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("sendToServer"),EncryptionAES.encryptLocal("false"));
				if(Game.gameTrack1Set == true)
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack1Set"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack1Set"),EncryptionAES.encryptLocal("false"));
				if(Game.gameTrack2Set == true)
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack2Set"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack2Set"),EncryptionAES.encryptLocal("false"));
				if(Game.gameTrack3Set == true)
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack3Set"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack3Set"),EncryptionAES.encryptLocal("false"));
				if(Game.gameTrack4Set == true)
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack4Set"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack4Set"),EncryptionAES.encryptLocal("false"));
				if(Game.gameTrack5Set == true)
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack5Set"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack5Set"),EncryptionAES.encryptLocal("false"));
				if(Game.gameTrack6Set == true)
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack6Set"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack6Set"),EncryptionAES.encryptLocal("false"));
				if(Game.gameTrack7Set == true)
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack7Set"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack7Set"),EncryptionAES.encryptLocal("false"));
				if(Game.gameTrack8Set == true)
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack8Set"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("gameTrack8Set"),EncryptionAES.encryptLocal("false"));
				if(Game.menuTrack1Set == true)
					settings.setProperty(EncryptionAES.encryptLocal("menuTrack1Set"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("menuTrack1Set"),EncryptionAES.encryptLocal("false"));
				if(Game.menuTrack2Set == true)
					settings.setProperty(EncryptionAES.encryptLocal("menuTrack2Set"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("menuTrack2Set"),EncryptionAES.encryptLocal("false"));
				if(Game.menuTrack3Set == true)
					settings.setProperty(EncryptionAES.encryptLocal("menuTrack3Set"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("menuTrack3Set"),EncryptionAES.encryptLocal("false"));
				if(Game.menuTrack4Set == true)
					settings.setProperty(EncryptionAES.encryptLocal("menuTrack4Set"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("menuTrack4Set"),EncryptionAES.encryptLocal("false"));
				if(Game.menuTrack5Set == true)
					settings.setProperty(EncryptionAES.encryptLocal("menuTrack5Set"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("menuTrack5Set"),EncryptionAES.encryptLocal("false"));
				
				if(Game.firstTimeRunning == true)
					settings.setProperty(EncryptionAES.encryptLocal("firstTimeRunning"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("firstTimeRunning"),EncryptionAES.encryptLocal("false"));
				if(Game.firstTimeBeating == true)
					settings.setProperty(EncryptionAES.encryptLocal("firstTimeBeating"),EncryptionAES.encryptLocal("true"));
				else
					settings.setProperty(EncryptionAES.encryptLocal("firstTimeBeating"),EncryptionAES.encryptLocal("false"));
				settings.setProperty(EncryptionAES.encryptLocal("upKey"),EncryptionAES.encryptLocal( upKey));
				settings.setProperty(EncryptionAES.encryptLocal("downKey"),EncryptionAES.encryptLocal( downKey));
				settings.setProperty(EncryptionAES.encryptLocal("leftKey"),EncryptionAES.encryptLocal( leftKey));
				settings.setProperty(EncryptionAES.encryptLocal("rightKey"),EncryptionAES.encryptLocal( rightKey));
				settings.setProperty(EncryptionAES.encryptLocal("shootKey"),EncryptionAES.encryptLocal( shootKey));
				settings.setProperty(EncryptionAES.encryptLocal("itemKey"),EncryptionAES.encryptLocal( itemKey));
				settings.setProperty(EncryptionAES.encryptLocal("pauseKey"),EncryptionAES.encryptLocal( pauseKey));
				settings.setProperty(EncryptionAES.encryptLocal("cancelKey"),EncryptionAES.encryptLocal( cancelKey));
				settings.setProperty(EncryptionAES.encryptLocal("upKeyXInput"),EncryptionAES.encryptLocal( upKeyXInput));
				settings.setProperty(EncryptionAES.encryptLocal("downKeyXInput"),EncryptionAES.encryptLocal( downKeyXInput));
				settings.setProperty(EncryptionAES.encryptLocal("leftKeyXInput"),EncryptionAES.encryptLocal( leftKeyXInput));
				settings.setProperty(EncryptionAES.encryptLocal("rightKeyXInput"),EncryptionAES.encryptLocal( rightKeyXInput));
				settings.setProperty(EncryptionAES.encryptLocal("shootKeyXInput"),EncryptionAES.encryptLocal( shootKeyXInput));
				settings.setProperty(EncryptionAES.encryptLocal("itemKeyXInput"),EncryptionAES.encryptLocal( itemKeyXInput));
				settings.setProperty(EncryptionAES.encryptLocal("pauseKeyXInput"),EncryptionAES.encryptLocal( pauseKeyXInput));
				settings.setProperty(EncryptionAES.encryptLocal("cancelKeyXInput"),EncryptionAES.encryptLocal( cancelKeyXInput));
				settings.setProperty(EncryptionAES.encryptLocal("aButton"),EncryptionAES.encryptLocal( aButton));
				settings.setProperty(EncryptionAES.encryptLocal("bButton"),EncryptionAES.encryptLocal( bButton));
				settings.setProperty(EncryptionAES.encryptLocal("xButton"),EncryptionAES.encryptLocal( xButton));
				settings.setProperty(EncryptionAES.encryptLocal("yButton"),EncryptionAES.encryptLocal( yButton));
				settings.setProperty(EncryptionAES.encryptLocal("backButton"),EncryptionAES.encryptLocal( backButton));
				settings.setProperty(EncryptionAES.encryptLocal("startButton"),EncryptionAES.encryptLocal( startButton));
				settings.setProperty(EncryptionAES.encryptLocal("lShoulderButton"),EncryptionAES.encryptLocal( lShoulderButton));
				settings.setProperty(EncryptionAES.encryptLocal("rShoulderButton"),EncryptionAES.encryptLocal( rShoulderButton));
				settings.setProperty(EncryptionAES.encryptLocal("lThumbButton"),EncryptionAES.encryptLocal( lThumbButton));
				settings.setProperty(EncryptionAES.encryptLocal("rThumbButton"),EncryptionAES.encryptLocal( rThumbButton));
				settings.setProperty(EncryptionAES.encryptLocal("lTriggerButton"),EncryptionAES.encryptLocal( lTriggerButton));
				settings.setProperty(EncryptionAES.encryptLocal("rTriggerButton"),EncryptionAES.encryptLocal( rTriggerButton));
				settings.setProperty(EncryptionAES.encryptLocal("guideButton"),EncryptionAES.encryptLocal( guideButton));
				settings.setProperty(EncryptionAES.encryptLocal("unknownButton"),EncryptionAES.encryptLocal( unknownButton));
				settings.setProperty(EncryptionAES.encryptLocal("upButton"),EncryptionAES.encryptLocal( upButton));
				settings.setProperty(EncryptionAES.encryptLocal("downButton"),EncryptionAES.encryptLocal( downButton));
				settings.setProperty(EncryptionAES.encryptLocal("leftButton"),EncryptionAES.encryptLocal( leftButton));
				settings.setProperty(EncryptionAES.encryptLocal("rightButton"),EncryptionAES.encryptLocal( rightButton));
				settings.setProperty(EncryptionAES.encryptLocal("upKeyDirectInput"),EncryptionAES.encryptLocal( upKeyDirectInput));
				settings.setProperty(EncryptionAES.encryptLocal("downKeyDirectInput"),EncryptionAES.encryptLocal( downKeyDirectInput));
				settings.setProperty(EncryptionAES.encryptLocal("leftKeyDirectInput"),EncryptionAES.encryptLocal( leftKeyDirectInput));
				settings.setProperty(EncryptionAES.encryptLocal("rightKeyDirectInput"),EncryptionAES.encryptLocal( rightKeyDirectInput));
				settings.setProperty(EncryptionAES.encryptLocal("shootKeyDirectInput"),EncryptionAES.encryptLocal( shootKeyDirectInput));
				settings.setProperty(EncryptionAES.encryptLocal("itemKeyDirectInput"),EncryptionAES.encryptLocal( itemKeyDirectInput));
				settings.setProperty(EncryptionAES.encryptLocal("pauseKeyDirectInput"),EncryptionAES.encryptLocal( pauseKeyDirectInput));
				settings.setProperty(EncryptionAES.encryptLocal("cancelKeyDirectInput"),EncryptionAES.encryptLocal( cancelKeyDirectInput));
				settings.setProperty(EncryptionAES.encryptLocal("Total Points: "),EncryptionAES.encryptLocal( totalPoints));
				settings.setProperty(EncryptionAES.encryptLocal("High Score: "),EncryptionAES.encryptLocal( highScore));
				settings.setProperty(EncryptionAES.encryptLocal("Controller Name: "),EncryptionAES.encryptLocal( controllerName));
				settings.setProperty(EncryptionAES.encryptLocal("saveName"),EncryptionAES.encryptLocal( saveName));
				settings.setProperty(EncryptionAES.encryptLocal("lastDisconnectedTimer"),EncryptionAES.encryptLocal( lastDisconnectedTimer));
				settings.store(output, null);
			
			
    	} catch(FileNotFoundException e) {
			OutputStream output = new FileOutputStream("settings.properties");
			settings.setProperty(EncryptionAES.encryptLocal("skin1Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("skin2Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("skin3Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("track4Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("track5Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("track6Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("fireball1Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("fireball2Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("fireball3Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("item4Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("item5Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("item6Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("menuTrack4Unlocked"),EncryptionAES.encryptLocal( "false"));
			settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedCharacterSkin"),EncryptionAES.encryptLocal( "0"));
			settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedTrack"),EncryptionAES.encryptLocal( "0"));
			settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedFireball"),EncryptionAES.encryptLocal( "0"));
			settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedItem"),EncryptionAES.encryptLocal( "0"));
			settings.setProperty(EncryptionAES.encryptLocal("volumeSliderPosition"),EncryptionAES.encryptLocal("3"));
			settings.setProperty(EncryptionAES.encryptLocal("sfxMusicSliderPosition"),EncryptionAES.encryptLocal("3"));
			settings.setProperty(EncryptionAES.encryptLocal("skipAnimations"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("sendToServer"),EncryptionAES.encryptLocal("true"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack1Set"),EncryptionAES.encryptLocal( "true"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack2Set"),EncryptionAES.encryptLocal( "true"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack3Set"),EncryptionAES.encryptLocal( "true"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack4Set"),EncryptionAES.encryptLocal( "true"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack5Set"),EncryptionAES.encryptLocal( "false"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack6Set"),EncryptionAES.encryptLocal( "false"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack7Set"),EncryptionAES.encryptLocal( "false"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack8Set"),EncryptionAES.encryptLocal( "true"));
			settings.setProperty(EncryptionAES.encryptLocal("menuTrack1Set"),EncryptionAES.encryptLocal( "true"));
			settings.setProperty(EncryptionAES.encryptLocal("menuTrack2Set"),EncryptionAES.encryptLocal( "true"));
			settings.setProperty(EncryptionAES.encryptLocal("menuTrack3Set"),EncryptionAES.encryptLocal( "true"));
			settings.setProperty(EncryptionAES.encryptLocal("menuTrack4Set"),EncryptionAES.encryptLocal( "true"));
			settings.setProperty(EncryptionAES.encryptLocal("menuTrack5Set"),EncryptionAES.encryptLocal( "false"));
			settings.setProperty(EncryptionAES.encryptLocal("firstTimeRunning"),EncryptionAES.encryptLocal("true"));
			settings.setProperty(EncryptionAES.encryptLocal("firstTimeBeating"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("upKey"),EncryptionAES.encryptLocal( String.valueOf(KeyEvent.VK_W)));
			settings.setProperty(EncryptionAES.encryptLocal("downKey"),EncryptionAES.encryptLocal( String.valueOf(KeyEvent.VK_S)));
			settings.setProperty(EncryptionAES.encryptLocal("leftKey"),EncryptionAES.encryptLocal( String.valueOf(KeyEvent.VK_A)));
			settings.setProperty(EncryptionAES.encryptLocal("rightKey"),EncryptionAES.encryptLocal( String.valueOf(KeyEvent.VK_D)));
			settings.setProperty(EncryptionAES.encryptLocal("shootKey"),EncryptionAES.encryptLocal( String.valueOf(KeyEvent.VK_SPACE)));
			settings.setProperty(EncryptionAES.encryptLocal("itemKey"),EncryptionAES.encryptLocal( String.valueOf(KeyEvent.VK_E)));
			settings.setProperty(EncryptionAES.encryptLocal("pauseKey"),EncryptionAES.encryptLocal( String.valueOf(KeyEvent.VK_ENTER)));
			settings.setProperty(EncryptionAES.encryptLocal("cancelKey"),EncryptionAES.encryptLocal( String.valueOf(KeyEvent.VK_ESCAPE)));
			settings.setProperty(EncryptionAES.encryptLocal("upKeyXInput"),EncryptionAES.encryptLocal( String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_UP)));
			settings.setProperty(EncryptionAES.encryptLocal("downKeyXInput"),EncryptionAES.encryptLocal( String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)));
			settings.setProperty(EncryptionAES.encryptLocal("leftKeyXInput"),EncryptionAES.encryptLocal( String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)));
			settings.setProperty(EncryptionAES.encryptLocal("rightKeyXInput"),EncryptionAES.encryptLocal( String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)));
			settings.setProperty(EncryptionAES.encryptLocal("shootKeyXInput"),EncryptionAES.encryptLocal( String.valueOf(XInputConstants.XINPUT_GAMEPAD_A)));
			settings.setProperty(EncryptionAES.encryptLocal("itemKeyXInput"),EncryptionAES.encryptLocal( String.valueOf(XInputConstants.XINPUT_GAMEPAD_X)));
			settings.setProperty(EncryptionAES.encryptLocal("pauseKeyXInput"),EncryptionAES.encryptLocal( String.valueOf(XInputConstants.XINPUT_GAMEPAD_START)));
			settings.setProperty(EncryptionAES.encryptLocal("cancelKeyXInput"),EncryptionAES.encryptLocal( String.valueOf(XInputConstants.XINPUT_GAMEPAD_B)));
			settings.setProperty(EncryptionAES.encryptLocal("aButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_A)));
			settings.setProperty(EncryptionAES.encryptLocal("bButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_B)));
			settings.setProperty(EncryptionAES.encryptLocal("xButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_X)));
			settings.setProperty(EncryptionAES.encryptLocal("yButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_Y)));
			settings.setProperty(EncryptionAES.encryptLocal("backButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_BACK)));
			settings.setProperty(EncryptionAES.encryptLocal("startButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_START)));
			settings.setProperty(EncryptionAES.encryptLocal("lShoulderButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER)));
			settings.setProperty(EncryptionAES.encryptLocal("rShoulderButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER)));
			settings.setProperty(EncryptionAES.encryptLocal("lThumbButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB)));
			settings.setProperty(EncryptionAES.encryptLocal("rThumbButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB)));
			settings.setProperty(EncryptionAES.encryptLocal("lTriggerButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER)));
			settings.setProperty(EncryptionAES.encryptLocal("rTriggerButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER)));
			settings.setProperty(EncryptionAES.encryptLocal("guideButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON)));
			settings.setProperty(EncryptionAES.encryptLocal("unknownButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_UNKNOWN)));
			settings.setProperty(EncryptionAES.encryptLocal("upButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_UP)));
			settings.setProperty(EncryptionAES.encryptLocal("downButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)));
			settings.setProperty(EncryptionAES.encryptLocal("leftButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)));
			settings.setProperty(EncryptionAES.encryptLocal("rightButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)));
			settings.setProperty(EncryptionAES.encryptLocal("upKeyDirectInput"),EncryptionAES.encryptLocal( String.valueOf(SwitchedValues.upKey)));
			settings.setProperty(EncryptionAES.encryptLocal("downKeyDirectInput"),EncryptionAES.encryptLocal( String.valueOf(SwitchedValues.downKey)));
			settings.setProperty(EncryptionAES.encryptLocal("leftKeyDirectInput"),EncryptionAES.encryptLocal( String.valueOf(SwitchedValues.leftKey)));
			settings.setProperty(EncryptionAES.encryptLocal("rightKeyDirectInput"),EncryptionAES.encryptLocal( String.valueOf(SwitchedValues.rightKey)));
			settings.setProperty(EncryptionAES.encryptLocal("shootKeyDirectInput"),EncryptionAES.encryptLocal( String.valueOf(SwitchedValues.shootKey)));
			settings.setProperty(EncryptionAES.encryptLocal("itemKeyDirectInput"),EncryptionAES.encryptLocal( String.valueOf(SwitchedValues.itemKey)));
			settings.setProperty(EncryptionAES.encryptLocal("pauseKeyDirectInput"),EncryptionAES.encryptLocal( String.valueOf(SwitchedValues.pauseKey)));
			settings.setProperty(EncryptionAES.encryptLocal("cancelKeyDirectInput"),EncryptionAES.encryptLocal( String.valueOf(SwitchedValues.cancelKey)));
			settings.setProperty(EncryptionAES.encryptLocal("Total Points: "),EncryptionAES.encryptLocal(""));
			settings.setProperty(EncryptionAES.encryptLocal("High Score: "),EncryptionAES.encryptLocal(""));
			settings.setProperty(EncryptionAES.encryptLocal("Controller Name: "),EncryptionAES.encryptLocal(""));
			settings.setProperty(EncryptionAES.encryptLocal("saveName"),EncryptionAES.encryptLocal(""));
			settings.setProperty(EncryptionAES.encryptLocal("lastDisconnectedTimer"),EncryptionAES.encryptLocal("0"));
			settings.store(output, null);
    	}
    }
    public static void resetScore() throws IOException{
    	//try {
    		File file = new File("settings.properties");
    		file.delete();
    		OutputStream output = new FileOutputStream("settings.properties");
			settings.setProperty(EncryptionAES.encryptLocal("skin1Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("skin2Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("skin3Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("track4Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("track5Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("track6Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("fireball1Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("fireball2Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("fireball3Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("item4Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("item5Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("item6Unlocked"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("menuTrack4Unlocked"),EncryptionAES.encryptLocal( "false"));
			settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedCharacterSkin"),EncryptionAES.encryptLocal( "0"));
			settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedTrack"),EncryptionAES.encryptLocal( "0"));
			settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedFireball"),EncryptionAES.encryptLocal( "0"));
			settings.setProperty(EncryptionAES.encryptLocal("currentlySelectedItem"),EncryptionAES.encryptLocal( "0"));
			settings.setProperty(EncryptionAES.encryptLocal("volumeSliderPosition"),EncryptionAES.encryptLocal("3"));
			settings.setProperty(EncryptionAES.encryptLocal("sfxMusicSliderPosition"),EncryptionAES.encryptLocal("3"));
			settings.setProperty(EncryptionAES.encryptLocal("skipAnimations"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("sendToServer"),EncryptionAES.encryptLocal("true"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack1Set"),EncryptionAES.encryptLocal( "true"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack2Set"),EncryptionAES.encryptLocal( "true"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack3Set"),EncryptionAES.encryptLocal( "true"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack4Set"),EncryptionAES.encryptLocal( "true"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack5Set"),EncryptionAES.encryptLocal( "false"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack6Set"),EncryptionAES.encryptLocal( "false"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack7Set"),EncryptionAES.encryptLocal( "false"));
			settings.setProperty(EncryptionAES.encryptLocal("gameTrack8Set"),EncryptionAES.encryptLocal( "true"));
			settings.setProperty(EncryptionAES.encryptLocal("menuTrack1Set"),EncryptionAES.encryptLocal( "true"));
			settings.setProperty(EncryptionAES.encryptLocal("menuTrack2Set"),EncryptionAES.encryptLocal( "true"));
			settings.setProperty(EncryptionAES.encryptLocal("menuTrack3Set"),EncryptionAES.encryptLocal( "true"));
			settings.setProperty(EncryptionAES.encryptLocal("menuTrack4Set"),EncryptionAES.encryptLocal( "true"));
			settings.setProperty(EncryptionAES.encryptLocal("menuTrack5Set"),EncryptionAES.encryptLocal( "false"));
			settings.setProperty(EncryptionAES.encryptLocal("firstTimeRunning"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("firstTimeBeating"),EncryptionAES.encryptLocal("false"));
			settings.setProperty(EncryptionAES.encryptLocal("upKey"),EncryptionAES.encryptLocal( String.valueOf(KeyEvent.VK_W)));
			settings.setProperty(EncryptionAES.encryptLocal("downKey"),EncryptionAES.encryptLocal( String.valueOf(KeyEvent.VK_S)));
			settings.setProperty(EncryptionAES.encryptLocal("leftKey"),EncryptionAES.encryptLocal( String.valueOf(KeyEvent.VK_A)));
			settings.setProperty(EncryptionAES.encryptLocal("rightKey"),EncryptionAES.encryptLocal( String.valueOf(KeyEvent.VK_D)));
			settings.setProperty(EncryptionAES.encryptLocal("shootKey"),EncryptionAES.encryptLocal( String.valueOf(KeyEvent.VK_SPACE)));
			settings.setProperty(EncryptionAES.encryptLocal("itemKey"),EncryptionAES.encryptLocal( String.valueOf(KeyEvent.VK_E)));
			settings.setProperty(EncryptionAES.encryptLocal("pauseKey"),EncryptionAES.encryptLocal( String.valueOf(KeyEvent.VK_ENTER)));
			settings.setProperty(EncryptionAES.encryptLocal("cancelKey"),EncryptionAES.encryptLocal( String.valueOf(KeyEvent.VK_ESCAPE)));
			settings.setProperty(EncryptionAES.encryptLocal("upKeyXInput"),EncryptionAES.encryptLocal( String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_UP)));
			settings.setProperty(EncryptionAES.encryptLocal("downKeyXInput"),EncryptionAES.encryptLocal( String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)));
			settings.setProperty(EncryptionAES.encryptLocal("leftKeyXInput"),EncryptionAES.encryptLocal( String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)));
			settings.setProperty(EncryptionAES.encryptLocal("rightKeyXInput"),EncryptionAES.encryptLocal( String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)));
			settings.setProperty(EncryptionAES.encryptLocal("shootKeyXInput"),EncryptionAES.encryptLocal( String.valueOf(XInputConstants.XINPUT_GAMEPAD_A)));
			settings.setProperty(EncryptionAES.encryptLocal("itemKeyXInput"),EncryptionAES.encryptLocal( String.valueOf(XInputConstants.XINPUT_GAMEPAD_X)));
			settings.setProperty(EncryptionAES.encryptLocal("pauseKeyXInput"),EncryptionAES.encryptLocal( String.valueOf(XInputConstants.XINPUT_GAMEPAD_START)));
			settings.setProperty(EncryptionAES.encryptLocal("cancelKeyXInput"),EncryptionAES.encryptLocal( String.valueOf(XInputConstants.XINPUT_GAMEPAD_B)));
			settings.setProperty(EncryptionAES.encryptLocal("aButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_A)));
			settings.setProperty(EncryptionAES.encryptLocal("bButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_B)));
			settings.setProperty(EncryptionAES.encryptLocal("xButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_X)));
			settings.setProperty(EncryptionAES.encryptLocal("yButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_Y)));
			settings.setProperty(EncryptionAES.encryptLocal("backButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_BACK)));
			settings.setProperty(EncryptionAES.encryptLocal("startButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_START)));
			settings.setProperty(EncryptionAES.encryptLocal("lShoulderButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER)));
			settings.setProperty(EncryptionAES.encryptLocal("rShoulderButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER)));
			settings.setProperty(EncryptionAES.encryptLocal("lThumbButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB)));
			settings.setProperty(EncryptionAES.encryptLocal("rThumbButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB)));
			settings.setProperty(EncryptionAES.encryptLocal("lTriggerButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER)));
			settings.setProperty(EncryptionAES.encryptLocal("rTriggerButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER)));
			settings.setProperty(EncryptionAES.encryptLocal("guideButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON)));
			settings.setProperty(EncryptionAES.encryptLocal("unknownButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_UNKNOWN)));
			settings.setProperty(EncryptionAES.encryptLocal("upButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_UP)));
			settings.setProperty(EncryptionAES.encryptLocal("downButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)));
			settings.setProperty(EncryptionAES.encryptLocal("leftButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)));
			settings.setProperty(EncryptionAES.encryptLocal("rightButton"),EncryptionAES.encryptLocal(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)));
			settings.setProperty(EncryptionAES.encryptLocal("upKeyDirectInput"),EncryptionAES.encryptLocal( String.valueOf(SwitchedValues.upKey)));
			settings.setProperty(EncryptionAES.encryptLocal("downKeyDirectInput"),EncryptionAES.encryptLocal( String.valueOf(SwitchedValues.downKey)));
			settings.setProperty(EncryptionAES.encryptLocal("leftKeyDirectInput"),EncryptionAES.encryptLocal( String.valueOf(SwitchedValues.leftKey)));
			settings.setProperty(EncryptionAES.encryptLocal("rightKeyDirectInput"),EncryptionAES.encryptLocal( String.valueOf(SwitchedValues.rightKey)));
			settings.setProperty(EncryptionAES.encryptLocal("shootKeyDirectInput"),EncryptionAES.encryptLocal( String.valueOf(SwitchedValues.shootKey)));
			settings.setProperty(EncryptionAES.encryptLocal("itemKeyDirectInput"),EncryptionAES.encryptLocal( String.valueOf(SwitchedValues.itemKey)));
			settings.setProperty(EncryptionAES.encryptLocal("pauseKeyDirectInput"),EncryptionAES.encryptLocal( String.valueOf(SwitchedValues.pauseKey)));
			settings.setProperty(EncryptionAES.encryptLocal("cancelKeyDirectInput"),EncryptionAES.encryptLocal( String.valueOf(SwitchedValues.cancelKey)));
			settings.setProperty(EncryptionAES.encryptLocal("Total Points: "),EncryptionAES.encryptLocal(""));
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
    /*
    public static void settingsBackup() throws IOException{
    	//run at initiation of game
    	//look for settingsBackup file (if not there create one)
    	//get properties from file 
    	//replace any null properties with property from regular settings file or replace them with default settings(if those are null too)
    	//look through properties of regular settings and replace any null values with settingsBackup
    	//return
    	Properties propBackup = new Properties();
    	InputStream inputBackup = null;
    	Properties prop = new Properties();
    	InputStream input = null;
    	OutputStream output = null;
    	OutputStream outputBackup = null;
    	String s = "";
    	String totalPoints = "";
    	String skin1Unlocked = "";
    	String skin2Unlocked = "";
    	String skin3Unlocked = "";
    	String track4Unlocked = "";
    	String track5Unlocked = "";
    	String track6Unlocked = "";
    	String fireball1Unlocked = "";
    	String fireball2Unlocked = "";
    	String fireball3Unlocked = "";
    	String item4Unlocked = "";
    	String item5Unlocked = "";
    	String item6Unlocked = "";
    	String menuTrack4Unlocked = "";
    	String gameTrack1Set = "";
    	String gameTrack2Set = "";
    	String gameTrack3Set = "";
    	String gameTrack4Set = "";
    	String gameTrack5Set = "";
    	String gameTrack6Set = "";
    	String gameTrack7Set = "";
    	String gameTrack8Set = "";
    	String menuTrack1Set = "";
    	String menuTrack2Set = "";
    	String menuTrack3Set = "";
    	String menuTrack4Set = "";
    	String menuTrack5Set = "";
    	String currentlySelectedCharacterSkin = "";
    	String currentlySelectedTrack = "";
    	String currentlySelectedFireball = "";
    	String currentlySelectedItem = "";
    	String volumeSliderPosition = "";
    	String sfxMusicSliderPosition = "";
    	String skipAnimations = "";
    	String firstTimeRunning = "";
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
    		inputBackup = new FileInputStream("src/settingsBackup.properties");
    		outputBackup = new FileOutputStream("src/settingsBackup.properties");
			propBackup.load(inputBackup);
    		input = new FileInputStream("settings.properties");
    		output = new FileOutputStream("settings.properties");
			prop.load(input);
			if(!propBackup.isEmpty()){
//				if(prop.getProperty("skin1Unlocked") == null && propBackup.getProperty("skin1Unlocked") == null)
//					propBackup.setProperty("skin1Unlocked", "false");
//				else
//					propBackup.setProperty("skin1Unlocked", prop.getProperty("skin1Unlocked"));
				
				if(propBackup.getProperty("skin1Unlocked") != null && prop.getProperty("skin1Unlocked") != null && !prop.getProperty("skin1Unlocked").toString().equals(propBackup.getProperty("skin1Unlocked").toString()))
					propBackup.setProperty("skin1Unlocked", prop.getProperty("skin1Unlocked"));
				else if(propBackup.getProperty("skin1Unlocked") == null)
					propBackup.setProperty("skin1Unlocked", "false");
				
				propBackup.store(outputBackup, null);
				prop.store(output, null);
			}
			else {
				settings.setProperty("skin1Unlocked","false");
				settings.setProperty("skin2Unlocked","false");
				settings.setProperty("skin3Unlocked","false");
				settings.setProperty("track4Unlocked","false");
				settings.setProperty("track5Unlocked","false");
				settings.setProperty("track6Unlocked","false");
				settings.setProperty("fireball1Unlocked","false");
				settings.setProperty("fireball2Unlocked","false");
				settings.setProperty("fireball3Unlocked","false");
				settings.setProperty("item4Unlocked","false");
				settings.setProperty("item5Unlocked","false");
				settings.setProperty("item6Unlocked","false");
				settings.setProperty("menuTrack4Unlocked", "false");
				settings.setProperty("currentlySelectedCharacterSkin", "0");
				settings.setProperty("currentlySelectedTrack", "0");
				settings.setProperty("currentlySelectedFireball", "0");
				settings.setProperty("currentlySelectedItem", "0");
				settings.setProperty("volumeSliderPosition","3");
				settings.setProperty("sfxMusicSliderPosition","3");
				settings.setProperty("skipAnimations","false");
				settings.setProperty("gameTrack1Set", "true");
				settings.setProperty("gameTrack2Set", "true");
				settings.setProperty("gameTrack3Set", "true");
				settings.setProperty("gameTrack4Set", "true");
				settings.setProperty("gameTrack5Set", "false");
				settings.setProperty("gameTrack6Set", "false");
				settings.setProperty("gameTrack7Set", "false");
				settings.setProperty("gameTrack8Set", "true");
				settings.setProperty("menuTrack1Set", "true");
				settings.setProperty("menuTrack2Set", "true");
				settings.setProperty("menuTrack3Set", "true");
				settings.setProperty("menuTrack4Set", "true");
				settings.setProperty("menuTrack5Set", "false");
				settings.setProperty("firstTimeRunning","true");
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
				settings.store(outputBackup, null);
			}
    	}catch(FileNotFoundException e) {
    		outputBackup = new FileOutputStream("src/settingsBackup.properties");
			settings.setProperty("skin1Unlocked","false");
			settings.setProperty("skin2Unlocked","false");
			settings.setProperty("skin3Unlocked","false");
			settings.setProperty("track4Unlocked","false");
			settings.setProperty("track5Unlocked","false");
			settings.setProperty("track6Unlocked","false");
			settings.setProperty("fireball1Unlocked","false");
			settings.setProperty("fireball2Unlocked","false");
			settings.setProperty("fireball3Unlocked","false");
			settings.setProperty("item4Unlocked","false");
			settings.setProperty("item5Unlocked","false");
			settings.setProperty("item6Unlocked","false");
			settings.setProperty("menuTrack4Unlocked", "false");
			settings.setProperty("currentlySelectedCharacterSkin", "0");
			settings.setProperty("currentlySelectedTrack", "0");
			settings.setProperty("currentlySelectedFireball", "0");
			settings.setProperty("currentlySelectedItem", "0");
			settings.setProperty("volumeSliderPosition","3");
			settings.setProperty("sfxMusicSliderPosition","3");
			settings.setProperty("skipAnimations","false");
			settings.setProperty("gameTrack1Set", "true");
			settings.setProperty("gameTrack2Set", "true");
			settings.setProperty("gameTrack3Set", "true");
			settings.setProperty("gameTrack4Set", "true");
			settings.setProperty("gameTrack5Set", "false");
			settings.setProperty("gameTrack6Set", "false");
			settings.setProperty("gameTrack7Set", "false");
			settings.setProperty("gameTrack8Set", "true");
			settings.setProperty("menuTrack1Set", "true");
			settings.setProperty("menuTrack2Set", "true");
			settings.setProperty("menuTrack3Set", "true");
			settings.setProperty("menuTrack4Set", "true");
			settings.setProperty("menuTrack5Set", "false");
			settings.setProperty("firstTimeRunning","true");
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
			settings.store(outputBackup, null);
			return;
    	}
    	prop.load(input);
    	propBackup.load(inputBackup);
    	if(prop.getProperty("skin1Unlocked") == null)
    		prop.setProperty("skin1Unlocked", propBackup.getProperty("skin1Unlocked"));
    	prop.store(output, null);
    	//set normal to backup
    }
    */
	public void initialize() {
		try {
			setupText();
			removeCommas();
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