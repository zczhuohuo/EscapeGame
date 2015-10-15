package com.mazhengyue.View;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MapImage {
	JLabel label = new JLabel();
	JFrame frame = new JFrame();
	
	public void init(String filename) {
		ImageIcon icon = new ImageIcon(filename);
		icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth() / 3, 
				icon.getIconHeight() / 3, Image.SCALE_SMOOTH));
		label.setBounds(0, 0, 1366, 768);
		label.setHorizontalAlignment(0);
		label.setIcon(icon);
		frame.add(label);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public MapImage(String filename) {
		this.init(filename);
	}
	
	public void setVisible() {
		this.frame.setVisible(true);
	}
}