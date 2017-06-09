package uom.tl.card.spells;

import java.util.ArrayList;
import java.util.Random;

import uom.tl.card.Card;
import uom.tl.card.MinionCard;
import uom.tl.gui.maingame.BackgroundGui;
import uom.tl.player.Player;
//Antiproswpeuei tis kartes tupou spell pou kanoun damage se kartes minion h ton antipalo hrwa.
public class DamageSpell extends SpellCard{
	//to damage ths kartas.
	private int value;
	//metavlith pou deixnei an o stoxos einai random.
	//e.g deal 2 damage to random minion.
	private String randomTarget;
	//metavlhth pou deixnei poios einai o stoxos mia kartas pou kanei damage randomly
	//e.g ALL_MINIONS,ALL_ENEMY_MINIONS
	private String target;
	public DamageSpell(int mana,String imagePath,String targetSelection){
		super(mana,imagePath,targetSelection);
		
	}

	
	public void action() {
		if(!attacked&&(turnsOnField>1)){
			
			if(targetSelection.equals("NONE")){	
				attack2();
			}
		}
		else{
			this.logMessage();
		}
		
		
	}
	

	public void action(MinionCard card) {
		if(!attacked&&(turnsOnField>1)){
			if(targetSelection.equals("ANY")){
				if(card==null){
					attack1(null);
				}
				else{
					attack1(card);
				}
				
			}
			
			else if(targetSelection.equals("MINIONS")){
				if(card!=null){
					attack3(card);
				}
			}
		}
		else{
			this.logMessage();
		}

	}
	public void attack1(MinionCard card){
		
			Player active =getBoard().getActivePlayer();
			Player opponent=getBoard().getOpponentPlayer();
			if(card==null){
				opponent.setHp(opponent.getHp()-value);
				active.getField().removeCardFromCardArea(this);
				BackgroundGui.getLogField().append("-You attacked the enemy hero!!\n"+"************\n");
			}
			else{
				BackgroundGui.getLogField().append("-You attacked an enemy minion!!\n"+"************\n");
				if(card.getHp()-this.value<=0){
					opponent.getField().removeCardFromCardArea(card);
					active.getField().removeCardFromCardArea(this);
					
				}
				else{
					card.setHp(card.getHp()-value);
					active.getField().removeCardFromCardArea(this);
				}
			}
	}
	public void attack2(){
		Player active =getBoard().getActivePlayer();
		Player opponent=getBoard().getOpponentPlayer();
	
		if(!(randomTarget==null)&& randomTarget.equals("true")){
			if(target.equals("ENEMY_MINIONS")){
				ArrayList<Card> opCards=opponent.getField().getCardsInField();
				ArrayList<MinionCard> mc=new ArrayList<MinionCard>();
				for(Card c:opCards){
					if(c instanceof MinionCard){
						mc.add((MinionCard) c);
					}
				}
				if(!mc.isEmpty()){
					Random rand = new Random();
					BackgroundGui.getLogField().append("-You randomly attacked one of the enemy minions!!\n"+"************\n");
					MinionCard minionTarget=mc.get(rand.nextInt(mc.size()));
					int mcHp=minionTarget.getHp();
					if(value-mcHp>=0){
						opponent.getField().removeCardFromCardArea(minionTarget);
						active.getField().removeCardFromCardArea(this);
					}
					else{
						minionTarget.setHp( minionTarget.getHp()-value);
						active.getField().removeCardFromCardArea(this);
					}
				}
			
			}
			
		}
		else{
			
			if(target.equals("ALL_ENEMY_MINIONS")){
				ArrayList<Card> opCards=opponent.getField().getCardsInField();
				ArrayList<MinionCard> mc=new ArrayList<MinionCard>();
				for(Card c:opCards){
					if(c instanceof MinionCard){
						mc.add((MinionCard) c);
					}
				}
				if(!mc.isEmpty()){
					BackgroundGui.getLogField().append("-You attacked all enemy minions in the field!!\n"+"************\n");
					for(MinionCard minion:mc){
						int mcHp=minion.getHp();
						if(value-mcHp>=0){
							opponent.getField().removeCardFromCardArea(minion);
							active.getField().removeCardFromCardArea(this);
						}
						else{
							minion.setHp(minion.getHp()-value);
							active.getField().removeCardFromCardArea(this);
						}
					}
				}
				
			}
			else if(target.equals("ALL_CHARACTERS")){
				BackgroundGui.getLogField().append("-You attacked all the charachters in the field!!\n"+"************\n");
				opponent.setHp(opponent.getHp()-value);
				active.setHp(active.getHp()-value);
				
				ArrayList<Card> opCards=opponent.getField().getCardsInField();
				ArrayList<Card> yourCards=active.getField().getCardsInField();
				ArrayList<MinionCard> mcOp=new ArrayList<MinionCard>();
				ArrayList<MinionCard> mcYours=new ArrayList<MinionCard>();
				for(Card c:opCards){
					if(c instanceof MinionCard){
						mcOp.add((MinionCard) c);
					}
				}
				for(Card c:yourCards){
					if(c instanceof MinionCard){
						mcYours.add((MinionCard) c);
					}
				}
				for(MinionCard minion:mcOp){
					int mcHp=minion.getHp();
					if(value-mcHp>=0){
						opponent.getField().removeCardFromCardArea(minion);
						
					}
					else{
						minion.setHp(minion.getHp()-value);
						
					}
				}
				for(MinionCard minion:mcYours){
					int mcHp=minion.getHp();
					if(value-mcHp>=0){
						active.getField().removeCardFromCardArea(minion);
						
					}
					else{
						minion.setHp(minion.getHp()-value);
						
					}

				}
				active.getField().removeCardFromCardArea(this);
				
				
			}
			else if(target.equals("ENEMY_CHARACTERS")){
				BackgroundGui.getLogField().append("-You attacked all the enemy charachters in the field!!\n"+"************\n");
				opponent.setHp(opponent.getHp()-value);
				ArrayList<Card> opCards=opponent.getField().getCardsInField();
				ArrayList<MinionCard> mcOp=new ArrayList<MinionCard>();
				for(Card c:opCards){
					if(c instanceof MinionCard){
						mcOp.add((MinionCard) c);
					}
				}
			
				for(MinionCard minion:mcOp){
					int mcHp=minion.getHp();
					if(value-mcHp>=0){
						opponent.getField().removeCardFromCardArea(minion);
						
					}
					else{
						minion.setHp(minion.getHp()-value);
					}
				}
				active.getField().removeCardFromCardArea(this);
	
				
			}
			else if(target.equals("ALL_MINIONS")){
				
				ArrayList<Card> opCards=opponent.getField().getCardsInField();
				ArrayList<Card> yourCards=active.getField().getCardsInField();
				ArrayList<MinionCard> mcOp=new ArrayList<MinionCard>();
				ArrayList<MinionCard> mcYours=new ArrayList<MinionCard>();
				for(Card c:opCards){
					if(c instanceof MinionCard){
						mcOp.add((MinionCard) c);
					}
				}
				for(Card c:yourCards){
					if(c instanceof MinionCard){
						mcYours.add((MinionCard) c);
					}
				}
				if(!mcYours.isEmpty() || !mcOp.isEmpty()){
					BackgroundGui.getLogField().append("-You attacked all minions in the field!!\n"+"************\n");
					for(MinionCard minion:mcOp){
						int mcHp=minion.getHp();
						if(value-mcHp>=0){
							opponent.getField().removeCardFromCardArea(minion);
							active.getField().removeCardFromCardArea(this);
						}
						else{
							minion.setHp(minion.getHp()-value);
							active.getField().removeCardFromCardArea(this);
						}
					}
					for(MinionCard minion:mcYours){
						int mcHp=minion.getHp();
						if(value-mcHp>=0){
							active.getField().removeCardFromCardArea(minion);
							active.getField().removeCardFromCardArea(this);
						}
						else{
							minion.setHp(minion.getHp()-value);
							active.getField().removeCardFromCardArea(this);
						}

					}
					
				}
					
					
				
				
			}
		}
		
	}
	public void attack3(MinionCard mc){
		BackgroundGui.getLogField().append("-You attacked an enemy minion!!\n"+"************\n");
		Player active =getBoard().getActivePlayer();
		Player opponent=getBoard().getOpponentPlayer();
		if(this.value>=mc.getHp()){
			opponent.getField().removeCardFromCardArea(mc);
			active.getField().removeCardFromCardArea(this);
			
		}
		else{
			mc.setHp(mc.getHp()-value);
			active.getField().removeCardFromCardArea(this);
		}
		
	}
	public String getTargetSelection() {
		return targetSelection;
	}

	public void setTargetSelection(String targetSelection) {
		this.targetSelection = targetSelection;
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

}
