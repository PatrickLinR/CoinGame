package view;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {
	private AppFrame appFrame;
	
	
	public GameEngineCallbackGUI(AppFrame appFrame) {
		this.appFrame = appFrame;
	}
	
	@Override
	public void playerCoinUpdate(Player player, Coin coin, GameEngine engine) {
		// TODO Auto-generated method stub

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if(appFrame.getSelectedPlayer() == player) {
					if (coin.getNumber() == 1) {
						appFrame.getCoinPanel().updateCoin1Face(coin);
					} else {
						appFrame.getCoinPanel().updateCoin2Face(coin);
					}
				} else {
					if(appFrame.getSelectedPlayer().getResult() == null) {
						appFrame.getCoinPanel().beforeStart();
					} else {
						appFrame.getCoinPanel().updateCoin1Face(appFrame.getSelectedPlayer().getResult().getCoin1());
						appFrame.getCoinPanel().updateCoin2Face(appFrame.getSelectedPlayer().getResult().getCoin2());
					}
				}
			}
		});
	}

	@Override
	public void spinnerCoinUpdate(Coin coin, GameEngine engine) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				appFrame.getStatusBar().setPlayingLabel("Spinner");
				if (coin.getNumber() == 1) {
					appFrame.getCoinPanel().updateCoin1Face(coin);
				} else {
					appFrame.getCoinPanel().updateCoin2Face(coin);
				}
			}
		});
	}

	@Override
	public void playerResult(Player player, CoinPair coinPair, GameEngine engine) {
		// TODO Auto-generated method stub
//		appFrame.updatePlayerInformation(player);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
//				if(appFrame.getSelectedPlayer() == player) {
//					appFrame.updatePlayerInformation(player);
//				}
				appFrame.pointsBefore(player);
				appFrame.getsummaryPanel().updatePlayerInfo(player);
				
			}
		});
	}

	@Override
	public void spinnerResult(CoinPair coinPair, GameEngine engine) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
//				FinalResultDialog finalResult = new FinalResultDialog(appFrame, engine, coinPair);
//				finalResult.setVisible(true);
				appFrame.getsummaryPanel().updateAllPlayerInfo();
				appFrame.updatePlayerStatusBar(appFrame.getSelectedPlayer());
				JOptionPane.showMessageDialog(appFrame, String.format("Spinner result: %s, all results have refreshed!", coinPair));
			}
		});
	}

}
