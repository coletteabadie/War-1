package war.gui;

import javax.swing.*;
import java.awt.*;
import war.WarView;
import war.WarModel;

/**
 * Assignment #10
 *
 * @author Walker Crouse
 */
public class WarGui implements WarView {
    private static final String FRAME_TITLE = "War!";
    private static final Color BACKGROUND_COLOR = new Color(0, 84, 10);

    private final WarModel model = new WarModel(this);
    private final JFrame frame = new JFrame(FRAME_TITLE);

    public static void main(String[] args) {
        new WarGui().start();
    }

    public void start() {
        frame.getContentPane().setBackground(BACKGROUND_COLOR);
        frame.pack();
        frame.setVisible(true);
    }
}
