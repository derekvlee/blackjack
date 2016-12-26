package com.dereklee.blackjack.model;

import java.util.List;

public interface MediatorI {
	
	public void dealCard(AbstractHand hand);
	
	public void sendCallBack(CardOption option, AbstractHand hand);
	
}
