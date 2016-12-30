package com.dereklee.blackjack.cards.creator;

import com.dereklee.blackjack.cards.product.CardDeck;
import com.dereklee.blackjack.cards.product.CardDeckType;

public abstract class CardDeckFactory {
	
	protected CardDeckShuffleFactory shuffleFactory;
	
	public CardDeckFactory() {
	}
	
	public CardDeckFactory(CardDeckShuffleFactory aShuffleFactory) {
		this.shuffleFactory = aShuffleFactory;
	}
	
	// create a single card deck
	protected abstract CardDeck createCardDeck(CardDeckType type);

	/**
	 * Prepare a single card deck
	 * @param type CardDeckType
	 * @return a CardDeck
	 */
	public CardDeck prepareCardDeck(CardDeckType type) {
		return prepareCardDecks(type, 1);
	}

	/**
	 * Prepare multiple Card Decks
	 * @param type CardDeckType
	 * @return a CardDeck
	 */
	public CardDeck prepareCardDecks(CardDeckType type, int numDecks) {
		CardDeck cardDeck = createCardDeck(type);
		// for each card deck
		for (int i = 0; i < numDecks; i++) {			
			cardDeck.prepare();
			cardDeck.shuffle();
		}
		return cardDeck;			
		
	}	

	// convenience method for adding a shuffle factory, post construction.
	public void setShuffleFactory(CardDeckShuffleFactory aShuffleFactory) {
		this.shuffleFactory = aShuffleFactory;
	}
	
	// convenience method for setting the seed to a shuffle factory, post construction.
	// pre-condition: shuffle factory must be set
	public void setShuffleSeed(long seed) {
		if(shuffleFactory!=null) {
			shuffleFactory.setSeed(seed);
		}
	}
	
}
