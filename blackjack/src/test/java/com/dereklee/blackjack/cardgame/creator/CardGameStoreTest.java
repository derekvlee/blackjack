package com.dereklee.blackjack.cardgame.creator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dereklee.blackjack.Shoe;
import com.dereklee.blackjack.cardgame.product.CardGame;
import com.dereklee.blackjack.cardgame.product.CardGameType;
import com.dereklee.blackjack.cards.creator.StandardDeckFactory;
import com.dereklee.blackjack.cards.product.CardDeckType;
import com.dereklee.blackjack.model.AbstractGameMediator;
import com.dereklee.blackjack.model.GameMediator;

public class CardGameStoreTest {

	@Test
	public void testBlackJack() {
		CardGameStore gameStore = new BlackJackCardGameStore();
		CardGame game = gameStore.prepareGame(CardGameType.BLACKJACK_STANDARD);
		//game.initDeal();
		
		int numDecks = 1;
		Shoe shoe = new Shoe(numDecks, new StandardDeckFactory(), CardDeckType.QUARTER_DECK);
		AbstractGameMediator gm = new GameMediator(game, shoe);
		
		
	}

}
