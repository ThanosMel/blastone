package uom.tl.card.spells;

import java.util.ArrayList;
import java.util.Random;

import uom.tl.card.Card;
import uom.tl.card.MinionCard;
import uom.tl.gui.maingame.BackgroundGui;
import uom.tl.player.Player;
//klash h opoia antiproswpeuei tis kartes pou allazoun to hp mias kartas
public class SetHpSpell extends SpellCard {
	private int value;
	private String target;
	private String randomTarget;
	public SetHpSpell(int mana, String imagePath, String targetSelection) {
		super(mana, imagePath, targetSelection);
		
	}

	public void action(){
		if(!attacked&&(turnsOnField>1)){
			if(targetSelection.equals("NONE")){
				
				Player opponent=getBoard().getOpponentPlayer();
				Player active=getBoard().getActivePlayer();
				if(target.equals("ALL_MINIONS")){
					if(opponent.getField().returnNumOfMinions()!=0 && active.getField().returnNumOfMinions()!=0){
						BackgroundGui.getLogField().append("-You have destroyed all minion cards from the field!!\n"+"************\n");
						opponent.getField().removeAllMinionFromCardArea();
						active.getField().removeAllMinionFromCardArea();
						active.getField().removeCardFromCardArea(this);
					}
					else{
						BackgroundGui.getLogField().append("-No minion cards in field!!\n"+"************\n");
					}
					
				}
				if(target.equals("ENEMY_MINIONS")){
	
					if(randomTarget.equals("true")){
						
						ArrayList<Card>opCards=opponent.getField().getCardsInField();
						ArrayList<MinionCard>minionCards=new ArrayList<>();
						for(Card c:opCards){
							if(c instanceof MinionCard){
								minionCards.add((MinionCard) c);
							}
							
						}
						Random r=new Random();
						if(!minionCards.isEmpty()){
							BackgroundGui.getLogField().append("-You have randomly destroyed an enemy minion!!\n"+"************\n");
							MinionCard minionTarget=minionCards.get(r.nextInt(minionCards.size()));
							opponent.getField().removeCardFromCardArea(minionTarget);
							active.getField().removeCardFromCardArea(this);
						}
						else{
							BackgroundGui.getLogField().append("-No enemy minions in the field!!\n"+"************\n");
						}
						
						
						
					}
				}
			
				
			}
		}
		else{
			this.logMessage();
		}
		
	}
	public void action(MinionCard minion) {
		if(!attacked&&(turnsOnField>1)){
			Player opponent=getBoard().getOpponentPlayer();
			Player active=getBoard().getActivePlayer();
			if(targetSelection.equals("MINIONS")){
				BackgroundGui.getLogField().append("-You changed the health of a minion!!\n"+"************\n");
				minion.setHp(value);
				if(value<=0){
					opponent.getField().removeCardFromCardArea(minion);
				}
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

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getRandomTarget() {
		return randomTarget;
	}

	public void setRandomTarget(String randomTarget) {
		this.randomTarget = randomTarget;
	}
	

}
