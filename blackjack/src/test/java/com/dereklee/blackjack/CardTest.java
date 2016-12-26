package com.dereklee.blackjack;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {

	@Test
	public void testCards() {
		CardI ks = new FaceCard(10, Suit.SPADES, "king");
		CardI kh = new FaceCard(10, Suit.HEARTS, "king");
		CardI kd = new FaceCard(10, Suit.DIAMONDS, "king");
		CardI kc = new FaceCard(10, Suit.CLUBS, "king");

		
		assertEquals("king", ks.getName());
		assertEquals(Suit.SPADES, ks.getSuit());
		assertEquals(10, ks.getValue());
		
		
		CardI as = new Ace(1, Suit.SPADES, "ace");
		CardI ah = new Ace(11, Suit.HEARTS, "ace");
		
		assertEquals("ace", ah.getName());
		assertEquals(Suit.HEARTS, ah.getSuit());
		assertEquals(11, ah.getValue());		
		
	}

}
