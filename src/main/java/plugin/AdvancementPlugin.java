package plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class AdvancementPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("advancement").setExecutor(new CommandAdvancement());
    }
}
