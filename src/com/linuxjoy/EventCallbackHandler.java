package com.linuxjoy;

public class EventCallbackHandler implements LinuxJoystickEventCallback {
	public void callback(LinuxJoystick j, LinuxJoystickEvent ev) {
		switch(ev.isAxisChanged()) {
			case 0:
				// handle axis 0 change with j.getAxisState(0)
				System.out.println("DOWN");
				break;
			case 1:
				// handle axis 1 change with j.getAxisState(1)
				System.out.println("UP");
				break;
		}

		switch(ev.isButtonDown()) {
			case 0:
				// handle button 0 press here
				break;
			case 1:
				// handle button 1 press here
				break;
			default:
				System.out.println("PRESSED");
				break;
		}
		
		switch(ev.isButtonUp()) {
			case 1:
				// handle button 1 release here
				break;
		}
	}
}
