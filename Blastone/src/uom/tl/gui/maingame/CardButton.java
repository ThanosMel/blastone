package uom.tl.gui.maingame;

import javax.swing.JButton;

import uom.tl.card.Card;
import uom.tl.card.MinionCard;
import uom.tl.card.spells.SpellCard;
//Super klash button thn opoia kanoun extend h spellbutton kai minionbutton.

public   class CardButton extends JButton{
	public Card card;
	
	public  Card getCard(){
		return card;
	}
	
	public void setCard(Card card){
		this.card=card;
	}
	public  void customizeButton(){
		
	}
	
	
}
