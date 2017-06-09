
package uom.tl.card;

import uom.tl.gui.maingame.BackgroundGui;
import uom.tl.player.Player;
//Klash gia tis kartes tupou minion.
//mia minion karta menei sto tablo ews otou h zwh ths ginei 0 h katastrafei.
public class MinionCard extends Card {
	//oi kartes tupou minion ektos apo to mana exoun kai health points kai attack damage.
	private int hp;
	private int dmg;
	public MinionCard(int hp, int mana, int dmg,String imagePath) {
		super(mana,imagePath);
		if(this instanceof ChargeCardClass){
			turnsOnField=2;
		}
		this.hp=hp;
		this.dmg=dmg;
	
		
	}
	//mia karta tupou minion borei na epitethei eite sth zwh tou antipalou hrwa mesw ths public void action() .
	//h se antipalh karta tupou minion mesw ths public void action(MinionCard minion).
	public void action() {
		if(!attacked&& (turnsOnField>1)){
			Player opponent=getBoard().getOpponentPlayer();
			if(opponent.getField().fieldContainTauntCard()){
				BackgroundGui.getLogField().append("-Cant attack hero cause there is a taunt card in field\n"+"************\n");
				attacked=false;
			}
			else{
				attackHero();
				attacked=true;
			}
			
		}
		else{
			this.logMessage();
		
		}
		
	}
	public void action(MinionCard minion){
		if(minion!=null && !attacked && (turnsOnField>1)){
			attackMinion(minion);
		
		}
		else{
			this.logMessage();
		}
		
		
	}

	public void attackHero(){
		int opponentHp = getBoard().getOpponentPlayer().getHp();
		getBoard().getOpponentPlayer().setHp(opponentHp-this.dmg);
		BackgroundGui.getLogField().append("-You attacked the enemy hero!\n"+"************\n");
	}
	//otan mia dikia mas  karta tupou minion kanei epithesh se ena antipalo  minion
	//ektos apo to oti xanei zwh to antipalo minion xanei zwh kai h dikia mas karta.
	public void attackMinion(MinionCard minion){
		Player active = getBoard().getActivePlayer();
		Player opponent = getBoard().getOpponentPlayer();
		if(opponent.getField().fieldContainTauntCard()){
			if(minion instanceof TauntCardClass){
				
				BackgroundGui.getLogField().append("-You attacked an enemy minion!\n"+"************\n");
				if(this.dmg>=minion.getHp()){
					opponent.getField().removeCardFromCardArea(minion);
					this.hp=hp-minion.getDmg();
					if(this.hp<=0){
						active.getField().removeCardFromCardArea(this);
					}
				}
				else if(this.dmg<minion.getHp()){
					minion.hp=minion.hp-this.dmg;
					this.hp=this.hp-minion.dmg;
					if(this.hp<=0){
						active.getField().removeCardFromCardArea(this);
					}
				}
			}
			else{
				BackgroundGui.getLogField().append("-Cant attack minion cause there is a taunt card in field\n"+"************\n");
				
			}
		}
		else{
			attacked=true;
			BackgroundGui.getLogField().append("-You attacked an enemy minion!\n"+"************\n");
			if(this.dmg>=minion.getHp()){
				opponent.getField().removeCardFromCardArea(minion);
				this.hp=hp-minion.getDmg();
				if(this.hp<=0){
					active.getField().removeCardFromCardArea(this);
				}
			}
			else if(this.dmg<minion.getHp()){
				minion.hp=minion.hp-this.dmg;
				this.hp=this.hp-minion.dmg;
				if(this.hp<=0){
					active.getField().removeCardFromCardArea(this);
				}
			}
			
		}
			
		
	}
	public int getHp(){
		return hp;
	}
	public int getDmg(){
		return dmg;
	}
	public void setHp(int hp){
		this.hp=hp;
	}
	public void setDmg(int dmg){
		this.dmg=dmg;
	}


	


	

}
