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
			if(Game.State == Game.STATE.SHOP) {
				//ArrowL Buttons
				if(mx >=  Game.WIDTH - 48 && mx <= Game.WIDTH - 32 && Game.State == Game.STATE.SHOP && buttonTimer < System.currentTimeMillis()) {
					if(my >= 120 && my <= 152 && Game.arrowL1Clicked) {
						//ArrowL1 Functionality
						buttonTimer = System.currentTimeMillis() + 200;
						if(Game.characterSkinPosition > 0)
							Game.characterSkinPosition--;
						else
							Game.characterSkinPosition = 1;//Set to Max Skins
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
						if(Game.characterSkinPosition == 1)//Max Skins
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
						Game.skin1Unlocked = true;
						Game.settingsSetup = false;
						Game.characterSkinPosition = 1;
						Game.currentlySelectedCharacterSkin = 1;
						Game.currentSkinLocked = false;
						Game.skinNumber = null;
						if(Game.smb31PupSoundLoop.clipIsActive())
							Game.smb31PupSoundLoop.stop();
						Game.smb31PupSoundLoop.play();
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
							Game.settingsSetup = false;
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
	}
}
