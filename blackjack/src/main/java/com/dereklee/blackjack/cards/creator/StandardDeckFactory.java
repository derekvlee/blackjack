package com.dereklee.blackjack.cards.creator;

import com.dereklee.blackjack.Suit;
import com.dereklee.blackjack.cards.product.CardDeck;
import com.dereklee.blackjack.cards.product.CardDeckType;
import com.dereklee.blackjack.cards.product.SimpleDeck;
import com.dereklee.blackjack.cards.product.StandardDeck;

public class StandardDeckFactory extends CardDeckFactory {
	
	public StandardDeckFactory() {
		super();
	}
	
	public StandardDeckFactory(CardDeckShuffleFactory aShuffleFactory) {
		super(aShuffleFactory);		
	}

	@Override
	protected CardDeck createCardDeck(CardDeckType type) {
		
		CardDeck deck = null;
		switch(type) {
		case STANDARD_DECK:
			deck = new StandardDeck(shuffleFactory);
			break;
		case SIMPLE_STANDARD_DECK:
			deck = new SimpleDeck(shuffleFactory, Suit.DIAMONDS);
			break;
		}
		return deck;
	}

}
