package cz.novosadkry.MazeGen.executors;

import cz.novosadkry.MazeGen.logic.Maze;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MazeGenExecutor implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;

            if (args.length < 4) {
                player.sendMessage(
                        "Usage: /mazegen <material> <width> <height> <depth>\n" +
                        "OR /mazegen <material> <width> <height> <depth> <cellX> <cellY>"
                );

                return false;
            }

            Maze maze = null;

            try {
                if (args.length == 4) {
                    maze = new Maze(
                            Material.getMaterial(args[0]),
                            Integer.parseInt(args[1]),
                            Integer.parseInt(args[2]),
                            Integer.parseInt(args[3])
                    );
                }

                else if (args.length == 6) {
                    maze = new Maze(
                            Material.getMaterial(args[0]),
                            Integer.parseInt(args[1]),
                            Integer.parseInt(args[2]),
                            Integer.parseInt(args[3]),
                            Integer.parseInt(args[4]),
                            Integer.parseInt(args[5])
                    );
                }
            } catch (NullPointerException | NumberFormatException ignored) { }

            if (maze == null)
                return false;

            maze.spawn(player.getLocation());
            player.sendMessage("[MazeGen] Generating... Please wait");
        }

        return true;
    }
}
