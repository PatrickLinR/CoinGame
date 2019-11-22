package model;

import java.util.Random;

import model.enumeration.CoinFace;
import model.interfaces.Coin;

public class CoinImpl implements Coin {

	private int number;
	private CoinFace face;
	
	public CoinImpl(int number) {
		this.number = number;
		int randomFace = new Random().nextInt(CoinFace.values().length);
		this.face = CoinFace.values()[randomFace];
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((face == null) ? 0 : face.hashCode());
		result = prime * result + number;
		return result;
	}	

	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return this.number;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coin other = (Coin) obj;
		if (face != other.getFace())
			return false;
		return true;
	}

	@Override
	public CoinFace getFace() {
		// TODO Auto-generated method stub
		return this.face;
	}

	@Override
	public void flip() {
		// TODO Auto-generated method stub
		if (this.face == CoinFace.HEADS) {
			this.face = CoinFace.TAILS;
		}
		else {
			this.face = CoinFace.HEADS;
		}
		
	}

	@Override
	public boolean equals(Coin coin) {
		// TODO Auto-generated method stub
		boolean equal = false;
		if (this.face == coin.getFace()) {
			equal = true;
		} else {
			equal = false;
		}
		return equal;
	}
	
	public String toString() {
		return String.format("Coin %d: %s", getNumber(), getFace().value());
	}
	
}
