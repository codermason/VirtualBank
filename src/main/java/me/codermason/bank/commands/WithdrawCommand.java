package me.codermason.bank.commands;

import me.codermason.bank.VB;
import me.codermason.bank.bank.Account;
import me.codermason.bank.bank.Bank;
import me.codermason.bank.lang.Lang;
import me.codermason.bank.util.MathUtil;
import me.codermason.bank.util.Messenger;
import me.codermason.bank.util.Permissions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WithdrawCommand extends VBCommand {
	
	public WithdrawCommand(VB plugin) {
		super(plugin);
	}

	public void execute(CommandSender sender, Command cmd, String[] args) {
		if(!args[0].equalsIgnoreCase("withdraw")) return;

		if(!Permissions.COMMANDS_WITHDRAW.authenticate(sender)) {
			Messenger.sendMessage(Lang.NO_PERMISSION.toString(), sender);
			return;
		}else if(!(sender instanceof Player)) {
			Messenger.sendMessage(Lang.NO_CONSOLE.toString(), sender);
			return;
		}
		
		Player p = (Player)sender;
		if(args.length == 2) {
			if(Bank.hasAccount(p.getName())) {
				Account account = Bank.getAccount(p.getName());
				if(MathUtil.isInt(args[1])) {
					int amount = Integer.parseInt(args[1]);
					if(account.hasEnough(amount)) {
						account.withdraw(amount);
						Messenger.sendMessage(Lang.WITHDRAWED.toString().replace("%amount%", amount+""), sender);
					}else{
						Messenger.sendMessage(Lang.NOT_ENOUGH_INGOTS.toString(), sender);
					}
				}else{
					Messenger.sendMessage(Lang.NUMBER_NOT_FOUND.toString(), sender);
				}
			}else{
				Messenger.sendMessage(Lang.NO_ACCOUNT.toString(), sender);
			}
		}else{
			Messenger.sendMessage(Lang.WRONG_USAGE.toString().replace("%usage%", "/bank withdraw [amount]"), sender);
		}
	}
}

