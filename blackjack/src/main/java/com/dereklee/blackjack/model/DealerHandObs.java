package com.dereklee.blackjack.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.dereklee.blackjack.CardI;
import com.dereklee.blackjack.util.BjConstants;

public class DealerHandObs extends AbstractHand implements ObservableI {

	private CardI upCard;    // dealer's first card is face up.
	private CardI holeCard;  // dealer's second card is face down
	private List<ObserverI> observers;

	public DealerHandObs(MediatorI mediator, int num) {
		super(mediator, num);
		this.standNum = BjConstants.DEALER_STAND;
		this.observers = new ArrayList<ObserverI>();
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
		// TODO ?
	}
	
	public CardI getUpCard() {
		return upCard;
	}

	/////////////////////////////////////////////////	
	// ObservableI, ObserverI 
	
	public void addObserver(ObserverI obs) {
		observers.add(obs);
	}
	
	public void notifyObservers() {
		for (ObserverI obs : observers) {
			obs.update(upCard);
		}
	}

	public void update(CardI upCard) {
		// n/a for the dealer
		logger.error("ERROR: SHOULD NOT BE INVOKED");
	}

	public void update(Observable o, Object arg) {
	}

}
