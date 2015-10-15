package com.mazhengyue.Model;

import com.mazhengyue.Controller.Input;


public class Alien extends Player {
	public Alien(String name, GameMap m, String role, Game game) {
		super(name, m, role, game);
		this.currentSec = this.map.alienSector;
		this.defaultSteps = 2;
	}
	
	// Alien attack has the same effect of use an attack cards
	private void attack() {
		AttackCard ac = new AttackCard(this);
		ac.useCard();
	}
	
	private void drawOrAttack() {
		int response = Input.drawOrAttackOptions();
		if (response == 0) {
			this.drawDangerousCard();
		} else {
			this.attack();
		}
	}
	
	private void attackOrNot() {
		if (Input.attackOrNotOptions() == 0) 
			this.attack();
	}
	
	public void run() {
		this.initEveryTurnVariable();
		this.setSteps(this.getDefaultSteps());
		this.goSteps();
		if (this.currentSec.getType() == Sector.dangerous) {
			this.drawOrAttack();
		} else 
			this.attackOrNot();
		
		//make sure the cards you have are not more than 3
		if (this.cards.size() > ItemCard.MaxCardNum)
			discardItemCard();
		
		// at last, we add the current sector to the history sectos variables
		this.historySectors.add(this.currentSec.getStr());
	}
	
	public void decAlive() {
		this.game.decAliveAlien();
	}
}
