/*
 * 
 */
package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityD;
import com.game.src.main.libs.Animation;

// TODO: Auto-generated Javadoc
/**
 * The Class ChainChompItem.
 */
public class ChainChompItem extends GameObject implements EntityD{
	
	/** The tex. */
	private Textures tex;
	
	/** The game. */
	private Game game;
	
	/** The vel Y. */
	protected double velX, velY;
	
	/** The item name. */
	private String itemName = "chainChompItem";
	
	/** The item spawn location. */
	private boolean itemSpawnLocation = false;
	
	/** The anim. */
	Animation anim;
	
	/** The anim backwards. */
	Animation animBackwards;

	/** The item sound loop. */
	SoundLoops itemSoundLoop;
	
	/**
	 * Instantiates a new chain chomp item.
	 *
	 * @param x the x
	 * @param y the y
	 * @param tex the tex
	 * @param game the game
	 */
	public ChainChompItem(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		velX = 0;
		velY = 0;
		
		anim = new Animation(1, tex.chainChompItem[0], tex.chainChompItem[1], tex.chainChompItem[2], tex.chainChompItem[3],
				tex.chainChompItem[4], tex.chainChompItem[5], tex.chainChompItem[6], tex.chainChompItem[7],
				tex.chainChompItem[8], tex.chainChompItem[9], tex.chainChompItem[10], tex.chainChompItem[11],
				tex.chainChompItem[12], tex.chainChompItem[13], tex.chainChompItem[14], tex.chainChompItem[15],
				tex.chainChompItem[16], tex.chainChompItem[17], tex.chainChompItem[18], tex.chainChompItem[19],
				tex.chainChompItem[20], tex.chainChompItem[21], tex.chainChompItem[22], tex.chainChompItem[23],
				tex.chainChompItem[24]);
		animBackwards = new Animation(1, tex.chainChompItem[24], tex.chainChompItem[23], tex.chainChompItem[22], tex.chainChompItem[21],
				tex.chainChompItem[20], tex.chainChompItem[19], tex.chainChompItem[18], tex.chainChompItem[17],
				tex.chainChompItem[16], tex.chainChompItem[15], tex.chainChompItem[14], tex.chainChompItem[13],
				tex.chainChompItem[12], tex.chainChompItem[11], tex.chainChompItem[10], tex.chainChompItem[9],
				tex.chainChompItem[8], tex.chainChompItem[7], tex.chainChompItem[6], tex.chainChompItem[5],
				tex.chainChompItem[4], tex.chainChompItem[3], tex.chainChompItem[2], tex.chainChompItem[1],
				tex.chainChompItem[0]);
		
		if(this.getX() < Game.WIDTH)
			itemSpawnLocation = false;
		else
			itemSpawnLocation = true;
		String itemFile = "res/Sounds/SFX/Items/sm64_chain_chomp.wav";
		SoundLoops itemSoundLoop = new SoundLoops(itemFile);
		itemSoundLoop.reduceSound(10f);
		this.itemSoundLoop = itemSoundLoop;
	}

	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityD#tick()
	 */
	@Override
	public void tick() {
		if(itemSpawnLocation == false){
			x += 2.5; //1.5
			y += velY;
			if(y > Game.HEIGHT * Game.SCALE - 33){
				velY = velY - 0.2; //0.5
			}
			else if(y < Game.HEIGHT * Game.SCALE){
				velY = velY + 0.2; //0.5
			}
			if (this.getX() >= (Game.WIDTH * Game.SCALE)){
				game.ed.remove(this);
			}
		}
		else{
			x -= 2.5; //1.5
			y += velY;
			if(y > Game.HEIGHT * Game.SCALE - 33){
				velY = velY - 0.2; //0.5
			}
			else if(y < Game.HEIGHT * Game.SCALE){
				velY = velY + 0.2; //0.5
			}
			if (this.getX() +16 <= 0){
				game.ed.remove(this);
			}
		}
		
		for(int i = 0; i < game.ea.size()-1; i++){
			EntityA tempEnt = game.ea.get(i);
			if(Physics.Collision(this, tempEnt)){
				game.ed.remove(this);
				if(!game.ea.isEmpty())
					game.ea.remove(game.ea.getLast());
			}
		}
		/*
		if(Physics.Collision(this, game.ea.getFirst())){
			//MAKE PLAYER INVULNERABLE THROUGH BOOLEAN OR SOMETHIN(HEALTH + EXTRA INT MAYBE)
			game.ec.remove(this);
		}*/
		if(itemSpawnLocation == false)
			anim.runAnimation();
		else
			animBackwards.runAnimation();
	}

	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityD#render(java.awt.Graphics)
	 */
	@Override
	public void render(Graphics g) {
		if(itemSpawnLocation == false)
			anim.drawAnimation(g, x, y, 0);
		else
			animBackwards.drawAnimation(g, x, y, 0);
	}

	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityD#getBounds()
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 14, 16);
	}

	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityD#getX()
	 */
	@Override
	public double getX() {
		return x;
	}

	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityD#getY()
	 */
	@Override
	public double getY() {
		return y;
	}

	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityD#getItemName()
	 */
	@Override
	public String getItemName() {
		return itemName;
	}

	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityD#getItemSoundLoop()
	 */
	public SoundLoops getItemSoundLoop() {
		return itemSoundLoop;
	}

	/**
	 * Sets the item sound loop.
	 *
	 * @param itemSoundLoop the new item sound loop
	 */
	public void setItemSoundLoop(SoundLoops itemSoundLoop) {
		this.itemSoundLoop = itemSoundLoop;
	}
	
}
