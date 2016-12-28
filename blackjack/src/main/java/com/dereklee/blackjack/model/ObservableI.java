package com.dereklee.blackjack.model;

// not used- instead used java.util.Observer
public interface ObservableI {

	public void notifyObservers();
	
	public void addObserver(ObserverI obs);
}
