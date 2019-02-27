package com.github.strikerx3.jxinput;

import java.nio.ByteBuffer;
import java.util.EnumSet;

import com.github.strikerx3.jxinput.enums.XInputButton;
import com.github.strikerx3.jxinput.enums.XInputDeviceSubType;
import com.github.strikerx3.jxinput.enums.XInputDeviceType;
import com.github.strikerx3.jxinput.natives.XInputConstants;
import com.github.strikerx3.jxinput.natives.XInputNatives;

/**
 * Contains information about the device's capabilities.
 *
 * @author Ivan "StrikerX3" Oliveira
 */
public class XInputCapabilities {
    private final XInputDeviceType type;
    private final XInputDeviceSubType subType;

    private final boolean forceFeedbackSupported;
    private final boolean wireless;
    private final boolean voiceSupported;
    private final boolean pluginModulesSupported;
    private final boolean noNavigation;

    private final EnumSet<XInputButton> supportedButtons = EnumSet.noneOf(XInputButton.class);

    private final XInputCapsResolutions resolutions;

    XInputCapabilities(final ByteBuffer buffer) {
        // typedef struct _XINPUT_CAPABILITIES
        // {
        //     BYTE                                Type;
        //     BYTE                                SubType;
        //     WORD                                Flags;
        //     XINPUT_GAMEPAD                      Gamepad;
        //     XINPUT_VIBRATION                    Vibration;
        // } XINPUT_CAPABILITIES, *PXINPUT_CAPABILITIES;

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

        // typedef struct _XINPUT_VIBRATION
        // {
        //     WORD                                wLeftMotorSpeed;
        //     WORD                                wRightMotorSpeed;
        // } XINPUT_VIBRATION, *PXINPUT_VIBRATION;

        final byte type = buffer.get();
        final byte subType = buffer.get();

        this.type = XInputDeviceType.fromNative(type);
        this.subType = XInputDeviceSubType.fromNative(subType);

        final short flags = buffer.getShort();
        forceFeedbackSupported = (flags & XInputConstants.XINPUT_CAPS_FFB_SUPPORTED) != 0;
        wireless = (flags & XInputConstants.XINPUT_CAPS_WIRELESS) != 0;
        voiceSupported = (flags & XInputConstants.XINPUT_CAPS_VOICE_SUPPORTED) != 0;
        pluginModulesSupported = (flags & XInputConstants.XINPUT_CAPS_PMD_SUPPORTED) != 0;
        noNavigation = (flags & XInputConstants.XINPUT_CAPS_NO_NAVIGATION) != 0;

        final short buttons = buffer.getShort();
        addSupportedButton(buttons, XInputConstants.XINPUT_GAMEPAD_A, XInputButton.A);
        addSupportedButton(buttons, XInputConstants.XINPUT_GAMEPAD_B, XInputButton.B);
        addSupportedButton(buttons, XInputConstants.XINPUT_GAMEPAD_X, XInputButton.X);
        addSupportedButton(buttons, XInputConstants.XINPUT_GAMEPAD_Y, XInputButton.Y);
        addSupportedButton(buttons, XInputConstants.XINPUT_GAMEPAD_BACK, XInputButton.BACK);
        addSupportedButton(buttons, XInputConstants.XINPUT_GAMEPAD_START, XInputButton.START);
        addSupportedButton(buttons, XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER, XInputButton.LEFT_SHOULDER);
        addSupportedButton(buttons, XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER, XInputButton.RIGHT_SHOULDER);
        addSupportedButton(buttons, XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB, XInputButton.LEFT_THUMBSTICK);
        addSupportedButton(buttons, XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB, XInputButton.RIGHT_THUMBSTICK);
        addSupportedButton(buttons, XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON, XInputButton.GUIDE_BUTTON);
        addSupportedButton(buttons, XInputConstants.XINPUT_GAMEPAD_UNKNOWN, XInputButton.UNKNOWN);
        addSupportedButton(buttons, XInputConstants.XINPUT_GAMEPAD_DPAD_UP, XInputButton.DPAD_UP);
        addSupportedButton(buttons, XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN, XInputButton.DPAD_DOWN);
        addSupportedButton(buttons, XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT, XInputButton.DPAD_LEFT);
        addSupportedButton(buttons, XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT, XInputButton.DPAD_RIGHT);

        // add Guide button support manually
        if (XInputNatives.isGuideButtonSupported()) {
            supportedButtons.add(XInputButton.GUIDE_BUTTON);
        }

        resolutions = new XInputCapsResolutions(buffer);
    }

    /**
     * Retrieves the device type.
     *
     * @return an {@link XInputDeviceType} representing the device type
     */
    public XInputDeviceType getType() {
        return type;
    }

    /**
     * Retrieves the device subtype.
     *
     * @return an {@link XInputDeviceSubType} representing the device subtype
     */
    public XInputDeviceSubType getSubType() {
        return subType;
    }

    /**
     * Determines whether this device supports Force Feedback.
     *
     * @return <code>true</code> if the device supports Force Feedback, <code>false</code> otherwise
     */
    public boolean isForceFeedbackSupported() {
        return forceFeedbackSupported;
    }

    /**
     * Determines whether this device is wireless.
     *
     * @return <code>true</code> for wireless devices, <code>false</code> for wired devices
     */
    public boolean isWireless() {
        return wireless;
    }

    /**
     * Determines whether this device has an integrated voice device.
     *
     * @return <code>true</code> if the device has a voice capture device, <code>false</code> otherwise
     */
    public boolean isVoiceSupported() {
        return voiceSupported;
    }

    /**
     * Determines whether this device supports plug-in modules.
     *
     * @return <code>true</code> if the device allows plug-in modules, <code>false</code> otherwise
     */
    public boolean isPluginModulesSupported() {
        return pluginModulesSupported;
    }

    /**
     * Determines whether this device has no navigation buttons (Start, Sack and the directional pad).
     *
     * @return <code>true</code> if the device does not have the navigation buttons, <code>false</code> otherwise
     */
    public boolean isNoNavigation() {
        return noNavigation;
    }

    /**
     * Retrieves the buttons supported by the device.
     *
     * @return a set of buttons supported by the device
     */
    public EnumSet<XInputButton> getSupportedButtons() {
        return supportedButtons;
    }

    /**
     * Retrieves the resolutions of the proportional axes of the device.
     *
     * @return the resolutions of the proportional axes of the device
     */
    public XInputCapsResolutions getResolutions() {
        return resolutions;
    }

    private void addSupportedButton(final short buttons, final short bit, final XInputButton button) {
        if ((buttons & bit) == bit) {
            supportedButtons.add(button);
        }
    }
}
