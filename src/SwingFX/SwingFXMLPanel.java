/*
 * 
 */
package SwingFX;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

// TODO: Auto-generated Javadoc
/**
 * The Class SwingFXMLPanel.
 */
public class SwingFXMLPanel extends JFXPanel {

    /** The test button. */
    @FXML
    private Button testButton;
    
    /** The test text field. */
    @FXML
    private TextField testTextField;
    
    /** The test label. */
    @FXML
    private Label testLabel;
    
    /** The pane. */
    private VBox pane;

	/**
	 * Instantiates a new swing FXML panel.
	 *
	 * @param fxmlFile the fxml file
	 */
	public SwingFXMLPanel(String fxmlFile) {
        init(fxmlFile);
    }

    /**
     * Inits the.
     *
     * @param fxmlFile the fxml file
     */
    private void init(String fxmlFile) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(SwingFXMLPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        pane = loader.getRoot();
        Platform.runLater(this::createScene);
    }

    /**
     * Creates the scene.
     */
    public void createScene() {
        Scene scene = new Scene(pane);
        setScene(scene);
    }

    /**
     * Creates the image.
     *
     * @param panel the panel
     * @return the buffered image
     */
    public BufferedImage createImage(JFrame panel) {

        int w = panel.getWidth();
        int h = panel.getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panel.paint(g);
        g.dispose();
        return bi;
    }
    
    /**
     * Gets the test button.
     *
     * @return the test button
     */
    public Button getTestButton() {
        return testButton;
    }

    /**
     * Gets the test text field.
     *
     * @return the test text field
     */
    public TextField getTestTextField() {
        return testTextField;
    }

    /**
     * Gets the test label.
     *
     * @return the test label
     */
    public Label getTestLabel() {
        return testLabel;
    }

}