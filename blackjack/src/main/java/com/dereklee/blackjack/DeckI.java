package com.dereklee.blackjack;

import java.util.Collection;
import java.util.List;

public interface DeckI {

	public void init();
	
	//public void shuffle();
	
	public Collection<CardI> getDeck();
}
