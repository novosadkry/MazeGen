package cz.novosadkry.MazeGen.tabcompleters;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MazeGenTabCompleter implements TabCompleter {
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            return Arrays.asList(
                    "spawn",
                    "set",
                    "cancel"
            );
        }

        if (args.length == 2 && args[0].equals("set")) {
            return Arrays.asList(
                    "material",
                    "width",
                    "height",
                    "depth",
                    "tick",
                    "cell"
            );
        }

        if (args.length == 3 && args[1].equals("material")) {
            return Arrays.stream(Material.values())
                    .map(Material::toString)
                    .collect(Collectors.toList());
        }

        return null;
    }
}
