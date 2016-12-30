package com.dereklee.blackjack.model;

import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.dereklee.blackjack.Shoe;
import com.dereklee.blackjack.cards.creator.CardDeckFactory;
import com.dereklee.blackjack.cards.creator.StandardDeckFactory;
import com.dereklee.blackjack.cards.creator.StandardDeckShuffleFactory;
import com.dereklee.blackjack.cards.product.CardDeckType;

public class GameMediatorTest {

	private static Logger logger = LogManager.getLogger();
	
	/**
	 *  Tests TODO
	 *  1) @testGame_SimpleDeckNoSuffle: test cutting card is given on initial deal and Hand is dealt a subsequent card
	 *  2) need functionality in GM to assert the cardValue for a given hand
	 *  3) with a standard deck not shuffled, assert the number of rounds played
	 */
	
	
	@Test
	public void testGame_SimpleDeckNoSuffle() {
//		CardDeckFactory fact = new StandardDeckFactory(new StandardDeckShuffleFactory());
		int numDecks = 1;
		Shoe shoe = new Shoe(numDecks, new StandardDeckFactory(), CardDeckType.SIMPLE_STANDARD_DECK);
		GameMediator gm = new GameMediator(shoe);
		runRound(gm,5);
		runRound(gm,5);
		runRound(gm,5);
		if (gm.isGameOver()) {
			logger.debug("Game Over");
		}
		runRound(gm,5);
		logger.debug("Game Client ends..");
	}
	
	void runRound(GameMediator gm,int numHands) {
		DealerHand dealer = null;
		int i=1;
		for (;i<numHands; i++) {
			gm.addHand(new Hand(gm,i));
		}
		dealer = new DealerHand(gm,i);
		gm.addHand(dealer);
		gm.runRound();
		logger.debug("Round ends..");
	}	

/*	private int getNumRounds(boolean isTest) {
		GameMediator gm = new GameMediator(1, isTest);
		int counter = 0;
		while(!gm.isGameOver()) {
			runRound(gm,4);
			counter++;			
		}
		return counter;
	}*/
	
}
