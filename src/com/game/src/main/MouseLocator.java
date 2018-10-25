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
						if(Game.selectorButtonPosition != 0)
							Game.selectorButtonPosition = 0;
						
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.playClicked)
							Game.backOnPlay = true;
						else if(!Game.mouseIsClickedDown)
							game.setPlayHighlighted(true);
					}
					else if(game.getPlayHighlighted() == true || Game.playClicked == true) {
						if(Game.selectorButtonPosition != 0)
							Game.selectorButtonPosition = 0;
						
						if(Game.playClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						game.setPlayHighlighted(false);
						Game.backOnPlay = false;
					}
					else if(Game.backOnPlay) {
						if(Game.selectorButtonPosition != 0)
							Game.selectorButtonPosition = 0;
						Game.backOnPlay = false;
					}
				}
				else if(game.getPlayHighlighted() == true || Game.playClicked == true) {
					if(Game.selectorButtonPosition != 0)
						Game.selectorButtonPosition = 0;
					
					if(Game.playClicked == true) 
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					game.setPlayHighlighted(false);
					Game.backOnPlay = false;
				}
				else if(Game.backOnPlay)
					Game.backOnPlay = false;
				
				//Help Button
				if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.MENU ||
						mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 248 && Game.State == Game.STATE.GAMEOVER) {
					if (my >= 300 && my <= 364) {
						if(Game.selectorButtonPosition != 1)
							Game.selectorButtonPosition = 1;
						
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.shopClicked)
							Game.backOnShop = true;
						else if(!Game.mouseIsClickedDown)
							Game.shopHighlighted = true;
					}
					else if(Game.shopHighlighted == true || Game.shopClicked == true) {
						if(Game.selectorButtonPosition != 0)
							Game.selectorButtonPosition = 0;
						
						if(Game.shopClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.shopHighlighted = false;
						Game.backOnShop = false;
					}
					else if(Game.backOnShop) {
						if(Game.selectorButtonPosition != 0)
							Game.selectorButtonPosition = 0;
						Game.backOnShop = false;
					}
				}
				else if(Game.shopHighlighted == true || Game.shopClicked == true) {
					if(Game.selectorButtonPosition != 0)
						Game.selectorButtonPosition = 0;
					
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
						if(Game.selectorButtonPosition != 2)
							Game.selectorButtonPosition = 2;
						
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.exitClicked)
							Game.backOnExit = true;
						else if(!Game.mouseIsClickedDown)
							Game.exitHighlighted = true;
					}
					else if(Game.exitHighlighted == true || Game.exitClicked == true) {
						if(Game.selectorButtonPosition != 0)
							Game.selectorButtonPosition = 0;
						
						if(Game.exitClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.exitHighlighted = false;
						Game.backOnExit = false;
					}
					else if(Game.backOnExit) {
						if(Game.selectorButtonPosition != 0)
							Game.selectorButtonPosition = 0;
						Game.backOnExit = false;
					}
				}
				else if(Game.State != Game.STATE.GAME && Game.exitHighlighted == true || 
						Game.State != Game.STATE.GAME && Game.exitClicked == true) {
					if(Game.selectorButtonPosition != 0)
						Game.selectorButtonPosition = 0;
					
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
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.exitClicked)
							Game.backOnExit = true;
						else if(!Game.mouseIsClickedDown)
							Game.exitHighlighted = true;
					}
					else if(Game.exitHighlighted == true || Game.exitClicked == true) {
						if(Game.exitClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.exitHighlighted = false;
						Game.backOnExit = false;
					}
					else if(Game.backOnExit)
						Game.backOnExit = false;
				}
				else if(Game.State == Game.STATE.GAME && Game.getUserHasPaused() && Game.exitHighlighted == true || 
						Game.State == Game.STATE.GAME && Game.getUserHasPaused() && Game.exitClicked == true) {
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
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.resumeClicked)
							Game.backOnResume = true;
						else if(!Game.mouseIsClickedDown)
							Game.resumeHighlighted = true;
					}
					else if(Game.resumeHighlighted == true || Game.resumeClicked == true) {
						if(Game.resumeClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.resumeHighlighted = false;
						Game.backOnResume = false;
					}
					else if(Game.backOnResume)
						Game.backOnResume = false;
				}
				else if(Game.resumeHighlighted == true || Game.resumeClicked == true) {
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
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.homeClicked)
							Game.backOnHome = true;
						else if(!Game.mouseIsClickedDown)
							Game.homeHighlighted = true;
					}
					else if(Game.homeHighlighted == true || Game.homeClicked == true) {
						if(Game.homeClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.homeHighlighted = false;
						Game.backOnHome = false;
					}
					else if(Game.backOnHome)
						Game.backOnHome = false;
				}
				else if(Game.homeHighlighted == true || Game.homeClicked == true) {
					if(Game.homeClicked == true) 
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.homeHighlighted = false;
					Game.backOnHome = false;
				}
				else if(Game.backOnHome)
					Game.backOnHome = false;
				
				//Back Button
				if(mx >= 40 && mx <= 88 && (Game.State == Game.STATE.SET_SCORE || Game.State == Game.STATE.LEADERBOARD || Game.State == Game.STATE.SHOP
						|| Game.State == Game.STATE.HELP || Game.State == Game.STATE.SETTINGS)) {
					if(my >= 20 && my <= 36)   {
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.backClicked)
							Game.backOnBack = true;
						else if(!Game.mouseIsClickedDown)
							Game.backHighlighted = true;
					}
					else if(Game.backHighlighted == true || Game.backClicked == true) {
						if(Game.backClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.backHighlighted = false;
						Game.backOnBack = false;
					}
					else if(Game.backOnBack)
						Game.backOnBack = false;
				}
				else if(Game.backHighlighted == true || Game.backClicked == true) {
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
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.helpClicked)
							Game.backOnHelp = true;
						else if(!Game.mouseIsClickedDown)
							Game.helpHighlighted = true;
					}
					else if(Game.helpHighlighted == true || Game.helpClicked == true) {
						if(Game.helpClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.helpHighlighted = false;
						Game.backOnHelp = false;
					}
					else if(Game.backOnHelp)
						Game.backOnHelp = false;
				}
				else if(Game.helpHighlighted == true || Game.helpClicked == true) {
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
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.settingsClicked)
							Game.backOnSettings = true;
						else if(!Game.mouseIsClickedDown)
							Game.settingsHighlighted = true;
					}
					else if(Game.settingsHighlighted == true || Game.settingsClicked == true) {
						if(Game.settingsClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.settingsHighlighted = false;
						Game.backOnSettings = false;
					}
					else if(Game.backOnSettings)
						Game.backOnSettings = false;
				}
				else if(Game.settingsHighlighted == true || Game.settingsClicked == true) {
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
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.setScoreClicked)
							Game.backOnSetScore = true;
						else if(!Game.mouseIsClickedDown)
							Game.setScoreHighlighted = true;
					}
					else if(Game.setScoreHighlighted == true || Game.setScoreClicked == true) {
						if(Game.setScoreClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.setScoreHighlighted = false;
						Game.backOnSetScore = false;
					}
					else if(Game.backOnSetScore)
						Game.backOnSetScore = false;
				}
				else if(Game.setScoreHighlighted == true || Game.setScoreClicked == true) {
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
						if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.leaderboardClicked)
							Game.backOnLeaderboard = true;
						else if(!Game.mouseIsClickedDown)
							Game.leaderboardHighlighted = true;
					}
					else if(Game.leaderboardHighlighted == true || Game.leaderboardClicked == true) {
						if(Game.leaderboardClicked == true) 
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.leaderboardHighlighted = false;
						Game.backOnLeaderboard = false;
					}
					else if(Game.backOnLeaderboard)
						Game.backOnLeaderboard = false;
				}
				else if(Game.leaderboardHighlighted == true || Game.leaderboardClicked == true) {
					if(Game.leaderboardClicked == true) 
						Game.mouseIsOffClickedObjectAndHeldDown = true;
					Game.leaderboardHighlighted = false;
					Game.backOnLeaderboard = false;
				}
				else if(Game.backOnLeaderboard)
					Game.backOnLeaderboard = false;
				if(Game.State == Game.STATE.SHOP) {
					//ArrowL1 Button
					if(mx >=  Game.WIDTH - 48 && mx <= Game.WIDTH - 32 && Game.State == Game.STATE.SHOP) {
						if(my >= 120 && my <= 152) {
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowL1Clicked )
									Game.backOnArrowL1 = true;
							else if(!Game.mouseIsClickedDown)
								Game.arrowL1Highlighted = true;
						}
						else if(Game.arrowL1Highlighted == true || Game.arrowL1Clicked == true) {
							if(Game.arrowL1Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.arrowL1Highlighted = false;
							Game.backOnArrowL1 = false;
						}
						else if(Game.backOnArrowL1)
							Game.backOnArrowL1 = false;
					}
					else if(Game.arrowL1Highlighted == true || Game.arrowL1Clicked == true) {
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
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowR1Clicked )
									Game.backOnArrowR1 = true;
							else if(!Game.mouseIsClickedDown)
								Game.arrowR1Highlighted = true;
						}
						else if(Game.arrowR1Highlighted == true || Game.arrowR1Clicked == true) {
							if(Game.arrowR1Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.arrowR1Highlighted = false;
							Game.backOnArrowR1 = false;
						}
						else if(Game.backOnArrowR1)
							Game.backOnArrowR1 = false;
					}
					else if(Game.arrowR1Highlighted == true || Game.arrowR1Clicked == true) {
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
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.buy1Clicked )
									Game.backOnBuy1 = true;
							else if(!Game.mouseIsClickedDown)
								Game.buy1Highlighted = true;
						}
						else if(Game.buy1Highlighted == true || Game.buy1Clicked == true) {
							if(Game.buy1Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.buy1Highlighted = false;
							Game.backOnBuy1 = false;
						}
						else if(Game.backOnBuy1)
							Game.backOnBuy1 = false;
					}
					else if(Game.buy1Highlighted == true || Game.buy1Clicked == true) {
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
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.set1Clicked )
									Game.backOnSet1 = true;
							else if(!Game.mouseIsClickedDown)
								Game.set1Highlighted = true;
						}
						else if(Game.set1Highlighted == true || Game.set1Clicked == true) {
							if(Game.set1Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.set1Highlighted = false;
							Game.backOnSet1 = false;
						}
						else if(Game.backOnSet1)
							Game.backOnSet1 = false;
					}
					else if(Game.set1Highlighted == true || Game.set1Clicked == true) {
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
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowL2Clicked )
									Game.backOnArrowL2 = true;
							else if(!Game.mouseIsClickedDown)
								Game.arrowL2Highlighted = true;
						}
						else if(Game.arrowL2Highlighted == true || Game.arrowL2Clicked == true) {
							if(Game.arrowL2Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.arrowL2Highlighted = false;
							Game.backOnArrowL2 = false;
						}
						else if(Game.backOnArrowL2)
							Game.backOnArrowL2 = false;
					}
					else if(Game.arrowL2Highlighted == true || Game.arrowL2Clicked == true) {
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
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowR2Clicked )
									Game.backOnArrowR2 = true;
							else if(!Game.mouseIsClickedDown)
								Game.arrowR2Highlighted = true;
						}
						else if(Game.arrowR2Highlighted == true || Game.arrowR2Clicked == true) {
							if(Game.arrowR2Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.arrowR2Highlighted = false;
							Game.backOnArrowR2 = false;
						}
						else if(Game.backOnArrowR2)
							Game.backOnArrowR2 = false;
					}
					else if(Game.arrowR2Highlighted == true || Game.arrowR2Clicked == true) {
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
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.buy2Clicked )
									Game.backOnBuy2 = true;
							else if(!Game.mouseIsClickedDown)
								Game.buy2Highlighted = true;
						}
						else if(Game.buy2Highlighted == true || Game.buy2Clicked == true) {
							if(Game.buy2Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.buy2Highlighted = false;
							Game.backOnBuy2 = false;
						}
						else if(Game.backOnBuy2)
							Game.backOnBuy2 = false;
					}
					else if(Game.buy2Highlighted == true || Game.buy2Clicked == true) {
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
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.set2Clicked )
									Game.backOnSet2 = true;
							else if(!Game.mouseIsClickedDown)
								Game.set2Highlighted = true;
						}
						else if(Game.set2Highlighted == true || Game.set2Clicked == true) {
							if(Game.set2Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.set2Highlighted = false;
							Game.backOnSet2 = false;
						}
						else if(Game.backOnSet2)
							Game.backOnSet2 = false;
					}
					else if(Game.set2Highlighted == true || Game.set2Clicked == true) {
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
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowL3Clicked )
									Game.backOnArrowL3 = true;
							else if(!Game.mouseIsClickedDown)
								Game.arrowL3Highlighted = true;
						}
						else if(Game.arrowL3Highlighted == true || Game.arrowL3Clicked == true) {
							if(Game.arrowL3Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.arrowL3Highlighted = false;
							Game.backOnArrowL3 = false;
						}
						else if(Game.backOnArrowL3)
							Game.backOnArrowL3 = false;
					}
					else if(Game.arrowL3Highlighted == true || Game.arrowL3Clicked == true) {
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
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowR3Clicked )
									Game.backOnArrowR3 = true;
							else if(!Game.mouseIsClickedDown)
								Game.arrowR3Highlighted = true;
						}
						else if(Game.arrowR3Highlighted == true || Game.arrowR3Clicked == true) {
							if(Game.arrowR3Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.arrowR3Highlighted = false;
							Game.backOnArrowR3 = false;
						}
						else if(Game.backOnArrowR3)
							Game.backOnArrowR3 = false;
					}
					else if(Game.arrowR3Highlighted == true || Game.arrowR3Clicked == true) {
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
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.buy3Clicked )
									Game.backOnBuy3 = true;
							else if(!Game.mouseIsClickedDown)
								Game.buy3Highlighted = true;
						}
						else if(Game.buy3Highlighted == true || Game.buy3Clicked == true) {
							if(Game.buy3Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.buy3Highlighted = false;
							Game.backOnBuy3 = false;
						}
						else if(Game.backOnBuy3)
							Game.backOnBuy3 = false;
					}
					else if(Game.buy3Highlighted == true || Game.buy3Clicked == true) {
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
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.set3Clicked )
									Game.backOnSet3 = true;
							else if(!Game.mouseIsClickedDown)
								Game.set3Highlighted = true;
						}
						else if(Game.set3Highlighted == true || Game.set3Clicked == true) {
							if(Game.set3Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.set3Highlighted = false;
							Game.backOnSet3 = false;
						}
						else if(Game.backOnSet3)
							Game.backOnSet3 = false;
					}
					else if(Game.set3Highlighted == true || Game.set3Clicked == true) {
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
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowL4Clicked )
									Game.backOnArrowL4 = true;
							else if(!Game.mouseIsClickedDown)
								Game.arrowL4Highlighted = true;
						}
						else if(Game.arrowL4Highlighted == true || Game.arrowL4Clicked == true) {
							if(Game.arrowL4Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.arrowL4Highlighted = false;
							Game.backOnArrowL4 = false;
						}
						else if(Game.backOnArrowL4)
							Game.backOnArrowL4 = false;
					}
					else if(Game.arrowL4Highlighted == true || Game.arrowL4Clicked == true) {
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
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowR4Clicked )
									Game.backOnArrowR4 = true;
							else if(!Game.mouseIsClickedDown)
								Game.arrowR4Highlighted = true;
						}
						else if(Game.arrowR4Highlighted == true || Game.arrowR4Clicked == true) {
							if(Game.arrowR4Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.arrowR4Highlighted = false;
							Game.backOnArrowR4 = false;
						}
						else if(Game.backOnArrowR4)
							Game.backOnArrowR4 = false;
					}
					else if(Game.arrowR4Highlighted == true || Game.arrowR4Clicked == true) {
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
							if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.buy4Clicked )
									Game.backOnBuy4 = true;
							else if(!Game.mouseIsClickedDown)
								Game.buy4Highlighted = true;
						}
						else if(Game.buy4Highlighted == true || Game.buy4Clicked == true) {
							if(Game.buy4Clicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.buy4Highlighted = false;
							Game.backOnBuy4 = false;
						}
						else if(Game.backOnBuy4)
							Game.backOnBuy4 = false;
					}
					else if(Game.buy4Highlighted == true || Game.buy4Clicked == true) {
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
						else if(Game.backOnSet4)
							Game.backOnSet4 = false;
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
								if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.yesClicked )
									Game.backOnYes = true;
							else if(!Game.mouseIsClickedDown)
								Game.yesHighlighted = true;
						}
						else if(Game.yesHighlighted == true || Game.yesClicked == true) {
							if(Game.yesClicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.yesHighlighted = false;
							Game.backOnYes = false;
						}
						else if(Game.backOnYes)
							Game.backOnYes = false;
					}
					else if(Game.yesHighlighted == true || Game.yesClicked == true) {
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
								if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.noClicked )
									Game.backOnNo = true;
							else if(!Game.mouseIsClickedDown)
								Game.noHighlighted = true;
						}
						else if(Game.noHighlighted == true || Game.noClicked == true) {
							if(Game.noClicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.noHighlighted = false;
							Game.backOnNo = false;
						}
						else if(Game.backOnNo)
							Game.backOnNo = false;
					}
					else if(Game.noHighlighted == true || Game.noClicked == true) {
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
								if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowL1Clicked )
										Game.backOnArrowL1 = true;
								else if(!Game.mouseIsClickedDown)
									Game.arrowL1Highlighted = true;
							}
							else if(Game.arrowL1Highlighted == true || Game.arrowL1Clicked == true) {
								if(Game.arrowL1Clicked == true) 
									Game.mouseIsOffClickedObjectAndHeldDown = true;
								Game.arrowL1Highlighted = false;
								Game.backOnArrowL1 = false;
							}
							else if(Game.backOnArrowL1)
								Game.backOnArrowL1 = false;
						}
						else if(Game.arrowL1Highlighted == true || Game.arrowL1Clicked == true) {
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
								if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowR1Clicked )
										Game.backOnArrowR1 = true;
								else if(!Game.mouseIsClickedDown)
									Game.arrowR1Highlighted = true;
							}
							else if(Game.arrowR1Highlighted == true || Game.arrowR1Clicked == true) {
								if(Game.arrowR1Clicked == true) 
									Game.mouseIsOffClickedObjectAndHeldDown = true;
								Game.arrowR1Highlighted = false;
								Game.backOnArrowR1 = false;
							}
							else if(Game.backOnArrowR1)
								Game.backOnArrowR1 = false;
						}
						else if(Game.arrowR1Highlighted == true || Game.arrowR1Clicked == true) {
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
								if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowL2Clicked )
										Game.backOnArrowL2 = true;
								else if(!Game.mouseIsClickedDown)
									Game.arrowL2Highlighted = true;
							}
							else if(Game.arrowL2Highlighted == true || Game.arrowL2Clicked == true) {
								if(Game.arrowL2Clicked == true) 
									Game.mouseIsOffClickedObjectAndHeldDown = true;
								Game.arrowL2Highlighted = false;
								Game.backOnArrowL2 = false;
							}
							else if(Game.backOnArrowL2)
								Game.backOnArrowL2 = false;
						}
						else if(Game.arrowL2Highlighted == true || Game.arrowL2Clicked == true) {
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
								if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.arrowR2Clicked )
										Game.backOnArrowR2 = true;
								else if(!Game.mouseIsClickedDown)
									Game.arrowR2Highlighted = true;
							}
							else if(Game.arrowR2Highlighted == true || Game.arrowR2Clicked == true) {
								if(Game.arrowR2Clicked == true) 
									Game.mouseIsOffClickedObjectAndHeldDown = true;
								Game.arrowR2Highlighted = false;
								Game.backOnArrowR2 = false;
							}
							else if(Game.backOnArrowR2)
								Game.backOnArrowR2 = false;
						}
						else if(Game.arrowR2Highlighted == true || Game.arrowR2Clicked == true) {
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
								if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.checkMarkClicked )
										Game.backOnCheckMark = true;
								else if(!Game.mouseIsClickedDown)
									Game.checkMarkHighlighted = true;
							}
							else if(Game.checkMarkHighlighted == true || Game.checkMarkClicked == true) {
								if(Game.checkMarkClicked == true) 
									Game.mouseIsOffClickedObjectAndHeldDown = true;
								Game.checkMarkHighlighted = false;
								Game.backOnCheckMark = false;
							}
							else if(Game.backOnCheckMark)
								Game.backOnCheckMark = false;
						}
						else if(Game.checkMarkHighlighted == true || Game.checkMarkClicked == true) {
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
								if(Game.mouseIsOffClickedObjectAndHeldDown == true && Game.resetStatsClicked )
										Game.backOnResetStats = true;
								else if(!Game.mouseIsClickedDown)
									Game.resetStatsHighlighted = true;
							}
							else if(Game.resetStatsHighlighted == true || Game.resetStatsClicked == true) {
								if(Game.resetStatsClicked == true) 
									Game.mouseIsOffClickedObjectAndHeldDown = true;
								Game.resetStatsHighlighted = false;
								Game.backOnResetStats = false;
							}
							else if(Game.backOnResetStats)
								Game.backOnResetStats = false;
						}
						else if(Game.resetStatsHighlighted == true || Game.resetStatsClicked == true) {
							if(Game.resetStatsClicked == true) 
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.resetStatsHighlighted = false;
							Game.backOnResetStats = false;
						}
						else if(Game.backOnResetStats)
							Game.backOnResetStats = false;
					}
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
			Game.yesHighlighted = false;
			Game.noHighlighted = false;
			
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
