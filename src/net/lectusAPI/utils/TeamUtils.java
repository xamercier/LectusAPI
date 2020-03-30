package net.lectusAPI.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * @author xamercier
 */
public class TeamUtils {

	private static TeamUtils instance = null;

	public static TeamUtils getInstance() {
		if (instance == null) {
			instance = new TeamUtils();
		}
		return instance;
	}

	@SuppressWarnings("deprecation")
	public void addPlayerToTeam(Player p, String team) {
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		Team t = board.getTeam(team);
		t.addPlayer(p);
	}

	@SuppressWarnings("deprecation")
	public Team getTeamOfPlayer(Player p) {
		for (Team team : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()) {
			if (team.hasPlayer(p)) {
				return team;
			}
		}
		return null;
	}

	public void refresh() {
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		for (Player o : Bukkit.getOnlinePlayers()) {
			o.setScoreboard(board);
		}
	}

	public String getTeamPrefix(Team team) {
		return team.getPrefix().toString();
	}

	@SuppressWarnings("deprecation")
	public boolean containsPlayerInTeam(Player p, String team) {
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		Team t = board.getTeam(team);
		if (t.hasPlayer(p)) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	public void removePlayerOfTeam(Player p, String team) {
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		Team t = board.getTeam(team);
		t.removePlayer(p);
	}

	public Boolean teamDispobinibility(String team, int maxPlayers) {
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		Team t = board.getTeam(team);
		System.out.println(t.getEntries().size());
		if (t.getEntries().size() < maxPlayers) {
			return true;
		} else {
			return false;
		}
	}

	public int getNumberOfPlayerInTeam(String team) {
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		Team t = board.getTeam(team);
		return t.getEntries().size();
	}

	public Player getRandomPlayerInTeam(String team) {
		List<OfflinePlayer> slt = new ArrayList<>();
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		Team t = board.getTeam(team);
		for (String st : t.getEntries()) {
			Player p = Bukkit.getPlayer(st);
			slt.add(p);
		}
		int r = RandomUtils.getInstance().random(slt.size() - 1, 0);
		return (Player) slt.get(r);
	}

	public void createTeam(String team, String prefix) {
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		Team t = board.getTeam(team);
		if (t == null) {
			t = board.registerNewTeam(team);
			t.setPrefix(prefix);
			t.setDisplayName("");
			t.setNameTagVisibility(NameTagVisibility.ALWAYS);
		} else {
			t.setPrefix(prefix);
			t.setNameTagVisibility(NameTagVisibility.ALWAYS);
		}
	}

	public void createTeam(String team, String prefix, String suffix) {
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		Team t = board.getTeam(team);
		if (team.length() > 16) {
			team = team.substring(0, 17);
		}
		if (t == null) {
			t = board.registerNewTeam(team);
			t.setPrefix(prefix);
			t.setSuffix(suffix);
			t.setDisplayName("");
			t.setNameTagVisibility(NameTagVisibility.ALWAYS);
		} else {
			t.setPrefix(prefix);
			t.setSuffix(suffix);
			t.setNameTagVisibility(NameTagVisibility.ALWAYS);
		}
	}

	public Team getTeam(String teamName) {
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		Team team = board.getTeam(teamName);
		if (team != null) {
			return team;
		} else {
			return null;
		}
	}

	public void deleteTeam(String team) {
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		Team t = board.getTeam(team);
		if (t == null) {
			return;
		} else {
			t.unregister();
		}
	}

}
