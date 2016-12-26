package com.dereklee.blackjack;

public abstract class Card implements CardI {

	private int value;
	private Suit   suit;
	private String name;	
	
	public Card(int val, Suit suit, String name) {
		this.value = val;
		this.suit = suit;
		this.name = name;
	}	

	public int getValue() {
		return value;
	}

	public Suit getSuit() {
		return suit;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		result = prime * result + value;
		return result;
	}

	@Override
	public String toString() {
		return "Card [ " +name + "  " + suit + "  " + value + " ]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (suit != other.suit)
			return false;
		if (value != other.value)
			return false;
		return true;
	}



}
