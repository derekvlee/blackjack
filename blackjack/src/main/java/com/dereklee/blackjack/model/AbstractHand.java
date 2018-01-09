package com.dereklee.blackjack.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dereklee.blackjack.CardI;
import com.dereklee.blackjack.rulesengine.BJStrategy;
import com.dereklee.blackjack.rulesengine.BJStrategyRules;
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
	protected	BJStrategy strategy;
	protected	boolean		hasBust;
	
	public AbstractHand(MediatorI mediator, int num) {
		this.mediator = mediator;
		this.handNum = num;
		this.standNum = BjConstants.PLAYER_STAND; // default
		this.cards = new ArrayList<CardI>();
	}
	
	public abstract void hit(CardI card);
	
	public abstract void stand();
	
	public abstract void logDecision();
	
	public void setBust(boolean b) {
		this.hasBust = b;
	}
	
	/**
	 * Depending on the cards which the Hand contains; the available options may include: hit/stand/double/split
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
		//logger.debug(toString());
		strategy = BJStrategyRules.callRules(this);
		mediator.callBack(strategy);
		logDecision();
	}
	
	public boolean isBust() {
		return hasBust;
	}

	/**
	 * @return e.g. cards=[10,10]
	 */
	protected String getCardsStrVal() {
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
	
	public List<CardI> getCards() {
		return cards;
	}
	
	@Override
	public String toString() {
		return "AbstractHand [handNum=" + handNum + ", cards=[" + getCardsStrVal() + "], total=" + cardsVal  + "]";
	}
}
