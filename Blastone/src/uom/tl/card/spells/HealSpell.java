package uom.tl.card.spells;

import java.util.ArrayList;

import uom.tl.card.Card;
import uom.tl.card.MinionCard;
import uom.tl.gui.maingame.BackgroundGui;
import uom.tl.player.Player;
//klash gia tis kartes pou healaroun h ton hrwa mas h ena apo ta minions mas.
public class HealSpell extends SpellCard {
	//to poso tha mas healarei h karta.
	int value;


	public HealSpell(int mana, String imagePath, String targetSelection) {
		super(mana, imagePath, targetSelection);
		
	}
	//kaleite otan epilegoume na healaroume ton hrwa mas
	public void action(){
		if(!attacked&&(turnsOnField>1)){
			Player active = getBoard().getActivePlayer();
			if(active.getHp()==30){
				//active.setHp(30);
				BackgroundGui.getLogField().append("-Hero already has 30+ health ,you cant use this card!!\n"+"************\n");
				//active.getField().removeCardFromCardArea(this);   
				
			}
			else if(active.getHp()+value>30){
				active.setHp(30);
				active.getField().removeCardFromCardArea(this);
			
				
			}
			else{
				active.setHp(active.getHp()+value);	
				BackgroundGui.getLogField().append("-You buffed the stats of your hero!!\n"+"************\n");
				active.getField().removeCardFromCardArea(this);
			}
			
			
		}
		else{
			this.logMessage();
		}
		
	}
	//kaleite otan epilegoume na healroume ena minion mas
	public void action(MinionCard minion) {
		if(!attacked&&(turnsOnField>1)){
			if(targetSelection.equals("ANY")){
				if(minion==null){
					action();
				}
				else{
					Player active = getBoard().getActivePlayer();
					if(active.getField().getCardsInField().contains(minion)){
						BackgroundGui.getLogField().append("-You increased the health of your minion!!\n"+"************\n");
						minion.setHp(minion.getHp()+value);
						active.getField().removeCardFromCardArea(this);
					}
					
				}
			}
			else{
				this.logMessage();
			}
			
			
			
		}
		
		
		
	}
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
