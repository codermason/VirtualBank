package me.codermason.bank.commands;

import me.codermason.bank.VB;
import me.codermason.bank.bank.Account;
import me.codermason.bank.bank.Bank;
import me.codermason.bank.lang.Lang;
import me.codermason.bank.util.MathUtil;
import me.codermason.bank.util.Messenger;
import me.codermason.bank.util.Permissions;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCommand extends VBCommand {
	
	public PayCommand(VB plugin) {
		super(plugin);
	}

	public void execute(CommandSender sender, Command cmd, String[] args) {
		if(!args[0].equalsIgnoreCase("pay")) return;
		
		if(!Permissions.COMMANDS_PAY.authenticate(sender)) {
			Messenger.sendMessage(Lang.NO_PERMISSION.toString(), sender);
			return;
		}else if(!(sender instanceof Player)) {
			Messenger.sendMessage(Lang.NO_CONSOLE.toString(), sender);
			return;
		}	
		
		Player p = (Player)sender;
		if (args.length == 3) {
			Player target = Bukkit.getPlayer(args[1]);
			if(target != null) {
				if(MathUtil.isInt(args[2])) {
					int amount = Integer.parseInt(args[2]);
					if(Bank.hasAccount(p.getName())) {
						Account pAccount = Bank.getAccount(p.getName());
						if(Bank.hasAccount(target.getName())) {
							Account tAccount = Bank.getAccount(target.getName());
							if(pAccount.hasEnough(amount)) {
								pAccount.payAccount(tAccount, amount);
								Messenger.sendMessage(Lang.TRANSACTION_SUCCESS.toString().replace("%amount%", amount+"").replace("%player%", target.getName()), sender);
								Messenger.sendMessage(Lang.RECEIVED_MONEY.toString().replace("%amount%", amount+"").replace("%player%", p.getName()), target);
							}else{
								Messenger.sendMessage(Lang.NOT_ENOUGH_INGOTS.toString(), sender);
							}
						}else{
							Messenger.sendMessage(Lang.NO_PLAYERS_ACCOUNT.toString().replace("%player%", target.getName()), sender);
						}
					}else{
						Messenger.sendMessage(Lang.NO_ACCOUNT.toString(), sender);
					}
				}else{
					Messenger.sendMessage(Lang.NUMBER_NOT_FOUND.toString(), sender);
				}
			}else{
				Messenger.sendMessage(Lang.PLAYER_NOT_FOUND.toString().replace("%player%", args[1]), sender);
			}
		}else{
			Messenger.sendMessage(Lang.WRONG_USAGE.toString().replace("%usage%", "/bank pay [player] [amount]"), sender);
		}
	}
}