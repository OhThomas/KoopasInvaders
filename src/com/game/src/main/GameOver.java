/*
 * 
 */
package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Class GameOver.
 */
public class GameOver {

	/** The play button. */
	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120, 200, 128, 64);
	
	/** The help button. */
	public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 120, 300, 128, 64);
	
	/** The quit button. */
	public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 120, 400, 128, 64);
	
	/** The set score button. */
	public Rectangle setScoreButton = new Rectangle(40,20, 70, 16);
	
	/** The title. */
	private BufferedImage title = null;
	
	/** The play title. */
	private BufferedImage playTitle = null;
	
	/** The help title. */
	private BufferedImage helpTitle = null;
	
	/** The exit title. */
	private BufferedImage exitTitle = null;
	
	/** The set score title. */
	private BufferedImage setScoreTitle = null;
	
	/**
	 * Render.
	 *
	 * @param g the g
	 */
	public void render(Graphics g){
		//Graphics2D g2d = (Graphics2D) g;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			title = loader.loadImage("/gameover1bigger.png");
			playTitle = loader.loadImage("/newplaybutton.png");
			helpTitle = loader.loadImage("/newhelpbutton.png");
			exitTitle = loader.loadImage("/newexitbutton.png");
			setScoreTitle = loader.loadImage("/setScoreSmaller.png");
		}catch(IOException e){
			e.printStackTrace();
		}
		
		g.drawImage(title, 70, 100, null);
		g.drawImage(playTitle, Game.WIDTH / 2 + 120, 200, null);
		g.drawImage(helpTitle, Game.WIDTH / 2 + 120, 300, null);
		g.drawImage(exitTitle, Game.WIDTH / 2 + 120, 400, null);
		g.drawImage(setScoreTitle, 40, 20, null);
		
		//g2d.draw(playButton);
		//g2d.draw(helpButton);
		//g2d.draw(quitButton);
		
		/*
		Font fnt0 = new Font("Helvetica", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("KOOPA'S INVADERS!", (Game.WIDTH / 2) - 100 , 100);
		*/
	}
}
