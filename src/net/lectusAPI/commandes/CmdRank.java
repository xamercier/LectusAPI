package net.lectusAPI.commandes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.lectusAPI.cache.PlayerCache;
import net.lectusAPI.grade.Rank;
import net.lectusAPI.utils.TimeManager;

public class CmdRank implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Rank.DEVELOPPEUR.hasPermission(player)) {
				if (args.length == 3) {
					Player target = Bukkit.getPlayer(args[0]);
					if (target != null) {
						Rank rank = Rank.rankByString(args[1]);
						if(rank == Rank.OWNER) {
							player.sendMessage(ChatColor.RED + "Erreur: Vous ne pouvez pas donner ce rang!");
							return false;
						}
						if (rank != null) {
							String timeString = args[2];
							Long rankTime;
							if (timeString.equalsIgnoreCase("def")) {
								rankTime = (long) -1;
							} else if (TimeManager.isCorrectFormat(timeString)) {
								rankTime = TimeManager.get().convertFromNow(timeString);
							} else {
								player.sendMessage(ChatColor.RED + "Erreur: Format du temps incorrect !");
								player.sendMessage(TimeManager.get().getTimeHelp());
								return false;
							}
							if(PlayerCache.getCacheByPlayer(target).getRank() == Rank.OWNER) {
								player.sendMessage(ChatColor.RED + "Erreur: Vous ne pouvez pas changer le grade de ce joueur!");
								return false;
							}
							player.sendMessage(ChatColor.YELLOW + "/Rank: Vous avez définit le rang " + rank.getDisplayName()
									+ ChatColor.YELLOW + " Ã  " + target.getName() + " Temps: " + timeString);
							PlayerCache.getCacheByPlayer(target).setRank(rank, rankTime);
						} else {
							player.sendMessage(ChatColor.RED + "Erreur: Le rang " + args[1] + " n'existe pas.");
							player.sendMessage(ChatColor.RED + "Liste des rang disponibles: \n" + Rank.RANK_LIST);
						}
					} else {
						player.sendMessage(
								ChatColor.RED + "Erreur: Le joueur " + args[0] + " n'est pas en ligne ou n'existe pas.");
					}
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("list")) {
						player.sendMessage(ChatColor.RED + "Liste des rang disponibles: \n" + Rank.RANK_LIST);
					} else {
						player.sendMessage(ChatColor.RED + "Utilisation: /rank <player> <rang> <time>");
						player.sendMessage(ChatColor.RED + "Utilisation: /rank <list>");
					}
				} else {
					player.sendMessage(ChatColor.RED + "Utilisation: /rank <player> <rang> <time>");
					player.sendMessage(ChatColor.RED + "Utilisation: /rank <list>");
				}
			} else {
				player.sendMessage(ChatColor.RED + "Erreur: Tu n'a pas la permission (rank.developpeur) pour faire cette commande!");
			}
		} else {
				if (args.length == 3) {
					Player target = Bukkit.getPlayer(args[0]);
					if (target != null) {
						Rank rank = Rank.rankByString(args[1]);
						if (rank != null) {
							String timeString = args[2];
							Long rankTime;
							if (timeString.equalsIgnoreCase("def")) {
								rankTime = (long) -1;
							} else if (TimeManager.isCorrectFormat(timeString)) {
								rankTime = TimeManager.get().convertFromNow(timeString);
							} else {
								sender.sendMessage(ChatColor.RED + "Erreur: Format du temps incorrect !");
								sender.sendMessage(TimeManager.get().getTimeHelp());
								return false;
							}
							sender.sendMessage(ChatColor.YELLOW + "/Rank: Vous avez définit le rang " + rank.getDisplayName()
									+ ChatColor.YELLOW + " Ã  " + target.getName() + " Temps: " + timeString);
							target.sendMessage(ChatColor.YELLOW + "Information: Le serveur" + " vous Ã  mis le rang "
									+ rank.getDisplayName() + ChatColor.YELLOW + " Temps: " + timeString);
							target.sendMessage(ChatColor.YELLOW
									+ "Une reconnexion sera probablement necessaire pour que le grade s'affecte");
							PlayerCache.getCacheByPlayer(target).setRank(rank, rankTime);
						} else {
							sender.sendMessage(ChatColor.RED + "Erreur: Le rang " + args[1] + " n'existe pas.");
							sender.sendMessage(ChatColor.RED + "Liste des rang disponibles: \n" + Rank.RANK_LIST);
						}
					} else {
						sender.sendMessage(
								ChatColor.RED + "Erreur: Le joueur " + args[0] + " n'est pas en ligne ou n'existe pas.");
					}
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("list")) {
						sender.sendMessage(ChatColor.RED + "Erreur: Liste des rang disponibles: \n" + Rank.RANK_LIST);
					} else {
						sender.sendMessage(ChatColor.RED + "Utilisation: /rank <sender> <rang> <time>");
						sender.sendMessage(ChatColor.RED + "Utilisation: /rank <list>");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "Utilisation: /rank <sender> <rang> <time>");
					sender.sendMessage(ChatColor.RED + "Utilisation: /rank <list>");
				}
		}
		return false;
	}
}
