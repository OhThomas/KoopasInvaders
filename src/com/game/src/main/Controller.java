package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityD;
import com.game.src.main.classes.EntityE;
import com.game.src.main.classes.EntityF;

public class Controller {
	private LinkedList<EntityA> ea = new LinkedList<EntityA>();
	private LinkedList<EntityB> eb = new LinkedList<EntityB>();
	private LinkedList<EntityB> ebGarbageCollect = new LinkedList<EntityB>();
	private LinkedList<EntityC> ec = new LinkedList<EntityC>();
	private LinkedList<EntityC> ecGarbageCollect = new LinkedList<EntityC>();
	private LinkedList<EntityD> ed = new LinkedList<EntityD>();
	private LinkedList<EntityD> edGarbageCollect = new LinkedList<EntityD>();
	private LinkedList<EntityE> ee = new LinkedList<EntityE>();
	private LinkedList<EntityE> eeGarbageCollect = new LinkedList<EntityE>();
	private LinkedList<EntityF> ef = new LinkedList<EntityF>();
	private LinkedList<EntityF> efGarbageCollect = new LinkedList<EntityF>();
	
	EntityA enta;//player+fireballs
	EntityB entb;//enemies
	EntityC entc;//enemy projectiles
	EntityD entd;//star+item balls
	EntityE ente;//items
	EntityF entf;//fx
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
					//if(!eb.get(i).getEntityBDead())
					
					entb.tick();
					if(game.bEntityRemoved) {
						i--;
						game.bEntityRemoved = false;
					}
					//System.out.println(i + " "+eb.get(i).getX());
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
				//F CLASS
				for(int i = 0; i < ef.size(); i++){
					entf = ef.get(i);
					
					entf.tick();
				}
	}
	
	public void render(Graphics g){
		boolean secondRender = false;
		//F CLASS
		for(int i = 0; i < ef.size(); i++){
			entf = ef.get(i);

			if(entf.entityName().equals("chainChompChainFX")) {
				if(!secondRender)
					secondRender = true;
			}
			else
				entf.render(g);
		}
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
			if(secondRender) {
				//F CLASS
				for(int i = 0; i < ef.size(); i++){
					entf = ef.get(i);
					if(entf.entityName().equals("chainChompChainFX"))
						entf.render(g);
				}
			}
	}
	
	public void reset() {
		for(int i = 0; i <= ea.size()-1; i++){
			ea.set(i, null);
		}
		ea.clear();
		for(int i = 0; i <= eb.size()-1; i++){
			eb.get(i).close();
			eb.set(i, null);
		}
		eb.clear();
		for(int i = 0; i <= ec.size()-1; i++){
			ec.get(i).close();
			ec.set(i, null);
		}
		ec.clear();
		for(int i = 0; i <= ed.size()-1; i++){
			ed.get(i).close();
			ed.set(i, null);
		}
		ed.clear();
		for(int i = 0; i <= ee.size()-1; i++){
			ee.get(i).close();
			ee.set(i, null);
		}
		ee.clear();
		for(int i = 0; i <= ef.size()-1; i++){
			ef.get(i).close();
			ef.set(i, null);
		}
		ef.clear();
		for(int i = 0; i <= ebGarbageCollect.size()-1; i++){
			//System.out.println(i);
			ebGarbageCollect.get(i).close();
			ebGarbageCollect.set(i, null);
		}
		ebGarbageCollect.clear();
		for(int i = 0; i <= ecGarbageCollect.size()-1; i++){
			ecGarbageCollect.get(i).close();
			ecGarbageCollect.set(i, null);
		}
		ecGarbageCollect.clear();
		for(int i = 0; i <= edGarbageCollect.size()-1; i++){
			edGarbageCollect.get(i).close();
			edGarbageCollect.set(i, null);
		}
		edGarbageCollect.clear();
		for(int i = 0; i <= eeGarbageCollect.size()-1; i++){
			eeGarbageCollect.get(i).close();
			eeGarbageCollect.set(i, null);
		}
		eeGarbageCollect.clear();
		for(int i = 0; i <= efGarbageCollect.size()-1; i++){
			efGarbageCollect.get(i).close();
			efGarbageCollect.set(i, null);
		}
		efGarbageCollect.clear();
		/*
		for(int i = 0; i <= Game.clipGarbageCollection.size()-1; i++) {
			if(Game.clipGarbageCollection.get(i).isActive()) {
				Game.clipGarbageCollection.get(i).stop();
				Game.clipGarbageCollection.get(i).setFramePosition(0);
			}
			Game.clipGarbageCollection.get(i).close();
		}
		Game.clipGarbageCollection.clear();
		*/
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
		//block.close();
		ebGarbageCollect.add(block);
		eb.remove(block);
	}
	
	public void addEntity(EntityC block){
		ec.add(block);
	}
	
	public void removeEntity(EntityC block){
		//block.close();
		ecGarbageCollect.add(block);
		ec.remove(block);
	}
	
	public void addEntity(EntityD block){
		ed.add(block);
	}
	
	public void removeEntity(EntityD block){
		//block.close();
		edGarbageCollect.add(block);
		ed.remove(block);
	}
	
	public void addEntity(EntityE block){
		ee.add(block);
	}
	
	public void removeEntity(EntityE block){
		//block.close();
		eeGarbageCollect.add(block);
		ee.remove(block);
	}

	public void addEntity(EntityF block){
		ef.add(block);
	}
	
	public void removeEntity(EntityF block){
		//block.close();
		efGarbageCollect.add(block);
		ef.remove(block);
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
	
	public LinkedList<EntityE> getEntityE(){
		return ee;
	}
	
	public LinkedList<EntityF> getEntityF(){
		return ef;
	}
}
