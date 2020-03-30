package net.lectusAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.lectusAPI.bStats.Metrics;
import net.lectusAPI.cache.PlayerCache;
import net.lectusAPI.commandes.CmdBroadcast;
import net.lectusAPI.commandes.CmdCoins;
import net.lectusAPI.commandes.CmdFly;
import net.lectusAPI.commandes.CmdGamemode;
import net.lectusAPI.commandes.CmdHelp;
import net.lectusAPI.commandes.CmdHub;
import net.lectusAPI.commandes.CmdMod;
import net.lectusAPI.commandes.CmdPlugins;
import net.lectusAPI.commandes.CmdRank;
import net.lectusAPI.commandes.CmdTP;
import net.lectusAPI.config.ConfigurationManager;
import net.lectusAPI.events.EventsManager;
import net.lectusAPI.sql.SQLMain;
import net.lectusAPI.thread.SQLThread;
import net.lectusAPI.utils.ErrorCatcher;
import net.lectusAPI.utils.ServerUtils;

public class MainLectusApi extends JavaPlugin {

	private static MainLectusApi instance;
	private ConfigurationManager configurationManager;
	public SQLMain sql;
	public String APIMODE;

	public List<Player> VanishedPlayers = new ArrayList<>();
	Thread SQLThread = new Thread(new SQLThread());

	public SQLMain getSql() {
		return sql;
	}

	public void onLoad() {
	}

	@SuppressWarnings("unused")
	public void onEnable() {
		super.onEnable();
		instance = this;

        Metrics metrics = new Metrics(this);
		
		try {
			this.configurationManager = new ConfigurationManager();
		} catch (IOException e) {
			ErrorCatcher.catchError(e);
		}

		sql = new SQLMain("jdbc:mysql://", configurationManager.getSQLhost(), configurationManager.getSQLdatabase(),
				configurationManager.getSQLuser(), configurationManager.getSQLpassword());
		sql.connexion();

		if (configurationManager.getServerMode().equalsIgnoreCase("other")) {
			System.out.println("API: Other Mode Activated");
			APIMODE = "other";
		} else {
			System.out.println("API: Game Mode Activated");
			APIMODE = "game";
		}

		getCommand("help").setExecutor(new CmdHelp());
		getCommand("?").setExecutor(new CmdHelp());
		getCommand("aide").setExecutor(new CmdHelp());

		getCommand("coins").setExecutor(new CmdCoins());
		getCommand("rank").setExecutor(new CmdRank());
		getCommand("broadcast").setExecutor(new CmdBroadcast());
		getCommand("broadcast").setAliases(Arrays.asList("bc"));
		getCommand("fly").setExecutor(new CmdFly());

		getCommand("gamemode").setExecutor(new CmdGamemode());
		getCommand("gamemode").setAliases(Arrays.asList("gm"));

		getCommand("plugins").setExecutor(new CmdPlugins());
		getCommand("plugin").setExecutor(new CmdPlugins());
		getCommand("pl").setExecutor(new CmdPlugins());

		getCommand("mod").setExecutor(new CmdMod());
		
		getCommand("tp").setExecutor(new CmdTP());

		getCommand("hub").setExecutor(new CmdHub());

		new EventsManager(this).registerEvents();

		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new ServerUtils());

		for (Player pls : Bukkit.getOnlinePlayers()) {
			PlayerCache.getCacheByPlayer(pls).loadPlayerCache();
		}

		Thread SQLThread = new Thread(new SQLThread());
		SQLThread.start();
	}

	@SuppressWarnings("deprecation")
	public void onDisable() {
		SQLThread.stop();
		for (Player pls : Bukkit.getOnlinePlayers()) {
			PlayerCache.getCacheByPlayer(pls).save(false);
		}
		sql.deconnexion();
		super.onDisable();
	}

	public static MainLectusApi getInstance() {
		return instance;
	}

	public ConfigurationManager getConfigurationManager() {
		return configurationManager;
	}

	public String getAPIMODE() {
		return APIMODE;
	}

}