package me.jassy.clearchat;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {

	File file = new File("plugins/KatsMCChatClear");
	File config = new File("plugins/KatsMCChatClear/config.yml");
	FileConfiguration cfg = YamlConfiguration.loadConfiguration(config);
	String prefix = cfg.getString("prefix");

	public void onEnable() {
		file.mkdirs();
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();

			}
		}

		if (!config.exists()) {
			getConfig().options().copyDefaults(true);
			this.saveConfig();

		}

		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();
		logger.info(pdfFile.getName() + " has been enabled (V." + pdfFile.getVersion() + ")");

	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();

		logger.info(pdfFile.getName() + " has been disabled (V." + pdfFile.getVersion() + ")");

	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (command.getName().equalsIgnoreCase("clearchat")) {
				if (p.hasPermission("clearchat.cc")) {
					for (int i = 0; i < 100; i++) {
						Bukkit.broadcastMessage("");
					}

					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + "Chat has been cleared by " + p.getName() + "."));
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							prefix + "You don't have permission to do that command " + p.getName() + "!"));
				}
			}
		} else {
			for (int i = 0; i < 100; i++) {
				Bukkit.broadcastMessage("");
			}

			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + "Chat has been cleared by the terminal."));
		}
		return true;
	}
}