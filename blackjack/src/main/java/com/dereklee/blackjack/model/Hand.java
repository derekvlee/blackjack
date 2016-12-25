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

}
