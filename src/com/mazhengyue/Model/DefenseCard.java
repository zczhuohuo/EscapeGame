package com.mazhengyue.Model;

import com.mazhengyue.View.Display;

public class DefenseCard extends ItemCard {
	private String name = "Defense";
	
	public DefenseCard(Player player) {
		this.owner = player;
	}
	
	@Override
	public boolean useCard() {
		if (!checkCanUse())
			return false;
		if (this.owner.getState() == Player.alive) {
			Display.messageBox("You are not being attacked");
			return false;
		}
		this.owner.setAlive();
		return true;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

}
