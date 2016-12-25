package com.dereklee.blackjack.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dereklee.blackjack.CardI;
import com.dereklee.blackjack.CuttingCard;
import com.dereklee.blackjack.Shoe;

public class GameMediator implements MediatorI, Iterator<AbstractHand> {
	
	private List<AbstractHand> 		hands;
	private Iterator<AbstractHand>	handIt;
	private AbstractHand 			hand; 	// current hand
	private	Shoe 					shoe;
	private boolean					hasCutCard;
	private Logger logger = LogManager.getLogger();
	private boolean 				bRoundOver;
	
	public GameMediator(int numDecks) {
		logger.debug("GameMediator starting");	
		hands = new ArrayList<AbstractHand>();
		shoe = new Shoe(numDecks);
		// test for git
	}
	
	public void addHand(AbstractHand hand) {
		hands.add(hand);
	}	

	public void runRound() {
		// TODO
		// for each hand (and the dealer)
		// deal two initial cards
		// the hands (observers) should be notified about the dealers up-card.
		
//		for(AbstractHand h : hands) {
//			dealCard(h);
//		}
		initDeal();
		initDeal();
		runRound_();
	}
	
	// deal 1 card to each Hand (TODO incl., dealer)
	private void initDeal() {
		handIt = hands.iterator(); 
		while(hasNext() && !bRoundOver) {
			dealCard(next());
		}
		logger.debug("finished dealing one card to each hand");
		
	}

	/**
	 * PreCondition: 
	 * 	The Game has dealt the initial two cards to all playing 'Hands' including the Dealers.
	 *  The dealers up-card has been notified to all playing Hands.
	 * 
	 * runRound(): starts the dealing to the first hand, this class then waits for a callback on the decision. 
	 * This runRound method continues dealing to all playing Hands for this round.
	 * 
	 * PostCondition:
	 *  The dealer needs to turn over her down-card and decide to hit/stand. (Note:
	 *  The decision to hit/stand is really outside the control of a dealer as she must play by the house rules i.e. Stand >= 17)
	 *  
	 */
	public void runRound_() {
		handIt = hands.iterator();
		if (hasNext()) {
			hand = next();
			while(hand != null && !bRoundOver) {			
				hand.makeDecision();
			}
			// round over
			reset();
		}
	}
	
	private void reset() {
		hands.clear();
	}
	
	/**
	 * Deal a card to the current hand
	 * @param hand2 
	 */
	public void dealCard(AbstractHand aHand) {
		if(shoe.hasNext()) {
			CardI card = shoe.next();
			if(card.equals(new CuttingCard(0, "no-suit", "cutting-card"))){
				logger.debug("card is cutting card");
				// this indicates it's the last round
				hasCutCard = true;
				
			}
			aHand.hit(card);
		} else {			
			// no more cards in the deck
			// TODO
			// no more cards in the shoe, mmm, what happens to current round?
			// and how do we exit?
			bRoundOver = true;
			logger.error("no more cards");
			//System.exit(-1);		
		}
	}

	/**
	 * CallBack method, invoked by each Hand. 
	 */
	public void sendCallBack(Option option, AbstractHand aHand) {
		//this.currHand = aHand; // TODO is the given Hand reference needed?
		switch(option) {
		case HIT:
			dealCard(hand);
			break;
		case STAND:
			// get the next hand.
			hand = hasNext() ? next() : null;
			break;
		default:
			
		}
	}
	
	public boolean isGameOver() {
		return hasCutCard;
	}

	public boolean isRoundOver() {
		return bRoundOver; 
	}

	public boolean hasNext() {
		return handIt.hasNext();
	}

	public AbstractHand next() {
		return handIt.next();
	}


}
