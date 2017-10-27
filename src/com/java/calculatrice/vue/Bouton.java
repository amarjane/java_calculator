package com.java.calculatrice.vue;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class Bouton extends JButton{
	
	private static final long serialVersionUID = 356001478924564L;
	
	private Font police = new Font("Arial", Font.BOLD, 30);
	
	private Border border = BorderFactory.createRaisedSoftBevelBorder();
	
	public Bouton(){
		super();
		this.setFont(police);
		this.setForeground(Color.DARK_GRAY);
		this.setBorder(this.border);
	}
	
	public Bouton(String name){
		super(name);
		this.setFont(police);
		this.setForeground(Color.DARK_GRAY);
		this.setBorder(this.border);
	}
}