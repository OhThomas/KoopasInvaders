/*
 * 
 */
package SwingFX;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

import com.game.src.main.Game;

// TODO: Auto-generated Javadoc
/**
 * The Class InteropSharedModelFrame.
 */
public class InteropSharedModelFrame extends JFrame {

    /** The central split pane. */
    private JSplitPane centralSplitPane;
    
    /** The swing panel. */
    private SwingPanel swingPanel;
    
    /** The swing FX panel. */
    private SwingFXPanel swingFXPanel;
	
	/** The swing FXML panel. */
	private SwingFXMLPanel swingFXMLPanel;
    
    /** The fxml file. */
    private String fxmlFile = "demo.fxml";
    
    /** The data model. */
    private DataModel dataModel;

    /**
     * Instantiates a new interop shared model frame.
     *
     * @param title the title
     * @param fxmlFile the fxml file
     */
    public InteropSharedModelFrame(String title, String fxmlFile) {
    	this.fxmlFile = fxmlFile;
        init(title);
    }

    /**
     * Inits the.
     *
     * @param title the title
     */
    private void init(String title) {
        setTitle(title);
        setSize(Game.WIDTH * 2, Game.HEIGHT * 2);
        this.setLocationRelativeTo(null);
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        requestFocus();

        dataModel = new DataModel();
        centralSplitPane = new JSplitPane();
        centralSplitPane.setDividerLocation(0.5);
        centralSplitPane.setResizeWeight(0.3);

        swingPanel = new SwingPanel();
        swingFXPanel = new SwingFXPanel();
        swingFXMLPanel = new SwingFXMLPanel(fxmlFile);

        TransparentBackground bg = new TransparentBackground(this);
        
        swingPanel.getTestButton().addActionListener((ActionEvent e) -> {
            dataModel.setLabelValue(swingPanel.getTestTextField().getText());
        });

        swingFXMLPanel.getTestButton().setOnAction((javafx.event.ActionEvent t) -> {
            dataModel.setLabelValue(swingFXMLPanel.getTestTextField().getText());
        });

        swingFXPanel.getTestButton().setOnAction((javafx.event.ActionEvent t) -> {
            dataModel.setLabelValue(swingFXPanel.getTestTextField().getText());
        });

        dataModel.labelValueProperty().addListener((ObservableValue<? extends String> ov, String t, String t1) -> {
            swingPanel.getTestLabel().setText(dataModel.getLabelValue());
            Platform.runLater(() -> {
                swingFXMLPanel.getTestLabel().setText(dataModel.getLabelValue());
            });
        });
        centralSplitPane.setLeftComponent(swingPanel);
        centralSplitPane.setRightComponent(swingFXMLPanel);
        //add(swingFXMLPanel);
        //this.getContentPane().add(bg); Transparent Swing Window
        add(centralSplitPane, BorderLayout.CENTER); //THIS ADDS BOTH SWING AND FXML TO THE SAME PANEL
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
    
    /**
     * Gets the swing panel.
     *
     * @return the swing panel
     */
    public SwingPanel getSwingPanel() {
		return swingPanel;
	}

	/**
	 * Sets the swing panel.
	 *
	 * @param swingPanel the new swing panel
	 */
	public void setSwingPanel(SwingPanel swingPanel) {
		this.swingPanel = swingPanel;
	}
    
    /**
     * Gets the swing FXML panel.
     *
     * @return the swing FXML panel
     */
    public SwingFXMLPanel getSwingFXMLPanel() {
		return swingFXMLPanel;
	}

	/**
	 * Sets the swing FXML panel.
	 *
	 * @param swingFXMLPanel the new swing FXML panel
	 */
	public void setSwingFXMLPanel(SwingFXMLPanel swingFXMLPanel) {
		this.swingFXMLPanel = swingFXMLPanel;
	}

}