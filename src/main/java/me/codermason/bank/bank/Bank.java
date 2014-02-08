package me.codermason.bank.bank;

import java.util.ArrayList;
import java.util.List;

import me.codermason.bank.config.Config;

public class Bank {
	
	private static List<Account> allAccounts = new ArrayList();
  
	public static List<Account> getAllAccounts() {  return allAccounts; }
  
	public static Account getAccount(String p) {
		for(Account account : allAccounts) 
			if(account.getPlayer().equals(p)) 
				return account;
		return null;
	}
  
	public static boolean hasAccount(String p) { return getAccount(p) != null; }
  
	public static Account openAccount(String p, int savings) {
		if(hasAccount(p)) return getAccount(p);
		Account account = new Account(p, savings);
		Config.Util.saveAccount(account);
		allAccounts.add(account);
		return account;
	}	
  
	public static void openAccounts() {
		for(String player : Config.getPlayersConfig().getKeys(false)) 
			openAccount(player, Config.Util.getSavings(player));
	}
  
	public static void saveAccount(Account account, boolean exists) {
		if(!allAccounts.contains(account)) return;
		if(exists) Config.Util.updateAccount(account);
		else Config.Util.saveAccount(account);
	}
  
	public static void saveAccounts() {
		for(Account account : allAccounts)
			saveAccount(account, true);
    }
  
	public static void closeAccount(Account account) {
		if(!allAccounts.contains(account)) return;
		allAccounts.remove(account);
		Config.Util.removeAccount(account);
	}
}