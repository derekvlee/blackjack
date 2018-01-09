package com.dereklee.blackjack.cards.creator;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.dereklee.blackjack.CardI;
import com.dereklee.blackjack.Suit;
import com.dereklee.blackjack.cards.product.CardDeck;
import com.dereklee.blackjack.cards.product.CardDeckType;
import com.dereklee.blackjack.util.BjConstants;

public class CardDeckFactoryTest {
	
	protected 	Logger 	  logger = LogManager.getLogger();

	// creates a standard deck without shuffling (i.e. no shuffle factory added) and asserts the sequence of cards
	@Test 
	public void testStandardDeckFactory() {
		CardDeckFactory fact = new StandardDeckFactory();
		CardDeck cardDeck = fact.prepareCardDeck(CardDeckType.STANDARD_DECK);		
		List<CardI> deck = cardDeck.getDeck();
		assertEquals(BjConstants.STANDARD_DECK_SIZE, deck.size());

		int i = 0;
		assertEquals("king", deck.get(i).getName());
		assertEquals(Suit.SPADES, deck.get(i).getSuit());
		assertEquals(10, deck.get(i).getValue());
		
		i=19;
		assertEquals("seven", deck.get(i).getName());
		assertEquals(Suit.HEARTS, deck.get(i).getSuit());
		assertEquals(7, deck.get(i).getValue());
		
		i=36;
		assertEquals("three", deck.get(i).getName());
		assertEquals(Suit.CLUBS, deck.get(i).getSuit());
		assertEquals(3, deck.get(i).getValue());		
	}
	
	// creates a simple deck (subset of standard deck) without shuffling and asserts the sequence of cards
	@Test
	public void testSimpleDeckFactory() {
		//CardDeckFactory fact = new StandardDeckFactory(new SimpleDeckShuffleFactory());
		CardDeckFactory fact = new StandardDeckFactory();
		CardDeck cardDeck = fact.prepareCardDeck(CardDeckType.QUARTER_DECK);		
		List<CardI> deck = cardDeck.getDeck();
		assertEquals(13, deck.size());
		

		int i = 0;
		assertEquals("king", deck.get(i).getName());
		assertEquals(Suit.DIAMONDS, deck.get(i).getSuit());
		assertEquals(10, deck.get(i).getValue());
		
		i=6;
		assertEquals("seven", deck.get(i).getName());
		assertEquals(Suit.DIAMONDS, deck.get(i).getSuit());
		assertEquals(7, deck.get(i).getValue());
	}
	
	// creates a standard deck without shuffling and asserts the sequence of cards.
	// then reuses the cardDeckFactory to create another deck.
	@Test 
	public void testReuseStandardDeckFactory() {
		CardDeckFactory fact = new StandardDeckFactory();
		CardDeck cardDeck = fact.prepareCardDeck(CardDeckType.STANDARD_DECK);		
		List<CardI> deck = cardDeck.getDeck();
		assertEquals(BjConstants.STANDARD_DECK_SIZE, deck.size());

		int i = 0;
		assertEquals("king", deck.get(i).getName());
		assertEquals(Suit.SPADES, deck.get(i).getSuit());
		assertEquals(10, deck.get(i).getValue());
		
		i=19;
		assertEquals("seven", deck.get(i).getName());
		assertEquals(Suit.HEARTS, deck.get(i).getSuit());
		assertEquals(7, deck.get(i).getValue());
		
		i=36;
		assertEquals("three", deck.get(i).getName());
		assertEquals(Suit.CLUBS, deck.get(i).getSuit());
		assertEquals(3, deck.get(i).getValue());	
		
		// reuse the cardDeckFactory
		cardDeck = fact.prepareCardDeck(CardDeckType.STANDARD_DECK);		
		deck = cardDeck.getDeck();
		assertEquals(52, deck.size());
	}	
	
	// creates multiple standard decks without shuffling and asserts the sequence of cards
	@Test
	public void testMulitpleCardDecks() {
		int nDecks = 2;
		CardDeckFactory fact = new StandardDeckFactory();
		CardDeck cardDecks = fact.prepareCardDecks(CardDeckType.STANDARD_DECK, nDecks);		
		List<CardI> decks = cardDecks.getDeck();
		assertEquals(nDecks*BjConstants.STANDARD_DECK_SIZE, decks.size());

		int pos1=0, pos2=19, pos3=36;
		
		// first deck
		int i = pos1;
		assertEquals("king", decks.get(i).getName());
		assertEquals(Suit.SPADES, decks.get(i).getSuit());
		assertEquals(10, decks.get(i).getValue());
		
		i=pos2;
		assertEquals("seven", decks.get(i).getName());
		assertEquals(Suit.HEARTS, decks.get(i).getSuit());
		assertEquals(7, decks.get(i).getValue());
		
		i=pos3;
		assertEquals("three", decks.get(i).getName());
		assertEquals(Suit.CLUBS, decks.get(i).getSuit());
		assertEquals(3, decks.get(i).getValue());		
		
		// second deck
		i=pos1 + BjConstants.STANDARD_DECK_SIZE;
		assertEquals("king", decks.get(i).getName());
		assertEquals(Suit.SPADES, decks.get(i).getSuit());
		assertEquals(10, decks.get(i).getValue());	
		
		i=pos2 + BjConstants.STANDARD_DECK_SIZE;
		assertEquals("seven", decks.get(i).getName());
		assertEquals(Suit.HEARTS, decks.get(i).getSuit());
		assertEquals(7, decks.get(i).getValue());
		
		i=pos3 + BjConstants.STANDARD_DECK_SIZE;
		assertEquals("three", decks.get(i).getName());
		assertEquals(Suit.CLUBS, decks.get(i).getSuit());
		assertEquals(3, decks.get(i).getValue());			
		//assertCards(decks, (pos1 + BjConstants.STANDARD_DECK_SIZE), "king", Suit.SPADES, 10);
	}	

	// creates multiple standard decks without shuffling and asserts the sequence of cards.
	// then reuses the cardDeckFactory to create another multiple deck.
	@Test
	public void testReuseMulitpleCardDecks() {
		int nDecks = 2;
		CardDeckFactory fact = new StandardDeckFactory();
		CardDeck cardDecks = fact.prepareCardDecks(CardDeckType.STANDARD_DECK, nDecks);		
		List<CardI> decks = cardDecks.getDeck();
		assertEquals(nDecks*BjConstants.STANDARD_DECK_SIZE, decks.size());
		
		int pos1=0, pos2=19, pos3=36;
		int i=pos2;
		assertEquals("seven", decks.get(i).getName());
		assertEquals(Suit.HEARTS, decks.get(i).getSuit());
		assertEquals(7, decks.get(i).getValue());		
		
		cardDecks = fact.prepareCardDecks(CardDeckType.STANDARD_DECK, nDecks);		
		decks = cardDecks.getDeck();
		assertEquals(nDecks*BjConstants.STANDARD_DECK_SIZE, decks.size());	
		
		i=pos2;
		assertEquals("seven", decks.get(i).getName());
		assertEquals(Suit.HEARTS, decks.get(i).getSuit());
		assertEquals(7, decks.get(i).getValue());			
	}
	
	// creates a standard deck and shuffles them
	@Test 
	public void testStandardDeckFactoryShuffle() {
		CardDeckFactory fact = new StandardDeckFactory(new StandardDeckShuffleFactory());
		CardDeck cardDeck = fact.prepareCardDeck(CardDeckType.STANDARD_DECK);		
		List<CardI> deck = cardDeck.getDeck();
		assertEquals(BjConstants.STANDARD_DECK_SIZE, deck.size());

		// TODO how to test the randomness of the deck
		
	}
}
