package plugin;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementDisplay;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CommandAdvancement implements CommandExecutor {

    private List<Advancement> allAdvancements;

    public CommandAdvancement() {
        this.allAdvancements = new ArrayList<>();
        Iterator<Advancement> iterator = Bukkit.advancementIterator();
        while (iterator.hasNext()) {
            Advancement adv = iterator.next();
            this.allAdvancements.add(adv);
            System.out.println(adv.getDisplay().getTitle());
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                return false;
            } else {
                StringBuilder message = new StringBuilder();
                String arg = args[0];
                switch (arg) {
                    case "all":
                        if(args.length > 1) {
                            if("details".equals(args[1])) {
                                for(Advancement adv : this.allAdvancements) {
                                    AdvancementProgress progress = player.getAdvancementProgress(adv);
                                    if(!progress.isDone()) {
                                        message.append(adv.getDisplay().getTitle() + "\n");
                                        for (String criteria : progress.getRemainingCriteria()) {
                                            String criteriaMessage = "    " + criteria + "\n";
                                            message.append(criteriaMessage);
                                        }
                                    }
                                }
                            } else {
                                return false;
                            }
                        } else {
                            for(Advancement adv : this.allAdvancements) {
                                if(!player.getAdvancementProgress(adv).isDone()) {
                                    message.append(adv.getDisplay().getTitle()).append("\n");
                                }
                            }
                        }
                        break;
                    case "MonsterHunter":
                        Advancement monsterHunter = this.allAdvancements.stream().filter(a -> a.getKey())
                    default:
                        return false;
                }
                sender.sendMessage(message.toString());
                return true;
            }
        }
        return false;
    }
}
