package war.gui;

import war.Card;

import javax.swing.*;

/**
 * Assignment #10
 *
 * @author Walker Crouse
 */
public class JCard extends Card {
    private static final ImageIcon DEFAULT_BACK_IMAGE = new ImageIcon("cards/b1fv.png");

    private final ImageIcon frontImage, backImage;

    public JCard(int rank, Suit suit, ImageIcon frontImage, ImageIcon backImage) {
        super(rank, suit);
        this.frontImage = frontImage;
        this.backImage = backImage;
    }

    public JCard(int rank, Suit suit, String frontImage, String backImage) {
        this(rank, suit, new ImageIcon(frontImage), new ImageIcon(backImage));
    }

    public JCard(int rank, Suit suit, ImageIcon frontImage) {
        this(rank, suit, frontImage, DEFAULT_BACK_IMAGE);
    }

    public JCard(int rank, Suit suit, String frontImage) {
        this(rank, suit, new ImageIcon(frontImage));
    }
}
