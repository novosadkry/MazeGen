package cz.novosadkry.MazeGen;

import cz.novosadkry.MazeGen.cell.Cell;
import cz.novosadkry.MazeGen.logic.MazeGenerator;

public class NonPluginMain {
    public static void main(String[] args) {
        MazeGenerator gen = new MazeGenerator(20, 20);
        Cell[][] cells = gen.generate();

        for (int y = 0; y < cells.length; y++) {
            System.out.print('\n');

            for (int x = 0; x < cells[y].length; x++) {
                System.out.print(cells[y][x].isWall() ? '#' : ' ');
            }
        }
    }
}
