package com.mazhengyue.Model;


abstract public class DangerousCard {
	protected String name;
	public boolean drawItemCard;
	abstract public void useCard(Player player);
	public String getName() {
		return this.name;
	}
}
