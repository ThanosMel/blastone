package uom.tl.card;


import uom.tl.board.Board;
import uom.tl.gui.maingame.BackgroundGui;
//H super class card apo thn opoia klhronomoun oles oi upoloipes klaseis card.
public abstract class Card {
	
	private int mana;
	//H thesh ths kartas
	private Position position;
	private static Board board;
	//pedio pou deixnei posous gurous einai h karta sto tablo.
	//gia na paiktei mia karta prepei na einai panw apo 1 guro sto tablo(ektos an einai charge pou paizete amesws).
	protected int turnsOnField;
	//to path ths eikonas ths kathe kartas.
	private String imagePath;
	//boolean pou deixnei an exei epitethei o xrhsths me mia karta
	//kathe karta borei na epitethei mono 1 fora se kathe guro.
	protected boolean attacked;
	
	public Card(int mana,String imagePath){
		this.imagePath=imagePath;
		this.mana=mana;
		this.turnsOnField=0;
		this.attacked=false;
	}
	//kathe karta exei diaforetikes energeies-epitheseis.kathe fora kaleitai polumorfika.
	public abstract void action();
	public void setMana(int mana) {
		this.mana = mana;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Card(int mana, Position  pos) {
		this.mana=mana;
		//this.isHidden=isHidden;
		this.position=pos;
	}
	
	
	public Position getPosition(){
		return position;
	}
	public void setPosition(Position position){
		this.position=position;
	}
	public static Board getBoard(){
		return board;
	}
	public void setTurnsOnField(int turn){
		this.turnsOnField=turn;
	}
	public int getTurnsOnField(){
		return turnsOnField;
	}
	public int getMana(){
		return mana;
	}
	public static void setBoard(Board board) {
		Card.board = board;
	}

	public String getImagePath(){
		return this.imagePath;
	}
	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	public Card getCard() {
		
		return this;
	}
	public void logMessage(){
		if(turnsOnField<=1){
			BackgroundGui.getLogField().append("-This card cannot be used because its just a round in the field!!\n"+"************\n");
		}
		if(attacked){
			BackgroundGui.getLogField().append("-You have already used the card on this round!!\n"+"************\n");
		}
	}



}
