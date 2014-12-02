package war.gui;

import war.Card;

import javax.swing.*;
import java.awt.*;

/**
 * Assignment #10
 *
 * @author Walker Crouse
 */
public class CardPanel extends JPanel {
    public CardPanel(int horizontalAlignment) {
        super(new CardLayout());
        setOpaque(false);
        add("back", new JLabel(JCard.DEFAULT_BACK_IMAGE));
        for (Card card : Card.all()) {
            JLabel label = new JCard(card).getComponent();
            label.setHorizontalAlignment(horizontalAlignment);
            add(card.toString(), label);
        }
        setVisible(false);
    }

    public void show(String name) {
        setVisible(true);
        ((CardLayout) getLayout()).show(this, name);
    }

    public void showCard(Card card) {
        show(card.toString());
    }

    public void showBack() {
        show("back");
    }
}
