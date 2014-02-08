package me.codermason.bank.commands;

import me.codermason.bank.VB;
import me.codermason.bank.lang.Lang;
import me.codermason.bank.util.Messenger;
import me.codermason.bank.util.Permissions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class HelpCommand extends VBCommand {
	
	public HelpCommand(VB plugin) {
		super(plugin);
	}

	public void execute(CommandSender sender, Command cmd, String[] args) {
		if(!args[0].equalsIgnoreCase("help")) return;

		if(!Permissions.COMMANDS_HELP.authenticate(sender)) {
			Messenger.sendMessage(Lang.NO_PERMISSION.toString(), sender);
			return;
		}
		
		Messenger.sendMessage(Lang.HEADER.toString(), sender, true);
		for(String s : VB.getCommandsList()) {
			Messenger.sendMessage(s, sender, false);
		}
	}
}