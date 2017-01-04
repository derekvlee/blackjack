package com.dereklee.blackjack.model;

public interface MediatorI {
	
	public void addHand(AbstractHand hand);
	
	public void dealCard(AbstractHand hand);
	
	public void sendCallBack(CardOption option, AbstractHand hand);
	
}
