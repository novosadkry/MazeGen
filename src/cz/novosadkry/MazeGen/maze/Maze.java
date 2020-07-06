package cz.novosadkry.MazeGen.maze;

import cz.novosadkry.MazeGen.Main;
import cz.novosadkry.MazeGen.cell.Cell;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

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

    public MazeBuilder spawn(Location loc) {
        return spawn(loc, 0);
    }

    public MazeBuilder spawn(Location loc, long tick) {
        MazeBuilder builder = new MazeBuilder(loc, this, tick);
        Bukkit.getScheduler().runTaskAsynchronously(Main.getPlugin(Main.class), builder);

        return builder;
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

    public MazeGenerator getGenerator() {
        return gen;
    }
}
