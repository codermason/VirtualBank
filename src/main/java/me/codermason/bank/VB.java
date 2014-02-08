package me.codermason.bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import me.codermason.bank.bank.Bank;
import me.codermason.bank.commands.CommandRouter;
import me.codermason.bank.config.Config;
import me.codermason.bank.items.Item;
import me.codermason.bank.items.ItemUtil;
import me.codermason.bank.lang.Lang;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class VB extends JavaPlugin {
	
	private static VB instance;
    private String[] commands = { "shop", "buy", "bank" };
    private static List<String> helpCommands = new ArrayList(), itemsList = new ArrayList();
  
    public void onEnable() {
    	instance = this;
    
    	Config.init();
    	Bank.openAccounts();
    	ItemUtil.loadItems();
    
    	loadItems();
    	registerCommands();
    	loadCommands();
    
    	getServer().getPluginManager().registerEvents(new Listener() {
    		@EventHandler
    		public void onJoin(PlayerJoinEvent e) {
    			if(!Bank.hasAccount(e.getPlayer().getName())) {
    				Bank.openAccount(e.getPlayer().getName(), 0);
    			}
    		}
    	}, this);
    
    	log("Loaded "+Bank.getAllAccounts().size()+" bank accounts!");
    	log("Loaded "+ItemUtil.getAllItems().size()+" items!");
    }
  
    public void onDisable() {}
  
    public static VB getInstance() { return instance; }
  
    public static void log(Object o) {
    	getInstance().getLogger().info(o+"");
    }
  
    public static void shutdown() {
    	log("Shutting down " + getInstance().getName() + "!");
    	getInstance().getPluginLoader().disablePlugin(getInstance());
  	}
  
    public static List<String> getCommandsList() { return helpCommands; }
  
    public static List<String> getItemsList() { return itemsList; }
  
    private void loadCommands() {
    	helpCommands = (Arrays.asList(new String[] {
    		"/shop - " + Lang.SHOP_DESCRIPTON.toString(), 
    		"/buy - " + Lang.BUY_DESCRIPTION.toString(), 
    		"/bank info - " + Lang.INFO_DESCRIPTION.toString(), 
    		"/bank pay - " + Lang.PAY_DESCRIPTION.toString(), 
    		"/bank deposit - " + Lang.DEPOSIT_DESCRIPTION.toString(), 
    		"/bank withdraw - " + Lang.WITHDRAW_DESCRIPTION.toString() }));
    }
  
    private void loadItems() {
    	for(Item item : ItemUtil.getAllItems())
    		itemsList.add("- "+item.getName()+"  ("+Lang.PRICE+": "+item.getPrice()+")");
    }
  
    private void registerCommands() {
    	for(String command : this.commands) {
    		getCommand(command).setExecutor(new CommandRouter(this));
    	}
    }
}
