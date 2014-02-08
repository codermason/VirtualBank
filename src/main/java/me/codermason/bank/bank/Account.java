package me.codermason.bank.bank;

import me.codermason.bank.items.Item;
import me.codermason.bank.util.IngotUtil;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Account {
	
	private String playerName;
	private int savings;
  
	public Account(String playerName, int savings) {
		this.playerName = playerName;
		this.savings = savings;
	}
  
	public String getPlayer() { return this.playerName; }
  
	public int getSavings() { return this.savings; }
  
	public void setSavings(int i) {
		this.savings = i;
	}
  
	public boolean hasEnough(int amount) { return getSavings() >= amount; }
  
	public void deposit(int amount) {
		if(!IngotUtil.hasEnoughIngots(Bukkit.getPlayer(getPlayer()), amount)) return;
		setSavings(getSavings() + amount);
		IngotUtil.takePlayerIngots(Bukkit.getPlayer(getPlayer()), amount);
	}
  
	public void withdraw(int amount) {
		if(!hasEnough(amount)) return;
		setSavings(getSavings() - amount);
		IngotUtil.givePlayerIngots(Bukkit.getPlayer(getPlayer()), amount);
	}
  
	public void payAccount(Account account, int amount) {
		if(!hasEnough(amount)) return;
		account.setSavings(account.getSavings() + amount);
		setSavings(getSavings() - amount);
	}
  
	public void buyItem(Item item) {
		if(!hasEnough(item.getPrice())) return;
		Player player = Bukkit.getPlayer(getPlayer());
		if(player == null) return;
		for(ItemStack is : item.getContents())
			player.getInventory().addItem(new ItemStack[] { is });
		setSavings(getSavings() - item.getPrice());
	}
}
