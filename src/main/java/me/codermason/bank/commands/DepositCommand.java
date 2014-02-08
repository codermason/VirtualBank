package me.codermason.bank.commands;

import me.codermason.bank.VB;
import me.codermason.bank.bank.Account;
import me.codermason.bank.bank.Bank;
import me.codermason.bank.lang.Lang;
import me.codermason.bank.util.IngotUtil;
import me.codermason.bank.util.MathUtil;
import me.codermason.bank.util.Messenger;
import me.codermason.bank.util.Permissions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DepositCommand extends VBCommand {
	
	public DepositCommand(VB plugin) {
		super(plugin);
	}

	public void execute(CommandSender sender, Command cmd, String[] args) {
		if(!args[0].equalsIgnoreCase("deposit")) return;
    
		if(!Permissions.COMMANDS_DEPOSIT.authenticate(sender)) {
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
					if(IngotUtil.hasEnoughIngots(p, amount)) {
						account.deposit(amount);
						Messenger.sendMessage(Lang.DEPOSITIED.toString().replace("%amount%", amount+""), sender);
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
			Messenger.sendMessage(Lang.WRONG_USAGE.toString().replace("%usage%", "/bank deposit [amount]"), sender);
		}
	}
}