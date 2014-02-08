package me.codermason.bank.commands;

import me.codermason.bank.VB;
import me.codermason.bank.bank.Account;
import me.codermason.bank.bank.Bank;
import me.codermason.bank.items.Item;
import me.codermason.bank.items.ItemUtil;
import me.codermason.bank.lang.Lang;
import me.codermason.bank.util.Messenger;
import me.codermason.bank.util.Permissions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuyCommand extends VBCommand {
	
	public BuyCommand(VB plugin) {
		super(plugin);
	}

	public void execute(CommandSender sender, Command cmd, String[] args) {
		if(!cmd.getLabel().equalsIgnoreCase("buy")) return;
		
		if(!Permissions.COMMANDS_BUY.authenticate(sender)) {
			Messenger.sendMessage(Lang.NO_PERMISSION.toString(), sender);
			return;
		}else if(!(sender instanceof Player)) {
			Messenger.sendMessage(Lang.NO_CONSOLE.toString(), sender);
			return;
		}	
		Player p = (Player)sender;
		if(args.length == 1) {
			String itemName = args[0];
			if(ItemUtil.isItem(itemName)) {
				Item item = ItemUtil.getItem(itemName);
				if(Bank.hasAccount(p.getName())) {
					Account account = Bank.getAccount(p.getName());
					if(account.hasEnough(item.getPrice())) {
						account.buyItem(item);
						Messenger.sendMessage(Lang.BOUGHT_ITEM.toString().replace("%item%", item.getName()).replace("%price%", item.getPrice()+""), sender);
						Messenger.sendMessage(Lang.GET_INGOTS.toString().replace("%amount%", account.getSavings()+""), sender);
					}else{
						Messenger.sendMessage(Lang.NOT_ENOUGH_INGOTS.toString(), sender);
					}
				}else{
					Messenger.sendMessage(Lang.NO_ACCOUNT.toString(), sender);
				}
			}else{
				Messenger.sendMessage(Lang.ITEM_NOT_FOUND.toString().replace("%item%", itemName), sender);
			}
		}else{
			Messenger.sendMessage(Lang.WRONG_USAGE.toString().replace("%usage%", "/buy [item]"), sender);
		}
	}
}