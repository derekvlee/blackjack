package com.dereklee.blackjack.model;

import com.dereklee.blackjack.CardI;
import com.dereklee.blackjack.util.BjConstants;

public class DealerHand extends AbstractHand {
	
	private CardI upCard;    // dealer's first card is face up.
	private CardI holeCard;  // dealer's second card is face down

	public DealerHand(MediatorI mediator, int num) {
		super(mediator, num);
		this.standNum = BjConstants.DEALER_STAND;
	}

	@Override
	public void hit(CardI card) {
		this.card = card;
		this.cardsVal += card.getValue();
	}

	@Override
	public String toString() {
		return "DealerHand [upCard=" + upCard + ", handNum=" + handNum + ", card=" + card + ", cardsVal=" + cardsVal + "]";
	}

	@Override
	public void stand() {
		// TODO
	}
	
	public CardI getUpCard() {
		return upCard;
	}

	public void update(CardI upCard) {
		// n/a for the dealer
		logger.error("ERROR: SHOULD NOT BE INVOKED");
	}

}
