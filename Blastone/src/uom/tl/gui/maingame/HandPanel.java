package uom.tl.gui.maingame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import uom.tl.board.Field;
import uom.tl.card.Card;
import uom.tl.card.MinionCard;
import uom.tl.card.spells.SpellCard;
import uom.tl.player.Player;
//panel me tis kartes pou exei kathe paikths sto xeri tou.
public class HandPanel extends JPanel {
	private ArrayList<MinionButton> minion;
	private ArrayList<SpellButton> spell;
	private ArrayList<CardButton> cards;
	//dexetai ton paikth, briskoume poies kartes exei sto xeri tou kai tis prostetoume sto panel.
	public HandPanel(Player p){
		super();		
		this.setPreferredSize(new Dimension(800,150));
		this.removeAll();
		this.revalidate();
		minion=new ArrayList<MinionButton>();
		spell=new ArrayList<SpellButton>();
		cards=new ArrayList<CardButton>();
		ArrayList<Card> hand= p.getField().getHand();
	
		this.setLayout(new FlowLayout());
		this.setOpaque(false);
		for (int i = 0; i<20; i++) {
			MinionButton mb= new MinionButton();
			mb.setVisible(false);
			minion.add(mb);
	
		}
		for (int i = 0; i<20; i++){
			SpellButton sb=new SpellButton();
			sb.setVisible(false);
			spell.add(sb);
		
		}
	
		for(int i=0; i<hand.size(); i++){
	
			if(hand.get(i) instanceof MinionCard){
				minion.get(i).setMinion((MinionCard) hand.get(i));
				minion.get(i).setVisible(true);
				String card=hand.get(i).getImagePath();
				ImageIcon img= new ImageIcon("images/cardImages/"+card);
				Image img2 = img.getImage();
				Image newimg=img2.getScaledInstance(100, 146, java.awt.Image.SCALE_SMOOTH);
				ImageIcon newIcon=new ImageIcon(newimg);
				minion.get(i).setIcon(newIcon);
				minion.get(i).customizeButton();
				minion.get(i).setPreferredSize(new Dimension(100,146));
				minion.get(i).revalidate();
				minion.get(i).setOpaque(false);
				minion.get(i).repaint();	
				cards.add(minion.get(i));
				
			}
			if(hand.get(i) instanceof SpellCard){
				spell.get(i).setSpell((SpellCard)hand.get(i));
				spell.get(i).setVisible(true);
				String card=hand.get(i).getImagePath();
				ImageIcon img= new ImageIcon("images/cardImages/"+card);
				Image img2 = img.getImage();
				Image newimg=img2.getScaledInstance(100, 146, java.awt.Image.SCALE_SMOOTH);
				ImageIcon newIcon=new ImageIcon(newimg);
				spell.get(i).setIcon(newIcon);
				spell.get(i).customizeButton();
				spell.get(i).setPreferredSize(new Dimension(100,146));
				spell.get(i).revalidate();
				spell.get(i).setOpaque(false);
				spell.get(i).repaint();		
				cards.add(spell.get(i));
			
				
			}
		
				
		
		}
		/*if(!minion.isEmpty()){
			cards.addAll(minion);
		}
		if(!spell.isEmpty()){
			cards.addAll(spell);
		}
		
		Collections.shuffle(cards);
		*/
		for(CardButton cb:cards){
			this.add(cb);
			
		}
		for(CardButton cb:cards){
			if(cb instanceof MinionButton){
				cb.setCard(((MinionButton) cb).getMinion());
			}
			else{
				cb.setCard(((SpellButton) cb).getSpell());
			}
			
		}
		
	}

	public ArrayList<CardButton> getCards() {
		return cards;
	}

	public void setCards(ArrayList<CardButton> cards) {
		this.cards = cards;
	}

	public ArrayList<MinionButton> getMinions() {
		return minion;
	}

	public void setMinions(ArrayList<MinionButton> minion) {
		this.minion = minion;
	}

	public ArrayList<SpellButton> getSpells() {
		return spell;
	}

	public void setSpells(ArrayList<SpellButton> spell) {
		this.spell = spell;
	}

	


	
	
	
}
