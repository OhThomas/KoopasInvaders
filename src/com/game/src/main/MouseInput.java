/*
 * 
 */
package com.game.src.main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import com.game.src.main.Game.STATE;

// TODO: Auto-generated Javadoc
/**
 * The Class MouseInput.
 */
public class MouseInput implements MouseListener {

	/** The game. */
	public Game game;
	
	/**
	 * Instantiates a new mouse input.
	 *
	 * @param game the game
	 */
	public MouseInput(Game game) {
		this.game = game;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e) {

		int mx = e.getX();
		int my = e.getY();

		/**
		 * 
			public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120, 200, 128, 64);
			public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 120, 300, 128, 64);
			public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 120, 400, 128, 64);
		 */

		// Play Button
		if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.MENU ||
				mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.GAMEOVER) {
			if (my >= 200 && my <= 264) {
				// Pressed Play Button
				if(Game.State == STATE.GAMEOVER)
					Game.State = Game.STATE.RESET;
				else
					Game.State = Game.STATE.TRANSITION_ENTRANCE;
			}
		}

		// Help Button
		if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.MENU ||
				mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.GAMEOVER) {
			if (my >= 300 && my <= 364) {
				// Pressed Help Button
				System.exit(1);
			}
		}

		// Quit Button
		if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.MENU ||
				mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.GAMEOVER) {
			if (my >= 400 && my <= 464) {
				// Pressed Quit Button
				System.exit(1);
			}
		}
		
		if(mx >= 40 && mx <= 110 && Game.State == Game.STATE.GAMEOVER) {
			if(my >= 20 && my <= 36) {
				// Pressed Submit Score
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						Game.showFXMLWindow("Submit Score","ScoreScreen.fxml");
					}
				});
			}
		}
		
		if(mx >=  Game.WIDTH / 2 + 380 && mx <= Game.WIDTH / 2 + 470 && Game.State == Game.STATE.GAMEOVER) {
			if(my >= 20 && my <= 36) {
				// Pressed Leaderboard
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						Game.showFXMLWindow("Leaderboard","Leaderboard.fxml");
					}
				});
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
