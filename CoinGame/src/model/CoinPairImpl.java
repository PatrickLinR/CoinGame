package model;

import model.interfaces.Coin;
import model.interfaces.CoinPair;

public class CoinPairImpl implements CoinPair {

	private Coin coin1 = new CoinImpl(1);
	private Coin coin2 = new CoinImpl(2);
	
	public CoinPairImpl(){
		this.coin1 = new CoinImpl(1);
		this.coin2 = new CoinImpl(2);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coin1 == null) ? 0 : coin1.hashCode());
		result = prime * result + ((coin2 == null) ? 0 : coin2.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoinPair other = (CoinPair) obj;
		if (coin1 == null) {
			if (other.getCoin1() != null)
				return false;
		} else if (!coin1.equals(other.getCoin1()))
			return false;
		if (coin2 == null) {
			if (other.getCoin2() != null)
				return false;
		} else if (!coin2.equals(other.getCoin2()))
			return false;
		return true;
	}
	

	@Override
	public Coin getCoin1() {
		// TODO Auto-generated method stub
		return this.coin1;
	}

	@Override
	public Coin getCoin2() {
		// TODO Auto-generated method stub
		return this.coin2;
	}

	@Override
	public boolean equals(CoinPair coinPair) {
		// TODO Auto-generated method stub
		boolean equal = false;
		if (getCoin1().getFace() == coinPair.getCoin1().getFace() && getCoin2().getFace() == coinPair.getCoin2().getFace()) {
			equal = true;
		} else {
			equal = false;
		}
		return equal;
	}
	
	public String toString() {
		return String.format("%s, %s", getCoin1().toString(), getCoin2().toString());
	}
}
