package com.dereklee.blackjack;

public enum Suit {
	SPADES("spades")
	, HEARTS("hearts")
	, CLUBS("clubs")
	, DIAMONDS("diamonds")
	, NO_SUIT("no-suit")
	;
	
	private String abbrev;
	
	private Suit(String s) {
		this.abbrev = s;
	}
	
	public Suit get(String s) {
		for(Suit suit: Suit.values()) {
			if(suit.getAbbrev().equalsIgnoreCase(s)) {
				return suit;
			}
		}
		return null;
	}
	
	public String getAbbrev() {
		return this.abbrev;
	}
}

