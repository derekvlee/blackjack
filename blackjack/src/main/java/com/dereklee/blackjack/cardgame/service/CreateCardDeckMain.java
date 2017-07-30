package com.dereklee.blackjack.cardgame.service;

import com.dereklee.blackjack.cardgame.creator.BlackJackCardGameStore;
import com.dereklee.blackjack.cardgame.creator.CardGameStore;
import com.dereklee.blackjack.cardgame.product.CardGame;
import com.dereklee.blackjack.cardgame.product.CardGameType;

// used to test the concept
public class CreateCardDeckMain {

	public static void main(String[] args) {
		CardGameStore gameStore = new BlackJackCardGameStore();
		CardGame game = gameStore.prepareGame(CardGameType.BLACKJACK_STANDARD);
		//CardGame game = new BlackJackCardGame();
		game.getInitialCardDealNumber();
		
		// TODO needs more work
	}
}
