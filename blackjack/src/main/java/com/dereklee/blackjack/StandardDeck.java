package com.dereklee.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dereklee.blackjack.util.BjUtil;

public class StandardDeck implements DeckI {
	
	private List<CardI> sortedDeck;
	private List<CardI> deck;
	private long	seed;
	
	public StandardDeck(boolean isTest) {
		if(isTest) {
			initTest();	
		}
	}	
	
	public StandardDeck() {
		init();	
	}
	
	// seed for shuffle
	public StandardDeck(long seed) {
		this();
		this.seed = seed;
	}	

	public void initTest() {
		// create only a small set(6) of standard cards
		sortedDeck = new ArrayList<CardI>();
		deck = new ArrayList<CardI>();
		Suit suit = Suit.SPADES;
		sortedDeck.add(new NumberCard(7, suit, "seven"));
		sortedDeck.add(new NumberCard(6, suit, "six"));
		sortedDeck.add(new NumberCard(5, suit, "five"));
		sortedDeck.add(new NumberCard(4, suit, "four"));
		sortedDeck.add(new NumberCard(3, suit, "three"));
		sortedDeck.add(new NumberCard(2, suit, "two"));
		deck = sortedDeck;
		//BjUtil.shuffle(sortedDeck, deck, (seed>0) ? seed : System.currentTimeMillis());
	}
	
	public void init() {
		// create 52 standard cards
		sortedDeck = new ArrayList<CardI>();
		deck = new ArrayList<CardI>();
		createSuit(Suit.SPADES);
		createSuit(Suit.HEARTS);
		createSuit(Suit.CLUBS);
		createSuit(Suit.DIAMONDS);
		BjUtil.shuffle(sortedDeck, deck, (seed>0) ? seed : System.currentTimeMillis());
	}
	
	private void createSuit(Suit suit) {
		addFaceCards(suit);
		addNumberCards(suit);
		addAce(suit);
	}

	private void addAce(Suit suit) {
		sortedDeck.add(new Ace(1, suit, "ace"));
	}

	private void addNumberCards(Suit suit) {
		sortedDeck.add(new NumberCard(10, suit, "ten"));
		sortedDeck.add(new NumberCard(9, suit, "nine"));
		sortedDeck.add(new NumberCard(8, suit, "eight"));
		sortedDeck.add(new NumberCard(7, suit, "seven"));
		sortedDeck.add(new NumberCard(6, suit, "six"));
		sortedDeck.add(new NumberCard(5, suit, "five"));
		sortedDeck.add(new NumberCard(4, suit, "four"));
		sortedDeck.add(new NumberCard(3, suit, "three"));
		sortedDeck.add(new NumberCard(2, suit, "two"));
	}

	private void addFaceCards(Suit suit) {
		sortedDeck.add(new FaceCard(10, suit, "king"));
		sortedDeck.add(new FaceCard(10, suit, "queen"));
		sortedDeck.add(new FaceCard(10, suit, "jack"));
	}


	
	public List<CardI> getDeck() {
		return deck;
	}

}
