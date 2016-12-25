package com.dereklee.blackjack;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {

	@Test
	public void test() {
		CardI ks = new FaceCard(10, "spades", "king");
		CardI kh = new FaceCard(10, "hearts", "king");
		CardI kd = new FaceCard(10, "diamonds", "king");
		CardI kc = new FaceCard(10, "clubs", "king");

		
		assertEquals("king", ks.getName());
		assertEquals("spades", ks.getSuit());
		assertEquals(10, ks.getValue());
		
		
		CardI as = new Ace(1, "spades", "ace");
		CardI ah = new Ace(11, "hearts", "ace");
		
		assertEquals("ace", ah.getName());
		assertEquals("hearts", ah.getSuit());
		assertEquals(11, ah.getValue());		
		
	}

}
