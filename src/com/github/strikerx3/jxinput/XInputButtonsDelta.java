package com.github.strikerx3.jxinput;

import com.github.strikerx3.jxinput.enums.XInputButton;
import com.github.strikerx3.jxinput.natives.XInputConstants;

/**
 * Represents the delta (change) of the buttons between two successive polls.
 *
 * @author Ivan "StrikerX3" Oliveira
 */
public class XInputButtonsDelta {
    private final XInputButtons lastButtons;
    private final XInputButtons buttons;
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
    
    protected XInputButtonsDelta(final XInputButtons lastButtons, final XInputButtons buttons) {
        this.lastButtons = lastButtons;
        this.buttons = buttons;
    }

    /**
     * Sorts buttons in the case of button mapping occurring.
     * 
     * @param button: button we're switching.
     * @param xInputConstant: button we're looking for.
     * @param defaultButton: default button case.
     * 
     */
    public short buttonSorter(short button, short xInputConstant, short defaultButton) {
    	if(XInputDevice.a == xInputConstant)
    		return XInputConstants.XINPUT_GAMEPAD_A;
    	else if(XInputDevice.b == xInputConstant)
    		return XInputConstants.XINPUT_GAMEPAD_B;
    	else if(XInputDevice.x == xInputConstant)
    		return XInputConstants.XINPUT_GAMEPAD_X;
    	else if(XInputDevice.y == xInputConstant)
    		return XInputConstants.XINPUT_GAMEPAD_Y;
    	else if(XInputDevice.up == xInputConstant)
    		return XInputConstants.XINPUT_GAMEPAD_DPAD_UP;
    	else if(XInputDevice.down == xInputConstant)
    		return XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN;
    	else if(XInputDevice.left == xInputConstant)
    		return XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT;
    	else if(XInputDevice.right == xInputConstant)
    		return XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT;
    	else if(XInputDevice.lShoulder == xInputConstant)
    		return XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER;
    	else if(XInputDevice.rShoulder == xInputConstant)
    		return XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER;
    	else if(XInputDevice.lThumb == xInputConstant)
    		return XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB;
    	else if(XInputDevice.rThumb == xInputConstant)
    		return XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB;
    	else if(XInputDevice.start == xInputConstant)
    		return XInputConstants.XINPUT_GAMEPAD_START;
    	else if(XInputDevice.back == xInputConstant)
    		return XInputConstants.XINPUT_GAMEPAD_BACK;
    	else if(XInputDevice.guide == xInputConstant)
    		return XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON;
    	else if(XInputDevice.unknown == xInputConstant)
    		return XInputConstants.XINPUT_GAMEPAD_UNKNOWN;
    	else if(XInputDevice.lTrigger == xInputConstant)
    		return XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER;
    	else if(XInputDevice.rTrigger == xInputConstant)
    		return XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER;
    	else
    		return defaultButton;
    }
    
    /**
     * Returns <code>true</code> if the button was pressed (i.e. changed from released to pressed between two consecutive polls).
     * 
     * @param button the button
     * @return <code>true</code> if the button was pressed, <code>false</code> otherwise
     */
    public boolean isPressed(XInputButton button) {//final XInputButton button
    	
    	/*
    	switch(button) {
    	case A:
    		switch(XInputDevice.a) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
				lastButtons.b = lastButtons.x = lastButtons.y = false;
				lastButtons.back = lastButtons.start = lastButtons.lShoulder = lastButtons.rShoulder = false;
				lastButtons.lThumb = lastButtons.rThumb = lastButtons.guide = lastButtons.unknown = false;
				lastButtons.up = lastButtons.down = lastButtons.left = lastButtons.right = false;
				buttons.b = buttons.x = buttons.y = false;
				buttons.back = buttons.start = buttons.lShoulder = buttons.rShoulder = false;
				buttons.lThumb = buttons.rThumb = buttons.guide = buttons.unknown = false;
				buttons.up = buttons.down = buttons.left = buttons.right = false;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
				buttons.a = buttons.b = buttons.x = false;
				buttons.back = buttons.start = buttons.lShoulder = buttons.rShoulder = false;
				buttons.lThumb = buttons.rThumb = buttons.guide = buttons.unknown = false;
				buttons.up = buttons.down = buttons.left = buttons.right = false;
				lastButtons.a = lastButtons.b = lastButtons.x = false;
				lastButtons.back = lastButtons.start = lastButtons.lShoulder = lastButtons.rShoulder = false;
				lastButtons.lThumb = lastButtons.rThumb = lastButtons.guide = lastButtons.unknown = false;
				lastButtons.up = lastButtons.down = lastButtons.left = lastButtons.right = false;
    			break;
    		default:
    			break;
    		}
    		break;
	    case Y:
			switch(XInputDevice.y) {
			case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
				lastButtons.b = lastButtons.x = lastButtons.y = false;
				lastButtons.back = lastButtons.start = lastButtons.lShoulder = lastButtons.rShoulder = false;
				lastButtons.lThumb = lastButtons.rThumb = lastButtons.guide = lastButtons.unknown = false;
				lastButtons.up = lastButtons.down = lastButtons.left = lastButtons.right = false;
				buttons.b = buttons.x = buttons.y = false;
				buttons.back = buttons.start = buttons.lShoulder = buttons.rShoulder = false;
				buttons.lThumb = buttons.rThumb = buttons.guide = buttons.unknown = false;
				buttons.up = buttons.down = buttons.left = buttons.right = false;
				break;
			case XInputConstants.XINPUT_GAMEPAD_Y:
				button = XInputButton.Y;
				buttons.a = buttons.b = buttons.x = false;
				buttons.back = buttons.start = buttons.lShoulder = buttons.rShoulder = false;
				buttons.lThumb = buttons.rThumb = buttons.guide = buttons.unknown = false;
				buttons.up = buttons.down = buttons.left = buttons.right = false;
				lastButtons.a = lastButtons.b = lastButtons.x = false;
				lastButtons.back = lastButtons.start = lastButtons.lShoulder = lastButtons.rShoulder = false;
				lastButtons.lThumb = lastButtons.rThumb = lastButtons.guide = lastButtons.unknown = false;
				lastButtons.up = lastButtons.down = lastButtons.left = lastButtons.right = false;
				break;
			default:
				break;
			}
			break;
		}*/
    	short buttonn = -1;
    	switch(button) {
    	case A:
    		buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_A,XInputDevice.a);
//        	if(XInputDevice.a == XInputConstants.XINPUT_GAMEPAD_A)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_A;
//        	else if(XInputDevice.b == XInputConstants.XINPUT_GAMEPAD_A)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_B;
//        	else if(XInputDevice.x == XInputConstants.XINPUT_GAMEPAD_A)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_X;
//        	else if(XInputDevice.y == XInputConstants.XINPUT_GAMEPAD_A)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_Y;
//        	else
//        		buttonn = XInputDevice.a;
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    			return false;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    			return false;
    		default:
    			break;
    		}
    		break;
    	case B:
    		buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_B,XInputDevice.b);
//        	if(XInputDevice.a == XInputConstants.XINPUT_GAMEPAD_B)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_A;
//        	else if(XInputDevice.b == XInputConstants.XINPUT_GAMEPAD_B)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_B;
//        	else if(XInputDevice.x == XInputConstants.XINPUT_GAMEPAD_B)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_X;
//        	else if(XInputDevice.y == XInputConstants.XINPUT_GAMEPAD_B)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_Y;
//        	else
//        		buttonn = XInputDevice.b;
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    			return false;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    			return false;
    		default:
    			break;
    		}
    		break;
    	case X:
    		buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_X,XInputDevice.x);
//    		if(XInputDevice.a == XInputConstants.XINPUT_GAMEPAD_X)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_A;
//        	else if(XInputDevice.b == XInputConstants.XINPUT_GAMEPAD_X)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_B;
//        	else if(XInputDevice.x == XInputConstants.XINPUT_GAMEPAD_X)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_X;
//        	else if(XInputDevice.y == XInputConstants.XINPUT_GAMEPAD_X)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_Y;
//        	else
//        		buttonn = XInputDevice.x;
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    			return false;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    			return false;
    		default:
    			break;
    		}
    		break;
	    case Y:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_Y,XInputDevice.y);
//	    	if(XInputDevice.a == XInputConstants.XINPUT_GAMEPAD_Y)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_A;
//        	else if(XInputDevice.b == XInputConstants.XINPUT_GAMEPAD_Y)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_B;
//        	else if(XInputDevice.x == XInputConstants.XINPUT_GAMEPAD_Y)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_X;
//        	else if(XInputDevice.y == XInputConstants.XINPUT_GAMEPAD_Y)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_Y;
//        	else
//        		buttonn = XInputDevice.y;
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    			return false;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    			return false;
    		default:
    			break;
    		}
    		break;
	    case DPAD_UP:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_DPAD_UP,XInputDevice.up);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
				System.out.println(button.toString());
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    			return false;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    			return false;
    		default:
    			break;
    		}
    		break;
	    case DPAD_DOWN:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN,XInputDevice.down);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    			return false;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    			return false;
    		default:
    			break;
    		}
    		break;
	    case DPAD_LEFT:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT,XInputDevice.left);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    			return false;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    			return false;
    		default:
    			break;
    		}
    		break;
	    case DPAD_RIGHT:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT,XInputDevice.right);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    			return false;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    			return false;
    		default:
    			break;
    		}
    		break;
	    case LEFT_SHOULDER:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER,XInputDevice.lShoulder);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    			return false;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    			return false;
    		default:
    			break;
    		}
    		break;
	    case RIGHT_SHOULDER:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER,XInputDevice.rShoulder);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    			return false;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    			return false;
    		default:
    			break;
    		}
    		break;
	    case LEFT_THUMBSTICK:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB,XInputDevice.lThumb);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    			return false;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    			return false;
    		default:
    			break;
    		}
    		break;
	    case RIGHT_THUMBSTICK:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB,XInputDevice.rThumb);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    			return false;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    			return false;
    		default:
    			break;
    		}
    		break;
	    case START:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_START,XInputDevice.start);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    			return false;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    			return false;
    		default:
    			break;
    		}
    		break;
	    case BACK:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_BACK,XInputDevice.back);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    			return false;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    			return false;
    		default:
    			break;
    		}
    		break;
	    case GUIDE_BUTTON:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON,XInputDevice.guide);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    			return false;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    			return false;
    		default:
    			break;
    		}
    		break;
	    case UNKNOWN:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_UNKNOWN,XInputDevice.unknown);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    			return false;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    			return false;
    		default:
    			break;
    		}
    		break;
		}
        return delta(lastButtons, buttons, button);
    }

    /**
     * Returns <code>true</code> if the button was released (i.e. changed from pressed to released between two consecutive polls).
     * 
     * @param button the button
     * @return <code>true</code> if the button was released, <code>false</code> otherwise
     */
    public boolean isReleased(XInputButton button) {//final XInputButton button
    	/*
    	switch(button) {
    	case A:
    		switch(XInputDevice.a) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		default:
    			break;
    		}
    		break;
	    case Y:
			switch(XInputDevice.y) {
			case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
				break;
			case XInputConstants.XINPUT_GAMEPAD_Y:
				button = XInputButton.Y;
				break;
			default:
				break;
			}
			break;
		}*/
    	short buttonn = -1;
    	switch(button) {
    	case A:
    		buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_A,XInputDevice.a);
//        	if(XInputDevice.a == XInputConstants.XINPUT_GAMEPAD_A)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_A;
//        	else if(XInputDevice.b == XInputConstants.XINPUT_GAMEPAD_A)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_B;
//        	else if(XInputDevice.x == XInputConstants.XINPUT_GAMEPAD_A)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_X;
//        	else if(XInputDevice.y == XInputConstants.XINPUT_GAMEPAD_A)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_Y;
//        	else
//        		buttonn = XInputDevice.a;
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    		default:
    			break;
    		}
    		break;
    	case B:
    		buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_B,XInputDevice.b);
//        	if(XInputDevice.a == XInputConstants.XINPUT_GAMEPAD_B)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_A;
//        	else if(XInputDevice.b == XInputConstants.XINPUT_GAMEPAD_B)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_B;
//        	else if(XInputDevice.x == XInputConstants.XINPUT_GAMEPAD_B)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_X;
//        	else if(XInputDevice.y == XInputConstants.XINPUT_GAMEPAD_B)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_Y;
//        	else
//        		buttonn = XInputDevice.b;
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    		default:
    			break;
    		}
    		break;
    	case X:
    		buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_X,XInputDevice.x);
//    		if(XInputDevice.a == XInputConstants.XINPUT_GAMEPAD_X)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_A;
//        	else if(XInputDevice.b == XInputConstants.XINPUT_GAMEPAD_X)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_B;
//        	else if(XInputDevice.x == XInputConstants.XINPUT_GAMEPAD_X)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_X;
//        	else if(XInputDevice.y == XInputConstants.XINPUT_GAMEPAD_X)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_Y;
//        	else
//        		buttonn = XInputDevice.x;
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    		default:
    			break;
    		}
    		break;
	    case Y:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_Y,XInputDevice.y);
//	    	if(XInputDevice.a == XInputConstants.XINPUT_GAMEPAD_Y)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_A;
//        	else if(XInputDevice.b == XInputConstants.XINPUT_GAMEPAD_Y)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_B;
//        	else if(XInputDevice.x == XInputConstants.XINPUT_GAMEPAD_Y)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_X;
//        	else if(XInputDevice.y == XInputConstants.XINPUT_GAMEPAD_Y)
//        		buttonn = XInputConstants.XINPUT_GAMEPAD_Y;
//        	else
//        		buttonn = XInputDevice.y;
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    		default:
    			break;
    		}
    		break;
	    case DPAD_UP:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_DPAD_UP,XInputDevice.up);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    		default:
    			break;
    		}
    		break;
	    case DPAD_DOWN:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN,XInputDevice.down);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    		default:
    			break;
    		}
    		break;
	    case DPAD_LEFT:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT,XInputDevice.left);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    		default:
    			break;
    		}
    		break;
	    case DPAD_RIGHT:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT,XInputDevice.right);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    		default:
    			break;
    		}
    		break;
	    case LEFT_SHOULDER:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER,XInputDevice.lShoulder);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    		default:
    			break;
    		}
    		break;
	    case RIGHT_SHOULDER:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER,XInputDevice.rShoulder);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    		default:
    			break;
    		}
    		break;
	    case LEFT_THUMBSTICK:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB,XInputDevice.lThumb);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    		default:
    			break;
    		}
    		break;
	    case RIGHT_THUMBSTICK:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB,XInputDevice.rThumb);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    		default:
    			break;
    		}
    		break;
	    case START:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_START,XInputDevice.start);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    		default:
    			break;
    		}
    		break;
	    case BACK:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_BACK,XInputDevice.back);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    		default:
    			break;
    		}
    		break;
	    case GUIDE_BUTTON:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON,XInputDevice.guide);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    		default:
    			break;
    		}
    		break;
	    case UNKNOWN:
	    	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_UNKNOWN,XInputDevice.unknown);
    		switch(buttonn) {
    		case XInputConstants.XINPUT_GAMEPAD_A:
				button = XInputButton.A;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_B:
				button = XInputButton.B;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_X:
				button = XInputButton.X;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_Y:
    			button = XInputButton.Y;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
				button = XInputButton.DPAD_UP;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
				button = XInputButton.DPAD_DOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
				button = XInputButton.DPAD_LEFT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
    			button = XInputButton.DPAD_RIGHT;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
    			button = XInputButton.LEFT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
    			button = XInputButton.RIGHT_SHOULDER;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
    			button = XInputButton.LEFT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
    			button = XInputButton.RIGHT_THUMBSTICK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_START:
    			button = XInputButton.START;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_BACK:
    			button = XInputButton.BACK;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
    			button = XInputButton.GUIDE_BUTTON;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
    			button = XInputButton.UNKNOWN;
    			break;
    		case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
    			if(!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased)
    				return true;
    		case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
    			if(!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased)
    				return true;
    		default:
    			break;
    		}
    		break;
		}
        return delta(buttons, lastButtons, button);
    }

    /**
     * Determines if the state of a button was changed from one poll to the following poll.
     * 
     * @param from the old state
     * @param to the new state
     * @param button the button
     * @return <code>true</code> if there was a change, <code>false</code> otherwise
     */
    private boolean delta(final XInputButtons from, final XInputButtons to, final XInputButton button) {
    	short buttonn = -1;
        switch (button) {
            case A:
            	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_A,XInputDevice.a);
//            	if(XInputDevice.a == XInputConstants.XINPUT_GAMEPAD_A)
//            		buttonn = XInputConstants.XINPUT_GAMEPAD_A;
//            	else if(XInputDevice.b == XInputConstants.XINPUT_GAMEPAD_A)
//            		buttonn = XInputConstants.XINPUT_GAMEPAD_B;
//            	else if(XInputDevice.x == XInputConstants.XINPUT_GAMEPAD_A)
//            		buttonn = XInputConstants.XINPUT_GAMEPAD_X;
//            	else if(XInputDevice.y == XInputConstants.XINPUT_GAMEPAD_A)
//            		buttonn = XInputConstants.XINPUT_GAMEPAD_Y;
//            	else
//            		buttonn = XInputDevice.a;
            	switch(buttonn) {
            	case XInputConstants.XINPUT_GAMEPAD_A:
                    return !from.a && to.a;
    	    	case XInputConstants.XINPUT_GAMEPAD_B:
                    return !from.b && to.b;
    	    	case XInputConstants.XINPUT_GAMEPAD_X:
//            		if(!from.x && to.x)
//            			System.out.println("HEREX");
                    return !from.x && to.x;
    	    	case XInputConstants.XINPUT_GAMEPAD_Y:
                    return !from.y && to.y;
    	    	case XInputConstants.XINPUT_GAMEPAD_BACK:
                    return !from.back && to.back;
    	    	case XInputConstants.XINPUT_GAMEPAD_START:
                    return !from.start && to.start;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
                    return !from.lShoulder && to.lShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
                    return !from.rShoulder && to.rShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
                    return !from.lThumb && to.lThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
                    return !from.rThumb && to.rThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
                    return !from.guide && to.guide;
    	    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
                    return !from.unknown && to.unknown;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
                    return !from.up && to.up;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
                    return !from.down && to.down;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
                    return !from.left && to.left;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
                    return !from.right && to.right;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
                    return (!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased);
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
                    return (!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased);/*
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER://PROBABLY WRONG
                    return (!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased);
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER://PROBABLY WRONG
                    return (!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased);*/
    	    	default:
    	    		return false;
            	}
            	//return !from.x && to.x;
            case B:
            	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_B,XInputDevice.b);
//            	if(XInputDevice.a == XInputConstants.XINPUT_GAMEPAD_B)
//            		buttonn = XInputConstants.XINPUT_GAMEPAD_A;
//            	else if(XInputDevice.b == XInputConstants.XINPUT_GAMEPAD_B)
//            		buttonn = XInputConstants.XINPUT_GAMEPAD_B;
//            	else if(XInputDevice.x == XInputConstants.XINPUT_GAMEPAD_B)
//            		buttonn = XInputConstants.XINPUT_GAMEPAD_X;
//            	else if(XInputDevice.y == XInputConstants.XINPUT_GAMEPAD_B)
//            		buttonn = XInputConstants.XINPUT_GAMEPAD_Y;
//            	else
//            		buttonn = XInputDevice.b;
            	switch(buttonn) {
            	case XInputConstants.XINPUT_GAMEPAD_A:
                    return !from.a && to.a;
    	    	case XInputConstants.XINPUT_GAMEPAD_B:
                    return !from.b && to.b;
    	    	case XInputConstants.XINPUT_GAMEPAD_X:
                    return !from.x && to.x;
    	    	case XInputConstants.XINPUT_GAMEPAD_Y:
                    return !from.y && to.y;
    	    	case XInputConstants.XINPUT_GAMEPAD_BACK:
                    return !from.back && to.back;
    	    	case XInputConstants.XINPUT_GAMEPAD_START:
                    return !from.start && to.start;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
                    return !from.lShoulder && to.lShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
                    return !from.rShoulder && to.rShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
                    return !from.lThumb && to.lThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
                    return !from.rThumb && to.rThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
                    return !from.guide && to.guide;
    	    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
                    return !from.unknown && to.unknown;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
                    return !from.up && to.up;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
                    return !from.down && to.down;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
                    return !from.left && to.left;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
                    return !from.right && to.right;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
                    return (!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased);
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
                    return (!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased);
    	    	default:
    	    		return false;
            	}
                //return !from.b && to.b;
            case X:
            	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_X,XInputDevice.x);
//            	if(XInputDevice.a == XInputConstants.XINPUT_GAMEPAD_X)
//            		buttonn = XInputConstants.XINPUT_GAMEPAD_A;
//            	else if(XInputDevice.b == XInputConstants.XINPUT_GAMEPAD_X)
//            		buttonn = XInputConstants.XINPUT_GAMEPAD_B;
//            	else if(XInputDevice.x == XInputConstants.XINPUT_GAMEPAD_X)
//            		buttonn = XInputConstants.XINPUT_GAMEPAD_X;
//            	else if(XInputDevice.y == XInputConstants.XINPUT_GAMEPAD_X)
//            		buttonn = XInputConstants.XINPUT_GAMEPAD_Y;
//            	else
//            		buttonn = XInputDevice.x;
            	switch(buttonn) {
            	case XInputConstants.XINPUT_GAMEPAD_A:
                    return !from.a && to.a;
    	    	case XInputConstants.XINPUT_GAMEPAD_B:
                    return !from.b && to.b;
    	    	case XInputConstants.XINPUT_GAMEPAD_X:
                    return !from.x && to.x;
    	    	case XInputConstants.XINPUT_GAMEPAD_Y:
                    return !from.y && to.y;
    	    	case XInputConstants.XINPUT_GAMEPAD_BACK:
                    return !from.back && to.back;
    	    	case XInputConstants.XINPUT_GAMEPAD_START:
                    return !from.start && to.start;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
                    return !from.lShoulder && to.lShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
                    return !from.rShoulder && to.rShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
                    return !from.lThumb && to.lThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
                    return !from.rThumb && to.rThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
                    return !from.guide && to.guide;
    	    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
                    return !from.unknown && to.unknown;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
                    return !from.up && to.up;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
                    return !from.down && to.down;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
                    return !from.left && to.left;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
                    return !from.right && to.right;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
                    return (!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased);
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
                    return (!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased);
    	    	default:
    	    		return false;
            	}
                //return !from.x && to.x;
            case Y:
            	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_Y,XInputDevice.y);
//            	if(XInputDevice.a == XInputConstants.XINPUT_GAMEPAD_Y)
//            		buttonn = XInputConstants.XINPUT_GAMEPAD_A;
//            	else if(XInputDevice.b == XInputConstants.XINPUT_GAMEPAD_Y)
//            		buttonn = XInputConstants.XINPUT_GAMEPAD_B;
//            	else if(XInputDevice.x == XInputConstants.XINPUT_GAMEPAD_Y)
//            		buttonn = XInputConstants.XINPUT_GAMEPAD_X;
//            	else if(XInputDevice.y == XInputConstants.XINPUT_GAMEPAD_Y)
//            		buttonn = XInputConstants.XINPUT_GAMEPAD_Y;
//            	else
//            		buttonn = XInputDevice.y;
            	switch(buttonn) {
            	case XInputConstants.XINPUT_GAMEPAD_A:
                    return !from.a && to.a;
    	    	case XInputConstants.XINPUT_GAMEPAD_B:
                    return !from.b && to.b;
    	    	case XInputConstants.XINPUT_GAMEPAD_X:
                    return !from.x && to.x;
    	    	case XInputConstants.XINPUT_GAMEPAD_Y:
                    return !from.y && to.y;
    	    	case XInputConstants.XINPUT_GAMEPAD_BACK:
                    return !from.back && to.back;
    	    	case XInputConstants.XINPUT_GAMEPAD_START:
                    return !from.start && to.start;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
                    return !from.lShoulder && to.lShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
                    return !from.rShoulder && to.rShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
                    return !from.lThumb && to.lThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
                    return !from.rThumb && to.rThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
                    return !from.guide && to.guide;
    	    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
                    return !from.unknown && to.unknown;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
                    return !from.up && to.up;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
                    return !from.down && to.down;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
                    return !from.left && to.left;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
                    return !from.right && to.right;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
                    return (!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased);
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
                    return (!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased);
    	    	default:
    	    		return false;
            	}
                //return !from.y && to.y;
            case BACK:
            	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_BACK,XInputDevice.back);
            	switch(buttonn) {
            	case XInputConstants.XINPUT_GAMEPAD_A:
                    return !from.a && to.a;
    	    	case XInputConstants.XINPUT_GAMEPAD_B:
                    return !from.b && to.b;
    	    	case XInputConstants.XINPUT_GAMEPAD_X:
                    return !from.x && to.x;
    	    	case XInputConstants.XINPUT_GAMEPAD_Y:
                    return !from.y && to.y;
    	    	case XInputConstants.XINPUT_GAMEPAD_BACK:
                    return !from.back && to.back;
    	    	case XInputConstants.XINPUT_GAMEPAD_START:
                    return !from.start && to.start;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
                    return !from.lShoulder && to.lShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
                    return !from.rShoulder && to.rShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
                    return !from.lThumb && to.lThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
                    return !from.rThumb && to.rThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
                    return !from.guide && to.guide;
    	    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
                    return !from.unknown && to.unknown;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
                    return !from.up && to.up;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
                    return !from.down && to.down;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
                    return !from.left && to.left;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
                    return !from.right && to.right;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
                    return (!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased);
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
                    return (!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased);
    	    	default:
    	    		return false;
            	}
                //return !from.back && to.back;
            case START:
            	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_START,XInputDevice.start);
            	switch(buttonn) {
            	case XInputConstants.XINPUT_GAMEPAD_A:
                    return !from.a && to.a;
    	    	case XInputConstants.XINPUT_GAMEPAD_B:
                    return !from.b && to.b;
    	    	case XInputConstants.XINPUT_GAMEPAD_X:
                    return !from.x && to.x;
    	    	case XInputConstants.XINPUT_GAMEPAD_Y:
                    return !from.y && to.y;
    	    	case XInputConstants.XINPUT_GAMEPAD_BACK:
                    return !from.back && to.back;
    	    	case XInputConstants.XINPUT_GAMEPAD_START:
                    return !from.start && to.start;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
                    return !from.lShoulder && to.lShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
                    return !from.rShoulder && to.rShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
                    return !from.lThumb && to.lThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
                    return !from.rThumb && to.rThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
                    return !from.guide && to.guide;
    	    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
                    return !from.unknown && to.unknown;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
                    return !from.up && to.up;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
                    return !from.down && to.down;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
                    return !from.left && to.left;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
                    return !from.right && to.right;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
                    return (!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased);
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
                    return (!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased);
    	    	default:
    	    		return false;
            	}
                //return !from.start && to.start;
            case LEFT_SHOULDER:
            	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER,XInputDevice.lShoulder);
            	switch(buttonn) {
            	case XInputConstants.XINPUT_GAMEPAD_A:
                    return !from.a && to.a;
    	    	case XInputConstants.XINPUT_GAMEPAD_B:
                    return !from.b && to.b;
    	    	case XInputConstants.XINPUT_GAMEPAD_X:
                    return !from.x && to.x;
    	    	case XInputConstants.XINPUT_GAMEPAD_Y:
                    return !from.y && to.y;
    	    	case XInputConstants.XINPUT_GAMEPAD_BACK:
                    return !from.back && to.back;
    	    	case XInputConstants.XINPUT_GAMEPAD_START:
                    return !from.start && to.start;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
                    return !from.lShoulder && to.lShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
                    return !from.rShoulder && to.rShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
                    return !from.lThumb && to.lThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
                    return !from.rThumb && to.rThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
                    return !from.guide && to.guide;
    	    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
                    return !from.unknown && to.unknown;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
                    return !from.up && to.up;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
                    return !from.down && to.down;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
                    return !from.left && to.left;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
                    return !from.right && to.right;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
                    return (!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased);
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
                    return (!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased);
    	    	default:
    	    		return false;
            	}
               // return !from.lShoulder && to.lShoulder;
            case RIGHT_SHOULDER:
            	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER,XInputDevice.rShoulder);
            	switch(buttonn) {
            	case XInputConstants.XINPUT_GAMEPAD_A:
                    return !from.a && to.a;
    	    	case XInputConstants.XINPUT_GAMEPAD_B:
                    return !from.b && to.b;
    	    	case XInputConstants.XINPUT_GAMEPAD_X:
                    return !from.x && to.x;
    	    	case XInputConstants.XINPUT_GAMEPAD_Y:
                    return !from.y && to.y;
    	    	case XInputConstants.XINPUT_GAMEPAD_BACK:
                    return !from.back && to.back;
    	    	case XInputConstants.XINPUT_GAMEPAD_START:
                    return !from.start && to.start;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
                    return !from.lShoulder && to.lShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
                    return !from.rShoulder && to.rShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
                    return !from.lThumb && to.lThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
                    return !from.rThumb && to.rThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
                    return !from.guide && to.guide;
    	    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
                    return !from.unknown && to.unknown;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
                    return !from.up && to.up;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
                    return !from.down && to.down;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
                    return !from.left && to.left;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
                    return !from.right && to.right;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
                    return (!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased);
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
                    return (!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased);
    	    	default:
    	    		return false;
            	}
                //return !from.rShoulder && to.rShoulder;
            case LEFT_THUMBSTICK:
            	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB,XInputDevice.lThumb);
            	switch(buttonn) {
            	case XInputConstants.XINPUT_GAMEPAD_A:
                    return !from.a && to.a;
    	    	case XInputConstants.XINPUT_GAMEPAD_B:
                    return !from.b && to.b;
    	    	case XInputConstants.XINPUT_GAMEPAD_X:
                    return !from.x && to.x;
    	    	case XInputConstants.XINPUT_GAMEPAD_Y:
                    return !from.y && to.y;
    	    	case XInputConstants.XINPUT_GAMEPAD_BACK:
                    return !from.back && to.back;
    	    	case XInputConstants.XINPUT_GAMEPAD_START:
                    return !from.start && to.start;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
                    return !from.lShoulder && to.lShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
                    return !from.rShoulder && to.rShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
                    return !from.lThumb && to.lThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
                    return !from.rThumb && to.rThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
                    return !from.guide && to.guide;
    	    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
                    return !from.unknown && to.unknown;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
                    return !from.up && to.up;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
                    return !from.down && to.down;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
                    return !from.left && to.left;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
                    return !from.right && to.right;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
                    return (!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased);
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
                    return (!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased);
    	    	default:
    	    		return false;
            	}
                //return !from.lThumb && to.lThumb;
            case RIGHT_THUMBSTICK:
            	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB,XInputDevice.rThumb);
            	switch(buttonn) {
            	case XInputConstants.XINPUT_GAMEPAD_A:
                    return !from.a && to.a;
    	    	case XInputConstants.XINPUT_GAMEPAD_B:
                    return !from.b && to.b;
    	    	case XInputConstants.XINPUT_GAMEPAD_X:
                    return !from.x && to.x;
    	    	case XInputConstants.XINPUT_GAMEPAD_Y:
                    return !from.y && to.y;
    	    	case XInputConstants.XINPUT_GAMEPAD_BACK:
                    return !from.back && to.back;
    	    	case XInputConstants.XINPUT_GAMEPAD_START:
                    return !from.start && to.start;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
                    return !from.lShoulder && to.lShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
                    return !from.rShoulder && to.rShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
                    return !from.lThumb && to.lThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
                    return !from.rThumb && to.rThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
                    return !from.guide && to.guide;
    	    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
                    return !from.unknown && to.unknown;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
                    return !from.up && to.up;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
                    return !from.down && to.down;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
                    return !from.left && to.left;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
                    return !from.right && to.right;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
                    return (!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased);
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
                    return (!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased);
    	    	default:
    	    		return false;
            	}
                //return !from.rThumb && to.rThumb;
            case DPAD_UP:
            	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_DPAD_UP,XInputDevice.up);
            	switch(buttonn) {
            	case XInputConstants.XINPUT_GAMEPAD_A:
                    return !from.a && to.a;
    	    	case XInputConstants.XINPUT_GAMEPAD_B:
                    return !from.b && to.b;
    	    	case XInputConstants.XINPUT_GAMEPAD_X:
                    return !from.x && to.x;
    	    	case XInputConstants.XINPUT_GAMEPAD_Y:
                    return !from.y && to.y;
    	    	case XInputConstants.XINPUT_GAMEPAD_BACK:
                    return !from.back && to.back;
    	    	case XInputConstants.XINPUT_GAMEPAD_START:
                    return !from.start && to.start;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
                    return !from.lShoulder && to.lShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
                    return !from.rShoulder && to.rShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
                    return !from.lThumb && to.lThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
                    return !from.rThumb && to.rThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
                    return !from.guide && to.guide;
    	    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
                    return !from.unknown && to.unknown;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
                    return !from.up && to.up;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
                    return !from.down && to.down;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
                    return !from.left && to.left;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
                    return !from.right && to.right;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
                    return (!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased);
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
                    return (!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased);
    	    	default:
    	    		return false;
            	}
                //return !from.up && to.up;
            case DPAD_DOWN:
            	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN,XInputDevice.down);
            	switch(buttonn) {
            	case XInputConstants.XINPUT_GAMEPAD_A:
                    return !from.a && to.a;
    	    	case XInputConstants.XINPUT_GAMEPAD_B:
                    return !from.b && to.b;
    	    	case XInputConstants.XINPUT_GAMEPAD_X:
                    return !from.x && to.x;
    	    	case XInputConstants.XINPUT_GAMEPAD_Y:
                    return !from.y && to.y;
    	    	case XInputConstants.XINPUT_GAMEPAD_BACK:
                    return !from.back && to.back;
    	    	case XInputConstants.XINPUT_GAMEPAD_START:
                    return !from.start && to.start;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
                    return !from.lShoulder && to.lShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
                    return !from.rShoulder && to.rShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
                    return !from.lThumb && to.lThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
                    return !from.rThumb && to.rThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
                    return !from.guide && to.guide;
    	    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
                    return !from.unknown && to.unknown;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
                    return !from.up && to.up;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
                    return !from.down && to.down;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
                    return !from.left && to.left;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
                    return !from.right && to.right;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
                    return (!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased);
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
                    return (!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased);
    	    	default:
    	    		return false;
            	}
                //return !from.down && to.down;
            case DPAD_LEFT:
            	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT,XInputDevice.left);
            	switch(buttonn) {
            	case XInputConstants.XINPUT_GAMEPAD_A:
                    return !from.a && to.a;
    	    	case XInputConstants.XINPUT_GAMEPAD_B:
                    return !from.b && to.b;
    	    	case XInputConstants.XINPUT_GAMEPAD_X:
                    return !from.x && to.x;
    	    	case XInputConstants.XINPUT_GAMEPAD_Y:
                    return !from.y && to.y;
    	    	case XInputConstants.XINPUT_GAMEPAD_BACK:
                    return !from.back && to.back;
    	    	case XInputConstants.XINPUT_GAMEPAD_START:
                    return !from.start && to.start;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
                    return !from.lShoulder && to.lShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
                    return !from.rShoulder && to.rShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
                    return !from.lThumb && to.lThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
                    return !from.rThumb && to.rThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
                    return !from.guide && to.guide;
    	    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
                    return !from.unknown && to.unknown;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
                    return !from.up && to.up;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
                    return !from.down && to.down;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
                    return !from.left && to.left;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
                    return !from.right && to.right;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
                    return (!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased);
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
                    return (!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased);
    	    	default:
    	    		return false;
            	}
                //return !from.left && to.left;
            case DPAD_RIGHT:
            	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT,XInputDevice.right);
            	switch(buttonn) {
            	case XInputConstants.XINPUT_GAMEPAD_A:
                    return !from.a && to.a;
    	    	case XInputConstants.XINPUT_GAMEPAD_B:
                    return !from.b && to.b;
    	    	case XInputConstants.XINPUT_GAMEPAD_X:
                    return !from.x && to.x;
    	    	case XInputConstants.XINPUT_GAMEPAD_Y:
                    return !from.y && to.y;
    	    	case XInputConstants.XINPUT_GAMEPAD_BACK:
                    return !from.back && to.back;
    	    	case XInputConstants.XINPUT_GAMEPAD_START:
                    return !from.start && to.start;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
                    return !from.lShoulder && to.lShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
                    return !from.rShoulder && to.rShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
                    return !from.lThumb && to.lThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
                    return !from.rThumb && to.rThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
                    return !from.guide && to.guide;
    	    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
                    return !from.unknown && to.unknown;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
                    return !from.up && to.up;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
                    return !from.down && to.down;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
                    return !from.left && to.left;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
                    return !from.right && to.right;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
                    return (!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased);
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
                    return (!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased);
    	    	default:
    	    		return false;
            	}
                //return !from.right && to.right;
            case GUIDE_BUTTON:
            	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON,XInputDevice.guide);
            	switch(buttonn) {
            	case XInputConstants.XINPUT_GAMEPAD_A:
                    return !from.a && to.a;
    	    	case XInputConstants.XINPUT_GAMEPAD_B:
                    return !from.b && to.b;
    	    	case XInputConstants.XINPUT_GAMEPAD_X:
                    return !from.x && to.x;
    	    	case XInputConstants.XINPUT_GAMEPAD_Y:
                    return !from.y && to.y;
    	    	case XInputConstants.XINPUT_GAMEPAD_BACK:
                    return !from.back && to.back;
    	    	case XInputConstants.XINPUT_GAMEPAD_START:
                    return !from.start && to.start;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
                    return !from.lShoulder && to.lShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
                    return !from.rShoulder && to.rShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
                    return !from.lThumb && to.lThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
                    return !from.rThumb && to.rThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
                    return !from.guide && to.guide;
    	    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
                    return !from.unknown && to.unknown;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
                    return !from.up && to.up;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
                    return !from.down && to.down;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
                    return !from.left && to.left;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
                    return !from.right && to.right;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
                    return (!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased);
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
                    return (!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased);
    	    	default:
    	    		return false;
            	}
                //return !from.guide && to.guide;
            case UNKNOWN:
            	buttonn = buttonSorter(buttonn,XInputConstants.XINPUT_GAMEPAD_UNKNOWN,XInputDevice.unknown);
            	switch(buttonn) {
            	case XInputConstants.XINPUT_GAMEPAD_A:
                    return !from.a && to.a;
    	    	case XInputConstants.XINPUT_GAMEPAD_B:
                    return !from.b && to.b;
    	    	case XInputConstants.XINPUT_GAMEPAD_X:
                    return !from.x && to.x;
    	    	case XInputConstants.XINPUT_GAMEPAD_Y:
                    return !from.y && to.y;
    	    	case XInputConstants.XINPUT_GAMEPAD_BACK:
                    return !from.back && to.back;
    	    	case XInputConstants.XINPUT_GAMEPAD_START:
                    return !from.start && to.start;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
                    return !from.lShoulder && to.lShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
                    return !from.rShoulder && to.rShoulder;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
                    return !from.lThumb && to.lThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
                    return !from.rThumb && to.rThumb;
    	    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
                    return !from.guide && to.guide;
    	    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
                    return !from.unknown && to.unknown;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
                    return !from.up && to.up;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
                    return !from.down && to.down;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
                    return !from.left && to.left;
    	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
                    return !from.right && to.right;
    	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_TRIGGER:
                    return (!XInputDevice.lTriggerPressed && XInputDevice.lTriggerReleased);
    	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_TRIGGER:
                    return (!XInputDevice.rTriggerPressed && XInputDevice.rTriggerReleased);
    	    	default:
    	    		return false;
            	}
                //return !from.unknown && to.unknown;
        }
        return false;
    }/*
    public void changeButton(String s, short btn) {
    	short shortTemp = 0;
    	switch(s) {
    		case "a":
        		shortTemp = XInputConstants.XINPUT_GAMEPAD_A;
    			break;
    		case "b":
        		shortTemp = XInputConstants.XINPUT_GAMEPAD_B;
        		break;
        	case "x":
        		shortTemp = XInputConstants.XINPUT_GAMEPAD_X;
        		break;
        	case "y":
        		shortTemp = XInputConstants.XINPUT_GAMEPAD_Y;
        		break;
        	case "back":
        		shortTemp = XInputConstants.XINPUT_GAMEPAD_BACK;
        		break;
        	case "start":
        		shortTemp = XInputConstants.XINPUT_GAMEPAD_START;
        		break;
        	case "lShoulder":
        		shortTemp = XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER;
        		break;
        	case "rShoulder":
        		shortTemp = XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER;
        		break;
        	case "lThumb":
        		shortTemp = XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB;
        		break;
        	case "rThumb":
        		shortTemp = XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB;
        		break;
        	case "guide":
        		shortTemp = XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON;
        		break;
        	case "unknown":
        		shortTemp = XInputConstants.XINPUT_GAMEPAD_UNKNOWN;
        		break;
        	case "up":
        		shortTemp = XInputConstants.XINPUT_GAMEPAD_DPAD_UP;
        		break;
        	case "down":
        		shortTemp = XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN;
        		break;
        	case "left":
        		shortTemp = XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT;
        		break;
        	case "right":
        		shortTemp = XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT;
        		break;
    		default:
    			break;
    	}
    	switch(btn) {
	    	case XInputConstants.XINPUT_GAMEPAD_A:
	    		//XInputDevice.a = shortTemp;
	    		a = shortTemp;
	    		break;
	    	case XInputConstants.XINPUT_GAMEPAD_B:
	    		b = shortTemp;
	    		break;
	    	case XInputConstants.XINPUT_GAMEPAD_X:
	    		x = shortTemp;
	    		break;
	    	case XInputConstants.XINPUT_GAMEPAD_Y:
	    		y = shortTemp;
	    		break;
	    	case XInputConstants.XINPUT_GAMEPAD_BACK:
	    		back = shortTemp;
	    		break;
	    	case XInputConstants.XINPUT_GAMEPAD_START:
	    		start = shortTemp;
	    		break;
	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_SHOULDER:
	    		lShoulder = shortTemp;
	    		break;
	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_SHOULDER:
	    		rShoulder = shortTemp;
	    		break;
	    	case XInputConstants.XINPUT_GAMEPAD_LEFT_THUMB:
	    		lThumb = shortTemp;
	    		break;
	    	case XInputConstants.XINPUT_GAMEPAD_RIGHT_THUMB:
	    		rThumb = shortTemp;
	    		break;
	    	case XInputConstants.XINPUT_GAMEPAD_GUIDE_BUTTON:
	    		guide = shortTemp;
	    		break;
	    	case XInputConstants.XINPUT_GAMEPAD_UNKNOWN:
	    		unknown = shortTemp;
	    		break;
	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_UP:
	    		up = shortTemp;
	    		break;
	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_DOWN:
	    		down = shortTemp;
	    		break;
	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_LEFT:
	    		left = shortTemp;
	    		break;
	    	case XInputConstants.XINPUT_GAMEPAD_DPAD_RIGHT:
	    		right = shortTemp;
	    		break;
    	}
    	
    	
    }*/
}
