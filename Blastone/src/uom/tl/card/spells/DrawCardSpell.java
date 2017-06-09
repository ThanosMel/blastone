package uom.tl.card.spells;

import uom.tl.card.Card;
import uom.tl.card.MinionCard;
import uom.tl.gui.maingame.BackgroundGui;
import uom.tl.player.Player;

public class DrawCardSpell extends SpellCard {
	//deixnei poses kartes tha travixthoun.
	private int value;
	
	public DrawCardSpell(int mana, String imagePath,String targetSelection) {
		super(mana, imagePath,targetSelection);
	}

	public void action() {
		if(!attacked&&(turnsOnField>1)){
			if(targetSelection.equals("NONE")){
				String valueString=Integer.toString(value);
				if(getBoard().getActivePlayer().getField().getDeck().getDeck().size()>value){
					BackgroundGui.getLogField().append("-"+ valueString +" cards have been added to your hand!!\n"+"************\n");
				}
				
				getBoard().getActivePlayer().addNCardsToHand(value);
				Player active=getBoard().getActivePlayer();
				active.getField().removeCardFromCardArea(this);
				
			}
		}
		else{
			this.logMessage();
		}
		
	}
	public void setValue(int value){
		this.value=value;
	}

	@Override
	public void action(MinionCard minion) {
		// TODO Auto-generated method stub
		
	}

}
