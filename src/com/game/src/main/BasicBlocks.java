/*
 * 
 */
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

// TODO: Auto-generated Javadoc
/**
 * The Class BasicBlocks.
 */
public class BasicBlocks {

	/** The tex. */
	private Textures tex;
	
	/** The game. */
	private Game game;
	
	/** The block hit. */
	private int blockHit;
	
	/** The keep destroying block. */
	private int keepDestroyingBlock = 0;
	
	/** The wall. */
	public ArrayList<Rectangle> wall = new ArrayList<Rectangle>();
	
	/**
	 * Instantiates a new basic blocks.
	 *
	 * @param tex the tex
	 * @param game the game
	 */
	public BasicBlocks(Textures tex,Game game){
		this.tex = tex;
		this.game = game;
		basicBlocks(45, 370);
		basicBlocks(215, 370);
		basicBlocks(385, 370);
		basicBlocks(555, 370);
	}
	
	/**
	 * Tick.
	 */
	public void tick(){
		for(int i = 0; i < game.ea.size(); i++){
			EntityA tempEnt = game.ea.get(i);
			blockHit = Physics.BlockCollision(this, tempEnt);
			if(blockHit != -1){
				wall.remove(blockHit);
				game.getHUD().setScore(2);
				Random rand = new Random();
				int r = rand.nextInt(5);
				keepDestroyingBlock += r;
				if(!game.ea.isEmpty() && r == 0 && keepDestroyingBlock > 20){
					game.ea.remove(game.ea.getLast());
					keepDestroyingBlock = 0;
				}
			}
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
			if(blockHit != -1){
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
				wall.remove(blockHit);
				game.getHUD().setScore(1);
				int r = rand.nextInt(3);
				keepDestroyingBlock += r;
				if(keepDestroyingBlock > 8){
					game.eb.remove(tempEnt);
					keepDestroyingBlock = 0;
				}
			}
		}
		
		for(int i = 0; i < game.ec.size(); i++){
			EntityC tempEnt = game.ec.get(i);
			blockHit = Physics.BlockCollision(this, tempEnt);
			if(blockHit != -1){
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
				if(!game.ec.isEmpty() && r == 0)
					game.ec.remove(tempEnt);
			}
		}
	}
	
	/**
	 * Draw.
	 *
	 * @param g the g
	 */
	public void draw(Graphics2D g){
		g.setColor(Color.GREEN);
		for(int i = 0; i < wall.size(); i++){
			g.fill(wall.get(i));
		}
	}
	
	/**
	 * Basic blocks.
	 *
	 * @param xPos the x pos
	 * @param yPos the y pos
	 */
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
	
	/**
	 * Row.
	 *
	 * @param rows the rows
	 * @param xPos the x pos
	 * @param yPos the y pos
	 */
	public void row(int rows, int xPos, int yPos){
		for(int i = 0; i < rows; i++){
			Rectangle brick = new Rectangle(xPos + (i * 3), yPos, 3, 3);
			wall.add(brick);
		}
	}
	
	/**
	 * Reset.
	 */
	public void reset(){
		wall.clear();

		basicBlocks(45, 370);
		basicBlocks(215, 370);
		basicBlocks(385, 370);
		basicBlocks(555, 370);
	}
	
	/**
	 * Gets the bounds.
	 *
	 * @param i the i
	 * @return the bounds
	 */
	public Rectangle getBounds(int i){
		return new Rectangle((int)wall.get(i).getX(), (int)wall.get(i).getY(), (int)wall.get(i).getWidth(), (int)wall.get(i).getHeight());
	}
	
	/**
	 * Gets the x.
	 *
	 * @param i the i
	 * @return the x
	 */
	public double getX(int i) {
		return (int)wall.get(i).getX();
	}

	/**
	 * Gets the y.
	 *
	 * @param i the i
	 * @return the y
	 */
	public double getY(int i){
		return (int)wall.get(i).getY();
	}
}
