package com.game.src.main;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;

import com.game.src.main.Game.STATE;
import com.github.strikerx3.jxinput.XInputDevice;
import com.github.strikerx3.jxinput.natives.XInputConstants;

import net.java.games.input.ControllerEnvironment;
import net.java.games.input.DirectAndRawInputEnvironmentPlugin;
import net.java.games.input.SwitchedValues;
import net.java.games.input.example.ReadAllEvents;

public class MouseInput implements MouseListener {

	public void reset() {
		Game.mouseIsClickedDown = false;
		Game.mouseIsOffClickedObjectAndHeldDown = false;
		Game.escapePressedNegateAction = false;
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
		Game.backOnTrackPlayButton1 = false;
		Game.trackPlayButton1Clicked = false;
		Game.backOnTrackPauseButton1 = false;
		Game.trackPauseButton1Clicked = false;
		Game.backOnArrowL2 = false;
		Game.arrowL2Clicked = false;
		Game.backOnArrowR2 = false;
		Game.arrowR2Clicked = false;
		Game.backOnBuy2 = false;
		Game.buy2Clicked = false;
		Game.backOnSet2 = false;
		Game.set2Clicked = false;
		Game.backOnTrackPlayButton2 = false;
		Game.trackPlayButton2Clicked = false;
		Game.backOnTrackPauseButton2 = false;
		Game.trackPauseButton2Clicked = false;
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
		Game.backOnReset = false;
		Game.resetClicked = false;
		Game.backOnYes = false;
		Game.yesClicked = false;
		Game.backOnNo = false;
		Game.noClicked = false;
		Game.backOnSkip = false;
		Game.skipClicked = false;
		Game.backOnSubmit = false;
		Game.submitClicked = false;
		Game.backOnGamepadImage = false;
		Game.gamepadImageClicked = false;
		Game.backOnNoteImage = false;
		Game.noteImageClicked = false;
		Game.backOnLocal = false;
		Game.localClicked = false;
		Game.backOnGlobal = false;
		Game.globalClicked = false;
		Game.backOnUpload = false;
		Game.uploadClicked = false;
		Game.backOnCredits = false;
		Game.creditsClicked = false;
		Game.resetLeaderboardClicked = false;
		Game.backOnResetLeaderboard = false;
		Game.rescanClicked = false;
		Game.backOnRescan = false;
		for(int i = 0; i <= ControlsController.backOnGamepadButtonHolder.length-1; i++) {
			ControlsController.backOnGamepadButtonHolder[i] = false;
			ControlsController.gamepadButtonHolderClicked[i] = false;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//int mx = e.getX();
		//int my = e.getY();
		if(Game.State == STATE.TRANSITION_ENTRANCE || Game.State == STATE.TRANSITION_ITEM || Game.State == STATE.TRANSITION_WIN || (Game.State == STATE.CREDITS && !Game.backToGameOver)) {
			if (e.getClickCount() == 5) {
				if(Game.askToSkipSequence) {
					//if(!(mx >= Game.WIDTH-73 && mx <= Game.WIDTH+93 && my >= Game.WIDTH-32 && my <= Game.WIDTH))
						Game.askToSkipSequence = false;
				}
				else
					Game.askToSkipSequence = true;
			}
		}

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
		if(Game.connectingToServer || Game.allUnlockedScreen)
			return;
		if(ControlsController.buttonChangeTimer < System.currentTimeMillis()) {
			if(Game.State == Game.STATE.CONTROLS && Game.keysAreInUse) {
				for(int i =0; i<= ControlsController.gamepadButtonHolderHighlighted.length-1; i++) {
					ControlsController.gamepadButtonHolderHighlighted[i] = false;
				}
			}
			Game.mouseIsClickedDown = true;
			Game.keysAreInUse = false;
		}
		int mx = e.getX();
		int my = e.getY();
		if(Game.escapePressedNegateAction)
			return;
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
		if(mx >= 40 && mx <= 88 && !Game.areYouSureBoolean &&
				(Game.State == Game.STATE.SET_SCORE || Game.State == Game.STATE.LEADERBOARD || Game.State == Game.STATE.SHOP
				|| Game.State == Game.STATE.HELP || Game.State == Game.STATE.SETTINGS || Game.State == Game.STATE.CONTROLS || Game.State == Game.STATE.TRACKLIST)) {
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
		if(mx >= Game.WIDTH-73 && mx <= Game.WIDTH+93 && (Game.State == Game.STATE.TRANSITION_ENTRANCE || Game.State == Game.STATE.TRANSITION_ITEM || Game.State == Game.STATE.CREDITS)) {
			if(my >= Game.HEIGHT-32 && my <= Game.HEIGHT+32 && Game.askToSkipSequence) 
				Game.skipClicked = true;
			else if(Game.skipClicked)
				Game.skipClicked = false;
		}
		else if(Game.skipClicked)
			Game.skipClicked = false;
		//Submit button Button
		if(mx >= Game.WIDTH-51 && mx <= Game.WIDTH+51 && (Game.State == Game.STATE.SET_SCORE)) {
			if(my >= 300 && my <= 332) 
				Game.submitClicked = true;
			else if(Game.submitClicked)
				Game.submitClicked = false;
		}
		else if(Game.submitClicked)
			Game.submitClicked = false;
		//Checkmark Button in Set Score Menu
		if(mx >=  304 && mx <= 336 && Game.State == Game.STATE.SET_SCORE) {
			if(my >= 410 && my <= 442) {
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
		
		if(Game.State == Game.STATE.SHOP) {
			if(Game.allUnlockedScreen) {
				return;
			}
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
				
				//Gamepad Image Button in Settings Menu
				if(mx >=  Game.WIDTH + 178 && mx <= Game.WIDTH + 217 && Game.State == Game.STATE.SETTINGS) {
					if(my >= 42 && my <= 62) 
						Game.gamepadImageClicked = true;
					else if(Game.gamepadImageClicked)
						Game.gamepadImageClicked = false;
				}
				else if(Game.gamepadImageClicked)
					Game.gamepadImageClicked = false;
				
				//Note Image Button in Settings Menu
				if(mx >=  Game.WIDTH + 268 && mx <= Game.WIDTH + 286 && Game.State == Game.STATE.SETTINGS) {
					if(my >= 41 && my <= 63) 
						Game.noteImageClicked = true;
					else if(Game.noteImageClicked)
						Game.noteImageClicked = false;
				}
				else if(Game.noteImageClicked)
					Game.noteImageClicked = false;
				
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
		else if(Game.State == Game.STATE.CONTROLS && ControlsController.buttonChangeTimer < System.currentTimeMillis()) {
			//Rescan Button
			if (mx >=  489 && mx <= 538) {
				if (my >= 65 && my <= 74) {
					Game.rescanClicked = true;
				}
				else if(Game.rescanClicked)
					Game.rescanClicked = false;
			}
			else if(Game.rescanClicked)
				Game.rescanClicked = false;
			
			//Up WASD Button
			if (mx >=  Game.WIDTH - 51 && mx <= Game.WIDTH - 28) {
				if (my >= 125 && my <= 147) {
					ControlsController.gamepadButtonHolderClicked[0] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[0])
					ControlsController.gamepadButtonHolderClicked[0] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[0])
				ControlsController.gamepadButtonHolderClicked[0] = false;
			
			//Down WASD Button
			if (mx >=  Game.WIDTH - 51 && mx <= Game.WIDTH - 28) {
				if (my >= 170 && my <= 192) {
					ControlsController.gamepadButtonHolderClicked[1] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[1])
					ControlsController.gamepadButtonHolderClicked[1] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[1])
				ControlsController.gamepadButtonHolderClicked[1] = false;

			//Left WASD Button
			if (mx >=  Game.WIDTH - 51 && mx <= Game.WIDTH - 28) {
				if (my >= 215 && my <= 237) {
					ControlsController.gamepadButtonHolderClicked[2] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[2])
					ControlsController.gamepadButtonHolderClicked[2] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[2])
				ControlsController.gamepadButtonHolderClicked[2] = false;
			
			//Right WASD Button
			if (mx >=  Game.WIDTH - 51 && mx <= Game.WIDTH - 28) {
				if (my >= 260 && my <= 282) {
					ControlsController.gamepadButtonHolderClicked[3] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[3])
					ControlsController.gamepadButtonHolderClicked[3] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[3])
				ControlsController.gamepadButtonHolderClicked[3] = false;

			//Shoot WASD Button
			if (mx >=  Game.WIDTH - 51 && mx <= Game.WIDTH - 28) {
				if (my >= 305 && my <= 327) {
					ControlsController.gamepadButtonHolderClicked[4] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[4])
					ControlsController.gamepadButtonHolderClicked[4] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[4])
				ControlsController.gamepadButtonHolderClicked[4] = false;
			
			//Item WASD Button
			if (mx >=  Game.WIDTH - 51 && mx <= Game.WIDTH - 28) {
				if (my >= 350 && my <= 372) {
					ControlsController.gamepadButtonHolderClicked[5] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[5])
					ControlsController.gamepadButtonHolderClicked[5] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[5])
				ControlsController.gamepadButtonHolderClicked[5] = false;

			//Pause WASD Button
			if (mx >=  Game.WIDTH - 51 && mx <= Game.WIDTH - 28) {
				if (my >= 395 && my <= 417) {
					ControlsController.gamepadButtonHolderClicked[6] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[6])
					ControlsController.gamepadButtonHolderClicked[6] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[6])
				ControlsController.gamepadButtonHolderClicked[6] = false;
			
			//Cancel WASD Button
			if (mx >=  Game.WIDTH - 51 && mx <= Game.WIDTH - 28) {
				if (my >= 440 && my <= 462) {
					ControlsController.gamepadButtonHolderClicked[7] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[7])
					ControlsController.gamepadButtonHolderClicked[7] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[7])
				ControlsController.gamepadButtonHolderClicked[7] = false;
			
			//Up XDevice Button
			if (mx >= 385 && mx <= 408) {
				if (my >= 125 && my <= 147) {
					ControlsController.gamepadButtonHolderClicked[8] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[8])
					ControlsController.gamepadButtonHolderClicked[8] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[8])
				ControlsController.gamepadButtonHolderClicked[8] = false;
			
			//Down XDevice Button
			if (mx >= 385 && mx <= 408) {
				if (my >= 170 && my <= 192) {
					ControlsController.gamepadButtonHolderClicked[9] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[9])
					ControlsController.gamepadButtonHolderClicked[9] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[9])
				ControlsController.gamepadButtonHolderClicked[9] = false;

			//Left XDevice Button
			if (mx >= 385 && mx <= 408) {
				if (my >= 215 && my <= 237) {
					ControlsController.gamepadButtonHolderClicked[10] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[10])
					ControlsController.gamepadButtonHolderClicked[10] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[10])
				ControlsController.gamepadButtonHolderClicked[10] = false;
			
			//Right XDevice Button
			if (mx >= 385 && mx <= 408) {
				if (my >= 260 && my <= 282) {
					ControlsController.gamepadButtonHolderClicked[11] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[11])
					ControlsController.gamepadButtonHolderClicked[11] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[11])
				ControlsController.gamepadButtonHolderClicked[11] = false;

			//Shoot XDevice Button
			if (mx >= 385 && mx <= 408) {
				if (my >= 305 && my <= 327) {
					ControlsController.gamepadButtonHolderClicked[12] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[12])
					ControlsController.gamepadButtonHolderClicked[12] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[12])
				ControlsController.gamepadButtonHolderClicked[12] = false;
			
			//Item XDevice Button
			if (mx >= 385 && mx <= 408) {
				if (my >= 350 && my <= 372) {
					ControlsController.gamepadButtonHolderClicked[13] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[13])
					ControlsController.gamepadButtonHolderClicked[13] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[13])
				ControlsController.gamepadButtonHolderClicked[13] = false;

			//Pause XDevice Button
			if (mx >= 385 && mx <= 408) {
				if (my >= 395 && my <= 417) {
					ControlsController.gamepadButtonHolderClicked[14] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[14])
					ControlsController.gamepadButtonHolderClicked[14] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[14])
				ControlsController.gamepadButtonHolderClicked[14] = false;
			
			//Cancel XDevice Button
			if (mx >= 385 && mx <= 408) {
				if (my >= 440 && my <= 462) {
					ControlsController.gamepadButtonHolderClicked[15] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[15])
					ControlsController.gamepadButtonHolderClicked[15] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[15])
				ControlsController.gamepadButtonHolderClicked[15] = false;
			

			//Up Direct Input Button
			if (mx >= 501 && mx <= 524) {
				if (my >= 125 && my <= 147) {
					ControlsController.gamepadButtonHolderClicked[16] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[16])
					ControlsController.gamepadButtonHolderClicked[16] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[16])
				ControlsController.gamepadButtonHolderClicked[16] = false;
			
			//Down Direct Input Button
			if (mx >= 501 && mx <= 524) {
				if (my >= 170 && my <= 192) {
					ControlsController.gamepadButtonHolderClicked[17] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[17])
					ControlsController.gamepadButtonHolderClicked[17] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[17])
				ControlsController.gamepadButtonHolderClicked[17] = false;

			//Left Direct Input Button
			if (mx >= 501 && mx <= 524) {
				if (my >= 215 && my <= 237) {
					ControlsController.gamepadButtonHolderClicked[18] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[18])
					ControlsController.gamepadButtonHolderClicked[18] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[18])
				ControlsController.gamepadButtonHolderClicked[18] = false;
			
			//Right Direct Input Button
			if (mx >= 501 && mx <= 524) {
				if (my >= 260 && my <= 282) {
					ControlsController.gamepadButtonHolderClicked[19] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[19])
					ControlsController.gamepadButtonHolderClicked[19] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[19])
				ControlsController.gamepadButtonHolderClicked[19] = false;

			//Shoot Direct Input Button
			if (mx >= 501 && mx <= 524) {
				if (my >= 305 && my <= 327) {
					ControlsController.gamepadButtonHolderClicked[20] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[20])
					ControlsController.gamepadButtonHolderClicked[20] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[20])
				ControlsController.gamepadButtonHolderClicked[20] = false;
			
			//Item Direct Input Button
			if (mx >= 501 && mx <= 524) {
				if (my >= 350 && my <= 372) {
					ControlsController.gamepadButtonHolderClicked[21] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[21])
					ControlsController.gamepadButtonHolderClicked[21] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[21])
				ControlsController.gamepadButtonHolderClicked[21] = false;

			//Pause Direct Input Button
			if (mx >= 501 && mx <= 524) {
				if (my >= 395 && my <= 417) {
					ControlsController.gamepadButtonHolderClicked[22] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[22])
					ControlsController.gamepadButtonHolderClicked[22] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[22])
				ControlsController.gamepadButtonHolderClicked[22] = false;
			
			//Cancel Direct Input Button
			if (mx >= 501 && mx <= 524) {
				if (my >= 440 && my <= 462) {
					ControlsController.gamepadButtonHolderClicked[23] = true;
				}
				else if(ControlsController.gamepadButtonHolderClicked[23])
					ControlsController.gamepadButtonHolderClicked[23] = false;
			}
			else if(ControlsController.gamepadButtonHolderClicked[23])
				ControlsController.gamepadButtonHolderClicked[23] = false;
			
			//Reset Button
			if (mx >= 564 && mx <= 605) {
				if (my >= 443 && my <= 459) {
					Game.resetClicked = true;
				}
				else if(Game.resetClicked)
					Game.resetClicked = false;
			}
			else if(Game.resetClicked)
				Game.resetClicked = false;
		}else if(Game.State == STATE.TRACKLIST) {
			//Arrow L1 Button
			if (mx >= Game.WIDTH  - 185 && mx <= Game.WIDTH  - 169) {
				if (my >= 150 && my <= 182) {
					Game.arrowL1Clicked = true;
				}
				else if(Game.arrowL1Clicked)
					Game.arrowL1Clicked = false;
			}
			else if(Game.arrowL1Clicked)
				Game.arrowL1Clicked = false;
			
			//Arrow R1 Button
			if (mx >= Game.WIDTH  - 129 && mx <= Game.WIDTH  - 113) {
				if (my >= 150 && my <= 182) {
					Game.arrowR1Clicked = true;
				}
				else if(Game.arrowR1Clicked)
					Game.arrowR1Clicked = false;
			}
			else if(Game.arrowR1Clicked)
				Game.arrowR1Clicked = false;
			
			//Arrow L2 Button
			if (mx >= Game.WIDTH  - 185 && mx <= Game.WIDTH  - 169) {
				if (my >= 350 && my <= 382) {
					Game.arrowL2Clicked = true;
				}
				else if(Game.arrowL2Clicked)
					Game.arrowL2Clicked = false;
			}
			else if(Game.arrowL2Clicked)
				Game.arrowL2Clicked = false;
			
			//Arrow R2 Button
			if (mx >= Game.WIDTH  - 129 && mx <= Game.WIDTH  - 113) {
				if (my >= 350 && my <= 382) {
					Game.arrowR2Clicked = true;
				}
				else if(Game.arrowR2Clicked)
					Game.arrowR2Clicked = false;
			}
			else if(Game.arrowR2Clicked)
				Game.arrowR2Clicked = false;
			
			//Play1 Button
			if (mx >= 250 && mx <= 266) {
				if (my >= 158 && my <= 174) {
					Game.trackPlayButton1Clicked = true;
				}
				else if(Game.trackPlayButton1Clicked)
					Game.trackPlayButton1Clicked = false;
			}
			else if(Game.trackPlayButton1Clicked)
				Game.trackPlayButton1Clicked = false;
			
			//Play2 Button
			if (mx >= 250 && mx <= 266) {
				if (my >= 358 && my <= 374) {
					Game.trackPlayButton2Clicked = true;
				}
				else if(Game.trackPlayButton2Clicked)
					Game.trackPlayButton2Clicked = false;
			}
			else if(Game.trackPlayButton2Clicked)
				Game.trackPlayButton2Clicked = false;
			
			//Pause1 Button
			if (mx >= 280 && mx <= 296) {
				if (my >= 158 && my <= 174) {
					Game.trackPauseButton1Clicked = true;
				}
				else if(Game.trackPauseButton1Clicked)
					Game.trackPauseButton1Clicked = false;
			}
			else if(Game.trackPauseButton1Clicked)
				Game.trackPauseButton1Clicked = false;
			
			//Pause2 Button
			if (mx >= 280 && mx <= 296) {
				if (my >= 358 && my <= 374) {
					Game.trackPauseButton2Clicked = true;
				}
				else if(Game.trackPauseButton2Clicked)
					Game.trackPauseButton2Clicked = false;
			}
			else if(Game.trackPauseButton2Clicked)
				Game.trackPauseButton2Clicked = false;
			
			//Set1 Button
			if (mx >= Game.WIDTH  + 18 && mx <= Game.WIDTH  + 42) {
				if (my >= 158 && my <= 174) {
					Game.set1Clicked = true;
				}
				else if(Game.set1Clicked)
					Game.set1Clicked = false;
			}
			else if(Game.set1Clicked)
				Game.set1Clicked = false;
			
			//Set2 Button
			if (mx >= Game.WIDTH  + 18 && mx <= Game.WIDTH  + 42) {
				if (my >= 358 && my <= 374) {
					Game.set2Clicked = true;
				}
				else if(Game.set2Clicked)
					Game.set2Clicked = false;
			}
			else if(Game.set2Clicked)
				Game.set2Clicked = false;
		}else if(Game.State == STATE.LEADERBOARD) {
			if(Game.areYouSureBoolean) {
				//Yes Button in Settings Menu
				if(mx >=  Game.WIDTH  + 18 && mx <= Game.WIDTH + 18 + 96 && Game.State == Game.STATE.LEADERBOARD) {
					if(my >= 375 && my <= 439)
						Game.yesClicked = true;
					else if(Game.yesClicked)
						Game.yesClicked = false;
				}
				else if(Game.yesClicked)
					Game.yesClicked = false;
				
				//No Button in Settings Menu
				if(mx >=  Game.WIDTH - 64 - 18 && mx <= Game.WIDTH - 18 && Game.State == Game.STATE.LEADERBOARD) {
					if(my >= 375 && my <= 439)
						Game.noClicked = true;
					else if(Game.noClicked)
						Game.noClicked = false;
				}
				else if(Game.noClicked)
					Game.noClicked = false;
			}
			else {
				//Local Button
				if(!LeaderboardController.globalList) {
					//Global Button
					if (mx >= 537 && mx <= 633) {
						if (my >= 50 && my <= 82) {
							Game.globalClicked = true;
						}
						else if(Game.globalClicked)
							Game.globalClicked = false;
					}
					else if(Game.globalClicked)
						Game.globalClicked = false;
					
					//Reset Leaderboard Button
					if (mx >= 40 && mx <= 122) {
						if (my >= 50 && my <= 82) {
							Game.resetLeaderboardClicked = true;
						}
						else if(Game.resetLeaderboardClicked)
							Game.resetLeaderboardClicked = false;
					}
					else if(Game.resetLeaderboardClicked)
						Game.resetLeaderboardClicked = false;
				}else {
					//Upload Button
					if (mx >= 537 && mx <= 633) {
						if (my >= 50 && my <= 82) {
							Game.uploadClicked = true;
						}
						else if(Game.uploadClicked)
							Game.uploadClicked = false;
					}
					else if(Game.uploadClicked)
						Game.uploadClicked = false;
					
					//Local Button
					if (mx >= 40 && mx <= 120) {
						if (my >= 50 && my <= 82) {
							Game.localClicked = true;
						}
						else if(Game.localClicked)
							Game.localClicked = false;
					}
					else if(Game.localClicked)
						Game.localClicked = false;
				}
			}
		}else if(Game.State == STATE.HELP) {
			if(Game.firstTimeBeating) {
				//Credits Button
				if (mx >= 477 && mx <= 591) {
					if (my >= 38 && my <= 70) {
						Game.creditsClicked = true;
					}
					else if(Game.creditsClicked)
						Game.creditsClicked = false;
				}
				else if(Game.creditsClicked)
					Game.creditsClicked = false;
			}
		}
		if(Game.gameControllerInUse && Game.State != Game.STATE.GAME)
			Game.gameControllerInUse = false;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(Game.connectingToServer)
			return;
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
			if(!(Game.serverErrorMessage && ((!Game.updateToConnect && mx >= 0 && mx <= 239 && my >= 0 && my <= 16 &&
				!Game.isPixelTransparentinBufferedImage(Game.serverErrorMessageImage, mx, my)) || 
				(Game.updateToConnect && mx >= 0 && mx <= 271 && my >= 0 && my <= 16 &&
				!Game.isPixelTransparentinBufferedImage(Game.updateToConnectImage, mx, my))))) {
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
				if(!Game.allUnlockedScreen) {
//					chainChompStringImage = Game.resize(HUD.stringToMario3FontImage("chain chomp"), 90, 14);
//					bulletBillStringImage = Game.resize(HUD.stringToMario3FontImage("bullet bill"), 90, 14);
//					bombOmbStringImage = Game.resize(HUD.stringToMario3FontImage("bomb omb"), 75, 14);
//					cheepCheepStringImage = Game.resize(HUD.stringToMario3FontImage("cheep cheep"), 90, 14);
//					ampStringImage = Game.resize(HUD.stringToMario3FontImage("amp"), 27, 14);
//					wigglerStringImage = Game.resize(HUD.stringToMario3FontImage("wiggler"), 63, 14);
//					lakituStringImage = Game.resize(HUD.stringToMario3FontImage("lakitu"), 54, 14);
					boolean b = true;
					switch(Game.itemPosition) {
					case 0:
						if(mx >= 428 && mx <= 518 && my >= 429 && my <= 443 && 
						!(Game.isPixelTransparentinBufferedImage(ShopController.chainChompStringImage, mx-428, my-429)))
							b = false; 
						break;
					case 1:
						if(mx >= 428 && mx <= 518 && my >= 429 && my <= 443 && 
						!(Game.isPixelTransparentinBufferedImage(ShopController.bulletBillStringImage, mx-428, my-429)))
							b = false; 
						break;
					case 2:
						if(mx >= 428 && mx <= 503 && my >= 429 && my <= 443 && 
						!(Game.isPixelTransparentinBufferedImage(ShopController.bombOmbStringImage, mx-428, my-429)))
							b = false; 
						break;
					case 3:
						if(mx >= 428 && mx <= 518 && my >= 429 && my <= 443 && 
						!(Game.isPixelTransparentinBufferedImage(ShopController.cheepCheepStringImage, mx-428, my-429)))
							b = false; 
						break;
					case 4:
						if(mx >= 428 && mx <= 455 && my >= 429 && my <= 443 && 
						!(Game.isPixelTransparentinBufferedImage(ShopController.ampStringImage, mx-428, my-429)))
							b = false; 
						break;
					case 5:
						if(mx >= 428 && mx <= 491 && my >= 429 && my <= 443 && 
						!(Game.isPixelTransparentinBufferedImage(ShopController.wigglerStringImage, mx-428, my-429)))
							b = false; 
						break;
					case 6:
						if(mx >= 428 && mx <= 482 && my >= 429 && my <= 443 && 
						!(Game.isPixelTransparentinBufferedImage(ShopController.lakituStringImage, mx-428, my-429)))
							b = false; 
						break;
					}
					if(Game.currentSkinLocked) {
						switch(Game.characterSkinPosition) {
						case 1:
							if(mx >= Game.WIDTH - 11 && mx <= Game.WIDTH - 11 + 40 && 
								my >= 155 && my <= 155 + ShopController.skinPriceImage.getHeight() && 
								!(Game.isPixelTransparentinBufferedImage(ShopController.skinPriceImage, mx-(Game.WIDTH -11), my-155)))
								b = false;
							else if(mx >= Game.WIDTH - 11 + 40 + 4 && mx <= Game.WIDTH - 11 + 40 + 4 + ShopController.pointsImage.getWidth() &&
									my >= 162 && my <= 162 + ShopController.pointsImage.getHeight() &&
									!(Game.isPixelTransparentinBufferedImage(ShopController.pointsImage, mx-(Game.WIDTH - 11 + 40 + 4), my-162)))
								b = false;
							break;
						case 2:
							if(mx >= Game.WIDTH - 16 && mx <= Game.WIDTH - 16 + 50 && 
								my >= 155 && my <= 155 + ShopController.skinPriceImage.getHeight() && 
								!(Game.isPixelTransparentinBufferedImage(ShopController.skinPriceImage, mx-(Game.WIDTH -16), my-155)))
								b = false;
							else if(mx >= Game.WIDTH - 16 + 50 + 4 && mx <= Game.WIDTH - 16 + 50 + 4 + ShopController.pointsImage.getWidth() &&
									my >= 162 && my <= 162 + ShopController.pointsImage.getHeight() &&
									!(Game.isPixelTransparentinBufferedImage(ShopController.pointsImage, mx-(Game.WIDTH - 16 + 50 + 4), my-162)))
								b = false;
							break;
						case 3:
							if(mx >= Game.WIDTH - 21 && mx <= Game.WIDTH - 21 + 60 && 
								my >= 155 && my <= 155 + ShopController.skinPriceImage.getHeight() && 
								!(Game.isPixelTransparentinBufferedImage(ShopController.skinPriceImage, mx-(Game.WIDTH -21), my-155)))
								b = false;
							else if(mx >= Game.WIDTH - 21 + 60 + 4 && mx <= Game.WIDTH - 21 + 60 + 4 + ShopController.pointsImage.getWidth() &&
									my >= 162 && my <= 162 + ShopController.pointsImage.getHeight() &&
									!(Game.isPixelTransparentinBufferedImage(ShopController.pointsImage, mx-(Game.WIDTH - 21 + 60 + 4), my-162)))
								b = false;
							break;
						}
					}
					if(Game.currentTrackLocked) {
						switch(Game.trackPosition) {
						case 4:
							if(mx >= Game.WIDTH - 11 && mx <= Game.WIDTH - 11 + 40 && 
								my >= 255 && my <= 255 + ShopController.trackPriceImage.getHeight() && 
								!(Game.isPixelTransparentinBufferedImage(ShopController.trackPriceImage, mx-(Game.WIDTH -11), my-255)))
								b = false;
							else if(mx >= Game.WIDTH - 11 + 40 + 4 && mx <= Game.WIDTH - 11 + 40 + 4 + ShopController.pointsImage.getWidth() &&
									my >= 262 && my <= 262 + ShopController.pointsImage.getHeight() &&
									!(Game.isPixelTransparentinBufferedImage(ShopController.pointsImage, mx-(Game.WIDTH - 11 + 40 + 4), my-262)))
								b = false;
							break;
						case 5:
							if(mx >= Game.WIDTH - 16 && mx <= Game.WIDTH - 16 + 50 && 
								my >= 255 && my <= 255 + ShopController.trackPriceImage.getHeight() && 
								!(Game.isPixelTransparentinBufferedImage(ShopController.trackPriceImage, mx-(Game.WIDTH -16), my-255)))
								b = false;
							else if(mx >= Game.WIDTH - 16 + 50 + 4 && mx <= Game.WIDTH - 16 + 50 + 4 + ShopController.pointsImage.getWidth() &&
									my >= 262 && my <= 262 + ShopController.pointsImage.getHeight() &&
									!(Game.isPixelTransparentinBufferedImage(ShopController.pointsImage, mx-(Game.WIDTH - 16 + 50 + 4), my-262)))
								b = false;
							break;
						case 6:
							if(mx >= Game.WIDTH - 21 && mx <= Game.WIDTH - 21 + 60 && 
								my >= 255 && my <= 255 + ShopController.trackPriceImage.getHeight() && 
								!(Game.isPixelTransparentinBufferedImage(ShopController.trackPriceImage, mx-(Game.WIDTH -21), my-255)))
								b = false;
							else if(mx >= Game.WIDTH - 21 + 60 + 4 && mx <= Game.WIDTH - 21 + 60 + 4 + ShopController.pointsImage.getWidth() &&
									my >= 262 && my <= 262 + ShopController.pointsImage.getHeight() &&
									!(Game.isPixelTransparentinBufferedImage(ShopController.pointsImage, mx-(Game.WIDTH - 21 + 60 + 4), my-262)))
								b = false;
							break;
						}
					}
					if(Game.currentFireballLocked) {
						switch(Game.fireballPosition) {
						case 1:
							if(mx >= Game.WIDTH - 11 && mx <= Game.WIDTH - 11 + 40 && 
								my >= 355 && my <= 355 + ShopController.fireballPriceImage.getHeight() && 
								!(Game.isPixelTransparentinBufferedImage(ShopController.fireballPriceImage, mx-(Game.WIDTH -11), my-355)))
								b = false;
							else if(mx >= Game.WIDTH - 11 + 40 + 4 && mx <= Game.WIDTH - 11 + 40 + 4 + ShopController.pointsImage.getWidth() &&
									my >= 362 && my <= 362 + ShopController.pointsImage.getHeight() &&
									!(Game.isPixelTransparentinBufferedImage(ShopController.pointsImage, mx-(Game.WIDTH - 11 + 40 + 4), my-362)))
								b = false;
							break;
						case 2:
							if(mx >= Game.WIDTH - 16 && mx <= Game.WIDTH - 16 + 50 && 
								my >= 355 && my <= 355 + ShopController.fireballPriceImage.getHeight() && 
								!(Game.isPixelTransparentinBufferedImage(ShopController.fireballPriceImage, mx-(Game.WIDTH -16), my-355)))
								b = false;
							else if(mx >= Game.WIDTH - 16 + 50 + 4 && mx <= Game.WIDTH - 16 + 50 + 4 + ShopController.pointsImage.getWidth() &&
									my >= 362 && my <= 362 + ShopController.pointsImage.getHeight() &&
									!(Game.isPixelTransparentinBufferedImage(ShopController.pointsImage, mx-(Game.WIDTH - 16 + 50 + 4), my-362)))
								b = false;
							break;
						case 3:
							if(mx >= Game.WIDTH - 21 && mx <= Game.WIDTH - 21 + 60 && 
								my >= 355 && my <= 355 + ShopController.fireballPriceImage.getHeight() && 
								!(Game.isPixelTransparentinBufferedImage(ShopController.fireballPriceImage, mx-(Game.WIDTH -21), my-355)))
								b = false;
							else if(mx >= Game.WIDTH - 21 + 60 + 4 && mx <= Game.WIDTH - 21 + 60 + 4 + ShopController.pointsImage.getWidth() &&
									my >= 362 && my <= 362 + ShopController.pointsImage.getHeight() &&
									!(Game.isPixelTransparentinBufferedImage(ShopController.pointsImage, mx-(Game.WIDTH - 21 + 60 + 4), my-362)))
								b = false;
							break;
						}
					}
					if(Game.currentItemLocked) {
						switch(Game.itemPosition) {
						case 4:
							if(mx >= Game.WIDTH - 11 && mx <= Game.WIDTH - 11 + 40 && 
								my >= 455 && my <= 455 + ShopController.itemPriceImage.getHeight() && 
								!(Game.isPixelTransparentinBufferedImage(ShopController.itemPriceImage, mx-(Game.WIDTH -11), my-455)))
								b = false;
							else if(mx >= Game.WIDTH - 11 + 40 + 4 && mx <= Game.WIDTH - 11 + 40 + 4 + ShopController.pointsImage.getWidth() &&
									my >= 462 && my <= 462 + ShopController.pointsImage.getHeight() &&
									!(Game.isPixelTransparentinBufferedImage(ShopController.pointsImage, mx-(Game.WIDTH - 11 + 40 + 4), my-462)))
								b = false;
							break;
						case 5:
							if(mx >= Game.WIDTH - 16 && mx <= Game.WIDTH - 16 + 50 && 
								my >= 455 && my <= 455 + ShopController.itemPriceImage.getHeight() && 
								!(Game.isPixelTransparentinBufferedImage(ShopController.itemPriceImage, mx-(Game.WIDTH -16), my-455)))
								b = false;
							else if(mx >= Game.WIDTH - 16 + 50 + 4 && mx <= Game.WIDTH - 16 + 50 + 4 + ShopController.pointsImage.getWidth() &&
									my >= 462 && my <= 462 + ShopController.pointsImage.getHeight() &&
									!(Game.isPixelTransparentinBufferedImage(ShopController.pointsImage, mx-(Game.WIDTH - 16 + 50 + 4), my-462)))
								b = false;
							break;
						case 6:
							if(mx >= Game.WIDTH - 21 && mx <= Game.WIDTH - 21 + 60 && 
								my >= 455 && my <= 455 + ShopController.itemPriceImage.getHeight() && 
								!(Game.isPixelTransparentinBufferedImage(ShopController.itemPriceImage, mx-(Game.WIDTH -21), my-455)))
								b = false;
							else if(mx >= Game.WIDTH - 21 + 60 + 4 && mx <= Game.WIDTH - 21 + 60 + 4 + ShopController.pointsImage.getWidth() &&
									my >= 462 && my <= 462 + ShopController.pointsImage.getHeight() &&
									!(Game.isPixelTransparentinBufferedImage(ShopController.pointsImage, mx-(Game.WIDTH - 21 + 60 + 4), my-462)))
								b = false;
							break;
						}
					}
					switch(Game.trackPosition) {
					case 0:
						if((Game.gameTrack1Set && mx >= Game.WIDTH - 2 &&
						mx <= Game.WIDTH +16 && my >= 201 && my <= 219))
							b = false;
						break;
					case 1:
						if((Game.gameTrack2Set && mx >= Game.WIDTH - 2 &&
						mx <= Game.WIDTH +16 && my >= 201 && my <= 219))
							b = false;
						break;
					case 2:
						if((Game.gameTrack3Set &&mx >= Game.WIDTH - 2 &&
						mx <= Game.WIDTH +16 && my >= 201 && my <= 219))
							b = false;
						break;
					case 3:
						if((Game.gameTrack4Set && mx >= Game.WIDTH - 2 &&
						mx <= Game.WIDTH +16 && my >= 201 && my <= 219))
							b = false;
						break;
					case 4:
						if((Game.gameTrack5Set && mx >= Game.WIDTH - 2 &&
						mx <= Game.WIDTH +16 && my >= 201 && my <= 219))
							b = false;
						break;
					case 5:
						if((Game.gameTrack6Set && mx >= Game.WIDTH - 2 &&
						mx <= Game.WIDTH +16 && my >= 201 && my <= 219))
							b = false;
						break;
					case 6:
						if((Game.gameTrack7Set && mx >= Game.WIDTH - 2 &&
						mx <= Game.WIDTH +16 && my >= 201 && my <= 219))
							b = false;
						break;
					case 7:
						if((Game.gameTrack8Set && mx >= Game.WIDTH - 2 &&
						mx <= Game.WIDTH +16 && my >= 201 && my <= 219))
							b = false;
						break;
					}
					if(b && !(mx >=  Game.WIDTH - 48 && mx <= Game.WIDTH - 32 && //L buttons
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
						(((Game.totalPoints != 0 && !(mx >= Game.WIDTH * Game.SCALE - Game.totalPointsImage.getWidth() - 60 &&
						  mx <= Game.WIDTH * Game.SCALE - Game.totalPointsImage.getWidth() - 60 + Game.totalPointsImage.getWidth() &&
						  my >= 20 && my <= 20 + Game.totalPointsImage.getHeight() && !(Game.isPixelTransparentinBufferedImage(Game.totalPointsImage, mx-(Game.WIDTH * Game.SCALE - Game.totalPointsImage.getWidth() - 60), my-20)))) &&//Total Points
						(Game.totalPoints != 0 && !(mx >= Game.WIDTH * Game.SCALE - 55 && mx <= Game.WIDTH * Game.SCALE - 55 + ShopController.pointsImage.getWidth() &&
						my >= 20 + Game.totalPointsImage.getHeight()/2 -3 && my <= 20 + Game.totalPointsImage.getHeight()/2 -3 +
						ShopController.pointsImage.getHeight() && !(Game.isPixelTransparentinBufferedImage(ShopController.pointsImage, mx-(Game.WIDTH * Game.SCALE - 55), my-(20 + Game.totalPointsImage.getHeight()/2 -3)))))|| Game.totalPoints == 0)) &&//points title
						!(mx >= Game.WIDTH && mx <= Game.WIDTH + 16 && my >= 120 && my <= 148) &&//Mario Skins
						!(Game.trackPosition == 0 && mx >= Game.WIDTH + 2 && mx <= Game.WIDTH + 2 + ShopController.songTrackImages[0].getWidth()&&
						my >= 227 && my <= 227+ShopController.songTrackImages[0].getHeight() &&
						!(Game.isPixelTransparentinBufferedImage(ShopController.songTrackImages[0], mx-(Game.WIDTH+2), my-227))) &&//Track Img 0
						!(Game.trackPosition == 1 && mx >= Game.WIDTH + 2 && mx <= Game.WIDTH + 2 + ShopController.songTrackImages[1].getWidth()&&
						my >= 227 && my <= 227+ShopController.songTrackImages[1].getHeight() &&
						!(Game.isPixelTransparentinBufferedImage(ShopController.songTrackImages[1], mx-(Game.WIDTH+2), my-227)))&& //Track Img 1
						!(Game.trackPosition == 2 && mx >= Game.WIDTH + 2 && mx <= Game.WIDTH + 2 + ShopController.songTrackImages[2].getWidth()&&
						my >= 227 && my <= 227+ShopController.songTrackImages[2].getHeight() &&
						!(Game.isPixelTransparentinBufferedImage(ShopController.songTrackImages[2], mx-(Game.WIDTH+2), my-227)))&& //Track Img 2
						!(Game.trackPosition == 3 && mx >= Game.WIDTH && mx <= Game.WIDTH + ShopController.songTrackImages[3].getWidth()&&
						my >= 227 && my <= 227+ShopController.songTrackImages[3].getHeight() &&
						!(Game.isPixelTransparentinBufferedImage(ShopController.songTrackImages[3], mx-(Game.WIDTH), my-227)))&& //Track Img 3
						!(Game.trackPosition == 4 && mx >= Game.WIDTH && mx <= Game.WIDTH + ShopController.songTrackImages[5].getWidth()&&
						my >= 223 && my <= 223+ShopController.songTrackImages[5].getHeight() &&
						!(Game.isPixelTransparentinBufferedImage(ShopController.songTrackImages[5], mx-(Game.WIDTH), my-223)))&& //Track Img 4
						!(Game.trackPosition == 5 && mx >= Game.WIDTH && mx <= Game.WIDTH + ShopController.songTrackImages[6].getWidth()&&
						my >= 223 && my <= 223+ShopController.songTrackImages[6].getHeight() &&
						!(Game.isPixelTransparentinBufferedImage(ShopController.songTrackImages[6], mx-(Game.WIDTH), my-223)))&& //Track Img 5
						!(Game.trackPosition == 6 && mx >= Game.WIDTH && mx <= Game.WIDTH + ShopController.songTrackImages[7].getWidth()&&
						my >= 223 && my <= 223+ShopController.songTrackImages[7].getHeight() &&
						!(Game.isPixelTransparentinBufferedImage(ShopController.songTrackImages[7], mx-(Game.WIDTH), my-223)))&& //Track Img 6
						!(Game.trackPosition == 7 && mx >= Game.WIDTH + 2 && mx <= Game.WIDTH + 2 + ShopController.songTrackImages[4].getWidth()&&
						my >= 227 && my <= 227+ShopController.songTrackImages[4].getHeight() &&
						!(Game.isPixelTransparentinBufferedImage(ShopController.songTrackImages[0], mx-(Game.WIDTH+2), my-227)))&& //Track Img 7
						!(!(Game.fireballPosition == 3) && mx >= Game.WIDTH && mx <= Game.WIDTH + 16 && my >= 328 && my <= 344) && //Fireballs
						!((Game.fireballPosition == 3) && mx >= Game.WIDTH && mx <= Game.WIDTH + 16 && my >= 329 && my <= 343) &&//Fireballs(BuzzyBeetle)
						!(!(Game.currentItemLocked) && mx >= Game.WIDTH && mx <= Game.WIDTH + 16 && my >= 428 && my <= 444) &&//Items unlocked
						!((Game.currentItemLocked) && mx >= Game.WIDTH && mx <= Game.WIDTH + 18 && my >= 426 && my <= 444) &&//Items locked
						!((Game.currentlySelectedCharacterSkin == Game.characterSkinPosition) && mx >= Game.WIDTH - 2 &&
						mx <= Game.WIDTH +16 && my >= 101 && my <= 119) &&//Skin Position Selected
//						!((Game.currentlySelectedTrack == Game.trackPosition) && mx >= Game.WIDTH - 2 &&
//						mx <= Game.WIDTH +16 && my >= 201 && my <= 219) &&//Track Position Selected
						!((Game.currentlySelectedFireball == Game.fireballPosition) && mx >= Game.WIDTH - 2 &&
						mx <= Game.WIDTH +16 && my >= 301 && my <= 319) &&//Fireball Position Selected
						!(!Game.currentItemLocked && mx >= Game.WIDTH - 2 &&
						mx <= Game.WIDTH +16 && my >= 401 && my <= 419) &&//Item Position Selected
						!(!(Game.currentlySelectedCharacterSkin == Game.characterSkinPosition) && mx >= Game.WIDTH + 2 &&
						mx <= Game.WIDTH +12 && my >= 105 && my <= 115) &&//Skin Position Unselected
						!(mx >= Game.WIDTH + 2 &&
						mx <= Game.WIDTH +12 && my >= 205 && my <= 215) &&//Track Position Unselected
						!(!(Game.currentlySelectedFireball == Game.fireballPosition) && mx >= Game.WIDTH + 2 &&
						mx <= Game.WIDTH +12 && my >= 305 && my <= 315) &&//Fireball Position Unselected
						!(Game.currentItemLocked && mx >= Game.WIDTH + 2 &&
						mx <= Game.WIDTH +12 && my >= 405 && my <= 415) //&&//Item Position Unselected
//						!(Game.currentSkinLocked && mx >= Game.WIDTH-((Game.characterSkinPosition*5)+1) && mx <= Game.WIDTH-((Game.characterSkinPosition*5)+1) + ShopController.skinPriceImage.getWidth() &&
//						my >= 155 && my <= 155 + ShopController.skinPriceImage.getHeight() && 
//						!(Game.isPixelTransparentinBufferedImage(ShopController.skinPriceImage, mx-(Game.WIDTH-((Game.characterSkinPosition*5)+1)), my-155))) &&//Skin Cost
//						!(Game.currentSkinLocked && mx >= Game.WIDTH-(((Game.characterSkinPosition*5)+1)-4-ShopController.skinPriceImage.getWidth()) &&
//						mx <= Game.WIDTH-(((Game.characterSkinPosition*5)+1)-4-ShopController.skinPriceImage.getWidth())+ShopController.pointsImage.getWidth() &&
//						my >= 162 && my <= 162+ShopController.pointsImage.getHeight() && 
//						!(Game.isPixelTransparentinBufferedImage(ShopController.pointsImage, mx-(Game.WIDTH-(((Game.characterSkinPosition*5)+1)-4-ShopController.skinPriceImage.getWidth())), my-162)))//Skin Cost Points
						) {
						Game.starExplode = true;
						Game.mx = mx;
						Game.my = my;
					}
				}
			}else if(Game.State == Game.STATE.HELP) {
				boolean b = false;
				if(Game.helpLines.isEmpty())
					b = true;
				else {
					for(int i = 0; i < Game.helpLines.size(); i++) {
						if(!(mx >= Game.WIDTH - (Game.helpLines.get(i).getWidth()/2) && mx <= Game.WIDTH - (Game.helpLines.get(i).getWidth()/2) + Game.helpLines.get(i).getWidth() 
								&& my >= ((i+1)*50)+70 && my <= ((i+1)*50)+70 + Game.helpLines.get(i).getHeight()))
							b = true;
						else {
							if(!(Game.isPixelTransparentinBufferedImage( Game.helpLines.get(i), mx-(Game.WIDTH - (Game.helpLines.get(i).getWidth()/2)), my-(((i+1)*50)+70)))) { 
								b = false;
							}else
								b=true;
							break;
						}
					}
				}
				if(b && Game.helpLinesLast != null) {
					if((mx >= Game.WIDTH - (Game.helpLinesLast.getWidth()/2)+4 && mx <= Game.WIDTH - (Game.helpLinesLast.getWidth()/2)+4+Game.helpLinesLast.getWidth() && 
							my >= 420 && my <= 420+Game.helpLinesLast.getHeight()) && !(Game.imageTranslucent < 0.01) && 
							!(Game.isPixelTransparentinBufferedImage(Game.helpLinesLast, mx-(Game.WIDTH - (Game.helpLinesLast.getWidth()/2)+4), my-420))) {
						b = false;
					}
					else
						b = true;
				}
				if(b && !(mx >= 40 && mx <= 88 && my >= 20 && my <= 36) && //Back button
					!(Game.firstTimeBeating && mx >= 477 && mx <= 591 && my >= 38 && my <= 70) &&//Credits button
					!(mx >= Game.WIDTH - (Game.helpTitleBigger.getWidth()/2) && mx <= Game.WIDTH - (Game.helpTitleBigger.getWidth()/2) + 128 && my >= 20 && my <= 84 && 
					!(Game.isPixelTransparentinBufferedImage(Game.helpTitleBigger, mx-(Game.WIDTH - (Game.helpTitleBigger.getWidth()/2)), my-20)))//Help Title
					) {
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
						!(mx >=  Game.WIDTH + 178 && mx <= Game.WIDTH + 217 && my >= 42 && my <= 62) &&//Gamepad Image Button
						!(mx >=  Game.WIDTH + 268 && mx <= Game.WIDTH + 286 && my >= 41 && my <= 63) &&//Note Image Button
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
						!(my >= 320 && my <= 352 && mx >= 20 &&mx <= 270 &&
						!(Game.isPixelTransparentinBufferedImage(Game.skipAnimationsTitle, mx-20, my-320)))
						) {
						Game.starExplode = true;
						Game.mx = mx;
						Game.my = my;
					}
				}
			}else if (Game.State == Game.STATE.LEADERBOARD) {
				if(!Game.areYouSureBoolean) {
					Boolean b = false;
					if(my > 84) {
						if(Game.leaderboardImage.isEmpty())
							b = true;
						else {
							for(int i = 0; i <= Game.leaderboardImage.size() -1; i++) {
								if(!(mx >= 44 && mx <= 44 + Game.leaderboardImage.get(i).getWidth() -16 && my >= (i*20) + 105 + (int) LeaderboardController.y && my <= (i*20) + 105 + (int) LeaderboardController.y + Game.leaderboardImage.get(i).getHeight()))
									b = true;
								else {
									if(!(Game.isPixelTransparentinBufferedImage( Game.leaderboardImage.get(i), mx-44, my-((i*20) + 105 + (int) LeaderboardController.y)))) { //THIS WILL CHECK FOR TRANSPARENCY IN LEADERBOARD
										b = false;//																												I WANT TO DISABLE BECAUSE SCROLLING PAST THE TOPS IMG Y LOCATIONS ARE OFF
									}else
										b=true;
									break;
								}
							}
						}
					}
					else if(!(mx >= (Game.WIDTH/2) && mx <= (Game.WIDTH/2)+360 && my >= 20 && my <= 84 &&
							!(Game.isPixelTransparentinBufferedImage(Game.leaderboardTitleBigger, mx-(Game.WIDTH/2), my-20))))
						b = true;
					if(b && !(mx >= 40 && mx <= 88 && my >= 20 && my <= 36) &&//Back Button
							!(!LeaderboardController.globalList && mx >= 40 && mx <= 122 && my >= 50 && my <= 82) &&//Reset Leaderboard Button
							!(mx >= 537 && mx <= 633 && my >= 50 && my <=82) &&//Global Button && Upload Button
							!(LeaderboardController.globalList && mx >= 40 && mx <= 120 && my >= 50 && my <=82)) {//Local button
						//System.out.println(Game.mx + " lead"+(Game.leaderboardImage.get(0).getWidth()+44));
						Game.starExplode = true;
						Game.mx = mx;
						Game.my = my;
					}
				}
			}else if(Game.State == Game.STATE.GAMEOVER) {
				boolean b = true;
				if(b && HUD.score != 0 && ((mx >= (Game.WIDTH-Game.scoreImage.getWidth()/2)-42 && 
						mx <= (Game.WIDTH-Game.scoreImage.getWidth()/2)-42+Game.scoreTitle.getWidth() &&
						my >= 30 && my <= 30 + Game.scoreTitle.getHeight() &&
						!Game.isPixelTransparentinBufferedImage(Game.scoreTitle,
						mx-((Game.WIDTH-Game.scoreImage.getWidth()/2)-42), my-30))||//Score Title
						(mx >= ((Game.WIDTH-Game.scoreImage.getWidth()/2)+Game.scoreTitle.getWidth())-42 && 
						mx <= ((Game.WIDTH-Game.scoreImage.getWidth()/2)+Game.scoreTitle.getWidth())-42+Game.scoreImage.getWidth() &&
						my >= 30 && my <= 30 + Game.scoreImage.getHeight() &&
						!Game.isPixelTransparentinBufferedImage(Game.scoreImage, 
						mx-(((Game.WIDTH-Game.scoreImage.getWidth()/2)+Game.scoreTitle.getWidth())-42), my-30))))//Score Image
					b = false;
				if(b && HUD.score == 0 && ((mx >= 267 && mx <= 373 && my >= 30 && my <= 62 &&
					!Game.isPixelTransparentinBufferedImage(Game.scoreTitle, mx-267, my-30)) ||//Score Title
					(mx >= 373 && mx <= 389 && my >= 30 && my <= 62 &&
					!Game.isPixelTransparentinBufferedImage(Game.zeroImage, mx-373, my-30))))//Zero Image
					b = false;
				if(b && !(mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && my >= 200 && my <= 264) &&//Play button
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
//					try {
//						if(Game.totalPointsImage != null && !LeaderboardController.getFromSettings("Total Points: ").equals("")) {
//							if(!(mx >= ((Game.WIDTH-Game.totalPointsImage.getWidth()/2)-42) && mx <= (((Game.WIDTH-Game.totalPointsImage.getWidth()/2)-42)) + Game.totalPointsImage.getWidth() &&
//									my >= 30 && my <= 30 + Game.totalPointsImage.getHeight() && !(Game.isPixelTransparentinBufferedImage(Game.scoreTitle, mx-((Game.WIDTH-Game.totalPointsImage.getWidth()/2)-42), my-30))) &&//Score Image
//									!(mx >= (((Game.WIDTH-Game.totalPointsImage.getWidth()/2)+Game.scoreTitle.getWidth())-42) && mx <= ((((Game.WIDTH-Game.totalPointsImage.getWidth()/2)+Game.scoreTitle.getWidth())-42)) + Game.totalPointsImage.getWidth() &&
//									my >= 30 && my <= 30 + Game.totalPointsImage.getHeight() &&
//									!(Game.isPixelTransparentinBufferedImage(Game.totalPointsImage, mx-(((Game.WIDTH-Game.totalPointsImage.getWidth()/2)+Game.scoreTitle.getWidth())-42), my-30)))) {//Points Image
//								Game.starExplode = true;
//								Game.mx = mx;
//								Game.my = my;
//							}
//						}
//						else {
//							if(!(mx >= (Game.WIDTH-Game.scoreTitle.getWidth()/2) && mx <= ((Game.WIDTH-Game.scoreTitle.getWidth()/2)+ Game.scoreTitle.getWidth()) && 
//									my >= 30 && my <= 30 + Game.scoreTitle.getHeight() &&
//									!(Game.isPixelTransparentinBufferedImage(Game.scoreTitle, mx-(Game.WIDTH-Game.scoreTitle.getWidth()/2), my-30))) &&//Score Image
//									!(mx >= (Game.WIDTH+Game.scoreTitle.getWidth()/2) && mx <= ((Game.WIDTH+Game.scoreTitle.getWidth()/2) + Game.zeroImage.getWidth()) &&
//									my >= 30 && my <= 30 + Game.zeroImage.getHeight() && 
//									!(Game.isPixelTransparentinBufferedImage(Game.zeroImage, mx-(Game.WIDTH+Game.scoreTitle.getWidth()/2), my-30)))) {//Zero Image
//								Game.starExplode = true;
//								Game.mx = mx;
//								Game.my = my;
//							}
//						}
//					} catch (IOException e1) {
//						Game.starExplode = true;
//						Game.mx = mx;
//						Game.my = my;
//					}
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
						if((mx >= 200+Game.postLetterXPosition && mx <= 216+Game.postLetterXPosition && my >= 200 && my <= 264) && 
								!(Game.imageTranslucent < 0.01)) {
							b = false;
						}
						else
							b = true;
					}
					else {
						if((mx >= Game.postLetterXPositionBeginning+Game.postLetterXPosition && mx <= Game.postLetterXPositionBeginning+Game.postLetterXPosition + 16 &&
								my >= 200 && my <= 264) && !(Game.imageTranslucent < 0.01)) {
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
					if(mx >= Game.WIDTH-51 && mx <= Game.WIDTH+51 && my >= 300 && my <= 332 && b == true) 
						b = false;	
				if(b && HUD.score != 0 && ((mx >= (Game.WIDTH-((Game.scoreTitle.getWidth()+Game.scoreImage.getWidth())/2)) && 
						mx <= (Game.WIDTH-((Game.scoreTitle.getWidth()+Game.scoreImage.getWidth())/2))+Game.scoreTitle.getWidth() &&
						my >= 130 && my <= 130 + Game.scoreTitle.getHeight() &&
						!Game.isPixelTransparentinBufferedImage(Game.scoreTitle,
						mx-((Game.WIDTH-((Game.scoreTitle.getWidth()+Game.scoreImage.getWidth())/2))), my-130))||//Score Title
						(mx >= ((Game.WIDTH-((Game.scoreTitle.getWidth()+Game.scoreImage.getWidth())/2))+Game.scoreTitle.getWidth()) && 
						mx <= ((Game.WIDTH-((Game.scoreTitle.getWidth()+Game.scoreImage.getWidth())/2))+Game.scoreTitle.getWidth())+Game.scoreImage.getWidth() &&
						my >= 130 && my <= 130 + Game.scoreImage.getHeight() &&
						!Game.isPixelTransparentinBufferedImage(Game.scoreImage, 
						mx-(((Game.WIDTH-((Game.scoreTitle.getWidth()+Game.scoreImage.getWidth())/2))+Game.scoreTitle.getWidth())), my-130))))//Score Image
					b = false;
				if(b && HUD.score == 0 && ((mx >= 267 && mx <= 373 && my >= 130 && my <= 162 &&
					!Game.isPixelTransparentinBufferedImage(Game.scoreTitle, mx-267, my-130)) ||//Score Title
					(mx >= 373 && mx <= 389 && my >= 130 && my <= 162 &&
					!Game.isPixelTransparentinBufferedImage(Game.zeroImage, mx-373, my-130))))//Zero Image
					b = false;
				if(b && !(mx >= 206 && mx <= 434 && my >= 370 && my <= 402 && 
						!Game.isPixelTransparentinBufferedImage(Game.sendToServerTitle, mx-206, my-370)) &&//Send To Server Title
						!(mx >= 304 && mx <= 336 && my >= 410 && my <= 442)) {//Checkbox
					Game.starExplode = true;
					Game.mx = mx;
					Game.my = my;
					Game.keysAreInUse = false;
				}
				//System.out.println("Game.playerNameImage"+(Game.playerNameImage.getWidth()+200)+"mx="+mx);
						
			}else if(Game.State == Game.STATE.CONTROLS) {
				if(ControlsController.buttonChangeTimer < System.currentTimeMillis() && 
						!(mx >= 40 && mx <= 88 && my >= 20 && my <= 36) && //Back Button
						!(mx >= 564 && mx <= 605 && my >= 443 && my <= 459) && //Reset Button
						!(mx >= 489 && mx <= 538 && my >= 65 && my <= 74) && //Rescan Button
						!(mx >= Game.WIDTH-51 && mx <= Game.WIDTH-28 && my >= 125 && my <= 147) && //WASD up Button
						!(mx >= Game.WIDTH-51 && mx <= Game.WIDTH-28 && my >= 170 && my <= 192) && //WASD down Button
						!(mx >= Game.WIDTH-51 && mx <= Game.WIDTH-28 && my >= 215 && my <= 237) && //WASD left Button
						!(mx >= Game.WIDTH-51 && mx <= Game.WIDTH-28 && my >= 260 && my <= 282) && //WASD right Button
						!(mx >= Game.WIDTH-51 && mx <= Game.WIDTH-28 && my >= 305 && my <= 327) && //WASD shoot Button
						!(mx >= Game.WIDTH-51 && mx <= Game.WIDTH-28 && my >= 350 && my <= 372) && //WASD item Button
						!(mx >= Game.WIDTH-51 && mx <= Game.WIDTH-28 && my >= 395 && my <= 417) && //WASD pause Button
						!(mx >= Game.WIDTH-51 && mx <= Game.WIDTH-28 && my >= 440 && my <= 462) && //WASD cancel Button
						!(mx >= 385 && mx <= 408 && my >= 125 && my <= 147) && //XDevice up Button
						!(mx >= 385 && mx <= 408 && my >= 170 && my <= 192) && //XDevice down Button
						!(mx >= 385 && mx <= 408 && my >= 215 && my <= 237) && //XDevice left Button
						!(mx >= 385 && mx <= 408 && my >= 260 && my <= 282) && //XDevice right Button
						!(mx >= 385 && mx <= 408 && my >= 305 && my <= 327) && //XDevice shoot Button
						!(mx >= 385 && mx <= 408 && my >= 350 && my <= 372) && //XDevice item Button
						!(mx >= 385 && mx <= 408 && my >= 395 && my <= 417) && //XDevice pause Button
						!(mx >= 385 && mx <= 408 && my >= 440 && my <= 462) && //XDevice cancel Button
						!(mx >= 501 && mx <= 524 && my >= 125 && my <= 147) && //DirectInput up Button
						!(mx >= 501 && mx <= 524 && my >= 170 && my <= 192) && //DirectInput down Button
						!(mx >= 501 && mx <= 524 && my >= 215 && my <= 237) && //DirectInput left Button
						!(mx >= 501 && mx <= 524 && my >= 260 && my <= 282) && //DirectInput right Button
						!(mx >= 501 && mx <= 524 && my >= 305 && my <= 327) && //DirectInput shoot Button
						!(mx >= 501 && mx <= 524 && my >= 350 && my <= 372) && //DirectInput item Button
						!(mx >= 501 && mx <= 524 && my >= 395 && my <= 417) && //DirectInput pause Button
						!(mx >= 501 && mx <= 524 && my >= 440 && my <= 462) && //DirectInput cancel Button
						!(mx >= 20 && mx <= 52 && my >= 120 && my <= 152 && 
						!(Game.isPixelTransparentinBufferedImage(Game.upImageTitle, mx-20, my-120))) &&//Up Image Title
						!(mx >= 20 && mx <= 84 && my >= 165 && my <= 197 && 
						!(Game.isPixelTransparentinBufferedImage(Game.downImageTitle, mx-20, my-165))) &&//Down Image Title
						!(mx >= 20 && mx <= 84 && my >= 210 && my <= 242 && 
						!(Game.isPixelTransparentinBufferedImage(Game.leftImageTitle, mx-20, my-210))) &&//Left Image Title
						!(mx >= 20 && mx <= 102 && my >= 255 && my <= 287 && 
						!(Game.isPixelTransparentinBufferedImage(Game.rightImageTitle, mx-20, my-255))) &&//Right Image Title
						!(mx >= 20 && mx <= 228 && my >= 300 && my <= 332 && 
						!(Game.isPixelTransparentinBufferedImage(Game.shootImageTitle, mx-20, my-300))) &&//Shoot Image Title
						!(mx >= 20 && mx <= 154 && my >= 345 && my <= 377 && 
						!(Game.isPixelTransparentinBufferedImage(Game.itemImageTitle, mx-20, my-345))) &&//Item Image Title
						!(mx >= 20 && mx <= 214 && my >= 390 && my <= 422 && 
						!(Game.isPixelTransparentinBufferedImage(Game.pauseImageTitle, mx-20, my-390))) &&//Pause Image Title
						!(mx >= 20 && mx <= 116 && my >= 435 && my <= 467 && 
						!(Game.isPixelTransparentinBufferedImage(Game.cancelImageTitle, mx-20, my-435))) &&//Cancel Image Title
						!(mx >= (Game.WIDTH-73) && mx <= (Game.WIDTH-5) && my >= 94 && my <= 110 && 
						!(Game.isPixelTransparentinBufferedImage(Game.keyboardTitle,mx-(Game.WIDTH-73),my-94))) &&//Keyboard Image Title
						!(mx >= 373 && mx <= 421 && my >= 94 && my <= 110 && 
						!(Game.isPixelTransparentinBufferedImage(Game.xInputTitle,mx-373,my-94))) &&//XInput Image Title
						!(mx >= 470 && mx <= 559 && my >= 94 && my <= 110 && 
						!(Game.isPixelTransparentinBufferedImage(Game.directInputTitle,mx-470,my-94))) &&//DirectInput Image Title
						!(mx >= Game.WIDTH-130 && mx <= Game.WIDTH+130 && my >= 20 && my <= 84 && 
						!(Game.isPixelTransparentinBufferedImage(Game.controlsTitle, mx-(Game.WIDTH-130), my-20)))//Controls Title
						) {
					Game.starExplode = true;
					Game.mx = mx;
					Game.my = my;
				}
			}else if(Game.State == Game.STATE.TRACKLIST) {
				boolean b = true;
				switch(Game.gameTrackPosition) {
				case 0:
					if(Game.gameTrack1Set && mx >= 155 && mx <=187 && my >= 147 && my <= 183 && 
					!Game.isPixelTransparentinBufferedImage(Game.trackSetBox, mx-155, my-147))
						b = false;
					break;
				case 1:
					if(Game.gameTrack2Set && mx >= 155 && mx <=187 && my >= 147 && my <= 183 && 
					!Game.isPixelTransparentinBufferedImage(Game.trackSetBox, mx-155, my-147))
						b = false;
					break;
				case 2:
					if(Game.gameTrack3Set && mx >= 155 && mx <=187 && my >= 147 && my <= 183 && 
					!Game.isPixelTransparentinBufferedImage(Game.trackSetBox, mx-155, my-147))
						b = false;
					break;
				case 3:
					if(Game.gameTrack4Set && mx >= 155 && mx <=187 && my >= 147 && my <= 183 && 
					!Game.isPixelTransparentinBufferedImage(Game.trackSetBox, mx-155, my-147))
						b = false;
					break;
				case 4:
					if(Game.gameTrack5Set && Game.track4Unlocked && mx >= 155 && mx <=187 && my >= 147 && my <= 183 && 
					!Game.isPixelTransparentinBufferedImage(Game.trackSetBox, mx-155, my-147))
						b = false;
					if(!Game.track4Unlocked && mx >= 163 && mx <=179 && my >= 135 && my <= 150 && 
						!Game.isPixelTransparentinBufferedImage(Game.lockedImage, mx-163, my-135))
								b = false;
					break;
				case 5:
					if(Game.gameTrack6Set && Game.track5Unlocked && mx >= 155 && mx <=187 && my >= 147 && my <= 183 && 
					!Game.isPixelTransparentinBufferedImage(Game.trackSetBox, mx-155, my-147))
						b = false;
					if(!Game.track5Unlocked && mx >= 163 && mx <=179 && my >= 135 && my <= 150 && 
							!Game.isPixelTransparentinBufferedImage(Game.lockedImage, mx-163, my-135))
									b = false;
					break;
				case 6:
					if(Game.gameTrack7Set && Game.track6Unlocked && mx >= 155 && mx <=187 && my >= 147 && my <= 183 && 
					!Game.isPixelTransparentinBufferedImage(Game.trackSetBox, mx-155, my-147))
						b = false;
					if(!Game.track6Unlocked && mx >= 163 && mx <=179 && my >= 135 && my <= 150 && 
							!Game.isPixelTransparentinBufferedImage(Game.lockedImage, mx-163, my-135))
									b = false;
					break;
				case 7:
					if(Game.gameTrack8Set && ((mx >= 155 && mx <=187 && my >= 147 && my <= 183 && 
					!Game.isPixelTransparentinBufferedImage(Game.trackSetBox, mx-155, my-147)) || 
					(mx >= 159 && mx <=184 && my >= 116 && my <= 145 && 
					!Game.isPixelTransparentinBufferedImage(Game.redBowserIcon, mx-159, my-116))))
						b = false;
					if(!Game.gameTrack8Set && mx >= 159 && mx <= 184 && my >= 124 && my <= 153 &&
						!Game.isPixelTransparentinBufferedImage(Game.redBowserIcon, mx-159, my-124))
						b = false;
					break;
				}
				switch(Game.menuTrackPosition) {
				case 0:
					if(Game.menuTrack1Set && mx >= 155 && mx <=187 && my >= 347 && my <= 383 && 
					!Game.isPixelTransparentinBufferedImage(Game.trackSetBox, mx-155, my-347))
						b = false;
					break;
				case 1:
					if(Game.menuTrack2Set && mx >= 155 && mx <=187 && my >= 347 && my <= 383 && 
					!Game.isPixelTransparentinBufferedImage(Game.trackSetBox, mx-155, my-347))
						b = false;
					break;
				case 2:
					if(Game.menuTrack3Set && mx >= 155 && mx <=187 && my >= 347 && my <= 383 && 
					!Game.isPixelTransparentinBufferedImage(Game.trackSetBox, mx-155, my-347))
						b = false;
					break;
				case 3:
					if(Game.menuTrack4Set && mx >= 155 && mx <=187 && my >= 347 && my <= 383 && 
					!Game.isPixelTransparentinBufferedImage(Game.trackSetBox, mx-155, my-347))
						b = false;
					break;
				case 4:
					if(Game.menuTrack5Set && Game.menuTrack4Unlocked && mx >= 155 && mx <=187 && my >= 347 && my <= 383 && 
					!Game.isPixelTransparentinBufferedImage(Game.trackSetBox, mx-155, my-347))
						b = false;
					if(!Game.menuTrack4Unlocked && mx >= 163 && mx <=179 && my >= 335 && my <= 350 && 
					!Game.isPixelTransparentinBufferedImage(Game.lockedImage, mx-163, my-335))
						b = false;
					break;
				}
				if(b && !(mx >= 40 && mx <= 88 && my >= 20 && my <= 36) && //Back Button 
						!(mx >= 135 && mx <= 151 &&
						((my >= 150 && my <= 182) || (my >= 350 && my <= 382))) &&//L Buttons
						!(mx >= 191 && mx <= 207 &&
						((my >= 150 && my <= 182) || (my >= 350 && my <= 382))) &&//R Buttons
						!(mx >= 250 && mx <= 266 &&
						((my >= 158 && my <= 174) || (my >= 358 && my <= 374))) &&//Play Buttons
						!(mx >= 280 && mx <= 296 &&
						((my >= 158 && my <= 174) || (my >= 358 && my <= 374))) &&//Stop Buttons
						!(mx >= 338 && mx <= 362 &&
						((my >= 158 && my <= 174) || (my >= 358 && my <= 374))) &&//Set Buttons
						!(mx >= 161 && mx <= 181 &&
						((my >= 155 && my <= 175 && !(Game.isPixelTransparentinBufferedImage(Game.gameTrackNumber, mx-161, my-155))) || 
						(my >= 355 && my <= 375 && !(Game.isPixelTransparentinBufferedImage(Game.menuTrackNumber, mx-161, my-355))))) &&//Track Numbers
						!(mx >= Game.WIDTH-102 && mx <= Game.WIDTH+102 && my >= 20 && my <= 84 && 
						!(Game.isPixelTransparentinBufferedImage(Game.tracklistTitle, mx-(Game.WIDTH-102), my-20))) &&//Tracklist Title
						!(mx >= 20 && mx <= 90 && my >= 150 && my <= 182 && 
						!(Game.isPixelTransparentinBufferedImage(Game.gameTitle, mx-20, my-150))) &&//Game Title
						!(mx >= 20 && mx <= 90 && my >= 350 && my <= 382 && 
						!(Game.isPixelTransparentinBufferedImage(Game.menuTitle, mx-20, my-350)))//Menu Title
						) {
					Game.starExplode = true;
					Game.mx = mx;
					Game.my = my;
				}
			}//STAR EXPLOSION
			}
			// Play Button
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.MENU ||
					mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.GAMEOVER) {
				if (my >= 200 && my <= 264 && Game.playClicked) {
					// Pressed Play Button
					if(Game.State == STATE.GAMEOVER)
						Game.State = Game.STATE.RESET;
					else {
						if(Game.skipAnimations)
							Game.State = Game.STATE.GAME;
						else
							Game.State = Game.STATE.TRANSITION_ENTRANCE;
					}
					Game.keysAreInUse = false;
					Game.smb3CoinSoundLoop.play();
					Game.selectorButtonPosition = 0;
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
					Game.closeGame();
					System.exit(1);
				}
				if (Game.State != Game.STATE.GAME && my >= 400 && my <= 464 && Game.exitClicked) {
					// Pressed Exit Button
					Game.closeGame();
					System.exit(1);
				}
			}
			
			//Resume Button
			if (mx >= Game.WIDTH / 2 + 80 && mx <= Game.WIDTH / 2 + 288 && Game.State == Game.STATE.GAME && Game.getUserHasPaused()) {
				if(my >= 100 && my <= 164 && Game.resumeClicked) {
					if(Game.isPaused() == true && Game.getSoundFXisPlaying() == false){
						Game.escapePressedNegateAction = false;
						if(Game.gameControllerInUse)
							Game.keysAreInUse = true;
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
					Game.selectorButtonPosition = 0;
					if(Game.gameControllerInUse)
						Game.keysAreInUse = true;
				}
			}	
			
			//Back Button
			if(mx >= 40 && mx <= 88 && (Game.State == Game.STATE.SET_SCORE || Game.State == Game.STATE.LEADERBOARD || Game.State == Game.STATE.SHOP
					|| Game.State == Game.STATE.HELP || Game.State == Game.STATE.SETTINGS || Game.State == Game.STATE.CONTROLS || Game.State == Game.STATE.TRACKLIST) 
					&& buttonTimer < System.currentTimeMillis()) {
				if(my >= 20 && my <= 36 && Game.backClicked) {
					// Pressed Back Button
					buttonTimer = System.currentTimeMillis() + 200;
					/*
					if(Game.selectorBPMP < -3)
						Game.selectorBPMP = 0;
					else if(Game.selectorBPMP > 2)
						Game.selectorBPMP = 0;
					Game.selectorButtonPosition = 0;
					*/
					if(Game.State == Game.STATE.CONTROLS) {
						if(Game.selectorBPMP < -3)
							Game.selectorBPMP = 0;
						else if(Game.selectorBPMP > 2)
							Game.selectorBPMP = 0;
						Game.selectorButtonPosition = 0;
						Game.State = Game.STATE.SETTINGS;
					}
					else if(Game.State == Game.STATE.TRACKLIST) {
						if(Game.selectorBPMP < -3)
							Game.selectorBPMP = 0;
						else if(Game.selectorBPMP > 2)
							Game.selectorBPMP = 0;
						Game.selectorButtonPosition = 0;
						Game.State = Game.STATE.SETTINGS;
					}
					else if(!Game.backToGameOver && Game.State != Game.STATE.SET_SCORE) {
						if(!Game.helpLines.isEmpty()) 
							Game.helpLines.clear();
						if(Game.selectorBPMP < -3)
							Game.selectorBPMP = 0;
						else if(Game.selectorBPMP > 2)
							Game.selectorBPMP = 0;
						/*
						if(Game.State == Game.STATE.LEADERBOARD)
							Game.selectorButtonPosition = -3;
						else if(Game.State == Game.STATE.SETTINGS)
							Game.selectorButtonPosition = -2;
						else if(Game.State == Game.STATE.SHOP)
							Game.selectorButtonPosition = -1;
						else*/
							Game.selectorButtonPosition = 0;
						Game.State = Game.STATE.MENU;
					}
					else {
						if(Game.selectorBPMP < -2)
							Game.selectorBPMP = 0;
						else if(Game.selectorBPMP > 2)
							Game.selectorBPMP = 0;
						Game.selectorButtonPosition = 0;
						Game.State = Game.STATE.GAMEOVER;
						if(Game.gameControllerInUse) 
							Game.keysAreInUse = true;
					}
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
					Game.State == Game.STATE.TRANSITION_ITEM || Game.State == Game.STATE.TRANSITION_WIN || Game.State == Game.STATE.CREDITS) && buttonTimer < System.currentTimeMillis()) {
				if(my >= Game.HEIGHT-32 && my <= Game.HEIGHT+32 && Game.askToSkipSequence && Game.skipClicked) {
					// Pressed Skip
					Game.skipSequence = true;
					if(Game.State != Game.STATE.CREDITS) {
						if(Game.sceneSkipCount < 1)
							Game.sceneSkipCount++;
						else {
							try {
								LeaderboardController.writeToSettings("skipAnimations", "true");
							} catch (IOException e1) {
							}
							Game.skipAnimations = true;
						}
					}
					buttonTimer = System.currentTimeMillis() + 200;
					if(Game.smb3OpenSoundLoop.clipIsActive())
						Game.smb3OpenSoundLoop.stop();
					Game.smb3OpenSoundLoop.play();
				}
			}
			if(Game.State == Game.STATE.SHOP) {
				if(Game.allUnlockedScreen) {
					Game.allUnlockedScreen = false;
					reset();
					return;
				}
				//ArrowL Buttons
				if(mx >=  Game.WIDTH - 48 && mx <= Game.WIDTH - 32 && Game.State == Game.STATE.SHOP && buttonTimer < System.currentTimeMillis()) {
					if(my >= 120 && my <= 152 && Game.arrowL1Clicked) {
						//ArrowL1 Functionality
						buttonTimer = System.currentTimeMillis() + 200;
						if(Game.characterSkinPosition == 5)
							Game.characterSkinPosition-=2;
						else if(Game.characterSkinPosition > 0)
							Game.characterSkinPosition--;
						else if(Game.currentlySelectedCharacterSkin == 4)
							Game.characterSkinPosition = 4;
						else if(Game.currentlySelectedCharacterSkin == 5) 
							Game.characterSkinPosition = 5;
						else
							Game.characterSkinPosition = 3;//Set to Max Skins
						if(Game.currentlySelectedCharacterSkin == 5 && Game.characterSkinPosition == 5)
							Game.skinNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.characterSkinPosition)), 10, 10);
						else
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
							Game.trackPosition = 7;//Set to Max Tracks
						Game.trackNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.trackPosition+1)), 10, 10);
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
					}else if(my >= 320 && my <= 352 && Game.arrowL3Clicked) {
						//ArrowL3 Functionality
						buttonTimer = System.currentTimeMillis() + 200;
						if(Game.fireballPosition == 5)
							Game.fireballPosition-=2;
						else if(Game.fireballPosition > 0)
							Game.fireballPosition--;
						else if(Game.currentlySelectedFireball == 4)
							Game.fireballPosition = 4;
						else if(Game.currentlySelectedFireball == 5) {
							Game.fireballPosition = 5;
							Game.skinNumber = null;
						}
						else
							Game.fireballPosition = 3;//Set to Max Fireballs
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
							Game.itemPosition = 6;//Set to Max Items
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
						if(Game.characterSkinPosition == 3 && Game.currentlySelectedCharacterSkin == 4)//Max Skins
							Game.characterSkinPosition = 4;
						else if(Game.characterSkinPosition == 3 && Game.currentlySelectedCharacterSkin == 5) 
							Game.characterSkinPosition = 5;
						else if (Game.characterSkinPosition == 3 || Game.characterSkinPosition == 4 || Game.characterSkinPosition == 5)
							Game.characterSkinPosition = 0;
						else {
							Game.characterSkinPosition++;
						}
						if(Game.currentlySelectedCharacterSkin == 5 && Game.characterSkinPosition == 5)
							Game.skinNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.characterSkinPosition)), 10, 10);
						else
							Game.skinNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.characterSkinPosition+1)), 10, 10);
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
					}else if(my >= 220 && my <= 252 && Game.arrowR2Clicked) {
						//ArrowR2 Functionality
						buttonTimer = System.currentTimeMillis() + 200;
						if(Game.trackPosition == 7)//Max Tracks
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
						if(Game.fireballPosition == 3 && Game.currentlySelectedFireball == 4)//Max Fireballs
							Game.fireballPosition = 4;
						else if(Game.fireballPosition == 3 && Game.currentlySelectedFireball == 5) {
							Game.fireballPosition = 5;
							Game.skinNumber = null;
						}
						else if(Game.fireballPosition == 3 || Game.fireballPosition == 4 || Game.fireballPosition == 5)
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
						if(Game.itemPosition == 6)//Max Items
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
							if(Game.totalPoints >= 1000){
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
								Game.totalPoints -= 1000;
								Game.starExplode = true;
								Game.mx = mx;
								Game.my = my;
								Game.checkIfAllUnlocked();
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
							if(Game.totalPoints >= 10000){
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
								Game.totalPoints -= 10000;
								Game.starExplode = true;
								Game.mx = mx;
								Game.my = my;
								Game.checkIfAllUnlocked();
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
							if(Game.totalPoints >= 100000){
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
								Game.totalPoints -= 100000;
								Game.starExplode = true;
								Game.mx = mx;
								Game.my = my;
								Game.checkIfAllUnlocked();
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
						switch(Game.trackPosition) {
						case 4:
							if(Game.totalPoints >= 1000){
								Game.track4Unlocked = true;
								//Game.settingsSetup = false;
								Game.writeOnceToSettings = true;
								Game.writeOnceProperty = "gameTrack5Set";
								Game.writeOnceString = "true";
								Game.writeOnceToSettingswithPoints = true;
								Game.writeOnceUnlock = "track4Unlocked";
								Game.trackPosition = 4;
								Game.currentlySelectedTrack = 4;
								Game.currentTrackLocked = false;
								Game.skinNumber = null;
								Game.totalPoints -= 1000;
								Game.starExplode = true;
								Game.track4Unlocked = true;
								Game.gameTrack5Set = true;
								int randomIPlus = 0;
								if(Game.track4Unlocked)
									randomIPlus++;
								if(Game.track5Unlocked)
									randomIPlus++;
								if(Game.track6Unlocked)
									randomIPlus++;
								Random rand = new Random();
								Game.soundRandomizer = rand.nextInt(7) + (-3+randomIPlus);
								Game.soundRandomizer = VolumeSlider.gameTrackSetup(Game.soundRandomizer);
								Game.mx = mx;
								Game.my = my;
								Game.checkIfAllUnlocked();
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
						case 5:
							if(Game.totalPoints >= 10000){
								Game.track5Unlocked = true;
								//Game.settingsSetup = false;
								Game.writeOnceToSettings = true;
								Game.writeOnceProperty = "gameTrack6Set";
								Game.writeOnceString = "true";
								Game.writeOnceToSettingswithPoints = true;
								Game.writeOnceUnlock = "track5Unlocked";
								Game.trackPosition = 5;
								Game.currentlySelectedTrack = 5;
								Game.currentTrackLocked = false;
								Game.skinNumber = null;
								Game.totalPoints -= 10000;
								Game.starExplode = true;
								Game.track5Unlocked = true;
								Game.gameTrack6Set = true;
								int randomIPlus = 0;
								if(Game.track4Unlocked)
									randomIPlus++;
								if(Game.track5Unlocked)
									randomIPlus++;
								if(Game.track6Unlocked)
									randomIPlus++;
								Random rand = new Random();
								Game.soundRandomizer = rand.nextInt(7) + (-3+randomIPlus);
								Game.soundRandomizer = VolumeSlider.gameTrackSetup(Game.soundRandomizer);
								Game.mx = mx;
								Game.my = my;
								Game.checkIfAllUnlocked();
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
						case 6:
							if(Game.totalPoints >= 100000){
								Game.track6Unlocked = true;
								//Game.settingsSetup = false;
								Game.writeOnceToSettings = true;
								Game.writeOnceProperty = "gameTrack7Set";
								Game.writeOnceString = "true";
								Game.writeOnceToSettingswithPoints = true;
								Game.writeOnceUnlock = "track6Unlocked";
								Game.trackPosition = 6;
								Game.currentlySelectedTrack = 6;
								Game.currentTrackLocked = false;
								Game.skinNumber = null;
								Game.totalPoints -= 100000;
								Game.starExplode = true;
								Game.track6Unlocked = true;
								Game.gameTrack7Set = true;
								int randomIPlus = 0;
								if(Game.track4Unlocked)
									randomIPlus++;
								if(Game.track5Unlocked)
									randomIPlus++;
								if(Game.track6Unlocked)
									randomIPlus++;
								Random rand = new Random();
								Game.soundRandomizer = rand.nextInt(7) + (-3+randomIPlus);
								Game.soundRandomizer = VolumeSlider.gameTrackSetup(Game.soundRandomizer);
								Game.mx = mx;
								Game.my = my;
								Game.checkIfAllUnlocked();
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
						default:
							break;
						}
					}else if(my >= 327 && my <= 344 && Game.buy3Clicked && Game.currentFireballLocked == true) {
						buttonTimer = System.currentTimeMillis() + 200;
						switch(Game.fireballPosition){
						case 1:
							if(Game.totalPoints >= 1000){
								Game.fireball1Unlocked = true;
								//Game.settingsSetup = false;
								Game.writeOnceToSettings = true;
								Game.writeOnceProperty = "currentlySelectedFireball";
								Game.writeOnceString = Integer.toString(Game.fireballPosition);
								Game.writeOnceToSettingswithPoints = true;
								Game.writeOnceUnlock = "fireball1Unlocked";
								Game.fireballPosition = 1;
								Game.currentlySelectedFireball = 1;
								Game.currentFireballLocked = false;
								Game.skinNumber = null;
								Game.totalPoints -= 1000;
								Game.starExplode = true;
								Game.mx = mx;
								Game.my = my;
								Game.checkIfAllUnlocked();
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
							if(Game.totalPoints >= 10000){
								Game.fireball2Unlocked = true;
								//Game.settingsSetup = false;
								Game.writeOnceToSettings = true;
								Game.writeOnceProperty = "currentlySelectedFireball";
								Game.writeOnceString = Integer.toString(Game.fireballPosition);
								Game.writeOnceToSettingswithPoints = true;
								Game.writeOnceUnlock = "fireball2Unlocked";
								Game.fireballPosition = 2;
								Game.currentlySelectedFireball = 2;
								Game.currentFireballLocked = false;
								Game.skinNumber = null;
								Game.totalPoints -= 10000;
								Game.starExplode = true;
								Game.mx = mx;
								Game.my = my;
								Game.checkIfAllUnlocked();
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
							if(Game.totalPoints >= 100000){
								Game.fireball3Unlocked = true;
								//Game.settingsSetup = false;
								Game.writeOnceToSettings = true;
								Game.writeOnceProperty = "currentlySelectedFireball";
								Game.writeOnceString = Integer.toString(Game.fireballPosition);
								Game.writeOnceToSettingswithPoints = true;
								Game.writeOnceUnlock = "fireball3Unlocked";
								Game.fireballPosition = 3;
								Game.currentlySelectedFireball = 3;
								Game.currentFireballLocked = false;
								Game.skinNumber = null;
								Game.totalPoints -= 100000;
								Game.starExplode = true;
								Game.mx = mx;
								Game.my = my;
								Game.checkIfAllUnlocked();
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
						Game.fireballSoundSet();
					}else if(my >= 427 && my <= 444 && Game.buy4Clicked && Game.currentItemLocked == true) {
						buttonTimer = System.currentTimeMillis() + 200;
						switch(Game.itemPosition){
						case 4:
							if(Game.totalPoints >= 1000){
								Game.item4Unlocked = true;
								//Game.settingsSetup = false;
								Game.writeOnceToSettings = true;
								Game.writeOnceProperty = "currentlySelectedItem";
								Game.writeOnceString = Integer.toString(Game.itemPosition);
								Game.writeOnceToSettingswithPoints = true;
								Game.writeOnceUnlock = "item4Unlocked";
								Game.itemPosition = 4;
								Game.currentlySelectedItem = 4;
								Game.currentItemLocked = false;
								Game.skinNumber = null;
								Game.totalPoints -= 1000;
								Game.starExplode = true;
								Game.mx = mx;
								Game.my = my;
								Game.checkIfAllUnlocked();
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
						case 5:
							if(Game.totalPoints >= 10000){
								Game.item5Unlocked = true;
								//Game.settingsSetup = false;
								Game.writeOnceToSettings = true;
								Game.writeOnceProperty = "currentlySelectedItem";
								Game.writeOnceString = Integer.toString(Game.itemPosition);
								Game.writeOnceToSettingswithPoints = true;
								Game.writeOnceUnlock = "item5Unlocked";
								Game.itemPosition = 5;
								Game.currentlySelectedItem = 5;
								Game.currentItemLocked = false;
								Game.skinNumber = null;
								Game.totalPoints -= 10000;
								Game.starExplode = true;
								Game.mx = mx;
								Game.my = my;
								Game.checkIfAllUnlocked();
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
						case 6:
							if(Game.totalPoints >= 100000){
								Game.item6Unlocked = true;
								//Game.settingsSetup = false;
								Game.writeOnceToSettings = true;
								Game.writeOnceProperty = "currentlySelectedItem";
								Game.writeOnceString = Integer.toString(Game.itemPosition);
								Game.writeOnceToSettingswithPoints = true;
								Game.writeOnceUnlock = "item6Unlocked";
								Game.itemPosition = 6;
								Game.currentlySelectedItem = 6;
								Game.currentItemLocked = false;
								Game.skinNumber = null;
								Game.totalPoints -= 100000;
								Game.starExplode = true;
								Game.mx = mx;
								Game.my = my;
								Game.checkIfAllUnlocked();
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
						default:
							break;
						}
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
							else {
								if(Game.smb3Checkmark2SoundLoop.clipIsActive())
									Game.smb3Checkmark2SoundLoop.stop();
								Game.smb3Checkmark2SoundLoop.play();
							}
							Game.currentlySelectedCharacterSkin = Game.characterSkinPosition;
							Game.writeOnceToSettings = true;
							Game.writeOnceProperty = "currentlySelectedCharacterSkin";
							Game.writeOnceString = Integer.toString(Game.characterSkinPosition);
							//Game.settingsSetup = false;
						}
						else {
							if(Game.smwErrorSoundLoop.clipIsActive())
								Game.smwErrorSoundLoop.stop();
							Game.smwErrorSoundLoop.play();
						}
					}else if(my >= 228 && my <= 244 && Game.set2Clicked) {
						buttonTimer = System.currentTimeMillis() + 200;
						if(!Game.currentTrackLocked) {
							boolean b = false;
							String s = "";
							String ss = "";
							switch(Game.trackPosition) {
							case 0:
								s = "gameTrack1Set";
								if(Game.gameTrack1Set) {
									Game.gameTrack1Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack1Set = true;
									ss = "true";
								}
								break;
							case 1:
								s = "gameTrack2Set";
								if(Game.gameTrack2Set) {
									Game.gameTrack2Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack2Set = true;
									ss = "true";
								}
								break;
							case 2:
								s = "gameTrack3Set";
								if(Game.gameTrack3Set) {
									Game.gameTrack3Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack3Set = true;
									ss = "true";
								}
								break;
							case 3:
								s = "gameTrack4Set";
								if(Game.gameTrack4Set) {
									Game.gameTrack4Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack4Set = true;
									ss = "true";
								}
								break;
							case 4:
								s = "gameTrack5Set";
								if(Game.gameTrack5Set || !Game.track4Unlocked) {
									if(!Game.track4Unlocked) {
										if(Game.smwErrorSoundLoop.clipIsActive())
											Game.smwErrorSoundLoop.stop();
										Game.smwErrorSoundLoop.play();
									}
									Game.gameTrack5Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack5Set = true;
									ss = "true";
								}
								break;
							case 5:
								s = "gameTrack6Set";
								if(Game.gameTrack6Set || !Game.track5Unlocked) {
									if(!Game.track5Unlocked) {
										if(Game.smwErrorSoundLoop.clipIsActive())
											Game.smwErrorSoundLoop.stop();
										Game.smwErrorSoundLoop.play();
									}
									Game.gameTrack6Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack6Set = true;
									ss = "true";
								}
								break;
							case 6:
								s = "gameTrack7Set";
								if(Game.gameTrack7Set || !Game.track6Unlocked) {
									if(!Game.track6Unlocked) {
										if(Game.smwErrorSoundLoop.clipIsActive())
											Game.smwErrorSoundLoop.stop();
										Game.smwErrorSoundLoop.play();
									}
									Game.gameTrack7Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack7Set = true;
									ss = "true";
								}
								break;
							case 7:
								s = "gameTrack8Set";
								if(Game.gameTrack8Set) {
									Game.gameTrack8Set = false;
									ss = "false";
								}
								else {
									b = true;
									Game.gameTrack8Set = true;
									ss = "true";
								}
								break;
							default:
								break;
							}
							if(b) {
								if(Game.smb3ItemSoundLoop.clipIsActive())
									Game.smb3ItemSoundLoop.stop();
								Game.smb3ItemSoundLoop.play();
							}
							if(!s.equals("") && !ss.equals("")) {
								try {
									LeaderboardController.writeToSettings(s, ss);
								} catch (IOException e1) {
								}
								if(ss.equals("true") && Game.soundRandomizer == 8 && Game.gameTrackCurrentlyPlaying == -1  && Game.menuTrackCurrentlyPlaying == -1) {
									int randomIPlus = 0;
									if(Game.track4Unlocked)
										randomIPlus++;
									if(Game.track5Unlocked)
										randomIPlus++;
									if(Game.track6Unlocked)
										randomIPlus++;
									Random rand = new Random();
									Game.soundRandomizer = rand.nextInt(7) + (-3+randomIPlus);
									Game.soundRandomizer = VolumeSlider.gameTrackSetup(Game.soundRandomizer);
									//setsetup[VolumeSlider.TrackSetup]
									if(Game.soundRandomizer != -4) {
										Game.gameSoundLoops.get(Game.soundRandomizer).stop();
										Game.gameSoundLoops.get(Game.soundRandomizer).setSoundLoopBoolean(true);
										Game.gameSoundLoops.get(Game.soundRandomizer).setFramePosition(0);
//										Game.gameSoundLoops.get(Game.soundRandomizer).play();
									}
									else
										Game.soundRandomizer = 8;
								}
								else if(ss.equals("false")) {  
									if(Game.gameTrack1Set == false && Game.gameTrack2Set == false && Game.gameTrack3Set == false && 
										Game.gameTrack4Set == false && Game.gameTrack5Set == false && Game.gameTrack6Set == false && 
										Game.gameTrack7Set == false && Game.gameTrack8Set == false && Game.soundRandomizer != 8 &&
										Game.soundRandomizer != -4) 
									Game.soundRandomizer = 8;
									if(Game.smbPopSoundLoop.clipIsActive())
										Game.smbPopSoundLoop.stop();
									Game.smbPopSoundLoop.play();
								}
							}
						}
						else {
							if(Game.smwErrorSoundLoop.clipIsActive())
								Game.smwErrorSoundLoop.stop();
							Game.smwErrorSoundLoop.play();
						}
					}else if(my >= 328 && my <= 344 && Game.set3Clicked) {
						buttonTimer = System.currentTimeMillis() + 200;
						if(!Game.currentFireballLocked) {
							if(Game.currentlySelectedFireball != Game.fireballPosition) {
								if(Game.smb3ItemSoundLoop.clipIsActive())
									Game.smb3ItemSoundLoop.stop();
								Game.smb3ItemSoundLoop.play();
							}
							else {
								if(Game.smb3Checkmark2SoundLoop.clipIsActive())
									Game.smb3Checkmark2SoundLoop.stop();
								Game.smb3Checkmark2SoundLoop.play();
							}
							Game.currentlySelectedFireball = Game.fireballPosition;
							Game.fireballSoundSet();
							Game.writeOnceToSettings = true;
							Game.writeOnceProperty = "currentlySelectedFireball";
							Game.writeOnceString = Integer.toString(Game.fireballPosition);
							//Game.settingsSetup = false;
						}
						else {
							if(Game.smwErrorSoundLoop.clipIsActive())
								Game.smwErrorSoundLoop.stop();
							Game.smwErrorSoundLoop.play();
						}
					}else if(my >= 428 && my <= 444 && Game.set4Clicked) {
						buttonTimer = System.currentTimeMillis() + 200;
//						if(Game.smb3ItemSoundLoop.clipIsActive())
//							Game.smb3ItemSoundLoop.stop();
//						Game.smb3ItemSoundLoop.play();
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
							Game.track4Unlocked = false;
							Game.track5Unlocked = false;
							Game.track6Unlocked = false;
							Game.fireball1Unlocked = false;
							Game.fireball2Unlocked = false;
							Game.fireball3Unlocked = false;
							Game.item4Unlocked = false;
							Game.item5Unlocked = false;
							Game.item6Unlocked = false;
							Game.menuTrack4Unlocked = false;
							Game.menuTrack5Set = false;
							Game.firstTimeBeating = false;
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
							Game.fireballSoundSet();
							if(Game.menuSoundLoopRandomizer == 4 || Game.menuSoundLoopRandomizer == 5) {
								Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).stop();
								Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).setFramePosition(0);
								Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).setSoundLoopBoolean(false);
								Game.menuSoundLoopRandomizer = 2;
//								Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(864007, 1728014);//Seconds.millisec * 44100
								Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(432004, 864007);//Seconds.millisec * 22050
								Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).setSoundLoopBoolean(true);
							}
							if(Game.soundRandomizer == 4 || Game.soundRandomizer == 5 || Game.soundRandomizer == 6 || Game.soundRandomizer == 8) {
								Game.soundRandomizer = 3;
							}
							Game.gameTrack1Set = true;
							Game.gameTrack2Set = true;
							Game.gameTrack3Set = true;
							Game.gameTrack4Set = true;
							Game.gameTrack5Set = false;
							Game.gameTrack6Set = false;
							Game.gameTrack7Set = false;
							Game.gameTrack8Set = true;
							Game.menuTrack1Set = true;
							Game.menuTrack2Set = true;
							Game.menuTrack3Set = true;
							Game.menuTrack4Set = true;
							Game.menuTrack5Set = false;
							Game.skinNumber = null;
//							XInputDevice.a = XInputConstants.XINPUT_GAMEPAD_A;
//							XInputDevice.b = XInputConstants.XINPUT_GAMEPAD_B;
//							XInputDevice.x = XInputConstants.XINPUT_GAMEPAD_X;
//							XInputDevice.y = XInputConstants.XINPUT_GAMEPAD_Y;
//							XInputDevice.back = XInputConstants.XINPUT_GAMEPAD_BACK;
//							XInputDevice.start = XInputConstants.XINPUT_GAMEPAD_START;
//							XInputDevice.lShoulder = XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER;
//							XInputDevice.rShoulder = XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER;
//							XInputDevice.lThumb = XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB;
//							XInputDevice.rThumb = XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB;
//							XInputDevice.guide = XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON;
//							XInputDevice.unknown = XInputConstants.XINPUT_GAMEPAD_UNKNOWN;
//							XInputDevice.up = XInputConstants.XINPUT_GAMEPAD_DPAD_UP;
//							XInputDevice.down = XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN;
//							XInputDevice.left = XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT;
//							XInputDevice.right = XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT;
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
							Game.selectorButtonPosition = Game.selectorBPMP;
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
							Game.selectorButtonPosition = Game.selectorBPMP;
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
							//Reset Stats Functionality
							buttonTimer = System.currentTimeMillis() + 200;
							if(Game.areYouSureBoolean) 
								Game.areYouSureBoolean = false;
							else
								Game.areYouSureBoolean = true;
							if(Game.hudSFX.get(4).clipIsActive())
								Game.hudSFX.get(4).stop();
							Game.hudSFX.get(4).play();
							Game.selectorButtonPosition = Game.selectorBPMP;
						}
					}
					
					//Gamepad Image Button in Settings Menu
					if(mx >=  Game.WIDTH + 178 && mx <= Game.WIDTH + 217 && Game.State == Game.STATE.SETTINGS && buttonTimer < System.currentTimeMillis()) {
						if(my >= 42 && my <= 62 && Game.gamepadImageClicked) {
							//Gamepad Image Functionality
							//if Game.selectorButtonPosition is < or > the available buttons set it to -1
							buttonTimer = System.currentTimeMillis() + 200;
							Game.State = Game.STATE.CONTROLS;
							if(Game.smb3OpenSoundLoop.clipIsActive())
								Game.smb3OpenSoundLoop.stop();
							Game.smb3OpenSoundLoop.play();
						}
					}
					
					//Note Image Button in Settings Menu
					if(mx >=  Game.WIDTH + 268 && mx <= Game.WIDTH + 286 && Game.State == Game.STATE.SETTINGS && buttonTimer < System.currentTimeMillis()) {
						if(my >= 41 && my <= 63 && Game.noteImageClicked) {
							//Note Image Functionality
							//if Game.selectorButtonPosition is < or > the available buttons set it to -1
							buttonTimer = System.currentTimeMillis() + 200;
							if(Game.trackCurrentlyPlaying == false)
								TrackController.reset();
							Game.State = Game.STATE.TRACKLIST;
							if(Game.smb3OpenSoundLoop.clipIsActive())
								Game.smb3OpenSoundLoop.stop();
							Game.smb3OpenSoundLoop.play();
						}
					}
				}
			}
			if(Game.State == STATE.SET_SCORE) {
				//Submit Score Button
				if(mx >=  Game.WIDTH - 51 && mx <= Game.WIDTH + 51 && buttonTimer < System.currentTimeMillis()) {
					if(my >= 300 && my <= 332 && Game.submitClicked) {
						//Submit Functionality
						buttonTimer = System.currentTimeMillis() + 200;
						Game.postLetter = '~';
						if(Game.sendToServer && HUD.score != 0)
							Game.connectingToServer = true;
						if(Game.gameControllerInUse) {
							Game.keysAreInUse = true;
							Game.selectorButtonPosition = 0;
						}
						if(!LeaderboardController.globalList)
							LeaderboardController.resetTrigger = true;
					}
				}
				if(mx >= 304 && mx <= 336 && buttonTimer < System.currentTimeMillis()) {
					if(my >= 410 && my <= 442 && Game.checkMarkClicked) {
						buttonTimer = System.currentTimeMillis() + 200;
						if(Game.sendToServer)
							Game.sendToServer = false;
						else
							Game.sendToServer = true;
						try {
							if(Game.sendToServer)
								LeaderboardController.writeToSettings("sendToServer", "true");
							else
								LeaderboardController.writeToSettings("sendToServer", "false");
						} catch (IOException e1) {
						}
						if(Game.smb3CheckmarkSoundLoop.clipIsActive())
							Game.smb3CheckmarkSoundLoop.stop();
						if(Game.smb3Checkmark2SoundLoop.clipIsActive())
							Game.smb3Checkmark2SoundLoop.stop();
						Game.smb3Checkmark2SoundLoop.play();
					}
				}
			}
			if(Game.State == STATE.CONTROLS && ControlsController.buttonChangeTimer < System.currentTimeMillis()) {
				//Rescan Button
				if(mx >= 489 && mx <= 538 && my >= 65 && my <= 74 && buttonTimer < System.currentTimeMillis()) {
					if(Game.rescanButtonCounter < 10) {
						ReadAllEvents.resettingControllerEnvironment = true;
						DirectAndRawInputEnvironmentPlugin directEnv = new DirectAndRawInputEnvironmentPlugin();
						if(directEnv != null & directEnv.isSupported())
							ControllerEnvironment.setDefaultEnvironment(directEnv);
						ReadAllEvents.controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
						if(Game.smb3Checkmark2SoundLoop.clipIsActive())
							Game.smb3Checkmark2SoundLoop.stop();
						Game.smb3Checkmark2SoundLoop.play();
						Game.rescanButtonCounter++;
					}
					else {
						if(Game.smwErrorSoundLoop.clipIsActive())
							Game.smwErrorSoundLoop.stop();
						Game.smwErrorSoundLoop.play();
					}
				}
				//WASD
				if(mx >=  Game.WIDTH - 51 && mx <= Game.WIDTH - 28 && buttonTimer < System.currentTimeMillis()) {
					//Up WASD Button
					if(my >= 125 && my <= 147 && ControlsController.gamepadButtonHolderClicked[0]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -2;
						buttonTimer = System.currentTimeMillis() + 200;
					}//Down WASD Button
					else if(my >= 170 && my <= 192 && ControlsController.gamepadButtonHolderClicked[1]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -3;
						buttonTimer = System.currentTimeMillis() + 200;
					}//Left WASD Button
					else if(my >= 215 && my <= 237 && ControlsController.gamepadButtonHolderClicked[2]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -4;
						buttonTimer = System.currentTimeMillis() + 200;
					}//Right WASD Button
					else if(my >= 260 && my <= 282 && ControlsController.gamepadButtonHolderClicked[3]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -5;
						buttonTimer = System.currentTimeMillis() + 200;
					}//Shoot WASD Button
					else if(my >= 305 && my <= 327 && ControlsController.gamepadButtonHolderClicked[4]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -6;
						buttonTimer = System.currentTimeMillis() + 200;
					}//Item WASD Button
					else if(my >= 350 && my <= 372 && ControlsController.gamepadButtonHolderClicked[5]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -7;
						buttonTimer = System.currentTimeMillis() + 200;
					}//Pause WASD Button
					else if(my >= 395 && my <= 417 && ControlsController.gamepadButtonHolderClicked[6]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -8;
						buttonTimer = System.currentTimeMillis() + 200;
					}//Cancel WASD Button
					else if(my >= 440 && my <= 462 && ControlsController.gamepadButtonHolderClicked[7]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -9;
						buttonTimer = System.currentTimeMillis() + 200;
					}
				}
				//XDevice
				else if(mx >= 385 && mx <= 408 && buttonTimer < System.currentTimeMillis()) {
					//Up XDevice Button
					if(my >= 125 && my <= 147 && ControlsController.gamepadButtonHolderClicked[8]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -10;
						Game.gameControllerInUse = true;
						Game.keysAreInUse = true;
						Game.keysAreInUseSwitchBack = true;
						Game.gameControllerSwitchBack = true;
						buttonTimer = System.currentTimeMillis() + 200;
					}//Down XDevice Button
					else if(my >= 170 && my <= 192 && ControlsController.gamepadButtonHolderClicked[9]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -11;
						Game.gameControllerInUse = true;
						Game.keysAreInUse = true;
						Game.keysAreInUseSwitchBack = true;
						Game.gameControllerSwitchBack = true;
						buttonTimer = System.currentTimeMillis() + 200;
					}//Left XDevice Button
					else if(my >= 215 && my <= 237 && ControlsController.gamepadButtonHolderClicked[10]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -12;
						Game.gameControllerInUse = true;
						Game.keysAreInUse = true;
						Game.keysAreInUseSwitchBack = true;
						Game.gameControllerSwitchBack = true;
						buttonTimer = System.currentTimeMillis() + 200;
					}//Right XDevice Button
					else if(my >= 260 && my <= 282 && ControlsController.gamepadButtonHolderClicked[11]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -13;
						Game.gameControllerInUse = true;
						Game.keysAreInUse = true;
						Game.keysAreInUseSwitchBack = true;
						Game.gameControllerSwitchBack = true;
						buttonTimer = System.currentTimeMillis() + 200;
					}//Shoot XDevice Button
					else if(my >= 305 && my <= 327 && ControlsController.gamepadButtonHolderClicked[12]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -14;
						Game.gameControllerInUse = true;
						Game.keysAreInUse = true;
						Game.keysAreInUseSwitchBack = true;
						Game.gameControllerSwitchBack = true;
						buttonTimer = System.currentTimeMillis() + 200;
					}//Item XDevice Button
					else if(my >= 350 && my <= 372 && ControlsController.gamepadButtonHolderClicked[13]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -15;
						Game.gameControllerInUse = true;
						Game.keysAreInUse = true;
						Game.keysAreInUseSwitchBack = true;
						Game.gameControllerSwitchBack = true;
						buttonTimer = System.currentTimeMillis() + 200;
					}//Pause XDevice Button
					else if(my >= 395 && my <= 417 && ControlsController.gamepadButtonHolderClicked[14]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -16;
						Game.gameControllerInUse = true;
						Game.keysAreInUse = true;
						Game.keysAreInUseSwitchBack = true;
						Game.gameControllerSwitchBack = true;
						buttonTimer = System.currentTimeMillis() + 200;
					}//Cancel XDevice Button
					else if(my >= 440 && my <= 462 && ControlsController.gamepadButtonHolderClicked[15]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -17;
						Game.gameControllerInUse = true;
						Game.keysAreInUse = true;
						Game.keysAreInUseSwitchBack = true;
						Game.gameControllerSwitchBack = true;
						buttonTimer = System.currentTimeMillis() + 200;
					}
				}
				//Direct Input
				else if(mx >= 501 && mx <= 524 && buttonTimer < System.currentTimeMillis()) {
					//Up Direct Input Button
					if(my >= 125 && my <= 147 && ControlsController.gamepadButtonHolderClicked[16]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -18;
						Game.gameControllerInUse = true;
						Game.gameControllerInUseDI = true;
						Game.keysAreInUse = true;
						Game.keysAreInUseSwitchBack = true;
						Game.gameControllerSwitchBack = true;
						Game.gameControllerSwitchBackDI = true;
						buttonTimer = System.currentTimeMillis() + 200;
					}//Down Direct Input Button
					else if(my >= 170 && my <= 192 && ControlsController.gamepadButtonHolderClicked[17]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -19;
						Game.gameControllerInUse = true;
						Game.gameControllerInUseDI = true;
						Game.keysAreInUse = true;
						Game.keysAreInUseSwitchBack = true;
						Game.gameControllerSwitchBack = true;
						Game.gameControllerSwitchBackDI = true;
						buttonTimer = System.currentTimeMillis() + 200;
					}//Left Direct Input Button
					else if(my >= 215 && my <= 237 && ControlsController.gamepadButtonHolderClicked[18]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -20;
						Game.gameControllerInUse = true;
						Game.gameControllerInUseDI = true;
						Game.keysAreInUse = true;
						Game.keysAreInUseSwitchBack = true;
						Game.gameControllerSwitchBack = true;
						Game.gameControllerSwitchBackDI = true;
						buttonTimer = System.currentTimeMillis() + 200;
					}//Right Direct Input Button
					else if(my >= 260 && my <= 282 && ControlsController.gamepadButtonHolderClicked[19]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -21;
						Game.gameControllerInUse = true;
						Game.gameControllerInUseDI = true;
						Game.keysAreInUse = true;
						Game.keysAreInUseSwitchBack = true;
						Game.gameControllerSwitchBack = true;
						Game.gameControllerSwitchBackDI = true;
						buttonTimer = System.currentTimeMillis() + 200;
					}//Shoot Direct Input Button
					else if(my >= 305 && my <= 327 && ControlsController.gamepadButtonHolderClicked[20]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -22;
						Game.gameControllerInUse = true;
						Game.gameControllerInUseDI = true;
						Game.keysAreInUse = true;
						Game.keysAreInUseSwitchBack = true;
						Game.gameControllerSwitchBack = true;
						Game.gameControllerSwitchBackDI = true;
						buttonTimer = System.currentTimeMillis() + 200;
					}//Item Direct Input Button
					else if(my >= 350 && my <= 372 && ControlsController.gamepadButtonHolderClicked[21]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -23;
						Game.gameControllerInUse = true;
						Game.gameControllerInUseDI = true;
						Game.keysAreInUse = true;
						Game.keysAreInUseSwitchBack = true;
						Game.gameControllerSwitchBack = true;
						Game.gameControllerSwitchBackDI = true;
						buttonTimer = System.currentTimeMillis() + 200;
					}//Pause Direct Input Button
					else if(my >= 395 && my <= 417 && ControlsController.gamepadButtonHolderClicked[22]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -24;
						Game.gameControllerInUse = true;
						Game.gameControllerInUseDI = true;
						Game.keysAreInUse = true;
						Game.keysAreInUseSwitchBack = true;
						Game.gameControllerSwitchBack = true;
						Game.gameControllerSwitchBackDI = true;
						buttonTimer = System.currentTimeMillis() + 200;
					}//Cancel Direct Input Button
					else if(my >= 440 && my <= 462 && ControlsController.gamepadButtonHolderClicked[23]) {
						ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
						ControlsController.buttonToChange = -25;
						Game.gameControllerInUse = true;
						Game.gameControllerInUseDI = true;
						Game.keysAreInUse = true;
						Game.keysAreInUseSwitchBack = true;
						Game.gameControllerSwitchBack = true;
						Game.gameControllerSwitchBackDI = true;
						buttonTimer = System.currentTimeMillis() + 200;
					}
				}
				//Reset Button
				else if(mx >= 564 && mx <= 605 && buttonTimer < System.currentTimeMillis()) {
					if(my >= 443 && my <= 459 && Game.resetClicked) {
						Game.upKey = KeyEvent.VK_W;
						Game.downKey = KeyEvent.VK_S;
						Game.leftKey = KeyEvent.VK_A;
						Game.rightKey = KeyEvent.VK_D;
						Game.shootKey = KeyEvent.VK_SPACE;
						Game.itemKey = KeyEvent.VK_E;
						Game.pauseKey = KeyEvent.VK_ENTER;
						Game.cancelKey = KeyEvent.VK_ESCAPE;
						XInputDevice.upKey = XInputConstants.XINPUT_GAMEPAD_DPAD_UP;
						XInputDevice.downKey = XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN;
						XInputDevice.leftKey = XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT;
						XInputDevice.rightKey = XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT;
						XInputDevice.shootKey = XInputConstants.XINPUT_GAMEPAD_A;
						XInputDevice.itemKey = XInputConstants.XINPUT_GAMEPAD_X;
						XInputDevice.pauseKey = XInputConstants.XINPUT_GAMEPAD_START;
						XInputDevice.cancelKey = XInputConstants.XINPUT_GAMEPAD_B;
						XInputDevice.up = XInputConstants.XINPUT_GAMEPAD_DPAD_UP;
						XInputDevice.down = XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN;
						XInputDevice.left = XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT;
						XInputDevice.right = XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT;
						XInputDevice.a = XInputConstants.XINPUT_GAMEPAD_A;
						XInputDevice.b = XInputConstants.XINPUT_GAMEPAD_B;
						XInputDevice.x = XInputConstants.XINPUT_GAMEPAD_X;
						XInputDevice.y = XInputConstants.XINPUT_GAMEPAD_Y;
						XInputDevice.lShoulder = XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER;
						XInputDevice.rShoulder = XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER;
						XInputDevice.lThumb = XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB;
						XInputDevice.rThumb = XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB;
						XInputDevice.start = XInputConstants.XINPUT_GAMEPAD_START;
						XInputDevice.back = XInputConstants.XINPUT_GAMEPAD_BACK;
						XInputDevice.guide = XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON;
						XInputDevice.unknown = XInputConstants.XINPUT_GAMEPAD_UNKNOWN;
						SwitchedValues.resetControlsfromResetButton();
						Game.writeOnceToSettings = true;
						Game.writeOnceProperty = "upKey";
						Game.writeOnceString = String.valueOf(KeyEvent.VK_W);
						Game.writeMultipleProperty.add("downKey");
						Game.writeMultipleString.add(String.valueOf(KeyEvent.VK_S));
						Game.writeMultipleProperty.add("leftKey");
						Game.writeMultipleString.add(String.valueOf(KeyEvent.VK_A));
						Game.writeMultipleProperty.add("rightKey");
						Game.writeMultipleString.add(String.valueOf(KeyEvent.VK_D));
						Game.writeMultipleProperty.add("shootKey");
						Game.writeMultipleString.add(String.valueOf(KeyEvent.VK_SPACE));
						Game.writeMultipleProperty.add("itemKey");
						Game.writeMultipleString.add(String.valueOf(KeyEvent.VK_E));
						Game.writeMultipleProperty.add("pauseKey");
						Game.writeMultipleString.add(String.valueOf(KeyEvent.VK_ENTER));
						Game.writeMultipleProperty.add("cancelKey");
						Game.writeMultipleString.add(String.valueOf(KeyEvent.VK_ESCAPE));
						Game.writeMultipleProperty.add("upKeyXInput");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_UP));
						Game.writeMultipleProperty.add("downKeyXInput");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN));
						Game.writeMultipleProperty.add("leftKeyXInput");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT));
						Game.writeMultipleProperty.add("rightKeyXInput");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT));
						Game.writeMultipleProperty.add("shootKeyXInput");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_A));
						Game.writeMultipleProperty.add("itemKeyXInput");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_X));
						Game.writeMultipleProperty.add("pauseKeyXInput");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_START));
						Game.writeMultipleProperty.add("cancelKeyXInput");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_B));
						Game.writeMultipleProperty.add("upButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_UP));
						Game.writeMultipleProperty.add("downButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN));
						Game.writeMultipleProperty.add("leftButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT));
						Game.writeMultipleProperty.add("rightButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT));
						Game.writeMultipleProperty.add("upButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_UP));
						Game.writeMultipleProperty.add("downButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN));
						Game.writeMultipleProperty.add("leftButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT));
						Game.writeMultipleProperty.add("rightButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT));
						Game.writeMultipleProperty.add("aButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_A));
						Game.writeMultipleProperty.add("bButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_B));
						Game.writeMultipleProperty.add("xButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_X));
						Game.writeMultipleProperty.add("yButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_Y));
						Game.writeMultipleProperty.add("lShoulderButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER));
						Game.writeMultipleProperty.add("rShoulderButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER));
						Game.writeMultipleProperty.add("lThumbButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB));
						Game.writeMultipleProperty.add("rThumbButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB));
						Game.writeMultipleProperty.add("startButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_START));
						Game.writeMultipleProperty.add("backButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_BACK));
						Game.writeMultipleProperty.add("guideButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON));
						Game.writeMultipleProperty.add("unknownButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_UNKNOWN));
						Game.writeMultipleProperty.add("lTriggerButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER));
						Game.writeMultipleProperty.add("rTriggerButton");
						Game.writeMultipleString.add(String.valueOf(XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER));
						Game.writeMultipleProperty.add("upKeyDirectInput");
						Game.writeMultipleString.add(SwitchedValues.upKey);
						Game.writeMultipleProperty.add("downKeyDirectInput");
						Game.writeMultipleString.add(SwitchedValues.downKey);
						Game.writeMultipleProperty.add("leftKeyDirectInput");
						Game.writeMultipleString.add(SwitchedValues.leftKey);
						Game.writeMultipleProperty.add("rightKeyDirectInput");
						Game.writeMultipleString.add(SwitchedValues.rightKey);
						Game.writeMultipleProperty.add("shootKeyDirectInput");
						Game.writeMultipleString.add(SwitchedValues.shootKey);
						Game.writeMultipleProperty.add("itemKeyDirectInput");
						Game.writeMultipleString.add(SwitchedValues.itemKey);
						Game.writeMultipleProperty.add("pauseKeyDirectInput");
						Game.writeMultipleString.add(SwitchedValues.pauseKey);
						Game.writeMultipleProperty.add("cancelKeyDirectInput");
						Game.writeMultipleString.add(SwitchedValues.cancelKey);
						Game.settingsSetup = false;
						//ControlsController.
						if(Game.smb3TailSoundLoop.clipIsActive())
							Game.smb3TailSoundLoop.stop();
						Game.smb3TailSoundLoop.play();
						buttonTimer = System.currentTimeMillis() + 200;
					}
				}
			}
			else if(Game.State == STATE.TRACKLIST && buttonTimer < System.currentTimeMillis()) {
				//ArrowL Buttons
				if(mx >= Game.WIDTH  - 185 && mx <= Game.WIDTH  - 169) {
					if(my >= 150 && my <= 182 && Game.arrowL1Clicked) {
						//ArrowL1 Functionality
						if(Game.gameTrackPosition > 0)
							Game.gameTrackPosition--;
						else
							Game.gameTrackPosition = 7;//Set to Max Tracks
						Game.gameTrackNumber = Game.makeTransparent(Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.gameTrackPosition+1)), 20, 20));
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
						buttonTimer = System.currentTimeMillis() + 200;
					}else if(my >= 350 && my <= 382 && Game.arrowL2Clicked) {
						//ArrowL2 Functionality
						if(Game.menuTrackPosition > 0)
							Game.menuTrackPosition--;
						else
							Game.menuTrackPosition = 4;//Set to Max Tracks
						Game.menuTrackNumber = Game.makeTransparent(Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.menuTrackPosition+1)), 20, 20));
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
						buttonTimer = System.currentTimeMillis() + 200;
					}
				}
				//ArrowR Buttons
				else if(mx >= Game.WIDTH  - 129 && mx <= Game.WIDTH  - 113) {
					if(my >= 150 && my <= 182 && Game.arrowR1Clicked) {
						//ArrowR1 Functionality
						if(Game.gameTrackPosition == 7)//Max Tracks
							Game.gameTrackPosition = 0;
						else 
							Game.gameTrackPosition++;
						Game.gameTrackNumber = Game.makeTransparent(Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.gameTrackPosition+1)), 20, 20));
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
						buttonTimer = System.currentTimeMillis() + 200;
					}else if(my >= 350 && my <= 382 && Game.arrowR2Clicked) {
						//ArrowR2 Functionality
						if(Game.menuTrackPosition == 4)//Max Tracks
							Game.menuTrackPosition = 0;
						else 
							Game.menuTrackPosition++;
						Game.menuTrackNumber = Game.makeTransparent(Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.menuTrackPosition+1)), 20, 20));
						if(Game.smb3Bump2SoundLoop.clipIsActive())
							Game.smb3Bump2SoundLoop.stop();
						Game.smb3Bump2SoundLoop.play();
						buttonTimer = System.currentTimeMillis() + 200;
					}
				}
				//Play / Stop Buttons
				else if(mx >= 250 && mx <= 266) {
					if(my >= 158 && my <= 174 && Game.trackPlayButton1Clicked) {
						//Play1 Functionality
						boolean b = true;
						switch(Game.gameTrackPosition) {
						case 4:
							if(!Game.track4Unlocked) {
								if(Game.smwErrorSoundLoop.clipIsActive())
									Game.smwErrorSoundLoop.stop();
								Game.smwErrorSoundLoop.play();
								b = false;
							}
							break;
						case 5:
							if(!Game.track5Unlocked) {
								if(Game.smwErrorSoundLoop.clipIsActive())
									Game.smwErrorSoundLoop.stop();
								Game.smwErrorSoundLoop.play();
								b = false;
							}
							break;
						case 6:
							if(!Game.track6Unlocked) {
								if(Game.smwErrorSoundLoop.clipIsActive())
									Game.smwErrorSoundLoop.stop();
								Game.smwErrorSoundLoop.play();
								b = false;
							}
							break;
						default:
							break;
						}
						if(b)
							TrackController.readyToSwitch1 = true;
						buttonTimer = System.currentTimeMillis() + 200;
//						if(Game.trackCurrentlyPlaying) {
//							if(Game.menuTrackCurrentlyPlaying == -1 && Game.gameTrackCurrentlyPlaying == -1) {
//								Game.menuTrackCurrentlyPlaying = 0;
//							}
//							else if(Game.menuTrackCurrentlyPlaying != -1){
//								//TrackController.trackSetup = false;
//								Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).stop();
//								Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).setFramePosition(0);
//								Game.trackCurrentlyPlaying = true;
//								Game.gameTrackCurrentlyPlaying = Game.gameTrackPosition;
//								Game.menuTrackCurrentlyPlaying = -1;
//							}
//							else {
//								Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).stop();
//								Game.trackCurrentlyPlaying = false;
//								TrackController.gameTrackPaused = true;
//								TrackController.menuTrackPaused = false;
//							}
//						}
//						else {
//							if(TrackController.gameTrackPaused) {
//								Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).continuePlaying();
//								TrackController.gameTrackPaused = false;
//								TrackController.menuTrackPaused = false;
//								Game.trackCurrentlyPlaying = true;
//							}
//							else {
//								if(TrackController.menuTrackPaused) {
//									Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).setFramePosition(0);
//									TrackController.menuTrackPaused = false;
//								}
//								TrackController.trackSetup = false;
//								Game.trackCurrentlyPlaying = true;
//								Game.gameTrackCurrentlyPlaying = Game.gameTrackPosition;
//								Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).play();
//								Game.menuTrackCurrentlyPlaying = -1;
//							}
//						}
					}else if(my >= 358 && my <= 374 && Game.trackPlayButton2Clicked) {
						//Play2 Functionality

						boolean b = true;
						switch(Game.menuTrackPosition) {

						case 4:
							if(!Game.menuTrack4Unlocked) {
								if(Game.smwErrorSoundLoop.clipIsActive())
									Game.smwErrorSoundLoop.stop();
								Game.smwErrorSoundLoop.play();
								b = false;
							}
							break;
						default:
							break;
						}
						if(b)
							TrackController.readyToSwitch2 = true;
						buttonTimer = System.currentTimeMillis() + 200;
//						if(Game.trackCurrentlyPlaying) {
//							if(Game.menuTrackCurrentlyPlaying == -1 && Game.gameTrackCurrentlyPlaying == -1) {
//								Game.menuTrackCurrentlyPlaying = 0;
//							}
//							else if(Game.menuTrackCurrentlyPlaying != -1){
//								Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).stop();
//								Game.trackCurrentlyPlaying = false;
//								TrackController.menuTrackPaused = true;
//								TrackController.gameTrackPaused = false;
//							}
//							else {
//								//TrackController.trackSetup = false;
//								Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).stop();
//								Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).setFramePosition(0);
//								Game.trackCurrentlyPlaying = true;
//								Game.menuTrackCurrentlyPlaying = Game.menuTrackPosition;
//								Game.gameTrackCurrentlyPlaying = -1;
//							}
//						}
//						else {
//							if(TrackController.menuTrackPaused) {
//								Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).continuePlaying();
//								TrackController.menuTrackPaused = false;
//								TrackController.gameTrackPaused = false;
//								Game.trackCurrentlyPlaying = true;
//							}
//							else {
//								if(TrackController.gameTrackPaused) {
//									Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).setFramePosition(0);
//									TrackController.gameTrackPaused = false;
//								}
//								TrackController.trackSetup = false;
//								Game.trackCurrentlyPlaying = true;
//								TrackController.gameTrackPaused = false;
//								Game.menuTrackCurrentlyPlaying = Game.menuTrackPosition;
//								Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).play();
//								Game.gameTrackCurrentlyPlaying = -1;
//							}
//						}
					}
				}
				//Pause Buttons [Now stop button]
				else if(mx >= 280 && mx <= 296) {
					if(my >= 158 && my <= 174 && Game.trackPauseButton1Clicked) {
						//Pause1 Functionality
						buttonTimer = System.currentTimeMillis() + 200;
						if(Game.gameTrackCurrentlyPlaying != -1) {// && Game.gameTrackCurrentlyPlaying == Game.gameTrackPosition) {
							TrackController.trackSetup = false;
							Game.trackCurrentlyPlaying = false;
							Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).stop();
							Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).setFramePosition(0);
						}
						else
							Game.gameSoundLoops.get(Game.gameTrackPosition).setFramePosition(0);
						if(Game.smb3Checkmark2SoundLoop.clipIsActive())
							Game.smb3Checkmark2SoundLoop.stop();
						Game.smb3Checkmark2SoundLoop.play();
					}else if(my >= 358 && my <= 374 && Game.trackPauseButton2Clicked) {
						//Pause2 Functionality
						buttonTimer = System.currentTimeMillis() + 200;
						if(Game.trackCurrentlyPlaying && Game.menuTrackCurrentlyPlaying != -1) { //&& Game.menuTrackCurrentlyPlaying == Game.menuTrackPosition) {
							TrackController.trackSetup = false;
							Game.trackCurrentlyPlaying = false;
							Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).stop();
							Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).setFramePosition(0);
						}
						else if(Game.menuTrackCurrentlyPlaying != -1)//Game.menuTrackPosition != Game.menuSoundLoopRandomizer)
							Game.menuSoundLoops.get(Game.menuTrackPosition).setFramePosition(0);
						if(Game.smb3Checkmark2SoundLoop.clipIsActive())
							Game.smb3Checkmark2SoundLoop.stop();
						Game.smb3Checkmark2SoundLoop.play();
					}
				}
				//Set Buttons
				else if(mx >= Game.WIDTH  + 18 && mx <= Game.WIDTH  + 42) {
					if(my >= 158 && my <= 174 && Game.set1Clicked) {
						buttonTimer = System.currentTimeMillis() + 200;
						String s = "";
						String ss = "";
						switch(Game.gameTrackPosition) {
						case 0:
							s = "gameTrack1Set";
							if(Game.gameTrack1Set) {
								Game.gameTrack1Set = false;
								ss = "false";
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.gameTrack1Set = true;
								ss = "true";
							}
							break;
						case 1:
							s = "gameTrack2Set";
							if(Game.gameTrack2Set) {
								Game.gameTrack2Set = false;
								ss = "false";
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.gameTrack2Set = true;
								ss = "true";
							}
							break;
						case 2:
							s = "gameTrack3Set";
							if(Game.gameTrack3Set) {
								Game.gameTrack3Set = false;
								ss = "false";
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.gameTrack3Set = true;
								ss = "true";
							}
							break;
						case 3:
							s = "gameTrack4Set";
							if(Game.gameTrack4Set) {
								Game.gameTrack4Set = false;
								ss = "false";
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.gameTrack4Set = true;
								ss = "true";
							}
							break;
						case 4:
							s = "gameTrack5Set";
							if(Game.gameTrack5Set || !Game.track4Unlocked) {
								if(!Game.track4Unlocked) {
									if(Game.smwErrorSoundLoop.clipIsActive())
										Game.smwErrorSoundLoop.stop();
									Game.smwErrorSoundLoop.play();
								}
								else {
									if(Game.smbPopSoundLoop.clipIsActive())
										Game.smbPopSoundLoop.stop();
									Game.smbPopSoundLoop.play();
								}
								Game.gameTrack5Set = false;
								ss = "false";
							}
							else {
								Game.gameTrack5Set = true;
								ss = "true";
							}
							break;
						case 5:
							s = "gameTrack6Set";
							if(Game.gameTrack6Set || !Game.track5Unlocked) {
								if(!Game.track5Unlocked) {
									if(Game.smwErrorSoundLoop.clipIsActive())
										Game.smwErrorSoundLoop.stop();
									Game.smwErrorSoundLoop.play();
								}
								else {
									if(Game.smbPopSoundLoop.clipIsActive())
										Game.smbPopSoundLoop.stop();
									Game.smbPopSoundLoop.play();
								}
								Game.gameTrack6Set = false;
								ss = "false";
							}
							else {
								Game.gameTrack6Set = true;
								ss = "true";
							}
							break;
						case 6:
							s = "gameTrack7Set";
							if(Game.gameTrack7Set || !Game.track6Unlocked) {
								if(!Game.track6Unlocked) {
									if(Game.smwErrorSoundLoop.clipIsActive())
										Game.smwErrorSoundLoop.stop();
									Game.smwErrorSoundLoop.play();
								}
								else {
									if(Game.smbPopSoundLoop.clipIsActive())
										Game.smbPopSoundLoop.stop();
									Game.smbPopSoundLoop.play();
								}
								Game.gameTrack7Set = false;
								ss = "false";
							}
							else {
								Game.gameTrack7Set = true;
								ss = "true";
							}
							break;
						case 7:
							s = "gameTrack8Set";
							if(Game.gameTrack8Set) {
								Game.gameTrack8Set = false;
								ss = "false";
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.gameTrack8Set = true;
								ss = "true";
							}
							break;
						default:
							break;
						}
						if(ss.equals("true")) {
							if(Game.smb3ItemSoundLoop.clipIsActive())
								Game.smb3ItemSoundLoop.stop();
							Game.smb3ItemSoundLoop.play();
						}
						if(!s.equals("") && !ss.equals("")) {
							try {
								LeaderboardController.writeToSettings(s, ss);
							} catch (IOException e1) {
							}
							if(ss.equals("true") && Game.soundRandomizer == 8 && Game.gameTrackCurrentlyPlaying == -1  && Game.menuTrackCurrentlyPlaying == -1) {
								int randomIPlus = 0;
								if(Game.track4Unlocked)
									randomIPlus++;
								if(Game.track5Unlocked)
									randomIPlus++;
								if(Game.track6Unlocked)
									randomIPlus++;
								Random rand = new Random();
								Game.soundRandomizer = rand.nextInt(7) + (-3+randomIPlus);
								Game.soundRandomizer = VolumeSlider.gameTrackSetup(Game.soundRandomizer);
								//setsetup[VolumeSlider.TrackSetup]
								if(Game.soundRandomizer != -4) {
									Game.gameSoundLoops.get(Game.soundRandomizer).stop();
									Game.gameSoundLoops.get(Game.soundRandomizer).setSoundLoopBoolean(true);
									Game.gameSoundLoops.get(Game.soundRandomizer).setFramePosition(0);
//									Game.gameSoundLoops.get(Game.soundRandomizer).play();
								}
								else
									Game.soundRandomizer = 8;
							}
							else if(ss.equals("false")) {  
								if(Game.gameTrack1Set == false && Game.gameTrack2Set == false && Game.gameTrack3Set == false && 
									Game.gameTrack4Set == false && Game.gameTrack5Set == false && Game.gameTrack6Set == false && 
									Game.gameTrack7Set == false && Game.gameTrack8Set == false && Game.soundRandomizer != 8 &&
									Game.soundRandomizer != -4) 
								Game.soundRandomizer = 8;
							}
						}
					}else if(my >= 358 && my <= 374 && Game.set2Clicked) {
						//Pause2 Functionality
						buttonTimer = System.currentTimeMillis() + 200;
						String s = "";
						String ss = "";
						switch(Game.menuTrackPosition) {
						case 0:
							s = "menuTrack1Set";
							if(Game.menuTrack1Set) {
								Game.menuTrack1Set = false;
								ss = "false";
								if(Game.menuSoundLoopRandomizer == 0) 
									Game.resetMenuSound();
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.menuTrack1Set = true;
								ss = "true";
							}
							break;
						case 1:
							s = "menuTrack2Set";
							if(Game.menuTrack2Set) {
								Game.menuTrack2Set = false;
								ss = "false";
								if(Game.menuSoundLoopRandomizer == 1) 
									Game.resetMenuSound();
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.menuTrack2Set = true;
								ss = "true";
							}
							break;
						case 2:
							s = "menuTrack3Set";
							if(Game.menuTrack3Set) {
								Game.menuTrack3Set = false;
								ss = "false";
								if(Game.menuSoundLoopRandomizer == 2) 
									Game.resetMenuSound();
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.menuTrack3Set = true;
								ss = "true";
							}
							break;
						case 3:
							s = "menuTrack4Set";
							if(Game.menuTrack4Set) {
								Game.menuTrack4Set = false;
								ss = "false";
								if(Game.menuSoundLoopRandomizer == 3) 
									Game.resetMenuSound();
								if(Game.smbPopSoundLoop.clipIsActive())
									Game.smbPopSoundLoop.stop();
								Game.smbPopSoundLoop.play();
							}
							else {
								Game.menuTrack4Set = true;
								ss = "true";
							}
							break;
						case 4:
							s = "menuTrack5Set";
							if(Game.menuTrack5Set || !Game.menuTrack4Unlocked) {
								if(!Game.menuTrack4Unlocked) {
									if(Game.smwErrorSoundLoop.clipIsActive())
										Game.smwErrorSoundLoop.stop();
									Game.smwErrorSoundLoop.play();
								}
								else {
									if(Game.smbPopSoundLoop.clipIsActive())
										Game.smbPopSoundLoop.stop();
									Game.smbPopSoundLoop.play();
								}
								Game.menuTrack5Set = false;
								ss = "false";
								if(Game.menuSoundLoopRandomizer == 4) 
									Game.resetMenuSound();
							}
							else {
								Game.menuTrack5Set = true;
								ss = "true";
							}
							break;
						default:
							break;
						}
						if(ss.equals("true")) {
							if(Game.smb3ItemSoundLoop.clipIsActive())
								Game.smb3ItemSoundLoop.stop();
							Game.smb3ItemSoundLoop.play();
						}
						if(!s.equals("") && !ss.equals("")) {
							try {
								LeaderboardController.writeToSettings(s, ss);
							} catch (IOException e1) {
							}

							if(ss.equals("true") && Game.menuSoundLoopRandomizer == 5 && Game.gameTrackCurrentlyPlaying == -1  && Game.menuTrackCurrentlyPlaying == -1) {
								Random rand = new Random();
								Game.menuSoundLoopRandomizer = rand.nextInt(5);
								VolumeSlider.adjustVolume(Game.menuSoundLoops, Game.gameSoundLoops);
								Game.menuSoundLoopRandomizer = VolumeSlider.menuTrackSetup(Game.menuSoundLoopRandomizer);
								if(Game.menuSoundLoopRandomizer == -2)
									Game.menuSoundLoopRandomizer = 5;
								if(Game.menuSoundLoopRandomizer == 2) 
//									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(864007, 1728014);//Seconds.millisec * 44100
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(432004, 864007);//Seconds.millisec * 22050
								else if(Game.menuSoundLoopRandomizer == 3) 
//									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(464196, 1949705);//Seconds.millisec * 44100
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(232098, 974853);//Seconds.millisec * 22050
								else if(Game.menuSoundLoopRandomizer == 4)
//									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(545561, 2291391);//Seconds.millisec * 44100
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(272781, 1145696);//Seconds.millisec * 22050
								else
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loop();
								Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).setSoundLoopBoolean(true);
							}
							else if(ss.equals("false")) {
								if(Game.gameTrackCurrentlyPlaying == -1  && Game.menuTrackCurrentlyPlaying == -1 && 
									Game.menuTrack1Set == false && Game.menuTrack2Set == false && Game.menuTrack3Set == false && 
									Game.menuTrack4Set == false && Game.menuTrack5Set == false && Game.menuSoundLoopRandomizer != 5 &&
									Game.menuSoundLoopRandomizer != -2) {
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).stop();
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).setFramePosition(0);
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).setSoundLoopBoolean(false);
									Game.menuSoundLoopRandomizer = 5;
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loop();
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).setSoundLoopBoolean(true);
								}
							}
						}
					}
				}
			}else if(Game.State == STATE.LEADERBOARD && buttonTimer < System.currentTimeMillis()) {
				if(Game.areYouSureBoolean) {
					//Yes Button in Settings Menu Game.WIDTH + 18,375,null
					if(mx >=  Game.WIDTH  + 18 && mx <= Game.WIDTH + 18 + 96 && Game.State == Game.STATE.LEADERBOARD && buttonTimer < System.currentTimeMillis()) {
						if(my >= 375 && my <= 439 && Game.yesClicked) {
							//Yes Button Functionality
							buttonTimer = System.currentTimeMillis() + 200;
							LeaderboardController.deleteTrigger = true;
							Game.selectorButtonPosition = 0;
							Game.areYouSureBoolean = false;
							if(Game.smb3TailSoundLoop.clipIsActive())
								Game.smb3TailSoundLoop.stop();
							Game.smb3TailSoundLoop.play();
							Game.selectorButtonPosition = Game.selectorBPMP;
						}
					}
					//No Button in Settings Menu Game.WIDTH + 18,375,null
					if(mx >=  Game.WIDTH - 64 - 18 && mx <= Game.WIDTH - 18 && Game.State == Game.STATE.LEADERBOARD && buttonTimer < System.currentTimeMillis()) {
						if(my >= 375 && my <= 439 && Game.noClicked) {
							//No Button Functionality
							buttonTimer = System.currentTimeMillis() + 200;
							Game.selectorButtonPosition = 0;
							Game.areYouSureBoolean = false;
							if(Game.smb3KickSoundLoop.clipIsActive())
								Game.smb3KickSoundLoop.stop();
							Game.smb3KickSoundLoop.play();
							Game.selectorButtonPosition = Game.selectorBPMP;
						}
					}
				}
				else {
					if(LeaderboardController.globalList) {
						// Local Button
						if (mx >= 40 && mx <= 120) {
							if (my >= 50 && my <= 82 && Game.localClicked) {
								// Pressed Local Button
								LeaderboardController.globalList = false;
								LeaderboardController.listTrigger = true;
								if(Game.smb3ItemSoundLoop.clipIsActive())
									Game.smb3ItemSoundLoop.stop();
								Game.smb3ItemSoundLoop.play();
							}
						}
						// Upload Button
						if (mx >= 537 && mx <= 633) {
							if (my >= 50 && my <= 82 && Game.uploadClicked) {
								// Pressed Upload Button
								if(!LeaderboardController.checkLeaderboard()) {
									if(Game.smwErrorSoundLoop.clipIsActive())
										Game.smwErrorSoundLoop.stop();
									Game.smwErrorSoundLoop.play();
								}
								else {
									String s = "";
									if(Game.scoreEntered && 0 < HUD.score) {
										s=HUD.nameEntered;
										s=s+": "+Long.toString((HUD.score));
										//Game.saveName = s;
									}
									LeaderboardController.uploadTrigger = true;
								}
							}
						}
					}
					else {
						// Global Button
						if (mx >= 537 && mx <= 633) {
							if (my >= 50 && my <= 82 && Game.globalClicked) {
								// Pressed Global Button
								LeaderboardController.globalList = true;
								LeaderboardController.listTrigger = true;
							}
						}
						
						// Reset Leaderboard Button
						if (mx >= 40 && mx <= 122) {
							if (my >= 50 && my <= 82 && Game.resetLeaderboardClicked) {
								// Pressed Global Button
	//							LeaderboardController.deleteTrigger = true;
								if(LeaderboardController.checkLeaderboard()) {
									Game.selectorButtonPosition = 0;
									Game.areYouSureBoolean = true;
									Game.hudSFX.get(4).play();
								}
								else {
									if(Game.smwErrorSoundLoop.clipIsActive())
										Game.smwErrorSoundLoop.stop();
									Game.smwErrorSoundLoop.play();
								}
							}
						}
					}
				}
			}else if(Game.State == STATE.HELP && buttonTimer < System.currentTimeMillis()) {
				if(Game.firstTimeBeating) {
					// Credits Button
					if (mx >= 477 && mx <= 591) {
						if (my >= 38 && my <= 70 && Game.creditsClicked) {
							// Pressed Credits Button
							Game.State = STATE.CREDITS;
							if(Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).clipIsActive()) {
								Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).stop();
//								Game.menuMusicStopped = true;
//								Game.trackCurrentlyPlaying = false;
								
							}
							Game.skipSequence = false;
							Game.askToSkipSequence = false;
							Game.selectorButtonPosition = 0;
						}
					}
				}
			}
		}
		reset();
	}
}
