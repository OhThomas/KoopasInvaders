/*
 * 
 */
package SwingFX;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javafx.application.Platform;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

// TODO: Auto-generated Javadoc
/**
 * The Class InteropFrame.
 */
public class InteropFrame extends JFrame {

   
    /** The central split pane. */
    private JSplitPane centralSplitPane;
    
    /** The swing panel. */
    private SwingPanel swingPanel;
    
    /** The swing FX panel. */
    private SwingFXMLPanel swingFXPanel;
    
    /** The fxml file. */
    private String fxmlFile = "demo.fxml";

    /**
     * Instantiates a new interop frame.
     */
    public InteropFrame() {
        init();
    }

    /**
     * Inits the frame.
     */
    private void init() {
        setTitle("Swing <-> JavaFX Interoperatbility");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        centralSplitPane = new JSplitPane();
        swingPanel = new SwingPanel();
        swingFXPanel = new SwingFXMLPanel(fxmlFile);
        swingPanel.getTestButton().addActionListener((ActionEvent e) -> {
            Platform.runLater(() -> {
                swingFXPanel.getTestLabel().setText(swingPanel.getTestTextField().getText());
            });
        });
        swingFXPanel.getTestButton().setOnAction(a -> {
            swingPanel.getTestLabel().setText(swingFXPanel.getTestTextField().getText());
        });
        centralSplitPane.setLeftComponent(swingPanel);
        centralSplitPane.setRightComponent(swingFXPanel);
        add(centralSplitPane, BorderLayout.CENTER);
    }
    
    /**
     * Gets the fxml file.
     *
     * @return the fxml file
     */
    public String getFxmlFile() {
    	return this.fxmlFile;
    }
    
    /**
     * Sets the fxml file.
     *
     * @param fxmlFile the new fxml file
     */
    public void setFxmlFile(String fxmlFile) {
    	this.fxmlFile = fxmlFile;
    }

}