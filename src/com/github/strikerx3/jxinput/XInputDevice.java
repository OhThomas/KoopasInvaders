package com.github.strikerx3.jxinput;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.LinkedList;
import java.util.List;

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
    public static boolean upHeld = false;
    public static boolean downHeld = false;
    public static boolean leftHeld = false;
    public static boolean rightHeld = false;
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
            return false;
        }
        setConnected(true);

        lastComponents.copy(components);

        stateReader.read(buffer, components);

        processDelta();
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
	    	default:
	    		break;
    	}
    	defaultControls = false;
    }
}
