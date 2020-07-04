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

        gen = new MazeGenerator(width, height);
    }

    public void generate(Location loc) {
        CompletableFuture.runAsync(() -> {
            Cell[][] cells = gen.generate();
            Bukkit.getScheduler().runTask(Main.getPlugin(Main.class), () -> spawnMaze(cells, loc));
        });
    }

    private void spawnMaze(Cell[][] cells, Location loc) {
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                if (cells[y][x].isWall()) {
                    for (int z = 0; z < depth; z++) {
                        Location _loc = new Location(loc.getWorld(), loc.getX() + y, loc.getY() + z, loc.getZ() + x);
                        _loc.getBlock().setType(Material.SMOOTH_STONE);
                    }
                }
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
