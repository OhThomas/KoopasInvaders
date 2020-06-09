package com.game.src.main;

import java.awt.Graphics;

public class TrackController {
	private Game game;
	private SineDraw sinewaves;
	public static boolean trackSetup = false;
	public static boolean readyToSwitch1 = false;
	public static boolean readyToSwitch2 = false;
	public static boolean gameTrackPaused = false;
	public static boolean menuTrackPaused = false;
	public static boolean trackSetupFake = false;
	public static boolean gameTrackPausedFake = false;
	public static boolean menuTrackPausedFake = false;
	private int sinX = 470;
	private int sinY = 0;
	private int amplitudeExample = 0;
	public TrackController(Game game, SineDraw sinewaves) {
		this.game = game;
		this.sinewaves = sinewaves;
	}
	public static void reset() {
		trackSetup = false;
		readyToSwitch1 = false;
		readyToSwitch2 = false;
		gameTrackPaused = false;
		menuTrackPaused = false;
		trackSetupFake = false;
		gameTrackPausedFake = false;
		menuTrackPausedFake = false;
	}
	public void tick() {
		if(Game.trackCurrentlyPlaying) {
			if(!trackSetup) {
				Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).stop();
				if(Game.menuTrackCurrentlyPlaying == -1 && Game.gameTrackCurrentlyPlaying == -1) {
					Game.gameTrackCurrentlyPlaying = 0;
					Game.gameTrackPosition = 0;
				}
				if(Game.gameTrackCurrentlyPlaying != -1) {
					//System.out.println(Game.gameTrackCurrentlyPlaying);
					Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).play();
					//System.out.println(Game.gameTrackCurrentlyPlaying);
					sinewaves.setSong(Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying));
					sinewaves.setAmplitude(0);
				}
				else if(Game.menuTrackCurrentlyPlaying != -1) {
					Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).play();
					sinewaves.setSong(Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying));
					sinewaves.setAmplitude(0);
				}
				trackSetup = true;
			}
			else {
				if(Game.gameTrackCurrentlyPlaying == -1 && Game.menuTrackCurrentlyPlaying == -1) {}
				else if(Game.gameTrackCurrentlyPlaying != -1) {
				    if(sinY != 170)
				    	sinY = 170;
					if(Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).clipIsActive() && !Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).endsSoon()) {
						int amplitudeExample = Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).getSampleInt((int)Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).getLongFramePosition());
						this.amplitudeExample = amplitudeExample;
						//System.out.println(amplitudeExample);
					    //sinewaves.setCycles(5);
					}
					else {
						if(amplitudeExample != 0)
							amplitudeExample = 0;
					}
					if(!Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).clipIsActive()) {
						trackSetup = false;
						Game.trackCurrentlyPlaying = false;
						Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).stop();
						Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).setFramePosition(0);
					}
				}
				else {
				    if(sinY != 370)
				    	sinY = 370;
					if(Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).clipIsActive() && !Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).endsSoon()) {
						int amplitudeExample = Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).getSampleInt((int)Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).getLongFramePosition());
						this.amplitudeExample = amplitudeExample;
						 //System.out.println(amplitudeExample);
					    //sinewaves.setCycles(5);
					}
					else {
						if(amplitudeExample != 0)
							amplitudeExample = 0;
					}
					if(!Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).clipIsActive()) {
						trackSetup = false;
						Game.trackCurrentlyPlaying = false;
						Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).stop();
						Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).setFramePosition(0);
					}
				}
			}
		}
		if(readyToSwitch1) {
			if(Game.trackCurrentlyPlaying) {
				if(Game.menuTrackCurrentlyPlaying == -1 && Game.gameTrackCurrentlyPlaying == -1) {
					Game.menuTrackCurrentlyPlaying = 0;
				}
				else if(Game.menuTrackCurrentlyPlaying != -1){
					TrackController.trackSetup = false;
					Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).stop();
					Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).setFramePosition(0);
					Game.trackCurrentlyPlaying = true;
					Game.gameTrackCurrentlyPlaying = Game.gameTrackPosition;
					Game.menuTrackCurrentlyPlaying = -1;
				}
				else if(Game.gameTrackCurrentlyPlaying != Game.gameTrackPosition) {
					TrackController.trackSetup = false;
					Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).stop();
					Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).setFramePosition(0);
					Game.trackCurrentlyPlaying = true;
					Game.gameTrackCurrentlyPlaying = Game.gameTrackPosition;
				}
				else {
					Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).stop();
					Game.trackCurrentlyPlaying = false;
					TrackController.gameTrackPaused = true;
					TrackController.menuTrackPaused = false;
				}
			}
			else {
				if(TrackController.gameTrackPaused) {
					Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).continuePlaying();
					TrackController.gameTrackPaused = false;
					TrackController.menuTrackPaused = false;
					Game.trackCurrentlyPlaying = true;
				}
				else {
					if(TrackController.menuTrackPaused) {
						Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).setFramePosition(0);
						TrackController.menuTrackPaused = false;
					}
					TrackController.trackSetup = false;
					Game.trackCurrentlyPlaying = true;
					Game.gameTrackCurrentlyPlaying = Game.gameTrackPosition;
					Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).play();
					Game.menuTrackCurrentlyPlaying = -1;
				}
			}
			Game.menuMusicStopped = true;
			readyToSwitch1 = false;
		}

		if(readyToSwitch2) {
			if(Game.trackCurrentlyPlaying) {
				if(Game.menuTrackCurrentlyPlaying == -1 && Game.gameTrackCurrentlyPlaying == -1) {
					Game.menuTrackCurrentlyPlaying = 0;
				}
				
				else if(Game.gameTrackCurrentlyPlaying != -1){
					TrackController.trackSetup = false;
					Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).stop();
					Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).setFramePosition(0);
					Game.trackCurrentlyPlaying = true;
					Game.menuTrackCurrentlyPlaying = Game.menuTrackPosition;
					Game.gameTrackCurrentlyPlaying = -1;
				}
				else if(Game.menuTrackCurrentlyPlaying != Game.menuTrackPosition) {
					TrackController.trackSetup = false;
					Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).stop();
					Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).setFramePosition(0);
					Game.trackCurrentlyPlaying = true;
					Game.menuTrackCurrentlyPlaying = Game.menuTrackPosition;
				}
				else {
					Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).stop();
					Game.trackCurrentlyPlaying = false;
					TrackController.menuTrackPaused = true;
					TrackController.gameTrackPaused = false;
				}
			}
			else {
				if(TrackController.menuTrackPaused) {
					Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).continuePlaying();
					TrackController.menuTrackPaused = false;
					TrackController.gameTrackPaused = false;
					Game.trackCurrentlyPlaying = true;
				}
				else {
					if(TrackController.gameTrackPaused) {
						Game.gameSoundLoops.get(Game.gameTrackCurrentlyPlaying).setFramePosition(0);
						TrackController.gameTrackPaused = false;
					}
					TrackController.trackSetup = false;
					Game.trackCurrentlyPlaying = true;
					TrackController.gameTrackPaused = false;
					Game.menuTrackCurrentlyPlaying = Game.menuTrackPosition;
					Game.menuSoundLoops.get(Game.menuTrackCurrentlyPlaying).play();
					Game.gameTrackCurrentlyPlaying = -1;
				}
			}
			Game.menuMusicStopped = true;
			readyToSwitch2 = false;
		}
	}
	public void drawTrack(Graphics g) {
		if(Game.trackCurrentlyPlaying) {
//			int amplitudeExample = this.gameSoundLoops.get(this.soundRandomizer).getSampleInt(140); // 140th amplitude value.
//
//			for (int i = 0; i < this.gameSoundLoops.get(this.soundRandomizer).getFramesCount(); i++) {
//			    int amplitude = this.gameSoundLoops.get(this.soundRandomizer).getSampleInt(i);
//			    System.out.println(amplitude);
//			    // Plot.

			sinewaves.draw(g, amplitudeExample,sinX,sinY);
		}   
	}
}
