package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import view.AddPlayerDialog;
import view.AppFrame;
import view.CoinPanel;
import view.CoinToolBar;
import view.StatusBarPanel;

public class AddPlayerListener implements ActionListener {
	private AppFrame appFrame;

	public AddPlayerListener(AppFrame appFrame) {
		// TODO Auto-generated constructor stub
		this.appFrame = appFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		AddPlayerDialog apd = new AddPlayerDialog(appFrame);
		apd.setVisible(true);
	}

}
