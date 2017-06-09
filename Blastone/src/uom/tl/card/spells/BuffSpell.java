 package uom.tl.card.spells;

import java.util.ArrayList;

import uom.tl.card.Card;
import uom.tl.card.MinionCard;
import uom.tl.gui.maingame.BackgroundGui;
import uom.tl.player.Player;
//H klassh antiproswpeuei tis kartes pou sou dinoun attack damage kai health points san bonus-buff.
public class BuffSpell extends SpellCard {
	int attackBonus;
	int hpBonus;
	
	public BuffSpell(int mana, String imagePath, String targetSelection) {
		super(mana, imagePath, targetSelection);
		
	}
	//methodos h opoia dexetai to minion tou opoiou tha buffaristoun ta stats.
	public void action(MinionCard minion) {
		if(!attacked&& (turnsOnField>1)){
			Player active = getBoard().getActivePlayer();
			Player opponent=getBoard().getOpponentPlayer();
			ArrayList<Card> cards=active.getField().getCardsInField();
			
			if(cards.contains(minion)){
				if(minion instanceof MinionCard){
					int mcHp=minion.getHp();
					int mcDmg=minion.getDmg();
					//ignore
					if(this.getMana()==8){
						minion.setHp(hpBonus);
						minion.setDmg(attackBonus);
					}
					else{
						minion.setHp(mcHp+hpBonus);
						minion.setDmg(mcDmg+attackBonus);
					}
					BackgroundGui.getLogField().append("-You buffed the stats of your minion!!\n"+"************\n");
					active.getField().removeCardFromCardArea(this);
				}
			}
			else{
				//ignore
				if(hpBonus==1 && attackBonus==1 ){
					minion.setHp(hpBonus);
					minion.setDmg(attackBonus);
					active.getField().removeCardFromCardArea(this);
					BackgroundGui.getLogField().append("-You set stats of an enemy minion!!\n"+"************\n");
					
				}
				
				
				if(hpBonus==0 && attackBonus<0 ){
					minion.setHp(hpBonus);
					minion.setDmg(attackBonus);
					if(minion.getHp()<=0){
						opponent.getField().removeCardFromCardArea(minion);
					}
					
					active.getField().removeCardFromCardArea(this);
				}
				
			}
		
			
		}
		else{
			this.logMessage();
		}
		
	}

	public int getAttackBonus() {
		return attackBonus;
	}

	public void setAttackBonus(int attackBonus) {
		this.attackBonus = attackBonus;
	}

	public int getHpBonus() {
		return hpBonus;
	}

	public void setHpBonus(int hpBonus) {
		this.hpBonus = hpBonus;
	}
	
	

}
