package com.dereklee.blackjack.model;

import java.util.Iterator;
import java.util.Observable;

import com.dereklee.blackjack.Shoe;
import com.dereklee.blackjack.cardgame.product.CardGame;

public abstract class AbstractGameMediator extends Observable implements MediatorI, Iterator<AbstractHand> {
	
	protected CardGame 		cardGame;
	protected Shoe 			shoe;

	public AbstractGameMediator(CardGame aCardGame, Shoe shoe) {
		this.cardGame = aCardGame;
		this.shoe = shoe;
	}
	
	//public abstract void initDeal(); // this should come from cardGame
	
	public abstract void runRound();
	
	public abstract boolean isGameOver();

	public abstract boolean isRoundOver();
	
}
