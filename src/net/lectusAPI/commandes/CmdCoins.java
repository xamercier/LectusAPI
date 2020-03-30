package net.lectusAPI.commandes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.lectusAPI.cache.PlayerCache;
import net.lectusAPI.grade.Rank;

public class CmdCoins implements CommandExecutor {

	public CmdCoins() {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				int balance = PlayerCache.getCacheByPlayer(p).getCoins();
				p.sendMessage(ChatColor.GRAY + "Vous avez : " + ChatColor.YELLOW + balance + ChatColor.GRAY + " coins");
			}
			if (args.length >= 1) {
				if (args[0].equalsIgnoreCase("add")) {
					if (Rank.SUPERMODO.hasPermission(p)) {
						if (args.length == 1 || args.length == 2) {
							p.sendMessage(ChatColor.RED + "Utilisation: /coins add <montant> <joueur> ");
						}
						if (args.length == 3) {
							Player cible = Bukkit.getPlayer(args[2]);
							if (cible != null) {
								long montant = Long.valueOf(args[1]);
								if (montant > Integer.MAX_VALUE) {
									montant = Integer.MAX_VALUE;
								}
								PlayerCache.getCacheByPlayer(cible).addCoins((int) montant);
								p.sendMessage(ChatColor.RED + "Coins: Vous ajoutez : " + ChatColor.GREEN + montant
										+ ChatColor.RED + " coins a " + ChatColor.GREEN + cible.getName());
							}
						}
					} else {
						p.sendMessage(ChatColor.RED + "Erreur: Tu n'a pas la permission (rank.supermodo) pour faire cette commande!");
					}
				}
				if (args[0].equalsIgnoreCase("remove")) {
					if (Rank.SUPERMODO.hasPermission(p)) {
						if (args.length == 1 || args.length == 2) {
							p.sendMessage(ChatColor.RED + "Utilisation: /coins remove <montant> <joueur>");
						}
						if (args.length == 3) {
							Player cible = Bukkit.getPlayer(args[2]);
							if (cible != null) {
								int montant = Integer.valueOf(args[1]);
								int balance = PlayerCache.getCacheByPlayer(cible).getCoins();
								if (montant > balance) {
									montant = balance;
									p.sendMessage(ChatColor.RED + "Coins: Le montant supprimer sera de " + ChatColor.GRAY
											+ balance + ChatColor.RED + " coins car le joueur n'en as pas asser !");
								}
								PlayerCache.getCacheByPlayer(cible).removeCoins(montant);
								p.sendMessage(ChatColor.GREEN + "Coins: Vous venez de retirer : " + ChatColor.RED + montant
										+ ChatColor.GREEN + " coins a " + ChatColor.RED + cible.getName());
							}
						}
					} else {
						p.sendMessage(ChatColor.RED + "Erreur: Tu n'a pas la permission (rank.supermodo) pour faire cette commande!");
					}
				}

			}
		}
		return false;
	}

}
