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
        this.width = width;
        this.height = height;
        this.depth = depth;

        gen = new MazeGenerator((width / 2) - 1, (height / 2) - 1);
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
            // TODO: Don't spawn individual walls

            for (int x = 0; x < cell.getWidth() + 2; x++) {
                Location _loc = loc.clone().add(0, z, x);
                _loc.getBlock().setType(Material.SMOOTH_STONE);
            }

            for (int x = 0; x < cell.getWidth() + 2; x++) {
                Location _loc = loc.clone().add(cell.getHeight() + 2, z, x);
                _loc.getBlock().setType(Material.SMOOTH_STONE);
            }

            for (int y = 0; y < cell.getHeight() + 2; y++) {
                Location _loc = loc.clone().add(y, z, 0);
                _loc.getBlock().setType(Material.SMOOTH_STONE);
            }

            for (int y = 0; y < cell.getHeight() + 2; y++) {
                Location _loc = loc.clone().add(y, z, cell.getWidth() + 2);
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
