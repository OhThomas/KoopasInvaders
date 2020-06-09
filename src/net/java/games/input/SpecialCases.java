package net.java.games.input;

public class SpecialCases {
	public static boolean specialCases(String deviceName, String componentName) {
		//SPECIAL CASES
		//!(controllers[i].getName().equals("Wireless Controller") && (comp.getName().equals("Button 7") || comp.getName().equals("Button 6") checks for DS4 L/R shoulder buttons
		if(deviceName.equals("Wireless Controller") && (componentName.equals("Button 7") || componentName.equals("Button 6")))
			return false;
		return true;
	}
	public static String buttonName(String deviceName, String componentName) {
		//DS4
		if(deviceName.equals("Wireless Controller")) {
			switch(componentName) {
			case "Button 0":
				return "Button Square";
			case "Button 1":
				return "Button X";
			case "Button 2":
				return "Button Circle";
			case "Button 3":
				return "Button Triangle";
			case "Button 4":
				return "Button L1";
			case "Button 5":
				return "Button R1";
			case "Button 6":
				return "Button L2";
			case "Button 7":
				return "Button R2";
			case "Button 8":
				return "Button Select";
			case "Button 9":
				return "Button Start";
			case "Button 10":
				return "Button L3";
			case "Button 11":
				return "Button R3";
			case "Hat Switch7":
				return "Button Left";
			case "Hat Switch5":
				return "Button Down";
			case "Hat Switch3":
				return "Button Right";
			case "Hat Switch1":
				return "Button Up";
			default:
				break;
			}
		}
		return componentName;
	}
}
