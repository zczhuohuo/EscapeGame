package com.mazhengyue.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mazhengyue.View.Display;


public class Input {
	public static int getOptions(String title, String message, String[] ops) {
		int response = -1;
		do {
			response = Display.showOptions(title, message, ops);
		} while (response == -1);
		return response;
	}
	
	public static int getYesOrNo(String message) {
		int response = -1;
		do {
			response = Display.YesOrNotMessageBox(message);
		}while (response == -1);
		
		return response;
	}
	
	public static int getMapNum() throws IOException {
		String [] mapNames = {"fermi", "galilei", "galvani"};
		return Input.getOptions("Game Initialazion", "Please choose a map to start the game", 
						mapNames);
	}
	
	public static int getGameLevel() {
		String [] levels = {"BASIC", "COMPLETE"};
		return Input.getOptions("Game Initialazion", "Please choose a game level", levels);
	}
	
	public static int getPlayerNum(int least, int most) throws IOException {
		int num = most - least + 1;
		String[] playerNums = new String[num];
		for (int i = 0; i < num; i++) {
			playerNums[i] = Integer.toString(i + least);
		}
		return least + Input.getOptions("Game Initialazion", "Please choose the number of the players",
										playerNums);
	}
	
	public static String readInput() throws IOException {
		InputStreamReader is_reader = new InputStreamReader(System.in);
		return new BufferedReader(is_reader).readLine();
	}
	
	public static int drawOrAttackOptions() {
		String[] options = {"Draw", "Attack"};
		return Input.getOptions("Draw or Attack", 
											"As an alien, you must make a decision to draw dangerous card or "
											+ "choose a sector to attack" , options);
	}
	
	public static int attackOrNotOptions() {
		String[] options = {"Yes", "No"};
		return Input.getOptions("Attack or Not", 
				"As an alien, do you want to attack a specific sector?", options);
	}
	
	public static Integer getInt(String hint) throws IOException {
		int rst;
		do {
			System.out.println(hint);
			try{
				rst = Integer.parseInt(Input.readInput());
			} catch (NumberFormatException e) {
				continue;
			}
			break;
		} while(true);
		return rst;
	}
	
	
	public static String input(String title) {
		String text;
		while (true) {
			text = Display.intputDialog(title);
			if (!Utilities.inputOk(text)) {
				Display.messageBox("Invalid input");
				continue;
			}
			return text;
		}
	}
}
