/*
 * 
 */
package SwingFX;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.game.src.main.Game;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

// TODO: Auto-generated Javadoc
/**
 * The Class fxmlWindow.
 */
public class fxmlWindow extends JFXPanel {


    /** The pane. */
    private VBox pane;
    
    /** The title. */
    private String title;
    
    /** The primary scene. */
    private static Scene primaryScene;
	
	/**
	 * Instantiates a new fxml window.
	 *
	 * @param title the title
	 * @param fxmlFile the fxml file
	 */
	public fxmlWindow(String title,String fxmlFile) {
		this.title = title;
        init(fxmlFile);
    }
	
	/**
	 * Sets the primary scene.
	 *
	 * @param scene the new primary scene
	 */
	private void setPrimaryScene(Scene scene) {
        fxmlWindow.primaryScene = scene;
    }

    /**
     * Gets the primary scene.
     *
     * @return the primary scene
     */
    static public Scene getPrimaryScene() {
        return fxmlWindow.primaryScene;
    }

    /**
     * Inits the.
     *
     * @param fxmlFile the fxml file
     */
    private void init(String fxmlFile) {
        //setSize(Game.WIDTH * 2, Game.HEIGHT * 2);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
       // loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(SwingFXMLPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        requestFocus();
        pane = loader.getRoot();
        Platform.runLater(this::createScene);
    }

    /**
     * Creates the scene.
     */
    public void createScene() {
        Scene scene = new Scene(pane);
        setScene(scene);
        setPrimaryScene(scene);
    }
}
