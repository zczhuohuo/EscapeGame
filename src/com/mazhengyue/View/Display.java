package com.mazhengyue.View;

import javax.swing.JOptionPane;

public class Display {
	public static void messageBox(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public static int YesOrNotMessageBox(String title) {
		String[] options = { "Yes", "No" };
		int response = Display.showOptions(title, "Warning", options);
		return response;
	}
	
	public static int showOptions(String title, String message, String []ops) {
		Object[] options = ops;
		return JOptionPane.showOptionDialog(null, message, title, 
									 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
									 null, options, options[0]);
	}
	
	public static String intputDialog(String title) {
		return JOptionPane.showInputDialog(title);
	}
	
	public static void terminalError(String errMessage) {
		System.out.println("Error: " + errMessage);
	}

	public static void humanWin() {
		Display.messageBox("Game over!! Human has won!!!!!");
	}
	
	
	public static void alienWin() {
		Display.messageBox("Game over!! Alien has won!!!!");
	}
	
	public static void outputTerminal(String message) {
		System.out.print(message);
	}
}
