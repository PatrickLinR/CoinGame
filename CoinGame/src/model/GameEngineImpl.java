package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import model.enumeration.BetType;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {
	
	private List<Player> playerList = new ArrayList<>();
	private List<GameEngineCallback> gameEngineCallbackList = new ArrayList<>();
	private GameEngine gameEngine = this;
	private int totalDelay1;
	private int totalDelay2;

	@Override
	public void spinPlayer(Player player, int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2,
			int finalDelay2, int delayIncrement2) throws IllegalArgumentException {
		// TODO Auto-generated method stub		
		//if any of the delay params are < 0
		if (initialDelay1 < 0 || finalDelay1 < 0 || delayIncrement1 < 0 || 
			initialDelay2 < 0 || finalDelay2 < 0 || delayIncrement2 < 0) {
			throw new IllegalArgumentException("Delay is less than 0!");
		}
		//if either of the finalDelay < initialDelay
		if (finalDelay1 < initialDelay1 || finalDelay2 < initialDelay1) {
			throw new IllegalArgumentException("InitialDelay is larger than finalDelay!");
		}
		//if either of the delayIncrement > (finalDelay - initialDelay)
		if (delayIncrement1 > (finalDelay1 - initialDelay1) || delayIncrement2 > (finalDelay2 - initialDelay2)) {
			throw new IllegalArgumentException("DelayIncrement should be always less than the result of finalDelay minus initialDelay!");
		}  

		CoinPair coinpair = new CoinPairImpl();
		//Start spin
		new Thread() {
			public void run() {
				int currentDelay1 = initialDelay1;
				while(currentDelay1 <= finalDelay1) {
					coinpair.getCoin1().flip();
					player.setResult(coinpair);
					for(GameEngineCallback g : gameEngineCallbackList) {
						g.playerCoinUpdate(player, coinpair.getCoin1(), gameEngine);
					}
					currentDelay1 += delayIncrement1;
					try {
						Thread.sleep(currentDelay1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}//end of delay
				}
			}
		}.start();
		new Thread() {
			public void run() {
				int currentDelay2 = initialDelay2;
				while(currentDelay2 <= finalDelay2) {
					coinpair.getCoin2().flip();
					player.setResult(coinpair);
					for(GameEngineCallback g : gameEngineCallbackList) {
						g.playerCoinUpdate(player, coinpair.getCoin2(), gameEngine);
					}
					currentDelay2 += delayIncrement2;
					try {
						Thread.sleep(currentDelay2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}//end of delay
				}
			}
		}.start();//end of spin 
		// TotalDelay for player
		totalDelay1 = (initialDelay1+finalDelay1)*((finalDelay1-initialDelay1)/delayIncrement1 + 1)/2;
		totalDelay2 = (initialDelay2+finalDelay2)*((finalDelay2-initialDelay2)/delayIncrement2 + 1)/2;
		if(totalDelay1 > totalDelay2) {
			try {
				Thread.sleep(totalDelay1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//end of delay
		} else {
			try {
				Thread.sleep(totalDelay2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//end of delay
		}
		
		for(GameEngineCallback g : gameEngineCallbackList) {
			g.playerResult(player, coinpair, gameEngine);
		}
		
	}
//		while(currentDelay1 <= finalDelay1 || currentDelay2 <= finalDelay2) {
//			//Flip for coin 1
//			if(currentDelay1 <= finalDelay1) {
//				coinpair.getCoin1().flip();
//				for (int i = 0; i < gameEngineCallbackList.size(); i++) {
//				    gameEngineCallbackList.get(i).playerCoinUpdate(player, coinpair.getCoin1(), this);
//				}
//				
//				currentDelay1 += delayIncrement1;
//				try {
//					Thread.sleep(currentDelay1);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}//end of delay
//			}
//			//Flip for coin 2
//			if(currentDelay2 <= finalDelay2) {
//				coinpair.getCoin2().flip();
//				for (int i = 0; i < gameEngineCallbackList.size(); i++) {
//				    gameEngineCallbackList.get(i).playerCoinUpdate(player, coinpair.getCoin2(), this);
//				}
//				currentDelay2 += delayIncrement2;
//				try {
//					Thread.sleep(currentDelay2);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}//end of delay
//			}
//		}//end of spin
		

	@Override
	public void spinSpinner(int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2, int finalDelay2,
			int delayIncrement2) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		//if any of the delay params are < 0
		if (initialDelay1 < 0 || finalDelay1 < 0 || delayIncrement1 < 0 || 
			initialDelay2 < 0 || finalDelay2 < 0 || delayIncrement2 < 0) {
			throw new IllegalArgumentException("Delay is less than 0!");
		}
		//if either of the finalDelay < initialDelay
		if (finalDelay1 < initialDelay1 || finalDelay2 < initialDelay1) {
			throw new IllegalArgumentException("InitialDelay is larger than finalDelay!");
		}
		//if either of the delayIncrement > (finalDelay - initialDelay)
		if (delayIncrement1 > (finalDelay1 - initialDelay1) || delayIncrement2 > (finalDelay2 - initialDelay2)) {
			throw new IllegalArgumentException("DelayIncrement should be always less than the result of finalDelay minus initialDelay!");
		}
		
		CoinPair coinpair = new CoinPairImpl();
		new Thread() {
			public void run() {
				int currentDelay1 = initialDelay1;
				while(currentDelay1 <= finalDelay1) {
					coinpair.getCoin1().flip();
					for(GameEngineCallback g : gameEngineCallbackList) {
						g.spinnerCoinUpdate(coinpair.getCoin1(), gameEngine);
					}
					currentDelay1 += delayIncrement1;
					try {
						Thread.sleep(currentDelay1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}//end of delay
				}
			}
		}.start();
		new Thread() {
			public void run() {
				int currentDelay2 = initialDelay2;
				while(currentDelay2 <= finalDelay2) {
					coinpair.getCoin2().flip();
					for(GameEngineCallback g : gameEngineCallbackList) {
						g.spinnerCoinUpdate(coinpair.getCoin2(), gameEngine);
					}
					currentDelay2 += delayIncrement2;
					try {
						Thread.sleep(currentDelay2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}//end of delay
				}
			}
		}.start();//end of spin 
		// TotalDelay for spinner
		totalDelay1 = (initialDelay1+finalDelay1)*((finalDelay1-initialDelay1)/delayIncrement1 + 1)/2;
		totalDelay2 = (initialDelay2+finalDelay2)*((finalDelay2-initialDelay2)/delayIncrement2 + 1)/2;
		if(totalDelay1 > totalDelay2) {
			try {
				Thread.sleep(totalDelay1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//end of delay
		} else {
			try {
				Thread.sleep(totalDelay2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//end of delay
		}
		
		applyBetResults(coinpair);
		for (int i = 0; i < gameEngineCallbackList.size(); i++) {
		    gameEngineCallbackList.get(i).spinnerResult(coinpair, this);
		}
	}
//	while(currentDelay1 <= finalDelay1 || currentDelay2 <= finalDelay2) {
//		//Flip for coin 1
//		if(currentDelay1 <= finalDelay1) {
//			coinpair.getCoin1().flip();
//			for (int i = 0; i < gameEngineCallbackList.size(); i++) {
//			    gameEngineCallbackList.get(i).spinnerCoinUpdate(coinpair.getCoin1(), this);
//			}
//			currentDelay1 += delayIncrement1;
//			try {
//				Thread.sleep(currentDelay1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}//end of delay
//		}
//		//Flip for coin 2
//		if(currentDelay2 <= finalDelay2) {
//			coinpair.getCoin2().flip();
//			for (int i = 0; i < gameEngineCallbackList.size(); i++) {
//			    gameEngineCallbackList.get(i).spinnerCoinUpdate(coinpair.getCoin2(), this);
//			}
//			currentDelay2 += delayIncrement2;
//			try {
//				Thread.sleep(currentDelay2);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}//end of delay
//		}
//	}//end of spin

	@Override
	public void applyBetResults(CoinPair spinnerResult) {
		// TODO Auto-generated method stub
		for (int i = 0; i < playerList.size(); ++i) {
			playerList.get(i).getBetType().applyWinLoss(playerList.get(i), spinnerResult);
		}
	}
	
	

	@Override
	public void addPlayer(Player player) {
		// TODO Auto-generated method stub
		Iterator<Player> playerIter = playerList.iterator();
		Player testPlayer = null;
		while (playerIter.hasNext()) {
			testPlayer = playerIter.next();
			if (testPlayer.getPlayerId().equals(player.getPlayerId())) {
				playerIter.remove();
			} 
		}
		playerList.add(player);
	}

	@Override
	public Player getPlayer(String id) {
		// TODO Auto-generated method stub
		Player thePlayer = null;
		for (Player player : playerList) {
			if(Integer.parseInt(player.getPlayerId()) == Integer.parseInt(id)) {
				thePlayer = player;
			}
		}
		return thePlayer;
	}

	@Override
	public boolean removePlayer(Player player) {
		// TODO Auto-generated method stub
		return playerList.remove(player);
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		// TODO Auto-generated method stub
		gameEngineCallbackList.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		// TODO Auto-generated method stub
		return gameEngineCallbackList.remove(gameEngineCallback);
	}

	@Override
	public Collection<Player> getAllPlayers() {
		// TODO Auto-generated method stub
		return Collections.unmodifiableList(playerList);
	}

	@Override
	public boolean placeBet(Player player, int bet, BetType betType) {
		// TODO Auto-generated method stub
		if (player.setBet(bet)) {
			player.setBetType(betType);
			return true;
		} else {
			return false;
		}
	}

}
