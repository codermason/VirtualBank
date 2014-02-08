package me.codermason.bank.util;

import org.bukkit.command.CommandSender;

public enum Permissions {
	
	COMMANDS_HELP,
	COMMANDS_HELP_ADMIN,
	COMMANDS_INFO,
	COMMANDS_INFO_OTHER,
	COMMANDS_DEPOSIT,  
	COMMANDS_WITHDRAW,  
	COMMANDS_PAY,  
	COMMANDS_SHOP,  
	COMMANDS_BUY;
  
  	public boolean authenticate(CommandSender sender) {
  		return sender.hasPermission(toString());
  	}
  
  	public String toString() {
  		return "virtualbank." + name().toLowerCase().replace('_', '.');
  	}
}