/*
 * 
 */
package SwingFX;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;

// TODO: Auto-generated Javadoc
/**
 * The Class LeaderboardController.
 */
public class LeaderboardController extends TextArea implements EventHandler {

	/** The text. */
	private static String text;
	
	/** The text area. */
	private TextArea textArea;
	
	/** The names. */
	private ArrayList<String> names = new ArrayList<String>();
	
	/** The scores. */
	private ArrayList<String> scores = new ArrayList<String>();
	
	/** The string decoy. */
	private String stringDecoy;
	
	/** The name decoy. */
	private String nameDecoy = "";
	
	/** The score decoy. */
	private String scoreDecoy = "";
	
	/**
	 * Sets the texttt.
	 *
	 * @param text the new texttt
	 */
	public static void setTexttt(String text) {
        LeaderboardController.text = text;
    }

    /**
     * Gets the texttt.
     *
     * @return the texttt
     */
    static public String getTexttt() {
        return LeaderboardController.text;
    }
    
    /**
     * Setup text.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void setupText() throws IOException {
		File file = new File("Leaderboard.txt");
		if(!file.exists())
			file.createNewFile();
		Scanner input = new Scanner(file);
		while(input.hasNextLine()) {
			stringDecoy = input.nextLine();
			for(int i = 0; i < stringDecoy.length(); i++) {
				if(!nameDecoy.contains(": "))
					nameDecoy += stringDecoy.charAt(i);
				else
					scoreDecoy += stringDecoy.charAt(i);
			}
			names.add(nameDecoy);
			scores.add(scoreDecoy);
			nameDecoy = "";
			scoreDecoy = "";
		}
    }
    
    /**
     * Sort text.
     */
    public void sortText() {
    	boolean swapped = true;
        while(swapped) {
            swapped = false;
            for(int i = 0; i < scores.size() - 1; i++) {
                if(Integer.valueOf(scores.get(i)) > Integer.valueOf(scores.get(i + 1))) {
                    String temp = scores.get(i);
                    scores.set(i, scores.get(i + 1));
                    scores.set(i + 1, temp);
                    String tempName = names.get(i);
                    names.set(i, names.get(i + 1));
                    names.set(i + 1, tempName);
                    swapped = true;
                }
            }
        }
    }
    
    /**
     * Display text.
     */
    public void displayText() {
    	for(int i = scores.size() - 1; i >= 0; i --) {
    		System.out.println(names.get(i) + scores.get(i) + "\n");
    		//textArea.setText(names.get(i) + scores.get(i) + "\n");
    	}
    }
    
	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(Event arg0) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Initialize.
	 */
	public void initialize() {
		try {
			setupText();
			sortText();
			displayText();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
