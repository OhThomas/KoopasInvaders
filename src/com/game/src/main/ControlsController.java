package com.game.src.main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.github.strikerx3.jxinput.XInputDevice;
import com.github.strikerx3.jxinput.natives.XInputConstants;

public class ControlsController {
	private Game game;
	private Textures tex;
	public static long buttonChangeTimer = 0;
	public static boolean[] gamepadButtonHolderHighlighted;
	public static boolean[] gamepadButtonHolderClicked;
	public static boolean[] backOnGamepadButtonHolder;
	public static int buttonToChange = 0;
	private int upKeyInt = 22;
	private int downKeyInt = 18;
	private int leftKeyInt = 0;
	private int rightKeyInt = 3;
	private int shootKeyInt = 46;
	private int itemKeyInt = 4;
	private int pauseKeyInt = 50;
	private int cancelKeyInt = 48;
	private BufferedImage[] gamepadButtonHolder = new BufferedImage[3];
	private BufferedImage[] dotdotdot = new BufferedImage[3];
	private BufferedImage[] upKeyWASD = new BufferedImage[3];
	private BufferedImage[] downKeyWASD = new BufferedImage[3];
	private BufferedImage[] leftKeyWASD = new BufferedImage[3];
	private BufferedImage[] rightKeyWASD = new BufferedImage[3];
	private BufferedImage[] shootKeyWASD = new BufferedImage[3];
	private BufferedImage[] itemKeyWASD = new BufferedImage[3];
	private BufferedImage[] pauseKeyWASD = new BufferedImage[3];
	private BufferedImage[] cancelKeyWASD = new BufferedImage[3];
	private BufferedImage[] upKeyXDevice = new BufferedImage[3];
	private BufferedImage[] downKeyXDevice = new BufferedImage[3];
	private BufferedImage[] leftKeyXDevice = new BufferedImage[3];
	private BufferedImage[] rightKeyXDevice = new BufferedImage[3];
	private BufferedImage[] shootKeyXDevice = new BufferedImage[3];
	private BufferedImage[] itemKeyXDevice = new BufferedImage[3];
	private BufferedImage[] pauseKeyXDevice = new BufferedImage[3];
	private BufferedImage[] cancelKeyXDevice = new BufferedImage[3];
	private BufferedImage[] upKeyDirectInput = new BufferedImage[3];
	private BufferedImage[] downKeyDirectInput = new BufferedImage[3];
	private BufferedImage[] leftKeyDirectInput = new BufferedImage[3];
	private BufferedImage[] rightKeyDirectInput = new BufferedImage[3];
	private BufferedImage[] shootKeyDirectInput = new BufferedImage[3];
	private BufferedImage[] itemKeyDirectInput = new BufferedImage[3];
	private BufferedImage[] pauseKeyDirectInput = new BufferedImage[3];
	private BufferedImage[] cancelKeyDirectInput = new BufferedImage[3];
	public ControlsController(Textures tex, Game game) {
		this.game = game;
		this.tex = tex;
		gamepadButtonHolderHighlighted = new boolean[24];
		gamepadButtonHolderClicked = new boolean[24];
		backOnGamepadButtonHolder = new boolean [24];
		gamepadButtonHolder = tex.gamepadButtonHolder;
		dotdotdot = tex.dotdotdot;
		tex.changeBufferedImagesWASD(upKeyWASD, 22);
		tex.changeBufferedImagesWASD(downKeyWASD, 18);
		tex.changeBufferedImagesWASD(leftKeyWASD, 0);
		tex.changeBufferedImagesWASD(rightKeyWASD, 3);
		tex.changeBufferedImagesWASD(shootKeyWASD, 46);
		tex.changeBufferedImagesWASD(itemKeyWASD, 4);
		tex.changeBufferedImagesWASD(pauseKeyWASD, 50);
		tex.changeBufferedImagesWASD(cancelKeyWASD, 48);
		upKeyInt = Game.upKey;
		downKeyInt = Game.downKey;
		leftKeyInt = Game.leftKey;
		rightKeyInt = Game.rightKey;
		shootKeyInt = Game.shootKey;
		itemKeyInt = Game.itemKey;
		pauseKeyInt = Game.pauseKey;
		cancelKeyInt = Game.cancelKey;
		
		tex.changeBufferedImagesXInput(upKeyXDevice, XInputConstants.XINPUT_GAMEPAD_DPAD_UP);
		tex.changeBufferedImagesXInput(downKeyXDevice, XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN);
		tex.changeBufferedImagesXInput(leftKeyXDevice, XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT);
		tex.changeBufferedImagesXInput(rightKeyXDevice, XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT);
		tex.changeBufferedImagesXInput(shootKeyXDevice, XInputConstants.XINPUT_GAMEPAD_A);
		tex.changeBufferedImagesXInput(itemKeyXDevice, XInputConstants.XINPUT_GAMEPAD_X);
		tex.changeBufferedImagesXInput(pauseKeyXDevice, XInputConstants.XINPUT_GAMEPAD_START);
		tex.changeBufferedImagesXInput(cancelKeyXDevice, XInputConstants.XINPUT_GAMEPAD_BACK);
		
		upKeyDirectInput = tex.upButtonImageDirectInput;
		downKeyDirectInput = tex.downButtonImageDirectInput;
		leftKeyDirectInput = tex.leftButtonImageDirectInput;
		rightKeyDirectInput = tex.rightButtonImageDirectInput;
		shootKeyDirectInput = tex.shootButtonImageDirectInput;
		itemKeyDirectInput = tex.itemButtonImageDirectInput;
		pauseKeyDirectInput = tex.pauseButtonImageDirectInput;
		cancelKeyDirectInput = tex.cancelButtonImageDirectInput;
	}
	public void draw(Graphics g) {
		//g.drawImage(gamepadButtonHolder,Game.WIDTH-51,305,null);
		//g.drawImage(dotdotdot[2],524+30,263,null);//524+26,232
		if(System.currentTimeMillis()+2000 < buttonChangeTimer) {
			g.drawImage(dotdotdot[0],524+30,263,null);
		}else if(System.currentTimeMillis()+1000 < buttonChangeTimer) {
			g.drawImage(dotdotdot[1],524+30,263,null);
		}else if(System.currentTimeMillis() < buttonChangeTimer) {
			g.drawImage(dotdotdot[2],524+30,263,null);
		}else if(buttonToChange != 0) {
			buttonToChange = 0;
			for(int i = 0; i < gamepadButtonHolderHighlighted.length-1; i++)
				gamepadButtonHolderHighlighted[i] = false;
		}
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[0], gamepadButtonHolderClicked[0], backOnGamepadButtonHolder[0], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,Game.WIDTH-51,125);
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[1], gamepadButtonHolderClicked[1], backOnGamepadButtonHolder[1], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,Game.WIDTH-51,170);
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[2], gamepadButtonHolderClicked[2], backOnGamepadButtonHolder[2], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,Game.WIDTH-51,215);
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[3], gamepadButtonHolderClicked[3], backOnGamepadButtonHolder[3], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,Game.WIDTH-51,260);
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[4], gamepadButtonHolderClicked[4], backOnGamepadButtonHolder[4], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,Game.WIDTH-51,305);
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[5], gamepadButtonHolderClicked[5], backOnGamepadButtonHolder[5], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,Game.WIDTH-51,350);
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[6], gamepadButtonHolderClicked[6], backOnGamepadButtonHolder[6], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,Game.WIDTH-51,395);
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[7], gamepadButtonHolderClicked[7], backOnGamepadButtonHolder[7], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,Game.WIDTH-51,440);

		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[8], gamepadButtonHolderClicked[8], backOnGamepadButtonHolder[8], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,385,125);
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[9], gamepadButtonHolderClicked[9], backOnGamepadButtonHolder[9], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,385,170);
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[10], gamepadButtonHolderClicked[10], backOnGamepadButtonHolder[10], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,385,215);
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[11], gamepadButtonHolderClicked[11], backOnGamepadButtonHolder[11], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,385,260);
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[12], gamepadButtonHolderClicked[12], backOnGamepadButtonHolder[12], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,385,305);
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[13], gamepadButtonHolderClicked[13], backOnGamepadButtonHolder[13], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,385,350);
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[14], gamepadButtonHolderClicked[14], backOnGamepadButtonHolder[14], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,385,395);
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[15], gamepadButtonHolderClicked[15], backOnGamepadButtonHolder[15], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,385,440);

		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[16], gamepadButtonHolderClicked[16], backOnGamepadButtonHolder[16], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,501,125);
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[17], gamepadButtonHolderClicked[17], backOnGamepadButtonHolder[17], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,501,170);
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[18], gamepadButtonHolderClicked[18], backOnGamepadButtonHolder[18], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,501,215);
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[19], gamepadButtonHolderClicked[19], backOnGamepadButtonHolder[19], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,501,260);
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[20], gamepadButtonHolderClicked[20], backOnGamepadButtonHolder[20], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,501,305);
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[21], gamepadButtonHolderClicked[21], backOnGamepadButtonHolder[21], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,501,350);
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[22], gamepadButtonHolderClicked[22], backOnGamepadButtonHolder[22], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,501,395);
		HUD.clickyButton(g, gamepadButtonHolder[0], gamepadButtonHolder[1], gamepadButtonHolder[2], gamepadButtonHolderHighlighted[23], gamepadButtonHolderClicked[23], backOnGamepadButtonHolder[23], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,501,440);
		
		if(!(System.currentTimeMillis() < buttonChangeTimer && buttonToChange == -2))
			HUD.clickyButton(g, upKeyWASD[0], upKeyWASD[1], upKeyWASD[2], gamepadButtonHolderHighlighted[0], gamepadButtonHolderClicked[0], backOnGamepadButtonHolder[0], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,Game.WIDTH-51+((gamepadButtonHolder[0].getWidth()-upKeyWASD[0].getWidth())/2),125+((gamepadButtonHolder[0].getHeight()-upKeyWASD[0].getHeight())/2));
		else if(gamepadButtonHolderHighlighted[0] == false)
			gamepadButtonHolderHighlighted[0] = true;
		if(!(System.currentTimeMillis() < buttonChangeTimer && buttonToChange == -3))
			HUD.clickyButton(g, downKeyWASD[0], downKeyWASD[1], downKeyWASD[2], gamepadButtonHolderHighlighted[1], gamepadButtonHolderClicked[1], backOnGamepadButtonHolder[1], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,Game.WIDTH-51+((gamepadButtonHolder[0].getWidth()-downKeyWASD[0].getWidth())/2),170+((gamepadButtonHolder[0].getHeight()-downKeyWASD[0].getHeight())/2));
		else if(gamepadButtonHolderHighlighted[1] == false)
			gamepadButtonHolderHighlighted[1] = true;
		if(!(System.currentTimeMillis() < buttonChangeTimer && buttonToChange == -4))
			HUD.clickyButton(g, leftKeyWASD[0], leftKeyWASD[1], leftKeyWASD[2], gamepadButtonHolderHighlighted[2], gamepadButtonHolderClicked[2], backOnGamepadButtonHolder[2], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,Game.WIDTH-51+((gamepadButtonHolder[0].getWidth()-leftKeyWASD[0].getWidth())/2),215+((gamepadButtonHolder[0].getHeight()-leftKeyWASD[0].getHeight())/2));
		else if(gamepadButtonHolderHighlighted[2] == false)
			gamepadButtonHolderHighlighted[2] = true;
		if(!(System.currentTimeMillis() < buttonChangeTimer && buttonToChange == -5))
			HUD.clickyButton(g, rightKeyWASD[0], rightKeyWASD[1], rightKeyWASD[2], gamepadButtonHolderHighlighted[3], gamepadButtonHolderClicked[3], backOnGamepadButtonHolder[3], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,Game.WIDTH-51+((gamepadButtonHolder[0].getWidth()-rightKeyWASD[0].getWidth())/2),260+((gamepadButtonHolder[0].getHeight()-rightKeyWASD[0].getHeight())/2));
		else if(gamepadButtonHolderHighlighted[3] == false)
			gamepadButtonHolderHighlighted[3] = true;
		if(!(System.currentTimeMillis() < buttonChangeTimer && buttonToChange == -6))
			HUD.clickyButton(g, shootKeyWASD[0], shootKeyWASD[1], shootKeyWASD[2], gamepadButtonHolderHighlighted[4], gamepadButtonHolderClicked[4], backOnGamepadButtonHolder[4], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,Game.WIDTH-51+((gamepadButtonHolder[0].getWidth()-shootKeyWASD[0].getWidth())/2),305+((gamepadButtonHolder[0].getHeight()-shootKeyWASD[0].getHeight())/2));
		else if(gamepadButtonHolderHighlighted[4] == false)
			gamepadButtonHolderHighlighted[4] = true;
		if(!(System.currentTimeMillis() < buttonChangeTimer && buttonToChange == -7))
			HUD.clickyButton(g, itemKeyWASD[0], itemKeyWASD[1], itemKeyWASD[2], gamepadButtonHolderHighlighted[5], gamepadButtonHolderClicked[5], backOnGamepadButtonHolder[5], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,Game.WIDTH-51+((gamepadButtonHolder[0].getWidth()-itemKeyWASD[0].getWidth())/2),350+((gamepadButtonHolder[0].getHeight()-itemKeyWASD[0].getHeight())/2));
		else if(gamepadButtonHolderHighlighted[5] == false)
			gamepadButtonHolderHighlighted[5] = true;
		if(!(System.currentTimeMillis() < buttonChangeTimer && buttonToChange == -8))
			HUD.clickyButton(g, pauseKeyWASD[0], pauseKeyWASD[1], pauseKeyWASD[2], gamepadButtonHolderHighlighted[6], gamepadButtonHolderClicked[6], backOnGamepadButtonHolder[6], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,Game.WIDTH-51+((gamepadButtonHolder[0].getWidth()-pauseKeyWASD[0].getWidth())/2),395+((gamepadButtonHolder[0].getHeight()-pauseKeyWASD[0].getHeight())/2));
		else if(gamepadButtonHolderHighlighted[6] == false)
			gamepadButtonHolderHighlighted[6] = true;
		if(!(System.currentTimeMillis() < buttonChangeTimer && buttonToChange == -9))
			HUD.clickyButton(g, cancelKeyWASD[0], cancelKeyWASD[1], cancelKeyWASD[2], gamepadButtonHolderHighlighted[7], gamepadButtonHolderClicked[7], backOnGamepadButtonHolder[7], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,Game.WIDTH-51+((gamepadButtonHolder[0].getWidth()-cancelKeyWASD[0].getWidth())/2),440+((gamepadButtonHolder[0].getHeight()-cancelKeyWASD[0].getHeight())/2));
		else if(gamepadButtonHolderHighlighted[7] == false)
			gamepadButtonHolderHighlighted[7] = true;
		
		if(!(System.currentTimeMillis() < buttonChangeTimer && buttonToChange == -10))
			HUD.clickyButton(g, upKeyXDevice[0], upKeyXDevice[1], upKeyXDevice[2], gamepadButtonHolderHighlighted[8], gamepadButtonHolderClicked[8], backOnGamepadButtonHolder[8], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,385+((gamepadButtonHolder[0].getWidth()-upKeyXDevice[0].getWidth())/2),125+((gamepadButtonHolder[0].getHeight()-upKeyXDevice[0].getHeight())/2));
		else if(gamepadButtonHolderHighlighted[8] == false)
			gamepadButtonHolderHighlighted[8] = true;
		if(!(System.currentTimeMillis() < buttonChangeTimer && buttonToChange == -11))
			HUD.clickyButton(g, downKeyXDevice[0], downKeyXDevice[1], downKeyXDevice[2], gamepadButtonHolderHighlighted[9], gamepadButtonHolderClicked[9], backOnGamepadButtonHolder[9], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,385+((gamepadButtonHolder[0].getWidth()-downKeyXDevice[0].getWidth())/2),170+((gamepadButtonHolder[0].getHeight()-downKeyXDevice[0].getHeight())/2));
		else if(gamepadButtonHolderHighlighted[9] == false)
			gamepadButtonHolderHighlighted[9] = true;
		if(!(System.currentTimeMillis() < buttonChangeTimer && buttonToChange == -12))
			HUD.clickyButton(g, leftKeyXDevice[0], leftKeyXDevice[1], leftKeyXDevice[2], gamepadButtonHolderHighlighted[10], gamepadButtonHolderClicked[10], backOnGamepadButtonHolder[10], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,385+((gamepadButtonHolder[0].getWidth()-leftKeyXDevice[0].getWidth())/2),215+((gamepadButtonHolder[0].getHeight()-leftKeyXDevice[0].getHeight())/2));
		else if(gamepadButtonHolderHighlighted[10] == false)
			gamepadButtonHolderHighlighted[10] = true;
		if(!(System.currentTimeMillis() < buttonChangeTimer && buttonToChange == -13))
			HUD.clickyButton(g, rightKeyXDevice[0], rightKeyXDevice[1], rightKeyXDevice[2], gamepadButtonHolderHighlighted[11], gamepadButtonHolderClicked[11], backOnGamepadButtonHolder[11], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,385+((gamepadButtonHolder[0].getWidth()-rightKeyXDevice[0].getWidth())/2),260+((gamepadButtonHolder[0].getHeight()-rightKeyXDevice[0].getHeight())/2));
		else if(gamepadButtonHolderHighlighted[11] == false)
			gamepadButtonHolderHighlighted[11] = true;
		if(!(System.currentTimeMillis() < buttonChangeTimer && buttonToChange == -14))
			HUD.clickyButton(g, shootKeyXDevice[0], shootKeyXDevice[1], shootKeyXDevice[2], gamepadButtonHolderHighlighted[12], gamepadButtonHolderClicked[12], backOnGamepadButtonHolder[12], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,385+((gamepadButtonHolder[0].getWidth()-shootKeyXDevice[0].getWidth())/2),305+((gamepadButtonHolder[0].getHeight()-shootKeyXDevice[0].getHeight())/2));
		else if(gamepadButtonHolderHighlighted[12] == false)
			gamepadButtonHolderHighlighted[12] = true;
		if(!(System.currentTimeMillis() < buttonChangeTimer && buttonToChange == -15))
			HUD.clickyButton(g, itemKeyXDevice[0], itemKeyXDevice[1], itemKeyXDevice[2], gamepadButtonHolderHighlighted[13], gamepadButtonHolderClicked[13], backOnGamepadButtonHolder[13], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,385+((gamepadButtonHolder[0].getWidth()-itemKeyXDevice[0].getWidth())/2),350+((gamepadButtonHolder[0].getHeight()-itemKeyXDevice[0].getHeight())/2));
		else if(gamepadButtonHolderHighlighted[13] == false)
			gamepadButtonHolderHighlighted[13] = true;
		if(!(System.currentTimeMillis() < buttonChangeTimer && buttonToChange == -16))
			HUD.clickyButton(g, pauseKeyXDevice[0], pauseKeyXDevice[1], pauseKeyXDevice[2], gamepadButtonHolderHighlighted[14], gamepadButtonHolderClicked[14], backOnGamepadButtonHolder[14], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,385+((gamepadButtonHolder[0].getWidth()-pauseKeyXDevice[0].getWidth())/2),395+((gamepadButtonHolder[0].getHeight()-pauseKeyXDevice[0].getHeight())/2));
		else if(gamepadButtonHolderHighlighted[14] == false)
			gamepadButtonHolderHighlighted[14] = true;
		if(!(System.currentTimeMillis() < buttonChangeTimer && buttonToChange == -17))
			HUD.clickyButton(g, cancelKeyXDevice[0], cancelKeyXDevice[1], cancelKeyXDevice[2], gamepadButtonHolderHighlighted[15], gamepadButtonHolderClicked[15], backOnGamepadButtonHolder[15], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,385+((gamepadButtonHolder[0].getWidth()-cancelKeyXDevice[0].getWidth())/2),440+((gamepadButtonHolder[0].getHeight()-cancelKeyXDevice[0].getHeight())/2));
		else if(gamepadButtonHolderHighlighted[15] == false)
			gamepadButtonHolderHighlighted[15] = true;
		
		HUD.clickyButton(g, upKeyDirectInput[0], upKeyDirectInput[1], upKeyDirectInput[2], gamepadButtonHolderHighlighted[16], gamepadButtonHolderClicked[16], backOnGamepadButtonHolder[16], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,501+((gamepadButtonHolder[0].getWidth()-upKeyDirectInput[0].getWidth())/2),125+((gamepadButtonHolder[0].getHeight()-upKeyDirectInput[0].getHeight())/2));
		HUD.clickyButton(g, downKeyDirectInput[0], downKeyDirectInput[1], downKeyDirectInput[2], gamepadButtonHolderHighlighted[17], gamepadButtonHolderClicked[17], backOnGamepadButtonHolder[17], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,501+((gamepadButtonHolder[0].getWidth()-downKeyDirectInput[0].getWidth())/2),170+((gamepadButtonHolder[0].getHeight()-downKeyDirectInput[0].getHeight())/2));
		HUD.clickyButton(g, leftKeyDirectInput[0], leftKeyDirectInput[1], leftKeyDirectInput[2], gamepadButtonHolderHighlighted[18], gamepadButtonHolderClicked[18], backOnGamepadButtonHolder[18], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,501+((gamepadButtonHolder[0].getWidth()-leftKeyDirectInput[0].getWidth())/2),215+((gamepadButtonHolder[0].getHeight()-leftKeyDirectInput[0].getHeight())/2));
		HUD.clickyButton(g, rightKeyDirectInput[0], rightKeyDirectInput[1], rightKeyDirectInput[2], gamepadButtonHolderHighlighted[19], gamepadButtonHolderClicked[19], backOnGamepadButtonHolder[19], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,501+((gamepadButtonHolder[0].getWidth()-rightKeyDirectInput[0].getWidth())/2),260+((gamepadButtonHolder[0].getHeight()-rightKeyDirectInput[0].getHeight())/2));
		HUD.clickyButton(g, shootKeyDirectInput[0], shootKeyDirectInput[1], shootKeyDirectInput[2], gamepadButtonHolderHighlighted[20], gamepadButtonHolderClicked[20], backOnGamepadButtonHolder[20], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,501+((gamepadButtonHolder[0].getWidth()-shootKeyDirectInput[0].getWidth())/2),305+((gamepadButtonHolder[0].getHeight()-shootKeyDirectInput[0].getHeight())/2));
		HUD.clickyButton(g, itemKeyDirectInput[0], itemKeyDirectInput[1], itemKeyDirectInput[2], gamepadButtonHolderHighlighted[21], gamepadButtonHolderClicked[21], backOnGamepadButtonHolder[21], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,501+((gamepadButtonHolder[0].getWidth()-itemKeyDirectInput[0].getWidth())/2),350+((gamepadButtonHolder[0].getHeight()-itemKeyDirectInput[0].getHeight())/2));
		HUD.clickyButton(g, pauseKeyDirectInput[0], pauseKeyDirectInput[1], pauseKeyDirectInput[2], gamepadButtonHolderHighlighted[22], gamepadButtonHolderClicked[22], backOnGamepadButtonHolder[22], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,501+((gamepadButtonHolder[0].getWidth()-pauseKeyDirectInput[0].getWidth())/2),395+((gamepadButtonHolder[0].getHeight()-pauseKeyDirectInput[0].getHeight())/2));
		HUD.clickyButton(g, cancelKeyDirectInput[0], cancelKeyDirectInput[1], cancelKeyDirectInput[2], gamepadButtonHolderHighlighted[23], gamepadButtonHolderClicked[23], backOnGamepadButtonHolder[23], Game.mouseIsOffClickedObjectAndHeldDown, Game.mouseIsClickedDown,501+((gamepadButtonHolder[0].getWidth()-cancelKeyDirectInput[0].getWidth())/2),440+((gamepadButtonHolder[0].getHeight()-cancelKeyDirectInput[0].getHeight())/2));
		return;
	}
	public void xInputImageSorter(short xInputConstant, BufferedImage[] image) {
		if(XInputDevice.a == xInputConstant)
			tex.changeBufferedImagesXInput(image, XInputConstants.XINPUT_GAMEPAD_A);
		else if(XInputDevice.b == xInputConstant)
			tex.changeBufferedImagesXInput(image, XInputConstants.XINPUT_GAMEPAD_B);
		else if(XInputDevice.x == xInputConstant)
			tex.changeBufferedImagesXInput(image, XInputConstants.XINPUT_GAMEPAD_X);
		else if(XInputDevice.y == xInputConstant)
			tex.changeBufferedImagesXInput(image, XInputConstants.XINPUT_GAMEPAD_Y);
		else if(XInputDevice.up == xInputConstant)
			tex.changeBufferedImagesXInput(image, XInputConstants.XINPUT_GAMEPAD_DPAD_UP);
		else if(XInputDevice.down == xInputConstant)
			tex.changeBufferedImagesXInput(image, XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN);
		else if(XInputDevice.left == xInputConstant)
			tex.changeBufferedImagesXInput(image, XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT);
		else if(XInputDevice.right == xInputConstant)
			tex.changeBufferedImagesXInput(image, XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT);
		else if(XInputDevice.lShoulder == xInputConstant)
			tex.changeBufferedImagesXInput(image, XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER);
		else if(XInputDevice.rShoulder == xInputConstant)
			tex.changeBufferedImagesXInput(image, XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER);
		else if(XInputDevice.lThumb == xInputConstant)
			tex.changeBufferedImagesXInput(image, XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB);
		else if(XInputDevice.rThumb == xInputConstant)
			tex.changeBufferedImagesXInput(image, XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB);
		else if(XInputDevice.start == xInputConstant)
			tex.changeBufferedImagesXInput(image, XInputConstants.XINPUT_GAMEPAD_START);
		else if(XInputDevice.back == xInputConstant)
			tex.changeBufferedImagesXInput(image, XInputConstants.XINPUT_GAMEPAD_BACK);
		else if(XInputDevice.guide == xInputConstant)
			tex.changeBufferedImagesXInput(image, XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON);
		else if(XInputDevice.unknown == xInputConstant)
			tex.changeBufferedImagesXInput(image, XInputConstants.XINPUT_GAMEPAD_UNKNOWN);
		return;
	}
	public void updateControls() {
		switch(Game.upKey) {
		case KeyEvent.VK_A:
			upKeyInt = 0;
			break;
		case KeyEvent.VK_B:
			upKeyInt = 1;
			break;
		case KeyEvent.VK_C:
			upKeyInt = 2;
			break;
		case KeyEvent.VK_D:
			upKeyInt = 3;
			break;
		case KeyEvent.VK_E:
			upKeyInt = 4;
			break;
		case KeyEvent.VK_F:
			upKeyInt = 5;
			break;
		case KeyEvent.VK_G:
			upKeyInt = 6;
			break;
		case KeyEvent.VK_H:
			upKeyInt = 7;
			break;
		case KeyEvent.VK_I:
			upKeyInt = 8;
			break;
		case KeyEvent.VK_J:
			upKeyInt = 9;
			break;
		case KeyEvent.VK_K:
			upKeyInt = 10;
			break;
		case KeyEvent.VK_L:
			upKeyInt = 11;
			break;
		case KeyEvent.VK_M:
			upKeyInt = 12;
			break;
		case KeyEvent.VK_N:
			upKeyInt = 13;
			break;
		case KeyEvent.VK_O:
			upKeyInt = 14;
			break;
		case KeyEvent.VK_P:
			upKeyInt = 15;
			break;
		case KeyEvent.VK_Q:
			upKeyInt = 16;
			break;
		case KeyEvent.VK_R:
			upKeyInt = 17;
			break;
		case KeyEvent.VK_S:
			upKeyInt = 18;
			break;
		case KeyEvent.VK_T:
			upKeyInt = 19;
			break;
		case KeyEvent.VK_U:
			upKeyInt = 20;
			break;
		case KeyEvent.VK_V:
			upKeyInt = 21;
			break;
		case KeyEvent.VK_W:
			upKeyInt = 22;
			break;
		case KeyEvent.VK_X:
			upKeyInt = 23;
			break;
		case KeyEvent.VK_Y:
			upKeyInt = 24;
			break;
		case KeyEvent.VK_Z:
			upKeyInt = 25;
			break;
		case KeyEvent.VK_0:
			upKeyInt = 26;
			break;
		case KeyEvent.VK_1:
			upKeyInt = 27;
			break;
		case KeyEvent.VK_2:
			upKeyInt = 28;
			break;
		case KeyEvent.VK_3:
			upKeyInt = 29;
			break;
		case KeyEvent.VK_4:
			upKeyInt = 30;
			break;
		case KeyEvent.VK_5:
			upKeyInt = 31;
			break;
		case KeyEvent.VK_6:
			upKeyInt = 32;
			break;
		case KeyEvent.VK_7:
			upKeyInt = 33;
			break;
		case KeyEvent.VK_8:
			upKeyInt = 34;
			break;
		case KeyEvent.VK_9:
			upKeyInt = 35;
			break;
		case KeyEvent.VK_NUMPAD0:
			upKeyInt = 36;
			break;
		case KeyEvent.VK_NUMPAD1:
			upKeyInt = 37;
			break;
		case KeyEvent.VK_NUMPAD2:
			upKeyInt = 38;
			break;
		case KeyEvent.VK_NUMPAD3:
			upKeyInt = 39;
			break;
		case KeyEvent.VK_NUMPAD4:
			upKeyInt = 40;
			break;
		case KeyEvent.VK_NUMPAD5:
			upKeyInt = 41;
			break;
		case KeyEvent.VK_NUMPAD6:
			upKeyInt = 42;
			break;
		case KeyEvent.VK_NUMPAD7:
			upKeyInt = 43;
			break;
		case KeyEvent.VK_NUMPAD8:
			upKeyInt = 44;
			break;
		case KeyEvent.VK_NUMPAD9:
			upKeyInt = 45;
			break;
		case KeyEvent.VK_SPACE:
			upKeyInt = 46;
			break;
		case KeyEvent.VK_BACK_SPACE:
			upKeyInt = 47;
			break;
		case KeyEvent.VK_ESCAPE:
			upKeyInt = 48;
			break;
		case KeyEvent.VK_SHIFT:
			upKeyInt = 49;
			break;
		case KeyEvent.VK_ENTER:
			upKeyInt = 50;
			break;
		case KeyEvent.VK_COMMA:
			upKeyInt = 51;
			break;
		case KeyEvent.VK_PERIOD:
			upKeyInt = 52;
			break;
		case KeyEvent.VK_SLASH:
			upKeyInt = 53;
			break;
		case KeyEvent.VK_UP: case KeyEvent.VK_KP_UP:
			upKeyInt = 54;
			break;
		case KeyEvent.VK_DOWN: case KeyEvent.VK_KP_DOWN:
			upKeyInt = 55;
			break;
		case KeyEvent.VK_LEFT: case KeyEvent.VK_KP_LEFT:
			upKeyInt = 56;
			break;
		case KeyEvent.VK_RIGHT: case KeyEvent.VK_KP_RIGHT:
			upKeyInt = 57;
			break;
		default:
			upKeyInt = 22;
			break;
		}

		switch(Game.downKey) {
		case KeyEvent.VK_A:
			downKeyInt = 0;
			break;
		case KeyEvent.VK_B:
			downKeyInt = 1;
			break;
		case KeyEvent.VK_C:
			downKeyInt = 2;
			break;
		case KeyEvent.VK_D:
			downKeyInt = 3;
			break;
		case KeyEvent.VK_E:
			downKeyInt = 4;
			break;
		case KeyEvent.VK_F:
			downKeyInt = 5;
			break;
		case KeyEvent.VK_G:
			downKeyInt = 6;
			break;
		case KeyEvent.VK_H:
			downKeyInt = 7;
			break;
		case KeyEvent.VK_I:
			downKeyInt = 8;
			break;
		case KeyEvent.VK_J:
			downKeyInt = 9;
			break;
		case KeyEvent.VK_K:
			downKeyInt = 10;
			break;
		case KeyEvent.VK_L:
			downKeyInt = 11;
			break;
		case KeyEvent.VK_M:
			downKeyInt = 12;
			break;
		case KeyEvent.VK_N:
			downKeyInt = 13;
			break;
		case KeyEvent.VK_O:
			downKeyInt = 14;
			break;
		case KeyEvent.VK_P:
			downKeyInt = 15;
			break;
		case KeyEvent.VK_Q:
			downKeyInt = 16;
			break;
		case KeyEvent.VK_R:
			downKeyInt = 17;
			break;
		case KeyEvent.VK_S:
			downKeyInt = 18;
			break;
		case KeyEvent.VK_T:
			downKeyInt = 19;
			break;
		case KeyEvent.VK_U:
			downKeyInt = 20;
			break;
		case KeyEvent.VK_V:
			downKeyInt = 21;
			break;
		case KeyEvent.VK_W:
			downKeyInt = 22;
			break;
		case KeyEvent.VK_X:
			downKeyInt = 23;
			break;
		case KeyEvent.VK_Y:
			downKeyInt = 24;
			break;
		case KeyEvent.VK_Z:
			downKeyInt = 25;
			break;
		case KeyEvent.VK_0:
			downKeyInt = 26;
			break;
		case KeyEvent.VK_1:
			downKeyInt = 27;
			break;
		case KeyEvent.VK_2:
			downKeyInt = 28;
			break;
		case KeyEvent.VK_3:
			downKeyInt = 29;
			break;
		case KeyEvent.VK_4:
			downKeyInt = 30;
			break;
		case KeyEvent.VK_5:
			downKeyInt = 31;
			break;
		case KeyEvent.VK_6:
			downKeyInt = 32;
			break;
		case KeyEvent.VK_7:
			downKeyInt = 33;
			break;
		case KeyEvent.VK_8:
			downKeyInt = 34;
			break;
		case KeyEvent.VK_9:
			downKeyInt = 35;
			break;
		case KeyEvent.VK_NUMPAD0:
			downKeyInt = 36;
			break;
		case KeyEvent.VK_NUMPAD1:
			downKeyInt = 37;
			break;
		case KeyEvent.VK_NUMPAD2:
			downKeyInt = 38;
			break;
		case KeyEvent.VK_NUMPAD3:
			downKeyInt = 39;
			break;
		case KeyEvent.VK_NUMPAD4:
			downKeyInt = 40;
			break;
		case KeyEvent.VK_NUMPAD5:
			downKeyInt = 41;
			break;
		case KeyEvent.VK_NUMPAD6:
			downKeyInt = 42;
			break;
		case KeyEvent.VK_NUMPAD7:
			downKeyInt = 43;
			break;
		case KeyEvent.VK_NUMPAD8:
			downKeyInt = 44;
			break;
		case KeyEvent.VK_NUMPAD9:
			downKeyInt = 45;
			break;
		case KeyEvent.VK_SPACE:
			downKeyInt = 46;
			break;
		case KeyEvent.VK_BACK_SPACE:
			downKeyInt = 47;
			break;
		case KeyEvent.VK_ESCAPE:
			downKeyInt = 48;
			break;
		case KeyEvent.VK_SHIFT:
			downKeyInt = 49;
			break;
		case KeyEvent.VK_ENTER:
			downKeyInt = 50;
			break;
		case KeyEvent.VK_COMMA:
			downKeyInt = 51;
			break;
		case KeyEvent.VK_PERIOD:
			downKeyInt = 52;
			break;
		case KeyEvent.VK_SLASH:
			downKeyInt = 53;
			break;
		case KeyEvent.VK_UP: case KeyEvent.VK_KP_UP:
			downKeyInt = 54;
			break;
		case KeyEvent.VK_DOWN: case KeyEvent.VK_KP_DOWN:
			downKeyInt = 55;
			break;
		case KeyEvent.VK_LEFT: case KeyEvent.VK_KP_LEFT:
			downKeyInt = 56;
			break;
		case KeyEvent.VK_RIGHT: case KeyEvent.VK_KP_RIGHT:
			downKeyInt = 57;
			break;
		default:
			downKeyInt = 18;
			break;
		}

		switch(Game.leftKey) {
		case KeyEvent.VK_A:
			leftKeyInt = 0;
			break;
		case KeyEvent.VK_B:
			leftKeyInt = 1;
			break;
		case KeyEvent.VK_C:
			leftKeyInt = 2;
			break;
		case KeyEvent.VK_D:
			leftKeyInt = 3;
			break;
		case KeyEvent.VK_E:
			leftKeyInt = 4;
			break;
		case KeyEvent.VK_F:
			leftKeyInt = 5;
			break;
		case KeyEvent.VK_G:
			leftKeyInt = 6;
			break;
		case KeyEvent.VK_H:
			leftKeyInt = 7;
			break;
		case KeyEvent.VK_I:
			leftKeyInt = 8;
			break;
		case KeyEvent.VK_J:
			leftKeyInt = 9;
			break;
		case KeyEvent.VK_K:
			leftKeyInt = 10;
			break;
		case KeyEvent.VK_L:
			leftKeyInt = 11;
			break;
		case KeyEvent.VK_M:
			leftKeyInt = 12;
			break;
		case KeyEvent.VK_N:
			leftKeyInt = 13;
			break;
		case KeyEvent.VK_O:
			leftKeyInt = 14;
			break;
		case KeyEvent.VK_P:
			leftKeyInt = 15;
			break;
		case KeyEvent.VK_Q:
			leftKeyInt = 16;
			break;
		case KeyEvent.VK_R:
			leftKeyInt = 17;
			break;
		case KeyEvent.VK_S:
			leftKeyInt = 18;
			break;
		case KeyEvent.VK_T:
			leftKeyInt = 19;
			break;
		case KeyEvent.VK_U:
			leftKeyInt = 20;
			break;
		case KeyEvent.VK_V:
			leftKeyInt = 21;
			break;
		case KeyEvent.VK_W:
			leftKeyInt = 22;
			break;
		case KeyEvent.VK_X:
			leftKeyInt = 23;
			break;
		case KeyEvent.VK_Y:
			leftKeyInt = 24;
			break;
		case KeyEvent.VK_Z:
			leftKeyInt = 25;
			break;
		case KeyEvent.VK_0:
			leftKeyInt = 26;
			break;
		case KeyEvent.VK_1:
			leftKeyInt = 27;
			break;
		case KeyEvent.VK_2:
			leftKeyInt = 28;
			break;
		case KeyEvent.VK_3:
			leftKeyInt = 29;
			break;
		case KeyEvent.VK_4:
			leftKeyInt = 30;
			break;
		case KeyEvent.VK_5:
			leftKeyInt = 31;
			break;
		case KeyEvent.VK_6:
			leftKeyInt = 32;
			break;
		case KeyEvent.VK_7:
			leftKeyInt = 33;
			break;
		case KeyEvent.VK_8:
			leftKeyInt = 34;
			break;
		case KeyEvent.VK_9:
			leftKeyInt = 35;
			break;
		case KeyEvent.VK_NUMPAD0:
			leftKeyInt = 36;
			break;
		case KeyEvent.VK_NUMPAD1:
			leftKeyInt = 37;
			break;
		case KeyEvent.VK_NUMPAD2:
			leftKeyInt = 38;
			break;
		case KeyEvent.VK_NUMPAD3:
			leftKeyInt = 39;
			break;
		case KeyEvent.VK_NUMPAD4:
			leftKeyInt = 40;
			break;
		case KeyEvent.VK_NUMPAD5:
			leftKeyInt = 41;
			break;
		case KeyEvent.VK_NUMPAD6:
			leftKeyInt = 42;
			break;
		case KeyEvent.VK_NUMPAD7:
			leftKeyInt = 43;
			break;
		case KeyEvent.VK_NUMPAD8:
			leftKeyInt = 44;
			break;
		case KeyEvent.VK_NUMPAD9:
			leftKeyInt = 45;
			break;
		case KeyEvent.VK_SPACE:
			leftKeyInt = 46;
			break;
		case KeyEvent.VK_BACK_SPACE:
			leftKeyInt = 47;
			break;
		case KeyEvent.VK_ESCAPE:
			leftKeyInt = 48;
			break;
		case KeyEvent.VK_SHIFT:
			leftKeyInt = 49;
			break;
		case KeyEvent.VK_ENTER:
			leftKeyInt = 50;
			break;
		case KeyEvent.VK_COMMA:
			leftKeyInt = 51;
			break;
		case KeyEvent.VK_PERIOD:
			leftKeyInt = 52;
			break;
		case KeyEvent.VK_SLASH:
			leftKeyInt = 53;
			break;
		case KeyEvent.VK_UP: case KeyEvent.VK_KP_UP:
			leftKeyInt = 54;
			break;
		case KeyEvent.VK_DOWN: case KeyEvent.VK_KP_DOWN:
			leftKeyInt = 55;
			break;
		case KeyEvent.VK_LEFT: case KeyEvent.VK_KP_LEFT:
			leftKeyInt = 56;
			break;
		case KeyEvent.VK_RIGHT: case KeyEvent.VK_KP_RIGHT:
			leftKeyInt = 57;
			break;
		default:
			leftKeyInt = 0;
			break;
		}

		switch(Game.rightKey) {
		case KeyEvent.VK_A:
			rightKeyInt = 0;
			break;
		case KeyEvent.VK_B:
			rightKeyInt = 1;
			break;
		case KeyEvent.VK_C:
			rightKeyInt = 2;
			break;
		case KeyEvent.VK_D:
			rightKeyInt = 3;
			break;
		case KeyEvent.VK_E:
			rightKeyInt = 4;
			break;
		case KeyEvent.VK_F:
			rightKeyInt = 5;
			break;
		case KeyEvent.VK_G:
			rightKeyInt = 6;
			break;
		case KeyEvent.VK_H:
			rightKeyInt = 7;
			break;
		case KeyEvent.VK_I:
			rightKeyInt = 8;
			break;
		case KeyEvent.VK_J:
			rightKeyInt = 9;
			break;
		case KeyEvent.VK_K:
			rightKeyInt = 10;
			break;
		case KeyEvent.VK_L:
			rightKeyInt = 11;
			break;
		case KeyEvent.VK_M:
			rightKeyInt = 12;
			break;
		case KeyEvent.VK_N:
			rightKeyInt = 13;
			break;
		case KeyEvent.VK_O:
			rightKeyInt = 14;
			break;
		case KeyEvent.VK_P:
			rightKeyInt = 15;
			break;
		case KeyEvent.VK_Q:
			rightKeyInt = 16;
			break;
		case KeyEvent.VK_R:
			rightKeyInt = 17;
			break;
		case KeyEvent.VK_S:
			rightKeyInt = 18;
			break;
		case KeyEvent.VK_T:
			rightKeyInt = 19;
			break;
		case KeyEvent.VK_U:
			rightKeyInt = 20;
			break;
		case KeyEvent.VK_V:
			rightKeyInt = 21;
			break;
		case KeyEvent.VK_W:
			rightKeyInt = 22;
			break;
		case KeyEvent.VK_X:
			rightKeyInt = 23;
			break;
		case KeyEvent.VK_Y:
			rightKeyInt = 24;
			break;
		case KeyEvent.VK_Z:
			rightKeyInt = 25;
			break;
		case KeyEvent.VK_0:
			rightKeyInt = 26;
			break;
		case KeyEvent.VK_1:
			rightKeyInt = 27;
			break;
		case KeyEvent.VK_2:
			rightKeyInt = 28;
			break;
		case KeyEvent.VK_3:
			rightKeyInt = 29;
			break;
		case KeyEvent.VK_4:
			rightKeyInt = 30;
			break;
		case KeyEvent.VK_5:
			rightKeyInt = 31;
			break;
		case KeyEvent.VK_6:
			rightKeyInt = 32;
			break;
		case KeyEvent.VK_7:
			rightKeyInt = 33;
			break;
		case KeyEvent.VK_8:
			rightKeyInt = 34;
			break;
		case KeyEvent.VK_9:
			rightKeyInt = 35;
			break;
		case KeyEvent.VK_NUMPAD0:
			rightKeyInt = 36;
			break;
		case KeyEvent.VK_NUMPAD1:
			rightKeyInt = 37;
			break;
		case KeyEvent.VK_NUMPAD2:
			rightKeyInt = 38;
			break;
		case KeyEvent.VK_NUMPAD3:
			rightKeyInt = 39;
			break;
		case KeyEvent.VK_NUMPAD4:
			rightKeyInt = 40;
			break;
		case KeyEvent.VK_NUMPAD5:
			rightKeyInt = 41;
			break;
		case KeyEvent.VK_NUMPAD6:
			rightKeyInt = 42;
			break;
		case KeyEvent.VK_NUMPAD7:
			rightKeyInt = 43;
			break;
		case KeyEvent.VK_NUMPAD8:
			rightKeyInt = 44;
			break;
		case KeyEvent.VK_NUMPAD9:
			rightKeyInt = 45;
			break;
		case KeyEvent.VK_SPACE:
			rightKeyInt = 46;
			break;
		case KeyEvent.VK_BACK_SPACE:
			rightKeyInt = 47;
			break;
		case KeyEvent.VK_ESCAPE:
			rightKeyInt = 48;
			break;
		case KeyEvent.VK_SHIFT:
			rightKeyInt = 49;
			break;
		case KeyEvent.VK_ENTER:
			rightKeyInt = 50;
			break;
		case KeyEvent.VK_COMMA:
			rightKeyInt = 51;
			break;
		case KeyEvent.VK_PERIOD:
			rightKeyInt = 52;
			break;
		case KeyEvent.VK_SLASH:
			rightKeyInt = 53;
			break;
		case KeyEvent.VK_UP: case KeyEvent.VK_KP_UP:
			rightKeyInt = 54;
			break;
		case KeyEvent.VK_DOWN: case KeyEvent.VK_KP_DOWN:
			rightKeyInt = 55;
			break;
		case KeyEvent.VK_LEFT: case KeyEvent.VK_KP_LEFT:
			rightKeyInt = 56;
			break;
		case KeyEvent.VK_RIGHT: case KeyEvent.VK_KP_RIGHT:
			rightKeyInt = 57;
			break;
		default:
			rightKeyInt = 3;
			break;
		}

		switch(Game.shootKey) {
		case KeyEvent.VK_A:
			shootKeyInt = 0;
			break;
		case KeyEvent.VK_B:
			shootKeyInt = 1;
			break;
		case KeyEvent.VK_C:
			shootKeyInt = 2;
			break;
		case KeyEvent.VK_D:
			shootKeyInt = 3;
			break;
		case KeyEvent.VK_E:
			shootKeyInt = 4;
			break;
		case KeyEvent.VK_F:
			shootKeyInt = 5;
			break;
		case KeyEvent.VK_G:
			shootKeyInt = 6;
			break;
		case KeyEvent.VK_H:
			shootKeyInt = 7;
			break;
		case KeyEvent.VK_I:
			shootKeyInt = 8;
			break;
		case KeyEvent.VK_J:
			shootKeyInt = 9;
			break;
		case KeyEvent.VK_K:
			shootKeyInt = 10;
			break;
		case KeyEvent.VK_L:
			shootKeyInt = 11;
			break;
		case KeyEvent.VK_M:
			shootKeyInt = 12;
			break;
		case KeyEvent.VK_N:
			shootKeyInt = 13;
			break;
		case KeyEvent.VK_O:
			shootKeyInt = 14;
			break;
		case KeyEvent.VK_P:
			shootKeyInt = 15;
			break;
		case KeyEvent.VK_Q:
			shootKeyInt = 16;
			break;
		case KeyEvent.VK_R:
			shootKeyInt = 17;
			break;
		case KeyEvent.VK_S:
			shootKeyInt = 18;
			break;
		case KeyEvent.VK_T:
			shootKeyInt = 19;
			break;
		case KeyEvent.VK_U:
			shootKeyInt = 20;
			break;
		case KeyEvent.VK_V:
			shootKeyInt = 21;
			break;
		case KeyEvent.VK_W:
			shootKeyInt = 22;
			break;
		case KeyEvent.VK_X:
			shootKeyInt = 23;
			break;
		case KeyEvent.VK_Y:
			shootKeyInt = 24;
			break;
		case KeyEvent.VK_Z:
			shootKeyInt = 25;
			break;
		case KeyEvent.VK_0:
			shootKeyInt = 26;
			break;
		case KeyEvent.VK_1:
			shootKeyInt = 27;
			break;
		case KeyEvent.VK_2:
			shootKeyInt = 28;
			break;
		case KeyEvent.VK_3:
			shootKeyInt = 29;
			break;
		case KeyEvent.VK_4:
			shootKeyInt = 30;
			break;
		case KeyEvent.VK_5:
			shootKeyInt = 31;
			break;
		case KeyEvent.VK_6:
			shootKeyInt = 32;
			break;
		case KeyEvent.VK_7:
			shootKeyInt = 33;
			break;
		case KeyEvent.VK_8:
			shootKeyInt = 34;
			break;
		case KeyEvent.VK_9:
			shootKeyInt = 35;
			break;
		case KeyEvent.VK_NUMPAD0:
			shootKeyInt = 36;
			break;
		case KeyEvent.VK_NUMPAD1:
			shootKeyInt = 37;
			break;
		case KeyEvent.VK_NUMPAD2:
			shootKeyInt = 38;
			break;
		case KeyEvent.VK_NUMPAD3:
			shootKeyInt = 39;
			break;
		case KeyEvent.VK_NUMPAD4:
			shootKeyInt = 40;
			break;
		case KeyEvent.VK_NUMPAD5:
			shootKeyInt = 41;
			break;
		case KeyEvent.VK_NUMPAD6:
			shootKeyInt = 42;
			break;
		case KeyEvent.VK_NUMPAD7:
			shootKeyInt = 43;
			break;
		case KeyEvent.VK_NUMPAD8:
			shootKeyInt = 44;
			break;
		case KeyEvent.VK_NUMPAD9:
			shootKeyInt = 45;
			break;
		case KeyEvent.VK_SPACE:
			shootKeyInt = 46;
			break;
		case KeyEvent.VK_BACK_SPACE:
			shootKeyInt = 47;
			break;
		case KeyEvent.VK_ESCAPE:
			shootKeyInt = 48;
			break;
		case KeyEvent.VK_SHIFT:
			shootKeyInt = 49;
			break;
		case KeyEvent.VK_ENTER:
			shootKeyInt = 50;
			break;
		case KeyEvent.VK_COMMA:
			shootKeyInt = 51;
			break;
		case KeyEvent.VK_PERIOD:
			shootKeyInt = 52;
			break;
		case KeyEvent.VK_SLASH:
			shootKeyInt = 53;
			break;
		case KeyEvent.VK_UP: case KeyEvent.VK_KP_UP:
			shootKeyInt = 54;
			break;
		case KeyEvent.VK_DOWN: case KeyEvent.VK_KP_DOWN:
			shootKeyInt = 55;
			break;
		case KeyEvent.VK_LEFT: case KeyEvent.VK_KP_LEFT:
			shootKeyInt = 56;
			break;
		case KeyEvent.VK_RIGHT: case KeyEvent.VK_KP_RIGHT:
			shootKeyInt = 57;
			break;
		default:
			shootKeyInt = 46;
			break;
		}

		switch(Game.itemKey) {
		case KeyEvent.VK_A:
			itemKeyInt = 0;
			break;
		case KeyEvent.VK_B:
			itemKeyInt = 1;
			break;
		case KeyEvent.VK_C:
			itemKeyInt = 2;
			break;
		case KeyEvent.VK_D:
			itemKeyInt = 3;
			break;
		case KeyEvent.VK_E:
			itemKeyInt = 4;
			break;
		case KeyEvent.VK_F:
			itemKeyInt = 5;
			break;
		case KeyEvent.VK_G:
			itemKeyInt = 6;
			break;
		case KeyEvent.VK_H:
			itemKeyInt = 7;
			break;
		case KeyEvent.VK_I:
			itemKeyInt = 8;
			break;
		case KeyEvent.VK_J:
			itemKeyInt = 9;
			break;
		case KeyEvent.VK_K:
			itemKeyInt = 10;
			break;
		case KeyEvent.VK_L:
			itemKeyInt = 11;
			break;
		case KeyEvent.VK_M:
			itemKeyInt = 12;
			break;
		case KeyEvent.VK_N:
			itemKeyInt = 13;
			break;
		case KeyEvent.VK_O:
			itemKeyInt = 14;
			break;
		case KeyEvent.VK_P:
			itemKeyInt = 15;
			break;
		case KeyEvent.VK_Q:
			itemKeyInt = 16;
			break;
		case KeyEvent.VK_R:
			itemKeyInt = 17;
			break;
		case KeyEvent.VK_S:
			itemKeyInt = 18;
			break;
		case KeyEvent.VK_T:
			itemKeyInt = 19;
			break;
		case KeyEvent.VK_U:
			itemKeyInt = 20;
			break;
		case KeyEvent.VK_V:
			itemKeyInt = 21;
			break;
		case KeyEvent.VK_W:
			itemKeyInt = 22;
			break;
		case KeyEvent.VK_X:
			itemKeyInt = 23;
			break;
		case KeyEvent.VK_Y:
			itemKeyInt = 24;
			break;
		case KeyEvent.VK_Z:
			itemKeyInt = 25;
			break;
		case KeyEvent.VK_0:
			itemKeyInt = 26;
			break;
		case KeyEvent.VK_1:
			itemKeyInt = 27;
			break;
		case KeyEvent.VK_2:
			itemKeyInt = 28;
			break;
		case KeyEvent.VK_3:
			itemKeyInt = 29;
			break;
		case KeyEvent.VK_4:
			itemKeyInt = 30;
			break;
		case KeyEvent.VK_5:
			itemKeyInt = 31;
			break;
		case KeyEvent.VK_6:
			itemKeyInt = 32;
			break;
		case KeyEvent.VK_7:
			itemKeyInt = 33;
			break;
		case KeyEvent.VK_8:
			itemKeyInt = 34;
			break;
		case KeyEvent.VK_9:
			itemKeyInt = 35;
			break;
		case KeyEvent.VK_NUMPAD0:
			itemKeyInt = 36;
			break;
		case KeyEvent.VK_NUMPAD1:
			itemKeyInt = 37;
			break;
		case KeyEvent.VK_NUMPAD2:
			itemKeyInt = 38;
			break;
		case KeyEvent.VK_NUMPAD3:
			itemKeyInt = 39;
			break;
		case KeyEvent.VK_NUMPAD4:
			itemKeyInt = 40;
			break;
		case KeyEvent.VK_NUMPAD5:
			itemKeyInt = 41;
			break;
		case KeyEvent.VK_NUMPAD6:
			itemKeyInt = 42;
			break;
		case KeyEvent.VK_NUMPAD7:
			itemKeyInt = 43;
			break;
		case KeyEvent.VK_NUMPAD8:
			itemKeyInt = 44;
			break;
		case KeyEvent.VK_NUMPAD9:
			itemKeyInt = 45;
			break;
		case KeyEvent.VK_SPACE:
			itemKeyInt = 46;
			break;
		case KeyEvent.VK_BACK_SPACE:
			itemKeyInt = 47;
			break;
		case KeyEvent.VK_ESCAPE:
			itemKeyInt = 48;
			break;
		case KeyEvent.VK_SHIFT:
			itemKeyInt = 49;
			break;
		case KeyEvent.VK_ENTER:
			itemKeyInt = 50;
			break;
		case KeyEvent.VK_COMMA:
			itemKeyInt = 51;
			break;
		case KeyEvent.VK_PERIOD:
			itemKeyInt = 52;
			break;
		case KeyEvent.VK_SLASH:
			itemKeyInt = 53;
			break;
		case KeyEvent.VK_UP: case KeyEvent.VK_KP_UP:
			itemKeyInt = 54;
			break;
		case KeyEvent.VK_DOWN: case KeyEvent.VK_KP_DOWN:
			itemKeyInt = 55;
			break;
		case KeyEvent.VK_LEFT: case KeyEvent.VK_KP_LEFT:
			itemKeyInt = 56;
			break;
		case KeyEvent.VK_RIGHT: case KeyEvent.VK_KP_RIGHT:
			itemKeyInt = 57;
			break;
		default:
			itemKeyInt = 4;
			break;
		}

		switch(Game.pauseKey) {
		case KeyEvent.VK_A:
			pauseKeyInt = 0;
			break;
		case KeyEvent.VK_B:
			pauseKeyInt = 1;
			break;
		case KeyEvent.VK_C:
			pauseKeyInt = 2;
			break;
		case KeyEvent.VK_D:
			pauseKeyInt = 3;
			break;
		case KeyEvent.VK_E:
			pauseKeyInt = 4;
			break;
		case KeyEvent.VK_F:
			pauseKeyInt = 5;
			break;
		case KeyEvent.VK_G:
			pauseKeyInt = 6;
			break;
		case KeyEvent.VK_H:
			pauseKeyInt = 7;
			break;
		case KeyEvent.VK_I:
			pauseKeyInt = 8;
			break;
		case KeyEvent.VK_J:
			pauseKeyInt = 9;
			break;
		case KeyEvent.VK_K:
			pauseKeyInt = 10;
			break;
		case KeyEvent.VK_L:
			pauseKeyInt = 11;
			break;
		case KeyEvent.VK_M:
			pauseKeyInt = 12;
			break;
		case KeyEvent.VK_N:
			pauseKeyInt = 13;
			break;
		case KeyEvent.VK_O:
			pauseKeyInt = 14;
			break;
		case KeyEvent.VK_P:
			pauseKeyInt = 15;
			break;
		case KeyEvent.VK_Q:
			pauseKeyInt = 16;
			break;
		case KeyEvent.VK_R:
			pauseKeyInt = 17;
			break;
		case KeyEvent.VK_S:
			pauseKeyInt = 18;
			break;
		case KeyEvent.VK_T:
			pauseKeyInt = 19;
			break;
		case KeyEvent.VK_U:
			pauseKeyInt = 20;
			break;
		case KeyEvent.VK_V:
			pauseKeyInt = 21;
			break;
		case KeyEvent.VK_W:
			pauseKeyInt = 22;
			break;
		case KeyEvent.VK_X:
			pauseKeyInt = 23;
			break;
		case KeyEvent.VK_Y:
			pauseKeyInt = 24;
			break;
		case KeyEvent.VK_Z:
			pauseKeyInt = 25;
			break;
		case KeyEvent.VK_0:
			pauseKeyInt = 26;
			break;
		case KeyEvent.VK_1:
			pauseKeyInt = 27;
			break;
		case KeyEvent.VK_2:
			pauseKeyInt = 28;
			break;
		case KeyEvent.VK_3:
			pauseKeyInt = 29;
			break;
		case KeyEvent.VK_4:
			pauseKeyInt = 30;
			break;
		case KeyEvent.VK_5:
			pauseKeyInt = 31;
			break;
		case KeyEvent.VK_6:
			pauseKeyInt = 32;
			break;
		case KeyEvent.VK_7:
			pauseKeyInt = 33;
			break;
		case KeyEvent.VK_8:
			pauseKeyInt = 34;
			break;
		case KeyEvent.VK_9:
			pauseKeyInt = 35;
			break;
		case KeyEvent.VK_NUMPAD0:
			pauseKeyInt = 36;
			break;
		case KeyEvent.VK_NUMPAD1:
			pauseKeyInt = 37;
			break;
		case KeyEvent.VK_NUMPAD2:
			pauseKeyInt = 38;
			break;
		case KeyEvent.VK_NUMPAD3:
			pauseKeyInt = 39;
			break;
		case KeyEvent.VK_NUMPAD4:
			pauseKeyInt = 40;
			break;
		case KeyEvent.VK_NUMPAD5:
			pauseKeyInt = 41;
			break;
		case KeyEvent.VK_NUMPAD6:
			pauseKeyInt = 42;
			break;
		case KeyEvent.VK_NUMPAD7:
			pauseKeyInt = 43;
			break;
		case KeyEvent.VK_NUMPAD8:
			pauseKeyInt = 44;
			break;
		case KeyEvent.VK_NUMPAD9:
			pauseKeyInt = 45;
			break;
		case KeyEvent.VK_SPACE:
			pauseKeyInt = 46;
			break;
		case KeyEvent.VK_BACK_SPACE:
			pauseKeyInt = 47;
			break;
		case KeyEvent.VK_ESCAPE:
			pauseKeyInt = 48;
			break;
		case KeyEvent.VK_SHIFT:
			pauseKeyInt = 49;
			break;
		case KeyEvent.VK_ENTER:
			pauseKeyInt = 50;
			break;
		case KeyEvent.VK_COMMA:
			pauseKeyInt = 51;
			break;
		case KeyEvent.VK_PERIOD:
			pauseKeyInt = 52;
			break;
		case KeyEvent.VK_SLASH:
			pauseKeyInt = 53;
			break;
		case KeyEvent.VK_UP: case KeyEvent.VK_KP_UP:
			pauseKeyInt = 54;
			break;
		case KeyEvent.VK_DOWN: case KeyEvent.VK_KP_DOWN:
			pauseKeyInt = 55;
			break;
		case KeyEvent.VK_LEFT: case KeyEvent.VK_KP_LEFT:
			pauseKeyInt = 56;
			break;
		case KeyEvent.VK_RIGHT: case KeyEvent.VK_KP_RIGHT:
			pauseKeyInt = 57;
			break;
		default:
			pauseKeyInt = 50;
			break;
		}

		switch(Game.cancelKey) {
		case KeyEvent.VK_A:
			cancelKeyInt = 0;
			break;
		case KeyEvent.VK_B:
			cancelKeyInt = 1;
			break;
		case KeyEvent.VK_C:
			cancelKeyInt = 2;
			break;
		case KeyEvent.VK_D:
			cancelKeyInt = 3;
			break;
		case KeyEvent.VK_E:
			cancelKeyInt = 4;
			break;
		case KeyEvent.VK_F:
			cancelKeyInt = 5;
			break;
		case KeyEvent.VK_G:
			cancelKeyInt = 6;
			break;
		case KeyEvent.VK_H:
			cancelKeyInt = 7;
			break;
		case KeyEvent.VK_I:
			cancelKeyInt = 8;
			break;
		case KeyEvent.VK_J:
			cancelKeyInt = 9;
			break;
		case KeyEvent.VK_K:
			cancelKeyInt = 10;
			break;
		case KeyEvent.VK_L:
			cancelKeyInt = 11;
			break;
		case KeyEvent.VK_M:
			cancelKeyInt = 12;
			break;
		case KeyEvent.VK_N:
			cancelKeyInt = 13;
			break;
		case KeyEvent.VK_O:
			cancelKeyInt = 14;
			break;
		case KeyEvent.VK_P:
			cancelKeyInt = 15;
			break;
		case KeyEvent.VK_Q:
			cancelKeyInt = 16;
			break;
		case KeyEvent.VK_R:
			cancelKeyInt = 17;
			break;
		case KeyEvent.VK_S:
			cancelKeyInt = 18;
			break;
		case KeyEvent.VK_T:
			cancelKeyInt = 19;
			break;
		case KeyEvent.VK_U:
			cancelKeyInt = 20;
			break;
		case KeyEvent.VK_V:
			cancelKeyInt = 21;
			break;
		case KeyEvent.VK_W:
			cancelKeyInt = 22;
			break;
		case KeyEvent.VK_X:
			cancelKeyInt = 23;
			break;
		case KeyEvent.VK_Y:
			cancelKeyInt = 24;
			break;
		case KeyEvent.VK_Z:
			cancelKeyInt = 25;
			break;
		case KeyEvent.VK_0:
			cancelKeyInt = 26;
			break;
		case KeyEvent.VK_1:
			cancelKeyInt = 27;
			break;
		case KeyEvent.VK_2:
			cancelKeyInt = 28;
			break;
		case KeyEvent.VK_3:
			cancelKeyInt = 29;
			break;
		case KeyEvent.VK_4:
			cancelKeyInt = 30;
			break;
		case KeyEvent.VK_5:
			cancelKeyInt = 31;
			break;
		case KeyEvent.VK_6:
			cancelKeyInt = 32;
			break;
		case KeyEvent.VK_7:
			cancelKeyInt = 33;
			break;
		case KeyEvent.VK_8:
			cancelKeyInt = 34;
			break;
		case KeyEvent.VK_9:
			cancelKeyInt = 35;
			break;
		case KeyEvent.VK_NUMPAD0:
			cancelKeyInt = 36;
			break;
		case KeyEvent.VK_NUMPAD1:
			cancelKeyInt = 37;
			break;
		case KeyEvent.VK_NUMPAD2:
			cancelKeyInt = 38;
			break;
		case KeyEvent.VK_NUMPAD3:
			cancelKeyInt = 39;
			break;
		case KeyEvent.VK_NUMPAD4:
			cancelKeyInt = 40;
			break;
		case KeyEvent.VK_NUMPAD5:
			cancelKeyInt = 41;
			break;
		case KeyEvent.VK_NUMPAD6:
			cancelKeyInt = 42;
			break;
		case KeyEvent.VK_NUMPAD7:
			cancelKeyInt = 43;
			break;
		case KeyEvent.VK_NUMPAD8:
			cancelKeyInt = 44;
			break;
		case KeyEvent.VK_NUMPAD9:
			cancelKeyInt = 45;
			break;
		case KeyEvent.VK_SPACE:
			cancelKeyInt = 46;
			break;
		case KeyEvent.VK_BACK_SPACE:
			cancelKeyInt = 47;
			break;
		case KeyEvent.VK_ESCAPE:
			cancelKeyInt = 48;
			break;
		case KeyEvent.VK_SHIFT:
			cancelKeyInt = 49;
			break;
		case KeyEvent.VK_ENTER:
			cancelKeyInt = 50;
			break;
		case KeyEvent.VK_COMMA:
			cancelKeyInt = 51;
			break;
		case KeyEvent.VK_PERIOD:
			cancelKeyInt = 52;
			break;
		case KeyEvent.VK_SLASH:
			cancelKeyInt = 53;
			break;
		case KeyEvent.VK_UP: case KeyEvent.VK_KP_UP:
			cancelKeyInt = 54;
			break;
		case KeyEvent.VK_DOWN: case KeyEvent.VK_KP_DOWN:
			cancelKeyInt = 55;
			break;
		case KeyEvent.VK_LEFT: case KeyEvent.VK_KP_LEFT:
			cancelKeyInt = 56;
			break;
		case KeyEvent.VK_RIGHT: case KeyEvent.VK_KP_RIGHT:
			cancelKeyInt = 57;
			break;
		default:
			cancelKeyInt = 48;
			break;
		}
		tex.changeBufferedImagesWASD(upKeyWASD, upKeyInt);
		tex.changeBufferedImagesWASD(downKeyWASD, downKeyInt);
		tex.changeBufferedImagesWASD(leftKeyWASD, leftKeyInt);
		tex.changeBufferedImagesWASD(rightKeyWASD, rightKeyInt);
		tex.changeBufferedImagesWASD(shootKeyWASD, shootKeyInt);
		tex.changeBufferedImagesWASD(itemKeyWASD, itemKeyInt);
		tex.changeBufferedImagesWASD(pauseKeyWASD, pauseKeyInt);
		tex.changeBufferedImagesWASD(cancelKeyWASD, cancelKeyInt);
		
		xInputImageSorter(XInputConstants.XINPUT_GAMEPAD_DPAD_UP,upKeyXDevice);
		xInputImageSorter(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN,downKeyXDevice);
		xInputImageSorter(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT,leftKeyXDevice);
		xInputImageSorter(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT,rightKeyXDevice);
		xInputImageSorter(XInputConstants.XINPUT_GAMEPAD_A,shootKeyXDevice);
		xInputImageSorter(XInputConstants.XINPUT_GAMEPAD_X,itemKeyXDevice);
		xInputImageSorter(XInputConstants.XINPUT_GAMEPAD_START,pauseKeyXDevice);
		xInputImageSorter(XInputConstants.XINPUT_GAMEPAD_BACK,cancelKeyXDevice);
		
		tex.changePressE(itemKeyInt);
		if(XInputDevice.a == XInputConstants.XINPUT_GAMEPAD_X)
			tex.changePressX(XInputConstants.XINPUT_GAMEPAD_A);
		else if(XInputDevice.b == XInputConstants.XINPUT_GAMEPAD_X)
			tex.changePressX(XInputConstants.XINPUT_GAMEPAD_B);
		else if(XInputDevice.x == XInputConstants.XINPUT_GAMEPAD_X)
			tex.changePressX(XInputConstants.XINPUT_GAMEPAD_X);
		else if(XInputDevice.y == XInputConstants.XINPUT_GAMEPAD_X)
			tex.changePressX(XInputConstants.XINPUT_GAMEPAD_Y);
		else if(XInputDevice.up == XInputConstants.XINPUT_GAMEPAD_X)
			tex.changePressX(XInputConstants.XINPUT_GAMEPAD_DPAD_UP);
		else if(XInputDevice.down == XInputConstants.XINPUT_GAMEPAD_X)
			tex.changePressX(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN);
		else if(XInputDevice.left == XInputConstants.XINPUT_GAMEPAD_X)
			tex.changePressX(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT);
		else if(XInputDevice.right == XInputConstants.XINPUT_GAMEPAD_X)
			tex.changePressX(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT);
		else if(XInputDevice.lShoulder == XInputConstants.XINPUT_GAMEPAD_X)
			tex.changePressX(XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER);
		else if(XInputDevice.rShoulder == XInputConstants.XINPUT_GAMEPAD_X)
			tex.changePressX(XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER);
		else if(XInputDevice.lThumb == XInputConstants.XINPUT_GAMEPAD_X)
			tex.changePressX(XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB);
		else if(XInputDevice.rThumb == XInputConstants.XINPUT_GAMEPAD_X)
			tex.changePressX(XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB);
		else if(XInputDevice.start == XInputConstants.XINPUT_GAMEPAD_X)
			tex.changePressX(XInputConstants.XINPUT_GAMEPAD_START);
		else if(XInputDevice.back == XInputConstants.XINPUT_GAMEPAD_X)
			tex.changePressX(XInputConstants.XINPUT_GAMEPAD_BACK);
		else if(XInputDevice.guide == XInputConstants.XINPUT_GAMEPAD_X)
			tex.changePressX(XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON);
		else if(XInputDevice.unknown == XInputConstants.XINPUT_GAMEPAD_X)
			tex.changePressX(XInputConstants.XINPUT_GAMEPAD_UNKNOWN);
	}
	public void changeButtonHelper(String key) {
		switch(buttonToChange) {
		case 0:
			break;
		case -2:
			Game.writeMultipleString.add(String.valueOf(Game.upKey));
			switch(key) {
			case "upKey":
				Game.upKey = Game.upKey;
				break;
			case "downKey":
				Game.downKey = Game.upKey;
				break;
			case "leftKey":
				Game.leftKey = Game.upKey;
				break;
			case "rightKey":
				Game.rightKey = Game.upKey;
				break;
			case "shootKey":
				Game.shootKey = Game.upKey;
				break;
			case "itemKey":
				Game.itemKey = Game.upKey;
				Game.settingsSetup = false;
				break;
			case "pauseKey":
				Game.pauseKey = Game.upKey;
				break;
			case "cancelKey":
				Game.cancelKey = Game.upKey;
				break;
			default:
				break;
			}
			break;
		case -3:
			Game.writeMultipleString.add(String.valueOf(Game.downKey));
			switch(key) {
			case "upKey":
				Game.upKey = Game.downKey;
				break;
			case "downKey":
				Game.downKey = Game.downKey;
				break;
			case "leftKey":
				Game.leftKey = Game.downKey;
				break;
			case "rightKey":
				Game.rightKey = Game.downKey;
				break;
			case "shootKey":
				Game.shootKey = Game.downKey;
				break;
			case "itemKey":
				Game.itemKey = Game.downKey;
				Game.settingsSetup = false;
				break;
			case "pauseKey":
				Game.pauseKey = Game.downKey;
				break;
			case "cancelKey":
				Game.cancelKey = Game.downKey;
				break;
			default:
				break;
			}
			break;
		case -4:
			Game.writeMultipleString.add(String.valueOf(Game.leftKey));
			switch(key) {
			case "upKey":
				Game.upKey = Game.leftKey;
				break;
			case "downKey":
				Game.downKey = Game.leftKey;
				break;
			case "leftKey":
				Game.leftKey = Game.leftKey;
				break;
			case "rightKey":
				Game.rightKey = Game.leftKey;
				break;
			case "shootKey":
				Game.shootKey = Game.leftKey;
				break;
			case "itemKey":
				Game.itemKey = Game.leftKey;
				Game.settingsSetup = false;
				break;
			case "pauseKey":
				Game.pauseKey = Game.leftKey;
				break;
			case "cancelKey":
				Game.cancelKey = Game.leftKey;
				break;
			default:
				break;
			}
			break;
		case -5:
			Game.writeMultipleString.add(String.valueOf(Game.rightKey));
			switch(key) {
			case "upKey":
				Game.upKey = Game.rightKey;
				break;
			case "downKey":
				Game.downKey = Game.rightKey;
				break;
			case "leftKey":
				Game.leftKey = Game.rightKey;
				break;
			case "rightKey":
				Game.rightKey = Game.rightKey;
				break;
			case "shootKey":
				Game.shootKey = Game.rightKey;
				break;
			case "itemKey":
				Game.itemKey = Game.rightKey;
				Game.settingsSetup = false;
				break;
			case "pauseKey":
				Game.pauseKey = Game.rightKey;
				break;
			case "cancelKey":
				Game.cancelKey = Game.rightKey;
				break;
			default:
				break;
			}
			break;
		case -6:
			Game.writeMultipleString.add(String.valueOf(Game.shootKey));
			switch(key) {
			case "upKey":
				Game.upKey = Game.shootKey;
				break;
			case "downKey":
				Game.downKey = Game.shootKey;
				break;
			case "leftKey":
				Game.leftKey = Game.shootKey;
				break;
			case "rightKey":
				Game.rightKey = Game.shootKey;
				break;
			case "shootKey":
				Game.shootKey = Game.shootKey;
				break;
			case "itemKey":
				Game.itemKey = Game.shootKey;
				Game.settingsSetup = false;
				break;
			case "pauseKey":
				Game.pauseKey = Game.shootKey;
				break;
			case "cancelKey":
				Game.cancelKey = Game.shootKey;
				break;
			default:
				break;
			}
			break;
		case -7:
			Game.writeMultipleString.add(String.valueOf(Game.itemKey));
			switch(key) {
			case "upKey":
				Game.upKey = Game.itemKey;
				break;
			case "downKey":
				Game.downKey = Game.itemKey;
				break;
			case "leftKey":
				Game.leftKey = Game.itemKey;
				break;
			case "rightKey":
				Game.rightKey = Game.itemKey;
				break;
			case "shootKey":
				Game.shootKey = Game.itemKey;
				break;
			case "itemKey":
				Game.itemKey = Game.itemKey;
				Game.settingsSetup = false;
				break;
			case "pauseKey":
				Game.pauseKey = Game.itemKey;
				break;
			case "cancelKey":
				Game.cancelKey = Game.itemKey;
				break;
			default:
				break;
			}
			break;
		case -8:
			Game.writeMultipleString.add(String.valueOf(Game.pauseKey));
			switch(key) {
			case "upKey":
				Game.upKey = Game.pauseKey;
				break;
			case "downKey":
				Game.downKey = Game.pauseKey;
				break;
			case "leftKey":
				Game.leftKey = Game.pauseKey;
				break;
			case "rightKey":
				Game.rightKey = Game.pauseKey;
				break;
			case "shootKey":
				Game.shootKey = Game.pauseKey;
				break;
			case "itemKey":
				Game.itemKey = Game.pauseKey;
				Game.settingsSetup = false;
				break;
			case "pauseKey":
				Game.pauseKey = Game.pauseKey;
				break;
			case "cancelKey":
				Game.cancelKey = Game.pauseKey;
				break;
			default:
				break;
			}
			break;
		case -9:
			Game.writeMultipleString.add(String.valueOf(Game.cancelKey));
			switch(key) {
			case "upKey":
				Game.upKey = Game.cancelKey;
				break;
			case "downKey":
				Game.downKey = Game.cancelKey;
				break;
			case "leftKey":
				Game.leftKey = Game.cancelKey;
				break;
			case "rightKey":
				Game.rightKey = Game.cancelKey;
				break;
			case "shootKey":
				Game.shootKey = Game.cancelKey;
				break;
			case "itemKey":
				Game.itemKey = Game.cancelKey;
				Game.settingsSetup = false;
				break;
			case "pauseKey":
				Game.pauseKey = Game.cancelKey;
				break;
			case "cancelKey":
				Game.cancelKey = Game.cancelKey;
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
		if(buttonToChange <= -2 && buttonToChange >= -9)
			Game.writeMultipleProperty.add(key);
	}
	public void changeButton(int key) {
		if(buttonToChange!=0) {
			if(key == Game.upKey && buttonToChange != -2)
				changeButtonHelper("upKey");
			else if(key == Game.downKey && buttonToChange != -3)
				changeButtonHelper("downKey");
			else if(key == Game.leftKey && buttonToChange != -4)
				changeButtonHelper("leftKey");
			else if(key == Game.rightKey && buttonToChange != -5)
				changeButtonHelper("rightKey");
			else if(key == Game.shootKey && buttonToChange != -6)
				changeButtonHelper("shootKey");
			else if(key == Game.itemKey && buttonToChange != -7)
				changeButtonHelper("itemKey");
			else if(key == Game.pauseKey && buttonToChange != -8)
				changeButtonHelper("pauseKey");
			else if(key == Game.cancelKey && buttonToChange != -9)
				changeButtonHelper("cancelKey");
		}
		switch(buttonToChange) {
		case 0:
			break;
		case -2:
			Game.upKey = key;
			Game.writeOnceProperty = "upKey";
			break;
		case -3:
			Game.downKey = key;
			Game.writeOnceProperty = "downKey";
			break;
		case -4:
			Game.leftKey = key;
			Game.writeOnceProperty = "leftKey";
			break;
		case -5:
			Game.rightKey = key;
			Game.writeOnceProperty = "rightKey";
			break;
		case -6:
			Game.shootKey = key;
			Game.writeOnceProperty = "shootKey";
			break;
		case -7:
			Game.itemKey = key;
			Game.writeOnceProperty = "itemKey";
			Game.settingsSetup = false;
			break;
		case -8:
			Game.pauseKey = key;
			Game.writeOnceProperty = "pauseKey";
			break;
		case -9:
			Game.cancelKey = key;
			Game.writeOnceProperty = "cancelKey";
			break;
		default:
			break;
		}
		if (buttonToChange != 0) {
			updateControls();
			Game.writeOnceToSettings = true;
			Game.writeOnceString = String.valueOf(key);
		}
		buttonChangeTimer = 0;
		return;
	}
	public void changeButtonXDeviceNewHelper3(short currentButton2, short currentKey, short currentButton) {
		switch(currentKey) {
		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
			if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
				XInputDevice.a = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("aButton");
			}
			else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
				XInputDevice.b = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("bButton");
			}else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
				XInputDevice.x = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("xButton");
			}else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
				XInputDevice.y = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("yButton");
			}else if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
				XInputDevice.up = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("upButton");
			}else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
				XInputDevice.down = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("downButton");
			}else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
				XInputDevice.left = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("leftButton");
			}else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
				XInputDevice.right = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rightButton");
			}else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
				XInputDevice.lShoulder = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("lShoulderButton");
			}else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
				XInputDevice.rShoulder = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rShoulderButton");
			}else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
				XInputDevice.lThumb = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("lThumbButton");
			}else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
				XInputDevice.rThumb = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rThumbButton");
			}else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
				XInputDevice.start = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("startButton");
			}else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
				XInputDevice.back = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("backButton");
			}else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
				XInputDevice.guide = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("guideButton");
			}else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
				XInputDevice.unknown = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("unknownButton");
			}
			break;
		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
			if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
				XInputDevice.a = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("aButton");
			}
			else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
				XInputDevice.b = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("bButton");
			}else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
				XInputDevice.x = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("xButton");
			}else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
				XInputDevice.y = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("yButton");
			}else if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
				XInputDevice.up = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("upButton");
			}else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
				XInputDevice.down = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("downButton");
			}else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
				XInputDevice.left = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("leftButton");
			}else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
				XInputDevice.right = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rightButton");
			}else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
				XInputDevice.lShoulder = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("lShoulderButton");
			}else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
				XInputDevice.rShoulder = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rShoulderButton");
			}else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
				XInputDevice.lThumb = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("lThumbButton");
			}else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
				XInputDevice.rThumb = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rThumbButton");
			}else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
				XInputDevice.start = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("startButton");
			}else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
				XInputDevice.back = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("backButton");
			}else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
				XInputDevice.guide = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("guideButton");
			}else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
				XInputDevice.unknown = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("unknownButton");
			}
			break;
		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
			if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
				XInputDevice.a = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("aButton");
			}
			else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
				XInputDevice.b = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("bButton");
			}else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
				XInputDevice.x = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("xButton");
			}else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
				XInputDevice.y = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("yButton");
			}else if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
				XInputDevice.up = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("upButton");
			}else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
				XInputDevice.down = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("downButton");
			}else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
				XInputDevice.left = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("leftButton");
			}else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
				XInputDevice.right = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rightButton");
			}else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
				XInputDevice.lShoulder = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("lShoulderButton");
			}else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
				XInputDevice.rShoulder = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rShoulderButton");
			}else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
				XInputDevice.lThumb = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("lThumbButton");
			}else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
				XInputDevice.rThumb = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rThumbButton");
			}else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
				XInputDevice.start = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("startButton");
			}else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
				XInputDevice.back = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("backButton");
			}else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
				XInputDevice.guide = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("guideButton");
			}else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
				XInputDevice.unknown = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("unknownButton");
			}
			break;
		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
			if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
				XInputDevice.a = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("aButton");
			}
			else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
				XInputDevice.b = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("bButton");
			}else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
				XInputDevice.x = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("xButton");
			}else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
				XInputDevice.y = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("yButton");
			}else if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
				XInputDevice.up = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("upButton");
			}else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
				XInputDevice.down = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("downButton");
			}else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
				XInputDevice.left = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("leftButton");
			}else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
				XInputDevice.right = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rightButton");
			}else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
				XInputDevice.lShoulder = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("lShoulderButton");
			}else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
				XInputDevice.rShoulder = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rShoulderButton");
			}else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
				XInputDevice.lThumb = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("lThumbButton");
			}else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
				XInputDevice.rThumb = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rThumbButton");
			}else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
				XInputDevice.start = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("startButton");
			}else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
				XInputDevice.back = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("backButton");
			}else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
				XInputDevice.guide = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("guideButton");
			}else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
				XInputDevice.unknown = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("unknownButton");
			}
			break;
		case XInputConstants.XINPUT_GAMEPAD_A:
			if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_A) {
				XInputDevice.a = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("aButton");
			}
			else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_A) {
				XInputDevice.b = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("bButton");
			}else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_A) {
				XInputDevice.x = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("xButton");
			}else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_A) {
				XInputDevice.y = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("yButton");
			}else if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_A) {
				XInputDevice.up = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("upButton");
			}else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_A) {
				XInputDevice.down = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("downButton");
			}else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_A) {
				XInputDevice.left = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("leftButton");
			}else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_A) {
				XInputDevice.right = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rightButton");
			}else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_A) {
				XInputDevice.lShoulder = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("lShoulderButton");
			}else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_A) {
				XInputDevice.rShoulder = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rShoulderButton");
			}else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_A) {
				XInputDevice.lThumb = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("lThumbButton");
			}else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_A) {
				XInputDevice.rThumb = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rThumbButton");
			}else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_A) {
				XInputDevice.start = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("startButton");
			}else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_A) {
				XInputDevice.back = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("backButton");
			}else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_A) {
				XInputDevice.guide = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("guideButton");
			}else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_A) {
				XInputDevice.unknown = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("unknownButton");
			}
			break;
		case XInputConstants.XINPUT_GAMEPAD_X:
			if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_X) {
				XInputDevice.a = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("aButton");
			}
			else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_X) {
				XInputDevice.b = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("bButton");
			}else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_X) {
				XInputDevice.x = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("xButton");
			}else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_X) {
				XInputDevice.y = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("yButton");
			}else if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_X) {
				XInputDevice.up = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("upButton");
			}else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_X) {
				XInputDevice.down = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("downButton");
			}else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_X) {
				XInputDevice.left = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("leftButton");
			}else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_X) {
				XInputDevice.right = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rightButton");
			}else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_X) {
				XInputDevice.lShoulder = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("lShoulderButton");
			}else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_X) {
				XInputDevice.rShoulder = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rShoulderButton");
			}else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_X) {
				XInputDevice.lThumb = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("lThumbButton");
			}else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_X) {
				XInputDevice.rThumb = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rThumbButton");
			}else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_X) {
				XInputDevice.start = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("startButton");
			}else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_X) {
				XInputDevice.back = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("backButton");
			}else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_X) {
				XInputDevice.guide = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("guideButton");
			}else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_X) {
				XInputDevice.unknown = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("unknownButton");
			}
			break;
		case XInputConstants.XINPUT_GAMEPAD_START:
			if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_START) {
				XInputDevice.a = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("aButton");
			}
			else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_START) {
				XInputDevice.b = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("bButton");
			}else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_START) {
				XInputDevice.x = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("xButton");
			}else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_START) {
				XInputDevice.y = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("yButton");
			}else if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_START) {
				XInputDevice.up = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("upButton");
			}else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_START) {
				XInputDevice.down = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("downButton");
			}else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_START) {
				XInputDevice.left = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("leftButton");
			}else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_START) {
				XInputDevice.right = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rightButton");
			}else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_START) {
				XInputDevice.lShoulder = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("lShoulderButton");
			}else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_START) {
				XInputDevice.rShoulder = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rShoulderButton");
			}else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_START) {
				XInputDevice.lThumb = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("lThumbButton");
			}else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_START) {
				XInputDevice.rThumb = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rThumbButton");
			}else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_START) {
				XInputDevice.start = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("startButton");
			}else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_START) {
				XInputDevice.back = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("backButton");
			}else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_START) {
				XInputDevice.guide = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("guideButton");
			}else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_START) {
				XInputDevice.unknown = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("unknownButton");
			}
			break;
		case XInputConstants.XINPUT_GAMEPAD_BACK:
			if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
				XInputDevice.a = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("aButton");
			}
			else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
				XInputDevice.b = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("bButton");
			}else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
				XInputDevice.x = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("xButton");
			}else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
				XInputDevice.y = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("yButton");
			}else if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
				XInputDevice.up = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("upButton");
			}else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
				XInputDevice.down = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("downButton");
			}else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
				XInputDevice.left = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("leftButton");
			}else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
				XInputDevice.right = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rightButton");
			}else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
				XInputDevice.lShoulder = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("lShoulderButton");
			}else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
				XInputDevice.rShoulder = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rShoulderButton");
			}else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
				XInputDevice.lThumb = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("lThumbButton");
			}else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
				XInputDevice.rThumb = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("rThumbButton");
			}else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
				XInputDevice.start = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("startButton");
			}else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
				XInputDevice.back = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("backButton");
			}else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
				XInputDevice.guide = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("guideButton");
			}else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
				XInputDevice.unknown = currentButton;
				Game.writeMultipleString.add(String.valueOf(currentButton));
				Game.writeMultipleProperty.add("unknownButton");
			}
			break;
		default:
			break;
		}
		return;
	}
	public void changeButtonXDeviceNewHelper(short key, short currentButton) {
		switch(buttonToChange) {
		case -10:
			changeButtonXDeviceNewHelper3(key,XInputConstants.XINPUT_GAMEPAD_DPAD_UP,currentButton);
			break;
		case -11:
			changeButtonXDeviceNewHelper3(key,XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN,currentButton);
			break;
		case -12:
			changeButtonXDeviceNewHelper3(key,XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT,currentButton);
			break;
		case -13:
			changeButtonXDeviceNewHelper3(key,XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT,currentButton);
			break;
		case -14:
			changeButtonXDeviceNewHelper3(key,XInputConstants.XINPUT_GAMEPAD_A,currentButton);
			break;
		case -15:
			changeButtonXDeviceNewHelper3(key,XInputConstants.XINPUT_GAMEPAD_X,currentButton);
			break;
		case -16:
			changeButtonXDeviceNewHelper3(key,XInputConstants.XINPUT_GAMEPAD_START,currentButton);
			break;
		case -17:
			changeButtonXDeviceNewHelper3(key,XInputConstants.XINPUT_GAMEPAD_BACK,currentButton);
			break;
		default:
			return;
		}
		return;
	}
	public void changeButtonXDeviceOrganizer(short key, short xInputDeviceButton, String button ) {
		if(XInputConstants.XINPUT_GAMEPAD_A == key) {
				xInputDeviceButton = XInputDevice.aFake;
				Game.writeMultipleProperty.add(button);
				Game.writeMultipleString.add(String.valueOf(XInputDevice.aFake));
		}
		else if(XInputConstants.XINPUT_GAMEPAD_B == key) {
				xInputDeviceButton = XInputDevice.bFake;
				Game.writeMultipleProperty.add(button);
				Game.writeMultipleString.add(String.valueOf(XInputDevice.bFake));
		}
		else if(XInputConstants.XINPUT_GAMEPAD_X == key) {
				xInputDeviceButton = XInputDevice.xFake;
				Game.writeMultipleProperty.add(button);
				Game.writeMultipleString.add(String.valueOf(XInputDevice.xFake));
		}
		else if(XInputConstants.XINPUT_GAMEPAD_Y == key) {
				xInputDeviceButton = XInputDevice.yFake;
				Game.writeMultipleProperty.add(button);
				Game.writeMultipleString.add(String.valueOf(XInputDevice.yFake));
		}
		else if(XInputConstants.XINPUT_GAMEPAD_DPAD_UP == key) {
			xInputDeviceButton = XInputDevice.upFake;
			Game.writeMultipleProperty.add(button);
			Game.writeMultipleString.add(String.valueOf(XInputDevice.upFake));
		}
		else if(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN == key) {
			xInputDeviceButton = XInputDevice.downFake;
			Game.writeMultipleProperty.add(button);
			Game.writeMultipleString.add(String.valueOf(XInputDevice.downFake));
		}
		else if(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT == key) {
			xInputDeviceButton = XInputDevice.leftFake;
			Game.writeMultipleProperty.add(button);
			Game.writeMultipleString.add(String.valueOf(XInputDevice.leftFake));
		}
		else if(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT == key) {
			xInputDeviceButton = XInputDevice.rightFake;
			Game.writeMultipleProperty.add(button);
			Game.writeMultipleString.add(String.valueOf(XInputDevice.rightFake));
		}
		else if(XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER == key) {
			xInputDeviceButton = XInputDevice.lShoulderFake;
			Game.writeMultipleProperty.add(button);
			Game.writeMultipleString.add(String.valueOf(XInputDevice.lShoulderFake));
		}
		else if(XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER == key) {
			xInputDeviceButton = XInputDevice.rShoulderFake;
			Game.writeMultipleProperty.add(button);
			Game.writeMultipleString.add(String.valueOf(XInputDevice.rShoulderFake));
		}
		else if(XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB == key) {
			xInputDeviceButton = XInputDevice.lThumbFake;
			Game.writeMultipleProperty.add(button);
			Game.writeMultipleString.add(String.valueOf(XInputDevice.lThumbFake));
		}
		else if(XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB == key) {
			xInputDeviceButton = XInputDevice.rThumbFake;
			Game.writeMultipleProperty.add(button);
			Game.writeMultipleString.add(String.valueOf(XInputDevice.rThumbFake));
		}
		else if(XInputConstants.XINPUT_GAMEPAD_START == key) {
			xInputDeviceButton = XInputDevice.startFake;
			Game.writeMultipleProperty.add(button);
			Game.writeMultipleString.add(String.valueOf(XInputDevice.startFake));
		}
		else if(XInputConstants.XINPUT_GAMEPAD_BACK == key) {
			xInputDeviceButton = XInputDevice.backFake;
			Game.writeMultipleProperty.add(button);
			Game.writeMultipleString.add(String.valueOf(XInputDevice.backFake));
		}
		else if(XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON == key) {
			xInputDeviceButton = XInputDevice.guideFake;
			Game.writeMultipleProperty.add(button);
			Game.writeMultipleString.add(String.valueOf(XInputDevice.guideFake));
		}
		else if(XInputConstants.XINPUT_GAMEPAD_UNKNOWN == key) {
			xInputDeviceButton = XInputDevice.unknownFake;
			Game.writeMultipleProperty.add(button);
			Game.writeMultipleString.add(String.valueOf(XInputDevice.unknownFake));
		}
		else {
			xInputDeviceButton = key;
			Game.writeMultipleProperty.add(button);
			Game.writeMultipleString.add(String.valueOf(key));
		}
		return;
	}
	public void changeButtonXDevice(short key) {
		boolean replaceCheck = false;
		String s = "";
		short up1 = -1;
		short down1 = -1;
		short left1 = -1;
		short right1 = -1;
		short a1 = -1;
		short x1 = -1;
		short start1 = -1;
		short back1 = -1;
		if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP)
			up1 = XInputDevice.up;
		else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP)
			up1 = XInputDevice.down;
		else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP)
			up1 = XInputDevice.left;
		else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP)
			up1 = XInputDevice.right;
		else if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP)
			up1 = XInputDevice.a;
		else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP)
			up1 = XInputDevice.b;
		else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP)
			up1 = XInputDevice.x;
		else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP)
			up1 = XInputDevice.y;
		else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP)
			up1 = XInputDevice.lShoulder;
		else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP)
			up1 = XInputDevice.rShoulder;
		else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP)
			up1 = XInputDevice.lThumb;
		else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP)
			up1 = XInputDevice.rThumb;
		else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP)
			up1 = XInputDevice.start;
		else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP)
			up1 = XInputDevice.back;
		else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP)
			up1 = XInputDevice.guide;
		else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP)
			up1 = XInputDevice.unknown;
		if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)
			down1 = XInputDevice.up;
		else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)
			down1 = XInputDevice.down;
		else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)
			down1 = XInputDevice.left;
		else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)
			down1 = XInputDevice.right;
		else if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)
			down1 = XInputDevice.a;
		else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)
			down1 = XInputDevice.b;
		else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)
			down1 = XInputDevice.x;
		else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)
			down1 = XInputDevice.y;
		else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)
			down1 = XInputDevice.lShoulder;
		else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)
			down1 = XInputDevice.rShoulder;
		else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)
			down1 = XInputDevice.lThumb;
		else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)
			down1 = XInputDevice.rThumb;
		else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)
			down1 = XInputDevice.start;
		else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)
			down1 = XInputDevice.back;
		else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)
			down1 = XInputDevice.guide;
		else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN)
			down1 = XInputDevice.unknown;
		if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)
			left1 = XInputDevice.up;
		else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)
			left1 = XInputDevice.down;
		else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)
			left1 = XInputDevice.left;
		else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)
			left1 = XInputDevice.right;
		else if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)
			left1 = XInputDevice.a;
		else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)
			left1 = XInputDevice.b;
		else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)
			left1 = XInputDevice.x;
		else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)
			left1 = XInputDevice.y;
		else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)
			left1 = XInputDevice.lShoulder;
		else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)
			left1 = XInputDevice.rShoulder;
		else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)
			left1 = XInputDevice.lThumb;
		else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)
			left1 = XInputDevice.rThumb;
		else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)
			left1 = XInputDevice.start;
		else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)
			left1 = XInputDevice.back;
		else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)
			left1 = XInputDevice.guide;
		else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT)
			left1 = XInputDevice.unknown;
		if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)
			right1 = XInputDevice.up;
		else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)
			right1 = XInputDevice.down;
		else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)
			right1 = XInputDevice.left;
		else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)
			right1 = XInputDevice.right;
		else if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)
			right1 = XInputDevice.a;
		else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)
			right1 = XInputDevice.b;
		else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)
			right1 = XInputDevice.x;
		else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)
			right1 = XInputDevice.y;
		else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)
			right1 = XInputDevice.lShoulder;
		else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)
			right1 = XInputDevice.rShoulder;
		else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)
			right1 = XInputDevice.lThumb;
		else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)
			right1 = XInputDevice.rThumb;
		else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)
			right1 = XInputDevice.start;
		else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)
			right1 = XInputDevice.back;
		else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)
			right1 = XInputDevice.guide;
		else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)
			right1 = XInputDevice.unknown;
		if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_A)
			a1 = XInputDevice.up;
		else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_A)
			a1 = XInputDevice.down;
		else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_A)
			a1 = XInputDevice.left;
		else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_A)
			a1 = XInputDevice.right;
		else if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_A)
			a1 = XInputDevice.a;
		else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_A)
			a1 = XInputDevice.b;
		else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_A)
			a1 = XInputDevice.x;
		else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_A)
			a1 = XInputDevice.y;
		else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_A)
			a1 = XInputDevice.lShoulder;
		else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_A)
			a1 = XInputDevice.rShoulder;
		else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_A)
			a1 = XInputDevice.lThumb;
		else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_A)
			a1 = XInputDevice.rThumb;
		else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_A)
			a1 = XInputDevice.start;
		else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_A)
			a1 = XInputDevice.back;
		else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_A)
			a1 = XInputDevice.guide;
		else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_A)
			a1 = XInputDevice.unknown;
		if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_X)
			x1 = XInputDevice.up;
		else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_X)
			x1 = XInputDevice.down;
		else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_X)
			x1 = XInputDevice.left;
		else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_X)
			x1 = XInputDevice.right;
		else if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_X)
			x1 = XInputDevice.a;
		else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_X)
			x1 = XInputDevice.b;
		else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_X)
			x1 = XInputDevice.x;
		else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_X)
			x1 = XInputDevice.y;
		else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_X)
			x1 = XInputDevice.lShoulder;
		else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_X)
			x1 = XInputDevice.rShoulder;
		else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_X)
			x1 = XInputDevice.lThumb;
		else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_X)
			x1 = XInputDevice.rThumb;
		else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_X)
			x1 = XInputDevice.start;
		else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_X)
			x1 = XInputDevice.back;
		else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_X)
			x1 = XInputDevice.guide;
		else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_X)
			x1 = XInputDevice.unknown;
		if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_START)
			start1 = XInputDevice.up;
		else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_START)
			start1 = XInputDevice.down;
		else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_START)
			start1 = XInputDevice.left;
		else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_START)
			start1 = XInputDevice.right;
		else if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_START)
			start1 = XInputDevice.a;
		else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_START)
			start1 = XInputDevice.b;
		else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_START)
			start1 = XInputDevice.x;
		else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_START)
			start1 = XInputDevice.y;
		else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_START)
			start1 = XInputDevice.lShoulder;
		else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_START)
			start1 = XInputDevice.rShoulder;
		else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_START)
			start1 = XInputDevice.lThumb;
		else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_START)
			start1 = XInputDevice.rThumb;
		else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_START)
			start1 = XInputDevice.start;
		else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_START)
			start1 = XInputDevice.back;
		else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_START)
			start1 = XInputDevice.guide;
		else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_START)
			start1 = XInputDevice.unknown;
		if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_BACK)
			back1 = XInputDevice.up;
		else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_BACK)
			back1 = XInputDevice.down;
		else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_BACK)
			back1 = XInputDevice.left;
		else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_BACK)
			back1 = XInputDevice.right;
		else if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_BACK)
			back1 = XInputDevice.a;
		else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_BACK)
			back1 = XInputDevice.b;
		else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_BACK)
			back1 = XInputDevice.x;
		else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_BACK)
			back1 = XInputDevice.y;
		else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_BACK)
			back1 = XInputDevice.lShoulder;
		else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_BACK)
			back1 = XInputDevice.rShoulder;
		else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_BACK)
			back1 = XInputDevice.lThumb;
		else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_BACK)
			back1 = XInputDevice.rThumb;
		else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_BACK)
			back1 = XInputDevice.start;
		else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_BACK)
			back1 = XInputDevice.back;
		else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_BACK)
			back1 = XInputDevice.guide;
		else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_BACK)
			back1 = XInputDevice.unknown;
		/**/
		if(buttonToChange!=0) {
			if((buttonToChange == -10 && key == up1) ||
				(buttonToChange == -11 && key == down1) ||
				(buttonToChange == -12 && key == left1)||
				(buttonToChange == -13 && key == right1)||
				(buttonToChange == -14 && key == a1)||
				(buttonToChange == -15 && key == x1)||
				(buttonToChange == -16 && key == start1)||
				(buttonToChange == -17 && key == back1)) {
				buttonChangeTimer = 0;
				Game.enterButtonPushedDown= false;
				return;
			}
			if(key == up1) { //&& buttonToChange != -10) {
				changeButtonXDeviceNewHelper(up1,XInputConstants.XINPUT_GAMEPAD_DPAD_UP);
				replaceCheck = true;
			}
			else if(key == down1) {// && buttonToChange != -11) {
				changeButtonXDeviceNewHelper(down1,XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN);
				replaceCheck = true;
			}
			else if(key == left1) {// && buttonToChange != -12) {
				changeButtonXDeviceNewHelper(left1,XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT);
				replaceCheck = true;
			}
			else if(key == right1) { //&& buttonToChange != -13) {
				changeButtonXDeviceNewHelper(right1,XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT);
				replaceCheck = true;
			}
			else if(key == a1) { //&& buttonToChange != -14) {
				System.out.println("replaceCheck in A");
				changeButtonXDeviceNewHelper(a1,XInputConstants.XINPUT_GAMEPAD_A);
				replaceCheck = true;
			}
			else if(key == x1) { //&& buttonToChange != -15) {
				System.out.println("replaceCheck in X");
				changeButtonXDeviceNewHelper(x1,XInputConstants.XINPUT_GAMEPAD_X);
				Game.settingsSetup = false;
				replaceCheck = true;
			}
			else if(key == start1) { //&& buttonToChange != -16) {
				changeButtonXDeviceNewHelper(start1,XInputConstants.XINPUT_GAMEPAD_START);
				replaceCheck = true;
			}
			else if(key == back1) { //&& buttonToChange != -17) {
				changeButtonXDeviceNewHelper(back1,XInputConstants.XINPUT_GAMEPAD_BACK);
				replaceCheck = true;
			}
		}
		switch(buttonToChange) {
		case 0:
			break;
		case -10:
			if(!replaceCheck) {
				if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.a, "aButton");
				}else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.b, "bButton");
				}else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.x, "xButton");
				}else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.y, "yButton");
				}else if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.up, "upButton");
				}else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.down, "downButton");
				}else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.left, "leftButton");
				}else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.right, "rightButton");
				}else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.lShoulder, "lShoulderButton");
				}else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.rShoulder, "rShoulderButton");
				}else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.lThumb, "lThumbButton");
				}else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.rThumb, "rThumbButton");
				}else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.start, "startButton");
				}else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.back, "backButton");
				}else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.guide, "guideButton");
				}else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_DPAD_UP) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.unknown, "unknownButton");
				}
			}	
			Game.writeOnceString = String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_UP);
			//}
//			XInputDevice.up = key;
//			Game.writeOnceProperty = "upButton";
			break;
		case -11:
			if(!replaceCheck) {
				if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.a, "aButton");
				}else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.b, "bButton");
				}else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.x, "xButton");
				}else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.y, "yButton");
				}else if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.up, "upButton");
				}else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.down, "downButton");
				}else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.left, "leftButton");
				}else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.right, "rightButton");
				}else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.lShoulder, "lShoulderButton");
				}else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.rShoulder, "rShoulderButton");
				}else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.lThumb, "lThumbButton");
				}else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.rThumb, "rThumbButton");
				}else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.start, "startButton");
				}else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.back, "backButton");
				}else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.guide, "guideButton");
				}else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.unknown, "unknownButton");
				}
			}
			Game.writeOnceString = String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN);
			//}
//			XInputDevice.down = key;
//			Game.writeOnceProperty = "downButton";
			break;
		case -12:
			if(!replaceCheck) {
				if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.a, "aButton");
				}else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.b, "bButton");
				}else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.x, "xButton");
				}else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.y, "yButton");
				}else if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.up, "upButton");
				}else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.down, "downButton");
				}else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.left, "leftButton");
				}else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.right, "rightButton");
				}else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.lShoulder, "lShoulderButton");
				}else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.rShoulder, "rShoulderButton");
				}else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.lThumb, "lThumbButton");
				}else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.rThumb, "rThumbButton");
				}else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.start, "startButton");
				}else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.back, "backButton");
				}else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.guide, "guideButton");
				}else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.unknown, "unknownButton");
				}
			}

			Game.writeOnceString = String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT);
			//}
//			XInputDevice.left = key;
//			Game.writeOnceProperty = "leftButton";
			break;
		case -13:
			if(!replaceCheck) {
				if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.a, "aButton");
				}else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.b, "bButton");
				}else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.x, "xButton");
				}else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.y, "yButton");
				}else if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.up, "upButton");
				}else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.down, "downButton");
				}else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.left, "leftButton");
				}else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.right, "rightButton");
				}else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.lShoulder, "lShoulderButton");
				}else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.rShoulder, "rShoulderButton");
				}else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.lThumb, "lThumbButton");
				}else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.rThumb, "rThumbButton");
				}else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.start, "startButton");
				}else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.back, "backButton");
				}else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.guide, "guideButton");
				}else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.unknown, "unknownButton");
				}
			}
			Game.writeOnceString = String.valueOf(XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT);
			//}
//			XInputDevice.right = key;
//			Game.writeOnceProperty = "rightButton";
			break;
		case -14:
			if(!replaceCheck) {
				if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_A) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.a, "aButton");
				}else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_A){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.b, "bButton");
				}else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_A){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.x, "xButton");
				}else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_A){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.y, "yButton");
				}else if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_A) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.up, "upButton");
				}else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_A) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.down, "downButton");
				}else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_A) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.left, "leftButton");
				}else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_A) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.right, "rightButton");
				}else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_A) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.lShoulder, "lShoulderButton");
				}else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_A) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.rShoulder, "rShoulderButton");
				}else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_A) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.lThumb, "lThumbButton");
				}else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_A) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.rThumb, "rThumbButton");
				}else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_A) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.start, "startButton");
				}else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_A) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.back, "backButton");
				}else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_A) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.guide, "guideButton");
				}else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_A) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.unknown, "unknownButton");
				}
			}
			Game.writeOnceString = String.valueOf(XInputConstants.XINPUT_GAMEPAD_A);
			//}
//			XInputDevice.a = key;
//			Game.writeOnceProperty = "aButton";
			break;
		case -15:
			if(!replaceCheck) {

				if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_X) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.a, "aButton");
				}else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_X){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.b, "bButton");
				}else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_X){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.x, "xButton");
				}else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_X){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.y, "yButton");
				}else if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_X) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.up, "upButton");
				}else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_X) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.down, "downButton");
				}else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_X) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.left, "leftButton");
				}else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_X) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.right, "rightButton");
				}else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_X) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.lShoulder, "lShoulderButton");
				}else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_X) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.rShoulder, "rShoulderButton");
				}else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_X) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.lThumb, "lThumbButton");
				}else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_X) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.rThumb, "rThumbButton");
				}else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_X) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.start, "startButton");
				}else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_X) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.back, "backButton");
				}else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_X) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.guide, "guideButton");
				}else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_X) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.unknown, "unknownButton");
				}
			}

			Game.writeOnceString = String.valueOf(XInputConstants.XINPUT_GAMEPAD_X);
			//}
//			XInputDevice.x = key;
//			Game.writeOnceProperty = "xButton";
			Game.settingsSetup = false;
			break;
		case -16:
			if(!replaceCheck) {
				if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_START) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.a, "aButton");
				}else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_START){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.b, "bButton");
				}else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_START){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.x, "xButton");
				}else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_START){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.y, "yButton");
				}else if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_START) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.up, "upButton");
				}else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_START) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.down, "downButton");
				}else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_START) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.left, "leftButton");
				}else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_START) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.right, "rightButton");
				}else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_START) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.lShoulder, "lShoulderButton");
				}else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_START) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.rShoulder, "rShoulderButton");
				}else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_START) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.lThumb, "lThumbButton");
				}else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_START) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.rThumb, "rThumbButton");
				}else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_START) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.start, "startButton");
				}else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_START) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.back, "backButton");
				}else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_START) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.guide, "guideButton");
				}else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_START) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.unknown, "unknownButton");
				}
			}	
			Game.writeOnceString = String.valueOf(XInputConstants.XINPUT_GAMEPAD_START);
			//}
//			XInputDevice.start = key;
//			Game.writeOnceProperty = "startButton";
			break;
		case -17:
			if(!replaceCheck) {
				if(XInputDevice.aFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.a, "aButton");
				}else if(XInputDevice.bFake == XInputConstants.XINPUT_GAMEPAD_BACK){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.b, "bButton");
				}else if(XInputDevice.xFake == XInputConstants.XINPUT_GAMEPAD_BACK){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.x, "xButton");
				}else if(XInputDevice.yFake == XInputConstants.XINPUT_GAMEPAD_BACK){
					this.changeButtonXDeviceOrganizer(key, XInputDevice.y, "yButton");
				}else if(XInputDevice.upFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.up, "upButton");
				}else if(XInputDevice.downFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.down, "downButton");
				}else if(XInputDevice.leftFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.left, "leftButton");
				}else if(XInputDevice.rightFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.right, "rightButton");
				}else if(XInputDevice.lShoulderFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.lShoulder, "lShoulderButton");
				}else if(XInputDevice.rShoulderFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.rShoulder, "rShoulderButton");
				}else if(XInputDevice.lThumbFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.lThumb, "lThumbButton");
				}else if(XInputDevice.rThumbFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.rThumb, "rThumbButton");
				}else if(XInputDevice.startFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.start, "startButton");
				}else if(XInputDevice.backFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.back, "backButton");
				}else if(XInputDevice.guideFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.guide, "guideButton");
				}else if(XInputDevice.unknownFake == XInputConstants.XINPUT_GAMEPAD_BACK) {
					this.changeButtonXDeviceOrganizer(key, XInputDevice.unknown, "unknownButton");
				}
			}
			Game.writeOnceString = String.valueOf(XInputConstants.XINPUT_GAMEPAD_BACK);
			//}
//			XInputDevice.back = key;
//			Game.writeOnceProperty = "backButton";
			break;
		default:
			break;
		}
		
		if (buttonToChange != 0) {
			switch(key) {
			case XInputConstants.XINPUT_GAMEPAD_A:
				Game.writeOnceProperty = "aButton";
				break;
			case XInputConstants.XINPUT_GAMEPAD_B:
				Game.writeOnceProperty = "bButton";
				break;
			case XInputConstants.XINPUT_GAMEPAD_X:
				Game.writeOnceProperty = "xButton";
				break;
			case XInputConstants.XINPUT_GAMEPAD_Y:
				Game.writeOnceProperty = "yButton";
				break;
			case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				Game.writeOnceProperty = "upButton";
				break;
			case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				Game.writeOnceProperty = "downButton";
				break;
			case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				Game.writeOnceProperty = "leftButton";
				break;
			case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
				Game.writeOnceProperty = "rightButton";
				break;
			case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
				Game.writeOnceProperty = "lShoulderButton";
				break;
			case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
				Game.writeOnceProperty = "rShoulderButton";
				break;
			case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
				Game.writeOnceProperty = "lThumbButton";
				break;
			case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
				Game.writeOnceProperty = "rThumbButton";
				break;
			case XInputConstants.XINPUT_GAMEPAD_START:
				Game.writeOnceProperty = "startButton";
				break;
			case XInputConstants.XINPUT_GAMEPAD_BACK:
				Game.writeOnceProperty = "backButton";
				break;
			case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
				Game.writeOnceProperty = "guideButton";
				break;
			case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
				Game.writeOnceProperty = "unknownButton";
				break;
			default:
				break;
			}
			updateControls();
			Game.writeOnceToSettings = true;
			
			//Game.writeOnceString = String.valueOf(key);
			
			if(XInputDevice.a != XInputConstants.XINPUT_GAMEPAD_A) {
				
			}
		}
		buttonChangeTimer = 0;
		Game.enterButtonPushedDown= false;
		//System.out.println("key = " + key);
		//System.out.println("XInputDevice.a = " + XInputDevice.a);
		//System.out.println("XInputDevice.x = " + XInputDevice.x);
		return;
	}

	public void changeButtonDirectInputHelper() {
		return;
	}
	
	public void changeButtonDirectInput() {
		return;
	}
	
	public void setGamepadButtonHolderHighlightedWithSelector(int selector) {
		switch(selector) {
		case -2:
			gamepadButtonHolderHighlighted[0] = true;
			break;
		case -3:
			gamepadButtonHolderHighlighted[1] = true;
			break;
		case -4:
			gamepadButtonHolderHighlighted[2] = true;
			break;
		case -5:
			gamepadButtonHolderHighlighted[3] = true;
			break;
		case -6:
			gamepadButtonHolderHighlighted[4] = true;
			break;
		case -7:
			gamepadButtonHolderHighlighted[5] = true;
			break;
		case -8:
			gamepadButtonHolderHighlighted[6] = true;
			break;
		case -9:
			gamepadButtonHolderHighlighted[7] = true;
			break;
		case -10:
			gamepadButtonHolderHighlighted[8] = true;
			break;
		case -11:
			gamepadButtonHolderHighlighted[9] = true;
			break;
		case -12:
			gamepadButtonHolderHighlighted[10] = true;
			break;
		case -13:
			gamepadButtonHolderHighlighted[11] = true;
			break;
		case -14:
			gamepadButtonHolderHighlighted[12] = true;
			break;
		case -15:
			gamepadButtonHolderHighlighted[13] = true;
			break;
		case -16:
			gamepadButtonHolderHighlighted[14] = true;
			break;
		case -17:
			gamepadButtonHolderHighlighted[15] = true;
			break;
		case -18:
			gamepadButtonHolderHighlighted[16] = true;
			break;
		case -19:
			gamepadButtonHolderHighlighted[17] = true;
			break;
		case -20:
			gamepadButtonHolderHighlighted[18] = true;
			break;
		case -21:
			gamepadButtonHolderHighlighted[19] = true;
			break;
		case -22:
			gamepadButtonHolderHighlighted[20] = true;
			break;
		case -23:
			gamepadButtonHolderHighlighted[21] = true;
			break;
		case -24:
			gamepadButtonHolderHighlighted[22] = true;
			break;
		case -25:
			gamepadButtonHolderHighlighted[23] = true;
			break;
		default:
			break;
		}
	}
	public BufferedImage[] getGamepadButtonHolder() {
		return gamepadButtonHolder;
	}
	public void setGamepadButtonHolder(BufferedImage[] gamepadButtonHolder) {
		this.gamepadButtonHolder = gamepadButtonHolder;
	}
	public BufferedImage[] getUpKeyWASD() {
		return upKeyWASD;
	}
	public void setUpKeyWASD(BufferedImage[] upKeyWASD) {
		this.upKeyWASD = upKeyWASD;
	}
	public BufferedImage[] getDownKeyWASD() {
		return downKeyWASD;
	}
	public void setDownKeyWASD(BufferedImage[] downKeyWASD) {
		this.downKeyWASD = downKeyWASD;
	}
	public BufferedImage[] getLeftKeyWASD() {
		return leftKeyWASD;
	}
	public void setLeftKeyWASD(BufferedImage[] leftKeyWASD) {
		this.leftKeyWASD = leftKeyWASD;
	}
	public BufferedImage[] getRightKeyWASD() {
		return rightKeyWASD;
	}
	public void setRightKeyWASD(BufferedImage[] rightKeyWASD) {
		this.rightKeyWASD = rightKeyWASD;
	}
	public BufferedImage[] getShootKeyWASD() {
		return shootKeyWASD;
	}
	public void setShootKeyWASD(BufferedImage[] shootKeyWASD) {
		this.shootKeyWASD = shootKeyWASD;
	}
	public BufferedImage[] getItemKeyWASD() {
		return itemKeyWASD;
	}
	public void setItemKeyWASD(BufferedImage[] itemKeyWASD) {
		this.itemKeyWASD = itemKeyWASD;
	}
	public BufferedImage[] getPauseKeyWASD() {
		return pauseKeyWASD;
	}
	public void setPauseKeyWASD(BufferedImage[] pauseKeyWASD) {
		this.pauseKeyWASD = pauseKeyWASD;
	}
	public BufferedImage[] getCancelKeyWASD() {
		return cancelKeyWASD;
	}
	public void setCancelKeyWASD(BufferedImage[] cancelKeyWASD) {
		this.cancelKeyWASD = cancelKeyWASD;
	}
	public BufferedImage[] getUpKeyXDevice() {
		return upKeyXDevice;
	}
	public void setUpKeyXDevice(BufferedImage[] upKeyXDevice) {
		this.upKeyXDevice = upKeyXDevice;
	}
	public BufferedImage[] getDownKeyXDevice() {
		return downKeyXDevice;
	}
	public void setDownKeyXDevice(BufferedImage[] downKeyXDevice) {
		this.downKeyXDevice = downKeyXDevice;
	}
	public BufferedImage[] getLeftKeyXDevice() {
		return leftKeyXDevice;
	}
	public void setLeftKeyXDevice(BufferedImage[] leftKeyXDevice) {
		this.leftKeyXDevice = leftKeyXDevice;
	}
	public BufferedImage[] getRightKeyXDevice() {
		return rightKeyXDevice;
	}
	public void setRightKeyXDevice(BufferedImage[] rightKeyXDevice) {
		this.rightKeyXDevice = rightKeyXDevice;
	}
	public BufferedImage[] getShootKeyXDevice() {
		return shootKeyXDevice;
	}
	public void setShootKeyXDevice(BufferedImage[] shootKeyXDevice) {
		this.shootKeyXDevice = shootKeyXDevice;
	}
	public BufferedImage[] getItemKeyXDevice() {
		return itemKeyXDevice;
	}
	public void setItemKeyXDevice(BufferedImage[] itemKeyXDevice) {
		this.itemKeyXDevice = itemKeyXDevice;
	}
	public BufferedImage[] getPauseKeyXDevice() {
		return pauseKeyXDevice;
	}
	public void setPauseKeyXDevice(BufferedImage[] pauseKeyXDevice) {
		this.pauseKeyXDevice = pauseKeyXDevice;
	}
	public BufferedImage[] getCancelKeyXDevice() {
		return cancelKeyXDevice;
	}
	public void setCancelKeyXDevice(BufferedImage[] cancelKeyXDevice) {
		this.cancelKeyXDevice = cancelKeyXDevice;
	}
	public BufferedImage[] getUpKeyDirectInput() {
		return upKeyDirectInput;
	}
	public void setUpKeyDirectInput(BufferedImage[] upKeyDirectInput) {
		this.upKeyDirectInput = upKeyDirectInput;
	}
	public BufferedImage[] getDownKeyDirectInput() {
		return downKeyDirectInput;
	}
	public void setDownKeyDirectInput(BufferedImage[] downKeyDirectInput) {
		this.downKeyDirectInput = downKeyDirectInput;
	}
	public BufferedImage[] getLeftKeyDirectInput() {
		return leftKeyDirectInput;
	}
	public void setLeftKeyDirectInput(BufferedImage[] leftKeyDirectInput) {
		this.leftKeyDirectInput = leftKeyDirectInput;
	}
	public BufferedImage[] getRightKeyDirectInput() {
		return rightKeyDirectInput;
	}
	public void setRightKeyDirectInput(BufferedImage[] rightKeyDirectInput) {
		this.rightKeyDirectInput = rightKeyDirectInput;
	}
	public BufferedImage[] getShootKeyDirectInput() {
		return shootKeyDirectInput;
	}
	public void setShootKeyDirectInput(BufferedImage[] shootKeyDirectInput) {
		this.shootKeyDirectInput = shootKeyDirectInput;
	}
	public BufferedImage[] getItemKeyDirectInput() {
		return itemKeyDirectInput;
	}
	public void setItemKeyDirectInput(BufferedImage[] itemKeyDirectInput) {
		this.itemKeyDirectInput = itemKeyDirectInput;
	}
	public BufferedImage[] getPauseKeyDirectInput() {
		return pauseKeyDirectInput;
	}
	public void setPauseKeyDirectInput(BufferedImage[] pauseKeyDirectInput) {
		this.pauseKeyDirectInput = pauseKeyDirectInput;
	}
	public BufferedImage[] getCancelKeyDirectInput() {
		return cancelKeyDirectInput;
	}
	public void setCancelKeyDirectInput(BufferedImage[] cancelKeyDirectInput) {
		this.cancelKeyDirectInput = cancelKeyDirectInput;
	}
}
