package com.mazhengyue.Model;


public class SlientCard extends DangerousCard {
	private String name = "Slient Card";
	
	public SlientCard(boolean di) {
		this.drawItemCard = di;
	}
	
	@Override
	public void useCard(Player player) {
		// Nothing to do 
	}

	public String getName() {
		return this.name;
	}
}
