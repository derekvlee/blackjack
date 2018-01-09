package com.dereklee.blackjack.model;

import java.util.Observable;

import com.dereklee.blackjack.CardI;
import com.dereklee.blackjack.CuttingCard;
import com.dereklee.blackjack.Suit;
import com.dereklee.blackjack.rulesengine.BJStrategy;
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
		//logger.debug("DealerHand [" + this.handNum + "]" + " dealt: " + card.toString());
		if(card.equals(new CuttingCard(0, Suit.NO_SUIT, "cutting-card"))) {
			return;
		}
		this.card = card;		
		if (upCard == null && holeCard == null && cardsVal == 0) {
			// implies initial deal
			upCard = card;
			// the mediator notifies the observers of the up-card
			mediator.notifyPlayers(upCard);
		}
		cards.add(card);
		this.cardsVal += card.getValue();
	}

	@Override
	public String toString() {
		return "DealerHand [handNum=" + handNum + ", cards=[" + getCardsStrVal() + "], total=" + cardsVal  + "]";
	}

	@Override
	public void stand() {
		// TODO
	}
	
	@Override
	public void makeDecision() {
		super.makeDecision();
		// at this point the round is over and we can begin to assert winners/losers
		mediator.setDealersTotal(cardsVal);
	}
	
	public CardI getUpCard() {
		return upCard;
	}

	public void update(Observable o, Object arg) {
		// n/a for the dealer
		logger.error("ERROR: DealerHand SHOULD NOT BE INVOKED for update on " + arg.toString());
	}

	
	public void logDecision() {
		if (cardsVal > BjConstants.MAX_CARDS_VALUE) return;
		if(!(BJStrategy.STAND.equals(strategy))) {
			logger.debug("*DealerHand [handNum=" + handNum + ", strategy("+strategy+") cards=[" + getCardsStrVal() + "], total=" + cardsVal  + "]");			
		}
	}

}
