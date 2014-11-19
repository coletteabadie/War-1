package war.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Assignment #10
 * Represents a "player" hand on the GUI.
 *
 * @author Walker Crouse
 */
public class PlayerPanel extends JPanel {
    private final JLabel nameTag = new JLabel();
    private final JTextField nameTagEditor = new JTextField();
    private boolean editingNameTag = false;

    /**
     * Creates a new panel.
     *
     * @param playerName name of player
     * @param inverted true if components should be placed backwards
     */
    public PlayerPanel(String playerName, boolean inverted) {
        if (inverted)
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        setOpaque(false);

        // place name tag
        nameTag.setText(playerName);
        nameTag.setForeground(Color.WHITE);
        nameTag.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // make the name tag editable when you double click it
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && !editingNameTag)
                    editNameTag();
            }
        });
        add(nameTag);

        // add the "deck" image
        add(new JLabel(JCard.DEFAULT_BACK_IMAGE));
    }

    /**
     * Creates a new panel.
     *
     * @param playerName name of player
     */
    public PlayerPanel(String playerName) {
        this(playerName, false);
    }

    public void addDiscardChangesListener() {
        getParent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (editingNameTag)
                    discardNameTagChanges();
            }
        });
    }

    /**
     * Changes the player's name tag to a {@link javax.swing.JTextField} for editing. Changes will be saved after the
     * user presses the "enter" key or {@link #saveNameTag} is called.
     */
    public void editNameTag() {
        if (editingNameTag)
            return;

        // remove name tag
        remove(nameTag);

        // setup editor
        String name = nameTag.getText();
        nameTagEditor.setText(name);
        nameTagEditor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER && editingNameTag)
                    saveNameTag();

            }
        });
        add(nameTagEditor, 0);

        // refresh window
        refresh();

        // select name
        nameTagEditor.requestFocusInWindow();
        nameTagEditor.selectAll();
        editingNameTag = true;
    }

    /**
     * Saves the name tag to the current text in the name tag editor {@link javax.swing.JTextField}.
     */
    public void saveNameTag() {
        if (!editingNameTag)
            return;
        remove(nameTagEditor);
        nameTag.setText(nameTagEditor.getText());
        add(nameTag, 0);
        refresh();
        editingNameTag = false;
    }

    /**
     * Reverts the name tag to it's former state.
     */
    public void discardNameTagChanges() {
        if (!editingNameTag)
            return;
        remove(nameTagEditor);
        add(nameTag, 0);
        refresh();
        editingNameTag = false;
    }

    /**
     * Returns true if currently editing the name tag.
     *
     * @return true if editing name tag
     */
    public boolean isEditingNameTag() {
        return editingNameTag;
    }

    private void refresh() {
        revalidate();
        repaint();
    }
}
