package com.dereklee.blackjack.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dereklee.blackjack.Shoe;
import com.dereklee.blackjack.cards.creator.CardDeckFactory;
import com.dereklee.blackjack.cards.creator.StandardDeckFactory;
import com.dereklee.blackjack.cards.creator.StandardDeckShuffleFactory;
import com.dereklee.blackjack.cards.product.CardDeckType;
import com.dereklee.blackjack.model.DealerHand;
import com.dereklee.blackjack.model.DealerHandObs;
import com.dereklee.blackjack.model.GameMediator;
import com.dereklee.blackjack.model.Hand;

public class GameClient {

	private static Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) {
		
		GameClient client = new GameClient();
		CardDeckFactory fact = new StandardDeckFactory(new StandardDeckShuffleFactory());
		Shoe shoe = new Shoe(1, new StandardDeckFactory(), CardDeckType.SIMPLE_STANDARD_DECK);
		
		GameMediator gm = new GameMediator(shoe);
		client.runRound(gm);
		client.runRound(gm);
		client.runRound(gm);
		client.runRound(gm);
		logger.debug("Game Client ends..");
	}
	
	void runRound(GameMediator gm) {
		DealerHand dealer = null;
		int i=0;
		for (i=1; i<5; i++) {
			gm.addHand(new Hand(gm,i));
		}
		dealer = new DealerHand(gm,i);
		gm.addHand(dealer);
		gm.runRound();
		logger.debug("Round ends..");
	}
}
