package net.lectusAPI.commandes;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.JSONObject;

import ca.xamercier.hal.spigot.halClient.thread.MainHalClientThread;
import net.lectusAPI.MainLectusApi;
import net.md_5.bungee.api.ChatColor;

public class CmdHub implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String laber, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if(!MainLectusApi.getInstance().getSql().getGameState(player).equalsIgnoreCase("hub")) {
			JSONObject registerOnNode = new JSONObject();
			registerOnNode.put("player", player.getName());
			registerOnNode.put("typeOfServer", "hub");
			MainHalClientThread.getClient().send(registerOnNode.toString());
			} else {
				player.sendMessage(ChatColor.RED + "Erreur: Vous êtes deja sur un Hub!");
			}
		}
		return false;
	}
	
	
}
