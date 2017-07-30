package com.dereklee.blackjack.model;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.dereklee.blackjack.Shoe;
import com.dereklee.blackjack.cardgame.creator.BlackJackCardGameStore;
import com.dereklee.blackjack.cardgame.creator.CardGameStore;
import com.dereklee.blackjack.cardgame.product.CardGame;
import com.dereklee.blackjack.cardgame.product.CardGameType;
import com.dereklee.blackjack.cards.creator.StandardDeckFactory;
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
		CardGameStore gameStore = new BlackJackCardGameStore();
		CardGame game = gameStore.prepareGame(CardGameType.BLACKJACK_STANDARD);	
//		CardDeckFactory fact = new StandardDeckFactory(new StandardDeckShuffleFactory());
		int numDecks = 1;
		Shoe shoe = new Shoe(numDecks, new StandardDeckFactory(), CardDeckType.SIMPLE_STANDARD_DECK);
		AbstractGameMediator gm = new GameMediator(game,shoe);
		runRound(gm,2);
		assertTrue(!gm.isGameOver());
		/*
		 * 21:02:08.800 [main] DEBUG com.dereklee.blackjack.model.GameMediator - --- End of Round Summary ---
			21:02:08.800 [main] DEBUG com.dereklee.blackjack.model.GameMediator - number of hands at end of round: 2  // TODO should be 1 (exclude bust hands)
			21:02:08.800 [main] DEBUG com.dereklee.blackjack.model.GameMediator - AbstractHand [handNum=1, cards=[10,10,9], total=29]
			21:02:08.800 [main] DEBUG com.dereklee.blackjack.model.GameMediator - DealerHand [handNum=2, cards=[10,10], total=20]
		 */
				
		runRound(gm,5);
		/*
		 * 21:02:08.806 [main] DEBUG com.dereklee.blackjack.model.GameMediator - --- End of Round Summary ---
			21:02:08.806 [main] DEBUG com.dereklee.blackjack.model.GameMediator - number of hands at end of round: 5
			21:02:08.806 [main] DEBUG com.dereklee.blackjack.model.GameMediator - AbstractHand [handNum=1, cards=[8,3], total=11]
			21:02:08.807 [main] DEBUG com.dereklee.blackjack.model.GameMediator - AbstractHand [handNum=2, cards=[7,2], total=9]
			21:02:08.807 [main] DEBUG com.dereklee.blackjack.model.GameMediator - AbstractHand [handNum=3, cards=[6,1], total=7]
			21:02:08.807 [main] DEBUG com.dereklee.blackjack.model.GameMediator - AbstractHand [handNum=4, cards=[5], total=5]
			21:02:08.807 [main] DEBUG com.dereklee.blackjack.model.GameMediator - DealerHand [handNum=5, cards=[4], total=4]
			21:02:08.808 [main] DEBUG com.dereklee.blackjack.model.GameMediatorTest - Round ends..
		 */
	}
	
	void runRound(AbstractGameMediator gm,int numHands) {
		DealerHand dealer = null;
		int i=1;
		for (;i<numHands; i++) {
			gm.addHand(new PlayerHand(gm,i));
		}
		dealer = new DealerHand(gm,i);
		gm.addHand(dealer);
		gm.runRound();
		logger.debug("Round ends..");
	}	

	
}
