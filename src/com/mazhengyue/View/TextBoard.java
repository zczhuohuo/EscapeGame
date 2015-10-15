package com.mazhengyue.View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextBoard {
	private JFrame frame;
	private JTextArea textArea;
	private JScrollPane jsPane;
	
	public void init() {
		this.frame = new JFrame("History Sectors");
		Container contenPanel = frame.getContentPane();
		contenPanel.setLayout(new BorderLayout());
		this.textArea = new JTextArea(300, 400);
		this.textArea.setTabSize(4);
		this.textArea.setLineWrap(true);
		this.textArea.setWrapStyleWord(true);
		this.textArea.setFont(new Font("Monospaced", Font.BOLD, 16));
		this.jsPane = new JScrollPane(this.textArea);
		contenPanel.add(this.jsPane, BorderLayout.CENTER);
		
		this.frame.setSize(500, 500);
		this.frame.setLocation(850, 0);
	}
	
	public void setText(String text) {
		this.textArea.setText(text);
	}
	
	public void makeVisible() {
		this.frame.setVisible(true);
	}
	
	public TextBoard() {
		this.init();
	}
}
