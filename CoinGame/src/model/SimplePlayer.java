package model;

import model.enumeration.BetType;
import model.interfaces.CoinPair;
import model.interfaces.Player;

public class SimplePlayer implements Player {

	private String playerId;
	private String playerName;
	private int initialPoints;
	
	private int bet;
	private BetType bettype;
	private CoinPair result;

	public SimplePlayer(String playerId, String playerName, int initialPoints) {
		// TODO Auto-generated constructor stub
		this.playerId = playerId;
		this.playerName = playerName;
		this.initialPoints = initialPoints;
	}

	@Override
	public String getPlayerName() {
		// TODO Auto-generated method stub
		return this.playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		// TODO Auto-generated method stub
		this.playerName = playerName;

	}

	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return this.initialPoints;
	}

	@Override
	public void setPoints(int points) {
		// TODO Auto-generated method stub
		this.initialPoints = points;
	}

	@Override
	public String getPlayerId() {
		// TODO Auto-generated method stub
		return this.playerId;
	}

	@Override
	public boolean setBet(int bet) {
		// TODO Auto-generated method stub
		if(bet > 0 && this.initialPoints >= bet) {
			this.bet = bet;
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public int getBet() {
		// TODO Auto-generated method stub
		return this.bet;
	}

	@Override
	public void setBetType(BetType betType) {
		// TODO Auto-generated method stub
		this.bettype = betType;
	}

	@Override
	public BetType getBetType() {
		// TODO Auto-generated method stub
		return this.bettype;
	}

	@Override
	public void resetBet() {
		// TODO Auto-generated method stub
		this.bet = 0;

	}

	@Override
	public CoinPair getResult() {
		// TODO Auto-generated method stub
		return this.result;
	}

	@Override
	public void setResult(CoinPair coinPair) {
		// TODO Auto-generated method stub
		result = coinPair;
	}
	
	public String toString() {
		return String.format(
				"Player: id=%s, name=%s, bet=%d, betType=%s, points=%d", 
				getPlayerId(), getPlayerName(), getBet(), getBetType(), getPoints());
	}

}
