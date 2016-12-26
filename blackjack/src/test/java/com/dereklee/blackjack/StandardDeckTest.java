package com.dereklee.blackjack;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class StandardDeckTest {

	@Test
	public void testDeck() {
		StandardDeck stdDeck = new StandardDeck();
		List<CardI> deck = stdDeck.getDeck();
		assertEquals(52, deck.size());
		for(int i=0; i<10; i++) {
			System.out.println(deck.get(i));
		}
	}
	
	@Test
	public void testPartialDeck() {
		StandardDeck stdDeck = new StandardDeck(true);
		List<CardI> deck = stdDeck.getDeck();
		assertEquals(6, deck.size());

		// assert first card
		assertEquals(7, deck.get(0).getValue());
		assertEquals("seven", deck.get(0).getName());
		assertEquals(Suit.SPADES, deck.get(0).getSuit());
		
		// assert last card
		int last = deck.size()-1;
		assertEquals(2, deck.get(last).getValue());
		assertEquals("two", deck.get(last).getName());
		assertEquals(Suit.SPADES, deck.get(last).getSuit());		

	}

}
