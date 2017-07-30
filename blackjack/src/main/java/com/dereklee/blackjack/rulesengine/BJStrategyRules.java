package com.dereklee.blackjack.rulesengine;

/**
 * The idea for creating this rules engine using Enums was adapted from this blog post:
 * http://danveloper.github.io/developing-a-rules-engine-with-java-enums.html
 * 
 * @author Derek
 *
 */
public enum BJStrategyRules implements BJStrategyRulesI {
	
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
	;
	
	private int cardVal;

	private BJStrategyRules(int cardValueRule) {
		this.cardVal = cardValueRule;
	}


	public static BJStrategy callRules(PlayerHandInfo info) {
		int playerCardsValue = info.getPlayerCardsValue();
		for(BJStrategyRules rule : BJStrategyRules.values()) {
			if(rule.cardVal == playerCardsValue) {
				return rule.apply(info.getDealersUpCardValue());
			}
		}
		// no rule matched the playerCardsValue
		return null; // TODO
	}

	

	
}
