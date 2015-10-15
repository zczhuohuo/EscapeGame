package com.mazhengyue.Model;

import java.util.HashSet;

import com.mazhengyue.Controller.Input;
import com.mazhengyue.Controller.Utilities;
import com.mazhengyue.View.Display;

public class SpotlightCard extends ItemCard {
	private String name = "Spotlight";
	
	public SpotlightCard(Player owner) {
		this.owner = owner;
	}
	
	@Override
	public boolean useCard() {
		if (!checkCanUse())
			return false;
		Sector target;
		HashSet<Sector> spotlightSectors = new HashSet<Sector>();
		HashSet<Sector> visitedSet = new HashSet<Sector>();
		do {
			target = this.owner.map.getSingleSector(Input.input("Select a spotlight target"));
		} while (!Utilities.validSector(target));
		this.owner.map.legalSectors(target, 1, spotlightSectors, visitedSet);
		spotlightSectors.add(target);
		for (Sector sec : spotlightSectors) {
			for (Player player : sec.getPlayers()) {
				Display.messageBox(player.getName() + " in Sector: " + sec.getStr());
			}
		}
		return true;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
}
