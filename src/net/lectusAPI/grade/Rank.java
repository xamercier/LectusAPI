package net.lectusAPI.grade;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.lectusAPI.cache.PlayerCache;

public enum Rank {
	
	JOUEUR(0, "joueur", ChatColor.GRAY + "Joueur", ChatColor.GRAY, ChatColor.GRAY + "Joueur"),
	ALPHA(5, "alpha", ChatColor.AQUA + "[Alpha]", ChatColor.AQUA, ChatColor.AQUA + "[Alpha]"),
	MINIVIP(6, "minivip", ChatColor.BLUE + "[Mini-Vip]", ChatColor.BLUE, ChatColor.BLUE + "[Mini-Vip]"),
	VIP(10, "vip", ChatColor.GOLD + "[VIP]", ChatColor.GOLD, ChatColor.GOLD + "[VIP]"),
	VIPPLUS(11, "vip+", ChatColor.GOLD + "[VIP+]", ChatColor.GOLD, ChatColor.GOLD + "[VIP+]"),
	MEGAVIP(12, "mvip", ChatColor.DARK_AQUA + "[MVIP]", ChatColor.DARK_AQUA, ChatColor.DARK_AQUA + "[MVIP]"),
	YOUTUBEUR(20, "youtubeur", ChatColor.GOLD + "[Youtubeur]", ChatColor.GOLD, ChatColor.GOLD + "[YTB]"),
	BUILDEUR(40, "buildeur", ChatColor.GREEN + "[Buildeur]", ChatColor.GREEN, ChatColor.GREEN + "[Build]"),
	MODO(50, "modo", ChatColor.BLUE + "[Moderateur]", ChatColor.BLUE, ChatColor.BLUE + "[Mod]"),
	SUPERMODO(60, "supermodo", ChatColor.DARK_GREEN + "[Super-Moderateur]", ChatColor.DARK_GREEN, ChatColor.DARK_GREEN + "[Super-Mod]"),
	DEVELOPPEUR(70, "developpeur", ChatColor.YELLOW + "[Developpeur]", ChatColor.YELLOW, ChatColor.YELLOW + "[Dev]"),
	SYSADMIN(90,"sysadmin", ChatColor.AQUA + "[Sys.Admin]", ChatColor.AQUA, ChatColor.AQUA + "[Sys.Admin]"),
	ADMIN(100, "admin", ChatColor.RED + "[Administrateur]", ChatColor.RED, ChatColor.RED + "[Admin]"),
	OWNER(1000, "owner", ChatColor.RED + "[Administrateur]", ChatColor.RED, ChatColor.RED + "[Admin]");
	
	private int							power;
	private String						name;
	private String						displayName;
	private ChatColor					colorTag;
	private String						shortName;
	public static Map<Integer, Rank>	grade	= new HashMap<>();
	public static final String RANK_LIST = ChatColor.GRAY + "Joueur" + "\n" +
			ChatColor.AQUA + "Alpha" + "\n" +
			ChatColor.BLUE + "MiniVip" + "\n" +
			ChatColor.GOLD + "VIP" + "\n" +
			ChatColor.GOLD + "Vip+" + "\n" +
			ChatColor.DARK_AQUA + "MVIP" + "\n" +
			ChatColor.GOLD + "Youtubeur" + "\n" +
			ChatColor.GREEN + "Buildeur" + "\n" +
			ChatColor.BLUE + "Modo" + "\n" +
			ChatColor.DARK_GREEN + "SuperModo" + "\n" +
			ChatColor.YELLOW + "Developpeur" + "\n" +
			ChatColor.AQUA + "SysAdmin" + "\n" +
			ChatColor.RED + "Admin";
	
	Rank(int power, String name, String displayName, ChatColor tag, String shortName) {
		this.power = power;
		this.name = name;
		this.displayName = displayName;
		this.colorTag = tag;
		this.shortName = shortName;
	}
	
	static {
		for (Rank r : Rank.values()) {
			grade.put(r.getPower(), r);
		}
	}
	
	public int getPower() {
		return power;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public ChatColor getTag() {
		return colorTag;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public boolean hasPermission(Player player) {
		return PlayerCache.getCacheByPlayer(player).getRank().getPower() >= this.power;
	}
	
	
	public static Rank powerToRank(int power) {
		return grade.get(power);
	}
	
	public static Rank rankByString(String name) {
		for (Rank rank : values()) {
			if (rank.getName().equalsIgnoreCase(name)) { return rank; }
		}
		return null;
	}
	
}