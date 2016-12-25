package com.dereklee.blackjack.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameClient {

	private static Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) {
		
		GameClient client = new GameClient();
		// TODO could do initial two card deal first
		// but also need to handle cutting card and no more cards
		// wanted to use observer pattern to notify Hands of dealers up-card.
		// I think it's best to keep the dealing logic inside GameMediator.
		
		// TODO save as Git project 
		GameMediator gm = new GameMediator(1);
		client.runRound(gm);
		client.runRound(gm);
		client.runRound(gm);
		client.runRound(gm);
		logger.debug("Game Client ends..");
	}
	
	void runRound(GameMediator gm) {
		for (int i=1; i<5; i++) {
			gm.addHand(new Hand(gm,i));
		}
		gm.runRound();
		logger.debug("Round ends..");
	}
}