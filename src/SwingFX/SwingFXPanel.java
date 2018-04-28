/*
 * 
 */
package SwingFX;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

// TODO: Auto-generated Javadoc
/**
 * The Class SwingFXPanel.
 */
public class SwingFXPanel extends JFXPanel {

    /** The test button. */
    private Button testButton;
    
    /** The test text field. */
    private TextField testTextField;
    
    /** The test label. */
    private Label testLabel;
    
    /** The pane. */
    private VBox pane;

    /**
     * Instantiates a new swing FX panel.
     */
    public SwingFXPanel() {
        init();
    }

    /**
     * Inits the.
     */
    private void init() {
        testButton = new Button("I am a JavaFX Button");
        testTextField = new TextField();
        testLabel = new Label("empty");
        pane = new VBox(testTextField, testButton, testLabel);
        pane.setAlignment(Pos.CENTER);
//        Platform.runLater(this::createScene);
        createScene();
    }

    /**
     * Creates the scene.
     */
    public void createScene() {
        Scene scene = new Scene(pane);
        setScene(scene);
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