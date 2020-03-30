package net.lectusAPI.events;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import net.lectusAPI.MainLectusApi;

public class EventsManager {
	
	public MainLectusApi pl;
	
	public EventsManager(MainLectusApi MainLectusApi) {
		this.pl = MainLectusApi;
	}
	
	public void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new JoinEvent(), pl);
		pm.registerEvents(new QuitEvent(), pl);
		pm.registerEvents(new ChatEvent(), pl);
	}
	
}
