package cz.novosadkry.MazeGen.executors;

import cz.novosadkry.MazeGen.Main;
import cz.novosadkry.MazeGen.cell.Cell;
import cz.novosadkry.MazeGen.maze.Maze;
import cz.novosadkry.MazeGen.maze.MazePersist;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MazeGenExecutor implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;

            if (!Main.mazePersist.containsKey(player))
                Main.mazePersist.put(player, new MazePersist());

            MazePersist persist = Main.mazePersist.get(player);

            if (args.length > 0) {
                if (args[0].equals("spawn")) {
                    if (!persist.validate()) {
                        player.sendMessage("[MazeGen] Property unset! Use /mazegen set");
                        return true;
                    }

                    Maze maze = new Maze(
                            persist.mat,
                            persist.width,
                            persist.height,
                            persist.depth,
                            persist.cellSize.getWidth(),
                            persist.cellSize.getHeight()
                    );

                    persist.last = maze.runSpawn(player.getLocation().clone().add(1, 0, 1), persist.tick);
                    player.sendMessage("[MazeGen] Generating... Please wait");
                }

                else if (args[0].equals("set")) {
                    try {
                        switch (args[1]) {
                            case "material":
                                persist.mat = Material.valueOf(args[2]);
                                break;
                            case "width":
                                persist.width = Integer.parseInt(args[2]);
                                break;
                            case "height":
                                persist.height = Integer.parseInt(args[2]);
                                break;
                            case "depth":
                                persist.depth = Integer.parseInt(args[2]);
                                break;
                            case "tick":
                                persist.tick = Long.parseLong(args[2]);
                                break;
                            case "cell":
                                persist.cellSize = new Cell(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                                break;
                        }
                    } catch (NumberFormatException ignored) { }
                }

                else if (args[0].equals("cancel")) {
                    persist.last.cancel();
                }
            }
        }

        return true;
    }
}
