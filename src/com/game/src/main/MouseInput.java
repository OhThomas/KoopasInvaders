package com.game.src.main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

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
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
