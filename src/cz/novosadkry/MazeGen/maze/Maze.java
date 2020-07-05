package cz.novosadkry.MazeGen.maze;

import cz.novosadkry.MazeGen.Main;
import cz.novosadkry.MazeGen.cell.Cell;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitTask;

public class Maze {
    int width, height, depth;

    Material mat;
    MazeGenerator gen;

    public Maze(Material mat, int width, int height, int depth) {
        this(mat, width, height, depth, 1, 1);
    }

    public Maze(Material mat, int width, int height, int depth, int cellX, int cellY) {
        if (mat == null)
            throw new NullPointerException();

        this.width = width;
        this.height = height;
        this.depth = depth;
        this.mat = mat;

        gen = new MazeGenerator((width / (1 + cellX)), (height / (1 + cellY)), new Cell(cellX, cellY));
    }

    public BukkitTask runSpawn(Location loc) {
        return runSpawn(loc, 0);
    }

    public BukkitTask runSpawn(Location loc, long tick) {
        return Bukkit.getScheduler().runTaskAsynchronously(Main.getPlugin(Main.class), () -> {
            Cell[][] cells = gen.generate();
            spawnMaze(cells, loc);
        });
    }

    private void spawnMaze(Cell[][] cells, Location loc) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Cell cell = cells[i][j];

                int y = (cell.getHeight() + 1) * i;
                int x = (cell.getWidth() + 1) * j;

                try { Thread.sleep(30); } catch (InterruptedException ignored) { }
                Bukkit.getScheduler().runTask(Main.getPlugin(Main.class), () ->
                    spawnCell(cell, loc.clone().add(y, 0, x))
                );
            }
        }
    }

    private void spawnCell(Cell cell, Location loc) {
        for (int z = 0; z < depth; z++) {
            for (int x = 0; x < cell.getWidth() + 2; x++) {
                if (!cell.north && x > 0 && x < cell.getWidth() + 1)
                    continue;

                Location _loc = loc.clone().add(0, z, x);
                _loc.getBlock().setType(mat, false);
            }

            for (int x = 0; x < cell.getWidth() + 2; x++) {
                if (!cell.south && x > 0 && x < cell.getWidth() + 1)
                    continue;

                Location _loc = loc.clone().add(cell.getHeight() + 1, z, x);
                _loc.getBlock().setType(mat, false);
            }

            for (int y = 0; y < cell.getHeight() + 2; y++) {
                if (!cell.west && y > 0 && y < cell.getHeight() + 1)
                    continue;

                Location _loc = loc.clone().add(y, z, 0);
                _loc.getBlock().setType(mat, false);
            }

            for (int y = 0; y < cell.getHeight() + 2; y++) {
                if (!cell.east && y > 0 && y < cell.getHeight() + 1)
                    continue;

                Location _loc = loc.clone().add(y, z, cell.getWidth() + 1);
                _loc.getBlock().setType(mat, false);
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
