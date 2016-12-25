package com.dereklee.blackjack;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShoeTest {

	@Test
	public void testShoe() {
		Shoe shoe = new Shoe(4);
		
		// TODO add more tests
		// assert total cards in shoe
		// how to assert randomness
		assertEquals((4*52)+1, shoe.getDeckSize()); // + 1 for card cut
		CardI nxt = null;
		for (int i = 0; i < 10; i++) {
			nxt = shoe.next();
			System.out.println("next card: " + nxt);
		}
	}
	
	// TODO test for cutting card
	@Test
	public void testShoe_CuttingCard() {
		int nDecks = 1;
		Shoe shoe = new Shoe(nDecks);
		assertEquals((nDecks*52)+1, shoe.getDeckSize()); // + 1 for card cut
		CardI nxt = null;
		for (int i = 0; i < shoe.getDeckSize(); i++) {
			nxt = shoe.next();
			System.out.println("[testShoe_CuttingCard()] next card: " + nxt);
		}		
	}
}
