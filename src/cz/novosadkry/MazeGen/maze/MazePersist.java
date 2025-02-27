package cz.novosadkry.MazeGen.maze;

import cz.novosadkry.MazeGen.cell.Cell;
import org.bukkit.Material;

public class MazePersist {
    public MazeBuilder last;
    public long tick;

    public int width, height, depth;
    public Material mat;
    public Cell cellSize;

    public boolean validate() {
        return (width != 0 &&
                height != 0 &&
                depth != 0 &&
                mat != null &&
                cellSize != null);
    }
}
