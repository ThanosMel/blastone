
package uom.tl.board;


import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;

import uom.tl.card.Card;
import uom.tl.card.ChargeCardClass;
import uom.tl.card.MinionCard;
import uom.tl.card.Position;
import uom.tl.card.TauntCardClass;
import uom.tl.card.spells.SpellCard;
import uom.tl.player.Deck;
import uom.tl.player.Player;

public class Field {
	private  Deck deck;
	//arraylist me tis kartes pou exei o kathe paikths sto xeri tou.
	private  ArrayList<Card>hand;
	//arraylist me tis kartes pou exei paiksei o kathe paikths sto tablo.
	private ArrayList<Card>cardsArea;
	//arraylist me tis kartes pou exoun afairethei apo to tablo.
	private ArrayList<Card>removedCards;
	
	public Field(){
		hand=new ArrayList<Card>();
		cardsArea=new ArrayList<Card>();
		removedCards=new ArrayList<Card>();
	
		
	}
	public void buildDeck() throws JSONException{
		//ftiaxei to deck 
		deck.buildDeck(deck.getJsonArray());
		//to anakateuei
		deck.shuffleDeck();
	}
	public  void setDeck(Deck deck){
		this.deck=deck;
	}
	//prosthetei mia karta sto tablo.
	public void addCardToField(Card card){
		if(this.cardsArea.size()<=6){
			hand.remove(card);
			cardsArea.add(card);
			if(card instanceof ChargeCardClass){
				card.setTurnsOnField(2);
			}
			else{
				card.setTurnsOnField(1);
			}
			card.setPosition(Position.FIELD);
			
			
		}		
	}
	//prosthetei mia karta sto xeri tou paikth.
	public void addCardToHand(){
		//o paikths xanei an den exei alles kartes  sto xeri tou sto deck kai sto tablo.
		if(deck.getDeck().size() == 0 && hand.size()==0 && cardsArea.size()==0){
			if (this == Card.getBoard().getActivePlayer().getField())
				Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
			else
				Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
			
		}
		if (deck.getDeck().size() == 0 ) {
			return;
			
		}
		if(hand.size()<=6){
			Card temp =  deck.drawOneCard();
			hand.add(temp);
			temp.setPosition(Position.HAND);
			
		}
	
		  
	}
	//prosthetei n kartes sto xeri tou paikth
	public void addNCardsToHand(int n){
		for(int i=0; i<n; i++){
			this.addCardToHand();
		}
	}
	public Deck getDeck(){
		return deck;
	}
	//afairei mia karta apo to tablo.
	public void removeCardFromCardArea(Card card){
		if(cardsArea.contains(card)){
			cardsArea.remove(card);
			removedCards.add(card);
			card.setPosition(Position.REMOVED);
		}
		if(cardsArea.size()==0&& hand.size()==0 && deck.getDeck().size()==0){
			if (this == Card.getBoard().getActivePlayer().getField())
				Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
			else
				Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
		}
	}
	//afairei oles tis kartes tupou minion apo to tablo.
	public void removeAllMinionFromCardArea(){
		Iterator<Card> ite = cardsArea.iterator();
		while(ite.hasNext()) {
			Card value=ite.next();
			if(value instanceof MinionCard){
				ite.remove();
			}
		}
		
		
	}
	public  int returnNumOfMinions(){
		int count=0;
		for(Card c: hand){
			if(c instanceof MinionCard){
				count++;
			}
		}
		return count;
	}
	public int returnNumOfSpells(){
		int count=0;
		for(Card c: hand){
			if(c instanceof SpellCard){
				count++;
			}
		}
		return count;
	}
	//teliwnei ton guro tou paikth/
	public  void endTurn(){
		if(!(cardsArea.isEmpty())){
			for(Card c:cardsArea){
				c.setAttacked(false);
				c.setTurnsOnField(2);
			}
		}
		//se kathe guro gemizoume to trexon mana tou epomenou paikth kai auksanoume to sunoliko tou mana kata 1.
		Player oppoenentPlayer= Card.getBoard().getOpponentPlayer();
		int maxMana=oppoenentPlayer.getMaxMana();
		if(maxMana<10){
			oppoenentPlayer.setCurrentMana(maxMana+1);
			oppoenentPlayer.setMaxMana(maxMana+1);
		}
		else{
			oppoenentPlayer.setCurrentMana(10);
			oppoenentPlayer.setMaxMana(10);
		}
		
		
		Card.getBoard().nextPlayer();
		
	}
	public ArrayList<Card> getHand(){
		return hand;
	}
	public ArrayList<Card> getCardsInField(){
		return cardsArea;
	}
	public boolean fieldContainTauntCard(){
		for(Card c:cardsArea){
			if( c instanceof TauntCardClass){
				return true;
			}
		}
		return false;
	}

	
}
