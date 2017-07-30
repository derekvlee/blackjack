package com.dereklee.blackjack.rulesengine;

import com.dereklee.blackjack.model.AbstractHand;
import com.dereklee.blackjack.model.PlayerHand;

/**
 * 
 * Contains card information relating to the Player Hand.
 * The PlayerHand is informed of the dealers up card via the Observer's update method.
 * We extract's the DealersUpCard from the Players hand.
 *  
 * @author Derek
 *
 */
public class PlayerHandInfo {
	
	private int dealersUpCardValue;
	private int playerCardsValue;
	
	
	public PlayerHandInfo(AbstractHand abstractHand) {
		if(abstractHand instanceof PlayerHand) {
			PlayerHand hand = ((PlayerHand) abstractHand);
			this.playerCardsValue = hand.getCardsValue();
			this.dealersUpCardValue = hand.getDealersUpCard().getValue();
		}
	}
//	// convenience constructor for unit tests 
//	public PlayerHandInfo(int playerCardsValue, int dealersUpCardValue) {
//		this.playerCardsValue = playerCardsValue;
//		this.dealersUpCardValue = dealersUpCardValue;
//	}

	public int getPlayerCardsValue() {
		return playerCardsValue;
	}

	public int getDealersUpCardValue() {
		return dealersUpCardValue;
	}
	
}
