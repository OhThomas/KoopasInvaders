package com.github.strikerx3.jxinput;

import com.github.strikerx3.jxinput.natives.XInputConstants;

/**
 * Contains the states of all XInput buttons.
 *
 * @author Ivan "StrikerX3" Oliveira
 */
public class XInputButtons {
    public boolean a, b, x, y;
    public boolean back, start;
    public boolean lShoulder, rShoulder;
    public boolean lThumb, rThumb;
    public boolean up, down, left, right;
    public boolean guide, unknown;

    protected XInputButtons() {
        reset();
    }

    /**
     * Resets the state of all buttons.
     */
    protected void reset() {
        a = b = x = y = false;
        back = start = false;
        lShoulder = rShoulder = false;
        lThumb = rThumb = false;
        up = down = left = right = false;
        guide = unknown = false;
    }

    /**
     * Copies the state of all buttons from the specified state.
     *
     * @param buttons the state to copy from
     */
    protected void copy(final XInputButtons buttons) {
        a = buttons.a;
        b = buttons.b;
        x = buttons.x;
        y = buttons.y;

        back = buttons.back;
        start = buttons.start;

        lShoulder = buttons.lShoulder;
        rShoulder = buttons.rShoulder;

        lThumb = buttons.lThumb;
        rThumb = buttons.rThumb;
        /*
        switch(XInputDevice.a) {
    	case XInputConstants.XINPUT_GAMEPAD_A:
    		a = buttons.a;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_B:
    		a = buttons.b;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_X:
    		a = buttons.x;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_Y:
    		a = buttons.y;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_BACK:
    		a = buttons.back;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_START:
    		a = buttons.start;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    		a = buttons.lShoulder;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    		a = buttons.rShoulder;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    		a = buttons.lThumb;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    		a = buttons.rThumb;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    		a = buttons.guide;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    		a = buttons.unknown;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
    		a = buttons.up;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
    		a = buttons.down;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
    		a = buttons.left;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    		a = buttons.right;
            break;
    	default:
    		a = buttons.a;
    		break;
	}
        switch(XInputDevice.x) {
    	case XInputConstants.XINPUT_GAMEPAD_A:
    		x = buttons.a;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_B:
    		x = buttons.b;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_X:
    		x = buttons.x;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_Y:
    		x = buttons.y;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_BACK:
    		x = buttons.back;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_START:
    		x = buttons.start;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    		x = buttons.lShoulder;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    		x = buttons.rShoulder;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    		x = buttons.lThumb;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    		x = buttons.rThumb;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    		x = buttons.guide;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    		x = buttons.unknown;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
    		x = buttons.up;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
    		x = buttons.down;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
    		x = buttons.left;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    		x = buttons.right;
            break;
    	default:
    		x = buttons.x;
    		break;
        }
        switch(XInputDevice.y) {
    	case XInputConstants.XINPUT_GAMEPAD_A:
    		y = buttons.a;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_B:
    		y = buttons.b;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_X:
    		y = buttons.x;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_Y:
    		y = buttons.y;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_BACK:
    		y = buttons.back;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_START:
    		y = buttons.start;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    		y = buttons.lShoulder;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    		y = buttons.rShoulder;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    		y = buttons.lThumb;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    		y = buttons.rThumb;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    		y = buttons.guide;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    		y = buttons.unknown;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
    		y = buttons.up;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
    		y = buttons.down;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
    		y = buttons.left;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    		y = buttons.right;
            break;
    	default:
    		y = buttons.y;
    		break;
        }
        switch(XInputDevice.up) {
    	case XInputConstants.XINPUT_GAMEPAD_A:
    		up = buttons.a;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_B:
    		up = buttons.b;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_X:
    		up = buttons.x;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_Y:
    		up = buttons.y;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_BACK:
    		up = buttons.back;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_START:
    		up = buttons.start;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    		up = buttons.lShoulder;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    		up = buttons.rShoulder;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    		up = buttons.lThumb;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    		up = buttons.rThumb;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    		up = buttons.guide;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    		up = buttons.unknown;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
    		up = buttons.up;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
    		up = buttons.down;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
    		up = buttons.left;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    		up = buttons.right;
            break;
    	default:
    		up = buttons.up;
    		break;
	}
        switch(XInputDevice.down) {
    	case XInputConstants.XINPUT_GAMEPAD_A:
    		down = buttons.a;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_B:
    		down = buttons.b;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_X:
    		down = buttons.x;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_Y:
    		down = buttons.y;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_BACK:
    		down = buttons.back;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_START:
    		down = buttons.start;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    		down = buttons.lShoulder;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    		down = buttons.rShoulder;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    		down = buttons.lThumb;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    		down = buttons.rThumb;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    		down = buttons.guide;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    		down = buttons.unknown;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
    		down = buttons.up;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
    		down = buttons.down;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
    		down = buttons.left;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    		down = buttons.right;
            break;
    	default:
    		down = buttons.down;
    		break;
	}*/
        up = buttons.up;
        down = buttons.down;
        left = buttons.left;
        right = buttons.right;

        guide = buttons.guide;
        unknown = buttons.unknown;
        /*
         * 
        switch(XInputDevice.up) {
    	case XInputConstants.XINPUT_GAMEPAD_A:
    		up = buttons.a;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_B:
    		up = buttons.b;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_X:
    		up = buttons.x;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_Y:
    		up = buttons.y;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_BACK:
    		up = buttons.back;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_START:
    		up = buttons.start;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    		up = buttons.lShoulder;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    		up = buttons.rShoulder;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    		up = buttons.lThumb;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    		up = buttons.rThumb;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    		up = buttons.guide;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    		up = buttons.unknown;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
    		up = buttons.up;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
    		up = buttons.down;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
    		up = buttons.left;
            break;
    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    		up = buttons.right;
            break;
    	default:
    		up = buttons.up;
    		break;
	}
         */
    }
}
