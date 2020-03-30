package net.lectusAPI.commandes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.lectusAPI.grade.Rank;
import net.lectusAPI.utils.ServerUtils;
import net.lectusAPI.utils.StringUtils;

public class CmdBroadcast implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(Rank.MODO.hasPermission(player)) {
				if(args.length >= 2) {
					StringBuilder msg = new StringBuilder(); 
					for (int i = 1; i < args.length; i++) {
						msg.append(args[i]).append(" ");
					}
					String message = msg.toString().trim();
					String broadcast = ChatColor.YELLOW + "[Message] " + ChatColor.RED + player.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE + StringUtils.formatColor(message);
					if(args[0].equalsIgnoreCase("all")) {
						ServerUtils.sendMessageToAllServer(broadcast, player);
					}
					else if(args[0].equalsIgnoreCase("server")) {
						Bukkit.broadcastMessage(broadcast);
					}
					else {
						player.sendMessage(ChatColor.RED + "Utilisation: /broadcast <all|server> <message>");
					}
				} else {
					player.sendMessage(ChatColor.RED + "Utilisation: /broadcast <all|server> <message>");
				}
			} else {
				player.sendMessage(ChatColor.RED + "Erreur: Tu n'a pas la permission (rank.modo) pour faire cette commande!");
			}
		}
		
		return true;
	}
	
}
