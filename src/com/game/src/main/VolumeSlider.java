package com.game.src.main;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.awt.Graphics;

public class VolumeSlider {
	public static int x = 0;
	public static int y = 0;
	public static int x2 = 0;
	public static int y2 = 0;
	public static int volumeSliderPosition = 5;
	public static BufferedImage volumeSlider = null;
	public static BufferedImage volumeSliderGlow = null;
	public static BufferedImage volumeSliderClicked = null;
	public static BufferedImage volumeSliderSelected = null;
	public static BufferedImage volumeSliderSelectedNormal = null;
	public static BufferedImage volumeSliderSelectedClicked = null;
	public static BufferedImage emptyVolumeSlider = null;
	public static boolean volumeSliderSetup = false;
	public static boolean volumeSliderChangingVolume = false;
	public static boolean sfxMusicSliderSetup = false;
	public static boolean sfxMusicSliderChangingVolume = false;
	public VolumeSlider(Game game, int x, int y) {
		this.x = x;
		this.y = y;
		volumeSliderPosition = Game.volumeSliderPosition;
		volumeSlider = game.getVolumeSlider();
		volumeSliderGlow = game.getVolumeSliderGlow();
		volumeSliderClicked = game.getVolumeSliderClicked();
		volumeSliderSelected = game.getVolumeSliderSelected();
		volumeSliderSelectedNormal = game.getVolumeSliderSelectedNormal();
		volumeSliderSelectedClicked = game.getVolumeSliderSelectedClicked();
		emptyVolumeSlider = game.getEmptyVolumeSlider();
	}
	
	public static void drawVolumeSlider(Graphics g) {
		
	}
	
	public static void adjustVolume(LinkedList<SoundLoops> menuSound, LinkedList<SoundLoops> gameSound) {
		for(int i = 0; i < menuSound.size(); i++) {
			adjustMusic(menuSound.get(i));
		}
		for(int i = 0; i < gameSound.size(); i++) {
			adjustMusic(gameSound.get(i));
		}
		for(int i = 0; i < Game.hudSFX.size(); i++) {
			adjustSFX(Game.hudSFX.get(i));
		}
		for(int i = 0; i < Game.marioVoices.size(); i++) {
			adjustSFX(Game.marioVoices.get(i));
		}
		for(int i = 0; i < Game.marioDanceSoundLoops.size(); i++) {
			adjustSFX(Game.marioDanceSoundLoops.get(i));
		}
		for(int i = 0; i < Game.luigiVoices.size(); i++) {
			adjustSFX(Game.luigiVoices.get(i));
		}
		adjustSFX(Game.smb31PupSoundLoop);
		adjustSFX(Game.smb3BeepSoundLoop);
		adjustSFX(Game.smbCoinPointsSoundLoop);
		adjustSFX(Game.smbCoinPoints2SoundLoop);
		adjustSFX(Game.smbFireworkSoundLoop);
		adjustSFX(Game.smbPopSoundLoop);
		adjustSFX(Game.smb3BumpSoundLoop);
		adjustSFX(Game.smb3Bump2SoundLoop);
		adjustSFX(Game.smb3CheckmarkSoundLoop);
		adjustSFX(Game.smb3Checkmark2SoundLoop);
		adjustSFX(Game.smb3TextSoundLoop);
		adjustSFX(Game.smb3CoinSoundLoop);
		adjustSFX(Game.smb3ItemSoundLoop);
		adjustSFX(Game.smb3KickSoundLoop);
		adjustSFX(Game.smb3OpenSoundLoop);
		adjustSFX(Game.smb3TailSoundLoop);
		adjustSFX(Game.smwErrorSoundLoop);
		adjustSFX(Game.smwCheatSoundLoop);
		adjustSFX(Game.smwCheat2SoundLoop);
		adjustSFX(Game.smwCheat3SoundLoop);
		adjustSFX(Game.smwCheat4SoundLoop);
		adjustSFX(Game.smwCheat5SoundLoop);
		adjustSFX(Game.smwCheat6SoundLoop);
		adjustSFX(Game.smwCheat7SoundLoop);
		adjustSFX(Game.smwCheat8SoundLoop);
		adjustSFX(Game.smwCheat9SoundLoop);
		adjustSFX(Game.smwCheat10SoundLoop);
		adjustSFX(Game.smwCheatFullSoundLoop);
		adjustSFX(Game.itemSwooshSoundLoop);
		adjustSFX(Game.itemPauseSoundLoop);
		adjustSFX(Game.fireballSFX);
		adjustSFX(Game.fireballPopSFX);
		adjustSFX(Game.pauseSoundFXSoundLoop);
		adjustSFX(Game.soundFXClip1SoundLoop);
		adjustSFX(Game.soundFXClip2SoundLoop);
		adjustSFX(Game.marioSpinningSoundLoop);
		adjustSFX(Game.marioDeathSoundLoop);
		adjustSFX(Game.gameOverWinningSoundLoop);
		adjustSFX(Game.gameOverWinningMikeTysonSoundLoop);
		adjustSFX(Game.gameOverWinningContraSoundLoop);
		adjustMusic(Game.creditsSong);
		adjustMusic(Game.marioStarSoundLoop);
		adjustMusic(Game.gameOverSoundLoop);
		adjustMusic(Game.gameOverIrisSoundLoop);
		if(Game.fireballSFX.getVolume() - 6f >= Game.fireballSFX.minimumVolume())
			Game.fireballSFX.reduceSound(6f);
		if(Game.fireballPopSFX.getVolume() - 6f >= Game.fireballPopSFX.minimumVolume())
			Game.fireballPopSFX.reduceSound(6f);
		if(Game.smb3TextSoundLoop.getVolume() - 6f >= Game.smb3TextSoundLoop.minimumVolume())
			Game.smb3TextSoundLoop.reduceSound(6f);
		volumeSliderChangingVolume = false;
		volumeSliderSetup = false;
		sfxMusicSliderChangingVolume = false;
		sfxMusicSliderSetup = false;
	}
	
	public static void adjustSFX(SoundLoops sound) {
		switch(Game.sfxMusicSliderPosition) {
		case 1:
			switch(Game.volumeSliderPosition) {
				case 1:
					sound.mute();
					break;
				case 2:
					sound.setVolume(-35f);
					break;
				case 3:
					sound.setVolume(-20f);
					break;
				case 4:
					sound.setVolume(-10f);
					break;
				case 5:
					sound.setVolume(0f);
					break;
				default:
					break;
			}
			break;
		case 2:
			switch(Game.volumeSliderPosition) {
				case 1:
					sound.mute();
					break;
				case 2:
					sound.setVolume(-35f);
					break;
				case 3:
					sound.setVolume(-20f);
					break;
				case 4:
					sound.setVolume(-10f);
					break;
				case 5:
					sound.setVolume(-5f);
					break;
				default:
					break;
			}
			break;
		case 3:
			switch(Game.volumeSliderPosition) {
				case 1:
					sound.mute();
					break;
				case 2:
					sound.setVolume(-35f);
					break;
				case 3:
					sound.setVolume(-20f);
					break;
				case 4:
					sound.setVolume(-16f);
					break;
				case 5:
					sound.setVolume(-12f);
					break;
				default:
					break;
			}
			break;
		case 4:
			switch(Game.volumeSliderPosition) {
				case 1:
					sound.mute();
					break;
				case 2:
					sound.setVolume(-35f);
					break;
				case 3:
					sound.setVolume(-31f);
					break;
				case 4:
					sound.setVolume(-28f);
					break;
				case 5:
					sound.setVolume(-23f);
					break;
				default:
					break;
			}
			break;
		case 5:
			sound.mute();
			break;
		default:
			break;
	}
		if(Game.volumeSliderPosition != 1 && Game.sfxMusicSliderPosition != 5)
			sound.unMute();
		//sfxMusicSliderChangingVolume = false;
		//sfxMusicSliderSetup = false;
	}

	public static void adjustMusic(SoundLoops sound) {
		switch(Game.sfxMusicSliderPosition) {
		case 1:
			sound.mute();
			break;
		case 2:
			switch(Game.volumeSliderPosition) {
				case 1:
					sound.mute();
					break;
				case 2:
					sound.setVolume(-35f);
					break;
				case 3:
					sound.setVolume(-31f);
					break;
				case 4:
					sound.setVolume(-28f);
					break;
				case 5:
					sound.setVolume(-23f);
					break;
				default:
					break;
			}
			break;
		case 3:
			switch(Game.volumeSliderPosition) {
				case 1:
					sound.mute();
					break;
				case 2:
					sound.setVolume(-35f);
					break;
				case 3:
					sound.setVolume(-20f);
					break;
				case 4:
					sound.setVolume(-16f);
					break;
				case 5:
					sound.setVolume(-12f);
					break;
				default:
					break;
		}
		break;
		case 4:
			switch(Game.volumeSliderPosition) {
				case 1:
					sound.mute();
					break;
				case 2:
					sound.setVolume(-35f);
					break;
				case 3:
					sound.setVolume(-20f);
					break;
				case 4:
					sound.setVolume(-10f);
					break;
				case 5:
					sound.setVolume(-5f);
					break;
				default:
					break;
			}
			break;
		case 5:
			switch(Game.volumeSliderPosition) {
				case 1:
					sound.mute();
					break;
				case 2:
					sound.setVolume(-35f);
					break;
				case 3:
					sound.setVolume(-20f);
					break;
				case 4:
					sound.setVolume(-10f);
					break;
				case 5:
					sound.setVolume(0f);
					break;
				default:
					break;
			}
			break;
		default:
			break;
	}
		if(Game.volumeSliderPosition != 1 && Game.sfxMusicSliderPosition != 1)
			sound.unMute();
	}

	public static int gameTrackSetup(int x) {
		switch(x) {
		case 0:
			if(Game.gameTrack1Set)
				return 0;
			x++;
		case 1: case -1:
			if(Game.gameTrack2Set)
				return 1;
			x++;
		case 2: case -2:
			if(Game.gameTrack3Set)
				return 2;
			x++;
		case 3: case -3:
			if(Game.gameTrack4Set)
				return 3;
			x++;
		case 4:
			if(Game.gameTrack5Set)
				return 4;
			x++;
		case 5:
			if(Game.gameTrack6Set)
				return 5;
			x++;
		case 6:
			if(Game.gameTrack7Set)
				return 6;
			x = 0;
			break;
		default:
			return x;
		}
		switch(x) {
		case 0:
			if(Game.gameTrack1Set)
				return 0;
			x++;
		case 1: case -1:
			if(Game.gameTrack2Set)
				return 1;
			x++;
		case 2: case -2:
			if(Game.gameTrack3Set)
				return 2;
			x++;
		case 3: case -3:
			if(Game.gameTrack4Set)
				return 3;
			x++;
		case 4:
			if(Game.gameTrack5Set)
				return 4;
			x++;
		case 5:
			if(Game.gameTrack6Set)
				return 5;
			x++;
		case 6:
			if(Game.gameTrack7Set)
				return 6;
			x = 0;
			break;
		default:
			return x;
		}
		x = -4;
		return x;
	}
	
	public static int gameTrackSetup(int x,int noRepeat) {
		switch(x) {
		case 0:
			if(Game.gameTrack1Set && x != noRepeat)
				return 0;
			x++;
		case 1: case -1:
			if(Game.gameTrack2Set && x != noRepeat)
				return 1;
			x++;
		case 2: case -2:
			if(Game.gameTrack3Set && x != noRepeat)
				return 2;
			x++;
		case 3: case -3:
			if(Game.gameTrack4Set && x != noRepeat)
				return 3;
			x++;
		case 4:
			if(Game.gameTrack5Set && x != noRepeat)
				return 4;
			x++;
		case 5:
			if(Game.gameTrack6Set && x != noRepeat)
				return 5;
			x++;
		case 6:
			if(Game.gameTrack7Set && x != noRepeat)
				return 6;
			x = 0;
			break;
		default:
			return x;
		}
		switch(x) {
		case 0:
			if(Game.gameTrack1Set && x != noRepeat)
				return 0;
			x++;
		case 1: case -1:
			if(Game.gameTrack2Set && x != noRepeat)
				return 1;
			x++;
		case 2: case -2:
			if(Game.gameTrack3Set && x != noRepeat)
				return 2;
			x++;
		case 3: case -3:
			if(Game.gameTrack4Set && x != noRepeat)
				return 3;
			x++;
		case 4:
			if(Game.gameTrack5Set && x != noRepeat)
				return 4;
			x++;
		case 5:
			if(Game.gameTrack6Set && x != noRepeat)
				return 5;
			x++;
		case 6:
			if(Game.gameTrack7Set && x != noRepeat)
				return 6;
			x = 0;
			break;
		default:
			return x;
		}
		switch(x) {
		case 0:
			if(Game.gameTrack1Set)
				return 0;
			x++;
		case 1: case -1:
			if(Game.gameTrack2Set)
				return 1;
			x++;
		case 2: case -2:
			if(Game.gameTrack3Set)
				return 2;
			x++;
		case 3: case -3:
			if(Game.gameTrack4Set)
				return 3;
			x++;
		case 4:
			if(Game.gameTrack5Set)
				return 4;
			x++;
		case 5:
			if(Game.gameTrack6Set)
				return 5;
			x++;
		case 6:
			if(Game.gameTrack7Set)
				return 6;
			x = 0;
			break;
		default:
			return x;
		}
		x = -4;
		return x;
	}
	
	public static int menuTrackSetup(int x) {
		switch(x) {
		case 0:
			if(Game.menuTrack1Set)
				return 0;
			x++;
		case 1: case -1:
			if(Game.menuTrack2Set)
				return 1;
			x++;
		case 2:
			if(Game.menuTrack3Set)
				return 2;
			x++;
		case 3:
			if(Game.menuTrack4Set)
				return 3;
			x++;
		case 4:
			if(Game.menuTrack5Set)
				return 4;
			x = 0;
			break;
		default:
			return x;
		}
		switch(x) {
		case 0:
			if(Game.menuTrack1Set)
				return 0;
			x++;
		case 1: case -1:
			if(Game.menuTrack2Set)
				return 1;
			x++;
		case 2:
			if(Game.menuTrack3Set)
				return 2;
			x++;
		case 3:
			if(Game.menuTrack4Set)
				return 3;
			x++;
		case 4:
			if(Game.menuTrack5Set)
				return 4;
			x = 0;
			break;
		default:
			return x;
		}
		x = -2;
		return x;
	}
	
}
