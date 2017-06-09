 package uom.tl.player;

import org.json.JSONException;

import uom.tl.board.Field;
import uom.tl.card.Card;
import uom.tl.card.ChargeCardClass;
import uom.tl.card.MinionCard;
import uom.tl.gui.maingame.BackgroundGui;
import uom.tl.gui.maingame.HeroButton;
import uom.tl.listeners.Bling;
import uom.tl.listeners.Listener;
//klash pou antiproswpeuei ton paikth
public class Player {
	//to onoma tou hrwa pou paizei o paikths.
	private String hero;
	//h zwh tou hrwa tou paikth.
	private int hp;
	//to trexon mana.
	private int currentMana;
	//to max mana.
	private int maxMana;
	//to field tou kathe paikth to opoio sthn ousia periexei to deck tou paikth KAI tis kartes pou exei sto xeri tou.
	private Field field;
	//kai to onoma tou xrhsth (Player1 ,Player2)
	private String name;

	
	public Player() throws JSONException  {
		this.hp=30;
		this.currentMana=1;
		this.maxMana=1;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setHero(String hero){
		this.hero=hero;
	}
	public String getHero(){
		return hero;
	}
	public void setField(Field f){
		this.field=f;

	}
	//methodos me thn opoia afaireiteai mia karta apo to xeri tou paikth kai prostithetai sto tablo
	public void throwCard(Card card){
		
		if(currentMana-card.getMana()>=0){
			if(this==Card.getBoard().getActivePlayer() && Card.getBoard().getActivePlayer().getField().getCardsInField().size()<=6){
				BackgroundGui.getLogField().append("-You throw a card to the field!\n"+"************\n");
				this.field.addCardToField(card);
				if(card instanceof ChargeCardClass){
					card.setTurnsOnField(2);
				}
				else{
					card.setTurnsOnField(1);
				}
				
				currentMana=currentMana-card.getMana();
			}
			else{
				BackgroundGui.getLogField().append("-Field is full!\n"+"************\n");
			}
			
		}
		
	}
	public void addCardToHand() {

		this.field.addCardToHand();

	}
	public void addNCardsToHand(int n) {

		this.field.addNCardsToHand(n);

	}
	//not used
	public void attackHero(MinionCard mc){
		if((mc.getTurnsOnField()>1)){
			Player opponent= Card.getBoard().getOpponentPlayer();
			int ophp=opponent.getHp();
			opponent.setHp(ophp-mc.getDmg());
			
		}
		
	}
	//teliwnei ton guro tou paikth.
	public boolean endTurn() {
		
		if (Card.getBoard().isGameOver()){
			System.out.println("game is over the winner is "+Card.getBoard().getWinner().getHero());
			return false;
			
		}
		
		if (this != Card.getBoard().getActivePlayer())
			return false;

		this.getField().endTurn();
		
		return true;

	}
	//methodos pou allazei to health tou paikth
	public void setHp(int hp){
		
		if(this.hp>hp){
			if(this.hp<=0){    
				this.hp=0;
			}
			if(name=="Player 1"){
				new Bling(Listener.getBg().getHeroButtonp1());
			}
			if(name=="Player 2"){
				new Bling(Listener.getBg().getHeroButtonp2());
			}
			
			
		}
		if(this.hp<hp){
			if(this.hp<=0){
				this.hp=0;
			}
			if(name=="Player 1"){
				new Bling(Listener.getBg().getHeroButtonp1(),"R");
			}
			if(name=="Player 2"){
				new Bling(Listener.getBg().getHeroButtonp2(),"R");
			}
		}
		if(hp<=0){
				
			this.hp=0;
			Player p1=Card.getBoard().getOpponentPlayer();
			Player p2=Card.getBoard().getActivePlayer();
			//an to hp tou p1 einai 0 tote exase kai nikhths einai o p2 kai to anapodo
			if(p1.hp==0){
				Card.getBoard().setWinner(p2);
			}
			else{
				Card.getBoard().setWinner(p1);
			}

			BackgroundGui.getLogField().append("-The winner is "+Card.getBoard().getWinner().getHero()+"!!\n ");
			
		}
		else{
			System.out.println("4");
			this.hp=hp;
		
		}
		
	}
	public int getHp(){
		return this.hp;
	}
	public Field getField(){
		return this.field;
	}
	public int getCurrentMana() {
		return currentMana;
	}
	public void setCurrentMana(int currentMana) {
		this.currentMana = currentMana;
	}
	public int getMaxMana() {
		return maxMana;
	}
	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}
	

}
