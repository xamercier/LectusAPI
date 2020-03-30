package net.lectusAPI.commandes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.lectusAPI.MainLectusApi;
import net.md_5.bungee.api.ChatColor;

public class CmdPlugins implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			p.sendMessage(ChatColor.GOLD + "-----------------------------------------------------");
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			p.sendMessage(ChatColor.GOLD + "Debug: " + dateFormat.format(date));
			p.sendMessage(ChatColor.YELLOW + "Environement: " + ChatColor.AQUA + "Developpment");
			p.sendMessage(ChatColor.YELLOW + "Server: " + MainLectusApi.getInstance().getConfigurationManager().getSrvPrefix() + Bukkit.getServer().getPort());
			p.sendMessage(ChatColor.YELLOW + "Plugins:");
			for(Plugin pl : Bukkit.getServer().getPluginManager().getPlugins()) {
				p.sendMessage(ChatColor.GRAY + "> " + ChatColor.AQUA + pl.getName());
			}
			p.sendMessage(ChatColor.GOLD + "-----------------------------------------------------");
		}
		
		return false;
	}

}