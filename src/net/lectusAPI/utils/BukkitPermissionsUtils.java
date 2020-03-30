package net.lectusAPI.utils;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;

public class BukkitPermissionsUtils {

	//PERMISSION MANAGER
	public static PermissionAttachment fetchPlayer(UUID uuid){
	    Plugin plugin = Bukkit.getPluginManager().getPlugin("LectusAPI");
	    Player player = Bukkit.getPlayer(uuid);
	    if (player != null){
	        PermissionAttachment perm = player.addAttachment(plugin);
	        return perm;
	    }
	    else{
	        return null;
	    }

	}
	
	public static void addPermission(UUID uuid, String permission){
	    PermissionAttachment perm = fetchPlayer(uuid);
	    perm.setPermission(permission, true);
	}
	
	public static void removePermission(UUID uuid, String permission){
	    PermissionAttachment perm = fetchPlayer(uuid);
	    perm.unsetPermission(permission);
	}
	//PERMISSION MANAGER
	
}
