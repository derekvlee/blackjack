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
import com.dereklee.blackjack.rulesengine.BJStrategy;
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
	private	int						dealerTotal;
	
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
			manageRoundEnd();
		}
	}
	
	/**
	 * Pay winning bets, take losing bets	
	 */
	private void manageRoundEnd() {
		printEndOfRoundSummary();
		logger.debug("--- manageRoundEnd ---");
		boolean dealerIsBust = false;
		if (dealerTotal > BjConstants.MAX_CARDS_VALUE) {
			dealerIsBust = true;
		}
		
		for (AbstractHand h : hands) {
			int playerTotal = -1;
			if(h instanceof PlayerHand) {
				playerTotal = h.getCardsValue();
				if (h.hasBust) {
					// dealer wins the bet from the PlayerHand
					logger.debug("PlayerHand bust: dealer wins the bet from the PlayerHand");
					// and the PlayerHand is updated to be Lost
				} else if (!dealerIsBust && dealerTotal < playerTotal){
					// the dealer pays the bet at the PlayerHand
					logger.debug("PlayerHand won: dealer pays the bet at the PlayerHand");
				} else if (!dealerIsBust && dealerTotal == playerTotal){ 
					// the cardTotals are equal so the bet is pushed
					logger.debug("cardTotals are equal so the bet is pushed");
				} else if (dealerIsBust) {
					// we know the playerHand is not bust, so we just pay the PlayerHand
					logger.debug("DealerBust, PlayerHand won: dealer pays the bet at the PlayerHand");
				}
				
				// TODO any other condition?
				
			}
		}
		
		// other
		
		reset();
		
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
			aHand.setBust(true);
			// TODO should the hand be removed from the list of hands?
			// If so, we would need to have a special case for the dealer
			logger.info("hand is bust. " + aHand.toString());
			getNextHand();
		}
	}
	
	private void getNextHand() {
		hand = hasNext() ? next() : null;
	}
	
	/**
	 * CallBack method, invoked by each Hand when it's asked to make a decision on how to play/proceed.
	 */
	public void callBack(BJStrategy strategy) {
		switch(strategy) {
		case STAND:
			getNextHand();
			break;
		case HIT:
			dealCard(hand);
			break;			
		case DOUBLE:
			logger.debug("TODO implement DOUBLE");
			dealCard(hand); // temp workaround
			break;
		case SPLIT:
			logger.debug("TODO implement SPLIT");
			dealCard(hand); // temp workaround
			break;			
		default:
			throw new RuntimeException("Unexpected callback");
		}
	}
	
	public void notifyPlayers(CardI upCard) {
		this.setChanged();
		this.notifyObservers(upCard);
		this.clearChanged();
	}
		
	public void setDealersTotal(int cardTotal) {
		this.dealerTotal = cardTotal;
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
