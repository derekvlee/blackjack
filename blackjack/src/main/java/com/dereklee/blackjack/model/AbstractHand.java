package com.dereklee.blackjack.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dereklee.blackjack.CardI;
import com.dereklee.blackjack.rulesengine.BJStrategy;
import com.dereklee.blackjack.rulesengine.BJStrategyRules;
import com.dereklee.blackjack.rulesengine.PlayerHandInfo;
import com.dereklee.blackjack.util.BjConstants;

/**
 * The AbstractHand represents a player/dealers Hand of cards. 
 * It implements the Observer interface so it can be notified of changes.
 *  
 * @author Derek
 *
 */
public abstract class AbstractHand implements Observer {

	protected 	MediatorI mediator;
	protected 	int	  	  handNum;
	protected 	CardI	  card;
	protected	int		  cardsVal;
	protected 	Logger 	  logger = LogManager.getLogger();
	protected   int		  standNum;
	protected	List<CardI>	 cards;
	
	public AbstractHand(MediatorI mediator, int num) {
		this.mediator = mediator;
		this.handNum = num;
		this.standNum = BjConstants.PLAYER_STAND; // default
		this.cards = new ArrayList<CardI>();
	}
	
	public abstract void hit(CardI card);
	
	public abstract void stand();
	
	/**
	 * Depending on the cards which the Hand has, available options may include: hit/stand/double/split
	 * The Hand decides how to play based on the following: 
	 * a) total card values
	 * b) strategy
	 * c) dealers up card
	 * d) house rules
	 * e) given cards 
	 * 	e.g. a pair could be split
	 *  e.g. double down on 9,10,11
	 */
	public void makeDecision() {
		// TODO makeDecision based on strategy
		// TODO hand need notification on dealers up card
		logger.debug(toString());
		
		// establish the (player) hands knowledge of the dealers up card
		PlayerHandInfo info = new PlayerHandInfo(this);
		BJStrategy strategy = BJStrategyRules.callRules(info);
		logger.debug("strategy("+strategy+") for handNum=" + handNum + " and totalCardValue=("+info.getPlayerCardsValue()+") with DealersUpCard("+info.getDealersUpCardValue()+")");
		
		if (cardsVal < standNum) {
			mediator.sendCallBack(CardOption.HIT, this);
		} else {
			mediator.sendCallBack(CardOption.STAND, this);
		}
	}

	@Override
	public String toString() {
		//return "AbstractHand [handNum=" + handNum + ", card=" + card +  ", cards= " + getAllCards() + ", cardsVal=" + cardsVal  + "]";
		return "AbstractHand [handNum=" + handNum + ", cards=[" + getAllCards() + "], total=" + cardsVal  + "]";
	}

	protected String getAllCards() {
		String s = "";
		for(CardI c : cards) {
			s += c.getValue() + ",";
		}
		// remove trailing comma
		s = s.substring(0,s.length()-1);
		return s;
	}

	public int getCardsValue() {
		return cardsVal;
	}
	
	public int getHandNum() {
		return handNum;
	}
}
