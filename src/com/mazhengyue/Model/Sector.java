package com.mazhengyue.Model;

import java.util.LinkedList;


public class Sector {
	public final static int notLegal = 0;
	public final static int secure = 1;
	public final static int dangerous = 2;
	public final static int human = 3;
	public final static int alien = 4;
	public final static int escape = 5;
	public final static int escapeOne = 51;
	public final static int escapeTwo = 52;
	public final static int escapeThree = 53;
	public final static int escapeFour = 54;
	
	public final static int red = 0;
	public final static int green = 1;
	public final static int blank = 2;
	
	public int x;
	public int y;
	private int type;
	private int escapeColor;
	private int hatchNumer;
	public boolean isBegan;	//Is the first round has ended
	private String English[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
								"J", "K", "L", "M", "N", "O", "P", "Q", "R", 
								"S", "T", "U", "V", "W"};
	private LinkedList<Player> players;	//players in this sector
	
	public Sector(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.escapeColor = Sector.blank;
		this.hatchNumer = -1;
		this.players = new LinkedList<Player>();
	}

	public int getHatchNumber() {
		return this.hatchNumer;
	}
	
	public void setHatchNumber(int num) {
		this.hatchNumer = num;
	}
	
	public Sector(String pos, int type) {
		this.x = pos.charAt(0) - 'A';
		this.y = this.x % 2 + (2 * (Integer.parseInt(pos.substring(1, 3))) - 2);
		this.type = type;
		this.escapeColor = Sector.blank;
		this.players = new LinkedList<Player>();
	}
	
	public void addPlayer(Player player) {
		this.players.add(player);
	}
	
	public void removePlayer(Player player) {
		this.players.remove(player);
	}
	
	public void setEscapeColor(int color) {
		this.escapeColor = color;
	}
	
	public int getEscapeColor() {
		return this.escapeColor;
	}
	
	public int getType() {
		return this.type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public LinkedList<Player> getPlayers() {
		return this.players;
	}
	
	public boolean equals(Sector sec) {
		return this.x == sec.x && this.y == sec.y && this.type == sec.type;
	}
	
	public String getStr() {
		if ((x % 2 == 0 && y % 2 == 0) || (x % 2 == 1 && y % 2 == 1)) {
			if ((this.y + 1) / 2 < 10){
				return this.English[this.x] + "0" + (this.y / 2 + 1);
			} else {
				return this.English[this.x] + (this.y / 2 + 1);
			}
		}
		else{
			return "";
		}
	}
}
