package net.lectusAPI.utils;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LectusItem {
	
	private ItemStack itemStack;
	
	public LectusItem(Material material, String name) {
		this(material, name, 1);
	}
	
	public LectusItem(Material material, String name, Integer amount) {
		this(material, name, amount, (short) 0);
	}
	
	public LectusItem(Material material, String name, Integer amount, short damage) {
		ItemStack i = new ItemStack(material, amount, damage);
		ItemMeta meta = i.getItemMeta();
		meta.setDisplayName(name);
		i.setItemMeta(meta);
		
		this.itemStack = i;
	}
	
	public LectusItem(ItemStack itemStack) {
		this.itemStack = itemStack;
	}
	
	public LectusItem setName(String name) {
		ItemMeta meta = getItemMeta();
		meta.setDisplayName(name);
		setItemMeta(meta);
		return this;
	}
	
	public LectusItem setLore(String... lore) {
		ItemMeta meta = getItemMeta();
		meta.setLore(Arrays.asList(lore));
		setItemMeta(meta);
		return this;
	}
	
	public String getName() {
		return getItemMeta().getDisplayName();
	}
	
	public ItemStack getItemStack() {
		return itemStack;
	}
	
	public ItemMeta getItemMeta() {
		return this.itemStack.getItemMeta();
	}
	
	public LectusItem setItemMeta(ItemMeta meta) {
		this.itemStack.setItemMeta(meta);
		return this;
	}
}
