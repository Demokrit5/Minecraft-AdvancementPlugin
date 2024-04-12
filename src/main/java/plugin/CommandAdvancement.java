package plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAdvancement implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                return false;
            } else {
                String arg = args[0];
                switch (arg) {
                    case "all":
                        System.out.println("all");
                }
            }



            sender.sendMessage("du hurensohn");
            return true;
        }
        return false;
    }
}
