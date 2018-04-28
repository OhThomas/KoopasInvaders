/*
 * 
 */
package SwingFX;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

// TODO: Auto-generated Javadoc
/**
 * The Class ScoreScreenController.
 */
public class ScoreScreenController extends TextField implements EventHandler {

	/** The score. */
	private String score;
	
	/** The previous file string. */
	private String previousFileString;
	
	/** The previous file stringg. */
	private String previousFileStringg;
	/** The button used to submit score. */
	@FXML Button button;
	/** The string from the  txt field. */
	@FXML String string;
	/** The name txt field. */
	@FXML private TextField nameTxtField;

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(Event event) {
		string = nameTxtField.getText();
	}
	
	/**
	 * Button pressed.
	 *
	 * @param event the event
	 */
	public void buttonPressed(ActionEvent event) {	

		string = nameTxtField.getText();
		try {
			File score = new File("Score.txt");
			File file = new File("Leaderboard.txt");
			File filee = new File("NewLeaderboard.txt");
			if(file.exists() && filee.exists()) {
				Scanner scoreInput = new Scanner(score);
				while(scoreInput.hasNextLine()) {
					this.score = scoreInput.nextLine();
				}
				scoreInput.close();
				Scanner input = new Scanner(file);
				BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filee));
				while (input.hasNextLine()) {
				    //previousFileString += "\n";
				    previousFileString = input.nextLine();
				    fileWriter.write(previousFileString);
				    fileWriter.newLine();
				}
				input.close();
				if(previousFileString == null) {
					if(string == null)
						fileWriter.write("Nobody: " + this.score);
					else
						fileWriter.write(string.trim() + ": " + this.score);
				}
				else {
					if(string == null)
						fileWriter.write("Nobody: " + this.score);
					else
						fileWriter.write(string.trim() + ": " + this.score);
				}
				fileWriter.flush();
				fileWriter.close();
	
				Scanner inputt = new Scanner(filee);
				BufferedWriter fileWriterr = new BufferedWriter(new FileWriter(file));
				while (inputt.hasNextLine()) {
				    previousFileStringg = inputt.nextLine();
				    fileWriterr.write(previousFileStringg);
				    fileWriterr.newLine();
				}
				inputt.close();
				fileWriterr.flush();
				fileWriterr.close();
			}
			else {
				file.createNewFile();
				filee.createNewFile();

				Scanner scoreInput = new Scanner(score);
				while(scoreInput.hasNextLine()) {
					this.score = scoreInput.nextLine();
				}
				scoreInput.close();
				BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filee));
				BufferedWriter fileWriterr = new BufferedWriter(new FileWriter(file));

				if(string == null) {
					fileWriter.write("Nobody: " + this.score);
					fileWriterr.write("Nobody: " + this.score);
				}
				else {
					fileWriter.write(string.trim() + ": " + this.score);
					fileWriterr.write(string.trim() + ": " + this.score);
				}

				fileWriter.flush();
				fileWriter.close();
				fileWriterr.flush();
				fileWriterr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}