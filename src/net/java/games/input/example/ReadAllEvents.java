package net.java.games.input.example;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.game.src.main.BuzzyBeetleShellFireball;
import com.game.src.main.ContraFireball;
import com.game.src.main.ControlsController;
import com.game.src.main.Fireball;
import com.game.src.main.Game;
import com.game.src.main.GloveFireball;
import com.game.src.main.GreenShellFireball;
import com.game.src.main.HUD;
import com.game.src.main.LeaderboardController;
import com.game.src.main.RedShellFireball;
import com.game.src.main.SoundLoops;
import com.game.src.main.TrackController;
import com.game.src.main.VolumeSlider;
import com.game.src.main.Game.STATE;
import com.github.strikerx3.jxinput.XInputDevice;
import com.github.strikerx3.jxinput.natives.XInputConstants;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.DirectAndRawInputEnvironmentPlugin;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;
import net.java.games.input.SpecialCases;
import net.java.games.input.SwitchedValues;

/**
 * This class shows how to use the event queue system in JInput. It will show
 * how to get the controllers, how to get the event queue for a controller, and
 * how to read and process events from the queue.
 * 
 * @author Endolf
 */
public class ReadAllEvents {
	public static ArrayList<String> disconnectedControllers = new ArrayList<String>();
	public static ArrayList<String> releasedControllers = new ArrayList<String>();
	public static Game game;
	public static Event event;
	public static String controllerName = "";
	public static String key = "";
	public static String lastDpadPressed = "";
	public static long joystickTimer = 0;
//	public static long buttonTimer = 0;
	public static long controllerSensitivityTimer = 0;
	public static long slowDownForAnalogTimer = 0;
	public static long reconnectTimer = 0;
//	public static long analogSlowDownTimer = 0;
	public static double lY = 0;
	public static double lX = 0;
	public static double lY2 = 0;
	public static double lX2 = 0;
	public static double lYDelta = 0;
	public static double lXDelta = 0;
	public static boolean upHeld = false;
	public static boolean downHeld = false;
	public static boolean leftHeld = false;
	public static boolean rightHeld = false;
	public static boolean shootPressed = false;
	public static boolean itemPressed = false;
	public static boolean pausePressed = false;
	public static boolean cancelPressed = false;
	public static boolean upReleased = false;
	public static boolean downReleased = false;
	public static boolean leftReleased = false;
	public static boolean rightReleased = false;
	public static boolean shootReleased = false;
	public static boolean itemReleased = false;
	public static boolean pauseReleased = false;
	public static boolean cancelReleased = false;
	public static boolean controllerWasConnected = false;
	public static boolean controllerDisconnectedReset = false;
	public static boolean resettingControllerEnvironment = false;
	/* Get the available controllers */
	public static Controller[] controllers = ControllerEnvironment
			.getDefaultEnvironment().getControllers();
	
	public static void reset() {
		upHeld = false;
		upReleased = false;
		downHeld = false;
		downReleased = false;
		leftHeld = false;
		leftReleased = false;
		rightHeld = false;
		rightReleased = false;
		shootPressed = false;
		shootReleased = false;
		itemPressed = false;
		itemReleased = false;
		cancelPressed = false;
		cancelReleased = false;
		pausePressed = false;
		pauseReleased = false;
		return;
	}

    
    /**
     * reflective constructor for rescanning and finding new devices.
     * @return	DefaultControllerEnvironment object to find new devices with
     * @throws ReflectiveOperationException
     */
    private static ControllerEnvironment createDefaultEnvironment() throws ReflectiveOperationException {
        // Find constructor (class is package private, so we can't access it directly)
        Constructor<ControllerEnvironment> constructor = (Constructor<ControllerEnvironment>)
            Class.forName("net.java.games.input.DefaultControllerEnvironment").getDeclaredConstructors()[0];

        // Constructor is package private, so we have to deactivate access control checks
        constructor.setAccessible(true);

        // Create object with default constructor
        return constructor.newInstance();
    }
    
    /**
     * Fix windows 8 warnings by defining a working plugin
     */
    static {

        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                String os = System.getProperty("os.name", "").trim();
                if (os.startsWith("Windows 8")) {  // 8, 8.1 etc.

                    // disable default plugin lookup
                    System.setProperty("jinput.useDefaultPlugin", "false");

                    // set to same as windows 7 (tested for windows 8 and 8.1)
                    System.setProperty("net.java.games.input.plugins", "net.java.games.input.DirectAndRawInputEnvironmentPlugin");

                }
                return null;
            }
        });

    }
	
	public ReadAllEvents() {
		float oldDPadvalue = 0.0f; // hat neutral state = 0.0
		while (true) {
			if(Game.connectingToServer)//maybe put this after while (queue.getNextEvent(event)){
				continue;
			if (controllers.length == 0) {
				System.out.println("Found no controllers.");
				break;
//				System.exit(0);
			}
			for (int i = 0; i < controllers.length; i++) {
				/* Remember to poll each one */
				if(controllers.length-1 <= i) {
					if(controllerWasConnected && controllerDisconnectedReset &&Game.gameControllerInUseDI) {
						controllerWasConnected = false;
						controllerDisconnectedReset = false;
	        			game.getPlayer().reset();
	        			if(Game.State != STATE.GAME && 
	        					!(Game.State == STATE.CONTROLS && System.currentTimeMillis() <ControlsController.buttonChangeTimer) &&
	        					!(Game.State == STATE.SET_SCORE && Game.selectorButtonPosition == 0)) 
	        					Game.keysAreInUse = false;
						Game.gameControllerInUse = false;
						Game.gameControllerInUseDI = false;
					}
				}
				if(System.currentTimeMillis() < ControlsController.buttonChangeTimer) {
					
				}else {
				controllers[i].poll();

				/* Get the controllers event queue */
				EventQueue queue = controllers[i].getEventQueue();

				/* Create an event object for the underlying plugin to populate */
				event = new Event();
				 
				/* For each object in the queue */
				while (queue.getNextEvent(event)) {
					/*
					 * Create a string buffer and put in it, the controller name,
					 * the time stamp of the event, the name of the component
					 * that changed and the new value.
					 * 
					 * Note that the timestamp is a relative thing, not
					 * absolute, we can tell what order events happened in
					 * across controllers this way. We can not use it to tell
					 * exactly *when* an event happened just the order.
					 */
					StringBuffer buffer = new StringBuffer(controllers[i]
							.getName());
					buffer.append(" at ");
					buffer.append(event.getNanos()).append(", ");
					Component comp = event.getComponent();
					buffer.append(comp.getName()).append(" changed to ");
					float value = event.getValue();

					/*
					 * Check the type of the component and display an
					 * appropriate value
					 */
					if (comp.isAnalog()) {
						buffer.append(value);
					} else {
						if (value == 1.0f) {
							buffer.append("On");
						} else {
							buffer.append("Off");
						}
					}
//					if(controllers[i].getType().toString().equals("Gamepad") && !(controllers[i].getName().equals(controllerName))){//change controllers on the fly
//					controllerName = controllers[i].getName();
//	    			SwitchedValues.setUpValues(controllers[i].getName());}
					//!comp.getName().equals("X Rotation") && !comp.getName().equals("Y Rotation") GET RID OF THIS TO MAKE L2 and R2 FEEL MORE ANALOG WHEN THEY'RE SET TO CANCEL/ITEM
					if(controllers[i].getType().toString().equals("Gamepad") && !comp.getName().equals("X Rotation") && !comp.getName().equals("Y Rotation") &&
					  (controllers[i].getName().equals(controllerName)||controllerName.equals("")||
					   (Game.keysAreInUse == false && !(Game.State == STATE.SET_SCORE && Game.selectorButtonPosition == 0)))) {//if gamepad && controllerName isn't set(1 controller)
						if((Game.gameControllerInUse == false || Game.gameControllerInUseDI == false || Game.keysAreInUse == false)) {//CHANGE value != -1 to something else(DS4 r2 and l2 trigger this)
//					    	joystickTimer = 0;
//					    	if(value != 1.0f && value != -1 && (value > 0.15 || value < -0.15 || value == 0)) {//Maybe Extend?
					    	if((comp.getName().equals("Hat Switch") || value != 1.0f) && value != -1 && (value > 0.15 || value < -0.15 || value == 0)) {//Maybe Extend?
					 	    	if(Game.State == STATE.SET_SCORE && Game.selectorButtonPosition == 0 ||
					 	    		((Game.State == STATE.TRANSITION_ENTRANCE || Game.State == STATE.TRANSITION_WIN || Game.State == STATE.TRANSITION_ITEM)))
					 	    		Game.keysAreInUse = false;
					 	    	else
					 	    		Game.keysAreInUse = true;
//					 	    	System.out.println("HOW" + buffer.toString() + " " + controllers[i].getType().toString() + " "+ comp.isRelative());
					    		Game.gameControllerInUse = true;
					    		Game.gameControllerInUseDI = true;
					    		controllerWasConnected = true;
					    		if(!(Game.State == STATE.SET_SCORE && Game.selectorButtonPosition == 0))
					    			reset();
//					    		if(controllerName != controllers[i].getName()) {
					    		if(!controllerName.equals(controllers[i].getName())) {
					    			controllerName = controllers[i].getName();
				    				SwitchedValues.setUpValues(controllers[i].getName());
//				    				System.out.println("controller name = "+controllerName+ " special button = "+SpecialCases.buttonName(controllerName, SwitchedValues.itemKey));
//				    				game.getTextures().changePressDI(SpecialCases.buttonName(controllerName, SwitchedValues.itemKey));
//				    				game.getHUD().setPressDI(game.getTextures().pressDI);
									try {
										if(game.getControlsController() == null)
											Thread.sleep(1000);
									} catch (InterruptedException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
				    				if(game.getControlsController() != null)
				    					game.getControlsController().updateControls();
				    				else
				    					game.getTextures().changePressDI(SpecialCases.buttonName(controllerName, SwitchedValues.itemKey));
				    					game.getHUD().setPressDI(game.getTextures().pressDI);
				    				try {
										LeaderboardController.writeToSettings("Controller Name: ", controllerName);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
//					    			SwitchedValues.setUpValues(controllers[i].getName());
					    		}
					    		//switching press B1 buttons for item
//					    		game.getTextures().changeBufferedImagesDirectInput(game.getControlsController().getUpKeyDirectInput(),SwitchedValues.upKey);
					    		if(game.getControlsController() != null)
					    			game.getControlsController().updateControls();
					    		if(!(Game.State == STATE.SET_SCORE && Game.selectorButtonPosition == 0))
					    		continue;
					    	}
					    	if((comp.getName().equals("Hat Switch") || comp.getName().equals("Z Rotation") ||
					    				comp.getName().equals("Z Axis") ||comp.getName().equals("X Axis") ||
					    				comp.getName().equals("Y Axis")) && Game.State != STATE.SET_SCORE) {
			 					if(joystickTimer == 0)
			 						joystickTimer = System.currentTimeMillis() + 500;
			 					else
			 						joystickTimer = System.currentTimeMillis() + 30;
			 					
					    	}
					    	//l & r triggers?
					    	//buttons?
//					    	upHeld = false;
//					    	downHeld = false;
//					    	leftHeld = false;
//					    	rightHeld = false;
					    }
			    		if(!controllerName.equals(controllers[i].getName()) && ControlsController.buttonChangeTimer < System.currentTimeMillis()) {
			    			continue;
			    		}
//			    		System.out.println("keys = "+Game.keysAreInUse +" xinput ="+ Game.gameControllerInUse +" dinput = "+ Game.gameControllerInUseDI);
//				    	upHeld = false;
//				    	downHeld = false;
//				    	leftHeld = false;
//				    	rightHeld = false;
						key = SwitchedValues.switchValue(comp.getName());
						/* Create hatSwitch for D-Pad on DS4 controllers */
						if(comp.getName().equals("Hat Switch")) {
//							String[] directions = { "NW", "N", "NE", "E", "SE", "S", "SW", "W" };
							Component hatSwitch = controllers[i].getComponent(Component.Identifier.Axis.POV);
							float dPadvalue = hatSwitch.getPollData() * 8;
							 if (dPadvalue != oldDPadvalue) // only operate on changes in component value
							    {
								 	oldDPadvalue = dPadvalue; // update old value
							        
							        // make sure the value is an integer in a valid range
							        if (dPadvalue % 1 != 0 && dPadvalue >= 0.0f && dPadvalue <= 8.0f)
							        {
							            System.err.println("Invalid value: " + dPadvalue);
							        }
							        else if (dPadvalue != 0.0f) // if value is a valid index
							        {
							        	StringBuffer s = new StringBuffer(comp.getName());
							        	s.append((int)dPadvalue-1);
							        	lastDpadPressed = s.toString();
							        	key = SwitchedValues.switchValue(s.toString());
//							        	System.out.println(s);
//							            System.out.println((int)dPadvalue - 1);
//							            System.out.println(directions[(int)dPadvalue - 1]);
							        }
							        else{
							        	if(SwitchedValues.upKey.contains("Hat Switch")) {
							        		upHeld = false;
							        	}
							        	if(SwitchedValues.downKey.contains("Hat Switch")) {
							        		downHeld = false;
							        	}
							        	if(SwitchedValues.leftKey.contains("Hat Switch")) {
							        		leftHeld = false;
							        	}
							        	if(SwitchedValues.rightKey.contains("Hat Switch")) {
							        		rightHeld = false;
							        	}
							        	if(SwitchedValues.shootKey.contains("Hat Switch")) {
							        		shootPressed = false;
							        	}
							        	if(SwitchedValues.itemKey.contains("Hat Switch")) {
							        		itemPressed = false;
							        	}
							        	if(SwitchedValues.cancelKey.contains("Hat Switch")) {
							        		cancelPressed = false;
							        	}
							        	if(SwitchedValues.pauseKey.contains("Hat Switch")) {
							        		pausePressed = false;
							        	}
							        }
//							        else if(leftHeld || rightHeld || upHeld || downHeld) {
//							        	upHeld = false;
//							        	downHeld = false;
//							        	leftHeld = false;
//							        	rightHeld = false;
//							        	key = "";
//							        	System.out.println(dPadvalue);
//							        }
							    }
						}
						if(value == 1.0f || key.contains("Hat Switch")) { //comp.getName().equals("Hat Switch")) {
							if(key == SwitchedValues.leftKey) {
								leftHeld = true;
								//put these if(a) a = false into hat up above?
								if(rightHeld)
									rightHeld = false;
								if(upHeld)
									upHeld = false;
								if(downHeld)
									downHeld = false;
								leftReleased = true;
							}
							else if(key == SwitchedValues.rightKey) {
								rightHeld = true;
								if(leftHeld)
									leftHeld = false;
								if(upHeld)
									upHeld = false;
								if(downHeld)
									downHeld = false;
								rightReleased = true;
							}
							else if(key == SwitchedValues.upKey) {
								upHeld = true;
								if(rightHeld)
									rightHeld = false;
								if(leftHeld)
									leftHeld = false;
								if(downHeld)
									downHeld = false;
								upReleased = true;
							}
							else if(key == SwitchedValues.downKey) {
								downHeld = true;
								if(rightHeld)
									rightHeld = false;
								if(upHeld)
									upHeld = false;
								if(leftHeld)
									leftHeld = false;
								downReleased = true;
							}
							else if(key == SwitchedValues.shootKey) {
								shootPressed = true;
								shootReleased = true;
							}	
							else if(key == SwitchedValues.itemKey) {
								itemPressed = true;
								itemReleased = true;
							}
							else if(key == SwitchedValues.pauseKey) {
								pausePressed = true;
								pauseReleased = true;
							}
							else if(key == SwitchedValues.cancelKey) {
								cancelPressed = true;
								cancelReleased = true;
							}
						}
						else if(value == 0.0f) {
//						else if(value == 0.0f && !comp.getName().equals("X Rotation") && !comp.getName().equals("Y Rotation") ||
//								((comp.getName().equals("X Rotation") || comp.getName().equals("Y Rotation")) && value == -1.0f)) {
							if(key == SwitchedValues.leftKey)
								leftHeld = false;
							else if(key == SwitchedValues.rightKey)
								rightHeld = false;
							else if(key == SwitchedValues.upKey)
								upHeld = false;
							else if(key == SwitchedValues.downKey)
								downHeld = false;
							else if(key == SwitchedValues.shootKey)
								shootPressed = false;
							else if(key == SwitchedValues.itemKey)
								itemPressed = false;
							else if(key == SwitchedValues.pauseKey)
								pausePressed = false;
							else if(key == SwitchedValues.cancelKey)
								cancelPressed = false;
						}
						if(upHeld && downHeld) {
							if(key == SwitchedValues.upKey)
								downHeld = false;
							else if(key == SwitchedValues.downKey)
								upHeld = false;
							else {
								upHeld = false;
								downHeld = false;
							}
						}
						if(leftHeld && rightHeld) {
							if(key == SwitchedValues.leftKey)
								rightHeld = false;
							else if(key == SwitchedValues.rightKey)
								leftHeld = false;
							else {
								leftHeld = false;
								rightHeld = false;
							}
						}
				    	if(comp.getName().equals("Y Axis")) {
				    		lY2 = lY;
				    		lY = value;
				    		lYDelta = lY2 - lY;
//				    		lYDelta = Math.abs(lY-lYDelta);
				    	}
				    	if(comp.getName().equals("X Axis")) {
				    		lX2 = lX;
				    		lX = value;
				    		lXDelta = lX2 - lX;
//				    		lXDelta = Math.abs(lX-lXDelta);
				    	}
//					    if(Game.State == STATE.MENU && Game.gameControllerInUse) {
//					    	if(((comp.getName().equals("Y Axis") && value < -.65) || (upHeld)) && joystickTimer < System.currentTimeMillis()) {
//					    		if((upHeld) && game.getCheatTimer() < System.currentTimeMillis()) {
//					    			game.setCheatString("u");
//					    			game.setCheatTimer(System.currentTimeMillis() + 1500);
//					    		}
//					    		else if((upHeld) && System.currentTimeMillis() < game.getCheatTimer() && game.getCheatString().equals("u")) {
//					    			game.setCheatString("uu");
//					    			game.setCheatTimer(System.currentTimeMillis() + 1500);
//					    		}
//					    		else if((XInputDevice.upHeld) && System.currentTimeMillis() < game.getCheatTimer()) {
//					    			game.setCheatString("");
//					    			game.setCheatTimer(System.currentTimeMillis() + 1500);
//					    		}
//					    		if(Game.enterButtonPushedDown)
//									Game.escapePressedNegateAction = true;
//								if(Game.selectorButtonPosition > 0) {
//									Game.selectorButtonPosition--;
//									if(Game.hudSFXPosition == 3)
//										Game.hudSFXPosition = 0;
//									else
//										Game.hudSFXPosition++;
//									Game.hudSFX.get(Game.hudSFXPosition).play();
//								}
//								else if(Game.selectorButtonPosition == 0) {
//									Game.selectorButtonPosition = -2;
//									if(Game.hudSFXPosition == 3)
//										Game.hudSFXPosition = 0;
//									else
//										Game.hudSFXPosition++;
//									Game.hudSFX.get(Game.hudSFXPosition).play();
//								}
//								if(joystickTimer == 0)
//									joystickTimer = System.currentTimeMillis() + 500;
//								else
//									joystickTimer = System.currentTimeMillis() + 30;
//					    	}
//					    	else if(((comp.getName().equals("Y Axis") && .65 < value) || (downHeld)) && joystickTimer < System.currentTimeMillis()) {
//					    		if((downHeld) && System.currentTimeMillis() < game.getCheatTimer() && game.getCheatString().equals("uu")) {
//					    			game.setCheatString("uud");
//					    			game.setCheatTimer(System.currentTimeMillis() + 1500);
//					    		}
//					    		else if((downHeld) && System.currentTimeMillis() < game.getCheatTimer() && game.getCheatString().equals("uud")) {
//					    			game.setCheatString("uudd");
//					    			game.setCheatTimer(System.currentTimeMillis() + 1500);
//					    		}
//					    		else if((downHeld) && System.currentTimeMillis() < game.getCheatTimer()) {
//					    			game.setCheatString("");
//					    			game.setCheatTimer(System.currentTimeMillis() + 1500);
//					    		}
//					    		if(Game.enterButtonPushedDown)
//									Game.escapePressedNegateAction = true;
//								if(Game.selectorButtonPosition < 0) {
//									Game.selectorButtonPosition = 0;
//									if(Game.hudSFXPosition == 3)
//										Game.hudSFXPosition = 0;
//									else
//										Game.hudSFXPosition++;
//									Game.hudSFX.get(Game.hudSFXPosition).play();
//								}
//								else if(Game.selectorButtonPosition < 2) {
//									Game.selectorButtonPosition++;
//									if(Game.hudSFXPosition == 3)
//										Game.hudSFXPosition = 0;
//									else
//										Game.hudSFXPosition++;
//									Game.hudSFX.get(Game.hudSFXPosition).play();
//								}
//								if(joystickTimer == 0)
//									joystickTimer = System.currentTimeMillis() + 500;
//								else
//									joystickTimer = System.currentTimeMillis() + 30;
//					    	}
//					    	if(((comp.getName().equals("X Axis") && value < -.65) || (leftHeld)) && joystickTimer < System.currentTimeMillis()) {
//					    		if((leftHeld) && System.currentTimeMillis() < game.getCheatTimer() && game.getCheatString().equals("uudd")) {
//					    			game.setCheatString("uuddl");
//					    			game.setCheatTimer(System.currentTimeMillis() + 1500);
//					    		}
//					    		else if((leftHeld) && System.currentTimeMillis() < game.getCheatTimer() && game.getCheatString().equals("uuddlr")) {
//					    			game.setCheatString("uuddlrl");
//					    			game.setCheatTimer(System.currentTimeMillis() + 1500);
//					    		}
//					    		else if((leftHeld) && System.currentTimeMillis() < game.getCheatTimer()) {
//					    			game.setCheatString("");
//					    			game.setCheatTimer(System.currentTimeMillis() + 1500);
//					    		}
//					    		if(Game.enterButtonPushedDown)
//									Game.escapePressedNegateAction = true;
//								if(Game.selectorButtonPosition == 0) {
//									Game.selectorButtonPosition = -1;
//									if(Game.hudSFXPosition == 3)
//										Game.hudSFXPosition = 0;
//									else
//										Game.hudSFXPosition++;
//									Game.hudSFX.get(Game.hudSFXPosition).play();
//								}
//								else if(Game.selectorButtonPosition < -1 && Game.selectorButtonPosition >= -3) {
//									Game.selectorButtonPosition++;
//									if(Game.hudSFXPosition == 3)
//										Game.hudSFXPosition = 0;
//									else
//										Game.hudSFXPosition++;
//									Game.hudSFX.get(Game.hudSFXPosition).play();
//								}
//								if(joystickTimer == 0)
//									joystickTimer = System.currentTimeMillis() + 500;
//								else
//									joystickTimer = System.currentTimeMillis() + 30;
//					    	}
//					    	//else if((lX > .65 || (device.getComponents().getButtons().right)) && joystickTimer < System.currentTimeMillis()) {
//					    	else if(((comp.getName().equals("X Axis") && 0.65 < value) || (rightHeld)) && joystickTimer < System.currentTimeMillis()) {
//					    		if((rightHeld) && System.currentTimeMillis() < game.getCheatTimer() && game.getCheatString().equals("uuddl")) {
//					    			game.setCheatString("uuddlr");
//					    			game.setCheatTimer(System.currentTimeMillis() + 1500);
//					    		}
//					    		else if((rightHeld) && System.currentTimeMillis() < game.getCheatTimer() && game.getCheatString().equals("uuddlrl")) {
//					    			game.setCheatString("uuddlrlr");
//					    			game.setCheatTimer(System.currentTimeMillis() + 1500);
//					    		}
//					    		else if((rightHeld) && System.currentTimeMillis() < game.getCheatTimer()) {
//					    			game.setCheatString("");
//					    			game.setCheatTimer(System.currentTimeMillis() + 1500);
//					    		}
//					    		if(Game.enterButtonPushedDown)
//									Game.escapePressedNegateAction = true;
//								if(Game.selectorButtonPosition == 0) {
//									Game.selectorButtonPosition = -3;
//									if(Game.hudSFXPosition == 3)
//										Game.hudSFXPosition = 0;
//									else
//										Game.hudSFXPosition++;
//									Game.hudSFX.get(Game.hudSFXPosition).play();
//								}
//								else if(Game.selectorButtonPosition <= -1 && Game.selectorButtonPosition > -3) {
//									Game.selectorButtonPosition--;
//									if(Game.hudSFXPosition == 3)
//										Game.hudSFXPosition = 0;
//									else
//										Game.hudSFXPosition++;
//									Game.hudSFX.get(Game.hudSFXPosition).play();
//								}
//								if(joystickTimer == 0)
//									joystickTimer = System.currentTimeMillis() + 500;
//								else
//									joystickTimer = System.currentTimeMillis() + 30;
//					    	}
//					    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
//					    		!(rightHeld) && !(XInputDevice.leftHeld)&&
//					    		!(upHeld) && !(XInputDevice.downHeld)) {
//					    		joystickTimer = 0;
//						    }
//					    }
				    	
				    	
				    	
//						if(!Game.keysAreInUse)
//							Game.keysAreInUse = true;
//						//CONTROLLER ANALOG STICK
//						if(comp.getName().equals("Y Axis") ||comp.getName().equals("y")) {
//							if(0 < value) {
//								Game.selectorButtonPosition--;
//							}
//						}
					}
					//DEBUG WITH THIS
//					if(!comp.getName().equals("X Axis") && !comp.getName().equals("Y Axis") && !comp.getName().equals("Z Axis") && !comp.getName().equals("Z Rotation"))
//						System.out.println(buffer.toString() + " " + controllers[i].getType().toString() + " Relative = "+ comp.isRelative() +" Analog = "+ comp.isAnalog());
					
//					if(leftHeld || rightHeld || downHeld || upHeld || 
//							lX < -0.89 || 0.89 < lX || lY < -0.89 || 0.89 < lY) {
//						/* Create an event object for the underlying plugin to populate */
//						event = new Event();
//					}
				}
			}
			}

			/*
			 * Sleep for 20 milliseconds, in here only so the example doesn't
			 * thrash the system.
			 */
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void tick() {
//		System.out.println("lYDelta = "+lYDelta+" lY = "+lY);
//		System.out.println("LX = "+lX + " LY = "+lY);
		//shootPressed == button.isPressed()
		//!shootPressed && shootReleased == button.isReleased()
//		 System.out.println("Cancel Pressed = "+cancelPressed + " & Cancel Released = "+cancelReleased+
//				 " Escape Pressed Negate = "+Game.escapePressedNegateAction + " Game Controlller In Use = "+Game.gameControllerInUse);
		 if(Game.State == STATE.MENU && Game.gameControllerInUse) {
		    	if((lY < -0.65|| upHeld) && joystickTimer < System.currentTimeMillis()) {
		    		if((upHeld) && game.getCheatTimer() < System.currentTimeMillis()) {
		    			game.setCheatString("u");
		    			game.setCheatTimer(System.currentTimeMillis() + 1500);
		    		}
		    		else if((upHeld) && System.currentTimeMillis() < game.getCheatTimer() && game.getCheatString().equals("u")) {
		    			game.setCheatString("uu");
		    			game.setCheatTimer(System.currentTimeMillis() + 1500);
		    		}
		    		else if((upHeld) && System.currentTimeMillis() < game.getCheatTimer()) {
		    			game.setCheatString("");
		    			game.setCheatTimer(System.currentTimeMillis() + 1500);
		    		}
		    		if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.selectorButtonPosition > 0) {
						Game.selectorButtonPosition--;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
					else if(Game.selectorButtonPosition == 0) {
						Game.selectorButtonPosition = -2;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
					if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	else if((0.65 < lY  || downHeld) && joystickTimer < System.currentTimeMillis()) {
		    		if((downHeld) && System.currentTimeMillis() < game.getCheatTimer() && game.getCheatString().equals("uu")) {
		    			game.setCheatString("uud");
		    			game.setCheatTimer(System.currentTimeMillis() + 1500);
		    		}
		    		else if((downHeld) && System.currentTimeMillis() < game.getCheatTimer() && game.getCheatString().equals("uud")) {
		    			game.setCheatString("uudd");
		    			game.setCheatTimer(System.currentTimeMillis() + 1500);
		    		}
		    		else if((downHeld) && System.currentTimeMillis() < game.getCheatTimer()) {
		    			game.setCheatString("");
		    			game.setCheatTimer(System.currentTimeMillis() + 1500);
		    		}
		    		if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.selectorButtonPosition < 0) {
						Game.selectorButtonPosition = 0;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
					else if(Game.selectorButtonPosition < 2) {
						Game.selectorButtonPosition++;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
					if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	if((lX < -.65 || leftHeld) && joystickTimer < System.currentTimeMillis()) {
		    		if((leftHeld) && System.currentTimeMillis() < game.getCheatTimer() && game.getCheatString().equals("uudd")) {
		    			game.setCheatString("uuddl");
		    			game.setCheatTimer(System.currentTimeMillis() + 1500);
		    		}
		    		else if((leftHeld) && System.currentTimeMillis() < game.getCheatTimer() && game.getCheatString().equals("uuddlr")) {
		    			game.setCheatString("uuddlrl");
		    			game.setCheatTimer(System.currentTimeMillis() + 1500);
		    		}
		    		else if((leftHeld) && System.currentTimeMillis() < game.getCheatTimer()) {
		    			game.setCheatString("");
		    			game.setCheatTimer(System.currentTimeMillis() + 1500);
		    		}
		    		if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.selectorButtonPosition == 0) {
						Game.selectorButtonPosition = -1;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
					else if(Game.selectorButtonPosition < -1 && Game.selectorButtonPosition >= -3) {
						Game.selectorButtonPosition++;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
					if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	//else if((lX > .65 || (device.getComponents().getButtons().right)) && joystickTimer < System.currentTimeMillis()) {
		    	else if((0.65 < lX || rightHeld) && joystickTimer < System.currentTimeMillis()) {
		    		if((rightHeld) && System.currentTimeMillis() < game.getCheatTimer() && game.getCheatString().equals("uuddl")) {
		    			game.setCheatString("uuddlr");
		    			game.setCheatTimer(System.currentTimeMillis() + 1500);
		    		}
		    		else if((rightHeld) && System.currentTimeMillis() < game.getCheatTimer() && game.getCheatString().equals("uuddlrl")) {
		    			game.setCheatString("uuddlrlr");
		    			game.setCheatTimer(System.currentTimeMillis() + 1500);
		    		}
		    		else if((rightHeld) && System.currentTimeMillis() < game.getCheatTimer()) {
		    			game.setCheatString("");
		    			game.setCheatTimer(System.currentTimeMillis() + 1500);
		    		}
		    		if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.selectorButtonPosition == 0) {
						Game.selectorButtonPosition = -3;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
					else if(Game.selectorButtonPosition <= -1 && Game.selectorButtonPosition > -3) {
						Game.selectorButtonPosition--;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
					if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
		    		!(rightHeld) && !(leftHeld)&&
		    		!(upHeld) && !(downHeld)) {
		    		joystickTimer = 0;
			    }
		    	if(shootPressed||pausePressed) {
			    		if(!game.getCheatString().equals("uuddlrlrb") && !game.getCheatString().equals("uuddlrlrba"))
			    			Game.enterButtonPushedDown = true;
			    }
		    	if((!shootPressed && shootReleased)||(!pausePressed && pauseReleased)) {
			    		if((!shootPressed && shootReleased) && 
			    				System.currentTimeMillis() < game.getCheatTimer() && game.getCheatString().equals("uuddlrlrb")) {
			    			game.setCheatString("uuddlrlrba");
			    			game.setCheatTimer(System.currentTimeMillis() + 1500);
			    		}
			    		else if((!pausePressed && pauseReleased) && 
			    				System.currentTimeMillis() < game.getCheatTimer() && game.getCheatString().equals("uuddlrlrba")) {
			    			if(Game.smwCheatFullSoundLoop.clipIsActive())
			    				Game.smwCheatFullSoundLoop.stop();
			    			Game.smwCheatFullSoundLoop.play();
			    			game.setCheatString("");
			    			game.setCheatTimer(System.currentTimeMillis() + 1500);
			    			Game.currentlySelectedFireball = 5;
							Game.currentlySelectedCharacterSkin = 5;
							Game.fireballPosition = 5;
							Game.characterSkinPosition = 5;
							game.getPlayer().changeAnimations(5);
							if(Game.fireballSFX != null)
								Game.fireballSFX.close();
							if(Game.fireballPopSFX != null)
								Game.fireballPopSFX.close();
							Game.fireballSFX = new SoundLoops("res/Sounds/SFX/contrashootsfx.wav");
							Game.fireballPopSFX = new SoundLoops("res/Sounds/SFX/contrafireballpopsfx.wav");
							VolumeSlider.adjustSFX(Game.fireballSFX);
							VolumeSlider.adjustSFX(Game.fireballPopSFX);
							Game.skinNumber = null;
							pauseReleased = false;
			    			return;
			    		}
			    		else {
			    			if(System.currentTimeMillis() < game.getCheatTimer()) {
				    			game.setCheatString("");
				    			game.setCheatTimer(System.currentTimeMillis() + 1500);
			    			}
							Game.enterButtonPushedDown = false;
							if(!Game.escapePressedNegateAction) {
								switch(Game.selectorButtonPosition) {
									case -3:
										Game.selectorButtonPosition = -1;
										Game.State = STATE.LEADERBOARD;
										if(Game.smb3OpenSoundLoop.clipIsActive())
											Game.smb3OpenSoundLoop.stop();
										Game.smb3OpenSoundLoop.play();
										break;
									case -2:
										Game.selectorButtonPosition = -1;
										Game.State = STATE.SETTINGS;
										if(Game.smb3OpenSoundLoop.clipIsActive())
											Game.smb3OpenSoundLoop.stop();
										Game.smb3OpenSoundLoop.play();
										break;
									case -1:
										Game.selectorButtonPosition = -1;
										Game.State = STATE.HELP;
										if(Game.smb3OpenSoundLoop.clipIsActive())
											Game.smb3OpenSoundLoop.stop();
										Game.smb3OpenSoundLoop.play();
										break;
									case 0:
										if(Game.skipAnimations)
											Game.State = STATE.GAME;
										else
											Game.State = STATE.TRANSITION_ENTRANCE;
										Game.keysAreInUse = false;
										Game.smb3CoinSoundLoop.play();
										break;
									case 1:
										Game.selectorButtonPosition = -1;
										Game.State = STATE.SHOP;
										if(Game.smb3OpenSoundLoop.clipIsActive())
											Game.smb3OpenSoundLoop.stop();
										Game.smb3OpenSoundLoop.play();
										break;
									case 2:
										Game.closeGame();
										System.exit(1);
										break;
								}
							}
							Game.escapePressedNegateAction = false;
			    		}
			    	}
		    	if(cancelPressed || itemPressed) {
			    		if(!game.getCheatString().equals("uuddlrlr")) {
				    		if(Game.mouseIsClickedDown) {
								if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.playHighlighted ||
										Game.shopHighlighted || Game.exitHighlighted || Game.helpHighlighted ||
										Game.settingsHighlighted || Game.leaderboardHighlighted))
									Game.mouseIsOffClickedObjectAndHeldDown = true;
								Game.playClicked = false;
								Game.shopClicked = false;
								Game.exitClicked = false;
								Game.helpClicked = false;
								Game.settingsClicked = false;
								Game.leaderboardClicked = false;
								Game.escapePressedNegateAction = true;
				    		}
				    		else if(Game.enterButtonPushedDown)
								Game.escapePressedNegateAction = true;
			    		}
			    	}
			    	if((!cancelPressed && cancelReleased) || (!itemPressed && itemReleased)) {
			    		if(System.currentTimeMillis() < game.getCheatTimer() && game.getCheatString().equals("uuddlrlr")) {
			    			game.setCheatString("uuddlrlrb");
			    			game.setCheatTimer(System.currentTimeMillis() + 1500);
			    		}
//			    		else if(System.currentTimeMillis() < game.getCheatTimer()) {
//			    			game.setCheatString("");
//			    			game.setCheatTimer(System.currentTimeMillis() + 1500);
//			    		}
			    		else {
			    			game.setCheatString("");
			    			game.setCheatTimer(System.currentTimeMillis() + 1500);
				    		if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
								Game.selectorBPMP = Game.selectorButtonPosition;
								Game.escapePressedNegateAction = false;
								if(Game.gameControllerInUseDI) {
									Game.gameControllerInUse = false;
									Game.gameControllerInUseDI = false;
									Game.keysAreInUse = false;
								}
								else {
									Game.gameControllerInUse = true;
									Game.gameControllerInUseDI = true;
						    		controllerWasConnected = true;
									Game.keysAreInUse = true;
								}
				    		}
			    		}
			    	}
		    }
		 else if(Game.State == STATE.GAME && !(Game.getUserHasPaused()) && !(System.currentTimeMillis() < Game.getPauseSoundFXTimer()) && Game.gameControllerInUse) {
			 if(lX > .65 && lXDelta > -1) {
		    		if(game.getPauseHoldOff()) {
		    			if(game.getSlowingDownTimerLong() < System.currentTimeMillis())
		    				game.setPauseHoldOff(false);
		    		}
		    		else {
			    		/*
			    		p.setRunningStartR(true);
						p.setRunningStartL(false);
						if(p.getVelX() == 0 || (slowingDownActivatedl || slowingDownActivatedr))
							p.setRunningStartUp(0);
						*/
			    		if(game.getPlayer().getVelX() < 1.2 || game.getxLBoolean()) {
			    			game.getPlayer().setVelX(1.2);
			    			game.getPlayer().animr.setSpeed(10);
			    		}
			    		else if(game.getPlayer().getVelX() >= 3 && game.getPlayer().getVelX() <= 5) {
			    			game.getPlayer().setVelX(game.getPlayer().getVelX() + lX/4);//lX/3
			    			game.getPlayer().animr.setSpeed(8);
			    		}
			    		else if(!game.getxLBoolean())
			    			game.getPlayer().setVelX(game.getPlayer().getVelX() + lX/7);//lX/6
			    		if(game.getPlayer().getVelX() >= 5) {
			    			game.getPlayer().setVelX(5);
			    			game.getPlayer().animr.setSpeed(6);
			    			slowDownForAnalogTimer = System.currentTimeMillis() + 200;
			    		}
			    		/*
			    		if(lX*7 > 5) 
			    			p.setVelX(5);
			    		if(p.getVelX() < 5)
			    			p.setVelX(lX*7);
			    		*/
						game.setxRBoolean(true);
						game.setxLBoolean(false);
						game.setSlowingDownTimerLong(0);
						game.setSlowingDownActivatedl(false);
						game.setSlowingDownActivatedr(false);
						if(game.getRunningTimerActivatedResponse() == false)
							game.setRunningTimerActivated(true);
		    		}
		    	}
			 else if(lX < -0.65 && lXDelta < 1) {
		    		if(game.getPauseHoldOff()) {
		    			if(game.getSlowingDownTimerLong() < System.currentTimeMillis())
		    				game.setPauseHoldOff(false);
		    		}
		    		else {
			    		/*
			    		p.setRunningStartR(false);
						p.setRunningStartL(true);
						if(p.getVelX() == 0 || (slowingDownActivatedl || slowingDownActivatedr))
							p.setRunningStartUp(0);
							*/
			    		if(game.getPlayer().getVelX() > -1.2  || game.getxRBoolean()) {
			    			game.getPlayer().setVelX(-1.2);
			    			game.getPlayer().animl.setSpeed(10);
			    		}
			    		else if(game.getPlayer().getVelX() <= -3 && game.getPlayer().getVelX() >= -5) {
			    			game.getPlayer().setVelX(game.getPlayer().getVelX()+ lX/4);//lX/3
			    			game.getPlayer().animl.setSpeed(8);
			    		}
			    		else if(!game.getxRBoolean())
			    			game.getPlayer().setVelX(game.getPlayer().getVelX() +lX/7);//lX/6
			    		if(game.getPlayer().getVelX() <= -5) {
			    			game.getPlayer().setVelX(-5);
			    			game.getPlayer().animl.setSpeed(6);
			    			slowDownForAnalogTimer = System.currentTimeMillis() + 200;
			    		}
			    		/*
			    		if(lX*7 < -5)
			    			p.setVelX(-5);
			    		if(p.getVelX() > -5)
			    			p.setVelX(lX*7);
			    			*/
						game.setxLBoolean(true);
						game.setxRBoolean(false);
						game.setSlowingDownTimerLong(0);
						game.setSlowingDownActivatedl(false);
						game.setSlowingDownActivatedr(false);
						if(game.getRunningTimerActivatedResponse() == false)
							game.setRunningTimerActivated(true);
		    		}
		    	}
			 else if(lX < .65 && lX > 0.145 && lXDelta > -.14) {
				 	if(game.getPauseHoldOff()) {
		    			if(game.getSlowingDownTimerLong() < System.currentTimeMillis())
		    				game.setPauseHoldOff(false);
		    		}
		    		else {
//		    			if(lX - lXDelta > 0.58 || lX + lXDelta > 0.58)
//		    				analogSlowDownTimer = System.currentTimeMillis() + 200;
			    		game.getPlayer().setVelX(lX*5);
			    		game.getPlayer().animr.setSpeed(10);
			    		if(controllerSensitivityTimer == 0) 
			    			controllerSensitivityTimer = System.currentTimeMillis() + 150;
			    		if(controllerSensitivityTimer < System.currentTimeMillis()) {
			    			game.setRunningTimerLong(System.currentTimeMillis());
			    			game.setRunningTimerActivatedResponse(false);
			    		}
			    		game.setxRBoolean(true);
			    		game.setxLBoolean(false);
			    		//xRBoolean = false;
			    		//xLBoolean = false;
		    		}
		    	}
		    	else if(lX > -0.65 && lX < -0.145 && lXDelta < 0.14) {
		    		if(game.getPauseHoldOff()) {
		    			if(game.getSlowingDownTimerLong() < System.currentTimeMillis())
		    				game.setPauseHoldOff(false);
		    		}
		    		else {
//		    			if(lX - lXDelta < -0.58 || lX + lXDelta < -0.58)
//		    				analogSlowDownTimer = System.currentTimeMillis() + 200;
			    		game.getPlayer().setVelX(lX*5);
			    		game.getPlayer().animl.setSpeed(10);
			    		if(controllerSensitivityTimer == 0) 
			    			controllerSensitivityTimer = System.currentTimeMillis() + 150;
			    		if(controllerSensitivityTimer < System.currentTimeMillis()) {
			    			game.setRunningTimerLong(System.currentTimeMillis());
			    			game.setRunningTimerActivatedResponse(false);
			    		}
			    		game.setxRBoolean(false);
			    		game.setxLBoolean(true);
			    		//xLBoolean = false;
			    		//xRBoolean = false;
		    		}
		    	}
		    	else if(leftHeld && !game.getPauseHoldOff()) {
		    		game.getPlayer().setRunningStartR(false);
		    		game.getPlayer().setRunningStartL(true);
					if(game.getPlayer().getVelX() == 0 || (game.getSlowingDownActivatedl() || game.getSlowingDownActivatedr()))
						game.getPlayer().setRunningStartUp(0);
					game.setxLBoolean(true);
					if(game.getxRBoolean()) {
//						game.setRunningTimerLong(System.currentTimeMillis());	//No slide after change
//						slowDownForAnalogTimer = 0;								//No slide after change
						game.setxRBoolean(false);
					}
					game.setSlowingDownTimerLong(0);
					game.setSlowingDownActivatedl(false);
					game.setSlowingDownActivatedr(false);
					if(game.getRunningTimerActivatedResponse() == false)
						game.setRunningTimerActivated(true);
					if(game.getAnimationTimer1() != 0) {
						game.getPlayer().setX(game.getPlayer().getX()-game.getPlayer().getVelX());
					}
					/*RESTARTS RUNNING SPEED
					if(animationTimer1 != 0) {
						p.setVelX(0);
						p.setRunningStartR(false);
						p.setRunningStartL(false);
					}*/
		    	}
		    	else if(rightHeld && !game.getPauseHoldOff()) {//device.getComponents().getButtons().right) {
		    		game.getPlayer().setRunningStartR(true);
		    		game.getPlayer().setRunningStartL(false);
					if(game.getPlayer().getVelX() == 0 || (game.getSlowingDownActivatedl() || game.getSlowingDownActivatedr()))
						game.getPlayer().setRunningStartUp(0);
					game.setxRBoolean(true);
					if(game.getxLBoolean()) {
//						game.setRunningTimerLong(System.currentTimeMillis());	//No slide after change
//						slowDownForAnalogTimer = 0;								//No slide after change
						game.setxLBoolean(false);
					}
					game.setSlowingDownTimerLong(0);
					game.setSlowingDownActivatedl(false);
					game.setSlowingDownActivatedr(false);
					if(game.getRunningTimerActivatedResponse() == false)
						game.setRunningTimerActivated(true);
					if(game.getAnimationTimer1() != 0) {
						game.getPlayer().setX(game.getPlayer().getX()-game.getPlayer().getVelX());
					}
					/*
					if(animationTimer1 != 0) {
						p.setVelX(0);
						p.setRunningStartR(false);
						p.setRunningStartL(false);
					}*/
		    	}
		    	else if(leftReleased) {
					game.setxLBoolean(false);
					game.getPlayer().setRunningStartL(false);
					if(game.getxRBoolean() == true){
						game.getPlayer().setRunningStartUp(1.2);
						game.getPlayer().setVelX(game.getPlayer().getRunningStartUp());
						game.setRunningTimerActivated(true);
						game.getPlayer().setRunningStartR(true);
					}
					else{
						if((System.currentTimeMillis() - game.getRunningTimerLong() > 666/2 || (game.getPlayer().getVelX() <= -5)) && game.getPlayer().getVelX() != 0){													//This activates sliding animation for left side
							game.setSlowingDownActivatedl(true);
							game.setSlowingDownTimerLong(System.currentTimeMillis() + 200);
							game.setSlowingDown(-1.73);
							game.getPlayer().setVelX(game.getSlowingDown());
							game.setRunningTimerLong(0);
							game.setRunningTimerActivatedResponse(false);
						}
						else
							game.getPlayer().setVelX(0);
					}
					game.setRunningTimerActivatedResponse(false);
					if(game.getPauseHoldOff())
						game.setPauseHoldOff(false);
		    	}
		    	else if(rightReleased) {
					game.setxRBoolean(false);
					game.getPlayer().setRunningStartR(false);
					if(game.getxLBoolean() == true){
						game.getPlayer().setRunningStartUp(-1.2);
						game.getPlayer().setVelX(game.getPlayer().getRunningStartUp());
						game.setRunningTimerActivated(true);
						game.getPlayer().setRunningStartL(true);
					}
					else{
						if((System.currentTimeMillis() - game.getRunningTimerLong() > 666/2 || (game.getPlayer().getVelX() >= 5)) && game.getPlayer().getVelX() != 0){														//This activates sliding animation for right side
							game.setSlowingDownActivatedr(true);
							game.setSlowingDownTimerLong(System.currentTimeMillis() + 200);
							game.setSlowingDown(1.73);
							game.getPlayer().setVelX(game.getSlowingDown());
							game.setRunningTimerLong(0);
							game.setRunningTimerActivatedResponse(false);
						}
						else
							game.getPlayer().setVelX(0);
					}
					game.setRunningTimerActivatedResponse(false);
					if(game.getPauseHoldOff())
						game.setPauseHoldOff(false);
		    	}
		    	else {
		    		if(game.getxLBoolean()) {
//						System.out.println("HERE " + game.getRunningTimerLong());
//		    			game.getPlayer().setVelX(0);
		    			game.setxLBoolean(false);
						if(game.getxRBoolean() == true){
							game.getPlayer().setVelX(1.2);
						}
						else{
							if(System.currentTimeMillis() - game.getRunningTimerLong() > 666/2 || (game.getPlayer().getVelX() <= -5) ||
									System.currentTimeMillis() < slowDownForAnalogTimer/*|| System.currentTimeMillis() < analogSlowDownTimer*/){														//This activates sliding animation for left side
								game.setSlowingDownActivatedl(true);
								game.setSlowingDownTimerLong(System.currentTimeMillis() + 200);
								game.setSlowingDown(-1.73);
								game.getPlayer().setVelX(game.getSlowingDown());
								game.setRunningTimerLong(0);
								game.setRunningTimerActivatedResponse(false);
								controllerSensitivityTimer = 0;
							}
							else {
//								game.setSlowingDownActivatedl(true);
//								game.setSlowingDownTimerLong(System.currentTimeMillis() + 200);
//								game.setSlowingDown(-1);
//								game.setRunningTimerActivated(false);
//								game.getPlayer().setVelX(0);
								game.getPlayer().setRunningStartUp(0);
								game.getPlayer().setRunningStartR(false);
								game.getPlayer().setRunningStartL(false);
							}
						}
						game.setRunningTimerActivatedResponse(false);
		    		}
		    		if(game.getxRBoolean()) {
//		    			game.getPlayer().setVelX(0);
						game.setxRBoolean(false);
						if(game.getxLBoolean() == true){
							game.getPlayer().setVelX(-1.2);
						}
						else{
							if(System.currentTimeMillis() - game.getRunningTimerLong() > 666/2 || (game.getPlayer().getVelX() >= 5) ||
									System.currentTimeMillis() < slowDownForAnalogTimer/*|| System.currentTimeMillis() < analogSlowDownTimer*/){														//This activates sliding animation for right side
								game.setSlowingDownActivatedr(true);
								game.setSlowingDownTimerLong(System.currentTimeMillis() + 200);
								game.setSlowingDown(1.73);
								game.getPlayer().setVelX(game.getSlowingDown());
								game.setRunningTimerLong(0);
								game.setRunningTimerActivatedResponse(false);
								controllerSensitivityTimer = 0;
							}
							else {
//								game.setSlowingDownActivatedr(true);
//								game.setSlowingDownTimerLong(System.currentTimeMillis() + 200);
//								game.setSlowingDown(1);
//								game.setRunningTimerActivated(false);
//								game.getPlayer().setVelX(0);
								game.getPlayer().setRunningStartUp(0);
								game.getPlayer().setRunningStartR(false);
								game.getPlayer().setRunningStartL(false);
							}
						}
						game.setRunningTimerActivatedResponse(false);
		    		}
		    		if(game.getPlayer().getVelX() != 0 && game.getPlayer().getVelX() != game.getSlowingDown()) {
						game.getPlayer().setRunningStartUp(0);		//notsure
						game.getPlayer().setRunningStartR(false);	//notsure
						game.getPlayer().setRunningStartL(false);	//notsure
						game.setRunningTimerActivated(false);		//notsure
						game.setRunningTimerLong(0);				//notsure
		    			game.getPlayer().setVelX(0);
						controllerSensitivityTimer = 0;
		    		}
//					if(pauseHoldOff && lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
//				    		!(device.getComponents().getButtons().right) && !(device.getComponents().getButtons().left)&&
//				    		!(device.getComponents().getButtons().up) && !(device.getComponents().getButtons().down))
//						pauseHoldOff = false;
		    		if(game.getPauseHoldOff() && lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
				    		!(rightHeld) && !(leftHeld)&&
				    		!(upHeld) && !(downHeld))
						game.setPauseHoldOff(false);
		    	}
		    	if(controllerSensitivityTimer != 0 && game.getPlayer().getVelX() == 0)
		    		controllerSensitivityTimer = 0;
		    	if (shootPressed && !game.getIsShooting()){											//Fireballs
					game.setIsShooting(true);
					if(game.ea.isEmpty() && !Game.isPaused()){
						switch(Game.currentlySelectedFireball) {
							case 0:
								game.getController().addEntity(new Fireball(game.getPlayer().getX(),game.getPlayer().getY() + 32,game.getTextures(), game));
								break;
							case 1:
								game.getController().addEntity(new GreenShellFireball(game.getPlayer().getX(),game.getPlayer().getY()+32,game.getTextures(),game.getPlayer().getVelX(),game));
								break;
							case 2:
								game.getController().addEntity(new RedShellFireball(game.getPlayer().getX(),game.getPlayer().getY()+32,game.getTextures(),game.getPlayer().getVelX(),game));
								break;
							case 3:
								game.getController().addEntity(new BuzzyBeetleShellFireball(game.getPlayer().getX(),game.getPlayer().getY()+32,game.getTextures(),game.getPlayer().getVelX(),game));
								break;
							case 4:
								game.getController().addEntity(new GloveFireball(game.getPlayer().getX(),game.getPlayer().getY() + 32,game.getTextures(), game));
								break;
							case 5:
								game.getController().addEntity(new ContraFireball(game.getPlayer().getX(),game.getPlayer().getY() + 32,game.getTextures(), game));
								break;
							default:
								break;
						}
						game.setAnimationTimer1(10);
						game.setNumberOfFireBallsShot(game.getNumberOfFireBallsShot()+1);
						if(Game.fireballSFX.clipIsActive()) {
							Game.fireballSFX.stop();
							Game.fireballSFX.setFramePosition(0);
						}
						Game.fireballSFX.play();
					}
				}
				if(pausePressed){
					if(Game.isPaused() == false && Game.getSoundFXisPlaying() == false && !Game.pauseSoundFXSoundLoop.clipIsActive()){
						if(game.getPlayer().getMarioInvincible() == true)
							Game.marioStarSoundLoop.stop();
						else
							Game.gameSoundLoops.get(Game.soundRandomizer).stop();
						Game.pauseSoundFXSoundLoop.setFramePosition(0);
						Game.pauseSoundFXSoundLoop.play();
						Game.setPauseSoundFXTimer(System.currentTimeMillis() + 685);
						Game.setPaused(true);
						Game.setUserHasPaused(true);
						if(System.currentTimeMillis() < game.getBowserSpawnSetup() && game.getBowserSpawnSetupBoolean() == true) {
							game.setBowserSpawnSetup(0);
							game.setBowserSpawnSetupBoolean(false);
						}
					}
					else if(Game.isPaused() == true && Game.getSoundFXisPlaying() == false && !Game.pauseSoundFXSoundLoop.clipIsActive()){
						if(Game.getPauseSoundFXTimer() < System.currentTimeMillis()){
						/*if(p.getMarioInvincible() == true)
							this.marioStarSoundLoop.loop();
						else
							this.gameSoundLoops.get(soundRandomizer).loop();
						paused = false;*/
						Game.pauseSoundFXSoundLoop.play();
						Game.setPauseSoundFXTimer(System.currentTimeMillis() + 685);
						Game.pauseSoundFXSoundLoop.setSoundLoopBoolean(true);
						}
						Game.setUserHasPaused(false);
					}

					if(game.getSlowingDownTimerLong() != 0) {
						game.setSlowingDownTimerLong(game.getSlowingDownTimerLong() - System.currentTimeMillis());
					}
					else if(System.currentTimeMillis() - game.getRunningTimerLong() > 666/2){
						if(game.getxLBoolean() == true)
							game.setKeepRunningAfterPauseL(true);
						else if(game.getxRBoolean() == true)
							game.setKeepRunningAfterPauseR(true);
					}
				}
		    	if(shootReleased){
		    		game.setIsShooting(false);
				} 
		    	else if(!itemPressed && itemReleased && !Game.isPaused()){
					if(game.getHUD().getItemObtained() == true){
						/*
						switch(hud.getItemName()){
							case "chainChompItem":
								useChainChompAnimation
								break;
							default:
								break;
						}DO LATER IN TRANSITION*/
						if(Game.skipAnimations) {
							//Play a sound
							game.setSpawnItem(true);
							//enemyHitPauseTimer = System.currentTimeMillis() + 800;
						}
						else {
							Game.State = STATE.TRANSITION_ITEM;
							Game.keysAreInUse = false;
							game.setSceneAcknowledgement(false);
							Game.askToSkipSequence = false;
						}
						game.setItemName(game.getHUD().getItemName());
						game.getHUD().setItemObtained(false);
					}
				}
		    	else if(pauseReleased) {
					if(!Game.keysAreInUse)
						Game.keysAreInUse = true;
				}
		 }
		 else if(Game.State == STATE.GAME && (Game.getUserHasPaused() || System.currentTimeMillis() < Game.getPauseSoundFXTimer()) && Game.gameControllerInUse) {
		    	if((lY < -0.65 || (upHeld)) && joystickTimer < System.currentTimeMillis()) {
		    		if(!Game.keysAreInUse) {
						Game.keysAreInUse = true;
					}
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.selectorButtonPosition > 0) {
						Game.selectorButtonPosition--;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
					if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	else if((0.65 < lY || (downHeld)) && joystickTimer < System.currentTimeMillis()) {
					if(!Game.keysAreInUse) {
						Game.keysAreInUse = true;
					}
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.selectorButtonPosition < 2) {
						Game.selectorButtonPosition++;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
					if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	else if(((lX > -0.65 && lX < -0.145 && lXDelta < 0.14) || (leftReleased) ||
		    			(lX > .145 && game.getKeepRunningAfterPauseL())) && joystickTimer < System.currentTimeMillis()) {
		    		if(!game.getKeepRunningAfterPauseR()) {
						game.setDontRunAfterPause(true);
						game.setxLBoolean(false);
						game.getPlayer().setRunningStartL(false);
					}
					if(game.getKeepRunningAfterPauseL()){														//This activates sliding animation for left side
						game.setSlowingDownActivatedl(true);
						game.setSlowingDownTimerLong(System.currentTimeMillis() + 200);
						game.setSlowingDown(-1.73);
						game.getPlayer().setVelX(game.getSlowingDown());
						game.setRunningTimerLong(0);
						game.setRunningTimerActivatedResponse(false);
						game.setSlowingDownFromPause(true);
					}
					if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	else if(((lX < .65 && lX > 0.145 && lXDelta > -.14) || (rightReleased) ||
		    			(lX < -.145 && game.getKeepRunningAfterPauseR())) && joystickTimer < System.currentTimeMillis()) {
		    		if(!game.getKeepRunningAfterPauseL()) {
						game.setDontRunAfterPause(true);
						game.setxRBoolean(false);
						game.getPlayer().setRunningStartR(false);
					}
					if(game.getKeepRunningAfterPauseR()){														//This activates sliding animation for left side
						game.setSlowingDownActivatedr(true);
						game.setSlowingDownTimerLong(System.currentTimeMillis() + 200);
						game.setSlowingDown(1.73);
						game.getPlayer().setVelX(game.getSlowingDown());
						game.setRunningTimerLong(0);
						game.setRunningTimerActivatedResponse(false);
						game.setSlowingDownFromPause(true);
					}
					if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	else if(lX < -0.65) {
			    		if(game.getSlowingDownActivatedl()) {
			    			game.setSlowingDownActivatedl(false);
			    			game.setSlowingDownTimerLong(0);
			    			game.setRunningTimerLong(System.currentTimeMillis() + (666/2));
			    			game.setRunningTimerActivatedResponse(true);
			    			game.setSlowingDownFromPause(false);
			    			game.getPlayer().setVelX(-5);
			    			game.getPlayer().setRunningStartUp(-5);
			    			game.getPlayer().setRunningStartL(true);
			    			game.setDontRunAfterPause(false);
			    		}
			    		if(game.getSlowingDownActivatedr()) {
			    			game.setSlowingDownTimerLong(System.currentTimeMillis() + 200);
			    			game.setSlowingDown(1.73);
			    			game.getPlayer().setVelX(game.getSlowingDown());
							game.setRunningTimerLong(0);
							game.setRunningTimerActivatedResponse(false);
							game.setSlowingDownFromPause(true);
			    		}
			    		//keepRunningAfterPauseL = true;
		    		
		    	}else if(lX > 0.65) {
			    		if(game.getSlowingDownActivatedr()) {
			    			game.setSlowingDownActivatedr(false);
			    			game.setSlowingDownTimerLong(0);
			    			game.setRunningTimerLong(System.currentTimeMillis() + (666/2));
			    			game.setRunningTimerActivatedResponse(true);
			    			game.setSlowingDownFromPause(false);
			    			game.getPlayer().setVelX(5);
			    			game.getPlayer().setRunningStartUp(5);
			    			game.getPlayer().setRunningStartR(true);
							game.setDontRunAfterPause(false);
			    		}if(game.getSlowingDownActivatedl()) {
			    			game.setSlowingDownTimerLong(System.currentTimeMillis() + 200);
			    			game.setSlowingDown(-1.73);
			    			game.getPlayer().setVelX(game.getSlowingDown());
							game.setRunningTimerLong(0);
							game.setRunningTimerActivatedResponse(false);
							game.setSlowingDownFromPause(true);
			    		}
			    		//keepRunningAfterPauseR = true;
		    		
		    	}
		    	if((leftHeld || rightHeld ||
		    		lX > .145 || lX < -.145) && !game.getxLBoolean() && !game.getxRBoolean() && !game.getPauseHoldOff())
		    		game.setPauseHoldOff(true);
		    	/*
		    	if((keepRunningAfterPauseL && lX > .145)) {
		    		slowingDownTimerLong = System.currentTimeMillis() + 200;
					slowingDown = -1.73;
					p.setVelX(slowingDown);
					runningTimerLong = 0;
					runningTimerActivatedResponse = false;
					slowingDownFromPause = true;
					slowingDownActivatedl = true;
					pauseHoldOff = true;
		    	}
		    	if((keepRunningAfterPauseR && lX < -.145)) {
		    		slowingDownTimerLong = System.currentTimeMillis() + 200;
					slowingDown = 1.73;
					p.setVelX(slowingDown);
					runningTimerLong = 0;
					runningTimerActivatedResponse = false;
					slowingDownFromPause = true;
					slowingDownActivatedr = true;
		    		pauseHoldOff = true;
		    	}*/
		    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
		    		!(rightHeld) && !(leftHeld)&&
		    		!(upHeld) && !(downHeld)) {
		    		joystickTimer = 0;
			    }
		    	if(shootPressed) {
					if(!Game.keysAreInUse) {
						Game.keysAreInUse = true;
					}else {
						Game.enterButtonPushedDown = true;
					}
		    	}
		    	if(pausePressed ||
		    		cancelPressed) {
		    		if(Game.mouseIsClickedDown && !pausePressed) {
						if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.resumeHighlighted ||
								Game.homeHighlighted || Game.exitHighlighted))
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.resumeClicked = false;
						Game.homeClicked = false;
						Game.exitClicked = false;
						Game.escapePressedNegateAction = true;
					}
		    		else if(Game.enterButtonPushedDown && !pausePressed)
						Game.escapePressedNegateAction = true;
					else if(Game.isPaused() == true && Game.getSoundFXisPlaying() == false && !Game.pauseSoundFXSoundLoop.clipIsActive()){
							Game.escapePressedNegateAction = false;
							if(Game.getPauseSoundFXTimer() < System.currentTimeMillis()){
							/*if(p.getMarioInvincible() == true)
								this.marioStarSoundLoop.loop();
							else
								this.gameSoundLoops.get(soundRandomizer).loop();
							paused = false;*/
							Game.pauseSoundFXSoundLoop.play();
							Game.setPauseSoundFXTimer(System.currentTimeMillis() + 685);
							Game.pauseSoundFXSoundLoop.setSoundLoopBoolean(true);
							}
							Game.setUserHasPaused(false);
						}
		    	}
		    	if(!shootPressed && shootReleased) {
		    		Game.enterButtonPushedDown = false;
					game.setIsShooting(false);
					if(!Game.escapePressedNegateAction) {
						switch(Game.selectorButtonPosition) {
							case 0:
								if(Game.isPaused() == true && Game.getSoundFXisPlaying() == false){
									if(Game.getPauseSoundFXTimer() < System.currentTimeMillis()){
										Game.getPauseSoundFXSoundLoop().play();
										Game.setPauseSoundFXTimer(System.currentTimeMillis() + 685);
										Game.getPauseSoundFXSoundLoop().setSoundLoopBoolean(true);
									}
									Game.setUserHasPaused(false);
								}
								break;
							case 1:
								Game.setDontStartOver(true);
								Game.State = Game.STATE.RESET;
								if(Game.smb3OpenSoundLoop.clipIsActive())
									Game.smb3OpenSoundLoop.stop();
								Game.smb3OpenSoundLoop.play();
								Game.selectorButtonPosition = 0;
								break;
							case 2:
								Game.closeGame();
								System.exit(1);
								break;
						}
					}
					Game.escapePressedNegateAction = false;
		    	}
		    }//START AGAIN HERE!
		 else if((Game.State == STATE.TRANSITION_ENTRANCE || Game.State == STATE.TRANSITION_WIN || Game.State == STATE.CREDITS) && Game.gameControllerInUse) {
		    	if((lY < -0.65 || (upHeld) ||
		    		0.65 < lY || (downHeld) ||
		    		lX > .65 || (leftHeld) ||
		    		lX < -.65 || (rightHeld)) && joystickTimer < System.currentTimeMillis()){
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
		    		!(rightHeld) && !(leftHeld)&&
		    		!(upHeld) && !(downHeld)) {
		    		joystickTimer = 0;
			    }
		    	if(cancelPressed ||
			    		itemPressed) {
			    		if(Game.mouseIsClickedDown) {
							if(!Game.mouseIsOffClickedObjectAndHeldDown && Game.skipHighlighted)
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.skipClicked = false;
							Game.escapePressedNegateAction = true;
						}
						if(!Game.keysAreInUse) {//|| sceneAcknowledgement == true){
							Game.keysAreInUse = true;
							game.setSceneAcknowledgement(true);
						}
						else {
						if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
						}
			    	}
			    	if(pausePressed ||
			    		shootPressed) {
			    		if(!Game.keysAreInUse) {//|| sceneAcknowledgement == true){
							Game.keysAreInUse = true;
							game.setSceneAcknowledgement(true);
							Game.enterButtonPushedDown = true;
						}
						else if(Game.escapePressedNegateAction == false)
							Game.enterButtonPushedDown = true;
			    	}
			    	if((!cancelPressed && cancelReleased) || (!itemPressed && itemReleased)) {
			    		if(Game.mouseIsClickedDown || Game.enterButtonPushedDown) {
						}
			    		else if(!Game.askToSkipSequence) {
			    			Game.askToSkipSequence = true;
						}
						else {
							Game.askToSkipSequence = false;
							Game.keysAreInUse = false;
							Game.gameControllerInUse = false;
							Game.gameControllerInUseDI = false;
							game.setSceneAcknowledgement(false);
						}
			    	}
			    	if((!pausePressed && pauseReleased) || (!shootPressed && shootReleased)){
			    		if(!Game.askToSkipSequence) {
			    			Game.enterButtonPushedDown = false;
			    			Game.askToSkipSequence = true;
						}
			    		else {
			    			if(Game.askToSkipSequence && Game.escapePressedNegateAction == false) {
			    				Game.skipSequence = true;
								if(Game.State != STATE.CREDITS) {
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
							}
							Game.keysAreInUse = true;
							Game.enterButtonPushedDown = false;
							Game.escapePressedNegateAction = false;
							game.setSceneAcknowledgement(false);
			    		}
			    	}
		    }
		 else if(Game.State == STATE.TRANSITION_ITEM && Game.gameControllerInUse) {
		    	if((lY < -0.65 || (upHeld) ||
		    		0.65 < lY || (downHeld) ||
		    		lX > .65 || (leftHeld) ||
		    		lX < -.65 || (rightHeld)) && joystickTimer < System.currentTimeMillis()){
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
		    		!(rightHeld) && !(leftHeld)&&
		    		!(upHeld) && !(downHeld)) {
		    		joystickTimer = 0;
			    }
		    	if(cancelPressed ||
		    		itemPressed
		    		) {
		    		if(Game.mouseIsClickedDown) {
						if(!Game.mouseIsOffClickedObjectAndHeldDown && Game.skipHighlighted)
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.skipClicked = false;
						Game.escapePressedNegateAction = true;
					}
					if(!Game.keysAreInUse) {//|| sceneAcknowledgement == true){
						Game.keysAreInUse = true;
						game.setSceneAcknowledgement(true);
					}
					else {
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					}
		    	}
		    	if(pausePressed ||
		    		shootPressed) {
		    		if(!Game.keysAreInUse) {//|| sceneAcknowledgement == true){
						Game.keysAreInUse = true;
						game.setSceneAcknowledgement(true);
						Game.enterButtonPushedDown = true;
					}
					else if(Game.escapePressedNegateAction == false)
						Game.enterButtonPushedDown = true;
		    	}
		    	if(game.getPlayer().getVelX() != 0)
		    		game.getPlayer().setVelX(0);
				if(game.getxLBoolean() == true)
					game.setxLBoolean(false);
				if(game.getxRBoolean() == true)
					game.setxRBoolean(false);
				if(game.getSlowingDownActivatedl() == true)
					game.setSlowingDownActivatedl(false);
				if(game.getSlowingDownActivatedr() == true)
					game.setSlowingDownActivatedr(false);
				if(game.getSlowingDown() != 0)
					game.setSlowingDown(0);
				if(game.getSlowingDownTimerLong() != 0)
					game.setSlowingDownTimerLong(0);
				if(game.getRunningTimerActivated() == true)
					game.setRunningTimerActivated(false);
				if(game.getRunningTimerActivatedResponse() == true)
					game.setRunningTimerActivatedResponse(false);
				if(game.getRunningTimerLong() != 0)
					game.setRunningTimerLong(0);
				if((!cancelPressed && cancelReleased) || (!itemPressed && itemReleased)) {
					if(Game.mouseIsClickedDown) {
					}
					else if(game.getSceneAcknowledgement()) {
						if(Game.enterButtonPushedDown) {}
						else if(!Game.askToSkipSequence) {
							Game.askToSkipSequence = true;
						}
						else {
							Game.askToSkipSequence = false;
							Game.keysAreInUse = false;
						}
						if(!Game.enterButtonPushedDown)
							game.setSceneAcknowledgement(false);
					}
				}
				if((!pausePressed && pauseReleased) || (!shootPressed && shootReleased)) {
					if(!Game.askToSkipSequence) {
		    			Game.enterButtonPushedDown = false;
						Game.askToSkipSequence = true;
					}
					else {
						if(Game.askToSkipSequence && Game.escapePressedNegateAction == false) {
							Game.skipSequence = true;
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
						Game.keysAreInUse = true;
						Game.enterButtonPushedDown = false;
						Game.escapePressedNegateAction = false;
						game.setSceneAcknowledgement(false);
					}
				}
		    }
		 else if(Game.State == STATE.GAMEOVER && Game.gameControllerInUse) {//-2 = leaderboard
		    	if((lX < -.65 || (leftHeld)) && joystickTimer < System.currentTimeMillis()) {
		    		if(Game.selectorButtonPosition == -2 || Game.selectorButtonPosition == 0) {
		    			Game.selectorButtonPosition = -1;
		    			if(Game.hudSFXPosition == 3)
		    				Game.hudSFXPosition = 0;
		    			else
		    				Game.hudSFXPosition++;
		    			Game.hudSFX.get(Game.hudSFXPosition).play();
		    		}
					if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	if((lX > .65 || (rightHeld)) && joystickTimer < System.currentTimeMillis()) {
		    		if(Game.selectorButtonPosition == -1 || Game.selectorButtonPosition == 0) {
						Game.selectorButtonPosition = -2;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
					if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	if((0.65 < lY || (downHeld)) && joystickTimer < System.currentTimeMillis()){
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.selectorButtonPosition == -1 || Game.selectorButtonPosition == -2) {
						Game.selectorButtonPosition = 0;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
					else if(Game.selectorButtonPosition < 2) {
						Game.selectorButtonPosition++;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
					if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	if((lY < -0.65 || (upHeld)) && joystickTimer < System.currentTimeMillis()){
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.selectorButtonPosition > -1) {
						Game.selectorButtonPosition--;
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
					if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
		    		!(rightHeld) && !(leftHeld)&&
		    		!(upHeld) && !(downHeld)) {
		    		joystickTimer = 0;
			    }
		    	if(pausePressed ||
			    		shootPressed) {
					Game.enterButtonPushedDown = true;
		    	}
		    	if(cancelPressed ||
		    		itemPressed){
		    		if(Game.mouseIsClickedDown) {
						if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.setScoreHighlighted ||
								Game.playHighlighted || Game.exitHighlighted || Game.homeHighlighted|| 
								Game.leaderboardHighlighted))
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.playClicked = false;
						Game.homeClicked = false;
						Game.exitClicked = false;
						Game.setScoreClicked = false;
						Game.leaderboardClicked = false;
						Game.escapePressedNegateAction = true;
					}
		    		else if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
		    	}
		    	if((!pausePressed && pauseReleased) || (!shootPressed && shootReleased)) {
		    		Game.enterButtonPushedDown = false;
					if(!Game.escapePressedNegateAction && Game.keysAreInUse) {
						switch(Game.selectorButtonPosition) {
							case -2:
								Game.State = STATE.LEADERBOARD;
								Game.selectorButtonPosition = -1;
								if(Game.smb3OpenSoundLoop.clipIsActive())
									Game.smb3OpenSoundLoop.stop();
								Game.smb3OpenSoundLoop.play();
								break;
							case -1:
								Game.State = STATE.SET_SCORE;
								Game.selectorButtonPosition = 0;
								Game.keysAreInUse = false;//CHANGE FOR VIRTUAL KEYBOARD
								//Game.gameControllerInUse = false;//CHANGE FOR VIRTUAL KEYBOARD
								if(Game.smb3KickSoundLoop.clipIsActive())
									Game.smb3KickSoundLoop.stop();
								Game.smb3KickSoundLoop.play();
								break;
							case 0:
								Game.State = STATE.RESET;
								Game.smb3CoinSoundLoop.play();
								break;
							case 1:
								Game.setDontStartOver(true);
								Game.State = Game.STATE.RESET;
								if(Game.smb3OpenSoundLoop.clipIsActive())
									Game.smb3OpenSoundLoop.stop();
								Game.smb3OpenSoundLoop.play();
								break;
							case 2:
								Game.closeGame();
								System.exit(1);
								break;
						}
					}
					Game.escapePressedNegateAction = false;
		    	}
		    	if((!cancelPressed && cancelReleased) || (!itemPressed && itemReleased)){
		    		if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
						Game.selectorBPMP = Game.selectorButtonPosition;
						Game.escapePressedNegateAction = false;
						if(Game.gameControllerInUse) {
							Game.gameControllerInUse = false;
							Game.gameControllerInUseDI = false;
							Game.keysAreInUse = false;
						}
						else {
							Game.gameControllerInUse = true;
							Game.gameControllerInUseDI = true;
				    		controllerWasConnected = true;
							Game.keysAreInUse = true;
						}
					}
		    	}
		    }
		    else if(Game.State == STATE.SET_SCORE && Game.gameControllerInUse) {
		    	if(Game.keysAreInUse) {
		    		if((lY < -0.65 || (upHeld)) && joystickTimer < System.currentTimeMillis()) {
		    			if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
						if(!Game.keysAreInUse) {
							Game.keysAreInUse = true;
//							Game.selectorButtonPosition = -1;
						}
						else {
//							if(Game.selectorButtonPosition == 1) {
//								Game.keysAreInUse = false;
//								Game.selectorButtonPosition--;
//							}
							if(Game.selectorButtonPosition > -1 && Game.selectorButtonPosition != 0) {
								Game.selectorButtonPosition--;
							}if(Game.selectorButtonPosition == 0) {
				    			if(joystickTimer == 0)
									joystickTimer = System.currentTimeMillis() + 500;
								else
									joystickTimer = System.currentTimeMillis() + 500;
								Game.keysAreInUse = false;
								return;
							}
						}
						if(joystickTimer == 0)
							joystickTimer = System.currentTimeMillis() + 500;
						else
							joystickTimer = System.currentTimeMillis() + 30;
			    	}
		    		else if((0.65 < lY || (downHeld)) && joystickTimer < System.currentTimeMillis()){
		    			if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
						if(!Game.keysAreInUse) {
							Game.keysAreInUse = true;
//							Game.selectorButtonPosition = 1;
						}
						else {
//							if(Game.selectorButtonPosition == -1) {
//								Game.keysAreInUse = false;
//								Game.selectorButtonPosition++;
//							}
							if(Game.selectorButtonPosition < 2 && Game.selectorButtonPosition != 0)
								Game.selectorButtonPosition++;
							if(Game.selectorButtonPosition == 0) {
				    			if(joystickTimer == 0)
									joystickTimer = System.currentTimeMillis() + 500;
								else
									joystickTimer = System.currentTimeMillis() + 500;
								Game.keysAreInUse = false;
								return;
							}
						}
						if(joystickTimer == 0)
							joystickTimer = System.currentTimeMillis() + 500;
						else
							joystickTimer = System.currentTimeMillis() + 30;
		    		}
		    		if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
	    	    		!(rightHeld) && !(leftHeld)&&
	    	    		!(upHeld) && !(downHeld)) {
	    	    		joystickTimer = 0;
	    		    }
			    	if(pausePressed ||
					    	shootPressed) {
							if(Game.selectorButtonPosition == 2 && !Game.enterButtonPushedDown) {
								if(Game.smb3CheckmarkSoundLoop.clipIsActive())
									Game.smb3CheckmarkSoundLoop.stop();
								if(Game.smb3Checkmark2SoundLoop.clipIsActive())
									Game.smb3Checkmark2SoundLoop.stop();
								Game.smb3CheckmarkSoundLoop.play();
							}
							Game.enterButtonPushedDown = true;
			    	}
			    	if(cancelPressed ||
					    itemPressed) {
			    		if(Game.mouseIsClickedDown) {
							if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.backHighlighted ||
									Game.submitHighlighted))
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.backClicked = false;
							Game.submitClicked = false;
							Game.settingsClicked = false;
							Game.leaderboardClicked = false;
							Game.escapePressedNegateAction = true;
						}
			    		else if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
			    	}
			    	if((!pausePressed && pauseReleased) || (!shootPressed && shootReleased)){
			    		Game.enterButtonPushedDown = false;
						if(!Game.escapePressedNegateAction) {
							switch(Game.selectorButtonPosition) {
								case -1:
									Game.selectorButtonPosition = -1;
									Game.State = Game.STATE.GAMEOVER;
									break;
								case 1:
									Game.postLetter = '~';
									Game.selectorButtonPosition = 0;
									if(Game.sendToServer && HUD.score != 0)
										Game.connectingToServer = true;
									if(Game.gameControllerInUse) {
										Game.keysAreInUse = true;
										Game.selectorButtonPosition = 0;
									}
									if(!LeaderboardController.globalList)
										LeaderboardController.resetTrigger = true;
									break;
								case 2:
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
									break;
								default:
									break;
							}
						}
						Game.escapePressedNegateAction = false;
			    	}
			    	if((!cancelPressed && cancelReleased) || (!itemPressed && itemReleased)){
			    		if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
							Game.selectorBPMP = Game.selectorButtonPosition;
							Game.escapePressedNegateAction = false;
							if(Game.keysAreInUse) {
								Game.keysAreInUse = false;
							}else
								Game.keysAreInUse = true;
							/*
							if(Game.gameControllerInUse) {
								Game.gameControllerInUse = false;
								Game.keysAreInUse = false;
								//Game.selectorButtonPosition = 0;
							}
							else {
								Game.gameControllerInUse = true;
								Game.keysAreInUse = true;
							}*/
						}
			    	}
		    	}
		    	else {
//		    		System.out.println("shootpressed = "+shootPressed+" shootreleased = "+shootReleased);
		    		if(((lY < -0.65 /*&& lY != -1*/ && 0 <= lYDelta) || (upHeld)) && joystickTimer < System.currentTimeMillis()) {
		    			if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
						Game.keysAreInUse = true;
						Game.selectorButtonPosition = -1;
		    			if(joystickTimer == 0)
							joystickTimer = System.currentTimeMillis() + 500;
						else
							joystickTimer = System.currentTimeMillis() + 500;
		    		}
		    		if(((0.65 < lY /*&& lY != 1*/ && lYDelta <= 0) || (downHeld)) && joystickTimer < System.currentTimeMillis()){
		    			if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
						Game.keysAreInUse = true;
						Game.selectorButtonPosition = 1;
		    			if(joystickTimer == 0)
							joystickTimer = System.currentTimeMillis() + 500;
						else
							joystickTimer = System.currentTimeMillis() + 500;
		    		}
		    		if((lX < -.65  || (leftHeld)) && joystickTimer < System.currentTimeMillis()) {
						//TRAVERSE LETTERS
		    			if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
		    			if(Game.gamepadKeyboardLetterPosition == 0)
		    				Game.gamepadKeyboardLetterPosition = 41;
		    			else
		    				Game.gamepadKeyboardLetterPosition--;
		    			Game.gamepadLetterImage = null;
		    			if(joystickTimer == 0)
							joystickTimer = System.currentTimeMillis() + 500;
						else
							joystickTimer = System.currentTimeMillis() + 100;
		    		}
		    		if((lX > .65 || (rightHeld)) && joystickTimer < System.currentTimeMillis()) {
		    			//TRAVERSE LETTERS
		    			if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
		    			if(Game.gamepadKeyboardLetterPosition == 41)
		    				Game.gamepadKeyboardLetterPosition = 0;
		    			else
		    				Game.gamepadKeyboardLetterPosition++;
		    			Game.gamepadLetterImage = null;
		    			if(joystickTimer == 0)
							joystickTimer = System.currentTimeMillis() + 500;
						else
							joystickTimer = System.currentTimeMillis() + 100;
		    		}
		    		if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
	    	    		!(rightHeld) && !(leftHeld)&&
	    	    		!(upHeld) && !(downHeld)) {
	    	    		joystickTimer = 0;
	    		    }
		    		if(shootPressed) {
		    			//about to enter letter
		    			//gamepadKeyboardLetterPosition
		    			//CREATE NEW LETTER WHEN PRESSED, DON'T CREATE ONE UNTIL THIS IS PRESSED
		    			//
		    			//OR MAKE TRANSPARENT LETTER(TWICE AS FAST AS TEXTINDICATOR) AND DON'T ENTER IT UNTIL THIS IS PRESSED
		    		}
		    		if(pausePressed) {
						Game.enterButtonPushedDown = true;
		    		}
		    		if(cancelPressed) {
		    			if(Game.mouseIsClickedDown) {
							if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.backHighlighted ||
									Game.submitHighlighted))
								Game.mouseIsOffClickedObjectAndHeldDown = true;
							Game.backClicked = false;
							Game.submitClicked = false;
							Game.settingsClicked = false;
							Game.leaderboardClicked = false;
							Game.escapePressedNegateAction = true;
						}
		    			else if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
		    		}
//		    		if(device.getDelta().getButtons().isReleased(XInputButton.LEFT_THUMBSTICK)) {
//		    			if(game.getShiftOn())
//		    				game.setShiftOn(false);
//		    			else
//		    				game.setShiftOn(true);
//		    		}
		    		if(!itemPressed && itemReleased) {
//		    		if(itemPressed && buttonTimer < System.currentTimeMillis()) {
		    			Game.postLetter = '+';
//		    			buttonTimer = System.currentTimeMillis()+150;
		    			//about to delete letter
		    		}
		    		if(!shootPressed && shootReleased) {
//		    		if(shootPressed && buttonTimer < System.currentTimeMillis()) {
//		    			buttonTimer = System.currentTimeMillis()+150;
		    			if(Game.postLetter == '=') {
			    			switch(Game.gamepadKeyboardLetterPosition) {
			    			case 0:
			    				Game.postLetter = 'A';
			    				break;
			    			case 1:
			    				Game.postLetter = 'B';
			    				break;
			    			case 2:
			    				Game.postLetter = 'C';
			    				break;
			    			case 3:
			    				Game.postLetter = 'D';
			    				break;
			    			case 4:
			    				Game.postLetter = 'E';
			    				break;
			    			case 5:
			    				Game.postLetter = 'F';
			    				break;
			    			case 6:
			    				Game.postLetter = 'G';
			    				break;
			    			case 7:
			    				Game.postLetter = 'H';
			    				break;
			    			case 8:
			    				Game.postLetter = 'I';
			    				break;
			    			case 9:
			    				Game.postLetter = 'J';
			    				break;
			    			case 10:
			    				Game.postLetter = 'K';
			    				break;
			    			case 11:
			    				Game.postLetter = 'L';
			    				break;
			    			case 12:
			    				Game.postLetter = 'M';
			    				break;
			    			case 13:
			    				Game.postLetter = 'N';
			    				break;
			    			case 14:
			    				Game.postLetter = 'O';
			    				break;
			    			case 15:
			    				Game.postLetter = 'P';
			    				break;
			    			case 16:
			    				Game.postLetter = 'Q';
			    				break;
			    			case 17:
			    				Game.postLetter = 'R';
			    				break;
			    			case 18:
			    				Game.postLetter = 'S';
			    				break;
			    			case 19:
			    				Game.postLetter = 'T';
			    				break;
			    			case 20:
			    				Game.postLetter = 'U';
			    				break;
			    			case 21:
			    				Game.postLetter = 'V';
			    				break;
			    			case 22:
			    				Game.postLetter = 'W';
			    				break;
			    			case 23:
			    				Game.postLetter = 'X';
			    				break;
			    			case 24:
			    				Game.postLetter = 'Y';
			    				break;
			    			case 25:
			    				Game.postLetter = 'Z';
			    				break;
			    			case 26:
			    				Game.postLetter = '1';
			    				break;
			    			case 27:
			    				Game.postLetter = '2';
			    				break;
			    			case 28:
			    				Game.postLetter = '3';
			    				break;
			    			case 29:
			    				Game.postLetter = '4';
			    				break;
			    			case 30:
			    				Game.postLetter = '5';
			    				break;
			    			case 31:
			    				Game.postLetter = '6';
			    				break;
			    			case 32:
			    				Game.postLetter = '7';
			    				break;
			    			case 33:
			    				Game.postLetter = '8';
			    				break;
			    			case 34:
			    				Game.postLetter = '9';
			    				break;
			    			case 35:
			    				Game.postLetter = '0';
			    				break;
			    			case 36:
			    				Game.postLetter = '.';
			    				break;
			    			case 37:
			    				Game.postLetter = '\'';
			    				break;
			    			case 38:
			    				Game.postLetter = '!';
			    				break;
			    			case 39:
			    				Game.postLetter = ':';
			    				break;
			    			case 40:
			    				Game.postLetter = ',';
			    				break;
			    			case 41:
			    				Game.postLetter = ' ';
			    				break;
			    			default:
			    				break;
			    			}
		    			}
		    		}
		    		if(!pausePressed && pauseReleased) {
		    			if(!Game.escapePressedNegateAction) {
		    				Game.postLetter = '~';
							if(Game.sendToServer && HUD.score != 0)
								Game.connectingToServer = true;
							if(!LeaderboardController.globalList)
								LeaderboardController.resetTrigger = true;
							Game.selectorButtonPosition = 0;
		    			}
						Game.enterButtonPushedDown = false;
						Game.escapePressedNegateAction = false;
						//Game.keysAreInUse = true;
						//Game.selectorButtonPosition = 0;
		    		}
		    		if(!cancelPressed && cancelReleased) {
		    			Game.keysAreInUse = true;
						Game.selectorButtonPosition = -1;
		    		}
		    	}
		    }
		    else if(Game.State == STATE.LEADERBOARD && Game.gameControllerInUse) {
		    	if((lY < -0.65 || (upHeld)) && joystickTimer < System.currentTimeMillis()) {
		    		int selector = Game.selectorButtonPosition;
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.areYouSureBoolean) {}
					else {
						if(Game.selectorButtonPosition == 0)
							Game.selectorButtonPosition = -1;
					}
					if(Game.selectorButtonPosition != selector) {
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
		    		if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	else if((0.65 < lY  || (downHeld)) && joystickTimer < System.currentTimeMillis()) {
			    		int selector = Game.selectorButtonPosition;
						if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
						if(Game.areYouSureBoolean) {}
						else {
							if(Game.selectorButtonPosition == -1)
								Game.selectorButtonPosition = 0;
						}
						if(Game.selectorButtonPosition != selector) {
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							Game.hudSFX.get(Game.hudSFXPosition).play();
						}
			    		if(joystickTimer == 0)
							joystickTimer = System.currentTimeMillis() + 500;
						else
							joystickTimer = System.currentTimeMillis() + 30;
			    	}
		    	if((lX < -.65 || (leftHeld)) && joystickTimer < System.currentTimeMillis()) {
		    		int selector = Game.selectorButtonPosition;
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.areYouSureBoolean) {
						if(Game.selectorButtonPosition != 0)
							Game.selectorButtonPosition = 0;
					}
					else {
						if(Game.selectorButtonPosition == 1)
							Game.selectorButtonPosition = 0;
						else if(Game.selectorButtonPosition == 0)
							Game.selectorButtonPosition = -1;
					}
					if(Game.selectorButtonPosition != selector) {
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
		    		if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	else if((lX > .65 || (rightHeld)) && joystickTimer < System.currentTimeMillis()) {
		    		int selector = Game.selectorButtonPosition;
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.areYouSureBoolean) {
						if(Game.selectorButtonPosition != 1)
							Game.selectorButtonPosition = 1;
					}
					else {
						if(Game.selectorButtonPosition == -1 || Game.selectorButtonPosition == 0)
							Game.selectorButtonPosition = 1;
					}
					if(Game.selectorButtonPosition != selector) {
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
		    		if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
			    		!(rightHeld) && !(leftHeld)&&
			    		!(upHeld) && !(downHeld)) {
			    		joystickTimer = 0;
			    }
		    	if(pausePressed ||
			    		shootPressed
			    		) {
					Game.enterButtonPushedDown = true;
		    	}
		    	if(cancelPressed ||
			    		itemPressed
			    		) {
		    		if(Game.mouseIsClickedDown) {
						if(!Game.mouseIsOffClickedObjectAndHeldDown && Game.backHighlighted)
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.backClicked = false;
						Game.escapePressedNegateAction = true;
					}
		    		else if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
		    	}
		    	if((!pausePressed && pauseReleased) || (!shootPressed && shootReleased)) {
		    		Game.enterButtonPushedDown = false;
					if(!Game.escapePressedNegateAction) {
						if(Game.areYouSureBoolean) {
							switch(Game.selectorButtonPosition) {
							case 0:
								Game.selectorButtonPosition = 0;
								Game.areYouSureBoolean = false;
								Game.smb3KickSoundLoop.play();
								break;	
							case 1:
								LeaderboardController.deleteTrigger = true;
								Game.selectorButtonPosition = 0;
								Game.areYouSureBoolean = false;
								if(Game.smb3TailSoundLoop.clipIsActive())
									Game.smb3TailSoundLoop.stop();
								Game.smb3TailSoundLoop.play();
								break;
							}
						}
						else {
							switch(Game.selectorButtonPosition) {
							case -1:
								//Back button
								if(Game.backToGameOver) {
									Game.selectorButtonPosition = -2;
									Game.State = Game.STATE.GAMEOVER;
								}
								else {
									Game.selectorButtonPosition = -3;
									Game.State = Game.STATE.MENU;
								}
								if(Game.smb3KickSoundLoop.clipIsActive())
									Game.smb3KickSoundLoop.stop();
								Game.smb3KickSoundLoop.play();
								break;
							case 0:
								if(LeaderboardController.globalList) {
									//Local button
									Game.selectorButtonPosition = 1;
									LeaderboardController.globalList = false;
									LeaderboardController.listTrigger = true;
									if(Game.smb3ItemSoundLoop.clipIsActive())
										Game.smb3ItemSoundLoop.stop();
									Game.smb3ItemSoundLoop.play();
								}
								else {
									//Reset Leaderboard Button
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
									break;	
								}
								break;
							case 1:
								if(LeaderboardController.globalList) {
									//Upload button
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
								else {
									//Global button
									LeaderboardController.globalList = true;
									LeaderboardController.listTrigger = true;
								}
								break;
							}
						}
					}
					Game.escapePressedNegateAction = false;
				}
		    	if((!cancelPressed && cancelReleased) || (!itemPressed && itemReleased)) {
		    		if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
						Game.selectorBPMP = Game.selectorButtonPosition;
						Game.escapePressedNegateAction = false;
						if(Game.gameControllerInUse) {
							Game.gameControllerInUse = false;
							Game.gameControllerInUseDI = false;
							Game.keysAreInUse = false;
						}
						else {
							Game.gameControllerInUse = true;
							Game.gameControllerInUseDI = true;
				    		controllerWasConnected = true;
							Game.keysAreInUse = true;
						}
						if(Game.areYouSureBoolean && Game.selectorButtonPosition != 0)
							Game.selectorButtonPosition = 0;
		    		}
		    	}
		    }
		    else if(Game.State == STATE.SHOP && Game.gameControllerInUse) {
		    	if((lY < -0.65 || (upHeld)) && joystickTimer < System.currentTimeMillis()) {
					if(Game.allUnlockedScreen) {
						return;
					}
					int selector = Game.selectorButtonPosition;
					if(Game.selectorButtonPosition == -2 && Game.currentSkinLocked == false)
						Game.selectorButtonPosition = 0;
					else if(Game.selectorButtonPosition == -5 && Game.currentTrackLocked == false)
						Game.selectorButtonPosition = 1;
					else if(Game.selectorButtonPosition == -8 && Game.currentFireballLocked == false)
						Game.selectorButtonPosition = 2;
					else if(Game.selectorButtonPosition == -11 && Game.currentItemLocked == false)
						Game.selectorButtonPosition = 3;
					
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					/*if(Game.selectorButtonPosition >-1 && Game.selectorButtonPosition < -5)
						Game.selectorButtonPosition = 1;
					else */
					if(Game.selectorButtonPosition > -1)
						Game.selectorButtonPosition--;
					else if(Game.selectorButtonPosition == -2 || Game.selectorButtonPosition == -3 || Game.selectorButtonPosition == -4)
						Game.selectorButtonPosition = -1;
					else if(Game.selectorButtonPosition == -5) {
						if(Game.currentSkinLocked)
							Game.selectorButtonPosition = -2;
						else
							Game.selectorButtonPosition = 0;
					}
					else if(Game.selectorButtonPosition == -6)
						Game.selectorButtonPosition = -3;
					else if(Game.selectorButtonPosition == -7)
						Game.selectorButtonPosition = -4;
					else if(Game.selectorButtonPosition == -8) {
						if(Game.currentTrackLocked)
							Game.selectorButtonPosition = -5;
						else
							Game.selectorButtonPosition = 1;
					}
					else if(Game.selectorButtonPosition == -9)
						Game.selectorButtonPosition = -6;
					else if(Game.selectorButtonPosition == -10)
						Game.selectorButtonPosition = -7;
					else if(Game.selectorButtonPosition == -11) {
						if(Game.currentFireballLocked)
							Game.selectorButtonPosition = -8;
						else
							Game.selectorButtonPosition = 2;
					}
					else if(Game.selectorButtonPosition == -12)
						Game.selectorButtonPosition = -9;
					if(Game.selectorButtonPosition != selector) {
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
		    		if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	else if((0.65 < lY || (downHeld)) && joystickTimer < System.currentTimeMillis()) {
					if(Game.allUnlockedScreen) {
						return;
					}
		    		int selector = Game.selectorButtonPosition;
					if(Game.selectorButtonPosition == -2 && Game.currentSkinLocked == false)
						Game.selectorButtonPosition = 0;
					else if(Game.selectorButtonPosition == -5 && Game.currentTrackLocked == false)
						Game.selectorButtonPosition = 1;
					else if(Game.selectorButtonPosition == -8 && Game.currentFireballLocked == false)
						Game.selectorButtonPosition = 2;
					else if(Game.selectorButtonPosition == -11 && Game.currentItemLocked == false)
						Game.selectorButtonPosition = 3;
					
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					/*if(Game.selectorButtonPosition >-1 && Game.selectorButtonPosition < -4)
						Game.selectorButtonPosition = 1;
					else */
					if(Game.selectorButtonPosition < 3 && Game.selectorButtonPosition > -1)
						Game.selectorButtonPosition++;
					else if(Game.selectorButtonPosition == -1)
						Game.selectorButtonPosition = 0;
					else if(Game.selectorButtonPosition == -2) {
						if(Game.currentTrackLocked)
							Game.selectorButtonPosition = -5;
						else
							Game.selectorButtonPosition = 1;
					}
					else if(Game.selectorButtonPosition == -3)
						Game.selectorButtonPosition = -6;
					else if(Game.selectorButtonPosition == -4)
						Game.selectorButtonPosition = -7;
					else if(Game.selectorButtonPosition == -5) {
						if(Game.currentFireballLocked)
							Game.selectorButtonPosition = -8;
						else
							Game.selectorButtonPosition = 2;
					}
					else if(Game.selectorButtonPosition == -6)
						Game.selectorButtonPosition = -9;
					else if(Game.selectorButtonPosition == -7)
						Game.selectorButtonPosition = -10;
					else if(Game.selectorButtonPosition == -8) {
						if(Game.currentItemLocked)
							Game.selectorButtonPosition = -11;
						else
							Game.selectorButtonPosition = 3;
					}
					else if(Game.selectorButtonPosition == -9)
						Game.selectorButtonPosition = -12;
					if(Game.selectorButtonPosition != selector) {
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
		    		if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	if((lX < -.65 || (leftHeld)) && joystickTimer < System.currentTimeMillis()) {
					if(Game.allUnlockedScreen) {
						return;
					}
		    		int selector = Game.selectorButtonPosition;
					if(Game.selectorButtonPosition == -2 && Game.currentSkinLocked == false)
						Game.selectorButtonPosition = 0;
					else if(Game.selectorButtonPosition == -5 && Game.currentTrackLocked == false)
						Game.selectorButtonPosition = 1;
					else if(Game.selectorButtonPosition == -8 && Game.currentFireballLocked == false)
						Game.selectorButtonPosition = 2;
					else if(Game.selectorButtonPosition == -11 && Game.currentItemLocked == false)
						Game.selectorButtonPosition = 3;
					
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.selectorButtonPosition == -3) {
						if(Game.currentSkinLocked)
							Game.selectorButtonPosition = -2;
						else
							Game.selectorButtonPosition = 0;
					}
					else if(Game.selectorButtonPosition == -2)
						Game.selectorButtonPosition = 0;
					else if(Game.selectorButtonPosition == -5)
						Game.selectorButtonPosition = 1;
					else if(Game.selectorButtonPosition == -8)
						Game.selectorButtonPosition = 2;
					else if(Game.selectorButtonPosition == -11)
						Game.selectorButtonPosition = 3;
					else if(Game.selectorButtonPosition == 0)
						Game.selectorButtonPosition--;
					else if(Game.selectorButtonPosition == -6) {
						if(Game.currentTrackLocked)
							Game.selectorButtonPosition = -5;
						else
							Game.selectorButtonPosition = 1;
					}
					else if(Game.selectorButtonPosition == -9) {
						if(Game.currentFireballLocked)
							Game.selectorButtonPosition = -8;
						else
							Game.selectorButtonPosition = 2;
					}
					else if(Game.selectorButtonPosition == -12) {
						if(Game.currentItemLocked)
							Game.selectorButtonPosition = -11;
						else
							Game.selectorButtonPosition = 3;
					}
					else if(Game.selectorButtonPosition < -2 && Game.selectorButtonPosition >= -4 ||
							Game.selectorButtonPosition < -5 && Game.selectorButtonPosition >= -7 ||
							Game.selectorButtonPosition < -8 && Game.selectorButtonPosition >= -10 ||
							Game.selectorButtonPosition < -11 && Game.selectorButtonPosition >= -13 ) {
						Game.selectorButtonPosition++;
					}
					if(Game.selectorButtonPosition != selector) {
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
		    		if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	else if((lX > .65 || (rightHeld)) && joystickTimer < System.currentTimeMillis()) {
					if(Game.allUnlockedScreen) {
						return;
					}
		    		int selector = Game.selectorButtonPosition;
					if(Game.selectorButtonPosition == -2 && Game.currentSkinLocked == false)
						Game.selectorButtonPosition = 0;
					else if(Game.selectorButtonPosition == -5 && Game.currentTrackLocked == false)
						Game.selectorButtonPosition = 1;
					else if(Game.selectorButtonPosition == -8 && Game.currentFireballLocked == false)
						Game.selectorButtonPosition = 2;
					else if(Game.selectorButtonPosition == -11 && Game.currentItemLocked == false)
						Game.selectorButtonPosition = 3;
					
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.selectorButtonPosition == 0) {
						if(Game.currentSkinLocked)
							Game.selectorButtonPosition = -2;
						else
							Game.selectorButtonPosition = -3;
					}
					else if(Game.selectorButtonPosition == -1)
						Game.selectorButtonPosition = 0;
					else if(Game.selectorButtonPosition == 1) {
						if(Game.currentTrackLocked)
							Game.selectorButtonPosition = -5;
						else
							Game.selectorButtonPosition = -6;
					}
					else if(Game.selectorButtonPosition == 2) {
						if(Game.currentFireballLocked)
							Game.selectorButtonPosition = -8;
						else
							Game.selectorButtonPosition = -9;
					}
					else if(Game.selectorButtonPosition == 3) {
						if(Game.currentItemLocked)
							Game.selectorButtonPosition = -11;
						else
							Game.selectorButtonPosition = -12;
					}
					else if(Game.selectorButtonPosition <= -2 && Game.selectorButtonPosition > -4 ||
							Game.selectorButtonPosition <=-5 && Game.selectorButtonPosition > -7 ||
							Game.selectorButtonPosition <=-8 && Game.selectorButtonPosition > -10 ||
							Game.selectorButtonPosition <=-11 && Game.selectorButtonPosition > -12) {
						Game.selectorButtonPosition--;
					}
					if(Game.selectorButtonPosition != selector) {
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
		    		if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
		    		!(rightHeld) && !(leftHeld)&&
		    		!(upHeld) && !(downHeld)) {
		    		joystickTimer = 0;
			    }

		    	if(pausePressed ||
		    		shootPressed
		    		) {
					if(!Game.allUnlockedScreen) 
						Game.enterButtonPushedDown = true;
		    	}
		    	if(cancelPressed ||
		    		itemPressed
		    		) {
		    		if(Game.mouseIsClickedDown) {
						if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.backHighlighted ||
								Game.arrowL1Highlighted || Game.arrowR1Highlighted ||
								Game.arrowL2Highlighted || Game.arrowR2Highlighted ||
								Game.arrowL3Highlighted || Game.arrowR3Highlighted ||
								Game.arrowL4Highlighted || Game.arrowR4Highlighted ||
								Game.set1Highlighted || Game.buy1Highlighted ||
								Game.set2Highlighted || Game.buy2Highlighted ||
								Game.set3Highlighted || Game.buy3Highlighted ||
								Game.set4Highlighted || Game.buy4Highlighted))
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.backClicked = false;
						Game.arrowL1Clicked = false;
						Game.arrowR1Clicked = false;
						Game.arrowL2Clicked = false;
						Game.arrowR2Clicked = false;
						Game.arrowL3Clicked = false;
						Game.arrowR3Clicked = false;
						Game.arrowL4Clicked = false;
						Game.arrowR4Clicked = false;
						Game.set1Clicked = false;
						Game.buy1Clicked = false;
						Game.set2Clicked = false;
						Game.buy2Clicked = false;
						Game.set3Clicked = false;
						Game.buy3Clicked = false;
						Game.set4Clicked = false;
						Game.buy4Clicked = false;
						Game.escapePressedNegateAction = true;
					}
		    		else if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
		    	}
		    	if((!pausePressed && pauseReleased) || (!shootPressed && shootReleased)) {
					if(Game.allUnlockedScreen) {
						Game.allUnlockedScreen = false;
						reset();
						return;
					}
		    		Game.enterButtonPushedDown = false;
					if(Game.selectorButtonPosition == -2 && Game.currentSkinLocked == false)
						Game.selectorButtonPosition = 0;
					else if(Game.selectorButtonPosition == -5 && Game.currentTrackLocked == false)
						Game.selectorButtonPosition = 1;
					else if(Game.selectorButtonPosition == -8 && Game.currentFireballLocked == false)
						Game.selectorButtonPosition = 2;
					else if(Game.selectorButtonPosition == -11 && Game.currentItemLocked == false)
						Game.selectorButtonPosition = 3;

					if(!Game.escapePressedNegateAction) {
						switch(Game.selectorButtonPosition) {
						case -12:
							if(Game.itemPosition == 6)//Max Items
								Game.itemPosition = 0;
							else
								Game.itemPosition++;
							Game.itemNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.itemPosition+1)), 10, 10);
							if(Game.smb3Bump2SoundLoop.clipIsActive())
								Game.smb3Bump2SoundLoop.stop();
							Game.smb3Bump2SoundLoop.play();
							break;
						case -11:
							//Item Buy
							if(Game.currentItemLocked) {
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
											Game.mx = Game.WIDTH +9;
											Game.my = 136;
											Game.checkIfAllUnlocked();
											if(Game.smb31PupSoundLoop.clipIsActive())
												Game.smb31PupSoundLoop.stop();
											Game.smb31PupSoundLoop.play();
											Game.selectorButtonPosition = -12;
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
											Game.mx = Game.WIDTH +9;
											Game.my = 136;
											Game.checkIfAllUnlocked();
											if(Game.smb31PupSoundLoop.clipIsActive())
												Game.smb31PupSoundLoop.stop();
											Game.smb31PupSoundLoop.play();
											Game.selectorButtonPosition = -12;
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
											Game.mx = Game.WIDTH +9;
											Game.my = 136;
											Game.checkIfAllUnlocked();
											if(Game.smb31PupSoundLoop.clipIsActive())
												Game.smb31PupSoundLoop.stop();
											Game.smb31PupSoundLoop.play();
											Game.selectorButtonPosition = -12;
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
							break;
						case -10:
							//Fireballs Set
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
							break;
						case -9:
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
							break;
						case -8:
							//Fireballs Buy
							if(Game.currentFireballLocked) {
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
											Game.mx = Game.WIDTH +9;
											Game.my = 336;
											Game.checkIfAllUnlocked();
											if(Game.smb31PupSoundLoop.clipIsActive())
												Game.smb31PupSoundLoop.stop();
											Game.smb31PupSoundLoop.play();
											Game.selectorButtonPosition = -9;
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
											Game.mx = Game.WIDTH +9;
											Game.my = 336;
											Game.checkIfAllUnlocked();
											if(Game.smb31PupSoundLoop.clipIsActive())
												Game.smb31PupSoundLoop.stop();
											Game.smb31PupSoundLoop.play();
											Game.selectorButtonPosition = -9;
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
											Game.mx = Game.WIDTH +9;
											Game.my = 336;
											Game.checkIfAllUnlocked();
											if(Game.smb31PupSoundLoop.clipIsActive())
												Game.smb31PupSoundLoop.stop();
											Game.smb31PupSoundLoop.play();
											Game.selectorButtonPosition = -9;
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
								Game.fireballSoundSet();
							}
							//else
								//Play error noise
							break;
						case -7:
							//Tracks Set
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
//											Game.gameSoundLoops.get(Game.soundRandomizer).play();
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
							break;
						case -6:
							if(Game.trackPosition == 7)//Max Tracks
								Game.trackPosition = 0;
							else 
								Game.trackPosition++;
							Game.trackNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.trackPosition+1)), 10, 10);
							if(Game.smb3Bump2SoundLoop.clipIsActive())
								Game.smb3Bump2SoundLoop.stop();
							Game.smb3Bump2SoundLoop.play();
							break;
						case -5:
							//Tracks Buy
							if(Game.currentTrackLocked) {
								switch(Game.trackPosition){
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
											Game.mx = Game.WIDTH +9;
											Game.my = 136;
											Game.checkIfAllUnlocked();
											if(Game.smb31PupSoundLoop.clipIsActive())
												Game.smb31PupSoundLoop.stop();
											Game.smb31PupSoundLoop.play();
											Game.selectorButtonPosition = -6;
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
											Game.mx = Game.WIDTH +9;
											Game.my = 136;
											Game.checkIfAllUnlocked();
											if(Game.smb31PupSoundLoop.clipIsActive())
												Game.smb31PupSoundLoop.stop();
											Game.smb31PupSoundLoop.play();
											Game.selectorButtonPosition = -6;
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
											Game.mx = Game.WIDTH +9;
											Game.my = 136;
											Game.checkIfAllUnlocked();
											if(Game.smb31PupSoundLoop.clipIsActive())
												Game.smb31PupSoundLoop.stop();
											Game.smb31PupSoundLoop.play();
											Game.selectorButtonPosition = -6;
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
							break;
						case -4:
							//Skin Set
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
							break;
						case -3:
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
							break;
						case -2:
							//Skin Buy
							if(Game.currentSkinLocked) {
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
											Game.mx = Game.WIDTH +9;
											Game.my = 136;
											Game.checkIfAllUnlocked();
											if(Game.smb31PupSoundLoop.clipIsActive())
												Game.smb31PupSoundLoop.stop();
											Game.smb31PupSoundLoop.play();
											Game.selectorButtonPosition = -3;
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
											Game.mx = Game.WIDTH +9;
											Game.my = 136;
											Game.checkIfAllUnlocked();
											if(Game.smb31PupSoundLoop.clipIsActive())
												Game.smb31PupSoundLoop.stop();
											Game.smb31PupSoundLoop.play();
											Game.selectorButtonPosition = -3;
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
											Game.mx = Game.WIDTH +9;
											Game.my = 136;
											Game.checkIfAllUnlocked();
											if(Game.smb31PupSoundLoop.clipIsActive())
												Game.smb31PupSoundLoop.stop();
											Game.smb31PupSoundLoop.play();
											Game.selectorButtonPosition = -3;
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
							//else
								//Play error noise
							break;
						case -1:
							Game.selectorButtonPosition = 1;
							Game.State = Game.STATE.MENU;
							if(Game.smb3KickSoundLoop.clipIsActive())
								Game.smb3KickSoundLoop.stop();
							Game.smb3KickSoundLoop.play();
							break;
						case 0:
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
							break;
						case 1:
							if(Game.trackPosition > 0)
								Game.trackPosition--;
							else
								Game.trackPosition = 7;//Set to Max Tracks
							Game.trackNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.trackPosition+1)), 10, 10);
							if(Game.smb3Bump2SoundLoop.clipIsActive())
								Game.smb3Bump2SoundLoop.stop();
							Game.smb3Bump2SoundLoop.play();
							break;
						case 2:
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
							break;
						case 3:
							if(Game.itemPosition > 0)
								Game.itemPosition--;
							else
								Game.itemPosition = 6;//Set to Max Items
							Game.itemNumber = Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.itemPosition+1)), 10, 10);
							if(Game.smb3Bump2SoundLoop.clipIsActive())
								Game.smb3Bump2SoundLoop.stop();
							Game.smb3Bump2SoundLoop.play();
							break;
						}
					}
					Game.escapePressedNegateAction = false;
		    	}
		    	if((!cancelPressed && cancelReleased) || (!itemPressed && itemReleased)) {
					if(Game.allUnlockedScreen) {
						Game.allUnlockedScreen = false;
						return;
					}
		    		if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
						if(Game.selectorButtonPosition == -2 && Game.currentSkinLocked == false)
							Game.selectorButtonPosition = 0;
						else if(Game.selectorButtonPosition == -5 && Game.currentTrackLocked == false)
							Game.selectorButtonPosition = 1;
						else if(Game.selectorButtonPosition == -8 && Game.currentFireballLocked == false)
							Game.selectorButtonPosition = 2;
						else if(Game.selectorButtonPosition == -11 && Game.currentItemLocked == false)
							Game.selectorButtonPosition = 3;
						Game.selectorBPMP = Game.selectorButtonPosition;
						Game.escapePressedNegateAction = false;
						if(Game.gameControllerInUse) {
							Game.gameControllerInUse = false;
							Game.gameControllerInUseDI = false;
							Game.keysAreInUse = false;
						}
						else {
							Game.gameControllerInUse = true;
							Game.gameControllerInUseDI = true;
				    		controllerWasConnected = true;
							Game.keysAreInUse = true;
						}
		    		}
		    	}
		    }

		    else if(Game.State == STATE.SETTINGS && Game.gameControllerInUse) {
		    	if((lY < -0.65 || (upHeld)) && joystickTimer < System.currentTimeMillis()) {
					int selector = Game.selectorButtonPosition;
		    		if(Game.areYouSureBoolean)
						Game.selectorButtonPosition = 0;
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.areYouSureBoolean) {}
					else {
						if(Game.selectorButtonPosition <= 3 && Game.selectorButtonPosition > -1)
							Game.selectorButtonPosition--;
						else if(Game.selectorButtonPosition == -3)
							Game.selectorButtonPosition = -2;
						else if(Game.selectorButtonPosition == -2)
							Game.selectorButtonPosition = -4;
					}
					if(Game.selectorButtonPosition != selector) {
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
					if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	else if((0.65 < lY || (downHeld)) && joystickTimer < System.currentTimeMillis()) {
		    		int selector = Game.selectorButtonPosition;
		    		if(Game.areYouSureBoolean)
						Game.selectorButtonPosition = 0;
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.areYouSureBoolean) {}
					else {
						if(Game.selectorButtonPosition >= -1 && Game.selectorButtonPosition < 3)
							Game.selectorButtonPosition++;
						else if(Game.selectorButtonPosition == -2)
							Game.selectorButtonPosition = -3;
						else if(Game.selectorButtonPosition == -3)
							Game.selectorButtonPosition = 2;
						else if(Game.selectorButtonPosition == -4 || Game.selectorButtonPosition == -5)
							Game.selectorButtonPosition = -2;
					}
					if(Game.selectorButtonPosition != selector) {
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
					if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	if((lX < -.65 || (leftHeld)) && joystickTimer < System.currentTimeMillis()) {
		    		int selector = Game.selectorButtonPosition;
					if(Game.areYouSureBoolean)
						Game.selectorButtonPosition = 0;
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.areYouSureBoolean) {
						if(Game.selectorButtonPosition == 1)
							Game.selectorButtonPosition = 0;
					}
					else {
						if(Game.selectorButtonPosition == -2)
							Game.selectorButtonPosition = 0;
						else if(Game.selectorButtonPosition == -3 || Game.selectorButtonPosition == 2)
							Game.selectorButtonPosition = 1;
						else if(Game.selectorButtonPosition == -4)
							Game.selectorButtonPosition = -1;
						else if(Game.selectorButtonPosition == -5)
							Game.selectorButtonPosition = -4;
					}
					if(Game.selectorButtonPosition != selector) {
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
		    		if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	else if((lX > .65 || (rightHeld)) && joystickTimer < System.currentTimeMillis()) {
		    		int selector = Game.selectorButtonPosition;
					if(Game.areYouSureBoolean)
						Game.selectorButtonPosition = 0;
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.areYouSureBoolean) {
						if(Game.selectorButtonPosition == 0)
							Game.selectorButtonPosition = 1;
					}
					else {
						if(Game.selectorButtonPosition == 0)
							Game.selectorButtonPosition = -2;
						else if(Game.selectorButtonPosition == 1 || Game.selectorButtonPosition == 2)
							Game.selectorButtonPosition = -3;
						else if(Game.selectorButtonPosition == -2 || Game.selectorButtonPosition == -1)
							Game.selectorButtonPosition = -4;
						else if(Game.selectorButtonPosition == -4)
							Game.selectorButtonPosition = -5;
					}
					if(Game.selectorButtonPosition != selector) {
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
		    		if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
		    		!(rightHeld) && !(leftHeld)&&
		    		!(upHeld) && !(downHeld)) {
		    		joystickTimer = 0;
		    	}
		    	if(pausePressed ||
				    	shootPressed){
					if(Game.selectorButtonPosition == 2 && !Game.enterButtonPushedDown) {
						if(Game.smb3CheckmarkSoundLoop.clipIsActive())
							Game.smb3CheckmarkSoundLoop.stop();
						if(Game.smb3Checkmark2SoundLoop.clipIsActive())
							Game.smb3Checkmark2SoundLoop.stop();
						Game.smb3CheckmarkSoundLoop.play();
					}
					Game.enterButtonPushedDown = true;
			    }
		    	if(cancelPressed ||
		    		itemPressed
		    		) {
		    		if(Game.mouseIsClickedDown) {
						if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.yesHighlighted || Game.noHighlighted ||
								Game.arrowL1Highlighted || Game.arrowR1Highlighted ||
								Game.arrowL2Highlighted || Game.arrowR2Highlighted ||
								Game.resetStatsHighlighted || Game.checkMarkHighlighted ||
								Game.gamepadImageHighlighted || Game.noteImageHighlighted ||
								Game.backHighlighted))
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.yesClicked = false;
						Game.noClicked = false;
						Game.arrowL1Clicked = false;
						Game.arrowR1Clicked = false;
						Game.arrowL2Clicked = false;
						Game.arrowR2Clicked = false;
						Game.resetStatsClicked = false;
						Game.checkMarkClicked = false;
						Game.gamepadImageClicked = false;
						Game.noteImageClicked = false;
						Game.backClicked = false;
						Game.escapePressedNegateAction = true;
					}
		    		else if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
		    	}
		    	if((!pausePressed && pauseReleased) || (!shootPressed && shootReleased)){
		    		Game.enterButtonPushedDown = false;
					if(!Game.escapePressedNegateAction) {
						if(Game.areYouSureBoolean) {
							switch(Game.selectorButtonPosition) {
							case 0:
								Game.selectorButtonPosition = 3;
								Game.areYouSureBoolean = false;
								Game.smb3KickSoundLoop.play();
								break;	
							case 1:
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
								Game.skinNumber = null;
//								XInputDevice.a = XInputConstants.XINPUT_GAMEPAD_A;
//								XInputDevice.b = XInputConstants.XINPUT_GAMEPAD_B;
//								XInputDevice.x = XInputConstants.XINPUT_GAMEPAD_X;
//								XInputDevice.y = XInputConstants.XINPUT_GAMEPAD_Y;
//								XInputDevice.back = XInputConstants.XINPUT_GAMEPAD_BACK;
//								XInputDevice.start = XInputConstants.XINPUT_GAMEPAD_START;
//								XInputDevice.lShoulder = XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER;
//								XInputDevice.rShoulder = XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER;
//								XInputDevice.lThumb = XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB;
//								XInputDevice.rThumb = XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB;
//								XInputDevice.guide = XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON;
//								XInputDevice.unknown = XInputConstants.XINPUT_GAMEPAD_UNKNOWN;
//								XInputDevice.up = XInputConstants.XINPUT_GAMEPAD_DPAD_UP;
//								XInputDevice.down = XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN;
//								XInputDevice.left = XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT;
//								XInputDevice.right = XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT;
								Game.totalPoints = 0;
								Game.highScore = 0;
								Game.skipAnimations = false;
								try {
									LeaderboardController.resetScore();
								} catch (IOException e1) {
									
								}
								VolumeSlider.volumeSliderChangingVolume = true;
								VolumeSlider.sfxMusicSliderChangingVolume = true;
								Game.settingsSetup = false;
								Game.selectorButtonPosition = 3;
								Game.areYouSureBoolean = false;
								Game.smb3TailSoundLoop.play();
								break;	
							default:
								break;
							}
						}
						else {
							switch(Game.selectorButtonPosition) {
							case -5:
								Game.selectorButtonPosition = -1;
								if(Game.trackCurrentlyPlaying == false)
									TrackController.reset();
								Game.State = Game.STATE.TRACKLIST;
								if(Game.smb3OpenSoundLoop.clipIsActive())
									Game.smb3OpenSoundLoop.stop();
								Game.smb3OpenSoundLoop.play();
								break;
							case -4:
								Game.selectorButtonPosition = -1;
								Game.State = Game.STATE.CONTROLS;
								if(Game.smb3OpenSoundLoop.clipIsActive())
									Game.smb3OpenSoundLoop.stop();
								Game.smb3OpenSoundLoop.play();
								break;
							case -3:
								if(Game.sfxMusicSliderPosition < 5) {
									Game.sfxMusicSliderPosition++;
									VolumeSlider.sfxMusicSliderChangingVolume = true;
								}
								if(Game.smb3BumpSoundLoop.clipIsActive())
									Game.smb3BumpSoundLoop.stop();
								Game.smb3BumpSoundLoop.play();
								break;
							case -2:
								if(Game.volumeSliderPosition < 5) {
									Game.volumeSliderPosition++;
									VolumeSlider.volumeSliderChangingVolume = true;
								}
								if(Game.smb3BumpSoundLoop.clipIsActive())
									Game.smb3BumpSoundLoop.stop();
								Game.smb3BumpSoundLoop.play();
								break;
							case -1:
								Game.selectorButtonPosition = -2;
								Game.State = Game.STATE.MENU;
								if(Game.smb3KickSoundLoop.clipIsActive())
									Game.smb3KickSoundLoop.stop();
								Game.smb3KickSoundLoop.play();
								break;	
							case 0:
								if(Game.volumeSliderPosition > 1) {
									Game.volumeSliderPosition--;
									VolumeSlider.volumeSliderChangingVolume = true;
								}
								if(Game.smb3BumpSoundLoop.clipIsActive())
									Game.smb3BumpSoundLoop.stop();
								Game.smb3BumpSoundLoop.play();
								break;
							case 1:
								if(Game.sfxMusicSliderPosition > 1) {
									Game.sfxMusicSliderPosition--;
									VolumeSlider.sfxMusicSliderChangingVolume = true;
								}
								if(Game.smb3BumpSoundLoop.clipIsActive())
									Game.smb3BumpSoundLoop.stop();
								Game.smb3BumpSoundLoop.play();
								break;
							case 2:
								if(Game.skipAnimations) {
									Game.skipAnimations = false;
									Game.settingsSetup = false;
								}
								else {
									Game.skipAnimations = true;
									Game.settingsSetup = false;
								}
								if(Game.smb3Checkmark2SoundLoop.clipIsActive())
									Game.smb3Checkmark2SoundLoop.stop();
								if(Game.smb3CheckmarkSoundLoop.clipIsActive())
									Game.smb3CheckmarkSoundLoop.stop();
								Game.smb3Checkmark2SoundLoop.play();
								break;	
							case 3:
								Game.selectorButtonPosition = 0;
								Game.areYouSureBoolean = true;
								Game.hudSFX.get(4).play();
								break;	
							default:
								break;
							}
						}
					}
					Game.escapePressedNegateAction = false;
		    	}
		    	if((!cancelPressed && cancelReleased) || (!itemPressed && itemReleased)){
		    		if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
						Game.selectorBPMP = Game.selectorButtonPosition;
						Game.escapePressedNegateAction = false;
						if(Game.gameControllerInUse) {
							Game.gameControllerInUse = false;
							Game.gameControllerInUseDI = false;
							Game.keysAreInUse = false;
						}
						else {
							Game.gameControllerInUse = true;
							Game.gameControllerInUseDI = true;
				    		controllerWasConnected = true;
							Game.keysAreInUse = true;
						}
						if(Game.areYouSureBoolean && Game.selectorButtonPosition != 0)
							Game.selectorButtonPosition = 0;
					}
		    	}
		    }
		    else if(Game.State == STATE.CONTROLS) {
		    	if((lY < -0.65 || (upHeld)) && joystickTimer < System.currentTimeMillis()) {
		    		if(System.currentTimeMillis() < ControlsController.buttonChangeTimer) {
					}
					else {
			    		if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
			    		else {
							if(Game.selectorButtonPosition == -2 || Game.selectorButtonPosition == -10) {
								Game.selectorButtonPosition = -1;
								if(Game.hudSFXPosition == 3)
									Game.hudSFXPosition = 0;
								else
									Game.hudSFXPosition++;
								Game.hudSFX.get(Game.hudSFXPosition).play();
							}
							else if(Game.selectorButtonPosition == -18) {
								Game.selectorButtonPosition = 0;
								if(Game.hudSFXPosition == 3)
									Game.hudSFXPosition = 0;
								else
									Game.hudSFXPosition++;
								Game.hudSFX.get(Game.hudSFXPosition).play();
							}
							else if(Game.selectorButtonPosition != -1 && Game.selectorButtonPosition != 0){
								Game.selectorButtonPosition++;
								if(Game.hudSFXPosition == 3)
									Game.hudSFXPosition = 0;
								else
									Game.hudSFXPosition++;
								Game.hudSFX.get(Game.hudSFXPosition).play();
							}
			    		}
			    		if(joystickTimer == 0)
							joystickTimer = System.currentTimeMillis() + 500;
						else
							joystickTimer = System.currentTimeMillis() + 30;
					}
		    	}
		    	else if((0.65 < lY || (downHeld)) && joystickTimer < System.currentTimeMillis()) {
		    		if(System.currentTimeMillis() < ControlsController.buttonChangeTimer) {
					}
					else {
						if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
						else {
							if(Game.selectorButtonPosition == -9 || Game.selectorButtonPosition == -17 ||
									Game.selectorButtonPosition == -25)
							{}
							else if(Game.selectorButtonPosition == 0){
								Game.selectorButtonPosition = -18;
								if(Game.hudSFXPosition == 3)
									Game.hudSFXPosition = 0;
								else
									Game.hudSFXPosition++;
								Game.hudSFX.get(Game.hudSFXPosition).play();
							}
							else if(Game.selectorButtonPosition != -26){
								Game.selectorButtonPosition--;
								if(Game.hudSFXPosition == 3)
									Game.hudSFXPosition = 0;
								else
									Game.hudSFXPosition++;
								Game.hudSFX.get(Game.hudSFXPosition).play();
							}
						}
						if(joystickTimer == 0)
							joystickTimer = System.currentTimeMillis() + 500;
						else
							joystickTimer = System.currentTimeMillis() + 30;
					}
		    	}
		    	if((lX < -.65 || (leftHeld)) && joystickTimer < System.currentTimeMillis()) {
		    		if(System.currentTimeMillis() < ControlsController.buttonChangeTimer) {
					}
					else {
						if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
						else {
							if(Game.selectorButtonPosition == -2 || Game.selectorButtonPosition == 0) {
								Game.selectorButtonPosition = -1;
								if(Game.hudSFXPosition == 3)
									Game.hudSFXPosition = 0;
								else
									Game.hudSFXPosition++;
								Game.hudSFX.get(Game.hudSFXPosition).play();
							}
							else if(Game.selectorButtonPosition == -26){
								Game.selectorButtonPosition = -25;
								if(Game.hudSFXPosition == 3)
									Game.hudSFXPosition = 0;
								else
									Game.hudSFXPosition++;
								Game.hudSFX.get(Game.hudSFXPosition).play();
							}
							else if (Game.selectorButtonPosition < -9){
								Game.selectorButtonPosition += 8;
								if(Game.hudSFXPosition == 3)
									Game.hudSFXPosition = 0;
								else
									Game.hudSFXPosition++;
								Game.hudSFX.get(Game.hudSFXPosition).play();
							}
						}
						if(joystickTimer == 0)
							joystickTimer = System.currentTimeMillis() + 500;
						else
							joystickTimer = System.currentTimeMillis() + 30;
					}
		    	}
		    	else if((lX > .65 || rightHeld) && joystickTimer < System.currentTimeMillis()) {
		    		if(System.currentTimeMillis() < ControlsController.buttonChangeTimer) {
					}
					else {
						if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
						else {
							if(Game.selectorButtonPosition == -1) {
								Game.selectorButtonPosition = -2;
								if(Game.hudSFXPosition == 3)
									Game.hudSFXPosition = 0;
								else
									Game.hudSFXPosition++;
								Game.hudSFX.get(Game.hudSFXPosition).play();
							}
							else if(Game.selectorButtonPosition == -25){
								Game.selectorButtonPosition = -26;
								if(Game.hudSFXPosition == 3)
									Game.hudSFXPosition = 0;
								else
									Game.hudSFXPosition++;
								Game.hudSFX.get(Game.hudSFXPosition).play();
							}
							else if (Game.selectorButtonPosition > -18 && Game.selectorButtonPosition != 0){
								Game.selectorButtonPosition -= 8;
								if(Game.hudSFXPosition == 3)
									Game.hudSFXPosition = 0;
								else
									Game.hudSFXPosition++;
								Game.hudSFX.get(Game.hudSFXPosition).play();
							}
						}
						if(joystickTimer == 0)
							joystickTimer = System.currentTimeMillis() + 500;
						else
							joystickTimer = System.currentTimeMillis() + 30;
					}
		    	}
		    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
		    		!(rightHeld) && !(leftHeld)&&
		    		!(upHeld) && !(downHeld)) {
		    		joystickTimer = 0;
		    	}
//	    		if(device.getDelta().getButtons().isPressed(XInputButton.DPAD_UP)) {
//	    			XInputDevice.upHeld = true;
//	    		}
//	    		if(device.getDelta().getButtons().isPressed(XInputButton.DPAD_DOWN)) {
//	    			XInputDevice.downHeld = true;
//	    		}
//	    		if(device.getDelta().getButtons().isPressed(XInputButton.DPAD_LEFT)) {
//	    			XInputDevice.leftHeld = true;
//	    		}
//	    		if(device.getDelta().getButtons().isPressed(XInputButton.DPAD_RIGHT)) {
//	    			XInputDevice.rightHeld = true;
//	    		}
		    	
		    	//PRESSES HERE BEFORE
		    	
		    	if(System.currentTimeMillis() < ControlsController.buttonChangeTimer) {
					if(ControlsController.buttonToChange <= -18 && -25 <= ControlsController.buttonToChange) {
//			    		if(Game.revertControllerSettings == false) {
//			    			SwitchedValues.resetControls();
//				    		Game.revertControllerSettings = true;
//			    		}
//			    		ControlsController.changeDirectInputButton(SwitchedValues.cancelKey);
		    			/* Get the available controllers */
		    			Controller[] controllers = ControllerEnvironment
		    					.getDefaultEnvironment().getControllers();
		    			if (controllers.length == 0) {
		    				System.out.println("Found no controllers.");
		    				return;
//			    				System.exit(0);
		    			}
		    			
		    			for (int i = 0; i < controllers.length; i++) {
//				    		if(!controllerName.equals(controllers[i].getName())) 
//				    			continue;
				    		float oldDPadvalue = 0.0f; // hat neutral state = 0.0
		    				/* Remember to poll each one */
		    				controllers[i].poll();
	
		    				/* Get the controllers event queue */
		    				EventQueue queue = controllers[i].getEventQueue();
		    				event = new Event();
		    				/* For each object in the queue */
		    				while (queue.getNextEvent(event)) {
		    					Component comp = event.getComponent();
		    					float value = event.getValue();
		    					if(comp.getName().equals("Z Rotation") || comp.getName().equals("Z Axis") ||
		    							comp.getName().equals("X Axis") || comp.getName().equals("Y Axis"))
		    						continue;
//		    					if(comp!=null)
//		    						System.out.println("compname = " +comp.getName()+" value = "+value);
		    					/*
		    					 * Check the type of the component and display an
		    					 * appropriate value
		    					 */
		    					if (comp.isAnalog()) { //&& !((comp.getName().equals("X Rotation")||comp.getName().equals("Y Rotation")) && (value < -.145 || value > .145))) {
		    					} else {
	//	    						if (value == 1.0f && !(controllers[i].getName().equals("Wireless Controller") && (comp.getName().equals("Button 7") || comp.getName().equals("Button 6")))) {
		    						if (value == 1.0f && !comp.getName().equals("Hat Switch") && /*!*/(SpecialCases.specialCases(controllers[i].getName(), comp.getName().toString()))) {
	//	    							buffer.append("On");
		    						} else {
		    							if(controllers[i].getType().toString().equals("Gamepad") /*&& 
		    							  (controllers[i].getName().equals(controllerName))*/){//||controllerName.equals("")||
		    									   //Game.keysAreInUse == false)) {
		    								/* Create hatSwitch for D-Pad on DS4 controllers */
		    								String button = comp.getName();
		    								if(comp.getName().equals("Hat Switch")) {
//		    									String[] directions = { "NW", "N", "NE", "E", "SE", "S", "SW", "W" };
		    									Component hatSwitch = controllers[i].getComponent(Component.Identifier.Axis.POV);
		    									float dPadvalue = hatSwitch.getPollData() * 8;
		    									 if (dPadvalue != oldDPadvalue) // only operate on changes in component value
		    									    {
		    										 	oldDPadvalue = dPadvalue; // update old value
		    									        
		    									        // make sure the value is an integer in a valid range
		    									        if (dPadvalue % 1 != 0 && dPadvalue >= 0.0f && dPadvalue <= 8.0f)
		    									        {
		    									            System.err.println("Invalid value: " + dPadvalue);
		    									        }
		    									        else if (dPadvalue != 0.0f) // if value is a valid index
		    									        {
		    									        	if(dPadvalue != 2 && dPadvalue != 4 && dPadvalue != 6 && dPadvalue != 8)
		    									        		dPadvalue = 2;
		    									        	button = button +String.valueOf(((int)dPadvalue-1));
		    									        }
		    									        else {
		    									        	//button = button+"1";
				    										 button = lastDpadPressed;
		    									        }
		    									    }
		    									 else
		    										 button = lastDpadPressed;
		    								}
		    								if(!controllerName.equals(controllers[i].getName())) {
		    									controllerName = controllers[i].getName().toString();
		    									try {
													LeaderboardController.writeToSettings("Controller Name: ", controllerName.toString());
												} catch (IOException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
		    									SwitchedValues.resetControlsfromResetButton();
		    								}
			    							game.getControlsController().changeDirectInputButton(button);
			    							reset();
			    							Game.enterButtonPushedDown = false;
			    							Game.escapePressedNegateAction = false;
			    							Game.settingsSetup = false;
			    							return;
		    							}
		    							//change button here
	//	    							buffer.append("Off");
		    						}
		    					}
		    				}
		    			}
					}
		    		if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	else {
			    	if(pausePressed ||
					    	shootPressed){
							Game.enterButtonPushedDown = true;
				    	}
				    	if(cancelPressed ||
				    		itemPressed
				    		) {
				    		if(Game.mouseIsClickedDown) {
								if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.backHighlighted || Game.resetHighlighted))
									Game.mouseIsOffClickedObjectAndHeldDown = true;
								else if(!Game.mouseIsOffClickedObjectAndHeldDown) {
									for(int i = 0; i <= ControlsController.gamepadButtonHolderHighlighted.length-1; i++) {
										if(ControlsController.gamepadButtonHolderHighlighted[i] == true) {
											Game.mouseIsOffClickedObjectAndHeldDown = true;
											break;
										}
									}
								}
								for(int i = 0; i <= ControlsController.gamepadButtonHolderClicked.length-1; i++) {
									ControlsController.gamepadButtonHolderClicked[i] = false;
								}
								Game.backClicked = false;
								Game.resetClicked = false;
								Game.escapePressedNegateAction = true;
							}
				    		else if(Game.enterButtonPushedDown)
								Game.escapePressedNegateAction = true;
				    	}
//		    		if(device.getDelta().getButtons().isReleased(XInputButton.DPAD_UP)) {
//		    			XInputDevice.upHeld = false;
//		    		}
//		    		if(device.getDelta().getButtons().isReleased(XInputButton.DPAD_DOWN)) {
//		    			XInputDevice.downHeld = false;
//		    		}
//		    		if(device.getDelta().getButtons().isReleased(XInputButton.DPAD_LEFT)) {
//		    			XInputDevice.leftHeld = false;
//		    		}
//		    		if(device.getDelta().getButtons().isReleased(XInputButton.DPAD_RIGHT)) {
//		    			XInputDevice.rightHeld = false;
//		    		}
		    		if((!pausePressed && pauseReleased) || (!shootPressed && shootReleased)){
						Game.enterButtonPushedDown = false;
		    			if(!Game.escapePressedNegateAction) {
							if(Game.selectorButtonPosition == -1) {
								Game.selectorButtonPosition = -4;
								Game.State = Game.STATE.SETTINGS;
								if(Game.smb3KickSoundLoop.clipIsActive())
									Game.smb3KickSoundLoop.stop();
								Game.smb3KickSoundLoop.play();
							}
							else if(Game.selectorButtonPosition == 0) {
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
							else if(Game.selectorButtonPosition == -26) {
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
//				    			SwitchedValues.resetControls();
								Game.settingsSetup = false;
								if(Game.smb3TailSoundLoop.clipIsActive())
									Game.smb3TailSoundLoop.stop();
								Game.smb3TailSoundLoop.play();
							}//-18 to -25
							else if(Game.selectorButtonPosition < -1 && Game.selectorButtonPosition > -26) {
								if(Game.selectorButtonPosition<= -10 && -17 <= Game.selectorButtonPosition) {
									Game.gameControllerInUseDI = false;
									Game.gameControllerSwitchBackDI = true;
								}
								ControlsController.buttonChangeTimer = System.currentTimeMillis() + 3000;
								ControlsController.buttonToChange = Game.selectorButtonPosition;
							}
						}
						Game.escapePressedNegateAction = false;
		    		}
		    		if((!cancelPressed && cancelReleased) || (!itemPressed && itemReleased)) {
		    			if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
		    				Game.selectorBPMP = Game.selectorButtonPosition;
							Game.escapePressedNegateAction = false;
							if(Game.gameControllerInUse) {
								Game.gameControllerInUse = false;
								Game.gameControllerInUseDI = false;
								Game.keysAreInUse = false;
							}
							else {
								Game.gameControllerInUse = true;
								Game.gameControllerInUseDI = true;
					    		controllerWasConnected = true;
								Game.keysAreInUse = true;
							}
						}
		    		}
		    	}
		    }
		    else if(Game.State == STATE.TRACKLIST && Game.gameControllerInUse) {
		    	if((lY < -0.65 || (upHeld)) && joystickTimer < System.currentTimeMillis()) {
		    		int selector = Game.selectorButtonPosition;
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					switch(Game.selectorButtonPosition) {
					case -11:
						Game.selectorButtonPosition = -10;
						break;
					case -9:
						Game.selectorButtonPosition = -8;
						break;
					case -7:
						Game.selectorButtonPosition = -6;
						break;
					case -5:
						Game.selectorButtonPosition = -4;
						break;
					case -3:
						Game.selectorButtonPosition = -2;
						break;
					case -2: case -4: case -6: case -8: case -10:
						Game.selectorButtonPosition = -1;
						break;
					default:
						break;
					}
					if(Game.selectorButtonPosition != selector) {
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
		    		if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	else if((0.65 < lY  || (downHeld)) && joystickTimer < System.currentTimeMillis()) {
			    		int selector = Game.selectorButtonPosition;
						if(Game.enterButtonPushedDown)
							Game.escapePressedNegateAction = true;
						switch(Game.selectorButtonPosition) {
						case -10:
							Game.selectorButtonPosition = -11;
							break;
						case -8:
							Game.selectorButtonPosition = -9;
							break;
						case -6:
							Game.selectorButtonPosition = -7;
							break;
						case -4:
							Game.selectorButtonPosition = -5;
							break;
						case -2:
							Game.selectorButtonPosition = -3;
							break;
						case -1:
							Game.selectorButtonPosition = -2;
							break;
						default:
							break;
						}
						if(Game.selectorButtonPosition != selector) {
							if(Game.hudSFXPosition == 3)
								Game.hudSFXPosition = 0;
							else
								Game.hudSFXPosition++;
							Game.hudSFX.get(Game.hudSFXPosition).play();
						}
			    		if(joystickTimer == 0)
							joystickTimer = System.currentTimeMillis() + 500;
						else
							joystickTimer = System.currentTimeMillis() + 30;
			    	}
		    	if((lX < -.65 || (leftHeld)) && joystickTimer < System.currentTimeMillis()) {
		    		int selector = Game.selectorButtonPosition;
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.selectorButtonPosition < -3)
						Game.selectorButtonPosition+=2;
					else if(Game.selectorButtonPosition != -2 && Game.selectorButtonPosition != -3)
						Game.selectorButtonPosition = -1;
					if(Game.selectorButtonPosition != selector) {
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
		    		if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	else if((lX > .65 || (rightHeld)) && joystickTimer < System.currentTimeMillis()) {
		    		int selector = Game.selectorButtonPosition;
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(-9 <= Game.selectorButtonPosition && Game.selectorButtonPosition != -1)
						Game.selectorButtonPosition-=2;
					else if(Game.selectorButtonPosition != -10 && Game.selectorButtonPosition != -1)
						Game.selectorButtonPosition = -11;
					if(Game.selectorButtonPosition != selector) {
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
		    		if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
		    		!(rightHeld) && !(leftHeld)&&
		    		!(upHeld) && !(downHeld)) {
		    		joystickTimer = 0;
			    }
		    	if(pausePressed ||
				    	shootPressed) {
					Game.enterButtonPushedDown = true;
		    	}
		    	if(cancelPressed ||
		    			itemPressed){
		    		if(Game.mouseIsClickedDown) {
						if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.backHighlighted))
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.backClicked = false;
						Game.escapePressedNegateAction = true;
					}
		    		else if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
		    	}

		    	if((!pausePressed && pauseReleased) ||
		    		(!shootPressed && shootReleased)
			    		) {
		    		Game.enterButtonPushedDown = false;
					if(!Game.escapePressedNegateAction) {
						switch(Game.selectorButtonPosition) {
						case -1:
							Game.selectorButtonPosition = -5;
							Game.State = Game.STATE.SETTINGS;
							if(Game.smb3KickSoundLoop.clipIsActive())
								Game.smb3KickSoundLoop.stop();
							Game.smb3KickSoundLoop.play();
							break;
						case -2:
							if(Game.gameTrackPosition > 0)
								Game.gameTrackPosition--;
							else
								Game.gameTrackPosition = 7;//Set to Max Tracks
							Game.gameTrackNumber = Game.makeTransparent(Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.gameTrackPosition+1)), 20, 20));
							if(Game.smb3Bump2SoundLoop.clipIsActive())
								Game.smb3Bump2SoundLoop.stop();
							Game.smb3Bump2SoundLoop.play();
							break;
						case -3:
							if(Game.menuTrackPosition > 0)
								Game.menuTrackPosition--;
							else
								Game.menuTrackPosition = 4;//Set to Max Tracks
							Game.menuTrackNumber = Game.makeTransparent(Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.menuTrackPosition+1)), 20, 20));
							if(Game.smb3Bump2SoundLoop.clipIsActive())
								Game.smb3Bump2SoundLoop.stop();
							Game.smb3Bump2SoundLoop.play();
							break;
						case -4:
							if(Game.gameTrackPosition == 7)//Max Tracks
								Game.gameTrackPosition = 0;
							else 
								Game.gameTrackPosition++;
							Game.gameTrackNumber = Game.makeTransparent(Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.gameTrackPosition+1)), 20, 20));
							if(Game.smb3Bump2SoundLoop.clipIsActive())
								Game.smb3Bump2SoundLoop.stop();
							Game.smb3Bump2SoundLoop.play();
							break;
						case -5:
							if(Game.menuTrackPosition == 4)//Max Tracks
								Game.menuTrackPosition = 0;
							else 
								Game.menuTrackPosition++;
							Game.menuTrackNumber = Game.makeTransparent(Game.resize(HUD.stringToMario3FontImage(Integer.toString(Game.menuTrackPosition+1)), 20, 20));
							if(Game.smb3Bump2SoundLoop.clipIsActive())
								Game.smb3Bump2SoundLoop.stop();
							Game.smb3Bump2SoundLoop.play();
							break;
						case -6://play1
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
							break;
						case -7://play2
							boolean bb = true;
							switch(Game.menuTrackPosition) {
							case 4:
								if(!Game.menuTrack4Unlocked) {
									if(Game.smwErrorSoundLoop.clipIsActive())
										Game.smwErrorSoundLoop.stop();
									Game.smwErrorSoundLoop.play();
									bb = false;
								}
								break;
							default:
								break;
							}
							if(bb)
								TrackController.readyToSwitch2 = true;
							break;
						case -8://stop1
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
							break;
						case -9://stop2
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
							break;
						case -10://set1
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
								}
							}
							break;
						case -11://set2
							String string1 = "";
							String string2 = "";
							switch(Game.menuTrackPosition) {
							case 0:
								string1 = "menuTrack1Set";
								if(Game.menuTrack1Set) {
									Game.menuTrack1Set = false;
									string2 = "false";
									if(Game.menuSoundLoopRandomizer == 0) 
										Game.resetMenuSound();
									if(Game.smbPopSoundLoop.clipIsActive())
										Game.smbPopSoundLoop.stop();
									Game.smbPopSoundLoop.play();
								}
								else {
									Game.menuTrack1Set = true;
									string2 = "true";
								}
								break;
							case 1:
								string1 = "menuTrack2Set";
								if(Game.menuTrack2Set) {
									Game.menuTrack2Set = false;
									string2 = "false";
									if(Game.menuSoundLoopRandomizer == 1) 
										Game.resetMenuSound();
									if(Game.smbPopSoundLoop.clipIsActive())
										Game.smbPopSoundLoop.stop();
									Game.smbPopSoundLoop.play();
								}
								else {
									Game.menuTrack2Set = true;
									string2 = "true";
								}
								break;
							case 2:
								string1 = "menuTrack3Set";
								if(Game.menuTrack3Set) {
									Game.menuTrack3Set = false;
									string2 = "false";
									if(Game.menuSoundLoopRandomizer == 2) 
										Game.resetMenuSound();
									if(Game.smbPopSoundLoop.clipIsActive())
										Game.smbPopSoundLoop.stop();
									Game.smbPopSoundLoop.play();
								}
								else {
									Game.menuTrack3Set = true;
									string2 = "true";
								}
								break;
							case 3:
								string1 = "menuTrack4Set";
								if(Game.menuTrack4Set) {
									Game.menuTrack4Set = false;
									string2 = "false";
									if(Game.menuSoundLoopRandomizer == 3) 
										Game.resetMenuSound();
									if(Game.smbPopSoundLoop.clipIsActive())
										Game.smbPopSoundLoop.stop();
									Game.smbPopSoundLoop.play();
								}
								else {
									Game.menuTrack4Set = true;
									string2 = "true";
								}
								break;
							case 4:
								string1 = "menuTrack5Set";
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
									string2 = "false";
									if(Game.menuSoundLoopRandomizer == 4) 
										Game.resetMenuSound();
								}
								else {
									Game.menuTrack5Set = true;
									string2 = "true";
								}
								break;
							default:
								break;
							}
							if(string2.equals("true")) {
								if(Game.smb3ItemSoundLoop.clipIsActive())
									Game.smb3ItemSoundLoop.stop();
								Game.smb3ItemSoundLoop.play();
							}
							if(!string1.equals("") && !string2.equals("")) {
								try {
									LeaderboardController.writeToSettings(string1, string2);
								} catch (IOException e1) {
								}

								if(string2.equals("true") && Game.menuSoundLoopRandomizer == 5 && Game.gameTrackCurrentlyPlaying == -1  && Game.menuTrackCurrentlyPlaying == -1) {
									Random rand = new Random();
									Game.menuSoundLoopRandomizer = rand.nextInt(5);
									VolumeSlider.adjustVolume(Game.menuSoundLoops, Game.gameSoundLoops);
									Game.menuSoundLoopRandomizer = VolumeSlider.menuTrackSetup(Game.menuSoundLoopRandomizer);
									if(Game.menuSoundLoopRandomizer == -2)
										Game.menuSoundLoopRandomizer = 5;
									if(Game.menuSoundLoopRandomizer == 2) 
//										Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(864007, 1728014);//Seconds.millisec * 44100
										Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(432004, 864007);//Seconds.millisec * 22050
									else if(Game.menuSoundLoopRandomizer == 3) 
//										Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(464196, 1949705);//Seconds.millisec * 44100
										Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(232098, 974853);//Seconds.millisec * 22050
									else if(Game.menuSoundLoopRandomizer == 4)
//										Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(545561, 2291391);//Seconds.millisec * 44100
										Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loopSegmentFromStart(272781, 1145696);//Seconds.millisec * 22050
									else
										Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).loop();
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).setSoundLoopBoolean(true);
								}
								else if(string2.equals("false")) {
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
							break;
						}
					}
					Game.escapePressedNegateAction = false;
				}
		    	if((!itemPressed && itemReleased) ||
			    	(!cancelPressed && cancelReleased)
			    		) {
		    		if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
						Game.selectorBPMP = Game.selectorButtonPosition;
						Game.escapePressedNegateAction = false;
						if(Game.gameControllerInUse) {
							Game.gameControllerInUse = false;
							Game.keysAreInUse = false;
						}
						else {
							Game.gameControllerInUse = true;
							Game.keysAreInUse = true;
						}
		    		}
		    	}
		    }
		    else if(Game.State == STATE.HELP && Game.gameControllerInUse) {
		    	if((lX < -.65 || (leftHeld)) && joystickTimer < System.currentTimeMillis()) {
		    		int selector = Game.selectorButtonPosition;
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.firstTimeBeating) {
						if(Game.selectorButtonPosition != -1)
							Game.selectorButtonPosition = -1;
					}
					if(Game.selectorButtonPosition != selector) {
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
		    		if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	else if((lX > .65 || (rightHeld)) && joystickTimer < System.currentTimeMillis()) {
		    		int selector = Game.selectorButtonPosition;
					if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
					if(Game.firstTimeBeating) {
						if(Game.selectorButtonPosition != 0)
							Game.selectorButtonPosition = 0;
					}
					if(Game.selectorButtonPosition != selector) {
						if(Game.hudSFXPosition == 3)
							Game.hudSFXPosition = 0;
						else
							Game.hudSFXPosition++;
						Game.hudSFX.get(Game.hudSFXPosition).play();
					}
		    		if(joystickTimer == 0)
						joystickTimer = System.currentTimeMillis() + 500;
					else
						joystickTimer = System.currentTimeMillis() + 30;
		    	}
		    	if(lX < 0.145 && lX > -0.145 && lY < 0.145 && lY > -0.145 &&
			    		!(rightHeld) && !(leftHeld)&&
			    		!(upHeld) && !(downHeld)) {
			    		joystickTimer = 0;
			    }
		    	if(pausePressed ||
				    	shootPressed) {
					Game.enterButtonPushedDown = true;
		    	}
		    	if(cancelPressed ||
		    			itemPressed){
		    		if(Game.mouseIsClickedDown) {
						if(!Game.mouseIsOffClickedObjectAndHeldDown && (Game.backHighlighted))
							Game.mouseIsOffClickedObjectAndHeldDown = true;
						Game.backClicked = false;
						Game.escapePressedNegateAction = true;
					}
		    		else if(Game.enterButtonPushedDown)
						Game.escapePressedNegateAction = true;
		    	}

		    	if((!pausePressed && pauseReleased) ||
			    	(!shootPressed && shootReleased)
			    		) {
		    		Game.enterButtonPushedDown = false;
					if(!Game.escapePressedNegateAction) {
						switch(Game.selectorButtonPosition) {
						case -1:
							Game.selectorButtonPosition = -1;
							Game.State = Game.STATE.MENU;
							if(Game.smb3KickSoundLoop.clipIsActive())
								Game.smb3KickSoundLoop.stop();
							Game.smb3KickSoundLoop.play();
							break;
						case 0:
							if(Game.firstTimeBeating) {
								Game.State = STATE.CREDITS;
								if(Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).clipIsActive()) {
									Game.menuSoundLoops.get(Game.menuSoundLoopRandomizer).stop();
								}
								Game.keysAreInUse = false;
								Game.gameControllerInUse = false;
								Game.gameControllerInUseDI = false;
								Game.skipSequence = false;
								Game.askToSkipSequence = false;
								Game.selectorButtonPosition = 0;
							}
							break;
						}
					}
					Game.escapePressedNegateAction = false;
				}
		    	if((!itemPressed && itemReleased) ||
				    (!cancelPressed && cancelReleased)
		    		) {
		    		if(!Game.mouseIsClickedDown && !Game.enterButtonPushedDown) {
						Game.selectorBPMP = Game.selectorButtonPosition;
						Game.escapePressedNegateAction = false;
						if(Game.gameControllerInUse) {
							Game.gameControllerInUse = false;
							Game.keysAreInUse = false;
						}
						else {
							Game.gameControllerInUse = true;
							Game.keysAreInUse = true;
						}
		    		}
		    	}
		    }
		 if(!shootPressed && shootReleased)
			 shootReleased = false;
		 if(!itemPressed && itemReleased)
			 itemReleased = false;
		 if(!pausePressed && pauseReleased)
			 pauseReleased = false;
		 if(!cancelPressed && cancelReleased)
			 cancelReleased = false;
		 if(!upHeld && upReleased)
			 upReleased = false;
		 if(!downHeld && downReleased)
			 downReleased = false;
		 if(!leftHeld && leftReleased)
			 leftReleased = false;
		 if(!rightHeld && rightReleased)
			 rightReleased = false;
	}

	//one way to refresh
//	DirectAndRawInputEnvironmentPlugin directEnv = new DirectAndRawInputEnvironmentPlugin();
//	controllers = directEnv.getControllers();
	//one way to refresh
//		//THIS KILLS THE THREAD
//		final Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
//		for (final Thread thread : threadSet) {
//		  final String name = thread.getClass().getName();
//		  if (name.equals("net.java.games.input.RawInputEventQueue$QueueThread")) {
//		    thread.interrupt();
//		    try {
//		      thread.join();
//		    } catch (final InterruptedException e) {
//		      thread.interrupt();
//		    }
//		  }
//		}

//	public static void tick() {
//		Component comp = event.getComponent();
//		float value = event.getValue();
//		if(comp.isAnalog() && !comp.isRelative()) {
//			if(!Game.keysAreInUse)
//				Game.keysAreInUse = true;
//			//CONTROLLER ANALOG STICK
//			if(comp.getName().equals("Y Axis") ||comp.getName().equals("y")) {
//				if(0 < value) {
//					System.out.println("HERE");
//					Game.selectorButtonPosition--;
//				}
//			}
//		}
//	}
	
//	public static void main(String[] args) {
//		new ReadAllEvents();
//	}
}
