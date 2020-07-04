package cz.novosadkry.MazeGen.logic;

import cz.novosadkry.MazeGen.cell.Cell;
import cz.novosadkry.MazeGen.cell.CellPos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MazeGenerator {
    Cell[][] cells;

    public MazeGenerator(int width, int height) {
        cells = new Cell[height][width];
    }

    public Cell[][] generate() {
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                cells[y][x] = new Cell(1, 1);
                cells[y][x].setWall(x % 2 != 0 || y % 2 != 0);
            }
        }

        generate(new CellPos(0, 0));
        return cells;
    }

    private void generate(CellPos pos) {
        cells[pos.getY()][pos.getX()].setVisited(true);

        while (true) {
            CellPos[] unvisited = getUnvisited(pos);

            if (unvisited.length < 1)
                break;

            int random = ThreadLocalRandom.current().nextInt(0, unvisited.length);
            CellPos selected = unvisited[random];
            CellPos wall = pos.subtract(selected);

            if (wall.getX() < 0)
                cells[selected.getY()][selected.getX() - 1].setWall(false);
            else if (wall.getX() > 0)
                cells[selected.getY()][selected.getX() + 1].setWall(false);

            if (wall.getY() < 0)
                cells[selected.getY() - 1][selected.getX()].setWall(false);
            else if (wall.getY() > 0)
                cells[selected.getY() + 1][selected.getX()].setWall(false);

            generate(selected);
        }
    }

    private CellPos[] getUnvisited(CellPos pos) {
        List<CellPos> unvisited = new ArrayList<>();

        int x = pos.getX(); int y = pos.getY();

        if ((x - 2) > -1 && !cells[y][x - 2].isVisited())
            unvisited.add(new CellPos(x - 2, y));
        if ((x + 2) < cells[y].length && !cells[y][x + 2].isVisited())
            unvisited.add(new CellPos(x + 2, y));
        if ((y - 2) > -1 && !cells[y - 2][x].isVisited())
            unvisited.add(new CellPos(x, y - 2));
        if ((y + 2) < cells.length && !cells[y + 2][x].isVisited())
            unvisited.add(new CellPos(x, y + 2));

        return unvisited.toArray(new CellPos[0]);
    }
}
