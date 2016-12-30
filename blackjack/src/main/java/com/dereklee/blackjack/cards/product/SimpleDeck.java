package com.dereklee.blackjack.cards.product;

import java.util.ArrayList;
import java.util.List;

import com.dereklee.blackjack.CardI;
import com.dereklee.blackjack.Suit;
import com.dereklee.blackjack.cards.creator.CardDeckShuffleFactory;

/**
 * Creates a small subset(13) of standard cards containing one suit.
 * @author Derek
 *
 */
public class SimpleDeck extends CardDeck {
	
	private CardDeckShuffleFactory shuffleFactory;
	private Suit suit;
	
	public SimpleDeck(CardDeckShuffleFactory factory, Suit suit) {
		this.shuffleFactory = factory;
		this.suit = suit;
		deck = new ArrayList<CardI>();
	}	

	@Override
	public void prepare() {
		createSuit(suit);
	}
	
	private void createSuit(Suit suit) {
		addFaceCards(suit);
		addNumberCards(suit);
		addAce(suit);
	}

	@Override
	public void shuffle() {
		if (shuffleFactory != null) {
			shuffleFactory.shuffle(this);
		}
	}

}
