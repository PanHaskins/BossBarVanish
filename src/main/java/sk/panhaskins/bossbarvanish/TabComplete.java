package sk.panhaskins.bossbarvanish;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabComplete implements TabCompleter {

    List<String> arguments = new ArrayList<>();

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (arguments.isEmpty()) {
            arguments.add("reload");
        }

        List<String> argumentsList = new ArrayList<>();
        if (args.length == 1) {
            for (String list : arguments) {
                if (list.toLowerCase().startsWith(args[0].toLowerCase()))
                    argumentsList.add(list);
            }
            return argumentsList;
        }
        return null;
    }
}
