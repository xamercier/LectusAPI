package net.lectusAPI.commandes;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.lectusAPI.MainLectusApi;
import net.lectusAPI.grade.Rank;

public class CmdMod implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(Rank.MODO.hasPermission(p)) {
				if(MainLectusApi.getInstance().getSql().hasModMode(p)) {
					MainLectusApi.getInstance().getSql().setModMode(p, false);
					p.sendMessage(ChatColor.AQUA + "/Mod: Mode de moderation desactiver.");
				} else {
					MainLectusApi.getInstance().sql.setModMode(p, true);
					p.sendMessage(ChatColor.AQUA + "/Mod: Mode de moderation activer.");
				}
			} else {
				p.sendMessage(ChatColor.RED + "Erreur: Tu n'a pas la permission (rank.modo) pour faire cette commande!");
			}
		}
		
		return false;
	}

}
