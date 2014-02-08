package me.codermason.bank.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.codermason.bank.VB;
import me.codermason.bank.bank.Account;
import me.codermason.bank.items.Item;
import me.codermason.bank.lang.Lang;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

public class Config {
	
	private static File dataFolder, langFile, playersFile, itemsFile;
	private static FileConfiguration lang, players, items;
  
	public static void init() {
		dataFolder = VB.getInstance().getDataFolder();
    	dataFolder.mkdirs();
    
    	langFile = new File(dataFolder, "lang.yml");
    	playersFile = new File(dataFolder, "players.yml");
    	itemsFile = new File(dataFolder, "items.yml");
    
    	lang = YamlConfiguration.loadConfiguration(langFile);
    	players = YamlConfiguration.loadConfiguration(playersFile);
    	items = YamlConfiguration.loadConfiguration(itemsFile);
    
    	checkFiles(new File[]{langFile, playersFile, itemsFile}, new FileConfiguration[]{lang, players, items});
    	loadLang();
	}
  
	private static void loadLang() {
		Lang.loadFrom(YamlConfiguration.loadConfiguration(getLangFile()));
		for(Lang value : Lang.values())
			if(!getLangConfig().isString(value.getPath()))
				getLangConfig().set(value.getPath(), value.getDefault());
		saveFile(getLangFile(), getLangConfig());
	}
  
	private static void checkFiles(File[] files, FileConfiguration[] configs) {
		if(files.length != configs.length) return;
		
		for(int x=0;x<files.length;x++) {
			File f = files[x];
			FileConfiguration config = configs[x];
			if (!f.exists()) {
				VB.log("Could not find file " + f.getName() + "! Attempting to create...");
				try {
					config.save(f);
				}catch (IOException e) {
					VB.log("Could not create file " + f.getName() + "!");
					VB.shutdown();
				}
				VB.log("Created file " + f.getName() + " successfully!");
			}
		}
	}
  
	public static File getLangFile() { return langFile; } 
	public static File getPlayersFile() { return playersFile; }
	public static File getItemsFile() { return itemsFile; }
  
	public static FileConfiguration getLangConfig() { return lang; }  
	public static FileConfiguration getPlayersConfig() { return players; }  
	public static FileConfiguration getItemsConfig() { return items; }
  
	public static void saveFile(File file, FileConfiguration config) {
		try {
			config.save(file);
		}catch (Exception localException) {}
	}
  
	public static class Util {
		
		public static int getSavings(String p) {
			return Config.getPlayersConfig().getInt(p + ".savings");
		}
    
		public static boolean hasSavings(String p) {
			return Config.getPlayersConfig().isInt(p + ".savings");
		}
    
		public static void saveAccount(Account account) {
			if(!hasSavings(account.getPlayer()))
				Config.getPlayersConfig().set(account.getPlayer() + ".savings", Integer.valueOf(account.getSavings()));
			Config.saveFile(Config.getPlayersFile(), Config.getPlayersConfig());
		}
    
		public static void updateAccount(Account account) {
			if(hasSavings(account.getPlayer()))
				Config.getPlayersConfig().set(account.getPlayer() + ".savings", Integer.valueOf(account.getSavings()));
			Config.saveFile(Config.getPlayersFile(), Config.getPlayersConfig());
		}
    
		public static void removeAccount(Account account) {
			if(hasSavings(account.getPlayer()))
				Config.getPlayersConfig().set(account.getPlayer() + ".savings", null);
			Config.saveFile(Config.getPlayersFile(), Config.getPlayersConfig());
		}
    
		public static Item getItem(String name) {
	        Item item;
			try {
				ItemStack[] contents = getContents(name);
				int price = getPrice(name);
				item = new Item(name, contents, price);
			}catch (Exception e) {
				VB.log("Unable to load item " + name + "! Error: " + e);
				return null;
			}
			return item;
		}
    
		public static int getPrice(String name) {
			return Config.getItemsConfig().getInt(name + ".price");
		}
    
		public static ItemStack[] getContents(String name) {
			List<String> items = Config.getItemsConfig().getStringList(name + ".items");
			List<ItemStack> contents = new ArrayList();
			for(String s : items) {
				String[] parts = s.split(":");
				Material material = Material.getMaterial(parts[0]);
				if(material != null) contents.add(new ItemStack(material, Integer.parseInt(parts[1])));
			}
			return (ItemStack[])contents.toArray(new ItemStack[0]);
    	}
	}
}
