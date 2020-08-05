package cz.novosadkry.MazeGen.maze;

import cz.novosadkry.MazeGen.Main;
import cz.novosadkry.MazeGen.cell.Cell;
import cz.novosadkry.MazeGen.nms.NMSWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class MazeBuilder implements Runnable {
    boolean cancelled;

    Location loc;
    Maze maze;
    long tick;

    public MazeBuilder(Location loc, Maze maze, long tick) {
        this.loc = loc;
        this.maze = maze;
        this.tick = tick;
    }

    @Override
    public void run() {
        if (!maze.gen.isGenerated())
            maze.gen.generate();

        Cell[][] cells = maze.gen.getCells();

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Cell cell = cells[i][j];

                int y = (cell.getHeight() + 1) * i;
                int x = (cell.getWidth() + 1) * j;

                if (cancelled)
                    return;

                try { Thread.sleep(tick); } catch (InterruptedException ignored) { }
                Bukkit.getScheduler().runTask(Main.getPlugin(Main.class), () ->
                        spawnCell(cell, loc.clone().add(y, 0, x))
                );
            }
        }
    }

    private void spawnCell(Cell cell, Location loc) {
        for (int z = 0; z < maze.depth; z++) {
            for (int x = 0; x < cell.getWidth() + 2; x++) {
                if (!cell.north && x > 0 && x < cell.getWidth() + 1)
                    continue;

                Location _loc = loc.clone().add(0, z, x);
                NMSWorld.setBlockInNativeWorld(
                        _loc.getWorld(),
                        _loc.getBlockX(),
                        _loc.getBlockY(),
                        _loc.getBlockZ(),
                        maze.mat,
                        false
                );
            }

            for (int x = 0; x < cell.getWidth() + 2; x++) {
                if (!cell.south && x > 0 && x < cell.getWidth() + 1)
                    continue;

                Location _loc = loc.clone().add(cell.getHeight() + 1, z, x);
                NMSWorld.setBlockInNativeWorld(
                        _loc.getWorld(),
                        _loc.getBlockX(),
                        _loc.getBlockY(),
                        _loc.getBlockZ(),
                        maze.mat,
                        false
                );
            }

            for (int y = 0; y < cell.getHeight() + 2; y++) {
                if (!cell.west && y > 0 && y < cell.getHeight() + 1)
                    continue;

                Location _loc = loc.clone().add(y, z, 0);
                NMSWorld.setBlockInNativeWorld(
                        _loc.getWorld(),
                        _loc.getBlockX(),
                        _loc.getBlockY(),
                        _loc.getBlockZ(),
                        maze.mat,
                        false
                );
            }

            for (int y = 0; y < cell.getHeight() + 2; y++) {
                if (!cell.east && y > 0 && y < cell.getHeight() + 1)
                    continue;

                Location _loc = loc.clone().add(y, z, cell.getWidth() + 1);
                NMSWorld.setBlockInNativeWorld(
                        _loc.getWorld(),
                        _loc.getBlockX(),
                        _loc.getBlockY(),
                        _loc.getBlockZ(),
                        maze.mat,
                        false
                );
            }
        }
    }

    public void cancel() {
        cancelled = true;
    }
}
