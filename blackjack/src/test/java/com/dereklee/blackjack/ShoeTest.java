package com.dereklee.blackjack;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShoeTest {

	@Test
	public void testShoeSize() {
		Shoe shoe = new Shoe(4);		
		assertEquals((4*52)+1, shoe.getDeckSize()); // + 1 for card cut
//		CardI nxt = null;
//		for (int i = 0; i < 10; i++) {
//			nxt = shoe.next();
//			System.out.println("next card: " + nxt);
//		}
	}
	
	@Test
	public void testShoeCuttingCard() {
		int nDecks = 1;
		boolean isTest = true;
		Shoe shoe = new Shoe(nDecks, isTest);
		assertEquals((6)+1, shoe.getDeckSize()); // + 1 for card cut
		CardI nxt = null;
		for (int i = 0; i < shoe.getDeckSize(); i++) {
			nxt = shoe.next();
			if(i==4) {
				assertEquals(Suit.NO_SUIT, nxt.getSuit());
				assertEquals("cutting-card", nxt.getName());
				assertEquals(0, nxt.getValue());
			}
		}		
	}
}
