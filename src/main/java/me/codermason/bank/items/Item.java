package me.codermason.bank.items;

import me.codermason.bank.lang.Lang;

import org.bukkit.inventory.ItemStack;

public class Item {
	
	private String name;
	private ItemStack[] contents;
	private int price;
  
	public Item(String name, ItemStack[] items, int price) {
		this.name = name;
		this.contents = items;
		this.price = price;
  	}
  
	public String getName() { return this.name; }
  
	public ItemStack[] getContents() { return this.contents; }
  
	public int getPrice() { return this.price; }
  
	public String[] formatString() {
		String s[] = new String[this.contents.length+1];
		s[s.length-1] = Lang.PRICE.toString()+": "+getPrice();
		for(int i=0;i<this.contents.length;i++) 
			s[i] = this.contents[i].getType().toString() +" x"+this.contents[i].getAmount();
		return s;
  	}
}