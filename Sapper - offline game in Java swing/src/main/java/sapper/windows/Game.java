package sapper.windows;

import sapper.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Game extends JFrame {
    public Game(int width, int height, int numberOfBombs) {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
        setTitle("Sapper");
        addComponentsToTheLayout(width, height, numberOfBombs);
        this.pack();
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addComponentsToTheLayout(int width, int height, int numberOfBombs) {
        setLayout(new BorderLayout());
        JPanel gameGrid = createGameGrid(width, height, numberOfBombs);
        add(gameGrid, BorderLayout.SOUTH);
    }

    private JPanel createGameGrid(int width, int height, int numberOfBombs) {
        Map map = new Map(width, height, numberOfBombs);
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(height, width));

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                JButton singleField = new JButton();
                singleField.setPreferredSize(new Dimension(40, 40));
                int xPos = j;
                int yPos = i;
                singleField.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            singleField.setBackground(Color.YELLOW);
                        } else if (map.getFields()[xPos][yPos].getNumberOfAdjacentBombs() > 8) {
                            bombDetonation(singleField);
                        } else {
                            deminedSingleField(singleField, map, xPos, yPos);
                        }
                    }
                });
                gridPanel.add(singleField);
            }
        }
        return gridPanel;
    }

    private void bombDetonation(JButton singleField) {
        singleField.setIcon(new ImageIcon("src/main/resources/images/bomb.png"));
        singleField.setBackground(Color.RED);
        new Fail(this);
    }

    private void deminedSingleField(JButton singleField, Map map, int xPos, int yPos) {
        singleField.setText("" + map.getFields()[xPos][yPos].getNumberOfAdjacentBombs());
        singleField.setBackground(Color.GREEN);
    }

}
