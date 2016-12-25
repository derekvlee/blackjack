package com.dereklee.blackjack.model;

public interface MediatorI {
	
	public void dealCard(AbstractHand hand);
	
	public void sendCallBack(Option option, AbstractHand hand);
}
