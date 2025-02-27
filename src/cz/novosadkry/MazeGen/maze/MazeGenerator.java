package cz.novosadkry.MazeGen.maze;

import cz.novosadkry.MazeGen.cell.Cell;
import cz.novosadkry.MazeGen.cell.CellPos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MazeGenerator {
    Cell[][] cells;
    Cell cellSize;

    boolean generated;

    public MazeGenerator(int width, int height, Cell cellSize) {
        cells = new Cell[height][width];
        this.cellSize = cellSize;
    }

    public void generate() {
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                cells[y][x] = new Cell(cellSize.getWidth(), cellSize.getHeight());
            }
        }

        generate(new CellPos(0, 0));
        generated = true;
    }

    private void generate(CellPos pos) {
        cells[pos.getY()][pos.getX()].setVisited(true);

        while (true) {
            List<CellPos> unvisited = getUnvisited(pos);

            if (unvisited.size() < 1)
                break;

            int random = ThreadLocalRandom.current().nextInt(0, unvisited.size());
            CellPos selected = unvisited.get(random);
            CellPos diff = pos.subtract(selected);

            if (diff.getX() < 0) {
                cells[pos.getY()][pos.getX()].east = false;
                cells[selected.getY()][selected.getX()].west = false;
            } else if (diff.getX() > 0) {
                cells[pos.getY()][pos.getX()].west = false;
                cells[selected.getY()][selected.getX()].east = false;
            }

            if (diff.getY() < 0) {
                cells[pos.getY()][pos.getX()].south = false;
                cells[selected.getY()][selected.getX()].north = false;
            } else if (diff.getY() > 0) {
                cells[pos.getY()][pos.getX()].north = false;
                cells[selected.getY()][selected.getX()].south = false;
            }

            generate(selected);
        }
    }

    private List<CellPos> getUnvisited(CellPos pos) {
        List<CellPos> unvisited = new ArrayList<>();

        int x = pos.getX(); int y = pos.getY();

        if ((x - 1) > -1 && !cells[y][x - 1].isVisited())
            unvisited.add(new CellPos(x - 1, y));
        if ((x + 1) < cells[y].length && !cells[y][x + 1].isVisited())
            unvisited.add(new CellPos(x + 1, y));
        if ((y - 1) > -1 && !cells[y - 1][x].isVisited())
            unvisited.add(new CellPos(x, y - 1));
        if ((y + 1) < cells.length && !cells[y + 1][x].isVisited())
            unvisited.add(new CellPos(x, y + 1));

        return unvisited;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public boolean isGenerated() {
        return generated;
    }
}
