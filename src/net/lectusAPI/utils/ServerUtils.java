package net.lectusAPI.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.lectusAPI.MainLectusApi;

public class ServerUtils implements PluginMessageListener {
	private static String[] serverList;
	private static ArrayList<String> playerList = new ArrayList<>();

	public static void sendPlayerToServer(Player player, String server) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(server);
		player.sendPluginMessage(MainLectusApi.getInstance(), "BungeeCord", out.toByteArray());
	}

	public static void sendMessageToAllServer(final String message, final Player sender) {
		getServerList(sender);
		Bukkit.getScheduler().runTaskLater(MainLectusApi.getInstance(), () -> {
			playerList.clear();
			getPlayers(sender);

			Bukkit.getScheduler().runTaskLater(MainLectusApi.getInstance(), () -> {
				for (String player : playerList) {
					ByteArrayDataOutput out = ByteStreams.newDataOutput();
					out.writeUTF("Message");
					out.writeUTF(player);
					out.writeUTF(message);
					sender.sendPluginMessage(MainLectusApi.getInstance(), "BungeeCord", out.toByteArray());
				}
			}, 2L);
		}, 2L);
	}

	private static void getServerList(Player player) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("GetServers");
		player.sendPluginMessage(MainLectusApi.getInstance(), "BungeeCord", out.toByteArray());
	}

	private static void getPlayers(Player player) {
		for (String server : serverList) {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("PlayerList");
			out.writeUTF(server);
			player.sendPluginMessage(MainLectusApi.getInstance(), "BungeeCord", out.toByteArray());
		}
	}

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if (!channel.equals("BungeeCord")) {
			return;
		}
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subChannel = in.readUTF();
		if (subChannel.equals("GetServers")) {
			serverList = in.readUTF().split(", ");
		}
		if (subChannel.equals("PlayerList")) {
			@SuppressWarnings("unused")
			String server = in.readUTF();

			String[] pls = in.readUTF().split(", ");
			for (String s : pls) {
				playerList.add(s);
			}
		}
	}

}
