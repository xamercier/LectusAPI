package net.lectusAPI.utils;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemStackUtils {
	
	public static ItemStack create(Material material, byte data, int nb, String name, List<String> lore) {
		ItemStack item = new ItemStack(material, nb, data);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(name);
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		item.setAmount(nb);
		return item;
	}
	
	@Deprecated
	public static ItemStack create(int id, byte data, int nb, String name, List<String> lore) {
		ItemStack item = new ItemStack(id, nb, data);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(name);
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		item.setAmount(nb);
		return item;
	}
	
	public static ItemStack getOwnedHead(Player player) {
		ItemStack head = new ItemStack(Material.SKULL_ITEM);
		SkullMeta meta = (SkullMeta) head.getItemMeta();
		head.setDurability((short) 3);
		meta.setOwner(player.getName());
		meta.setDisplayName(player.getName());
		head.setItemMeta(meta);
		return head;
	}
}
