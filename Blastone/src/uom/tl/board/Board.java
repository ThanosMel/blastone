package uom.tl.board;

import java.util.Random;

import uom.tl.card.Card;
import uom.tl.gui.createdeck.ChooseHeroAndDeck;
import uom.tl.player.Player;


public class Board {

	private Player activePlayer;
	private Player opponentPlayer;
	private Player winner;

	public Board() {
		Card.setBoard(this);
	}
	//methodos pou epilegei tuxaia poios apo tous 2 paiktes ksekinaei
	public void whoStarts(Player p1, Player p2) {

		Random r = new Random();

		if (r.nextInt(2) == 0) {
			activePlayer = p1;
			opponentPlayer = p2;
		} else {
			activePlayer = p2;
			opponentPlayer = p1;
		}

	}
	//to paixnidi ksekinaei thetontas gia kathe paikth to field tou
	//kai to onoma tou hrwa pou tha paiksei
	public void startGame(Player p1, Player p2) {
		p1.setField(ChooseHeroAndDeck.getP1Field());
		p1.setHero(ChooseHeroAndDeck.getHerop1());
		p2.setField(ChooseHeroAndDeck.getP2Field());
		p2.setHero(ChooseHeroAndDeck.getHerop2());
	
		whoStarts(p1, p2);
		//stous 2 paiktes prosthetoume apo 3 kartes.
		p1.addNCardsToHand(3);
		p2.addNCardsToHand(3);

		//se auton pou ksekinaei 2os prosthetoume allh mia.
		opponentPlayer.addCardToHand();

	}

	public void nextPlayer() {

		Player temp = activePlayer;
		activePlayer = opponentPlayer;
		opponentPlayer = temp;
		activePlayer.addCardToHand();

	}

	public boolean isGameOver() {
		if (winner != null)
			return true;
		return false;
	}

	public Player getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}

	public Player getOpponentPlayer() {
		return opponentPlayer;
	}

	public void setOpponentPlayer(Player opponentPlayer) {
		this.opponentPlayer = opponentPlayer;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		if (isGameOver())
			return;
		this.winner = winner;
	}

}
