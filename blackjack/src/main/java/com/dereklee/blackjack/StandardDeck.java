package com.dereklee.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dereklee.blackjack.util.BjUtil;

public class StandardDeck implements DeckI {
	
	private List<CardI> sortedDeck;
	private List<CardI> deck;
	private long	seed;
	
	
	public StandardDeck() {
		init();	
//		initTest();
	}
	
	// seed for shuffle
	public StandardDeck(long seed) {
		this();
		this.seed = seed;
	}	

	public void initTest() {
		// create only a small set of standard cards
		sortedDeck = new ArrayList<CardI>();
		deck = new ArrayList<CardI>();
		String suit = "spades";
		sortedDeck.add(new NumberCard(7, suit, "seven"));
		sortedDeck.add(new NumberCard(6, suit, "six"));
		sortedDeck.add(new NumberCard(5, suit, "five"));
		sortedDeck.add(new NumberCard(4, suit, "four"));
		sortedDeck.add(new NumberCard(3, suit, "three"));
		sortedDeck.add(new NumberCard(2, suit, "two"));
		BjUtil.shuffle(sortedDeck, deck, (seed>0) ? seed : System.currentTimeMillis());
	}
	
	public void init() {
		// create 52 standard cards
		sortedDeck = new ArrayList<CardI>();
		deck = new ArrayList<CardI>();
		createSuit("spades");
		createSuit("hearts");
		createSuit("clubs");
		createSuit("diamonds");
		BjUtil.shuffle(sortedDeck, deck, (seed>0) ? seed : System.currentTimeMillis());
	}
	
	private void createSuit(String suit) {
		addFaceCards(suit);
		addNumberCards(suit);
		addAce(suit);
	}

	private void addAce(String suit) {
		sortedDeck.add(new Ace(1, suit, "ace"));
	}

	private void addNumberCards(String suit) {
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

	private void addFaceCards(String suit) {
		sortedDeck.add(new FaceCard(10, suit, "king"));
		sortedDeck.add(new FaceCard(10, suit, "queen"));
		sortedDeck.add(new FaceCard(10, suit, "jack"));
	}


	
	public List<CardI> getDeck() {
		return deck;
	}

}
