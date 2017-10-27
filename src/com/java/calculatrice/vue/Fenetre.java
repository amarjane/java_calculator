package com.java.calculatrice.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Fenetre extends JFrame{
	
	private static final long serialVersionUID = 41225756398L;
	
	private JPanel content = new JPanel();	
	
	private JLabel label = new JLabel();	
	private JPanel clavier = new JPanel();
	
	private JPanel clavier1 = new JPanel();
	private JPanel clavier2 = new JPanel();

	private String[] tabText = {"1", "2", "3", "4", "5", "6", "7", "8", "9", 
			"0", ".", "=", "C", "+", "-", "*", "/"};
		
	private Bouton[] tabBouton = new Bouton[tabText.length];

	private Font policeLabel = new Font("Arial", Font.ITALIC, 20);
	
	private Border borderLabel = BorderFactory.createLoweredBevelBorder();

	private Dimension dimensionContent = new Dimension(500, 500);
	private Dimension dimensionLabel = new Dimension(460, 50);
	private Dimension dimensionClavier = new Dimension(460, 380);
	private Dimension dimensionClavier1 = new Dimension(300, 400);
	
	private GridLayout gl1 = new GridLayout(4, 4);
	private GridLayout gl2 = new GridLayout(5, 1);
		
	private double memory = 0;
	private String operateurSelected = "";
	
	private boolean operateur = false;
	private boolean update = false;
	
	public Fenetre(){
		// définition des attributs principaux de la fenêtre
		this.initFenetre();
		
		this.initAddSubContainers();
		
		this.initAddBoutons();
		
		// this.char0.addActionListener(new ChiffreListener());
	}		
	

	
	public void initFenetre(){
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Calculatrice");
		this.setResizable(false);		
	}
	
	public void initAddSubContainers(){
		// Mise en forme du label
		this.label.setFont(this.policeLabel);
		this.label.setBackground(Color.WHITE);
		this.label.setForeground(Color.DARK_GRAY);
		this.label.setHorizontalAlignment(JLabel.RIGHT);
		this.label.setVerticalAlignment(JLabel.CENTER);
		this.label.setBorder(this.borderLabel);
		this.label.setPreferredSize(this.dimensionLabel);
		this.label.setText("");
		
		// mise en forme des claviers
		this.clavier1.setPreferredSize(this.dimensionClavier1); 	
		this.clavier.setPreferredSize(this.dimensionClavier);
		this.clavier.setLayout(new BoxLayout(this.clavier, BoxLayout.LINE_AXIS));
		this.clavier.add(this.clavier1);
		this.clavier.add(Box.createRigidArea(new Dimension(20, 0)));
		this.clavier.add(this.clavier2);
		
		// ajout du label et des claviers au conteneur
		this.content.setPreferredSize(this.dimensionContent);
		this.content.add(this.label);
		this.content.add(Box.createVerticalStrut(70));
		this.content.add(this.clavier);
		
		this.setContentPane(this.content);	
	}
	
	public void initAddBoutons(){
		for(int i = 0; i < 12; i++){
			this.tabBouton[i] = new Bouton(tabText[i]);	
			this.clavier1.add(this.tabBouton[i]);
			if(i == 11){
				this.tabBouton[i].addActionListener(new EqualListener());
			}
			if(i < 11){
				this.tabBouton[i].addActionListener(new ChiffreListener());				
			}
		}
		
		this.clavier1.setLayout(this.gl1);		
		this.gl1.setHgap(30);
		this.gl1.setVgap(30);

		for(int i = 12; i < tabText.length; i++){
			this.tabBouton[i] = new Bouton(tabText[i]);
			this.clavier2.add(this.tabBouton[i]);
			if(i == 12){
				this.tabBouton[i].addActionListener(new CListener());
			}
			if(i == 13){
				this.tabBouton[i].addActionListener(new PlusListener());
			}
			if(i == 14){
				this.tabBouton[i].addActionListener(new MinusListener());
			}
			if(i == 15){
				this.tabBouton[i].addActionListener(new MultiplyListener());				
			}
			if(i == 16){
				this.tabBouton[i].addActionListener(new DivisionListener());				
			}
		}

		this.clavier2.setLayout(gl2);
		gl2.setHgap(20);
		gl2.setVgap(20);				
	}
	
	class ChiffreListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String caption = ((JButton) event.getSource()).getText();
			String labelText = label.getText();

			if(update){
				update = false;
				memory = Double.parseDouble(labelText);
			} else {
				if((caption == "." && labelText.indexOf(caption) == -1) || caption != "."){
					caption = labelText + caption;
				} else {
					caption = labelText;
				}
			}
			
			label.setText(caption);			
		}				
	}
	
	class EqualListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			calculer();
			operateur = false;
		}
	}
	
	class CListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			memory = 0;
			label.setText("");
			update = false;
			operateurSelected = "";
			operateur = false;
		}
	}
	
	class PlusListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(operateur){
				calculer();		
				label.setText(String.valueOf(memory));
			} else {
				memory = Double.parseDouble(label.getText());
			}
			operateurSelected = "+";	
			operateur = true;
			update = true;
		}
	}
	
	class MinusListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(operateur){
				calculer();		
				label.setText(String.valueOf(memory));
			} else {
				memory = Double.parseDouble(label.getText());
			}
			operateurSelected = "-";	
			operateur = true;
			update = true;
		}
	}
	
	class MultiplyListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(operateur){
				calculer();		
				label.setText(String.valueOf(memory));
			} else {
				memory = Double.parseDouble(label.getText());
			}
			operateurSelected = "*";	
			operateur = true;
			update = true;
		}
	}
	
	class DivisionListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(operateur){
				calculer();		
				label.setText(String.valueOf(memory));
			} else {
				memory = Double.parseDouble(label.getText());
			}
			operateurSelected = "/";	
			operateur = true;
			update = true;
		}
	}
	
	private void calculer(){
		Double labelText;
		if(label.getText() != ""){
			labelText = Double.parseDouble(label.getText());
			if(operateur){
				if(operateurSelected == "+"){
					memory += labelText;
				} else if(operateurSelected == "-"){
					memory -= labelText;
				} else if(operateurSelected == "*"){
					memory *= labelText;
				} else if(operateurSelected == "/"){
					try{
						memory /= labelText;
					} catch(ArithmeticException e){
						label.setText("0");
					}
				}
				label.setText(String.valueOf(memory));
			}
			update = true;
		}
	}
}

