package net.lectusAPI.sql;

import java.sql.*;
import java.util.ArrayList;

import org.bukkit.entity.Player;

import net.lectusAPI.cache.PlayerCache;
import net.lectusAPI.grade.Rank;

public class SQLMain {

	private Connection connexion;
	private String urlbase, host, database, user, pass;

	public SQLMain(String urlbase, String host, String databse, String user, String pass) {
		this.urlbase = urlbase;
		this.host = host;
		this.database = databse;
		this.user = user;
		this.pass = pass;
	}

	public void connexion() {
		if (!isConnected()) {
			try {
				connexion = DriverManager.getConnection(urlbase + host + "/" + database, user, pass);
				System.out.println("La base de donnees a bien ete connecte !");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deconnexion() {
		if (isConnected()) {
			try {
				connexion.close();
				System.out.println("La base de donnees a bien ete deconnecte !");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isConnected() {
		return connexion != null;
	}

	public void createAccount(Player player) {
		connexion();
		if (!hasAccount(player)) {
			try {
				PreparedStatement q = connexion
						.prepareStatement("INSERT INTO joueurs(uuid,coins,rank,pseudo) VALUES (?,?,?,?);");
				q.setString(1, player.getUniqueId().toString());
				q.setInt(2, 100);
				q.setInt(3, 0);
				q.setString(4, player.getName());
				q.executeUpdate();
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateAccount(Player player) {
		connexion();
		if (!hasAccount(player)) {
			createAccount(player);
		}
		try {
			PreparedStatement updatePseudo = connexion.prepareStatement("UPDATE joueurs SET pseudo = ? WHERE uuid = ?");
			updatePseudo.setString(1, player.getName());
			updatePseudo.setString(2, player.getUniqueId().toString());
			updatePseudo.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean hasAccount(Player player) {
		connexion();
		try {
			PreparedStatement q = connexion.prepareStatement("SELECT uuid FROM joueurs WHERE uuid = ?");
			q.setString(1, player.getUniqueId().toString());
			ResultSet resultat = q.executeQuery();
			while (resultat.next()) {
				q.close();
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getCoins(Player player) {
		connexion();
		try {
			PreparedStatement q = connexion.prepareStatement("SELECT coins FROM joueurs WHERE uuid = ?");
			q.setString(1, player.getUniqueId().toString());
			int balance = 0;
			ResultSet rs = q.executeQuery();
			while (rs.next()) {
				balance = rs.getInt("coins");
			}
			q.close();
			return balance;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void addCoins(Player player, int amount) {
		connexion();
		int balance = PlayerCache.getCacheByPlayer(player).getCoins();
		int newbalance = balance + amount;
		try {
			PreparedStatement rs = connexion.prepareStatement("UPDATE joueurs SET coins = ? WHERE uuid = ?");
			rs.setInt(1, newbalance);
			rs.setString(2, player.getUniqueId().toString());
			rs.executeUpdate();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setCoins(Player player, int amount) {
		connexion();
		try {
			PreparedStatement rs = connexion.prepareStatement("UPDATE joueurs SET coins = ? WHERE uuid = ?");
			rs.setInt(1, amount);
			rs.setString(2, player.getUniqueId().toString());
			rs.executeUpdate();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeCoins(Player player, int amount) {
		connexion();
		int balance = PlayerCache.getCacheByPlayer(player).getCoins();
		int newbalance = balance - amount;
		if (newbalance < 0) {
			newbalance = 0;
		}
		try {
			PreparedStatement rs = connexion.prepareStatement("UPDATE joueurs SET coins = ? WHERE uuid = ?");
			rs.setInt(1, newbalance);
			rs.setString(2, player.getUniqueId().toString());
			rs.executeUpdate();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setRank(Player player, Rank rank, Long begin, Long end) {
		connexion();
		try {
			PreparedStatement rs = connexion
					.prepareStatement("UPDATE joueurs SET rank = ?, rank_begin = ?, rank_end = ? WHERE uuid = ?");
			rs.setInt(1, rank.getPower());
			rs.setLong(2, begin);
			rs.setLong(3, end);
			rs.setString(4, player.getUniqueId().toString());
			rs.executeUpdate();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Rank getRank(Player player) {
		connexion();
		try {
			PreparedStatement q = connexion.prepareStatement("SELECT rank FROM joueurs WHERE uuid = ?");
			q.setString(1, player.getUniqueId().toString());
			int power = 0;
			ResultSet rs = q.executeQuery();
			while (rs.next()) {
				power = rs.getInt("rank");
			}
			q.close();
			return Rank.powerToRank(power);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Rank.JOUEUR;
	}

	public Long getRankBeginTime(Player player) {
		connexion();
		try {
			PreparedStatement begin = connexion.prepareStatement("SELECT rank_begin FROM joueurs WHERE uuid = ?");
			begin.setString(1, player.getUniqueId().toString());
			ResultSet result = begin.executeQuery();
			if (result.next()) {
				return result.getLong("rank_begin");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public Long getRankEndTime(Player player) {
		connexion();
		try {
			PreparedStatement end = connexion.prepareStatement("SELECT rank_end FROM joueurs WHERE uuid = ?");
			end.setString(1, player.getUniqueId().toString());
			ResultSet result = end.executeQuery();
			if (result.next()) {
				return result.getLong("rank_end");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	
	//MOVED TO BUNGEE
	public void setGameState(Player player, String newGameState) {
		connexion();
		try {
			PreparedStatement ps = connexion.prepareStatement("UPDATE joueurs SET gamestate = ? WHERE uuid = ?;");
			ps.setString(1, newGameState);
			ps.setString(2, player.getUniqueId().toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getGameState(Player player) {
		connexion();
		try {
			PreparedStatement ps = connexion.prepareStatement("SELECT gamestate FROM joueurs WHERE uuid = ?");
			ps.setString(1, player.getUniqueId().toString());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("gamestate");
			}
			return null;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void setServer(Player player, String newServer) {
		connexion();
		try {
			PreparedStatement ps = connexion.prepareStatement("UPDATE joueurs SET server = ? WHERE uuid = ?;");
			ps.setString(1, newServer);
			ps.setString(2, player.getUniqueId().toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getServer(Player player) {
		connexion();
		try {
			PreparedStatement ps = connexion.prepareStatement("SELECT server FROM joueurs WHERE uuid = ?");
			ps.setString(1, player.getUniqueId().toString());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("server");
			}
			return null;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	//MOVED TO BUNGEE
	

	public void setModMode(Player player, boolean newMode) {
		connexion();
		String mode;
		if (newMode) {
			mode = "yes";
		} else {
			mode = "no";
		}
		try {
			PreparedStatement ps = connexion.prepareStatement("UPDATE joueurs SET modMode = ? WHERE uuid = ?;");
			ps.setString(1, mode);
			ps.setString(2, player.getUniqueId().toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean hasModMode(Player player) {
		connexion();
		if (!Rank.MODO.hasPermission(player)) {
			return false;
		}
		try {
			PreparedStatement ps = connexion.prepareStatement("SELECT modMode FROM joueurs WHERE uuid = ?");
			ps.setString(1, player.getUniqueId().toString());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				String mode = rs.getString("modMode");
				if (mode == null){
					return false;
				}
				if (mode.equalsIgnoreCase("yes")) {
					return true;
				} else {
					return false;
				}
			}
			return false;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public void setState(String port, String state) {
		connexion();
		String serverPort = port;
		String serverState = state;
		try {
			PreparedStatement rs = connexion.prepareStatement("UPDATE servers SET state = ? WHERE port = ?");
			rs.setString(1, serverState);
			rs.setString(2, serverPort);
			rs.executeUpdate();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getState(int servPort) {
		connexion();
		try {
			PreparedStatement ps = connexion.prepareStatement("SELECT state FROM servers WHERE port = ?");
			ps.setInt(1, servPort);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("state");
			}
			return null;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void setPlayers(String port, int players) {
		connexion();
		String serverPort = port;
		int serverPlayers = players;
		try {
			PreparedStatement rs = connexion.prepareStatement("UPDATE servers SET players = ? WHERE port = ?");
			rs.setInt(1, serverPlayers);
			rs.setString(2, serverPort);
			rs.executeUpdate();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getServers(String type) {
		connexion();
		ArrayList<String> Servers = new ArrayList<String>();
		try {
			PreparedStatement ps = connexion.prepareStatement("SELECT port FROM servers WHERE type = ?;");
			ps.setString(1, type);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Servers.add(rs.getString("port"));
			}
			return Servers;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
