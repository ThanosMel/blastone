package uom.tl.gui.maingame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import uom.tl.card.Card;
import uom.tl.card.spells.SpellCard;
//klash-button gia tis kartes tupou spell
public class SpellButton extends CardButton {
	private SpellCard spell;
	public SpellButton(){
		this.setVisible(true);
	}

	public void setSpell(SpellCard card) {
		this.spell=(SpellCard) card;
		
	}
	//methodos pou prosthetei sto button to mana tis spell kartas.
	public void customizeButton() {
		this.setLayout(null);
		JLabel mana= new JLabel(Integer.toString(spell.getMana()));
		this.add(mana);
		Font hsFont=BackgroundGui.gethsFont();
		
		if(spell.getMana()>9){
			mana.setBounds(0,0 , 28, 30);
		}
		else{
			mana.setBounds(7,0, 28, 30);
		}
		mana.setFont(hsFont);
		mana.setForeground(Color.WHITE);
		
		
		this.setContentAreaFilled(false);
		this.setBorderPainted(true);	
		this.setOpaque(false);
		this.setBorder(null);
		
	}
	public SpellCard getSpell(){
		return spell;
	}

}
