package net.java.games.input;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.game.src.main.Game;
import com.game.src.main.LeaderboardController;

import net.java.games.input.example.ReadAllEvents;

public class SwitchedValues {
//	public static List<DIDeviceObject> deviceObject = new ArrayList<>();
	public static String upKey = "Button 0";
	public static String downKey = "Button 1";
	public static String leftKey = "Button 2";
	public static String rightKey = "Button 3";
	public static String shootKey = "Button 4";
	public static String itemKey = "Button 5";
	public static String pauseKey = "Button 6";
	public static String cancelKey = "Button 7";
	
	public static String switchValue(String key) {
		if(upKey.equals(key)) {
			return upKey;
		}
		else if(downKey.equals(key)) {
			return downKey;
		}
		else if(leftKey.equals(key)) {
			return leftKey;
		}
		else if(rightKey.equals(key)) {
			return rightKey;
		}
		else if(shootKey.equals(key)) {
			return shootKey;
		}
		else if(itemKey.equals(key)) {
			return itemKey;
		}
		else if(pauseKey.equals(key)) {
			return pauseKey;
		}
		else if(cancelKey.equals(key)) {
			return cancelKey;
		}
		else {
			return key;
		}
	}
	
	public static void switchValue(IDirectInputDevice device, DIDeviceObject object, DIComponent component,
			int event_value) {
		for(int i = 0; i < IDirectInput.devices.size(); i++) {
			if(device == IDirectInput.devices.get(i)) {
				
			}
		}
	}
	
	public static void resetControls() {

		Event event;
		Controller[] controllers = ControllerEnvironment
				.getDefaultEnvironment().getControllers();
		if (controllers.length == 0) {
//			System.out.println("Found no controllers.");
			return;
//			System.exit(0);
		}
		for (int i = 0; i < controllers.length; i++) {
			/* Get the available controllers */
			/* Remember to poll each one */
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
//				if(ReadAllEvents.controllerName != controllers[i].getName()) {
				if(!ReadAllEvents.controllerName.equals(controllers[i].getName())) {
					ReadAllEvents.controllerName = controllers[i].getName();
	    			SwitchedValues.setUpValues(controllers[i].getName());
	    		}
			}
		}
	}

	public static void resetControlsfromResetButton() {
		Controller[] controllers = ControllerEnvironment
				.getDefaultEnvironment().getControllers();
		if (controllers.length == 0) {
//			System.out.println("Found no controllers.");
			return;
//				System.exit(0);
		}
		
		for (int i = 0; i < controllers.length; i++) {
//    		if(ReadAllEvents.controllerName != controllers[i].getName()) 
			if(!ReadAllEvents.controllerName.equals(controllers[i].getName())) 
    			continue;
			SwitchedValues.setUpValues(controllers[i].getName());
			ReadAllEvents.reset();
			Game.settingsSetup = false;
			return;
			//SWITCH pressDI pics and all pics
		}
	}
	public static void setUpValues(String name) {
		//if name == directInputName stored in memory, load from there; otherwise
		if(name.contains("GC/N64")) {
			upKey = "Button 6";
			downKey = "Button 7";
			leftKey = "Button 8";
			rightKey = "Button 9";
			shootKey = "Button 0";
			itemKey = "Button 1";
			pauseKey = "Button 3";
			cancelKey = "Button 2";
			return;
		}
		else if(name.equals("Wireless Controller")) {
			upKey = "Hat Switch1";
			rightKey = "Hat Switch3";
			downKey = "Hat Switch5";
			leftKey = "Hat Switch7";
			shootKey = "Button 1";
			cancelKey = "Button 2";
			itemKey = "Button 0";
			pauseKey = "Button 9";
			return;
		}
		else {//any other case
			for(int i = 0; i < IDirectInput.devices.size(); i++) {
				if(IDirectInput.devices.get(i).getProductName().equals(name)){
					for(int j = 0; j < IDirectInput.devices.get(i).getObjects().size(); j++) {
						if(IDirectInput.devices.get(i).getObjects().get(j).getName().equals("Hat Switch")) {
							upKey = "Hat Switch1";
							rightKey = "Hat Switch3";
							downKey = "Hat Switch5";
							leftKey = "Hat Switch7";
							shootKey = "Button 1";
							cancelKey = "Button 2";
							itemKey = "Button 0";
							pauseKey = "Button 9";
							return;
						}
						else if(j == IDirectInput.devices.get(i).getObjects().size()-1) {
							upKey = "Button 0";
							downKey = "Button 1";
							leftKey = "Button 2";
							rightKey = "Button 3";
							shootKey = "Button 4";
							itemKey = "Button 5";
							pauseKey = "Button 6";
							cancelKey = "Button 7";
						}
					}
				}
			}
		}
		try {
			LeaderboardController.gameUnlocksToSettings();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
