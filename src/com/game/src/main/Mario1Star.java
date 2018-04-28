/*
 * 
 */
package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityD;
import com.game.src.main.libs.Animation;

// TODO: Auto-generated Javadoc
/**
 * The Class Mario1Star.
 */
public class Mario1Star extends GameObject implements EntityD{
	
	/** The tex. */
	private Textures tex;
	
	/** The game. */
	private Game game;
	
	/** The vel Y. */
	protected double velX, velY;
	
	/** The item name. */
	private String itemName = "mario1Star";
	
	/** The item spawn location. */
	private boolean itemSpawnLocation = false;
	
	/** The anim. */
	Animation anim;
	
	/** The item sound loop. */
	SoundLoops itemSoundLoop;
	
	/**
	 * Instantiates a new mario 1 star.
	 *
	 * @param x the x
	 * @param y the y
	 * @param tex the tex
	 * @param game the game
	 */
	public Mario1Star(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		velX = 0;
		velY = 0;
		
		anim = new Animation(6, tex.mario1Star[0], tex.mario1Star[1], tex.mario1Star[2], tex.mario1Star[3]);
		
		if(this.getX() < Game.WIDTH)
			itemSpawnLocation = false;
		else
			itemSpawnLocation = true;
		String itemFile = "res/Sounds/SFX/Items/powerstar.wav";
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
			if (this.getX()+16 <= 0){
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
		anim.runAnimation();
	}

	/* (non-Javadoc)
	 * @see com.game.src.main.classes.EntityD#render(java.awt.Graphics)
	 */
	@Override
	public void render(Graphics g) {
		anim.drawAnimation(g, x, y, 0);
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
	@Override
	public SoundLoops getItemSoundLoop() {
		return itemSoundLoop;
	}

}
