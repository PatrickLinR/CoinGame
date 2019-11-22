package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.enumeration.BetType;
import model.enumeration.GameStatus;
import model.interfaces.Player;
import view.AppFrame;

public class StartPlayerGameListener implements ActionListener {

	private AppFrame appFrame;
	private BetType betType;

	public StartPlayerGameListener(AppFrame appFrame) {
		// TODO Auto-generated constructor stub
		this.appFrame = appFrame;
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Player current = appFrame.getSelectedPlayer();
		if (appFrame.getGameEngine().getAllPlayers().size() < 1){
			JOptionPane.showMessageDialog(appFrame, "Add at least one player before spinning start!");
			return;
		} else if(current.getBetType().equals(betType.NO_BET)) {
			JOptionPane.showMessageDialog(appFrame, "Please place a bet first!");
			return;
		} else if(appFrame.checkInProcess()){
			JOptionPane.showMessageDialog(appFrame, "Someone is spinning now, please wait!");
			return;
		}
		new Thread() {
			public void run() {
				appFrame.updatePlayerStatusBar(current);
				appFrame.postSpinUIUpdate();
				if(appFrame.setPlayerStartResult(current)) {
					appFrame.getStatusBar().setPlayingLabel("Player ID: " + current.getPlayerId() + "  " + "Player Name: " + current.getPlayerName());
					appFrame.spinInProcess();
					appFrame.setPlayerInProcess(current);
					appFrame.getGameEngine().spinPlayer(current, appFrame.getInitialDelay1(), appFrame.getFinalDelay1(), appFrame.getDelayIncrement1(), appFrame.getInitialDelay2(), appFrame.getFinalDelay2(), appFrame.getDelayIncrement2());
					appFrame.setPlayerFinish(current);
					appFrame.spinEnd();
					if(appFrame.getSelectedPlayer() == current) {
						appFrame.updatePlayerStatusBar(current);
						appFrame.getStatusBar().setPlayingLabel("Waiting for spinning");
					}
					appFrame.checkAllPlayerSpin();
				}
			}
		}.start();
	}
	
	public void spinnerStart() {
		// TODO Auto-generated method stub
		new Thread() {
			
			public void run() {
				appFrame.spinnerSpinUIUpdate();
				appFrame.getToolBar().lockCombobox();
				appFrame.getStatusBar().setReadyStatusLabel(GameStatus.SPINNERSPINNING);
				appFrame.getGameEngine().spinSpinner(appFrame.getInitialDelay1(), appFrame.getFinalDelay1(), appFrame.getDelayIncrement1(), appFrame.getInitialDelay2(), appFrame.getFinalDelay2(), appFrame.getDelayIncrement2());
				appFrame.getToolBar().unlockCombobox();
				appFrame.preSpinUIUpdate();//reset gameUI
				appFrame.getStatusBar().setPlayingLabel("Game over, Waiting for next spinning");
			}
		}.start();
	}

}
