package com.game.src.main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;

public class BasicBlocks {

	private Textures tex;
	private Game game;
	private int entranceInt = 395;
	private int entranceInt2 = 1185;
	private int entranceInt3;
	private boolean entranceIntHalf;
	private boolean entranceComplete;
	private int blockHit;
	private int keepDestroyingBlock = 0;
	public ArrayList<Rectangle> wall = new ArrayList<Rectangle>();
	SoundLoops brickBreaking;
	
	public BasicBlocks(Textures tex,Game game){
		this.tex = tex;
		this.game = game;
		basicBlocks(45, 370);
		basicBlocks(215, 370);
		basicBlocks(385, 370);
		basicBlocks(555, 370);
		String brickBreakingFile = "res/Sounds/SFX/brickbreaking.wav";
		SoundLoops brickBreakingSoundLoop = new SoundLoops(brickBreakingFile);
		VolumeSlider.adjustSFX(brickBreakingSoundLoop);
		this.brickBreaking = brickBreakingSoundLoop;
	}
	public void tick(){
		for(int i = 0; i < game.ea.size(); i++){
			EntityA tempEnt = game.ea.get(i);
			blockHit = Physics.BlockCollision(this, tempEnt);
			if(blockHit != -1){
				if(wall.size() > blockHit) {
					wall.remove(blockHit);
					game.getHUD().setScore(2);
				}
				Random rand = new Random();
				int r = rand.nextInt(5);
				keepDestroyingBlock += r;
				if(!game.ea.isEmpty() && r == 0 && keepDestroyingBlock > 20 && Game.currentlySelectedFireball != 3){
					game.ea.remove(game.ea.getLast());
					keepDestroyingBlock = 0;
				}
				for(int j = game.brickBreakingSFX.size(); j > 0; j--){
					if(game.brickBreakingSFX.get(j-1) != null && !game.brickBreakingSFX.get(j-1).clipIsActive()){
						game.brickBreakingSFX.remove(j-1);
						//j--;
					}
				}	
				for(int k = 0; k < game.brickBreakingSFX.size() || k == 0; k++){
					if(game.brickBreakingSFX.isEmpty()) {
						VolumeSlider.adjustSFX(brickBreaking);
						game.brickBreakingSFX.add(this.brickBreaking);
					}
					else if (game.brickBreakingSFX.get(k) == game.brickBreakingSFX.getLast()){
						VolumeSlider.adjustSFX(brickBreaking);
						if(brickBreaking.getVolume() - (1.5f*k) >= brickBreaking.minimumVolume())
							this.brickBreaking.reduceSound(1.5f*k);
						game.brickBreakingSFX.add(this.brickBreaking);
						k++;
					}
				}
				//System.out.println(game.brickBreakingSFX.size());
				game.brickBreakingSFX.getLast().play();
				if(keepDestroyingBlock == 0 && game.brickBreakingSFX.size() == 1)
					game.brickBreakingSFX.getLast().stop();
			}
			else if(game.brickBreakingSFX.size() == 1)
				game.brickBreakingSFX.getLast().stop();
			/*
			if(Physics.Collision(this, tempEnt)){
				blockHit = Physics.BlockCollision(this, tempEnt);
				//rect = basicBlocks(wall.get(blockHit).x,wall.get(blockHit).y);
				//basicBlocks((int)wall.get(blockHit).getX(),(int)wall.get(blockHit).getY()-10);
				wall.remove(blockHit);
				game.ea.removeLast();
				//wall.remove(blockHit-26);

				if(!game.ea.isEmpty())
					game.ea.remove(game.ea.getLast());
			}*/
		}

		for(int i = 0; i < game.eb.size(); i++){
			EntityB tempEnt = game.eb.get(i);
			blockHit = Physics.BlockCollision(this, tempEnt);
			if(blockHit != -1 && game.getEnemyHitPauseTimer() < System.currentTimeMillis()){
				Random rand = new Random();
				int randWidth = rand.nextInt(11) + 17;
				int randHeight = rand.nextInt(3) + 16;
				Rectangle brickOff = new Rectangle();
				if(game.enemyHitRightBarrier == true)
					brickOff = new Rectangle((int)tempEnt.getX(),(int)tempEnt.getY(),randWidth,randHeight);
				else
					brickOff = new Rectangle((int)tempEnt.getX(),(int)tempEnt.getY(),randWidth,randHeight);
				for(int j = 0; j < wall.size(); j++){
					if(brickOff.contains(wall.get(j).getX(), wall.get(j).getY())){
							game.getHUD().setScore(1);
							wall.remove(j);
							j--;
					}
				}
				if(wall.size() > blockHit) {
					wall.remove(blockHit);
					game.getHUD().setScore(1);
				}
				int r = rand.nextInt(3);
				keepDestroyingBlock += r;
				if(keepDestroyingBlock > 8){
					game.eb.remove(tempEnt);
					keepDestroyingBlock = 0;
				}
				for(int j = game.brickBreakingSFX.size(); j > 0; j--){
					if(game.brickBreakingSFX.get(j-1) != null && !game.brickBreakingSFX.get(j-1).clipIsActive()){
						game.brickBreakingSFX.remove(j-1);
						//j--;
					}
				}	
				for(int k = 0; k < game.brickBreakingSFX.size() || k == 0; k++){
					if(game.brickBreakingSFX.isEmpty()) {
						VolumeSlider.adjustSFX(brickBreaking);
						game.brickBreakingSFX.add(this.brickBreaking);
					}
					else if (game.brickBreakingSFX.get(k) == game.brickBreakingSFX.getLast()){
						VolumeSlider.adjustSFX(brickBreaking);
						if(brickBreaking.getVolume() - (1.5f*k) >= brickBreaking.minimumVolume())
							this.brickBreaking.reduceSound(1.5f*k);
						game.brickBreakingSFX.add(this.brickBreaking);
						k++;
					}
				}
				game.brickBreakingSFX.getLast().play();
				//if(keepDestroyingBlock == 0 && game.brickBreakingSFX.size() == 1)
				//	game.brickBreakingSFX.getLast().stop();
			}
			//else if(game.brickBreakingSFX.size() == 1)
				//game.brickBreakingSFX.getLast().stop();
		}
		
		for(int i = 0; i < game.ec.size(); i++){
			EntityC tempEnt = game.ec.get(i);
			blockHit = Physics.BlockCollision(this, tempEnt);
			if(blockHit != -1 && game.getEnemyHitPauseTimer() < System.currentTimeMillis()){
				Random rand = new Random();
				int randWidth = rand.nextInt(11) + 17;
				int randHeight = rand.nextInt(3) + 16;
				int r = rand.nextInt(3);
				Rectangle brickOff = new Rectangle((int)tempEnt.getX(),(int)tempEnt.getY(),randWidth,randHeight);
				for(int j = 0; j < wall.size(); j++){
					if(brickOff.contains(wall.get(j).getX(), wall.get(j).getY())){
							game.getHUD().setScore(1);
							wall.remove(j);
							j--;
					}
				}
				if(!game.ec.isEmpty() && r == 0 && !game.ec.get(i).entityName().equals("BuzzyBeetleShell"))
					game.ec.remove(tempEnt);
				for(int j = game.brickBreakingSFX.size(); j > 0; j--){
					if(game.brickBreakingSFX.get(j-1) != null && !game.brickBreakingSFX.get(j-1).clipIsActive()){
						game.brickBreakingSFX.remove(j-1);
						//j--;
					}
				}	
				for(int k = 0; k < game.brickBreakingSFX.size() || k == 0; k++){
					if(game.brickBreakingSFX.isEmpty()) {
						VolumeSlider.adjustSFX(brickBreaking);
						game.brickBreakingSFX.add(this.brickBreaking);
					}
					else if (game.brickBreakingSFX.get(k) == game.brickBreakingSFX.getLast()){
						VolumeSlider.adjustSFX(brickBreaking);
						if(brickBreaking.getVolume() - (1.5f *k) >= brickBreaking.minimumVolume())
							this.brickBreaking.reduceSound(1.5f*k);
						game.brickBreakingSFX.add(this.brickBreaking);
						k++;
					}
				}
				game.brickBreakingSFX.getLast().play();
				//if(keepDestroyingBlock == 0 && game.brickBreakingSFX.size() == 1)
					//game.brickBreakingSFX.getLast().stop();
			}
			//else if(game.brickBreakingSFX.size() == 1)
				//game.brickBreakingSFX.getLast().stop();
		}
		for(int i = 0; i < game.ee.size(); i++){
			EntityE tempEnt = game.ee.get(i);
			if(tempEnt.entityName().equals("bombOmbShrapnel1") || tempEnt.entityName().equals("bombOmbShrapnel2")) {
				blockHit = Physics.BlockCollisionBackwards(this, tempEnt);
				if(blockHit != -1){
					if(wall.size() > blockHit) {
						wall.remove(blockHit);
						game.getHUD().setScore(2);
					}
					Random rand = new Random();
					int r = rand.nextInt(5);
					keepDestroyingBlock += r;
					if(!game.ee.isEmpty() && r == 0 && keepDestroyingBlock > 20){
						game.ee.remove(tempEnt);
						keepDestroyingBlock = 0;
					}
					for(int j = game.brickBreakingSFX.size(); j > 0; j--){
						if(game.brickBreakingSFX.get(j-1) != null && !game.brickBreakingSFX.get(j-1).clipIsActive()){
							game.brickBreakingSFX.remove(j-1);
							//j--;
						}
					}	
					for(int k = 0; k < game.brickBreakingSFX.size() || k == 0; k++){
						if(game.brickBreakingSFX.isEmpty()) {
							VolumeSlider.adjustSFX(brickBreaking);
							game.brickBreakingSFX.add(this.brickBreaking);
						}
						else if (game.brickBreakingSFX.get(k) == game.brickBreakingSFX.getLast()){
							VolumeSlider.adjustSFX(brickBreaking);
							if(brickBreaking.getVolume() - (1.5f*k) >= brickBreaking.minimumVolume())
								this.brickBreaking.reduceSound(1.5f*k);
							game.brickBreakingSFX.add(this.brickBreaking);
							k++;
						}
					}
					//System.out.println(game.brickBreakingSFX.size());
					game.brickBreakingSFX.getLast().play();
					if(keepDestroyingBlock == 0 && game.brickBreakingSFX.size() == 1)
						game.brickBreakingSFX.getLast().stop();
				}
				else if(game.brickBreakingSFX.size() == 1)
					game.brickBreakingSFX.getLast().stop();
			}
		}
		if(game.getBrickBreakingSFXSoundPauseBoolean() == true && !Game.isPaused()){
			for(int l = Game.brickBreakingSFX.size()-1; l >= 0; l--){
				if(!Game.brickBreakingSFX.get(l).clipIsActive())
					Game.brickBreakingSFX.get(l).continuePlaying();
			}
			game.setBrickBreakingSFXSoundPauseBoolean(false);
		}
	}
	public void draw(Graphics2D g){
		g.setColor(Color.GREEN);
		for(int i = 0; i < wall.size(); i++){
			g.fill(wall.get(i));
		}
		if(Game.isPaused()){
			if(game.getBrickBreakingSFXSoundPauseBoolean() == false){
				for(int i = 0; i < Game.brickBreakingSFX.size();i++){
					if(Game.brickBreakingSFX.get(i).clipIsActive())
						Game.brickBreakingSFX.get(i).stop();
				}
				game.setBrickBreakingSFXSoundPauseBoolean(true);
			}
		}
	}
	
	public void drawEntrance(Graphics2D g){
		g.setColor(Color.GREEN);
		//System.out.println(game.soundFXClip2SoundLoop.getFrameLength());
		/*
		if(game.getPlayer().marioEntranceGrowingAnim.getCount() >= 1 && game.getPlayer().marioEntranceGrowingAnim.getCount()< 5) {
			for(int i = 0; i < 395; i++){
				g.fill(wall.get(i));
			}
		}
		else if(game.getPlayer().marioEntranceGrowingAnim.getCount() >= 5 && game.getPlayer().marioEntranceGrowingAnim.getCount() < 9) {
			for(int i = 0; i < 395; i++){
				g.fill(wall.get(i));
			}
			for(int i = 1185; i <= wall.size()-1; i++){
				g.fill(wall.get(i));
			}
		}*/
		/*
		System.out.println((int)game.soundFXClip2SoundLoop.getLongFramePosition());
		if(game.getPlayer().marioEntranceGrowingAnim.getCount() < 9) {
			int j = (int)game.soundFXClip2SoundLoop.getLongFramePosition()/110;
			if(j > 1580)
				j = 1580;
			for(int i = 1; i <= j -1; i++){
				g.fill(wall.get(i));
			}
		}*/
		if(entranceComplete) {
			for(int i = 0; i < wall.size(); i++){
				g.fill(wall.get(i));
			}
			return;
		}
		else if(game.getPlayer().marioEntranceGrowingAnim.getCount() >= 1 && game.getPlayer().marioEntranceGrowingAnim.getCount()< 4) {
			int j = (int)game.soundFXClip2SoundLoop.getLongFramePosition()/20 - 185;
			if(j > 395)
				j = 395;
			for(int i = 0; i < j; i++){
				g.fill(wall.get(i));
			}
			return;
		}
		else if(game.getPlayer().marioEntranceGrowingAnim.getCount() >= 3 && game.getPlayer().marioEntranceGrowingAnim.getCount() < 6) {
			if(entranceInt3==0)
				entranceInt3 = (int)game.soundFXClip2SoundLoop.getLongFramePosition()/20 + 608;
			entranceInt3--;
			//int k = (int)game.soundFXClip2SoundLoop.getLongFramePosition()/20 + 208;
			//System.out.println(k);
			if(entranceInt3 < 1185)
				entranceInt3 = 1185;
			for(int i = 0; i < 395; i++){
				g.fill(wall.get(i));
			}
			for(int i = wall.size()-1; i > entranceInt3; i--){
				g.fill(wall.get(i));
			}
			return;
		}
		else if(game.getPlayer().marioEntranceGrowingAnim.getCount() >= 6){
			//if(entranceInt2 == 0)
				//entranceInt2 = 1185; //(int)game.soundFXClip2SoundLoop.getLongFramePosition()/20;
			//if(entranceInt == 0)
				//entranceInt = 395;//(int)game.soundFXClip2SoundLoop.getLongFramePosition()/20-1532;
			//if(entranceInt2 == 1185)
				//entranceInt = 395;
			//if(entranceInt == 395)
				//entranceInt2 = 1185;
			if(entranceIntHalf) {//game.soundFXClip2SoundLoop.clipIsActive() && entranceIntHalf) {
				//entranceInt = (int)game.soundFXClip2SoundLoop.getLongFramePosition()/20 - 1052;
				entranceInt++;
				entranceInt2--;
			}
			/*
			else if(!game.soundFXClip2SoundLoop.clipIsActive() && entranceIntHalf) {
				entranceInt++;
				entranceInt2--;
			}*/
			if(entranceIntHalf)
				entranceIntHalf = false;
			else
				entranceIntHalf = true;
			if(entranceInt > 790) {
				entranceInt = 790;
				entranceComplete = true;
			}
			if(entranceInt2 < 790) {
				entranceInt2 = 790;
				entranceComplete = true;
			}
			for(int i = 0; i < 395; i++){
				g.fill(wall.get(i));
			}
			for(int i = 395; i < entranceInt; i++){
				g.fill(wall.get(i));
			}
			for(int i = 1185; i > entranceInt2; i--){
				g.fill(wall.get(i));
			}
			for(int i = 1185; i <= wall.size()-1; i++){
				g.fill(wall.get(i));
			}
			return;
		}
		/*
		else if(game.getPlayer().marioEntranceGrowingAnim.getCount() >= 9) {
			for(int i = 0; i < wall.size(); i++){
				g.fill(wall.get(i));
			}
		}*/
	}
	
	public void basicBlocks(int xPos, int yPos){
		int wallWidth = 3;
		int x = 0;
		int y = 0;
		
		for(int i = 0; i < 13; i++){
			if((14 + (i * 2) + wallWidth < 22 + wallWidth)){
				row(14 + (i * 2) + wallWidth, xPos - (i * 3), yPos + (i * 3));
				x = (i * 3) + 3;
			}else{
				row(22 + wallWidth, xPos - x, yPos + (i * 3));
				y = (i * 3);
			}
		}
		
		//Left side.
		for(int i = 0; i < 5; i++){
			row(8 + wallWidth - i, xPos - x, (yPos + y) + (i * 3));
		}
		
		//Right side.
		for(int i = 0; i < 5; i++){
			row(8 + wallWidth - i, (xPos - x + (14 * 3)) + (i * 3), (yPos + y) + (i * 3));
		}
	}
	
	public void row(int rows, int xPos, int yPos){
		for(int i = 0; i < rows; i++){
			Rectangle brick = new Rectangle(xPos + (i * 3), yPos, 3, 3);
			wall.add(brick);
		}
	}
	
	public void reset(){
		wall.clear();
		entranceInt = 395;
		entranceInt2 = 1185;
		entranceInt3 = 0;
		entranceComplete = false;
		basicBlocks(45, 370);
		basicBlocks(215, 370);
		basicBlocks(385, 370);
		basicBlocks(555, 370);
		if(!game.brickBreakingSFX.isEmpty()) {
			for(int i = 0; i <= game.brickBreakingSFX.size()-1; i++){
				if(game.brickBreakingSFX.get(i) != null){
					game.brickBreakingSFX.remove(i);
					i--;
				}
			}
		}
		brickBreaking.setSoundLoopBoolean(false);
		brickBreaking.setFramePosition(0);
	}
	
	public Rectangle getBounds(int i){
		return new Rectangle((int)wall.get(i).getX(), (int)wall.get(i).getY(), (int)wall.get(i).getWidth(), (int)wall.get(i).getHeight());
	}
	
	public SoundLoops getBrickBreakingSoundLoop() {
		return brickBreaking;
	}
	
	public double getX(int i) {
		return (int)wall.get(i).getX();
	}

	public double getY(int i){
		return (int)wall.get(i).getY();
	}
}
