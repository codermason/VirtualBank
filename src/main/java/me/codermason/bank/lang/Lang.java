package me.codermason.bank.lang;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public enum Lang { //many enum
	
	PREFIX("prefix", "&0[&eVB&0]&f"),  
	
	PRICE("price", "Price"),  
	INFO_DESCRIPTION("info_description", "Show how much Gold Ingot you have"),  
	BUY_DESCRIPTION("buy_description", "Buy a item package"),  
	SHOP_DESCRIPTON("shop_description", "Shop for item packages"),  
	WITHDRAW_DESCRIPTION("withdraw_description", "Withdraw your Gold Ingots"),  
	DEPOSIT_DESCRIPTION("deposit_description", "Deposit Gold Ingots to your bank"),  
	PAY_DESCRIPTION("pay_description", "Pay a player Gold Ingots"),  
	NO_PERMISSION("no_permission", "You do not have permission!"),  
	UNKNOWN_COMMAND("unknown_command", "Command not found! Type /bank help for help!"),  
	WRONG_USAGE("wrong_usage", "Usage: %usage%"),  
	NO_CONSOLE("no_console", "Only players can use this command!"),  
	GET_INGOTS("get_ingots", "You have %amount% Gold Ingots!"),  
	NOT_ENOUGH_INGOTS("not_enough_ingots", "You do not have enough Gold Ingots!"), 
	NO_ACCOUNT("no_account", "You do not have a bank account! Please relog!"),  
	NO_PLAYERS_ACCOUNT("no_players_account", "Could not find an account for %player%!"), 
	WITHDRAWED("withdrawed", "You withdrew %amount% Gold Ingots!"),  
	DEPOSITIED("deposited", "You deposited %amount% Gold Ingots!"),  
	ITEM_NOT_FOUND("item_not_found", "Could not find item %item%!"),  
	PLAYER_NOT_FOUND("player_not_found", "Could not find player %player%!"),  
	NUMBER_NOT_FOUND("number_not_found", "Please enter a number not a word!"),  
	RECEIVED_MONEY("recieved_money", "You got %amount% Gold Ingots from %player%"),  
	TRANSACTION_SUCCESS("payment_success", "Payed %amount% Gold Ingots to %player%!"),  
	BOUGHT_ITEM("bought_item", "You bough %item% for %price% Gold Ingots!"),  
	PROMPT_MESSAGES("prompt_messages", "VirtualBank v%version% created by %author%!%newline%Use (/bank help) for help!"),  
	
	HEADER("header", "----------&0[&eVB&0]&f----------"),  
	ITEMS_HEADER("items_header", "----------&0[&eItems&0]&f----------");
  
	private String path;
	private String defaul;
	private static FileConfiguration lang;
  
	private Lang(String path, String defaul) {
		this.path = path;
		this.defaul = defaul;
  	}
  
	public String getPath() {
		return this.path;
  	}
  
	public String getDefault() {
		return this.defaul;
  	}
  
	public String toString() {
		if(lang == null)
			return "";
		if(this == PREFIX) 
			return ChatColor.translateAlternateColorCodes('&', lang.getString(getPath(), getDefault())) + " ";
		return ChatColor.translateAlternateColorCodes('&', lang.getString(getPath(), getDefault()));
  	}
  
	public static void loadFrom(FileConfiguration config) {
		lang = config;
  	}
}

