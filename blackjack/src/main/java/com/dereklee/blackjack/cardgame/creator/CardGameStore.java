package com.dereklee.blackjack.cardgame.creator;

import com.dereklee.blackjack.cardgame.product.CardGame;
import com.dereklee.blackjack.cardgame.product.CardGameType;

/**
 * 
 * @author Derek
 *
 */
public abstract class CardGameStore {

	public CardGame prepareGame(CardGameType type) { 
		
		CardGame game = createCardGame(type);
		game.getInitialCardDealNumber();
		// TODO others
		
		return game;
	}

	protected abstract CardGame createCardGame(CardGameType type);
}
