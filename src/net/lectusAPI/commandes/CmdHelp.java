package net.lectusAPI.commandes;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class CmdHelp implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			p.sendMessage(ChatColor.RED + "-----------------------------------------------------");
			p.sendMessage(ChatColor.RED + "Le menu de l'aide n'est presentement pas disponible.");
			p.sendMessage(ChatColor.RED + "-----------------------------------------------------");
		}
		return false;
	}
}