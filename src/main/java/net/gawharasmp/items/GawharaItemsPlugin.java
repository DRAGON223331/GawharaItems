package net.gawharasmp.items;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class GawharaItemsPlugin extends JavaPlugin {
    private ItemFactory itemFactory;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        itemFactory = new ItemFactory(this);

        getServer().getPluginManager().registerEvents(new ItemListener(this, itemFactory), this);
        getServer().getPluginManager().registerEvents(new MenuListener(itemFactory), this);

        PluginCommand command = getCommand("gitems");
        if (command == null) {
            getLogger().severe("Command 'gitems' is missing from plugin.yml. Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        GawharaItemsCommand executor = new GawharaItemsCommand(this, itemFactory);
        command.setExecutor(executor);
        command.setTabCompleter(executor);

        getLogger().info("GawharaItems enabled. Loaded "
                + ItemType.values().length + " custom item types.");
    }
}
