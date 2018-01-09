package com.dereklee.blackjack.model;

import com.dereklee.blackjack.CardI;
import com.dereklee.blackjack.rulesengine.BJStrategy;

public interface MediatorI {
	
	public void addHand(AbstractHand hand);
	
	public void dealCard(AbstractHand hand);
	
	public void callBack(BJStrategy strategy);

	public void notifyPlayers(CardI dealerUpCard);
	
	public void setDealersTotal(int dealersTotal);
	
	
}
