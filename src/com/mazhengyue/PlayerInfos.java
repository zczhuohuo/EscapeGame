package com.mazhengyue;

import com.mazhengyue.Model.Sector;

public class PlayerInfos {
	//Informations about players playing the game
	private int id;		//the unique identification of player
	private int type;	//teamate or enemy
	private Sector realSec;	// real sector the play is in
	private Sector suspectedSec;	//We are not sure whether the player is in that position or not
	private int state;	// dead or alive or unknown
}
