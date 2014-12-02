package war.gui;

import war.WarModel;

import javax.swing.*;
import java.awt.*;

/**
 * Assignment #10
 *
 * @author Walker Crouse
 */
public class ControlPanel extends JPanel {
    private JButton drawBtn = new JButton("Draw"), mobilizeButton = new JButton("Mobilize");
    private JLabel poolCount = new JLabel("Pool : 0", SwingConstants.CENTER);
    private JLabel cardCount1 = new JLabel("26"), cardCount2 = new JLabel("26");

    public ControlPanel(WarModel model) {
        super(new BorderLayout());

        JPanel stats = new JPanel(new BorderLayout());
        stats.add(cardCount1, BorderLayout.WEST);
        stats.add(poolCount, BorderLayout.CENTER);
        stats.add(cardCount2, BorderLayout.EAST);
        add(stats, BorderLayout.NORTH);

        // add "draw" button
        add(drawBtn, BorderLayout.CENTER);
        drawBtn.addActionListener(a -> model.nextTurn());

        // add "mobilize" button
        mobilizeButton.setEnabled(false);
        add(mobilizeButton, BorderLayout.SOUTH);
        mobilizeButton.addActionListener(a -> model.mobilize());
    }

    public JButton getDrawButton() {
        return drawBtn;
    }

    public JButton getMobilizeButton() {
        return mobilizeButton;
    }

    public void setPoolCount(int count) {
        poolCount.setText("Pool : " + count);
    }

    public void setCardCount(int count1, int count2) {
        cardCount1.setText("" + count1);
        cardCount2.setText("" + count2);
    }
}
