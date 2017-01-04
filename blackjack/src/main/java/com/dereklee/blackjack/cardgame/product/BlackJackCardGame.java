package com.dereklee.blackjack.cardgame.product;

public class BlackJackCardGame extends CardGame {

	@Override
	public int getInitialCardDealNumber() {
		System.out.println("BlackJackCardGame deal's an initial two cards to each Hand");
		return 2;
	}
	

}
