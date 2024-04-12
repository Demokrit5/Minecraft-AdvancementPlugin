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
            if(!adv.getKey().getKey().contains("recipe")) {
                this.allAdvancements.add(adv);
            }
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
                AdvancementProgress progress;
                switch (arg) {
                    case "all":
                        if(args.length > 1) {
                            if("details".equals(args[1])) {
                                for(Advancement adv : this.allAdvancements) {
                                    progress = player.getAdvancementProgress(adv);
                                    if(!progress.isDone()) {
                                        message.append(adv.getKey().getKey() + "\n");
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
                                    message.append(adv.getKey().getKey()).append("\n");
                                }
                            }
                        }
                        break;
                    case "AdventuringTime":
                        Advancement adventuringTime = Bukkit.getAdvancement(NamespacedKey.fromString("minecraft:adventure/adventuring_time"));
                        progress = player.getAdvancementProgress(adventuringTime);
                        message.append(adventuringTime.getKey().getKey() + "\n");
                        for (String criteria : progress.getRemainingCriteria()) {
                            criteria.split(":");
                            String criteriaMessage = "    " + criteria.split(":")[1] + "\n";
                            message.append(criteriaMessage);
                        }
                        break;
                    case "BalancedDiet":
                        Advancement balancedDiet = Bukkit.getAdvancement(NamespacedKey.fromString("minecraft:husbandry/balanced_diet"));
                        progress = player.getAdvancementProgress(balancedDiet);
                        message.append(balancedDiet.getKey().getKey() + "\n");
                        for (String criteria : progress.getRemainingCriteria()) {
                            String criteriaMessage = "    " + criteria + "\n";
                            message.append(criteriaMessage);
                        }
                        break;
                    case "BredAllAnimals":
                        Advancement bredAllAnimals = Bukkit.getAdvancement(NamespacedKey.fromString("minecraft:husbandry/bred_all_animals"));
                        progress = player.getAdvancementProgress(bredAllAnimals);
                        message.append(bredAllAnimals.getKey().getKey() + "\n");
                        for (String criteria : progress.getRemainingCriteria()) {
                            String criteriaMessage = "    " + criteria.split(":")[1] + "\n";
                            message.append(criteriaMessage);
                        }
                        break;
                    case "CompleteCatalogue":
                        Advancement completeCatalogue = Bukkit.getAdvancement(NamespacedKey.fromString("minecraft:husbandry/complete_catalogue"));
                        progress = player.getAdvancementProgress(completeCatalogue);
                        message.append(completeCatalogue.getKey().getKey() + "\n");
                        for (String criteria : progress.getRemainingCriteria()) {
                            String criteriaMessage = "    " + criteria.split(":")[1] + "\n";
                            message.append(criteriaMessage);
                        }
                        break;
                    case "MonsterHunter":
                        Advancement monsterHunter = Bukkit.getAdvancement(NamespacedKey.fromString("minecraft:adventure/kill_a_mob"));
                        progress = player.getAdvancementProgress(monsterHunter);
                        message.append(monsterHunter.getKey().getKey() + "\n");
                        for (String criteria : progress.getRemainingCriteria()) {
                            String criteriaMessage = "    " + criteria.split(":")[1] + "\n";
                            message.append(criteriaMessage);
                        }
                        break;
                    case "SmithingWithStyle":
                        Advancement smithingWithStyle = Bukkit.getAdvancement(NamespacedKey.fromString("minecraft:adventure/trim_with_all_exclusive_armor_patterns"));
                        progress = player.getAdvancementProgress(smithingWithStyle);
                        message.append(smithingWithStyle.getKey().getKey() + "\n");
                        for (String criteria : progress.getRemainingCriteria()) {
                            String criteriaMessage = "    " + criteria.split(":")[1] + "\n";
                            message.append(criteriaMessage);
                        }
                        break;
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
