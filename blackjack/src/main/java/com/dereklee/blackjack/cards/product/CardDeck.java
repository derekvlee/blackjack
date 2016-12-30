package com.dereklee.blackjack.cards.product;

import java.util.List;

import com.dereklee.blackjack.Ace;
import com.dereklee.blackjack.CardI;
import com.dereklee.blackjack.FaceCard;
import com.dereklee.blackjack.NumberCard;
import com.dereklee.blackjack.Suit;

public abstract class CardDeck {

	// instance members
	protected List<CardI> deck;
	
	/**
	 * Prepare deck(s)
	 */
	public abstract void prepare();
	
	/**
	 * Shuffle deck(s)
	 */
	public abstract void shuffle();


	protected void addAce(Suit suit) {
		deck.add(new Ace(1, suit, "ace"));
	}

	protected void addNumberCards(Suit suit) {
		deck.add(new NumberCard(10, suit, "ten"));
		deck.add(new NumberCard(9, suit, "nine"));
		deck.add(new NumberCard(8, suit, "eight"));
		deck.add(new NumberCard(7, suit, "seven"));
		deck.add(new NumberCard(6, suit, "six"));
		deck.add(new NumberCard(5, suit, "five"));
		deck.add(new NumberCard(4, suit, "four"));
		deck.add(new NumberCard(3, suit, "three"));
		deck.add(new NumberCard(2, suit, "two"));
	}

	protected void addFaceCards(Suit suit) {
		deck.add(new FaceCard(10, suit, "king"));
		deck.add(new FaceCard(10, suit, "queen"));
		deck.add(new FaceCard(10, suit, "jack"));
	}
	
	public List<CardI> getDeck() {
		return deck;
	}	
	
	public void setDeck(List<CardI> aDeck) {
		deck = aDeck;
	}

}
