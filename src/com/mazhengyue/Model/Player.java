package com.mazhengyue.Model;

import java.util.HashSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mazhengyue.Controller.Input;
import com.mazhengyue.Controller.Utilities;
import com.mazhengyue.View.Display;

abstract public class Player {
	public final static String human = "Human";
	public final static String alien = "Alien";
	public final static int dead = 0;
	public final static int alive = 1;
	public final static int escaped = 2;
	
	public GameMap map;		
	public String role;				//human or alien
	public boolean shouldDrawCard; 	//for sedatives card
	public Game game;
	
	protected LinkedList<String> historySectors;
	protected int state;					// dead or alive
	protected LinkedList<ItemCard> cards;	// cards we have
	protected Sector nextSec;
	protected Sector currentSec;
	protected int steps;					// how steps the player can step in this round
	protected int defaultSteps;				// 1 for human, 2 or 3 for alien
	protected Sector target;				// 
	protected boolean isContinue;
	protected String name;					// player's name
	
	abstract public void run();
	abstract public void decAlive();
	
	public String getName() {
		return this.name;
	}
	
	public Player(String name, GameMap m, String role, Game game) {
		this.name = name;
		this.state = Player.alive;
		this.map = m;
		this.role = role;
		this.game = game;
		this.cards = new LinkedList<ItemCard>();
		this.shouldDrawCard = false;
		this.isContinue = false;
		this.historySectors = new LinkedList<String>();
		if (this.role == Player.human) {
			currentSec = m.humanSector;
			this.map.humanSector.addPlayer(this);
		} 
		else if (this.role == Player.alien) {
			currentSec = m.alienSector;
			this.map.alienSector.addPlayer(this);
		}
	}
	
	public void initEveryTurnVariable() {
		this.shouldDrawCard = true;
	}
	
	public boolean equals(Player b) {
		return this.name == b.name;
	}
	
	public void drawItemCard() {
		ItemCard ic = this.game.getCardStack().drawItemCard();
		ic.owner = this;
		this.addCard(ic);
	}
	
	public void cardOptions() {
		if (this.useCardOrNot()) 
			this.useCards();
	}
	
	public void drawDangerousCard() {
		if (!this.shouldDrawCard)
			return ;
		DangerousCard dc = this.game.getCardStack().drawDangerousCard();
		JOptionPane.showMessageDialog(null, this.getName() + " get a " + dc.getName());
		if (this.game.getLevel() == Game.COMPLETE && dc.drawItemCard) {
			this.drawItemCard();
		}
		dc.useCard(this);
	}
	
	public ItemCard removeCard(int index) {
		return this.cards.remove(index);
	}
	
	public ItemCard getCard(int index) {
		return this.cards.get(index);
	}
	
	public Sector getCurrentSector() {
		return this.currentSec;
	}
	
	public LinkedList<String> getHistorySec() {
		return this.historySectors;
	}
	
	public int getDefaultSteps() {
		return this.defaultSteps;
	}
	public int getState() {
		return this.state;
	}
	
	public int getSteps() {
		return this.steps;
	}
	
	public void setDefaultSteps(int steps) {
		this.defaultSteps = steps;
	}
	
	public void setCurrentSector(Sector sec) {
		this.currentSec = sec;
	}
	
	public void setDead() {
		this.state = Player.dead;
	}
	
	public void setAlive() {
		this.state = Player.alive;
	}
	
	public void setEscaped() {
		this.state = Player.escaped;
	}
	
	public void setSteps(int steps) {
		this.steps = steps;
	}

	// sectors player can move into from this current sector
	public HashSet<Sector> possibleSectors() {
		HashSet<Sector> ps = new HashSet<Sector>();
		HashSet<Sector> visitedSet = new HashSet<Sector>();
		this.map.legalSectors(this.currentSec, this.getSteps(), ps, visitedSet);
		return ps;
	}
	
	public String messagePrefix() {
		return this.role + "-" + this.name;
	}
	
	public void displayHistory() {
		String message = this.role + ": " + this.getName() + "\n";
		for (String sec : this.historySectors) 
			message = message + " " + sec;
		this.game.getTextBoard().setText(message);
	}
	
	// How many cards a player has
	public int showCards(String title, String message) {
		int cardNum = cards.size();
		String[] cardNames = new String[cardNum];
		int idx = 0;
		for (ItemCard card : cards) {
			cardNames[idx++] = card.getName();
		}
		return Display.showOptions(title, message, cardNames);
	}
	
	// player to choose to use card or not
	public boolean useCardOrNot() {
		if (this.cards.size() == 0) 
			return false;
		return Input.getYesOrNo("Do you want to use card, " + this.getName() + " ?") == 0;
	}
	
	public void useCards() {
		 int choice = -1;
		 while (true) {
			 choice = this.showCards("Player's cards", "Player should chose a card to use");
			 if (choice == -1) {
				 if (Input.getYesOrNo("You are not going to use cards, " + this.getName() + " ?") == 0)
					 break;
			 } else {
				 ItemCard card = this.getCard(choice);
				 if(card.useCard()) {
					 this.removeCard(choice);
					 break;
				 }
			 }
		 }
	}
	
	public void discardItemCard () {
		int response = -1;
		do {
			response = this.showCards("Discard a card", "You should choose a card to discard");
		} while (response == -1);
		this.cards.remove(response);
	}
	
	public void addCard(ItemCard card) {
		cards.add(card);
	}
	
	public void goSteps() {
		LinkedList<Sector> ps = Utilities.filterSectors((possibleSectors()), this);
		int response = Input.getOptions(this.role + " to move", "Player should choose a sector to move", 
				Utilities.sectorListToStringList(ps));
		this.currentSec.removePlayer(this);
		this.currentSec = ps.get(response);
		this.currentSec.addPlayer(this);
		this.setSteps(-1);
	}
}
