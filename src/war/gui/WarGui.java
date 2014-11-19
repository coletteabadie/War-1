package war.gui;

import javax.swing.*;
import java.awt.*;

import war.Card;
import war.WarView;

/**
 * Assignment #10
 * GUI Implementation of "War" card game.
 *
 * @author Walker Crouse
 */
public class WarGui extends JPanel implements WarView, Runnable {
    public static final String FRAME_TITLE = "War!";

    /**
     * Creates and initializes the game.
     */
    public WarGui() {
        super(new BorderLayout());
        add(new TablePanel(), BorderLayout.CENTER);
    }

    @Override
    public void run() {
        // create window
        JFrame frame = new JFrame(FRAME_TITLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // add content
        frame.setContentPane(this);

        // display window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = 2/3d * screenSize.width;
        double height = screenSize.height / 2d;
        frame.setSize((int) width, (int) height);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new WarGui());
    }
}
