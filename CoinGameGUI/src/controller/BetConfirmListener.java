package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.PlaceBetDialog;

public class BetConfirmListener implements ActionListener {

	private PlaceBetDialog placeBet;

	public BetConfirmListener(PlaceBetDialog placeBetDialog) {
		// TODO Auto-generated constructor stub
		this.placeBet = placeBetDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.placeBet.placeBet();
	}

}
