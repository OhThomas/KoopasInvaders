package com.game.src.main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import com.game.src.main.Game.STATE;

public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		

	}

	public void mouseEntered(MouseEvent e) {
		/*
		int mx = e.getPoint().x;
		int my = e.getPoint().y;
		System.out.println(mx + "\n" + my + "\n");
		if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.MENU ||
				mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.GAMEOVER) {
			if (my >= 200 && my <= 264) {
				System.out.println("WTF");
				// Pressed Play Button
				if(!Game.getPauseSoundFXSoundLoop().clipIsActive())
					Game.getPauseSoundFXSoundLoop().play();
			}
		}*/
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		Game.mouseIsClickedDown = true;
		Game.keysAreInUse = false;
		int mx = e.getX();
		int my = e.getY();
		
		// Play Button
		if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.MENU ||
			mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.GAMEOVER) {
			if (my >= 200 && my <= 264) {
				Game.playClicked = true;
			}
			else if(Game.playClicked)
				Game.playClicked = false;
		}
		else if(Game.playClicked)
			Game.playClicked = false;
		
		//Shop Button
		if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.MENU) {
			if (my >= 300 && my <= 364) {
				Game.shopClicked = true;
			}
			else if(Game.shopClicked)
				Game.shopClicked = false;
		}
		else if(Game.shopClicked)
			Game.shopClicked = false;
		
		//Exit Button
		if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.MENU ||
			mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.GAMEOVER) {
			if (my >= 400 && my <= 464 && Game.State != Game.STATE.GAME) 
				Game.exitClicked = true;
			else if(Game.exitClicked)
				Game.exitClicked = false;
		}
		else if(Game.exitClicked && Game.State != Game.STATE.GAME)
			Game.exitClicked = false;
		
		//Exit Button Paused
		if (mx >= Game.WIDTH / 2 + 121 && mx <= Game.WIDTH / 2 + 249 && Game.State == Game.STATE.GAME && Game.getUserHasPaused()) {
			if(Game.getUserHasPaused() && my >= 300 && my <= 364) 
				Game.exitClicked = true;
			else if(Game.exitClicked)
				Game.exitClicked = false;
		}
		else if(Game.exitClicked && Game.State == Game.STATE.GAME)
			Game.exitClicked = false;
		
		//Resume Button
		if (mx >= Game.WIDTH / 2 + 80 && mx <= Game.WIDTH / 2 + 288 && Game.State == Game.STATE.GAME && Game.getUserHasPaused()) {
			if(my >= 100 && my <= 164 && Game.getUserHasPaused()) 
				Game.resumeClicked = true;
			else if(Game.resumeClicked)
				Game.resumeClicked = false;
		}
		else if(Game.resumeClicked)
			Game.resumeClicked = false;
		
		
		//Home Button
		if (mx >= Game.WIDTH / 2 + 114 && mx <= Game.WIDTH / 2 + 254 && Game.State == Game.STATE.GAME && Game.getUserHasPaused() ||
				mx >= Game.WIDTH / 2 + 114 && mx <= Game.WIDTH / 2 + 254 && Game.State == Game.STATE.GAMEOVER) {
			if(my >= 200 && my <= 264 && Game.State == Game.STATE.GAME && Game.getUserHasPaused() ||
					(my >= 300 && my <= 364 && Game.State == Game.STATE.GAMEOVER)) 
				Game.homeClicked = true;
			else if(Game.homeClicked)
				Game.homeClicked = false;
		}
		else if(Game.homeClicked)
			Game.homeClicked = false;
		
		//Back Button
		if(mx >= 40 && mx <= 88 && (Game.State == Game.STATE.SET_SCORE || Game.State == Game.STATE.LEADERBOARD || Game.State == Game.STATE.SHOP
				|| Game.State == Game.STATE.HELP || Game.State == Game.STATE.SETTINGS)) {
			if(my >= 20 && my <= 36) {
				Game.backClicked = true;
			}
			else if(Game.backClicked)
				Game.backClicked = false;
		}
		else if(Game.backClicked)
			Game.backClicked = false;
		
		//Help Button
		if(mx >= 54 && mx <= 86 && (Game.State == Game.STATE.MENU)) {
			if(my >= 20 && my <= 36) {
				Game.helpClicked = true;
			}
			else if(Game.helpClicked)
				Game.helpClicked = false;
		}
		else if(Game.helpClicked)
			Game.helpClicked = false;
		
		//Settings Button
		if(mx >= 312 && mx <= 376 && (Game.State == Game.STATE.MENU)) {
			if(my >= 20 && my <= 36) {
				Game.settingsClicked = true;
			}
			else if(Game.settingsClicked)
				Game.settingsClicked = false;
		}
		else if(Game.settingsClicked)
			Game.settingsClicked = false;
		
		//Submit Score Button
		if(mx >= 40 && mx <= 110 && Game.State == Game.STATE.GAMEOVER) {
			if(my >= 20 && my <= 36) {
				Game.setScoreClicked = true;
			}
			else if(Game.setScoreClicked)
				Game.setScoreClicked = false;
		}
		else if(Game.setScoreClicked)
			Game.setScoreClicked = false;
		
		//Leaderboard Button
		if(mx >=  Game.WIDTH / 2 + 380 && mx <= Game.WIDTH / 2 + 470 && (Game.State == Game.STATE.GAMEOVER || Game.State == Game.STATE.MENU)) {
			if(my >= 20 && my <= 36) 
				Game.leaderboardClicked = true;
			else if(Game.leaderboardClicked)
				Game.leaderboardClicked = false;
		}
		else if(Game.leaderboardClicked)
			Game.leaderboardClicked = false;
		//Skip Button
		if(mx >= Game.WIDTH-73 && mx <= Game.WIDTH+93 && (Game.State == Game.STATE.TRANSITION_ENTRANCE || Game.State == Game.STATE.TRANSITION_ITEM)) {
			if(my >= Game.HEIGHT-32 && my <= Game.HEIGHT+32 && Game.askToSkipSequence) 
				Game.skipClicked = true;
			else if(Game.skipClicked)
				Game.skipClicked = false;
		}
		else if(Game.skipClicked)
			Game.skipClicked = false;
		if(Game.State == Game.STATE.SHOP) {
			//ArrowL1 Button
			if(mx >=  Game.WIDTH - 48 && mx <= Game.WIDTH - 32 && Game.State == Game.STATE.SHOP) {
				if(my >= 120 && my <= 152) 
					Game.arrowL1Clicked = true;
				else if(Game.arrowL1Clicked)
					Game.arrowL1Clicked = false;
			}
			else if(Game.arrowL1Clicked)
				Game.arrowL1Clicked = false;
			
			//ArrowR1 Button
			if(mx >=  Game.WIDTH + 48 && mx <= Game.WIDTH + 64 && Game.State == Game.STATE.SHOP) {
				if(my >= 120 && my <= 152) 
					Game.arrowR1Clicked = true;
				else if(Game.arrowR1Clicked)
					Game.arrowR1Clicked = false;
			}
			else if(Game.arrowR1Clicked)
				Game.arrowR1Clicked = false;
			
			//Buy1 Button
			if(mx >=  Game.WIDTH - 5 && mx <= Game.WIDTH + 24 && Game.State == Game.STATE.SHOP && Game.currentSkinLocked == true) {
				if(my >= 127 && my <= 144 ) 
					Game.buy1Clicked = true;
				else if(Game.buy1Clicked)
					Game.buy1Clicked = false;
			}
			else if(Game.buy1Clicked)
				Game.buy1Clicked = false;
			
			//Set1 Button
			if(mx >=  Game.WIDTH  + 108 && mx <= Game.WIDTH + 132 && Game.State == Game.STATE.SHOP) {
				if(my >= 128 && my <= 144 ) 
					Game.set1Clicked = true;
				else if(Game.set1Clicked)
					Game.set1Clicked = false;
			}
			else if(Game.set1Clicked)
				Game.set1Clicked = false;
			
			//ArrowL2 Button
			if(mx >=  Game.WIDTH - 48 && mx <= Game.WIDTH - 32 && Game.State == Game.STATE.SHOP) {
				if(my >= 220 && my <= 252) 
					Game.arrowL2Clicked = true;
				else if(Game.arrowL2Clicked)
					Game.arrowL2Clicked = false;
			}
			else if(Game.arrowL2Clicked)
				Game.arrowL2Clicked = false;
			
			//ArrowR2 Button
			if(mx >=  Game.WIDTH + 48 && mx <= Game.WIDTH + 64 && Game.State == Game.STATE.SHOP) {
				if(my >= 220 && my <= 252) 
					Game.arrowR2Clicked = true;
				else if(Game.arrowR2Clicked)
					Game.arrowR2Clicked = false;
			}
			else if(Game.arrowR2Clicked)
				Game.arrowR2Clicked = false;
			
			//Buy2 Button
			if(mx >=  Game.WIDTH - 5 && mx <= Game.WIDTH + 24 && Game.State == Game.STATE.SHOP && Game.currentTrackLocked == true) {
				if(my >= 227 && my <= 244 ) 
					Game.buy2Clicked = true;
				else if(Game.buy2Clicked)
					Game.buy2Clicked = false;
			}
			else if(Game.buy2Clicked)
				Game.buy2Clicked = false;
			
			//Set2 Button
			if(mx >=  Game.WIDTH  + 108 && mx <= Game.WIDTH + 132 && Game.State == Game.STATE.SHOP) {
				if(my >= 228 && my <= 244 ) 
					Game.set2Clicked = true;
				else if(Game.set2Clicked)
					Game.set2Clicked = false;
			}
			else if(Game.set2Clicked)
				Game.set2Clicked = false;
			
			//ArrowL3 Button
			if(mx >=  Game.WIDTH - 48 && mx <= Game.WIDTH - 32 && Game.State == Game.STATE.SHOP) {
				if(my >= 320 && my <= 352) 
					Game.arrowL3Clicked = true;
				else if(Game.arrowL3Clicked)
					Game.arrowL3Clicked = false;
			}
			else if(Game.arrowL3Clicked)
				Game.arrowL3Clicked = false;
			
			//ArrowR3 Button
			if(mx >=  Game.WIDTH + 48 && mx <= Game.WIDTH + 64 && Game.State == Game.STATE.SHOP) {
				if(my >= 320 && my <= 352) 
					Game.arrowR3Clicked = true;
				else if(Game.arrowR3Clicked)
					Game.arrowR3Clicked = false;
			}
			else if(Game.arrowR3Clicked)
				Game.arrowR3Clicked = false;
			
			//Buy3 Button
			if(mx >=  Game.WIDTH - 5 && mx <= Game.WIDTH + 24 && Game.State == Game.STATE.SHOP && Game.currentFireballLocked == true) {
				if(my >= 327 && my <= 344 ) 
					Game.buy3Clicked = true;
				else if(Game.buy3Clicked)
					Game.buy3Clicked = false;
			}
			else if(Game.buy3Clicked)
				Game.buy3Clicked = false;
			
			//Set3 Button
			if(mx >=  Game.WIDTH  + 108 && mx <= Game.WIDTH + 132 && Game.State == Game.STATE.SHOP) {
				if(my >= 328 && my <= 344 ) 
					Game.set3Clicked = true;
				else if(Game.set3Clicked)
					Game.set3Clicked = false;
			}
			else if(Game.set3Clicked)
				Game.set3Clicked = false;
	
			//ArrowL4 Button
			if(mx >=  Game.WIDTH - 48 && mx <= Game.WIDTH - 32 && Game.State == Game.STATE.SHOP) {
				if(my >= 420 && my <= 452) 
					Game.arrowL4Clicked = true;
				else if(Game.arrowL4Clicked)
					Game.arrowL4Clicked = false;
			}
			else if(Game.arrowL3Clicked)
				Game.arrowL4Clicked = false;
			
			//ArrowR4 Button
			if(mx >=  Game.WIDTH + 48 && mx <= Game.WIDTH + 64 && Game.State == Game.STATE.SHOP) {
				if(my >= 420 && my <= 452) 
					Game.arrowR4Clicked = true;
				else if(Game.arrowR4Clicked)
					Game.arrowR4Clicked = false;
			}
			else if(Game.arrowR4Clicked)
				Game.arrowR4Clicked = false;
			
			//Buy4 Button
			if(mx >=  Game.WIDTH - 5 && mx <= Game.WIDTH + 24 && Game.State == Game.STATE.SHOP && Game.currentItemLocked == true) {
				if(my >= 427 && my <= 444 ) 
					Game.buy4Clicked = true;
				else if(Game.buy4Clicked)
					Game.buy4Clicked = false;
			}
			else if(Game.buy4Clicked)
				Game.buy4Clicked = false;
			
			//Set4 Button
			if(mx >=  Game.WIDTH  + 108 && mx <= Game.WIDTH + 132 && Game.State == Game.STATE.SHOP) {
				if(my >= 428 && my <= 444 ) 
					Game.set4Clicked = true;
				else if(Game.set4Clicked)
					Game.set4Clicked = false;
			}
			else if(Game.set4Clicked)
				Game.set4Clicked = false;
		}
		if(Game.State == Game.STATE.SETTINGS) {
			if(Game.areYouSureBoolean) {
				//Yes Button in Settings Menu
				if(mx >=  Game.WIDTH  + 18 && mx <= Game.WIDTH + 18 + 96 && Game.State == Game.STATE.SETTINGS) {
					if(my >= 375 && my <= 439)
						Game.yesClicked = true;
					else if(Game.yesClicked)
						Game.yesClicked = false;
				}
				else if(Game.yesClicked)
					Game.yesClicked = false;
				
				//No Button in Settings Menu
				if(mx >=  Game.WIDTH - 64 - 18 && mx <= Game.WIDTH - 18 && Game.State == Game.STATE.SETTINGS) {
					if(my >= 375 && my <= 439)
						Game.noClicked = true;
					else if(Game.noClicked)
						Game.noClicked = false;
				}
				else if(Game.noClicked)
					Game.noClicked = false;
			}
			else {
				//ArrowL1 Button in Settings Menu
				if(mx >=  Game.WIDTH  - 125 && mx <= Game.WIDTH - 109 && Game.State == Game.STATE.SETTINGS) {
					if(my >= 121 && my <= 153) 
						Game.arrowL1Clicked = true;
					else if(Game.arrowL1Clicked)
						Game.arrowL1Clicked = false;
				}
				else if(Game.arrowL1Clicked)
					Game.arrowL1Clicked = false;
				
				//ArrowR1 Button in Settings Menu
				if(mx >=  Game.WIDTH + 109 && mx <= Game.WIDTH + 125 && Game.State == Game.STATE.SETTINGS) {
					if(my >= 121 && my <= 153) 
						Game.arrowR1Clicked = true;
					else if(Game.arrowR1Clicked)
						Game.arrowR1Clicked = false;
				}
				else if(Game.arrowR1Clicked)
					Game.arrowR1Clicked = false;
				
				//ArrowL2 Button in Settings Menu
				if(mx >=  Game.WIDTH  - 125 && mx <= Game.WIDTH - 109 && Game.State == Game.STATE.SETTINGS) {
					if(my >= 221 && my <= 253) 
						Game.arrowL2Clicked = true;
					else if(Game.arrowL2Clicked)
						Game.arrowL2Clicked = false;
				}
				else if(Game.arrowL2Clicked)
					Game.arrowL2Clicked = false;
				
				//ArrowR2 Button in Settings Menu
				if(mx >=  Game.WIDTH + 109 && mx <= Game.WIDTH + 125 && Game.State == Game.STATE.SETTINGS) {
					if(my >= 221 && my <= 253) 
						Game.arrowR2Clicked = true;
					else if(Game.arrowR2Clicked)
						Game.arrowR2Clicked = false;
				}
				else if(Game.arrowR2Clicked)
					Game.arrowR2Clicked = false;
				
				//Checkmark Button in Settings Menu
				if(mx >=  Game.WIDTH - 16 && mx <= Game.WIDTH + 16 && Game.State == Game.STATE.SETTINGS) {
					if(my >= 320 && my <= 352) {
						Game.checkMarkClicked = true;
						if(Game.smb3CheckmarkSoundLoop.clipIsActive())
							Game.smb3CheckmarkSoundLoop.stop();
						if(Game.smb3Checkmark2SoundLoop.clipIsActive())
							Game.smb3Checkmark2SoundLoop.stop();
						Game.smb3CheckmarkSoundLoop.play();
					}
					else if(Game.checkMarkClicked)
						Game.checkMarkClicked = false;
				}
				else if(Game.checkMarkClicked)
					Game.checkMarkClicked = false;
				
				//Reset Stats Button in Settings Menu
				if(mx >=  Game.WIDTH - 89 && mx <= Game.WIDTH + 89 && Game.State == Game.STATE.SETTINGS) {
					if(my >= 420 && my <= 452) 
						Game.resetStatsClicked = true;
					else if(Game.resetStatsClicked)
						Game.resetStatsClicked = false;
				}
				else if(Game.resetStatsClicked)
					Game.resetStatsClicked = false;
				
				//Volume Slider
				if(my >=  121 && my <= 153 && Game.State == Game.STATE.SETTINGS) {
					if(mx >= Game.WIDTH - 68  && mx <= Game.WIDTH - 60 ) {
						Game.volumeSliderPosition = 1;
						VolumeSlider.volumeSliderChangingVolume = true;
					}
					if(mx >= Game.WIDTH - 68 + 32  && mx <= Game.WIDTH - 60 + 32 ) {
						Game.volumeSliderPosition = 2;
						VolumeSlider.volumeSliderChangingVolume = true;
					}
					if(mx >= Game.WIDTH - 68 + (32*2)  && mx <= Game.WIDTH - 60 + (32*2) ) {
						Game.volumeSliderPosition = 3;
						VolumeSlider.volumeSliderChangingVolume = true;
					}
					if(mx >= Game.WIDTH - 68 + (32*3)  && mx <= Game.WIDTH - 60 + (32*3) ) {
						Game.volumeSliderPosition = 4;
						VolumeSlider.volumeSliderChangingVolume = true;
					}
					if(mx >= Game.WIDTH - 68 + (32*4)  && mx <= Game.WIDTH - 60 + (32*4) ) {
						Game.volumeSliderPosition = 5;
						VolumeSlider.volumeSliderChangingVolume = true;
					}
				}
			}
			
			//SFX/Music Slider
			if(my >=  221 && my <= 253 && Game.State == Game.STATE.SETTINGS) {
				if(mx >= Game.WIDTH - 68  && mx <= Game.WIDTH - 60 ) {
					Game.sfxMusicSliderPosition = 1;
					VolumeSlider.sfxMusicSliderChangingVolume = true;
				}
				if(mx >= Game.WIDTH - 68 + 32  && mx <= Game.WIDTH - 60 + 32 ) {
					Game.sfxMusicSliderPosition = 2;
					VolumeSlider.sfxMusicSliderChangingVolume = true;
				}
				if(mx >= Game.WIDTH - 68 + (32*2)  && mx <= Game.WIDTH - 60 + (32*2) ) {
					Game.sfxMusicSliderPosition = 3;
					VolumeSlider.sfxMusicSliderChangingVolume = true;
				}
				if(mx >= Game.WIDTH - 68 + (32*3)  && mx <= Game.WIDTH - 60 + (32*3) ) {
					Game.sfxMusicSliderPosition = 4;
					VolumeSlider.sfxMusicSliderChangingVolume = true;
				}
				if(mx >= Game.WIDTH - 68 + (32*4)  && mx <= Game.WIDTH - 60 + (32*4) ) {
					Game.sfxMusicSliderPosition = 5;
					VolumeSlider.sfxMusicSliderChangingVolume = true;
				}
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		long buttonTimer = 0;
		int mx = e.getX();
		int my = e.getY();

		/**
		 * 
			public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120, 200, 128, 64);
			public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 120, 300, 128, 64);
			public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 120, 400, 128, 64);
		 */
		if(!Game.keysAreInUse) {
			if(Game.State == Game.STATE.MENU) {//STAR EXPLOSION
				Boolean b = false;
				if(!(my >= 20 && my <= 36 && mx >= 54 && mx <= 86) && //help
					!(my >= 20 && my <= 36 &&mx >= 312 && mx <= 376) && //settings
					!(my >= 20 && my <= 36 && mx >=  Game.WIDTH / 2 + 380 && mx <= Game.WIDTH / 2 + 470) && //leaderboard
					!(my >= 200 && my <= 264 && mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248) && //play
					!(my >= 300 && my <= 364 && mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248) && //shop
					!(my >= 400 && my <= 464 && mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248)) { //exit
					/*
					!(my >= 100 && my <= 164 && mx >= 70 && mx <= 302) && //title Koopa
					!(my >= 100 && my <= 164 && mx >= 330 && mx <= 590) ||//title Invaders
					(my >= 124 && my <= 128 && mx >= 586 && mx <= 590) ||//s space
					(my >= 120 && my <= 124 && mx >= 582 && mx <= 590) ||//s space
					(my >= 140 && my <= 144 && mx >= 562 && mx <= 566) ||//rs space
					(my >= 136 && my <= 144 && mx >= 558 && mx <= 562) ||//rs space
					(my >= 128 && my <= 140 && mx >= 554 && mx <= 558) ||//rs space
					(my >= 132 && my <= 136 && mx >= 550 && mx <= 554) ||//rs space
					(my >= 104 && my <= 108 && mx >= 554 && mx <= 558) ||//rs space
					(my >= 100 && my <= 104 && mx >= 550 && mx <= 562) ||//rs space
					(my >= 140 && my <= 164 && mx >= 538 && mx <= 542) ||//r space
					(my >= 116 && my <= 122 && mx >= 538 && mx <= 542) ||//r space
					(my >= 140 && my <= 148 && mx >= 506 && mx <= 518) ||//e space
					(my >= 116 && my <= 124 && mx >= 506 && mx <= 518) ||//e space
					(my >= 152 && my <= 164 && mx >= 418 && mx <= 422) ||//v space
					(my >= 152 && my <= 164 && mx >= 398 && mx <= 402) ||//v space
					(my >= 144 && my <= 164 && mx >= 394 && mx <= 398) ||//v space
					(my >= 144 && my <= 164 && mx >= 422 && mx <= 426) ||//v space
					(my >= 116 && my <= 148 && mx >= 354 && mx <= 362) ||//i space
					(my >= 116 && my <= 148 && mx >= 330 && mx <= 338) ||//i space
					(my >= 132 && my <= 164 && mx >= 238 && mx <= 270) ||//'s space
					(my >= 124 && my <= 132 && mx >= 238 && mx <= 242) ||//'s space
					(my >= 124 && my <= 132 && mx >= 266 && mx <= 270) ||//'s space
					(my >= 128 && my <= 132 && mx >= 242 && mx <= 250) ||//'s space
					(my >= 128 && my <= 132 && mx >= 258 && mx <= 266) ||//'s space
					(my >= 144 && my <= 164 && mx >= 190 && mx <= 206) ||//pa space
					(my >= 140 && my <= 144 && mx >= 202 && mx <= 206) ||//pa space
					(my >= 120 && my <= 144 && mx >= 106 && mx <= 110) || (my >= 128 && my <= 144 && mx >= 102 && mx <= 106) ||
					(my >= 132 && my <= 140 && mx >= 98 && mx <= 102) || (my >= 132 && my <= 136 && mx >= 94 && mx <= 98) ||
					(my >= 100 && my <= 116 && mx >= 78 && mx <= 94) || (my >= 116 && my <= 120 && mx >= 86 && mx <= 90) ||
					(my >= 152 && my <= 164 && mx >= 86 && mx <= 90) || (my >= 156 && my <= 164 && mx >= 90 && mx <= 98) //k space
					*/
					b = true;
					if(mx >= 70 && mx <= Game.title.getWidth() + 70 && my >= 100 && my <= Game.title.getHeight() + 100) {
						if (Game.isPixelTransparentinBufferedImage(Game.title, mx-70, my-100)) {
							b = true;
						}
						else
							b = false;
					}
					if(b) {
						Game.starExplode = true;
						Game.mx = mx;
						Game.my = my;
					}
					
					
				}
			}else if(Game.State == Game.STATE.SHOP) {
				if(!(mx >=  Game.WIDTH - 48 && mx <= Game.WIDTH - 32 && //L buttons
					((my >= 120 && my <= 152) || (my >= 220 && my <= 252) ||
					(my >= 320 && my <= 352) || (my >= 420 && my <= 452))) &&
					!(mx >=  Game.WIDTH + 48 && mx <= Game.WIDTH + 64 && //R buttons
					((my >= 120 && my <= 152) || (my >= 220 && my <= 252) ||
					(my >= 320 && my <= 352) || (my >= 420 && my <= 452))) &&
					!(mx >=  Game.WIDTH - 5 && mx <= Game.WIDTH + 24 && // Buy Buttons
					((my >= 127 && my <= 144 && Game.currentSkinLocked) || (my >= 227 && my <= 244 && Game.currentTrackLocked) ||
					(my >= 327 && my <= 344 && Game.currentFireballLocked) || (my >= 427 && my <= 444 && Game.currentItemLocked)))&&
					!(mx >=  Game.WIDTH + 108 && mx <= Game.WIDTH + 132 && //Set buttons
					((my >= 128 && my <= 144) || ((my >= 228 && my <= 244) ||
					((my >= 328 && my <= 344))))) &&
					!(mx >= 40 && mx <= 88 && my >= 20 && my <= 36) &&//Back Button
					!(mx >= Game.WIDTH - 54 && mx <= Game.WIDTH - 54 + 128 && my >= 20 && my <= 84 && !(Game.isPixelTransparentinBufferedImage(Game.shopTitle, mx-(Game.WIDTH - 54), my-20))) &&//Shop Title
					!(mx >= 20 && mx <= 104 && my >= 120 && my <= 152 && !(Game.isPixelTransparentinBufferedImage(Game.skinTitle, mx-20, my-120))) &&//Skin Title
					!(mx >= 20 && mx <= 122 && my >= 220 && my <= 252 && !(Game.isPixelTransparentinBufferedImage(Game.tracksTitle, mx-20, my-220))) &&//Tracks Title
					!(mx >= 20 && mx <= 166 && my >= 320 && my <= 352 && !(Game.isPixelTransparentinBufferedImage(Game.fireballsTitle, mx-20, my-320))) &&//Fireball Title
					!(mx >= 20 && mx <= 106 && my >= 420 && my <= 452 && !(Game.isPixelTransparentinBufferedImage(Game.itemsTitle, mx-20, my-420))) &&//Item Title
					!(mx >= Game.WIDTH * Game.SCALE - Game.totalPointsImage.getWidth() - 60 &&
					  mx <= Game.WIDTH * Game.SCALE - Game.totalPointsImage.getWidth() - 60 + Game.totalPointsImage.getWidth() &&
					  my >= 20 && my <= 20 + Game.totalPointsImage.getHeight() && !(Game.isPixelTransparentinBufferedImage(Game.totalPointsImage, mx-(Game.WIDTH * Game.SCALE - Game.totalPointsImage.getWidth() - 60), my-20))) &&//Total Points
					!(mx >= Game.WIDTH * Game.SCALE - 55 && mx <= Game.WIDTH * Game.SCALE - 55 + ShopController.pointsImage.getWidth() &&
					my >= 20 + Game.totalPointsImage.getHeight()/2 -3 && my <= 20 + Game.totalPointsImage.getHeight()/2 -3 +
					ShopController.pointsImage.getHeight() && !(Game.isPixelTransparentinBufferedImage(ShopController.pointsImage, mx-(Game.WIDTH * Game.SCALE - 55), my-(20 + Game.totalPointsImage.getHeight()/2 -3)))) &&//points title
					!(mx >= Game.WIDTH && mx <= Game.WIDTH + 16 && my >= 120 && my <= 148) &&//Mario Skins
					!(Game.trackPosition == 0 && mx >= Game.WIDTH + 2 && mx <= Game.WIDTH + 2 + ShopController.songTrackImages[0].getWidth()&&
					my >= 227 && my <= 227+ShopController.songTrackImages[0].getHeight()) &&//Track Img 1
					!(Game.trackPosition == 1 && mx >= Game.WIDTH && mx <= Game.WIDTH + ShopController.songTrackImages[1].getWidth()&&
					my >= 223 && my <= 223+ShopController.songTrackImages[1].getHeight())&& //Track Img 2
					!(!(Game.fireballPosition == 3) && mx >= Game.WIDTH && mx <= Game.WIDTH + 16 && my >= 328 && my <= 344) && //Fireballs
					!((Game.fireballPosition == 3) && mx >= Game.WIDTH && mx <= Game.WIDTH + 16 && my >= 329 && my <= 343) &&//Fireballs(BuzzyBeetle)
					!(!(Game.currentItemLocked) && mx >= Game.WIDTH && mx <= Game.WIDTH + 16 && my >= 428 && my <= 444) &&//Items unlocked
					!((Game.currentItemLocked) && mx >= Game.WIDTH && mx <= Game.WIDTH + 18 && my >= 426 && my <= 444) &&//Items locked
					!((Game.currentlySelectedCharacterSkin == Game.characterSkinPosition) && mx >= Game.WIDTH - 2 &&
					mx <= Game.WIDTH +16 && my >= 101 && my <= 119) &&//Skin Position Selected
					!((Game.currentlySelectedTrack == Game.trackPosition) && mx >= Game.WIDTH - 2 &&
					mx <= Game.WIDTH +16 && my >= 201 && my <= 219) &&//Track Position Selected
					!((Game.currentlySelectedFireball == Game.fireballPosition) && mx >= Game.WIDTH - 2 &&
					mx <= Game.WIDTH +16 && my >= 301 && my <= 319) &&//Fireball Position Selected
					!((Game.currentlySelectedItem == Game.itemPosition) && mx >= Game.WIDTH - 2 &&
					mx <= Game.WIDTH +16 && my >= 401 && my <= 419) &&//Item Position Selected
					!(!(Game.currentlySelectedCharacterSkin == Game.characterSkinPosition) && mx >= Game.WIDTH + 2 &&
					mx <= Game.WIDTH +12 && my >= 105 && my <= 115) &&//Skin Position Unselected
					!(!(Game.currentlySelectedTrack == Game.trackPosition) && mx >= Game.WIDTH + 2 &&
					mx <= Game.WIDTH +12 && my >= 205 && my <= 215) &&//Track Position Unselected
					!(!(Game.currentlySelectedFireball == Game.fireballPosition) && mx >= Game.WIDTH + 2 &&
					mx <= Game.WIDTH +12 && my >= 305 && my <= 315) &&//Fireball Position Unselected
					!(!(Game.currentlySelectedItem == Game.itemPosition) && mx >= Game.WIDTH + 2 &&
					mx <= Game.WIDTH +12 && my >= 405 && my <= 415)//Item Position Unselected
					) {
					Game.starExplode = true;
					Game.mx = mx;
					Game.my = my;
				}
			}else if(Game.State == Game.STATE.HELP) {
				if(!(mx >= 40 && mx <= 88 && my >= 20 && my <= 36)) {
					Game.starExplode = true;
					Game.mx = mx;
					Game.my = my;
				}
			}else if(Game.State == Game.STATE.SETTINGS) {
				if(!Game.areYouSureBoolean) {
					if(!(mx >= Game.WIDTH - 125 && mx <= Game.WIDTH - 109 &&
						((my >= 121 && my <= 153) || (my >= 221 && my <= 253))) &&//L Buttons
						!(mx >= Game.WIDTH + 109 && mx <= Game.WIDTH + 125 &&
						((my >= 121 && my <= 153) || (my >= 221 && my <= 253))) &&//R Buttons
						!(mx >= Game.WIDTH - 69 && mx <= Game.WIDTH - 59 && 
						((my >= 120 && my <= 154) || (my >= 220 && my <= 254))) &&//First Volume Slider Block
						!(mx >= Game.WIDTH - 37 && mx <= Game.WIDTH - 27 && 
						((my >= 120 && my <= 154) || (my >= 220 && my <= 254))) &&//Second Volume Slider Block
						!(mx >= Game.WIDTH - 5 && mx <= Game.WIDTH + 5 && 
						((my >= 120 && my <= 154) || (my >= 220 && my <= 254))) &&//Third Volume Slider Block
						!(mx >= Game.WIDTH + 27 && mx <= Game.WIDTH + 37 && 
						((my >= 120 && my <= 154) || (my >= 220 && my <= 254))) &&//Fourth Volume Slider Block
						!(mx >= Game.WIDTH + 59 && mx <= Game.WIDTH + 69 && 
						((my >= 120 && my <= 154) || (my >= 220 && my <= 254))) &&//Fifth Volume Slider Block
						!(mx >= Game.WIDTH - 69 && mx <= Game.WIDTH + 69 && 
						((my >= 134 && my <= 140) || (my >= 234 && my <= 240))) &&//Horizontal Volume Slider Line
						!(mx >= 40 && mx <= 88 && my >= 20 && my <= 36) &&//Back Button
						!(mx >= Game.WIDTH - 16 && mx <= Game.WIDTH + 16 && my >= 320 && my <= 352) &&//Checkmark Button	
						!(mx >= Game.WIDTH - 89 && mx <= Game.WIDTH + 89 && my >= 420 && my <= 452) &&//Reset Stats Button
						!(mx >= Game.WIDTH-128 && mx <= Game.WIDTH+128 && my >= 20 && my <= 84 &&
						!(Game.isPixelTransparentinBufferedImage(Game.settingsTitleBigger, mx-(Game.WIDTH-128), my-20))) &&//Settings Title
						!(mx >= 20 && mx <= 122 && my >= 120 && my <= 152 && 
						!(Game.isPixelTransparentinBufferedImage(Game.volumeTitle, mx-20, my-120))) &&//Volume Title
						!(my >= 220 && my <= 252 && (mx >= 20 && mx <= 188) &&
						!(Game.isPixelTransparentinBufferedImage(Game.sfxMusicTitle, mx-20, my-220))) &&//SFX/Music Title
						/*
						!((mx >= 76 && mx <= 81 && my >= 250 && my <= 252) || (mx >= 76 && mx <= 83 && my >= 241 && my <= 250) || 
						(mx >= 79 && mx <= 86 && my >= 235 && my <= 246) || (mx >= 81 && mx <= 89 && my >= 228 && my <= 239) || 
						(mx >= 84 && mx <= 92 && my >= 222 && my <= 232) || (mx >= 87 && mx <= 93 && my >= 220 && my <= 222)) &&// / Split Up
						*/
						!(my >= 320 && my <= 352 && ((mx >= 20 && mx <= 88) || (mx >= 104 && mx <= 270)))
						) {
						Game.starExplode = true;
						Game.mx = mx;
						Game.my = my;
					}
				}
			}else if (Game.State == Game.STATE.LEADERBOARD) {
				Boolean b = false;
				if(my > 84) {
					for(int i = 0; i <= Game.leaderboardImage.size() -1; i++) {
						if(!(mx >= 44 && mx <= 44 + Game.leaderboardImage.get(i).getWidth() -16 && my >= (i*20) + 105 + (int) LeaderboardController.y && my <= (i*20) + 105 + (int) LeaderboardController.y + Game.leaderboardImage.get(i).getHeight()))
							b = true;
						else {
							if(!(Game.isPixelTransparentinBufferedImage( Game.leaderboardImage.get(i), mx-44, my-((i*20) + 105 + (int) LeaderboardController.y)))) { //THIS WILL CHECK FOR TRANSPARENCY IN LEADERBOARD
							b = false;//																												I WANT TO DISABLE BECAUSE SCROLLING PAST THE TOPS IMG Y LOCATIONS ARE OFF
							break;}
						}
					}
				}
				else if(!(mx >= (Game.WIDTH/2) && mx <= (Game.WIDTH/2)+360 && my >= 20 && my <= 84 &&
						!(Game.isPixelTransparentinBufferedImage(Game.leaderboardTitleBigger, mx-(Game.WIDTH/2), my-20))))
					b = true;
				if(b && !(mx >= 40 && mx <= 88 && my >= 20 && my <= 36) ) {
					//System.out.println(Game.mx + " lead"+(Game.leaderboardImage.get(0).getWidth()+44));
					Game.starExplode = true;
					Game.mx = mx;
					Game.my = my;
				}
			}else if(Game.State == Game.STATE.GAMEOVER) {
				if(!(mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && my >= 200 && my <= 264) &&//Play button
					!(mx >= Game.WIDTH / 2 + 114 && mx <= Game.WIDTH / 2 + 254 && my >= 300 && my <= 364) &&//Home button
					!(mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && my >= 400 && my <= 464) &&//Exit button
					!(my >= 20 && my <= 36 && ((mx >= 40 && mx <= 110) || (mx >=  Game.WIDTH / 2 + 380 && mx <= Game.WIDTH / 2 + 470))) &&//Set Score & Leaderboard Button
					!(my >= 100 && my <= 164 && mx >= 170 && mx <= 490 &&
					!(Game.isPixelTransparentinBufferedImage(Game.gameOverTitle, mx-170, my-100))) /*||//Game Over
					(mx >= 486 && mx <= 490 && my >= 128 && my <= 140)||//r space
					(mx >= 482 && mx <= 486 && my >= 132 && my <= 136)||//r space
					(mx >= 482 && mx <= 490 && my >= 100 && my <= 104)||//r space
					(mx >= 486 && mx <= 490 && my >= 104 && my <= 108)||//r space
					(mx >= 470 && mx <= 474 && my >= 116 && my <= 124)||//r space
					(mx >= 470 && mx <= 474 && my >= 140 && my <= 164)||//r space
					(mx >= 442 && mx <= 454 && my >= 116 && my <= 124)||//e space
					(mx >= 442 && mx <= 454 && my >= 140 && my <= 148)||//e space
					(mx >= 422 && mx <= 426 && my >= 144 && my <= 164)||//v space
					(mx >= 394 && mx <= 398 && my >= 144 && my <= 164)||//v space
					(mx >= 398 && mx <= 402 && my >= 152 && my <= 164)||//v space
					(mx >= 418 && mx <= 422 && my >= 152 && my <= 164)||//v space
					(mx >= 290 && mx <= 306 && my >= 116 && my <= 124)||//e space
					(mx >= 290 && mx <= 306 && my >= 140 && my <= 148)||//e space
					(mx >= 250 && mx <= 262 && my >= 100 && my <= 108)||//m space
					(mx >= 254 && mx <= 258 && my >= 108 && my <= 120)||//m space
					(mx >= 250 && mx <= 254 && my >= 152 && my <= 164)||//m space
					(mx >= 254 && mx <= 258 && my >= 156 && my <= 164)||//m space
					(mx >= 258 && mx <= 262 && my >= 152 && my <= 164)||//m space
					(mx >= 186 && mx <= 202 && my >= 128 && my <= 132)//g space
					*/
					) {
					Game.starExplode = true;
					Game.mx = mx;
					Game.my = my;
				}
			}else if(Game.State == Game.STATE.SET_SCORE) {
				Boolean b = false;/*
				if(!Game.playerName.isEmpty() && mx >= Game.postLetterXPositionBeginning && mx <= Game.postLetterXPositionBeginning + Game.playerNameImage.getWidth() &&
					my >= 200 && my <= 200 + Game.playerNameImage.getHeight()) {
					if(Game.playerNameImage.getWidth()+Game.postLetterXPositionBeginning-mx >= 1)
					if(Game.isPixelTransparentinBufferedImage(Game.playerNameImage,mx-Game.postLetterXPositionBeginning,my-200)) {
						b = true;
					}
				}
				if(!b) {*/
					if(200 <= Game.postLetterXPositionBeginning) {
						if((mx >= 200+Game.postLetterXPosition && mx <= 216+Game.postLetterXPosition && my >= 200 && my <= 264)) {
							b = false;
						}
						else
							b = true;
					}
					else {
						if((mx >= Game.postLetterXPositionBeginning+Game.postLetterXPosition && mx <= Game.postLetterXPositionBeginning+Game.postLetterXPosition + 16 &&
								my >= 200 && my <= 264)) {
							b = false;
						}
						else 
							b = true;
					}
					if(b) {
						if((mx >= 40 && mx <= 88 && my >= 20 && my <= 36)) {
							b = false;
						}
						else if(mx >= Game.WIDTH / 2 + 35 && mx <= Game.WIDTH / 2 + 315 && my >= 20 && my <= 84) {
							if((Game.isPixelTransparentinBufferedImage(Game.setScoreTitleBigger,mx-(Game.WIDTH / 2 + 35),my-20)))
								b = true;
							else
								b = false;
						}
					}
					if(!Game.playerName.isEmpty() && mx >= Game.postLetterXPositionBeginning && mx <= Game.postLetterXPositionBeginning + Game.playerNameImage.getWidth() &&
							my >= 200 && my <= 200 + Game.playerNameImage.getHeight()) {
							if(Game.playerNameImage.getWidth()+Game.postLetterXPositionBeginning-mx >= 1)
							if(Game.isPixelTransparentinBufferedImage(Game.playerNameImage,mx-Game.postLetterXPositionBeginning,my-200)) {
								b = true;
							}
							else
								b = false;
						}
					
				if(b) {
					Game.starExplode = true;
					Game.mx = mx;
					Game.my = my;
				}
				//System.out.println("Game.playerNameImage"+(Game.playerNameImage.getWidth()+200)+"mx="+mx);
						
			}//STAR EXPLOSION
			// Play Button
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.MENU ||
					mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.GAMEOVER) {
				if (my >= 200 && my <= 264) {
					// Pressed Play Button
					if(Game.State == STATE.GAMEOVER && Game.playClicked)
						Game.State = Game.STATE.RESET;
					else if(Game.playClicked) {
						if(Game.skipAnimations)
							Game.State = Game.STATE.GAME;
						else
							Game.State = Game.STATE.TRANSITION_ENTRANCE;
					}
					Game.smb3CoinSoundLoop.play();
				}
			}
	
			// Shop Button
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.MENU) {
				if (my >= 300 && my <= 364 && Game.shopClicked) {
					// Pressed SHOP Button
					Game.selectorButtonPosition = -1;
					Game.State = Game.STATE.SHOP;
					if(Game.smb3OpenSoundLoop.clipIsActive())
						Game.smb3OpenSoundLoop.stop();
					Game.smb3OpenSoundLoop.play();
				}
			}
	
			// Exit Button
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.MENU ||
					mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.GAMEOVER||
					mx >= Game.WIDTH / 2 + 121 && mx <= Game.WIDTH / 2 + 249 && Game.State == Game.STATE.GAME && Game.getUserHasPaused()) {
				if(Game.getUserHasPaused() && my >= 300 && my <= 364 && Game.exitClicked) {
					// Pressed Exit Button While Paused
					System.exit(1);
				}
				if (Game.State != Game.STATE.GAME && my >= 400 && my <= 464 && Game.exitClicked) {
					// Pressed Exit Button
					System.exit(1);
				}
			}
			
			//Resume Button
			if (mx >= Game.WIDTH / 2 + 80 && mx <= Game.WIDTH / 2 + 288 && Game.State == Game.STATE.GAME && Game.getUserHasPaused()) {
				if(my >= 100 && my <= 164 && Game.resumeClicked) {
					if(Game.isPaused() == true && Game.getSoundFXisPlaying() == false){
						if(Game.getPauseSoundFXTimer() < System.currentTimeMillis()){
						/*if(p.getMarioInvincible() == true)
							this.marioStarSoundLoop.loop();
						else
							this.gameSoundLoops.get(soundRandomizer).loop();
						paused = false;*/
						Game.getPauseSoundFXSoundLoop().play();
						Game.setPauseSoundFXTimer(System.currentTimeMillis() + 685);
						Game.getPauseSoundFXSoundLoop().setSoundLoopBoolean(true);
						}
						Game.setUserHasPaused(false);
					}
				}
			}
			
			//Home Button
			if (mx >= Game.WIDTH / 2 + 114 && mx <= Game.WIDTH / 2 + 254 && Game.State == Game.STATE.GAME && Game.getUserHasPaused() ||
					(mx >= Game.WIDTH / 2 + 114 && mx <= Game.WIDTH / 2 + 254 && Game.State == Game.STATE.GAMEOVER)) {
				if(my >= 200 && my <= 264 && Game.State == Game.STATE.GAME && Game.getUserHasPaused() && Game.homeClicked ||
					my >= 300 && my <= 364 && Game.State == Game.STATE.GAMEOVER && Game.homeClicked) {  //might want to make two seperate
					Game.selectorButtonPosition = -1;
					Game.setDontStartOver(true);														//buttons here to change GameOver->Menu
					Game.State = Game.STATE.RESET;														//to make it feel more fluid
					if(Game.smb3OpenSoundLoop.clipIsActive())
						Game.smb3OpenSoundLoop.stop();
					Game.smb3OpenSoundLoop.play();
				}
			}	
			
			//Back Button
			if(mx >= 40 && mx <= 88 && (Game.State == Game.STATE.SET_SCORE || Game.State == Game.STATE.LEADERBOARD || Game.State == Game.STATE.SHOP
					|| Game.State == Game.STATE.HELP || Game.State == Game.STATE.SETTINGS) && buttonTimer < System.currentTimeMillis()) {
				if(my >= 20 && my <= 36 && Game.backClicked) {
					// Pressed Back Button
					buttonTimer = System.currentTimeMillis() + 200;
					Game.selectorButtonPosition = 0;
					if(!Game.backToGameOver)
						Game.State = Game.STATE.MENU;
					else
						Game.State = Game.STATE.GAMEOVER;
					if(Game.smb3KickSoundLoop.clipIsActive())
						Game.smb3KickSoundLoop.stop();
					Game.smb3KickSoundLoop.play();
				}
			}
			
			//Help Button
			if(mx >= 54 && mx <= 86 && (Game.State == Game.STATE.MENU) && buttonTimer < System.currentTimeMillis()) {
				if(my >= 20 && my <= 36 && Game.helpClicked) {
					// Pressed Help Button
					buttonTimer = System.currentTimeMillis() + 200;
					Game.State = Game.STATE.HELP;
					if(Game.smb3OpenSoundLoop.clipIsActive())
						Game.smb3OpenSoundLoop.stop();
					Game.smb3OpenSoundLoop.play();
				}
			}
			
			//Settings Button
			if(mx >= 312 && mx <= 376 && (Game.State == Game.STATE.MENU) && buttonTimer < System.currentTimeMillis()) {
				if(my >= 20 && my <= 36 && Game.settingsClicked) {
					// Pressed Settings Button
					Game.selectorButtonPosition = -1;
					buttonTimer = System.currentTimeMillis() + 200;
					Game.State = Game.STATE.SETTINGS;
					if(Game.smb3OpenSoundLoop.clipIsActive())
						Game.smb3OpenSoundLoop.stop();
					Game.smb3OpenSoundLoop.play();
				}
			}
			
			//Submit Score Button
			if(mx >= 40 && mx <= 110 && Game.State == Game.STATE.GAMEOVER && buttonTimer < System.currentTimeMillis()) {
				if(my >= 20 && my <= 36 && Game.setScoreClicked) {
					// Pressed Submit Score
					buttonTimer = System.currentTimeMillis() + 200;
					Game.State = Game.STATE.SET_SCORE;
					if(Game.smb3OpenSoundLoop.clipIsActive())
						Game.smb3OpenSoundLoop.stop();
					Game.smb3OpenSoundLoop.play();
				}
			}
			
			//Leaderboard Button
			if(mx >=  Game.WIDTH / 2 + 380 && mx <= Game.WIDTH / 2 + 470 && (Game.State == Game.STATE.GAMEOVER
					|| Game.State == Game.STATE.MENU) && buttonTimer < System.currentTimeMillis()) {
				if(my >= 20 && my <= 36 && Game.leaderboardClicked) {
					// Pressed Leaderboard
					Game.selectorButtonPosition = -1;
					buttonTimer = System.currentTimeMillis() + 200;
					Game.State = Game.STATE.LEADERBOARD;
					if(Game.smb3OpenSoundLoop.clipIsActive())
						Game.smb3OpenSoundLoop.stop();
					Game.smb3OpenSoundLoop.play();
				}
			}
			
			//Skip Button
			if(mx >= Game.WIDTH-73 && mx <= Game.WIDTH+93 && (Game.State == Game.STATE.TRANSITION_ENTRANCE || 
					Game.State == Game.STATE.TRANSITION_ITEM || Game.State == Game.STATE.TRANSITION_WIN) && buttonTimer < System.currentTimeMillis()) {
				if(my >= Game.HEIGHT-32 && my <= Game.HEIGHT+32 && Game.askToSkipSequence && Game.skipClicked) {
					// Pressed Skip
					Game.skipSequence = true;
					buttonTimer = System.currentTimeMillis() + 200;
					if(Game.smb3OpenSoundLoop.clipIsActive())
						Game.smb3OpenSoundLoop.stop();
					Game.smb3OpenSoundLoop.play();
				}
			}
			if(Game.State == Game.STATE.SHOP) {
				//ArrowL Buttons
				if(mx >=  Game.WIDTH - 48 && mx <= Game.WIDTH - 32 && Game.State == Game.STATE.SHOP && buttonTimer < System.currentTimeMillis()) {
					if(my >= 120 && my <= 152 && Game.arrowL1Clicked) {
						//ArrowL1 Functionality
						buttonTimer = System.currentTimeMillis() + 200;
						if(Game.characterSkinPosition > 0)
							Game.characterSkinPosition--;
						else
							Game.characterSkinPosition = 3;//Set to Max Skins
						Game.skinNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.characterSkinPosition+1)), 10, 10);
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
					}else if(my >= 220 && my <= 252 && Game.arrowL2Clicked) {
						//ArrowL2 Functionality
						buttonTimer = System.currentTimeMillis() + 200;
						if(Game.trackPosition > 0)
							Game.trackPosition--;
						else
							Game.trackPosition = 1;//Set to Max Tracks
						Game.trackNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.trackPosition+1)), 10, 10);
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
					}else if(my >= 320 && my <= 352 && Game.arrowL3Clicked) {
						//ArrowL3 Functionality
						buttonTimer = System.currentTimeMillis() + 200;
						if(Game.fireballPosition > 0)
							Game.fireballPosition--;
						else
							Game.fireballPosition = 1;//Set to Max Fireballs
						Game.fireballNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.fireballPosition+1)), 10, 10);
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
					}else if(my >= 420 && my <= 452 && Game.arrowL4Clicked) {
						//ArrowL4 Functionality
						buttonTimer = System.currentTimeMillis() + 200;
						if(Game.itemPosition > 0)
							Game.itemPosition--;
						else
							Game.itemPosition = 2;//Set to Max Items
						Game.itemNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.itemPosition+1)), 10, 10);
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
					}
				}
				
				//ArrowR Buttons
				if(mx >=  Game.WIDTH + 48 && mx <= Game.WIDTH + 64 && Game.State == Game.STATE.SHOP && buttonTimer < System.currentTimeMillis()) {
					if(my >= 120 && my <= 152 && Game.arrowR1Clicked) {
						//ArrowR1 Functionality
						buttonTimer = System.currentTimeMillis() + 200;
						if(Game.characterSkinPosition == 3)//Max Skins
							Game.characterSkinPosition = 0;
						else {
							Game.characterSkinPosition++;
						}
						Game.skinNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.characterSkinPosition+1)), 10, 10);
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
					}else if(my >= 220 && my <= 252 && Game.arrowR2Clicked) {
						//ArrowR2 Functionality
						buttonTimer = System.currentTimeMillis() + 200;
						if(Game.trackPosition == 1)//Max Tracks
							Game.trackPosition = 0;
						else 
							Game.trackPosition++;
						Game.trackNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.trackPosition+1)), 10, 10);
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
					}else if(my >= 320 && my <= 352 && Game.arrowR3Clicked) {
						//ArrowR3 Functionality
						buttonTimer = System.currentTimeMillis() + 200;
						if(Game.fireballPosition == 1)//Max Fireballs
							Game.fireballPosition = 0;
						else
							Game.fireballPosition++;
						Game.fireballNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.fireballPosition+1)), 10, 10);
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
					}else if(my >= 420 && my <= 452 && Game.arrowR4Clicked) {
						//ArrowR4 Functionality
						buttonTimer = System.currentTimeMillis() + 200;
						if(Game.itemPosition == 2)//Max Items
							Game.itemPosition = 0;
						else
							Game.itemPosition++;
						Game.itemNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.itemPosition+1)), 10, 10);
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
					}
				}
				
				//Buy Buttons
				if(mx >=  Game.WIDTH - 5 && mx <= Game.WIDTH + 24 && Game.State == Game.STATE.SHOP && buttonTimer < System.currentTimeMillis()) {
					if(my >= 127 && my <= 144 && Game.buy1Clicked && Game.currentSkinLocked == true) {
						buttonTimer = System.currentTimeMillis() + 200;
						switch(Game.characterSkinPosition){
						case 1:
							if(Game.totalPoints >= 100){
								Game.skin1Unlocked = true;
								//Game.settingsSetup = false;
								Game.writeOnceToSettings = true;
								Game.writeOnceProperty = "currentlySelectedCharacterSkin";
								Game.writeOnceString = Integer.toString(Game.characterSkinPosition);
								Game.writeOnceToSettingswithPoints = true;
								Game.writeOnceUnlock = "skin1Unlocked";
								Game.characterSkinPosition = 1;
								Game.currentlySelectedCharacterSkin = 1;
								Game.currentSkinLocked = false;
								Game.skinNumber = null;
								Game.totalPoints -= 100;
								Game.starExplode = true;
								Game.mx = mx;
								Game.my = my;
								if(Game.smb31PupSoundLoop.clipIsActive())
									Game.smb31PupSoundLoop.stop();
								Game.smb31PupSoundLoop.play();
							}
							else{
								if(Game.smwErrorSoundLoop.clipIsActive())
									Game.smwErrorSoundLoop.stop();
								Game.smwErrorSoundLoop.play();
							}
							break;
						case 2:
							if(Game.totalPoints >= 1000){
								Game.skin2Unlocked = true;
								//Game.settingsSetup = false;
								Game.writeOnceToSettings = true;
								Game.writeOnceProperty = "currentlySelectedCharacterSkin";
								Game.writeOnceString = Integer.toString(Game.characterSkinPosition);
								Game.writeOnceToSettingswithPoints = true;
								Game.writeOnceUnlock = "skin2Unlocked";
								Game.characterSkinPosition = 2;
								Game.currentlySelectedCharacterSkin = 2;
								Game.currentSkinLocked = false;
								Game.skinNumber = null;
								Game.totalPoints -= 1000;
								Game.starExplode = true;
								Game.mx = mx;
								Game.my = my;
								if(Game.smb31PupSoundLoop.clipIsActive())
									Game.smb31PupSoundLoop.stop();
								Game.smb31PupSoundLoop.play();
							}
							else{
								if(Game.smwErrorSoundLoop.clipIsActive())
									Game.smwErrorSoundLoop.stop();
								Game.smwErrorSoundLoop.play();
							}
							break;
						case 3:
							if(Game.totalPoints >= 10000){
								Game.skin3Unlocked = true;
								//Game.settingsSetup = false;
								Game.writeOnceToSettings = true;
								Game.writeOnceProperty = "currentlySelectedCharacterSkin";
								Game.writeOnceString = Integer.toString(Game.characterSkinPosition);
								Game.writeOnceToSettingswithPoints = true;
								Game.writeOnceUnlock = "skin3Unlocked";
								Game.characterSkinPosition = 3;
								Game.currentlySelectedCharacterSkin = 3;
								Game.currentSkinLocked = false;
								Game.skinNumber = null;
								Game.totalPoints -= 10000;
								Game.starExplode = true;
								Game.mx = mx;
								Game.my = my;
								if(Game.smb31PupSoundLoop.clipIsActive())
									Game.smb31PupSoundLoop.stop();
								Game.smb31PupSoundLoop.play();
							}
							else{
								if(Game.smwErrorSoundLoop.clipIsActive())
									Game.smwErrorSoundLoop.stop();
								Game.smwErrorSoundLoop.play();
							}
							break;
						}
					}else if(my >= 227 && my <= 244 && Game.buy2Clicked && Game.currentTrackLocked == true) {
						buttonTimer = System.currentTimeMillis() + 200;
						if(Game.smb31PupSoundLoop.clipIsActive())
							Game.smb31PupSoundLoop.stop();
						Game.smb31PupSoundLoop.play();
					}else if(my >= 327 && my <= 344 && Game.buy3Clicked && Game.currentFireballLocked == true) {
						buttonTimer = System.currentTimeMillis() + 200;
						if(Game.smb31PupSoundLoop.clipIsActive())
							Game.smb31PupSoundLoop.stop();
						Game.smb31PupSoundLoop.play();
					}else if(my >= 427 && my <= 444 && Game.buy4Clicked && Game.currentItemLocked == true) {
						buttonTimer = System.currentTimeMillis() + 200;
						if(Game.smb31PupSoundLoop.clipIsActive())
							Game.smb31PupSoundLoop.stop();
						Game.smb31PupSoundLoop.play();
					}
				}
				
				//Set Buttons
				if(mx >=  Game.WIDTH + 108 && mx <= Game.WIDTH + 132 && Game.State == Game.STATE.SHOP && buttonTimer < System.currentTimeMillis()) {
					if(my >= 128 && my <= 144 && Game.set1Clicked) {
						buttonTimer = System.currentTimeMillis() + 200;
						if(!Game.currentSkinLocked) {
							if(Game.currentlySelectedCharacterSkin != Game.characterSkinPosition) {
								if(Game.smb3ItemSoundLoop.clipIsActive())
									Game.smb3ItemSoundLoop.stop();
								Game.smb3ItemSoundLoop.play();
							}
							Game.currentlySelectedCharacterSkin = Game.characterSkinPosition;
							Game.writeOnceToSettings = true;
							Game.writeOnceProperty = "currentlySelectedCharacterSkin";
							Game.writeOnceString = Integer.toString(Game.characterSkinPosition);
							//Game.settingsSetup = false;
						}
					}else if(my >= 228 && my <= 244 && Game.set2Clicked) {
						buttonTimer = System.currentTimeMillis() + 200;
						if(Game.smb3ItemSoundLoop.clipIsActive())
							Game.smb3ItemSoundLoop.stop();
						Game.smb3ItemSoundLoop.play();
					}else if(my >= 328 && my <= 344 && Game.set3Clicked) {
						buttonTimer = System.currentTimeMillis() + 200;
						if(Game.smb3ItemSoundLoop.clipIsActive())
							Game.smb3ItemSoundLoop.stop();
						Game.smb3ItemSoundLoop.play();
					}else if(my >= 428 && my <= 444 && Game.set4Clicked) {
						buttonTimer = System.currentTimeMillis() + 200;
						if(Game.smb3ItemSoundLoop.clipIsActive())
							Game.smb3ItemSoundLoop.stop();
						Game.smb3ItemSoundLoop.play();
					}
				}
			}
			
			if(Game.State == Game.STATE.SETTINGS) {
				if(Game.areYouSureBoolean) {
					//Yes Button in Settings Menu Game.WIDTH + 18,375,null
					if(mx >=  Game.WIDTH  + 18 && mx <= Game.WIDTH + 18 + 96 && Game.State == Game.STATE.SETTINGS && buttonTimer < System.currentTimeMillis()) {
						if(my >= 375 && my <= 439 && Game.yesClicked) {
							//Yes Button Functionality
							buttonTimer = System.currentTimeMillis() + 200;
							Game.skin1Unlocked = false;
							Game.skin2Unlocked = false;
							Game.skin3Unlocked = false;
							Game.track1Unlocked = false;
							Game.fireball1Unlocked = false;
							Game.item1Unlocked = false;
							Game.item2Unlocked = false;
							Game.currentlySelectedCharacterSkin = 0;
							Game.currentlySelectedTrack = 0;
							Game.currentlySelectedFireball = 0;
							Game.currentlySelectedItem = 0;
							Game.volumeSliderPosition = 3;
							Game.sfxMusicSliderPosition = 3;
							Game.characterSkinPosition = 0;
							Game.trackPosition = 0;
							Game.fireballPosition = 0;
							Game.itemPosition = 0;
							Game.skinNumber = null;
							Game.totalPoints = 0;
							Game.skipAnimations = false;
							try {
								LeaderboardController.resetScore();
							} catch (IOException e1) {
								
							}
							VolumeSlider.volumeSliderChangingVolume = true;
							VolumeSlider.sfxMusicSliderChangingVolume = true;
							Game.settingsSetup = false;
							Game.areYouSureBoolean = false;
							if(Game.smb3TailSoundLoop.clipIsActive())
								Game.smb3TailSoundLoop.stop();
							Game.smb3TailSoundLoop.play();
						}
					}
					//No Button in Settings Menu Game.WIDTH + 18,375,null
					if(mx >=  Game.WIDTH - 64 - 18 && mx <= Game.WIDTH - 18 && Game.State == Game.STATE.SETTINGS && buttonTimer < System.currentTimeMillis()) {
						if(my >= 375 && my <= 439 && Game.noClicked) {
							//No Button Functionality
							buttonTimer = System.currentTimeMillis() + 200;
							Game.selectorButtonPosition = 3;
							Game.areYouSureBoolean = false;
							if(Game.smb3KickSoundLoop.clipIsActive())
								Game.smb3KickSoundLoop.stop();
							Game.smb3KickSoundLoop.play();
						}
					}
				}
				else {
					//ArrowL Buttons in Settings Menu
					if(mx >=  Game.WIDTH  - 125 && mx <= Game.WIDTH - 109 && Game.State == Game.STATE.SETTINGS && buttonTimer < System.currentTimeMillis()) {
						if(my >= 121 && my <= 153 && Game.arrowL1Clicked) {
							//ArrowL1 Functionality
							buttonTimer = System.currentTimeMillis() + 200;
							if(Game.volumeSliderPosition > 1) {
								Game.volumeSliderPosition--;
								VolumeSlider.volumeSliderChangingVolume = true;
							}
							if(Game.smb3BumpSoundLoop.clipIsActive())
								Game.smb3BumpSoundLoop.stop();
							Game.smb3BumpSoundLoop.play();
						}
					}
					
					//ArrowR Buttons in Settings Menu
					if(mx >=  Game.WIDTH + 109 && mx <= Game.WIDTH + 125 && Game.State == Game.STATE.SETTINGS && buttonTimer < System.currentTimeMillis()) {
						if(my >= 121 && my <= 153 && Game.arrowR1Clicked) {
							//ArrowR1 Functionality
							buttonTimer = System.currentTimeMillis() + 200;
							if(Game.volumeSliderPosition < 5) {
								Game.volumeSliderPosition++;
								VolumeSlider.volumeSliderChangingVolume = true;
							}
							if(Game.smb3BumpSoundLoop.clipIsActive())
								Game.smb3BumpSoundLoop.stop();
							Game.smb3BumpSoundLoop.play();
						}
					}
					
					//ArrowL2 Buttons in Settings Menu
					if(mx >=  Game.WIDTH  - 125 && mx <= Game.WIDTH - 109 && Game.State == Game.STATE.SETTINGS && buttonTimer < System.currentTimeMillis()) {
						if(my >= 221 && my <= 253 && Game.arrowL2Clicked) {
							//ArrowL1 Functionality
							buttonTimer = System.currentTimeMillis() + 200;
							if(Game.sfxMusicSliderPosition > 1) {
								Game.sfxMusicSliderPosition--;
								VolumeSlider.sfxMusicSliderChangingVolume = true;
							}
							if(Game.smb3BumpSoundLoop.clipIsActive())
								Game.smb3BumpSoundLoop.stop();
							Game.smb3BumpSoundLoop.play();
						}
					}
					
					//ArrowR2 Buttons in Settings Menu
					if(mx >=  Game.WIDTH + 109 && mx <= Game.WIDTH + 125 && Game.State == Game.STATE.SETTINGS && buttonTimer < System.currentTimeMillis()) {
						if(my >= 221 && my <= 253 && Game.arrowR2Clicked) {
							//ArrowR1 Functionality
							buttonTimer = System.currentTimeMillis() + 200;
							if(Game.sfxMusicSliderPosition < 5) {
								Game.sfxMusicSliderPosition++;
								VolumeSlider.sfxMusicSliderChangingVolume = true;
							}
							if(Game.smb3BumpSoundLoop.clipIsActive())
								Game.smb3BumpSoundLoop.stop();
							Game.smb3BumpSoundLoop.play();
						}
					}
					
					//Checkmark Button in Settings Menu
					if(mx >=  Game.WIDTH - 16 && mx <= Game.WIDTH + 16 && Game.State == Game.STATE.SETTINGS && buttonTimer < System.currentTimeMillis()) {
						if(my >= 320 && my <= 352 && Game.checkMarkClicked) {
							//Checkmark Functionality
							buttonTimer = System.currentTimeMillis() + 200;
							if(Game.skipAnimations) 
								Game.skipAnimations = false;
							else
								Game.skipAnimations = true;
							Game.settingsSetup = false;
							if(Game.smb3CheckmarkSoundLoop.clipIsActive())
								Game.smb3CheckmarkSoundLoop.stop();
							if(Game.smb3Checkmark2SoundLoop.clipIsActive())
								Game.smb3Checkmark2SoundLoop.stop();
							Game.smb3Checkmark2SoundLoop.play();
						}
					}
					
					//Reset Stats Button in Settings Menu
					if(mx >=  Game.WIDTH - 89 && mx <= Game.WIDTH + 89 && Game.State == Game.STATE.SETTINGS && buttonTimer < System.currentTimeMillis()) {
						if(my >= 420 && my <= 452 && Game.resetStatsClicked) {
							//Checkmark Functionality
							buttonTimer = System.currentTimeMillis() + 200;
							if(Game.areYouSureBoolean) 
								Game.areYouSureBoolean = false;
							else
								Game.areYouSureBoolean = true;
							if(Game.hudSFX.get(4).clipIsActive())
								Game.hudSFX.get(4).stop();
							Game.hudSFX.get(4).play();
						}
					}
				}
			}
		}
		Game.mouseIsClickedDown = false;
		Game.mouseIsOffClickedObjectAndHeldDown = false;
		Game.backOnPlay = false;
		Game.playClicked = false;
		Game.backOnShop = false;
		Game.shopClicked = false;
		Game.backOnExit = false;
		Game.exitClicked = false;
		Game.backOnResume = false;
		Game.resumeClicked = false;
		Game.backOnHome = false;
		Game.homeClicked = false;
		Game.backOnSetScore = false;
		Game.setScoreClicked = false;
		Game.backOnLeaderboard = false;
		Game.leaderboardClicked = false;
		Game.backOnBack = false;
		Game.backClicked = false;
		Game.backOnHelp = false;
		Game.helpClicked = false;
		Game.backOnSettings = false;
		Game.settingsClicked = false;
		Game.backOnArrowL1 = false;
		Game.arrowL1Clicked = false;
		Game.backOnArrowR1 = false;
		Game.arrowR1Clicked = false;
		Game.backOnBuy1 = false;
		Game.buy1Clicked = false;
		Game.backOnSet1 = false;
		Game.set1Clicked = false;
		Game.backOnArrowL2 = false;
		Game.arrowL2Clicked = false;
		Game.backOnArrowR2 = false;
		Game.arrowR2Clicked = false;
		Game.backOnBuy2 = false;
		Game.buy2Clicked = false;
		Game.backOnSet2 = false;
		Game.set2Clicked = false;
		Game.backOnArrowL3 = false;
		Game.arrowL3Clicked = false;
		Game.backOnArrowR3 = false;
		Game.arrowR3Clicked = false;
		Game.backOnBuy3 = false;
		Game.buy3Clicked = false;
		Game.backOnSet3 = false;
		Game.set3Clicked = false;
		Game.backOnArrowL4 = false;
		Game.arrowL4Clicked = false;
		Game.backOnArrowR4 = false;
		Game.arrowR4Clicked = false;
		Game.backOnBuy4 = false;
		Game.buy4Clicked = false;
		Game.backOnSet4 = false;
		Game.set4Clicked = false;
		Game.backOnCheckMark = false;
		Game.checkMarkClicked = false;
		Game.backOnResetStats = false;
		Game.resetStatsClicked = false;
		Game.backOnYes = false;
		Game.yesClicked = false;
		Game.backOnNo = false;
		Game.noClicked = false;
		Game.backOnSkip = false;
		Game.skipClicked = false;
	}
}
