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
 * The Class fxmlWindowFrame.
 */
public class fxmlWindowFrame extends JFrame {

    /** The central split pane. */
    private JSplitPane centralSplitPane;
    
    /** The swing panel. */
    private SwingPanel swingPanel;
    
    /** The fxml window. */
    private fxmlWindow fxmlWindow;
    
    /** The fxml file. */
    private String fxmlFile = "demo.fxml";
    
    /** The data model. */
    private DataModel dataModel;
    
    /** The m. */
    public MenuMusicController m;

    /**
     * Instantiates a new fxml window frame.
     *
     * @param title the title
     * @param fxmlFile the fxml file
     */
    public fxmlWindowFrame(String title, String fxmlFile) {
    	this.fxmlFile = fxmlFile;
        init(title);
        m = new MenuMusicController();
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
        fxmlWindow = new fxmlWindow(title, fxmlFile);
        
        centralSplitPane.setLeftComponent(swingPanel);
        centralSplitPane.setRightComponent(fxmlWindow);
        add(fxmlWindow);
        //this.getContentPane().add(bg); Transparent Swing Window
        //add(centralSplitPane, BorderLayout.CENTER); THIS ADDS BOTH SWING AND FXML TO THE SAME PANEL
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
     * Gets the FXML window.
     *
     * @return the FXML window
     */
    public fxmlWindow getFXMLWindow() {
		return fxmlWindow;
	}

	/**
	 * Sets the FXML window.
	 *
	 * @param fxmlWindow the new FXML window
	 */
	public void setFXMLWindow(fxmlWindow fxmlWindow) {
		this.fxmlWindow = fxmlWindow;
	}
	
	/**
	 * Gets the m.
	 *
	 * @return the m
	 */
	public MenuMusicController getM() {
		return m;
	}

	/**
	 * Sets the m.
	 *
	 * @param m the new m
	 */
	public void setM(MenuMusicController m) {
		this.m = m;
	}

}