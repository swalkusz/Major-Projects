package sapper.windows;

import javax.swing.*;
import java.awt.*;

public class Startup extends JFrame {
    private JTextField numberOfRows;
    private JTextField numberOfColumns;
    private JTextField numberOfBombs;

    public Startup() {
        setTitle("Sapper - creator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToTheLayout();
        this.pack();
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addComponentsToTheLayout() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        numberOfRows = createOption("Number of rows:", panel);
        numberOfColumns = createOption("Number of columns:", panel);
        numberOfBombs = createOption("Number of bombs:", panel);
        createStandardGameButton(panel);
        createPlayButton(panel);
        add(panel);
    }

    private void triggerTheGame(int width, int height, int numberOfBombs) {
        dispose();
        new Game(width, height, numberOfBombs);
    }

    private JTextField createOption(String label, JPanel panel) {
        panel.add(new JLabel(label));
        JTextField textField = new JTextField();
        panel.add(textField);
        return textField;
    }

    private JButton createButton(String text, JPanel panel) {
        JButton button = new JButton(text);
        panel.add(button);
        return button;
    }

    private void createStandardGameButton(JPanel panel) {
        createButton("Create standard game", panel)
                .addActionListener(e -> triggerTheGame(10, 10, 11));
    }

    private void createPlayButton(JPanel panel) {
        createButton("Play", panel)
                .addActionListener(e -> {
                    try {
                        int width = Integer.parseInt(numberOfRows.getText());
                        int height = Integer.parseInt(numberOfColumns.getText());
                        int numberOfBombs = Integer.parseInt(Startup.this.numberOfBombs.getText());
                        triggerTheGame(width, height, numberOfBombs);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter the correct numbers for each item!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });
    }

}
