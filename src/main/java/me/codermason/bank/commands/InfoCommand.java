package me.codermason.bank.commands;

import me.codermason.bank.VB;
import me.codermason.bank.bank.Account;
import me.codermason.bank.bank.Bank;
import me.codermason.bank.lang.Lang;
import me.codermason.bank.util.Messenger;
import me.codermason.bank.util.Permissions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoCommand extends VBCommand {
	
	public InfoCommand(VB plugin) {
		super(plugin);
	}

	public void execute(CommandSender sender, Command cmd, String[] args) {
		if(!args[0].equalsIgnoreCase("info")) return;
		
		if(!Permissions.COMMANDS_INFO.authenticate(sender)) {
			Messenger.sendMessage(Lang.NO_PERMISSION.toString(), sender);
			return;
		}else if(!(sender instanceof Player)) {
			Messenger.sendMessage(Lang.NO_CONSOLE.toString(), sender);
			return;
		}
		
		Player p = (Player)sender;
		if (Bank.hasAccount(p.getName())) {
			Account account = Bank.getAccount(p.getName());
			Messenger.sendMessage(Lang.GET_INGOTS.toString().replace("%amount%", account.getSavings()+""), sender);
		}else{
			Messenger.sendMessage(Lang.NO_ACCOUNT.toString(), sender);
		}
	}
}