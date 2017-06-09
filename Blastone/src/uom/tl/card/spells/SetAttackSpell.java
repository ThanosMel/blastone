package uom.tl.card.spells;

import uom.tl.card.MinionCard;
import uom.tl.gui.maingame.BackgroundGui;
import uom.tl.player.Player;
//klash h opoia allazei to damage mia kartas tupou minion.
public class SetAttackSpell extends SpellCard{
	int value;
	public SetAttackSpell(int mana, String imagePath, String targetSelection) {
		super(mana, imagePath, targetSelection);
		
	}

	@Override
	public void action(MinionCard minion) {
		if(!attacked&&(turnsOnField>1)){
			Player active =getBoard().getActivePlayer();
			BackgroundGui.getLogField().append("-You changed the damage of a minion!!\n"+"************\n");
			minion.setDmg(value);
			active.getField().removeCardFromCardArea(this);
			
			
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
