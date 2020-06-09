package com.github.strikerx3.jxinput;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.LinkedList;
import java.util.List;

import com.game.src.main.ControlsController;
import com.game.src.main.Game;
import com.game.src.main.Game.STATE;
import com.github.strikerx3.jxinput.enums.XInputButton;
import com.github.strikerx3.jxinput.exceptions.XInputNotLoadedException;
import com.github.strikerx3.jxinput.listener.XInputDeviceListener;
import com.github.strikerx3.jxinput.natives.XInputConstants;
import com.github.strikerx3.jxinput.natives.XInputNatives;

/**
 * Represents all XInput devices registered in the system.
 * Use the {@link #getAllDevices()} or {@link #getDeviceFor(int)} methods to start using the devices.
 * <p>
 * Certain methods in this class are not thread-safe and are documented as such.
 * It is not recommended to share an instance of {@code XInputDevice} across multiple threads.
 *
 * @author Ivan "StrikerX3" Oliveira
 * @see XInputComponents
 * @see XInputComponentsDelta
 */
public class XInputDevice {
    protected final int playerNum;
    private final ByteBuffer buffer;// Contains the XINPUT_STATE struct
    private final XInputComponents lastComponents;
    private final XInputComponents components;
    private final XInputComponentsDelta delta;
    public static XInputButtons xInputButtons;
    private boolean connected;
    
    private final List<XInputDeviceListener> listeners;

    private static final XInputDevice[] DEVICES;
    private static final XInputLibraryVersion LIBRARY_VERSION;
    private static final boolean GUIDE_BUTTON_SUPPORTED;
    private static boolean defaultControls = true;

    private static XInputStateReader stateReader = XInputStatePreProcessedReader.INSTANCE;
    public static Game game;
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
    public static boolean lTriggerPressed = false;
    public static boolean lTriggerReleased = false;
    public static boolean rTriggerPressed = false;
    public static boolean rTriggerReleased = false;
	public static short upKey = XInputConstants.XINPUT_GAMEPAD_DPAD_UP;
	public static short downKey = XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN;
	public static short leftKey = XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT;
	public static short rightKey = XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT;
	public static short shootKey = XInputConstants.XINPUT_GAMEPAD_A;
	public static short itemKey = XInputConstants.XINPUT_GAMEPAD_X;
	public static short pauseKey = XInputConstants.XINPUT_GAMEPAD_START;
	public static short cancelKey = XInputConstants.XINPUT_GAMEPAD_B;
    public static short a = XInputConstants.XINPUT_GAMEPAD_A;
    public static short b = XInputConstants.XINPUT_GAMEPAD_B;
    public static short x = XInputConstants.XINPUT_GAMEPAD_X;
    public static short y = XInputConstants.XINPUT_GAMEPAD_Y;
    public static short back = XInputConstants.XINPUT_GAMEPAD_BACK;
    public static short start = XInputConstants.XINPUT_GAMEPAD_START;
    public static short lShoulder = XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER;
    public static short rShoulder = XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER;
    public static short lThumb = XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB;
    public static short rThumb = XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB;
    public static short guide = XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON;
    public static short unknown = XInputConstants.XINPUT_GAMEPAD_UNKNOWN;
    public static short up = XInputConstants.XINPUT_GAMEPAD_DPAD_UP;
    public static short down = XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN;
    public static short left = XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT;
    public static short right = XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT;
    public static short lTrigger = XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER;
    public static short rTrigger = XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER;
    public static short aFake = 0;
    public static short bFake = 0;
    public static short xFake = 0;
    public static short yFake = 0;
    public static short backFake = 0;
    public static short startFake = 0;
    public static short lShoulderFake = 0;
    public static short rShoulderFake = 0;
    public static short lThumbFake = 0;
    public static short rThumbFake = 0;
    public static short guideFake = 0;
    public static short unknownFake = 0;
    public static short upFake = 0;
    public static short downFake = 0;
    public static short leftFake = 0;
    public static short rightFake = 0;
    public static short lTriggerFake = 0;
    public static short rTriggerFake = 0;
    private boolean controllerWasConnected = false;
    static {
        XInputDevice[] devices;
        if (XInputNatives.isLoaded()) {
            devices = new XInputDevice[XInputConstants.MAX_PLAYERS];
            for (int i = 0; i < XInputConstants.MAX_PLAYERS; i++) {
                devices[i] = new XInputDevice(i);
            }
        } else {
            devices = null;
        }
        DEVICES = devices;

        LIBRARY_VERSION = XInputLibraryVersion.values()[XInputNatives.getLoadedLibVersion()];
        GUIDE_BUTTON_SUPPORTED = XInputNatives.isGuideButtonSupported();
    }

    protected XInputDevice(final int playerNum) {
        this.playerNum = playerNum;
        buffer = newBuffer(16);// sizeof(XINPUT_STATE)

        lastComponents = new XInputComponents();
        components = new XInputComponents();
        delta = new XInputComponentsDelta(lastComponents, components);
        xInputButtons = new XInputButtons();
        listeners = new LinkedList<XInputDeviceListener>();

        poll();
    }

    /**
     * Determines if the XInput devices are available on this platform.
     *
     * @return <code>true</code> if the XInput devices are available, <code>false</code> if not
     */
    public static boolean isAvailable() {
        return DEVICES != null;
    }

    /**
     * Retrieves the loaded XInput library version.
     *
     * @return one of the values of {@link XInputLibraryVersion}
     */
    public static XInputLibraryVersion getLibraryVersion() {
        return LIBRARY_VERSION;
    }

    /**
     * Determines if polling the Guide button is supported.
     *
     * @return <code>true</code> if the Guide button state can be read, <code>false</code> otherwise.
     */
    public static boolean isGuideButtonSupported() {
        return GUIDE_BUTTON_SUPPORTED;
    }

    /**
     * Returns an array containing all registered XInput devices.
     * <p>
     * The {@code XInputDevice} objects are not thread-safe.
     *
     * @return all XInput devices
     * @throws XInputNotLoadedException if the native library failed to load
     */
    public static XInputDevice[] getAllDevices() throws XInputNotLoadedException {
        checkLibraryReady();
        return DEVICES.clone();
    }

    /**
     * Returns the XInput device for the specified player. An instance is created for each of the four players in a system.
     * <p>
     * The returned object should not be shared among multiple threads.
     *
     * @param playerNum the player number
     * @return the XInput device for the specified player
     * @throws XInputNotLoadedException if the native library failed to load
     */
    public static XInputDevice getDeviceFor(final int playerNum) throws XInputNotLoadedException {
        checkLibraryReady();
        if (playerNum < 0 || playerNum >= XInputConstants.MAX_PLAYERS) {
            throw new IllegalArgumentException("Invalid player number: " + playerNum + ". Must be between 0 and " + (XInputConstants.MAX_PLAYERS - 1));
        }
        return DEVICES[playerNum];
    }

    /**
     * Defines whether to perform additional precalculations to the data. If enabled, in addition to filling in the raw
     * values, the fields {@code lx}, {@code ly}, {@code rx}, {@code ry}, {@code lt} and {@code rt} in {@code XInputAxes}
     * will be filled with non-zero {@code float} values ranging from -1 to 1 for the thumbsticks or 0 to 1 for the triggers,
     * based on the raw values. If disabled, only the raw values will be filled. By default, the float values are calculated.
     *
     * @param preprocess whether to preprocess data into the {@code XInputAxes}'s {@code float} fields ({@code true}) or
     * just use raw data ({@code false})
     */
    public static void setPreProcessData(final boolean preprocess) {
        if (preprocess) {
            stateReader = XInputStatePreProcessedReader.INSTANCE;
        } else {
            stateReader = XInputStateRawReader.INSTANCE;
        }
    }

    /**
     * Adds an event listener that will react to changes in the input.
     *
     * @param listener the listener
     */
    public void addListener(final XInputDeviceListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes a registered event listener
     *
     * @param listener the listener
     */
    public void removeListener(final XInputDeviceListener listener) {
        listeners.remove(listener);
    }

    /**
     * Reads input from the device and updates components.
     * <p>
     * This method is not thread-safe.
     *
     * @return <code>false</code> if the device is not connected
     * @throws IllegalStateException if there is an error trying to read the device state
     */
    public boolean poll() {
        if (!checkReturnCode(XInputNatives.pollDevice(playerNum, buffer))) {
        	//Controller disconnected
        	if(controllerWasConnected) {
        		reset();
        		if(Game.gameControllerInUse && !Game.gameControllerInUseDI) {
        			//reset player controls now
        			game.getPlayer().reset();
        			if(Game.State != STATE.GAME && 
        					!(Game.State == STATE.CONTROLS && System.currentTimeMillis() <ControlsController.buttonChangeTimer) &&
        					!(Game.State == STATE.SET_SCORE && Game.selectorButtonPosition == 0)) 
        					Game.keysAreInUse = false;
        			Game.gameControllerInUse = false;
        		}
        		controllerWasConnected = false;
        	}
        	return false;
        }
        setConnected(true);

        lastComponents.copy(components);

        stateReader.read(buffer, components);

        processDelta();
        if(!controllerWasConnected)
        	controllerWasConnected = true;
        return true;
    }

    protected boolean checkReturnCode(final int ret) {
        if (ret == XInputConstants.ERROR_DEVICE_NOT_CONNECTED) {
            setConnected(false);
            return false;
        }
        if (ret != XInputConstants.ERROR_SUCCESS) {
            setConnected(false);
            throw new IllegalStateException("Could not read controller state: 0x" + Integer.toHexString(ret));
        }
        return true;
    }

    protected boolean checkReturnCode(final int ret, final int... validRetCodes) {
        if (ret == XInputConstants.ERROR_DEVICE_NOT_CONNECTED) {
            setConnected(false);
            return false;
        }
        if (ret != XInputConstants.ERROR_SUCCESS) {
            setConnected(false);
            for (final int validRet : validRetCodes) {
                if (ret == validRet) {
                    return false;
                }
            }
            throw new IllegalStateException("Could not read controller state: 0x" + Integer.toHexString(ret));
        }
        return true;
    }

    private void setConnected(final boolean state) {
        final boolean lastConnected = connected;
        connected = state;
        for (final XInputDeviceListener listener : listeners) {
            if (connected && !lastConnected) {
                listener.connected();
            } else if (!connected && lastConnected) {
                listener.disconnected();
            }
        }
    }

    private void processDelta() {
        final XInputButtonsDelta buttons = delta.getButtons();
        for (final XInputDeviceListener listener : listeners) {
            for (final XInputButton button : XInputButton.values()) {
                if (buttons.isPressed(button)) {
                    listener.buttonChanged(button, true);
                } else if (buttons.isReleased(button)) {
                    listener.buttonChanged(button, false);
                }
            }
        }
    }

    /**
     * Sets the vibration of the controller. Returns <code>false</code> if the device was not connected.
     * <p>
     * This method is not thread-safe.
     *
     * @param leftMotor the left motor speed, from 0 to 65535
     * @param rightMotor the right motor speed, from 0 to 65535
     * @return <code>false</code> if the device was not connected
     * @throws IllegalArgumentException if either motor speed values lie out of the range 0..65535
     */
    public boolean setVibration(final int leftMotor, final int rightMotor) {
        if (leftMotor < 0 || leftMotor > 65535) {
            throw new IllegalArgumentException("Left motor speed out of range (0..65535): " + leftMotor);
        }
        if (rightMotor < 0 || rightMotor > 65535) {
            throw new IllegalArgumentException("Right motor speed out of range (0..65535): " + rightMotor);
        }
        return XInputNatives.setVibration(playerNum, leftMotor, rightMotor) == XInputConstants.ERROR_SUCCESS;
    }

    /**
     * Returns the state of the XInput controller components before the last poll.
     *
     * @return the state of the XInput controller components before the last poll.
     */
    public XInputComponents getLastComponents() {
        return lastComponents;
    }

    /**
     * Returns the state of the XInput controller components at the last poll.
     *
     * @return the state of the XInput controller components at the last poll.
     */
    public XInputComponents getComponents() {
        return components;
    }

    /**
     * Returns the difference between the last two states of the XInput controller components.
     *
     * @return the difference between the last two states of the XInput controller components.
     */
    public XInputComponentsDelta getDelta() {
        return delta;
    }

    /**
     * Returns a boolean indicating whether this device is connected.
     *
     * @return <code>true</code> if the device is connected, <code>false</code> otherwise
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * Returns the player number that this device represents.
     *
     * @return the player number that this device represents.
     */
    public int getPlayerNum() {
        return playerNum;
    }

    /**
     * Checks if the native library is loaded and ready for use.
     *
     * @throws XInputNotLoadedException if the native library is not loaded
     */
    private static void checkLibraryReady() throws XInputNotLoadedException {
        if (!XInputNatives.isLoaded()) {
            throw new XInputNotLoadedException("Native library failed to load", XInputNatives.getLoadError());
        }
    }

    /**
     * Creates a new direct ByteBuffer to be used for communicating with the native library.
     *
     * @param capacity the buffer capacity
     * @return a direct ByteBuffer with the specified capacity
     */
    protected static ByteBuffer newBuffer(final int capacity) {
        final ByteBuffer buffer = ByteBuffer.allocateDirect(capacity);
        buffer.order(ByteOrder.nativeOrder());
        return buffer;
    }

    private static interface XInputStateReader {
        void read(final ByteBuffer buffer, final XInputComponents components);
    }
    private static boolean buttonChanger(short btns, short xInputConstant) {
    	if(XInputDevice.a == xInputConstant) 
    		return (btns & a) != 0;
    	else if(XInputDevice.right == xInputConstant)
    		return (btns & XInputDevice.right) != 0;
//    	switch(xInputConstant) {
//    	case XInputConstants.XINPUT_GAMEPAD_A:
//    		return (btns & a) != 0;
//    	default:
//    		break;
//    	}
    	return false;
    }
    private static void dPadChanger(short btns, boolean up, boolean down, boolean left, boolean right) {
    	if(XInputDevice.a == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)
    		right = (btns & XInputConstants.XINPUT_GAMEPAD_A) != 0;
    	else if(XInputDevice.b == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) 
    		right = (btns & XInputConstants.XINPUT_GAMEPAD_B) != 0;
    	else if(XInputDevice.x == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) 
    		right = (btns & XInputConstants.XINPUT_GAMEPAD_X) != 0;
    	else if(XInputDevice.y == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) 
    		right = (btns & XInputConstants.XINPUT_GAMEPAD_Y) != 0;
    	else
    		right = (btns & XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) != 0;
    	return;
    }
    private static class XInputStateRawReader implements XInputStateReader {
        public static final XInputStateRawReader INSTANCE = new XInputStateRawReader();

        protected XInputStateRawReader() {}

        @Override
        public void read(final ByteBuffer buffer, final XInputComponents components) {
            // typedef struct _XINPUT_STATE
            // {
            //     DWORD                               dwPacketNumber;
            //     XINPUT_GAMEPAD                      Gamepad;
            // } XINPUT_STATE, *PXINPUT_STATE;

            // typedef struct _XINPUT_GAMEPAD
            // {
            //     WORD                                wButtons;
            //     BYTE                                bLeftTrigger;
            //     BYTE                                bRightTrigger;
            //     SHORT                               sThumbLX;
            //     SHORT                               sThumbLY;
            //     SHORT                               sThumbRX;
            //     SHORT                               sThumbRY;
            // } XINPUT_GAMEPAD, *PXINPUT_GAMEPAD;

            /*int packetNumber = */buffer.getInt(); // can be safely ignored
            final short btns = buffer.getShort();
            final byte leftTrigger = buffer.get();
            final byte rightTrigger = buffer.get();
            final short thumbLX = buffer.getShort();
            final short thumbLY = buffer.getShort();
            final short thumbRX = buffer.getShort();
            final short thumbRY = buffer.getShort();
            buffer.flip();
            
            final boolean up = (btns & XInputConstants.XINPUT_GAMEPAD_DPAD_UP) != 0;
            final boolean down = (btns & XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN) != 0;
            final boolean left = (btns & XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT) != 0;
            final boolean right = (btns & XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT) != 0;
            //dPadChanger(btns,up,down,left,right);
            final XInputAxes axes = components.getAxes();
            axes.lxRaw = thumbLX;
            axes.lyRaw = thumbLY;
            axes.rxRaw = thumbRX;
            axes.ryRaw = thumbRY;
            axes.ltRaw = leftTrigger & 0xff;
            axes.rtRaw = rightTrigger & 0xff;
            axes.lx = axes.ly = 0f;
            axes.rx = axes.ry = 0f;
            axes.lt = axes.rt = 0f;
            axes.dpad = XInputAxes.dpadFromButtons(up, down, left, right);
        	final XInputButtons buttons = components.getButtons();/*
            if(defaultControls) {
	            buttons.a = (btns & XInputConstants.XINPUT_GAMEPAD_A) != 0;
	            buttons.b = (btns & XInputConstants.XINPUT_GAMEPAD_B) != 0;
	            buttons.x = (btns & XInputConstants.XINPUT_GAMEPAD_X) != 0;
	            buttons.y = (btns & XInputConstants.XINPUT_GAMEPAD_Y) != 0;
	            buttons.back = (btns & XInputConstants.XINPUT_GAMEPAD_BACK) != 0;
	            buttons.start = (btns & XInputConstants.XINPUT_GAMEPAD_START) != 0;
	            buttons.lShoulder = (btns & XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER) != 0;
	            buttons.rShoulder = (btns & XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER) != 0;
	            buttons.lThumb = (btns & XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB) != 0;
	            buttons.rThumb = (btns & XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB) != 0;
	            buttons.guide = (btns & XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON) != 0;
	            buttons.unknown = (btns & XInputConstants.XINPUT_GAMEPAD_UNKNOWN) != 0;
	            buttons.up = up;
	            buttons.down = down;
	            buttons.left = left;
	            buttons.right = right;
            }
            else {*/
            	buttons.a = (btns & a) != 0;
//        		buttons.a = buttonChanger(btns,XInputConstants.XINPUT_GAMEPAD_A);
	            buttons.b = (btns & b) != 0;
	            buttons.x = (btns & x) != 0;
	            buttons.y = (btns & y) != 0;
	            buttons.back = (btns & back) != 0;
	            buttons.start = (btns & start) != 0;
	            buttons.lShoulder = (btns & lShoulder) != 0;
	            buttons.rShoulder = (btns & rShoulder) != 0;
	            buttons.lThumb = (btns & lThumb) != 0;
	            buttons.rThumb = (btns & rThumb) != 0;
	            buttons.guide = (btns & guide) != 0;
	            buttons.unknown = (btns & unknown) != 0;
//	            buttons.lTrigger = (btns & lTrigger) != 0;
//	            buttons.rTrigger = (btns & rTrigger) != 0;
	            /*
	            switch(XInputDevice.up) {
	        	case XInputConstants.XINPUT_GAMEPAD_A:
		            buttons.up =  (btns & XInputDevice.a) != 0;
	                break;
	        	case XInputConstants.XINPUT_GAMEPAD_B:
		            buttons.up =  (btns & XInputDevice.b) != 0;
	                break;
	        	case XInputConstants.XINPUT_GAMEPAD_X:
		            buttons.up =  (btns & XInputDevice.x) != 0;
	                break;
	        	case XInputConstants.XINPUT_GAMEPAD_Y:
		            buttons.up =  (btns & XInputDevice.y) != 0;
	                break;
	        	case XInputConstants.XINPUT_GAMEPAD_BACK:
		            buttons.up =  (btns & XInputDevice.back) != 0;
	                break;
	        	case XInputConstants.XINPUT_GAMEPAD_START:
		            buttons.up =  (btns & XInputDevice.start) != 0;
	                break;
	        	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
		            buttons.up =  (btns & XInputDevice.lShoulder) != 0;
	                break;
	        	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
		            buttons.up =  (btns & XInputDevice.rShoulder) != 0;
	                break;
	        	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
		            buttons.up =  (btns & XInputDevice.lThumb) != 0;
	                break;
	        	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
		            buttons.up =  (btns & XInputDevice.rThumb) != 0;
	                break;
	        	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
		            buttons.up =  (btns & XInputDevice.guide) != 0;
	                break;
	        	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
		            buttons.up =  (btns & XInputDevice.unknown) != 0;
	                break;
	        	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
		            buttons.up =  (btns & XInputDevice.up) != 0;
	                break;
	        	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
		            buttons.up =  (btns & XInputDevice.down) != 0;
	                break;
	        	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
		            buttons.up =  (btns & XInputDevice.left) != 0;
	                break;
	        	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
		            buttons.up =  (btns & XInputDevice.right) != 0;
	                break;
	        	default:
		            buttons.up =  (btns & XInputDevice.up) != 0;
	        		break;
	    	}*/
	            
	            buttons.up =  (btns & XInputDevice.up) != 0;
	            buttons.down = (btns & XInputDevice.down) != 0;
	            buttons.left = (btns & XInputDevice.left) != 0;
	            buttons.right = (btns & XInputDevice.right) != 0;
//	            buttons.right = buttonChanger(btns, XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT);
//	            if(XInputDevice.a == XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT)
//		            buttons.right = (btns & b) != 0;
//	            else
//	            	buttons.right =(btns & XInputDevice.right) != 0;
            //}
        }
    }

    private static class XInputStatePreProcessedReader extends XInputStateRawReader {
        public static final XInputStatePreProcessedReader INSTANCE = new XInputStatePreProcessedReader();

        protected XInputStatePreProcessedReader() {}

        @Override
        public void read(final ByteBuffer buffer, final XInputComponents components) {
            super.read(buffer, components);
            final XInputAxes axes = components.getAxes();
            axes.lx = (axes.lxRaw + 32768) / 32767.5f - 1;
            axes.ly = (axes.lyRaw + 32768) / 32767.5f - 1;
            axes.rx = (axes.rxRaw + 32768) / 32767.5f - 1;
            axes.ry = (axes.ryRaw + 32768) / 32767.5f - 1;

            axes.lt = (axes.ltRaw & 0xff) / 255f;
            axes.rt = (axes.rtRaw & 0xff) / 255f;
        }
    }
    public static void reset() {
    	upHeld = false;
    	downHeld = false;
    	leftHeld = false;
    	rightHeld = false;
    	shootPressed = false;
    	itemPressed = false;
    	pausePressed = false;
    	cancelPressed = false;
    	upReleased = false;
    	downReleased = false;
    	leftReleased = false;
    	rightReleased = false;
    	shootReleased = false;
    	itemReleased = false;
    	pauseReleased = false;
    	cancelReleased = false;
    }
    public void changeButton(String s, short btn) {
    	switch(s) {
	    	case "a":
	    		this.a = btn;
	    		break;
	    	case "b":
	    		this.b = btn;
	    		break;
	    	case "x":
	    		this.x = btn;
	    		break;
	    	case "y":
	    		this.y = btn;
	    		break;
	    	case "back":
	    		this.back = btn;
	    		break;
	    	case "start":
	    		this.start = btn;
	    		break;
	    	case "lShoulder":
	    		this.lShoulder = btn;
	    		break;
	    	case "rShoulder":
	    		this.rShoulder = btn;
	    		break;
	    	case "lThumb":
	    		this.lThumb = btn;
	    		break;
	    	case "rThumb":
	    		this.rThumb = btn;
	    		break;
	    	case "guide":
	    		this.guide = btn;
	    		break;
	    	case "unknown":
	    		this.unknown = btn;
	    		break;
	    	case "up":
	    		this.up = btn;
	    		break;
	    	case "down":
	    		this.down = btn;
	    		break;
	    	case "left":
	    		this.left = btn;
	    		break;
	    	case "right":
	    		this.right = btn;
	    		break;
	    	case "lTrigger":
	    		this.lTrigger = btn;
	    		break;
	    	case "rTrigger":
	    		this.rTrigger = btn;
	    		break;
	    	default:
	    		break;
    	}
    	defaultControls = false;
    }
    public static void emulatedControls(Game game, int lTrigger, int rTrigger) {
    	switch(upKey) {
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_UP)) {
    			upHeld = true;
    			upReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_UP)) {
    			upHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_DOWN)) {
    			upHeld = true;
    			upReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_DOWN)) {
    			upHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_LEFT)) {
    			upHeld = true;
    			upReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_LEFT)) {
    			upHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_RIGHT)) {
    			upHeld = true;
    			upReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_RIGHT)) {
    			upHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_A:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.A)) {
    			upHeld = true;
    			upReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.A)) {
    			upHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_B:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.B)) {
    			upHeld = true;
    			upReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.B)) {
    			upHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_X:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.X)) {
    			upHeld = true;
    			upReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.X)) {
    			upHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_Y:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.Y)) {
    			upHeld = true;
    			upReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.Y)) {
    			upHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_BACK:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.BACK)) {
    			upHeld = true;
    			upReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.BACK)) {
    			upHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_START:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.START)) {
    			upHeld = true;
    			upReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.START)) {
    			upHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.LEFT_SHOULDER)) {
    			upHeld = true;
    			upReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.LEFT_SHOULDER)) {
    			upHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.RIGHT_SHOULDER)) {
    			upHeld = true;
    			upReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.RIGHT_SHOULDER)) {
    			upHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.LEFT_THUMBSTICK)) {
    			upHeld = true;
    			upReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.LEFT_THUMBSTICK)) {
    			upHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.RIGHT_THUMBSTICK)) {
    			upHeld = true;
    			upReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.RIGHT_THUMBSTICK)) {
    			upHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.GUIDE_BUTTON)) {
    			upHeld = true;
    			upReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.GUIDE_BUTTON)) {
    			upHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.UNKNOWN)) {
    			upHeld = true;
    			upReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.UNKNOWN)) {
    			upHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    		if(0.3 < lTrigger) {
    			if(!upHeld)
    				upHeld = true;
    			if(!upReleased)
    				upReleased = true;
    	    }
    	    else if(lTrigger == 0 && XInputDevice.lTriggerReleased){
    	    	if(upHeld)
    	    		upHeld = false;
    	    }
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    		if(0.3 < rTrigger) {
    			if(!upHeld)
    				upHeld = true;
    			if(!upReleased)
    				upReleased = true;
    	    }
    	    else if(rTrigger == 0 && XInputDevice.rTriggerReleased){
    	    	if(upHeld)
    	    		upHeld = false;
    	    }
    		break;
    	default:
    		break;
    	}
    	switch(downKey) {
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_UP)) {
    			downHeld = true;
    			downReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_UP)) {
    			downHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_DOWN)) {
    			downHeld = true;
    			downReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_DOWN)) {
    			downHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_LEFT)) {
    			downHeld = true;
    			downReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_LEFT)) {
    			downHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_RIGHT)) {
    			downHeld = true;
    			downReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_RIGHT)) {
    			downHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_A:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.A)) {
    			downHeld = true;
    			downReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.A)) {
    			downHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_B:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.B)) {
    			downHeld = true;
    			downReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.B)) {
    			downHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_X:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.X)) {
    			downHeld = true;
    			downReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.X)) {
    			downHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_Y:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.Y)) {
    			downHeld = true;
    			downReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.Y)) {
    			downHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_BACK:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.BACK)) {
    			downHeld = true;
    			downReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.BACK)) {
    			downHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_START:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.START)) {
    			downHeld = true;
    			downReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.START)) {
    			downHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.LEFT_SHOULDER)) {
    			downHeld = true;
    			downReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.LEFT_SHOULDER)) {
    			downHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.RIGHT_SHOULDER)) {
    			downHeld = true;
    			downReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.RIGHT_SHOULDER)) {
    			downHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.LEFT_THUMBSTICK)) {
    			downHeld = true;
    			downReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.LEFT_THUMBSTICK)) {
    			downHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.RIGHT_THUMBSTICK)) {
    			downHeld = true;
    			downReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.RIGHT_THUMBSTICK)) {
    			downHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.GUIDE_BUTTON)) {
    			downHeld = true;
    			downReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.GUIDE_BUTTON)) {
    			downHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.UNKNOWN)) {
    			downHeld = true;
    			downReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.UNKNOWN)) {
    			downHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    		if(0.3 < lTrigger) {
    			if(!downHeld)
    				downHeld = true;
    			if(!downReleased)
    				downReleased = true;
    	    }
    	    else if(lTrigger == 0 && XInputDevice.lTriggerReleased){
    	    	if(downHeld)
    	    		downHeld = false;
    	    }
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    		if(0.3 < rTrigger) {
    			if(!downHeld)
    				downHeld = true;
    			if(!downReleased)
    				downReleased = true;
    	    }
    	    else if(rTrigger == 0 && XInputDevice.rTriggerReleased){
    	    	if(downHeld)
    	    		downHeld = false;
    	    }
    		break;
    	default:
    		break;
    	}
    	switch(leftKey) {
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_UP)) {
    			leftHeld = true;
    			leftReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_UP)) {
    			leftHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_DOWN)) {
    			leftHeld = true;
    			leftReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_DOWN)) {
    			leftHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_LEFT)) {
    			leftHeld = true;
    			leftReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_LEFT)) {
    			leftHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_RIGHT)) {
    			leftHeld = true;
    			leftReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_RIGHT)) {
    			leftHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_A:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.A)) {
    			leftHeld = true;
    			leftReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.A)) {
    			leftHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_B:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.B)) {
    			leftHeld = true;
    			leftReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.B)) {
    			leftHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_X:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.X)) {
    			leftHeld = true;
    			leftReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.X)) {
    			leftHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_Y:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.Y)) {
    			leftHeld = true;
    			leftReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.Y)) {
    			leftHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_BACK:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.BACK)) {
    			leftHeld = true;
    			leftReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.BACK)) {
    			leftHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_START:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.START)) {
    			leftHeld = true;
    			leftReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.START)) {
    			leftHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.LEFT_SHOULDER)) {
    			leftHeld = true;
    			leftReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.LEFT_SHOULDER)) {
    			leftHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.RIGHT_SHOULDER)) {
    			leftHeld = true;
    			leftReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.RIGHT_SHOULDER)) {
    			leftHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.LEFT_THUMBSTICK)) {
    			leftHeld = true;
    			leftReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.LEFT_THUMBSTICK)) {
    			leftHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.RIGHT_THUMBSTICK)) {
    			leftHeld = true;
    			leftReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.RIGHT_THUMBSTICK)) {
    			leftHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.GUIDE_BUTTON)) {
    			leftHeld = true;
    			leftReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.GUIDE_BUTTON)) {
    			leftHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.UNKNOWN)) {
    			leftHeld = true;
    			leftReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.UNKNOWN)) {
    			leftHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    		if(0.3 < lTrigger) {
    			if(!leftHeld)
    				leftHeld = true;
    			if(!leftReleased)
    				leftReleased = true;
    	    }
    	    else if(lTrigger == 0 && XInputDevice.lTriggerReleased){
    	    	if(leftHeld)
    	    		leftHeld = false;
    	    }
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    		if(0.3 < rTrigger) {
    			if(!leftHeld)
    				leftHeld = true;
    			if(!leftReleased)
    				leftReleased = true;
    	    }
    	    else if(rTrigger == 0 && XInputDevice.rTriggerReleased){
    	    	if(leftHeld)
    	    		leftHeld = false;
    	    }
    		break;
    	default:
    		break;
    	}
    	switch(rightKey) {
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_UP)) {
    			rightHeld = true;
    			rightReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_UP)) {
    			rightHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_DOWN)) {
    			rightHeld = true;
    			rightReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_DOWN)) {
    			rightHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_LEFT)) {
    			rightHeld = true;
    			rightReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_LEFT)) {
    			rightHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_RIGHT)) {
    			rightHeld = true;
    			rightReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_RIGHT)) {
    			rightHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_A:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.A)) {
    			rightHeld = true;
    			rightReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.A)) {
    			rightHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_B:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.B)) {
    			rightHeld = true;
    			rightReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.B)) {
    			rightHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_X:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.X)) {
    			rightHeld = true;
    			rightReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.X)) {
    			rightHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_Y:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.Y)) {
    			rightHeld = true;
    			rightReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.Y)) {
    			rightHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_BACK:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.BACK)) {
    			rightHeld = true;
    			rightReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.BACK)) {
    			rightHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_START:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.START)) {
    			rightHeld = true;
    			rightReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.START)) {
    			rightHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.LEFT_SHOULDER)) {
    			rightHeld = true;
    			rightReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.LEFT_SHOULDER)) {
    			rightHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.RIGHT_SHOULDER)) {
    			rightHeld = true;
    			rightReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.RIGHT_SHOULDER)) {
    			rightHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.LEFT_THUMBSTICK)) {
    			rightHeld = true;
    			rightReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.LEFT_THUMBSTICK)) {
    			rightHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.RIGHT_THUMBSTICK)) {
    			rightHeld = true;
    			rightReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.RIGHT_THUMBSTICK)) {
    			rightHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.GUIDE_BUTTON)) {
    			rightHeld = true;
    			rightReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.GUIDE_BUTTON)) {
    			rightHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.UNKNOWN)) {
    			rightHeld = true;
    			rightReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.UNKNOWN)) {
    			rightHeld = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    		if(0.3 < lTrigger) {
    			if(!rightHeld)
    				rightHeld = true;
    			if(!rightReleased)
    				rightReleased = true;
    	    }
    	    else if(lTrigger == 0 && XInputDevice.lTriggerReleased){
    	    	if(rightHeld)
    	    		rightHeld = false;
    	    }
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    		if(0.3 < rTrigger) {
    			if(!rightHeld)
    				rightHeld = true;
    			if(!rightReleased)
    				rightReleased = true;
    	    }
    	    else if(rTrigger == 0 && XInputDevice.rTriggerReleased){
    	    	if(rightHeld)
    	    		rightHeld = false;
    	    }
    		break;
    	default:
    		break;
    	}
    	switch(shootKey) {
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_UP)) {
    			shootPressed = true;
    			shootReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_UP)) {
    			shootPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_DOWN)) {
    			shootPressed = true;
    			shootReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_DOWN)) {
    			shootPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_LEFT)) {
    			shootPressed = true;
    			shootReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_LEFT)) {
    			shootPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_RIGHT)) {
    			shootPressed = true;
    			shootReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_RIGHT)) {
    			shootPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_A:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.A)) {
    			shootPressed = true;
    			shootReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.A)) {
    			shootPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_B:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.B)) {
    			shootPressed = true;
    			shootReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.B)) {
    			shootPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_X:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.X)) {
    			shootPressed = true;
    			shootReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.X)) {
    			shootPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_Y:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.Y)) {
    			shootPressed = true;
    			shootReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.Y)) {
    			shootPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_BACK:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.BACK)) {
    			shootPressed = true;
    			shootReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.BACK)) {
    			shootPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_START:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.START)) {
    			shootPressed = true;
    			shootReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.START)) {
    			shootPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.LEFT_SHOULDER)) {
    			shootPressed = true;
    			shootReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.LEFT_SHOULDER)) {
    			shootPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.RIGHT_SHOULDER)) {
    			shootPressed = true;
    			shootReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.RIGHT_SHOULDER)) {
    			shootPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.LEFT_THUMBSTICK)) {
    			shootPressed = true;
    			shootReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.LEFT_THUMBSTICK)) {
    			shootPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.RIGHT_THUMBSTICK)) {
    			shootPressed = true;
    			shootReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.RIGHT_THUMBSTICK)) {
    			shootPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.GUIDE_BUTTON)) {
    			shootPressed = true;
    			shootReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.GUIDE_BUTTON)) {
    			shootPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.UNKNOWN)) {
    			shootPressed = true;
    			shootReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.UNKNOWN)) {
    			shootPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    		if(0.3 < lTrigger) {
    			if(!shootPressed)
    				shootPressed = true;
    			if(!shootReleased)
    				shootReleased = true;
    	    }
    	    else if(lTrigger == 0 && XInputDevice.lTriggerReleased){
    	    	if(shootPressed)
    	    		shootPressed = false;
    	    }
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    		if(0.3 < rTrigger) {
    			if(!shootPressed)
    				shootPressed = true;
    			if(!shootReleased)
    				shootReleased = true;
    	    }
    	    else if(rTrigger == 0 && XInputDevice.rTriggerReleased){
    	    	if(shootPressed)
    	    		shootPressed = false;
    	    }
    		break;
    	default:
    		break;
    	}
    	switch(itemKey) {
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_UP)) {
    			itemPressed = true;
    			itemReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_UP)) {
    			itemPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_DOWN)) {
    			itemPressed = true;
    			itemReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_DOWN)) {
    			itemPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_LEFT)) {
    			itemPressed = true;
    			itemReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_LEFT)) {
    			itemPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_RIGHT)) {
    			itemPressed = true;
    			itemReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_RIGHT)) {
    			itemPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_A:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.A)) {
    			itemPressed = true;
    			itemReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.A)) {
    			itemPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_B:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.B)) {
    			itemPressed = true;
    			itemReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.B)) {
    			itemPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_X:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.X)) {
    			itemPressed = true;
    			itemReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.X)) {
    			itemPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_Y:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.Y)) {
    			itemPressed = true;
    			itemReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.Y)) {
    			itemPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_BACK:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.BACK)) {
    			itemPressed = true;
    			itemReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.BACK)) {
    			itemPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_START:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.START)) {
    			itemPressed = true;
    			itemReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.START)) {
    			itemPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.LEFT_SHOULDER)) {
    			itemPressed = true;
    			itemReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.LEFT_SHOULDER)) {
    			itemPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.RIGHT_SHOULDER)) {
    			itemPressed = true;
    			itemReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.RIGHT_SHOULDER)) {
    			itemPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.LEFT_THUMBSTICK)) {
    			itemPressed = true;
    			itemReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.LEFT_THUMBSTICK)) {
    			itemPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.RIGHT_THUMBSTICK)) {
    			itemPressed = true;
    			itemReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.RIGHT_THUMBSTICK)) {
    			itemPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.GUIDE_BUTTON)) {
    			itemPressed = true;
    			itemReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.GUIDE_BUTTON)) {
    			itemPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.UNKNOWN)) {
    			itemPressed = true;
    			itemReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.UNKNOWN)) {
    			itemPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    		if(0.3 < lTrigger) {
    			if(!itemPressed)
    				itemPressed = true;
    			if(!itemReleased)
    				itemReleased = true;
    	    }
    	    else if(lTrigger == 0 && XInputDevice.lTriggerReleased){
    	    	if(itemPressed)
    	    		itemPressed = false;
    	    }
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    		if(0.3 < rTrigger) {
    			if(!itemPressed)
    				itemPressed = true;
    			if(!itemReleased)
    				itemReleased = true;
    	    }
    	    else if(rTrigger == 0 && XInputDevice.rTriggerReleased){
    	    	if(itemPressed)
    	    		itemPressed = false;
    	    }
    		break;
    	default:
    		break;
    	}
    	switch(pauseKey) {
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_UP)) {
    			pausePressed = true;
    			pauseReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_UP)) {
    			pausePressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_DOWN)) {
    			pausePressed = true;
    			pauseReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_DOWN)) {
    			pausePressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_LEFT)) {
    			pausePressed = true;
    			pauseReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_LEFT)) {
    			pausePressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_RIGHT)) {
    			pausePressed = true;
    			pauseReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_RIGHT)) {
    			pausePressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_A:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.A)) {
    			pausePressed = true;
    			pauseReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.A)) {
    			pausePressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_B:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.B)) {
    			pausePressed = true;
    			pauseReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.B)) {
    			pausePressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_X:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.X)) {
    			pausePressed = true;
    			pauseReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.X)) {
    			pausePressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_Y:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.Y)) {
    			pausePressed = true;
    			pauseReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.Y)) {
    			pausePressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_BACK:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.BACK)) {
    			pausePressed = true;
    			pauseReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.BACK)) {
    			pausePressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_START:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.START)) {
    			pausePressed = true;
    			pauseReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.START)) {
    			pausePressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.LEFT_SHOULDER)) {
    			pausePressed = true;
    			pauseReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.LEFT_SHOULDER)) {
    			pausePressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.RIGHT_SHOULDER)) {
    			pausePressed = true;
    			pauseReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.RIGHT_SHOULDER)) {
    			pausePressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.LEFT_THUMBSTICK)) {
    			pausePressed = true;
    			pauseReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.LEFT_THUMBSTICK)) {
    			pausePressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.RIGHT_THUMBSTICK)) {
    			pausePressed = true;
    			pauseReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.RIGHT_THUMBSTICK)) {
    			pausePressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.GUIDE_BUTTON)) {
    			pausePressed = true;
    			pauseReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.GUIDE_BUTTON)) {
    			pausePressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.UNKNOWN)) {
    			pausePressed = true;
    			pauseReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.UNKNOWN)) {
    			pausePressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    		if(0.3 < lTrigger) {
    			if(!pausePressed)
    				pausePressed = true;
    			if(!pauseReleased)
    				pauseReleased = true;
    	    }
    	    else if(lTrigger == 0 && XInputDevice.lTriggerReleased){
    	    	if(pausePressed)
    	    		pausePressed = false;
    	    }
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    		if(0.3 < rTrigger) {
    			if(!pausePressed)
    				pausePressed = true;
    			if(!pauseReleased)
    				pauseReleased = true;
    	    }
    	    else if(rTrigger == 0 && XInputDevice.rTriggerReleased){
    	    	if(pausePressed)
    	    		pausePressed = false;
    	    }
    		break;
    	default:
    		break;
    	}
    	switch(cancelKey) {
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_UP)) {
    			cancelPressed = true;
    	    	cancelReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_UP)) {
    			cancelPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_DOWN)) {
    			cancelPressed = true;
    	    	cancelReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_DOWN)) {
    			cancelPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_LEFT)) {
    			cancelPressed = true;
    	    	cancelReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_LEFT)) {
    			cancelPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.DPAD_RIGHT)) {
    			cancelPressed = true;
    	    	cancelReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.DPAD_RIGHT)) {
    			cancelPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_A:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.A)) {
    			cancelPressed = true;
    	    	cancelReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.A)) {
    			cancelPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_B:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.B)) {
    			cancelPressed = true;
    	    	cancelReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.B)) {
    			cancelPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_X:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.X)) {
    			cancelPressed = true;
    	    	cancelReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.X)) {
    			cancelPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_Y:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.Y)) {
    			cancelPressed = true;
    	    	cancelReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.Y)) {
    			cancelPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_BACK:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.BACK)) {
    			cancelPressed = true;
    	    	cancelReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.BACK)) {
    			cancelPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_START:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.START)) {
    			cancelPressed = true;
    	    	cancelReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.START)) {
    			cancelPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.LEFT_SHOULDER)) {
    			cancelPressed = true;
    	    	cancelReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.LEFT_SHOULDER)) {
    			cancelPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.RIGHT_SHOULDER)) {
    			cancelPressed = true;
    	    	cancelReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.RIGHT_SHOULDER)) {
    			cancelPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.LEFT_THUMBSTICK)) {
    			cancelPressed = true;
    	    	cancelReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.LEFT_THUMBSTICK)) {
    			cancelPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.RIGHT_THUMBSTICK)) {
    			cancelPressed = true;
    	    	cancelReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.RIGHT_THUMBSTICK)) {
    			cancelPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.GUIDE_BUTTON)) {
    			cancelPressed = true;
    	    	cancelReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.GUIDE_BUTTON)) {
    			cancelPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    		if(game.getDevice().getDelta().getButtons().isPressed(XInputButton.UNKNOWN)) {
    			cancelPressed = true;
    	    	cancelReleased = true;
    		}
    		if(game.getDevice().getDelta().getButtons().isReleased(XInputButton.UNKNOWN)) {
    			cancelPressed = false;
    		}
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    		if(0.3 < lTrigger) {
    			if(!cancelPressed)
    				cancelPressed = true;
    			if(!cancelReleased)
    				cancelReleased = true;
    	    }
    	    else if(lTrigger == 0 && XInputDevice.lTriggerReleased){
    	    	if(cancelPressed)
    	    		cancelPressed = false;
    	    }
    		break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    		if(0.3 < rTrigger) {
    			if(!cancelPressed)
    				cancelPressed = true;
    			if(!cancelReleased)
    				cancelReleased = true;
    	    }
    	    else if(rTrigger == 0 && XInputDevice.rTriggerReleased){
    	    	if(cancelPressed)
    	    		cancelPressed = false;
    	    }
    		break;
    	default:
    		break;
    	}
    }
}
