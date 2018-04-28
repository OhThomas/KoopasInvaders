/*
 * 
 */
package SwingFX;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.game.src.main.Game;

// TODO: Auto-generated Javadoc
/**
 * The Class SwingPanel.
 */
public class SwingPanel extends JPanel{

    /** The test button. */
    private JButton testButton;
    
    /** The test text field. */
    private JTextField testTextField;
    
    /** The test label. */
    private JLabel testLabel;
    
    /**
     * Instantiates a new swing panel.
     */
    public SwingPanel() {
        init();
    }
    
    /**
     * Inits the.
     */
    private void init(){
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        testButton = new JButton("I am a Swing Button");
        testTextField = new JTextField();
        testLabel = new JLabel("empty");
        testButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        testTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        testLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Box.Filler filler1 = new Box.Filler(new Dimension(0, 0), new Dimension(0, 1000), new Dimension(0, 32767));
        Box.Filler filler2 = new Box.Filler(new Dimension(0, 0), new Dimension(0, 1000), new Dimension(0, 32767));
        panel.add(filler1);
        panel.add(testTextField);
        panel.add(testButton);
        panel.add(testLabel);
        panel.add(filler2);
        add(panel, BorderLayout.CENTER);
    }
    
    /**
     * Creates the image.
     *
     * @param panel the panel
     * @return the buffered image
     */
    public BufferedImage createImage(JPanel panel) {

        int w = Game.WIDTH * Game.SCALE;
        int h = Game.HEIGHT * Game.SCALE;
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        panel.paint(g);
        g.dispose();
        return bi;
    }
    
    /**
     * Save image.
     *
     * @param panel the panel
     * @return the buffered image
     */
    public BufferedImage saveImage(JPanel panel) {
        BufferedImage img = new BufferedImage(Game.WIDTH * Game.SCALE,Game.HEIGHT * Game.SCALE, BufferedImage.TYPE_INT_ARGB);
        panel.paint(img.getGraphics());
        try {
            ImageIO.write(img, "png", new File("res/Screen.png"));
            System.out.println("panel saved as image");

        } catch (Exception e) {
            System.out.println("panel not saved" + e.getMessage());
        }
        return img;
    }
    
    /**
     * Gets the test button.
     *
     * @return the test button
     */
    public JButton getTestButton() {
        return testButton;
    }

    /**
     * Gets the test label.
     *
     * @return the test label
     */
    public JLabel getTestLabel() {
        return testLabel;
    }

    /**
     * Gets the test text field.
     *
     * @return the test text field
     */
    public JTextField getTestTextField() {
        return testTextField;
    }
    
}