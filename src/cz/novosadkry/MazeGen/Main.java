package cz.novosadkry.MazeGen;

import cz.novosadkry.MazeGen.executors.MazeGenExecutor;
import cz.novosadkry.MazeGen.maze.MazePersist;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {
    public static Map<Player, MazePersist> mazePersist = new HashMap<>();

    @Override
    public void onEnable() {
        getCommand("mazegen").setExecutor(new MazeGenExecutor());
    }

    @Override
    public void onDisable() {
        mazePersist.clear();
    }
}
