package cz.novosadkry.MazeGen;

import cz.novosadkry.MazeGen.executors.MazeGenExecutor;
import cz.novosadkry.MazeGen.maze.MazePersist;
import cz.novosadkry.MazeGen.tabcompleters.MazeGenTabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {
    public static Map<Player, MazePersist> mazePersist = new HashMap<>();

    @Override
    public void onEnable() {
        getCommand("mazegen").setExecutor(new MazeGenExecutor());
        getCommand("mazegen").setTabCompleter(new MazeGenTabCompleter());
    }

    @Override
    public void onDisable() {
        mazePersist.clear();
    }
}
