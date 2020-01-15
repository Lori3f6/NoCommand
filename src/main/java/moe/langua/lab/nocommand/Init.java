package moe.langua.lab.nocommand;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Init extends JavaPlugin implements Listener, CommandExecutor {
    private String message;
    private boolean blockMode;
    private List<String> commandList;

    @Override
    public void onEnable() {
        getLogger().info("loading...");
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("reload").setExecutor(this);
        this.saveDefaultConfig();
        blockMode = getConfig().getBoolean("whitelistmode");
        message = getConfig().getString("feedback").replace('&', '§');
        commandList = getConfig().getStringList("commands");
        getLogger().info("plugin has loaded successfully.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("nocommand.reload")) return false;
        this.reloadConfig();
        blockMode = getConfig().getBoolean("whitelistmode");
        message = getConfig().getString("feedback").replace('&', '§');
        commandList = getConfig().getStringList("commands");
        sender.sendMessage("NoCommand has reloaded successfully.");
        return true;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if (e.getPlayer().hasPermission("nocommand.bypass")) return;
        String command = e.getMessage().toLowerCase().substring(1,e.getMessage().length());
        if(command.contains(" ")){
            command = command.substring(0,command.indexOf(" "));
        }
        boolean reject;
        if (blockMode) {
            reject = true;
            for (String x : commandList) {
                if (x.equals(command)) {
                    reject = false;
                    break;
                }
            }
        } else {
            reject = false;
            for (String x : commandList) {
                if (x.equals(command)) {
                    reject = true;
                    break;
                }
            }
        }
        if (reject) {
            e.setCancelled(true);
            if (message.length() != 0) {
                e.getPlayer().sendMessage(message.replace("{player}", e.getPlayer().getName()).replace("{command}", command));
            }
        }
    }

}
