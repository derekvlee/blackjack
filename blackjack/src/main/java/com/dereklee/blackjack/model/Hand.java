package com.dereklee.blackjack.model;

import java.util.Observable;

import com.dereklee.blackjack.CardI;

public class Hand extends AbstractHand {

	public Hand(MediatorI mediator, int num) {
		super(mediator, num);
	}

	@Override
	public void hit(CardI card) {
		logger.debug("[" + this.handNum + "]" + " hit with: " + card.toString());
		this.card = card;
		cards.add(card);
		this.cardsVal += card.getValue();
	}

	@Override
	public void stand() {
	}

	// Observer 
	public void update(Observable o, Object arg) {
		logger.debug( "[" + this.handNum + "]" + " Dealers up Card: " + arg.toString() );
	}

}
