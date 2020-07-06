package cz.novosadkry.MazeGen.tabcompleters;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MazeGenTabCompleter implements TabCompleter {
    private List<String> defaultCMDs = Arrays.asList(
            "spawn",
            "set",
            "cancel"
    );

    private List<String> setCMDs = Arrays.asList(
            "material",
            "width",
            "height",
            "depth",
            "tick",
            "cell"
    );

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            return defaultCMDs.stream()
                    .filter(c -> c.startsWith(args[0]))
                    .collect(Collectors.toList());
        }

        else if (args.length == 2 && args[0].equals("set")) {
            return setCMDs.stream()
                    .filter(c -> c.startsWith(args[1]))
                    .collect(Collectors.toList());
        }

        else if (args.length == 3 && args[1].equals("material")) {
            return Arrays.stream(Material.values())
                    .map(Material::toString)
                    .filter(m -> m.startsWith(args[2].toUpperCase()))
                    .collect(Collectors.toList());
        }

        return null;
    }
}
