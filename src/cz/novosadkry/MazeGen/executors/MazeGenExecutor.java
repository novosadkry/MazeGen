package cz.novosadkry.MazeGen.executors;

import cz.novosadkry.MazeGen.logic.Maze;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MazeGenExecutor implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;

            if (args.length < 3)
                return false;

            Maze maze = new Maze(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            maze.generate(player.getLocation());

            player.sendMessage("[MazeGen] Maze generating... Please wait");
        }

        return true;
    }
}
