package com.dereklee.blackjack.util;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dereklee.blackjack.CardI;
import com.dereklee.blackjack.cards.creator.CardDeckShuffleFactory;
import com.dereklee.blackjack.cards.product.CardDeck;

public class BjUtil {
	
	private static Logger logger = LogManager.getLogger();
	
	public static void shuffle(List<CardI> oldDeck, List<CardI> newDeck) {
		shuffle(oldDeck, newDeck, System.currentTimeMillis());
	}

	public static void shuffle(List<CardI> oldDeck, List<CardI> newDeck, long seed) {
		Random rand = new Random(seed);
		while(!oldDeck.isEmpty()) {
			int n = rand.nextInt(oldDeck.size());
			CardI c = oldDeck.remove(n);
			newDeck.add(c);
			//logger.debug("{"+n+"} " + c);
		}
	}
	
	public static void printAll(List<CardI> deck) {
		printDeck(deck, deck.size());
	}
	/**
	 * Print n cards
	 * @param deck
	 * @param num
	 */
	public static void printDeck(List<CardI> deck, int n) {
		for(int i=0; i<n; i++) {
			CardI card = deck.get(i);
			logger.debug("["+i+"]" + card);
		}
		logger.debug("");
	}
	
	public static void printDeck2(List<CardI> decks) {
		for(int i=0; i<decks.size(); i+=52) {
			//CardI card = deck.get(i);
			logger.debug("["+i+"]" + decks.get(i));
			logger.debug("["+(i+1)+"]" + decks.get(i+1));
			logger.debug("["+(i+2)+"]" + decks.get(i+2));
			logger.debug("");
		}
		logger.debug("");
	}


}
