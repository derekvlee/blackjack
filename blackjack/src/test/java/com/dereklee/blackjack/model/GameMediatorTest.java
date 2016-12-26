package com.dereklee.blackjack.model;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class GameMediatorTest {

	private static Logger logger = LogManager.getLogger();
	
	@Test
	public void testGameMediator() {		
		GameMediator gm = new GameMediator(1);
		runRound(gm,5);
		runRound(gm,5);
		runRound(gm,5);
		if (gm.isGameOver()) {
			logger.debug("Game Over");
		}
		runRound(gm,5);
		logger.debug("Game Client ends..");
	}
	
	void runRound(GameMediator gm, int numHands) {
		for (int i=1; i<numHands; i++) {
			gm.addHand(new Hand(gm,i));
		}
		gm.runRound();
		logger.debug("Round ends..");
	}
	
	@Test
	public void testGameMediator_Test1() {
		boolean isTest = true;
		int countRounds = getNumRounds(isTest);
		assertEquals(1, countRounds);
	}
	
	//@Test This test won't always be consistent as a full deck is shuffled for each run
	public void testGameMediator_Test2() {
		boolean isTest = false;
		int countRounds = getNumRounds(isTest);
		assertEquals(1, countRounds);
	}	
	
	private int getNumRounds(boolean isTest) {
		GameMediator gm = new GameMediator(1, isTest);
		int counter = 0;
		while(!gm.isGameOver()) {
			runRound(gm,4);
			counter++;			
		}
		return counter;
	}
	
}
