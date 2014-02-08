package me.codermason.bank.commands;

import me.codermason.bank.VB;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class VBCommand {
	
	private VB plugin;
  
  	public VBCommand(VB plugin) {
    	this.plugin = plugin;
  	}
  
  	public abstract void execute(CommandSender paramCommandSender, Command paramCommand, String[] paramArrayOfString);
}
