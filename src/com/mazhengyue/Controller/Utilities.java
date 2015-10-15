package com.mazhengyue.Controller;

import java.util.HashSet;
import java.util.LinkedList;

import com.mazhengyue.Model.Alien;
import com.mazhengyue.Model.Player;
import com.mazhengyue.Model.Sector;
import com.mazhengyue.View.Display;

// some utilities functions
public class Utilities {
	// check if the input is a legal sector string
	public static boolean inputOk(String text) {
		if (text == null || text.length() != 3) 
			return false;
		if (Utilities.outOfRange(text.charAt(0), 'A', 'W'))
			return false;
		try{
			int val = Integer.parseInt(text.substring(1, 3)); 
			if (Utilities.outOfRange(val, 1, 27))
				return false;
		} catch (NumberFormatException e) {
			Display.terminalError("Not legal input, please input again");
			return false;
		}
		return true;
	}
	
	public static boolean validSector(Sector sec) {
		if (Sector.notLegal == sec.getType()) {
			Display.messageBox("Invalid sector has been input");
			return false;
		}
		else
			return true;
	}

	public static boolean outOfRange(int x, int left, int right) {
		return (x < left) || (x > right);
	}
	
	// Some sectors may not reachable according to the rules, so filter them
	public static LinkedList<Sector> filterSectors(HashSet<Sector> ps, Player player) {
		LinkedList<Sector> rstList = new LinkedList<Sector>();
		for (Sector sec : ps) {
			if (sec.getType() == Sector.human || sec.getType() == Sector.alien)
				continue;
			if (player instanceof Alien && sec.getType() == Sector.escape)
				continue;
			if (sec.getEscapeColor() == Sector.red || sec.getEscapeColor() == Sector.green)
				continue;
			// filter duplicated sector
			if (player.getCurrentSector() == sec)
				continue;
			rstList.add(sec);
		}
		
		return rstList;
	}
	
	public static String[] sectorListToStringList(LinkedList<Sector> secList) {
		String[] strList = new String[secList.size()];
		int idx = 0;
		for (Sector sec : secList) {
			strList[idx++] = sec.getStr();
		}
		return strList;
	}
	
	// given a sector, such as "K01", return a x-coordinate(in this example, 10)
	public static int getX(String sec) {
		return sec.charAt(0) - 'A';
	}
	//given a sector, such as "K01", return a x-coordinate(in this example, 2)
	public static int getY(String sec) {
		return (Integer.parseInt(sec.substring(1, 3)) - 1) * 2 + getX(sec) % 2;
	}
	
}
