package com.dereklee.blackjack.model;

import java.util.Observable;

import com.dereklee.blackjack.CardI;
import com.dereklee.blackjack.NumberCard;

public class PlayerHand extends AbstractHand {
	
	private CardI dealersUpCard;

	public PlayerHand(MediatorI mediator, int num) {
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

	// Observer - informed of changes
	public void update(Observable o, Object obj) {
		logger.debug( "[" + this.handNum + "]" + " Dealers up Card: " + obj.toString() );
		if(obj instanceof CardI) {
			dealersUpCard = ((CardI) obj);
		}
	}
	
	public CardI getDealersUpCard() {
		return dealersUpCard;
	}

}
