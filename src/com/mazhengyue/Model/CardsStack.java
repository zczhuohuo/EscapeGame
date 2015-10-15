package com.mazhengyue.Model;

import java.util.LinkedList;

public class CardsStack {
	private LinkedList<ItemCard> itemCards;
	private LinkedList<DangerousCard> dangerousCards;
	private LinkedList<EscapeHatchCard> escapeHatchCards;
	
	public final static int ATTACK_CARD_NUM = 2;
	public final static int TELEPORT_CARD_NUM = 2;
	public final static int SEDATIVES_CARD_NUM = 3;
	public final static int SPOTLIGHT_CARD_NUM = 2;
	public final static int DEFENSE_CARD_NUM = 1;
	public final static int ADRENALINE_CARD_NUM = 2;
	public final static int NOISE_CURRENT_CARD_NUM = 6;
	public final static int NOISE_CURRENT_ITEM_CARD_NUM = 4;
	public final static int NOISE_ANY_CARD_NUM = 6;
	public final static int NOISE_ANY_ITEM_CARD_NUM = 4;
	public final static int SLIENCE_CARD_NUM = 5;
	public final static int ESCAPE_HATCH_RED_CARD_NUM = 5;
	public final static int ESCAPE_HATCH_GREEN_CARD_NUM = 5;
	
	public CardsStack() {
		itemCards = new LinkedList<ItemCard>();
		dangerousCards = new LinkedList<DangerousCard>();
		escapeHatchCards = new LinkedList<EscapeHatchCard>();
		this.init();
	}
	
	public void initItemCards() {
		itemCards.clear();
		for (int i = 0; i < CardsStack.ATTACK_CARD_NUM; i++) {
			itemCards.add(new AttackCard(null));
		}
		for (int i = 0; i < CardsStack.TELEPORT_CARD_NUM; i++) {
			itemCards.add(new TeleportCard(null));
		}
		for (int i = 0; i < CardsStack.SEDATIVES_CARD_NUM; i++) {
			itemCards.add(new SedativesCard(null));
		}
		for (int i = 0; i < CardsStack.SPOTLIGHT_CARD_NUM; i++) {
			itemCards.add(new SpotlightCard(null));
		}
		for (int i = 0; i < CardsStack.DEFENSE_CARD_NUM; i++) {
			itemCards.add(new DefenseCard(null));
		}
		for (int i = 0; i < CardsStack.ADRENALINE_CARD_NUM; i++) {
			itemCards.add(new AdrenalineCard(null));
		}
	}
	
	public void initDangerousCards() {
		dangerousCards.clear();
		for (int i = 0; i < CardsStack.NOISE_CURRENT_CARD_NUM; i++) {
			dangerousCards.add(new NoiseCurrentCard(false));
		}
		for (int i = 0; i < CardsStack.NOISE_CURRENT_ITEM_CARD_NUM; i++) {
			dangerousCards.add(new NoiseCurrentCard(true));
		}
		for (int i = 0; i < CardsStack.NOISE_ANY_CARD_NUM; i++) {
			dangerousCards.add(new NoiseAnyCard(false));
		}
		for (int i = 0; i < CardsStack.NOISE_ANY_ITEM_CARD_NUM; i++) {
			dangerousCards.add(new NoiseAnyCard(true));
		}
	}
	
	private void initEscapeHatchCard() {
		escapeHatchCards.clear();
		for (int i = 0; i < CardsStack.ESCAPE_HATCH_RED_CARD_NUM; i++) 
			escapeHatchCards.add(new EscapeHatchCard(Sector.red));
		for (int i = 0; i < CardsStack.ESCAPE_HATCH_GREEN_CARD_NUM; i++)
			escapeHatchCards.add(new EscapeHatchCard(Sector.green));
	}
	
	public void init() {
		this.initItemCards();
		this.initDangerousCards();
		this.initEscapeHatchCard();
	}
	
	public DangerousCard drawDangerousCard() {
		if (dangerousCards.size() == 0)
			this.initDangerousCards();
		int idx = (int) Math.floor(Math.random() * dangerousCards.size());
		return dangerousCards.remove(idx);
	}
	
	public ItemCard drawItemCard() {
		if (itemCards.size() == 0) 
			this.initItemCards();
		int idx = (int) Math.floor(Math.random() * itemCards.size());
		return itemCards.remove(idx);
	}
	
	public EscapeHatchCard drawEscapeHatchCard() {
		if (this.escapeHatchCards.size() == 0) 
			this.initEscapeHatchCard();
		int idx = (int) Math.floor(Math.random() * this.escapeHatchCards.size());
		return this.escapeHatchCards.remove(idx);
	}
	
}
