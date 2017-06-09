package uom.tl.card.spells;

import uom.tl.card.MinionCard;
import uom.tl.gui.maingame.BackgroundGui;
import uom.tl.player.Player;
//klash gia tis kartes pou pollaplasiazoun to damage mia minion kartas mas.
public class MultiplyAttackSpell extends SpellCard{
	//to kata poso tha pollaplasiastei to dmg ths minion kartas mas.
	int value;
	
	public MultiplyAttackSpell(int mana, String imagePath, String targetSelection) {
		super(mana, imagePath, targetSelection);
		
	}
	public void action(MinionCard minion) {
		if(!attacked&&(turnsOnField>1)){
			Player active=getBoard().getActivePlayer();
			if(active.getField().getCardsInField().contains(minion)){
				BackgroundGui.getLogField().append("-You increased the attack of your minion!!\n"+"************\n");
				minion.setDmg(value*minion.getDmg());
				active.getField().removeCardFromCardArea(this);
			}
			
			 
		}
		else{
			this.logMessage();
		}
		
	}
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
