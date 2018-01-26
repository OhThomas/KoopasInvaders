package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityD;

public class Controller {
	private LinkedList<EntityA> ea = new LinkedList<EntityA>();
	private LinkedList<EntityB> eb = new LinkedList<EntityB>();
	private LinkedList<EntityC> ec = new LinkedList<EntityC>();
	private LinkedList<EntityD> ed = new LinkedList<EntityD>();
	
	EntityA enta;
	EntityB entb;
	EntityC entc;
	EntityD entd;
	private Textures tex;
	private Game game;
	private Enemy enemy;
	
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
	}
	
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
	}
	
	public void addEntity(EntityA block){
		ea.add(block);
	}
	
	public void removeEntity(EntityA block){
		ea.remove(block);
	}
	
	public void addEntity(EntityB block){
		eb.add(block);
	}
	
	public void removeEntity(EntityB block){
		eb.remove(block);
	}
	
	public void addEntity(EntityC block){
		ec.add(block);
	}
	
	public void removeEntity(EntityC block){
		ec.remove(block);
	}
	
	public void addEntity(EntityD block){
		ed.add(block);
	}
	
	public void removeEntity(EntityD block){
		ed.remove(block);
	}
	
	public LinkedList<EntityA> getEntityA(){
		return ea;
	}
	
	public LinkedList<EntityB> getEntityB(){
		return eb;
	}
	
	public LinkedList<EntityC> getEntityC(){
		return ec;
	}
	
	public LinkedList<EntityD> getEntityD(){
		return ed;
	}
}
