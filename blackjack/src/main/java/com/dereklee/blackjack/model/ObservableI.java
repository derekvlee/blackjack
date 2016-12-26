package com.dereklee.blackjack.model;

public interface ObservableI {

	public void notifyObservers();
	
	public void addObserver(ObserverI obs);
}
