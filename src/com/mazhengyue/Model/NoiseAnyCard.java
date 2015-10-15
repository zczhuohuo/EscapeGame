package com.mazhengyue.Model;

import com.mazhengyue.Controller.Input;
import com.mazhengyue.View.Display;

public class NoiseAnyCard extends DangerousCard {
	private String name = "Noise in Any Card";
	
	public NoiseAnyCard(boolean di) {
		this.drawItemCard = di;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public void useCard(Player player) {
		String declaredSector = Input.input("Input the sector you declaring in");
		Display.messageBox(player.getName() + " are in " + declaredSector);
	}
}
