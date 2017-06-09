package uom.tl.gui.maingame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import uom.tl.card.Card;
import uom.tl.card.MinionCard;
import uom.tl.card.spells.SpellCard;
import uom.tl.player.Player;

//Panel me tis kartes tou kathe paikth pou uparxoun sto tablo
public class CardsPanel extends JPanel{
	private ArrayList<CardButton> cards;
	private ArrayList<MinionButton> minion;
	private ArrayList<SpellButton> spell;
	
	//o kataskeuasths dexetai ton paikth
	//Briskoume poies kartes exei o paikths sto tablo kai tis prosthetoume to panel
	public CardsPanel(Player p){
		super();
		this.removeAll();
		this.setPreferredSize(new Dimension(800,150));
		this.setVisible(true);
		this.setOpaque(false);
		this.setLayout(new FlowLayout());
		cards=new ArrayList<CardButton>();
		minion=new ArrayList<MinionButton>();
		spell=new ArrayList<SpellButton>();
		ArrayList<Card> cardsInArea = p.getField().getCardsInField();
		
		for(int i=0;i<20; i++){
			MinionButton mb= new MinionButton();
			minion.add(mb);
			mb.setVisible(false);
		}
		for(int i=0;i<20; i++){
			SpellButton sb=new SpellButton();
			spell.add(sb);
			sb.setVisible(false);
		}
			
	
		for(int i=0; i<cardsInArea.size(); i++){
			if(cardsInArea.get(i) instanceof MinionCard){
				minion.get(i).setMinion((MinionCard) cardsInArea.get(i));
				minion.get(i).setVisible(true);
				minion.get(i).customizeButton();
				ImageIcon img = new ImageIcon("images/cardImages/"+cardsInArea.get(i).getImagePath());
				Image img2 = img.getImage();
				Image newimg = img2.getScaledInstance(100, 146,  java.awt.Image.SCALE_SMOOTH);
				ImageIcon newIcon = new ImageIcon(newimg);
				minion.get(i).setIcon(newIcon);
				minion.get(i).setPreferredSize(new Dimension(100, 146));
				minion.get(i).revalidate();
				minion.get(i).setOpaque(false);
				minion.get(i).repaint();
				cards.add(minion.get(i));
			}
			if(cardsInArea.get(i) instanceof SpellCard){
				spell.get(i).setSpell((SpellCard)cardsInArea.get(i));
				spell.get(i).setVisible(true);
				spell.get(i).customizeButton();
				ImageIcon img = new ImageIcon("images/cardImages/"+cardsInArea.get(i).getImagePath());
				Image img2 = img.getImage();
				Image newimg = img2.getScaledInstance(100, 146,  java.awt.Image.SCALE_SMOOTH);
				ImageIcon newIcon = new ImageIcon(newimg);
				spell.get(i).setIcon(newIcon);
				spell.get(i).setPreferredSize(new Dimension(100, 146));
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
		
		
		Collections.shuffle(cards);*/
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
	public ArrayList<MinionButton> getMinions() {
		return minion;
	}
	public ArrayList<CardButton> getCards() {
		return cards;
	}
	public void setCards(ArrayList<CardButton> cards) {
		this.cards = cards;
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
