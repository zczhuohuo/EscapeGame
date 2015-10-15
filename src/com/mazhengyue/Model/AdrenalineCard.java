package com.mazhengyue.Model;

import com.mazhengyue.View.Display;
import com.mazhengyue.Model.Player;

public class AdrenalineCard extends ItemCard {
	private String name = "Adrenaline";
	
	public  AdrenalineCard(Player player) {
		this.owner = player;
	}
	@Override
	public boolean useCard() {
		if (!checkCanUse() || this.owner.getSteps() == -1) {
			Display.messageBox("You are not allowed to use adrenaline card");
			return false;
		}
		this.owner.setSteps(2);
		return true;
	}

	@Override
	public String getName() {
		return name;
	}
}
