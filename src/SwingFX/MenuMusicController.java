/*
 * 
 */
package SwingFX;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuMusicController.
 */
public class MenuMusicController implements Initializable {
	
	/** The volume slider double. */
	private double volumeSliderDouble = 0;
	
	/** The volume slider. */
	@FXML Slider volumeSlider;


	/**
	 * Gets the volume slider double.
	 *
	 * @return the volume slider double
	 */
	public double getVolumeSliderDouble() {
		return volumeSliderDouble;
	}

	/**
	 * Sets the volume slider double.
	 *
	 * @param volumeSliderDouble the new volume slider double
	 */
	public void setVolumeSliderDouble(double volumeSliderDouble) {
		this.volumeSliderDouble = volumeSliderDouble;
	}


	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		volumeSlider.setValue(50);
		volumeSlider.valueProperty().addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable observable) {
				 if (volumeSlider.isValueChanging()) {
					 //System.out.println(volumeSliderDouble);
					 volumeSliderDouble = (volumeSlider.getValue()/10);
				 }
			}
		});
		
	}

}
