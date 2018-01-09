package com.dereklee.blackjack.rulesengine;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dereklee.blackjack.CardI;
import com.dereklee.blackjack.model.PlayerHand;

/**
 * Unit Tests mocking with Mockito.
 * 
 * @author Derek
 *
 */
public class BJStrategyRulesTest {
	
	private static PlayerHand mockedPlayerHand;
	private static CardI mockedUpCardI;
	
	@BeforeClass
	public static void setUp() {
		// Create our objects to Mock
		mockedPlayerHand = mock(PlayerHand.class);
		mockedUpCardI = mock(CardI.class);
	}

	@Test
	public void testBJStrategyRules() {
		
		when(mockedUpCardI.getValue()).thenReturn(2);
		when(mockedPlayerHand.getCardsValue()).thenReturn(5);
		when(mockedPlayerHand.getDealersUpCard()).thenReturn(mockedUpCardI);
		assertEquals(BJStrategy.HIT, BJStrategyRules.callRules(mockedPlayerHand));
		
		when(mockedUpCardI.getValue()).thenReturn(9);
		when(mockedPlayerHand.getCardsValue()).thenReturn(15);
		when(mockedPlayerHand.getDealersUpCard()).thenReturn(mockedUpCardI);
		assertEquals(BJStrategy.HIT, BJStrategyRules.callRules(mockedPlayerHand));
		
		when(mockedUpCardI.getValue()).thenReturn(3);
		when(mockedPlayerHand.getCardsValue()).thenReturn(15);
		when(mockedPlayerHand.getDealersUpCard()).thenReturn(mockedUpCardI);
		assertEquals(BJStrategy.STAND, BJStrategyRules.callRules(mockedPlayerHand));
		
		when(mockedUpCardI.getValue()).thenReturn(10);
		when(mockedPlayerHand.getCardsValue()).thenReturn(18);
		when(mockedPlayerHand.getDealersUpCard()).thenReturn(mockedUpCardI);
		assertEquals(BJStrategy.STAND, BJStrategyRules.callRules(mockedPlayerHand));
		
		when(mockedUpCardI.getValue()).thenReturn(7);
		when(mockedPlayerHand.getCardsValue()).thenReturn(11);
		when(mockedPlayerHand.getDealersUpCard()).thenReturn(mockedUpCardI);
		assertEquals(BJStrategy.DOUBLE, BJStrategyRules.callRules(mockedPlayerHand));
	}

	

}
