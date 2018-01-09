package com.dereklee.blackjack.rulesengine;

import com.dereklee.blackjack.model.AbstractHand;
import com.dereklee.blackjack.model.DealerHand;
import com.dereklee.blackjack.model.PlayerHand;

/**
 * The idea for creating this rules engine using Enums, was adapted from this blog post:
 * http://danveloper.github.io/developing-a-rules-engine-with-java-enums.html
 * 
 * 
 * This Rules Engine using standard BlacjJack Strategy rules. 
 * The player total card value is compared to the Dealers up card and a decision is made to
 * either HIT,STAND,DOUBLE or SPLIT.
 * 
 * SPLIT : TODO
 *  
 * 
 * @author Derek
 *
 */
public enum BJStrategyRules implements BJStrategyRulesI {
	
	TWENTY_ONE_RULE(21) {
		public BJStrategy apply(int upCardVal) {
			return BJStrategy.STAND;
		}
	}
	,
	TWENTY_RULE(20) {
		public BJStrategy apply(int upCardVal) {
			return BJStrategy.STAND;
		}
	}
	,
	NINETEEN_RULE(19) {
		public BJStrategy apply(int upCardVal) {
			return BJStrategy.STAND;
		}
	}
	,
	EIGHTEEN_RULE(18) {
		public BJStrategy apply(int upCardVal) {
			return BJStrategy.STAND;
		}
	}
	,
	SEVENTEEN_RULE(17) {
		public BJStrategy apply(int upCardVal) {
			return BJStrategy.STAND;
		}
	}
	,
	SIXTEEN_RULE(16) {
		public BJStrategy apply(int upCardVal) {
			return (upCardVal >= 2 && upCardVal <= 6) ? BJStrategy.STAND : BJStrategy.HIT;
		}
	}
	,
	FIFTEEN_RULE(15) {
		public BJStrategy apply(int upCardVal) {
			return (upCardVal >= 2 && upCardVal <= 6) ? BJStrategy.STAND : BJStrategy.HIT;
		}
	}
	,
	FOURTEEN_RULE(14) {
		public BJStrategy apply(int upCardVal) {
			return (upCardVal >= 2 && upCardVal <= 6) ? BJStrategy.STAND : BJStrategy.HIT;
		}
	}
	,
	THIRTEEN_RULE(13) {
		public BJStrategy apply(int upCardVal) {
			return (upCardVal >= 2 && upCardVal <= 6) ? BJStrategy.STAND : BJStrategy.HIT;
		}
	}
	,
	TWELVE_RULE(12) {
		public BJStrategy apply(int upCardVal) {
			return (upCardVal == 4 || upCardVal == 5 || upCardVal == 6) ? BJStrategy.STAND : BJStrategy.HIT;
		}
	}
	,ELEVEN_RULE(11) {
		public BJStrategy apply(int upCardVal) {
			return (upCardVal == 1 ) ? BJStrategy.HIT : BJStrategy.DOUBLE;
		}
	}
	,TEN_RULE(10) {
		public BJStrategy apply(int upCardVal) {
			return (upCardVal == 10 || upCardVal == 1 ) ? BJStrategy.HIT : BJStrategy.DOUBLE;
		}
	}
	, NINE_RULE(9) {
		public BJStrategy apply(int upCardVal) {
			return (upCardVal >= 3 && upCardVal <= 6 ) ? BJStrategy.DOUBLE : BJStrategy.HIT;
		}
		
	}
	, EIGHT_RULE(8) {public BJStrategy apply(int upCardVal) {return BJStrategy.HIT;}}
	, SEVEN_RULE(7) {public BJStrategy apply(int upCardVal) {return BJStrategy.HIT;}}	
	, SIX_RULE(6) {public BJStrategy apply(int upCardVal) {return BJStrategy.HIT;}}	
	, FIVE_RULE(5) {public BJStrategy apply(int upCardVal) {return BJStrategy.HIT;}}	
	;
	
	private int cardVal;

	private BJStrategyRules(int cardValueRule) {
		this.cardVal = cardValueRule;
	}
	
	public static BJStrategy callRules(AbstractHand hand) {
		if (hand instanceof PlayerHand) {
			return callRules((PlayerHand) hand);
		} else if (hand instanceof DealerHand) {
			return callRules((DealerHand) hand);
		}
		return null;
		
	}

	
	public static BJStrategy callRules(PlayerHand player) {
		int playerCardsValue = player.getCardsValue();
		for(BJStrategyRules rule : BJStrategyRules.values()) {
			if(rule.cardVal == playerCardsValue) {
				return rule.apply(player.getDealersUpCard().getValue());
			}
		}
		return null; // no rule matched the playerCardsValue
	}	

	public static BJStrategy callRules(DealerHand dealer) {
		int dealersCardsValue = dealer.getCardsValue();
		// dealer rules depend on the House
		// but generally the dealer will continue to draw cards until a hard 17 (or over) is reached.
		return ( dealersCardsValue < 17 ) ? BJStrategy.HIT :  BJStrategy.STAND;
	}
	
}
