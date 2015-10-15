package com.mazhengyue.Model;

import java.io.IOException;
import java.util.LinkedList;

import com.mazhengyue.Controller.Input;
import com.mazhengyue.View.Display;
import com.mazhengyue.View.MapImage;
import com.mazhengyue.View.TextBoard;


public class Game {
	public final static int MAX_ROUND = 39;
	public final static int BASIC = 0;
	public final static int COMPLETE = 1;
	public final static int FERMI = 0;
	public final static int GALILEI = 1;
	public final static int GALVANI = 2;
	
	private String[] mapName = {"E:/code/Java/EscapeGame/src/com/mazhengyue/mapOne.txt", 
								"E:/code/Java/EscapeGame/src/com/mazhengyue/mapTwo.txt", 
								"E:/code/Java/EscapeGame/src/com/mazhengyue/mapThree.txt"};
	private String[] mapImageNames = {"E:/code/Java/EscapeGame/src/com/mazhengyue/MAPfermi.jpg",
									  "E:/code/Java/EscapeGame/src/com/mazhengyue/MAPgalilei.jpg",
									  "E:/code/Java/EscapeGame/src/com/mazhengyue/MAPgalvani.jpg"};
	private int round = 0;
	private CardsStack cardStack;
	private int level;
	private int hummanNum;
	private int alienNum;
	private int aliveHuman;
	private int aliveAlien;
	private String[] hummanNames = {"Ennio Maria Dominoni", "Julia Niguloti", "Silvano Porpora", "Tuccio Brendon"};
	private String[] alienNames = {"Piero Ceccarella", "Vittorio Martana", "Maria Galbani", "Paolo Landon"};
	private int playerNum;
	private int mapNum;
	private GameMap map;
	private LinkedList<Player> players;
	private int[] turnOrder;
	private int escapeHumanNum;
	private MapImage mi;
	private TextBoard tb;
	
	public Game() throws IOException {
		this.cardStack = new CardsStack();
		this.gameConfig();
		this.gameInit();
	}
	
	public int getRound() {
		return this.round;
	}
	
	public CardsStack getCardStack() {
		return this.cardStack;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public TextBoard getTextBoard() {
		return this.tb;
	}

	public int getPlayersNum() throws IOException {
		if (this.mapNum == 0) 
			return Input.getPlayerNum(2, 6);
		else if (this.mapNum == 1) 
			return Input.getPlayerNum(4, 8);
		else
			return Input.getPlayerNum(2, 8);
	}
	
	private String getHumanName() {
		String hummanName;
		int idx;
		do {
			idx = (int) (Math.random() * 4);
			hummanName = this.hummanNames[idx];
		} while (hummanName == null);
		this.hummanNames[idx] = null;
		return hummanName;
	}
	
	private String getAlienName() {
		String an;
		int idx;
		do {
			idx = (int) (Math.random() * 4);
			an = this.alienNames[idx];
		} while (an == null);
		this.alienNames[idx] = null;
		return an;
	}
	
	// a simple realization of shuffle algorithm
	private void initTurnOrder() {
		this.turnOrder = new int[this.playerNum];
		for (int i = 0; i < this.playerNum; i++)
			this.turnOrder[i] = i;
		for (int i = 0; i < this.playerNum; i++) {
			int changeIdx = (int) (Math.random() * (this.playerNum - i));
			int tmp = turnOrder[changeIdx + i];
			turnOrder[changeIdx + i] = turnOrder[i];
			turnOrder[i] = tmp;
		}
	}

	
	private boolean isGameOver() {
		if (this.level == Game.BASIC) {
			if (this.escapeHumanNum > 0 || this.aliveAlien == 0) {
				Display.humanWin();
				return true;
			}
			if (this.aliveHuman == 0 || this.round == Game.MAX_ROUND) {
				Display.alienWin();
				return true;
			}
		}
		
		if (this.level == Game.COMPLETE) {
			if (this.escapeHumanNum == this.hummanNum || this.aliveAlien == 0) {
				Display.humanWin();
				return true;
			}
			if (this.aliveHuman == 0 || this.round == Game.MAX_ROUND) {
				Display.alienWin();
				return true;
			}
		}
		return false;
	}
	
	public void gameConfig() throws IOException {
		this.mapNum = Input.getMapNum();
		this.playerNum =  this.getPlayersNum();
		this.level = Input.getGameLevel();
		this.hummanNum = (int)Math.floor(this.playerNum / 2);
		this.alienNum = (int)Math.ceil(this.playerNum / 2);
		this.aliveHuman = this.hummanNum;
		this.aliveAlien = this.alienNum;
	}
	
	public void incEscapeHumanNum() {
		this.escapeHumanNum += 1;
	}
	
	public void decAliveHuman() {
		this.aliveHuman -= 1;
	}
	
	public void decAliveAlien() {
		this.aliveAlien -= 1;
	}
	
	public void gameInit() throws IOException {
		this.escapeHumanNum = 0;
		this.map = new GameMap(this.mapName[this.mapNum]);
		this.players = new LinkedList<Player>();
		for (int i = 0; i < this.hummanNum; i++) {
			String name = this.getHumanName();
			this.players.add(new Human(name, this.map, Player.human, this));
		}
		for (int i = 0; i < this.alienNum; i++) {
			String name = this.getAlienName();
			this.players.add(new Alien(name, this.map, Player.alien, this));
		}
		this.mi = new MapImage(this.mapImageNames[this.mapNum]);
		this.tb = new TextBoard();
		this.initTurnOrder();
	}

	public void run() {
		this.tb.makeVisible();
		this.mi.setVisible();
		do {
			this.round += 1;
			if (isGameOver()) 
				return;
			for (int i = 0; i < this.playerNum; i++) {
				int idx = this.turnOrder[i];
				Player player = this.players.get(idx);
				player.displayHistory();
				if (player.getState() == Player.dead || player.getState() == Player.escaped)
					continue;
				Display.messageBox(player.role + " : " + player.getName() + "'s turn");
				player.run(); 
			}
		} while (true);
	}
	
	public static void main(String args[]) throws IOException {
		Game game = new Game(); 
		game.run();
	}
}
