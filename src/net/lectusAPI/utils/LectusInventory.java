package net.lectusAPI.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class LectusInventory {
	
	public static ArrayList<LectusInventory> inventorries = new ArrayList<>();
	private String title;
	private Integer size;
	private Inventory inventory;
	
	public LectusInventory(String title, int size) {
		this.title = title;
		this.size = size;
		this.inventory = Bukkit.createInventory(null, size, title);
		
		LectusInventory.inventorries.add(this);
		System.out.println("Invetory registred: " + this.title);
	}
	
	public abstract void buildInventory();
	
	public abstract void onClick(Player player, ItemStack item);
	
	public abstract ItemStack getRepresenter();
	
	public LectusInventory addItem(LectusItem... items) {
		for (LectusItem i : items) {
			addItem(i.getItemStack());
		}
		return this;
	}
	
	public LectusInventory setItem(int index, LectusItem item) {
		this.inventory.setItem(index, item.getItemStack());
		return this;
	}
	
	public LectusInventory addItem(ItemStack... items) {
		this.inventory.addItem(items);
		return this;
	}
	
	public LectusInventory setItem(int index, ItemStack item) {
		this.inventory.setItem(index, item);
		return this;
	}
	
	public void openInvetory(Player player) {
		player.openInventory(getInventory());
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setSize(Integer size) {
		this.size = size;
	}
	
	public Integer getSize() {
		return size;
	}
}
