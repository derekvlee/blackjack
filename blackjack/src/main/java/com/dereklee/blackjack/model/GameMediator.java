package com.dereklee.blackjack.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dereklee.blackjack.CardI;
import com.dereklee.blackjack.CuttingCard;
import com.dereklee.blackjack.Shoe;
import com.dereklee.blackjack.Suit;
import com.dereklee.blackjack.cardgame.product.CardGame;
import com.dereklee.blackjack.util.BjConstants;

/**
 * The Mediator notifies the Observers of the dealers up-card
 * 
 * @author Derek
 *
 */
public class GameMediator extends AbstractGameMediator {
	
	private Logger logger = LogManager.getLogger();
	private List<AbstractHand> 		hands;
	private Iterator<AbstractHand>	handIt;
	private AbstractHand 			hand; 	// current hand	
	private boolean					bCutCard;
	private boolean 				bRoundOver;	
	
	/**
	 * Construct a GameMediator object
	 * @param cardGame a card game
	 * @param shoe a dealers shoe which contains the playing card deck(s).
	 */
	public GameMediator(CardGame cardGame, Shoe shoe) {
		super(cardGame, shoe);
		hands = new ArrayList<AbstractHand>();
	}

	/**
	 * Add each hand to a list of hands.
	 * Also add each hand (observer) to the Observable collection excluding the Dealer's Hand.
	 * @param hand
	 */
	public void addHand(AbstractHand hand) {
		hands.add(hand);
		if(!(hand instanceof DealerHand)) {
			this.addObserver(hand);
		}
	}
	/**
	 * Returns the total card value for a given hand. 
	 * @param handNum
	 * @return
	 */
	public int getHandCardValue(int handNum) {
		return hands.get(handNum).getCardsValue();
	}
	
	// Responsible for invoking the initial two card deal to each Hand, including the dealer.
	public void runRound() {
		if(isGameOver()) {
			logger.debug("unable to run round, cutting card dispensed in previous round or no more cards in shoe");
			return;
		}
		// for each hand (and the dealer)
		// deal two initial cards
		// the hands (observers) should be notified about the dealers up-card.

		// TODO add dealer
		// handle exceptions like no more cards and cutting card 
		int initialDealNum = cardGame.getInitialCardDealNumber();
		logger.debug("Dealing initial "+initialDealNum+" cards to " + hands.size() + " hands for a total of: " + (initialDealNum * hands.size()) + " cards");

		inititalDeal(initialDealNum);
		runRound_();
	}
	
	private void inititalDeal(int initialCardDealNumber) {
		for(int i=0; i<initialCardDealNumber; i++) {
			initDeal();
		}
	}

	// Deal a single card to each Hand
	public void initDeal() {
		handIt = hands.iterator(); 
		while(hasNext() && !bRoundOver) {
			AbstractHand aHand = next();
			dealCard(aHand);
		}
	}

	/**
	 * Starts offering to deal to the first hand, invoking a 'makeDecision()' on the hand. 
	 * Waits (synchronous) for a callback on the decision. 
	 * Continues offering to deal to all playing Hands for this round.
	 * 
	 * PreCondition: 
	 * 	The Game has dealt the initial two cards to all playing 'Hands' including the Dealer.
	 *  The dealers up-card has been notified to all playing Hands.
	 * 
	 * PostCondition:
	 *  The dealer needs to turn over her down-card and decide to hit/stand. (Note:
	 *  The decision to hit/stand is really outside the control of a dealer as she must play by the house rules i.e. Stand >= 17)
	 *  
	 */
	public void runRound_() {
		handIt = hands.iterator();
		if (hasNext()) { // hasNext Hand
			hand = next(); // get next Hand
			while(hand != null && !bRoundOver) {			
				hand.makeDecision();
			}
			// round over
			// TODO pay winning bets, take losing bets			
			printEndOfRoundSummary();
			reset();
		}
	}
	
	private void printEndOfRoundSummary() {
		logger.debug("--- End of Round Summary ---");
		logger.debug("number of hands at end of round: " + hands.size());
		for (AbstractHand h : hands) {
			logger.debug(h.toString());
		}
	}

	/**
	 * End of Round: Tidy up loose ends.
	 */
	private void reset() {
		hands.clear();
		this.deleteObservers();
	}
	
	/**
	 * Deal a card to the current hand
	 * @param hand2 
	 */
	public void dealCard(AbstractHand aHand) {
		if(shoe.hasNext()) {
			CardI card = shoe.next();
			if(card.equals(new CuttingCard(0, Suit.NO_SUIT, "cutting-card"))){
				logger.debug("card is cutting card");
				// cutting card indicates it's the last round
				bCutCard = true;
				// it's not necessary to give the cutting card to the hand
				// just draw another card from the shoe and give to the hand
				// we assume there is another card in the shoe, given the (safe) position of the cutting card
				 card = shoe.next();				
			}
			aHand.hit(card);
			handleBust(aHand);

		} else {			
			// no more cards in the deck
			bRoundOver = true;
			logger.error("no more cards in shoe");
		}
	}
	
	private void handleBust(AbstractHand aHand) {
		if(aHand.getCardsValue() > BjConstants.MAX_CARDS_VALUE) {
			// TODO
			logger.error("TODO: hand is bust. " + aHand.toString());
			getNextHand();
		}
	}
	
	private void getNextHand() {
		hand = hasNext() ? next() : null;
	}

	/**
	 * CallBack method, invoked by each Hand when it's asked to make a decision on how to play/proceed.
	 * Also used by Dealer to signify it's up-card. 
	 */
	public void sendCallBack(CardOption option, AbstractHand aHand) {
		//this.currHand = aHand; // TODO is the given Hand reference needed?
		switch(option) {
		case HIT:
			dealCard(hand);
			break;
		case STAND:
			getNextHand();
			break;
		default:
			
		}
	}
	
	public void sendCallBackFromDealer(CardOption option, AbstractHand aHand) {
		switch(option) {
		case DEALERS_UPCARD:
			if(aHand instanceof DealerHand) { // only for initial deal
				this.setChanged();
				this.notifyObservers(((DealerHand) aHand).getUpCard());
				this.clearChanged();
			}
			break;
		default:
			
		}
	}
	
	@Override
	public boolean isGameOver() {
		return bCutCard;
	}
	
	@Override
	public boolean isRoundOver() {
		return bRoundOver; 
	}
	
	/**
	 * Has Next Hand
	 */
	public boolean hasNext() {
		return handIt.hasNext();
	}
	
	/**
	 * Next Hand
	 */
	public AbstractHand next() {
		return handIt.next();
	}


	///////////////////////////////////////////////////////
	//
	
	
}
