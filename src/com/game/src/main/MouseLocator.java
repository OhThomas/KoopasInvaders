package com.game.src.main;

import java.awt.Point;

import com.game.src.main.Game.STATE;

public class MouseLocator {

	private Game game;
	
	public MouseLocator(Game game) {
		this.game = game;
	}
	
	public void locateMouse(){
		Point mousePos = game.getMousePosition();
		if(mousePos != null) {
			int mx = (int)mousePos.getX();
			int my = (int)mousePos.getY();
			//if(!game.mouseIsClickedDown) {
				//Play Button Highlighted
			//this.locateMouseButtonPrompt(Game.playHighlighted, Game.playClicked, Game.backOnPlay, Game.mouseIsClickedDown, Game.mouseIsOffClickedObjectAndHeldDown, mx, my, 120, 248, 200, 264);
			//this.locateMouseButtonPrompt(Game.helpHighlighted, Game.helpClicked, Game.backOnHelp, Game.mouseIsClickedDown, Game.mouseIsOffClickedObjectAndHeldDown, mx, my, 120, 248, 300, 364);
			//this.locateMouseButtonPrompt(Game.exitHighlighted, Game.exitClicked, Game.backOnExit, Game.mouseIsClickedDown, Game.mouseIsOffClickedObjectAndHeldDown, mx, my, 120, 248, 400, 464);
			if(!Game.keysAreInUse) {
				//Play Button
				if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.MENU ||
						mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.GAMEOVER) {
					if (my >= 200 && my <= 264) {
						if(Game.selectorButtonPosition != 0) {
							Game.selectorBPMP = Game.selectorButtonPosition;
							Game.selectorButtonPosition = 0;
						}
						
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.playClicked)
							Game.backOnPlay = true;
						else if(!Game.mouseIsClickedDown)
							game.setPlayHighlighted(true);
					}
					else if(game.getPlayHighlighted() == true || Game.playClicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						
						if(Game.playClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						game.setPlayHighlighted(false);
						Game.backOnPlay = false;
					}
					else if(Game.backOnPlay) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						Game.backOnPlay = false;
					}
				}
				else if(game.getPlayHighlighted() == true || Game.playClicked == true) {
					if(Game.selectorButtonPosition != Game.selectorBPMP)
						Game.selectorButtonPosition = Game.selectorBPMP;
					
					if(Game.playClicked == true) 
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					game.setPlayHighlighted(false);
					Game.backOnPlay = false;
				}
				else if(Game.backOnPlay)
					Game.backOnPlay = false;
				
				//Shop Button
				if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.MENU ||
						mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.GAMEOVER) {
					if (my >= 300 && my <= 364) {
						if(Game.selectorButtonPosition != 1) {
							Game.selectorBPMP = Game.selectorButtonPosition;
							Game.selectorButtonPosition = 1;
						}
						
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.shopClicked)
							Game.backOnShop = true;
						else if(!Game.mouseIsClickedDown)
							Game.shopHighlighted = true;
					}
					else if(Game.shopHighlighted == true || Game.shopClicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						
						if(Game.shopClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.shopHighlighted = false;
						Game.backOnShop = false;
					}
					else if(Game.backOnShop) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						Game.backOnShop = false;
					}
				}
				else if(Game.shopHighlighted == true || Game.shopClicked == true) {
					if(Game.selectorButtonPosition != Game.selectorBPMP)
						Game.selectorButtonPosition = Game.selectorBPMP;
					
					if(Game.shopClicked == true) 
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.shopHighlighted = false;
					Game.backOnShop = false;
				}
				else if(Game.backOnShop)
					Game.backOnShop = false;
				
				//Exit Button
				if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.MENU ||
						mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.GAMEOVER) {
					if (my >= 400 && my <= 464) {
						if(Game.selectorButtonPosition != 2) {
							Game.selectorBPMP = Game.selectorButtonPosition;
							Game.selectorButtonPosition = 2;
						}
						
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.exitClicked)
							Game.backOnExit = true;
						else if(!Game.mouseIsClickedDown)
							Game.exitHighlighted = true;
					}
					else if(Game.exitHighlighted == true || Game.exitClicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						
						if(Game.exitClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.exitHighlighted = false;
						Game.backOnExit = false;
					}
					else if(Game.backOnExit) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						Game.backOnExit = false;
					}
				}
				else if(Game.State != Game.STATE.GAME && Game.exitHighlighted == true || 
						Game.State != Game.STATE.GAME && Game.exitClicked == true) {
					if(Game.selectorButtonPosition != Game.selectorBPMP)
						Game.selectorButtonPosition = Game.selectorBPMP;
					
					if(Game.exitClicked == true) 
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.exitHighlighted = false;
					Game.backOnExit = false;
				}
				else if(Game.State != Game.STATE.GAME && Game.backOnExit)
					Game.backOnExit = false;
				
				//Exit Button on Pause Screen
				if (mx >= Game.WIDTH / 2 + 121 && mx <= Game.WIDTH / 2 + 249 && Game.State == Game.STATE.GAME && Game.getUserHasPaused()) {
					if(Game.getUserHasPaused() && my >= 300 && my <= 364) {
						if(Game.selectorButtonPosition != 2) {
							Game.selectorBPMP = Game.selectorButtonPosition;
							Game.selectorButtonPosition = 2;
						}
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.exitClicked)
							Game.backOnExit = true;
						else if(!Game.mouseIsClickedDown)
							Game.exitHighlighted = true;
					}
					else if(Game.exitHighlighted == true || Game.exitClicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.exitClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.exitHighlighted = false;
						Game.backOnExit = false;
					}
					else if(Game.backOnExit) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						Game.backOnExit = false;
					}
				}
				else if(Game.State == Game.STATE.GAME && Game.getUserHasPaused() && Game.exitHighlighted == true || 
						Game.State == Game.STATE.GAME && Game.getUserHasPaused() && Game.exitClicked == true) {
					if(Game.selectorButtonPosition != Game.selectorBPMP)
						Game.selectorButtonPosition = Game.selectorBPMP;
					if(Game.exitClicked == true) 
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.exitHighlighted = false;
					Game.backOnExit = false;
				}
				else if(Game.State == Game.STATE.GAME && Game.getUserHasPaused() && Game.backOnExit)
					Game.backOnExit = false;
				
				//Resume Button
				if (mx >= Game.WIDTH / 2 + 80 && mx <= Game.WIDTH / 2 + 288 && Game.State == Game.STATE.GAME && Game.getUserHasPaused()) {
					if(my >= 100 && my <= 164) {
						if(Game.selectorButtonPosition != 0) {
							Game.selectorBPMP = Game.selectorButtonPosition;
							Game.selectorButtonPosition = 0;
						}
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.resumeClicked)
							Game.backOnResume = true;
						else if(!Game.mouseIsClickedDown)
							Game.resumeHighlighted = true;
					}
					else if(Game.resumeHighlighted == true || Game.resumeClicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.resumeClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.resumeHighlighted = false;
						Game.backOnResume = false;
					}
					else if(Game.backOnResume) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						Game.backOnResume = false;
					}
				}
				else if(Game.resumeHighlighted == true || Game.resumeClicked == true) {
					if(Game.selectorButtonPosition != Game.selectorBPMP)
						Game.selectorButtonPosition = Game.selectorBPMP;
					if(Game.resumeClicked == true) 
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.resumeHighlighted = false;
					Game.backOnResume = false;
				}
				else if(Game.backOnResume)
					Game.backOnResume = false;
				
				//Home Button
				if (mx >= Game.WIDTH / 2 + 114 && mx <= Game.WIDTH / 2 + 254 && Game.State == Game.STATE.GAME && Game.getUserHasPaused() ||
						mx >= Game.WIDTH / 2 + 114 && mx <= Game.WIDTH / 2 + 254 && Game.State == Game.STATE.GAMEOVER) {
					if(my >= 200 && my <= 264 && Game.State == Game.STATE.GAME && Game.getUserHasPaused() || 
							(my >= 300 && my <= 364 && Game.State == Game.STATE.GAMEOVER))  {
						if(Game.selectorButtonPosition != 1) {
							Game.selectorBPMP = Game.selectorButtonPosition;
							Game.selectorButtonPosition = 1;
						}
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.homeClicked)
							Game.backOnHome = true;
						else if(!Game.mouseIsClickedDown)
							Game.homeHighlighted = true;
					}
					else if(Game.homeHighlighted == true || Game.homeClicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.homeClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.homeHighlighted = false;
						Game.backOnHome = false;
					}
					else if(Game.backOnHome) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						Game.backOnHome = false;
					}
				}
				else if(Game.homeHighlighted == true || Game.homeClicked == true) {
					if(Game.selectorButtonPosition != Game.selectorBPMP)
						Game.selectorButtonPosition = Game.selectorBPMP;
					if(Game.homeClicked == true) 
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.homeHighlighted = false;
					Game.backOnHome = false;
				}
				else if(Game.backOnHome)
					Game.backOnHome = false;
				
				//Back Button
				if(mx >= 40 && mx <= 88 && (Game.State == Game.STATE.SET_SCORE || Game.State == Game.STATE.LEADERBOARD || Game.State == Game.STATE.SHOP
						|| Game.State == Game.STATE.HELP || Game.State == Game.STATE.SETTINGS || Game.State == Game.STATE.CONTROLS || Game.State == Game.STATE.TRACKLIST)) {
					if(my >= 20 && my <= 36)   {
						if(Game.selectorButtonPosition != -1) {
							Game.selectorBPMP = Game.selectorButtonPosition;
							Game.selectorButtonPosition = -1;
						}
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.backClicked)
							Game.backOnBack = true;
						else if(!Game.mouseIsClickedDown)
							Game.backHighlighted = true;
					}
					else if(Game.backHighlighted == true || Game.backClicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.backClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.backHighlighted = false;
						Game.backOnBack = false;
					}
					else if(Game.backOnBack) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						Game.backOnBack = false;
					}
				}
				else if(Game.backHighlighted == true || Game.backClicked == true) {
					if(Game.selectorButtonPosition != Game.selectorBPMP)
						Game.selectorButtonPosition = Game.selectorBPMP;
					if(Game.backClicked == true) 
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.backHighlighted = false;
					Game.backOnBack = false;
				}
				else if(Game.backOnBack)
					Game.backOnBack = false;
				
				//Help Button
				if(mx >= 54 && mx <= 86 && (Game.State == Game.STATE.MENU)) {
					if(my >= 20 && my <= 36)   {
						if(Game.selectorButtonPosition != -1) {
							Game.selectorBPMP = Game.selectorButtonPosition;
							Game.selectorButtonPosition = -1;
						}
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.helpClicked)
							Game.backOnHelp = true;
						else if(!Game.mouseIsClickedDown)
							Game.helpHighlighted = true;
					}
					else if(Game.helpHighlighted == true || Game.helpClicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.helpClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.helpHighlighted = false;
						Game.backOnHelp = false;
					}
					else if(Game.backOnHelp) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						Game.backOnHelp = false;
					}
				}
				else if(Game.helpHighlighted == true || Game.helpClicked == true) {
					if(Game.selectorButtonPosition != Game.selectorBPMP)
						Game.selectorButtonPosition = Game.selectorBPMP;
					if(Game.helpClicked == true) 
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.helpHighlighted = false;
					Game.backOnHelp = false;
				}
				else if(Game.backOnHelp)
					Game.backOnHelp = false;
				
				//Settings Button
				if(mx >= 312 && mx <= 376 && (Game.State == Game.STATE.MENU)) {
					if(my >= 20 && my <= 36)   {
						if(Game.selectorButtonPosition != -2) {
							Game.selectorBPMP = Game.selectorButtonPosition;
							Game.selectorButtonPosition = -2;
						}
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.settingsClicked)
							Game.backOnSettings = true;
						else if(!Game.mouseIsClickedDown)
							Game.settingsHighlighted = true;
					}
					else if(Game.settingsHighlighted == true || Game.settingsClicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.settingsClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.settingsHighlighted = false;
						Game.backOnSettings = false;
					}
					else if(Game.backOnSettings) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						Game.backOnSettings = false;
					}
				}
				else if(Game.settingsHighlighted == true || Game.settingsClicked == true) {
					if(Game.selectorButtonPosition != Game.selectorBPMP)
						Game.selectorButtonPosition = Game.selectorBPMP;
					if(Game.settingsClicked == true) 
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.settingsHighlighted = false;
					Game.backOnSettings = false;
				}
				else if(Game.backOnSettings)
					Game.backOnSettings = false;
				
				//Set Score Button
				if(mx >= 40 && mx <= 110 && Game.State == Game.STATE.GAMEOVER) {
					if(my >= 20 && my <= 36){
						if(Game.selectorButtonPosition != -1) {
							Game.selectorBPMP = Game.selectorButtonPosition;
							Game.selectorButtonPosition = -1;
						}
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.setScoreClicked)
							Game.backOnSetScore = true;
						else if(!Game.mouseIsClickedDown)
							Game.setScoreHighlighted = true;
					}
					else if(Game.setScoreHighlighted == true || Game.setScoreClicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.setScoreClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.setScoreHighlighted = false;
						Game.backOnSetScore = false;
					}
					else if(Game.backOnSetScore) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						Game.backOnSetScore = false;
					}
				}
				else if(Game.setScoreHighlighted == true || Game.setScoreClicked == true) {
					if(Game.selectorButtonPosition != Game.selectorBPMP)
						Game.selectorButtonPosition = Game.selectorBPMP;
					if(Game.setScoreClicked == true) 
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.setScoreHighlighted = false;
					Game.backOnSetScore = false;
				}
				else if(Game.backOnSetScore)
					Game.backOnSetScore = false;
				
				//Leaderboard Button
				if(mx >=  Game.WIDTH / 2 + 380 && mx <= Game.WIDTH / 2 + 470 && (Game.State == Game.STATE.GAMEOVER || Game.State == Game.STATE.MENU)) {
					if(my >= 20 && my <= 36) {
						if(Game.selectorButtonPosition != -3) {
							Game.selectorBPMP = Game.selectorButtonPosition;
							if(Game.State == Game.STATE.GAMEOVER)
								Game.selectorButtonPosition = -2;
							else
								Game.selectorButtonPosition = -3;
						}
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.leaderboardClicked)
							Game.backOnLeaderboard = true;
						else if(!Game.mouseIsClickedDown)
							Game.leaderboardHighlighted = true;
					}
					else if(Game.leaderboardHighlighted == true || Game.leaderboardClicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.leaderboardClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.leaderboardHighlighted = false;
						Game.backOnLeaderboard = false;
					}
					else if(Game.backOnLeaderboard) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						Game.backOnLeaderboard = false;
					}
				}
				else if(Game.leaderboardHighlighted == true || Game.leaderboardClicked == true) {
					if(Game.selectorButtonPosition != Game.selectorBPMP)
						Game.selectorButtonPosition = Game.selectorBPMP;
					if(Game.leaderboardClicked == true) 
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.leaderboardHighlighted = false;
					Game.backOnLeaderboard = false;
				}
				else if(Game.backOnLeaderboard)
					Game.backOnLeaderboard = false;
				
				//Skip Button
				if(mx >= Game.WIDTH-73 && mx <= Game.WIDTH+93 && (Game.State == Game.STATE.TRANSITION_ENTRANCE || Game.State == Game.STATE.TRANSITION_ITEM || Game.State == Game.STATE.TRANSITION_WIN)) {
					if(my >= Game.HEIGHT-32 && my <= Game.HEIGHT+32 && Game.askToSkipSequence) {
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.skipClicked)
							Game.backOnSkip = true;
						else if(!Game.mouseIsClickedDown)
							Game.skipHighlighted = true;
					}
					else if(Game.skipHighlighted == true || Game.skipClicked == true) {
						if(Game.skipClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.skipHighlighted = false;
						Game.backOnSkip = false;
					}
					else if(Game.backOnSkip)
						Game.backOnSkip = false;
				}
				else if(Game.skipHighlighted == true || Game.skipClicked == true) {
					if(Game.skipClicked == true) 
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.skipHighlighted = false;
					Game.backOnSkip = false;
				}
				else if(Game.backOnSkip)
					Game.backOnSkip = false;
				if(Game.State == Game.STATE.SHOP) {
					//ArrowL1 Button
					if(mx >=  Game.WIDTH - 48 && mx <= Game.WIDTH - 32 && Game.State == Game.STATE.SHOP) {
						if(my >= 120 && my <= 152) {
							if(Game.selectorButtonPosition != 0) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = 0;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowL1Clicked )
									Game.backOnArrowL1 = true;
							else if(!Game.mouseIsClickedDown)
								Game.arrowL1Highlighted = true;
						}
						else if(Game.arrowL1Highlighted == true || Game.arrowL1Clicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.arrowL1Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.arrowL1Highlighted = false;
							Game.backOnArrowL1 = false;
						}
						else if(Game.backOnArrowL1) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							Game.backOnArrowL1 = false;
						}
					}
					else if(Game.arrowL1Highlighted == true || Game.arrowL1Clicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.arrowL1Clicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.arrowL1Highlighted = false;
						Game.backOnArrowL1 = false;
					}
					else if(Game.backOnArrowL1)
						Game.backOnArrowL1 = false;
					
					//ArrowR1 Button
					if(mx >=  Game.WIDTH + 48 && mx <= Game.WIDTH + 64 && Game.State == Game.STATE.SHOP) {
						if(my >= 120 && my <= 152) {
							if(Game.selectorButtonPosition != -3) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -3;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowR1Clicked )
									Game.backOnArrowR1 = true;
							else if(!Game.mouseIsClickedDown)
								Game.arrowR1Highlighted = true;
						}
						else if(Game.arrowR1Highlighted == true || Game.arrowR1Clicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.arrowR1Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.arrowR1Highlighted = false;
							Game.backOnArrowR1 = false;
						}
						else if(Game.backOnArrowR1) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							Game.backOnArrowR1 = false;
						}
					}
					else if(Game.arrowR1Highlighted == true || Game.arrowR1Clicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.arrowR1Clicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.arrowR1Highlighted = false;
						Game.backOnArrowR1 = false;
					}
					else if(Game.backOnArrowR1)
						Game.backOnArrowR1 = false;
					
					//Buy1 Button
					if(mx >=  Game.WIDTH - 5 && mx <= Game.WIDTH + 24 && Game.State == Game.STATE.SHOP && Game.currentSkinLocked == true) {
						if(my >= 127 && my <= 144 )  {
							if(Game.selectorButtonPosition != -2) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -2;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.buy1Clicked )
									Game.backOnBuy1 = true;
							else if(!Game.mouseIsClickedDown)
								Game.buy1Highlighted = true;
						}
						else if(Game.buy1Highlighted == true || Game.buy1Clicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.buy1Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.buy1Highlighted = false;
							Game.backOnBuy1 = false;
						}
						else if(Game.backOnBuy1) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							Game.backOnBuy1 = false;
						}
					}
					else if(Game.buy1Highlighted == true || Game.buy1Clicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.buy1Clicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.buy1Highlighted = false;
						Game.backOnBuy1 = false;
					}
					else if(Game.backOnBuy1)
						Game.backOnBuy1 = false;
					
					//Set1 Button
					if(mx >=  Game.WIDTH + 108 && mx <= Game.WIDTH + 132 && Game.State == Game.STATE.SHOP) {
						if(my >= 128 && my <= 144 )  {
							if(Game.selectorButtonPosition != -4) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -4;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.set1Clicked )
									Game.backOnSet1 = true;
							else if(!Game.mouseIsClickedDown)
								Game.set1Highlighted = true;
						}
						else if(Game.set1Highlighted == true || Game.set1Clicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.set1Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.set1Highlighted = false;
							Game.backOnSet1 = false;
						}
						else if(Game.backOnSet1) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							Game.backOnSet1 = false;
						}
					}
					else if(Game.set1Highlighted == true || Game.set1Clicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.set1Clicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.set1Highlighted = false;
						Game.backOnSet1 = false;
					}
					else if(Game.backOnSet1)
						Game.backOnSet1 = false;
					
					
					//ArrowL2 Button
					if(mx >=  Game.WIDTH - 48 && mx <= Game.WIDTH - 32 && Game.State == Game.STATE.SHOP) {
						if(my >= 220 && my <= 252) {
							if(Game.selectorButtonPosition != 1) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = 1;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowL2Clicked )
									Game.backOnArrowL2 = true;
							else if(!Game.mouseIsClickedDown)
								Game.arrowL2Highlighted = true;
						}
						else if(Game.arrowL2Highlighted == true || Game.arrowL2Clicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.arrowL2Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.arrowL2Highlighted = false;
							Game.backOnArrowL2 = false;
						}
						else if(Game.backOnArrowL2) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							Game.backOnArrowL2 = false;
						}
					}
					else if(Game.arrowL2Highlighted == true || Game.arrowL2Clicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.arrowL2Clicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.arrowL2Highlighted = false;
						Game.backOnArrowL2 = false;
					}
					else if(Game.backOnArrowL2)
						Game.backOnArrowL2 = false;
					
					//ArrowR2 Button
					if(mx >=  Game.WIDTH + 48 && mx <= Game.WIDTH + 64 && Game.State == Game.STATE.SHOP) {
						if(my >= 220 && my <= 252) {
							if(Game.selectorButtonPosition != -6) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -6;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowR2Clicked )
									Game.backOnArrowR2 = true;
							else if(!Game.mouseIsClickedDown)
								Game.arrowR2Highlighted = true;
						}
						else if(Game.arrowR2Highlighted == true || Game.arrowR2Clicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.arrowR2Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.arrowR2Highlighted = false;
							Game.backOnArrowR2 = false;
						}
						else if(Game.backOnArrowR2) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							Game.backOnArrowR2 = false;
						}
					}
					else if(Game.arrowR2Highlighted == true || Game.arrowR2Clicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.arrowR2Clicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.arrowR2Highlighted = false;
						Game.backOnArrowR2 = false;
					}
					else if(Game.backOnArrowR2)
						Game.backOnArrowR2 = false;
					
					//Buy2 Button
					if(mx >=  Game.WIDTH - 5 && mx <= Game.WIDTH + 24 && Game.State == Game.STATE.SHOP && Game.currentTrackLocked == true) {
						if(my >= 227 && my <= 244 )  {
							if(Game.selectorButtonPosition != -5) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -5;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.buy2Clicked )
									Game.backOnBuy2 = true;
							else if(!Game.mouseIsClickedDown)
								Game.buy2Highlighted = true;
						}
						else if(Game.buy2Highlighted == true || Game.buy2Clicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.buy2Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.buy2Highlighted = false;
							Game.backOnBuy2 = false;
						}
						else if(Game.backOnBuy2) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							Game.backOnBuy2 = false;
						}
					}
					else if(Game.buy2Highlighted == true || Game.buy2Clicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.buy2Clicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.buy2Highlighted = false;
						Game.backOnBuy2 = false;
					}
					else if(Game.backOnBuy2)
						Game.backOnBuy2 = false;
					
					//Set2 Button
					if(mx >=  Game.WIDTH + 108 && mx <= Game.WIDTH + 132 && Game.State == Game.STATE.SHOP) {
						if(my >= 228 && my <= 244 )  {
							if(Game.selectorButtonPosition != -7) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -7;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.set2Clicked )
									Game.backOnSet2 = true;
							else if(!Game.mouseIsClickedDown)
								Game.set2Highlighted = true;
						}
						else if(Game.set2Highlighted == true || Game.set2Clicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.set2Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.set2Highlighted = false;
							Game.backOnSet2 = false;
						}
						else if(Game.backOnSet2) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							Game.backOnSet2 = false;
						}
					}
					else if(Game.set2Highlighted == true || Game.set2Clicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.set2Clicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.set2Highlighted = false;
						Game.backOnSet2 = false;
					}
					else if(Game.backOnSet2)
						Game.backOnSet2 = false;
					
					
					//ArrowL3 Button
					if(mx >=  Game.WIDTH - 48 && mx <= Game.WIDTH - 32 && Game.State == Game.STATE.SHOP) {
						if(my >= 320 && my <= 352) {
							if(Game.selectorButtonPosition != 2) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = 2;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowL3Clicked )
									Game.backOnArrowL3 = true;
							else if(!Game.mouseIsClickedDown)
								Game.arrowL3Highlighted = true;
						}
						else if(Game.arrowL3Highlighted == true || Game.arrowL3Clicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.arrowL3Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.arrowL3Highlighted = false;
							Game.backOnArrowL3 = false;
						}
						else if(Game.backOnArrowL3) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							Game.backOnArrowL3 = false;
						}
					}
					else if(Game.arrowL3Highlighted == true || Game.arrowL3Clicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.arrowL3Clicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.arrowL3Highlighted = false;
						Game.backOnArrowL3 = false;
					}
					else if(Game.backOnArrowL3)
						Game.backOnArrowL3 = false;
					
					//ArrowR3 Button
					if(mx >=  Game.WIDTH + 48 && mx <= Game.WIDTH + 64 && Game.State == Game.STATE.SHOP) {
						if(my >= 320 && my <= 352) {
							if(Game.selectorButtonPosition != -9) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -9;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowR3Clicked )
									Game.backOnArrowR3 = true;
							else if(!Game.mouseIsClickedDown)
								Game.arrowR3Highlighted = true;
						}
						else if(Game.arrowR3Highlighted == true || Game.arrowR3Clicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.arrowR3Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.arrowR3Highlighted = false;
							Game.backOnArrowR3 = false;
						}
						else if(Game.backOnArrowR3) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							Game.backOnArrowR3 = false;
						}
					}
					else if(Game.arrowR3Highlighted == true || Game.arrowR3Clicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.arrowR3Clicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.arrowR3Highlighted = false;
						Game.backOnArrowR3 = false;
					}
					else if(Game.backOnArrowR3)
						Game.backOnArrowR3 = false;
					
					//Buy3 Button
					if(mx >=  Game.WIDTH - 5 && mx <= Game.WIDTH + 24 && Game.State == Game.STATE.SHOP && Game.currentFireballLocked == true) {
						if(my >= 327 && my <= 344 )  {
							if(Game.selectorButtonPosition != -8) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -8;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.buy3Clicked )
									Game.backOnBuy3 = true;
							else if(!Game.mouseIsClickedDown)
								Game.buy3Highlighted = true;
						}
						else if(Game.buy3Highlighted == true || Game.buy3Clicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.buy3Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.buy3Highlighted = false;
							Game.backOnBuy3 = false;
						}
						else if(Game.backOnBuy3) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							Game.backOnBuy3 = false;
						}
					}
					else if(Game.buy3Highlighted == true || Game.buy3Clicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.buy3Clicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.buy3Highlighted = false;
						Game.backOnBuy3 = false;
					}
					else if(Game.backOnBuy3)
						Game.backOnBuy3 = false;
					
					//Set3 Button
					if(mx >=  Game.WIDTH + 108 && mx <= Game.WIDTH + 132 && Game.State == Game.STATE.SHOP) {
						if(my >= 328 && my <= 344 )  {
							if(Game.selectorButtonPosition != -10) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -10;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.set3Clicked )
									Game.backOnSet3 = true;
							else if(!Game.mouseIsClickedDown)
								Game.set3Highlighted = true;
						}
						else if(Game.set3Highlighted == true || Game.set3Clicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.set3Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.set3Highlighted = false;
							Game.backOnSet3 = false;
						}
						else if(Game.backOnSet3) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							Game.backOnSet3 = false;
						}
					}
					else if(Game.set3Highlighted == true || Game.set3Clicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.set3Clicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.set3Highlighted = false;
						Game.backOnSet3 = false;
					}
					else if(Game.backOnSet3)
						Game.backOnSet3 = false;
					
					//ArrowL4 Button
					if(mx >=  Game.WIDTH - 48 && mx <= Game.WIDTH - 32 && Game.State == Game.STATE.SHOP) {
						if(my >= 420 && my <= 452) {
							if(Game.selectorButtonPosition != 3) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = 3;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowL4Clicked )
									Game.backOnArrowL4 = true;
							else if(!Game.mouseIsClickedDown)
								Game.arrowL4Highlighted = true;
						}
						else if(Game.arrowL4Highlighted == true || Game.arrowL4Clicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.arrowL4Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.arrowL4Highlighted = false;
							Game.backOnArrowL4 = false;
						}
						else if(Game.backOnArrowL4) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							Game.backOnArrowL4 = false;
						}
					}
					else if(Game.arrowL4Highlighted == true || Game.arrowL4Clicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.arrowL4Clicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.arrowL4Highlighted = false;
						Game.backOnArrowL4 = false;
					}
					else if(Game.backOnArrowL4)
						Game.backOnArrowL4 = false;
					
					//ArrowR4 Button
					if(mx >=  Game.WIDTH + 48 && mx <= Game.WIDTH + 64 && Game.State == Game.STATE.SHOP) {
						if(my >= 420 && my <= 452) {
							if(Game.selectorButtonPosition != -12) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -12;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowR4Clicked )
									Game.backOnArrowR4 = true;
							else if(!Game.mouseIsClickedDown)
								Game.arrowR4Highlighted = true;
						}
						else if(Game.arrowR4Highlighted == true || Game.arrowR4Clicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.arrowR4Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.arrowR4Highlighted = false;
							Game.backOnArrowR4 = false;
						}
						else if(Game.backOnArrowR4) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							Game.backOnArrowR4 = false;
						}
					}
					else if(Game.arrowR4Highlighted == true || Game.arrowR4Clicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.arrowR4Clicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.arrowR4Highlighted = false;
						Game.backOnArrowR4 = false;
					}
					else if(Game.backOnArrowR4)
						Game.backOnArrowR4 = false;
					
					//Buy4 Button
					if(mx >=  Game.WIDTH - 5 && mx <= Game.WIDTH + 24 && Game.State == Game.STATE.SHOP && Game.currentItemLocked == true) {
						if(my >= 427 && my <= 444 )  {
							if(Game.selectorButtonPosition != -11) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -11;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.buy4Clicked )
									Game.backOnBuy4 = true;
							else if(!Game.mouseIsClickedDown)
								Game.buy4Highlighted = true;
						}
						else if(Game.buy4Highlighted == true || Game.buy4Clicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.buy4Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.buy4Highlighted = false;
							Game.backOnBuy4 = false;
						}
						else if(Game.backOnBuy4) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							Game.backOnBuy4 = false;
						}
					}
					else if(Game.buy4Highlighted == true || Game.buy4Clicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.buy4Clicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.buy4Highlighted = false;
						Game.backOnBuy4 = false;
					}
					else if(Game.backOnBuy4)
						Game.backOnBuy4 = false;
					
					//Set4 Button
					if(mx >=  Game.WIDTH + 108 && mx <= Game.WIDTH + 132 && Game.State == Game.STATE.SHOP) {
						if(my >= 428 && my <= 444 )  {
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.set4Clicked )
									Game.backOnSet4 = true;
							else if(!Game.mouseIsClickedDown)
								Game.set4Highlighted = true;
						}
						else if(Game.set4Highlighted == true || Game.set4Clicked == true) {
							if(Game.set4Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.set4Highlighted = false;
							Game.backOnSet4 = false;
						}
						else if(Game.backOnSet4) {
							Game.backOnSet4 = false;
						}
					}
					else if(Game.set4Highlighted == true || Game.set4Clicked == true) {
						if(Game.set4Clicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.set4Highlighted = false;
						Game.backOnSet4 = false;
					}
					else if(Game.backOnSet4)
						Game.backOnSet4 = false;
				}
				
				if(Game.State == Game.STATE.SETTINGS) {
					if(Game.areYouSureBoolean) {
						if(Game.resetStatsHighlighted || Game.resetStatsClicked || Game.backOnResetStats) {
							Game.resetStatsHighlighted = false;
							Game.resetStatsClicked = false;
							Game.backOnResetStats = false;
						}
							
						//Yes Button in Settings Menu
						if(mx >=  Game.WIDTH  + 18 && mx <= Game.WIDTH + 18 + 96 && Game.State == Game.STATE.SETTINGS) {
							if(my >= 375 && my <= 439) {
								if(Game.selectorButtonPosition != 1) {
									Game.selectorBPMP = Game.selectorButtonPosition;
									Game.selectorButtonPosition = 1;
								}
								if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.yesClicked )
									Game.backOnYes = true;
							else if(!Game.mouseIsClickedDown)
								Game.yesHighlighted = true;
						}
						else if(Game.yesHighlighted == true || Game.yesClicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.yesClicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.yesHighlighted = false;
							Game.backOnYes = false;
						}
						else if(Game.backOnYes) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							Game.backOnYes = false;
						}
					}
					else if(Game.yesHighlighted == true || Game.yesClicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.yesClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.yesHighlighted = false;
						Game.backOnYes = false;
					}
					else if(Game.backOnYes)
						Game.backOnYes = false;
						//No Button in Settings Menu
						if(mx >=  Game.WIDTH - 64 - 18 && mx <= Game.WIDTH - 18 && Game.State == Game.STATE.SETTINGS) {
							if(my >= 375 && my <= 439) {
								if(Game.selectorButtonPosition != 0 && !Game.mouseIsClickedDown) {
									Game.selectorBPMP = Game.selectorButtonPosition;
									Game.selectorButtonPosition = 0;
								}
								if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.noClicked )
									Game.backOnNo = true;
							else if(!Game.mouseIsClickedDown)
								Game.noHighlighted = true;
						}
						else if(Game.noHighlighted == true || Game.noClicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.noClicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.noHighlighted = false;
							Game.backOnNo = false;
						}
						else if(Game.backOnNo) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							Game.backOnNo = false;
						}
					}
					else if(Game.noHighlighted == true || Game.noClicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.noClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.noHighlighted = false;
						Game.backOnNo = false;
					}
					else if(Game.backOnNo)
						Game.backOnNo = false;
				}
					else {
						//ArrowL1 Button in Settings Menu
						if(mx >=  Game.WIDTH  - 125 && mx <= Game.WIDTH - 109 && Game.State == Game.STATE.SETTINGS) {
							if(my >= 121 && my <= 153) {
								if(Game.selectorButtonPosition != 0) {
									Game.selectorBPMP = Game.selectorButtonPosition;
									Game.selectorButtonPosition = 0;
								}
								if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowL1Clicked )
										Game.backOnArrowL1 = true;
								else if(!Game.mouseIsClickedDown)
									Game.arrowL1Highlighted = true;
							}
							else if(Game.arrowL1Highlighted == true || Game.arrowL1Clicked == true) {
								if(Game.selectorButtonPosition != Game.selectorBPMP)
									Game.selectorButtonPosition = Game.selectorBPMP;
								if(Game.arrowL1Clicked == true) 
									Game.mouseIsOffClickedObjectAndHeldDown = true;
								Game.arrowL1Highlighted = false;
								Game.backOnArrowL1 = false;
							}
							else if(Game.backOnArrowL1) {
								if(Game.selectorButtonPosition != Game.selectorBPMP)
									Game.selectorButtonPosition = Game.selectorBPMP;
								Game.backOnArrowL1 = false;
							}
						}
						else if(Game.arrowL1Highlighted == true || Game.arrowL1Clicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.arrowL1Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.arrowL1Highlighted = false;
							Game.backOnArrowL1 = false;
						}
						else if(Game.backOnArrowL1)
							Game.backOnArrowL1 = false;
						
						
						//ArrowR1 Button in Settings Menu
						if(mx >=  Game.WIDTH + 109 && mx <= Game.WIDTH + 125 && Game.State == Game.STATE.SETTINGS) {
							if(my >= 121 && my <= 153) {
								if(Game.selectorButtonPosition != -2) {
									Game.selectorBPMP = Game.selectorButtonPosition;
									Game.selectorButtonPosition = -2;
								}
								if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowR1Clicked )
										Game.backOnArrowR1 = true;
								else if(!Game.mouseIsClickedDown)
									Game.arrowR1Highlighted = true;
							}
							else if(Game.arrowR1Highlighted == true || Game.arrowR1Clicked == true) {
								if(Game.selectorButtonPosition != Game.selectorBPMP)
									Game.selectorButtonPosition = Game.selectorBPMP;
								if(Game.arrowR1Clicked == true) 
									Game.mouseIsOffClickedObjectAndHeldDown = true;
								Game.arrowR1Highlighted = false;
								Game.backOnArrowR1 = false;
							}
							else if(Game.backOnArrowR1) {
								if(Game.selectorButtonPosition != Game.selectorBPMP)
									Game.selectorButtonPosition = Game.selectorBPMP;
								Game.backOnArrowR1 = false;
							}
						}
						else if(Game.arrowR1Highlighted == true || Game.arrowR1Clicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.arrowR1Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.arrowR1Highlighted = false;
							Game.backOnArrowR1 = false;
						}
						else if(Game.backOnArrowR1)
							Game.backOnArrowR1 = false;
						
						//ArrowL2 Button in Settings Menu
						if(mx >=  Game.WIDTH  - 125 && mx <= Game.WIDTH - 109 && Game.State == Game.STATE.SETTINGS) {
							if(my >= 221 && my <= 253) {
								if(Game.selectorButtonPosition != 1) {
									Game.selectorBPMP = Game.selectorButtonPosition;
									Game.selectorButtonPosition = 1;
								}
								if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowL2Clicked )
										Game.backOnArrowL2 = true;
								else if(!Game.mouseIsClickedDown)
									Game.arrowL2Highlighted = true;
							}
							else if(Game.arrowL2Highlighted == true || Game.arrowL2Clicked == true) {
								if(Game.selectorButtonPosition != Game.selectorBPMP)
									Game.selectorButtonPosition = Game.selectorBPMP;
								if(Game.arrowL2Clicked == true) 
									Game.mouseIsOffClickedObjectAndHeldDown = true;
								Game.arrowL2Highlighted = false;
								Game.backOnArrowL2 = false;
							}
							else if(Game.backOnArrowL2) {
								if(Game.selectorButtonPosition != Game.selectorBPMP)
									Game.selectorButtonPosition = Game.selectorBPMP;
								Game.backOnArrowL2 = false;
							}
						}
						else if(Game.arrowL2Highlighted == true || Game.arrowL2Clicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.arrowL2Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.arrowL2Highlighted = false;
							Game.backOnArrowL2 = false;
						}
						else if(Game.backOnArrowL2)
							Game.backOnArrowL2 = false;
						
						
						//ArrowR2 Button in Settings Menu
						if(mx >=  Game.WIDTH + 109 && mx <= Game.WIDTH + 125 && Game.State == Game.STATE.SETTINGS) {
							if(my >= 221 && my <= 253) {
								if(Game.selectorButtonPosition != -3) {
									Game.selectorBPMP = Game.selectorButtonPosition;
									Game.selectorButtonPosition = -3;
								}
								if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowR2Clicked )
										Game.backOnArrowR2 = true;
								else if(!Game.mouseIsClickedDown)
									Game.arrowR2Highlighted = true;
							}
							else if(Game.arrowR2Highlighted == true || Game.arrowR2Clicked == true) {
								if(Game.selectorButtonPosition != Game.selectorBPMP)
									Game.selectorButtonPosition = Game.selectorBPMP;
								if(Game.arrowR2Clicked == true) 
									Game.mouseIsOffClickedObjectAndHeldDown = true;
								Game.arrowR2Highlighted = false;
								Game.backOnArrowR2 = false;
							}
							else if(Game.backOnArrowR2) {
								if(Game.selectorButtonPosition != Game.selectorBPMP)
									Game.selectorButtonPosition = Game.selectorBPMP;
								Game.backOnArrowR2 = false;
							}
						}
						else if(Game.arrowR2Highlighted == true || Game.arrowR2Clicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.arrowR2Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.arrowR2Highlighted = false;
							Game.backOnArrowR2 = false;
						}
						else if(Game.backOnArrowR2)
							Game.backOnArrowR2 = false;
						
						//Checkmark Button in Settings Menu
						if(mx >=  Game.WIDTH - 16 && mx <= Game.WIDTH + 16 && Game.State == Game.STATE.SETTINGS) {
							if(my >= 320 && my <= 352) {
								if(Game.selectorButtonPosition != 2) {
									Game.selectorBPMP = Game.selectorButtonPosition;
									Game.selectorButtonPosition = 2;
								}
								if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.checkMarkClicked )
										Game.backOnCheckMark = true;
								else if(!Game.mouseIsClickedDown)
									Game.checkMarkHighlighted = true;
							}
							else if(Game.checkMarkHighlighted == true || Game.checkMarkClicked == true) {
								if(Game.selectorButtonPosition != Game.selectorBPMP)
									Game.selectorButtonPosition = Game.selectorBPMP;
								if(Game.checkMarkClicked == true) 
									Game.mouseIsOffClickedObjectAndHeldDown = true;
								Game.checkMarkHighlighted = false;
								Game.backOnCheckMark = false;
							}
							else if(Game.backOnCheckMark) {
								if(Game.selectorButtonPosition != Game.selectorBPMP)
									Game.selectorButtonPosition = Game.selectorBPMP;
								Game.backOnCheckMark = false;
							}
						}
						else if(Game.checkMarkHighlighted == true || Game.checkMarkClicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.checkMarkClicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.checkMarkHighlighted = false;
							Game.backOnCheckMark = false;
						}
						else if(Game.backOnCheckMark)
							Game.backOnCheckMark = false;
						
						//Reset Stats Button in Settings Menu
						if(mx >=  Game.WIDTH - 89 && mx <= Game.WIDTH + 89 && Game.State == Game.STATE.SETTINGS) {
							if(my >= 420 && my <= 452) {
								if(Game.selectorButtonPosition != 3) {
									Game.selectorBPMP = Game.selectorButtonPosition;
									Game.selectorButtonPosition = 3;
								}
								if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.resetStatsClicked )
										Game.backOnResetStats = true;
								else if(!Game.mouseIsClickedDown)
									Game.resetStatsHighlighted = true;
							}
							else if(Game.resetStatsHighlighted == true || Game.resetStatsClicked == true) {
								if(Game.selectorButtonPosition != Game.selectorBPMP)
									Game.selectorButtonPosition = Game.selectorBPMP;
								if(Game.resetStatsClicked == true) 
									Game.mouseIsOffClickedObjectAndHeldDown = true;
								Game.resetStatsHighlighted = false;
								Game.backOnResetStats = false;
							}
							else if(Game.backOnResetStats) {
								if(Game.selectorButtonPosition != Game.selectorBPMP)
									Game.selectorButtonPosition = Game.selectorBPMP;
								Game.backOnResetStats = false;
							}
						}
						else if(Game.resetStatsHighlighted == true || Game.resetStatsClicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.resetStatsClicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.resetStatsHighlighted = false;
							Game.backOnResetStats = false;
						}
						else if(Game.backOnResetStats)
							Game.backOnResetStats = false;
						
						//Gamepad Image Button in Settings Menu
						if(mx >=  Game.WIDTH + 178 && mx <= Game.WIDTH + 217 && Game.State == Game.STATE.SETTINGS) {
							if(my >= 42 && my <= 62) {
								if(Game.selectorButtonPosition != -4) {
									Game.selectorBPMP = Game.selectorButtonPosition;
									Game.selectorButtonPosition = -4;
								}
								if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.gamepadImageClicked )
										Game.backOnGamepadImage = true;
								else if(!Game.mouseIsClickedDown)
									Game.gamepadImageHighlighted = true;
							}
							else if(Game.gamepadImageHighlighted == true || Game.gamepadImageClicked == true) {
								if(Game.selectorButtonPosition != Game.selectorBPMP)
									Game.selectorButtonPosition = Game.selectorBPMP;
								if(Game.gamepadImageClicked == true) 
									Game.mouseIsOffClickedObjectAndHeldDown = true;
								Game.gamepadImageHighlighted = false;
								Game.backOnGamepadImage = false;
							}
							else if(Game.backOnGamepadImage) {
								if(Game.selectorButtonPosition != Game.selectorBPMP)
									Game.selectorButtonPosition = Game.selectorBPMP;
								Game.backOnGamepadImage = false;
							}
						}
						else if(Game.gamepadImageHighlighted == true || Game.gamepadImageClicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.gamepadImageClicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.gamepadImageHighlighted = false;
							Game.backOnGamepadImage = false;
						}
						else if(Game.backOnGamepadImage)
							Game.backOnGamepadImage = false;
						
						//Note Image Button in Settings Menu
						if(mx >=  Game.WIDTH + 268 && mx <= Game.WIDTH + 286 && Game.State == Game.STATE.SETTINGS) {
							if(my >= 41 && my <= 63) {
								if(Game.selectorButtonPosition != -5) {
									Game.selectorBPMP = Game.selectorButtonPosition;
									Game.selectorButtonPosition = -5;
								}
								if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.noteImageClicked )
										Game.backOnNoteImage = true;
								else if(!Game.mouseIsClickedDown)
									Game.noteImageHighlighted = true;
							}
							else if(Game.noteImageHighlighted == true || Game.noteImageClicked == true) {
								if(Game.selectorButtonPosition != Game.selectorBPMP)
									Game.selectorButtonPosition = Game.selectorBPMP;
								if(Game.noteImageClicked == true) 
									Game.mouseIsOffClickedObjectAndHeldDown = true;
								Game.noteImageHighlighted = false;
								Game.backOnNoteImage = false;
							}
							else if(Game.backOnNoteImage) {
								if(Game.selectorButtonPosition != Game.selectorBPMP)
									Game.selectorButtonPosition = Game.selectorBPMP;
								Game.backOnNoteImage = false;
							}
						}
						else if(Game.noteImageHighlighted == true || Game.noteImageClicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.noteImageClicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.noteImageHighlighted = false;
							Game.backOnNoteImage = false;
						}
						else if(Game.backOnNoteImage)
							Game.backOnNoteImage = false;
					}
				}
				
				if(Game.State == STATE.SET_SCORE) {
					//Submit Score Button in Set Score Menu
					if(mx >=  Game.WIDTH - 51 && mx <= Game.WIDTH + 51) {
						if(my >= 300 && my <= 332) {
							if(Game.selectorButtonPosition != 1) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = 1;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.submitClicked )
									Game.backOnSubmit = true;
							else if(!Game.mouseIsClickedDown)
								Game.submitHighlighted = true;
						}
						else if(Game.submitHighlighted == true || Game.submitClicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.submitClicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.submitHighlighted = false;
							Game.backOnSubmit = false;
						}
						else if(Game.backOnSubmit) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							Game.backOnSubmit = false;
						}
					}
					else if(Game.submitHighlighted == true || Game.submitClicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.submitClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.submitHighlighted = false;
						Game.backOnSubmit = false;
					}
					else if(Game.backOnSubmit)
						Game.backOnSubmit = false;
				}
				
				if(Game.State == STATE.CONTROLS) {
					//Up WASD Button
					if(mx >=  Game.WIDTH - 51 && mx <= Game.WIDTH - 28) {
						if(my >= 125 && my <= 147) {
							if(Game.selectorButtonPosition != -2) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -2;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[0] )
								ControlsController.backOnGamepadButtonHolder[0] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[0] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[0] == true || ControlsController.gamepadButtonHolderClicked[0] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[0]   == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[0] = false;
							ControlsController.backOnGamepadButtonHolder[0] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[0]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[0] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[0] == true || ControlsController.gamepadButtonHolderClicked[0] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[0] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[0] = false;
						ControlsController.backOnGamepadButtonHolder[0] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[0])
						ControlsController.backOnGamepadButtonHolder[0] = false;
					
					//Down WASD Button
					if(mx >=  Game.WIDTH - 51 && mx <= Game.WIDTH - 28) {
						if(my >= 170 && my <= 192) {
							if(Game.selectorButtonPosition != -3) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -3;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[1])
								ControlsController.backOnGamepadButtonHolder[1] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[1] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[1] == true || ControlsController.gamepadButtonHolderClicked[1] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[1] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[1] = false;
							ControlsController.backOnGamepadButtonHolder[1] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[1]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[1] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[1] == true || ControlsController.gamepadButtonHolderClicked[1] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[1] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[1] = false;
						ControlsController.backOnGamepadButtonHolder[1] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[1])
						ControlsController.backOnGamepadButtonHolder[1] = false;
					
					//Left WASD Button
					if(mx >=  Game.WIDTH - 51 && mx <= Game.WIDTH - 28) {
						if(my >= 215 && my <= 237) {
							if(Game.selectorButtonPosition != -4) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -4;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[2])
								ControlsController.backOnGamepadButtonHolder[2] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[2] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[2] == true || ControlsController.gamepadButtonHolderClicked[2] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[2] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[2] = false;
							ControlsController.backOnGamepadButtonHolder[2] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[2]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[2] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[2] == true || ControlsController.gamepadButtonHolderClicked[2] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[2] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[2] = false;
						ControlsController.backOnGamepadButtonHolder[2] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[2])
						ControlsController.backOnGamepadButtonHolder[2] = false;
					
					//Right WASD Button
					if(mx >=  Game.WIDTH - 51 && mx <= Game.WIDTH - 28) {
						if(my >= 260 && my <= 282) {
							if(Game.selectorButtonPosition != -5) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -5;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[3])
								ControlsController.backOnGamepadButtonHolder[3] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[3] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[3] == true || ControlsController.gamepadButtonHolderClicked[3] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[3] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[3] = false;
							ControlsController.backOnGamepadButtonHolder[3] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[3]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[3] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[3] == true || ControlsController.gamepadButtonHolderClicked[3] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[3] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[3] = false;
						ControlsController.backOnGamepadButtonHolder[3] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[3])
						ControlsController.backOnGamepadButtonHolder[3] = false;
					
					//Shoot WASD Button
					if(mx >=  Game.WIDTH - 51 && mx <= Game.WIDTH - 28) {
						if(my >= 305 && my <= 327) {
							if(Game.selectorButtonPosition != -6) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -6;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[4])
								ControlsController.backOnGamepadButtonHolder[4] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[4] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[4] == true || ControlsController.gamepadButtonHolderClicked[4] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[4] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[4] = false;
							ControlsController.backOnGamepadButtonHolder[4] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[4]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[4] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[4] == true || ControlsController.gamepadButtonHolderClicked[4] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[4] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[4] = false;
						ControlsController.backOnGamepadButtonHolder[4] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[4])
						ControlsController.backOnGamepadButtonHolder[4] = false;
					
					//Item WASD Button
					if(mx >=  Game.WIDTH - 51 && mx <= Game.WIDTH - 28) {
						if(my >= 350 && my <= 372) {
							if(Game.selectorButtonPosition != -7) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -7;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[5])
								ControlsController.backOnGamepadButtonHolder[5] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[5] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[5] == true || ControlsController.gamepadButtonHolderClicked[5] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[5] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[5] = false;
							ControlsController.backOnGamepadButtonHolder[5] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[5]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[5] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[5] == true || ControlsController.gamepadButtonHolderClicked[5] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[5] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[5] = false;
						ControlsController.backOnGamepadButtonHolder[5] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[5])
						ControlsController.backOnGamepadButtonHolder[5] = false;
					
					//Pause WASD Button
					if(mx >=  Game.WIDTH - 51 && mx <= Game.WIDTH - 28) {
						if(my >= 395 && my <= 417) {
							if(Game.selectorButtonPosition != -8) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -8;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[6])
								ControlsController.backOnGamepadButtonHolder[6] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[6] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[6] == true || ControlsController.gamepadButtonHolderClicked[6] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[6] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[6] = false;
							ControlsController.backOnGamepadButtonHolder[6] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[6]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[6] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[6] == true || ControlsController.gamepadButtonHolderClicked[6] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[6] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[6] = false;
						ControlsController.backOnGamepadButtonHolder[6] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[6])
						ControlsController.backOnGamepadButtonHolder[6] = false;
					
					//Cancel WASD Button
					if(mx >=  Game.WIDTH - 51 && mx <= Game.WIDTH - 28) {
						if(my >= 440 && my <= 462) {
							if(Game.selectorButtonPosition != -9) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -9;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[7])
								ControlsController.backOnGamepadButtonHolder[7] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[7] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[7] == true || ControlsController.gamepadButtonHolderClicked[7] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[7] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[7] = false;
							ControlsController.backOnGamepadButtonHolder[7] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[7]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[7] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[7] == true || ControlsController.gamepadButtonHolderClicked[7] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[7] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[7] = false;
						ControlsController.backOnGamepadButtonHolder[7] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[7])
						ControlsController.backOnGamepadButtonHolder[7] = false;
					
					//Up XDevice Button
					if(mx >= 385 && mx <= 408) {
						if(my >= 125 && my <= 147) {
							if(Game.selectorButtonPosition != -10) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -10;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[8])
								ControlsController.backOnGamepadButtonHolder[8] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[8] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[8] == true || ControlsController.gamepadButtonHolderClicked[8] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[8] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[8] = false;
							ControlsController.backOnGamepadButtonHolder[8] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[8]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[8] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[8] == true || ControlsController.gamepadButtonHolderClicked[8] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[8] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[8] = false;
						ControlsController.backOnGamepadButtonHolder[8] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[8])
						ControlsController.backOnGamepadButtonHolder[8] = false;
					
					//Down XDevice Button
					if(mx >= 385 && mx <= 408) {
						if(my >= 170 && my <= 192) {
							if(Game.selectorButtonPosition != -11) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -11;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[9])
								ControlsController.backOnGamepadButtonHolder[9] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[9] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[9] == true || ControlsController.gamepadButtonHolderClicked[9] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[9] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[9] = false;
							ControlsController.backOnGamepadButtonHolder[9] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[9]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[9] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[9] == true || ControlsController.gamepadButtonHolderClicked[9] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[9] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[9] = false;
						ControlsController.backOnGamepadButtonHolder[9] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[9])
						ControlsController.backOnGamepadButtonHolder[9] = false;
					
					//Left XDevice Button
					if(mx >= 385 && mx <= 408) {
						if(my >= 215 && my <= 237) {
							if(Game.selectorButtonPosition != -12) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -12;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[10])
								ControlsController.backOnGamepadButtonHolder[10] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[10] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[10] == true || ControlsController.gamepadButtonHolderClicked[10] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[10] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[10] = false;
							ControlsController.backOnGamepadButtonHolder[10] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[10]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[10] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[10] == true || ControlsController.gamepadButtonHolderClicked[10] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[10] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[10] = false;
						ControlsController.backOnGamepadButtonHolder[10] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[10])
						ControlsController.backOnGamepadButtonHolder[10] = false;
					
					//Right XDevice Button
					if(mx >= 385 && mx <= 408) {
						if(my >= 260 && my <= 282) {
							if(Game.selectorButtonPosition != -13) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -13;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[11])
								ControlsController.backOnGamepadButtonHolder[11] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[11] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[11] == true || ControlsController.gamepadButtonHolderClicked[11] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[11] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[11] = false;
							ControlsController.backOnGamepadButtonHolder[11] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[11]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[11] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[11] == true || ControlsController.gamepadButtonHolderClicked[11] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[11] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[11] = false;
						ControlsController.backOnGamepadButtonHolder[11] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[11])
						ControlsController.backOnGamepadButtonHolder[11] = false;
					
					//Shoot XDevice Button
					if(mx >= 385 && mx <= 408) {
						if(my >= 305 && my <= 327) {
							if(Game.selectorButtonPosition != -14) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -14;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[12])
								ControlsController.backOnGamepadButtonHolder[12] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[12] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[12] == true || ControlsController.gamepadButtonHolderClicked[12] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[12] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[12] = false;
							ControlsController.backOnGamepadButtonHolder[12] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[12]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[12] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[12] == true || ControlsController.gamepadButtonHolderClicked[12] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[12] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[12] = false;
						ControlsController.backOnGamepadButtonHolder[12] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[12])
						ControlsController.backOnGamepadButtonHolder[12] = false;
					
					//Item XDevice Button
					if(mx >= 385 && mx <= 408) {
						if(my >= 350 && my <= 372) {
							if(Game.selectorButtonPosition != -15) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -15;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[13])
								ControlsController.backOnGamepadButtonHolder[13] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[13] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[13] == true || ControlsController.gamepadButtonHolderClicked[13] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[13] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[13] = false;
							ControlsController.backOnGamepadButtonHolder[13] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[13]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[13] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[13] == true || ControlsController.gamepadButtonHolderClicked[13] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[13] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[13] = false;
						ControlsController.backOnGamepadButtonHolder[13] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[13])
						ControlsController.backOnGamepadButtonHolder[13] = false;
					
					//Pause XDevice Button
					if(mx >= 385 && mx <= 408) {
						if(my >= 395 && my <= 417) {
							if(Game.selectorButtonPosition != -16) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -16;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[14])
								ControlsController.backOnGamepadButtonHolder[14] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[14] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[14] == true || ControlsController.gamepadButtonHolderClicked[14] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[14] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[14] = false;
							ControlsController.backOnGamepadButtonHolder[14] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[14]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[14] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[14] == true || ControlsController.gamepadButtonHolderClicked[14] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[14] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[14] = false;
						ControlsController.backOnGamepadButtonHolder[14] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[14])
						ControlsController.backOnGamepadButtonHolder[14] = false;
					
					//Cancel XDevice Button
					if(mx >= 385 && mx <= 408) {
						if(my >= 440 && my <= 462) {
							if(Game.selectorButtonPosition != -17) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -17;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[15])
								ControlsController.backOnGamepadButtonHolder[15] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[15] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[15] == true || ControlsController.gamepadButtonHolderClicked[15] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[15] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[15] = false;
							ControlsController.backOnGamepadButtonHolder[15] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[15]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[15] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[15] == true || ControlsController.gamepadButtonHolderClicked[15] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[15] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[15] = false;
						ControlsController.backOnGamepadButtonHolder[15] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[15])
						ControlsController.backOnGamepadButtonHolder[15] = false;
					
					//Up DirectInput Button
					if(mx >= 501 && mx <= 524) {
						if(my >= 125 && my <= 147) {
							if(Game.selectorButtonPosition != -18) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -18;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[16])
								ControlsController.backOnGamepadButtonHolder[16] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[16] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[16] == true || ControlsController.gamepadButtonHolderClicked[16] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[16] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[16] = false;
							ControlsController.backOnGamepadButtonHolder[16] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[16]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[16] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[16] == true || ControlsController.gamepadButtonHolderClicked[16] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[16] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[16] = false;
						ControlsController.backOnGamepadButtonHolder[16] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[16])
						ControlsController.backOnGamepadButtonHolder[16] = false;
					
					//Down DirectInput Button
					if(mx >= 501 && mx <= 524) {
						if(my >= 170 && my <= 192) {
							if(Game.selectorButtonPosition != -19) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -19;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[17])
								ControlsController.backOnGamepadButtonHolder[17] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[17] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[17] == true || ControlsController.gamepadButtonHolderClicked[17] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[17] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[17] = false;
							ControlsController.backOnGamepadButtonHolder[17] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[17]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[17] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[17] == true || ControlsController.gamepadButtonHolderClicked[17] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[17] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[17] = false;
						ControlsController.backOnGamepadButtonHolder[17] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[17])
						ControlsController.backOnGamepadButtonHolder[17] = false;
					
					//Left DirectInput Button
					if(mx >= 501 && mx <= 524) {
						if(my >= 215 && my <= 237) {
							if(Game.selectorButtonPosition != -20) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -20;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[18])
								ControlsController.backOnGamepadButtonHolder[18] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[18] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[18] == true || ControlsController.gamepadButtonHolderClicked[18] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[18] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[18] = false;
							ControlsController.backOnGamepadButtonHolder[18] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[18]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[18] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[18] == true || ControlsController.gamepadButtonHolderClicked[18] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[18] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[18] = false;
						ControlsController.backOnGamepadButtonHolder[18] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[18])
						ControlsController.backOnGamepadButtonHolder[18] = false;
					
					//Right DirectInput Button
					if(mx >= 501 && mx <= 524) {
						if(my >= 260 && my <= 282) {
							if(Game.selectorButtonPosition != -21) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -21;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[19])
								ControlsController.backOnGamepadButtonHolder[19] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[19] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[19] == true || ControlsController.gamepadButtonHolderClicked[19] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[19] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[19] = false;
							ControlsController.backOnGamepadButtonHolder[19] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[19]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[19] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[19] == true || ControlsController.gamepadButtonHolderClicked[19] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[19] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[19] = false;
						ControlsController.backOnGamepadButtonHolder[19] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[19])
						ControlsController.backOnGamepadButtonHolder[19] = false;
					
					//Shoot DirectInput Button
					if(mx >= 501 && mx <= 524) {
						if(my >= 305 && my <= 327) {
							if(Game.selectorButtonPosition != -22) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -22;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[20])
								ControlsController.backOnGamepadButtonHolder[20] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[20] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[20] == true || ControlsController.gamepadButtonHolderClicked[20] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[20] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[20] = false;
							ControlsController.backOnGamepadButtonHolder[20] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[20]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[20] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[20] == true || ControlsController.gamepadButtonHolderClicked[20] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[20] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[20] = false;
						ControlsController.backOnGamepadButtonHolder[20] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[20])
						ControlsController.backOnGamepadButtonHolder[20] = false;
					
					//Item DirectInput Button
					if(mx >= 501 && mx <= 524) {
						if(my >= 350 && my <= 372) {
							if(Game.selectorButtonPosition != -23) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -23;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[21])
								ControlsController.backOnGamepadButtonHolder[21] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[21] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[21] == true || ControlsController.gamepadButtonHolderClicked[21] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[21] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[21] = false;
							ControlsController.backOnGamepadButtonHolder[21] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[21]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[21] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[21] == true || ControlsController.gamepadButtonHolderClicked[21] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[21] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[21] = false;
						ControlsController.backOnGamepadButtonHolder[21] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[21])
						ControlsController.backOnGamepadButtonHolder[21] = false;
					
					//Pause DirectInput Button
					if(mx >= 501 && mx <= 524) {
						if(my >= 395 && my <= 417) {
							if(Game.selectorButtonPosition != -24) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -24;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[22])
								ControlsController.backOnGamepadButtonHolder[22] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[22] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[22] == true || ControlsController.gamepadButtonHolderClicked[22] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[22] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[22] = false;
							ControlsController.backOnGamepadButtonHolder[22] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[22]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[22] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[22] == true || ControlsController.gamepadButtonHolderClicked[22] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[22] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[22] = false;
						ControlsController.backOnGamepadButtonHolder[22] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[22])
						ControlsController.backOnGamepadButtonHolder[22] = false;
					
					//Cancel DirectInput Button
					if(mx >= 501 && mx <= 524) {
						if(my >= 440 && my <= 462) {
							if(Game.selectorButtonPosition != -25) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -25;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && ControlsController.gamepadButtonHolderClicked[23])
								ControlsController.backOnGamepadButtonHolder[23] = true;
							else if(!Game.mouseIsClickedDown)
								ControlsController.gamepadButtonHolderHighlighted[23] = true;
						}
						else if(ControlsController.gamepadButtonHolderHighlighted[23] == true || ControlsController.gamepadButtonHolderClicked[23] == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(ControlsController.gamepadButtonHolderClicked[23] == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							ControlsController.gamepadButtonHolderHighlighted[23] = false;
							ControlsController.backOnGamepadButtonHolder[23] = false;
						}
						else if(ControlsController.backOnGamepadButtonHolder[23]) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							ControlsController.backOnGamepadButtonHolder[23] = false;
						}
					}
					else if(ControlsController.gamepadButtonHolderHighlighted[23] == true || ControlsController.gamepadButtonHolderClicked[23] == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(ControlsController.gamepadButtonHolderClicked[23] == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						ControlsController.gamepadButtonHolderHighlighted[23] = false;
						ControlsController.backOnGamepadButtonHolder[23] = false;
					}
					else if(ControlsController.backOnGamepadButtonHolder[23])
						ControlsController.backOnGamepadButtonHolder[23] = false;
					
					//Reset Button in Controls Menu
					if(mx >= 564 && mx <= 605) {
						if(my >= 443 && my <= 459) {
							if(Game.selectorButtonPosition != -26) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.selectorButtonPosition = -26;
							}
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.resetClicked )
									Game.backOnReset = true;
							else if(!Game.mouseIsClickedDown)
								Game.resetHighlighted = true;
						}
						else if(Game.resetHighlighted == true || Game.resetClicked == true) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							if(Game.resetClicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.resetHighlighted = false;
							Game.backOnReset = false;
						}
						else if(Game.backOnReset) {
							if(Game.selectorButtonPosition != Game.selectorBPMP)
								Game.selectorButtonPosition = Game.selectorBPMP;
							Game.backOnReset = false;
						}
					}
					else if(Game.resetHighlighted == true || Game.resetClicked == true) {
						if(Game.selectorButtonPosition != Game.selectorBPMP)
							Game.selectorButtonPosition = Game.selectorBPMP;
						if(Game.resetClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.resetHighlighted = false;
						Game.backOnReset = false;
					}
					else if(Game.backOnReset)
						Game.backOnReset = false;
				}
				
				/*
					//Help Button Highlighted
					if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.MENU ||
							mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.GAMEOVER) {
						if (my >= 300 && my <= 364) {
							game.setHelpHighlighted(true);
						}
						else if(game.getHelpHighlighted() == true)
							game.setHelpHighlighted(false);
					}
					else if(game.getHelpHighlighted() == true)
						game.setHelpHighlighted(false);
					
					//Exit Button Highlighted
					if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.MENU ||
							mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.GAMEOVER) {
						if (my >= 400 && my <= 464) {
							game.setExitHighlighted(true);
						}
						else if(game.getExitHighlighted() == true)
							game.setExitHighlighted(false);
					}
					else if(game.getExitHighlighted() == true)
						game.setExitHighlighted(false);
					
					//Resume Button Highlighted
					if (mx >= Game.WIDTH / 2 + 80 && mx <= Game.WIDTH / 2 + 288 && Game.State == Game.STATE.GAME && Game.getUserHasPaused()) {
						if(my >= 100 && my <= 164) 
							game.setResumeHighlighted(true);
						else if(game.getResumeHighlighted() == true)
							game.setResumeHighlighted(false);
					}
					else if(game.getResumeHighlighted() == true)
						game.setResumeHighlighted(false);
					
					//Home Button Highlighted
					if (mx >= Game.WIDTH / 2 + 114 && mx <= Game.WIDTH / 2 + 254 && Game.State == Game.STATE.GAME && Game.getUserHasPaused()) {
						if(my >= 200 && my <= 264)  
							game.setHomeHighlighted(true);
						else if(game.getHomeHighlighted() == true)
							game.setHomeHighlighted(false);
					}
					else if(game.getHomeHighlighted() == true)
						game.setHomeHighlighted(false);
					
					//Back Button Highlighted
					if(mx >= 40 && mx <= 88 && (Game.State == Game.STATE.SET_SCORE || Game.State == Game.STATE.LEADERBOARD)) {
						if(my >= 20 && my <= 36) 
							game.setBackHighlighted(true);
						else if(game.getBackHighlighted() == true)
							game.setBackHighlighted(false);
					}
					else if(game.getBackHighlighted() == true)
						game.setBackHighlighted(false);
					
					//Submit Score Button Highlighted
					if(mx >= 40 && mx <= 110 && Game.State == Game.STATE.GAMEOVER) {
						if(my >= 20 && my <= 36) 
							game.setSetScoreHighlighted(true);
						else if(game.getSetScoreHighlighted() == true)
							game.setSetScoreHighlighted(false);
					}
					else if(game.getSetScoreHighlighted() == true)
						game.setSetScoreHighlighted(false);
					
					//Leaderboard Button Highlighted
					if(mx >=  Game.WIDTH / 2 + 380 && mx <= Game.WIDTH / 2 + 470 && Game.State == Game.STATE.GAMEOVER) {
						if(my >= 20 && my <= 36) 
							game.setLeaderboardHighlighted(true);
						else if(game.getLeaderboardHighlighted() == true)
							game.setLeaderboardHighlighted(false);
					}
					else if(game.getLeaderboardHighlighted() == true)
						game.setLeaderboardHighlighted(false);
						*/
				//}
			}
		}
		else {
			game.setPlayHighlighted(false);
			game.setShopHighlighted(false);
			game.setExitHighlighted(false);
			game.setResumeHighlighted(false);
			game.setHomeHighlighted(false);
			game.setBackHighlighted(false);
			game.setSetScoreHighlighted(false);
			game.setLeaderboardHighlighted(false);
			Game.helpHighlighted = false;
			Game.settingsHighlighted = false;
			Game.arrowL1Highlighted = false;
			Game.arrowR1Highlighted = false;
			Game.buy1Highlighted = false;
			Game.set1Highlighted = false;
			Game.arrowL2Highlighted = false;
			Game.arrowR2Highlighted = false;
			Game.buy2Highlighted = false;
			Game.set2Highlighted = false;
			Game.arrowL3Highlighted = false;
			Game.arrowR3Highlighted = false;
			Game.buy3Highlighted = false;
			Game.set3Highlighted = false;
			Game.checkMarkHighlighted = false;
			Game.resetStatsHighlighted = false;
			Game.resetHighlighted = false;
			Game.yesHighlighted = false;
			Game.noHighlighted = false;
			Game.skipHighlighted = false;
			Game.submitHighlighted = false;
			Game.gamepadImageHighlighted = false;
			Game.noteImageHighlighted = false;
			for(int i = 0; i <= ControlsController.gamepadButtonHolderHighlighted.length-1; i++) {
				ControlsController.gamepadButtonHolderHighlighted[i] = false;
			}
			//ControlsController.gamepadButtonHolderHighlighted[0] = false;
		}
		
		
	}
	private void locateMouseButtonPrompt(boolean buttonHighlighted, boolean buttonClicked, boolean backonButton, 
			boolean mouseIsClickedDown, boolean mouseIsOffClickedObjectAndHeldDown, 
			int mx, int my, int x , int xWidth, int y, int yHeight){
		
		if (mx >= Game.WIDTH / 2 + x && mx <= Game.WIDTH / 2 + xWidth && Game.State == Game.STATE.MENU ||
				mx >= Game.WIDTH / 2 + x && mx <= Game.WIDTH / 2 + xWidth && Game.State == Game.STATE.GAMEOVER) {
			if (my >= y && my <= yHeight) {
				if(mouseIsOffClickedObjectAndHeldDown == true && buttonClicked)
					backonButton = true;
				else if(!mouseIsClickedDown)
					buttonHighlighted = true;
			}
			else if(buttonHighlighted == true || buttonClicked == true) {
				if(buttonClicked == true) 
					mouseIsOffClickedObjectAndHeldDown = true;
				buttonHighlighted = false;
				backonButton = false;
			}
			else
				backonButton = false;
		}
		else if(buttonHighlighted == true || buttonClicked == true) {
			if(buttonClicked == true) 
				mouseIsOffClickedObjectAndHeldDown = true;
			buttonHighlighted = false;
			backonButton = false;
		}
		else
			backonButton = false;
	}
}
