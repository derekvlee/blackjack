package com.dereklee.blackjack.model;

import java.util.Observable;

import com.dereklee.blackjack.CardI;
import com.dereklee.blackjack.NumberCard;
import com.dereklee.blackjack.rulesengine.BJStrategy;
import com.dereklee.blackjack.util.BjConstants;

public class PlayerHand extends AbstractHand {
	
	private CardI dealersUpCard;

	public PlayerHand(MediatorI mediator, int num) {
		super(mediator, num);
	}

	@Override
	public void hit(CardI card) {
		//logger.debug("PlayerHand [" + this.handNum + "]" + " dealt: " + card.toString());
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
	
	@Override
	public void makeDecision() {
		super.makeDecision();
	}
	
	public CardI getDealersUpCard() {
		return dealersUpCard;
	}

	@Override
	public String toString() {
		return "PlayerHand [handNum=" + handNum + ", cards=[" + getCardsStrVal() + "], total=" + cardsVal  + ", upCard["+getDealersUpCard().getValue()+"]]";
	}

	@Override
	public void logDecision() {
		if (cardsVal > BjConstants.MAX_CARDS_VALUE) return;
		if(!(BJStrategy.STAND.equals(strategy)))
			logger.debug("PlayerHand [handNum=" + handNum + ", strategy("+strategy+") cards=[" + getCardsStrVal() + "], upCard["+getDealersUpCard().getValue()+"], total=" + cardsVal  + "]");
	}
	
}
