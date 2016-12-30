package com.dereklee.blackjack.cards.product;

import java.util.ArrayList;
import java.util.List;

import com.dereklee.blackjack.CardI;
import com.dereklee.blackjack.Suit;
import com.dereklee.blackjack.cards.creator.CardDeckShuffleFactory;

public class StandardDeck extends CardDeck {
	
	private CardDeckShuffleFactory shuffleFactory;
	
	public StandardDeck(CardDeckShuffleFactory factory) {
		this.shuffleFactory = factory;
		deck = new ArrayList<CardI>(); 
	}	

	@Override
	public void prepare() {
		// create 52 standard cards
		createSuit(Suit.SPADES);
		createSuit(Suit.HEARTS);
		createSuit(Suit.CLUBS);
		createSuit(Suit.DIAMONDS);
	}	
	
	/**
	 * Create a suit of cards
	 * @param suit
	 */
	private void createSuit(Suit suit) {
		addFaceCards(suit);
		addNumberCards(suit);
		addAce(suit);
	}

	/**
	 * If we have a shuffleFactory, shuffle the cards based on the factory's implementation.
	 */
	@Override
	public void shuffle() {
		if (shuffleFactory != null) {
			shuffleFactory.shuffle(this);
		}
	}



}
