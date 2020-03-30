package net.lectusAPI.utils;

import org.bukkit.entity.Player;
import org.json.JSONObject;

import ca.xamercier.hal.spigot.halClient.thread.MainHalClientThread;

public class SendPlayerViaBungee {
	
	public static void sendPlayer(Player p, String srvType) {
		JSONObject registerOnNode = new JSONObject();
		registerOnNode.put("player", p.getName());
		registerOnNode.put("typeOfServer", srvType);
		MainHalClientThread.getClient().send(registerOnNode.toString());
	}

}
