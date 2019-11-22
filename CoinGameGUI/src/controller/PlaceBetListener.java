package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import view.AppFrame;
import view.PlaceBetDialog;

public class PlaceBetListener implements ActionListener {
	

	private AppFrame appFrame;
	private GameEngine gameEngine;

	public PlaceBetListener(AppFrame appFrame, GameEngine gameEngine) {
		// TODO Auto-generated constructor stub
		this.appFrame = appFrame;
		this.gameEngine = gameEngine;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(appFrame.getSelectedPlayer() == null) {
			JOptionPane.showMessageDialog(appFrame, "Please add at least 1 player first!");
			return;
		} else {
			PlaceBetDialog placeBet = new PlaceBetDialog(gameEngine, appFrame);
			placeBet.setVisible(true);
		}

	}
	

}
