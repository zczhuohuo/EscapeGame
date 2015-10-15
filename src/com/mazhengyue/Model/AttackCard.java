package com.mazhengyue.Model;

import com.mazhengyue.View.Display;


public class AttackCard extends ItemCard {
	private String name = "Attack";
	
	public AttackCard(Player owner) {
		this.owner = owner;
	}
	
	@Override
	public boolean useCard() {
		boolean flag = false;
		Sector target = this.owner.getCurrentSector();
		Display.messageBox("I(" + this.owner.getName() + ") is gonna to attack sector " + target.getStr());
		for (Player player : target.getPlayers()) {
			// We can not attack ourself;
			if (this.owner.equals(player))
				continue;
			player.setDead();
			if (player.useCardOrNot())
				player.useCards();
			if (player.getState() == Player.dead) { 
				player.decAlive();
				Display.messageBox(player.role + " " + player.getName() + " has been killed!");
				flag = true;
				if (this.owner.role == Player.alien && player.role == Player.human) {
					this.owner.setDefaultSteps(3);
				}
			}
		}
		if (!flag) 
			Display.messageBox("No one has been killed!");
		return true;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
}
