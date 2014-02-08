package me.codermason.bank.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class IngotUtil {
	
	public static void givePlayerIngots(Player p, int ingots) {
		ItemStack is = new ItemStack(Material.GOLD_INGOT, ingots);
		p.getInventory().addItem(new ItemStack[] { is });
	}
  
	public static void takePlayerIngots(Player p, int ingots) {
		for(int i=0;i<ingots;i++) {
			p.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 1) });
		}
  	}
  
	public static boolean hasEnoughIngots(Player p, int ingots) {
		PlayerInventory inv = p.getInventory();
		return inv.containsAtLeast(new ItemStack(Material.GOLD_INGOT), ingots);
	}
}
