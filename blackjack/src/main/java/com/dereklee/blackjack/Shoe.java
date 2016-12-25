package com.dereklee.blackjack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dereklee.blackjack.util.BjUtil;

/**
 * The Shoe represents a Dealers Shoe, containing multiple shuffled card decks.
 * It's function is to dispense cards to 'hands', one at a time, until the cutting card is reached. 
 * The cutting card is usually handed to the receiving player for next cut.
 * The cut card is then placed by the dealer, usually cutting off 2 decks of a 6 deck shoe.
 * 
 * @author Derek
 *
 */
public class Shoe {
	
	private static final int BOUNDS = 10*10*100;
	private int 		numDecks;
	private int 		randInt;
	private long 		randLong;
	private List<CardI> decks;
	private Iterator<CardI> shoeIt;
	private CardI		nextCard;
	private Logger 		logger = LogManager.getLogger();
	
	public Shoe(int numDecks) {
		this.numDecks = numDecks;
		init();			
	}
	
	private void init()  {	
		logger.debug("Shoe init");
		decks = new ArrayList<CardI>();
		List<CardI> decksTmp = new ArrayList<CardI>();
		for (int i = 0; i < numDecks; i++) {
			decksTmp.addAll(new StandardDeck(genRand()).getDeck());
		}
		BjUtil.shuffle(decksTmp, decks);
		
		// add cutting card at cutting position = 1/3 of end decks.
		int cutPos = (decks.size() - (decks.size()/3)) ;
		logger.debug("cutPos: " + cutPos);
		decks.add(cutPos,new CuttingCard(0, "no-suit", "cutting-card"));
		shoeIt = decks.iterator();
		
		BjUtil.printAll(decks);
		
		if(shoeIt.hasNext()) {
			nextCard = shoeIt.next();
		}
	}	

	private long genRand() {
		randInt = new Random(System.currentTimeMillis()).nextInt(BOUNDS);
		randLong = new Random(System.currentTimeMillis()).nextLong();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return randLong * randInt;
	}
	
	public boolean hasNext() {
		return nextCard != null;
	}
	
	public CardI next() {
		CardI card = nextCard;
		if(shoeIt.hasNext()) {
			nextCard = shoeIt.next();
			if(nextCard.equals(new CuttingCard(0, "no-suit", "cutting-card"))){
				logger.debug("card is cutting card");
			}
		} else {
			nextCard = null;
		}
		return card;
	}

	public int getDeckSize() {
		return decks.size();
	}

}
