package cz.novosadkry.MazeGen.cell;

public class Cell {
    int width, height;
    boolean visited;

    public boolean
            north = true,
            east = true,
            south = true,
            west = true;

    public Cell(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
