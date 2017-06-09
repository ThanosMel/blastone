package uom.tl.card;
//Sthn ousia prokeite gia minion card apla borei na paiksei apo thn prwth gura pou feugei apo to xeri tou paikth.
public class ChargeCardClass extends MinionCard {
	
	public ChargeCardClass(int hp, int mana, int dmg, String imagePath) {
		super(hp, mana, dmg, imagePath);
		turnsOnField=2;
	}
	
}
