package me.codermason.bank.items;

import java.util.ArrayList;
import java.util.List;

import me.codermason.bank.VB;
import me.codermason.bank.config.Config;

public class ItemUtil {
	
	private static List<Item> allItems = new ArrayList();
  
	public static void loadItems() {
		for(String name : Config.getItemsConfig().getKeys(false)) {
			Item item = Config.Util.getItem(name);
			if(item != null) allItems.add(item);
		}
	}
  
	public static List<Item> getAllItems() { return allItems; }
  
	public static boolean isItem(String name) { return getItem(name) != null; }
  
	public static Item getItem(String name) {
		for(Item item : allItems)
			if(item.getName().equalsIgnoreCase(name))
				return item;
		return null;
	}
  
	public static String[] formatString() {
		String[] s = new String[VB.getItemsList().size()];
		for(int x=0;x<VB.getItemsList().size();x++)
			s[x] = VB.getItemsList().get(x);
		return s;
	}
}
