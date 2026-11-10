package net.gawharasmp.items;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class GawharaItemsPlugin extends JavaPlugin {
    private ItemFactory itemFactory;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        // Merge new defaults into existing installations without overwriting their settings.
        getConfig().options().copyDefaults(true);
        saveConfig();
        itemFactory = new ItemFactory(this);
        ResourcePackManager resourcePackManager = new ResourcePackManager(this);

        getServer().getPluginManager().registerEvents(new ItemListener(this, itemFactory), this);
        getServer().getPluginManager().registerEvents(new MenuListener(itemFactory), this);
        getServer().getPluginManager().registerEvents(resourcePackManager, this);

        PluginCommand command = getCommand("gitems");
        if (command == null) {
            getLogger().severe("Command 'gitems' is missing from plugin.yml. Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        GawharaItemsCommand executor = new GawharaItemsCommand(this, itemFactory, resourcePackManager);
        command.setExecutor(executor);
        command.setTabCompleter(executor);

        getLogger().info("GawharaItems enabled. Loaded "
                + ItemType.values().length + " custom item types.");
        String resourcePackUrl = getConfig().getString("resource-pack.url", "");
        if (getConfig().getBoolean("resource-pack.enabled", true)
                && (resourcePackUrl == null || resourcePackUrl.isBlank())) {
            getLogger().warning("Automatic Java resource-pack delivery is enabled but resource-pack.url is empty. "
                    + "Upload the Java pack to a direct HTTPS host and set its URL in config.yml.");
        }
    }
}
