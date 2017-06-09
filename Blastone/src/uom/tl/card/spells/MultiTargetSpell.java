package uom.tl.card.spells;

import java.util.ArrayList;
import java.util.Random;

import uom.tl.card.Card;
import uom.tl.card.MinionCard;
import uom.tl.gui.maingame.BackgroundGui;
import uom.tl.player.Player;
//klash gia tis kartes pou tautoxrona kanoun damage kai se antipales minion kartes alla kai ston antipalo hrwa.
public class MultiTargetSpell extends SpellCard {
	//to damage
	private int value;
	//an o stoxos pou tha ginei to damage einai tuxaios h an prepei na epileksei o xrhsths pou tha ginei to damage.
	private String randomTarget;
	
	private String target;
	//o arithmos twn stoxwn pou tha ginei to damage.
	private int targetsNum;
	
	public MultiTargetSpell(int mana, String imagePath, String targetSelection) {
		super(mana, imagePath, targetSelection);
		
	}

	public void action(){
		if(!attacked&&(turnsOnField>1)){
			Player opponent =getBoard().getOpponentPlayer();
			Player active= getBoard().getActivePlayer();
			if(targetSelection.equals("NONE")){
				if(target.equals("ENEMY_MINIONS")){
					if(randomTarget.equals("true"))
					{
						Random r=new Random();
						ArrayList<MinionCard>minionCards=new ArrayList<>();
						ArrayList<Card>opCards=opponent.getField().getCardsInField();
						for(Card c:opCards){
						
							if(c instanceof MinionCard){
								
								minionCards.add((MinionCard) c);
							}
							
						}
						if(!(minionCards.isEmpty())){
							BackgroundGui.getLogField().append("-You attacked " +targetsNum+" enemy minions randomly!!\n"+"************\n");
							MinionCard minionTarget;
							if(minionCards.size()<targetsNum){
								minionTarget=minionCards.get(0);
								if(minionTarget.getHp()-value<=0){
									minionCards.remove(minionTarget);
									opponent.getField().removeCardFromCardArea(minionTarget);
								}
								else{
									minionTarget.setHp(minionTarget.getHp()-value);
								}
								active.getField().removeCardFromCardArea(this);
							}
							else{
								for(int i=0; i<targetsNum; i++){
									minionTarget=minionCards.get(r.nextInt(minionCards.size()));
									if(minionTarget.getHp()-value<=0){
										minionCards.remove(minionTarget);
										opponent.getField().removeCardFromCardArea(minionTarget);
									}
									else{
										minionTarget.setHp(minionTarget.getHp()-value);
									}
							
									
								}
							}
							
								
								
						}
						
							
							
						
						
						
					}
				}
			}
		}
		
		
	}
	public void action(MinionCard minion) {
		if(!attacked&&(turnsOnField>1)){
			Player opponent =getBoard().getOpponentPlayer();
			Player active= getBoard().getActivePlayer();
			if(targetSelection.equals("MINIONS")){
				if(target.equals("BOTH")){
					opponent.setHp(opponent.getHp()-value);
					if(value>minion.getHp()){
						opponent.getField().removeCardFromCardArea(minion);
					}
					else{
						minion.setHp(minion.getHp()-value);
					}
					active.getField().removeCardFromCardArea(this);
					
				}
			}
				
		}
		
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}


	public String getRandomTarget() {
		return randomTarget;
	}


	public void setRandomTarget(String randomTarget) {
		this.randomTarget = randomTarget;
	}


	public String getTarget() {
		return target;
	}


	public void setTarget(String target) {
		this.target = target;
	}


	public int getTargetsNum() {
		return targetsNum;
	}


	public void setTargetsNum(int targetsNum) {
		this.targetsNum = targetsNum;
	}
	

}
