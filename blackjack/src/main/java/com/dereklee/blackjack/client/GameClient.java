package com.dereklee.blackjack.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dereklee.blackjack.model.DealerHand;
import com.dereklee.blackjack.model.DealerHandObs;
import com.dereklee.blackjack.model.GameMediator;
import com.dereklee.blackjack.model.Hand;

public class GameClient {

	private static Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) {
		
		GameClient client = new GameClient();
		// TODO could do initial two card deal first
		// but also need to handle cutting card and no more cards
		// wanted to use observer pattern to notify Hands of dealers up-card.
		// I think it's best to keep the dealing logic inside GameMediator.
		
		GameMediator gm = new GameMediator(1);
		client.runRound(gm);
		client.runRound(gm);
		client.runRound(gm);
		client.runRound(gm);
		logger.debug("Game Client ends..");
	}
	
	void runRound(GameMediator gm) {
		DealerHandObs dealer = null;
		int i=0;
		for (i=1; i<5; i++) {
			gm.addHand(new Hand(gm,i));
		}
		dealer = new DealerHandObs(gm,i);
		gm.addHand(dealer);
		gm.runRound();
		logger.debug("Round ends..");
	}
}
