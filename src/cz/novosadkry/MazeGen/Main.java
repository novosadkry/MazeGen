package cz.novosadkry.MazeGen;

import cz.novosadkry.MazeGen.executors.MazeGenExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getCommand("mazegen").setExecutor(new MazeGenExecutor());
    }
}
