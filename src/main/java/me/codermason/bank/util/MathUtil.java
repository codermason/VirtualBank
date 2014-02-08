package me.codermason.bank.util;

public class MathUtil {

	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		}catch(Exception e) { return false; }
		return true;
	}
	
}
