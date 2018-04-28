/*
 * 
 */
package SwingFX;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
 
 
 
// TODO: Auto-generated Javadoc
/**
 * The Class TransparentBackground.
 */
public class TransparentBackground extends JComponent
                implements ComponentListener, WindowFocusListener, Runnable {
 
        // constants ---------------------------------------------------------------
        /** The frame. */
        // instance ----------------------------------------------------------------
        private JFrame _frame;
        
        /** The background. */
        private BufferedImage _background;
        
        /** The last update. */
        private long _lastUpdate = 0;
        
        /** The refresh requested. */
        private boolean _refreshRequested = true;
        
        /** The robot. */
        private Robot _robot;
        
        /** The screen rect. */
        private Rectangle _screenRect;
        
        /** The blur op. */
        private ConvolveOp _blurOp;
 
        // constructor -------------------------------------------------------------
 
        /**
         * Instantiates a new transparent background.
         *
         * @param frame the frame
         */
        public TransparentBackground(JFrame frame) {
                _frame = frame;
                try {
                        _robot = new Robot();
                } catch (AWTException e) {
                        e.printStackTrace();
                        return;
                }
 
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                _screenRect = new Rectangle(dim.width, dim.height);
 
                float[] my_kernel = {
                                0.10f, 0.10f, 0.10f,
                                0.10f, 0.20f, 0.10f,
                                0.10f, 0.10f, 0.10f};
                _blurOp = new ConvolveOp(new Kernel(2, 2, my_kernel));
 
                updateBackground();
                _frame.addComponentListener(this);
                _frame.addWindowFocusListener(this);
                new Thread(this).start();
        }
 
        // protected ---------------------------------------------------------------
 
        /**
         * Update background.
         */
        protected void updateBackground() {
                _background = _robot.createScreenCapture(_screenRect);
        }
 
 
 
        /**
         * Refresh.
         */
        protected void refresh() {
                if (_frame.isVisible() && this.isVisible()) {
                        repaint();
                        _refreshRequested = true;
                        _lastUpdate = System.currentTimeMillis();
                }
        }
 
 
        // JComponent --------------------------------------------------------------
 
        /* (non-Javadoc)
         * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
         */
        protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                Point pos = this.getLocationOnScreen();
                BufferedImage buf = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
                buf.getGraphics().drawImage(_background, -pos.x, -pos.y, null);
 
                Image img = _blurOp.filter(buf, null);
                g2.drawImage(img, 0, 0, null);
                g2.setColor(new Color(255, 255, 255, 0));
                g2.fillRect(0, 0, getWidth(), getHeight());
        }
 
        /* (non-Javadoc)
         * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.ComponentEvent)
         */
        // ComponentListener -------------------------------------------------------
        public void componentHidden(ComponentEvent e) {
        }
 
        /* (non-Javadoc)
         * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.ComponentEvent)
         */
        public void componentMoved(ComponentEvent e) {
                repaint();
        }
 
        /* (non-Javadoc)
         * @see java.awt.event.ComponentListener#componentResized(java.awt.event.ComponentEvent)
         */
        public void componentResized(ComponentEvent e) {
                repaint();
 
        }
 
        /* (non-Javadoc)
         * @see java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent)
         */
        public void componentShown(ComponentEvent e) {
                repaint();
        }
 
        /* (non-Javadoc)
         * @see java.awt.event.WindowFocusListener#windowGainedFocus(java.awt.event.WindowEvent)
         */
        // WindowFocusListener -----------------------------------------------------
        public void windowGainedFocus(WindowEvent e) {
                refresh();
        }
 
        /* (non-Javadoc)
         * @see java.awt.event.WindowFocusListener#windowLostFocus(java.awt.event.WindowEvent)
         */
        public void windowLostFocus(WindowEvent e) {
                refresh();
        }
 
        /* (non-Javadoc)
         * @see java.lang.Runnable#run()
         */
        // Runnable ----------------------------------------------------------------
        public void run() {
                try {
                        while (true) {
                                Thread.sleep(100);
                                long now = System.currentTimeMillis();
                                if (_refreshRequested && ((now - _lastUpdate) > 1000)) {
                                        if (_frame.isVisible()) {
                                                Point location = _frame.getLocation();
                                                _frame.setLocation(-_frame.getWidth(), -_frame.getHeight());
                                                updateBackground();
                                                _frame.setLocation(location);
                                                refresh();
                                        }
                                        _lastUpdate = now;
                                        _refreshRequested = false;
                                }
                        }
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
        }
}