package com.dereklee.blackjack.cards.creator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dereklee.blackjack.CardI;
import com.dereklee.blackjack.cards.product.CardDeck;

public class StandardDeckShuffleFactory implements CardDeckShuffleFactory {
	
	private static Logger logger = LogManager.getLogger();
	private long seed;
	
	/**
	 * Set the seed for the Random number generator
	 * @param seed
	 */
	public StandardDeckShuffleFactory(long seed) {
		this.seed = seed;
	}
	
	public StandardDeckShuffleFactory() {}
	
	@Override
	public void shuffle(CardDeck deck) {
		shuffle(deck, this.seed);
	}

	@Override
	public void shuffle(CardDeck cardDeck, long seed) {
		Random rand = (seed > 0) ? new Random(seed) : new Random();
		List<CardI> deck = cardDeck.getDeck();
		List<CardI> oldDeck = deck;
		List<CardI> newDeck = new ArrayList<CardI>();
		while(!oldDeck.isEmpty()) {
			int n = rand.nextInt(oldDeck.size());
			CardI c = oldDeck.remove(n);
			newDeck.add(c);
			//logger.debug("{"+n+"} " + c);
		}		
		cardDeck.setDeck(newDeck);
	}

	@Override
	public void setSeed(long seed) {
		this.seed = seed;
	}

}
