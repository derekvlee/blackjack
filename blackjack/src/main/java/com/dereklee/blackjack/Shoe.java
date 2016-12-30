package com.dereklee.blackjack;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dereklee.blackjack.cards.creator.CardDeckFactory;
import com.dereklee.blackjack.cards.product.CardDeck;
import com.dereklee.blackjack.cards.product.CardDeckType;
import com.dereklee.blackjack.util.BjUtil;

/**
 * The Shoe represents a Dealers Shoe, containing multiple shuffled card decks.
 * It's function is to dispense cards to 'hands', one at a time, until the cutting card is reached. 
 * The cutting card is usually handed to the receiving player for the next cut.
 * After the cut, the cutting card is then inserted by the dealer into about the last 1/3 of the shoe (usually cutting off 2 decks of a 6 deck shoe).
 * 
 * @author Derek
 *
 */
public class Shoe {
	
	private static final int BOUNDS = 10*10*100;
	private static final int SLEEP_TIME_MS = 1*1000;
	private int 		numDecks;
	private int 		randInt;
	private long 		randLong;
	private List<CardI> decks;
	private Iterator<CardI> shoeIt;
	private CardI		nextCard;
	private Logger 		logger = LogManager.getLogger();
	private CardDeckFactory 		cardDeckFactory;
	private CardDeckType 			cardDeckType;
	
	public Shoe(int numDecks, CardDeckFactory aCardDeckFactory, CardDeckType deckType) {
		this.numDecks = numDecks;
		this.cardDeckFactory = aCardDeckFactory;
		this.cardDeckType = deckType;
		init();
	}
	
	/**
	 * Initialise the Shoe.
	 * Populates the Shoe with playing card deck(s).
	 * The decks can be shuffled/un-shuffled depending on the given shuffle factory.
	 * Inserts a cutting card.
	 * Prepares the shoe Iterator<CardI> ready to dispense the first playing card.
	 */	
	private void init() {
		logger.debug("Shoe init");				
		cardDeckFactory.setShuffleSeed(genRand());
		CardDeck cardDeck = cardDeckFactory.prepareCardDecks(cardDeckType, numDecks);
		decks = cardDeck.getDeck();
		insertCuttingCard();
		shoeIt = decks.iterator();
		
		BjUtil.printAll(decks); // TODO should only be used for debug
		
		if(shoeIt.hasNext()) {
			nextCard = shoeIt.next();
		} else {
			throw new RuntimeException("Error: Shoe has no cards");
		}
	}
	
	/**
	 * Insert the cutting card into the deck.
	 */
	private void insertCuttingCard() {		
		int cutPos = (decks.size() - (decks.size()/3)) ;
		logger.debug("cutPos: " + cutPos);
		decks.add(cutPos,new CuttingCard(0, Suit.NO_SUIT, "cutting-card"));
	}
	
	/**
	 * check for another card
	 * @return
	 */
	public boolean hasNext() {
		return nextCard != null;
	}
	
	/**
	 * return next card
	 * @return
	 */
	public CardI next() {
		CardI card = nextCard;
		if(shoeIt.hasNext()) {
			nextCard = shoeIt.next();
			if(nextCard.equals(new CuttingCard(0, Suit.NO_SUIT, "cutting-card"))){
				logger.debug("card is cutting card");
			}
		} else {
			nextCard = null;
		}
		return card;
	}
	
	// generate a random long based on the current time in milliseconds and
	// sleeps for a duration to allow for different seed generation
	private long genRand() {
		randInt = new Random(System.currentTimeMillis()).nextInt(BOUNDS);
		randLong = new Random(System.currentTimeMillis()).nextLong();
		try {
			Thread.sleep(SLEEP_TIME_MS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return randLong * randInt;
	}

	public int getDeckSize() {
		return decks.size();
	}

}
