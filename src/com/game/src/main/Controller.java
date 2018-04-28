/*
 * 
 */
package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityD;
import com.game.src.main.classes.EntityE;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
public class Controller {
	
	/** The ea. */
	private LinkedList<EntityA> ea = new LinkedList<EntityA>();
	
	/** The eb. */
	private LinkedList<EntityB> eb = new LinkedList<EntityB>();
	
	/** The ec. */
	private LinkedList<EntityC> ec = new LinkedList<EntityC>();
	
	/** The ed. */
	private LinkedList<EntityD> ed = new LinkedList<EntityD>();
	
	/** The ee. */
	private LinkedList<EntityE> ee = new LinkedList<EntityE>();
	
	/** The enta. */
	EntityA enta;
	
	/** The entb. */
	EntityB entb;
	
	/** The entc. */
	EntityC entc;
	
	/** The entd. */
	EntityD entd;
	
	/** The ente. */
	EntityE ente;
	
	/** The tex. */
	private Textures tex;
	
	/** The game. */
	private Game game;
	
	/** The enemy. */
	private Enemy enemy;
	
	/**
	 * Instantiates a new controller.
	 *
	 * @param tex the tex
	 * @param game the game
	 */
	public Controller(Textures tex, Game game){
		this.tex = tex;
		this.game = game;
		/*ENEMY SPAWNS!
		for(int i = 0; i < (Game.WIDTH * Game.SCALE); i+=64){
			addEntity(new Enemy(i,0, tex, this , game));
		}
		*/
	}
	/*
	public void createEnemy(int enemy_count){
		for(int i = 0; i < (Game.WIDTH * Game.SCALE); i+=64){
			addEntity(new Enemy(i,0, tex, this , game));
		}
	}
	*/
	
	/**
	 * Tick.
	 */
	public void tick(){
		//A CLASS
		for(int i = 0; i < ea.size(); i++){
			enta = ea.get(i);
			
			enta.tick();
		}
		//B CLASS
				for(int i = 0; i < eb.size(); i++){
					entb = eb.get(i);
					
					entb.tick();
				}
				//C CLASS
				for(int i = 0; i < ec.size(); i++){
					entc = ec.get(i);
					
					entc.tick();
				}
				//D CLASS
				for(int i = 0; i < ed.size(); i++){
					entd = ed.get(i);
					
					entd.tick();
				}
				//E CLASS
				for(int i = 0; i < ee.size(); i++){
					ente = ee.get(i);
					
					ente.tick();
				}
	}
	
	/**
	 * Render.
	 *
	 * @param g the g
	 */
	public void render(Graphics g){
		//A CLASS
		for(int i = 0; i < ea.size(); i++){
			enta = ea.get(i);
			
			enta.render(g);
		}
		//B CLASS
			for(int i = 0; i < eb.size(); i++){
				entb = eb.get(i);
				
				entb.render(g);
			}
		//C CLASS
			for(int i = 0; i < ec.size(); i++){
				entc = ec.get(i);
				
				entc.render(g);
			}
		//D CLASS
			for(int i = 0; i < ed.size(); i++){
				entd = ed.get(i);
				
				entd.render(g);
			}
		//E CLASS
			for(int i = 0; i < ee.size(); i++){
				ente = ee.get(i);
				
				ente.render(g);
			}
	}
	
	/**
	 * Adds the entity.
	 *
	 * @param block the block
	 */
	public void addEntity(EntityA block){
		ea.add(block);
	}
	
	/**
	 * Removes the entity.
	 *
	 * @param block the block
	 */
	public void removeEntity(EntityA block){
		ea.remove(block);
	}
	
	/**
	 * Adds the entity.
	 *
	 * @param block the block
	 */
	public void addEntity(EntityB block){
		eb.add(block);
	}
	
	/**
	 * Removes the entity.
	 *
	 * @param block the block
	 */
	public void removeEntity(EntityB block){
		eb.remove(block);
	}
	
	/**
	 * Adds the entity.
	 *
	 * @param block the block
	 */
	public void addEntity(EntityC block){
		ec.add(block);
	}
	
	/**
	 * Removes the entity.
	 *
	 * @param block the block
	 */
	public void removeEntity(EntityC block){
		ec.remove(block);
	}
	
	/**
	 * Adds the entity.
	 *
	 * @param block the block
	 */
	public void addEntity(EntityD block){
		ed.add(block);
	}
	
	/**
	 * Removes the entity.
	 *
	 * @param block the block
	 */
	public void removeEntity(EntityD block){
		ed.remove(block);
	}
	
	/**
	 * Adds the entity.
	 *
	 * @param block the block
	 */
	public void addEntity(EntityE block){
		ee.add(block);
	}
	
	/**
	 * Removes the entity.
	 *
	 * @param block the block
	 */
	public void removeEntity(EntityE block){
		ee.remove(block);
	}
	
	/**
	 * Gets the entity A.
	 *
	 * @return the entity A
	 */
	public LinkedList<EntityA> getEntityA(){
		return ea;
	}
	
	/**
	 * Gets the entity B.
	 *
	 * @return the entity B
	 */
	public LinkedList<EntityB> getEntityB(){
		return eb;
	}
	
	/**
	 * Gets the entity C.
	 *
	 * @return the entity C
	 */
	public LinkedList<EntityC> getEntityC(){
		return ec;
	}
	
	/**
	 * Gets the entity D.
	 *
	 * @return the entity D
	 */
	public LinkedList<EntityD> getEntityD(){
		return ed;
	}
	
	/**
	 * Gets the entity E.
	 *
	 * @return the entity E
	 */
	public LinkedList<EntityE> getEntityE(){
		return ee;
	}
}
