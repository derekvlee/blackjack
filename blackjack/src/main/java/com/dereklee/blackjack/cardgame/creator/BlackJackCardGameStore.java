package com.dereklee.blackjack.cardgame.creator;

import com.dereklee.blackjack.cardgame.product.BlackJackCardGame;
import com.dereklee.blackjack.cardgame.product.CardGame;
import com.dereklee.blackjack.cardgame.product.CardGameType;

/**
 * 
 * @author Derek
 *
 */
public class BlackJackCardGameStore extends CardGameStore {

	@Override
	protected CardGame createCardGame(CardGameType type) {
		switch(type) {
		case BLACKJACK_STANDARD:
			return new BlackJackCardGame();
		default: 
				
		}
		return null;
	}

}
