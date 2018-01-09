package com.dereklee.blackjack.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dereklee.blackjack.Shoe;
import com.dereklee.blackjack.cardgame.creator.BlackJackCardGameStore;
import com.dereklee.blackjack.cardgame.creator.CardGameStore;
import com.dereklee.blackjack.cardgame.product.CardGame;
import com.dereklee.blackjack.cardgame.product.CardGameType;
import com.dereklee.blackjack.cards.creator.StandardDeckFactory;
import com.dereklee.blackjack.cards.product.CardDeckType;
import com.dereklee.blackjack.model.AbstractGameMediator;
import com.dereklee.blackjack.model.DealerHand;
import com.dereklee.blackjack.model.GameMediator;
import com.dereklee.blackjack.model.PlayerHand;

public class GameClient {

	private static Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) {
		
		// Create the card game type
		CardGameStore gameStore = new BlackJackCardGameStore();
		CardGame game = gameStore.prepareGame(CardGameType.BLACKJACK_STANDARD);		
		// Create the Dealers shoe
		Shoe shoe = new Shoe(1, new StandardDeckFactory(), CardDeckType.STANDARD_DECK);
		// Instantiate the GameMediator
		AbstractGameMediator gm = new GameMediator(game, shoe);
		
		// Run the Game
		GameClient client = new GameClient();
		client.runRound(gm);
		client.runRound(gm);
		client.runRound(gm);
		client.runRound(gm);
		logger.debug("Game Client ends..");
	}
	
	void runRound(AbstractGameMediator gm) {
		DealerHand dealer = null;
		int i=0;
		for (i=1; i<5; i++) {
			gm.addHand(new PlayerHand(gm,i));
		}
		dealer = new DealerHand(gm,i);
		gm.addHand(dealer);
		gm.runRound();
		logger.debug("Round ends..");
	}
}
