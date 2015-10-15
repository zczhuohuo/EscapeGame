package com.mazhengyue.Model;

import com.mazhengyue.View.Display;

public class NoiseCurrentCard extends DangerousCard {
	private String name = "Noise in Current Card";
	
	public NoiseCurrentCard(boolean di) {
		this.drawItemCard = di;
	}
	
	public void useCard(Player player) {
		Display.messageBox(player.getName() + " in sector " + player.getCurrentSector().getStr());
	}
	
	public String getName() {
		return this.name;
	}

}
