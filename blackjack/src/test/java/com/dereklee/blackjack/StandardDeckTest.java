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

}
