package com.mazhengyue.Model;



public class TeleportCard extends ItemCard {
	private String name = "Teleport";
	
	public TeleportCard(Player player) {
		this.owner = player;
	}
	
	@Override
	public boolean useCard() {
		checkCanUse();
		this.owner.setCurrentSector(this.owner.map.humanSector);
		return true;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
}
