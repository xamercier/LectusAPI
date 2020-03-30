package net.lectusAPI.commandes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.lectusAPI.MainLectusApi;
import net.lectusAPI.cache.PlayerCache;
import net.lectusAPI.grade.Rank;
import net.lectusAPI.utils.StringUtils;

public class CmdGamemode implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String laber, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Rank.BUILDEUR.hasPermission(player)) {
				String gameState = MainLectusApi.getInstance().APIMODE;
				if (gameState.equalsIgnoreCase("game") && !MainLectusApi.getInstance().getSql().hasModMode(player)
						&& PlayerCache.getCacheByPlayer(player).getRank() != Rank.OWNER) {
					player.sendMessage(
							ChatColor.RED + "Erreur: Tu ne peux pas faire cette commande lorsque tu es dans un jeux!");
				} else if (gameState.equalsIgnoreCase("other")
						|| PlayerCache.getCacheByPlayer(player).getRank() == Rank.OWNER) {
					if (args.length == 0) {
						switch (player.getGameMode()) {
						case CREATIVE:
							player.setGameMode(GameMode.ADVENTURE);
							player.sendMessage(ChatColor.GREEN + "Gamemode: Vous avez été mis en adventure!");
							break;
						case SURVIVAL:
						case ADVENTURE:
							player.setGameMode(GameMode.CREATIVE);
							player.sendMessage(ChatColor.GREEN + "Gamemode: Vous avez été mis en créatif!");
							break;
						default:
							player.setGameMode(GameMode.ADVENTURE);
							player.sendMessage(ChatColor.GREEN + "Gamemode: Vous avez été mis en adventure!");
							break;
						}
					} else if (args.length == 1) {
						String gamemode = args[0];
						switch (gamemode.toLowerCase()) {
						case "creatif":
						case "creative":
						case "c":
						case "1":
							player.setGameMode(GameMode.CREATIVE);
							player.sendMessage(ChatColor.GREEN + "Gamemode: Vous avez été mis en gamemode créatif!");
							break;
						case "survie":
						case "survival":
						case "s":
						case "0":
							player.setGameMode(GameMode.SURVIVAL);
							player.sendMessage(ChatColor.GREEN + "Gamemode: Vous avez été mis en gamemode survie!");
							break;
						case "adventure":
						case "aventure":
						case "a":
						case "2":
							player.setGameMode(GameMode.ADVENTURE);
							player.sendMessage(ChatColor.GREEN + "Gamemode: Vous avez été mis en gamemode aventure!");
							break;
						case "spectateur":
						case "spectator":
						case "spec":
						case "3":
							player.setGameMode(GameMode.SPECTATOR);
							player.sendMessage(ChatColor.GREEN + "Gamemode: Vous avez été mis en gamemode specateur!");
							break;
						default:
							player.sendMessage(ChatColor.RED + "Erreur: Ce gamemode n'existe pas!");
							break;
						}
					} else if (args.length == 2) {
						String gamemode = args[0];
						Player target = Bukkit.getPlayer(args[1]);
						if (target == null) {
							player.sendMessage(ChatColor.RED + "Erreur: Le joueur " + ChatColor.YELLOW + args[1]
									+ ChatColor.RED + " n'est pas en ligne!");
						} else {
							switch (gamemode.toLowerCase()) {
							case "creatif":
							case "creative":
							case "c":
							case "1":
								target.setGameMode(GameMode.CREATIVE);
								target.sendMessage(
										StringUtils.format(ChatColor.YELLOW + "Gamemode: {0} vous a mis en {1}",
												player.getName(), "gamemode créatif!"));
								player.sendMessage(ChatColor.GREEN + StringUtils
										.format("Gamemode: Vous avez mis {0} en créatif!", target.getName()));
								break;
							case "survie":
							case "survival":
							case "s":
							case "0":
								target.setGameMode(GameMode.SURVIVAL);
								target.sendMessage(
										StringUtils.format(ChatColor.YELLOW + "Gamemode: {0} vous a mis en {1}",
												player.getName(), "gamemode survie!"));
								player.sendMessage(ChatColor.GREEN + StringUtils
										.format("Gamemode: Vous avez mis {0} en survie!", target.getName()));
								break;
							case "adventure":
							case "aventure":
							case "a":
							case "2":
								target.setGameMode(GameMode.ADVENTURE);
								target.sendMessage(
										StringUtils.format(ChatColor.YELLOW + "Gamemode: {0} vous a mis en {1}",
												player.getName(), "gamemode aventure!"));
								player.sendMessage(ChatColor.GREEN + StringUtils
										.format("Gamemode: Vous avez mis {0} en gamemode aventure!", target.getName()));
								break;

							case "spectateur":
							case "spectator":
							case "spec":
							case "3":
								target.setGameMode(GameMode.SPECTATOR);
								target.sendMessage(
										StringUtils.format(ChatColor.YELLOW + "Gamemode: {0} vous a mis en {1}",
												player.getName(), "Gamemode: gamemode spectateur"));
								player.sendMessage(ChatColor.GREEN + StringUtils.format(
										"Gamemode: Vous avez mis {0} en gamemode specateur!", target.getName()));
								break;
							default:
								player.sendMessage(ChatColor.RED + "Erreur: Ce gamemode n'existe pas!");
								break;
							}
						}
					} else {
						player.sendMessage(ChatColor.RED + "Erreur: Utilisation incorrecte !");
						player.sendMessage(ChatColor.RED + "/gm");
						player.sendMessage(ChatColor.RED + "/gm <mode de jeu>");
						player.sendMessage(ChatColor.RED + "/gm <mode de jeu> <joueur>");
					}
				} else if (gameState.equalsIgnoreCase("game") && MainLectusApi.getInstance().getSql().hasModMode(player)
						|| PlayerCache.getCacheByPlayer(player).getRank() == Rank.OWNER) {
					if (args.length == 0) {
						switch (player.getGameMode()) {
						case CREATIVE:
							player.setGameMode(GameMode.ADVENTURE);
							player.sendMessage(ChatColor.GREEN + "Gamemode: Vous avez été mis en adventure!");
							break;
						case SURVIVAL:
						case ADVENTURE:
							player.setGameMode(GameMode.CREATIVE);
							player.sendMessage(ChatColor.GREEN + "Gamemode: Vous avez été mis en créatif!");
							break;
						default:
							player.setGameMode(GameMode.ADVENTURE);
							player.sendMessage(ChatColor.GREEN + "Gamemode: Vous avez été mis en adventure!");
							break;
						}
					} else if (args.length == 1) {
						String gamemode = args[0];
						switch (gamemode.toLowerCase()) {
						case "creatif":
						case "creative":
						case "c":
						case "1":
							player.setGameMode(GameMode.CREATIVE);
							player.sendMessage(ChatColor.GREEN + "Gamemode: Vous avez été mis en gamemode créatif!");
							break;
						case "survie":
						case "survival":
						case "s":
						case "0":
							player.setGameMode(GameMode.SURVIVAL);
							player.sendMessage(ChatColor.GREEN + "Gamemode: Vous avez été mis en gamemode survie!");
							break;
						case "adventure":
						case "aventure":
						case "a":
						case "2":
							player.setGameMode(GameMode.ADVENTURE);
							player.sendMessage(ChatColor.GREEN + "Gamemode: Vous avez été mis en gamemode aventure!");
							break;
						case "spectateur":
						case "spectator":
						case "spec":
						case "3":
							player.setGameMode(GameMode.SPECTATOR);
							player.sendMessage(ChatColor.GREEN + "Gamemode: Vous avez été mis en gamemode specateur!");
							break;
						default:
							player.sendMessage(ChatColor.RED + "Gamemode: Ce gamemode n'existe pas!");
							break;
						}
					} else if (args.length == 2) {
						String gamemode = args[0];
						Player target = Bukkit.getPlayer(args[1]);
						if (target == null) {
							player.sendMessage(ChatColor.RED + "Erreur: Le joueur " + ChatColor.YELLOW + args[1]
									+ ChatColor.RED + " n'est pas en ligne!");
						} else {
							switch (gamemode.toLowerCase()) {
							case "creatif":
							case "creative":
							case "c":
							case "1":
								target.setGameMode(GameMode.CREATIVE);
								target.sendMessage(
										StringUtils.format(ChatColor.YELLOW + "Gamemode: {0} vous a mis en {1}",
												player.getName(), "gamemode créatif!"));
								player.sendMessage(ChatColor.GREEN + StringUtils
										.format("Gamemode: Vous avez mis {0} en créatif!", target.getName()));
								break;
							case "survie":
							case "survival":
							case "s":
							case "0":
								target.setGameMode(GameMode.SURVIVAL);
								target.sendMessage(
										StringUtils.format(ChatColor.YELLOW + "Gamemode: {0} vous a mis en {1}",
												player.getName(), "gamemode survie!"));
								player.sendMessage(ChatColor.GREEN + StringUtils
										.format("Gamemode: Vous avez mis {0} en survie!", target.getName()));
								break;
							case "adventure":
							case "aventure":
							case "a":
							case "2":
								target.setGameMode(GameMode.ADVENTURE);
								target.sendMessage(
										StringUtils.format(ChatColor.YELLOW + "Gamemode: {0} vous a mis en {1}",
												player.getName(), "gamemode aventure!"));
								player.sendMessage(ChatColor.GREEN + StringUtils
										.format("Gamemode: Vous avez mis {0} en gamemode aventure!", target.getName()));
								break;

							case "spectateur":
							case "spectator":
							case "spec":
							case "3":
								target.setGameMode(GameMode.SPECTATOR);
								target.sendMessage(
										StringUtils.format(ChatColor.YELLOW + "Gamemode: {0} vous a mis en {1}",
												player.getName(), "Gamemode: gamemode spectateur"));
								player.sendMessage(ChatColor.GREEN + StringUtils.format(
										"Gamemode: Vous avez mis {0} en gamemode specateur!", target.getName()));
								break;
							default:
								player.sendMessage(ChatColor.RED + "Erreur: Ce gamemode n'existe pas!");
								break;
							}
						}
					} else {
						player.sendMessage(ChatColor.RED + "Erreur: Utilisation incorrecte !");
						player.sendMessage(ChatColor.RED + "/gm");
						player.sendMessage(ChatColor.RED + "/gm <mode de jeu>");
						player.sendMessage(ChatColor.RED + "/gm <mode de jeu> <joueur>");
					}
				} else {
					player.sendMessage(ChatColor.RED + "Erreur: Tu n'a pas la permission (rank.buildeur) pour faire cette commande!");
				}
			}
			return true;
		}
		return true;
	}
}