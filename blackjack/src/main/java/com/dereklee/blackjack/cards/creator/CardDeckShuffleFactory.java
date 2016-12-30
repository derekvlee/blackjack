package com.dereklee.blackjack.cards.creator;

import com.dereklee.blackjack.cards.product.CardDeck;

public interface CardDeckShuffleFactory {

	public void shuffle(CardDeck deck);
	
	public void shuffle(CardDeck deck, long seed);
	
	public void setSeed(long seed);
}
