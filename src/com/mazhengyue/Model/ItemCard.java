package com.mazhengyue.Model;

import com.mazhengyue.View.Display;

abstract public class ItemCard {
	public final static int MaxCardNum = 3;
	
	public Player owner;
	abstract public boolean useCard();
	abstract public String getName();
	
	protected boolean canUsed() {
		if (this.owner instanceof Alien) 
			return false;
		else
			return true;
	}
	
	protected boolean checkCanUse() {
		if (!canUsed()) {
			Display.messageBox("Aliens are not allowed to use cards");
			return false;
		}
		return true;
	}
}
