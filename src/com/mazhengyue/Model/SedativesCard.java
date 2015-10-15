package com.mazhengyue.Model;

import com.mazhengyue.View.Display;

public class SedativesCard extends ItemCard {
	private String name = "Sedatives";
	
	@Override
	public boolean useCard() {// only we are human and in dangerous sector can we use sedatives cards
		if (!checkCanUse() || this.owner.getCurrentSector().getType() != Sector.dangerous) {
			Display.messageBox("You are not allowed to use sedatives card");
			return false;
		}
		this.owner.shouldDrawCard = false;
		return true;
	}
	
	public SedativesCard(Player owner) {
		this.owner = owner;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
}
