package com.dereklee.blackjack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.dereklee.blackjack.cards.creator.CardDeckFactory;
import com.dereklee.blackjack.cards.creator.StandardDeckFactory;
import com.dereklee.blackjack.cards.product.CardDeckType;
import com.dereklee.blackjack.util.BjConstants;

public class ShoeTest {
	
	private Logger logger = LogManager.getLogger();
	
	@Test
	public void testShoeSimpleDeckNoShuffle() {
		
		List<String> cardNames = Arrays.asList("king","queen","jack","ten","nine","eight","seven","six","five","cutting-card", "four","three","two","ace");
		List<Integer> cardValues = Arrays.asList(10,10,10,10,9,8,7,6,5,0,4,3,2,1);
		Suit diamonds = Suit.DIAMONDS;
		
		//CardDeckShuffleFactory shuffle = new StandardDeckShuffleFactory();
		CardDeckFactory cdf = new StandardDeckFactory();
		int numDecks = 1;
		Shoe shoe = new Shoe(numDecks, cdf, CardDeckType.QUARTER_DECK);
		assertEquals((numDecks*BjConstants.SIMPLE_DECK_SIZE)+1, shoe.getDeckSize()); // + 1 for card cut
		
		CardI card = null;
		// we expect the cutting card position equal last 1/3 deck(s)
		int cutPos = (BjConstants.SIMPLE_DECK_SIZE - (BjConstants.SIMPLE_DECK_SIZE/3)) ;
				
		for(int i=0; i<BjConstants.SIMPLE_DECK_SIZE+1; i++) {
			assertTrue(shoe.hasNext());
			card = shoe.next();
			assertEquals(cardNames.get(i), card.getName());	
			assertEquals(((int)cardValues.get(i)), card.getValue());
			if(i == cutPos) {
				assertEquals(Suit.NO_SUIT, card.getSuit());
			} else {
				assertEquals(diamonds, card.getSuit());
			}
		}
				
	}
	
	@Test
	public void testShoeStandardDeckNoShuffle() {
		//CardDeckShuffleFactory shuffle = new StandardDeckShuffleFactory();
		CardDeckFactory cdf = new StandardDeckFactory();
		int numDecks = 1;
		Shoe shoe = new Shoe(numDecks, cdf, CardDeckType.STANDARD_DECK);
		assertEquals((numDecks*BjConstants.STANDARD_DECK_SIZE)+1, shoe.getDeckSize()); // + 1 for card cut
		assertTrue(shoe.hasNext());
		CardI card = null;
		card = shoe.next();
		
		assertEquals("king", card.getName());
		assertEquals(Suit.SPADES, card.getSuit());
		assertEquals(10, card.getValue());
		
		card = shoe.next();
		assertEquals("queen", card.getName());
		assertEquals(Suit.SPADES, card.getSuit());
		assertEquals(10, card.getValue());
		
		card = shoe.next();
		assertEquals("jack", card.getName());
		assertEquals(Suit.SPADES, card.getSuit());
		assertEquals(10, card.getValue());
		
		card = shoe.next();
		assertEquals("ten", card.getName());
		assertEquals(Suit.SPADES, card.getSuit());
		assertEquals(10, card.getValue());	
		
		card = shoe.next();
		assertEquals("nine", card.getName());
		assertEquals(Suit.SPADES, card.getSuit());
		assertEquals(9, card.getValue());			
				
	}	
	
}
