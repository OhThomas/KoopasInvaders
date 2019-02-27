package com.github.strikerx3.jxinput;

import java.nio.ByteBuffer;

import com.github.strikerx3.jxinput.enums.XInputBatteryDeviceType;
import com.github.strikerx3.jxinput.exceptions.XInputNotLoadedException;
import com.github.strikerx3.jxinput.natives.XInputConstants;
import com.github.strikerx3.jxinput.natives.XInputNatives14;

/**
 * Provides extended functionality available on XInput 1.4.
 * <p>
 * As with {@code XInputDevice}, certain methods in this class are not thread-safe.
 * It is not recommended to share an instance of {@code XInputDevice14} across multiple threads.
 *
 * @author Ivan "StrikerX3" Oliveira
 */
public class XInputDevice14 extends XInputDevice {
    private final ByteBuffer capsBuffer; // Contains the XINPUT_CAPABILITIES struct
    private final ByteBuffer battBuffer; // Contains the XINPUT_BATTERY_INFORMATION struct
    private final ByteBuffer keysBuffer; // Contains the XINPUT_KEYSTROKE struct

    private static final XInputDevice14[] DEVICES;

    static {
        XInputDevice14[] devices;
        if (XInputNatives14.isLoaded()) {
            devices = new XInputDevice14[XInputConstants.MAX_PLAYERS];
            for (int i = 0; i < XInputConstants.MAX_PLAYERS; i++) {
                devices[i] = new XInputDevice14(i);
            }
        } else {
            devices = null;
        }
        DEVICES = devices;
    }

    protected XInputDevice14(final int playerNum) {
        super(playerNum);

        capsBuffer = newBuffer(20); // sizeof(XINPUT_CAPABILITIES)
        battBuffer = newBuffer(2); // sizeof(XINPUT_BATTERY_INFORMATION)
        keysBuffer = newBuffer(8); // sizeof(XINPUT_KEYSTROKE)
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
     * Returns an array containing all registered XInput devices.
     * <p>
     * The XInputDevice14 objects are not thread-safe.
     *
     * @return all XInput devices
     * @throws XInputNotLoadedException if the native library failed to load
     */
    public static XInputDevice14[] getAllDevices() throws XInputNotLoadedException {
        checkLibraryReady();
        return DEVICES.clone();
    }

    /**
     * Returns the XInput device for the specified player.
     * <p>
     * The returned object should not be shared among multiple threads.
     *
     * @param playerNum the player number
     * @return the XInput device for the specified player
     * @throws XInputNotLoadedException if the native library failed to load
     */
    public static XInputDevice14 getDeviceFor(final int playerNum) throws XInputNotLoadedException {
        checkLibraryReady();
        if (playerNum < 0 || playerNum >= XInputConstants.MAX_PLAYERS) {
            throw new IllegalArgumentException("Invalid player number: " + playerNum + ". Must be between 0 and " + (XInputConstants.MAX_PLAYERS - 1));
        }
        return DEVICES[playerNum];
    }

    /**
     * Enables or disables the reporting state of XInput. Disabling this will cause XInput to send neutral data in response
     * to polls and not send vibration to the device. This is meant to be used when the application loses focus so as to
     * prevent the application from reading data while it is in the background.
     *
     * @param enabled <code>true</code> to enable XInput to send and receive data to/from the device, <code>false</code> to
     * force it to return neutral data and prevent sending data to the device
     */
    public static void setEnabled(final boolean enabled) {
        XInputNatives14.setEnabled(enabled);
    }

    /**
     * Retrieves the capabilities of the device.
     * <p>
     * This method is not thread-safe.
     *
     * @return the device's capabilities, or <code>null</code> if the device is not connected
     * @throws IllegalStateException if there is an error trying to read the device state
     */
    public XInputCapabilities getCapabilities() {
        return getCapabilities(0);
    }

    /**
     * Retrieves the capabilities of the gamepad.
     * <p>
     * This method is not thread-safe.
     *
     * @return the gamepad's capabilities, or <code>null</code> if the device is not connected
     * @throws IllegalStateException if there is an error trying to read the device state
     */
    public XInputCapabilities getGamepadCapabilities() {
        return getCapabilities(XInputConstants.XINPUT_FLAG_GAMEPAD);
    }

    private XInputCapabilities getCapabilities(final int flags) {
        if (!checkReturnCode(XInputNatives14.getCapabilities(playerNum, flags, capsBuffer))) {
            return null;
        }

        final XInputCapabilities caps = new XInputCapabilities(capsBuffer);
        capsBuffer.flip();
        return caps;
    }

    /**
     * Retrieves the device's battery information.
     * <p>
     * This method is not thread-safe.
     *
     * @param deviceType the type of device to obtain battery information from
     * @return an {@link XInputBatteryInformation} or {@code null} if the device is not connected
     * @throws IllegalStateException if there is an error trying to read the device state
     */
    public XInputBatteryInformation getBatteryInformation(final XInputBatteryDeviceType deviceType) {
        if (!checkReturnCode(XInputNatives14.getBatteryInformation(playerNum, 0, battBuffer))) {
            return null;
        }

        final XInputBatteryInformation battInfo = new XInputBatteryInformation(battBuffer);
        battBuffer.flip();
        return battInfo;
    }

    /**
     * Retrieves the next keystroke from this device, or <code>null</code> if there are no more keystrokes or the device is
     * not connected.
     * <p>
     * This method is not thread-safe.
     *
     * @return the next keystroke, or null if the device is not connected or there was no keystroke
     */
    public XInputKeystroke getKeystroke() {
        final int ret = XInputNatives14.getKeystroke(playerNum, keysBuffer);
        if (!checkReturnCode(ret, XInputConstants.ERROR_EMPTY) && ret == XInputConstants.ERROR_EMPTY) {
            return null;
        }

        final XInputKeystroke keystroke = new XInputKeystroke(keysBuffer);
        keysBuffer.flip();
        return keystroke;
    }

    /**
     * Checks if the native library is loaded and ready for use.
     *
     * @throws XInputNotLoadedException if the native library is not loaded
     */
    private static void checkLibraryReady() throws XInputNotLoadedException {
        if (!XInputNatives14.isLoaded()) {
            throw new XInputNotLoadedException("Native library failed to load", XInputNatives14.getLoadError());
        }
    }
}
