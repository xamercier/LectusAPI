package net.lectusAPI.cache;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import net.lectusAPI.MainLectusApi;
import net.lectusAPI.grade.Rank;
import net.lectusAPI.sql.SQLMain;

public class PlayerCache {
	
	private static Map<Player, PlayerCache>	playerCachesList	= new HashMap<>();
	
	private Player							player;
	private int								coins;
	
	// Rank
	private Rank							rank;
	private Long							rankBegin;
	private Long							rankEnd;
	
	private int								baseCoins;
	
	private PlayerCache(Player player) {
		this.player = player;
		playerCachesList.put(this.player, this);
		loadPlayerCache();
	}
	
	public void loadPlayerCache() {
		SQLMain sql = MainLectusApi.getInstance().sql;
		this.coins = sql.getCoins(player);
		this.rank = sql.getRank(player);
		this.rankBegin = sql.getRankBeginTime(player);
		this.rankEnd = sql.getRankEndTime(player);
		
		this.baseCoins = this.coins;
	}
	
	public void addCoins(int amount) {
		this.coins += amount;
	}
	
	public void removeCoins(int amount) {
		int newbalance = this.coins - amount;
		if (newbalance < 0) {
			newbalance = 0;
		}
		this.coins = newbalance;
	}
	
	public int getCoins() {
		return coins;
	}
	
	public Rank getRank() {
		if (this.rankEnd != -1) {
			if (this.rankEnd < System.currentTimeMillis()) {
				setRank(Rank.JOUEUR, (long) -1);
				return Rank.JOUEUR;
			}
		}
		return rank;
	}
	
	public void setRank(Rank rank, Long end) {
		this.rank = rank;
		this.rankBegin = System.currentTimeMillis();
		this.rankEnd = end;
		save(false);
	}
	
	public void save(boolean removeAfter) {
		SQLMain sql = MainLectusApi.getInstance().sql;
		
		// Coins
		int otherCoins = sql.getCoins(this.player);
		sql.setCoins(this.player, (otherCoins - this.baseCoins) + this.coins);
		
		// Rank
		sql.setRank(this.player, this.rank, this.rankBegin, this.rankEnd);
		
		if (removeAfter) {
			playerCachesList.remove(this.player);
		}
	}
	
	public static PlayerCache getCacheByPlayer(Player player) {
		if (!playerCachesList.containsKey(player)) {
			return new PlayerCache(player);
		} else {
			return playerCachesList.get(player);
		}
	}
}
