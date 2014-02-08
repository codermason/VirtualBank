package me.codermason.bank.util;

import me.codermason.bank.lang.Lang;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Messenger {
	
	public static void sendMessage(String msg, Player p) {
		p.sendMessage(Lang.PREFIX + msg);
  	}
  
	public static void sendMessage(String msg, CommandSender sender) {
		sender.sendMessage(Lang.PREFIX + msg);
  	}
  
	public static void sendMessage(String msg, CommandSender sender, boolean prefix) {
		if(prefix)
			sender.sendMessage(msg);
		else
			sendMessage(msg, sender);
		
  	}
}