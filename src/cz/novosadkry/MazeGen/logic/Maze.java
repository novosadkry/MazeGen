package cz.novosadkry.MazeGen.logic;

import cz.novosadkry.MazeGen.Main;
import cz.novosadkry.MazeGen.cell.Cell;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.concurrent.CompletableFuture;

public class Maze {
    int width, height, depth;

    MazeGenerator gen;

    public Maze(int width, int height, int depth) {
        this(width, height, depth, 1, 1);
    }

    public Maze(int width, int height, int depth, int cellX, int cellY) {
        this.width = width;
        this.height = height;
        this.depth = depth;

        gen = new MazeGenerator((width / (1 + cellX)), (height / (1 + cellY)), new Cell(cellX, cellY));
    }

    public void generate(Location loc) {
        CompletableFuture.runAsync(() -> {
            Cell[][] cells = gen.generate();
            Bukkit.getScheduler().runTask(Main.getPlugin(Main.class), () -> spawnMaze(cells, loc));
        });
    }

    private void spawnMaze(Cell[][] cells, Location loc) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Cell cell = cells[i][j];
                spawnCell(cell, loc.clone().add((cell.getHeight() + 1) * i, 0, (cell.getWidth() + 1) * j));
            }
        }
    }

    private void spawnCell(Cell cell, Location loc) {
        for (int z = 0; z < depth; z++) {
            for (int x = 0; x < cell.getWidth() + 2; x++) {
                if (!cell.north && x > 0 && x < cell.getWidth() + 1)
                    continue;

                Location _loc = loc.clone().add(0, z, x);
                _loc.getBlock().setType(Material.SMOOTH_STONE);
            }

            for (int x = 0; x < cell.getWidth() + 2; x++) {
                if (!cell.south && x > 0 && x < cell.getWidth() + 1)
                    continue;

                Location _loc = loc.clone().add(cell.getHeight() + 1, z, x);
                _loc.getBlock().setType(Material.SMOOTH_STONE);
            }

            for (int y = 0; y < cell.getHeight() + 2; y++) {
                if (!cell.west && y > 0 && y < cell.getHeight() + 1)
                    continue;

                Location _loc = loc.clone().add(y, z, 0);
                _loc.getBlock().setType(Material.SMOOTH_STONE);
            }

            for (int y = 0; y < cell.getHeight() + 2; y++) {
                if (!cell.east && y > 0 && y < cell.getHeight() + 1)
                    continue;

                Location _loc = loc.clone().add(y, z, cell.getWidth() + 1);
                _loc.getBlock().setType(Material.SMOOTH_STONE);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }
}
