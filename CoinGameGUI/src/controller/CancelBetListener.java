package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AppFrame;
import view.PlaceBetDialog;

public class CancelBetListener implements ActionListener {

	private GameEngine gameEngine;
	private AppFrame appFrame;

	public CancelBetListener(AppFrame appFrame, GameEngine gameEngine) {
		// TODO Auto-generated constructor stub
		this.appFrame = appFrame;
		this.gameEngine = gameEngine;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Player current = appFrame.getSelectedPlayer();
		if(current == null) {
			JOptionPane.showMessageDialog(appFrame, "Please add at least 1 player first!");
			return;
		} else {
			appFrame.cancelBet(current);
			appFrame.getsummaryPanel().updatePlayerInfo(current);
			appFrame.updatePlayerStatusBar(current);
		}
	}
	


}
