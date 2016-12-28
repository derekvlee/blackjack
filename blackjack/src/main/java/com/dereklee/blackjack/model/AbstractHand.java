package com.dereklee.blackjack.model;

import java.util.Observer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dereklee.blackjack.CardI;
import com.dereklee.blackjack.util.BjConstants;

public abstract class AbstractHand implements Observer {

	protected 	MediatorI mediator;
	protected 	int	  	  handNum;
	protected 	CardI	  card;
	protected	int		  cardsVal;
	protected 	Logger 	  logger = LogManager.getLogger();
	protected   int		  standNum;
	
	public AbstractHand(MediatorI mediator, int num) {
		this.mediator = mediator;
		this.handNum = num;
		this.standNum = BjConstants.PLAYER_STAND; // default
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
		if (cardsVal < standNum) {
			mediator.sendCallBack(CardOption.HIT, this);
		} else {
			mediator.sendCallBack(CardOption.STAND, this);
		}
	}

	@Override
	public String toString() {
		return "AbstractHand [handNum=" + handNum + ", card=" + card + ", cardsVal=" + cardsVal + "]";
	}

	
}
