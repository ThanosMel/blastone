package uom.tl.player;

import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONException;
import org.json.JSONObject;

import uom.tl.card.Card;
import uom.tl.card.ChargeCardClass;
import uom.tl.card.MinionCard;
import uom.tl.card.Position;
import uom.tl.card.TauntCardClass;
import uom.tl.card.spells.BuffSpell;
import uom.tl.card.spells.DamageSpell;
import uom.tl.card.spells.DrawCardSpell;
import uom.tl.card.spells.HealSpell;
import uom.tl.card.spells.MultiTargetSpell;
import uom.tl.card.spells.MultiplyAttackSpell;
import uom.tl.card.spells.SetAttackSpell;
import uom.tl.card.spells.SetHpSpell;

public class Deck {


	private final ArrayList<Card> deck;
	private  ArrayList<JSONObject> jsonObj;
	
	
	public Deck() throws JSONException{
		deck=new ArrayList<Card>();
		
	}
	public void setJsonObj(ArrayList<JSONObject> jsonObj){
		this.jsonObj=jsonObj;
	}
	
	//methodos h opoia dexetai ena arraylist<jsonobject>.
	//Diavazontas apo ta arxeia json plhrfories pou aforoun stoixeia ths kartas opws healthPoints,manaPoints,damage klp
	//dhmiourgei antikeimena tupou card ta opoia prostethei sto arraylist deck.
	public void buildDeck(ArrayList<JSONObject> JSONar) throws JSONException{
		if(!JSONar.isEmpty()){
			//diatrexoume ton pinaka 
			for(JSONObject obj:JSONar){
				if(obj.getString("type").equals("MINION")){
					int healthPoints=obj.getInt("baseHp");
					int manaPoints=obj.getInt("baseManaCost");
					int attackDmg=obj.getInt("baseAttack");
					String imagePath=obj.getString("iconName");
					
					if(obj.has("class")){
						if(obj.getString("class").equals("ChargeClass")){
							ChargeCardClass chargeCard=new ChargeCardClass(healthPoints,manaPoints,attackDmg,imagePath);
							chargeCard.setPosition(Position.DECK);
							deck.add(chargeCard);
						
							
						}
						else{
							TauntCardClass tauntCard=new TauntCardClass(healthPoints,manaPoints,attackDmg,imagePath);
							 tauntCard.setPosition(Position.DECK);
							deck.add(tauntCard);
						}
					}
					else{
						MinionCard mc=new MinionCard(healthPoints,manaPoints,attackDmg,imagePath);
						mc.setPosition(Position.DECK);
						deck.add(mc);

					}
					
					
				}
				else{
					int manaPoints=obj.getInt("baseManaCost");
					String imagePath=obj.getString("iconName");
					String targetSelection=obj.getString("targetSelection");
				
					if(obj.has("class")){
						if(obj.getString("class").equals("DamageSpell")){
							DamageSpell damageCard=new DamageSpell(manaPoints,imagePath,targetSelection);
							if(obj.has("value")){
								int value=obj.getInt("value");
								damageCard.setValue(value);
							}
							if(obj.has("randomTarget")){
								String randomTarget=obj.getString("randomTarget");
								damageCard.setRandomTarget(randomTarget);
							}
							if(obj.has("target")){
								String target=obj.getString("target");
								damageCard.setTarget(target);
							}
	
							damageCard.setPosition(Position.DECK);
							deck.add(damageCard);
							
							
						}
						else if(obj.getString("class").equals("DrawCardSpell")){
							DrawCardSpell drawCard=new DrawCardSpell(manaPoints,imagePath,targetSelection);
							if(obj.has("value")){
								int value=obj.getInt("value");
								drawCard.setValue(value);
							}
							drawCard.setPosition(Position.DECK);
							deck.add(drawCard);
						}
						else if(obj.getString("class").equals("DoubleAttackSpell")){
							MultiplyAttackSpell multiCard=new MultiplyAttackSpell(manaPoints,imagePath,targetSelection);
							if(obj.has("value")){
								int value=obj.getInt("value");
								multiCard.setValue(value);
							}
							multiCard.setPosition(Position.DECK);
							deck.add(multiCard);
							
						}
						else if(obj.getString("class").equals("BuffSpell")){
							BuffSpell buffCard= new BuffSpell(manaPoints,imagePath,targetSelection);
							if(obj.has("attackBonus")){
								int dmg=obj.getInt("attackBonus");
								buffCard.setAttackBonus(dmg);
							}
							if(obj.has("hpBonus")){
								int hp=obj.getInt("hpBonus");
								buffCard.setHpBonus(hp);
							}
							buffCard.setPosition(Position.DECK);
							deck.add(buffCard);
						}
						else if(obj.getString("class").equals("HealSpell")){
							HealSpell healCard=new HealSpell(manaPoints,imagePath,targetSelection);
							if(obj.has("value")){
								int value=obj.getInt("value");
								 healCard.setValue(value);
							}
							healCard.setPosition(Position.DECK);
							deck.add(healCard);
						}
						else if(obj.getString("class").equals("SetAttackSpell")){
							SetAttackSpell setDmgCard=new SetAttackSpell(manaPoints,imagePath,targetSelection);
							if(obj.has("value")){
								int value=obj.getInt("value");
								 setDmgCard.setValue(value);
							}
							setDmgCard.setPosition(Position.DECK);
							deck.add(setDmgCard);
						}
						else if(obj.getString("class").equals("SetHpSpell")){
							SetHpSpell setHpCard=new SetHpSpell(manaPoints,imagePath,targetSelection);
							if(obj.has("value")){
								int value=obj.getInt("value");
								setHpCard.setValue(value);
							}
							if(obj.has("randomTarget")){
								String randomTarget=obj.getString("randomTarget");
								setHpCard.setRandomTarget(randomTarget);
							}
							if(obj.has("target")){
								String target=obj.getString("target");
								setHpCard.setTarget(target);
							}
							setHpCard.setPosition(Position.DECK);
							deck.add(setHpCard);
									
									
						}
						else if(obj.getString("class").equals("MultiTargetSpell")){
							MultiTargetSpell mtsCard=new MultiTargetSpell(manaPoints,imagePath,targetSelection);
							if(obj.has("value")){
								int value=obj.getInt("value");
								mtsCard.setValue(value);
							}
							if(obj.has("randomTarget")){
								String randomTarget=obj.getString("randomTarget");
								mtsCard.setRandomTarget(randomTarget);
							}
							if(obj.has("target")){
								String target=obj.getString("target");
								mtsCard.setTarget(target);
							}
							if(obj.has("targetsNum")){
								int targetsNum=obj.getInt("targetsNum");
								mtsCard.setTargetsNum(targetsNum);
									
							}
							mtsCard.setPosition(Position.DECK);
							deck.add(mtsCard);
						}
	
						
					}
					
				}
			}
		}
		
	}
	
	public void shuffleDeck() {

		Collections.shuffle(deck);

	}
	//afairei mia karta apo ton pinaka deck.
	public Card drawOneCard() {
		return deck.remove(0);
	}
	//afairei n kartes apo ton pinaka deck.
	public ArrayList<Card> drawNCards(int n) {

		ArrayList<Card> cards = new ArrayList<Card>(n);

		for (int i = 0; i < n; i++)
			cards.add(deck.remove(0));

		return (cards);

	}

	public ArrayList<JSONObject> getJsonArray(){
		return this.jsonObj;
	}
	public ArrayList<Card> getDeck(){
		return deck;
	}
	public void removeElements(){
		this.jsonObj.removeAll(jsonObj);
	}
	

	
}
	
