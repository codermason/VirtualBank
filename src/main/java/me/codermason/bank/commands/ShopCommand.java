package me.codermason.bank.commands;

import me.codermason.bank.VB;
import me.codermason.bank.items.Item;
import me.codermason.bank.items.ItemUtil;
import me.codermason.bank.lang.Lang;
import me.codermason.bank.util.Messenger;
import me.codermason.bank.util.Permissions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ShopCommand extends VBCommand {
	
	public ShopCommand(VB plugin) {
		super(plugin);
	}

	public void execute(CommandSender sender, Command cmd, String[] args) {
		if(!cmd.getLabel().equalsIgnoreCase("shop")) return;

		if(!Permissions.COMMANDS_SHOP.authenticate(sender)) {
			Messenger.sendMessage(Lang.NO_PERMISSION.toString(), sender);
			return;
		}
		
		if(args.length == 0) {
			Messenger.sendMessage(Lang.ITEMS_HEADER.toString(), sender, true);
			for(String s : ItemUtil.formatString()) 
				Messenger.sendMessage(s, sender);
		}else if(args.length == 1) {
			String itemName = args[0];
			if(ItemUtil.isItem(itemName)) {
				Item item = ItemUtil.getItem(itemName);
				Messenger.sendMessage(Lang.ITEMS_HEADER.toString(), sender, true);
				for(String s : item.formatString()) 
					Messenger.sendMessage(s, sender);
			}else{
				Messenger.sendMessage(Lang.ITEM_NOT_FOUND.toString().replace("%item%", itemName), sender);
			}
		}else{
			Messenger.sendMessage(Lang.WRONG_USAGE.toString().replace("%usage%", "/shop [item]"), sender);
		}
	}
}