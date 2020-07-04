package cz.novosadkry.MazeGen.cell;

public class Cell {
    int width, height;
    boolean visited;
    boolean wall;

    public Cell(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isWall() {
        return wall;
    }

    public void setWall(boolean wall) {
        this.wall = wall;
    }
}
