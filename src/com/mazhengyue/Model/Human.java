package com.mazhengyue.Model;

import com.mazhengyue.View.Display;

public class Human extends Player {
	public Human(String name, GameMap m, String role, Game game) {
		super(name, m, role, game);
		this.currentSec = this.map.humanSector;
		this.defaultSteps = 1;
	}
	
	public void decAlive() {
		this.game.decAliveHuman();
	}
	
	public void run() {
		this.initEveryTurnVariable();
		this.setSteps(this.getDefaultSteps());
		this.cardOptions();
		goSteps();
		this.cardOptions();
		// only when we end in dangerous sector, should we draw dangerous card
		if (this.currentSec.getType() == Sector.dangerous) {
			this.drawDangerousCard();
		} else if (this.currentSec.getType() == Sector.escape && this.currentSec.getEscapeColor() == Sector.blank) {
			EscapeHatchCard ehc = this.game.getCardStack().drawEscapeHatchCard();
			Display.messageBox(this.getName() + " has arrived at escape hatch " + this.currentSec.getHatchNumber());
			if (ehc.getColor() == Sector.red) {
				this.currentSec.setEscapeColor(Sector.red);
			} else if (ehc.getColor() == Sector.green) {
				this.currentSec.setEscapeColor(Sector.green);
				this.game.incEscapeHumanNum();
				this.setEscaped();
			}
		}
		this.historySectors.add(this.getCurrentSector().getStr());
	}
}
