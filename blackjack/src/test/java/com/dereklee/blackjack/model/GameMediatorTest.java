package com.dereklee.blackjack.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.dereklee.blackjack.Card;
import com.dereklee.blackjack.CardI;
import com.dereklee.blackjack.CuttingCard;
import com.dereklee.blackjack.FaceCard;
import com.dereklee.blackjack.NumberCard;
import com.dereklee.blackjack.Shoe;
import com.dereklee.blackjack.Suit;
import com.dereklee.blackjack.cardgame.creator.BlackJackCardGameStore;
import com.dereklee.blackjack.cardgame.creator.CardGameStore;
import com.dereklee.blackjack.cardgame.product.CardGame;
import com.dereklee.blackjack.cardgame.product.CardGameType;
import com.dereklee.blackjack.cards.creator.StandardDeckFactory;
import com.dereklee.blackjack.cards.product.CardDeckType;

public class GameMediatorTest {

	private static Logger logger = LogManager.getLogger();
	private PlayerHand ph;
	private DealerHand dh;
	
	/**
	 *  Tests TODO
	 *  1) @testGame_SimpleDeckNoSuffle: test cutting card is given on initial deal and Hand is dealt a subsequent card
	 *  2) need functionality in GM to assert the cardValue for a given hand
	 *  3) with a standard deck not shuffled, assert the number of rounds played
	 */
	
//	TODO
	// add more tests, use lower cards so that we can test strategy
	// test what happens when a player busts
	// test money won/lost (betting functionality required)
	
	@Test
	public void testMockedDeck() {
		CardGameStore gameStore = new BlackJackCardGameStore();
		CardGame game = gameStore.prepareGame(CardGameType.BLACKJACK_STANDARD);	
//		CardDeckFactory fact = new StandardDeckFactory(new StandardDeckShuffleFactory());
		int numDecks = 1;
		Shoe spyShoe = spy(new Shoe(numDecks, new StandardDeckFactory(), CardDeckType.QUARTER_DECK));
		AbstractGameMediator gm = new GameMediator(game,spyShoe); 
		
		List<CardI> cards = getCards();
		Mockito.doAnswer(new SequenceAnswer<CardI>(getCards())).when(spyShoe).next();
		runRound(gm);
	}
	
	private List<CardI> getCards() {
		Suit suit = Suit.CLUBS;
		List<CardI> cards = new ArrayList<CardI>();
		cards.add(new NumberCard(5, suit, "five"));
		cards.add(new NumberCard(4, suit, "four"));
		cards.add(new NumberCard(3, suit, "three"));
		cards.add(new NumberCard(2, suit, "two"));
		
		suit = Suit.SPADES;
		cards.add(new NumberCard(5, suit, "five"));
		cards.add(new NumberCard(4, suit, "four"));
		cards.add(new NumberCard(3, suit, "three"));
		cards.add(new NumberCard(2, suit, "two"));		
		
		suit = Suit.HEARTS;
		cards.add(new FaceCard(10, suit, "king"));
		cards.add(new CuttingCard(0, Suit.NO_SUIT, "cutting-card"));
		cards.add(new FaceCard(10, suit, "queen"));
		cards.add(new FaceCard(10, suit, "jack"));
		cards.add(new FaceCard(7, suit, "seven"));
		cards.add(new FaceCard(4, suit, "four"));
		
		return cards;
	}
	
	// 
	/**
	 * This method is from: 
	 * 	https://stackoverflow.com/questions/33310960/java-enumerating-list-in-mockitos-thenreturn
	 * It allows us to create a Mocked Sequence Answer from a List<T>.
	 * @author Derek
	 *
	 * @param <T>
	 */
	private class SequenceAnswer<T> implements Answer<T> {
	    private Iterator<T> resultIterator;

	    // the last element is always returned once the iterator is exhausted, as with thenReturn()
	    private T last;

	    public SequenceAnswer(List<T> results) {
	        this.resultIterator = results.iterator();
	        this.last = results.get(results.size() - 1);
	    }

	    public T answer(InvocationOnMock invocation) throws Throwable {
	        if (resultIterator.hasNext()) {
	            return resultIterator.next();
	        }
	        return last;
	    }

	}
	
	// test with quarter deck, not shuffled.
	@Test
	public void testQuarterDeck() {
		CardGameStore gameStore = new BlackJackCardGameStore();
		CardGame game = gameStore.prepareGame(CardGameType.BLACKJACK_STANDARD);	
//		CardDeckFactory fact = new StandardDeckFactory(new StandardDeckShuffleFactory());
		int numDecks = 1;
		Shoe shoe = new Shoe(numDecks, new StandardDeckFactory(), CardDeckType.QUARTER_DECK);
		AbstractGameMediator gm = spy(new GameMediator(game,shoe)); // mockito spying here
		runRound(gm);
		
		// end of round
		
		// Player cards 
		assertEquals(20, ph.getCardsValue());
		assertEquals(1, ph.getHandNum());
		List<CardI> cards = ph.getCards();
		assertEquals(2,cards.size());
		
		CardI cardI = cards.get(0);
		assertEquals("king",cardI.getName());
		assertEquals(Suit.DIAMONDS,cardI.getSuit());
		assertEquals(10,cardI.getValue());
		
		cardI = cards.get(1);
		assertEquals("jack",cardI.getName());
		assertEquals(Suit.DIAMONDS,cardI.getSuit());
		assertEquals(10,cardI.getValue());

		// Dealer cards 
		assertEquals(20, dh.getCardsValue());
		assertEquals(2, dh.getHandNum());
		cards = dh.getCards();
		assertEquals(2,cards.size());
		
		cardI = cards.get(0);
		assertEquals("queen",cardI.getName());
		assertEquals(Suit.DIAMONDS,cardI.getSuit());
		assertEquals(10,cardI.getValue());
		
		cardI = cards.get(1);
		assertEquals("ten",cardI.getName());
		assertEquals(Suit.DIAMONDS,cardI.getSuit());
		assertEquals(10,cardI.getValue());
		
		// test game is not over
		assertTrue(!gm.isGameOver());
	}
	
	// deal a round 
	void runRound(AbstractGameMediator gm) {
		dh = null;
		ph = null;
		ph = new PlayerHand(gm,1);
		dh = new DealerHand(gm,2);
		gm.addHand(ph);
		gm.addHand(dh);
		gm.runRound();
		logger.debug("Round ends..");
	}		
	
	

		

	
}
