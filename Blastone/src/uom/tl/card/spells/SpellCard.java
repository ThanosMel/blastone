package uom.tl.card.spells;

import uom.tl.card.Card;
import uom.tl.card.MinionCard;
import uom.tl.gui.maingame.BackgroundGui;
//klash pou antiproswpeuei tis kartes tupou spell.
//kathe fora pou mia karta tupou spell paizete feugei amesws apo to tablo.
public abstract class SpellCard extends Card {
	//oi kartes tupou spell xarakthrizontai epipleon apo mia string metavlhth targetSelection
	//an to targetSelection einai NONE o xrhsths apla pataei panw sth karta kai automata h karta kanei thn "special"
	//epithesh ths.an to target selection den einai NONE tote prepei na epileksei pou tha ginei h epithesh.
	protected String targetSelection;
	
	public SpellCard(int mana,String imagePath,String targetSelection){
		super(mana,imagePath);
		this.targetSelection=targetSelection;
	}

	public void action(){
		
	}
		
	//Mia methodos pou voithaei sthn klash Listener sto paketo uom.tl.listeners
	//ekei prepei na gnwrizoume an mia karta epivalei ston xrhsth na epileksei allo hrwa h karta gia na paiktei h an tha paiktei monh ths.
	//e.g : card with targetSelection!="NONE" --> choose a minion to heal.
	//    	card with targetSelection==NONE -->randomly destroy 2 minions.
	public String targetSelection(){
		if(targetSelection=="NONE"){
			return "NONE";
		}
		else{
			return "ACTION";
		}
	}
	public void setTargetSelection(String targetSelection){
		this.targetSelection=targetSelection;
	}
	public abstract void action(MinionCard minion) ;
	public String getTargetSelection(){
		return targetSelection;
	}
		
	

}
