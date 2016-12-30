package com.dereklee.blackjack.cards.service;

import java.util.List;

import com.dereklee.blackjack.CardI;
import com.dereklee.blackjack.cards.creator.CardDeckFactory;
import com.dereklee.blackjack.cards.creator.StandardDeckFactory;
import com.dereklee.blackjack.cards.creator.StandardDeckShuffleFactory;
import com.dereklee.blackjack.cards.product.CardDeck;
import com.dereklee.blackjack.cards.product.CardDeckType;

public class CreateCardDeckMain {
	public static void main(String[] args) {
		CardDeckFactory standardDeckFactory = new StandardDeckFactory(new StandardDeckShuffleFactory());
		CardDeck cardDeck = standardDeckFactory.prepareCardDeck(CardDeckType.STANDARD_DECK);  
		List<CardI> deck = cardDeck.getDeck();
		for (CardI card : deck) {
			System.out.println(card);
		}
		
	}
}
