package com.dereklee.blackjack.model;

import java.util.Observable;

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
		logger.debug("[" + this.handNum + "]" + " hit with: " + card.toString());
		this.card = card;		
		if (upCard == null && holeCard == null && cardsVal == 0) {
			// implies initial deal
			upCard = card;
			// let the Mediator notify the observers of the up-card
			notifyMediator();
		}
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

	public void update(Observable o, Object arg) {
		// n/a for the dealer
		logger.error("ERROR: DealerHand SHOULD NOT BE INVOKED for update on " + arg.toString());
	}
	
	private void notifyMediator() {
		mediator.sendCallBack(CardOption.DEALERS_UPCARD, this);
	}

}
