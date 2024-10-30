package sapper;

import java.util.Random;

public class Map {
    private final int width;
    private final int height;
    private final int numberOfBombs;
    private Field[][] fields;

    public Map(int width, int height, int numberOfBombs) {
        this.width = width;
        this.height = height;
        this.numberOfBombs = numberOfBombs;
        generateMap();
    }

    private void generateMap() {
        fields = new Field[width][height];
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                fields[i][j] = new Field();
        setUpBombs();
        markTheBoxesWithTheNumberOfAdjacentBombs();
    }

    private void setUpBombs() {
        Random random = new Random();
        int index;
        int xPos;
        int yPos;
        for (int i = 0; i < numberOfBombs; i++) {
            index = random.nextInt(fields.length * fields[0].length);
            xPos = index % width;
            yPos = (index - xPos) / width;
            if (fields[xPos][yPos].getNumberOfAdjacentBombs() < 9)
                fields[xPos][yPos].setNumberOfAdjacentBombs(9);
            else
                i--;
        }
    }

    private void markTheBoxesWithTheNumberOfAdjacentBombs() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (fields[j][i].getNumberOfAdjacentBombs() > 8) {
                    for (int k = -1; k < 2; k++) {
                        for (int l = -1; l < 2; l++) {
                            if (!(j + k < 0 || i + l < 0 || j + k == width || i + l == height))
                                fields[j + k][i + l].incrementNumberOfAdjacentBombs();
                        }
                    }
                }
            }
        }
    }

    public Field[][] getFields() {
        return fields;
    }
}
