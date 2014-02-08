package me.codermason.bank.commands;

import me.codermason.bank.VB;
import me.codermason.bank.lang.Lang;
import me.codermason.bank.util.Messenger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandRouter implements CommandExecutor {
	
	private VB plugin;

	public CommandRouter(VB plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getLabel().equalsIgnoreCase("shop")) {
			new ShopCommand(this.plugin).execute(sender, cmd, args);
			return true;
		}
		
		if(cmd.getLabel().equalsIgnoreCase("buy")) {
			new BuyCommand(this.plugin).execute(sender, cmd, args);
			return true;
		}
		
		if(cmd.getLabel().equalsIgnoreCase("bank")) {
			if(args.length == 0) {
				String[] messages = Lang.PROMPT_MESSAGES.toString().split("%newline%");
				for(String s : messages) {
					if(s.contains("%version%")) 
						s = s.replace("%version%", this.plugin.getDescription().getVersion());
					if(s.contains("%author%")) 
						s = s.replace("%author%", (CharSequence)this.plugin.getDescription().getAuthors().get(0));
					Messenger.sendMessage(s, sender);
				}
				return true;
			}
			
			if(args[0].equalsIgnoreCase("help")) {
				new HelpCommand(this.plugin).execute(sender, cmd, args);
				return true;
			}
			
			if(args[0].equalsIgnoreCase("info")) {
				new InfoCommand(this.plugin).execute(sender, cmd, args);
				return true;
			}
			
			if(args[0].equalsIgnoreCase("pay")) {
				new PayCommand(this.plugin).execute(sender, cmd, args);
				return true;
			}
			
			if(args[0].equalsIgnoreCase("withdraw")) {
				new WithdrawCommand(this.plugin).execute(sender, cmd, args);
				return true;
			}
			
			if(args[0].equalsIgnoreCase("deposit")) {
				new DepositCommand(this.plugin).execute(sender, cmd, args);
				return true;
			}	
		}
		return false;
	}
}