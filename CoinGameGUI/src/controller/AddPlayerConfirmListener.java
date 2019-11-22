package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.enumeration.GameStatus;
import model.interfaces.GameEngine;
import view.AddPlayerDialog;
import view.AppFrame;

public class AddPlayerConfirmListener implements ActionListener {

	private AddPlayerDialog addPlayer;
	private AppFrame appFrame;

	public AddPlayerConfirmListener(AppFrame appFrame, AddPlayerDialog addPlayerDialog) {
		// TODO Auto-generated constructor stub
//		super();
		this.addPlayer = addPlayerDialog;
		this.appFrame = appFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.addPlayer.addPlayer();
	}

}
