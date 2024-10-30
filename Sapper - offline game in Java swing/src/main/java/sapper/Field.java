package sapper;

public class Field {
    private int numberOfAdjacentBombs;

    public int getNumberOfAdjacentBombs() {
        return numberOfAdjacentBombs;
    }

    public void setNumberOfAdjacentBombs(int numberOfAdjacentBombs) {
        this.numberOfAdjacentBombs = numberOfAdjacentBombs;
    }

    public void incrementNumberOfAdjacentBombs() {
        numberOfAdjacentBombs++;
    }
}
