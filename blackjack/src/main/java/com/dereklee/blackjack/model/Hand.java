package com.dereklee.blackjack.model;

import com.dereklee.blackjack.CardI;

public class Hand extends AbstractHand {

	public Hand(MediatorI mediator, int num) {
		super(mediator, num);
	}

	@Override
	public void hit(CardI card) {
		this.card = card;
		this.cardsVal += card.getValue();
	}

	@Override
	public void stand() {
	}

	/**
	 * The dealer (Observable) invokes this method, to notify it's Observer's (Hands). 
	 */
	public void update(CardI upCard) {
		logger.debug( "[" + this.handNum + "]" + " Dealers up Card: " + upCard);
	}

}
